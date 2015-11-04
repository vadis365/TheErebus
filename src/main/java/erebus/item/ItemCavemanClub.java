package erebus.item;

import erebus.ModMaterials;
import erebus.ModTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class ItemCavemanClub extends ItemSword {

	public ItemCavemanClub() {
		super(ModMaterials.toolCAVEMANCLUB);
		setCreativeTab(ModTabs.gears);
	}

	@Override
	public boolean getIsRepairable(ItemStack itemStack1, ItemStack itemStack2) {
		return Items.bone == itemStack2.getItem() ? true : super.getIsRepairable(itemStack1, itemStack2);
	}

	// Remove onItemUse method completely after testing is over!!!!
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		player.getFoodStats().addStats(-200, 0);
		//		Random rand = new Random();
		//		if (!world.isRemote && player.isSneaking()) {
		//			new WorldGenDragonflyDungeon().generate(world, rand, x, y + 1, z);
		//			return true;
		//		}
		return false;
	}
}
