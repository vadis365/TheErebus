package erebus.entity;

import erebus.ModBlocks;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityMagmaCrawler extends EntityMob {
	private static final DataParameter<Boolean> ON_CEILING = EntityDataManager.<Boolean>createKey(EntityMagmaCrawler.class, DataSerializers.BOOLEAN);

	public EntityMagmaCrawler(World world) {
		super(world);
		isImmuneToFire = true;
		setSize(0.9F, 0.9F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(ON_CEILING, true);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIErebusAttackMelee(this, 0.6D, false));
		tasks.addTask(2, new EntityMagmaCrawler.AIFireballAttack(this));
		tasks.addTask(3, new EntityAIWander(this, 0.5D));
		tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(5, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 20D : 20D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 4D : 4D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
	}


	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.MAGMACRAWLER;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.MAGMACRAWLER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.MAGMACRAWLER_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		entityDropItem(erebus.items.ItemMaterials.EnumErebusMaterialsType.MAGMA_CRAWLER_EYE.createStack(), 0.0F);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 5;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}

	@Override
	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
	}

	@Override
	public boolean getCanSpawnHere() {
		return getEntityWorld().checkNoEntityCollision(getEntityBoundingBox()) && getEntityWorld().getCollisionBoxes(this, getEntityBoundingBox()).isEmpty() && !getEntityWorld().containsAnyLiquid(getEntityBoundingBox()) && getEntityWorld().isAirBlock(getPosition()) && getEntityWorld().getBlockState(getPosition().up()).getBlock() == ModBlocks.GNEISS;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!getEntityWorld().isRemote) {
			if (recentlyHit == 60 )
				if (getOnCeiling())
					setOnCeiling(false);
			}

		if(getAttackTarget() !=null && getDistanceSq(getAttackTarget()) < 9D) {
			if (getOnCeiling())
				setOnCeiling(false);
		}

		if (getOnCeiling()) {
			motionY += 0.1D;
		}

	}

	@Override
	public void onLivingUpdate() {
		if (!getEntityWorld().isRemote && getOnCeiling()) {
			if (getAttackTarget() != null) {
				double var1 = getAttackTarget().posX + 0.5D - posX;
				double var5 = getAttackTarget().posZ + 0.5D - posZ;
				double var11 = getAttackTarget().posY - posY;
				motionX += (Math.signum(var1) * 0.5D - motionX) * 0.050000000149011612D;
				motionZ += (Math.signum(var5) * 0.5D - motionZ) * 0.050000000149011612D;
				float var7 = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
				float var8 = MathHelper.wrapDegrees(var7 - rotationYaw);
				moveForward = 0.1F;
				rotationYaw += var8;
			}
		}
		super.onLivingUpdate();
	}

    public boolean attackEntityAsMob(Entity entity) {
    	if (canEntityBeSeen(entity))
    		if (super.attackEntityAsMob(entity))
    			return true;
        return false;
    }
	
	public void setOnCeiling(boolean climbState) {
		dataManager.set(ON_CEILING, climbState);
	}

	public boolean getOnCeiling() {
		return dataManager.get(ON_CEILING) && getEntityWorld().getBlockState(getPosition().up()).getBlock() == ModBlocks.GNEISS && getEntityWorld().isAirBlock(getPosition().down());
	}

	static class AIFireballAttack extends EntityAIBase {
		private final EntityMagmaCrawler magma_crawler;
		private int attackStep;
		private int attackTime;

		public AIFireballAttack(EntityMagmaCrawler magma_crawlerIn) {
			magma_crawler = magma_crawlerIn;
			setMutexBits(3);
		}

		@Override
		public boolean shouldExecute() {
			EntityLivingBase entitylivingbase = magma_crawler.getAttackTarget();
			return entitylivingbase != null && entitylivingbase.isEntityAlive();
		}

		@Override
		public void startExecuting() {
			attackStep = 0;
		}

		@Override
		public void updateTask() {
			--attackTime;
			EntityLivingBase entitylivingbase = magma_crawler.getAttackTarget();
			double d0 = magma_crawler.getDistanceSq(entitylivingbase);
			if (d0 < 4.0D) {
				if (attackTime <= 0) {
					attackTime = 20;
					magma_crawler.attackEntityAsMob(entitylivingbase);
				}
			} else if (d0 > 9 && d0 < 256.0D) {
				double d1 = entitylivingbase.posX - magma_crawler.posX;
				double d2 = entitylivingbase.getEntityBoundingBox().minY + (double) (entitylivingbase.height / 2.0F) - (magma_crawler.posY + (double) (magma_crawler.height / 2.0F));
				double d3 = entitylivingbase.posZ - magma_crawler.posZ;
				if (attackTime == 0) {
					++attackStep;
					if (attackStep <= 4) 
						attackTime = 60;
					else {
						attackTime = 100;
						attackStep = 0;
					}
					if (attackStep > 1) {
						float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
						magma_crawler.getEntityWorld().playEvent((EntityPlayer) null, 1018, new BlockPos((int) magma_crawler.posX, (int) magma_crawler.posY, (int) magma_crawler.posZ), 0);
						EntitySmallFireball entitysmallfireball = new EntitySmallFireball(magma_crawler.getEntityWorld(), magma_crawler, d1 + magma_crawler.getRNG().nextGaussian() * (double) f, d2, d3 + magma_crawler.getRNG().nextGaussian() * (double) f);
						entitysmallfireball.posY = magma_crawler.posY + (double) (magma_crawler.height / 2.0F) + 0.5D;
						magma_crawler.getEntityWorld().spawnEntity(entitysmallfireball);
					}
				}
			}
			super.updateTask();
		}
	}
}
