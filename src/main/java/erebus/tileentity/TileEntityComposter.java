package erebus.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.recipes.ComposterRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityComposter extends TileEntityBasicInventory {

	public int composterBurnTime;
	public int currentItemBurnTime;
	public int composterCookTime;

	public TileEntityComposter() {
		super(3, "container.composter");
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		composterBurnTime = nbt.getShort("BurnTime");
		composterCookTime = nbt.getShort("CookTime");
		currentItemBurnTime = getItemBurnTime(inventory[1]);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("BurnTime", (short) composterBurnTime);
		nbt.setShort("CookTime", (short) composterCookTime);
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int cookTime) {
		return composterCookTime * cookTime / 200;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int burnTime) {
		if (currentItemBurnTime == 0)
			currentItemBurnTime = 200;

		return composterBurnTime * burnTime / currentItemBurnTime;
	}

	public boolean isBurning() {
		return composterBurnTime > 0;
	}

	@Override
	public void updateEntity() {
		boolean flag = composterBurnTime > 0;
		boolean flag1 = false;

		if (composterBurnTime > 0)
			composterBurnTime--;

		if (!worldObj.isRemote) {
			if (composterBurnTime != 0 || inventory[1] != null && inventory[0] != null) {
				if (composterBurnTime == 0 && canSmelt()) {
					currentItemBurnTime = composterBurnTime = getItemBurnTime(inventory[1]);

					if (composterBurnTime > 0) {
						flag1 = true;

						if (inventory[1] != null) {
							--inventory[1].stackSize;

							if (inventory[1].stackSize == 0)
								inventory[1] = inventory[1].getItem().getContainerItem(inventory[1]);
						}
					}
				}

				if (isBurning() && canSmelt()) {
					++composterCookTime;

					if (composterCookTime == 200) {
						composterCookTime = 0;
						smeltItem();
						flag1 = true;
					}
				} else
					composterCookTime = 0;
			}

			if (flag != composterBurnTime > 0) {
				flag1 = true;
				boolean tileActive = composterBurnTime > 0;
				int meta = getBlockMetadata();
				boolean blockActive = meta == 1;
				if (blockActive && !tileActive)
					worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
				if (!blockActive && tileActive)
					worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
			}
		}

		if (flag1)
			markDirty();
	}

	private boolean canSmelt() {
		if (inventory[0] == null)
			return false;
		else {
			ItemStack itemstack = ComposterRegistry.isCompostable(inventory[0]);
			if (itemstack == null)
				return false;
			if (inventory[2] == null)
				return true;
			if (!inventory[2].isItemEqual(itemstack))
				return false;
			int result = inventory[2].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit() && result <= inventory[2].getMaxStackSize(); // Forge
			// BugFix:
			// Make
			// it
			// respect
			// stack
			// sizes
			// properly.
		}
	}

	public void smeltItem() {
		if (canSmelt()) {
			ItemStack itemstack = ComposterRegistry.isCompostable(inventory[0]);

			if (inventory[2] == null)
				inventory[2] = itemstack.copy();
			else if (inventory[2].getItem() == itemstack.getItem())
				inventory[2].stackSize += itemstack.stackSize; // Forge BugFix:
			// Results may
			// have multiple
			// items

			--inventory[0].stackSize;

			if (inventory[0].stackSize <= 0)
				inventory[0] = null;
		}
	}

	public static int getItemBurnTime(ItemStack is) {
		if (is == null)
			return 0;
		else {
			Item item = is.getItem();

			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
				Block block = Block.getBlockFromItem(item);

				if (block == ModBlocks.wallPlants && is.getItemDamage() == 1)
					return 800;

				if (block == ModBlocks.wallPlantsCultivated && is.getItemDamage() == 1)
					return 400;
			}
		}

		return 0;
	}

	public static boolean isItemFuel(ItemStack is) {
		return getItemBurnTime(is) > 0;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is) {
		return slot == 2 ? false : slot == 1 ? isItemFuel(is) : true;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack is, int side) {
		return isItemValidForSlot(slot, is);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack is, int side) {
		return slot == 2;
	}
}