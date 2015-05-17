package erebus.item.hearts;

import erebus.api.animationmagic.EnergyType;
import net.minecraft.item.ItemStack;

public class ItemHeartJade extends ItemBaseHeart {
	public ItemHeartJade() {
		super("jade");
	}

	@Override
	public int getMaxStorage(ItemStack stack, EnergyType type) {
		return 10000;
	}
}