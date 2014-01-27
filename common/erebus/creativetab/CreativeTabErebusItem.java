package erebus.creativetab;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

public class CreativeTabErebusItem extends CreativeTabErebus {

	public CreativeTabErebusItem(String name) {
		super(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack() {
		return new ItemStack(ModItems.erebusFood, 1, 0);
	}
}
