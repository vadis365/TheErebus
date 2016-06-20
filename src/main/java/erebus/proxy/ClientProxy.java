package erebus.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.core.handler.GogglesClientTickHandler;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerItemAndBlockRenderers() {
		ModItems.registerRenderers();
		ModBlocks.registerRenderers();
		MinecraftForge.EVENT_BUS.register(new GogglesClientTickHandler());
	}

	@Override
	public void setCustomStateMap(Block block, StateMap stateMap) {
		ModelLoader.setCustomStateMapper(block, stateMap);
	}
}