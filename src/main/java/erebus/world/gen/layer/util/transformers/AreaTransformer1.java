package erebus.world.gen.layer.util.transformers;

import erebus.world.gen.layer.util.Area;
import erebus.world.gen.layer.util.AreaFactory;
import erebus.world.gen.layer.util.BigContext;

public interface AreaTransformer1 extends DimensionTransformer {
    default <A extends Area> AreaFactory<A> run(BigContext<A> context, AreaFactory<A> factory) {
        return () -> {
            A area = factory.make();
            return context.createResult((x, z) -> {
                context.initRandom(x, z);
                return apply(context, area, x, z);
            });
        };
    }

    int apply(BigContext<?> context, Area area, int x, int z);
}
