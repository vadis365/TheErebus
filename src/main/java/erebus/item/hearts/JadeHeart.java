package erebus.item.hearts;

import net.minecraft.item.ItemStack;
import erebus.api.animationmagic.EnergyType;

public class JadeHeart extends BaseHeart {
	public JadeHeart() {
		super("jade");
	}

	@Override
	public int getMaxStorage(ItemStack stack, EnergyType type) {
		return 10000;
	}
}