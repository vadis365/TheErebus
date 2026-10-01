package erebus.events;

import erebus.registries.data.tags.ModBiomeTags;
import erebus.registries.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.item.context.UseOnContext;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

public class BedPlaceEventHandler {
    // Placement events omit the hand. Keep the native use context only while its
    // synchronous placement transaction runs, including any nested interactions.
    private final ThreadLocal<UseOnContext> bedUse = new ThreadLocal<>();

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onBedUse(UseItemOnBlockEvent event) {
        if (event.getUsePhase() != UseItemOnBlockEvent.UsePhase.ITEM_AFTER_BLOCK
                || !(event.getLevel() instanceof ServerLevel) || event.getPlayer() == null
                || !event.getItemStack().is(ItemTags.BEDS)) return;
        var previous = bedUse.get();
        bedUse.set(event.getUseOnContext());
        try {
            event.cancelWithResult(CommonHooks.onPlaceItemIntoWorld(event.getUseOnContext()));
        } finally {
            if (previous == null) bedUse.remove();
            else bedUse.set(previous);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onPlayerBedPlacement(BlockEvent.EntityPlaceEvent event) {
        var context = bedUse.get();
        if (context == null || event.getEntity() != context.getPlayer()
                || !(event.getLevel() instanceof ServerLevel level)
                || !event.getPlacedBlock().is(BlockTags.BEDS)
                || !level.getBiome(event.getPos()).is(ModBiomeTags.IS_EREBUS)) return;
        // Native cancellation restores both bed halves and sends inventory/block
        // corrections. Keep native infinite-material behavior for Creative.
        event.setCanceled(true);
        context.getItemInHand().consume(1, context.getPlayer());
        var pos = event.getPos();
        for (int i = 0; i < 3; i++) {
            var bug = ModEntities.BED_BUG.get().create(level, EntitySpawnReason.TRIGGERED);
            if (bug != null) {
                bug.setPos(pos.getX() + 0.5D + (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * 0.3D,
                        pos.getY() + 0.25D,
                        pos.getZ() + 0.5D + (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * 0.3D);
                level.addFreshEntity(bug);
            }
        }
    }
}
