package erebus.events;

import erebus.Erebus;
import erebus.network.client.AntiVenomPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = Erebus.MODID)
public final class AntiVenomHandler {
    public static final String DURATION = "anti_venom_duration";
    public static final int MAX_SECONDS = 180;

    private AntiVenomHandler() {
    }

    public static int seconds(Player player) {
        return Math.clamp(player.getPersistentData().getIntOr(DURATION, 0), 0, MAX_SECONDS);
    }

    public static void drink(Player player, int seconds) {
        if (player.level().isClientSide()) return;
        player.getPersistentData().putInt(DURATION, Math.min(MAX_SECONDS, seconds(player) + seconds));
        sync(player);
    }

    @SubscribeEvent
    public static void tick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        int remaining = seconds(player);
        if (player.level().isClientSide() || remaining == 0) return;
        var harmful = player.getActiveEffects().stream()
                .filter(effect -> effect.getEffect().value().getCategory() == MobEffectCategory.HARMFUL)
                .map(effect -> effect.getEffect()).toList();
        harmful.forEach(player::removeEffect);
        if (player.level().getGameTime() % 20 == 0) {
            player.getPersistentData().putInt(DURATION, remaining - 1);
            sync(player);
        }
    }

    @SubscribeEvent
    public static void login(PlayerEvent.PlayerLoggedInEvent event) {
        if (seconds(event.getEntity()) > 0) sync(event.getEntity());
    }

    @SubscribeEvent
    public static void changedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (seconds(event.getEntity()) > 0) sync(event.getEntity());
    }

    private static void sync(Player player) {
        if (player instanceof ServerPlayer serverPlayer)
            PacketDistributor.sendToPlayer(serverPlayer, new AntiVenomPacket(seconds(player)));
    }
}
