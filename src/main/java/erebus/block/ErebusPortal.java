package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.core.handler.configs.ConfigHandler;
import erebus.world.teleporter.TeleporterHandler;

public class ErebusPortal extends Block
{
	public ErebusPortal()
	{
		super(Material.portal);
		setLightLevel(1.0F);
		setBlockUnbreakable();
		setBlockName("erebus.portal");
		setStepSound(Block.soundTypeGlass);
		setBlockTextureName("erebus:portal");
	}

	public static boolean makePortal(World world, int x, int y, int z)
	{
		if (isPatternValid(world, x, y, z))
		{
			world.setBlock(x, y, z, ModBlocks.portal);
			world.setBlock(x, y + 1, z, ModBlocks.portal);
			return true;
		}
		return false;
	}

	public static boolean isPatternValid(World world, int x, int y, int z)
	{
		// Layer 0
		if (!check(world, x, y - 1, z, Blocks.stonebrick, 3))
		{
			return false;
		}

		// Layer 1
		if (!check(world, x - 1, y, z - 1, Blocks.stonebrick, 0))
		{
			return false;
		}
		if (!check(world, x - 1, y, z + 1, Blocks.stonebrick, 0))
		{
			return false;
		}
		if (!check(world, x + 1, y, z - 1, Blocks.stonebrick, 0))
		{
			return false;
		}
		if (!check(world, x + 1, y, z + 1, Blocks.stonebrick, 0))
		{
			return false;
		}
		if (!world.isAirBlock(x, y, z) && world.getBlock(x, y, z) != ModBlocks.portal)
		{
			return false;
		}

		// Layer 2
		if (!check(world, x - 1, y + 1, z - 1, Blocks.stonebrick, 0))
		{
			return false;
		}
		if (!check(world, x - 1, y + 1, z + 1, Blocks.stonebrick, 0))
		{
			return false;
		}
		if (!check(world, x + 1, y + 1, z - 1, Blocks.stonebrick, 0))
		{
			return false;
		}
		if (!check(world, x + 1, y + 1, z + 1, Blocks.stonebrick, 0))
		{
			return false;
		}
		if (!world.isAirBlock(x, y + 1, z) && world.getBlock(x, y + 1, z) != ModBlocks.portal)
		{
			return false;
		}

		// Layer 3
		if (world.getBlock(x, y + 2, z) != ModBlocks.gaeanKeystone)
		{
			return false;
		}

		for (int i = -1; i <= -1; i++)
		{
			for (int j = -1; j <= -1; j++)
			{
				if (i == 0 && j == 0)
				{
					continue;
				}
				if (!check(world, x + i, y + 2, z + j, Blocks.stone_slab, 5))
				{
					return false;
				}
			}
		}

		return true;
	}

	private static boolean check(World world, int x, int y, int z, Block target, int meta)
	{
		return world.getBlock(x, y, z) == target && world.getBlockMetadata(x, y, z) == meta;
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
	{
		if (entity.ridingEntity == null && entity.riddenByEntity == null && entity.timeUntilPortal <= 0)
		{
			if (entity.dimension == 0)
			{
				TeleporterHandler.transferToErebus(entity);
			} else
			{
				TeleporterHandler.transferToOverworld(entity);
			}
			if (entity != null)
			{
				entity.timeUntilPortal = ConfigHandler.INSTANCE.portalCooldown * 20; //or set this to an arbitrary amount
			}
		}
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
	{
		return side > 1;
	}
}