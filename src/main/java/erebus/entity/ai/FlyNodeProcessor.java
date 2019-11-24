package erebus.entity.ai;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;

public class FlyNodeProcessor extends NodeProcessor {
	@Override
	public PathPoint getStart() {
		return this.openPoint(MathHelper.floor(this.entity.getEntityBoundingBox().minX), MathHelper.floor(this.entity.getEntityBoundingBox().minY), MathHelper.floor(this.entity.getEntityBoundingBox().minZ));
	}

	@Override
	public PathPoint getPathPointToCoords(double x, double y, double z) {
		return this.openPoint(MathHelper.floor(x - (double) (this.entity.width / 2.0F)), MathHelper.floor(y - (double) (this.entity.height / 2.0F)), MathHelper.floor(z - (double) (this.entity.width / 2.0F)));
	}

	@Override
	public int findPathOptions(PathPoint[] pathOptions, PathPoint currentPoint, PathPoint targetPoint, float maxDistance) {
		int i = 0;
		for (EnumFacing enumfacing : EnumFacing.values()) {
			PathPoint pathpoint = this.getAirNode(currentPoint.x + enumfacing.getXOffset(), currentPoint.y + enumfacing.getYOffset(), currentPoint.z + enumfacing.getZOffset());
			if (pathpoint != null && !pathpoint.visited && pathpoint.distanceTo(targetPoint) < maxDistance)
				pathOptions[i++] = pathpoint;
		}
		return i;
	}

	@Override
	public PathNodeType getPathNodeType(IBlockAccess blockaccessIn, int x, int y, int z, EntityLiving entitylivingIn, int xSize, int ySize, int zSize, boolean canBreakDoorsIn, boolean canEnterDoorsIn) {
		return PathNodeType.OPEN;
	}

	@Override
	public PathNodeType getPathNodeType(IBlockAccess blockaccessIn, int x, int y, int z) {
		return PathNodeType.OPEN;
	}

	@Nullable
	private PathPoint getAirNode(int x, int y, int z) {
		PathNodeType pathnodetype = this.isFree(x, y, z);
		return pathnodetype == PathNodeType.OPEN ? this.openPoint(x, y, z) : null;
	}

	private PathNodeType isFree(int x, int y, int z) {
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
		for (int i = x; i < x + this.entitySizeX; ++i) {
			for (int j = y; j < y + this.entitySizeY; ++j) {
				for (int k = z; k < z + this.entitySizeZ; ++k) {
					IBlockState iblockstate = this.blockaccess.getBlockState(blockpos$mutableblockpos.setPos(i, j, k));
					if (iblockstate.getMaterial() != Material.AIR)
						return PathNodeType.BLOCKED;
				}
			}
		}
		return PathNodeType.OPEN;
	}
}
