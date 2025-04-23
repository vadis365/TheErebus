package erebus.world.feature.tree.foliage;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.world.ModFoliagePlacers;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;

public class EucalyptusFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<EucalyptusFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance -> foliagePlacerParts(instance).apply(instance, EucalyptusFoliagePlacer::new));


    public EucalyptusFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return ModFoliagePlacers.EUCALYPTUS_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(@NotNull LevelSimulatedReader level, @NotNull FoliageSetter setter, @NotNull RandomSource random, @NotNull TreeConfiguration config, int maxHeight, @NotNull FoliageAttachment attachment, int h, int radius, int offset) {
        tryPlaceLeaf(level, setter, random, config, attachment.pos());
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
