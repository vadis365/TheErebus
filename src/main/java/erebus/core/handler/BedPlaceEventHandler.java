package erebus.core.handler;

import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.EntityBedBug;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BedPlaceEventHandler {
	@SubscribeEvent
	public void onPlayerBedPlacement(PlaceEvent event) {

		World world = event.getWorld();
		if (world.isRemote)
			return;

		if (event.getPlayer().dimension == ConfigHandler.INSTANCE.erebusDimensionID) {
			ItemStack itemstack = event.getPlayer().inventory.getCurrentItem();
			if (itemstack != null && itemstack.getItem() == Items.BED) {
				event.setCanceled(true);
				itemstack.shrink(1);
				for (int i = 0; i < 3; i++) {
					EntityBedBug bedBug = new EntityBedBug(world);
					bedBug.setPosition(event.getPos().getX() + (world.rand.nextFloat() * 0.03D - world.rand.nextFloat() * 0.03D), event.getPos().getY() + 0.25D, event.getPos().getZ() + (world.rand.nextFloat() * 0.03D - world.rand.nextFloat() * 0.03D));
					world.spawnEntity(bedBug);
				}
			}
		}
	}
}