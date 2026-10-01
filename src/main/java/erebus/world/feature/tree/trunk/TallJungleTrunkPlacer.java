package erebus.world.feature.tree.trunk;

import com.mojang.serialization.MapCodec;
import erebus.registries.world.tree.ModTrunkPlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jspecify.annotations.NonNull;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.BiConsumer;

public class TallJungleTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<TallJungleTrunkPlacer> CODEC = MapCodec.unit(TallJungleTrunkPlacer::new);

    public TallJungleTrunkPlacer() {
        super(0, 0, 0);
    }

    @Override
    protected @NonNull TrunkPlacerType<?> type() {
        return ModTrunkPlacers.TALL_JUNGLE_TRUNK_PLACER.get();
    }

    @Override
    public int getTreeHeight(RandomSource random) {
        return 3 + random.nextInt(3) + (2 + random.nextInt(4)) * 3;
    }

    @Override
    public @NonNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NonNull WorldGenLevel level, @NonNull BiConsumer<BlockPos, BlockState> setter, @NonNull RandomSource random, int height, @NonNull BlockPos origin, @NonNull TreeConfiguration config) {
        int base = 3 + height % 3;
        int branches = (height - base) / 3;
        if (branches < 2 || branches > 5 || level.isOutsideBuildHeight(origin.below()) || level.isOutsideBuildHeight(origin.above(height + 1))) return List.of();
        var soil = level.getBlockState(origin.below());
        if (soil != Blocks.GRASS_BLOCK.defaultBlockState() && soil != Blocks.DIRT.defaultBlockState()) return List.of();
        for (int y = 0; y < base; y++) if (!level.isEmptyBlock(origin.above(y))) return List.of();
        for (int y = base; y <= height + 1; y++)
            for (int x = -2; x <= 2; x++)
                for (int z = -2; z <= 2; z++)
                    if (!level.isEmptyBlock(origin.offset(x, y, z))) return List.of();
        var leaves = new LinkedHashSet<BlockPos>();
        for (int y = 0; y < height; y++) setter.accept(origin.above(y), config.trunkProvider.getState(level, random, origin.above(y)));
        for (int branch = 0; branch < branches; branch++) {
            var center = origin.above(base + branch * 3);
            for (var side : Direction.Plane.HORIZONTAL) {
                leaves.add(center.relative(side));
                leaves.add(center.above(2).relative(side));
                leaves.add(center.above().relative(side, 2));
            }
            for (int x : new int[]{-1, 1}) for (int z : new int[]{-1, 1}) leaves.add(center.offset(x, 1, z));
        }
        leaves.add(origin.above(height));
        return leaves.stream().map(p -> new FoliagePlacer.FoliageAttachment(p, 0, false)).toList();
    }
}
