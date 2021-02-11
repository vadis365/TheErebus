package erebus.entity;

import java.util.Random;

import javax.annotation.Nullable;

import erebus.Erebus;
import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import erebus.entity.ai.EntityAIFlyingWander;
import erebus.entity.ai.FlyingMoveHelper;
import erebus.entity.ai.PathNavigateFlying;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDragonfly extends EntityMob {

	public double pickupHeight;
	private boolean dropped;
	private int droptime = 0;// cool-down for picking up
	private int countDown;// makes sure player is always dropped
	private static final DataParameter<Integer> SKIN_TYPE = EntityDataManager.<Integer>createKey(EntityDragonfly.class, DataSerializers.VARINT);

	public EntityDragonfly(World world) {
		super(world);
		setSize(ConfigHandler.INSTANCE.dragonfly_width, ConfigHandler.INSTANCE.dragonfly_height);
		moveHelper = new FlyingMoveHelper(this);
		setPathPriority(PathNodeType.BLOCKED, -8.0F);
		setPathPriority(PathNodeType.OPEN, 8.0F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(SKIN_TYPE, rand.nextInt(51));
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIErebusAttackMelee(this, ConfigHandler.INSTANCE.dragonfly_attackMovementSpeed, true));
		tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(4, new EntityAILookIdle(this));
		tasks.addTask(5, new EntityAIFlyingWander(this, ConfigHandler.INSTANCE.dragonfly_wanderingSpeed, 1));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, ConfigHandler.INSTANCE.dragonfly_attackTogether));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? ConfigHandler.INSTANCE.dragonfly_baseHealth : ConfigHandler.INSTANCE.dragonfly_baseHealth * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? ConfigHandler.INSTANCE.dragonfly_baseDamage : ConfigHandler.INSTANCE.dragonfly_baseDamage * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ConfigHandler.INSTANCE.dragonfly_movementSpeed);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(ConfigHandler.INSTANCE.dragonfly_followRange);
		if (ConfigHandler.INSTANCE.dragonfly_armor > -1) {
			getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(ConfigHandler.INSTANCE.dragonfly_armor);
		}
		if (ConfigHandler.INSTANCE.dragonfly_armorToughness > -1) {
			getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(ConfigHandler.INSTANCE.dragonfly_armorToughness);
		}
		if (ConfigHandler.INSTANCE.dragonfly_knockbackResistance > -1) {
			getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(ConfigHandler.INSTANCE.dragonfly_knockbackResistance);
		}
		if (ConfigHandler.INSTANCE.dragonfly_luck > -1) {
			getEntityAttribute(SharedMonsterAttributes.LUCK).setBaseValue(ConfigHandler.INSTANCE.dragonfly_luck);
		}
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
    protected PathNavigate createNavigator(World world) {
		return new PathNavigateFlying(this, world);
	}

	@Override
    public boolean canRiderInteract() {
        return true;
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
		return ConfigHandler.INSTANCE.dragonfly_soundVolume;
	}

	@Override
	protected float getSoundPitch() {
		return super.getSoundPitch() * ConfigHandler.INSTANCE.dragonfly_soundPitch;
	}

	@Override
    public SoundEvent getAmbientSound() {
		return ModSounds.FLY_SOUND;
	}

	@Override
    protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.FLY_HURT;
	}

	@Override
    protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (motionY < 0.0D)
			motionY *= 0.3D;

		if (isBeingRidden()){
			if (getAttackTarget() != null && !getEntityWorld().isAirBlock(getPosition().down(3)) || !getDropped() && getPosition().getY() < pickupHeight + 10D) {
				getNavigator().clearPath();
				getNavigator().tryMoveToXYZ(this.posX, this.posY + 10D, this.posZ, 1D);
				motionY += 0.08D;
			}

			if (!getEntityWorld().isRemote && captured() && (posY > pickupHeight + 10D || countDown <= 0 || !getEntityWorld().isRemote && captured() && getEntityWorld().isSideSolid(new BlockPos (MathHelper.floor(posX), MathHelper.floor(posY + 1D), MathHelper.floor(posZ)), EnumFacing.DOWN))) {
				setDropped(true);
				removePassengers();
			}
		}

		if (dropped) {
			droptime++;
			if (droptime >= ConfigHandler.INSTANCE.dragonfly_dropTime) {
				setDropped(false);
				droptime = 0;
			}
		}

		if (countDown >= 0)
			countDown--;

		if (getEntityWorld().isRemote)
			if (getSkin() == 0) {
				spawnParticles(getEntityWorld(), posX, posY, posZ, rand);
				if (!hasCustomName())
					setCustomNameTag("Ender Dragonfly");
			}

		if(isInWater())
			getMoveHelper().setMoveTo(this.posX, this.posY + 1, this.posZ, ConfigHandler.INSTANCE.dragonfly_speedWater);
	}

	@SideOnly(Side.CLIENT)
	public void spawnParticles(World world, double x, double y, double z, Random rand) {
		for (int count = 0; count < ConfigHandler.INSTANCE.dragonfly_particleCount; ++count) {
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

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		if (player.isSneaking())
			player.setSneaking(false);
		if (ConfigHandler.INSTANCE.dragonfly_pickupPlayer && !getEntityWorld().isRemote && !player.capabilities.isCreativeMode && !captured() && rand.nextInt(20) == 0 && !getDropped()) {
			player.startRiding(this, true);
			pickupHeight = posY;
			setPosition(posX, player.posY + getYOffset(), posZ);
			setCountdown(60);
		}
	}

	@Override
	public double getYOffset() {
		if (getCapturedPlayer() != null)
			return getCapturedPlayer().height;
		else
			return super.getYOffset();
	}

	public EntityPlayer getCapturedPlayer() {
		for (Entity entity : this.getPassengers())
			if (entity instanceof EntityPlayer)
				return (EntityPlayer) entity;
		return null;
	}

	@Override
	public void updatePassenger(Entity entity) {
		super.updatePassenger(entity);
		if (entity instanceof EntityLivingBase) {
			double a = Math.toRadians(renderYawOffset);
			double offSetX = -Math.sin(a) * -0.6D;
			double offSetZ = Math.cos(a) * -0.6D;
			entity.setPosition(posX - offSetX, posY - getYOffset(), posZ - offSetZ);
			if (entity.isSneaking())
				entity.setSneaking(false);
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amp) {
		if (getIsInvulnerable())
			return false;
		else if (super.attackEntityFrom(source, amp)) {
			Entity entity = source.getTrueSource();
			if (isBeingRidden() && getCapturedPlayer() != null) {
				if (entity == getCapturedPlayer())
					setDropped(true);
					removePassengers();
				return true;
			} else
				return true;
		} else
			return false;
	}

	@Override
	public boolean getCanSpawnHere() {
		BlockPos blockpos = new BlockPos(posX, getEntityBoundingBox().minY, posZ);
		if (blockpos.getY() > ConfigHandler.INSTANCE.getDragonfly_spawnMaxHeight || blockpos.getY() < ConfigHandler.INSTANCE.dragonfly_spawnMinHeight)
			return false;
		else {
			int lightValue = world.getLightFromNeighbors(blockpos);
			return lightValue > rand.nextInt(ConfigHandler.INSTANCE.dragonfly_spawnMinLightLevel) ? false : isNotColliding() && super.getCanSpawnHere();
		}
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return ConfigHandler.INSTANCE.dragonfly_maxInChunk;
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

	@Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        setSkin(rand.nextInt(51));
		return super.onInitialSpawn(difficulty, livingdata);
	}

	public void setSkin(int skinType) {
		dataManager.set(SKIN_TYPE, skinType);
	}

	public int getSkin() {
		return dataManager.get(SKIN_TYPE);
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
