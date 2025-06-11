package erebus.world.feature.structure.pieces;

import erebus.datagen.loot.ModChestLootTables;
import erebus.registries.world.structure.ModStructurePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.ScatteredFeaturePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import org.jetbrains.annotations.NotNull;

public class AntlionLairPiece extends ScatteredFeaturePiece {

    public AntlionLairPiece(RandomSource random, int x, int z) {
        super(ModStructurePieces.ANTLION_LAIR.get(), x, 30, z, 11, 10, 11, getRandomHorizontalDirection(random));
    }

    public AntlionLairPiece(StructurePieceSerializationContext ignoredContext, CompoundTag tag) {
        super(ModStructurePieces.ANTLION_LAIR.get(), tag);
    }

    @Override
    public void postProcess(@NotNull WorldGenLevel level, @NotNull StructureManager manager, @NotNull ChunkGenerator generator, @NotNull RandomSource random, @NotNull BoundingBox boundingBox, @NotNull ChunkPos chunkPos, @NotNull BlockPos pos) {
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();

        for(int xx = x - 5; xx <= x + 5; xx++) {
            for(int zz = z - 5; zz <= x + 5; zz++) {
                for(int yy = y - 1, layer = 0; yy >= y - 7; yy--, layer++) {
                    if(Math.sqrt(Math.pow(xx - x, 2) + Math.pow(zz - z, 2)) < 4.9D && yy != y - 7) {
                        if(yy >= y - 3 || Math.abs(xx - x) <= 1 + 6 - layer && Math.abs(zz - z) <= 1 + 6 - layer) {
                            if(yy == y - 1) {
                                level.setBlock(pos.offset(xx, yy, zz), Blocks.SANDSTONE.defaultBlockState(), Block.UPDATE_ALL);
                            } else {
                                level.setBlock(pos.offset(xx, yy, zz), Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                            }
                        }
                    }

                    if(layer > 0) {
                        level.setBlock(pos.offset(xx, yy, zz), Blocks.SAND.defaultBlockState(), Block.UPDATE_ALL);
                    }
                }
            }
        }

        level.setBlock(pos.below(7), Blocks.CHEST.defaultBlockState(), Block.UPDATE_ALL);
        ChestBlockEntity chest = (ChestBlockEntity)level.getBlockEntity(pos.below(7));
        if(chest != null) chest.setLootTable(ModChestLootTables.ANTLION_LAIR);
        Chicken chicken = new Chicken(EntityType.CHICKEN, level.getLevel());
        chicken.setChickenJockey(true);
        chicken.setPos(x, y - 5, z);
        level.getLevel().addFreshEntity(chicken);
    }
}
