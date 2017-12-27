package erebus.entity.ai;

import java.util.List;

import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.EntityPoisonJet;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityTarantulaBaby;
import erebus.entity.EntityTarantulaEgg;
import erebus.entity.EntityTarantulaMiniboss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.MobEffects;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

public class EntityAITarantulaMinibossAttack extends EntityAIBase {

	EntityCreature attacker;
	int attackTick;
	double speedTowardsTarget;
	boolean longMemory;
	Path entityPathEntity;
	Class<? extends Entity> classTarget;
	private int findAttemptCount;
	private int failedPathFindingPenalty;
	private int shouldDo;
	private boolean jumpAttack;

	public EntityAITarantulaMinibossAttack(EntityCreature entityCreature, Class<? extends Entity> entityClass, double moveSpeed, boolean memory) {
		this(entityCreature, moveSpeed, memory);
		classTarget = entityClass;
	}

	public EntityAITarantulaMinibossAttack(EntityCreature entityCreature, double moveSpeed, boolean memory) {
		attacker = entityCreature;
		speedTowardsTarget = moveSpeed;
		longMemory = memory;
		setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		EntityLivingBase entitylivingbase = attacker.getAttackTarget();
		if (entitylivingbase == null)
			return false;
		else if (!entitylivingbase.isEntityAlive())
			return false;
		else if (classTarget != null && !classTarget.isAssignableFrom(entitylivingbase.getClass()))
			return false;
		else if (--findAttemptCount <= 0) {
			entityPathEntity = attacker.getNavigator().getPathToEntityLiving(entitylivingbase);
			findAttemptCount = 4 + attacker.getRNG().nextInt(7);
			return entityPathEntity != null;
		} else
			return true;
	}

	@Override
	public boolean shouldContinueExecuting() {
		EntityLivingBase entitylivingbase = attacker.getAttackTarget();
		return entitylivingbase == null ? false : !entitylivingbase.isEntityAlive() ? false : !longMemory ? !attacker.getNavigator().noPath() : attacker.isWithinHomeDistanceCurrentPosition();
	}

	@Override
	public void startExecuting() {
		if (attacker.getHealth() <= 150)
			attacker.getNavigator().setPath(entityPathEntity, speedTowardsTarget);
		else
			attacker.getNavigator().setPath(entityPathEntity, 0);
		findAttemptCount = 0;
	}

	@Override
	public void resetTask() {
		attacker.getNavigator().clearPath();
	}

	@Override
	public void updateTask() {
		EntityLivingBase entitylivingbase = attacker.getAttackTarget();
		attacker.getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
		if ((longMemory && attacker.getHealth() <= 150 || attacker.getEntitySenses().canSee(entitylivingbase)) && --findAttemptCount <= 0 && attacker.getHealth() <= 150) {
			findAttemptCount = failedPathFindingPenalty + 4 + attacker.getRNG().nextInt(7);
			attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, speedTowardsTarget);
			if (attacker.getNavigator().getPath() != null) {
				PathPoint finalPathPoint = attacker.getNavigator().getPath().getFinalPathPoint();
				if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1D)
					failedPathFindingPenalty = 0;
				else
					failedPathFindingPenalty += 10;
			} else
				failedPathFindingPenalty += 10;
		}
		attackTick = Math.max(attackTick - 1, 0);
		double d0 = attacker.width * 1F * attacker.width * 1F + entitylivingbase.width;
		if (attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ) <= d0)
			if (attackTick <= 0) {
				attackTick = 10;
				attacker.attackEntityAsMob(entitylivingbase);
				entitylivingbase.attackEntityFrom(DamageSource.causeMobDamage(attacker), (float) (ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 4D : 4D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier));
				entitylivingbase.addVelocity(-MathHelper.sin(attacker.rotationYaw * 3.141593F / 180.0F) * 0.5F, 0.1D, MathHelper.cos(attacker.rotationYaw * 3.141593F / 180.0F) * 0.5F);
			}

		if (attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ) > d0 + 1D && attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ) < d0 + 256.0D && attacker.getHealth() <= attacker.getMaxHealth() / 2)
			if (attackTick <= 0) {
				++shouldDo;
				if (shouldDo == 1)
					attackTick = 40;
				else if (shouldDo <= 2)
					attackTick = 20;
				else {
					attackTick = 20;
					shouldDo = 0;
				}
				if (shouldDo == 1) {
					double direction = Math.toRadians(attacker.renderYawOffset);
					double targetX = entitylivingbase.posX - attacker.posX;
					double targetY = entitylivingbase.getEntityBoundingBox().minY + (double) (entitylivingbase.height) - (attacker.posY + (double) (attacker.height));
					double targetZ = entitylivingbase.posZ - attacker.posZ;
					EntityPoisonJet jet = new EntityPoisonJet(attacker.getEntityWorld(), attacker);
					jet.setPosition(attacker.posX + -Math.sin(direction) * 3.5D, attacker.posY + attacker.height * 0.5, attacker.posZ + Math.cos(direction) * 3.5D);
					jet.shoot(targetX, targetY, targetZ, 1.0F, 0.0F);
					attacker.getEntityWorld().spawnEntity(jet);
				}
			}

		if (attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ) > d0 + 9D && attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ) < d0 + 256.0D && attacker.getHealth() > attacker.getMaxHealth() / 2) {
			if (attackTick <= 0) {
				++shouldDo;
				if (shouldDo == 1)
					attackTick = 200;
				else if (shouldDo <= 2)
					attackTick = 20;
				else {
					attackTick = 40;
					shouldDo = 0;
				}
				if (shouldDo == 1) {
					double direction = Math.toRadians(attacker.renderYawOffset);
					double targetX = entitylivingbase.posX - attacker.posX;
					double targetY = entitylivingbase.getEntityBoundingBox().minY + (double) (entitylivingbase.height) - (attacker.posY + (double) (attacker.height));
					double targetZ = entitylivingbase.posZ - attacker.posZ;
					EntityTarantulaEgg babyEgg = new EntityTarantulaEgg(attacker.getEntityWorld(), attacker);
					babyEgg.setPosition(attacker.posX - Math.sin(direction) * 3.5, attacker.posY + attacker.height, attacker.posZ + Math.cos(direction) * 3.5);
					babyEgg.shoot(targetX, targetY, targetZ, 0.7F, 0.0F);
					attacker.getEntityWorld().spawnEntity(babyEgg);
				}

				if (attacker.getEntityWorld().rand.nextInt(3) == 1) {
					attackTick = 30;
					attacker.motionY = 0.61999998688697815D;
					jumpAttack = true;
				}
			}
			if (jumpAttack && attacker.motionY == -0.0784000015258789D) {
				areaOfEffect();
				((EntityTarantulaMiniboss) attacker).spawnBlamParticles();
				jumpAttack = false;
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected Entity areaOfEffect() {
		List<EntityLivingBase> list = attacker.getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(attacker.getEntityBoundingBox().minX, attacker.getEntityBoundingBox().minY, attacker.getEntityBoundingBox().minZ, attacker.getEntityBoundingBox().maxX, attacker.getEntityBoundingBox().maxY, attacker.getEntityBoundingBox().maxZ).grow(8D, 1D, 8D));
		for (int i = 0; i < list.size(); i++) {
			Entity entity = list.get(i);
			if (entity != null)
				if (entity instanceof EntityLivingBase && !(entity instanceof EntityTarantulaMiniboss) && !(entity instanceof EntityTarantula) && !(entity instanceof EntityTarantulaBaby)) {
					float Knockback = 2;
					entity.attackEntityFrom(DamageSource.causeMobDamage(attacker), (float) (ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 8D : 8D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier));
					entity.addVelocity(-MathHelper.sin(attacker.rotationYaw * 3.141593F / 180.0F) * Knockback * 0.5F, 0.4D, MathHelper.cos(attacker.rotationYaw * 3.141593F / 180.0F) * Knockback * 0.5F);
					attacker.getEntityWorld().playSound(null, attacker.getPosition(), ModSounds.BLAM_SOUND, SoundCategory.HOSTILE, 1.5F, 1.0F);
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 8 * 20, 0));
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 5 * 20, 0));
				}
		}
		return null;
	}
}
