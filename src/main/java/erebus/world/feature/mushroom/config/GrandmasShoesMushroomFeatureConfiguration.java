package erebus.world.feature.mushroom.config;

import erebus.registries.blocks.ModBlocks;
import erebus.world.util.FeatureUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class GrandmasShoesMushroomFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final int[] offsetX = {0, -1, 0, 1};
    private final int[] offsetZ = {1, 0, -1, 0};
    private final FeatureUtils Utils = new FeatureUtils();

    public GrandmasShoesMushroomFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var pos = context.origin().west();
        var random = context.random();
        int height = random.nextInt(8) + 5;
        int splits = random.nextInt(height > 8 ? 3 : 2);
        int splitSize = splits == 0 ? height : (int) Math.ceil(height / (1.0F + splits));
        int splitDir = splits != 0 ? random.nextInt(4) : -1;
        int splitOffsetX = splitDir == -1 ? 0 : offsetX[splitDir];
        int splitOffsetZ = splitDir == -1 ? 0 : offsetZ[splitDir];

        var STEM = ModBlocks.GRANDMAS_SHOES_MUSHROOM_STEM.get().defaultBlockState()
                .setValue(HugeMushroomBlock.UP, true)
                .setValue(HugeMushroomBlock.DOWN, true);
        var SHROOM = ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK.get().defaultBlockState();

        if (!level.getBlockState(context.origin().below()).is(Blocks.GRASS_BLOCK)
                && !level.getBlockState(context.origin().below()).is(Blocks.MYCELIUM)) return false;

        if (!Utils.checkAirCube(level, pos.offset(-1, 0, -1), pos.offset(2, height - 2, 2))) return false;
        var capPos = pos;
        for (int y = 0, split = splitSize; y <= height; y++) {
            if (!Utils.checkAirCube(level, capPos.above(y), capPos.offset(1, y, 1))) return false;
            if (--split < 0 && y < height) {
                capPos = capPos.offset(splitOffsetX, 0, splitOffsetZ);
                split = splitSize - 1;
            }
        }
        if (!Utils.checkAirCube(level, capPos.offset(-3, height - 1, -3), capPos.offset(4, height + 1, 4))) return false;
        if (!Utils.checkSolidCube(level, pos.offset(-1, -1, -1), pos.offset(3, -1, 3))) return false;

        for (int a = 0; a < 2; a++) {
            for (int b = 0; b < 2; b++) {
                Utils.setBlockCube(level, pos.offset(-1 + 3 * b, 0, 0), pos.offset(-1 + 3 * b, 0, 1), STEM);
                Utils.setBlockCube(level, pos.offset(0, 0, -1 + 3 * b), pos.offset(1, 0, -1 + 3 * b), STEM);
            }
        }

        for (int y = 0, split = splitSize; y <= height; y++) {
            Utils.setBlockCube(level, pos.above(y), pos.offset(1, y, 1), STEM);

            if (--split < 0 && y < height) {
                pos = pos.offset(splitOffsetX, 0, splitOffsetZ);
                split = splitSize - 1;
            }
        }

        for (int a = 0; a < 2; a++) {
            for (int b = 0; b < 2; b++) {
                setBlock(level, pos.offset(a, height + 1, b), SHROOM);
                setBlock(level, pos.offset(-1 + 3 * a, height + 1, b), SHROOM);
                setBlock(level, pos.offset(b, height + 1, -1 + 3 * a), SHROOM);
                setBlock(level, pos.offset(-1 + 3 * a, height, -1 + 3 * b), SHROOM);
                setBlock(level, pos.offset(-2 + 5 * a, height - 1, -2 + 5 * b), SHROOM);
            }

            for (int b = 0; b < 4; b++) {
                setBlock(level, pos.offset(-2 + 5 * a, height, -1 + b), SHROOM);
                setBlock(level, pos.offset(-1 + b, height, -2 + 5 * a), SHROOM);
                setBlock(level, pos.offset(-3 + 7 * a, height - 1, -1 + b), SHROOM);
                setBlock(level, pos.offset(-1 + b, height - 1, -3 + 7 * a), SHROOM);
            }
        }

        return true;
    }
}
