package erebus.world.layer.trait;

public interface DimensionOffset0Transformer extends DimensionTransformer {
    @Override
    default int getParentX(int x) {
        return x;
    }

    @Override
    default int getParentZ(int z) {
        return z;
    }
}
