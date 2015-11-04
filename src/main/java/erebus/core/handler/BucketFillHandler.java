package erebus.core.handler;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.ModBlocks;
import erebus.ModItems;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class BucketFillHandler {

	@SubscribeEvent
	public void onBucketFill(FillBucketEvent event) {
		Block block = event.world.getBlock(event.target.blockX, event.target.blockY, event.target.blockZ);
		if (block == ModBlocks.honeyBlock && event.current != null && event.current.getItem() == Items.bucket) {
			event.world.setBlockToAir(event.target.blockX, event.target.blockY, event.target.blockZ);
			event.result = new ItemStack(ModItems.bucketHoney);
			event.setResult(Result.ALLOW);
		}
	}
}