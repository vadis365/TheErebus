package erebus.integration;

import net.minecraft.block.Block;
import codechicken.microblock.BlockMicroMaterial;
import codechicken.microblock.MicroMaterialRegistry;
import cpw.mods.fml.common.Loader;
import erebus.ModBlocks;
import erebus.block.BlockGneiss;
import erebus.block.BlockPlanksErebus;
import erebus.block.BlockUmberstone;

public class FMBIntegration {

	public static void integrate() {
		if (Loader.isModLoaded("ForgeMicroblock")) {
			addMicroblock(ModBlocks.umberstone, 0, BlockUmberstone.iconPaths.length);
			addMicroblock(ModBlocks.redGem, 0, 2);
			addMicroblock(ModBlocks.blockAmber, 0, 3);
			addMicroblock(ModBlocks.gneiss, 0, BlockGneiss.iconPaths.length);
			addMicroblock(ModBlocks.blockSilk);
			addMicroblock(ModBlocks.mirBrick);
			addMicroblock(ModBlocks.petrifiedWoodPlanks);
			addMicroblock(ModBlocks.planksErebus, 0, BlockPlanksErebus.plankTypes.length);
			addMicroblock(ModBlocks.umberPaver, 0, 3);
			addMicroblock(ModBlocks.umberstonePillar);
		}
	}

	private static void addMicroblock(Block block) {
		MicroMaterialRegistry.registerMaterial(new BlockMicroMaterial(block, 0), block.getUnlocalizedName());
	}

	private static void addMicroblock(Block block, int metaFrom, int metaTo) {
		for (int i = metaFrom; i < metaTo; i++)
			MicroMaterialRegistry.registerMaterial(new BlockMicroMaterial(block, i), block.getUnlocalizedName() + "." + i);
	}
}