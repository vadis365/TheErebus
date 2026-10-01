package erebus.world.feature.tree.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.world.tree.ModTrunkPlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jspecify.annotations.NonNull;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.BiConsumer;

public class EucalyptusTrunkPlacer extends TrunkPlacer {

    public static final MapCodec<EucalyptusTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.intRange(0, 32).fieldOf("base_height").forGetter(placer -> placer.baseHeight),
                    Codec.intRange(0, 32).fieldOf("height_rand_a").forGetter(placer -> placer.heightRandA),
                    Codec.intRange(0, 32).fieldOf("height_rand_b").forGetter(placer -> placer.heightRandB)
            ).apply(instance, EucalyptusTrunkPlacer::new)
    );
    private static final int SPAN = 5;
    private static final int BRANCHES = 8;

    public EucalyptusTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    public int getTreeHeight(RandomSource random) {
        return baseHeight + random.nextInt(heightRandA + 1) + (heightRandB == 0 ? 0 : random.nextInt(heightRandB + 1));
    }

    @Override
    protected @NonNull TrunkPlacerType<?> type() {
        return ModTrunkPlacers.EUCALYPTUS_TRUNK_PLACER.get();
    }

    @Override
    public @NonNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NonNull WorldGenLevel level, @NonNull BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, int treeHeight, BlockPos origin, @NonNull TreeConfiguration config) {
        var leaves = new LinkedHashSet<BlockPos>();
        var logs = new LinkedHashSet<BlockPos>();
        int height = treeHeight;
        if (level.isOutsideBuildHeight(origin.below())
                || level.getBlockState(origin.below()) != Blocks.GRASS_BLOCK.defaultBlockState()) return List.of();
        // The reference checks this whole volume, even cells outside the sampled branches.
        for (int yy = 0; yy < SPAN; yy++)
            for (int xx = -SPAN; xx <= SPAN; xx++)
                for (int zz = -SPAN; zz <= SPAN; zz++) {
                    var pos = origin.offset(xx, height + yy, zz);
                    if (level.isOutsideBuildHeight(pos) || !level.getBlockState(pos).isAir()) return List.of();
                }
        int x = origin.getX();
        int y = origin.getY();
        int z = origin.getZ();

        for (int c = -2; c < 3; c++) {
            for (int d = -1; d < 2; d++) {
                leaves.add(new BlockPos(x + c, y + height + SPAN + 1, z + d));
                leaves.add(new BlockPos(x + d, y + height + SPAN + 1, z + c));
            }
        }

        for (int c = -1; c < 2; c++)
            for (int d = -1; d < 2; d++)
                leaves.add(new BlockPos(x + c, y + height + SPAN + 2, z + d));

        for (int c = 0; c < BRANCHES; c++) {
            int disX = random.nextInt(SPAN * 2 + 1) - SPAN;
            int disY = random.nextInt(SPAN + 1);
            int disZ = random.nextInt(SPAN * 2 + 1) - SPAN;

            int posX = x + disX;
            int posY = y + height - 1 + disY;
            int posZ = z + disZ;

            for (int d = -2; d < 3; d++) {
                for (int e = -1; e < 2; e++) {
                    leaves.add(new BlockPos(posX + d, posY, posZ + e));
                    leaves.add(new BlockPos(posX + e, posY, posZ + d));
                }
            }

            for (int d = -1; d < 2; d++)
                for (int e = -1; e < 2; e++)
                    leaves.add(new BlockPos(posX + d, posY + 1, posZ + e));

            for (int d = 0; d < SPAN; d++) {
                int xx = disX * (d + 1) / SPAN;
                int yy = disY * (d + 1) / SPAN;
                int zz = disZ * (d + 1) / SPAN;

                logs.add(origin.offset(xx, height - 1 + yy, zz));
            }

            logs.add(origin.offset(disX, height - 1 + disY, disZ));
        }

        for (int c = 0; c < height + SPAN + 2; ++c) {
            logs.add(origin.above(c));
        }

        // Approved adaptation: all branches survive overlapping leaf clusters.
        leaves.removeAll(logs);
        var targets = new HashSet<>(logs);
        targets.addAll(leaves);
        for (var pos : targets) {
            if (level.isOutsideBuildHeight(pos)) return List.of();
            var state = level.getBlockState(pos);
            if (!state.isAir() && !(pos.equals(origin) && state.is(BlockTags.SAPLINGS))) return List.of();
        }
        logs.forEach(pos -> trunkSetter.accept(pos, config.trunkProvider.getState(level, random, pos)));
        return leaves.stream().map(pos -> new FoliagePlacer.FoliageAttachment(pos, 0, false)).toList();
    }

    @Override
    public boolean isFree(WorldGenLevel level, BlockPos pos) {
        return validTreePos(level, pos);
    }
}
