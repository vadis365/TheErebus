package erebus.entity.ai;

import erebus.entity.EntityAnimatedBlock;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAIBlockFollowOwner extends EntityAIBase {
	private EntityAnimatedBlock animatedBlock;
	private EntityLivingBase theOwner;
	World theWorld;
	private double moveSpeed;
	private PathNavigate petPathfinder;
	private int distanceCounter;
	float maxDist;
	float minDist;
	private float avoidWater;

	public EntityAIBlockFollowOwner(EntityAnimatedBlock entity, double speed, float minDistance, float maxDistance) {
		animatedBlock = entity;
		theWorld = entity.getEntityWorld();
		moveSpeed = speed;
		petPathfinder = entity.getNavigator();
		minDist = minDistance;
		maxDist = maxDistance;
		setMutexBits(3);

        if (!(animatedBlock.getNavigator() instanceof PathNavigateGround) && !(animatedBlock.getNavigator() instanceof PathNavigateFlying)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
        }
	}

	@Override
	public boolean shouldExecute() {
		EntityLivingBase entitylivingbase = animatedBlock.getOwner();
		if (entitylivingbase == null)
			return false;
		else if (animatedBlock.getDistanceSq(entitylivingbase) < (double) (minDist * minDist))
			return false;
		else {
			theOwner = entitylivingbase;
			return true;
		}
	}

	@Override
    public boolean shouldContinueExecuting() {
		return !petPathfinder.noPath() && animatedBlock.getDistanceSq(theOwner) > (double) (maxDist * maxDist);
	}

	@Override
	public void startExecuting() {
		distanceCounter = 0;
		avoidWater = animatedBlock.getPathPriority(PathNodeType.WATER);
		animatedBlock.setPathPriority(PathNodeType.WATER, 0.0F);
	}

	@Override
	public void resetTask() {
		theOwner = null;
		petPathfinder.clearPath();
		animatedBlock.setPathPriority(PathNodeType.WATER, avoidWater);
	}

	@Override
	public void updateTask() {
		animatedBlock.getLookHelper().setLookPositionWithEntity(theOwner, 10.0F, (float) animatedBlock.getVerticalFaceSpeed());
		if (--distanceCounter <= 0) {
			distanceCounter = 10;
			if (!petPathfinder.tryMoveToEntityLiving(theOwner, moveSpeed)) {
				if (!animatedBlock.getLeashed()) {
					if (animatedBlock.getDistanceSq(theOwner) >= 144.0D) {
						int i = MathHelper.floor(theOwner.posX) - 2;
						int j = MathHelper.floor(theOwner.posZ) - 2;
						int k = MathHelper.floor(theOwner.getEntityBoundingBox().minY);
						for (int l = 0; l <= 4; ++l) {
							for (int i1 = 0; i1 <= 4; ++i1) {
								if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && isTeleportFriendlyBlock(i, k, j, l, i1)) {
									animatedBlock.setLocationAndAngles((double) ((float) (i + l) + 0.5F), (double) k, (double) ((float) (j + i1) + 0.5F), animatedBlock.rotationYaw, animatedBlock.rotationPitch);
									petPathfinder.clearPath();
									return;
								}
							}
						}
					}
				}
			}
		}
	}

    protected boolean isTeleportFriendlyBlock(int x, int y, int z, int offSetX, int offSetZ) {
        BlockPos blockpos = new BlockPos(x + offSetX, y - 1, z + offSetZ);
        IBlockState iblockstate = theWorld.getBlockState(blockpos);
        return iblockstate.getBlockFaceShape(theWorld, blockpos, EnumFacing.DOWN) == BlockFaceShape.SOLID && iblockstate.canEntitySpawn(animatedBlock) && theWorld.isAirBlock(blockpos.up()) && theWorld.isAirBlock(blockpos.up(2));
    }
}
