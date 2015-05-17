package erebus.item.hearts;

import erebus.api.animationmagic.EnergyType;
import net.minecraft.item.ItemStack;

public class ItemHeartElven extends ItemBaseHeart {
	public ItemHeartElven() {
		super("elven");
	}

	@Override
	public int getMaxStorage(ItemStack stack, EnergyType type) {
		return 15000;
	}
}