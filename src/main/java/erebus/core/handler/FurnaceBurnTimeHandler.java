package erebus.core.handler;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.block.BlockSlabPlanks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.FuelBurnTimeEvent;

@SuppressWarnings("deprecation")
public class FurnaceBurnTimeHandler {

	@SubscribeEvent
	public void onFurnaceBurnTime(FuelBurnTimeEvent event) {
		ItemStack fuel = event.fuel;
		if (fuel != null) {
			Block block = Block.getBlockFromItem(fuel.getItem());
			if (block instanceof BlockSlabPlanks) {
				event.burnTime = 150;
				event.setResult(Result.ALLOW);
			}
		}
	}
}