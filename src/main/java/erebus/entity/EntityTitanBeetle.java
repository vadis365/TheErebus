package erebus.entity;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.item.ItemErebusFood;
import erebus.item.ItemMaterials;
import erebus.tileentity.TileEntityTitanChest;
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
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityTitanBeetle extends EntityTameable {

	private final EntityAINearestAttackableTarget aiNearestAttackableTarget = new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true);
	boolean isOpen;
	float openticks;
	int shagCount;
	public ItemStack[] inventory;

	public EntityTitanBeetle(World world) {
		super(world);
		inventory = new ItemStack[27];
		stepHeight = 2.0F;
		setSize(2.5F, 1.2F);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAttackOnCollide(this, 0.5D, true));
		tasks.addTask(2, new EntityAIMate(this, 0.5D));
		tasks.addTask(3, new EntityAITempt(this, 0.5D, ModItems.turnip, false));
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
		if (worldObj.isRemote && getTameState() == 4) {
			double a = Math.toRadians(renderYawOffset);
			double offSetX = -Math.sin(a) * 1.2D;
			double offSetZ = Math.cos(a) * 1.2D;
			enderChestParticles(worldObj, posX - offSetX, posY + 1.2, posZ - offSetZ, rand);
		}
		if (shagCount > 0)
			shagCount--;
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
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.75D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:bombardierbeetlesound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:bombardierbeetlehurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness(1.0F);
		if (light >= 0F)
			return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox) && worldObj.difficultySetting != EnumDifficulty.PEACEFUL;
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
	public boolean allowLeashing() {
		return !canDespawn() && super.allowLeashing();
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (getTameState() >= 2)
			entityDropItem(ItemMaterials.DATA.rhinoRidingKit.makeStack(), 0.0F);
		int var3 = 1 + rand.nextInt(3) + rand.nextInt(1 + looting);
		for (int a = 0; a < var3; ++a)
			entityDropItem(ItemMaterials.DATA.plateExo.makeStack(), 0.0F);

		entityDropItem(new ItemStack(ModItems.food, 1 + rand.nextInt(1), isBurning() ? ItemErebusFood.FoodType.TITAN_CHOP_COOKED.ordinal() : ItemErebusFood.FoodType.TITAN_CHOP_RAW.ordinal()), 0.0F);
		dropChests();
	}

	public void dropChests() {
		if (!worldObj.isRemote) {
			if (getTameState() == 3)
				dropItem(Item.getItemFromBlock(Blocks.chest), 1);
			if (getTameState() == 4)
				dropItem(Item.getItemFromBlock(Blocks.ender_chest), 1);
		}
	}

	public void openGUI(EntityPlayer player) {
		if (!worldObj.isRemote && (riddenByEntity == null || riddenByEntity == player) && getTameState() != 0) {
			if (getTameState() == 3)
				player.displayGUIChest(new TileEntityTitanChest(this));
			if (getTameState() == 4) {
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
		if (is != null && is.getItem() == ModItems.materials && is.getItemDamage() == ItemMaterials.DATA.beetleTamingAmulet.ordinal() && getTameState() == 0) {
			healingBuff = 20F;
			is.stackSize--;
			setTameState((byte) 1);
			playTameEffect(true);
			player.swingItem();
			tasks.removeTask(aiNearestAttackableTarget);
			setAttackTarget((EntityLivingBase) null);
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0D);
			heal(healingBuff);
			return true;
		}
		if (is != null && is.getItem() == ModItems.materials && is.getItemDamage() == ItemMaterials.DATA.rhinoRidingKit.ordinal() && getTameState() == 1) {
			is.stackSize--;
			player.swingItem();
			setTameState((byte) 2);
			return true;
		}
		if (is != null && is.getItem() == ModItems.turnip && !shagging() && getTameState() != 0) {
			is.stackSize--;
			shagCount = 600;
			worldObj.playSoundEffect(posX, posY, posZ, "erebus:beetlelarvamunch", 1.0F, 0.75F);
			return true;
		}
		if (is == null && getTameState() >= 2) {
			if (!worldObj.isRemote)
				player.mountEntity(this);
			return true;
		}
		if (is != null && is.getItem() == ModItems.materials && is.getItemDamage() == ItemMaterials.DATA.bambooShoot.ordinal() && getTameState() != 0) {
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
			if (!flag && getTameState() == 2 && is.getItem() == Item.getItemFromBlock(Blocks.chest)) {
				setTameState((byte) 3);
				playSound("mob.chickenplop", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
				flag = true;
			}
			if (flag && getTameState() == 3) {
				if (!player.capabilities.isCreativeMode && --is.stackSize == 0)
					player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
				return true;
			}
			if (!flag && getTameState() == 2 && is.getItem() == Item.getItemFromBlock(Blocks.ender_chest)) {
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

	public boolean shagging() {
		return shagCount > 0;
	}

	@Override
	public void moveEntityWithHeading(float strafe, float forward) {
		if (riddenByEntity != null) {
			prevRotationYaw = rotationYaw = riddenByEntity.rotationYaw;
			rotationPitch = riddenByEntity.rotationPitch * 0.5F;
			setRotation(rotationYaw, rotationPitch);
			rotationYawHead = renderYawOffset = rotationYaw;
			strafe = ((EntityLivingBase) riddenByEntity).moveStrafing * 0.3F;
			forward = ((EntityLivingBase) riddenByEntity).moveForward * 0.3F;
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
			Erebus.proxy.spawnCustomParticle("portal", worldObj, x, y, z, velX, velY, velZ);
		}
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return spawnBabyAnimal(entityageable);
	}

	@Override
	public boolean isBreedingItem(ItemStack is) {
		if (getTameState() != 0)
			return is != null && is.getItem() == ModItems.turnip;
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
		if (entity != null && getDistanceToEntity(entity) <= 2.5F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
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
			NBTTagList nbttaglist = data.getTagList("Items", 10);
			inventory = new ItemStack[27];
			for (int i = 0; i < nbttaglist.tagCount(); i++) {
				NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
				byte b0 = nbttagcompound1.getByte("Slot");
				if (b0 >= 0 && b0 < inventory.length)
					inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}
}