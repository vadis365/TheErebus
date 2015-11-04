package erebus.core.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.ModAchievements;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.block.BlockLogErebus;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class EntityPickupEventHandler {

	@SubscribeEvent
	public void itemPickup(EntityItemPickupEvent event) {
		ItemStack stack = event.item.getEntityItem();
		if (stack == null || stack.getItem() == null)
			return;

		if (stack.getItem() == ModItems.spiderTShirt)
			event.entityPlayer.triggerAchievement(ModAchievements.tshirt);
		else {
			Block block = Block.getBlockFromItem(stack.getItem());
			if (block instanceof BlockLogErebus || block == ModBlocks.scorchedWood || block == ModBlocks.saplessLog)
				event.entityPlayer.triggerAchievement(AchievementList.mineWood);
		}
	}
}
