package erebus.world.feature.plant.config;

import erebus.datagen.loot.ModChestLootTables;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class RottenTreeStumpFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    private final int maxRadius = 6;
    private final int maxHeight = 16;
    private int height = -1;
    private int baseRadius = -1;

    public RottenTreeStumpFeatureConfiguration(int height, int radius) {
        super(NoneFeatureConfiguration.CODEC);
        this.height = height;
        this.baseRadius = radius;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();
        BlockState log = ModBlocks.LOG_ROTTEN.get().defaultBlockState();

        for (int x = -baseRadius; x <= baseRadius; x++) {
            for (int y = 1; y < height; y++) {
                for (int z = -baseRadius; z <= baseRadius; z++) {
                    if (!level.isEmptyBlock(pos.offset(x, y, z))) return false;
                }
            }
        }

        if(level.isEmptyBlock(pos.below())) return false;

        generateTrunk(level, pos, random, log);
        generateRoots(level, pos, random, log);

        if (baseRadius >= maxRadius) {
            generateLowerSpawner(level, pos);
            generateUpperSpawner(level, pos);
            addChests(level, pos, random, log);
        }

        return true;
    }

    private void generateTrunk(WorldGenLevel level, BlockPos pos, RandomSource random, BlockState log) {
        int radius = baseRadius - 1;
        BlockState air = Blocks.AIR.defaultBlockState();

        for (int y = 0; y <= height; y++) {
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    double dSq = x * x + z * z;
                    long rounded = Math.round(Math.sqrt(dSq));
                    BlockPos check = pos.offset(x, y, z);

                    if (rounded == radius) {
                        setBlock(level, check, log);
                        if (y >= 5 && random.nextInt(20) == 0) {
                            setBlock(level, check, air);
                        }

                        if (y == height && random.nextInt(2) == 0) {
                            setBlock(level, check, air);
                        }
                    } else {
                        setBlock(level, check, air);
                    }

                    if (rounded <= radius - 1 && baseRadius >= maxRadius && height >= maxHeight) {
                        if (y == 10) {
                            setBlock(level, check, log);
                        }
                    }
                }
            }
        }
    }

    private void generateRoots(WorldGenLevel level, BlockPos pos, RandomSource random, BlockState log) {
        for (int x = -baseRadius; x <= baseRadius; x++) {
            for (int z = -baseRadius; z <= baseRadius; z++) {
                double dSq = x * x + z * z;
                long rounded = Math.round(Math.sqrt(dSq));

                if (rounded <= baseRadius) {
                    setBlock(level, pos.offset(x, 0, z), log);
                    setBlock(level, pos.offset(x + random.nextInt(2) - 1, 0, z + random.nextInt(2) - 1), log);
                    setBlock(level, pos.offset(x, random.nextInt(2), z), log);
                }

                if (rounded == baseRadius) {
                    for (int y = 0; y < height; y++) {
                        if (y < height - 1 && y > 5 && random.nextInt(12) == 0) {
                            setBlock(level, pos.offset(x, y, z), log);
                            setBlock(level, pos.offset(x + random.nextInt(2) - 1, y, z + random.nextInt(2) - 1), log);
                        }
                    }
                }
            }
        }
    }

    private void generateLowerSpawner(WorldGenLevel level, BlockPos pos) {
        BlockState web = Blocks.COBWEB.defaultBlockState();
        BlockState spawner = ModBlocks.JUMPING_SPIDER_SPAWNER.get().defaultBlockState();

        setBlock(level, pos.above(2).north(), web);
        setBlock(level, pos.above(2).south(), web);
        setBlock(level, pos.above(2).east(), web);
        setBlock(level, pos.above(2).west(), web);
        setBlock(level, pos.above(), web);
        setBlock(level, pos.above(2), spawner);
        setBlock(level, pos.above(3), web);
    }

    private void generateUpperSpawner(WorldGenLevel level, BlockPos pos) {
        BlockState web = Blocks.COBWEB.defaultBlockState();
        BlockState spawner = ModBlocks.TARANTULA_SPAWNER.get().defaultBlockState();

        if (height >= maxHeight - 3) {
            setBlock(level, pos.above(11), web);
            setBlock(level, pos.above(12).north(), web);
            setBlock(level, pos.above(12).south(), web);
            setBlock(level, pos.above(12).east(), web);
            setBlock(level, pos.above(12).west(), web);
            setBlock(level, pos.above(12), spawner);
            setBlock(level, pos.above(13), web);
        }
    }

    private void addChests(WorldGenLevel level, BlockPos pos, RandomSource random, BlockState log) {
        BlockState chest = Blocks.CHEST.defaultBlockState();

        setBlock(level, pos, chest);

        ChestBlockEntity chest1 = (ChestBlockEntity) level.getBlockEntity(pos);
        if (chest1 != null) chest1.setLootTable(ModChestLootTables.ROTTEN_LOG);

        int z = random.nextInt(5) - 2;
        BlockPos chest2Pos = pos.offset(-4, 11, z);
        BlockPos chest3Pos = pos.offset(4, 11, z);

        setBlock(level, chest2Pos, chest);
        setBlock(level, chest3Pos, chest);
        setBlock(level, chest2Pos.above(), log);
        setBlock(level, chest3Pos.above(), log);

        ChestBlockEntity chest2 = (ChestBlockEntity) level.getBlockEntity(chest2Pos);
        ChestBlockEntity chest3 = (ChestBlockEntity) level.getBlockEntity(chest3Pos);

        if (chest2 != null) chest2.setLootTable(ModChestLootTables.ROTTEN_LOG);
        if (chest3 != null) chest3.setLootTable(ModChestLootTables.ROTTEN_LOG);
    }
}
