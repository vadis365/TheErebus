package erebus.entity;

import erebus.ModBlocks;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityMagmaCrawler extends EntityMob {
	private static final DataParameter<Boolean> CLIMBING = EntityDataManager.<Boolean>createKey(EntityMagmaCrawler.class, DataSerializers.BOOLEAN);

	public EntityMagmaCrawler(World world) {
		super(world);
		isImmuneToFire = true;
		setSize(1F, 1F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(CLIMBING, true);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIErebusAttackMelee(this, 0.6D, false));
		tasks.addTask(2, new EntityAIWander(this, 0.5D));
		tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(4, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 20D : 20D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 4D : 4D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
	}


	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.MAGMACRAWLER;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.MAGMACRAWLER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.MAGMACRAWLER_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		entityDropItem(erebus.items.ItemMaterials.EnumErebusMaterialsType.MAGMA_CRAWLER_EYE.createStack(), 0.0F);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 5;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}

	@Override
	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
	}

	@Override
	public boolean getCanSpawnHere() {
		return getEntityWorld().checkNoEntityCollision(getEntityBoundingBox()) && getEntityWorld().getCollisionBoxes(this, getEntityBoundingBox()).isEmpty() && !getEntityWorld().containsAnyLiquid(getEntityBoundingBox()) && getEntityWorld().isAirBlock(getPosition()) && getEntityWorld().getBlockState(getPosition().up()).getBlock() == ModBlocks.GNEISS;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!getEntityWorld().isRemote) {
			if (checkIsOnASurfaceNotGround() && isClimbing()) {

					switch (sideWalkingOn()) {
					case DOWN:
						setClimbing(false);
						break;
					case EAST:
						this.motionX += 0.1D;
						break;
					case NORTH:
						this.motionZ -= 0.1D;
						break;
					case SOUTH:
						this.motionZ += 0.1D;
						break;
					case UP:
						this.motionY += 0.1D;
						break;
					case WEST:
						this.motionX -= 0.1D;
						break;
					default:
						break;
					
					}
			}
			if (!hasNoGravity() && recentlyHit <= 0) {
				setNoGravity(true);
				setClimbing(true);
			}

		if (recentlyHit == 60 || getEntityWorld().canSeeSky(new BlockPos(posX, getEntityBoundingBox().minY, posZ))) {
			if (hasNoGravity())
				setNoGravity(false);
			setClimbing(false);
		}
		/*
		if(getAttackTarget() !=null && getDistanceSq(getAttackTarget()) < 9D) {
			if (hasNoGravity())
				setNoGravity(false);
			setClimbing(false);
		}
			if (!isClimbing() && collidedHorizontally) {
				if (!hasNoGravity())
					setNoGravity(true);
				setClimbing(true);
			}

*/
		}
	}

	public EnumFacing sideWalkingOn() {
		//if (!getEntityWorld().isAirBlock(getPosition().offset(EnumFacing.UP)) && getEntityWorld().isAirBlock(getPosition().offset(EnumFacing.DOWN))) {
		if(isClimbing()) {
			if (getHorizontalFacing() == EnumFacing.NORTH || getHorizontalFacing() == EnumFacing.SOUTH) {
				if (!getEntityWorld().isAirBlock(getPosition().offset(EnumFacing.WEST)) && getEntityWorld().isAirBlock(getPosition().offset(EnumFacing.EAST)))
					return EnumFacing.WEST;
				if (!getEntityWorld().isAirBlock(getPosition().offset(EnumFacing.EAST)) && getEntityWorld().isAirBlock(getPosition().offset(EnumFacing.WEST)))
					return EnumFacing.EAST;
			}
			if (getHorizontalFacing() == EnumFacing.EAST || getHorizontalFacing() == EnumFacing.WEST) {
				if (!getEntityWorld().isAirBlock(getPosition().offset(EnumFacing.NORTH)) && getEntityWorld().isAirBlock(getPosition().offset(EnumFacing.SOUTH)))
					return EnumFacing.NORTH;
				if (!getEntityWorld().isAirBlock(getPosition().offset(EnumFacing.SOUTH)) && getEntityWorld().isAirBlock(getPosition().offset(EnumFacing.NORTH)))
					return EnumFacing.SOUTH;
			}
		}
	//	}
		/*
		if (getEntityWorld().isAirBlock(getPosition().offset(EnumFacing.UP)) && getEntityWorld().isAirBlock(getPosition().offset(EnumFacing.DOWN))) {
			if (getHorizontalFacing() == EnumFacing.NORTH || getHorizontalFacing() == EnumFacing.SOUTH) {
				if (!getEntityWorld().isAirBlock(getPosition().offset(EnumFacing.WEST)))
					return EnumFacing.WEST;
				else if (!getEntityWorld().isAirBlock(getPosition().offset(EnumFacing.EAST)))
					return EnumFacing.EAST;
			}
			if (getHorizontalFacing() == EnumFacing.EAST || getHorizontalFacing() == EnumFacing.WEST) {
				if (!getEntityWorld().isAirBlock(getPosition().offset(EnumFacing.NORTH)))
					return EnumFacing.NORTH;
				else if (!getEntityWorld().isAirBlock(getPosition().offset(EnumFacing.SOUTH)))
					return EnumFacing.SOUTH;
			}
		}*/

		return !isClimbing() ? EnumFacing.DOWN : EnumFacing.UP;
	}

	private boolean checkIsOnASurfaceNotGround() {
		for (EnumFacing facing : EnumFacing.VALUES)
			if(facing != EnumFacing.DOWN)
				if(!getEntityWorld().isAirBlock(getPosition().offset(facing)))
					return true;
		return false;
	}

	@Override
	public void onLivingUpdate() {
		if (!getEntityWorld().isRemote && sideWalkingOn() != EnumFacing.DOWN)
			if (getAttackTarget() != null) {
				double var1 = getAttackTarget().posX + 0.5D - posX;
				double var5 = getAttackTarget().posZ + 0.5D - posZ;
				double var11 = getAttackTarget().posY + 0.5D - posY;
				motionX += (Math.signum(var1) * 0.5D - motionX) * 0.050000000149011612D;
				if(sideWalkingOn() != EnumFacing.UP)
					motionY += (var11 * 0.5D - motionY);
				motionZ += (Math.signum(var5) * 0.5D - motionZ) * 0.050000000149011612D;
				float var7 = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
				float var8 = MathHelper.wrapDegrees(var7 - rotationYaw);
				moveForward = 0.1F;
				rotationYaw += var8;
			}
		super.onLivingUpdate();
	}
	
	public void setClimbing(boolean climbState) {
		dataManager.set(CLIMBING, climbState);
	}

	public boolean isClimbing() {
		return dataManager.get(CLIMBING);
	}
}
