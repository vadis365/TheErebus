package erebus.item;

import erebus.Erebus;
import erebus.registries.world.ModDimensionRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public final class EmptyErebusMapItem extends Item {
    public EmptyErebusMapItem() {
        super(new Properties().setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("erebus_map"))));
    }

    @Override
    public @NonNull InteractionResult use(Level level, @NonNull Player player, @NonNull InteractionHand hand) {
        if (!level.dimension().equals(ModDimensionRegistries.DIMENSION_KEY)) {
            if (player instanceof ServerPlayer serverPlayer) serverPlayer.sendSystemMessage(Component.translatable("message.erebus.map.dimension"), true);
            return InteractionResult.FAIL;
        }
        if (!(level instanceof ServerLevel server)) return InteractionResult.SUCCESS;
        var empty = player.getItemInHand(hand);
        var map = ErebusMapItem.createMap(server, player.getBlockX(), player.getBlockZ(), (byte) 4);
        empty.shrink(1);
        player.awardStat(Stats.ITEM_USED.get(this));
        server.playSound(null, player, SoundEvents.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, player.getSoundSource(), 1, 1);
        if (empty.isEmpty()) return InteractionResult.SUCCESS.heldItemTransformedTo(map);
        if (!player.getInventory().add(map)) player.drop(map, false);
        return InteractionResult.SUCCESS;
    }
}
