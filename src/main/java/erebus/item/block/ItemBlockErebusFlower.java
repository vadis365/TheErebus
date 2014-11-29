package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import erebus.core.helper.Utils;
import erebus.lib.EnumColour;

public class ItemBlockErebusFlower extends ItemBlockGeneric {

	public ItemBlockErebusFlower(Block block) {
		super(block);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if (stack.getItemDamage() >= 14)
			return super.getItemStackDisplayName(stack);

		String colour = EnumColour.values()[Utils.getFlowerMetadata(stack)].getTranslatedName();
		return String.format(StatCollector.translateToLocal(getUnlocalizedName() + ".name"), colour);
	}
}