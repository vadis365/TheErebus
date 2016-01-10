package erebus.core.handler;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.EntityBedBug;

public class BedPlaceEventHandler {
	@SubscribeEvent
	public void onPlayerBedPlacement(PlaceEvent event) {

		World world = event.world;
		if (world.isRemote)
			return;

		if (event.player.dimension == ConfigHandler.INSTANCE.erebusDimensionID) {
			ItemStack itemstack = event.player.inventory.getCurrentItem();
			if (itemstack != null && itemstack.getItem() == Items.bed) {
				event.setCanceled(true);
				itemstack.stackSize--;
				for (int i = 0; i < 3; i++) {
					EntityBedBug bedBug = new EntityBedBug(world);
					bedBug.setPosition(event.x + (world.rand.nextFloat() * 0.03D - world.rand.nextFloat() * 0.03D), event.y + 0.25D, event.z + (world.rand.nextFloat() * 0.03D - world.rand.nextFloat() * 0.03D));
					bedBug.worldObj.spawnEntityInWorld(bedBug);
				}
			}
		}
	}
}