package erebus.tileentity;

import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityCraftingAltar extends TileEntity
{
	@SideOnly(Side.CLIENT)
	public float rotation;
	@SideOnly(Side.CLIENT)
	private static final float ROTATION_SPEED = 0.01F;

	@Override
	public void updateEntity()
	{
		if (worldObj.isRemote)
		{
			rotation += 0.5F;
			if (rotation >= 360.0F)
			{
				rotation -= 360.0F;
			}
		}
	}
}