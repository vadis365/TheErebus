package erebus.item.hearts;

import erebus.api.animationmagic.EnergyType;
import net.minecraft.item.ItemStack;

public class ItemHeartStone extends ItemBaseHeart {
	public ItemHeartStone() {
		super("stone");
	}

	@Override
	public int getMaxStorage(ItemStack stack, EnergyType type) {
		return 2500;
	}
}