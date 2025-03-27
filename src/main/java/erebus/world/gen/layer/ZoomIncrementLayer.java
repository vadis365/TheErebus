package erebus.world.gen.layer;

import erebus.world.gen.layer.util.Area;
import erebus.world.gen.layer.util.BigContext;
import erebus.world.gen.layer.util.transformers.AreaTransformer1;

public class ZoomIncrementLayer implements AreaTransformer1 {
    @Override
    public int apply(BigContext<?> context, Area area, int x, int z) {
        int init = area.get(getParentX(x), getParentY(z));
        context.initRandom(x >> 1 << 1, z >> 1 << 1);
        int X = x & 1;
        int Z = z & 1;

        if (X == 0 && Z == 0) return init;

        int initZ = area.get(getParentX(x), getParentY(z + 1));
        int rand = context.random(init, initZ);

        if (X == 0 && Z == 1) return rand;

        int initX = area.get(getParentX(x + 1), getParentY(z));
        rand = context.random(init, initX);

        if (X == 1 && Z == 0) return rand;

        int initXZ = area.get(getParentX(x + 1), getParentY(z + 1));
        return modeOrRandom(context, init, initZ, initX, initXZ);
    }

    @Override
    public int getParentX(int x) {
        return x >> 1;
    }

    @Override
    public int getParentY(int y) {
        return y >> 1;
    }

    protected int modeOrRandom(BigContext<?> context, int c, int x, int z, int xz) {
        if (x == z && z == xz) return x;
        if (c == x && c == z) return c;
        if (c == z && c == xz) return c;
        if (c == x && z != xz) return c;
        if (c == z && x != xz) return c;
        if (c == xz && x != z) return c;
        if (x == z && c != xz) return x;
        if (x == xz && c != z) return x;
        return z == xz && c != x ? z : context.random(c, x, z, xz);
    }
}
