package erebus.world.carver;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.FloatProviders;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;

import java.util.Optional;

public class ErebusCaveCarverConfiguration extends CarverConfiguration {
    public static final Codec<ErebusCaveCarverConfiguration> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            CarverConfiguration.CODEC.forGetter(config -> config),
                            FloatProviders.CODEC.fieldOf("horizontal_radius_multiplier").forGetter(config -> config.horizontalRadiusMultiplier),
                            FloatProviders.CODEC.fieldOf("vertical_radius_multiplier").forGetter(config -> config.verticalRadiusMultiplier),
                            FloatProviders.codec(-1.0F, 1.0F).fieldOf("floor_level").forGetter(config -> config.floorLevel),
                            GaussianHeight.CODEC.optionalFieldOf("gaussian_height").forGetter(config -> config.gaussianHeight))
                    .apply(instance, ErebusCaveCarverConfiguration::new)
    );
    public final FloatProvider horizontalRadiusMultiplier;
    public final FloatProvider verticalRadiusMultiplier;
    public final Optional<GaussianHeight> gaussianHeight;
    final FloatProvider floorLevel;

    public ErebusCaveCarverConfiguration(float probability, HeightProvider y, FloatProvider yScale, VerticalAnchor lavaLevel, CarverDebugSettings debugSettings, HolderSet<Block> replaceable, FloatProvider horizontalRadiusMultiplier, FloatProvider verticalRadiusMultiplier, FloatProvider floorLevel) {
        this(new CarverConfiguration(probability, y, yScale, lavaLevel, debugSettings, replaceable),
                horizontalRadiusMultiplier, verticalRadiusMultiplier, floorLevel, Optional.empty());
    }

    public ErebusCaveCarverConfiguration(CarverConfiguration config, FloatProvider horizontalRadiusMultiplier, FloatProvider verticalRadiusMultiplier, FloatProvider floorLevel, Optional<GaussianHeight> gaussianHeight) {
        super(config.probability, config.y, config.yScale, config.lavaLevel, config.debugSettings, config.replaceable);
        this.gaussianHeight = gaussianHeight;
        this.horizontalRadiusMultiplier = horizontalRadiusMultiplier;
        this.verticalRadiusMultiplier = verticalRadiusMultiplier;
        this.floorLevel = floorLevel;
    }

    public ErebusCaveCarverConfiguration(float probability, HeightProvider y, FloatProvider yScale, VerticalAnchor lavaLevel, HolderSet<Block> replaceable, FloatProvider horizontalRadiusMultiplier, FloatProvider verticalRadiusMultiplier, FloatProvider floorLevel) {
        this(probability, y, yScale, lavaLevel, CarverDebugSettings.DEFAULT, replaceable, horizontalRadiusMultiplier, verticalRadiusMultiplier, floorLevel);
    }

    public ErebusCaveCarverConfiguration(CarverConfiguration config, FloatProvider horizontalRadiusMultiplier, FloatProvider verticalRadiusMultiplier, FloatProvider floorLevel) {
        this(config.probability, config.y, config.yScale, config.lavaLevel, config.debugSettings, config.replaceable, horizontalRadiusMultiplier, verticalRadiusMultiplier, floorLevel);
    }

    public double sampleHeight(RandomSource random, CarvingContext context) {
        return gaussianHeight.map(height -> context.getMinGenY() + height.mean() + random.nextGaussian() * height.deviation())
                .orElseGet(() -> (double) y.sample(random, context));
    }

    public record GaussianHeight(double mean, double deviation) {
        public static final Codec<GaussianHeight> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.doubleRange(-2032, 2031).fieldOf("mean").forGetter(GaussianHeight::mean),
                Codec.doubleRange(0, 4096).fieldOf("deviation").forGetter(GaussianHeight::deviation)
        ).apply(instance, GaussianHeight::new));
    }
}
