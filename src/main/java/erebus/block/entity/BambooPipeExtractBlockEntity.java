package erebus.block.entity;

import erebus.block.bamboo.BambooPipeExtract;
import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandlerUtil;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nullable;

public class BambooPipeExtractBlockEntity extends BlockEntity {

	public FluidStacksResourceHandler tank = new FluidStacksResourceHandler(1, 100);
	public int prevTankAmount;

	public BambooPipeExtractBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.BAMBOO_PIPE_EXTRACT.get(), pos, state);
	}

	public static <T extends BlockEntity> void serverTick(Level level, BlockPos pos, BlockState state, T t) {
		if (t instanceof BambooPipeExtractBlockEntity tile) {
			if (tile.prevTankAmount != tile.tank.getAmountAsInt(0)) {
				tile.updateBlock();
				tile.setChanged();
			}
			tile.prevTankAmount = tile.tank.getAmountAsInt(0);

			if (state.getValue(BambooPipeExtract.ACTIVE)) {
				Direction pipeFacing = tile.getBlockState().getValue(BambooPipeExtract.FACING);
				ResourceHandler<FluidResource> tankToDrawFrom = level.getCapability(Capabilities.Fluid.BLOCK, pos.relative(pipeFacing), pipeFacing.getOpposite());
				if (tankToDrawFrom != null) {
					if (ResourceHandlerUtil.move(tankToDrawFrom, tile.tank, _ -> true, 100, null) > 0) {
						tile.setChanged();
					}
				}

				for (Direction facing : Direction.values()) {
					if (facing != pipeFacing) {
						ResourceHandler<FluidResource> receptacle = level.getCapability(Capabilities.Fluid.BLOCK, pos.relative(facing), facing.getOpposite());
						if (receptacle != null) {
							if (ResourceHandlerUtil.move(tile.tank, receptacle, _ -> true, 100, null) > 0) {
								tile.setChanged();
							}
						}
					}
				}
			}
		}
	}

	public void updateBlock() {
		getLevel().sendBlockUpdated(worldPosition, getLevel().getBlockState(worldPosition), getLevel().getBlockState(worldPosition), 3);
	}

	@Override
	public void onDataPacket(@NonNull Connection net, @NonNull ValueInput input) {
		super.onDataPacket(net, input);
		loadAdditional(input);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	protected void loadAdditional(@NonNull ValueInput input) {
		super.loadAdditional(input);
		tank.deserialize(input);
	}

	@Override
	protected void saveAdditional(@NonNull ValueOutput output) {
		super.saveAdditional(output);
		tank.serialize(output);
	}

	public FluidStacksResourceHandler getTank() {
		return this.tank;
	}

	public FluidStacksResourceHandler getTank(@Nullable Direction direction) {
		return this.tank;
	}
}
