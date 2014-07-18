package erebus.entity;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import erebus.Erebus;
import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.core.proxy.CommonProxy;
import erebus.entity.ai.EntityAIHarvestCrops;

public class EntityBlackAnt extends EntityMob implements IInventory {

	private final EntityAIPanic aiPanic = new EntityAIPanic(this, 0.8D);
	private final EntityAIHarvestCrops aiHarvestCrops = new EntityAIHarvestCrops(this, 0.6D, 1);
	private final EntityAIWander aiWander = new EntityAIWander(this, 0.6D);
	public boolean setAttributes; // needed for logic later
	public boolean isEating;

	protected ItemStack[] inventory;
	private final int TOOL_SLOT = 0;
	private final int CROP_ID_SLOT = 1;
	private final int INVENTORY_SLOT = 2;

	public EntityBlackAnt(World world) {
		super(world);
		stepHeight = 1.0F;
		setAttributes = false;
		setSize(1.3F, 0.55F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, aiWander);
		tasks.addTask(2, aiPanic);
		tasks.addTask(3, new EntityAILookIdle(this));
		inventory = new ItemStack[3];
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(24, new Integer(0));
		dataWatcher.addObject(25, new Integer(0));
		dataWatcher.addObject(26, new Integer(0));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected boolean canDespawn() {
		return true;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:fireantsound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:fireanthurt";
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
	public int getMaxSpawnedInChunk() {
		return 5;
	}

	public void openGUI(EntityPlayer player) {
		player.openGui(Erebus.instance, CommonProxy.GUI_ID_ANT_INVENTORY, player.worldObj, getEntityId(), 0, 0);
	}

	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack is = player.inventory.getCurrentItem();
		if (is != null && is.getItem() == ModItems.antTamingAmulet && is.hasTagCompound() && is.stackTagCompound.hasKey("homeX")) {
			setDropPoint(is.getTagCompound().getInteger("homeX"), is.getTagCompound().getInteger("homeY"), is.getTagCompound().getInteger("homeZ"));
			player.swingItem();
			return true;
		}
		else {
			openInventory();
			openGUI(player);
		}
		return super.interact(player);
	}

	public void setIsEating(boolean isEating) {
		this.isEating = isEating;
	}

	public void setBlockHarvested(Block block, int meta) {
		Random rand = new Random();
		if (block != null) {
			Utils.dropStack(worldObj, (int) posX, (int) posY, (int) posZ, new ItemStack(block.getItemDropped(meta, rand, 0), rand.nextInt(2) + 1));
			Utils.dropStack(worldObj, (int) posX, (int) posY, (int) posZ, new ItemStack(block.getItemDropped(0, rand, 0), rand.nextInt(2) + 1));
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote)
			if (!hasCustomNameTag())
				setCustomNameTag("X: " + getDropPointX() + " Y: " + getDropPointX() + " Z: " + getDropPointX());
		
		if (!worldObj.isRemote && !setAttributes) {
			openInventory();
			closeInventory();
			setAttributes = true;
		}
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int size) {
		if (inventory[slot] != null) {
			ItemStack itemstack;
			if (inventory[slot].stackSize <= size) {
				itemstack = inventory[slot];
				inventory[slot] = null;
				return itemstack;
			} else {
				itemstack = inventory[slot].splitStack(size);
				if (inventory[slot].stackSize == 0)
					inventory[slot] = null;
				return itemstack;
			}
		} else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (inventory[slot] != null) {
			ItemStack itemstack = inventory[slot];
			inventory[slot] = null;
			return itemstack;
		} else
			return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();
	}

	@Override
	public String getInventoryName() {
		return "container.antInventory";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public final boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		if (slot == TOOL_SLOT)
			return stack.getItem() == Items.shears || stack.getItem() == Items.bucket || stack.getItem() instanceof ItemHoe;

		return slot == CROP_ID_SLOT || slot == INVENTORY_SLOT;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		
		setDropPoint(nbt.getInteger("dropPointX"), nbt.getInteger("dropPointY"), nbt.getInteger("dropPointZ"));
		
		NBTTagList tags = nbt.getTagList("Items", 10);
		inventory = new ItemStack[getSizeInventory()];

		for (int i = 0; i < tags.tagCount(); i++) {
			NBTTagCompound data = tags.getCompoundTagAt(i);
			int j = data.getByte("Slot") & 255;

			if (j >= 0 && j < inventory.length)
				inventory[j] = ItemStack.loadItemStackFromNBT(data);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		
		nbt.setInteger("dropPointX", getDropPointX());
		nbt.setInteger("dropPointY", getDropPointY());
		nbt.setInteger("dropPointZ", getDropPointZ());
		
		NBTTagList tags = new NBTTagList();

		for (int i = 0; i < inventory.length; i++)
			if (inventory[i] != null) {
				NBTTagCompound data = new NBTTagCompound();
				data.setByte("Slot", (byte) i);
				inventory[i].writeToNBT(data);
				tags.appendTag(data);
			}

		nbt.setTag("Items", tags);
	}

	@Override
	public void openInventory() {
		if(worldObj.isRemote)
			return;
		tasks.removeTask(aiWander);
		// TODO tasks.removeTask(aiPlantCrops);
		tasks.removeTask(aiHarvestCrops);
		// TODO tasks.removeTask(aiCollectCrops);
		System.out.println("Open");
		System.out.println("Tasks Removed");
	}

	@Override
	public void closeInventory() {
		if(worldObj.isRemote)
			return;
		
		if (getStackInSlot(TOOL_SLOT) == null)
			tasks.addTask(1, aiWander);

		if (getStackInSlot(TOOL_SLOT) != null && getStackInSlot(TOOL_SLOT).getItem() instanceof ItemHoe) {
			//TO DO tasks.addTask(1, aiPlantCrops);
			System.out.println("Planter Set");
		}

		if (getStackInSlot(TOOL_SLOT) != null && getStackInSlot(TOOL_SLOT).getItem() instanceof ItemBucket) {
			//TO DO tasks.addTask(1, aiCollectCrops);
			System.out.println("Collector Set");
		}

		if (getStackInSlot(TOOL_SLOT) != null && getStackInSlot(TOOL_SLOT).getItem() instanceof ItemShears) {
			tasks.addTask(1, aiHarvestCrops);
			System.out.println("Harvester Set");
		}
		updateAITasks();
		System.out.println("Close");
	}

	@Override
	public void markDirty() {
	}
	
	public void setDropPoint(int x, int y, int z) {
		dataWatcher.updateObject(24, Integer.valueOf(x));
		dataWatcher.updateObject(25, Integer.valueOf(y));
		dataWatcher.updateObject(26, Integer.valueOf(z));
	}

	public int getDropPointX() {
		return dataWatcher.getWatchableObjectInt(24);
	}

	public int getDropPointY() {
		return dataWatcher.getWatchableObjectInt(25);
	}

	public int getDropPointZ() {
		return dataWatcher.getWatchableObjectInt(26);
	}
}