package erebus.core.handler;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.ModItems;

public class DeathCompassRespawnEvent {

	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone event) {
		if (event.wasDeath) {
			NBTTagCompound compound = new NBTTagCompound();
			ErebusExtendedPlayerProperties.get(event.original).saveNBTData(compound);
			ErebusExtendedPlayerProperties.get(event.entityPlayer).loadNBTData(compound);
			ErebusExtendedPlayerProperties playerProps = ErebusExtendedPlayerProperties.get(event.entityPlayer);
			ItemStack stack = new ItemStack(ModItems.deathCompass);
			if (!stack.hasTagCompound())
				stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setString("dimName", playerProps.getDimension());
			stack.getTagCompound().setInteger("homeX", playerProps.getXLocation());
			stack.getTagCompound().setInteger("homeZ", playerProps.getZLocation());
			event.entityPlayer.inventory.addItemStackToInventory(stack);
			event.entityPlayer.inventoryContainer.detectAndSendChanges();
			event.entityPlayer.inventory.markDirty();
		}
}
}