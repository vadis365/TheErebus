package erebus.block.entity;

import erebus.inventory.server.BlenderMenu;
import erebus.recipes.smoothie.SmoothieRecipe;
import erebus.recipes.smoothie.SmoothieRecipeInput;
import erebus.registries.ModCustomRecipes;
import erebus.registries.blocks.ModBlockEntities;
import erebus.registries.data.ModDataComponents;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
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
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;

public class BlenderBlockEntity extends BlockEntityInventoryHelper implements MenuProvider {

    public final RecipeManager.CachedCheck<SmoothieRecipeInput, SmoothieRecipe> quickCheck = RecipeManager.createCheck(ModCustomRecipes.SMOOTHIE_RECIPE.get());
    public static NamedFluidTank[] tanks = new NamedFluidTank[4];
    private static final int MAX_TIME = 432;
    private static int progress = 0;
    private static int prevProgress = 0;
    protected ItemStack output = ItemStack.EMPTY;

    public BlenderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLENDER.get(), 5, pos, state);

        for(int c = 0; c < tanks.length; c++) {
            tanks[c] = new NamedFluidTank("tank_%d".formatted(c), FluidType.BUCKET_VOLUME * 8);
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
                if(!blender.getItem(c).isEmpty())
                    itemInputs.add(blender.getItem(c));
            }

            NonNullList<SizedFluidIngredient> fluidInputs = NonNullList.create();
            for(int c = 0; c < 4; c++) {
                FluidStack fluid = blender.getTank(c).getFluid();
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
            for(NamedFluidTank tank : tanks) {
                if(tank.getFluid().is(fluid.getFluids()[0].getFluid())) {
                    tank.drain(fluid.amount(), IFluidHandler.FluidAction.EXECUTE);
                }
            }
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, @NotNull ItemStack itemStack, @Nullable Direction direction) {
        return true;
    }

    @Override
    public boolean canTakeItemThroughFace(int i, @NotNull ItemStack itemStack, @NotNull Direction direction) {
        return true;
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(getItems(), slot);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("erebus.container.blender");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory, @NotNull Player player) {
        return new BlenderMenu(containerId, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(worldPosition));
    }

    public NamedFluidTank getTank0(@Nullable Direction ignoredDirection) {
        return tanks[0];
    }

    public NamedFluidTank getTank1(@Nullable Direction ignoredDirection) {
        return tanks[1];
    }

    public NamedFluidTank getTank2(@Nullable Direction ignoredDirection) {
        return tanks[2];
    }

    public NamedFluidTank getTank3(@Nullable Direction ignoredDirection) {
        return tanks[3];
    }

    public NamedFluidTank getTank(int tank) {
        return tanks[tank];
    }

    public NamedFluidTank[] getTanks(@Nullable Direction ignoredDirection) {
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
        for (NamedFluidTank tank : tanks) {
            tank.writeToNBT(registries, tag);
        }
        tag.putInt("progress", progress);
    }

    @Override
    public void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);
        for (NamedFluidTank tank : tanks) {
            tank.readFromNBT(registries, tag);
        }
        progress = tag.getInt("progress");
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider registries) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, Objects.requireNonNull(level).registryAccess());
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(@NotNull Connection net, @NotNull ClientboundBlockEntityDataPacket pkt, HolderLookup.@NotNull Provider lookupProvider) {
        super.onDataPacket(net, pkt, lookupProvider);
        loadAdditional(pkt.getTag(), lookupProvider);
    }

    @Override
    protected void applyImplicitComponents(@Nonnull DataComponentGetter getter) {
        super.applyImplicitComponents(getter);

        for (NamedFluidTank tank : tanks) {
            tank.setFluid(getter.getOrDefault(ModDataComponents.FLUID, FluidContents.EMPTY).get());
        }
    }

    @Override
    protected void collectImplicitComponents(@Nonnull DataComponentMap.Builder builder) {
        super.collectImplicitComponents(builder);

        for (NamedFluidTank tank : tanks) {
            builder.set(ModDataComponents.FLUID, FluidContents.of(tank.getFluid()));
        }
    }
}
