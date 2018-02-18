package erebus.entity;

import javax.annotation.Nullable;

import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityBlackWidow extends EntityMob {
	private static final DataParameter<Integer> SIZE = EntityDataManager.<Integer>createKey(EntityBlackWidow.class, DataSerializers.VARINT);
	private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityBlackWidow.class, DataSerializers.BYTE);

	public EntityBlackWidow(World world) {
		super(world);
		isImmuneToFire = true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(SIZE, Integer.valueOf(1 << rand.nextInt(3)));
		dataManager.register(CLIMBING, Byte.valueOf((byte)0));
	}

	@Override
	protected void initEntityAI() {
        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(2, new EntityBlackWidow.AIWebSlingAttack(this));
        tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        tasks.addTask(4, new EntityAIErebusAttackMelee(this, 0.5D, true));
        tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.5D));
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(6, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityFly.class, true));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityBotFly.class, true));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityMidgeSwarm.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
	}

	@Override
    protected PathNavigate createNavigator(World worldIn) {
        return new PathNavigateClimber(this, worldIn);
    }

	@Override
	public void onUpdate() {
		super.onUpdate();
        if (!this.world.isRemote) {
            this.setBesideClimbableBlock(this.collidedHorizontally);
        }
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
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
		return 2;
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
		return ModSounds.BLACK_WIDOW_SOUND;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.BLACK_WIDOW_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH;
	}

	@Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
    }

	protected SoundEvent getWebSlingThrowSound() {
		return ModSounds.WEBSLING_THROW;
	}

	@Override
	protected Item getDropItem() {
		return Items.STRING;
	}

	@Override
	protected void dropFewItems(boolean attackedByPlayer, int looting) {
		super.dropFewItems(attackedByPlayer, looting);
		if (attackedByPlayer && (rand.nextInt(3) == 0 || rand.nextInt(1 + looting) > 0))
			dropItem(Items.SPIDER_EYE, 1);
		entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.POISON_GLAND.ordinal()), 0.0F);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity)) {
			if (entity instanceof EntityLivingBase) {
				byte duration = 0;
				if (getEntityWorld().getDifficulty() == EnumDifficulty.NORMAL)
					duration = 7;
				else if (getEntityWorld().getDifficulty() == EnumDifficulty.HARD)
					duration = 15;
				if (duration > 0) {
					if (rand.nextBoolean())
						((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WITHER, duration * 20, 0));
				}
			}
			return true;
		} else
			return false;
	}

	@Override
	public void notifyDataManagerChange(DataParameter<?> key) {
		if (SIZE.equals(key)) {
			int size = getWidowSize();
			setSize(0.9F * size, 0.4F * size);
			rotationYaw = this.rotationYawHead;
			renderYawOffset = this.rotationYawHead;
		}
		super.notifyDataManagerChange(key);
	}

	protected void setWidowSize(int size, boolean resetHealth) {
		dataManager.set(SIZE, size);
		setSize(0.9F * size, 0.4F * size);
		setPosition(this.posX, this.posY, this.posZ);
		
		if (size == 1) {
			getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 15D : 15D * ConfigHandler.INSTANCE.mobHealthMultipier);
			getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 1D : 1D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		}
		if (size == 2) {
			getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 20D : 20D * ConfigHandler.INSTANCE.mobHealthMultipier);
			getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 1.5D : 1.5D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		}
		if (size == 4) {
			getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 25D : 25D * ConfigHandler.INSTANCE.mobHealthMultipier);
			getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 2D : 2D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		}

        if (resetHealth)
            setHealth(getMaxHealth());
	}

	public int getWidowSize() {
		return dataManager.get(SIZE);
	}

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        int randomSize = this.rand.nextInt(3);

        if (randomSize < 2 && this.rand.nextFloat() < 0.5F * difficulty.getClampedAdditionalDifficulty())
            ++randomSize;

        int size = 1 << randomSize;
        setWidowSize(size, true);
        return super.onInitialSpawn(difficulty, livingdata);
    }

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("widowSize", getWidowSize() - 1);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		int size = nbt.getInteger("widowSize");
		if (size < 0)
			size = 0;
		setWidowSize(size + 1, false);
	}

	static class AIWebSlingAttack extends EntityAIBase {
		private final EntityBlackWidow widow;
		private int attackStep;
		private int attackTime;

		public AIWebSlingAttack(EntityBlackWidow widowIn) {
			widow = widowIn;
			setMutexBits(3);
		}

		@Override
		public boolean shouldExecute() {
			EntityLivingBase entitylivingbase = widow.getAttackTarget();
			return entitylivingbase != null && entitylivingbase.isEntityAlive() && widow.getWidowSize() > 1;
		}

		@Override
		public void startExecuting() {
			attackStep = 0;
		}

		@Override
		public void updateTask() {
			--attackTime;
			EntityLivingBase entitylivingbase = widow.getAttackTarget();
			double distance = widow.getDistanceSq(entitylivingbase);

			if (distance < 4.0D) {
				if (attackTime <= 0) {
					attackTime = 20;
					widow.attackEntityAsMob(entitylivingbase);
				}

				widow.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, widow.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());

			} else if (distance < 256.0D) {
				double targetX = entitylivingbase.posX - widow.posX;
				double targetY = entitylivingbase.getEntityBoundingBox().minY + (double) (entitylivingbase.height / 2.0F) - (widow.posY + (double) (widow.height / 2.0F));
				double targetZ = entitylivingbase.posZ - widow.posZ;

				if (attackTime <= 0) {
					++attackStep;

					if (attackStep == 1) {
						attackTime = 60;
					} else if (attackStep <= 4) {
						attackTime = 6;
					} else {
						attackTime = 100;
						attackStep = 0;

					}

					if (attackStep > 1 && entitylivingbase instanceof EntityPlayer) {

						widow.getEntityWorld().playSound((EntityPlayer) null, widow.getPosition(), widow.getWebSlingThrowSound(), SoundCategory.HOSTILE, 1.0F, 1.0F);
						for (int count = 0; count < 1; ++count) {
							EntityWebSling webSling = new EntityWebSling(widow.getEntityWorld(), widow);
							webSling.posY = widow.posY + (double) (widow.height / 2.0F) + 0.5D;
							webSling.setType((byte) 1);
							webSling.shoot(targetX, targetY, targetZ, 1.0F, 0.0F);
							widow.getEntityWorld().spawnEntity(webSling);
						}
					}
				}
				widow.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
				widow.getNavigator().clearPath();
				widow.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, widow.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
			}
			super.updateTask();
		}
	}
}
