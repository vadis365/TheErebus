package erebus.world.layer.context;

import erebus.world.layer.area.Area;

public interface BigContext<A extends Area> extends Context {
    A createResult(Area transformer);
    A createResult(Area transformer, A layer);
    A createResult(Area transformer, A layer1, A layer2);
}
