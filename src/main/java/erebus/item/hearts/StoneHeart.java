package erebus.item.hearts;

import net.minecraft.item.ItemStack;
import erebus.api.animationmagic.EnergyType;

public class StoneHeart extends BaseHeart {
	public StoneHeart() {
		super("stone");
	}

	@Override
	public int getMaxStorage(ItemStack stack, EnergyType type) {
		return 2500;
	}
}