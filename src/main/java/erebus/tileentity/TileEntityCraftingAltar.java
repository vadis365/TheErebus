package erebus.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.network.PacketPipeline;
import erebus.network.client.PacketOfferingAltar;
import erebus.recipes.CraftingAltarRecipe;

public class TileEntityCraftingAltar extends TileEntityOfferingAltar
{
	@SideOnly(Side.CLIENT)
	public float rotation;
	@SideOnly(Side.CLIENT)
	private static final float ROTATION_SPEED = 0.5F;

	public TileEntityCraftingAltar()
	{
		super(10, "craftingAltar");
	}

	@Override
	public void updateEntity()
	{
		if (worldObj.isRemote)
		{
			rotation += ROTATION_SPEED;
			if (rotation >= 360.0F)
			{
				rotation -= 360.0F;
			}
		} else
		{
			ItemStack[] inputs = new ItemStack[8];
			for (int i = 1; i < getSizeInventory() - 1; i++)
			{
				inputs[i - 1] = inventory[i];
			}
			ItemStack output = CraftingAltarRecipe.getOutput(inventory[0], inputs);
			if (output != null)
			{
				for (int i = 0; i < getSizeInventory() - 1; i++)
				{
					if (inventory[i] != null)
					{
						if (--inventory[i].stackSize <= 0)
						{
							inventory[i] = null;
						}
					}
				}
				inventory[getSizeInventory() - 1] = ItemStack.copyItemStack(output);
				markDirty();
			}
		}
	}

	@Override
	public void markDirty()
	{
		super.markDirty();

		if (worldObj != null && !worldObj.isRemote)
		{
			NBTTagCompound nbt = new NBTTagCompound();
			writeToNBT(nbt);
			PacketPipeline.sendToAll(new PacketOfferingAltar(xCoord, yCoord, zCoord, nbt));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox()
	{
		return AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord, zCoord - 1, xCoord + 2, yCoord + 3, zCoord + 2);
	}
}