package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;

public class ItemBlockLocalised extends ItemCloth {

	public ItemBlockLocalised(Block block) {
		super(block);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return getBlock().getLocalizedName();
	}

	public Block getBlock() {
		return field_150939_a;
	}
}