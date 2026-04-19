package erebus.world.carver;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.FloatProviders;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CanyonCarverConfiguration.CanyonShapeConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;

public class ErebusCanyonCarverConfiguration extends CarverConfiguration {

    public static final Codec<ErebusCanyonCarverConfiguration> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    CarverConfiguration.CODEC.forGetter(o -> o),
                    FloatProviders.CODEC.fieldOf("vertical_rotation").forGetter(o -> o.verticalRotation),
                    CanyonShapeConfiguration.CODEC.fieldOf("shape").forGetter(o -> o.shape)
            ).apply(instance, ErebusCanyonCarverConfiguration::new)
    );

    public final FloatProvider verticalRotation;
    public final CanyonShapeConfiguration shape;

    public ErebusCanyonCarverConfiguration(float probability, HeightProvider y, FloatProvider yScale, VerticalAnchor lavaLevel, CarverDebugSettings debugSettings, HolderSet<Block> replaceable, FloatProvider verticalRotation, CanyonShapeConfiguration shape) {
        super(probability, y, yScale, lavaLevel, debugSettings, replaceable);
        this.verticalRotation = verticalRotation;
        this.shape = shape;
    }

    public ErebusCanyonCarverConfiguration(CarverConfiguration config, FloatProvider verticalRotation, CanyonShapeConfiguration shape) {
        this(config.probability, config.y, config.yScale, config.lavaLevel, config.debugSettings, config.replaceable, verticalRotation, shape);
    }
}
