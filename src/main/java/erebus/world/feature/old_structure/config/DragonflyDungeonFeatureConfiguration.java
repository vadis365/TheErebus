package erebus.world.feature.old_structure.config;

import erebus.datagen.loot.ModChestLootTables;
import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.blocks.providers.PlantBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

public class DragonflyDungeonFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    private final BlockState AIR = Blocks.AIR.defaultBlockState();
    private final BlockState GIANT_LILY_PAD = PlantBlocks.GIANT_LILY_PAD.get().defaultBlockState();
    private final BlockState PETAL = PlantBlocks.PETAL_WHITE.get().defaultBlockState();
    private final BlockState SPAWNER = OtherBlocks.DRAGON_FLY_SPAWNER.get().defaultBlockState();

    public DragonflyDungeonFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        if(hasSpace(level, pos)) {
            generateMainLilyPad(level, pos);
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            switch (direction) {
                case NORTH -> northAirGap(level, pos);
                case SOUTH -> southAirGap(level, pos);
                case EAST -> eastAirGap(level, pos);
                case WEST -> westAirGap(level, pos);
            }
            generateStem(level, pos);
            generateFlower(level, pos);
            addChestAndSpawners(level, pos);

            return true;
        }

        return false;
    }

    private boolean hasSpace(WorldGenLevel level, BlockPos pos) {
        for(int x = -6; x <= 6; x++) {
            for(int z = -6; z <= 6; z++) {
                for(int y = 1; y < 5; y++) {
                    if(!level.isEmptyBlock(pos.offset(x, y, z)) || !level.getBlockState(pos).getFluidState().is(Fluids.WATER)) return false;
                }
            }
        }
        return true;
    }

    private void generateMainLilyPad(WorldGenLevel level, BlockPos pos) {
        for(int x = -6; x <= 6; x++) {
            for(int z = -6; z <= 6; z++) {
                double dSqCylinder = Math.pow(x, 2) + Math.pow(z, 2);
                long rounded = Math.round(Math.sqrt(dSqCylinder));

                if(rounded < 6) {
                    if(dSqCylinder <= Math.pow(6, 2)) {
                        setBlock(level, pos.offset(x, 0, z), GIANT_LILY_PAD);
                    }
                }

                if(rounded == 5) {
                    if(dSqCylinder <= Math.pow(6, 2)) {
                        setBlock(level, pos.offset(x, 1, z), GIANT_LILY_PAD);
                    }
                }
            }
        }
    }

    private void northAirGap(WorldGenLevel level, BlockPos pos) {
        for(int z = -5; z < -1; z++) {
            zAirGap(level, pos, z);
        }
    }

    private void southAirGap(WorldGenLevel level, BlockPos pos) {
        for(int z = 5; z > 1; z--) {
            zAirGap(level, pos, z);
        }
    }

    private void eastAirGap(WorldGenLevel level, BlockPos pos) {
        for(int x = 5; x > 1; x--) {
            xAirGap(level, pos, x);
        }
    }

    private void westAirGap(WorldGenLevel level, BlockPos pos) {
        for(int x = -5; x < -1; x++) {
            xAirGap(level, pos, x);
        }
    }

    private void generateStem(WorldGenLevel level, BlockPos pos) {
        setBlock(level, pos.offset(1, 1, 0), GIANT_LILY_PAD);
        setBlock(level, pos.offset(0, 1, 1), GIANT_LILY_PAD);
        setBlock(level, pos.offset(-1, 1, 0), GIANT_LILY_PAD);
        setBlock(level, pos.offset(0, 1, -1), GIANT_LILY_PAD);
    }

    private void generateFlower(WorldGenLevel level, BlockPos pos) {
        setBlock(level, pos.offset(1, 2, 0), PETAL);
        setBlock(level, pos.offset(0, 2, 1), PETAL);
        setBlock(level, pos.offset(- 1, 2, 0), PETAL);
        setBlock(level, pos.offset(0, 2, - 1), PETAL);
        setBlock(level, pos.offset(1, 2, 1), PETAL);
        setBlock(level, pos.offset(1, 2, - 1), PETAL);
        setBlock(level, pos.offset(- 1, 2, 1), PETAL);
        setBlock(level, pos.offset(- 1, 2, - 1), PETAL);
        setBlock(level, pos.offset(2, 2, 0), PETAL);
        setBlock(level, pos.offset(0, 2, 2), PETAL);
        setBlock(level, pos.offset(- 2, 2, 0), PETAL);
        setBlock(level, pos.offset(0, 2, - 2), PETAL);
        setBlock(level, pos.offset(2, 3, 0), PETAL);
        setBlock(level, pos.offset(0, 3, 2), PETAL);
        setBlock(level, pos.offset(- 2, 3, 0), PETAL);
        setBlock(level, pos.offset(0, 3, - 2), PETAL);
    }

    private void addChestAndSpawners(WorldGenLevel level, BlockPos pos) {
        setBlock(level, pos.above(), Blocks.CHEST.defaultBlockState());
        ChestBlockEntity chest = (ChestBlockEntity) level.getBlockEntity(pos.above());
        if(chest != null) {
            chest.setLootTable(ModChestLootTables.DRAGONFLY_DUNGEON);
        }
        setBlock(level, pos.above(2), SPAWNER);
        setBlock(level, pos.above(3), SPAWNER);
    }

    private void zAirGap(WorldGenLevel level, BlockPos pos, int z) {
        setBlock(level, pos.offset(0, 0, z), AIR);
        setBlock(level, pos.offset(0, 1, z), AIR);
        setBlock(level, pos.offset(-1, 1, z), GIANT_LILY_PAD);
        setBlock(level, pos.offset(1, 1, z), GIANT_LILY_PAD);
    }

    private void xAirGap(WorldGenLevel level, BlockPos pos, int x) {
        setBlock(level, pos.offset(x, 0, 0), AIR);
        setBlock(level, pos.offset(x, 1, 0), AIR);
        setBlock(level, pos.offset(x, 1, -1), GIANT_LILY_PAD);
        setBlock(level, pos.offset(x, 1, 1), GIANT_LILY_PAD);
    }
}
