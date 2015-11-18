package erebus.entity;

import erebus.Erebus;
import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.core.proxy.CommonProxy;
import erebus.entity.ai.EntityAIBlockFollowOwner;
import erebus.tileentity.TileEntityBambooCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAnimatedBambooCrate extends EntityAnimatedBlock implements IInventory {

	public ItemStack[] inventory;

	public EntityAnimatedBambooCrate(World world) {
		super(world);
		inventory = new ItemStack[27];
		tasks.removeTask(aiWander);
		tasks.removeTask(aiAttackOnCollide);
		tasks.removeTask(aiAttackNearestTarget);
		tasks.addTask(1, new EntityAIBlockFollowOwner(this, 1.0D, 10.0F, 2.0F));
		isImmuneToFire = true;
	}

	public EntityAnimatedBambooCrate setContents(IInventory chest) {
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
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote && isDead)
			for (ItemStack is : inventory)
				if (is != null)
					Utils.dropStack(worldObj, (int) posX, (int) posY, (int) posZ, is);
	}

	public TileEntityBambooCrate getTile() {
		TileEntityBambooCrate crate = new TileEntityBambooCrate();
		for (int i = 0; i < inventory.length; i++)
			crate.setInventorySlotContents(i, inventory[i]);
		return crate;
	}

	@Override
	public boolean interact(EntityPlayer player) {
		if (worldObj.isRemote)
			return true;
		ItemStack is = player.inventory.getCurrentItem();
		if (is != null && is.getItem() == ModItems.wandOfAnimation) {
			setDead();
			worldObj.playSoundEffect(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), "erebus:altaroffering", 0.2F, 1.0F);
			worldObj.setBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), blockID, blockMeta, 3);
			TileEntityBambooCrate chest = Utils.getTileEntity(worldObj, MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), TileEntityBambooCrate.class);
			for (int i = 0; i < chest.getSizeInventory(); i++)
				chest.setInventorySlotContents(i, inventory[i]);
			return true;
		} else {
			player.openGui(Erebus.instance, CommonProxy.GuiID.ANIMATED_BAMBOO_CRATE.ordinal(), player.worldObj, getEntityId(), 0, 0);
			return true;
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound data) {
		super.writeEntityToNBT(data);
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

	@Override
	public void readEntityFromNBT(NBTTagCompound data) {
		super.readEntityFromNBT(data);
		NBTTagList nbttaglist = data.getTagList("Items", 10);
		inventory = new ItemStack[27];
		for (int i = 0; i < nbttaglist.tagCount(); i++) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");
			if (b0 >= 0 && b0 < inventory.length)
				inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
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
	public String getInventoryName() {
		return "container.bamber";
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
	public void openInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();
	}

	@Override
	public void markDirty() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void fall(float distance) {
	}
}