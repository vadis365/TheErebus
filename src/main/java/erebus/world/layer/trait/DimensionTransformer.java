package erebus.world.layer.trait;

public interface DimensionTransformer {
    int getParentX(int x);
    int getParentZ(int z);
}
