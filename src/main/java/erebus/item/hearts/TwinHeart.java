package erebus.item.hearts;

import net.minecraft.item.ItemStack;
import erebus.api.animationmagic.EnergyType;

public class TwinHeart extends BaseHeart {
	public TwinHeart() {
		super("twin");
	}

	@Override
	public int getMaxStorage(ItemStack stack, EnergyType type) {
		return 5000;
	}
}