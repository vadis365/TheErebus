package erebus.entity;

import erebus.ModItems;
import erebus.entity.ai.EntityAIEatCrops;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGrasshopper extends EntityCreature {

	private ChunkCoordinates currentJumpTarget;
	public boolean isEating;

	public EntityGrasshopper(World world) {
		super(world);
		stepHeight = 1.0F;
		jumpMovementFactor = 0.1F;
		setSize(1.3F, 0.5F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIEatCrops(this, 0.6D, 20));
		tasks.addTask(2, new EntityAIPanic(this, 0.8D));
		tasks.addTask(3, new EntityAILookIdle(this));
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness(1.0F);
		if (light >= 0F)
			return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
		return super.getCanSpawnHere();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 3;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:grasshoppersound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:grasshopperhurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) { // playStepSound
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			if (isBurning())
				entityDropItem(new ItemStack(ModItems.food, 1, 3), 0.0F);
			else
				entityDropItem(new ItemStack(ModItems.food, 1, 2), 0.0F);
	}

	public boolean randJump() {
		return rand.nextInt(50) == 0;
	}

	@Override
	protected void jump() {
		currentJumpTarget = new ChunkCoordinates((int) posX + rand.nextInt(3) - rand.nextInt(3), (int) posY, (int) posZ + rand.nextInt(3) - rand.nextInt(3));
		motionX += (Math.signum(currentJumpTarget.posX + 0.5D - posX) * 0.5D - motionX) * 0.60000000149011612D;
		motionZ += (Math.signum(currentJumpTarget.posZ + 0.5D - posZ) * 0.5D - motionZ) * 0.60000000149011612D;
		motionY = 0.61999998688697815D;
		float direction = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
		float rotation = MathHelper.wrapAngleTo180_float(direction - rotationYaw);
		rotationYaw += rotation;
		setPositionAndUpdate(posX, posY, posZ);
	}

	public void setIsEating(boolean isEating) {
		this.isEating = isEating;
	}

	@Override
	protected void fall(float par1) {
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote && onGround && randJump() && !isEating)
			jump();
	}
}