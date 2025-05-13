package erebus.world.feature.plant.config;

import erebus.registries.blocks.providers.UmberstoneBlocks;
import erebus.registries.blocks.providers.WoodBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.function.Supplier;

public class MossPatchFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    private final Supplier<? extends Block> mossHolder;
    private boolean blockPlaced = false;

    public MossPatchFeatureConfiguration(Supplier<? extends Block> moss) {
        super(NoneFeatureConfiguration.CODEC);
        this.mossHolder = moss;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        placeBlockAt(level, pos, random);
        if (blockPlaced) createPatch(level, pos, random);

        return true;
    }

    private void placeBlockAt(WorldGenLevel level, BlockPos pos, RandomSource random) {
        BlockState moss = mossHolder.get().defaultBlockState();
        Direction side = Direction.getRandom(random);

        if (level.getBlockState(pos.relative(side)).isFaceSturdy(level, pos.relative(side), side.getOpposite()) && isValidBlock(level, pos.relative(side))) {
            setBlock(level, pos, moss.setValue(BlockStateProperties.FACING, side));
            blockPlaced = true;
        }
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
        BlockState state = level.getBlockState(pos);

        return state.is(WoodBlocks.LOG_ROTTEN) || state.is(UmberstoneBlocks.UMBERSTONE);
    }
}
