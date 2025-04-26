package erebus.world.feature.tree.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.world.ModTrunkPlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class MarshwoodTrunkPlacer extends TrunkPlacer {

    private final List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
    private LevelSimulatedReader level;
    private BiConsumer<BlockPos, BlockState> setter;
    private RandomSource random;
    private TreeConfiguration config;

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
        this.level = level;
        this.setter = setter;
        this.config = config;
        this.random = random;

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        for (int yy = y; yy < height; yy++) {
            if (yy % 5 == 0 && radius != 1) --radius;

            for(int xOff = -radius; xOff <= radius; xOff++) {
                for(int zOff = -radius; zOff <= radius; zOff++) {
                    double dSq = Math.pow(xOff, 2) + Math.pow(zOff, 2);
                    long rounded = Math.round(Math.sqrt(dSq));
                    if(rounded <= radius) {
                        BlockPos newPos = new BlockPos(x + xOff, yy, z + zOff);
                        if (yy <= y + height - 2) {
                            placeLog(level, setter, random, newPos, config);
                        }

                        if (yy == y || yy == y + height - 1) {
                            placeLog(level, setter, random, newPos, config);
                        }
                    }
                }
            }

            if (yy == y + height - 1) {
                createBranches(x, yy, z, radius, false);
            }

            if (yy == y + 1) {
                createBranches(x, yy, z, radius, true);
            }
        }

        return list;
    }

    private void createBranches(int x, int y, int z, int radius, boolean root) {
        createBranch(getPos(x + radius - 1, y, z), 1, root);
        createBranch(getPos(x - radius - 1, y, z), 2, root);
        createBranch(getPos(x, y, z + radius + 1), 3, root);
        createBranch(getPos(x, y, z - radius - 1), 4, root);

        createBranch(getPos(x + radius + 1, y, z + radius + 1), 5, root);
        createBranch(getPos(x - radius - 1, y, z - radius - 1), 6, root);
        createBranch(getPos(x - radius - 1, y, z + radius + 1), 7, root);
        createBranch(getPos(x + radius + 1, y, z - radius - 1), 8, root);
    }

    private BlockPos getPos(int x, int y, int z) {
        return new BlockPos(x, getYOffset(y), z);
    }

    private int getYOffset(int y) {
        return y - RandomSource.create().nextInt(3);
    }

    private void createBranch(BlockPos pos, int direction, boolean root) {
        int branchLength = random.nextInt(heightRandA) + heightRandB;

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        for (int c = 0; c <= branchLength; c++) {
            if (c >= 3) {
                y--;
            }

            if (direction == 1) {
                BlockPos logPos = new BlockPos(x + c, y, z);
                if (!root) {
                    placeLog(logPos, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.X));

                    if (c < branchLength) {
                        //TODO: Add vines
                    }

                    if (c == branchLength) createLeaves(logPos.below());
                } else {
                    placeLog(logPos);
                    placeLog(logPos.below());
                }
            }

            if (direction == 2) {
                BlockPos logPos = new BlockPos(x - c, y, z);
                if (!root) {
                    placeLog(logPos, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.X));
                    if (c == branchLength) createLeaves(logPos.below());
                } else {
                    placeLog(logPos);
                    placeLog(logPos.below());
                }
            }

            if (direction == 3) {
                BlockPos logPos = new BlockPos(x, y, z + c);
                if (!root) {
                    placeLog(logPos, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.Z));
                    if (c == branchLength) createLeaves(logPos.below());
                } else {
                    placeLog(logPos);
                    placeLog(logPos.below());
                }
            }

            if (direction == 4) {
                BlockPos logPos = new BlockPos(x, y, z - c);
                if (!root) {
                    placeLog(logPos, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.Z));
                    if (c == branchLength) createLeaves(logPos.below());
                } else {
                    placeLog(logPos);
                    placeLog(logPos.below());
                }
            }

            if (direction == 5) {
                BlockPos logPos = new BlockPos(x + c - 1, y, z + c - 1);
                if (!root) {
                    placeLog(logPos, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.X));
                    if (c == branchLength) createLeaves(new BlockPos(x + c, y - 1, z + c));
                } else {
                    placeLog(logPos);
                    placeLog(logPos.below());
                }
            }

            if (direction == 6) {
                BlockPos logPos = new BlockPos(x - c + 1, y, z - c + 1);
                if (!root) {
                    placeLog(logPos, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.X));
                    if (c == branchLength) createLeaves(new BlockPos(x - c, y - 1, z - c));
                } else {
                    placeLog(logPos);
                    placeLog(logPos.below());
                }
            }

            if (direction == 7) {
                BlockPos logPos = new BlockPos(x - c + 1, y, z + c - 1);
                if (!root) {
                    placeLog(logPos, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.Z));
                    if (c == branchLength) createLeaves(new BlockPos(x - c, y - 1, z + c));
                } else {
                    placeLog(logPos);
                    placeLog(logPos.below());
                }
            }

            if (direction == 8) {
                BlockPos logPos = new BlockPos(x + c - 1, y, z - c + 1);
                if (!root) {
                    placeLog(logPos, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.Z));
                    if (c == branchLength) createLeaves(new BlockPos(x + c, y - 1, z - c));
                } else {
                    placeLog(logPos);
                    placeLog(logPos.below());
                }
            }
        }
    }

    private void placeLog(BlockPos pos, Function<BlockState, BlockState> propertySetter) {
        placeLog(level, setter, random, pos, config, propertySetter);
    }

    private void placeLog(BlockPos pos) {
        setter.accept(pos, Blocks.AIR.defaultBlockState());
        placeLog(level, setter, random, pos, config);
    }

    private void createLeaves(BlockPos pos) {
        list.add(new FoliagePlacer.FoliageAttachment(pos, 1, false));
    }
}
