package erebus.utils;

import net.minecraft.util.Mth;

public class AnimationMathHelper {

    private float phase;
    private float magnitude;
    private float oscillationSpeed;

    public AnimationMathHelper() {
        phase = 0.0F;
        magnitude = 0.0F;
        oscillationSpeed = 1.0F;
    }

    public float swing(float speed, float max) {
        magnitude += 3.2F;

        if (magnitude < 0.0F)
            magnitude = 0.0F;

        if (magnitude > speed)
            magnitude = speed;

        if (oscillationSpeed < speed)
            oscillationSpeed = speed;

        oscillationSpeed *= 0.8F;
        phase += oscillationSpeed * 0.5F;

        return (Mth.sin(phase) + 0.5F) * magnitude * max;
    }
}
