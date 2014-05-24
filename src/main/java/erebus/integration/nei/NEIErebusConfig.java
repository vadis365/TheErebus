package erebus.integration.nei;

import net.minecraft.item.ItemStack;
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

		API.hideItem(new ItemStack(ModBlocks.erebusGrass));
		API.hideItem(new ItemStack(ModBlocks.portalErebus));
		API.hideItem(new ItemStack(ModBlocks.blockTurnip));
		API.hideItem(new ItemStack(ModBlocks.insectRepellent));
		API.hideItem(new ItemStack(ModBlocks.umberFurnace_on));
		API.hideItem(new ItemStack(ModBlocks.flowerPlanted));
		API.hideItem(new ItemStack(ModBlocks.doorAmber));
		API.hideItem(new ItemStack(ModBlocks.erebusHoneyBlock));
		API.hideItem(new ItemStack(ModBlocks.altar));
		API.hideItem(new ItemStack(ModBlocks.erebusMushroomCap3));
		API.hideItem(new ItemStack(ModBlocks.erebusMushroomCap4));
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