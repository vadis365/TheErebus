package erebus.block.entity;

import erebus.inventory.server.BlenderMenu;
import erebus.recipes.smoothie.SmoothieRecipe;
import erebus.recipes.smoothie.SmoothieRecipeInput;
import erebus.registries.ModCustomRecipes;
import erebus.registries.blocks.ModBlockEntities;
import erebus.registries.data.FluidContents;
import erebus.registries.data.ModDataComponents;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;

public class BlenderBlockEntity extends BlockEntityInventoryHelper implements MenuProvider {

    public final RecipeManager.CachedCheck<SmoothieRecipeInput, SmoothieRecipe> quickCheck = RecipeManager.createCheck(ModCustomRecipes.SMOOTHIE_RECIPE.get());
    public static FluidTank[] tanks = new FluidTank[4];
    private static final int MAX_TIME = 432;
    private static int progress = 0;
    private static int prevProgress = 0;
    protected ItemStack output = ItemStack.EMPTY;

    public BlenderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLENDER.get(), 5, pos, state);

        for(int c = 0; c < tanks.length; c++) {
            tanks[c] = new FluidTank(FluidType.BUCKET_VOLUME * 8);
        }
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T entity) {
        if (entity instanceof BlenderBlockEntity blender) {
            if(!level.isClientSide()) {
                prevProgress = progress;
                return;
            }

            NonNullList<ItemStack> itemInputs = NonNullList.create();
            for(int c = 0; c < 4; c++) {
                itemInputs.add(blender.getItem(c));
            }

            NonNullList<SizedFluidIngredient> fluidInputs = NonNullList.create();
            for(int c = 0; c < 4; c++) {
                FluidStack fluid = blender.getTanks(null)[c].getFluid();
                if(!fluid.isEmpty()) {
                    fluidInputs.add(SizedFluidIngredient.of(fluid));
                }
            }

            SmoothieRecipeInput input = new SmoothieRecipeInput(fluidInputs, itemInputs);

            Optional<RecipeHolder<SmoothieRecipe>> optional = blender.quickCheck.getRecipeFor(input, level);

            if(optional.isPresent()) {
                SmoothieRecipe recipe = optional.get().value();
                blender.output = recipe.assemble(input, level.registryAccess());
                progress++;

                if(progress >= MAX_TIME) {
                    for(int c = 0; c < 5; c++) {
                        if(!blender.getItem(c).isEmpty()) {
                            blender.getItem(c).shrink(1);
                        }
                    }

                    extractFluids(recipe);
                    blender.setItem(4, blender.output.copy());
                    progress = 0;
                    setChanged(level, pos, blockState);
                }
            }
        }
    }

    private static void extractFluids(SmoothieRecipe recipe) {
        for(SizedFluidIngredient fluid : recipe.getFluidIngredients()) {
            for(FluidTank tank : tanks) {
                if(tank.getFluid().is(fluid.getFluids()[0].getFluid())) {
                    tank.drain(fluid.amount(), IFluidHandler.FluidAction.EXECUTE);
                }
            }
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, ItemStack itemStack, @Nullable Direction direction) {
        return true;
    }

    @Override
    public boolean canTakeItemThroughFace(int i, ItemStack itemStack, Direction direction) {
        return true;
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        return null;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("erebus.container.blender");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new BlenderMenu(containerId, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(worldPosition));
    }

    public FluidTank getTank0(@Nullable Direction direction) {
        return tanks[0];
    }

    public FluidTank getTank1(@Nullable Direction direction) {
        return tanks[1];
    }

    public FluidTank getTank2(@Nullable Direction direction) {
        return tanks[2];
    }

    public FluidTank getTank3(@Nullable Direction direction) {
        return tanks[3];
    }

    public FluidTank[] getTanks(@Nullable Direction direction) {
        return tanks;
    }

    public float getBlendProgress() {
        return progress / 12F;
    }

    public float getPrevBlendProgress() {
        return prevProgress / 12F;
    }

    public boolean isBlending() {
        return progress > 0;
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(tag, registries);
        for (FluidTank tank : tanks) {
            tank.writeToNBT(registries, tag);
        }
    }

    @Override
    public void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);
        for (FluidTank tank : tanks) {
            tank.readFromNBT(registries, tag);
        }
    }

    @Override
    protected void applyImplicitComponents(@Nonnull DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);

        for (FluidTank tank : tanks) {
            tank.setFluid(componentInput.getOrDefault(ModDataComponents.FLUID, FluidContents.EMPTY).get());
        }
    }

    @Override
    protected void collectImplicitComponents(@Nonnull DataComponentMap.Builder builder) {
        super.collectImplicitComponents(builder);

        for (FluidTank tank : tanks) {
            builder.set(ModDataComponents.FLUID, FluidContents.of(tank.getFluid()));
        }
    }
}
