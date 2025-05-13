package erebus.world.feature.plant.config.util;

public enum StemShape {
    SMALL_X(1, 1),
    SMALL_PLUS(1, 1),
    LARGE_PLUS(2, 2);

    public final byte radius, height;

    StemShape(int radius, int height) {
        this.radius = (byte) radius;
        this.height = (byte) height;
    }
}
