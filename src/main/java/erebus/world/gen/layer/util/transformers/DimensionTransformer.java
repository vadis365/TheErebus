package erebus.world.gen.layer.util.transformers;

public interface DimensionTransformer {
    int getParentX(int x);

    int getParentY(int y);
}
