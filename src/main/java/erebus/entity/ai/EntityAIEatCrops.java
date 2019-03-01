package erebus.entity.ai;

import erebus.ModBlocks;
import erebus.ModSounds;
import erebus.blocks.BlockSmallPlant;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityLocust;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class EntityAIEatCrops extends EntityAIEatBlock {

	private final double moveSpeed;
	private int reproCap = 0;

	public EntityAIEatCrops(EntityLiving entity, double moveSpeed, int eatSpeed) {
		super(entity, null, 0, moveSpeed, eatSpeed);
		setMutexBits(1);
		this.moveSpeed = moveSpeed;
	}

	@Override
	protected boolean canEatBlock(IBlockState state) {
		Block block = state.getBlock();
		if (block == null)
			return false;
		else if (block == Blocks.TALLGRASS || state == ModBlocks.SMALL_PLANT.getDefaultState().withProperty(BlockSmallPlant.PLANT_TYPE, BlockSmallPlant.EnumSmallPlantType.FERN) || block instanceof BlockCrops && ((BlockCrops) block).isMaxAge(state))
			return true;
		return false;
	}

	@Override
	public boolean shouldContinueExecuting() {
		return entity instanceof EntityAnimal ? !((EntityAnimal) entity).isInLove() && !entity.isChild() : super.shouldContinueExecuting();
	}

	@Override
	protected boolean isEntityReady() {
		return true;
	}
	
	@Override
	public boolean shouldExecute() {
		EntityGrasshopper grasshopper = (EntityGrasshopper) entity;
		return !grasshopper.getMoveHelper().isUpdating() && super.shouldExecute();
		//return !entity.getNavigator().noPath() && super.shouldExecute();
	}

	@Override
	protected void moveToLocation() {
		EntityGrasshopper grasshopper = (EntityGrasshopper) entity;
		if (!grasshopper.isEating)
			grasshopper.getNavigator().tryMoveToXYZ(cropX + 0.5D, cropY + 0.5D, cropZ + 0.5D, moveSpeed);
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
		grasshopper.getNavigator().clearPath();
	}

	@Override
	protected void afterEaten() {
		EntityGrasshopper grasshopper = (EntityGrasshopper) entity;
		grasshopper.getEntityWorld().setBlockToAir(new BlockPos(cropX, cropY, cropZ));
		grasshopper.setIsEating(false);
		reproCap++;
		if (reproCap == 6)
			if (grasshopper.getEntityWorld().countEntities(EntityGrasshopper.class) < 80) {
				EntityGrasshopper entityGrasshopper = new EntityGrasshopper(grasshopper.getEntityWorld());
				entityGrasshopper.setPosition(cropX, cropY + 1, cropZ);
				grasshopper.getEntityWorld().spawnEntity(entityGrasshopper);
				grasshopper.getNavigator().clearPath();
			}
		if (reproCap >= 12) {
			if (grasshopper.getEntityWorld().countEntities(EntityLocust.class) < 5) {
				grasshopper.setDead();
				EntityLocust entityLocust = new EntityLocust(grasshopper.getEntityWorld());
				entityLocust.setPosition(cropX, cropY + 1, cropZ);
				grasshopper.getEntityWorld().spawnEntity(entityLocust);
				grasshopper.getEntityWorld().playSound((EntityPlayer)null, entityLocust.getPosition(), ModSounds.LOCUST_SPAWN, SoundCategory.HOSTILE, 1.0F, 1.0F);
			}
		}
	}
}