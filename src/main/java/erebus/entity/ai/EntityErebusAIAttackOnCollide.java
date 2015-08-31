package erebus.entity.ai;

import erebus.entity.EntityAntlionMiniBoss;
import erebus.entity.EntityPrayingMantis;
import erebus.entity.EntityVelvetWorm;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityErebusAIAttackOnCollide extends EntityAIBase {

	World worldObj;
	EntityCreature attacker;
	int attackTick;
	double speedTowardsTarget;
	boolean longMemory;
	PathEntity entityPathEntity;
	Class<?> classTarget;
	private int field_75445_i;
	private int failedPathFindingPenalty;

	public EntityErebusAIAttackOnCollide(EntityCreature entityCreature, Class<?> par2Class, double par3, boolean par5) {
		this(entityCreature, par3, par5);
		classTarget = par2Class;
	}

	public EntityErebusAIAttackOnCollide(EntityCreature entityCreature, double par2, boolean par4) {
		attacker = entityCreature;
		worldObj = entityCreature.worldObj;
		speedTowardsTarget = par2;
		longMemory = par4;
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
		else if (--field_75445_i <= 0) {
			entityPathEntity = attacker.getNavigator().getPathToEntityLiving(entitylivingbase);
			field_75445_i = 4 + attacker.getRNG().nextInt(7);
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
		attacker.getNavigator().setPath(entityPathEntity, speedTowardsTarget);
		field_75445_i = 0;
	}

	@Override
	public void resetTask() {
		attacker.getNavigator().clearPathEntity();
	}

	@Override
	public void updateTask() {
		EntityLivingBase entitylivingbase = attacker.getAttackTarget();
		attacker.getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
		if ((longMemory || attacker.getEntitySenses().canSee(entitylivingbase)) && --field_75445_i <= 0) {
			field_75445_i = failedPathFindingPenalty + 4 + attacker.getRNG().nextInt(7);
			attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, speedTowardsTarget);
			if (attacker.getNavigator().getPath() != null) {
				PathPoint finalPathPoint = attacker.getNavigator().getPath().getFinalPathPoint();
				if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.xCoord, finalPathPoint.yCoord, finalPathPoint.zCoord) < 1)
					failedPathFindingPenalty = 0;
				else
					failedPathFindingPenalty += 10;
			} else
				failedPathFindingPenalty += 10;
		}
		attackTick = Math.max(attackTick - 1, 0);
		double d0 = attacker.width * attacker.width + entitylivingbase.width;

		if (attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.boundingBox.minY, entitylivingbase.posZ) <= d0 + 2)
			if (attackTick <= 0) {
				attackTick = 10;
				if (attacker.getHeldItem() != null)
					attacker.swingItem();
				if (attacker instanceof EntityPrayingMantis)
					((EntityPrayingMantis) attacker).setAttackAnimation(0, (byte) 0);
				if (attacker instanceof EntityAntlionMiniBoss)
					entitylivingbase.addPotionEffect(new PotionEffect(Potion.weakness.id, 15 * 20, 0));
				if (attacker instanceof EntityVelvetWorm)
					((EntityVelvetWorm) attacker).setInflateSize(0);
				attacker.attackEntityAsMob(entitylivingbase);
			}
	}
}
