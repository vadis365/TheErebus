package erebus.block;

import java.util.List;

import net.minecraft.block.BlockWall;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;

public class BlockWallErebus extends BlockWall {

	public BlockWallErebus(int id) {
		super(id, ModBlocks.umberstone);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		switch (meta) {
			default:
				return ModBlocks.umberstone.getIcon(side, 0); // Umberstone
			case 1:
				return ModBlocks.umberstone.getIcon(side, 1); // Umbercobble
			case 2:
				return ModBlocks.umberstone.getIcon(side, 2); // Mossy
																// Umbercobble
			case 3:
				return ModBlocks.umberstone.getIcon(side, 3); // Webbed
																// Umbercobble
			case 4:
				return ModBlocks.umberstone.getIcon(side, 4); // Umberstone
																// Bricks
			case 5:
				return ModBlocks.umberPaver.getIcon(side, 0); // Umberpaver
			case 6:
				return ModBlocks.umberPaver.getIcon(side, 1); // Mossy
																// Umberpaver
			case 7:
				return ModBlocks.umberPaver.getIcon(side, 2); // Webbed
																// Umberpaver
			case 8:
				return ModBlocks.blockAmber.getIcon(side, 2); // Amber Bricks
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs creativeTab, List list) {
		for (int a = 0; a < 9; a++)
			list.add(new ItemStack(id, 1, a));
	}
}
