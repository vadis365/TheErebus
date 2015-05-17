package erebus.item.hearts;

import erebus.api.animationmagic.EnergyType;
import net.minecraft.item.ItemStack;

public class ItemHeartTwin extends ItemBaseHeart {
	public ItemHeartTwin() {
		super("twin");
	}

	@Override
	public int getMaxStorage(ItemStack stack, EnergyType type) {
		return 5000;
	}
}