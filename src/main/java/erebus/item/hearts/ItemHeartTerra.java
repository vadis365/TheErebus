package erebus.item.hearts;

import erebus.api.animationmagic.EnergyType;
import net.minecraft.item.ItemStack;

public class ItemHeartTerra extends ItemBaseHeart {
	public ItemHeartTerra() {
		super("terra");
	}

	@Override
	public int getMaxStorage(ItemStack stack, EnergyType type) {
		return 25000;
	}
}