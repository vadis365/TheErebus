package erebus.world.feature.plant.config;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class VinesFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    private final int maxVineHeight;
    private final int variation;

    public VinesFeatureConfiguration(int height, int variation) {
        super(NoneFeatureConfiguration.CODEC);
        this.maxVineHeight = height;
        this.variation = variation;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        for (BlockPos check = pos; check.getY() < maxVineHeight + random.nextInt(variation) - random.nextInt(variation); check = check.above()) {

            if (level.isAreaLoaded(check, 1) && level.isEmptyBlock(check)) {
                for (Direction direction : HorizontalDirectionalBlock.FACING.getPossibleValues()) {
                    if (level.isAreaLoaded(check.relative(direction), 1)) {
                        if (VineBlock.isAcceptableNeighbour(level, check.relative(direction), direction.getOpposite())) {
                            BlockState vine = Blocks.VINE.defaultBlockState();

                            vine.setValue(VineBlock.NORTH, direction.getName().equals(Direction.NORTH.getName()));
                            vine.setValue(VineBlock.SOUTH, direction.getName().equals(Direction.SOUTH.getName()));
                            vine.setValue(VineBlock.EAST, direction.getName().equals(Direction.EAST.getName()));
                            vine.setValue(VineBlock.WEST, direction.getName().equals(Direction.WEST.getName()));

                            setBlock(level, check, vine);
                            break;
                        }
                    } else {
                        check = check.offset(random.nextInt(4) - random.nextInt(4), 0, random.nextInt(4) - random.nextInt(4));
                    }
                }
            }
        }

        return true;
    }
}
