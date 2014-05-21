package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockDoubleHeightPlant extends ItemBlockGeneric {

	public ItemBlockDoubleHeightPlant(Block block) {
		super(block, "doubleHeightPlant");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int damage) {
		return getBlock().getIcon(2, damage < 8 ? damage + 8 : damage);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass) {
		int meta = stack.getItemDamage();
		if (meta == 4 || meta == 12 || meta == 7 || meta == 15)
			return 9415752;
		return 16777215;
	}
}