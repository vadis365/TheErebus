package erebus.item;

import java.util.Random;

import erebus.world.feature.plant.WorldGenRottenLogs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import erebus.ModMaterials;

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
	
	// Remove onItemUse method completely after testing hollow logs is over!!!!
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && player.isSneaking()) {
			Random rand = new Random();
			//new WorldGenRottenLogs(rand.nextInt(11)+5, rand.nextInt(4)+3).generate(world, rand, x, y+1, z);
			new WorldGenRottenLogs(15, 6).generate(world, rand, x, y+1, z);
			
			return true;
		}
		return false;
	}
}