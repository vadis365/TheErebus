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
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiConsumer;

public class AsperTrunkPlacer extends TrunkPlacer {

    protected final int width;

    private final Direction[] directions = new Direction[4];

    public static final MapCodec<AsperTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.intRange(0, 32).fieldOf("base_height").forGetter(placer -> placer.baseHeight),
                    Codec.intRange(0, 24).fieldOf("height_rand_a").forGetter(placer -> placer.heightRandA),
                    Codec.intRange(0, 24).fieldOf("height_rand_b").forGetter(placer -> placer.heightRandB),
                    Codec.intRange(1, 2).fieldOf("width").forGetter(placer -> placer.width)
            ).apply(instance, AsperTrunkPlacer::new)
    );

    public AsperTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, int width) {
        super(baseHeight, heightRandA, heightRandB);
        this.width = width;
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
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        int x = pos.getX(), z = pos.getZ();
        int c = 3 - random.nextInt(3);
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        OptionalInt optionalInt = OptionalInt.empty();

        for(int yy = 0; yy < freeTreeHeight; ++yy) {
            int y = pos.getY() + yy;

            if(y >= 1 && c > 0) {
                x += direction.getStepX();
                z += direction.getStepZ();
            }

            if(placeLog(level, blockSetter, random, mutableBlockPos.set(x, y, z), config)) {
                optionalInt = OptionalInt.of(y + 1);
            }
        }

        if(optionalInt.isPresent()) {
            list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(x, optionalInt.getAsInt(), z), 1, false));
        }

        x = pos.getX();
        z = pos.getZ();

        for(int d = 0; d < 4; d++) {

            int ran1 = c - random.nextInt(2) - 1;
            int ran2 = 1 + random.nextInt(3);
            optionalInt = OptionalInt.empty();

            for(int y = ran1; y < freeTreeHeight && ran2 > 0; --ran2) {
                if(y >= 1) {
                    int yy = pos.getY() + y;
                    x += directions[d].getStepX();
                    z += directions[d].getStepZ();

                    if(placeLog(level, blockSetter, random, mutableBlockPos.set(x, yy, z), config)) {
                        optionalInt = OptionalInt.of(yy + 1);
                    }
                }

                ++y;
            }

            if(optionalInt.isPresent()) {
                list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(x, optionalInt.getAsInt(), z), 0, false));
            }
        }

        return list;
    }
}
