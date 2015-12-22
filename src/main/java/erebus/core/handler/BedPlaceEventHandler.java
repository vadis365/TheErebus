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
	public void onPlayerTorchPlacement(PlaceEvent event) {

		World world = event.world;
		if (world.isRemote)
			return;

		if (event.player.dimension == ConfigHandler.INSTANCE.erebusDimensionID) {
			ItemStack itemstack = event.player.inventory.getCurrentItem();
			if (itemstack != null && itemstack.getItem() == Items.bed) {
				event.setCanceled(true);
				itemstack.stackSize--;
				EntityBedBug bedBug = new EntityBedBug(world);
				bedBug.setPosition(event.x, event.y, event.z);
				bedBug.worldObj.spawnEntityInWorld(bedBug);
			}
		}
	}
}