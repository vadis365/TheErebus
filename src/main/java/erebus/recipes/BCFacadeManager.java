package erebus.recipes;

import cpw.mods.fml.common.event.FMLInterModComms;
import erebus.ModBlocks;

public class BCFacadeManager {

	public static void registerFacades() {
		for (int i = 0; i < 5; i++)
			addFacade(ModBlocks.umberstone.blockID, i);
		addFacade(ModBlocks.redGem.blockID);
		addFacade(ModBlocks.blockAmber.blockID);
		addFacade(ModBlocks.quickSand.blockID);
		for (int i = 0; i < 9; i++)
			addFacade(ModBlocks.planksErebus.blockID, i);
		addFacade(ModBlocks.blockSilk.blockID);
		addFacade(ModBlocks.mirBrick.blockID);
		addFacade(ModBlocks.petrifiedWoodPlanks.blockID);
		for (int i = 0; i < 3; i++)
			addFacade(ModBlocks.umberPaver.blockID);
	}

	private static void addFacade(int blockID) {
		addFacade(blockID, 0);
	}

	private static void addFacade(int blockID, int meta) {
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", String.format("%d@%d", blockID, meta));
	}
}