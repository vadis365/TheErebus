package erebus.core.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import erebus.Erebus;
import erebus.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DeathCompassRespawnEvent {

	@SubscribeEvent
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		if (!event.player.worldObj.isRemote) {
			NBTTagCompound playerData = Erebus.proxy.getEntityData(event.player.getCommandSenderName());
			ErebusExtendedPlayerProperties playerProps = ErebusExtendedPlayerProperties.get(event.player);
			playerProps.saveNBTData(playerData);
			System.out.println("Repawn Event Fires");
			if (playerData != null) {
				System.out.println("Data is not Null");
				playerProps.loadNBTData(playerData);
				ItemStack stack = new ItemStack(ModItems.deathCompass);
				stack.setTagCompound(new NBTTagCompound());
				stack.getTagCompound().setString("dimName", playerProps.getDimension());
				stack.getTagCompound().setInteger("homeX", playerProps.getXLocation());
				stack.getTagCompound().setInteger("homeZ", playerProps.getZLocation());
				event.player.inventory.addItemStackToInventory(stack);
				event.player.inventory.markDirty();
				playerProps.setRecentlyDeceased(false);
			}
		}
	}
}