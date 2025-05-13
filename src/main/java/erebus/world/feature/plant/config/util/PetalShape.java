package erebus.world.feature.plant.config.util;

public enum PetalShape {
    DENSE_HEMISPHERE(3, 5, false),
    DISPERSE_HEMISPHERE(3, 3, true),
    UMBRELLA(4, 3, true);

    public final byte radius, height;
    public final boolean canHaveSecondaryColor;

    PetalShape(int radius, int height, boolean canHaveSecondaryColor) {
        this.radius = (byte) radius;
        this.height = (byte) height;
        this.canHaveSecondaryColor = canHaveSecondaryColor;
    }
}
