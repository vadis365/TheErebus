package erebus.items.block;

import erebus.api.IErebusEnum;
import net.minecraft.block.Block;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;

public class ItemBlockEnum<T extends Enum<T> & IErebusEnum> extends ItemCloth {

	public static <T extends Enum<T> & IErebusEnum> ItemBlockEnum<T> create(Block block, Class<T> cls) {
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