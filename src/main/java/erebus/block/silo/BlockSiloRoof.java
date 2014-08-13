package erebus.block.silo;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.block.BlockSimple;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;

public class BlockSiloRoof extends BlockSimple
{

	public BlockSiloRoof(Material material)
	{
		super(material);
		setBlockBounds(0F, 0F, 0F, 1F, 0.75F, 1F);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		if (world.getBlock(x, y - 1, z) == ModBlocks.siloTank)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return canPlaceBlockAt(world, x, y, z);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour)
	{
		if (!canBlockStay(world, x, y, z))
		{
			world.setBlockToAir(x, y, z);
			dropBlockAsItem(world, x, y, z, 0, 0);
		}
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int id, EntityPlayer player)
	{
		world.setBlockToAir(x, y, z);
		dropBlockAsItem(world, x, y, z, 0, 0);
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return 1;
	}

	@Override
	public int getRenderType()
	{
		return BlockRenderIDs.SILO_ROOF.id();
	}
}