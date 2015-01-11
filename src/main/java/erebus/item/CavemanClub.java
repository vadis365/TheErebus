package erebus.item;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.ModMaterials;
import erebus.world.feature.structure.WorldGenAntlionMaze;

public class CavemanClub extends ItemSword {

	public CavemanClub() {
		super(ModMaterials.toolCAVEMANCLUB);
	}

	@Override
	public boolean getIsRepairable(ItemStack itemStack1, ItemStack itemStack2) {
		return Items.bone == itemStack2.getItem() ? true : super.getIsRepairable(itemStack1, itemStack2);
	}
	
	// Remove onItemUse method completely after testing is over!!!!
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		Random rand = new Random();
		if (!world.isRemote && player.isSneaking()) {
			//new WorldGenAntlionMaze().generate(world, rand, x, y, z);
			new WorldGenAntlionMaze().createPyramid(world, ModBlocks.templeBrickUnbreaking, 0, true, x, z, 44, 44, y - 6);
			return true;
		}
		return false;
	}

}
