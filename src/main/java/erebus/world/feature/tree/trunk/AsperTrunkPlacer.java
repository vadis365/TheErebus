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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jspecify.annotations.NonNull;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.BiConsumer;

public class AsperTrunkPlacer extends TrunkPlacer {

    public static final MapCodec<AsperTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.intRange(0, 32).fieldOf("base_height").forGetter(placer -> placer.baseHeight),
                    Codec.intRange(0, 24).fieldOf("height_rand_a").forGetter(placer -> placer.heightRandA),
                    Codec.intRange(0, 24).fieldOf("height_rand_b").forGetter(placer -> placer.heightRandB)
            ).apply(instance, AsperTrunkPlacer::new)
    );
    private static final Direction[] DIRECTIONS = {Direction.WEST, Direction.EAST, Direction.NORTH, Direction.SOUTH};

    public AsperTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    public int getTreeHeight(RandomSource random) {
        return baseHeight + random.nextInt(heightRandA + 1) + (heightRandB == 0 ? 0 : random.nextInt(heightRandB + 1));
    }

    @Override
    protected @NonNull TrunkPlacerType<?> type() {
        return ModTrunkPlacers.ASPER_TRUNK_PLACER.get();
    }

    @Override
    public @NonNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NonNull WorldGenLevel level, @NonNull BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, int treeHeight, @NonNull BlockPos origin, @NonNull TreeConfiguration config) {
        if (treeHeight < 1 || level.isOutsideBuildHeight(origin.below())) return List.of();
        var soil = level.getBlockState(origin.below());
        if (soil != Blocks.DIRT.defaultBlockState()
                && soil != Blocks.GRASS_BLOCK.defaultBlockState()) return List.of();
        // Preserve the broad legacy clearance; protect every base target as well.
        for (int y = 1; y <= treeHeight + 1; y++)
            for (int x = -2; x <= 2; x++)
                for (int z = -2; z <= 2; z++) {
                    var pos = origin.offset(x, y, z);
                    if (level.isOutsideBuildHeight(pos) || !level.getBlockState(pos).isAir()) return List.of();
                }
        var logs = new LinkedHashMap<BlockPos, BlockState>();
        var leaves = new LinkedHashSet<BlockPos>();
        for (int y = 0; y < treeHeight; y++) {
            var center = origin.above(y);
            logs.put(center, config.trunkProvider.getState(level, random, center));
            if (y == treeHeight - 1) continue;
            for (int count = 0, attempt = 0; attempt < 5 && count < 3; attempt++) {
                var direction = DIRECTIONS[random.nextInt(4)];
                var branch = center.relative(direction);
                // Read the planned trunk as the reference reads its earlier writes.
                if (y > 0 && (logs.containsKey(branch.below()) || !level.getBlockState(branch.below()).isAir())) continue;
                logs.put(branch, config.trunkProvider.getState(level, random, branch).setValue(BlockStateProperties.AXIS, direction.getAxis()));
                if (y > 0 && random.nextBoolean()) leaves.add(center.relative(direction, 2));
                count++;
            }
        }
        double centerY = 2D + (treeHeight - 2D) * 0.5D;
        for (int y = 1; y < treeHeight; y++)
            for (int x = -1; x <= 1; x++)
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && z == 0) continue;
                    double distance = Math.sqrt(x * x + Math.pow(centerY - y, 2) + z * z);
                    var pos = origin.offset(x, y, z);
                    if ((distance <= 1.5D || random.nextDouble() > distance - 1.5D) && !logs.containsKey(pos)) leaves.add(pos);
                }
        for (var direction : DIRECTIONS) leaves.add(origin.above(treeHeight).relative(direction));
        leaves.add(origin.above(treeHeight));
        for (var pos : logs.keySet()) {
            if (level.isOutsideBuildHeight(pos)) return List.of();
            var state = level.getBlockState(pos);
            if (!state.isAir() && !(pos.equals(origin) && state.is(BlockTags.SAPLINGS))) return List.of();
        }
        // Leaves are contained in the already checked air volume.
        logs.forEach(trunkSetter);
        return leaves.stream().map(pos -> new FoliagePlacer.FoliageAttachment(pos, 0, false)).toList();
    }

    @Override
    public boolean isFree(WorldGenLevel level, BlockPos pos) {
        return validTreePos(level, pos);
    }
}
