package erebus.world.feature.tree.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.world.tree.ModTrunkPlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.function.BiConsumer;

public class BalsamTrunkPlacer extends TrunkPlacer {

    protected final int baseHeight;
    protected final int heightRandA;
    protected final int heightRandB;

    public static final MapCodec<BalsamTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.intRange(0, 32).fieldOf("base_height").forGetter(placer -> placer.baseHeight),
                    Codec.intRange(0, 32).fieldOf("height_rand_a").forGetter(placer -> placer.heightRandA),
                    Codec.intRange(0, 32).fieldOf("height_rand_b").forGetter(placer -> placer.heightRandB)
            ).apply(instance, BalsamTrunkPlacer::new)
    );

    public BalsamTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
        this.baseHeight = baseHeight;
        this.heightRandA = heightRandA;
        this.heightRandB = heightRandB;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacers.BALSAM_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos pos, TreeConfiguration config) {
        setDirtAt(level, blockSetter, random, pos.below(), config);
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        int height = random.nextInt(heightRandA) + baseHeight;
        boolean alternate = random.nextBoolean();

        for (int y = 0; y < height; y++) {
            BlockPos posToPlace = pos.above(y);

            placeLog(level, blockSetter, random, posToPlace, config);

            if (posToPlace.getY() == pos.getY() + height - 7 || posToPlace.getY() == pos.getY() + height - 10) {
                alternate = alternatePlacingBranches(level, blockSetter, random, config, alternate, posToPlace, list);
            }

            if (posToPlace.getY() == pos.getY() + height - 4) {
                alternate = alternatePlacingBranches(level, blockSetter, random, config, alternate, posToPlace, list);
            }
        }

        list.add(new FoliagePlacer.FoliageAttachment(pos.above(height), 0, false));

        return list;
    }

    private boolean alternatePlacingBranches(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, TreeConfiguration config, boolean alternate, BlockPos posToPlace, List<FoliagePlacer.FoliageAttachment> list) {
        if (alternate) {
            createBranch(level, blockSetter, random, posToPlace.relative(Direction.Axis.X, 1), config, Direction.Axis.X, list, true);
            createBranch(level, blockSetter, random, posToPlace.relative(Direction.Axis.X, -1), config, Direction.Axis.X, list, false);
            alternate = false;
        } else {
            createBranch(level, blockSetter, random, posToPlace.relative(Direction.Axis.Z, 1), config, Direction.Axis.Z, list, true);
            createBranch(level, blockSetter, random, posToPlace.relative(Direction.Axis.Z, -1), config, Direction.Axis.Z, list, false);
            alternate = true;
        }
        return alternate;
    }

    private void createBranch(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos pos, TreeConfiguration config, Direction.Axis axis, List<FoliagePlacer.FoliageAttachment> list, boolean positive) {
        int y = 0;
        for (int c = 0; c < 2; c++) {
            if (c == 1) y++;

            BlockPos place;

            if (axis == Direction.Axis.X) {
                place = pos.mutable().move(positive ? c : -c, y, 0);
            } else {
                place = pos.mutable().move(0, y, positive ? c : -c);
            }

            placeLog(
                    level,
                    blockSetter,
                    random,
                    place,
                    config,
                    state -> state.setValue(BlockStateProperties.AXIS, axis)
            );
            if (y == 1) list.add(new FoliagePlacer.FoliageAttachment(place, 0, false));
        }
    }
}
