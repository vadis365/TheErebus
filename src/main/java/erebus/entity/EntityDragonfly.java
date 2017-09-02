package erebus.entity;

import java.util.Random;

import erebus.Erebus;
import erebus.ModItems;
import erebus.client.render.entity.AnimationMathHelper;
import erebus.core.handler.configs.ConfigHandler;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDragonfly extends EntityMob {

	private BlockPos currentFlightTarget;
	EntityLivingBase entityToAttack;
	AnimationMathHelper mathWings = new AnimationMathHelper();
	public float wingFloat;

	public double pickupHeight;
	private boolean dropped;
	private int droptime = 0;// cool-down for picking up
	private int countDown;// makes sure player is always dropped

	private static final DataParameter<Integer> SKIN_TYPE = EntityDataManager.<Integer>createKey(EntityDragonfly.class, DataSerializers.VARINT);

	public EntityDragonfly(World world) {
		super(world);
		setSize(2.5F, 1.0F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(SKIN_TYPE, new Integer(rand.nextInt(51)));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 15D : 15D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 1D : 1D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(128.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected void collideWithEntity(Entity entity) {
	}

	@Override
	public boolean shouldRiderSit() {
		return false;
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}

	@Override
	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
	}

	@Override
	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}

	public boolean captured() {
		return isBeingRidden();
	}

	private void setCountdown(int count) {
		countDown = count;
	}

	private void setDropped(boolean dropstate) {
		dropped = dropstate;
	}

	public boolean getDropped() {
		return dropped;
	}

	@Override
	protected float getSoundVolume() {
		return 0.1F;
	}

	@Override
	protected float getSoundPitch() {
		return super.getSoundPitch() * 0.5F;
	}

	@Override
    public SoundEvent getAmbientSound() {
		return null;// rand.nextInt(4) != 0 ? null : "erebus:flysound";
	}

	@Override
    protected SoundEvent getHurtSound(DamageSource source) {
		return null;// "erebus:flyhurt";
	}

	@Override
    protected SoundEvent getDeathSound() {
		return null;// "erebus:squish";
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		wingFloat = mathWings.swing(4.0F, 0.1F);
		motionY *= 0.6000000238418579D;
		if (getAttackTarget() == null)
			flyAbout();
		if (isBeingRidden())
			if (!getEntityWorld().isRemote && captured() && (posY > pickupHeight + 10D || countDown <= 0 || !getEntityWorld().isRemote && captured() && getEntityWorld().isSideSolid(new BlockPos (MathHelper.floor(posX), MathHelper.floor(posY + 1D), MathHelper.floor(posZ)), EnumFacing.UP))) {
				setDropped(true);
				removePassengers();
			}
		if (dropped) {
			droptime++;
			if (droptime >= 20) {
				setDropped(false);
				droptime = 0;
			}
		}
		if (countDown >= 0)
			countDown--;
		if (getAttackTarget() != null) {
			currentFlightTarget = new BlockPos((int) getAttackTarget().posX, (int) ((int) getAttackTarget().posY + getAttackTarget().getEyeHeight()), (int) getAttackTarget().posZ);
			flyToTarget();
		}
		if (getEntityWorld().isRemote)
			if (getSkin() == 0) {
				spawnParticles(getEntityWorld(), posX - 0.5D, posY, posZ - 0.5D, rand);
				if (!hasCustomName())
					setCustomNameTag("Ender Dragonfly");
			}
	}

	@SideOnly(Side.CLIENT)
	public void spawnParticles(World world, double x, double y, double z, Random rand) {
		for (int count = 0; count < 20; ++count) {
			double velX = 0.0D;
			double velY = 0.0D;
			double velZ = 0.0D;
			int motionX = rand.nextInt(2) * 2 - 1;
			int motionZ = rand.nextInt(2) * 2 - 1;
			velY = (rand.nextFloat() - 0.5D) * 0.125D;
			velZ = rand.nextFloat() * 1.0F * motionZ;
			velX = rand.nextFloat() * 1.0F * motionX;
			Erebus.PROXY.spawnCustomParticle("portal", getEntityWorld(), x, y, z, velX, velY, velZ);
		}
	}

	public void flyAbout() {
		if (rand.nextInt(200) == 0)
			rotationYawHead = rand.nextInt(360);
		if (currentFlightTarget != null && !getEntityWorld().isAirBlock(currentFlightTarget) && (getEntityWorld().isAirBlock(currentFlightTarget.down(3)) || currentFlightTarget.getY() < 1))
			currentFlightTarget = null;
		if (currentFlightTarget == null || rand.nextInt(30) == 0 || currentFlightTarget.distanceSq((int) posX, (int) posY, (int) posZ) < 4.0F)
			currentFlightTarget = new BlockPos((int) posX + rand.nextInt(10) - rand.nextInt(10), (int) posY + rand.nextInt(4) - rand.nextInt(4), (int) posZ + rand.nextInt(10) - rand.nextInt(10));
		if (currentFlightTarget != null && getAttackTarget() != null && !getEntityWorld().isAirBlock(currentFlightTarget.down(3)) || currentFlightTarget != null && !getDropped() && currentFlightTarget.getY() < pickupHeight + 10D)
			currentFlightTarget.up(1);
		flyToTarget();
	}

	public void flyToTarget() {
		double targetX = currentFlightTarget.getX() + 0.5D - posX;
		double targetY = currentFlightTarget.getY() + 1D - posY;
		double targetZ = currentFlightTarget.getZ() + 0.5D - posZ;
		motionX += (Math.signum(targetX) * 0.5D - motionX) * 0.20000000149011612D;
		motionY += (Math.signum(targetY) * 0.699999988079071D - motionY) * 0.30000000149011612D;
		motionZ += (Math.signum(targetZ) * 0.5D - motionZ) * 0.20000000149011612D;
		float angle = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
		float rotation = MathHelper.wrapDegrees(angle - rotationYaw);
		moveForward = 0.5F;
		rotationYaw += rotation;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		if (player.isSneaking())
			player.setSneaking(false);
		if (!getEntityWorld().isRemote && !player.capabilities.isCreativeMode && !captured() && rand.nextInt(20) == 0 && !getDropped()) {
			player.startRiding(this, true);
			pickupHeight = posY;
			setCountdown(60);
		}
	}

	@Override
	public void updatePassenger(Entity entity) {
		super.updatePassenger(entity);
		if (entity instanceof EntityLivingBase) {
			double a = Math.toRadians(renderYawOffset);
			double offSetX = -Math.sin(a) * -0.6D;
			double offSetZ = Math.cos(a) * -0.6D;
			entity.setPosition(posX - offSetX, posY - 2, posZ - offSetZ);
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amp) {
		if (getIsInvulnerable())
			return false;
		else if (super.attackEntityFrom(source, amp)) {
			Entity entity = source.getTrueSource();
			if (getControllingPassenger() != entity && getRidingEntity() != entity) {
				if (entity != this)
					entityToAttack = (EntityLivingBase) entity;
				return true;
			} else
				return true;
		} else
			return false;
	}

	@Override
	public boolean getCanSpawnHere() {
		BlockPos blockpos = new BlockPos(posX, getEntityBoundingBox().minY, posZ);
		if (blockpos.getY() > 100)
			return false;
		else {
			int lightValue = world.getLightFromNeighbors(blockpos);
			return lightValue > rand.nextInt(7) ? false : super.getCanSpawnHere();
		}
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 6;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.DRAGONFLY_WING.ordinal()), 0.0F);
		if (rand.nextInt(5) == 0)
			entityDropItem(new ItemStack(ModItems.MATERIALS, rand.nextInt(1) + 1 + looting, EnumErebusMaterialsType.COMPOUND_EYES.ordinal()), 0.0F);
		if (getSkin() == 0)
			entityDropItem(new ItemStack(Items.ENDER_PEARL, rand.nextInt(1) + 1 + looting), 0.0F);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		return super.attackEntityAsMob(entity);
		// I know this does nothing! - But it may soon!
	}

	public void setSkin(int skinType) {
		dataManager.set(SKIN_TYPE, skinType);
	}

	public int getSkin() {
		return dataManager.get(SKIN_TYPE).intValue();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("dragonflySkin", getSkin());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setSkin(nbt.getInteger("dragonflySkin"));
	}
}
