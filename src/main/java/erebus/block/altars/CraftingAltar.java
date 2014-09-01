package erebus.block.altars;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.core.helper.Utils;
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

	public static void formAltar(World world, int x, int y, int z)
	{
		for (int i = -1; i <= 1; i++)
		{
			for (int j = -1; j <= 1; j++)
			{
				Utils.breakBlockWithParticles(world, x + i, y, z + j);
			}
		}
		world.setBlock(x, y, z, ModBlocks.craftingAltar);
	}

	public static boolean isValid(World world, int x, int y, int z)
	{
		for (int i = -1; i <= 1; i++)
		{
			for (int j = -1; j <= 1; j++)
			{
				if (world.getBlock(x + i, y, z + j) != ModBlocks.altarBase)
				{
					return false;
				}
			}
		}
		return true;
	}
}