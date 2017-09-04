package erebus.entity.ai;

import erebus.entity.EntityAntlionMiniBoss;
import erebus.entity.EntityPrayingMantis;
import erebus.entity.EntityVelvetWorm;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityErebusAIAttackOnCollide extends EntityAIBase {

	World world;
	protected EntityCreature attacker;
	protected int attackTick;
	double speedTowardsTarget;
	boolean longMemory;
	Path entityPathEntity;
	private int delayCounter;
	private double targetX;
	private double targetY;
	private double targetZ;
	protected final int attackInterval = 20;
	private int failedPathFindingPenalty = 0;
	private boolean canPenalize = false;

	public EntityErebusAIAttackOnCollide(EntityCreature creature, double speedIn, boolean useLongMemory) {
		attacker = creature;
		world = creature.world;
		speedTowardsTarget = speedIn;
		longMemory = useLongMemory;
		setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		EntityLivingBase entitylivingbase = attacker.getAttackTarget();
		if (entitylivingbase == null)
			return false;
		else if (!entitylivingbase.isEntityAlive())
			return false;
		else if (canPenalize)
			if (--delayCounter <= 0) {
				entityPathEntity = attacker.getNavigator().getPathToEntityLiving(entitylivingbase);
				delayCounter = 4 + attacker.getRNG().nextInt(7);
				return entityPathEntity != null;
			} else
				return true;
		entityPathEntity = attacker.getNavigator().getPathToEntityLiving(entitylivingbase);
		if (entityPathEntity != null)
			return true;
		else
			return getAttackReachSqr(entitylivingbase) >= attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
	}

	@Override
	public boolean shouldContinueExecuting() {
		EntityLivingBase entitylivingbase = attacker.getAttackTarget();
		if (entitylivingbase == null)
			return false;
		 else if (!entitylivingbase.isEntityAlive())
			return false;
		else if (!longMemory)
			return !attacker.getNavigator().noPath();
		else if (!attacker.isWithinHomeDistanceFromPosition(new BlockPos(entitylivingbase)))
			return false;
		else
			return !(entitylivingbase instanceof EntityPlayer) || !((EntityPlayer) entitylivingbase).isSpectator() && !((EntityPlayer) entitylivingbase).isCreative();
	}

	@Override
	public void startExecuting() {
		attacker.getNavigator().setPath(entityPathEntity, speedTowardsTarget);
		delayCounter = 0;
	}

	@Override
	public void resetTask() {
		EntityLivingBase entitylivingbase = attacker.getAttackTarget();
		if (entitylivingbase instanceof EntityPlayer && (((EntityPlayer) entitylivingbase).isSpectator() || ((EntityPlayer) entitylivingbase).isCreative()))
			attacker.setAttackTarget((EntityLivingBase) null);
		attacker.getNavigator().clearPathEntity();
	}

	@Override
	public void updateTask() {
		EntityLivingBase entitylivingbase = attacker.getAttackTarget();
		attacker.getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
		double distance = attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
		--delayCounter;

		if ((longMemory || attacker.getEntitySenses().canSee(entitylivingbase)) && delayCounter <= 0 && (targetX == 0.0D && targetY == 0.0D && targetZ == 0.0D || entitylivingbase.getDistanceSq(targetX, targetY, targetZ) >= 1.0D || attacker.getRNG().nextFloat() < 0.05F)) {
			targetX = entitylivingbase.posX;
			targetY = entitylivingbase.getEntityBoundingBox().minY;
			targetZ = entitylivingbase.posZ;
			delayCounter = 4 + attacker.getRNG().nextInt(7);

			if (canPenalize) {
				delayCounter += failedPathFindingPenalty;
				if (attacker.getNavigator().getPath() != null) {
					PathPoint finalPathPoint = attacker.getNavigator().getPath().getFinalPathPoint();
					if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
						failedPathFindingPenalty = 0;
					else
						failedPathFindingPenalty += 10;
				} else
					failedPathFindingPenalty += 10;
			}

			if (distance > 1024.0D)
				delayCounter += 10;
			else if (distance > 256.0D)
				delayCounter += 5;

			if (!attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, speedTowardsTarget))
				delayCounter += 15;
		}

		attackTick = Math.max(attackTick - 1, 0);
		checkAndPerformAttack(entitylivingbase, distance);
	}

	protected void checkAndPerformAttack(EntityLivingBase entity, double distanceIn) {
		double distanceReach = getAttackReachSqr(entity);

		if (distanceIn <= distanceReach && attackTick <= 0) {
			attackTick = 20;
			attacker.swingArm(EnumHand.MAIN_HAND);
			//TODO remove this shit, delete the class, and just extend the vanilla melee attack in each mob
			if (attacker instanceof EntityPrayingMantis)
				((EntityPrayingMantis) attacker).setAttackAnimation(0, (byte) 0);
			if (attacker instanceof EntityAntlionMiniBoss)
				entity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 15 * 20, 0));
			if (attacker instanceof EntityVelvetWorm)
				((EntityVelvetWorm) attacker).setInflateSize(0);
			attacker.attackEntityAsMob(entity);
		}
	}

	protected double getAttackReachSqr(EntityLivingBase attackTarget) {
		return (double) (attacker.width * 2.0F * attacker.width * 2.0F + attackTarget.width);
	}
}
