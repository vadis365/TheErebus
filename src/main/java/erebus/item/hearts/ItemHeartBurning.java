package erebus.item.hearts;

import erebus.api.animationmagic.EnergyType;
import net.minecraft.item.ItemStack;

public class ItemHeartBurning extends ItemBaseHeart {
	public ItemHeartBurning() {
		super("burning");
	}

	@Override
	public int getMaxStorage(ItemStack stack, EnergyType type) {
		return 20000;
	}
}