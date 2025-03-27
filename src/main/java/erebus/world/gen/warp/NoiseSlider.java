package erebus.world.gen.warp;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Mth;

public record NoiseSlider(double target, int size, int offset) {
    public static final Codec<NoiseSlider> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.DOUBLE.fieldOf("target").forGetter(slider -> slider.target),
            Codec.INT.fieldOf("size").forGetter(slider -> slider.size),
            Codec.INT.fieldOf("offset").forGetter(slider -> slider.offset)
    ).apply(instance, NoiseSlider::new));

    public double applySlide(double density, double y) {
        if (size <= 0) return density;

        double slide = y - (double) offset / size;
        return Mth.clampedLerp(target, density, slide);
    }
}
