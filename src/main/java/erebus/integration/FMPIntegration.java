package erebus.integration;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.event.FMLInterModComms;
import erebus.ModBlocks;
import erebus.block.BlockGneiss;
import erebus.block.BlockUmberstone;
import erebus.integration.ModIntegrationHandler.IModIntegration;
import erebus.lib.EnumWood;

public class FMPIntegration implements IModIntegration
{
	@Override
	public String getModId()
	{
		return "ForgeMicroblock";
	}

	@Override
	public void onInit()
	{
		registerMaterial(ModBlocks.umberstone, 0, BlockUmberstone.iconPaths.length);
		registerMaterial(ModBlocks.redGem, 0, 2);
		registerMaterial(ModBlocks.blockAmber, 0, 3);
		registerMaterial(ModBlocks.gneiss, 0, BlockGneiss.iconPaths.length);
		registerMaterial(ModBlocks.blockSilk, 0, 1);
		registerMaterial(ModBlocks.mirBrick, 0, 1);
		registerMaterial(ModBlocks.petrifiedWoodPlanks, 0, 1);
		registerMaterial(ModBlocks.planks, 0, EnumWood.values().length);
		registerMaterial(ModBlocks.umberPaver, 0, 3);
		registerMaterial(ModBlocks.umberstonePillar, 0, 1);

		for (EnumWood wood : EnumWood.values())
		{
			if (wood.hasLog())
			{
				registerMaterial(wood.getLog(), 0, 1);
			}
			if (wood.hasLeaves())
			{
				registerMaterial(wood.getLeaves(), 0, 1);
			}
		}
	}

	@Override
	public void onPostInit()
	{
	}

	private void registerMaterial(Block block, int minMeta, int maxMeta)
	{
		for (int i = minMeta; i <= maxMeta; i++)
		{
			FMLInterModComms.sendMessage(getModId(), "microMaterial", new ItemStack(block, 1, i));
		}
	}
}