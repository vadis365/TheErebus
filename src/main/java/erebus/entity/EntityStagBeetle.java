package erebus.entity;

import javax.annotation.Nullable;

import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import erebus.items.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityStagBeetle extends EntityTameable {
	private static final DataParameter<Byte> BEETLE_TYPE = EntityDataManager.<Byte>createKey(EntityStagBeetle.class, DataSerializers.BYTE);
	private static final DataParameter<Byte> HEAD_POSITION = EntityDataManager.<Byte>createKey(EntityStagBeetle.class, DataSerializers.BYTE);
	private static final DataParameter<Byte> ACTION = EntityDataManager.<Byte>createKey(EntityStagBeetle.class, DataSerializers.BYTE);
	private static final DataParameter<Integer> JAW_MOTION = EntityDataManager.<Integer>createKey(EntityStagBeetle.class, DataSerializers.VARINT);
	private EntityAINearestAttackableTarget aiNearestAttackableTarget;
	int shagCount;
	public int prevAnimation;

	public EntityStagBeetle(World world) {
		super(world);
		stepHeight = 2.0F;
		setSize(2.5F, 1.2F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(HEAD_POSITION, (byte) 1);//28
		dataManager.register(ACTION, (byte) 0);//29
		dataManager.register(JAW_MOTION, 0);//30
		dataManager.register(BEETLE_TYPE, (byte) 0);//31
	}

	@Override
    protected void initEntityAI() {
		aiNearestAttackableTarget = new EntityAINearestAttackableTarget(this, EntityPlayer.class, true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIErebusAttackMelee(this, 0.5D, true));
		tasks.addTask(2, new EntityAIMate(this, 0.5D));
		tasks.addTask(3, new EntityAITempt(this, 0.5D, ModItems.TURNIP, false));
		tasks.addTask(5, new EntityAIWander(this, 0.5D));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(7, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, aiNearestAttackableTarget);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 60D : 60D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.75D);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		prevAnimation = getJawMotion();
		if (shagCount > 0)
			shagCount--;
		if (!getEntityWorld().isRemote) {
			if (getAction() == 1)
				setJawMotion(getJawMotion() + 1);
			if (getJawMotion() >= 6) {
				setHeadPos((byte) 1);
				setAction((byte) 0);
				setJawMotion(0);
			}
		}
	}

	public void setTameState(byte tameState) {
		dataManager.set(BEETLE_TYPE, tameState);
	}

	public byte getTameState() {
		return dataManager.get(BEETLE_TYPE);
	}

	public void setHeadPos(byte position) {
		dataManager.set(HEAD_POSITION, position);
	}

	public byte getHeadPos() {
		return dataManager.get(HEAD_POSITION);
	}
	
	public void setAction(byte action) {
		dataManager.set(ACTION, action);
	}

	public byte getAction() {
		return dataManager.get(ACTION);
	}

	public void setJawMotion(int motion) {
		dataManager.set(JAW_MOTION, motion);
	}

	public int getJawMotion() {
		return dataManager.get(JAW_MOTION);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.BOMBARDIER_BEETLE_SOUND;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.BOMBARDIER_BEETLE_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness();
		if (light >= 0F)
			return getEntityWorld().checkNoEntityCollision(getEntityBoundingBox()) && getEntityWorld().getCollisionBoxes(this, getEntityBoundingBox()).isEmpty() && !getEntityWorld().containsAnyLiquid(getEntityBoundingBox()) && getEntityWorld().getDifficulty() != EnumDifficulty.PEACEFUL;
		return super.getCanSpawnHere();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}

	@Override
	protected boolean canDespawn() {
		if (getTameState() != 0)
			return false;
		else
			return true;
	}

	@Override
    public boolean canBeLeashedTo(EntityPlayer player) {
        return !canDespawn() && super.canBeLeashedTo(player);
    }

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (getTameState() >= 2)
			entityDropItem(ItemMaterials.EnumErebusMaterialsType.BEETLE_RIDING_KIT.createStack(), 0.0F);
		int var3 = 1 + rand.nextInt(3) + rand.nextInt(1 + looting);
		for (int a = 0; a < var3; ++a) 
			entityDropItem(ItemMaterials.EnumErebusMaterialsType.PLATE_EXO.createStack(), 0.0F);
		int rareDropChance = rand.nextInt(30);
		if (rareDropChance == 0)
			entityDropItem(ItemMaterials.EnumErebusMaterialsType.STAG_BEETLE_MANDIBLES.createStack(), 0.0F);
		if (rareDropChance >= 1 && rareDropChance <= 4) {
			if(isBurning())
				entityDropItem(new ItemStack(ModItems.STAG_HEART_COOKED), 1);
			else
				entityDropItem(new ItemStack(ModItems.STAG_HEART_RAW), 1);
		}
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack is = player.inventory.getCurrentItem();
		float healingBuff = 0.0F;
		if (!is.isEmpty() && is.getItem() == ModItems.MATERIALS && is.getItemDamage() == ItemMaterials.EnumErebusMaterialsType.BEETLE_TAMING_AMULET.ordinal() && getTameState() == 0) {
			healingBuff = (float) (ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 20D : 20D * ConfigHandler.INSTANCE.mobHealthMultipier);
			is.shrink(1);
			setTameState((byte) 1);
			playTameEffect(true);
			player.swingArm(hand);
			tasks.removeTask(aiNearestAttackableTarget);
			setAttackTarget((EntityLivingBase) null);
			getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 80D : 80D * ConfigHandler.INSTANCE.mobHealthMultipier);
			heal(healingBuff);
			return true;
		}
		if (!is.isEmpty() && is.getItem() == ModItems.MATERIALS && is.getItemDamage() == ItemMaterials.EnumErebusMaterialsType.BEETLE_RIDING_KIT.ordinal() && getTameState() == 1) {
			is.shrink(1);
			player.swingArm(hand);
			setTameState((byte) 2);
			return true;
		}
		if (!is.isEmpty() && is.getItem() == ModItems.TURNIP && !shagging() && getTameState() != 0) {
			is.shrink(1);
			shagCount = 600;
			getEntityWorld().playSound((EntityPlayer)null, getPosition(), ModSounds.BEETLE_LARVA_MUNCH, SoundCategory.NEUTRAL, 1.0F, 0.75F);
			return true;
		}
		if (is.isEmpty() && getTameState() >= 2) {
			if (!getEntityWorld().isRemote)
				player.startRiding(this);
			return true;
		}
		if (!is.isEmpty() && is.getItem() == ModItems.MATERIALS && is.getItemDamage() == ItemMaterials.EnumErebusMaterialsType.BAMBOO.ordinal() && getTameState() != 0) {
			healingBuff = (float) (ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 5D : 5D * ConfigHandler.INSTANCE.mobHealthMultipier);
			if (getHealth() < getMaxHealth()) {
				heal(healingBuff);
				playTameEffect(true);
				player.swingArm(hand);
				is.shrink(1);
				if (getHealth() == getMaxHealth())
					getEntityWorld().playSound((EntityPlayer)null, getPosition(), ModSounds.BEETLE_LARVA_MUNCH, SoundCategory.NEUTRAL, 1.0F, 0.75F);
			}
			return true;
		}
		return super.processInteract(player, hand);
	}

	public boolean shagging() {
		return shagCount > 0;
	}

	@Override
    public void travel(float strafe, float up, float forward) {
        if (isBeingRidden() && canBeSteered()) {
            EntityLivingBase entitylivingbase = (EntityLivingBase)getControllingPassenger();
            rotationYaw = entitylivingbase.rotationYaw;
            prevRotationYaw = rotationYaw;
            rotationPitch = entitylivingbase.rotationPitch * 0.5F;
            setRotation(rotationYaw, rotationPitch);
            renderYawOffset = rotationYaw;
            rotationYawHead = renderYawOffset;
            strafe = entitylivingbase.moveStrafing * 0.3F;
            forward = entitylivingbase.moveForward * 0.3F;

            if (forward <= 0.0F) 
                forward *= 0.25F;

            jumpMovementFactor = getAIMoveSpeed() * 0.1F;

            if (canPassengerSteer()) {
                setAIMoveSpeed((float)getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
                super.travel(strafe, up, forward);
            }
            else if (entitylivingbase instanceof EntityPlayer) {
            	setAIMoveSpeed((float)getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
                super.travel(strafe, up, forward);
            }

            prevLimbSwingAmount = limbSwingAmount;
            double d1 = posX - prevPosX;
            double d0 = posZ - prevPosZ;
            float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

            if (f2 > 1.0F)
                f2 = 1.0F;

            limbSwingAmount += (f2 - limbSwingAmount) * 0.4F;
            limbSwing += limbSwingAmount;
        }
        else {
            jumpMovementFactor = 0.02F;
            super.travel(strafe, up, forward);
        }
    }

	@Override
	public void updatePassenger(Entity entity) {
		super.updatePassenger(entity);
		if (entity instanceof EntityLivingBase) {
			double a = Math.toRadians(renderYawOffset);
			double offSetX = -Math.sin(a) * 0.75D;
			double offSetZ = Math.cos(a) * 0.75D;
			entity.setPosition(posX - offSetX, posY + 0.8D + entity.getYOffset(), posZ - offSetZ);
		}
	}

	@Override
    public boolean canBeSteered() {
        return this.getControllingPassenger() instanceof EntityLivingBase;
    }

	@Override
    public boolean canPassengerSteer() {
        Entity entity = this.getControllingPassenger();
        return entity instanceof EntityPlayer ? ((EntityPlayer)entity).isUser() : !this.world.isRemote;
    }

    @Nullable
    public Entity getControllingPassenger()  {
        return this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
    }

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return spawnBabyAnimal(entityageable);
	}

	@Override
	public boolean isBreedingItem(ItemStack is) {
		if (getTameState() != 0)
			return !is.isEmpty() && is.getItem() == ModItems.TURNIP;
		else
			return false;
	}

	public EntityBeetleLarva spawnBabyAnimal(EntityAgeable entityageable) {
		EntityBeetleLarva entityBeetleLarva = new EntityBeetleLarva(getEntityWorld());
		entityBeetleLarva.setLarvaType((byte) 5);
		return entityBeetleLarva;
	}

	@Override
	public void setAttackTarget(EntityLivingBase entity) {
		if (getTameState() != 0) {
			if (entity instanceof EntityPlayer)
				super.setAttackTarget((EntityLivingBase) null);
		} else
			super.setAttackTarget(entity);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (getTameState() != 0)
			if (entity instanceof EntityPlayer) {
				setAttackTarget((EntityLivingBase) null);
				return false;
			}
		if (entity != null && getDistanceToEntity(entity) <= 2.5F && entity.getEntityBoundingBox().maxY > getEntityBoundingBox().minY && entity.getEntityBoundingBox().minY < getEntityBoundingBox().maxY)
			entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) (ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 4D : 4D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier));
		return super.attackEntityAsMob(entity);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound data) {
		super.writeEntityToNBT(data);
		data.setByte("tameState", getTameState());
		setJawMotion(data.getInteger("jawMotion"));
		setHeadPos(data.getByte("headPos"));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound data) {
		super.readEntityFromNBT(data);
		setTameState(data.getByte("tameState"));
		data.setInteger("jawMotion", getJawMotion());
		data.setByte("headPos", getHeadPos());
	}
}