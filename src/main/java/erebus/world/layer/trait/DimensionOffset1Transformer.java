package erebus.world.layer.trait;

public interface DimensionOffset1Transformer extends DimensionTransformer {
    @Override
    default int getParentX(int x) {
        return x - 1;
    }

    @Override
    default int getParentZ(int z) {
        return z - 1;
    }
}
