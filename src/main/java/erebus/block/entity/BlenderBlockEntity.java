package erebus.block.entity;

import erebus.inventory.server.BlenderMenu;
import erebus.recipes.smoothie.SmoothieRecipe;
import erebus.recipes.smoothie.SmoothieRecipeInput;
import erebus.registries.ModCustomRecipes;
import erebus.registries.blocks.ModBlockEntities;
import erebus.registries.data.ModDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class BlenderBlockEntity extends BlockEntityInventoryHelper implements MenuProvider {

    public static final int TANK_CAPACITY = FluidType.BUCKET_VOLUME * 8;
    private static final int MAX_TIME = 432;
    public final FluidStacksResourceHandler tanks = new FluidStacksResourceHandler(4, TANK_CAPACITY) {
        @Override
        protected void onContentsChanged(int index, FluidStack previous) {
            setChanged();
        }
    };
    public final RecipeManager.CachedCheck<SmoothieRecipeInput, SmoothieRecipe> quickCheck = RecipeManager.createCheck(ModCustomRecipes.SMOOTHIE_RECIPE.get());
    private final ContainerData data = new ContainerData() {
        public int get(int index) {
            return progress;
        }

        public void set(int index, int value) {
            progress = value;
        }

        public int getCount() {
            return 1;
        }
    };
    private int progress = 0;
    private int prevProgress = 0;

    public BlenderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLENDER.get(), 5, pos, state);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T entity) {
        if (!(entity instanceof BlenderBlockEntity blender)) return;
        if (level.isClientSide()) {
            blender.prevProgress = blender.progress;
            return;
        }
        var input = blender.recipeInput();
        var match = blender.quickCheck.getRecipeFor(input, (ServerLevel) level);
        int previous = blender.progress;
        if (match.isEmpty()) {
            blender.progress = 0;
        } else {
            blender.progress++;
            if (blender.progress >= MAX_TIME) {
                blender.craft(match.get().value(), input);
                blender.progress = 0;
            }
        }
        if (previous != blender.progress) blender.setChanged();
    }

    private SmoothieRecipeInput recipeInput() {
        var fluids = new ArrayList<FluidStack>();
        for (int tank = 0; tank < 4; tank++) fluids.add(tanks.getResource(tank).toStack(tanks.getAmountAsInt(tank)));
        return new SmoothieRecipeInput(fluids, getItems());
    }

    private void craft(SmoothieRecipe recipe, SmoothieRecipeInput input) {
        int[] items = recipe.itemConsumption(input);
        int[] fluids = recipe.fluidAssignments(input);
        if (items == null || fluids == null) return;
        try (Transaction transaction = Transaction.openRoot()) {
            for (int ingredient = 0; ingredient < fluids.length; ingredient++) {
                int tank = fluids[ingredient];
                int amount = recipe.getFluidIngredients().get(ingredient).amount();
                if (tanks.extract(tank, tanks.getResource(tank), amount, transaction) != amount) return;
            }
            transaction.commit();
        }
        for (int slot = 0; slot < 4; slot++) getItem(slot).shrink(items[slot]);
        setItem(4, recipe.assemble(input));
        setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return BlenderMenu.isContainer(stack) ? 1 : super.getMaxStackSize(stack);
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return slot == 4 ? BlenderMenu.isContainer(stack) && getItem(4).isEmpty() : !BlenderMenu.isContainer(stack);
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, @NotNull ItemStack itemStack, @Nullable Direction direction) {
        return canPlaceItem(i, itemStack);
    }

    @Override
    public boolean canTakeItemThroughFace(int i, @NotNull ItemStack itemStack, @NotNull Direction direction) {
        return i == 4 && !BlenderMenu.isContainer(itemStack);
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
        return new BlenderMenu(containerId, inventory, this, data, tanks);
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
        FluidResource resource = getter.getOrDefault(ModDataComponents.FLUID, FluidResource.EMPTY);
        if (!resource.isEmpty()) {
            try (Transaction transaction = Transaction.openRoot()) {
                if (tanks.insert(resource, FluidType.BUCKET_VOLUME, transaction) == FluidType.BUCKET_VOLUME) {
                    transaction.commit();
                }
            }
        }
    }

    @Override
    protected void collectImplicitComponents(@Nonnull DataComponentMap.Builder builder) {
        super.collectImplicitComponents(builder);
        builder.set(ModDataComponents.FLUID, tanks.getResource(0));
    }
}
