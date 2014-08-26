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