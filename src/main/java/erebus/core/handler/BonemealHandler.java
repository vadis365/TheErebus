package erebus.core.handler;

import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.block.plants.BlockSaplingErebus;

public class BonemealHandler
{
	@SubscribeEvent
	public void onBonemeal(BonemealEvent event)
	{
		if (!event.world.isRemote && event.block instanceof BlockSaplingErebus)
		{
			if (event.world.rand.nextFloat() < 0.45D)
			{
				((BlockSaplingErebus) event.block).growTree(event.world, event.x, event.y, event.z, event.world.rand);
			}
			event.setResult(Result.ALLOW);
		}
	}
}