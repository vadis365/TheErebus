package erebus.item.hearts;

import erebus.api.animationmagic.EnergyType;
import net.minecraft.item.ItemStack;

public class ItemHeartMana extends ItemBaseHeart {
	public ItemHeartMana() {
		super("mana");
	}

	@Override
	public int getMaxStorage(ItemStack stack, EnergyType type) {
		return 10000;
	}
}