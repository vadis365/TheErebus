package erebus.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;
import erebus.ModBlocks;
import erebus.entity.EntityBlackAnt;

public class EntityAIEatMushroom extends EntityAIEatBlock {

	private final double moveSpeed;

	public EntityAIEatMushroom(EntityAnimal entity, double moveSpeed, int eatSpeed) {
		super(entity, null, 0, null, moveSpeed, eatSpeed);
		this.moveSpeed = moveSpeed;
	}

	@Override
	protected boolean canEatBlock(Block block, int blockMeta) {
		if (block == null)
			return false;

		if (block == ModBlocks.erebusPlantSmall && blockMeta <= 4 || block == Blocks.brown_mushroom || block == Blocks.red_mushroom)
			return true;
		
		if (block == ModBlocks.doubleHeightPlant && blockMeta == 4 || block == ModBlocks.doubleHeightPlant && blockMeta == 12)
			return true;
		
		if (block == ModBlocks.doubleHeightPlant && blockMeta == 5 || block == ModBlocks.doubleHeightPlant && blockMeta == 13)
			return true;

		else if (block.hasTileEntity(blockMeta))
			return false;

		return false;
	}

	@Override
	protected boolean isEntityReady() {
		return true;
	}

	@Override
	protected void moveToLocation() {
		EntityBlackAnt blackAnt = (EntityBlackAnt) entity;
		if (!blackAnt.isEating)
			if(entity.getNavigator().tryMoveToXYZ(cropX + 0.5D, cropY, cropZ + 0.5D, moveSpeed))
				entity.getMoveHelper().setMoveTo(cropX + 0.5D, cropY, cropZ + 0.5D, moveSpeed);
	}

	@Override
	protected void prepareToEat() {
		EntityBlackAnt blackAnt = (EntityBlackAnt) entity;
		blackAnt.setIsEating(true);
	}

	@Override
	protected void eatingInterupted() {
		EntityBlackAnt blackAnt = (EntityBlackAnt) entity;
		blackAnt.setIsEating(false);
	}

	@Override
	protected void afterEaten() {
		EntityBlackAnt blackAnt = (EntityBlackAnt) entity;
		blackAnt.setIsEating(false);
	}
}