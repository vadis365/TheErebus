package erebus.tileentity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Arrays;

public class TileEntityOfferingAltar extends TileEntity {
    public ItemStack[] stack;

    public TileEntityOfferingAltar() {
        stack = new ItemStack[3];
    }

    public static TileEntityOfferingAltar instance(World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        return tileEntity instanceof TileEntityOfferingAltar ? (TileEntityOfferingAltar) tileEntity : null;
    }

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

    public ItemStack getLatestItem() {
        int latest = 0;
        ItemStack item;
        for (int i = 0; i < stack.length; i++) {
            if (stack[i] != null) latest = i;
        }
        item = stack[latest];
        stack[latest] = null;
        return item;
    }

    public boolean hasItems() {
        for (ItemStack item : stack) {
            if (item != null) {
               return true;
            }
        }
        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
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
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
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
    public void updateEntity() {
        if (!worldObj.isRemote) System.out.println(Arrays.asList(stack));
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        readFromNBT(packet.func_148857_g());
    }
}
