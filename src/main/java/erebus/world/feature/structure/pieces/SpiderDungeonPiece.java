package erebus.world.feature.structure.pieces;

import erebus.datagen.loot.ModChestLootTables;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.structure.ModStructurePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.ScatteredFeaturePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import org.jetbrains.annotations.NotNull;

public class SpiderDungeonPiece extends ScatteredFeaturePiece {
    public SpiderDungeonPiece(RandomSource random, int x, int z) {
        super(ModStructurePieces.SPIDER_DUNGEON.get(), x, 64, z, 16, 7, 13, getRandomHorizontalDirection(random));
    }

    public SpiderDungeonPiece(StructurePieceSerializationContext ignoredContext, CompoundTag tag) {
        super(ModStructurePieces.SPIDER_DUNGEON.get(), tag);
    }

    @Override
    public void postProcess(@NotNull WorldGenLevel level, @NotNull StructureManager manager, @NotNull ChunkGenerator generator, @NotNull RandomSource random, @NotNull BoundingBox boundingBox, @NotNull ChunkPos chunkPos, @NotNull BlockPos pos) {
        int halfSizeX = random.nextInt(4) + 4;
        int halfSizeZ = random.nextInt(4) + 4;
        int xOff;

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
                            setBlock(level, check, ModBlocks.UMBERCOBBLE_WEBBED.get().defaultBlockState());
                        } else if(y == -1 && random.nextInt(4) == 0 || y == height + 1 && random.nextInt(4) == 0) {
                            setBlock(level, check, ModBlocks.UMBERCOBBLE_MOSSY.get().defaultBlockState());
                        } else {
                            setBlock(level, check, ModBlocks.UMBERCOBBLE.get().defaultBlockState());
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
    }

    private void setBlock(WorldGenLevel level, BlockPos pos, BlockState state) {
        level.setBlock(pos, state, Block.UPDATE_ALL);
    }

    private BlockState getRandomSpawner(RandomSource random) {
        int type = random.nextInt(3);

        return switch(type) {
            case 1 -> ModBlocks.JUMPING_SPIDER_SPAWNER.get().defaultBlockState();
            case 2 -> ModBlocks.TARANTULA_SPAWNER.get().defaultBlockState();
            default -> ModBlocks.SPIDER_SPAWNER.get().defaultBlockState();
        };
    }
}
