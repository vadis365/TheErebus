package erebus.item.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockGeneric extends ItemBlock {

	private final String name;

	public ItemBlockGeneric(int id, String unlocalizedName) {
		super(id);
		setHasSubtypes(true);
		name = unlocalizedName + "_item_";
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return name + is.getItemDamage();
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}
}
