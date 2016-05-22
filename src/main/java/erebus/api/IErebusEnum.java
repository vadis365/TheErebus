package erebus.api;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public interface IErebusEnum extends IStringSerializable {

	default ItemStack createStack() {
		return createStack(1);
	}

	ItemStack createStack(int size);
}