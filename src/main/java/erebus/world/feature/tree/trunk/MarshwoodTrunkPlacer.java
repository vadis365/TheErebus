package erebus.world.feature.tree.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.world.tree.ModTrunkPlacers;
import erebus.world.feature.tree.foliage.MarshwoodFoliagePlacer;
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
import org.apache.commons.compress.utils.Lists;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class MarshwoodTrunkPlacer extends TrunkPlacer {

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
    protected @NonNull TrunkPlacerType<?> type() {
        return ModTrunkPlacers.MARSHWOOD_TRUNK_PLACER.get();
    }

    @Override
    public boolean isFree(WorldGenLevel level, BlockPos pos) {
        // Existing trees/fallen logs are obstacles, not extra clearance.
        return validTreePos(level, pos);
    }

    @Override
    public @NonNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NonNull WorldGenLevel level, @NonNull BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, int treeHeight, BlockPos origin, @NonNull TreeConfiguration config) {
        return new Placement(level, trunkSetter, random, config).generate(origin);
    }

    private final class Placement {
        private final List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        private final WorldGenLevel level;
        private final BiConsumer<BlockPos, BlockState> setter;
        private final BiConsumer<BlockPos, BlockState> output;
        private final Map<BlockPos, BlockState> planned = new LinkedHashMap<>();
        private final RandomSource random;
        private final TreeConfiguration config;
        private boolean intersectsLog;

        private Placement(WorldGenLevel level, BiConsumer<BlockPos, BlockState> setter, RandomSource random, TreeConfiguration config) {
            this.level = level;
            this.output = setter;
            this.setter = (pos, state) -> planned.put(pos.immutable(), state);
            this.random = random;
            this.config = config;
        }

        private List<FoliagePlacer.FoliageAttachment> generate(BlockPos origin) {
            int radius = random.nextInt(heightRandA) + heightRandB;
            int height = random.nextInt(radius) + baseHeight;
            int x = origin.getX();
            int y = origin.getY();
            int z = origin.getZ();

            for (int yy = y; yy < y + height; yy++) {
                if (yy % 5 == 0 && radius != 1) --radius;

                for (int xOff = -radius; xOff <= radius; xOff++) {
                    for (int zOff = -radius; zOff <= radius; zOff++) {
                        double dSq = Math.pow(xOff, 2) + Math.pow(zOff, 2);
                        long rounded = Math.round(Math.sqrt(dSq));
                        if (rounded <= radius) {
                            BlockPos newPos = new BlockPos(x + xOff, yy, z + zOff);
                            if (yy <= y + height - 2) {
                                placeLog(newPos, Function.identity());
                            }

                            if (yy == y || yy == y + height - 1) {
                                placeLog(newPos, Function.identity());
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

            if (intersectsLog) return List.of();
            planned.forEach(output);
            return list;
        }

        private void createBranches(int x, int y, int z, int radius, boolean root) {
            createBranch(getPos(x + radius + 1, y, z), 1, root);
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
            return y - random.nextInt(3);
        }

        private void createBranch(BlockPos pos, int direction, boolean root) {
            int branchLength = random.nextInt(heightRandA) + heightRandB;
            List<BlockPos> hangers = new ArrayList<>();

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
                        if (placeLog(logPos, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.X)) && c < branchLength) hangers.add(logPos.below());

                        if (c == branchLength) createLeaves(logPos.below(), hangers);
                    } else {
                        placeLog(logPos);
                        placeLog(logPos.below());
                    }
                }

                if (direction == 2) {
                    BlockPos logPos = new BlockPos(x - c, y, z);
                    if (!root) {
                        if (placeLog(logPos, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.X)) && c < branchLength) hangers.add(logPos.below());
                        if (c == branchLength) createLeaves(logPos.below(), hangers);
                    } else {
                        placeLog(logPos);
                        placeLog(logPos.below());
                    }
                }

                if (direction == 3) {
                    BlockPos logPos = new BlockPos(x, y, z + c);
                    if (!root) {
                        if (placeLog(logPos, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.Z)) && c < branchLength) hangers.add(logPos.below());
                        if (c == branchLength) createLeaves(logPos.below(), hangers);
                    } else {
                        placeLog(logPos);
                        placeLog(logPos.below());
                    }
                }

                if (direction == 4) {
                    BlockPos logPos = new BlockPos(x, y, z - c);
                    if (!root) {
                        if (placeLog(logPos, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.Z)) && c < branchLength) hangers.add(logPos.below());
                        if (c == branchLength) createLeaves(logPos.below(), hangers);
                    } else {
                        placeLog(logPos);
                        placeLog(logPos.below());
                    }
                }

                if (direction == 5) {
                    BlockPos logPos = new BlockPos(x + c - 1, y, z + c - 1);
                    if (!root) {
                        if (placeLog(logPos, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.X)) && c < branchLength) hangers.add(logPos.below());
                        if (c == branchLength) createLeaves(new BlockPos(x + c, y - 1, z + c), hangers);
                    } else {
                        placeLog(logPos);
                        placeLog(logPos.below());
                    }
                }

                if (direction == 6) {
                    BlockPos logPos = new BlockPos(x - c + 1, y, z - c + 1);
                    if (!root) {
                        if (placeLog(logPos, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.X)) && c < branchLength) hangers.add(logPos.below());
                        if (c == branchLength) createLeaves(new BlockPos(x - c, y - 1, z - c), hangers);
                    } else {
                        placeLog(logPos);
                        placeLog(logPos.below());
                    }
                }

                if (direction == 7) {
                    BlockPos logPos = new BlockPos(x - c + 1, y, z + c - 1);
                    if (!root) {
                        if (placeLog(logPos, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.Z)) && c < branchLength) hangers.add(logPos.below());
                        if (c == branchLength) createLeaves(new BlockPos(x - c, y - 1, z + c), hangers);
                    } else {
                        placeLog(logPos);
                        placeLog(logPos.below());
                    }
                }

                if (direction == 8) {
                    BlockPos logPos = new BlockPos(x + c - 1, y, z - c + 1);
                    if (!root) {
                        if (placeLog(logPos, state -> state.setValue(BlockStateProperties.AXIS, Direction.Axis.Z)) && c < branchLength) hangers.add(logPos.below());
                        if (c == branchLength) createLeaves(new BlockPos(x + c, y - 1, z - c), hangers);
                    } else {
                        placeLog(logPos);
                        placeLog(logPos.below());
                    }
                }
            }
        }

        private boolean placeLog(BlockPos pos, Function<BlockState, BlockState> propertySetter) {
            // Reuse our own log as branch support without replacing it.
            if (planned.containsKey(pos)) return true;
            if (level.getBlockState(pos).is(BlockTags.LOGS)) {
                intersectsLog = true;
                return false;
            }
            return MarshwoodTrunkPlacer.this.placeLog(level, setter, random, pos, config, propertySetter);
        }

        private void placeLog(BlockPos pos) {
            if (planned.containsKey(pos)) return;
            BlockState existing = level.getBlockState(pos);
            if (existing.is(BlockTags.LOGS)) {
                intersectsLog = true;
                return;
            }
            if (existing.hasBlockEntity()) return;
            boolean soil = existing.is(BlockTags.DIRT) || existing.is(BlockTags.GRASS_BLOCKS)
                    || existing.is(BlockTags.MUD) || existing.is(BlockTags.MOSS_BLOCKS)
                    || existing.is(Blocks.PODZOL) || existing.is(Blocks.MYCELIUM) || existing.is(Blocks.FARMLAND);
            boolean vegetation = existing.is(BlockTags.REPLACEABLE_BY_TREES) && !existing.liquid();
            if (existing.isAir() || soil || vegetation) {
                setter.accept(pos, config.trunkProvider.getState(level, random, pos));
            }
        }

        private void createLeaves(BlockPos pos, List<BlockPos> hangers) {
            for (BlockPos hanger : hangers) list.add(MarshwoodFoliagePlacer.hangerAttachment(hanger));
            list.add(new FoliagePlacer.FoliageAttachment(pos, 1, false));
        }
    }
}
