package erebus.world;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import erebus.core.handler.configs.ConfigHandler;

public final class SpawnerErebus{
	@SubscribeEvent
	public void onWorldTick(WorldTickEvent e){
		if (e.phase == Phase.START && !e.world.isRemote && e.world.provider.dimensionId == ConfigHandler.INSTANCE.erebusDimensionID)runSpawning(e.world);
	}
	
	private void runSpawning(World world){
		
	}
}
