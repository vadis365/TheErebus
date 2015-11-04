package erebus.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.item.ItemMaterials;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGlowingJar extends TileEntity {

	@SideOnly(Side.CLIENT)
	private EntityItem ghostItem;

	@SideOnly(Side.CLIENT)
	public EntityItem getGhostItem() {
		if (ghostItem == null) {
			ghostItem = new EntityItem(worldObj);
			ghostItem.hoverStart = 0.0F;
			ghostItem.setEntityItemStack(new ItemStack(ModItems.materials, 1, ItemMaterials.DATA.bioLuminescence.ordinal()));
		}
		return ghostItem;
	}

	@Override
	public boolean canUpdate() {
		return false;
	}
}