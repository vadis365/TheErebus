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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.ScatteredFeaturePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import org.jetbrains.annotations.NotNull;

public class AntlionLairPiece extends ScatteredFeaturePiece {

    private final BlockState AIR = Blocks.AIR.defaultBlockState();
    //private final BlockState GHOST_SAND = OtherBlocks.GHOST_SAND.get().defaultBlockState();
    private final BlockState GHOST_SAND = Blocks.PINK_WOOL.defaultBlockState();
    private final BlockState SAND = Blocks.SAND.defaultBlockState();

    public AntlionLairPiece(RandomSource random, int x, int z) {
        super(ModStructurePieces.ANTLION_LAIR.get(), x, 30, z, 11, 10, 11, getRandomHorizontalDirection(random));
    }

    public AntlionLairPiece(StructurePieceSerializationContext ignoredContext, CompoundTag tag) {
        super(ModStructurePieces.ANTLION_LAIR.get(), tag);
    }

    @Override
    public void postProcess(@NotNull WorldGenLevel level, @NotNull StructureManager manager, @NotNull ChunkGenerator generator, @NotNull RandomSource random, @NotNull BoundingBox boundingBox, @NotNull ChunkPos chunkPos, @NotNull BlockPos pos) {
        boolean found = false;
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        for(int c = 0; c < 15; c++) {
            if(level.isEmptyBlock(pos) && level.getBlockState(pos.below()).is(Blocks.SAND)) {
                for(int xOff = -4; xOff <= 4; xOff++) {
                    for(int zOff = -4; zOff <= 4; zOff++) {
                        if(!level.isEmptyBlock(pos.offset(xOff, 0, zOff)) || level.getBlockState(pos.offset(xOff, 0, zOff).below()).is(Blocks.SAND)) {
                            return;
                        }
                    }
                }
                found = true;
                break;
            }

            if(pos.below(c).getY() <= 12) return;
        }

        if(!found) return;

        for (int xx = x - 5; xx <= x + 5; xx++) {
            for (int zz = z - 5; zz <= z + 5; zz++) {
                for (int yy = y - 1, layer = 0; yy >= y - 7; yy--, layer++) {
                    if (Math.sqrt(Math.pow(xx - x, 2) + Math.pow(zz - z, 2)) < 4.9D && yy != y - 7) {
                        if (yy >= y - 3 || Math.abs(xx - x) <= 1 + 6 - layer && Math.abs(zz - z) <= 1 + 6 - layer) {
                            placeBlock(level, yy == y - 1 ? GHOST_SAND : AIR, xx, yy, zz, boundingBox);
                        }
                    }

                    if (layer > 0 && !level.isEmptyBlock(new BlockPos(xx, yy, zz))) {
                        placeBlock(level, SAND, xx, yy, zz, boundingBox);
                    }
                }
            }
        }

        level.setBlock(pos.below(7), Blocks.CHEST.defaultBlockState(), Block.UPDATE_ALL);
        ChestBlockEntity chest = (ChestBlockEntity)level.getBlockEntity(pos.below(7));
        if(chest != null) chest.setLootTable(ModChestLootTables.ANTLION_LAIR);
        Chicken chicken = new Chicken(EntityType.CHICKEN, level.getLevel());
        chicken.setChickenJockey(true);
        BlockPos chickenPos = pos.below(5);
        chicken.setPos(chickenPos.getX() + 0.5D, chickenPos.getY() + 0.5D, chickenPos.getZ() + 0.5D);
        level.getLevel().addFreshEntity(chicken);
    }
}
