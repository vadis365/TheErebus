package erebus.world.feature.old_structure.config;

import erebus.datagen.loot.ModChestLootTables;
import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.blocks.providers.UmberstoneBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class DungPileFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    private final int height = 5;
    private final int radius = 5;
    private final BlockState DUNG = UmberstoneBlocks.DUNG.get().defaultBlockState();
    private final BlockState BOT_FLY = OtherBlocks.DUNG_SPAWNER_BOT_FLY.get().defaultBlockState();
    private final BlockState FLY = OtherBlocks.DUNG_SPAWNER_FLY.get().defaultBlockState();

    public DungPileFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        if(canPlace(level, pos)) {
            for(int x = -radius; x <= radius; x++) {
                for(int z = -radius; z <= radius; z++) {
                    for(int y = 0; y < height; y++) {
                        double dome = Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2);
                        long rounded = Math.round(Math.sqrt(dome));
                        BlockPos offset = pos.offset(x, y, z);

                        if(y == 0 && rounded == radius) setBlock(level, offset, DUNG);
                        if(rounded < radius) setBlock(level, offset, DUNG);
                    }

                    if(random.nextInt(5) == 0) setBlock(level, pos.offset(x, 0, z), DUNG);
                }
            }

            setBlock(level, pos, Blocks.CHEST.defaultBlockState());
            ChestBlockEntity chest = (ChestBlockEntity)level.getBlockEntity(pos);
            if(chest != null) chest.setLootTable(ModChestLootTables.DUNG_PILE);

            setBlock(level, pos.offset(-1, 1, 1), BOT_FLY);
            setBlock(level, pos.offset(1, 1, 1), FLY);
            setBlock(level, pos.offset(1, 1, -1), BOT_FLY);
            setBlock(level, pos.offset(-1, 1, -1), FLY);
            setBlock(level, pos.offset(1, height, 1), FLY);

            return true;
        }

        return false;
    }

    private boolean canPlace(WorldGenLevel level, BlockPos pos) {
        for(int x = -radius; x <= radius; x++) {
            for(int z = -radius; z <= radius; z++) {
                if(!level.getBlockState(pos.offset(x, -1, z)).is(Blocks.SAND)) return false;
                for(int y = 0; y < height; y++) {
                    if(!level.isEmptyBlock(pos.offset(x, y, z))) return false;
                }
            }
        }
        return true;
    }
}
