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
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.network.Connection;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;

public class BlenderBlockEntity extends BlockEntityInventoryHelper implements MenuProvider {

    public final FluidStacksResourceHandler tanks = new FluidStacksResourceHandler(4, FluidType.BUCKET_VOLUME * 8);
    public final RecipeManager.CachedCheck<SmoothieRecipeInput, SmoothieRecipe> quickCheck = RecipeManager.createCheck(ModCustomRecipes.SMOOTHIE_RECIPE.get());
    private static final int MAX_TIME = 432;
    private static int progress = 0;
    private static int prevProgress = 0;
    protected ItemStack output = ItemStack.EMPTY;

    public BlenderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLENDER.get(), 5, pos, state);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T entity) {
        if (entity instanceof BlenderBlockEntity blender) {
            if(level.isClientSide()) {
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
                FluidResource fluid = blender.tanks.getResource(c);
                if(!fluid.isEmpty()) {
                    fluidInputs.add(SizedFluidIngredient.of(fluid.getFluid(), FluidType.BUCKET_VOLUME));
                }
            }

            SmoothieRecipeInput input = new SmoothieRecipeInput(fluidInputs, itemInputs);

            Optional<RecipeHolder<SmoothieRecipe>> optional = blender.quickCheck.getRecipeFor(input, (ServerLevel) level);

            if(optional.isPresent()) {
                SmoothieRecipe recipe = optional.get().value();
                blender.output = recipe.assemble(input);
                progress++;

                if(progress >= MAX_TIME) {
                    for(int c = 0; c < 5; c++) {
                        if(!blender.getItem(c).isEmpty()) {
                            blender.getItem(c).shrink(1);
                        }
                    }

                    blender.extractFluids(recipe);
                    blender.setItem(4, blender.output.copy());
                    progress = 0;
                    setChanged(level, pos, blockState);
                }
            }
        }
    }

    private void extractFluids(SmoothieRecipe recipe) {
        try(Transaction tx = Transaction.openRoot()) {
            for (SizedFluidIngredient fluidIngredient : recipe.getFluidIngredients()) {
                for (int c = 0; c < 4; c++) {
                    FluidResource resource = tanks.getResource(c);
                    if (fluidIngredient.test(new FluidStack(resource.getFluid(), tanks.getAmountAsInt(c)))) {
                        tanks.extract(resource, fluidIngredient.amount(), tx);
                        break;
                    }
                }
            }
            tx.commit();
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
    public void saveAdditional(@NotNull ValueOutput output) {
        super.saveAdditional(output);
        tanks.serialize(output);
        output.putInt("progress", progress);
    }

    @Override
    public void loadAdditional(@NotNull ValueInput input) {
        super.loadAdditional(input);
        tanks.deserialize(input);
        progress = input.getIntOr("progress", 0);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(@NotNull Connection net, @NotNull ValueInput input) {
        super.onDataPacket(net, input);
        loadAdditional(input);
    }

    @Override
    protected void applyImplicitComponents(@Nonnull DataComponentGetter getter) {
        super.applyImplicitComponents(getter);
        try(Transaction transaction = Transaction.openRoot()) {
            if(tanks.insert(getter.getOrDefault(ModDataComponents.FLUID, FluidResource.EMPTY), FluidType.BUCKET_VOLUME, transaction) == FluidType.BUCKET_VOLUME) {
                transaction.commit();
            }
        }
    }

    @Override
    protected void collectImplicitComponents(@Nonnull DataComponentMap.Builder builder) {
        super.collectImplicitComponents(builder);
        builder.set(ModDataComponents.FLUID, tanks.getResource(0));
    }
}
