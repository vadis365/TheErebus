package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import erebus.lib.EnumWood;

public class ItemBlockPlanks extends ItemBlockGeneric {

	public ItemBlockPlanks(Block block) {
		super(block);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		int meta = stack.getItemDamage();
		if (meta >= EnumWood.values().length)
			return "Error";
		String wood = EnumWood.values()[meta].getTranslatedName();
		return String.format(StatCollector.translateToLocal(getUnlocalizedName() + ".name"), wood);
	}
}