package erebus.entity;

import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFly extends EntityAmbientCreature {

	private BlockPos spawnPosition;
	private static final DataParameter<Byte> HANGING = EntityDataManager.<Byte>createKey(EntityFly.class, DataSerializers.BYTE);

	public EntityFly(World world) {
		super(world);
		setSize(0.5F, 0.45F);
		setIsFlyHanging(false);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
        dataManager.register(HANGING, Byte.valueOf((byte)0));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 4D : 4D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected float getSoundVolume() {
		return 0.1F;
	}

	@Override
	protected float getSoundPitch() {
		return super.getSoundPitch() * 0.95F;
	}

	@Override
    public SoundEvent getAmbientSound() {
		return getIsFlyHanging() && rand.nextInt(4) != 0 ? null : ModSounds.FLY_SOUND;
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
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected void collideWithEntity(Entity entity) {
	}

	public boolean getIsFlyHanging() {
		return (((Byte) dataManager.get(HANGING)).byteValue() & 1) != 0;
	}

	public void setIsFlyHanging(boolean isHanging) {
		byte b0 = ((Byte) dataManager.get(HANGING)).byteValue();
		if (isHanging)
			dataManager.set(HANGING, Byte.valueOf((byte) (b0 | 1)));
		else
			dataManager.set(HANGING, Byte.valueOf((byte) (b0 & -2)));
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (getIsFlyHanging()) {
			motionX = 0.0D;
			motionY = 0.0D;
			motionZ = 0.0D;
			posY = (double) MathHelper.floor(posY) + 1.0D - (double) height;
		} else {
			motionY *= 0.6000000238418579D;
		}
	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();
		BlockPos blockpos = new BlockPos(this);
		BlockPos blockpos1 = blockpos.up();

		if (getIsFlyHanging()) {
			if (world.getBlockState(blockpos1).isNormalCube()) {
				if (rand.nextInt(200) == 0)
					rotationYawHead = (float) rand.nextInt(360);

				if (world.getNearestPlayerNotCreative(this, 4.0D) != null) {
					setIsFlyHanging(false);
					world.playEvent((EntityPlayer) null, 1025, blockpos, 0);
				}
			} else {
				setIsFlyHanging(false);
				world.playEvent((EntityPlayer) null, 1025, blockpos, 0);
			}
		} else {
			if (spawnPosition != null && (!world.isAirBlock(spawnPosition) || spawnPosition.getY() < 1))
				spawnPosition = null;

			if (spawnPosition == null || rand.nextInt(30) == 0 || spawnPosition.distanceSq((double) ((int) posX), (double) ((int) posY), (double) ((int) posZ)) < 4.0D)
				spawnPosition = new BlockPos((int) posX + rand.nextInt(7) - rand.nextInt(7), (int) posY + rand.nextInt(6) - 2, (int) posZ + rand.nextInt(7) - rand.nextInt(7));

			double d0 = (double) spawnPosition.getX() + 0.5D - posX;
			double d1 = (double) spawnPosition.getY() + 0.1D - posY;
			double d2 = (double) spawnPosition.getZ() + 0.5D - posZ;
			motionX += (Math.signum(d0) * 0.5D - motionX) * 0.10000000149011612D;
			motionY += (Math.signum(d1) * 0.699999988079071D - motionY) * 0.10000000149011612D;
			motionZ += (Math.signum(d2) * 0.5D - motionZ) * 0.10000000149011612D;
			float f = (float) (MathHelper.atan2(motionZ, motionX) * (180D / Math.PI)) - 90.0F;
			float f1 = MathHelper.wrapDegrees(f - rotationYaw);
			moveForward = 0.5F;
			rotationYaw += f1;

			if (rand.nextInt(100) == 0 && world.getBlockState(blockpos1).isNormalCube()) {
				setIsFlyHanging(true);
			}
		}
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

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (isEntityInvulnerable(source))
			return false;
		else if (!world.isRemote && getIsFlyHanging())
			setIsFlyHanging(false);
		return super.attackEntityFrom(source, amount);
	}

	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		dataManager.set(HANGING, Byte.valueOf(compound.getByte("fly_hanging")));
	}

	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setByte("fly_hanging", ((Byte) dataManager.get(HANGING)).byteValue());
	}

	@Override
	public boolean getCanSpawnHere() {
		BlockPos blockpos = new BlockPos(posX, getEntityBoundingBox().minY, posZ);
		if (blockpos.getY() >= 120 || blockpos.getY() <= 20)
			return false;
		else {
			int lightValue = world.getLightFromNeighbors(blockpos);
			if (rand.nextBoolean())
				return false;
			return lightValue > rand.nextInt(4) ? false : isNotColliding() && super.getCanSpawnHere();
		}
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 5;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		if (rand.nextInt(10) == 0)
			entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.FLY_WING.ordinal()), 0.0F);
		if (rand.nextInt(20) == 0)
			entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.COMPOUND_EYES.ordinal()), 0.0F);
	}
}