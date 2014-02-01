package erebus.creativetab;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

public class CreativeTabErebusSpecialItem extends CreativeTabErebus {

	public CreativeTabErebusSpecialItem(String name) {
		super(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack() {
		return new ItemStack(ModItems.erebusSpecialItem, 1, 1);
	}
}
