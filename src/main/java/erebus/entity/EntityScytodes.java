package erebus.entity;

import javax.annotation.Nullable;

import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityScytodes extends EntityMob {

	private static final DataParameter<Integer> SKIN_TYPE = EntityDataManager.<Integer>createKey(EntityScytodes.class, DataSerializers.VARINT);
	private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityScytodes.class, DataSerializers.BYTE);

	public EntityScytodes(World world) {
		super(world);
		setSize(2F, 1F);
		isImmuneToFire = true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(SKIN_TYPE, rand.nextInt(4));
		dataManager.register(CLIMBING, Byte.valueOf((byte)0));
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityScytodes.AIWebSlingAttack(this));
		tasks.addTask(2, new EntityAILeapAtTarget(this, 0.4F));
		tasks.addTask(3, new EntityAIErebusAttackMelee(this, 0.5D, true));
		tasks.addTask(4, new EntityAIWanderAvoidWater(this, 0.5D));
		tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false, new Class[0]));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 25D : 25D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 4D : 4D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
	}

	@Override
    protected PathNavigate createNavigator(World worldIn) {
        return new PathNavigateClimber(this, worldIn);
    }

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness();
		if (light >= 0F)
			return isNotColliding();
		return super.getCanSpawnHere();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 3;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
        if (!this.world.isRemote) {
            this.setBesideClimbableBlock(this.isCollidedHorizontally);
        }
	}
	
	@Override
	public void fall(float distance, float damageMultiplier) {
	}

	@Override
	public void setInWeb() {
	}

	@Override
    public boolean isOnLadder() {
        return isBesideClimbableBlock();
    }
	
    public boolean isBesideClimbableBlock() {
        return (((Byte)dataManager.get(CLIMBING)).byteValue() & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean climbing) {
        byte b0 = ((Byte)dataManager.get(CLIMBING)).byteValue();
        if (climbing)
            b0 = (byte)(b0 | 1);
        else
            b0 = (byte)(b0 & -2);

        this.dataManager.set(CLIMBING, Byte.valueOf(b0));
    }

	@Override
	public boolean isPotionApplicable(PotionEffect potioneffectIn) {
		 return (potioneffectIn.getPotion() == MobEffects.POISON || potioneffectIn.getPotion() == MobEffects.WITHER) ? false : super.isPotionApplicable(potioneffectIn);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_SPIDER_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_SPIDER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SPIDER_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block block) {
		this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
	}

	protected SoundEvent getWebSlingThrowSound() {
		return ModSounds.WEBSLING_THROW;
	}

	@Override
	protected Item getDropItem() {
		return Items.STRING;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		super.dropFewItems(recentlyHit, looting);
		if (recentlyHit && (rand.nextInt(3) == 0 || rand.nextInt(1 + looting) > 0))
			dropItem(Items.SPIDER_EYE, 1);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);

		if (getEntityWorld().rand.nextInt(100) == 0) {
			EntityMoneySpider moneyspider = new EntityMoneySpider(getEntityWorld());
			moneyspider.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
			moneyspider.onInitialSpawn(difficulty, (IEntityLivingData) null);
			getEntityWorld().spawnEntity(moneyspider);
			moneyspider.startRiding(this);
		}
		if (livingdata == null) {
			livingdata = new EntitySpider.GroupData();
            if (this.world.getDifficulty() == EnumDifficulty.HARD && this.world.rand.nextFloat() < 0.1F * difficulty.getClampedAdditionalDifficulty())
                ((EntitySpider.GroupData)livingdata).setRandomEffect(this.world.rand);

            if (livingdata instanceof EntitySpider.GroupData) {
                Potion potion = ((EntitySpider.GroupData)livingdata).effect;
                if (potion != null)
                    this.addPotionEffect(new PotionEffect(potion, Integer.MAX_VALUE));
            }
		}
		return livingdata;
	}

	public void setSkin(int skinType) {
		dataManager.set(SKIN_TYPE, skinType);
	}

	public int getSkin() {
		return dataManager.get(SKIN_TYPE);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("skin", getSkin());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		if (nbt.hasKey("skin"))
			setSkin(nbt.getInteger("skin"));
		else
			setSkin(rand.nextInt(4));
	}
	
	static class AIWebSlingAttack extends EntityAIBase {
		private final EntityScytodes scytodes;
		private int attackStep;
		private int attackTime;

		public AIWebSlingAttack(EntityScytodes scytodesIn) {
			scytodes = scytodesIn;
			setMutexBits(3);
		}

		@Override
		public boolean shouldExecute() {
			EntityLivingBase entitylivingbase = scytodes.getAttackTarget();
			return entitylivingbase != null && entitylivingbase.isEntityAlive();
		}

		@Override
		public void startExecuting() {
			attackStep = 0;
		}

		@Override
		public void updateTask() {
			--attackTime;
			EntityLivingBase entitylivingbase = scytodes.getAttackTarget();
			double distance = scytodes.getDistanceSqToEntity(entitylivingbase);

			if (distance < 4.0D) {
				if (attackTime <= 0) {
					attackTime = 20;
					scytodes.attackEntityAsMob(entitylivingbase);
				}

				scytodes.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, scytodes.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());

			} else if (distance < 256.0D) {
				double targetX = entitylivingbase.posX - scytodes.posX;
				double targetY = entitylivingbase.getEntityBoundingBox().minY + (double) (entitylivingbase.height / 2.0F) - (scytodes.posY + (double) (scytodes.height / 2.0F));
				double targetZ = entitylivingbase.posZ - scytodes.posZ;

				if (attackTime <= 0) {
					++attackStep;
					if (attackStep == 1)
						attackTime = 60;
					else if (attackStep <= 4)
						attackTime = 6;
					else {
						attackTime = 100;
						attackStep = 0;
					}

					if (attackStep > 1 && entitylivingbase instanceof EntityPlayer) {
						scytodes.getEntityWorld().playSound((EntityPlayer) null, scytodes.getPosition(), scytodes.getWebSlingThrowSound(), SoundCategory.HOSTILE, 1.0F, 1.0F);
						for (int count = 0; count < 1; ++count) {
							EntityWebSling webSling = new EntityWebSling(scytodes.getEntityWorld(), scytodes);
							webSling.posY = scytodes.posY + (double) (scytodes.height / 2.0F) + 0.5D;
							webSling.setType((byte) 0);
							webSling.setThrowableHeading(targetX, targetY, targetZ, 1.0F, 0.0F);
							scytodes.getEntityWorld().spawnEntity(webSling);
						}
					}
				}
				scytodes.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
				scytodes.getNavigator().clearPathEntity();
				scytodes.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, scytodes.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
			}
			super.updateTask();
		}
	}
}