package erebus.item;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import erebus.ModMaterials;
import erebus.world.feature.plant.WorldGenGiantBaobab;
import erebus.world.feature.plant.WorldGenRottenLogs;

public class CavemanClub extends ItemSword
{

	public CavemanClub()
	{
		super(ModMaterials.toolCAVEMANCLUB);
	}

	@Override
	public boolean getIsRepairable(ItemStack itemStack1, ItemStack itemStack2)
	{
		return Items.bone == itemStack2.getItem() ? true : super.getIsRepairable(itemStack1, itemStack2);
	}
	
	//Just in case
	// Remove onItemUse method completely after testing hollow logs is over!!!!
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		Random rand = new Random();
		if (!world.isRemote && player.isSneaking()) {
			new WorldGenGiantBaobab(28, 14).generate(world, rand, x, y+1, z);
			return true;
		}
		return false;
	}
}
