package erebus.block.glowshroom;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.core.helper.Utils;

public class BlockGlowshroomStalkNS2 extends Block
{

	public BlockGlowshroomStalkNS2()
	{
		super(Material.wood);
		setTickRandomly(true);
		setBlockBounds(0.1875F, 0.1875F, 0F, 0.8125F, 0.8125F, 1F);
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

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (world.isRemote)
		{
			return;
		}
		if (world.getBlock(x, y, z + 1) == ModBlocks.glowshroomStalkMain)
		{
			if (rand.nextInt(2) == 0 && world.isAirBlock(x, y, z - 1))
			{
				world.setBlock(x, y, z - 1, ModBlocks.glowshroomStalkN1);
			} else if (world.getBlock(x, y, z - 1) == ModBlocks.glowshroomStalkN1)
			{
				return;
			} else if (world.getBlock(x, y, z - 1) == ModBlocks.glowshroomStalkN3)
			{
				return;
			} else
			{
				world.setBlock(x, y, z, ModBlocks.glowshroomStalkMain);
			}
		}

		else if (world.getBlock(x, y, z - 1) == ModBlocks.glowshroomStalkMain)
		{
			if (rand.nextInt(2) == 0 && world.isAirBlock(x, y, z + 1))
			{
				world.setBlock(x, y, z + 1, ModBlocks.glowshroomStalkS1);
			} else if (world.getBlock(x, y, z + 1) == ModBlocks.glowshroomStalkS1)
			{
				return;
			} else if (world.getBlock(x, y, z + 1) == ModBlocks.glowshroomStalkS3)
			{
				return;
			} else
			{
				world.setBlock(x, y, z, ModBlocks.glowshroomStalkMain);
			}
		}
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		return 0;
	}

	@Override
	public Item getItemDropped(int id, Random random, int fortune)
	{
		return null;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return isValidBlock(world.getBlock(x, y, z + 1)) || isValidBlock(world.getBlock(x, y, z - 1));
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return isValidBlock(world.getBlock(x, y, z + 1)) || isValidBlock(world.getBlock(x, y, z - 1));
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour)
	{
		if (world.isRemote)
		{
			return;
		}

		boolean flag = false;
		if (isValidBlock(world.getBlock(x, y, z + 1)))
		{
			flag = true;
		}
		if (isValidBlock(world.getBlock(x, y, z - 1)))
		{
			flag = true;
		}

		if (!flag)
		{
			Utils.breakBlockWithParticles(world, x, y, z);
		}
	}

	private boolean isValidBlock(Block block)
	{
		return block == ModBlocks.glowshroomStalkMain;
	}
}