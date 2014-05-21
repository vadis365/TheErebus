package erebus.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.item.ItemErebusMaterial.DATA;

public class EntityWoodlouse extends EntityCreature {

	public EntityWoodlouse(World world) {
		super(world);
		stepHeight = 0.0F;
		setSize(1.0F, 0.3F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAvoidEntity(this, EntityPlayer.class, 10.0F, 0.9D, 0.7D));
		tasks.addTask(2, new EntityAIAvoidEntity(this, EntityMob.class, 10.0F, 0.9D, 0.7D));
		tasks.addTask(3, new EntityAIWander(this, 0.7D));
		tasks.addTask(4, new EntityAIPanic(this, 0.9F));
		tasks.addTask(5, new EntityAILookIdle(this));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness(1.0F);
		if (light >= 0F)
			return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
		return super.getCanSpawnHere();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.7D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(15.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getHurtSound() {
		return "erebus:beetleHurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void playStepSound(int x, int y, int z, int blockID) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}
	
	@Override
	public boolean interact(EntityPlayer player) {
		if (worldObj.isRemote)
			return true;
		
		ItemStack is = player.inventory.getCurrentItem();
		if (is == null) {
			setDead();
			Utils.dropStack(worldObj, (int) posX, (int)  posY, (int)  posZ, new ItemStack(ModItems.woodlouseBall, 1));
			return true;
		} else
			return false;
	}
	
	@Override
	public void setDead() {
		super.setDead();
		if (worldObj.isRemote)
	        for (int i = 0; i < 7; ++i){
	            double velX = this.rand.nextGaussian() * 0.02D;
	            double velY = this.rand.nextGaussian() * 0.02D;
	            double velZ = this.rand.nextGaussian() * 0.02D;
	            this.worldObj.spawnParticle("smoke", this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, velX, velY, velZ);
	        }
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.equals(DamageSource.inWall) || source.equals(DamageSource.drown)||source instanceof EntityDamageSourceIndirect)
			return false;
		return super.attackEntityFrom(source, damage);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		entityDropItem(new ItemStack(ModItems.erebusMaterials, 1 + looting, DATA.whetstonePowder.ordinal()), 0F);
	}
}