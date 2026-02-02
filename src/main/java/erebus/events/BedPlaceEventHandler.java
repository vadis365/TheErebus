package erebus.events;

import erebus.entity.BedBug;
import erebus.registries.data.tags.ModBiomeTags;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class BedPlaceEventHandler {
	@SubscribeEvent
	public void onPlayerBedPlacement(PlayerInteractEvent.RightClickBlock event) {
		Level level = event.getEntity().level();
		ItemStack itemstack = event.getItemStack();
		if (level.getBiome(event.getPos()).is(ModBiomeTags.IS_EREBUS)) {
			if (!itemstack.isEmpty() && itemstack.is(ItemTags.BEDS)) {
				if (level.isClientSide()) {
					event.setCanceled(true);
				}
				else {
					BlockPos posAbove = event.getPos().above();
					event.setCanceled(true);
					itemstack.shrink(1);
					for (int i = 0; i < 3; i++) {
						BedBug bed_bug = ModEntities.BED_BUG.get().create(level, EntitySpawnReason.TRIGGERED);
						if (bed_bug != null) {
							bed_bug.setPos(posAbove.getX() + 0.5D +  (level.getRandom().nextFloat() * 0.3D - level.getRandom().nextFloat() * 0.3D), posAbove.getY() + 0.25D, posAbove.getZ() + 0.5D + (level.getRandom().nextFloat() * 0.3D - level.getRandom().nextFloat() * 0.3D));
							level.addFreshEntity(bed_bug);
						}
					}
				}
			}
		}
	}
}
