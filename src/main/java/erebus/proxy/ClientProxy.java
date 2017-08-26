package erebus.proxy;

import erebus.ModBlocks;
import erebus.ModColourManager;
import erebus.ModItems;
import erebus.client.render.item.RenderErebusShield;
import erebus.client.render.tile.TileEntityGaeanKeystoneRenderer;
import erebus.core.handler.GogglesClientTickHandler;
import erebus.tileentity.TileEntityBambooShield;
import erebus.tileentity.TileEntityExoPlateShield;
import erebus.tileentity.TileEntityGaeanKeystone;
import erebus.tileentity.TileEntityJadeShield;
import erebus.tileentity.TileEntityReinExoShield;
import erebus.tileentity.TileEntityRhinoExoShield;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerItemAndBlockRenderers() {
		MinecraftForge.EVENT_BUS.register(new GogglesClientTickHandler());
	}

	@Override
	public void setCustomStateMap(Block block, StateMap stateMap) {
		ModelLoader.setCustomStateMapper(block, stateMap);
	}

	@Override
	public void registerItemAndBlockColourRenderers() {
		ModColourManager.registerColourHandlers();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGaeanKeystone.class, new TileEntityGaeanKeystoneRenderer());
	}

	@Override
	public void postInit() {
		// shield rendering
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBambooShield.class, new RenderErebusShield(RenderErebusShield.Shieldtype.BAMBOO));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExoPlateShield.class, new RenderErebusShield(RenderErebusShield.Shieldtype.EXO_PLATE));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJadeShield.class, new RenderErebusShield(RenderErebusShield.Shieldtype.JADE));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityReinExoShield.class, new RenderErebusShield(RenderErebusShield.Shieldtype.REIN_EXO));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRhinoExoShield.class, new RenderErebusShield(RenderErebusShield.Shieldtype.RHINO_EXO));

		// item models
		ForgeHooksClient.registerTESRItemStack(ModItems.BAMBOO_SHIELD, 0, TileEntityBambooShield.class);
		ForgeHooksClient.registerTESRItemStack(ModItems.EXOSKELETON_SHIELD, 0, TileEntityExoPlateShield.class);
		ForgeHooksClient.registerTESRItemStack(ModItems.JADE_SHIELD, 0, TileEntityJadeShield.class);
		ForgeHooksClient.registerTESRItemStack(ModItems.REIN_EXOSKELETON_SHIELD, 0, TileEntityReinExoShield.class);
		ForgeHooksClient.registerTESRItemStack(ModItems.RHINO_EXOSKELETON_SHIELD, 0, TileEntityRhinoExoShield.class);
	}
}