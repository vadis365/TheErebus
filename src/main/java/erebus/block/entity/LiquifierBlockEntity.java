package erebus.block.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import erebus.block.Liquifier;
import erebus.inventory.server.LiquifierMenu;
import erebus.registries.ModBlockEntities;
import erebus.registries.ModFluids;
import erebus.registries.ModItems;
import erebus.registries.data.FluidContents;
import erebus.registries.data.ModDataComponents;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public class LiquifierBlockEntity extends BlockEntityInventoryHelper implements MenuProvider {
	public FluidTank tank = new FluidTank(FluidType.BUCKET_VOLUME * 8);
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
			if(tile.prevTankAmount != tile.tank.getFluidAmount()) {
				tile.updateBlock();
				tile.setChanged();
			}
			tile.prevTankAmount = tile.tank.getFluidAmount();

			if (level.getBlockState(pos).getValue(Liquifier.POWERED)) {
				boolean isDirty = false;
				
				if (!tile.getItems().get(0).isEmpty()) {
					if (tile.canOperate()) {
						++tile.operatingTime;

						if (tile.operatingTime >= 180) {
							tile.operatingTime = 0;
							tile.liquifyItem();
							isDirty = true;
						}
					} else
						tile.operatingTime = 0;
					tile.updateBlock(); // TODO remove this and sort out the gui update properly
				}

				if (isDirty)
					tile.updateBlock();
			}
			else if(tile.operatingTime != 0)
				tile.operatingTime = 0;
		}
	}

	private boolean canOperate() {
		if (getItems().get(0).isEmpty())
			return false;
		else {
			ItemStack stack = getItems().get(0);
			if (stack.getItem() == ModItems.HONEY_DRIP.get())
				if(tank.isEmpty() || tank.getFluid().getAmount() <= tank.getCapacity() - 50 && tank.getFluid().is(ModFluids.HONEY_TYPE.get()));
					return true;
		}
	}

	public void liquifyItem() {
		if (canOperate()) {
			if (tank.isEmpty() || tank.getFluid().getAmount() <= tank.getCapacity() - 50 && tank.getFluid().is(ModFluids.HONEY_TYPE.get())) {
				tank.fill(new FluidStack(ModFluids.HONEY_STILL.get(), 50), IFluidHandler.FluidAction.EXECUTE);
				getItems().get(0).shrink(1);
				if (getItems().get(0).getCount() <= 0)
					getItems().set(0, ItemStack.EMPTY);
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
	public void saveAdditional(@Nonnull CompoundTag nbt, @Nonnull HolderLookup.Provider registries) {
		super.saveAdditional(nbt, registries);
		nbt.putBoolean("active", active);
		tank.writeToNBT(registries, nbt);
		nbt.putShort("operatingTime", (short) operatingTime);
	}

	@Override
	public void loadAdditional(@Nonnull CompoundTag nbt, @Nonnull HolderLookup.Provider registries) {
		super.loadAdditional(nbt, registries);
		active = nbt.getBoolean("active");
		tank.readFromNBT(registries, nbt);
		operatingTime = nbt.getShort("operatingTime");
	}

	@Nonnull
	@Override
	public CompoundTag getUpdateTag(@Nonnull HolderLookup.Provider registries) {
		CompoundTag nbt = new CompoundTag();
		saveAdditional(nbt, registries);
		return nbt;
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		CompoundTag nbt = new CompoundTag();
		saveAdditional(nbt, level.registryAccess());
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet, @Nonnull HolderLookup.Provider registries) {
		super.onDataPacket(net, packet, registries);
		loadAdditional(packet.getTag(), registries);
	}

	@Override
	public int getMaxStackSize() {
		return 64;
	}

	public FluidTank getTank() {
		return this.tank;
	}

	public FluidTank getTank(@Nullable Direction direction) {
		return this.tank;
	}
	@Override
	protected void applyImplicitComponents(@Nonnull DataComponentInput componentInput) {
		super.applyImplicitComponents(componentInput);

		tank.setFluid(componentInput.getOrDefault(ModDataComponents.FLUID, FluidContents.EMPTY).get());
	}

	@Override
	protected void collectImplicitComponents(@Nonnull DataComponentMap.Builder builder) {
		super.collectImplicitComponents(builder);

		builder.set(ModDataComponents.FLUID, FluidContents.of(tank.getFluid()));
	}

	public int getScaledFluid(int scale) {
		return tank.getFluid() != null ? (int) ((float) tank.getFluidAmount() / (float) tank.getCapacity() * scale) : 0;
	}

	@OnlyIn(Dist.CLIENT)
	public int getOperationProgressScaled(int time) {
		return operatingTime * time / 180;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return SLOTS;
	}
	
	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return stack.getItem() == ModItems.HONEY_DRIP.get();
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack stack, Direction direction) {
		return stack.getItem() == ModItems.HONEY_DRIP.get();
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		return stack.getItem() == ModItems.HONEY_DRIP.get();
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(getItems(), slot);
	}

	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new LiquifierMenu(containerId, playerInventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(worldPosition));
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("erebus.containers.liquifier");
	}
}
