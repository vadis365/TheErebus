package erebus.world.carver;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;

public class ErebusCaveCarverConfiguration extends CarverConfiguration {
    public static final Codec<ErebusCaveCarverConfiguration> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    CarverConfiguration.CODEC.forGetter(config -> config),
                    FloatProvider.CODEC.fieldOf("horizontal_radius_multiplier").forGetter(config -> config.horizontalRadiusMultiplier),
                    FloatProvider.CODEC.fieldOf("vertical_radius_multiplier").forGetter(config -> config.verticalRadiusMultiplier),
                    FloatProvider.codec(-1.0F, 1.0F).fieldOf("floor_level").forGetter(config -> config.floorLevel))
                    .apply(instance, ErebusCaveCarverConfiguration::new)
    );
    public final FloatProvider horizontalRadiusMultiplier;
    public final FloatProvider verticalRadiusMultiplier;
    final FloatProvider floorLevel;

    public ErebusCaveCarverConfiguration(float probability, HeightProvider y, FloatProvider yScale, VerticalAnchor lavaLevel, CarverDebugSettings debugSettings, HolderSet<Block> replaceable, FloatProvider horizontalRadiusMultiplier, FloatProvider verticalRadiusMultiplier, FloatProvider floorLevel) {
        super(probability, y, yScale, lavaLevel, debugSettings, replaceable);
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
}
