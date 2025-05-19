package erebus.world.feature.tree.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.world.tree.ModTrunkPlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiConsumer;

public class CypressTrunkPlacer extends TrunkPlacer {

    public static final MapCodec<CypressTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.intRange(0, 32).fieldOf("base_height").forGetter(placer -> placer.baseHeight),
                    Codec.intRange(0, 32).fieldOf("height_rand_a").forGetter(placer -> placer.heightRandA),
                    Codec.intRange(0, 32).fieldOf("height_rand_b").forGetter(placer -> placer.heightRandB)
            ).apply(instance, CypressTrunkPlacer::new)
    );

    private List<FoliagePlacer.FoliageAttachment> list;

    private static final int[] offsetX = { -1, 1, 0, 0 };
    private static final int[] offsetZ = { 0, 0, -1, 1 };

    public CypressTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return ModTrunkPlacers.CYPRESS_TRUNK_PLACER.get();
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NotNull LevelSimulatedReader level, @NotNull BiConsumer<BlockPos, BlockState> blockSetter, @NotNull RandomSource random, int freeTreeHeight, BlockPos pos, @NotNull TreeConfiguration config) {
        setDirtAt(level, blockSetter, random, pos.below(), config);
        list = Lists.newArrayList();
        float chance =  random.nextFloat();

        if(chance >= 0.5F) {
            generateMediumTree(level, blockSetter, random, pos, config);
        } else if(chance >= 0.3F) {
            generateLargeTree(level, blockSetter, random, pos, config);
        } else {
            generateSmallTree(level, blockSetter, random, pos, config);
        }

        return list;
    }

    private boolean canPlaceAt(LevelSimulatedReader level, BlockPos pos, int height, int radius) {
        if(!level.isStateAtPosition(pos, BlockBehaviour.BlockStateBase::isAir)) return false;

        for(int x = -radius; x <= radius; x++) {
            for(int z = -radius; z <= radius; z++) {
                for(int y = 1; y < height; y++) {
                    if(!level.isStateAtPosition(pos, BlockBehaviour.BlockStateBase::isAir)) return false;
                }
            }
        }

        return true;
    }

    private void placeLeaf(BlockPos pos) {
        list.add(new FoliagePlacer.FoliageAttachment(pos, 0, false));
    }

    private void placeOuterLeaves(RandomSource random, BlockPos pos, int x, int z, int y, boolean canSkip) {
        if(!canSkip || random.nextInt(10) != 0) placeLeaf(pos.offset(-2 + 4 * x, y, -1 + 2 * z));
        if(!canSkip || random.nextInt(10) != 0) placeLeaf(pos.offset(-1 + 2 * x, y, -2 + 4 * z));
    }

    private void generateSmallTree(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> setter, RandomSource random, BlockPos pos, TreeConfiguration config) {
        int height = random.nextInt(4) + 5;
        int trunkHeight = random.nextInt(2) + 1;
        int leafHeight = height - trunkHeight;

        if(canPlaceAt(level, pos, height + 2, 2)) {
            for(int y = 0; y < height; y++) placeLog(level, setter, random, pos.above(y), config);
            for(int y = height + 1; y <= height + 2; y++) placeLeaf(pos.above(y));

            for(int c = 0; c < 4; c++) {
                for(int y = trunkHeight; y <= height; y++) placeLog(level, setter, random, pos.offset(offsetX[c], y, offsetZ[c]), config);

                if(leafHeight - 4 > 1) {
                    for(int y = trunkHeight + 2; y <= height - 2; y++) {
                        if((y == trunkHeight + 2 || y == height - 2) && leafHeight - 4 > 3 && random.nextInt(11) == 0)
                            continue;
                        placeLeaf(pos.offset(offsetX[c] * 2, y, offsetZ[c] * 2));
                    }
                }
            }

            for(int x = 0; x < 2; x++) {
                for(int z = 0; z < 2; z++) {
                    for(int y = trunkHeight + 1; y <= height - 1; y++) {
                        if((y == trunkHeight + 1 || y == height - 1) && leafHeight - 2 > 3 && random.nextInt(8) == 0)
                            continue;
                        placeLeaf(pos.offset(-1 + 2 * x, y, -1 + 2 * z));
                    }
                }
            }
        }
    }

    private void generateMediumTree(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> setter, RandomSource random, BlockPos pos, TreeConfiguration config) {
        int height = random.nextInt(5) + 8;
        int trunkHeight = random.nextInt(3) + 1;
        int leafHeight = height - trunkHeight;

        if(canPlaceAt(level, pos, height + 3, 3)) {
            for(int y = 0; y <= height; y++) placeLog(level, setter, random, pos.above(y), config);
            for(int y = height + 1; y <= height + 3; y++) placeLeaf(pos.above(y));

            for(int c = 0; c < 4; c++) {
                for(int y = trunkHeight; y <= height + 1; y++) placeLeaf(pos.offset(offsetX[c], y, offsetZ[c]));
                for(int y = trunkHeight + 1; y <= height - 2; y++) placeLeaf(pos.offset(offsetX[c] * 2, y, offsetZ[c] * 2));

                if(leafHeight - 7 > 1) {
                    for(int y = trunkHeight + 3; y <= height - 4; y++) {
                        if((y == trunkHeight + 3 || y == height - 4) && leafHeight - 7 > 3 && random.nextInt(10) == 0)
                            continue;
                        placeLeaf(pos.offset(offsetX[c] * 3, y, offsetZ[c] * 3));
                    }
                }
            }

            for(int x = 0; x < 2; x++) {
                for(int z = 0; z < 2; z++) {
                    for(int y = trunkHeight + 1; y <= height - 1; y++) {
                        if((y == trunkHeight + 1 || y == height - 1) && random.nextInt(14) == 0)
                            continue;
                        placeLeaf(pos.offset(-1 + 2 * x, y, -1 + 2 * z));
                    }

                    for(int y = trunkHeight + 2; y <= height - 3; y++) {
                        boolean canSkip = y == trunkHeight + 2 || y == height - 3;
                        placeOuterLeaves(random, pos, x, z, y, canSkip);
                    }
                }
            }
        }
    }

    private void generateLargeTree(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> setter, RandomSource random, BlockPos pos, TreeConfiguration config) {
        int height = random.nextInt(6) + 12;
        int trunkHeight = random.nextInt(3) + 2;
        int leafHeight = height - trunkHeight;

        if(canPlaceAt(level, pos, height + 4, 3)) {
            for(int y = 0; y <= height; y++) {
                placeLog(level, setter, random, pos.above(y), config);

                if(y <= height - 2) {
                    for(int c = 0; c < 4; c++) {
                        placeLeaf(pos.offset(offsetX[c], y, offsetZ[c]));
                    }
                }
            }

            for(int y = height + 1; y <= height + 4; y++) placeLeaf(pos.above(y));

            for(int c = 0; c < 4; c++) {
                for(int y = height - 1; y <= height + 2; y++) {
                    if(y == height + 2 && random.nextInt(5) == 0) continue;
                    placeLeaf(pos.offset(offsetX[c], y, offsetZ[c]));
                }

                for(int y = trunkHeight; y <= height; y++) {
                    if(y == height && random.nextInt(6) == 0) continue;
                    placeLeaf(pos.offset(offsetX[c] * 2, y, offsetZ[c] * 2));
                }

                for(int y = trunkHeight + 2; y <= height - 3; y++) {
                    placeLeaf(pos.offset(offsetX[c] * 3, y, offsetZ[c] * 3));
                }
            }

            for(int x = 0; x < 2; x++) {
                for(int z = 0; z < 2; z++) {
                    for(int y = trunkHeight; y <= height + 1; y++) placeLeaf(pos.offset(-1 + 2 * x, y, -1 + 2 * z));

                    for(int y = trunkHeight + 1; y <= height - 2; y++) {
                        boolean canSkip = y == trunkHeight + 1 || y == height - 2;
                        placeOuterLeaves(random, pos, x, z, y, canSkip);
                    }

                    for(int y = trunkHeight + 3; y <= height - 3; y++) {
                        if(y == height - 3  && random.nextInt(7) == 0) continue;
                        placeLeaf(pos.offset(-2 + 4 * x, y, -2 + 4 * z));
                    }

                    if(leafHeight - 9 > 1) {
                        for(int y = trunkHeight + 4; y <= height - 5; y++) {
                            boolean canSkip = (y == trunkHeight + 4 || y == height - 5) && leafHeight - 9 > 2;
                            if(!canSkip || random.nextInt(12) != 0) placeLeaf(pos.offset(-3 + 6 * x, y, -1 + 2 * z));
                            if(!canSkip || random.nextInt(12) != 0) placeLeaf(pos.offset(-1 + 2 * x, y, -3 + 6 * z));
                        }
                    }
                }
            }
        }
    }
}
