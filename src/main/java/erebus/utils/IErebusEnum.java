package erebus.utils;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;

public interface IErebusEnum extends StringRepresentable {

    default ItemStack createStack() {
        return createStack(1);
    }

    ItemStack createStack(int count);
}
