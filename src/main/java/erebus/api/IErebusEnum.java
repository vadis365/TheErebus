package erebus.api;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public interface IErebusEnum extends IStringSerializable {

	ItemStack createStack();

	ItemStack createStack(int size);
}