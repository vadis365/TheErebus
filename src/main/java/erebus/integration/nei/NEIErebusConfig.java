package erebus.integration.nei;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.DefaultOverlayHandler;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.client.gui.GuiPetrifiedWorkbench;
import erebus.core.handler.configs.ConfigHandler;
import erebus.lib.Reference;

public class NEIErebusConfig implements IConfigureNEI
{

	@Override
	public void loadConfig()
	{
		API.registerGuiOverlay(GuiPetrifiedWorkbench.class, "crafting");
		API.registerGuiOverlayHandler(GuiPetrifiedWorkbench.class, new DefaultOverlayHandler(), "crafting");

		API.registerRecipeHandler(new OfferingAltarNEIHandler());
		API.registerUsageHandler(new OfferingAltarNEIHandler());

		API.hideItem(new ItemStack(ModBlocks.portal));
		API.hideItem(new ItemStack(ModBlocks.blockTurnip));
		API.hideItem(new ItemStack(ModBlocks.insectRepellent));
		API.hideItem(new ItemStack(ModBlocks.flowerPlanted, 1, OreDictionary.WILDCARD_VALUE));
		API.hideItem(new ItemStack(ModBlocks.doorAmber));
		API.hideItem(new ItemStack(ModBlocks.honeyBlock));
		API.hideItem(new ItemStack(ModBlocks.altar));
		API.hideItem(new ItemStack(ModBlocks.mushroomCap3));
		API.hideItem(new ItemStack(ModBlocks.mushroomCap4));

		byte b = 0;
		if (!ConfigHandler.INSTANCE.aluminium)
		{
			API.hideItem(new ItemStack(ModBlocks.oreExtra, 1, 0));
			API.hideItem(new ItemStack(ModItems.metalIngot, 1, 0));
			b++;
		}
		if (!ConfigHandler.INSTANCE.copper)
		{
			API.hideItem(new ItemStack(ModBlocks.oreExtra, 1, 1));
			API.hideItem(new ItemStack(ModItems.metalIngot, 1, 1));
			b++;
		}
		if (!ConfigHandler.INSTANCE.lead)
		{
			API.hideItem(new ItemStack(ModBlocks.oreExtra, 1, 2));
			API.hideItem(new ItemStack(ModItems.metalIngot, 1, 2));
			b++;
		}
		if (!ConfigHandler.INSTANCE.silver)
		{
			API.hideItem(new ItemStack(ModBlocks.oreExtra, 1, 3));
			API.hideItem(new ItemStack(ModItems.metalIngot, 1, 3));
			b++;
		}
		if (!ConfigHandler.INSTANCE.tin)
		{
			API.hideItem(new ItemStack(ModBlocks.oreExtra, 1, 4));
			API.hideItem(new ItemStack(ModItems.metalIngot, 1, 4));
			b++;
		}
		if (b >= 5)
		{
			API.hideItem(new ItemStack(ModItems.metalIngot, 1, OreDictionary.WILDCARD_VALUE));
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
	}

	@Override
	public String getName()
	{
		return Reference.MOD_NAME;
	}

	@Override
	public String getVersion()
	{
		return Reference.MOD_VERSION;
	}
}