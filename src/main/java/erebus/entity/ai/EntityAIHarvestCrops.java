package erebus.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import erebus.entity.EntityBlackAnt;

public class EntityAIHarvestCrops extends EntityAIEatBlock {

	private final double moveSpeed;
	private Block blockMunched;
	private int metaData;
	public EntityAIHarvestCrops(EntityLivingBase entity, double moveSpeed, int eatSpeed) {
		super((EntityLiving) entity, null, 0, null, moveSpeed, eatSpeed);
		this.moveSpeed = moveSpeed;
	}

	@Override
	protected boolean canEatBlock(Block block, int blockMeta) {
		if (block == null)
			return false;

		if (block instanceof BlockCrops && blockMeta >= 7)
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
		blockMunched = getTargetBlock();
		metaData = entity.worldObj.getBlockMetadata(cropX, cropY, cropZ);	
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
		blackAnt.setBlockHarvested(blockMunched, metaData);
	}
}