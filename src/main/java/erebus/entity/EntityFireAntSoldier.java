package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFireAntSoldier extends EntityMob {
	private int shouldDo;

	public EntityFireAntSoldier(World world) {
		super(world);
		this.stepHeight = 0.1F;
		this.isImmuneToFire = true;
		this.setSize(0.9F, 0.5F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
	}

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:FireantSound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:FireantHurt";
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
	protected void dropFewItems(boolean recentlyHit, int looting) {
		entityDropItem(new ItemStack(Items.magma_cream, rand.nextInt(1) + 1 + looting, 0), 0.0F);
		if (rand.nextInt(5) == 0)
			entityDropItem(new ItemStack(Items.blaze_powder, rand.nextInt(1) + 1 + looting, 0), 0.0F);
	}

	public boolean isClimbing() {
		return (!this.onGround) && (isOnLadder());
	}

	public boolean isOnLadder() {
		return (this.isCollidedHorizontally);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 5;
	}

	@Override
	protected void attackEntity(Entity entity, float distance) {
		if (attackTime <= 0 && distance < 1.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY) {
			this.setFire(1);
			entity.setFire(10);
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
					EntityLargeFireball fireball = new EntityLargeFireball(worldObj, this, distanceX + rand.nextGaussian() * targetAreaOffset, distanceY, distanceZ + rand.nextGaussian() * targetAreaOffset);
					fireball.posY = posY + height / 2.0F + 0.5D;
					worldObj.spawnEntityInWorld(fireball);
				}
			}
		}
	}
}
