package erebus.tileentity;

import erebus.ModBlocks;
import erebus.recipes.ComposterRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityComposter extends TileEntityBasicInventory implements ITickable {

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
		currentItemBurnTime = getItemBurnTime(getInventory().get(1));
	}

	@Override
	public NBTTagCompound  writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("BurnTime", (short) composterBurnTime);
		nbt.setShort("CookTime", (short) composterCookTime);
		return nbt;
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
	public void update() {
		boolean flag = composterBurnTime > 0;
		boolean flag1 = false;

		if (composterBurnTime > 0)
			composterBurnTime--;

		if (!getWorld().isRemote) {
			if (composterBurnTime != 0 || !getInventory().get(1).isEmpty() && !getInventory().get(0).isEmpty()) {
				if (composterBurnTime == 0 && canSmelt()) {
					currentItemBurnTime = composterBurnTime = getItemBurnTime(getInventory().get(1));

					if (composterBurnTime > 0) {
						flag1 = true;

						if (getInventory().get(1).isEmpty()) {
							getInventory().get(1).shrink(1);

							if (getInventory().get(1).getCount() == 0)
								getInventory().set(1, getInventory().get(1).getItem().getContainerItem(getInventory().get(1)));
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
					getWorld().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
				if (!blockActive && tileActive)
					getWorld().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
			}
		}

		if (flag1)
			markDirty();
	}

	private boolean canSmelt() {
		if (getInventory().get(0).isEmpty())
			return false;
		else {
			ItemStack itemstack = ComposterRegistry.isCompostable(getInventory().get(0));
			if (itemstack.isEmpty())
				return false;
			if (getInventory().get(2).isEmpty())
				return true;
			if (!getInventory().get(2).isItemEqual(itemstack))
				return false;
			int result = getInventory().get(2).getCount() + itemstack.getCount();
			return result <= getInventoryStackLimit() && result <= getInventory().get(2).getMaxStackSize(); // Forge
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
			ItemStack itemstack = ComposterRegistry.isCompostable(getInventory().get(0));

			if (getInventory().get(2).isEmpty())
				getInventory().set(2, itemstack.copy());
			else if (getInventory().get(2).getItem() == itemstack.getItem())
				getInventory().get(2).grow(itemstack.getCount());// Forge BugFix:
			// Results may
			// have multiple
			// items

			getInventory().get(0).shrink(1);

			if (getInventory().get(0).getCount() <= 0)
				getInventory().set(0, ItemStack.EMPTY);
		}
	}

	public static int getItemBurnTime(ItemStack is) {
		if (is.isEmpty())
			return 0;
		else {
			Item item = is.getItem();

			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) {
				Block block = Block.getBlockFromItem(item);

				if (block == ModBlocks.WALL_PLANTS && is.getItemDamage() == 1)
					return 800;

				if (block == ModBlocks.WALL_PLANTS_CULTIVATED && is.getItemDamage() == 1)
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

}