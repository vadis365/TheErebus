package erebus.world.feature.tree.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.world.tree.ModTrunkPlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.apache.commons.compress.utils.Lists;
import org.jspecify.annotations.NonNull;

import java.util.List;
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
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        int radius = random.nextInt(2) + 3;
        int height = random.nextInt(radius) + 12;

        for (int yOffset = 0; yOffset < height; yOffset++) {
            if (yOffset % 5 == 0 && radius != 1) --radius;

            for (int xOffset = -radius; xOffset <= radius; xOffset++) {
                for (int zOffset = -radius; zOffset <= radius; zOffset++) {
                    double sqrd = Mth.square(xOffset) + Mth.square(zOffset);
                    double roundedRoot = Math.round(Math.sqrt(sqrd));
                    int topBuffer = origin.getY() + height - 2;

                    if (roundedRoot <= radius && origin.getY() + yOffset <= topBuffer) {
                        placeLog(level, trunkSetter, random, origin.mutable().move(xOffset, yOffset, zOffset), config);
                    }
                }
            }

            if (yOffset == height - 2) {
                createBranch(level, trunkSetter, config, list, random, origin.mutable().move(radius + 1, yOffset - random.nextInt(3), 0), Direction.Axis.X, true);
                createBranch(level, trunkSetter, config, list, random, origin.mutable().move(-radius - 1, yOffset - random.nextInt(3), 0), Direction.Axis.X, false);
                createBranch(level, trunkSetter, config, list, random, origin.mutable().move(0, yOffset - random.nextInt(3), radius + 1), Direction.Axis.Z, true);
                createBranch(level, trunkSetter, config, list, random, origin.mutable().move(0, yOffset - random.nextInt(3), -radius - 1), Direction.Axis.Z, false);
            }
        }

        list.add(new FoliagePlacer.FoliageAttachment(origin.above(height), 0, false));

        return list;
    }

    private void createBranch(WorldGenLevel level, BiConsumer<BlockPos, BlockState> setter, TreeConfiguration config, List<FoliagePlacer.FoliageAttachment> list, RandomSource random, BlockPos pos, Direction.Axis axis, boolean positive) {
        int branchLength = random.nextInt(2) + 2;
        int y = 0;

        for (int c = 0; c < branchLength; c++) {
            if (c >= 2) y++;

            BlockPos place;

            if (axis == Direction.Axis.X) {
                place = pos.mutable().move(positive ? c : -c, y, 0);
            } else {
                place = pos.mutable().move(0, y, positive ? c : -c);
            }

            placeLog(level, setter, random, place, config, state -> state.setValue(BlockStateProperties.AXIS, axis));
            list.add(new FoliagePlacer.FoliageAttachment(place, 0, false));
        }
    }
}
