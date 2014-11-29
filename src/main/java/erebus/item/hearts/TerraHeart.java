package erebus.item.hearts;

import net.minecraft.item.ItemStack;
import erebus.api.animationmagic.EnergyType;

public class TerraHeart extends BaseHeart {
	public TerraHeart() {
		super("terra");
	}

	@Override
	public int getMaxStorage(ItemStack stack, EnergyType type) {
		return 25000;
	}
}