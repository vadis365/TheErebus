package erebus.item.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.lib.EnumWood;
import erebus.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class ItemBlockPlanks extends ItemBlockGeneric {

	public ItemBlockPlanks(Block block) {
		super(block);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		int meta = stack.getItemDamage();
		if (meta >= EnumWood.values().length)
			return "Error";
		EnumWood wood = EnumWood.values()[meta];
		return StatCollector.translateToLocal("tile." + Reference.MOD_ID + ".planks_" + wood.getUnlocalisedName() + ".name");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return getBlock().getIcon(0, meta);
	}
}