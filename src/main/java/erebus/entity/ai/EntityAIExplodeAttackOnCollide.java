package erebus.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAIExplodeAttackOnCollide extends EntityAIBase {

	private World worldObj;
	private EntityCreature attacker;
	private int attackTick;
	private double speedTowardsTarget;
	private boolean longMemory;
	private PathEntity entityPathEntity;
	private Class<? extends Entity> classTarget;
	private int field_75445_i;
	private int failedPathFindingPenalty;

	public EntityAIExplodeAttackOnCollide(EntityCreature entityCreature, Class<? extends Entity> par2Class, double par3, boolean par5) {
		this(entityCreature, par3, par5);
		classTarget = par2Class;
	}

	public EntityAIExplodeAttackOnCollide(EntityCreature entityCreature, double par2, boolean par4) {
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
		if (attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.boundingBox.minY, entitylivingbase.posZ) <= d0)
			if (attackTick <= 0) {
				attackTick = 20;
				if (attacker.getHeldItem() != null)
					attacker.swingItem();
				attacker.attackEntityAsMob(entitylivingbase);
				boolean rule = worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
				if (rule)
					worldObj.createExplosion(attacker, entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0F, rule);
			}
	}
}
