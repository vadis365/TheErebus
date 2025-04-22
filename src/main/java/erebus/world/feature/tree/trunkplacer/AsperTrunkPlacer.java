package erebus.world.feature.tree.trunkplacer;

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
    protected final int baseHeight;
    protected final int heightRandA;

    private final Direction[] directions = new Direction[4];
    protected final int heightRandB;

    public AsperTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
        this.baseHeight = baseHeight;
        this.heightRandA = heightRandA;
        this.heightRandB = heightRandB;
        directions[0] = Direction.fromAxisAndDirection(Direction.Axis.X, Direction.AxisDirection.NEGATIVE);
        directions[1] = Direction.fromAxisAndDirection(Direction.Axis.X, Direction.AxisDirection.POSITIVE);
        directions[2] = Direction.fromAxisAndDirection(Direction.Axis.Z, Direction.AxisDirection.NEGATIVE);
        directions[3] = Direction.fromAxisAndDirection(Direction.Axis.Z, Direction.AxisDirection.POSITIVE);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacers.ASPER_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos pos, TreeConfiguration config) {
        setDirtAt(level, blockSetter, random, pos.below(), config);
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        int height = random.nextInt(heightRandA) + baseHeight;

        for (int y = 0; y < height; y++) {
            placeLog(level, blockSetter, random, pos.above(y), config);

            if (random.nextBoolean()) {
                for (int extraWood = 0, extraWoodAttempt = 0; extraWoodAttempt < 5 && extraWood < 3; ++extraWoodAttempt) {
                    int dir = random.nextInt(4);

                    if (random.nextInt(4) != 3) {
                        if (placeLog(level, blockSetter, random, pos.above(y).relative(directions[dir], 1), config, state -> state.setValue(BlockStateProperties.AXIS, directions[dir].getAxis()))) {
                            list.add(new FoliagePlacer.FoliageAttachment(pos.above(y).relative(directions[dir], 1), 0, false));
                        }

                    }
                    extraWood++;
                }
            }
        }

        list.add(new FoliagePlacer.FoliageAttachment(pos.above(height), 0, false));

        return list;
    }
}
