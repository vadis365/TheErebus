package erebus.block.entity;

import erebus.block.bamboo.BambooPipeExtract;
import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandlerUtil;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nullable;

public class BambooPipeExtractBlockEntity extends BlockEntity {

    public final FluidStacksResourceHandler tank = new FluidStacksResourceHandler(1, 100) {
        @Override
        protected void onContentsChanged(int index, FluidStack previous) {
            setChanged();
            needsClientUpdate = true;
        }
    };
    private boolean needsClientUpdate;

    public BambooPipeExtractBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BAMBOO_PIPE_EXTRACT.get(), pos, state);
    }

    public static <T extends BlockEntity> void serverTick(Level level, BlockPos pos, BlockState state, T t) {
        if (t instanceof BambooPipeExtractBlockEntity tile) {
            if (state.getValue(BambooPipeExtract.ACTIVE)) {
                Direction pipeFacing = tile.getBlockState().getValue(BambooPipeExtract.FACING);
                ResourceHandler<FluidResource> tankToDrawFrom = level.hasChunkAt(pos.relative(pipeFacing))
                        ? level.getCapability(Capabilities.Fluid.BLOCK, pos.relative(pipeFacing), pipeFacing.getOpposite()) : null;
                if (tankToDrawFrom != null) {
                    ResourceHandlerUtil.move(tankToDrawFrom, tile.tank, _ -> true, 100, null);
                }

                for (Direction facing : Direction.values()) {
                    if (facing != pipeFacing && level.hasChunkAt(pos.relative(facing))) {
                        ResourceHandler<FluidResource> receptacle = level.getCapability(Capabilities.Fluid.BLOCK, pos.relative(facing), facing.getOpposite());
                        if (receptacle != null) {
                            ResourceHandlerUtil.move(tile.tank, receptacle, _ -> true, 100, null);
                        }
                    }
                }
            }
            if (tile.needsClientUpdate) {
                tile.needsClientUpdate = false;
                tile.updateBlock();
            }
        }
    }

    public void updateBlock() {
        getLevel().sendBlockUpdated(worldPosition, getLevel().getBlockState(worldPosition), getLevel().getBlockState(worldPosition), 2);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveCustomOnly(registries);
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
