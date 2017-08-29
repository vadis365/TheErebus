package erebus.entity;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.core.handler.configs.ConfigHandler;

public class EntityFireAnt extends EntityMob {
	private int shouldDo;

	public EntityFireAnt(World world) {
		super(world);
		stepHeight = 0.0F;
		isImmuneToFire = true;
		setSize(0.75F, 0.25F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 15D : 15D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 2D : 2D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
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

	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 5;
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
}
