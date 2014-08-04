package erebus.integration;

import net.minecraft.block.Block;
import codechicken.microblock.BlockMicroMaterial;
import codechicken.microblock.MicroMaterialRegistry;
import erebus.ModBlocks;
import erebus.block.BlockGneiss;
import erebus.block.BlockUmberstone;
import erebus.integration.ModIntegrationHandler.IModIntegration;
import erebus.lib.EnumWood;

public class FMBIntegration implements IModIntegration
{
	public String getModId() {
		return "ForgeMicroblock";
	}

	public void onInit()
    {
		addMicroblock(ModBlocks.umberstone, 0, BlockUmberstone.iconPaths.length);
		addMicroblock(ModBlocks.redGem, 0, 2);
		addMicroblock(ModBlocks.blockAmber, 0, 3);
		addMicroblock(ModBlocks.gneiss, 0, BlockGneiss.iconPaths.length);
		addMicroblock(ModBlocks.blockSilk);
		addMicroblock(ModBlocks.mirBrick);
		addMicroblock(ModBlocks.petrifiedWoodPlanks);
		addMicroblock(ModBlocks.planksErebus, 0, EnumWood.values().length);
		addMicroblock(ModBlocks.umberPaver, 0, 3);
		addMicroblock(ModBlocks.umberstonePillar);
	}

	public void onPostInit()
    {

	}

	private static void addMicroblock(Block block)
    {
		MicroMaterialRegistry.registerMaterial(new BlockMicroMaterial(block, 0), block.getUnlocalizedName());
	}

	private static void addMicroblock(Block block, int metaFrom, int metaTo)
    {
		for (int i = metaFrom; i < metaTo; i++)
			MicroMaterialRegistry.registerMaterial(new BlockMicroMaterial(block, i), block.getUnlocalizedName() + "." + i);
	}
}