package erebus.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import erebus.ModBlocks;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityLocust;

public class EntityAIEatCrops extends EntityAIEatBlock {

	private double moveSpeed;
	private int reproCap = 0;
	
	public EntityAIEatCrops(EntityLiving entity, double moveSpeed, int eatSpeed) {
		super(entity, Block.crops, 7, new ItemStack(Item.seeds), moveSpeed, eatSpeed);
		this.moveSpeed=moveSpeed;
	}
	
	@Override
	protected boolean canEatBlock(int blockID, int blockMeta) {
		
		if (blockID == 0)
			return false;

		else if (blockID == Block.tallGrass.blockID || blockID == ModBlocks.erebusGrass.blockID || blockID == ModBlocks.blockTurnip.blockID || blockID == Block.crops.blockID)
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
		if (!grasshopper.isEating){
			if(!entity.getNavigator().tryMoveToXYZ(cropX+ 0.5D, cropY, cropZ+ 0.5D, moveSpeed))
				entity.getMoveHelper().setMoveTo(cropX + 0.5D, cropY, cropZ + 0.5D, moveSpeed);
		}
	}

	@Override
	protected void prepareToEat() {
		EntityGrasshopper grasshopper = (EntityGrasshopper) entity;
		grasshopper.setCanJump(false);
		grasshopper.setMoveTasks(false);
		grasshopper.setIsEating(true);	
	}

	@Override
	protected void eatingInterupted() {
		EntityGrasshopper grasshopper = (EntityGrasshopper) entity;
		grasshopper.setCanJump(true);
		grasshopper.setMoveTasks(true);
		grasshopper.setIsEating(false);	
	}
	
	@Override
	protected void afterEaten() {
		EntityGrasshopper grasshopper = (EntityGrasshopper) entity;
		grasshopper.setCanJump(true);
		grasshopper.setMoveTasks(true);
		grasshopper.setIsEating(false);
		reproCap++;
		System.out.println("Repro Number: "+reproCap);
		if (reproCap == 6)
			if (grasshopper.worldObj.countEntities(EntityGrasshopper.class) < 80) {
				EntityGrasshopper entityGrasshopper = new EntityGrasshopper(grasshopper.worldObj);
				entityGrasshopper.setPosition(cropX, cropY + 1, cropZ);
				grasshopper.worldObj.spawnEntityInWorld(entityGrasshopper);
			}
		if (reproCap == 12) {
			grasshopper.setDead();
			EntityLocust entityLocust = new EntityLocust(grasshopper.worldObj);
			entityLocust.setPosition(cropX, cropY + 1, cropZ);
			grasshopper.worldObj.spawnEntityInWorld(entityLocust);
			grasshopper.worldObj.playSoundAtEntity(entityLocust, "erebus:locustspawn", 1.0F, 1.0F);
		}
	}
}