package erebus.core.handler;

import erebus.ModItems;
import erebus.core.capabilities.player.IPlayerDeathLocationCapability;
import erebus.core.capabilities.player.PlayerDeathLocationCapability;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DeathCompassRespawnEvent {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onClonePlayer(PlayerEvent.Clone event) {
		if (event.isWasDeath()) {
			if (!event.getOriginal().getEntityWorld().getGameRules().getBoolean("keepInventory")) {
				if (event.getOriginal().hasCapability(PlayerDeathLocationCapability.CAPABILITY_PLAYER_DEATH_LOCATION, null)) {
					IPlayerDeathLocationCapability cap = event.getOriginal().getCapability(PlayerDeathLocationCapability.CAPABILITY_PLAYER_DEATH_LOCATION, null);
					ItemStack stack = new ItemStack(ModItems.DEATH_COMPASS);
					if (!stack.hasTagCompound())
						stack.setTagCompound(new NBTTagCompound());
					stack.getTagCompound().setInteger("dimID", cap.getGraveDimension());
					stack.getTagCompound().setString("dimName", cap.getGraveDimensionName());
					stack.getTagCompound().setInteger("homeX", cap.getGraveLocationX());
					stack.getTagCompound().setInteger("homeZ", cap.getGraveLocationZ());
					if(!event.getEntityPlayer().inventory.addItemStackToInventory(stack))
						if(!event.getEntityPlayer().getEntityWorld().isRemote)
							event.getEntityPlayer().entityDropItem(stack, 1F);
					event.getEntityPlayer().inventoryContainer.detectAndSendChanges();
					event.getEntityPlayer().inventory.markDirty();
				}
			}
		}
	}
}