package erebus.items.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public class ItemBlockEnum<T extends Enum<T> & IStringSerializable> extends ItemCloth {

	public static <T extends Enum<T> & IStringSerializable> ItemBlockEnum<T> create(Block block, Class<T> cls) {
		return new ItemBlockEnum<T>(block, cls.getEnumConstants());
	}

	private final T[] values;

	private ItemBlockEnum(Block block, T[] values) {
		super(block);
		this.values = values;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + values[stack.getMetadata()].getName();
	}
}