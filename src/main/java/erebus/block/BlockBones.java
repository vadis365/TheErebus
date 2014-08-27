package erebus.block;

import java.util.ArrayList;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.tileentity.TileEntityBones;

public class BlockBones extends BlockContainer
{

	public BlockBones()
	{
		super(Material.rock);
		setBlockTextureName("erebus:blockBonesBreak");
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.6875F, 1.0F);
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
		return new TileEntityBones();
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) || BlockFence.func_149825_a(world.getBlock(x, y - 1, z));
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack is)
	{
		byte b0 = 0;
		int l1 = MathHelper.floor_double(entityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		if (l1 == 0)
		{
			b0 = 2;
		}
		if (l1 == 1)
		{
			b0 = 5;
		}
		if (l1 == 2)
		{
			b0 = 3;
		}
		if (l1 == 3)
		{
			b0 = 4;
		}
		world.setBlockMetadataWithNotify(x, y, z, b0, 3);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
	{
		return AxisAlignedBB.getBoundingBox(i, j, k, i + 1, j + 1, k + 1);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		int count = 1 + world.rand.nextInt(3);
		for (int i = 0; i < count; i++)
		{
			Item id = null;
			int damage = 0;
			if (world.rand.nextInt(3) == 0)
			{
				id = Items.bone;
			} else
			{
				id = ModItems.materials;
				damage = 2;
			}
			if (id != null)
			{
				ret.add(new ItemStack(id, 1, damage));
			}
		}
		return ret;
	}
}