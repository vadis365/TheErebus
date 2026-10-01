package erebus.world.feature.plant.config;

import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.function.Supplier;

public class MossPatchFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    private final Supplier<? extends Block> mossHolder;

    public MossPatchFeatureConfiguration(Supplier<? extends Block> moss) {
        super(NoneFeatureConfiguration.CODEC);
        this.mossHolder = moss;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var pos = context.origin();
        var random = context.random();

        if (!placeBlockAt(level, pos, random)) return false;
        createPatch(level, pos, random);

        return true;
    }

    private boolean placeBlockAt(WorldGenLevel level, BlockPos pos, RandomSource random) {
        if (!level.isEmptyBlock(pos)) return false;
        var moss = mossHolder.get().defaultBlockState();
        var side = Direction.getRandom(random);

        if (level.getBlockState(pos.relative(side)).isFaceSturdy(level, pos.relative(side), side.getOpposite()) && isValidBlock(level, pos.relative(side))) {
            moss = moss.setValue(BlockStateProperties.FACING, side.getOpposite());
            return level.setBlock(pos, moss, 2);
        }
        return false;
    }

    private void createPatch(WorldGenLevel level, BlockPos pos, RandomSource random) {
        byte radius = 2;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    if (level.isEmptyBlock(pos.offset(x, y, z))) {
                        for (int c = 0; c < 3; c++) {
                            placeBlockAt(level, pos.offset(x, y, z), random);
                        }
                    }
                }
            }
        }
    }

    private boolean isValidBlock(WorldGenLevel level, BlockPos pos) {
        var state = level.getBlockState(pos);
        return state.is(ModBlocks.LOG_ROTTEN) || state.is(ModBlocks.UMBERSTONE);
    }
}
