package erebus.integration.nei;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.DefaultOverlayHandler;
import erebus.ModBlocks;
import erebus.client.gui.GuiPetrifiedWorkbench;
import erebus.core.handler.configs.ConfigHandler;
import erebus.item.Materials;
import erebus.lib.Reference;

public class NEIErebusConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {
		API.registerGuiOverlay(GuiPetrifiedWorkbench.class, "crafting");
		API.registerGuiOverlayHandler(GuiPetrifiedWorkbench.class, new DefaultOverlayHandler(), "crafting");

		API.registerRecipeHandler(new OfferingAltarNEIHandler());
		API.registerUsageHandler(new OfferingAltarNEIHandler());

		API.registerRecipeHandler(new CraftingAltarNEIHandler());
		API.registerUsageHandler(new CraftingAltarNEIHandler());

		API.hideItem(new ItemStack(ModBlocks.portal));
		API.hideItem(new ItemStack(ModBlocks.blockTurnip));
		API.hideItem(new ItemStack(ModBlocks.insectRepellent));
		API.hideItem(new ItemStack(ModBlocks.flowerPlanted, 1, OreDictionary.WILDCARD_VALUE));
		API.hideItem(new ItemStack(ModBlocks.honeyBlock));

		if (!ConfigHandler.INSTANCE.aluminium) {
			API.hideItem(new ItemStack(ModBlocks.oreAluminium));
			API.hideItem(Materials.createStack(Materials.DATA.ingotAluminium));
		}
		if (!ConfigHandler.INSTANCE.copper) {
			API.hideItem(new ItemStack(ModBlocks.oreCopper));
			API.hideItem(Materials.createStack(Materials.DATA.ingotCopper));
		}
		if (!ConfigHandler.INSTANCE.lead) {
			API.hideItem(new ItemStack(ModBlocks.oreLead));
			API.hideItem(Materials.createStack(Materials.DATA.ingotLead));
		}
		if (!ConfigHandler.INSTANCE.silver) {
			API.hideItem(new ItemStack(ModBlocks.oreSilver));
			API.hideItem(Materials.createStack(Materials.DATA.ingotSilver));
		}
		if (!ConfigHandler.INSTANCE.tin) {
			API.hideItem(new ItemStack(ModBlocks.oreTin));
			API.hideItem(Materials.createStack(Materials.DATA.ingotTin));
		}

		API.hideItem(new ItemStack(ModBlocks.hanger));

		API.hideItem(new ItemStack(ModBlocks.glowshroomStalkMain));
		API.hideItem(new ItemStack(ModBlocks.glowshroomStalkDown1));
		API.hideItem(new ItemStack(ModBlocks.glowshroomStalkDown2));
		API.hideItem(new ItemStack(ModBlocks.glowshroomStalkDown3));
		API.hideItem(new ItemStack(ModBlocks.glowshroomStalkN1));
		API.hideItem(new ItemStack(ModBlocks.glowshroomStalkS1));
		API.hideItem(new ItemStack(ModBlocks.glowshroomStalkNS2));
		API.hideItem(new ItemStack(ModBlocks.glowshroomStalkW1));
		API.hideItem(new ItemStack(ModBlocks.glowshroomStalkE1));
		API.hideItem(new ItemStack(ModBlocks.glowshroomStalkWE2));
		API.hideItem(new ItemStack(ModBlocks.glowshroomStalkN3));
		API.hideItem(new ItemStack(ModBlocks.glowshroomStalkS3));
		API.hideItem(new ItemStack(ModBlocks.glowshroomStalkW3));
		API.hideItem(new ItemStack(ModBlocks.glowshroomStalkE3));

		API.hideItem(new ItemStack(ModBlocks.doorAmber));
		API.hideItem(new ItemStack(ModBlocks.doorBaobab));
		API.hideItem(new ItemStack(ModBlocks.doorEucalyptus));
		API.hideItem(new ItemStack(ModBlocks.doorMahogany));
		API.hideItem(new ItemStack(ModBlocks.doorMossbark));
		API.hideItem(new ItemStack(ModBlocks.doorAsper));
		API.hideItem(new ItemStack(ModBlocks.doorCypress));
		API.hideItem(new ItemStack(ModBlocks.doorRotten));
		API.hideItem(new ItemStack(ModBlocks.doorPetrified));
		API.hideItem(new ItemStack(ModBlocks.doorScorched));
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