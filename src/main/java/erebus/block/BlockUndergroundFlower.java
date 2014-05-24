package erebus.block;

import static net.minecraftforge.common.EnumPlantType.Plains;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockUndergroundFlower extends Block implements IPlantable {

	protected BlockUndergroundFlower() {
		super(Material.vine);
		setTickRandomly(true);
		float var4 = 0.2F;
		setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return super.canPlaceBlockAt(world, x, y, z) && canBlockStay(world, x, y, z);
	}

	protected boolean canThisPlantGrowOnThisBlockID(Block blockID) {
		return blockID == Blocks.grass || blockID == Blocks.dirt || blockID == Blocks.farmland;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		super.onNeighborBlockChange(world, x, y, z, neighbour);
		checkFlowerChange(world, x, y, z);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		checkFlowerChange(world, x, y, z);
	}

	protected final void checkFlowerChange(World world, int x, int y, int z) {
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		Block soil = world.getBlock(x, y - 1, z);
		return soil != null && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
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
		return 1;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return Plains;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return this;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}
}