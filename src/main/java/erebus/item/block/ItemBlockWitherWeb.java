package erebus.item.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.helper.Utils;

public class ItemBlockWitherWeb extends ItemBlock {

	public ItemBlockWitherWeb(int id) {
		super(id);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass) {
		return Utils.getColour(0, 0, 0);
	}
}