package erebus.world.feature.old_structure.config;

import erebus.datagen.loot.ModChestLootTables;
import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.blocks.providers.UmberstoneBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class SpiderDungeonFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    public SpiderDungeonFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();
        byte height = 4;
        int halfSizeX = random.nextInt(4) + 4;
        int halfSizeZ = random.nextInt(4) + 4;
        int j1 = 0;
        int xOff;

        if(pos.getY() + height + 1 >= 127) return false;

        for(int x = -halfSizeX - 1; x <= halfSizeX + 1; x++) {
            for(int y = -1; y <= height + 1; y++) {
                for (int z = -halfSizeZ - 1; z <= halfSizeZ + 1; z++) {
                    BlockPos check = pos.offset(x, y, z);
                    if((y == -1 || y == height + 1) && !level.getBlockState(check).isSolid()) return false;

                    if((x == -halfSizeX - 1 || x == halfSizeX + 1 || z == -halfSizeZ - 1 || z == halfSizeZ + 1) && level.isEmptyBlock(check) && level.isEmptyBlock(check.above())) {
                        ++j1;
                    }
                }
            }
        }

        if(j1 >= 1 && j1 <= 5) {
            for(int x = -halfSizeX - 1; x <= halfSizeX + 1; x++) {
                for(int y = -1; y <= height + 1; y++) {
                    for (int z = -halfSizeZ - 1; z <= halfSizeZ + 1; z++) {
                        BlockPos check = pos.offset(x, y, z);
                        if(x != -halfSizeX - 1 && y != -1 && z != -halfSizeX - 1 && x != halfSizeX + 1 && y != height + 1 && z != halfSizeZ + 1) {
                            setBlock(level, check, Blocks.AIR.defaultBlockState());
                        } else if(y >= 0 && !level.getBlockState(check.below()).isSolid()) {
                            setBlock(level, check, Blocks.AIR.defaultBlockState());
                        } else if(level.getBlockState(check).isSolid()) {
                            if(y == -1 && random.nextInt(4) == 0 || y == height + 1 && random.nextInt(4) == 0) {
                                setBlock(level, check, UmberstoneBlocks.UMBERCOBBLE_WEBBED.get().defaultBlockState());
                            } else if(y == -1 && random.nextInt(4) == 0 || y == height + 1 && random.nextInt(4) == 0) {
                                setBlock(level, check, UmberstoneBlocks.UMBERCOBBLE_MOSSY.get().defaultBlockState());
                            } else {
                                setBlock(level, check, UmberstoneBlocks.UMBERCOBBLE.get().defaultBlockState());
                            }
                        }
                    }
                }
            }

            for(int c = 0; c < 2; c++) {
                for(int d = 0; d < (c == 0 ? Integer.MAX_VALUE : 3); d++) {
                    xOff = random.nextInt(halfSizeX * 2 + 1) - halfSizeX;
                    int zOff = random.nextInt(halfSizeZ * 2 + 1) - halfSizeZ;

                    if(level.isEmptyBlock(pos.offset(xOff, 0, zOff))) {
                        int adjacentSolidBlocks = 0;
                        Direction facing = Direction.EAST;

                        if(level.getBlockState(pos.offset(xOff - 1, 0, zOff)).isSolid()) {
                            ++adjacentSolidBlocks;
                            facing = Direction.EAST;
                        }

                        if(level.getBlockState(pos.offset(xOff + 1, 0, zOff)).isSolid()) {
                            ++adjacentSolidBlocks;
                            facing = Direction.WEST;
                        }

                        if(level.getBlockState(pos.offset(xOff, 0, zOff - 1)).isSolid()) {
                            ++adjacentSolidBlocks;
                            facing = Direction.SOUTH;
                        }

                        if(level.getBlockState(pos.offset(xOff, 0, zOff + 1)).isSolid()) {
                            ++adjacentSolidBlocks;
                            facing = Direction.NORTH;
                        }

                        if(adjacentSolidBlocks == 1) {
                            setBlock(level, pos.offset(xOff, 0, zOff), Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, facing));
                            ChestBlockEntity chest = (ChestBlockEntity)level.getBlockEntity(pos.offset(xOff, 0, zOff));
                            if(chest != null) chest.setLootTable(ModChestLootTables.SPIDER_DUNGEON);
                            break;
                        }
                    }
                }
            }
            BlockState web = Blocks.COBWEB.defaultBlockState();

            setBlock(level, pos.offset(1, 0, 0), web);
            setBlock(level, pos.offset(-1, 0, 0), web);
            setBlock(level, pos.offset(0, 0, 1), web);
            setBlock(level, pos.offset(0, 0, -1), web);
            setBlock(level, pos.offset(0, 1, 0), web);
            setBlock(level, pos.offset(0, -1, 0), web);
            setBlock(level, pos, getRandomSpawner(random));

            return true;
        }

        return false;
    }

    private BlockState getRandomSpawner(RandomSource random) {
        int type = random.nextInt(3);

        return switch(type) {
            case 1 -> OtherBlocks.JUMPING_SPIDER_SPAWNER.get().defaultBlockState();
            case 2 -> OtherBlocks.TARANTULA_SPAWNER.get().defaultBlockState();
            default -> OtherBlocks.SPIDER_SPAWNER.get().defaultBlockState();
        };
    }
}
