package erebus.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDeathCompass extends ItemHomingBeecon {

	public ItemDeathCompass() {
		setCreativeTab(null);
		setUnlocalizedName("erebus.deathCompass");
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		return false;
	}
}