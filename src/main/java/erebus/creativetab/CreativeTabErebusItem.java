package erebus.creativetab;

import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

public class CreativeTabErebusItem extends CreativeTabErebus {

	public CreativeTabErebusItem() {
		super("erebus.item");
	}

	@Override
	public Item getTabIconItem() {
		return ModItems.erebusFood;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int func_151243_f() {
		return 0;
	}
}