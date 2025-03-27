package erebus.world.gen.layer.util;

public interface BigContext<A extends Area> extends Context {
    void initRandom(long x, long z);

    A createResult(PixelTransformer transformer);

    default A createResult(PixelTransformer transformer, A area) {
        return createResult(transformer);
    }

    default A createResult(PixelTransformer transformer, A first, A second) {
        return createResult(transformer);
    }

    default int random(int first, int second) {
        return nextRandom(2) == 0 ? first : second;
    }

    default int random(int first, int second, int third, int fourth) {
        return switch (nextRandom(4)) {
            case 0 -> first;
            case 1 -> second;
            case 2 -> third;
            default -> fourth;
        };
    }
}
