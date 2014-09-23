package erebus.item;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
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
	
	/*//Just in case
	// Remove onItemUse method completely after testing hollow logs is over!!!!
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		Random rand = new Random();
		if (!world.isRemote && player.isSneaking()) {
			new WorldGenRottenLogs(rand.nextInt(8)+4, rand.nextInt(3)+2, (byte)rand.nextInt(2)).generate(world, rand, x, y+1, z);
			return true;
		}
		return false;
	}
	*/
}
