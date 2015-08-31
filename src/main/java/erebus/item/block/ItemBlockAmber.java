package erebus.item.block;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockAmber extends ItemCloth {

	public ItemBlockAmber(Block block) {
		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getUnlocalizedName() + is.getItemDamage();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return field_150939_a.func_149735_b(2, meta);
	}
}