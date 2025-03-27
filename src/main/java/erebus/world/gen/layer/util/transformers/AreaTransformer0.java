package erebus.world.gen.layer.util.transformers;

import erebus.world.gen.layer.util.Area;
import erebus.world.gen.layer.util.AreaFactory;
import erebus.world.gen.layer.util.BigContext;
import erebus.world.gen.layer.util.Context;

public interface AreaTransformer0 {
    default <A extends Area> AreaFactory<A> run(BigContext<A> context) {
        return () -> context.createResult((x, z) -> {
            context.initRandom(x, z);
            return apply(context, x, z);
        });
    }

    int apply(Context context, int x, int z);
}
