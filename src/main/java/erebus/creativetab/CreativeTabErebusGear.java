package erebus.creativetab;

import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

public class CreativeTabErebusGear extends CreativeTabErebus {

	public CreativeTabErebusGear() {
		super("erebus.gear");
	}

	@Override
	public Item getTabIconItem() {
		return ModItems.jadePaxel;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int func_151243_f() {
		return 0;
	}
}