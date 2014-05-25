package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import erebus.lib.EnumWood;

public class ItemBlockPlanks extends ItemBlockGeneric {

	public ItemBlockPlanks(Block block) {
		super(block, "planks");
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String wood = EnumWood.values()[stack.getItemDamage()].getTranslatedName();
		return String.format(StatCollector.translateToLocal(getUnlocalizedName() + ".name"), wood);
	}
}