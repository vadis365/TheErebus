package erebus.entity;

import java.util.Random;

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
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.item.ItemErebusMaterial.DATA;
import erebus.item.ItemErebusSpecial;
import erebus.tileentity.TileEntityTitanChest;

public class EntityTitanBeetle extends EntityTameable {

	private final EntityAINearestAttackableTarget aiNearestAttackableTarget = new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true);
	boolean isOpen;
	float openticks;
	public ItemStack[] inventory;
	
	public EntityTitanBeetle(World world) {
		super(world);
		inventory = new ItemStack[27];
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
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(21, 0.0F);
		dataWatcher.addObject(31, new Byte((byte) 0));
	}
	
	public EntityTitanBeetle setContents(IInventory chest) {
		if (chest == null)
			return this;

		inventory = new ItemStack[chest.getSizeInventory()];
		for (int i = 0; i < chest.getSizeInventory(); i++) {
			if (chest.getStackInSlot(i) == null)
				continue;
			inventory[i] = chest.getStackInSlot(i).copy();
			chest.setInventorySlotContents(i, null);
		}
		return this;
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
		if (!worldObj.isRemote && isDead)
			for (ItemStack is : inventory)
				if (is != null)
					Utils.dropStack(worldObj, (int) posX, (int) posY, (int) posZ, is);
		if (worldObj.isRemote && getTameState()==4) {
			double a = Math.toRadians(renderYawOffset);
			double offSetX = -Math.sin(a) * 1.5D;
			double offSetZ = Math.cos(a) * 1.5D;
			randomDisplayTick(worldObj, posX - offSetX, posY+1, posZ - offSetZ, rand);
		}
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
	protected void playStepSound(int x, int y, int z, int blockID) {
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
		int var3 = 1 + rand.nextInt(3) + rand.nextInt(1 + looting);
		for (int a = 0; a < var3; ++a)
			entityDropItem(new ItemStack(ModItems.erebusMaterials, 1, DATA.plateExo.ordinal()), 0.0F);
		dropChests();
	}

	public void dropChests() {
		if (!worldObj.isRemote) {
			if (getTameState() == 3)
				dropItem(Block.chest.blockID, 1);
			if (getTameState() == 4)
				dropItem(Block.enderChest.blockID, 1);
		}
	}

	public void openGUI(EntityPlayer player) {
		if (!worldObj.isRemote && (riddenByEntity == null || riddenByEntity == player) && getTameState() != 0) {
			if(getTameState()==3)
				player.displayGUIChest(new TileEntityTitanChest(this));
			if(getTameState()==4) {
				InventoryEnderChest inventoryenderchest = player.getInventoryEnderChest();
				player.displayGUIChest(inventoryenderchest);
			}
		}
	}

	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack is = player.inventory.getCurrentItem();
		float healingBuff = 0.0F;
		if (getTameState() == 3 && player.isSneaking()) {
			worldObj.playSoundEffect(posX, posY + 0.5D, posZ, "random.chestopen", 0.5F, 0.9F);
			openGUI(player);
			return true;
			}
		if (getTameState() == 4 && player.isSneaking()) {
			openGUI(player);
			return true;
			}
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
			}
			if (flag && getTameState() == 3) {
				if (!player.capabilities.isCreativeMode && --is.stackSize == 0)
					player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
				return true;
			}
			if (!flag && getTameState() == 2 && is.itemID == Block.enderChest.blockID) {
				setTameState((byte) 4);
				playSound("mob.chickenplop", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
				flag = true;
			}
			if (flag && getTameState() == 4) {
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
	
	@SideOnly(Side.CLIENT)
	   public void randomDisplayTick(World world, double d, double f, double e, Random rand) {
	        for (int l = 0; l < 3; ++l) {
	            double d0 = ((float)d + rand.nextFloat());
	            double d1 = ((float)f + rand.nextFloat());
	            d0 = ((float)e + rand.nextFloat());
	            double d2 = 0.0D;
	            double d3 = 0.0D;
	            double d4 = 0.0D;
	            int i1 = rand.nextInt(2) * 2 - 1;
	            int j1 = rand.nextInt(2) * 2 - 1;
	            d2 = (rand.nextFloat() - 0.5D) * 0.125D;
	            d3 = (rand.nextFloat() - 0.5D) * 0.125D;
	            d4 = (rand.nextFloat() - 0.5D) * 0.125D;
	            double d5 = e + 0.5D + 0.25D * j1;
	            d4 = (rand.nextFloat() * 1.0F * j1);
	            double d6 = d + 0.5D + 0.25D * i1;
	            d2 = (rand.nextFloat() * 1.0F * i1);
	            world.spawnParticle("portal", d6, d1, d5, d2, d3, d4);
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
		entity.attackEntityFrom(DamageSource.causeMobDamage(this), 4);
		return super.attackEntityAsMob(entity);
	}

	public void setTameState(byte tameState) {
		dataWatcher.updateObject(31, Byte.valueOf(tameState));
	}

	public byte getTameState() {
		return dataWatcher.getWatchableObjectByte(31);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound data) {
		super.writeEntityToNBT(data);
		data.setByte("tameState", getTameState());
		if (getTameState() == 3) {
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < inventory.length; i++)
			if (inventory[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		data.setTag("Items", nbttaglist);
		}
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound data) {
		super.readEntityFromNBT(data);
		setTameState(data.getByte("tameState"));
		if (getTameState() == 3) {
		NBTTagList nbttaglist = data.getTagList("Items");
		inventory = new ItemStack[27];
		for (int i = 0; i < nbttaglist.tagCount(); i++) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");
			if (b0 >= 0 && b0 < inventory.length)
				inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}
}
