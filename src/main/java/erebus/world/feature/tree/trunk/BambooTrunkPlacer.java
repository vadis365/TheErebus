package erebus.world.feature.tree.trunk;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.block.bamboo.BambooBlock;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.tree.ModTrunkPlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.BiConsumer;

public class BambooTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<BambooTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance -> trunkPlacerParts(instance).apply(instance, BambooTrunkPlacer::new));

    public BambooTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected @NonNull TrunkPlacerType<?> type() {
        return ModTrunkPlacers.BAMBOO_TRUNK_PLACER.get();
    }

    @Override
    public @NonNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NonNull WorldGenLevel level, @NonNull BiConsumer<BlockPos, BlockState> trunkSetter, @NonNull RandomSource random, int treeHeight, @NonNull BlockPos origin, @NonNull TreeConfiguration config) {
        for(int y = 0; y < treeHeight; y++) {
            trunkSetter.accept(origin.above(y), ModBlocks.COLOSSAL_BAMBOO.get().defaultBlockState().setValue(BambooBlock.AGE, random.nextInt(15)));
        }

        return List.of();
    }
}
