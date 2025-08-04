package erebus.events;

import erebus.entity.BedBug;
import erebus.registries.data.ModTags;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class BedPlaceEventHandler {
	@SubscribeEvent
	public void onPlayerBedPlacement(PlayerInteractEvent.RightClickBlock event) {
		Level level = event.getEntity().level();
		if (level.isClientSide())
			return;

		if (level.getBiome(event.getPos()).is(ModTags.IS_EREBUS)) {
			ItemStack itemstack = event.getItemStack();
			BlockPos posAbove = event.getPos().above();
			if (!itemstack.isEmpty() && itemstack.is(ItemTags.BEDS)) {
				event.setCanceled(true);
				itemstack.shrink(1);
				for (int i = 0; i < 3; i++) {
					BedBug bed_bug = ModEntities.BED_BUG.get().create(level);
					if (bed_bug != null) {
						bed_bug.setPos(posAbove.getX() + (level.random.nextFloat() * 0.03D - level.random.nextFloat() * 0.03D), posAbove.getY() + 0.25D, posAbove.getZ() + (level.random.nextFloat() * 0.03D - level.random.nextFloat() * 0.03D));
						level.addFreshEntity(bed_bug);
					}
				}
			}
		}
	}
}