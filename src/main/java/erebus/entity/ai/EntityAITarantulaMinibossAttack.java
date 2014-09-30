package erebus.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import erebus.entity.EntityTarantulaEgg;

public class EntityAITarantulaMinibossAttack extends EntityAIBase {
	World worldObj;
	EntityCreature attacker;
	int attackTick;
	double speedTowardsTarget;
	boolean longMemory;
	PathEntity entityPathEntity;
	Class classTarget;
	private int findAttemptCount;
	private int failedPathFindingPenalty;
	private int shouldDo;
	private boolean jumpAttack;

	public EntityAITarantulaMinibossAttack(EntityCreature entityCreature, Class entityClass, double moveSpeed, boolean memory) {
		this(entityCreature, moveSpeed, memory);
		classTarget = entityClass;
	}

	public EntityAITarantulaMinibossAttack(EntityCreature entityCreature, double moveSpeed, boolean memory) {
		attacker = entityCreature;
		worldObj = entityCreature.worldObj;
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
	public boolean continueExecuting() {
		EntityLivingBase entitylivingbase = attacker.getAttackTarget();
		return entitylivingbase == null ? false : !entitylivingbase.isEntityAlive() ? false : !longMemory ? !attacker.getNavigator().noPath() : attacker.isWithinHomeDistance(MathHelper.floor_double(entitylivingbase.posX), MathHelper.floor_double(entitylivingbase.posY), MathHelper.floor_double(entitylivingbase.posZ));
	}

	@Override
	public void startExecuting() {
		if(attacker.getHealth() <= 150 )
			attacker.getNavigator().setPath(entityPathEntity, speedTowardsTarget);
		else
			attacker.getNavigator().setPath(entityPathEntity, 0);
		findAttemptCount = 0;
	}

	@Override
	public void resetTask() {
		attacker.getNavigator().clearPathEntity();
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
				if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.xCoord, finalPathPoint.yCoord, finalPathPoint.zCoord) < 1D)
					failedPathFindingPenalty = 0;
				else
					failedPathFindingPenalty += 10;
			} else
				failedPathFindingPenalty += 10;
		}
		attackTick = Math.max(attackTick - 1, 0);
		double d0 = (double)(this.attacker.width * 1F * this.attacker.width * 1F + entitylivingbase.width);
		if (attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.boundingBox.minY, entitylivingbase.posZ) <= d0) {
			if (attackTick <= 0) {
				attackTick = 10;
				float Knockback = 0.5F;
				attacker.attackEntityAsMob(entitylivingbase);
				entitylivingbase.attackEntityFrom(DamageSource.causeMobDamage(attacker), 4.0F);
				entitylivingbase.addVelocity(-MathHelper.sin(attacker.rotationYaw * 3.141593F / 180.0F) * 0.5F, 0.1D, MathHelper.cos(attacker.rotationYaw * 3.141593F / 180.0F) * 0.5F);
				}
		} 
		if (attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.boundingBox.minY, entitylivingbase.posZ) > d0 + 9D && attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.boundingBox.minY, entitylivingbase.posZ) < d0 + 256.0D && attacker.getHealth() > 150 ){
			if (attackTick <= 0) {
				++shouldDo;
				if (shouldDo == 1)
					attackTick = 300;	
				else if (shouldDo <= 2)
					attackTick = 20;
				else {
					attackTick = 40;
					shouldDo = 0;
				}
				if (shouldDo == 1) {
					Vec3 look = attacker.getLookVec();
					double direction = Math.toRadians(attacker.renderYawOffset);
					EntityTarantulaEgg babyEgg = new EntityTarantulaEgg (worldObj, attacker);
					babyEgg.setPosition(attacker.posX + -Math.sin(direction) * 3.5D, attacker.posY + attacker.height, attacker.posZ + Math.cos(direction) * 3.5D);
					babyEgg.motionX = look.xCoord * 1.0;
					babyEgg.motionY = look.yCoord * 2.2;
					babyEgg.motionZ = look.zCoord * 1.0;
					worldObj.spawnEntityInWorld(babyEgg);
				}
			}
		}
	}
	}
