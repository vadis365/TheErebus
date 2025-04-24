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
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> setter, RandomSource random, int freeTreeHeight, BlockPos pos, TreeConfiguration config) {
        setDirtAt(level, setter, random, pos.below(), config);
        int radius = random.nextInt(heightRandA) + heightRandB;
        int height = random.nextInt(radius) + baseHeight;

        for(int y = 0; y < height; y++) {
            if(pos.getY() + y % 5 == 0 && radius != 1) --radius;

            for(int xOff = -radius; xOff <= radius; xOff++) {
                for(int zOff = -radius; zOff <= radius; zOff++) {
                    double dSq = Math.pow(xOff, 2) + Math.pow(zOff, 2);
                    long rounded = Math.round(Math.sqrt(dSq));
                    if(rounded <= radius) {
                        if(pos.getY() + y <= pos.getY() + height - 2) {
                            placeLog(level, setter, random, pos.offset(xOff, y, zOff), config);
                        }

                        if(y == 0 || pos.getY() + y == pos.getY() + height - 1) {
                            placeLog(level, setter, random, pos.offset(xOff, y, zOff), config);
                        }
                    }
                }
            }

            if(y == height - 1) {
                createBranch(level, setter, config, list, random, pos.offset(radius + 1, getYOffset(y), 0), 1, false);
                createBranch(level, setter, config, list, random, pos.offset(radius - 1, getYOffset(y), 0), 2, false);
                createBranch(level, setter, config, list, random, pos.offset(0, getYOffset(y), radius + 1), 3, false);
                createBranch(level, setter, config, list, random, pos.offset(0, getYOffset(y), radius - 1), 4, false);

                createBranch(level, setter, config, list, random, pos.offset(radius + 1, getYOffset(y), radius + 1), 5, false);
                createBranch(level, setter, config, list, random, pos.offset(-radius - 1, getYOffset(y), -radius - 1), 6, false);
                createBranch(level, setter, config, list, random, pos.offset(-radius - 1, getYOffset(y), radius + 1), 7, false);
                createBranch(level, setter, config, list, random, pos.offset(radius + 1, getYOffset(y), -radius - 1), 8, false);
            }

            if(pos.getY() + 1 == pos.above().getY()) {
                createBranch(level, setter, config, list, random, pos.offset(radius + 1, getYOffset(y), 0), 1, true);
                createBranch(level, setter, config, list, random, pos.offset(radius - 1, getYOffset(y), 0), 2, true);
                createBranch(level, setter, config, list, random, pos.offset(0, getYOffset(y), radius + 1), 3, true);
                createBranch(level, setter, config, list, random, pos.offset(0, getYOffset(y), radius - 1), 4, true);

                createBranch(level, setter, config, list, random, pos.offset(radius + 1, getYOffset(y), radius + 1), 5, true);
                createBranch(level, setter, config, list, random, pos.offset(-radius - 1, getYOffset(y), -radius - 1), 6, true);
                createBranch(level, setter, config, list, random, pos.offset(-radius - 1, getYOffset(y), radius + 1), 7, true);
                createBranch(level, setter, config, list, random, pos.offset(radius + 1, getYOffset(y), -radius - 1), 8, true);
            }
        }

        return list;
    }

    private int getYOffset(int y) {
        return y - RandomSource.create().nextInt(3);
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
                    placeLog(level, setter, random, pos.offset(c, yOffset, 0), config, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.X));

                    if (c < branchLength) {
                        //TODO: Add vines
                    }

                    if (c == branchLength) createLeaves(pos.east(c).below(), 1);
                } else {
                    placeLog(level, setter, random, pos.offset(c, yOffset, 0), config);
                    placeLog(level, setter, random, pos.offset(c, yOffset - 1, 0), config);
                }
            }

            if (direction == 2) {
                if (!root) {
                    placeLog(level, setter, random, pos.offset(-c, yOffset, 0), config, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.X));
                    if (c == branchLength) createLeaves(pos.offset(-c, yOffset - 1, 0), 1);
                } else {
                    placeLog(level, setter, random, pos.offset(-c, yOffset, 0), config);
                    placeLog(level, setter, random, pos.offset(-c, yOffset - 1, 0), config);
                }
            }

            if (direction == 3) {
                if (!root) {
                    placeLog(level, setter, random, pos.offset(0, yOffset, c), config, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.Z));
                    if(c == branchLength) createLeaves(pos.offset(0, yOffset - 1, c), 1);
                } else {
                    placeLog(level, setter, random, pos.offset(0, yOffset, c), config);
                    placeLog(level, setter, random, pos.offset(0, yOffset - 1, c), config);
                }
            }

            if (direction == 4) {
                if (!root) {
                    placeLog(level, setter, random, pos.offset(0, yOffset, -c), config, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.Z));
                    if(c == branchLength) createLeaves(pos.offset(0, yOffset - 1, -c), 1);
                } else {
                    placeLog(level, setter, random, pos.offset(0, yOffset, -c), config);
                    placeLog(level, setter, random, pos.offset(0, yOffset - 1, -c), config);
                }
            }

            if (direction == 5) {
                if (!root) {
                    placeLog(level, setter, random, pos.offset(c - 1, yOffset, c - 1), config, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.X));
                    if(c == branchLength) createLeaves(pos.offset(0, yOffset - 1, c), 1);
                } else {
                    placeLog(level, setter, random, pos.offset(c - 1, yOffset, c - 1), config);
                    placeLog(level, setter, random, pos.offset(c - 1, yOffset - 1, c - 1), config);
                }
            }

            if (direction == 6) {
                if (!root) {
                    placeLog(level, setter, random, pos.offset(-c + 1, yOffset, -c + 1), config, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.X));
                    if(c == branchLength) createLeaves(pos.offset(-c, yOffset - 1, -c), 1);
                } else {
                    placeLog(level, setter, random, pos.offset(-c + 1, yOffset, -c + 1), config);
                    placeLog(level, setter, random, pos.offset(-c + 1, yOffset - 1, -c + 1), config);
                }
            }

            if (direction == 7) {
                if (!root) {
                    placeLog(level, setter, random, pos.offset(-c + 1, yOffset, c - 1), config, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.Z));
                    if(c == branchLength) createLeaves(pos.offset(-c, yOffset - 1, c), 1);
                } else {
                    placeLog(level, setter, random, pos.offset(-c + 1, yOffset, c - 1), config);
                    placeLog(level, setter, random, pos.offset(-c + 1, yOffset - 1, c - 1), config);
                }
            }

            if (direction == 8) {
                if (!root) {
                    placeLog(level, setter, random, pos.offset(c - 1, yOffset, -c + 1), config, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.Z));
                    if(c == branchLength) createLeaves(pos.offset(c, yOffset - 1, -c), 1);
                } else {
                    placeLog(level, setter, random, pos.offset(c - 1, yOffset, -c + 1), config);
                    placeLog(level, setter, random, pos.offset(c - 1, yOffset - 1, -c + 1), config);
                }
            }
        }
    }

    private void createLeaves(BlockPos pos, int radius) {
        list.add(new FoliagePlacer.FoliageAttachment(pos, radius, false));
    }
}
