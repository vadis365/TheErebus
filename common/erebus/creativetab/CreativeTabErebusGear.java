package erebus.creativetab;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

public class CreativeTabErebusGear extends CreativeTabErebus {

	public CreativeTabErebusGear(String name) {
		super(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack() {
		return new ItemStack(ModItems.jadePaxel, 1, 0);
	}
}
