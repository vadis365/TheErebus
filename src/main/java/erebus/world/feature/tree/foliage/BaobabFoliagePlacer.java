package erebus.world.feature.tree.foliage;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.world.tree.ModFoliagePlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;

public class BaobabFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<BaobabFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance -> foliagePlacerParts(instance).apply(instance, BaobabFoliagePlacer::new));


    public BaobabFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return ModFoliagePlacers.BAOBAB_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(@NotNull LevelSimulatedReader level, @NotNull FoliageSetter setter, @NotNull RandomSource random, @NotNull TreeConfiguration config, int maxHeight, @NotNull FoliageAttachment attachment, int h, int radius, int offset) {
        int height = 2;
        BlockPos pos = attachment.pos();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        for (int xOff = x - radius; xOff <= x + radius; xOff++) {
            for (int zOff = z - radius; zOff <= z + radius; zOff++) {
                for (int yOff = y; yOff < y + height; yOff++) {
                    double sq = Math.pow(xOff - x, 2) + Math.pow(zOff - z, 2) + Math.pow(yOff - y, 2);
                    if (Math.round(Math.sqrt(sq)) <= radius) {
                        if (Math.round(Math.sqrt(sq)) == 0) {
                            setter.set(new BlockPos(xOff, yOff, zOff), ModBlocks.LOG_BAOBAB.get().defaultBlockState());
                        } else {
                            placeLeavesRow(level, setter, random, config, new BlockPos(xOff, yOff, zOff), radius, 0, attachment.doubleTrunk());
                        }
                    }
                }
            }
        }
    }

    @Override
    public int foliageHeight(@NotNull RandomSource random, int i, @NotNull TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(@NotNull RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return false;
    }
}
