package erebus.world.feature.mushroom.config;

import erebus.registries.blocks.providers.PlantBlocks;
import erebus.world.util.FeatureConfigurationUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class DarkCappedMushroomFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final int[] offsetX = {0, -1, 0, 1};
    private final int[] offsetZ = {1, 0, -1, 0};
    private final FeatureConfigurationUtils utils = new FeatureConfigurationUtils();

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

        if(!utils.checkAirCube(level, pos, pos.above(stalkHeight - sideHeight)) || !utils.checkAirCube(level, pos.offset(-2, stalkHeight - sideHeight + 1, -2), pos.offset(2, stalkHeight + 1, 2))) {
            return false;
        }

        utils.setBlockPillar(level, pos, stalkHeight, PlantBlocks.DARK_CAPPED_MUSHROOM_BLOCK.get().defaultBlockState());

        return true;
    }
}
