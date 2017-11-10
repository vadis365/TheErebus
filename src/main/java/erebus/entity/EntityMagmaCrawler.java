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
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityMagmaCrawler extends EntityMob {
	private int shouldDo;
	public boolean upAbove;

	public EntityMagmaCrawler(World world) {
		super(world);
		isImmuneToFire = true;
		setSize(1.25F, 1.0F);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIErebusAttackMelee(this, 0.7D, false));
		tasks.addTask(5, new EntityAIWander(this, 0.5D));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(7, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.8D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 20D : 20D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 4D : 4D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
		upAbove = true;
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

	public boolean isOnCeiling() {
		return upAbove && getEntityWorld().getBlockState(getPosition().up(1)).getBlock() == ModBlocks.GNEISS && getEntityWorld().isAirBlock(getPosition().down()) ||
				upAbove && getEntityWorld().getBlockState(getPosition().up(1)).getBlock() == ModBlocks.MAGMA_CRAWLER_SPAWNER && getEntityWorld().isAirBlock(getPosition().down());
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
			if (checkIsOnASurfaceNotGround() || isOnCeiling())
				if(!this.hasNoGravity()) {
					setNoGravity(true);
					this.motionY += 0.1;
				}

			if (onGround && upAbove || collidedHorizontally && upAbove || getHealth() < getMaxHealth() && upAbove) {
				upAbove = false;
				setNoGravity(false);
			}
		}
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
		if (!getEntityWorld().isRemote)
			if (getAttackTarget() != null) {
				double var1 = getAttackTarget().posX + 0.5D - posX;
				double var5 = getAttackTarget().posZ + 0.5D - posZ;
				motionX += (Math.signum(var1) * 0.5D - motionX) * 0.050000000149011612D;
				motionZ += (Math.signum(var5) * 0.5D - motionZ) * 0.050000000149011612D;
				float var7 = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
				float var8 = MathHelper.wrapDegrees(var7 - rotationYaw);
				moveForward = 0.5F;
				rotationYaw += var8;
			}
		super.onLivingUpdate();
	}
}
