package erebus.world.feature.mushroom.config;

import erebus.world.util.FeatureUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class DarkCappedMushroomFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final FeatureUtils Utils = new FeatureUtils();
    private final BlockState STEM = ModBlocks.DARK_CAPPED_MUSHROOM_STEM.get().defaultBlockState()
            .setValue(HugeMushroomBlock.UP, true)
            .setValue(HugeMushroomBlock.DOWN, true);
    private final BlockState SHROOM = ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK.get().defaultBlockState();

    public DarkCappedMushroomFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();
        int stalkHeight = 3 + random.nextInt(3 + random.nextInt(2));
        int sideHeight = 1 + random.nextInt(stalkHeight > 3 ? 3 : 2);

        if(!level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)) return false;

        if(!Utils.checkAirCube(level, pos, pos.above(stalkHeight - sideHeight)) || !Utils.checkAirCube(level, pos.offset(-2, stalkHeight - sideHeight + 1, -2), pos.offset(2, stalkHeight + 1, 2))) {
            return false;
        }

        Utils.setBlockPillar(level, pos, stalkHeight, STEM);

        for(int x = -1; x <= 1; x++) {
            for(int z = -1; z <= 1; z++) {
                setBlock(level, pos.offset(x, stalkHeight, z), SHROOM);
            }
        }

        for(int y = 1; y <= sideHeight; y++) {
            for(int offset = -1; offset <= 1; offset++) {
                setBlock(level, pos.offset(2, stalkHeight - y, offset), SHROOM);
                setBlock(level, pos.offset(-2, stalkHeight - y, offset), SHROOM);
                setBlock(level, pos.offset(offset, stalkHeight - y, 2), SHROOM);
                setBlock(level, pos.offset(offset, stalkHeight - y, -2), SHROOM);
            }
        }

        return true;
    }
}
