package erebus.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import erebus.ModBlocks;

public class TileEntityGlowGem extends TileEntity {

	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			return;
	}

}