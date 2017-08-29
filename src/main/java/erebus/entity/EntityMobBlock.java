package erebus.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public abstract class EntityMobBlock extends EntityCreature implements IMob {

	public EntityMobBlock(World world) {
		super(world);
	}

	@Override
	public void onLivingUpdate() {
		updateArmSwingProgress();
		super.onLivingUpdate();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (isEntityInvulnerable())
			return false;
		else if (super.attackEntityFrom(source, damage)) {
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
		float damage = (float) getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
		int knockback = 0;

		if (entity instanceof EntityLivingBase) {
			damage += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase) entity);
			knockback += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase) entity);
		}

		boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);

		if (flag) {
			if (knockback > 0) {
				entity.addVelocity(-MathHelper.sin(rotationYaw * (float) Math.PI / 180.0F) * knockback * 0.5F, 0.1D, MathHelper.cos(rotationYaw * (float) Math.PI / 180.0F) * knockback * 0.5F);
				motionX *= 0.6D;
				motionZ *= 0.6D;
			}

			if (entity instanceof EntityLivingBase)
				EnchantmentHelper.func_151384_a((EntityLivingBase) entity, this);

			EnchantmentHelper.func_151385_b(this, entity);
		}

		return flag;
	}

	@Override
	protected void attackEntity(Entity entity, float distance) {
		if (attackTime <= 0 && distance < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY) {
			attackTime = 20;
			attackEntityAsMob(entity);
		}
	}

	@Override
	public float getBlockPathWeight(int x, int y, int z) {
		return 0.5F - worldObj.getLightBrightness(x, y, z);
	}

	@Override
	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting.ordinal() > EnumDifficulty.PEACEFUL.ordinal() && super.getCanSpawnHere();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
	}
}