package erebus.client;

import erebus.Erebus;
import erebus.entity.AnimatedBlock;
import erebus.entity.GlowWorm;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.LightLayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

import java.util.HashSet;
import java.util.Set;

@EventBusSubscriber(modid = Erebus.MODID, value = Dist.CLIENT)
public final class EntityLighting {
    private static volatile Sources sources = new Sources(null, Set.of());
    private static ClientLevel previousLevel;
    private EntityLighting() {
    }

    public static int emission(Object engine, long position) {
        var current = sources;
        return current.engine == engine && current.positions.contains(position) ? 9 : 0;
    }

    @SubscribeEvent
    public static void tick(ClientTickEvent.Post event) {
        var level = Minecraft.getInstance().level;
        var old = sources;
        if (level == null) {
            sources = new Sources(null, Set.of());
            previousLevel = null;
            return;
        }
        Set<Long> positions = new HashSet<>();
        for (var entity : level.entitiesForRendering()) {
            boolean emits = entity instanceof GlowWorm worm && worm.isNearPlayer()
                    || entity instanceof AnimatedBlock block && block.getBlockType().getLightEmission() > 0;
            if (emits && entity.isAlive()
                    && level.environmentAttributes().getValue(EnvironmentAttributes.SKY_LIGHT_FACTOR, entity.position(), null) < 0.5F)
                positions.add(entity.blockPosition().asLong());
        }
        var lightEngine = level.getLightEngine();
        var engine = lightEngine.getLayerListener(LightLayer.BLOCK);
        if (previousLevel == level && old.engine == engine && old.positions.equals(positions)) return;
        sources = new Sources(engine, Set.copyOf(positions));
        Set<Long> changed = new HashSet<>(positions);
        if (previousLevel == level) {
            changed.addAll(old.positions);
            changed.removeIf(pos -> positions.contains(pos) && old.positions.contains(pos));
        }
        previousLevel = level;
        for (long pos : changed) {
            var block = BlockPos.of(pos);
            if (level.hasChunkAt(block)) lightEngine.checkBlock(block);
        }
    }

    private record Sources(Object engine, Set<Long> positions) {
    }
}
