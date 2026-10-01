package erebus.world.feature.tree.trunk;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.world.tree.ModTrunkPlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jspecify.annotations.NonNull;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

public class GiantMahoganyTrunkPlacer extends GiantTrunkPlacer {
    public static final MapCodec<GiantMahoganyTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> trunkPlacerParts(instance).apply(instance, GiantMahoganyTrunkPlacer::new));

    public GiantMahoganyTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    private static void crown(Set<BlockPos> leaves, RandomSource random, BlockPos anchor, int extraRadius) {
        for (int y = -2; y <= 0; y++) {
            int radius = extraRadius + 1 - y;
            for (int x = -radius; x <= radius + 1; x++) {
                for (int z = -radius; z <= radius + 1; z++) {
                    int distance = x * x + z * z;
                    if ((x >= 0 || z >= 0 || distance <= radius * radius)
                            && ((x <= 0 && z <= 0) || distance <= (radius + 1) * (radius + 1))
                            && (random.nextInt(4) != 0 || distance <= (radius - 1) * (radius - 1))) {
                        leaves.add(anchor.offset(x, y, z));
                    }
                }
            }
        }
    }

    @Override
    protected @NonNull TrunkPlacerType<?> type() {
        return ModTrunkPlacers.GIANT_MAHOGANY_TRUNK_PLACER.get();
    }

    @Override
    public @NonNull List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel level, @NonNull BiConsumer<BlockPos, BlockState> setter, @NonNull RandomSource random, int height, BlockPos origin, @NonNull TreeConfiguration config) {
        if (level.isOutsideBuildHeight(origin.below()) || level.isOutsideBuildHeight(origin.above(height + 1))) return List.of();
        for (int x = 0; x < 2; x++)
            for (int z = 0; z < 2; z++) {
                var below = origin.offset(x, -1, z);
                var soil = level.getBlockState(below);
                if (!(soil.is(BlockTags.DIRT) || soil.is(BlockTags.GRASS_BLOCKS)) || soil.hasBlockEntity() || !soil.getFluidState().isEmpty() || !soil.isFaceSturdy(level, below, Direction.UP)) return List.of();
            }
        super.placeTrunk(level, setter, random, height, origin, config);
        Set<BlockPos> leaves = new LinkedHashSet<>();
        crown(leaves, random, origin.above(height), 2);
        for (int y = height - 2 - random.nextInt(4); y > height / 2; y -= 2 + random.nextInt(4)) {
            float angle = random.nextFloat() * (float) Math.PI * 2.0F;
            var anchor = origin.offset((int) (0.5F + Mth.cos(angle) * 4.0F), y, (int) (0.5F + Mth.sin(angle) * 4.0F));
            crown(leaves, random, anchor, 0);
            for (int step = 0; step < 5; step++) {
                var pos = origin.offset((int) (1.5F + Mth.cos(angle) * step), y - 3 + step / 2, (int) (1.5F + Mth.sin(angle) * step));
                if (!level.isOutsideBuildHeight(pos)) placeLog(level, setter, random, pos, config);
            }
        }
        return leaves.stream().filter(pos -> !level.isOutsideBuildHeight(pos)).map(pos -> new FoliagePlacer.FoliageAttachment(pos, 0, false)).toList();
    }
}
