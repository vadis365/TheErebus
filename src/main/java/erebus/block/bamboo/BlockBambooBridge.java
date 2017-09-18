package erebus.block.bamboo;

import java.util.List;

import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityBambooBridge;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBambooBridge extends BlockContainer {

	public BlockBambooBridge() {
		super(Material.wood);
		setCreativeTab(ModTabs.blocks);
		setHardness(0.4F);
		setStepSound(Block.soundTypeLadder);
		setBlockName("erebus.bambooBridge");
		setBlockTextureName("erebus:planks_bamboo");
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityBambooBridge();
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack is) {
		byte meta = 0;
		int rotation = MathHelper.floor_double(entityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		if (rotation == 0)
			meta = 2;
		if (rotation == 1)
			meta = 5;
		if (rotation == 2)
			meta = 3;
		if (rotation == 3)
			meta = 4;
		world.setBlockMetadataWithNotify(x, y, z, meta, 3);
		onBlockAdded(world, x, y, z);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		TileEntityBambooBridge te = Utils.getTileEntity(world, x, y, z, TileEntityBambooBridge.class);

		boolean front = canConnectBridgeTo(world, x, y, z - 1);
		boolean back = canConnectBridgeTo(world, x, y, z + 1);
		boolean left = canConnectBridgeTo(world, x - 1, y, z);
		boolean right = canConnectBridgeTo(world, x + 1, y, z);

		switch (world.getBlockMetadata(x, y, z)) {
			case 2:
				if (!right)
					te.setRenderSide1(true);
				if (!left)
					te.setRenderSide2(true);
				if (right)
					te.setRenderSide1(false);
				if (left)
					te.setRenderSide2(false);
				break;
			case 3:
				if (!right)
					te.setRenderSide2(true);
				if (!left)
					te.setRenderSide1(true);
				if (right)
					te.setRenderSide2(false);
				if (left)
					te.setRenderSide1(false);
				break;
			case 4:
				if (!back)
					te.setRenderSide1(true);
				if (!front)
					te.setRenderSide2(true);
				if (back)
					te.setRenderSide1(false);
				if (front)
					te.setRenderSide2(false);
				break;
			case 5:
				if (!back)
					te.setRenderSide2(true);
				if (!front)
					te.setRenderSide1(true);
				if (back)
					te.setRenderSide2(false);
				if (front)
					te.setRenderSide1(false);
				break;
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		onBlockAdded(world, x, y, z);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB box, List list, Entity entity) {
		float pixel = 0.0625F;
		int meta = world.getBlockMetadata(x, y, z);
		boolean front = canConnectBridgeTo(world, x, y, z - 1);
		boolean back = canConnectBridgeTo(world, x, y, z + 1);
		boolean left = canConnectBridgeTo(world, x - 1, y, z);
		boolean right = canConnectBridgeTo(world, x + 1, y, z);

		if (meta == 2 || meta == 3) {
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, pixel * 2, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, box, list, entity);

			if (!right) {
				setBlockBounds(1.0F - pixel * 2, 0.0F, 0.0F, 1.0F, 0.875F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, box, list, entity);
			}
			if (!left) {
				setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F + pixel * 2, 0.875F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, box, list, entity);
			}
		}

		if (meta == 4 || meta == 5) {
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, pixel * 2, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, box, list, entity);
			if (!back) {
				setBlockBounds(0.0F, 0.0F, 1.0F - pixel * 2, 1.0F, 0.875F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, box, list, entity);
			}
			if (!front) {
				setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.875F, 0.0F + pixel * 2);
				super.addCollisionBoxesToList(world, x, y, z, box, list, entity);
			}
		}
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.875F, 1.0F);
	}

	public boolean canConnectBridgeTo(IBlockAccess world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		if (block != this)
			return !block.isAir(world, x, y, z) && block.renderAsNormalBlock() ? block.getMaterial() != Material.gourd : false;
		else
			return true;
	}
}