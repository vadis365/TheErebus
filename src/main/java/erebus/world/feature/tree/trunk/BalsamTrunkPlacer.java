package erebus.world.feature.tree.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.world.tree.ModTrunkPlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jspecify.annotations.NonNull;

import java.util.*;
import java.util.function.BiConsumer;

public class BalsamTrunkPlacer extends TrunkPlacer {


    public static final MapCodec<BalsamTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.intRange(0, 32).fieldOf("base_height").forGetter(placer -> placer.baseHeight),
                    Codec.intRange(0, 32).fieldOf("height_rand_a").forGetter(placer -> placer.heightRandA),
                    Codec.intRange(0, 32).fieldOf("height_rand_b").forGetter(placer -> placer.heightRandB)
            ).apply(instance, BalsamTrunkPlacer::new)
    );

    public BalsamTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    private static void crown(Set<BlockPos> leaves, BlockPos center) {
        for (int x = -1; x <= 1; x++) for (int z = -1; z <= 1; z++) leaves.add(center.offset(x, 0, z));
    }

    @Override
    public int getTreeHeight(RandomSource random) {
        return baseHeight + random.nextInt(heightRandA + 1) + (heightRandB == 0 ? 0 : random.nextInt(heightRandB + 1));
    }

    @Override
    protected @NonNull TrunkPlacerType<?> type() {
        return ModTrunkPlacers.BALSAM_TRUNK_PLACER.get();
    }

    @Override
    public @NonNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NonNull WorldGenLevel level, @NonNull BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, int treeHeight, @NonNull BlockPos origin, @NonNull TreeConfiguration config) {
        int height = treeHeight;
        var below = origin.below();
        if (level.isOutsideBuildHeight(below)) return List.of();
        var soil = level.getBlockState(below);
        if (!(soil.is(BlockTags.DIRT) || soil.is(BlockTags.GRASS_BLOCKS)) || soil.hasBlockEntity()
                || !soil.getFluidState().isEmpty() || !soil.isFaceSturdy(level, below, Direction.UP)) return List.of();
        boolean alternate = random.nextBoolean();
        for (int x = -5; x <= 5; x++)
            for (int z = -5; z <= 5; z++)
                for (int y = 2; y < height; y++) {
                    var pos = origin.offset(x, y, z);
                    if (level.isOutsideBuildHeight(pos) || !level.getBlockState(pos).isAir()) return List.of();
                }
        var logs = new LinkedHashMap<BlockPos, Direction.Axis>();
        var leaves = new LinkedHashSet<BlockPos>();
        for (int y = 0; y < height; y++) {
            if (y < height - 1) logs.put(origin.above(y), Direction.Axis.Y);
            if (y == height - 1) {
                crown(leaves, origin.above(y));
                leaves.add(origin.above(y + 1));
            }
            if (y == height - 10 || y == height - 7 || y == height - 4) {
                var positive = alternate ? Direction.EAST : Direction.SOUTH;
                for (var direction : new Direction[]{positive, positive.getOpposite()}) {
                    var start = origin.above(y - random.nextInt(2)).relative(direction);
                    logs.put(start, direction.getAxis());
                    var tip = start.relative(direction).above();
                    logs.put(tip, Direction.Axis.Y);
                    crown(leaves, tip);
                    leaves.add(tip.relative(direction, 2));
                }
                alternate = !alternate;
            }
        }
        leaves.removeAll(logs.keySet());
        var targets = new HashSet<>(logs.keySet());
        targets.addAll(leaves);
        for (var pos : targets) {
            if (level.isOutsideBuildHeight(pos)) return List.of();
            var state = level.getBlockState(pos);
            if (!state.isAir() && !(pos.equals(origin) && state.is(BlockTags.SAPLINGS))) return List.of();
        }
        logs.forEach((pos, axis) -> trunkSetter.accept(pos, config.trunkProvider.getState(level, random, pos).setValue(BlockStateProperties.AXIS, axis)));
        return leaves.stream().map(pos -> new FoliagePlacer.FoliageAttachment(pos, 0, false)).toList();
    }

    @Override
    public boolean isFree(WorldGenLevel level, BlockPos pos) {
        return validTreePos(level, pos);
    }
}
