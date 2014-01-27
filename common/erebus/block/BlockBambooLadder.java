package erebus.block;

import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.tileentity.TileEntityLadder;

public class BlockBambooLadder extends BlockContainer {

	public BlockBambooLadder(int id) {
		super(id, Material.circuits);
	}

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
		return blockID;
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
		return world.isBlockSolidOnSide(x - 1, y, z, EAST) || world.isBlockSolidOnSide(x + 1, y, z, WEST) || world.isBlockSolidOnSide(x, y, z - 1, SOUTH) || world.isBlockSolidOnSide(x, y, z + 1, NORTH);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		int j1 = meta;

		if ((j1 == 0 || side == 2) && world.isBlockSolidOnSide(x, y, z + 1, NORTH))
			j1 = 2;

		if ((j1 == 0 || side == 3) && world.isBlockSolidOnSide(x, y, z - 1, SOUTH))
			j1 = 3;

		if ((j1 == 0 || side == 4) && world.isBlockSolidOnSide(x + 1, y, z, WEST))
			j1 = 4;

		if ((j1 == 0 || side == 5) && world.isBlockSolidOnSide(x - 1, y, z, EAST))
			j1 = 5;

		return j1;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourID) {
		int i1 = world.getBlockMetadata(x, y, z);
		boolean flag = false;

		if (i1 == 2 && world.isBlockSolidOnSide(x, y, z + 1, NORTH))
			flag = true;

		if (i1 == 3 && world.isBlockSolidOnSide(x, y, z - 1, SOUTH))
			flag = true;

		if (i1 == 4 && world.isBlockSolidOnSide(x + 1, y, z, WEST))
			flag = true;

		if (i1 == 5 && world.isBlockSolidOnSide(x - 1, y, z, EAST))
			flag = true;

		if (!flag) {
			dropBlockAsItem(world, x, y, z, i1, 0);
			world.setBlockToAir(x, y, z);
		}

		super.onNeighborBlockChange(world, x, y, z, neighbourID);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	public boolean isLadder(World world, int x, int y, int z, EntityLivingBase entity) {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityLadder();
	}
}