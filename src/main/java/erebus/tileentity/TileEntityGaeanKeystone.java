package erebus.tileentity;

import erebus.ModBlocks;
import erebus.blocks.ErebusPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityGaeanKeystone extends TileEntity implements ITickable {
	@Override
	public void update() {
		if (getWorld().isRemote)
			return;

		if (getWorld().getTotalWorldTime() % 20L == 0L)
			if (!ErebusPortal.isPatternValid(getWorld(), getPos().down(2)) || !hasPortals()) {
				if (getWorld().getBlockState(getPos().down(1)) == ModBlocks.PORTAL.getDefaultState())
					getWorld().setBlockToAir(getPos().down(1));
				if (getWorld().getBlockState(getPos().down(2)) == ModBlocks.PORTAL.getDefaultState())
					getWorld().setBlockToAir(getPos().down(2));
				if (getBlockMetadata() != 0) {
					getWorld().setBlockState(getPos(), ModBlocks.GAEAN_KEYSTONE.getDefaultState(), 3);
					markDirty();
				}
			}
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
	return oldState.getBlock() != newState.getBlock();
	}

	private boolean hasPortals() {
		return getWorld().getBlockState(getPos().down(1)) == ModBlocks.PORTAL.getDefaultState() && getWorld().getBlockState(getPos().down(2)) == ModBlocks.PORTAL.getDefaultState();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return INFINITE_EXTENT_AABB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return Double.MAX_VALUE;
	}
}
