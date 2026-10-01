package erebus.block.entity;

import erebus.inventory.server.UmberFurnaceMenu;
import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.transfer.ResourceHandlerUtil;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.VanillaContainerWrapper;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jetbrains.annotations.Nullable;

public class UmberFurnaceBlockEntity extends AbstractFurnaceBlockEntity {
    public static final int TANK_CAPACITY = FluidType.BUCKET_VOLUME * 16;
    public static final int BUCKET_SLOT = 3;
    private static final int[] SLOTS_FOR_UP = {0};
    private static final int[] SLOTS_FOR_DOWN = {2, 3, 1};
    private static final int[] SLOTS_FOR_SIDES = {3, 1, 0};
    private final FluidStacksResourceHandler tank = new FluidStacksResourceHandler(1, TANK_CAPACITY) {
        @Override
        public boolean isValid(int index, FluidResource resource) {
            return resource.is(Fluids.LAVA);
        }

        @Override
        protected void onContentsChanged(int index, FluidStack previous) {
            setChanged();
        }
    };
    private final ContainerData menuData = new ContainerData() {
        public int get(int index) {
            return index == 4 ? tank.getAmountAsInt(0) : dataAccess.get(index);
        }

        public void set(int index, int value) {
            if (index < 4) dataAccess.set(index, value);
        }

        public int getCount() {
            return 5;
        }
    };

    public UmberFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.UMBERFURNACE.get(), pos, state, RecipeType.SMELTING);
        // Keep native input/fuel/result indices so existing native saves remain valid.
        items = NonNullList.withSize(4, ItemStack.EMPTY);
    }

    public static void serverTick(ServerLevel level, BlockPos pos, BlockState state, UmberFurnaceBlockEntity furnace) {
        if (!furnace.getItem(BUCKET_SLOT).isEmpty()) {
            var access = ItemAccess.forHandlerIndexStrict(VanillaContainerWrapper.of(furnace), BUCKET_SLOT);
            var source = access.getCapability(Capabilities.Fluid.ITEM);
            try (var transaction = Transaction.openRoot()) {
                if (ResourceHandlerUtil.move(source, furnace.tank, resource -> resource.is(Fluids.LAVA), FluidType.BUCKET_VOLUME, transaction) == FluidType.BUCKET_VOLUME) {
                    transaction.commit();
                }
            }
        }
        int baseTime = level.recipeAccess().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(furnace.getItem(0)), level)
                .map(recipe -> recipe.value().cookingTime()).orElse(200);
        int lavaAmount = furnace.tank.getResource(0).is(Fluids.LAVA) ? furnace.tank.getAmountAsInt(0) : 0;
        int duration = Math.max(1, baseTime - (int) (baseTime * 0.8F * lavaAmount / TANK_CAPACITY));
        furnace.dataAccess.set(DATA_COOKING_TOTAL_TIME, duration);
        if (furnace.dataAccess.get(DATA_COOKING_PROGRESS) >= duration) furnace.dataAccess.set(DATA_COOKING_PROGRESS, duration - 1);
        int inputCount = furnace.getItem(0).getCount();
        AbstractFurnaceBlockEntity.serverTick(level, pos, state, furnace);
        if (furnace.getItem(0).getCount() < inputCount && furnace.tank.getResource(0).is(Fluids.LAVA)) {
            try (var transaction = Transaction.openRoot()) {
                furnace.tank.extract(FluidResource.of(Fluids.LAVA), FluidType.BUCKET_VOLUME / 10, transaction);
                transaction.commit();
            }
        }
        furnace.dataAccess.set(DATA_COOKING_TOTAL_TIME, duration);
    }

    public FluidStacksResourceHandler getTank() {
        return tank;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        tank.serialize(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        tank.deserialize(input);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("erebus.container.umberfurnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new UmberFurnaceMenu(id, inventory, this, menuData);
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return side == Direction.DOWN ? SLOTS_FOR_DOWN : side == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return slot == BUCKET_SLOT ? stack.getCapability(Capabilities.Fluid.ITEM, null) != null : super.canPlaceItem(slot, stack);
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction side) {
        return canPlaceItem(slot, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction side) {
        if (slot == BUCKET_SLOT) return stack.is(Items.BUCKET);
        return super.canTakeItemThroughFace(slot, stack, side);
    }
}
