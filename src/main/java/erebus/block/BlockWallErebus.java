package erebus.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.ModTabs;
import erebus.item.block.ItemBlockGeneric;
import net.minecraft.block.BlockWall;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockWallErebus extends BlockWall implements ISubBlocksBlock {

	public BlockWallErebus() {
		super(ModBlocks.umberstone);
		setHarvestLevel("pickaxe", 0);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.wallErebus");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		switch (meta) {
			default:
				return ModBlocks.umberstone.getIcon(side, 0); // Umberstone
			case 1:
				return ModBlocks.umberstone.getIcon(side, 1); // Umbercobble
			case 2:
				return ModBlocks.umberstone.getIcon(side, 2); // Mossy Umbercobble
			case 3:
				return ModBlocks.umberstone.getIcon(side, 3); // Webbed Umbercobble
			case 4:
				return ModBlocks.umberstone.getIcon(side, 4); // Umberstone Bricks
			case 5:
				return ModBlocks.umberPaver.getIcon(side, 0); // Umberpaver
			case 6:
				return ModBlocks.umberPaver.getIcon(side, 1); // Mossy Umberpaver
			case 7:
				return ModBlocks.umberPaver.getIcon(side, 2); // Webbed Umberpaver
			case 8:
				return ModBlocks.amber.getIcon(side, 2); // Amber Bricks
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item id, CreativeTabs creativeTab, List list) {
		for (int a = 0; a < 9; a++)
			list.add(new ItemStack(id, 1, a));
	}

	@Override
	public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
		return true;
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockGeneric.class;
	}
}