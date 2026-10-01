package erebus.world.feature.tree.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.world.tree.ModTrunkPlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jspecify.annotations.NonNull;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.BiConsumer;

public class MossbarkTrunkPlacer extends TrunkPlacer {

    public static final MapCodec<MossbarkTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.intRange(0, 32).fieldOf("base_height").forGetter(placer -> placer.baseHeight),
                    Codec.intRange(0, 32).fieldOf("height_rand_a").forGetter(placer -> placer.heightRandA),
                    Codec.intRange(0, 32).fieldOf("height_rand_b").forGetter(placer -> placer.heightRandB)
            ).apply(instance, MossbarkTrunkPlacer::new)
    );

    public MossbarkTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected @NonNull TrunkPlacerType<?> type() {
        return ModTrunkPlacers.MOSSBARK_TRUNK_PLACER.get();
    }

    @Override
    public int getTreeHeight(RandomSource random) {
        return baseHeight + random.nextInt(heightRandA + 1) + (heightRandB == 0 ? 0 : random.nextInt(heightRandB + 1));
    }

    @Override
    public @NonNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NonNull WorldGenLevel level, @NonNull BiConsumer<BlockPos, BlockState> trunkSetter, @NonNull RandomSource random, int stump, @NonNull BlockPos origin, @NonNull TreeConfiguration config) {
        int[] middle = new int[4], outside = new int[4];
        var sides = new Direction[]{Direction.WEST, Direction.EAST, Direction.NORTH, Direction.SOUTH};
        int max = 0;
        for (int i = 0; i < 4; i++) {
            middle[i] = 1 + random.nextInt(2);
            outside[i] = middle[i] + random.nextInt(4);
            max = Math.max(max, middle[i] + outside[i]);
        }
        int top = stump + max + 3; // Includes the reference's extra clearance band above the actual leaves.
        if (level.isOutsideBuildHeight(origin.below()) || level.isOutsideBuildHeight(origin.above(top))) return List.of();
        var soil = level.getBlockState(origin.below());
        if (soil != Blocks.DIRT.defaultBlockState() && soil != Blocks.GRASS_BLOCK.defaultBlockState()) return List.of();
        for (int y = 0; y < 3; y++) if (!level.isEmptyBlock(origin.above(y))) return List.of();
        for (int y = 3; y <= top; y++)
            for (int a = -4; a <= 4; a++)
                for (int b = -1; b <= 1; b++) {
                    if (!level.isEmptyBlock(origin.offset(a, y, b)) || !level.isEmptyBlock(origin.offset(b, y, a))) return List.of();
                }
        var logs = new LinkedHashMap<BlockPos, Direction.Axis>();
        var leaves = new LinkedHashSet<BlockPos>();
        for (int y = 0; y < stump; y++) logs.put(origin.above(y), Direction.Axis.Y);
        for (int i = 0; i < 4; i++) {
            var side = sides[i];
            logs.put(origin.above(stump - 1).relative(side), side.getAxis());
            var inner = origin.above(stump).relative(side, 2);
            leaves.add(inner.below());
            leaves.add(inner.below(2));
            for (int y = 0; y < middle[i]; y++) logs.put(inner.above(y), Direction.Axis.Y);
            var outer = origin.above(stump + middle[i]).relative(side, 3);
            for (int y = 0; y < 2; y++) {
                leaves.add(outer.below(1 + y));
                leaves.add(outer.above(outside[i] + y));
            }
            for (int y = 0; y < outside[i]; y++) {
                if (y < outside[i] - 1) logs.put(outer.above(y), Direction.Axis.Y);
                for (var leafSide : sides) leaves.add(outer.above(y).relative(leafSide));
            }
        }
        // Low hanging leaves extend outside the legacy clearance scan; protect their targets too.
        var targets = new HashSet<>(logs.keySet());
        targets.addAll(leaves);
        for (var target : targets) if (level.isOutsideBuildHeight(target) || !level.isEmptyBlock(target)) return List.of();
        logs.forEach((pos, axis) -> trunkSetter.accept(pos, config.trunkProvider.getState(level, random, pos)
                .setValue(RotatedPillarBlock.AXIS, axis)));
        return leaves.stream().map(p -> new FoliagePlacer.FoliageAttachment(p, 0, false)).toList();
    }
}
