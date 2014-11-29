package erebus.item.hearts;

import net.minecraft.item.ItemStack;
import erebus.api.animationmagic.EnergyType;

public class BurningHeart extends BaseHeart {
	public BurningHeart() {
		super("burning");
	}

	@Override
	public int getMaxStorage(ItemStack stack, EnergyType type) {
		return 20000;
	}
}