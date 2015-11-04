package erebus.block.bamboo;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import erebus.tileentity.TileEntityLadder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockBambooLadder extends BlockContainer {

	public BlockBambooLadder() {
		super(Material.circuits);
		setHardness(0.4F);
		setHarvestLevel("axe", 0);
		setStepSound(soundTypeLadder);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.bambooLadder");
		setBlockTextureName("erebus:bambooLadder");
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		setBlockBoundsBasedOnState(world, x, y, z);
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		setBlockBoundsBasedOnState(world, x, y, z);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		updateLadderBounds(world.getBlockMetadata(x, y, z));
	}

	public void updateLadderBounds(int meta) {
		float f = 0.125F;

		if (meta == 2)
			setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
		if (meta == 3)
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
		if (meta == 4)
			setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		if (meta == 5)
			setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
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
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return world.isSideSolid(x - 1, y, z, ForgeDirection.EAST) || world.isSideSolid(x + 1, y, z, ForgeDirection.WEST) || world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH) || world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		if ((meta == 0 || side == 2) && world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH))
			meta = 2;
		if ((meta == 0 || side == 3) && world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH))
			meta = 3;
		if ((meta == 0 || side == 4) && world.isSideSolid(x + 1, y, z, ForgeDirection.WEST))
			meta = 4;
		if ((meta == 0 || side == 5) && world.isSideSolid(x - 1, y, z, ForgeDirection.EAST))
			meta = 5;

		return meta;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		int i1 = world.getBlockMetadata(x, y, z);
		boolean flag = false;

		if (i1 == 2 && world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH))
			flag = true;

		if (i1 == 3 && world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH))
			flag = true;

		if (i1 == 4 && world.isSideSolid(x + 1, y, z, ForgeDirection.WEST))
			flag = true;

		if (i1 == 5 && world.isSideSolid(x - 1, y, z, ForgeDirection.EAST))
			flag = true;

		if (!flag) {
			dropBlockAsItem(world, x, y, z, i1, 0);
			world.setBlockToAir(x, y, z);
		}

		super.onNeighborBlockChange(world, x, y, z, neighbour);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityLadder();
	}
}