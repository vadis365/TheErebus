package erebus.block;

import java.util.ArrayList;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.tileentity.TileEntityBones;

public class BlockBones extends BlockContainer
{

	public BlockBones()
	{
		super(Material.rock);
		setBlockName("erebus.blockBones");
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.6875F, 1.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return Blocks.quartz_block.getIcon(side, 0);
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