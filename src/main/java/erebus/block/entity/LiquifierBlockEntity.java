package erebus.block.entity;

import erebus.block.LiquifierBlock;
import erebus.inventory.server.LiquifierMenu;
import erebus.registries.ModFluids;
import erebus.registries.blocks.ModBlockEntities;
import erebus.registries.data.ModDataComponents;
import erebus.registries.item.ModItems;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.network.Connection;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class LiquifierBlockEntity extends BlockEntityInventoryHelper implements MenuProvider {
	public FluidStacksResourceHandler tank = new FluidStacksResourceHandler(1, FluidType.BUCKET_VOLUME * 8);
	public boolean active;
	public int operatingTime;
	public int animationTicks, prevAnimationTicks;
	public int prevTankAmount;
    private static final int[] SLOTS = new int[] {0};

	public LiquifierBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.LIQUIFIER.get(), 1,  pos, state);
	}

	public static <T extends BlockEntity> void clientTick(Level world, BlockPos worldPosition, BlockState blockState, T t) {
		if (t instanceof LiquifierBlockEntity tile) {
			if (tile.active) {
				tile.prevAnimationTicks = tile.animationTicks;
				if (tile.animationTicks < 360)
					tile.animationTicks += 18;
				if (tile.animationTicks >= 360) {
					tile.animationTicks -= 360;
					tile.prevAnimationTicks -= 360;
				}
			}
			else
				tile.prevAnimationTicks = tile.animationTicks = 0;
		}
	}

	public static <T extends BlockEntity> void serverTick(Level level, BlockPos pos, BlockState state, T t) {
		if (t instanceof LiquifierBlockEntity tile) {
			boolean isDirty = false;

			if(tile.prevTankAmount != tile.tank.getAmountAsInt(0)) {
				isDirty = true;
				tile.setChanged();
			}

			tile.prevTankAmount = tile.tank.getAmountAsInt(0);

			if (level.getBlockState(pos).getValue(LiquifierBlock.POWERED)) {
				if (tile.canOperate()) {
					++tile.operatingTime;
					isDirty = true;
					if (tile.operatingTime >= 180) {
						tile.operatingTime = 0;
						tile.liquifyItem();
					}
				} else {
					if (tile.operatingTime != 0) {
						tile.operatingTime = 0;
						isDirty = true;
					}
				}
			} else if (tile.operatingTime != 0) {
				tile.operatingTime = 0;
				isDirty = true;
			}

			if (isDirty)
				tile.updateBlock();
		}
	}

	private boolean canOperate() {
		if (getItems().get(0).isEmpty()) {
			return false;
		}
		else {
			ItemStack stack = getItems().get(0);
			if (stack.getItem() == ModItems.HONEY_DRIP.get()) {
                return tank.getAmountAsInt(0) <= FluidType.BUCKET_VOLUME * 8 - 50 && (tank.getResource(0).isEmpty() || tank.getResource(0).is(ModFluids.HONEY_STILL.get()));
			}
			return false;
		}
	}

	public void liquifyItem() {
		if (canOperate()) {
			if (tank.getAmountAsInt(0) <= FluidType.BUCKET_VOLUME * 8 - 50 && (tank.getResource(0).isEmpty() || tank.getResource(0).is(ModFluids.HONEY_STILL.get()))) {
				try(Transaction tx = Transaction.openRoot()) {
					if(tank.insert(FluidResource.of(ModFluids.HONEY_STILL.get()), 50, tx) == 50) {
						tx.commit();
						getItems().get(0).shrink(1);
						if (getItems().get(0).getCount() <= 0)
							getItems().set(0, ItemStack.EMPTY);
					}
				}
			}
		}
	}

	public void setActive(boolean isActive) {
		active = isActive;
		updateBlock();
	}

	public void updateBlock() {
		getLevel().sendBlockUpdated(worldPosition, getLevel().getBlockState(worldPosition), getLevel().getBlockState(worldPosition), 3);
	}

	@Override
	public void saveAdditional(@NonNull ValueOutput output) {
		super.saveAdditional(output);
		tank.serialize(output);
		output.putInt("operatingTime", operatingTime);
		output.putBoolean("active", active);
	}

	@Override
	public void loadAdditional(@NonNull ValueInput input) {
		super.loadAdditional(input);
		tank.deserialize(input);
		operatingTime = input.getIntOr("operatingTime", 0);
		active = input.getBooleanOr("active", false);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void onDataPacket(@NonNull Connection net, @NonNull ValueInput input) {
		super.onDataPacket(net, input);
		loadAdditional(input);
	}

	@Override
	public int getMaxStackSize() {
		return 64;
	}

	public FluidStacksResourceHandler getTank() {
		return this.tank;
	}

	public FluidStacksResourceHandler getTank(@Nullable Direction direction) {
		return this.tank;
	}

	@Override
	protected void applyImplicitComponents(@Nonnull DataComponentGetter getter) {
		super.applyImplicitComponents(getter);
		FluidResource resource = getter.getOrDefault(ModDataComponents.FLUID, FluidResource.EMPTY);
		if (!resource.isEmpty()) {
			try (Transaction tx = Transaction.openRoot()) {
				if (tank.insert(resource, FluidType.BUCKET_VOLUME, tx) == FluidType.BUCKET_VOLUME) {
					tx.commit();
				}
			}
		}
	}

	@Override
	protected void collectImplicitComponents(@Nonnull DataComponentMap.Builder builder) {
		super.collectImplicitComponents(builder);
		builder.set(ModDataComponents.FLUID, tank.getResource(0));
	}

	public int getScaledFluid(int scale) {
		return (int) ((float) tank.getAmountAsInt(0) / (float) (FluidType.BUCKET_VOLUME * 8) * scale);
	}

	public int getOperationProgressScaled(int time) {
		return operatingTime * time / 180;
	}

	@Override
	public int @NotNull [] getSlotsForFace(@NotNull Direction side) {
		return SLOTS;
	}

	@Override
	public boolean canPlaceItem(int slot, @NotNull ItemStack stack) {
		return stack.getItem() == ModItems.HONEY_DRIP.get();
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, @NotNull ItemStack stack, @Nullable Direction direction) {
		return canPlaceItem(index, stack);
	}

	@Override
	public boolean canTakeItemThroughFace(int index, @NotNull ItemStack stack, @NotNull Direction direction) {
		return stack.getItem() == ModItems.HONEY_DRIP.get();
	}

	@Override
	public @NotNull ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(getItems(), slot);
	}

	@Override
	public @NotNull AbstractContainerMenu createMenu(int containerId, @NotNull Inventory playerInventory, @NotNull Player player) {
		return new LiquifierMenu(containerId, playerInventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(worldPosition));
	}

	@Override
	public @NotNull Component getDisplayName() {
		return Component.translatable("erebus.container.liquifier");
	}
}
