package erebus.block.bamboo;

import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.NotNull;

public class BambooCrateBlock extends Block {

    public static final EnumProperty<EnumCrateType> CRATE_TYPE = EnumProperty.create("crate_type", EnumCrateType.class);

    public BambooCrateBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(CRATE_TYPE, EnumCrateType.DEFAULT));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CRATE_TYPE);
    }

    @Override
    protected void onPlace(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState oldState, boolean movedByPiston) {
        if (isCrate(level, pos)) {
            // Bottom
            if (isCrate(level, pos.above()) && isCrate(level, pos.offset(1, 1, 0)) && isCrate(level, pos.offset(1, 1, 1)) && isCrate(level, pos.offset(0, 1, 1))) {
                if (isCrate(level, pos.offset(1, 0, 0)) && isCrate(level, pos.offset(1, 0, 1)) && isCrate(level, pos.offset(0, 0, 1))) {
                    level.setBlock(pos, getStateDefinition().any().setValue(CRATE_TYPE, EnumCrateType.BTL), 3);
                    return;
                }
            }


            if (isCrate(level, pos.above()) && isCrate(level, pos.offset(-1, 1, 0)) && isCrate(level, pos.offset(-1, 1, 1)) && isCrate(level, pos.offset(0, 1, 1))) {
                if (isCrate(level, pos.offset(-1, 0, 0)) && isCrate(level, pos.offset(-1, 0, 1)) && isCrate(level, pos.offset(0, 0, 1))) {
                    level.setBlock(pos, getStateDefinition().any().setValue(CRATE_TYPE, EnumCrateType.BTR), 3); // BTR
                    return;
                }
            }

            if (isCrate(level, pos.above()) && isCrate(level, pos.offset(1, 1, 0)) && isCrate(level, pos.offset(1, 1, -1)) && isCrate(level, pos.offset(0, 1, -1))) {
                if (isCrate(level, pos.offset(1, 0, 0)) && isCrate(level, pos.offset(1, 0, -1)) && isCrate(level, pos.offset(0, 0, -1))) {
                    level.setBlock(pos, getStateDefinition().any().setValue(CRATE_TYPE, EnumCrateType.BBL), 3); // BBL
                    return;
                }
            }

            if (isCrate(level, pos.above()) && isCrate(level, pos.offset(-1, 1, 0)) && isCrate(level, pos.offset(-1, 1, -1)) && isCrate(level, pos.offset(0, 1, -1))) {
                if (isCrate(level, pos.offset(-1, 0, 0)) && isCrate(level, pos.offset(-1, 0, -1)) && isCrate(level, pos.offset(0, 0, -1))) {
                    level.setBlock(pos, getStateDefinition().any().setValue(CRATE_TYPE, EnumCrateType.BBR), 3); // BBR
                    return;
                }
            }

            // TOP
            if (isCrate(level, pos.below()) && isCrate(level, pos.offset(1, -1, 0)) && isCrate(level, pos.offset(1, -1, 1)) && isCrate(level, pos.offset(0, -1, 1))) {
                if (isCrate(level, pos.offset(1, 0, 0)) && isCrate(level, pos.offset(1, 0, 1)) && isCrate(level, pos.offset(0, 0, 1))) {
                    level.setBlock(pos, getStateDefinition().any().setValue(CRATE_TYPE, EnumCrateType.TTL), 3); // TTL
                    return;
                }
            }

            if (isCrate(level, pos.below()) && isCrate(level, pos.offset(-1, -1, 0)) && isCrate(level, pos.offset(-1, -1, 1)) && isCrate(level, pos.offset(0, -1, 1))) {
                if (isCrate(level, pos.offset(-1, 0, 0)) && isCrate(level, pos.offset(-1, 0, 1)) && isCrate(level, pos.offset(0, 0, 1))) {
                    level.setBlock(pos, getStateDefinition().any().setValue(CRATE_TYPE, EnumCrateType.TTR), 3); // TTR
                    return;
                }
            }

            if (isCrate(level, pos.below()) && isCrate(level, pos.offset(1, -1, 0)) && isCrate(level, pos.offset(1, -1, -1)) && isCrate(level, pos.offset(0, -1, -1))) {
                if (isCrate(level, pos.offset(1, 0, 0)) && isCrate(level, pos.offset(1, 0, -1)) && isCrate(level, pos.offset(0, 0, -1))) {
                    level.setBlock(pos, getStateDefinition().any().setValue(CRATE_TYPE, EnumCrateType.TBL), 3); // TBL
                    return;
                }
            }

            if (isCrate(level, pos.below()) && isCrate(level, pos.offset(-1, -1, 0)) && isCrate(level, pos.offset(-1, -1, -1)) && isCrate(level, pos.offset(0, -1, -1)))
                if (isCrate(level, pos.offset(-1, 0, 0)) && isCrate(level, pos.offset(-1, 0, -1)) && isCrate(level, pos.offset(0, 0, -1))) {
                    level.setBlock(pos, getStateDefinition().any().setValue(CRATE_TYPE, EnumCrateType.TBR), 3); // TBR
                }
        }
    }


    @Override
    public void onNeighborChange(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockPos neighbor) {
        if (level.getBlockState(neighbor).is(OtherBlocks.BAMBOO_CRATE)) {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if (level.getBlockState(pos.offset(x, y, z)).is(OtherBlocks.BAMBOO_CRATE)) {
                            onPlace(state, (Level) level, pos, state, false);
                            onPlace(state, (Level) level, neighbor, state, false);
                        }
                    }
                }
            }
        }
    }

    private boolean isCrate(Level level, BlockPos pos) {
        return level.getBlockState(pos).is(OtherBlocks.BAMBOO_CRATE.get());
    }
}
