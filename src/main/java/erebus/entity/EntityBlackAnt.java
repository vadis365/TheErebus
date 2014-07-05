package erebus.entity;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.entity.ai.EntityAIEatMushroom;

public class EntityBlackAnt extends EntityAnimal {
	private int shouldDo;
	public boolean isEating;
	public EntityBlackAnt(World world) {
		super(world);
		stepHeight = 1.0F;
		setSize(1.3F, 0.5F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIEatMushroom(this, 0.6D, 5));
		tasks.addTask(2, new EntityAIPanic(this, 0.8D));
		tasks.addTask(3, new EntityAILookIdle(this));
		tasks.addTask(4, new EntityAIWander(this, 0.6D));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:fireantsound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:fireanthurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected Item getDropItem() {
		return Items.magma_cream;
	}

	@Override
	protected void dropRareDrop(int fortune) {
		for (int i = 0; i < 1 + new Random().nextInt(1 + fortune); i++)
			dropItem(Items.magma_cream, 1);
	}

	public boolean isClimbing() {
		return !onGround && isOnLadder();
	}

	//@Override
	//public boolean isOnLadder() {
	//	return isCollidedHorizontally;
	//}

	@Override
	public int getMaxSpawnedInChunk() {
		return 5;
	}
	
	public void setIsEating(boolean isEating) {
		this.isEating = isEating;
	}

	@Override
	protected void attackEntity(Entity entity, float distance) {
		if (attackTime <= 0 && distance < 1.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY) {
			attackTime = 20;
			attackEntityAsMob(entity);
		} else if (distance >= 5.0F & distance <= 16.0F) {
			double distanceX = entity.posX - posX;
			double distanceY = entity.boundingBox.minY + entity.height / 2.0F - (posY + height / 2.0F);
			double distanceZ = entity.posZ - posZ;
			if (attackTime == 0) {
				++shouldDo;
				if (shouldDo <= 4)
					attackTime = 60;
				else {
					attackTime = 100;
					shouldDo = 0;
				}
				if (shouldDo > 1) {
					float targetAreaOffset = MathHelper.sqrt_float(distance) * 0.5F;
					worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1009, (int) posX, (int) posY, (int) posZ, 0);
					EntitySmallFireball fireball = new EntitySmallFireball(worldObj, this, distanceX + rand.nextGaussian() * targetAreaOffset, distanceY, distanceZ + rand.nextGaussian() * targetAreaOffset);
					fireball.posY = posY + height / 2.0F + 0.5D;
					worldObj.spawnEntityInWorld(fireball);
				}
			}
		}
	}

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		// TODO Auto-generated method stub
		return null;
	}
}
