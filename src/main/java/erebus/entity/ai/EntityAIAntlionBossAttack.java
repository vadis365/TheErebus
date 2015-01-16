package erebus.entity.ai;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import erebus.entity.EntityAntlionBoss;
import erebus.entity.EntityThrownSand;

public class EntityAIAntlionBossAttack extends EntityAIBase {
	World worldObj;
	EntityCreature attacker;
	int attackTick;
	double speedTowardsTarget;
	boolean longMemory;
	PathEntity entityPathEntity;
	Class classTarget;
	private int field_75445_i;
	private int failedPathFindingPenalty;
	private int shouldDo;
	private boolean jumpAttack;

	public EntityAIAntlionBossAttack(EntityCreature par1EntityCreature, Class par2Class, double par3, boolean par5) {
		this(par1EntityCreature, par3, par5);
		classTarget = par2Class;
	}

	public EntityAIAntlionBossAttack(EntityCreature par1EntityCreature, double par2, boolean par4) {
		attacker = par1EntityCreature;
		worldObj = par1EntityCreature.worldObj;
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
		return entitylivingbase == null ? false : !entitylivingbase
				.isEntityAlive() ? false : !longMemory ? !attacker
				.getNavigator().noPath() : attacker.isWithinHomeDistance(
				MathHelper.floor_double(entitylivingbase.posX),
				MathHelper.floor_double(entitylivingbase.posY),
				MathHelper.floor_double(entitylivingbase.posZ));
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
				worldObj.playSoundAtEntity(attacker, "antlionboss:antliongrowl", 1.0F, 1.0F);
				float Knockback = 1;
				attacker.attackEntityAsMob(entitylivingbase);
				entitylivingbase.attackEntityFrom(DamageSource.causeMobDamage(attacker), 8.0F);
				entitylivingbase.addVelocity(-MathHelper.sin(attacker.rotationYaw * 3.141593F / 180.0F) * 0.5F, 0.1D, MathHelper.cos(attacker.rotationYaw * 3.141593F / 180.0F) * 0.5F);
				}
		}
		if (attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.boundingBox.minY, entitylivingbase.posZ) > d0 + 64D && attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.boundingBox.minY, entitylivingbase.posZ) < d0 + 1024.0D){
			if (attackTick <= 0) {
				++shouldDo;
				if (shouldDo == 1)
					attackTick = 30;
				else if (shouldDo <= 4)
					attackTick = 10;
				else {
					attackTick = 20;
					shouldDo = 0;
				}
				if (shouldDo > 1) {
					Vec3 look = attacker.getLookVec();
					double direction = Math.toRadians(attacker.renderYawOffset);
					EntityThrownSand var11 = new EntityThrownSand(worldObj, attacker);
					var11.setPosition(attacker.posX + -Math.sin(direction) * 3.5, attacker.posY+ attacker.height, attacker.posZ + Math.cos(direction) * 3.5);
					var11.motionX = look.xCoord * 2.0;
					var11.motionY = look.yCoord * 2.2;
					var11.motionZ = look.zCoord * 2.0;
					worldObj.spawnEntityInWorld(var11);
				}
			}
		}
		
		if (attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.boundingBox.minY, entitylivingbase.posZ) <= d0 + 64D && attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.boundingBox.minY, entitylivingbase.posZ) > d0 + 25.0D)
			if (attackTick <= 0) {
				int x= worldObj.rand.nextInt(3);
				if(x ==0){
					attackTick = 30;
				attacker.motionY = 0.61999998688697815D;
				jumpAttack=true;
				}
				else if(x ==1 && !jumpAttack && attacker.onGround) {
					attackTick = 65;
				((EntityAntlionBoss) attacker).setBlam(10, (byte) 1);
				}
				}
				if(jumpAttack && attacker.motionY==-0.0784000015258789D) {
					areaOfEffect();
					((EntityAntlionBoss) attacker).setBlam(0, (byte) 0);
					jumpAttack=false;
				}
	}
	
	protected Entity areaOfEffect() {
		List list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(attacker.boundingBox.minX, attacker.boundingBox.minY, attacker.boundingBox.minZ, attacker.boundingBox.maxX, attacker.boundingBox.maxY, attacker.boundingBox.maxZ).expand(8D, 1D, 8D));
			for (int i = 0; i < list.size(); i++) {
				Entity entity = (Entity) list.get(i);
				if (entity != null)
					if (entity instanceof EntityLivingBase && !(entity instanceof EntityAntlionBoss)){
						float Knockback = 2;
						entity.attackEntityFrom(DamageSource.causeMobDamage(attacker), 8.0F);
						entity.addVelocity(-MathHelper.sin(attacker.rotationYaw * 3.141593F / 180.0F) * Knockback * 0.5F, 0.4D, MathHelper.cos(attacker.rotationYaw * 3.141593F / 180.0F) * Knockback * 0.5F);
						worldObj.playSoundAtEntity(entity, "erebus:antlionslam", 1.0F, 1.0F);
						worldObj.playSoundAtEntity(entity, "erebus:antlionexplode", 1.0F, 1.0F);
						((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 8 * 20, 0));
						((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 8 * 20, 0));	
					}
			}	
		return null;
	}
	}
