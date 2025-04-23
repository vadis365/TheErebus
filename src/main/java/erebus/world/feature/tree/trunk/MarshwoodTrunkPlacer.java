package erebus.world.feature.tree.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.world.ModTrunkPlacers;
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
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiConsumer;

public class MarshwoodTrunkPlacer extends TrunkPlacer {

    private final List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

    public static final MapCodec<MarshwoodTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.intRange(0, 32).fieldOf("base_height").forGetter(placer -> placer.baseHeight),
                    Codec.intRange(0, 32).fieldOf("height_rand_a").forGetter(placer -> placer.heightRandA),
                    Codec.intRange(0, 32).fieldOf("height_rand_b").forGetter(placer -> placer.heightRandB)
            ).apply(instance, MarshwoodTrunkPlacer::new)
    );

    public MarshwoodTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacers.MARSHWOOD_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos pos, TreeConfiguration config) {
        setDirtAt(level, blockSetter, random, pos.below(), config);
        int radius = random.nextInt(heightRandA) + heightRandB;
        int height = random.nextInt(radius) + baseHeight;
        int maxRadius = 9;
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        for (int c = 0; c < freeTreeHeight; ++c) {
            placeLog(level, blockSetter, random, pos.above(c), config);
        }

        return list;
    }

    private void createBranch(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> setter, @NotNull TreeConfiguration config, List<FoliagePlacer.FoliageAttachment> list, RandomSource random, BlockPos pos, int direction, boolean root) {
        int branchLength = random.nextInt(heightRandA) + heightRandB;
        int yOffset = 0;

        for (int c = 0; c <= branchLength; c++) {
            if (c >= 3) {
                yOffset--;
            }

            if (direction == 1) {
                if (!root) {
                    placeLog(level, setter, random, pos.east(c), config, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.X));

                    if (c < branchLength) {
                        //TODO: Add vines
                    }

                    if (c == branchLength) createLeaves(pos.east(c).below(), 1);
                } else {
                    placeLog(level, setter, random, pos.east(), config);
                    placeLog(level, setter, random, pos.east().below(), config);
                }
            }

            if (direction == 2) {
                if (!root) {
                    placeLog(level, setter, random, pos.west(c), config, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.X));
                    if (c == branchLength) createLeaves(pos.west(c).below(), 1);
                } else {
                    placeLog(level, setter, random, pos.west(), config);
                    placeLog(level, setter, random, pos.west().below(), config);
                }
            }

            if (direction == 3) {
                if (!root) {
                    placeLog(level, setter, random, pos.north(c), config, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.Z));
                } else {

                }
            }

            if (direction == 4) {
                if (!root) {
                    placeLog(level, setter, random, pos.south(c), config, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.Z));
                } else {

                }
            }

            if (direction == 5) {
                if (!root) {
                    placeLog(level, setter, random, pos.south(c), config, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.Z));
                } else {

                }
            }

            if (direction == 6) {
                if (!root) {
                    placeLog(level, setter, random, pos.south(c), config, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.Z));
                } else {

                }
            }

            if (direction == 7) {
                if (!root) {
                    placeLog(level, setter, random, pos.south(c), config, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.Z));
                } else {

                }
            }

            if (direction == 8) {
                if (!root) {
                    placeLog(level, setter, random, pos.south(c), config, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.Z));
                } else {

                }
            }
        }
    }

    private void createLeaves(BlockPos pos, int radius) {
        list.add(new FoliagePlacer.FoliageAttachment(pos, radius, false));
    }
}
