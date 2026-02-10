package erebus.world.util;

import net.neoforged.neoforge.server.ServerLifecycleHooks;

import java.util.Objects;

public class LevelUtils {

    public static long getOverworldSeed() {
        return Objects.requireNonNull(ServerLifecycleHooks.getCurrentServer()).overworld().getSeed();
    }
}
