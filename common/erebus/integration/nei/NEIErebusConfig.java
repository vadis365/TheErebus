package erebus.integration.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.DefaultOverlayHandler;
import erebus.ModBlocks;
import erebus.client.gui.GuiPetrifiedWorkbench;
import erebus.lib.Reference;

public class NEIErebusConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {
		API.registerGuiOverlay(GuiPetrifiedWorkbench.class, "crafting");
		API.registerGuiOverlayHandler(GuiPetrifiedWorkbench.class, new DefaultOverlayHandler(), "crafting");

		API.hideItem(ModBlocks.erebusGrass.blockID);
		API.hideItem(ModBlocks.portalErebus.blockID);
		API.hideItem(ModBlocks.blockTurnip.blockID);
		API.hideItem(ModBlocks.insectRepellent.blockID);
		API.hideItem(ModBlocks.umberFurnace_on.blockID);
		API.hideItem(ModBlocks.umberGolemStatue.blockID);
	}

	@Override
	public String getName() {
		return Reference.MOD_NAME;
	}

	@Override
	public String getVersion() {
		return Reference.MOD_VERSION;
	}
}

// Here: http://adf.ly/WAp6y