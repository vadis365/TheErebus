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
		registerMaterial(ModBlocks.umberstone, 0, BlockUmberstone.iconPaths.length - 1);
		registerMaterial(ModBlocks.redGem);
		registerMaterial(ModBlocks.blockAmber, 0, 2);
		registerMaterial(ModBlocks.gneiss, 0, BlockGneiss.iconPaths.length - 1);
		registerMaterial(ModBlocks.blockSilk);
		registerMaterial(ModBlocks.mirBrick);
		registerMaterial(ModBlocks.petrifiedWoodPlanks);
		registerMaterial(ModBlocks.planks, 0, EnumWood.values().length - 1);
		registerMaterial(ModBlocks.umberPaver, 0, 2);
		registerMaterial(ModBlocks.umberstonePillar);

		for (EnumWood wood : EnumWood.values())
		{
			if (wood.hasLog())
			{
				registerMaterial(wood.getLog());
			}
			if (wood.hasLeaves())
			{
				registerMaterial(wood.getLeaves());
			}
		}
	}

	@Override
	public void onPostInit()
	{
	}

	private void registerMaterial(Block block)
	{
		registerMaterial(block, 0, 1);
	}

	private void registerMaterial(Block block, int minMeta, int maxMeta)
	{
		for (int i = minMeta; i <= maxMeta; i++)
		{
			FMLInterModComms.sendMessage(getModId(), "microMaterial", new ItemStack(block, 1, i));
		}
	}
}