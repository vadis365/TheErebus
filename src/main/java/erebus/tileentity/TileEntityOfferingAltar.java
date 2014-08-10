package erebus.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class TileEntityOfferingAltar extends TileEntity implements IExtendedEntityProperties {
    public ItemStack[] stack;

    public boolean canAddItem() {
        for (ItemStack item : stack) {
            if (item == null) {
                return true;
            }
        }
        return false;
    }

    public boolean addItem(Item item) {
        if (canAddItem()) {
            for (int i = 0; i < stack.length; i++) {
                if (stack[i] == null) {
                    stack[i] = new ItemStack(item);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void saveNBTData(NBTTagCompound tag) {
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < stack.length; ++i) {
            if (stack[i] != null) {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("slot", (byte) i);
                stack[i].writeToNBT(compound);
                list.appendTag(compound);
            }
        }
        tag.setTag("items", list);
    }

    @Override
    public void loadNBTData(NBTTagCompound tag) {
        NBTTagList list = tag.getTagList("items", 10);
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound compound = list.getCompoundTagAt(i);
            int j = compound.getByte("slot") & 255;

            if (j >= 0 && j < stack.length) {
                stack[j] = ItemStack.loadItemStackFromNBT(compound);
            }
        }
    }

    @Override
    public void init(Entity entity, World world) {
        stack = new ItemStack[3];
    }
}
