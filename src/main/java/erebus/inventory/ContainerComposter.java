package erebus.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.tileentity.TileEntityComposter;

public class ContainerComposter extends Container
{
    private TileEntityComposter tileComposter;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerComposter(InventoryPlayer player, TileEntityComposter composter)
    {
        this.tileComposter = composter;
        this.addSlotToContainer(new Slot(composter, 0, 56, 17));
        this.addSlotToContainer(new Slot(composter, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnace(player.player, composter, 2, 116, 35));
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting crafting)
    {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, tileComposter.composterCookTime);
        crafting.sendProgressBarUpdate(this, 1, tileComposter.composterBurnTime);
        crafting.sendProgressBarUpdate(this, 2, tileComposter.currentItemBurnTime);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.tileComposter.composterCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileComposter.composterCookTime);
            }

            if (this.lastBurnTime != this.tileComposter.composterBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileComposter.composterBurnTime);
            }

            if (this.lastItemBurnTime != this.tileComposter.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileComposter.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.tileComposter.composterCookTime;
        this.lastBurnTime = this.tileComposter.composterBurnTime;
        this.lastItemBurnTime = this.tileComposter.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value)
    {
        if (id == 0)
        {
            this.tileComposter.composterCookTime = value;
        }

        if (id == 1)
        {
            this.tileComposter.composterBurnTime = value;
        }

        if (id == 2)
        {
            this.tileComposter.currentItemBurnTime = value;
        }
    }

    public boolean canInteractWith(EntityPlayer player)
    {
        return this.tileComposter.isUseableByPlayer(player);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotIndex == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (slotIndex != 1 && slotIndex != 0)
            {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityComposter.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (slotIndex >= 3 && slotIndex < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (slotIndex >= 30 && slotIndex < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}