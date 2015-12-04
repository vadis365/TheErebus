package erebus.item;

import erebus.ModTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFoodHeartBerries extends ItemFood {

	public ItemFoodHeartBerries(int healAmount, float saturation, boolean favorite) {
		super(healAmount, saturation, favorite);
		setMaxDamage(0);
		setAlwaysEdible();
		setCreativeTab(ModTabs.items);
		setTextureName("erebus:heart_berries");
		setUnlocalizedName("erebus.heartBerries");
	}

	@Override
	protected void onFoodEaten(ItemStack is, World world, EntityPlayer player) {
		player.heal(1);
	}
}