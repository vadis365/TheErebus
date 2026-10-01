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

public class BaobabTrunkPlacer extends TrunkPlacer {

    public static final MapCodec<BaobabTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.intRange(0, 32).fieldOf("base_height").forGetter(placer -> placer.baseHeight),
                    Codec.intRange(0, 32).fieldOf("height_rand_a").forGetter(placer -> placer.heightRandA),
                    Codec.intRange(0, 32).fieldOf("height_rand_b").forGetter(placer -> placer.heightRandB)
            ).apply(instance, BaobabTrunkPlacer::new)
    );

    public BaobabTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected @NonNull TrunkPlacerType<?> type() {
        return ModTrunkPlacers.BAOBAB_TRUNK_PLACER.get();
    }

    @Override
    public @NonNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NonNull WorldGenLevel level, @NonNull BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, int treeHeight, @NonNull BlockPos origin, @NonNull TreeConfiguration config) {
        var attachments = new ArrayList<FoliagePlacer.FoliageAttachment>();
        var logs = new LinkedHashMap<BlockPos, BlockState>();
        int radius = random.nextInt(2) + 3;
        int height = random.nextInt(radius) + 12;
        int clearanceRadius = radius + 2;
        // Preserve the reference's broad air clearance, including its actual sampled height.
        for (int x = -clearanceRadius; x <= clearanceRadius; x++)
            for (int z = -clearanceRadius; z <= clearanceRadius; z++)
                for (int y = 1; y < height + 2; y++) {
                    var pos = origin.offset(x, y, z);
                    if (level.isOutsideBuildHeight(pos) || !level.getBlockState(pos).isAir()) return List.of();
                }
        for (int y = 0; y < height; y++) {
            // Approved base-relative taper: planting elevation never changes the shape.
            if (y % 5 == 0 && radius != 1) radius--;
            for (int x = -radius; x <= radius; x++)
                for (int z = -radius; z <= radius; z++) {
                    if (Math.round(Math.sqrt(x * x + z * z)) <= radius && y <= height - 2)
                        planLog(level, logs, random, config, origin.offset(x, y, z), Direction.Axis.Y);
                }
            if (y == height - 2) {
                createBranch(level, logs, config, attachments, random, origin.offset(radius + 1, y - random.nextInt(3), 0), Direction.Axis.X, true);
                createBranch(level, logs, config, attachments, random, origin.offset(-radius - 1, y - random.nextInt(3), 0), Direction.Axis.X, false);
                createBranch(level, logs, config, attachments, random, origin.offset(0, y - random.nextInt(3), radius + 1), Direction.Axis.Z, true);
                createBranch(level, logs, config, attachments, random, origin.offset(0, y - random.nextInt(3), -radius - 1), Direction.Axis.Z, false);
            }
        }
        var targets = new HashSet<>(logs.keySet());
        for (var attachment : attachments) {
            int r = attachment.radiusOffset();
            for (int x = -r; x <= r; x++)
                for (int z = -r; z <= r; z++)
                    for (int y = 0; y < 2; y++)
                        if (Math.round(Math.sqrt(x * x + y * y + z * z)) <= r) targets.add(attachment.pos().offset(x, y, z));
        }
        // Branch-tip crowns can extend beyond the old clearance box. Validate them too.
        for (var pos : targets) {
            if (level.isOutsideBuildHeight(pos)) return List.of();
            var state = level.getBlockState(pos);
            if (!state.isAir() && !(pos.equals(origin) && state.is(BlockTags.SAPLINGS))) return List.of();
        }
        // Validate the actual tapered base, not just the five legacy soil probes.
        for (var pos : logs.keySet()) {
            if (pos.getY() != origin.getY()) continue;
            var below = pos.below();
            if (level.isOutsideBuildHeight(below)) return List.of();
            var soil = level.getBlockState(below);
            if (!(soil.is(BlockTags.DIRT) || soil.is(BlockTags.GRASS_BLOCKS)) || soil.hasBlockEntity()
                    || !soil.getFluidState().isEmpty() || !soil.isFaceSturdy(level, below, Direction.UP)) return List.of();
        }
        logs.forEach(trunkSetter);
        return attachments;
    }

    @Override
    public boolean isFree(WorldGenLevel level, BlockPos pos) {
        return validTreePos(level, pos);
    }

    private void planLog(WorldGenLevel level, Map<BlockPos, BlockState> logs, RandomSource random, TreeConfiguration config, BlockPos pos, Direction.Axis axis) {
        logs.put(pos.immutable(), config.trunkProvider.getState(level, random, pos).setValue(BlockStateProperties.AXIS, axis));
    }

    private void createBranch(WorldGenLevel level, Map<BlockPos, BlockState> logs, TreeConfiguration config,
                              List<FoliagePlacer.FoliageAttachment> attachments, RandomSource random, BlockPos start, Direction.Axis axis, boolean positive) {
        int length = random.nextInt(2) + 2;
        int rise = 0;
        for (int step = 0; step <= length; step++) {
            if (step >= 2) rise++;
            int distance = positive ? step : -step;
            var pos = start.offset(axis == Direction.Axis.X ? distance : 0, rise, axis == Direction.Axis.Z ? distance : 0);
            planLog(level, logs, random, config, pos, step >= 2 ? Direction.Axis.Y : axis);
            if (step == length) {
                var crown = pos.above();
                planLog(level, logs, random, config, crown, Direction.Axis.Y);
                attachments.add(new FoliagePlacer.FoliageAttachment(crown, length, false));
            }
        }
    }
}
