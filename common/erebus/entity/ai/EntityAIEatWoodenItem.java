package erebus.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockMushroomCap;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntityAnimal;
import erebus.ModBlocks;
import erebus.block.BlockBambooCrop;
import erebus.block.BlockBambooTorch;
import erebus.block.BlockHollowLog;
import erebus.core.handler.ConfigurationHandler;

public class EntityAIEatWoodenItem extends EntityAIEatBlock {

	public EntityAIEatWoodenItem(EntityAnimal entity) {
		super(entity, null, 0);
	}

	@Override
	protected boolean canEatBlock(int blockID, int blockMeta) {
		if (blockID == 0)
			return false;

		Block block = Block.blocksList[blockID];
		if (block.blockHardness == -1)
			return false;

		if (blockID == ModBlocks.planksErebus.blockID && blockMeta == 9)
			return false;

		if (blockID == ModBlocks.plankSlabs[2].blockID && blockMeta == 1)
			return false;

		if (blockID == ModBlocks.plankStairs[9].blockID)
			return false;

		if (ConfigurationHandler.beetleLarvaEating == 2)
			return true;
		else if (block.blockMaterial != Material.wood || block instanceof BlockLog || block instanceof BlockBambooCrop || block instanceof BlockHollowLog || block instanceof BlockMushroomCap || block instanceof BlockBambooTorch)
			return false;
		else if (ConfigurationHandler.beetleLarvaEating == 0 && block.hasTileEntity(blockMeta))
			return false;

		return true;
	}

	@Override
	protected boolean isEntityReady() {
		return false;
	}

	@Override
	protected void afterEaten() {
	}
}