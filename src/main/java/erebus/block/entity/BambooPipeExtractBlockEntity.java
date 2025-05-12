package erebus.block.entity;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import erebus.block.bamboo.BambooPipeExtractActive;
import erebus.registries.ModBlockEntities;
import erebus.utils.CapHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public class BambooPipeExtractBlockEntity extends BlockEntity {

	public FluidTank tank = new FluidTank(100);
	public int prevTankAmount;

	public BambooPipeExtractBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.BAMBOO_PIPE_EXTRACT.get(), pos, state);
	}

	public static <T extends BlockEntity> void serverTick(Level level, BlockPos pos, BlockState state, T t) {
		if (t instanceof BambooPipeExtractBlockEntity tile) {
			if (tile.prevTankAmount != tile.tank.getFluidAmount()) {
				tile.updateBlock();
				tile.setChanged();
			}
			tile.prevTankAmount = tile.tank.getFluidAmount();
			
			if (!(level.getBlockState(pos).getBlock() instanceof BambooPipeExtractActive))
				return;

			else {
				Direction pipeFacing = tile.getBlockState().getValue(BambooPipeExtractActive.FACING);
				Optional<IFluidHandler> handlerOptionalInput = CapHelper.getFluidHandler(level, pos.relative(pipeFacing.getOpposite()), pipeFacing);
				handlerOptionalInput.ifPresent((tankToDrawFrom) -> {
					int tanks = tankToDrawFrom.getTanks();
					for (int x = 0; x < tanks; x++) {
						if (tankToDrawFrom.getTankCapacity(x) > 0) {
							FluidStack contents = tankToDrawFrom.getFluidInTank(x);
							if (!contents.isEmpty()) {
								if (tile.tank.isEmpty() || tile.tank.getFluid().getAmount() <= tile.tank.getCapacity() - tile.tank.getFluid().getAmount() && tile.tank.getFluid().is(contents.getFluid())) {
									tile.tank.fill(tankToDrawFrom.drain(new FluidStack(contents.getFluid(), tile.tank.getCapacity()), IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);
									tile.setChanged();
								}
							}
						}
					}
				});

			for (Direction facing : Direction.values()) {
				Optional<IFluidHandler> handlerOptionalOutput = CapHelper.getFluidHandler(level, pos.relative(facing), facing.getOpposite());
				handlerOptionalOutput.ifPresent((receptacle) -> {
					int tanks = receptacle.getTanks();
					for (int x = 0; x < tanks; x++) {
						if (receptacle.getTankCapacity(x) > 0) {
							FluidStack contents = receptacle.getFluidInTank(x);
							if (!tile.tank.getFluid().isEmpty()) {
								if (contents.isEmpty() || contents.getAmount() <= receptacle.getTankCapacity(x) - tile.tank.getFluid().getAmount() && contents.is(tile.tank.getFluid().getFluid())) {
									receptacle.fill(tile.tank.drain(new FluidStack(tile.tank.getFluid().getFluid(), tile.tank.getFluid().getAmount()), IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);
									tile.setChanged();
								}
							}
						}
					}
				});
			}
		}
		}
	}

	public void updateBlock() {
		getLevel().sendBlockUpdated(worldPosition, getLevel().getBlockState(worldPosition), getLevel().getBlockState(worldPosition), 3);
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet, @Nonnull HolderLookup.Provider registries) {
		super.onDataPacket(net, packet, registries);
		loadAdditional(packet.getTag(), registries);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		CompoundTag nbt = new CompoundTag();
		saveAdditional(nbt, level.registryAccess());
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Nonnull
	@Override
	public CompoundTag getUpdateTag(@Nonnull HolderLookup.Provider registries) {
		CompoundTag nbt = new CompoundTag();
		saveAdditional(nbt, registries);
		return nbt;
	}

	@Override
	public void loadAdditional(@Nonnull CompoundTag nbt, @Nonnull HolderLookup.Provider registries) {
		super.loadAdditional(nbt, registries);
		tank.readFromNBT(registries, nbt);
	}

	@Override
	public void saveAdditional(@Nonnull CompoundTag nbt, @Nonnull HolderLookup.Provider registries) {
		super.saveAdditional(nbt, registries);
		tank.writeToNBT(registries, nbt);
	}

	public FluidTank getTank() {
		return this.tank;
	}

	public FluidTank getTank(@Nullable Direction direction) {
		return this.tank;
	}
}
