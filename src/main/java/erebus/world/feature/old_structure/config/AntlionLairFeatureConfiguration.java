package erebus.world.feature.old_structure.config;

import erebus.datagen.loot.ModChestLootTables;
import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class AntlionLairFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    public AntlionLairFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        boolean found = false;

        for(int c = 0; c < 15; c++) {
            if(level.isEmptyBlock(pos) && level.getBlockState(pos.below()).is(Blocks.SAND)) {
                for(int xx = -4; xx <= 4; xx++) {
                    for(int zz = -4; zz <= 4; zz++) {
                        BlockPos offset = pos.offset(xx, 0, zz);
                        if(!level.isEmptyBlock(offset) || level.getBlockState(offset.below()).is(Blocks.SAND)) {
                            return false;
                        }
                    }
                }
                found = true;
                break;
            }
        }

        if(!found) return false;

        for(int xx = x - 5; xx <= x + 5; xx++) {
            for(int zz = z - 5; zz <= x + 5; zz++) {
                for(int yy = y - 1, layer = 0; yy >= y - 7; yy--, layer++) {
                    if(Math.sqrt(Math.pow(xx - x, 2) + Math.pow(zz - z, 2)) < 4.9D && yy != y - 7) {
                        if(yy >= y - 3 || Math.abs(xx - x) <= 1 + 6 - layer && Math.abs(zz - z) <= 1 + 6 - layer) {
                            if(yy == y - 1) {
                                setBlock(level, pos.offset(xx, yy, zz), OtherBlocks.GHOST_SAND.get().defaultBlockState());
                            } else {
                                setBlock(level, pos.offset(xx, yy, zz), Blocks.AIR.defaultBlockState());
                            }
                        }
                    }

                    if(layer > 0 && !level.isEmptyBlock(pos.offset(xx, yy, zz))) {
                        setBlock(level, pos.offset(xx, yy, zz), Blocks.SAND.defaultBlockState());
                    }
                }
            }
        }

        setBlock(level, pos.below(7), Blocks.CHEST.defaultBlockState());
        ChestBlockEntity chest = (ChestBlockEntity)level.getBlockEntity(pos.below(7));
        if(chest != null) chest.setLootTable(ModChestLootTables.ANTLION_LAIR);
        Chicken chicken = new Chicken(EntityType.CHICKEN, level.getLevel());
        chicken.setChickenJockey(true);
        chicken.setPos(x, y - 5, z);
        level.getLevel().addFreshEntity(chicken);

        return true;
    }
}
