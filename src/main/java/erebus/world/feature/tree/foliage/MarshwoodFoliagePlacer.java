package erebus.world.feature.tree.foliage;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.ModBlocks;
import erebus.registries.world.ModFoliagePlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;

public class MarshwoodFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<MarshwoodFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance -> foliagePlacerParts(instance).apply(instance, MarshwoodFoliagePlacer::new));

    public MarshwoodFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return ModFoliagePlacers.BALSAM_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(@NotNull LevelSimulatedReader level, @NotNull FoliageSetter setter, @NotNull RandomSource random, @NotNull TreeConfiguration config, int maxHeight, @NotNull FoliageAttachment attachment, int height, int radius, int offset) {
        BlockPos pos = attachment.pos();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        for (int xOff = x - radius; xOff <= x + radius; xOff++) {
            for (int zOff = z - radius; zOff <= z + radius; zOff++) {
                for (int yOff = y; yOff > y - height; yOff--) {
                    double dSq = Math.pow(xOff - x, 2) + Math.pow(zOff - z, 2) + Math.pow(yOff - y, 2);
                    long rounded = Math.round(Math.sqrt(dSq));
                    if (rounded <= radius) {
                        if (rounded == 0) {
                            setter.set(new BlockPos(xOff, yOff, zOff), ModBlocks.LOG_MARSHWOOD.get().defaultBlockState());
                        } else {
                            tryPlaceLeaf(level, setter, random, config, new BlockPos(xOff, yOff, zOff));
                        }
                    }

                    if (rounded == 0) {
                        tryPlaceLeaf(level, setter, random, config, new BlockPos(xOff, yOff - 2, zOff));
                        // TODO: Add a vine down 1
                    }
                }
            }
        }
    }

    @Override
    public int foliageHeight(@NotNull RandomSource random, int i, @NotNull TreeConfiguration config) {
        return 3;
    }

    @Override
    protected boolean shouldSkipLocation(@NotNull RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return localX == range && localZ == range && (random.nextInt(2) == 0 || localY == 0);
    }
}
