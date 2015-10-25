package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import erebus.lib.EnumWood;
import erebus.lib.Reference;

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
}