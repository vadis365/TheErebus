package erebus.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDeathBeecon extends ItemHomingBeecon {

	public ItemDeathBeecon() {
		setCreativeTab(null);
		setUnlocalizedName("erebus.deathBeecon");
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		return false;
	}
}