package erebus.entity;

import java.util.Random;

import javax.annotation.Nullable;

import erebus.Erebus;
import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.core.helper.Utils;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import erebus.items.ItemErebusFood;
import erebus.items.ItemMaterials;
import erebus.tileentity.TileEntityTitanChest;
import erebus.tileentity.TileEntityTitanEnderChest;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityTitanBeetle extends EntityTameable {
	private static final DataParameter<Byte> BEETLE_TYPE = EntityDataManager.<Byte>createKey(EntityTitanBeetle.class, DataSerializers.BYTE);
	private static final DataParameter<Float> OPENTICKS = EntityDataManager.<Float>createKey(EntityTitanBeetle.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> PREV_OPENTICKS = EntityDataManager.<Float>createKey(EntityTitanBeetle.class, DataSerializers.FLOAT);
	private EntityAINearestAttackableTarget aiNearestAttackableTarget;
	boolean isOpen;
	int shagCount;
	public NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);

	public EntityTitanBeetle(World world) {
		super(world);
		stepHeight = 2.0F;
		setSize(2.5F, 1.2F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(OPENTICKS, 0.0F);
		dataManager.register(PREV_OPENTICKS, 0.0F);
		dataManager.register(BEETLE_TYPE, new Byte((byte) 0));
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

	public EntityTitanBeetle setContents(IInventory chest) {
		if (chest == null)
			return this;

		inventory = NonNullList.<ItemStack>withSize(chest.getSizeInventory(), ItemStack.EMPTY);
		for (int i = 0; i < chest.getSizeInventory(); i++) {
			if (chest.getStackInSlot(i).isEmpty())
				continue;
			inventory.set(i, chest.getStackInSlot(i).copy());
			chest.setInventorySlotContents(i, ItemStack.EMPTY);
		}
		return this;
	}

	public void setOpen(boolean open) {
		if (!getEntityWorld().isRemote)
			if (open)
				if(getTameState() == 3)
					getEntityWorld().playSound((EntityPlayer)null, getPosition(), SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, 0.9F);
				else
					getEntityWorld().playSound((EntityPlayer)null, getPosition(), SoundEvents.BLOCK_ENDERCHEST_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
			else
				if(getTameState() == 3)
					getEntityWorld().playSound((EntityPlayer)null, getPosition(), SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, 0.9F);
				else
					getEntityWorld().playSound((EntityPlayer)null, getPosition(), SoundEvents.BLOCK_ENDERCHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
		isOpen = open;
	}

	public boolean getOpen() {
		return isOpen;
	}

	public void setOpenTicks(float ticks) {
		dataManager.set(OPENTICKS, ticks);
	}

	public float getOpenTicks() {
		return dataManager.get(OPENTICKS);
	}

	public void setPrevOpenTicks(float ticks) {
		dataManager.set(PREV_OPENTICKS, ticks);
	}

	public float getPrevOpenTicks() {
		return dataManager.get(PREV_OPENTICKS);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!getEntityWorld().isRemote && isDead)
			for (ItemStack is : inventory)
				if (!is.isEmpty())
					Utils.dropStack(getEntityWorld(), getPosition(), is);
		if (getEntityWorld().isRemote && getTameState() == 4) {
			double a = Math.toRadians(renderYawOffset);
			double offSetX = -Math.sin(a) * 1.2D;
			double offSetZ = Math.cos(a) * 1.2D;
			enderChestParticles(getEntityWorld(), posX - offSetX, posY + 1.2, posZ - offSetZ, rand);
		}
		if (shagCount > 0)
			shagCount--;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (!getEntityWorld().isRemote) {
			setPrevOpenTicks(getOpenTicks());
			if (isOpen)
				if (getOpenTicks() < 1F)
					setOpenTicks(getOpenTicks() + 0.1F);
			if (!isOpen)
				if (getOpenTicks() > 0F)
					setOpenTicks(getOpenTicks() - 0.1F);
			if (getOpenTicks() > 1)
				setOpenTicks(1F);
			if (getOpenTicks() < 0)
				setOpenTicks(0F);
		}
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 60D : 60D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.75D);
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
		entityDropItem(new ItemStack(ModItems.EREBUS_FOOD, 1 + rand.nextInt(1), isBurning() ? ItemErebusFood.EnumFoodType.TITAN_CHOP_COOKED.ordinal() : ItemErebusFood.EnumFoodType.TITAN_CHOP_RAW.ordinal()), 0.0F);
		dropChests();
	}

	public void dropChests() {
		if (!getEntityWorld().isRemote) {
			if (getTameState() == 3)
				dropItem(Item.getItemFromBlock(Blocks.CHEST), 1);
			if (getTameState() == 4)
				dropItem(Item.getItemFromBlock(Blocks.ENDER_CHEST), 1);
		}
	}

	public void openGUI(EntityPlayer player) {
		if (!getEntityWorld().isRemote && !isBeingRidden() && getTameState() != 0) {
			if (getTameState() == 3)
				player.displayGUIChest(new TileEntityTitanChest(this));
			if (getTameState() == 4) {
				setOpen(true);
				InventoryEnderChest inventoryenderchest = player.getInventoryEnderChest();
                inventoryenderchest.setChestTileEntity(new TileEntityTitanEnderChest(this));
				player.displayGUIChest(inventoryenderchest);
			}
		}
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack is = player.inventory.getCurrentItem();
		float healingBuff = 0.0F;
		if (getTameState() == 3 && player.isSneaking()) {
			openGUI(player);
			return true;
		}
		if (getTameState() == 4 && player.isSneaking()) {
			openGUI(player);
			return true;
		}
		if (!is.isEmpty() && is.getItem() == ModItems.MATERIALS && is.getItemDamage() == ItemMaterials.EnumErebusMaterialsType.BEETLE_TAMING_AMULET.ordinal() && getTameState() == 0) {
			healingBuff = (float) (ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 20D : 20D * ConfigHandler.INSTANCE.mobHealthMultipier);
			is.shrink(1);
			setTameState((byte) 1);
			playTameEffect(true);
			player.swingArm(hand);
			tasks.removeTask(aiNearestAttackableTarget);
			setAttackTarget((EntityLivingBase) null);
			getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
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
		if (!is.isEmpty()) {
			boolean flag = false;
			if (!flag && getTameState() == 2 && is.getItem() == Item.getItemFromBlock(Blocks.CHEST)) {
				setTameState((byte) 3);
				getEntityWorld().playSound((EntityPlayer)null, getPosition(), SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
				flag = true;
			}
			if (flag && getTameState() == 3) {
				if (!player.capabilities.isCreativeMode)
					is.shrink(1);
				return true;
			}
			if (!flag && getTameState() == 2 && is.getItem() == Item.getItemFromBlock(Blocks.ENDER_CHEST)) {
				setTameState((byte) 4);
				getEntityWorld().playSound((EntityPlayer)null, getPosition(), SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
				flag = true;
			}
			if (flag && getTameState() == 4) {
				if (!player.capabilities.isCreativeMode)
					is.shrink(1);
				return true;
			}
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
            strafe = entitylivingbase.moveStrafing * 0.4F;
            forward = entitylivingbase.moveForward * 0.4F;

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
			double offSetX = -Math.sin(a) * 0.1D;
			double offSetZ = Math.cos(a) * 0.1D;
			entity.setPosition(posX - offSetX, posY + 1.1D + entity.getYOffset(), posZ - offSetZ);
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

	@SideOnly(Side.CLIENT)
	public void enderChestParticles(World world, double x, double y, double z, Random rand) {
		for (int count = 0; count < 3; ++count) {
			double velX = 0.0D;
			double velY = 0.0D;
			double velZ = 0.0D;
			int motionX = rand.nextInt(2) * 2 - 1;
			int motionZ = rand.nextInt(2) * 2 - 1;
			velY = (rand.nextFloat() - 0.5D) * 0.125D;
			velZ = rand.nextFloat() * 1.0F * motionZ;
			velX = rand.nextFloat() * 1.0F * motionX;
			Erebus.PROXY.spawnCustomParticle("portal", getEntityWorld(), x, y, z, velX, velY, velZ);
		}
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
		entityBeetleLarva.setLarvaType((byte) 3);
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
		if (entity != null && entity.getEntityBoundingBox().maxY > getEntityBoundingBox().minY && entity.getEntityBoundingBox().minY < getEntityBoundingBox().maxY)
			entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) (ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 4D : 4D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier));
		return super.attackEntityAsMob(entity);
	}

	public void setTameState(byte tameState) {
		dataManager.set(BEETLE_TYPE, tameState);
	}

	public byte getTameState() {
		return dataManager.get(BEETLE_TYPE);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.loadFromNbt(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		return this.saveToNbt(compound);
	}

	public void loadFromNbt(NBTTagCompound compound) {
		setTameState(compound.getByte("tameState"));
		if (getTameState() == 3) {
			inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
			if (compound.hasKey("Items", 9))
				ItemStackHelper.loadAllItems(compound, inventory);
		}
	}

	public NBTTagCompound saveToNbt(NBTTagCompound compound) {
		compound.setByte("tameState", getTameState());
		if (getTameState() == 3) {
			ItemStackHelper.saveAllItems(compound, inventory, false);
		}
		return compound;
	}

	public int getSizeInventory() {
		return inventory.size();
	}

}