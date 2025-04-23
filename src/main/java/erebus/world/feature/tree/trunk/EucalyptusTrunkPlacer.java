package erebus.world.feature.tree.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.world.ModTrunkPlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiConsumer;

public class EucalyptusTrunkPlacer extends TrunkPlacer {

    private static final int SPAN = 5;
    private static final int BRANCHES = 8;

    public static final MapCodec<EucalyptusTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.intRange(0, 32).fieldOf("base_height").forGetter(placer -> placer.baseHeight),
                    Codec.intRange(0, 32).fieldOf("height_rand_a").forGetter(placer -> placer.heightRandA),
                    Codec.intRange(0, 32).fieldOf("height_rand_b").forGetter(placer -> placer.heightRandB)
            ).apply(instance, EucalyptusTrunkPlacer::new)
    );

    public EucalyptusTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return ModTrunkPlacers.EUCALYPTUS_TRUNK_PLACER.get();
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NotNull LevelSimulatedReader level, @NotNull BiConsumer<BlockPos, BlockState> blockSetter, @NotNull RandomSource random, int freeTreeHeight, BlockPos pos, @NotNull TreeConfiguration config) {
        setDirtAt(level, blockSetter, random, pos.below(), config);
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        int height = baseHeight + random.nextInt(heightRandA);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        for (int c = -2; c < 3; c++) {
            for (int d = -1; d < 2; d++) {
                list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(x + c, y + height + SPAN + 1, z + d), 0, false));
                list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(x + d, y + height + SPAN + 1, z + c), 0, false));
            }
        }

        for (int c = -1; c < 2; c++)
            for (int d = -1; d < 2; d++)
                list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(x + c, y + height + SPAN + 2, z + d), 0, false));

        for (int c = 0; c < BRANCHES; c++) {
            int disX = random.nextInt(SPAN * 2 + 1) - SPAN;
            int disY = random.nextInt(SPAN + 1);
            int disZ = random.nextInt(SPAN * 2 + 1) - SPAN;

            int posX = x + disX;
            int posY = y + height - 1 + disY;
            int posZ = z + disZ;

            for (int d = -1; d < 3; d++) {
                for (int e = -1; e < 2; e++) {
                    list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(posX + d, posY, posZ + e), 0, false));
                    list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(posX + e, posY, posZ + d), 0, false));
                }
            }

            for (int d = -1; d < 2; d++)
                for (int e = -1; e < 2; e++)
                    list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(posX + d, posY + 1, posZ + e), 0, false));

            for (int d = 0; d < SPAN; d++) {
                int xx = disX * (d + 1) / SPAN;
                int yy = disY * (d + 1) / SPAN;
                int zz = disZ * (d + 1) / SPAN;

                placeLog(level, blockSetter, random, pos.offset(xx, height - 1 + yy, zz), config);
            }

            placeLog(level, blockSetter, random, new BlockPos(posX, posY, posZ), config);
        }

        for (int c = 0; c < height + SPAN + 2; ++c) {
            placeLog(level, blockSetter, random, pos.above(c), config);
        }

        return list;
    }
}
