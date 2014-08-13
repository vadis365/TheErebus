package erebus.block.altars;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityErebusAltarHealing;

public class BlockErebusAltarHealing extends BlockContainer
{

	public BlockErebusAltarHealing()
	{
		super(Material.rock);
		setBlockTextureName("erebus:blockErebusAltarBreak");
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

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityErebusAltarHealing();
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) || BlockFence.func_149825_a(world.getBlock(x, y - 1, z));
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		TileEntityErebusAltarHealing te = Utils.getTileEntity(world, x, y, z, TileEntityErebusAltarHealing.class);
		te.setActive(false);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		TileEntityErebusAltarHealing te = Utils.getTileEntity(world, x, y, z, TileEntityErebusAltarHealing.class);
		if (player.getCurrentEquippedItem() != null)
		{
			if (player.getCurrentEquippedItem().getItem() == ModItems.wandOfAnimation && !te.active)
			{
				player.getCurrentEquippedItem().damageItem(1, player);
				te.setActive(true);
				te.setSpawnTicks(12000);
				return true;
			}
		}
		if (player.getCurrentEquippedItem() != null)
		{
			if (player.getCurrentEquippedItem().getItem() == ModItems.wandOfAnimation && te.active)
			{
				player.getCurrentEquippedItem().damageItem(1, player);
				te.setActive(false);
				return true;
			}
		}
		return false;
	}
}