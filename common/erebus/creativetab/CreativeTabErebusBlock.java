package erebus.creativetab;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;

public class CreativeTabErebusBlock extends CreativeTabErebus {

	public CreativeTabErebusBlock(String name) {
		super(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack() {
		return new ItemStack(ModBlocks.umberstone, 1, 0);
	}
}
