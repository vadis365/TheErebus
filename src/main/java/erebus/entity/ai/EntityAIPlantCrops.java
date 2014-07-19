package erebus.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathEntity;
import erebus.entity.EntityBlackAnt;

public class EntityAIPlantCrops extends EntityAIEatBlock {

	private final double moveSpeed;
	public EntityAIPlantCrops(EntityLivingBase entity, double moveSpeed, int eatSpeed) {
		super((EntityLiving) entity, null, 0, null, moveSpeed, eatSpeed);
		this.moveSpeed = moveSpeed;
	}

	@Override
	public boolean shouldExecute() {
		EntityBlackAnt blackAnt = (EntityBlackAnt) entity;
		return blackAnt.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing") && !blackAnt.canCollectFromSilo;
	}
	
	@Override
	protected boolean canEatBlock(Block block, int blockMeta) {
		if (block == null)
			return false;

		if (block == Blocks.dirt || block == Blocks.grass)
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
		PathEntity pathentity = blackAnt.worldObj.getEntityPathToXYZ(blackAnt, cropX, cropY+1, cropZ, 16.0F, true, false, false, true);
		if (pathentity != null) {
			blackAnt.setPathToEntity(pathentity);
			blackAnt.getNavigator().setPath(pathentity, 0.5D);
		}
		if (blackAnt.getDistance(cropX, cropY, cropZ) < 1.5D)
			blackAnt.getMoveHelper().setMoveTo(cropX + 0.5D, cropY + 1, cropZ + 0.5D, 0.5D);
	}
	
	@Override
	protected void prepareToEat() {
	}

	@Override
	protected void eatingInterupted() {
	}

	@Override
	protected void afterEaten() {
		EntityBlackAnt blackAnt = (EntityBlackAnt) entity;
		blackAnt.worldObj.playSoundEffect((double)cropX + 0.5F, (double) cropY + 0.5F, (double) cropZ + 0.5F, Blocks.farmland.stepSound.getStepResourcePath(), (Blocks.farmland.stepSound.getVolume() + 1.0F) / 2.0F, Blocks.farmland.stepSound.getPitch() * 0.8F);
		blackAnt.worldObj.setBlock(cropX, cropY, cropZ, Blocks.farmland);
	}
}