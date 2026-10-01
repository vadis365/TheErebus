package erebus.events;

import erebus.Erebus;
import erebus.entity.BotFlyLarva;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.UUIDUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.jspecify.annotations.Nullable;

@EventBusSubscriber(modid = Erebus.MODID)
public final class BotFlyInfestationHandler {
    public static final String COUNT = "erebus_bot_fly_parasites";
    private static final String VISUAL = "erebus_bot_fly_visual";

    private BotFlyInfestationHandler() {
    }

    public static int count(Player player) {
        return Math.clamp(player.getPersistentData().getIntOr(COUNT, 0), 0, 3);
    }

    public static @Nullable BotFlyLarva getLarva(Player player) {
        if (!(player.level() instanceof ServerLevel level)) return null;
        return player.getPersistentData().read(VISUAL, UUIDUtil.CODEC)
                .map(level::getEntity).filter(BotFlyLarva.class::isInstance).map(BotFlyLarva.class::cast).orElse(null);
    }

    public static void infect(Player player) {
        if (player.level().isClientSide() || !player.isAlive() || player.isCreative() || player.isSpectator()) return;
        player.getPersistentData().putInt(COUNT, Math.min(3, count(player) + 1));
        ensureVisual(player);
    }

    public static void setCount(Player player, int count) {
        if (player.level().isClientSide()) return;
        player.getPersistentData().putInt(COUNT, Math.clamp(count, 0, 3));
        var larva = getLarva(player);
        if (larva != null) {
            larva.setParasiteCount((byte) count(player));
            if (count(player) == 0) larva.discard();
        }
    }

    public static void ensureVisual(Player player) {
        if (!(player.level() instanceof ServerLevel level) || count(player) == 0 || !player.isAlive()) return;
        var larva = getLarva(player);
        if (larva == null || !larva.isAlive()) {
            larva = ModEntities.BOT_FLY_LARVA.get().create(level, EntitySpawnReason.EVENT);
            if (larva == null) return;
            larva.bind(player);
            larva.setPos(player.position());
            if (!level.addFreshEntity(larva)) return;
            player.getPersistentData().store(VISUAL, UUIDUtil.CODEC, larva.getUUID());
        }
        larva.setParasiteCount((byte) count(player));
    }

    @SubscribeEvent
    public static void tick(PlayerTickEvent.Post event) {
        if (!event.getEntity().level().isClientSide() && event.getEntity().tickCount % 20 == 0)
            ensureVisual(event.getEntity());
    }

    @SubscribeEvent
    public static void death(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) setCount(player, 0);
    }
}
