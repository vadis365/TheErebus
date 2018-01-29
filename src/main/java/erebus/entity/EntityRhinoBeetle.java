package erebus.entity;

import javax.annotation.Nullable;

import erebus.Erebus;
import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.KeyBindingHandler;
import erebus.core.handler.configs.ConfigHandler;
import erebus.items.ItemMaterials;
import erebus.network.server.PacketBeetleRamAttack;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
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
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityRhinoBeetle extends EntityTameable {
	private static final DataParameter<Byte> BEETLE_TYPE = EntityDataManager.<Byte>createKey(EntityRhinoBeetle.class, DataSerializers.BYTE);
	private static final DataParameter<Byte> RAMMING_CHARGE = EntityDataManager.<Byte>createKey(EntityRhinoBeetle.class, DataSerializers.BYTE);
	private static final DataParameter<Boolean> IS_RAMMING = EntityDataManager.<Boolean>createKey(EntityRhinoBeetle.class, DataSerializers.BOOLEAN);
	private EntityAINearestAttackableTarget aiNearestAttackableTarget;
	int shagCount;

	public EntityRhinoBeetle(World world) {
		super(world);
		setSize(2.3F, 1.4F);
		stepHeight = 1F;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(RAMMING_CHARGE, (byte) 0);
		dataManager.register(BEETLE_TYPE, (byte) 0);
		dataManager.register(IS_RAMMING, false);
	}

	@Override
    protected void initEntityAI() {
		aiNearestAttackableTarget = new EntityAINearestAttackableTarget(this, EntityPlayer.class, true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAttackMelee(this, 0.7D, true));
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
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.7D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 60D : 60D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(8.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.75D);
	}

	public void setRammingCharge(byte velocity) {
		dataManager.set(RAMMING_CHARGE, velocity);
	}

	public byte getRammingCharge() {
		return dataManager.get(RAMMING_CHARGE);
	}

	public void setTameState(byte tameState) {
		dataManager.set(BEETLE_TYPE, tameState);
	}

	public byte getTameState() {
		return dataManager.get(BEETLE_TYPE);
	}

	public void setRamming(boolean ramming) {
		dataManager.set(IS_RAMMING, ramming);
	}

	public boolean getRamming() {
		return dataManager.get(IS_RAMMING);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.RHINO_BEETLE_SOUND;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.RHINO_BEETLE_HURT;
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
	public boolean isOnLadder() {
		return isBeingRidden() && collidedHorizontally;
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
		return 1;
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
		if (getTameState() == 2)
			entityDropItem(ItemMaterials.EnumErebusMaterialsType.BEETLE_RIDING_KIT.createStack(), 0.0F);
		int dropRate = 1 + rand.nextInt(2 + looting);
		for (int a = 0; a < dropRate; ++a)
			entityDropItem(ItemMaterials.EnumErebusMaterialsType.PLATE_EXO_RHINO.createStack(), 0.0F);
		if (rand.nextInt(20) == 0)
			entityDropItem(ItemMaterials.EnumErebusMaterialsType.RHINO_BEETLE_HORN.createStack(), 0.0F);
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
		if (is.isEmpty() && getTameState() == 2) {
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
	protected void collideWithEntity(Entity entity) {
		if (isBeingRidden() && entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer) && !(entity instanceof EntityBotFlyLarva) && getRamming()) {
			ram(entity, getRammingCharge() * 0.2F, getRammingCharge() * 0.4F);
			setRammingCharge((byte) 0);
		}
		super.collideWithEntity(entity);
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
		return ram(entity, 1F, (float) (ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 6D : 6D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier));
	}

	private boolean ram(Entity entity, float knockback, float damage) {
		if (getTameState() == 0 || !isBeingRidden())
			setRammingCharge((byte) 32);
		if (!getEntityWorld().isRemote && entity.getEntityBoundingBox().maxY >= getEntityBoundingBox().minY && entity.getEntityBoundingBox().minY <= getEntityBoundingBox().maxY && entity.getEntityBoundingBox().maxX >= getEntityBoundingBox().minX - 0.25D && entity.getEntityBoundingBox().minX <= getEntityBoundingBox().maxX + 0.25D && entity.getEntityBoundingBox().maxZ >= getEntityBoundingBox().minZ - 0.25D && entity.getEntityBoundingBox().minZ <= getEntityBoundingBox().maxZ + 0.25D) {
			entity.attackEntityFrom(DamageSource.causeMobDamage(this), (int) damage);
			entity.addVelocity(-MathHelper.sin(rotationYaw * 3.141593F / 180.0F) * knockback, 0.4D, MathHelper.cos(rotationYaw * 3.141593F / 180.0F) * knockback);
			getEntityWorld().playSound((EntityPlayer)null, getPosition(), SoundEvents.ENTITY_PLAYER_BIG_FALL, SoundCategory.HOSTILE, 1.0F, 1.0F);
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, getEntityWorld().getDifficulty().ordinal() * 50, 0));
			setRamming(false);
		}
		return true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!getEntityWorld().isRemote) {
			if (!getRamming())
				if (getTameState() == 0 || !isBeingRidden())
					if (getEntityWorld().getTotalWorldTime() % 10 == 0)
						setRammingCharge((byte) 0);
			if (shagCount > 0)
				shagCount--;
		}

		if (getEntityWorld().isRemote)
			if (getRamming() && !KeyBindingHandler.BEETLE_RAM.isPressed())
				Erebus.NETWORK_WRAPPER.sendToServer(new PacketBeetleRamAttack(false));
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
            strafe = entitylivingbase.moveStrafing * 0.4F;
            forward = entitylivingbase.moveForward * 0.75F;

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
            travelSpeed(f2);
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
	
	private void travelSpeed(float velocity) {
		if (!getEntityWorld().isRemote) {
			if (velocity >= 4F)
				setRammingCharge((byte) (getRammingCharge() + 1));
			else if (velocity <= 4F)
				setRammingCharge((byte) (getRammingCharge() - 1));
			if (getRammingCharge() <= 0)
				setRammingCharge((byte) 0);
			if (getRammingCharge() >= 25)
				setRammingCharge((byte) 25);
		}
	}

	@Override
	public void updatePassenger(Entity entity) {
		super.updatePassenger(entity);
		if (entity instanceof EntityLivingBase) {
			double a = Math.toRadians(renderYawOffset);
			double offSetX = -Math.sin(a) * 0.35D;
			double offSetZ = Math.cos(a) * 0.35D;
			entity.setPosition(posX - offSetX, posY + 1.3D + entity.getYOffset(), posZ - offSetZ);
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
		entityBeetleLarva.setLarvaType((byte) 2);
		return entityBeetleLarva;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setByte("tameState", getTameState());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setTameState(nbt.getByte("tameState"));
	}
}
