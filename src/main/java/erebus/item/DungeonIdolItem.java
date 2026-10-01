package erebus.item;

import erebus.Erebus;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jspecify.annotations.NonNull;

public class DungeonIdolItem extends Item {
    private final int variant;

    public DungeonIdolItem(String name, int variant) {
        super(new Properties().setId(ResourceKey.create(Registries.ITEM, Erebus.prefix(name))));
        this.variant = variant;
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        var player = context.getPlayer();
        var pos = context.getClickedPos().above();
        var level = context.getLevel();
        var stack = context.getItemInHand();
        if (player == null || !player.mayUseItemAt(pos, context.getClickedFace(), stack)
                || !level.isInWorldBounds(pos.above()) || !level.getWorldBorder().isWithinBounds(pos)
                || !level.hasChunkAt(pos)) return InteractionResult.FAIL;
        if (!(level instanceof ServerLevel server)) return InteractionResult.SUCCESS;
        var guardian = ModEntities.DUNGEON_UMBER_GOLEM.get().create(server, EntitySpawnReason.SPAWN_ITEM_USE);
        if (guardian == null) return InteractionResult.FAIL;
        guardian.setVariant(variant);
        guardian.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        if (!server.noCollision(guardian) || !server.isUnobstructed(guardian) || !server.addFreshEntity(guardian)) return InteractionResult.FAIL;
        stack.consume(1, player);
        server.gameEvent(player, GameEvent.ENTITY_PLACE, pos);
        return InteractionResult.SUCCESS_SERVER;
    }
}
