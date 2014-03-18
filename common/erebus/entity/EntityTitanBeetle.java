package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
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
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.IInvBasic;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.Erebus;
import erebus.ModItems;
import erebus.core.proxy.CommonProxy;
import erebus.item.ItemErebusMaterial.DATA;
import erebus.item.ItemErebusSpecial;

public class EntityTitanBeetle extends EntityTameable implements IInvBasic {

	public AnimalChest beetleChest;
	private final EntityAINearestAttackableTarget aiNearestAttackableTarget = new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true);
	boolean isOpen;
	float openticks;

	public EntityTitanBeetle(World world) {
		super(world);
		stepHeight = 2.0F;
		setSize(3F, 1.2F);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAttackOnCollide(this, 0.5D, true));
		tasks.addTask(2, new EntityAIMate(this, 0.5D));
		tasks.addTask(3, new EntityAITempt(this, 0.5D, ModItems.turnip.itemID, false));
		tasks.addTask(5, new EntityAIWander(this, 0.5D));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(7, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, aiNearestAttackableTarget);
		createChest();
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(21, 0.0F);
		dataWatcher.addObject(31, new Byte((byte) 0));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	public void setOpen(boolean open) {
		isOpen = open;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (isOpen)
			if (openticks >= -1.570F) {
				openticks = openticks - 0.19625F;
				dataWatcher.updateObject(21, openticks);
			}
		if (!isOpen) {
			if (openticks < 0F) {
				openticks = openticks + 0.19625F;
				dataWatcher.updateObject(21, openticks);
			}
			if (openticks == -1.5699999F)
				worldObj.playSoundEffect(posX, posY + 0.5D, posZ, "random.chestclosed", 0.5F, 0.9F);
		}
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.0D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(60.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(0.75D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:BombardierBeetleSound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:BombardierBeetleHurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void playStepSound(int x, int y, int z, int par4) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness(1.0F);
		if (light >= 0F)
			return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
		return super.getCanSpawnHere();
	}

	@Override
	protected boolean canDespawn() {
		if (getTameState() != 0)
			return false;
		else
			return true;
	}

	@Override
	public boolean allowLeashing() {
		return !canDespawn() && super.allowLeashing();
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (getTameState() >= 2)
			entityDropItem(new ItemStack(ModItems.erebusSpecialItem, 1, ItemErebusSpecial.dataRhinoRidingKit), 0.0F);
		if (getTameState() == 3) {
			dropChests();
			dropItemsInChest(this, beetleChest);
		}
		entityDropItem(new ItemStack(ModItems.erebusMaterials, rand.nextInt(3) + 1, DATA.plateExo.ordinal()), 0.0F);
	}

	public void dropChests() {
		if (!worldObj.isRemote)
			dropItem(Block.chest.blockID, 1);
	}

	private void dropItemsInChest(Entity entity, AnimalChest animalChest) {
		if (animalChest != null && !worldObj.isRemote)
			for (int i = 0; i < animalChest.getSizeInventory(); ++i) {
				ItemStack itemstack = animalChest.getStackInSlot(i);
				if (itemstack != null)
					entityDropItem(itemstack, 0.0F);
			}
	}

	private void createChest() {
		AnimalChest animalchest = beetleChest;
		beetleChest = new AnimalChest("beetleChest", 27);
		beetleChest.func_110133_a(getEntityName());
		if (animalchest != null) {
			animalchest.func_110132_b(this);
			int i = Math.min(animalchest.getSizeInventory(), beetleChest.getSizeInventory());
			for (int j = 0; j < i; ++j) {
				ItemStack itemstack = animalchest.getStackInSlot(j);
				if (itemstack != null)
					beetleChest.setInventorySlotContents(j, itemstack.copy());
			}
			animalchest = null;
		}
		beetleChest.func_110134_a(this);
	}

	@Override
	public void onInventoryChanged(InventoryBasic inventoryBasic) {

	}

	public void openGUI(EntityPlayer player) {
		if (!worldObj.isRemote && (riddenByEntity == null || riddenByEntity == player) && getTameState() != 0) {
			beetleChest.func_110133_a(getEntityName());
			player.openGui(Erebus.instance, CommonProxy.GUI_ID_TITAN_BEETLE, worldObj, entityId, 0, 0);
		}
	}

	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack is = player.inventory.getCurrentItem();
		float healingBuff = 0.0F;
		if (getTameState() == 3 && player.isSneaking()) {
			worldObj.playSoundEffect(posX, posY + 0.5D, posZ, "random.chestopen", 0.5F, 0.9F);
			setOpen(true);
			openGUI(player);
			return true;
		} else
			setOpen(false);
		if (is != null && is.itemID == ModItems.erebusSpecialItem.itemID && is.getItemDamage() == 1 && getTameState() == 0) {
			healingBuff = 20F;
			is.stackSize--;
			setTameState((byte) 1);
			playTameEffect(true);
			player.swingItem();
			tasks.removeTask(aiNearestAttackableTarget);
			setAttackTarget((EntityLivingBase) null);
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(80.0D);
			heal(healingBuff);
			return true;
		}
		if (is != null && is.itemID == ModItems.erebusSpecialItem.itemID && is.getItemDamage() == 0 && getTameState() == 1) {
			is.stackSize--;
			player.swingItem();
			setTameState((byte) 2);
			return true;
		}
		if (is != null && is.itemID == ModItems.turnip.itemID && !isInLove() && getTameState() != 0) {
			is.stackSize--;
			inLove = 600;
			return true;
		}
		if (is == null && getTameState() >= 2) {
			if (!worldObj.isRemote)
				player.mountEntity(this);
			return true;
		}
		if (is != null && is.itemID == ModItems.erebusMaterials.itemID && is.getItemDamage() == 11 && getTameState() != 0) {
			healingBuff = 5.0F;
			if (getHealth() < getMaxHealth()) {
				heal(healingBuff);
				playTameEffect(true);
				player.swingItem();
				is.stackSize--;
				if (getHealth() == getMaxHealth())
					worldObj.playSoundEffect(posX, posY, posZ, "erebus:beetlelarvamunch", 1.0F, 0.75F);
			}
			return true;
		}
		if (is != null) {
			boolean flag = false;
			if (!flag && getTameState() == 2 && is.itemID == Block.chest.blockID) {
				setTameState((byte) 3);
				playSound("mob.chickenplop", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
				flag = true;
				createChest();
			}
			if (flag) {
				if (!player.capabilities.isCreativeMode && --is.stackSize == 0)
					player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
				return true;
			}
		}
		return super.interact(player);
	}

	@Override
	public void moveEntityWithHeading(float strafe, float forward) {
		if (riddenByEntity != null) {
			prevRotationYaw = rotationYaw = riddenByEntity.rotationYaw;
			rotationPitch = riddenByEntity.rotationPitch * 0.5F;
			setRotation(rotationYaw, rotationPitch);
			rotationYawHead = renderYawOffset = rotationYaw;
			strafe = ((EntityLivingBase) riddenByEntity).moveStrafing * 0.20F;
			forward = ((EntityLivingBase) riddenByEntity).moveForward * 0.20F;
			if (forward <= 0.0F)
				forward *= 0.25F;
			stepHeight = 2.0F;
			jumpMovementFactor = getAIMoveSpeed() * 0.1F;
			if (!worldObj.isRemote) {
				setAIMoveSpeed((float) getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
				super.moveEntityWithHeading(strafe, forward);
			}
			prevLimbSwingAmount = limbSwingAmount;
			double d0 = posX - prevPosX;
			double d1 = posZ - prevPosZ;
			float f4 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;
			if (f4 > 1.0F)
				f4 = 1.0F;
			limbSwingAmount += (f4 - limbSwingAmount) * 0.4F;
			limbSwing += limbSwingAmount;
		} else {
			stepHeight = 0.5F;
			jumpMovementFactor = 0.02F;
			super.moveEntityWithHeading(strafe, forward);
		}
	}

	@Override
	public void updateRiderPosition() {
		super.updateRiderPosition();
		if (riddenByEntity instanceof EntityLivingBase) {
			double a = Math.toRadians(renderYawOffset);
			double offSetX = -Math.sin(a) * 0.1D;
			double offSetZ = Math.cos(a) * 0.1D;
			riddenByEntity.setPosition(posX - offSetX, posY + 1.1D + riddenByEntity.getYOffset(), posZ - offSetZ);
		}
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return spawnBabyAnimal(entityageable);
	}

	@Override
	public boolean isBreedingItem(ItemStack is) {
		if (getTameState() != 0)
			return is != null && is.itemID == ModItems.turnip.itemID;
		else
			return false;
	}

	public EntityBeetleLarva spawnBabyAnimal(EntityAgeable entityageable) {
		EntityBeetleLarva entityBeetleLarva = new EntityBeetleLarva(worldObj);
		entityBeetleLarva.setTame((byte) 3);
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
		return super.attackEntityAsMob(entity);
	}

	public void setTameState(byte tameState) {
		dataWatcher.updateObject(31, Byte.valueOf(tameState));
	}

	public byte getTameState() {
		return dataWatcher.getWatchableObjectByte(31);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setByte("tameState", getTameState());
		if (getTameState() == 3) {
			NBTTagList nbttaglist = new NBTTagList();
			for (int i = 2; i < beetleChest.getSizeInventory(); ++i) {
				ItemStack itemstack = beetleChest.getStackInSlot(i);
				if (itemstack != null) {
					NBTTagCompound nbttagcompound1 = new NBTTagCompound();
					nbttagcompound1.setByte("Slot", (byte) i);
					itemstack.writeToNBT(nbttagcompound1);
					nbttaglist.appendTag(nbttagcompound1);
				}
			}
			nbt.setTag("Items", nbttaglist);
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setTameState(nbt.getByte("tameState"));
		if (getTameState() == 3) {
			NBTTagList nbttaglist = nbt.getTagList("Items");
			createChest();
			for (int i = 0; i < nbttaglist.tagCount(); ++i) {
				NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
				int j = nbttagcompound1.getByte("Slot") & 255;
				if (j >= 2 && j < beetleChest.getSizeInventory())
					beetleChest.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(nbttagcompound1));
			}
		}
	}
}
