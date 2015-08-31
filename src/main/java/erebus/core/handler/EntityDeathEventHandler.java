package erebus.core.handler;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import erebus.ModAchievements;
import erebus.core.helper.Utils;
import erebus.entity.EntityBeetleLarva;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import java.util.ArrayList;

public class EntityDeathEventHandler {
	@SubscribeEvent
	public void dropEvent(LivingDropsEvent event) {
		if (event.entity instanceof EntityBeetleLarva) {
			EntityBeetleLarva beetle = (EntityBeetleLarva) event.entity;
			if (event.source.getSourceOfDamage() instanceof EntityPlayer) {
				event.entity.worldObj.getClosestPlayer(event.entity.posX, event.entity.posY, event.entity.posZ, 30).triggerAchievement(ModAchievements.beetle);

				if (beetle.isSquashed && dropedItem(event.drops, new ItemStack(Items.diamond)))
					event.entity.worldObj.getClosestPlayer(event.entity.posX, event.entity.posY, event.entity.posZ, 30).triggerAchievement(ModAchievements.diamond);
			}
		}
	}

	private boolean dropedItem(ArrayList<EntityItem> drops, ItemStack target) {
		for (EntityItem entity : drops)
			if (entity != null && Utils.areStacksTheSame(entity.getEntityItem(), target, false))
				return true;
		return false;
	}

}