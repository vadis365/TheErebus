package erebus.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public abstract class EntityMobBlock extends EntityCreature implements IMob {
	public EntityMobBlock(World world) {
		super(world);
		experienceValue = 5;
	}

	/**
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate() {
		updateArmSwingProgress();
		float f = getBrightness(1.0F);

		if (f > 0.5F)
			entityAge += 2;

		super.onLivingUpdate();
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!worldObj.isRemote && worldObj.difficultySetting == 0)
			setDead();
	}

	/**
	 * Finds the closest player within 16 blocks to attack, or null if this
	 * Entity isn't interested in attacking (Animals, Spiders at day, peaceful
	 * PigZombies).
	 */
	@Override
	protected Entity findPlayerToAttack() {
		EntityPlayer entityplayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return entityplayer != null && canEntityBeSeen(entityplayer) ? entityplayer : null;
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float par2) {
		if (isEntityInvulnerable())
			return false;
		else if (super.attackEntityFrom(source, par2)) {
			Entity entity = source.getEntity();

			if (riddenByEntity != entity && ridingEntity != entity) {
				if (entity != this)
					entityToAttack = entity;

				return true;
			} else
				return true;
		} else
			return false;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		float f = (float) getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
		int i = 0;

		if (entity instanceof EntityLivingBase) {
			f += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase) entity);
			i += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase) entity);
		}

		boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), f);

		if (flag) {
			if (i > 0) {
				entity.addVelocity(-MathHelper.sin(rotationYaw * (float) Math.PI / 180.0F) * i * 0.5F, 0.1D, MathHelper.cos(rotationYaw * (float) Math.PI / 180.0F) * i * 0.5F);
				motionX *= 0.6D;
				motionZ *= 0.6D;
			}

			int j = EnchantmentHelper.getFireAspectModifier(this);

			if (j > 0)
				entity.setFire(j * 4);

			if (entity instanceof EntityLivingBase)
				EnchantmentThorns.func_92096_a(this, (EntityLivingBase) entity, rand);
		}

		return flag;
	}

	/**
	 * Basic mob attack. Default to touch of death in EntityCreature. Overridden
	 * by each mob to define their attack.
	 */
	@Override
	protected void attackEntity(Entity entity, float par2) {
		if (attackTime <= 0 && par2 < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY) {
			attackTime = 20;
			attackEntityAsMob(entity);
		}
	}

	/**
	 * Takes a coordinate in and returns a weight to determine how likely this
	 * creature will try to path to the block. Args: x, y, z
	 */
	@Override
	public float getBlockPathWeight(int par1, int par2, int par3) {
		return 0.5F - worldObj.getLightBrightness(par1, par2, par3);
	}

	/**
	 * Checks to make sure the light is not too bright where the mob is spawning
	 */
	protected boolean isValidLightLevel() {
		int i = MathHelper.floor_double(posX);
		int j = MathHelper.floor_double(boundingBox.minY);
		int k = MathHelper.floor_double(posZ);

		if (worldObj.getSavedLightValue(EnumSkyBlock.Sky, i, j, k) > rand.nextInt(32))
			return false;
		else {
			int l = worldObj.getBlockLightValue(i, j, k);

			if (worldObj.isThundering()) {
				int i1 = worldObj.skylightSubtracted;
				worldObj.skylightSubtracted = 10;
				l = worldObj.getBlockLightValue(i, j, k);
				worldObj.skylightSubtracted = i1;
			}

			return l <= rand.nextInt(8);
		}
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this
	 * entity.
	 */
	@Override
	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting > 0 && isValidLightLevel() && super.getCanSpawnHere();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getAttributeMap().func_111150_b(SharedMonsterAttributes.attackDamage);
	}
}
