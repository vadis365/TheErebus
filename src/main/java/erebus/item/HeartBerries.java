package erebus.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.ModTabs;

public class HeartBerries extends ItemFood {

	public HeartBerries(int healAmount, float saturation, boolean favorite) {
		super(healAmount, saturation, favorite);
		setMaxDamage(0);
		setAlwaysEdible();
		setCreativeTab(ModTabs.items);
	}

	@Override
	protected void onFoodEaten(ItemStack is, World world, EntityPlayer player) {
		player.heal(1);
	}
}