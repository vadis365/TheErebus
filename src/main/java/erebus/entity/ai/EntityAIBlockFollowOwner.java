package erebus.entity.ai;

import erebus.entity.EntityAnimatedBlock;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;
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
	private boolean avoidWater;

	public EntityAIBlockFollowOwner(EntityAnimatedBlock entity, double speed, float minDistance, float maxDistance) {
		animatedBlock = entity;
		theWorld = entity.worldObj;
		moveSpeed = speed;
		petPathfinder = entity.getNavigator();
		minDist = minDistance;
		maxDist = maxDistance;
		setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		EntityLivingBase entitylivingbase = animatedBlock.getOwner();
		if (entitylivingbase == null)
			return false;
		else if (animatedBlock.getDistanceSqToEntity(entitylivingbase) < (double) (minDist * minDist))
			return false;
		else {
			theOwner = entitylivingbase;
			return true;
		}
	}

	@Override
	public boolean continueExecuting() {
		return !petPathfinder.noPath() && animatedBlock.getDistanceSqToEntity(theOwner) > (double) (maxDist * maxDist);
	}

	@Override
	public void startExecuting() {
		distanceCounter = 0;
		avoidWater = animatedBlock.getNavigator().getAvoidsWater();
		animatedBlock.getNavigator().setAvoidsWater(false);
	}

	@Override
	public void resetTask() {
		theOwner = null;
		petPathfinder.clearPathEntity();
		animatedBlock.getNavigator().setAvoidsWater(avoidWater);
	}

	@Override
	public void updateTask() {
		animatedBlock.getLookHelper().setLookPositionWithEntity(theOwner, 10.0F, (float) animatedBlock.getVerticalFaceSpeed());
		if (--distanceCounter <= 0) {
			distanceCounter = 10;
			if (!petPathfinder.tryMoveToEntityLiving(theOwner, moveSpeed)) {
				if (!animatedBlock.getLeashed()) {
					if (animatedBlock.getDistanceSqToEntity(theOwner) >= 144.0D) {
						int i = MathHelper.floor_double(theOwner.posX) - 2;
						int j = MathHelper.floor_double(theOwner.posZ) - 2;
						int k = MathHelper.floor_double(theOwner.boundingBox.minY);
						for (int l = 0; l <= 4; ++l) {
							for (int i1 = 0; i1 <= 4; ++i1) {
								if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && World.doesBlockHaveSolidTopSurface(theWorld, i + l, k - 1, j + i1) && !theWorld.getBlock(i + l, k, j + i1).isNormalCube() && !theWorld.getBlock(i + l, k + 1, j + i1).isNormalCube()) {
									animatedBlock.setLocationAndAngles((double) ((float) (i + l) + 0.5F), (double) k, (double) ((float) (j + i1) + 0.5F), animatedBlock.rotationYaw, animatedBlock.rotationPitch);
									petPathfinder.clearPathEntity();
									return;
								}
							}
						}
					}
				}
			}
		}
	}
}
