package erebus.integration.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.DefaultOverlayHandler;
import erebus.ModBlocks;
import erebus.block.BlockDoorErebus;
import erebus.client.gui.GuiPetrifiedWorkbench;
import erebus.item.ItemMaterials;
import erebus.lib.Reference;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class NEIErebusConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {
		API.registerGuiOverlay(GuiPetrifiedWorkbench.class, "crafting");
		API.registerGuiOverlayHandler(GuiPetrifiedWorkbench.class, new DefaultOverlayHandler(), "crafting");

		API.registerRecipeHandler(new OfferingAltarNEIHandler());
		API.registerUsageHandler(new OfferingAltarNEIHandler());

		API.registerRecipeHandler(new SmoothieMakerNEIHandler());
		API.registerUsageHandler(new SmoothieMakerNEIHandler());

		API.hideItem(new ItemStack(ModBlocks.portal));
		API.hideItem(new ItemStack(ModBlocks.blockTurnip));
		API.hideItem(new ItemStack(ModBlocks.insectRepellent));
		API.hideItem(new ItemStack(ModBlocks.flowerPlanted, 1, OreDictionary.WILDCARD_VALUE));
		API.hideItem(new ItemStack(ModBlocks.honeyBlock));
		API.hideItem(new ItemStack(ModBlocks.bambooShoot));
		API.hideItem(new ItemStack(ModBlocks.slidingBlockPuzzle));
		API.hideItem(new ItemStack(ModBlocks.completedPuzzle));
		API.hideItem(new ItemStack(ModBlocks.anthillBlock));
		API.hideItem(new ItemStack(ModBlocks.anthillStairs));
		API.hideItem(new ItemStack(ModBlocks.soldierAntTrap));
		API.hideItem(new ItemStack(ModBlocks.puffShroom));
		API.hideItem(new ItemStack(ModBlocks.forceLock));
		API.hideItem(ItemMaterials.DATA.FORCE_KEY.makeStack());

		if (!OreType.ALUMINIUM.isEnabled()) {
			API.hideItem(new ItemStack(ModBlocks.oreAluminium));
			API.hideItem(ItemMaterials.DATA.INGOT_ALUMINIUM.makeStack());
		}
		if (!OreType.COPPER.isEnabled()) {
			API.hideItem(new ItemStack(ModBlocks.oreCopper));
			API.hideItem(ItemMaterials.DATA.INGOT_COPPER.makeStack());
		}
		if (!OreType.LEAD.isEnabled()) {
			API.hideItem(new ItemStack(ModBlocks.oreLead));
			API.hideItem(ItemMaterials.DATA.INGOT_LEAD.makeStack());
		}
		if (!OreType.SILVER.isEnabled()) {
			API.hideItem(new ItemStack(ModBlocks.oreSilver));
			API.hideItem(ItemMaterials.DATA.INGOT_SILVER.makeStack());
		}
		if (!OreType.TIN.isEnabled()) {
			API.hideItem(new ItemStack(ModBlocks.oreTin));
			API.hideItem(ItemMaterials.DATA.INGOT_TIN.makeStack());
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

		for (Block block : ModBlocks.BLOCKS)
			if (block instanceof BlockDoorErebus)
				API.hideItem(new ItemStack(block));
	}

	@Override
	public String getName() {
		return Reference.MOD_NAME;
	}

	@Override
	public String getVersion() {
		return "${version}";
	}
}