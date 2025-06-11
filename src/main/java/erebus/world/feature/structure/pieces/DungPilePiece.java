package erebus.world.feature.structure.pieces;

import erebus.datagen.loot.ModChestLootTables;
import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.blocks.providers.UmberstoneBlocks;
import erebus.registries.world.structure.ModStructurePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.ScatteredFeaturePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import org.jetbrains.annotations.NotNull;

public class DungPilePiece extends ScatteredFeaturePiece {

    private final BlockState DUNG = UmberstoneBlocks.DUNG.get().defaultBlockState();
    private final BlockState BOT_FLY = OtherBlocks.DUNG_SPAWNER_BOT_FLY.get().defaultBlockState();
    private final BlockState FLY = OtherBlocks.DUNG_SPAWNER_FLY.get().defaultBlockState();

    public DungPilePiece(RandomSource random, int x, int z) {
        super(ModStructurePieces.DUNG_PILE.get(), x, 64, z, 10, 8, 10, getRandomHorizontalDirection(random));
    }

    public DungPilePiece(StructurePieceSerializationContext ignoredContext, CompoundTag tag) {
        super(ModStructurePieces.DUNG_PILE.get(), tag);
    }

    @Override
    public void postProcess(@NotNull WorldGenLevel level, @NotNull StructureManager manager, @NotNull ChunkGenerator generator, @NotNull RandomSource random, @NotNull BoundingBox boundingBox, @NotNull ChunkPos chunkPos, @NotNull BlockPos pos) {
        int radius = 5;
        int height = 5;
        for(int x = -radius; x <= radius; x++) {
            for(int z = -radius; z <= radius; z++) {
                for(int y = 0; y < height; y++) {
                    double dome = Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2);
                    long rounded = Math.round(Math.sqrt(dome));
                    BlockPos offset = pos.offset(x, y, z);

                    if(y == 0 && rounded == radius) level.setBlock(offset, DUNG, Block.UPDATE_ALL);
                    if(rounded < radius) level.setBlock(offset, DUNG, Block.UPDATE_ALL);
                }

                if(random.nextInt(5) == 0) level.setBlock(pos.offset(x, 0, z), DUNG, Block.UPDATE_ALL);
            }
        }

        level.setBlock(pos, Blocks.CHEST.defaultBlockState(), Block.UPDATE_ALL);
        ChestBlockEntity chest = (ChestBlockEntity)level.getBlockEntity(pos);
        if(chest != null) chest.setLootTable(ModChestLootTables.DUNG_PILE);

        level.setBlock(pos.offset(-1, 1, 1), BOT_FLY, Block.UPDATE_ALL);
        level.setBlock(pos.offset(1, 1, 1), FLY, Block.UPDATE_ALL);
        level.setBlock(pos.offset(1, 1, -1), BOT_FLY, Block.UPDATE_ALL);
        level.setBlock(pos.offset(-1, 1, -1), FLY, Block.UPDATE_ALL);
        level.setBlock(pos.offset(1, height, 1), FLY, Block.UPDATE_ALL);
    }
}