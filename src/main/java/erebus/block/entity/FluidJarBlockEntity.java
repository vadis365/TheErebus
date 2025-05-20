package erebus.block.entity;

import erebus.registries.ModBlockEntities;
import erebus.registries.data.FluidContents;
import erebus.registries.data.ModDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidJarBlockEntity extends BlockEntity {
	public FluidTank tank = new FluidTank(FluidType.BUCKET_VOLUME * 32);
	public int prevTankAmount;

	public FluidJarBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.FLUID_JAR.get(), pos, state);
	}

	public static <T extends BlockEntity> void serverTick(Level world, BlockPos worldPosition, BlockState blockState, T t) {
		if (t instanceof FluidJarBlockEntity tile) {
			if(tile.prevTankAmount != tile.tank.getFluidAmount()) {
				tile.updateBlock();
				tile.setChanged();
			}
			tile.prevTankAmount = tile.tank.getFluidAmount();
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

	public int getScaledFluid(int scale) {
		return tank.getFluid() != null ? (int) ((float) tank.getFluidAmount() / (float) tank.getCapacity() * scale) : 0;
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

}
