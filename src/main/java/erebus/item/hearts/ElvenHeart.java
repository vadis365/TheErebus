package erebus.item.hearts;

import net.minecraft.item.ItemStack;
import erebus.api.animationmagic.EnergyType;

public class ElvenHeart extends BaseHeart {
	public ElvenHeart() {
		super("elven");
	}

	@Override
	public int getMaxStorage(ItemStack stack, EnergyType type) {
		return 15000;
	}
}