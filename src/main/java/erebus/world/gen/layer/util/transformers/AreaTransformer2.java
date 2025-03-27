package erebus.world.gen.layer.util.transformers;

import erebus.world.gen.layer.util.Area;
import erebus.world.gen.layer.util.AreaFactory;
import erebus.world.gen.layer.util.BigContext;
import erebus.world.gen.layer.util.Context;

public interface AreaTransformer2 extends DimensionTransformer {
    default <A extends Area> AreaFactory<A> run(BigContext<A> context, AreaFactory<A> first, AreaFactory<A> second) {
        return () -> {
            A f = first.make();
            A s = second.make();
            return context.createResult((x, z) -> {
                context.initRandom(x, z);
                return applyPixel(context, f, s, x, z);
            }, f, s);
        };
    }

    int applyPixel(Context context, Area first, Area second, int x, int z);
}
