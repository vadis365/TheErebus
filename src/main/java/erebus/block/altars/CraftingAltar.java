package erebus.block.altars;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import erebus.ModTabs;
import erebus.tileentity.TileEntityCraftingAltar;

public class CraftingAltar extends BlockContainer
{
	public CraftingAltar()
	{
		super(Material.rock);
		setHardness(5.0F);
		setResistance(10.0F);
		setStepSound(soundTypeStone);
		setBlockTextureName("stone");
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.craftingAltar");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityCraftingAltar();
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
}