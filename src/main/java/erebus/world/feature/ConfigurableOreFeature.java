package erebus.world.feature;

import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import org.jspecify.annotations.NonNull;

import java.util.function.BooleanSupplier;

public class ConfigurableOreFeature extends OreFeature {
    private final BooleanSupplier enabled;

    public ConfigurableOreFeature(BooleanSupplier enabled) {
        super(OreConfiguration.CODEC);
        this.enabled = enabled;
    }

    @Override
    public boolean place(@NonNull FeaturePlaceContext<OreConfiguration> context) {
        return enabled.getAsBoolean() && super.place(context);
    }
}
