package erebus.world.feature.misc.config;

import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class QuickSandFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public QuickSandFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();
        BlockState quickSand = ModBlocks.QUICK_SAND.get().defaultBlockState();

        if (!level.getBlockState(pos).is(Blocks.GRASS_BLOCK)) return false;

        setBlock(level, pos, quickSand);
        setBlock(level, pos.below(), quickSand);
        setBlock(level, pos.below(2), quickSand);

        setBlock(level, pos.north(), quickSand);
        setBlock(level, pos.north().below(), quickSand);

        setBlock(level, pos.south(), quickSand);
        setBlock(level, pos.south().below(), quickSand);

        setBlock(level, pos.west(), quickSand);
        setBlock(level, pos.west().below(), quickSand);

        setBlock(level, pos.east(), quickSand);
        setBlock(level, pos.east().below(), quickSand);

        setBlock(level, pos.west(2), quickSand);
        setBlock(level, pos.west().south(), quickSand);

        setBlock(level, pos.east(2), quickSand);
        setBlock(level, pos.east().north(), quickSand);

        setBlock(level, pos.south(2), quickSand);
        setBlock(level, pos.west().north(), quickSand);

        setBlock(level, pos.north(2), quickSand);
        setBlock(level, pos.east().south(), quickSand);

        // Top Layer
        BlockPos topLayer = pos.below(2);
        if (random.nextBoolean()) setBlock(level, topLayer.north(), quickSand);
        if (random.nextBoolean()) setBlock(level, topLayer.south(), quickSand);
        if (random.nextBoolean()) setBlock(level, topLayer.east(), quickSand);
        if (random.nextBoolean()) setBlock(level, topLayer.west(), quickSand);

        // Middle Layer
        BlockPos midLayer = pos.below();
        if (random.nextBoolean()) setBlock(level, midLayer.north(2), quickSand);
        if (random.nextBoolean()) setBlock(level, midLayer.south(2), quickSand);
        if (random.nextBoolean()) setBlock(level, midLayer.east(2), quickSand);
        if (random.nextBoolean()) setBlock(level, midLayer.west(2), quickSand);
        if (random.nextBoolean()) setBlock(level, midLayer.north().east(), quickSand);
        if (random.nextBoolean()) setBlock(level, midLayer.north().west(), quickSand);
        if (random.nextBoolean()) setBlock(level, midLayer.south().east(), quickSand);
        if (random.nextBoolean()) setBlock(level, midLayer.south().west(), quickSand);

        // Bottom Layer
        if (random.nextBoolean()) setBlock(level, pos.north(3), quickSand);
        if (random.nextBoolean()) setBlock(level, pos.south(3), quickSand);
        if (random.nextBoolean()) setBlock(level, pos.east(3), quickSand);
        if (random.nextBoolean()) setBlock(level, pos.west(3), quickSand);
        if (random.nextBoolean()) setBlock(level, pos.north().east(2), quickSand);
        if (random.nextBoolean()) setBlock(level, pos.north(2).east(), quickSand);
        if (random.nextBoolean()) setBlock(level, pos.north().west(2), quickSand);
        if (random.nextBoolean()) setBlock(level, pos.north(2).west(), quickSand);
        if (random.nextBoolean()) setBlock(level, pos.south().east(2), quickSand);
        if (random.nextBoolean()) setBlock(level, pos.south(2).east(), quickSand);
        if (random.nextBoolean()) setBlock(level, pos.south().west(2), quickSand);
        if (random.nextBoolean()) setBlock(level, pos.south(2).west(), quickSand);

        return true;
    }
}
