package erebus.world.feature.misc.config;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class LakePopulationFeature extends Feature<NoneFeatureConfiguration> {
    private final boolean acid;

    public LakePopulationFeature(boolean acid) {
        super(NoneFeatureConfiguration.CODEC);
        this.acid = acid;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(acid ? ModBiomes.PETRIFIED_FOREST_KEY : ModBiomes.VOLCANIC_DESERT_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < 35; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + 15 + random.nextInt(90);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            var soil = new BlockPos(x, y, z);
            if (!level.isOutsideBuildHeight(soil) && isHost(level.getBlockState(soil))) {
                placed |= placeLake(context, soil.above());
            }
        }
        return placed;
    }

    private boolean isHost(BlockState state) {
        return acid ? state == ModBlocks.VOLCANIC_ROCK.get().defaultBlockState()
                : state == Blocks.SAND.defaultBlockState()
                || state == Blocks.RED_SAND.defaultBlockState();
    }

    protected boolean placeLake(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos origin) {
        var level = context.level();
        var corner = origin.offset(-8, 0, -8);
        while (corner.getY() > level.getMinY() + 5 && level.isEmptyBlock(corner)) corner = corner.below();
        if (corner.getY() <= level.getMinY() + 4) return false;
        var lake = new LakeWithEdgeFeatureConfiguration(acid ? ModBlocks.FLUID_FORMIC_ACID_BLOCK.get() : Blocks.LAVA, ModBlocks.VOLCANIC_ROCK);
        return lake.place(NoneFeatureConfiguration.INSTANCE, level, context.chunkGenerator(), level.getRandom(), corner);
    }
}
