package erebus.world.feature.mushroom.config;

import erebus.registries.blocks.ModBlocks;
import erebus.world.util.FeatureUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class DarkCappedMushroomFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final FeatureUtils UTILS = new FeatureUtils();

    public DarkCappedMushroomFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var pos = context.origin();
        var random = context.random();
        int stalkHeight = 3 + random.nextInt(3 + random.nextInt(2));
        int sideHeight = 1 + random.nextInt(stalkHeight > 3 ? 3 : 2);

        var STEM = ModBlocks.DARK_CAPPED_MUSHROOM_STEM.get().defaultBlockState()
                .setValue(HugeMushroomBlock.UP, true)
                .setValue(HugeMushroomBlock.DOWN, true);
        var SHROOM = ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK.get().defaultBlockState();

        if (!level.getBlockState(context.origin().below()).is(Blocks.GRASS_BLOCK) && !level.getBlockState(context.origin().below()).is(Blocks.MYCELIUM)) return false;

        if (!UTILS.checkAirCube(level, pos, pos.above(stalkHeight - sideHeight)) || !UTILS.checkAirCube(level, pos.offset(-2, stalkHeight - sideHeight + 1, -2), pos.offset(2, stalkHeight + 1, 2))) {
            return false;
        }

        UTILS.setBlockPillar(level, pos, stalkHeight + 1, STEM);

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                setBlock(level, pos.offset(x, stalkHeight + 1, z), SHROOM);
            }
        }

        for (int y = 1; y <= sideHeight; y++) {
            for (int offset = -1; offset <= 1; offset++) {
                setBlock(level, pos.offset(2, stalkHeight + 1 - y, offset), SHROOM);
                setBlock(level, pos.offset(-2, stalkHeight + 1 - y, offset), SHROOM);
                setBlock(level, pos.offset(offset, stalkHeight + 1 - y, 2), SHROOM);
                setBlock(level, pos.offset(offset, stalkHeight + 1 - y, -2), SHROOM);
            }
        }

        return true;
    }
}
