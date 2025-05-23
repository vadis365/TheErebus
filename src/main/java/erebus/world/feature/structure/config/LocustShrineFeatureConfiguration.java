package erebus.world.feature.structure.config;

import erebus.block.BlockOfBonesBlock;
import erebus.block.entity.BlockOfBonesBlockEntity;
import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.blocks.providers.UmberstoneBlocks;
import erebus.registries.blocks.providers.WallBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class LocustShrineFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    private final int RADIUS = 4;
    private final BlockState COARSE_DIRT = Blocks.COARSE_DIRT.defaultBlockState();
    private final BlockState UMBERGRAVEL = UmberstoneBlocks.UMBERGRAVEL.get().defaultBlockState();
    private final BlockState DEAD_BUSH = Blocks.DEAD_BUSH.defaultBlockState();
    private final BlockState MOSSY_UMBERCOBBLE = UmberstoneBlocks.UMBERCOBBLE_MOSSY.get().defaultBlockState();
    private final BlockState MOSSY_UMBERCOBBLE_WALL = WallBlocks.WALL_UMBERCOBBLE_MOSSY.get().defaultBlockState();
    private final BlockState BONES = OtherBlocks.BLOCK_OF_BONES.get().defaultBlockState();
    private final BlockState SPAWNER = OtherBlocks.LOCUST_SPAWNER.get().defaultBlockState();

    public LocustShrineFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        if (!aircheck(level, pos))
            return false;

        for (int x = -RADIUS; x <= RADIUS; x++) {
            for (int z = -RADIUS; z <= RADIUS; z++) {
                double circle = Math.pow(x, 2.0D) + Math.pow(z, 2.0D);
                if (Math.round(Math.sqrt(circle)) < 5)
                    setBlock(level, pos.offset(x, -1, z), random.nextInt(5) != 0 ? COARSE_DIRT : UMBERGRAVEL);
                if(level.getBlockState(pos.offset(x, -1, z)).is(Blocks.DIRT) && random.nextInt(3) == 0)
                    setBlock(level, pos.offset(x, 0, z), DEAD_BUSH);
            }
        }
        placeSpawnerPillar(level, pos);
        placePillar(level, pos.offset(3, 0, 3), Direction.EAST);
        placePillar(level, pos.offset(3, 0, -3), Direction.NORTH);
        placePillar(level, pos.offset(-3, 0, 3), Direction.SOUTH);
        placePillar(level, pos.offset(-3, 0, -3), Direction.WEST);

        return true;
    }

    private void placePillar(WorldGenLevel level, BlockPos pos, Direction facing) {
        setBlock(level, pos, MOSSY_UMBERCOBBLE);
        setBlock(level, pos.above(1), MOSSY_UMBERCOBBLE_WALL);
        setBlock(level, pos.above(2), MOSSY_UMBERCOBBLE);
        setBlock(level, pos.above(3), MOSSY_UMBERCOBBLE);
        setBlock(level, pos.above(4), MOSSY_UMBERCOBBLE_WALL);
        setBlock(level, pos.above(5), MOSSY_UMBERCOBBLE);
        setBlock(level, pos.above(6), BONES.setValue(BlockOfBonesBlock.FACING, facing));
        BlockOfBonesBlockEntity bones = (BlockOfBonesBlockEntity) level.getBlockEntity(pos.above(6));
        if (bones != null) {
            // Set Loot
        }
    }

    private void placeSpawnerPillar(WorldGenLevel level, BlockPos pos) {
        setBlock(level, pos, MOSSY_UMBERCOBBLE);
        setBlock(level, pos.above(1), MOSSY_UMBERCOBBLE);
        setBlock(level, pos.above(2), MOSSY_UMBERCOBBLE_WALL);
        setBlock(level, pos.above(3), MOSSY_UMBERCOBBLE);
        setBlock(level, pos.above(4), SPAWNER);
    }

    private boolean aircheck(WorldGenLevel level, BlockPos pos) {
        int HEIGHT = 7;
        for (int x = -RADIUS; x <= RADIUS; x++)
            for (int z = -RADIUS; z <= RADIUS; z++) {
                if(!level.getBlockState(pos.offset(x, -1, z)).is(Blocks.GRASS_BLOCK)) return false;
                for (int y = 0; y < HEIGHT; y++)
                    if (!level.isEmptyBlock(pos.offset(x, y, z)))
                        return false;
            }
        return true;
    }
}
