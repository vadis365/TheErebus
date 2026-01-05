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

public class GrandmasShoesMushroomFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final int[] offsetX = {0, -1, 0, 1};
    private final int[] offsetZ = {1, 0, -1, 0};
    private final FeatureUtils Utils = new FeatureUtils();
    private final BlockState STEM = ModBlocks.GRANDMAS_SHOES_MUSHROOM_STEM.get().defaultBlockState()
            .setValue(HugeMushroomBlock.UP, true)
            .setValue(HugeMushroomBlock.DOWN, true);
    private final BlockState SHROOM = ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK.get().defaultBlockState();

    public GrandmasShoesMushroomFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin().west();
        RandomSource random = context.random();
        int height = random.nextInt(8) + 5;
        int splits = random.nextInt(height > 8 ? 3 : 2);
        int splitSize = splits == 0 ? height : (int) Math.ceil(height / (1.0F + splits));
        int splitDir = splits != 0 ? random.nextInt(4) : -1;
        int splitOffsetX = splitDir == -1 ? 0 : offsetX[splitDir];
        int splitOffsetZ = splitDir == -1 ? 0 : offsetZ[splitDir];

        if(!level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)) return false;

        if(!Utils.checkAirCube(level, pos.offset(-1, 0, -1), pos.offset(2, height - 2, 2))) return false;
        if(!Utils.checkAirCube(level, pos.offset(-3 + splitOffsetX, height - 1, -3 + splitOffsetZ), pos.offset(3 + splitOffsetX, height + 1, 3 + splitOffsetZ))) return false;
        if(!Utils.checkAirCube(level, pos.offset(-1, -1, -1), pos.offset(3, -1, 3))) return false;

        for(int a = 0; a < 2; a++) {
            for(int b = 0; b < 2; b++) {
                Utils.setBlockCube(level, pos.offset(-1 + 3 * b, 0, 0), pos.offset(-1 + 3 * b, 0, 1), STEM);
                Utils.setBlockCube(level, pos.offset(0, 0, -1 + 3 * b), pos.offset(1, 0, -1 + 3 * b), STEM);
            }
        }

        for(int x = 1, y = 0, z = 1, split = splitSize; y <= height; y++) {
            Utils.setBlockCube(level, pos.above(y), pos.offset(x, y, z), STEM);

            if(--split < 0 && y < height) {
                x += splitOffsetX;
                z += splitOffsetZ;
                split = splitSize - 1;
            }
        }

        for(int a = 0; a < 2; a++) {
            for(int b = 0; b < 2; b++) {
                setBlock(level, pos.offset(a, height + 1, b),  SHROOM);
                setBlock(level, pos.offset(-1 + 3 * a, height + 1, b), SHROOM);
                setBlock(level, pos.offset(b, height + 1, -1 + 3 * a), SHROOM);
                setBlock(level, pos.offset(-1 + 3 * a, height, -1 + 3 * b), SHROOM);
                setBlock(level, pos.offset(-2 + 5 * a, height - 1, -2 + 5 * b), SHROOM);
            }

            for(int b = 0; b < 4; b++) {
                setBlock(level, pos.offset(-2 + 5 * a, height, -1 + b), SHROOM);
                setBlock(level, pos.offset(-1 + b, height, -2 + 5 * a), SHROOM);
                setBlock(level, pos.offset(-3 + 7 * a, height - 1, -1 + b), SHROOM);
                setBlock(level, pos.offset(-1 + b, height - 1, -3 + 7 * a), SHROOM);
            }
        }

        return true;
    }
}
