package erebus.entity.ai;

import erebus.ModBlocks;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityLocust;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;

public class EntityAIEatCrops extends EntityAIEatBlock {

	private final double moveSpeed;
	private int reproCap = 0;

	public EntityAIEatCrops(EntityLiving entity, double moveSpeed, int eatSpeed) {
		super(entity, Blocks.wheat, 7, moveSpeed, eatSpeed);
		this.moveSpeed = moveSpeed;
	}

	@Override
	protected boolean canEatBlock(Block block, int blockMeta) {
		if (block == null)
			return false;
		else if (block == Blocks.tallgrass || block == ModBlocks.blockTurnip || block == Blocks.wheat)
			// for
			// time
			// being
			// block
			// ==
			// Blocks.double_plant
			return true;
		return false;
	}

	@Override
	public boolean continueExecuting() {
		return entity instanceof EntityAnimal ? !((EntityAnimal) entity).isInLove() && !entity.isChild() : super.continueExecuting();
	}

	@Override
	protected boolean isEntityReady() {
		return true;
	}

	@Override
	protected void moveToLocation() {
		EntityGrasshopper grasshopper = (EntityGrasshopper) entity;
		if (!grasshopper.isEating)
			if (!entity.getNavigator().tryMoveToXYZ(cropX + 0.5D, cropY, cropZ + 0.5D, moveSpeed))
				entity.getMoveHelper().setMoveTo(cropX + 0.5D, cropY, cropZ + 0.5D, moveSpeed);
	}

	@Override
	protected void prepareToEat() {
		EntityGrasshopper grasshopper = (EntityGrasshopper) entity;
		grasshopper.setIsEating(true);
	}

	@Override
	protected void eatingInterupted() {
		EntityGrasshopper grasshopper = (EntityGrasshopper) entity;
		grasshopper.setIsEating(false);
	}

	@Override
	protected void afterEaten() {
		EntityGrasshopper grasshopper = (EntityGrasshopper) entity;
		grasshopper.worldObj.setBlockToAir(cropX, cropY, cropZ);
		grasshopper.setIsEating(false);
		reproCap++;
		if (reproCap == 6)
			if (grasshopper.worldObj.countEntities(EntityGrasshopper.class) < 80) {
				EntityGrasshopper entityGrasshopper = new EntityGrasshopper(grasshopper.worldObj);
				entityGrasshopper.setPosition(cropX, cropY + 1, cropZ);
				grasshopper.worldObj.spawnEntityInWorld(entityGrasshopper);
			}
		if (reproCap >= 12) {
			if (grasshopper.worldObj.countEntities(EntityLocust.class) < 5) {
				grasshopper.setDead();
				EntityLocust entityLocust = new EntityLocust(grasshopper.worldObj);
				entityLocust.setPosition(cropX, cropY + 1, cropZ);
				grasshopper.worldObj.spawnEntityInWorld(entityLocust);
				grasshopper.worldObj.playSoundAtEntity(entityLocust, "erebus:locustspawn", 1.0F, 1.0F);
			}
		}
	}
}