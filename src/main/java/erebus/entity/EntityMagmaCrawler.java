package erebus.entity;

import erebus.ModBlocks;
import erebus.item.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityMagmaCrawler extends EntityMob {
	private int shouldDo;
	public boolean upAbove;

	public EntityMagmaCrawler(World world) {
		super(world);
		isImmuneToFire = true;
		setSize(1.25F, 1.0F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
		upAbove = true;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:magmacrawler";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:magmacrawlerhurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:magmacrawlerdeath";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		entityDropItem(ItemMaterials.DATA.magmaCrawlerEye.makeStack(), 0.0F);
	}

	public boolean isOnCeiling() {
		return upAbove && worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY + 1.5), MathHelper.floor_double(posZ)) == ModBlocks.gneiss && worldObj.isAirBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY - 0.5), MathHelper.floor_double(posZ));
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 5;
	}

	@Override
	protected void fall(float par1) {
	}

	@Override
	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox) && worldObj.isAirBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) && worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY) + 1, MathHelper.floor_double(posZ)) == ModBlocks.gneiss;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			if (upAbove && worldObj.isAirBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY) + 1, MathHelper.floor_double(posZ)) && worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY) + 2, MathHelper.floor_double(posZ)) == ModBlocks.gneiss) {
				motionY += 0.1D;
				if (getEntityToAttack() != null && upAbove) {
					double distance = getDistance(getEntityToAttack().posX, getEntityToAttack().boundingBox.maxY, getEntityToAttack().posZ);
					if (distance < 2.0D) {
						motionY += 0D;
						upAbove = false;
					}
				}
			}
			if (onGround && upAbove || isCollidedHorizontally && upAbove || getHealth() < getMaxHealth() && upAbove)
				upAbove = false;
		}
	}

	@Override
	public void onLivingUpdate() {
		if (!worldObj.isRemote && upAbove)
			if (getEntityToAttack() != null) {
				double var1 = getEntityToAttack().posX + 0.5D - posX;
				double var5 = getEntityToAttack().posZ + 0.5D - posZ;
				motionX += (Math.signum(var1) * 0.5D - motionX) * 0.050000000149011612D;
				motionZ += (Math.signum(var5) * 0.5D - motionZ) * 0.050000000149011612D;
				float var7 = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
				float var8 = MathHelper.wrapAngleTo180_float(var7 - rotationYaw);
				moveForward = 0.5F;
				rotationYaw += var8;
			}
		super.onLivingUpdate();
	}

	@Override
	protected void attackEntity(Entity entity, float distance) {
		if (attackTime <= 0 && distance < 2.0F) {
			attackTime = 20;
			super.attackEntityAsMob(entity);
		}
		if (distance > 1.0F && distance < 5.0F && rand.nextInt(10) == 0) {
			if (onGround) {
				double d0 = entity.posX - posX;
				double d1 = entity.posZ - posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				motionX = d0 / f2 * 0.5D * 0.900000011920929D + motionX * 0.70000000298023224D;
				motionZ = d1 / f2 * 0.5D * 0.900000011920929D + motionZ * 0.70000000298023224D;
				motionY = 0.3000000059604645D;
			}
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
					fireball.posY = posY + height / 2.0F;
					worldObj.spawnEntityInWorld(fireball);
				}
			}
		}
	}
}
