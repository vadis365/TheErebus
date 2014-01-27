package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.ModItems;

public class EntityUmberGolem extends EntityCreature implements IMob {

	public EntityUmberGolem(World world) {
		super(world);
		isImmuneToFire = true;
		setSize(1.0F, 1.0F);
		tasks.addTask(0, new EntityAITempt(this, 0.5D, ModItems.wandOfAnimation.itemID, false));
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityMob.class, 0.5D, false));
		tasks.addTask(3, new EntityAIWander(this, 0.5D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true));
		experienceValue = 0;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.5D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(75.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean canDespawn() {
		return false;
	}

	/*
	 * protected String getLivingSound() { return "erebus:umbergolemsound"; }
	 * 
	 * protected String getHurtSound() { return "erebus:umbergolemhurt"; }
	 */

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void playStepSound(int x, int y, int z, int blockID) {
		worldObj.playSoundAtEntity(this, "mob.zombie.step", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		entityDropItem(new ItemStack(Block.stone, 5, 0), 0.0F);
	}

	public boolean isClimbing() {
		return !onGround && isOnLadder();
	}

	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		return Attack(entity);
	}

	protected boolean Attack(Entity entity) {
		if (!worldObj.isRemote) {
			if (onGround) {
				double d0 = entity.posX - posX;
				double d1 = entity.posZ - posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				motionX = d0 / f2 * 0.5D * 0.800000011920929D + motionX * 0.20000000298023224D;
				motionZ = d1 / f2 * 0.5D * 0.800000011920929D + motionZ * 0.20000000298023224D;
				motionY = 0.4000000059604645D;
			}
			int Knockback = 1;
			entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F + 3);
			entity.addVelocity(-MathHelper.sin(rotationYaw * 3.141593F / 180.0F) * Knockback * 0.5F, 0.4D, MathHelper.cos(rotationYaw * 3.141593F / 180.0F) * Knockback * 0.5F);
			worldObj.playSoundAtEntity(entity, "damage.fallbig", 1.0F, 1.0F);
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, worldObj.difficultySetting * 50, 0));
			return true;
		}
		return true;
	}

	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack is = player.inventory.getCurrentItem();
		if (!worldObj.isRemote && is != null && is.itemID == ModItems.wandOfAnimation.itemID) {
			setDead();
			byte b0 = 0;
			int l1 = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
			if (l1 == 0)
				b0 = 2;

			if (l1 == 1)
				b0 = 5;

			if (l1 == 2)
				b0 = 3;

			if (l1 == 3)
				b0 = 4;
			worldObj.setBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY + 0.1D), MathHelper.floor_double(posZ), ModBlocks.umberGolemStatueID, b0, 3);
			worldObj.playSoundEffect(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), "erebus:altaroffering", 0.2F, 1.0F);
			return true;
		} else
			return false;
	}
}
