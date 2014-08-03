package erebus.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.block.BlockComposter;
import erebus.block.plants.BlockWallPlants;

public class TileEntityComposter extends TileEntity implements ISidedInventory
{
    private static final int[] slotsTop = new int[] {0};
    private static final int[] slotsBottom = new int[] {2, 1};
    private static final int[] slotsSides = new int[] {1};
    /**
     * The ItemStacks that hold the items currently being used in the composter
     */
    private ItemStack[] composterItemStacks = new ItemStack[3];
    /** The number of ticks that the composter will keep burning */
    public int composterBurnTime;
    /**
     * The number of ticks that a fresh copy of the currently-burning item would keep the composter burning for
     */
    public int currentItemBurnTime;
    /** The number of ticks that the current item has been cooking for */
    public int composterCookTime;
    private String field_145958_o;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.composterItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int slot)
    {
        return this.composterItemStacks[slot];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int slot, int itemCount)
    {
        if (this.composterItemStacks[slot] != null)
        {
            ItemStack itemstack;

            if (this.composterItemStacks[slot].stackSize <= itemCount)
            {
                itemstack = this.composterItemStacks[slot];
                this.composterItemStacks[slot] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.composterItemStacks[slot].splitStack(itemCount);

                if (this.composterItemStacks[slot].stackSize == 0)
                {
                    this.composterItemStacks[slot] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if (this.composterItemStacks[slot] != null)
        {
            ItemStack itemstack = this.composterItemStacks[slot];
            this.composterItemStacks[slot] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int slot, ItemStack is)
    {
        this.composterItemStacks[slot] = is;

        if (is != null && is.stackSize > this.getInventoryStackLimit())
        {
            is.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory
     */
    public String getInventoryName()
    {
        return this.hasCustomInventoryName() ? this.field_145958_o : "container.composter";
    }

    /**
     * Returns if the inventory is named
     */
    public boolean hasCustomInventoryName()
    {
        return this.field_145958_o != null && this.field_145958_o.length() > 0;
    }

    public void func_145951_a(String p_145951_1_)
    {
        this.field_145958_o = p_145951_1_;
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        this.composterItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.composterItemStacks.length)
            {
                this.composterItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.composterBurnTime = nbt.getShort("BurnTime");
        this.composterCookTime = nbt.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(this.composterItemStacks[1]);

        if (nbt.hasKey("CustomName", 8))
        {
            this.field_145958_o = nbt.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setShort("BurnTime", (short)this.composterBurnTime);
        nbt.setShort("CookTime", (short)this.composterCookTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.composterItemStacks.length; ++i)
        {
            if (this.composterItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.composterItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbt.setTag("Items", nbttaglist);

        if (this.hasCustomInventoryName())
        {
            nbt.setString("CustomName", this.field_145958_o);
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot.
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int cookTime)
    {
        return this.composterCookTime * cookTime / 200;
    }

    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int burnTime)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }

        return this.composterBurnTime * burnTime / this.currentItemBurnTime;
    }

    /**
     * Furnace isBurning
     */
    public boolean isBurning()
    {
        return this.composterBurnTime > 0;
    }

    public void updateEntity()
    {
        boolean flag = this.composterBurnTime > 0;
        boolean flag1 = false;

        if (this.composterBurnTime > 0)
        {
            --this.composterBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.composterBurnTime != 0 || this.composterItemStacks[1] != null && this.composterItemStacks[0] != null)
            {
                if (this.composterBurnTime == 0 && this.canSmelt())
                {
                    this.currentItemBurnTime = this.composterBurnTime = getItemBurnTime(this.composterItemStacks[1]);

                    if (this.composterBurnTime > 0)
                    {
                        flag1 = true;

                        if (this.composterItemStacks[1] != null)
                        {
                            --this.composterItemStacks[1].stackSize;

                            if (this.composterItemStacks[1].stackSize == 0)
                            {
                                this.composterItemStacks[1] = composterItemStacks[1].getItem().getContainerItem(composterItemStacks[1]);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt())
                {
                    ++this.composterCookTime;

                    if (this.composterCookTime == 200)
                    {
                        this.composterCookTime = 0;
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.composterCookTime = 0;
                }
            }

            if (flag != this.composterBurnTime > 0)
            {
                flag1 = true;
                BlockComposter.updateFurnaceBlockState(this.composterBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    /**
     * Returns true if the composter can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (this.composterItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.composterItemStacks[0]);
            if (itemstack == null) return false;
            if (this.composterItemStacks[2] == null) return true;
            if (!this.composterItemStacks[2].isItemEqual(itemstack)) return false;
            int result = composterItemStacks[2].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.composterItemStacks[2].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
        }
    }

    /**
     * Turn one item from the composter source stack into the appropriate smelted item in the composter result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.composterItemStacks[0]);

            if (this.composterItemStacks[2] == null)
            {
                this.composterItemStacks[2] = itemstack.copy();
            }
            else if (this.composterItemStacks[2].getItem() == itemstack.getItem())
            {
                this.composterItemStacks[2].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
            }

            --this.composterItemStacks[0].stackSize;

            if (this.composterItemStacks[0].stackSize <= 0)
            {
                this.composterItemStacks[0] = null;
            }
        }
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the composter burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack is)
    {
        if (is == null)
        {
            return 0;
        }
        else
        {
            Item item = is.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);

                if (block == ModBlocks.erebusWallPlants)
                {
                    return 800;
                }
            }
        }

            return 0;
    }

    public static boolean isItemFuel(ItemStack is)
    {
        /**
         * Returns the number of ticks that the supplied fuel item will keep the composter burning, or 0 if the item isn't
         * fuel
         */
        return getItemBurnTime(is) > 0;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openInventory() {}

    public void closeInventory() {}

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int slot, ItemStack is)
    {
        return slot == 2 ? false : (slot == 1 ? isItemFuel(is) : true);
    }

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     */
    public int[] getAccessibleSlotsFromSide(int slot)
    {
        return slot == 0 ? slotsBottom : (slot == 1 ? slotsTop : slotsSides);
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canInsertItem(int slot, ItemStack is, int side)
    {
        return this.isItemValidForSlot(slot, is);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canExtractItem(int slot, ItemStack is, int side)
    {
        return side != 0 || slot != 1 || is.getItem() == Items.bucket;
    }
}
