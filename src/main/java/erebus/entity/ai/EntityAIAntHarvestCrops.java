package erebus.entity.ai;

import erebus.entity.EntityBlackAnt;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathEntity;

public class EntityAIAntHarvestCrops extends EntityAIAntsBlock {

	EntityBlackAnt blackAnt = (EntityBlackAnt) entity;
	private Block blockMunched;
	private int metaData;

	public EntityAIAntHarvestCrops(EntityLivingBase entity, double moveSpeed, int eatSpeed) {
		super((EntityLiving) entity, null, 0, null, moveSpeed, eatSpeed);
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
		PathEntity pathentity = blackAnt.worldObj.getEntityPathToXYZ(blackAnt, cropX, cropY, cropZ, 16.0F, true, false, false, true);

		if (pathentity != null) {
			blackAnt.setPathToEntity(pathentity);
			blackAnt.getNavigator().setPath(pathentity, 0.5D);
		}

		if (blackAnt.getDistance(cropX, cropY, cropZ) < 1.5D)
			blackAnt.getMoveHelper().setMoveTo(cropX + 0.5D, cropY, cropZ + 0.5D, 0.5D);
	}

	@Override
	protected void prepareToEat() {
		blockMunched = getTargetBlock();
		metaData = blackAnt.worldObj.getBlockMetadata(cropX, cropY, cropZ);
	}

	@Override
	protected void eatingInterupted() {
	}

	@Override
	protected void afterEaten() {
		blackAnt.worldObj.setBlockToAir(cropX, cropY, cropZ);
		blackAnt.worldObj.setBlock(cropX, cropY - 1, cropZ, Blocks.dirt);
		blackAnt.setBlockHarvested(blockMunched, metaData);
	}
}