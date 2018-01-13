package erebus.entity;

import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.helper.Utils;
import erebus.entity.ai.EntityAIFlyingWander;
import erebus.entity.ai.EntityAIPolinate;
import erebus.entity.ai.FlyingMoveHelper;
import erebus.entity.ai.PathNavigateFlying;
import erebus.items.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityWorkerBee extends EntityTameable {
	public boolean beeFlying;
	public boolean beePollinating = false;
	public boolean beeCollecting = false;
	private static final DataParameter<Integer> DROP_POINT_X = EntityDataManager.<Integer>createKey(EntityWorkerBee.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> DROP_POINT_Y = EntityDataManager.<Integer>createKey(EntityWorkerBee.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> DROP_POINT_Z = EntityDataManager.<Integer>createKey(EntityWorkerBee.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> NECTAR_POINTS = EntityDataManager.<Integer>createKey(EntityWorkerBee.class, DataSerializers.VARINT);
	private static final DataParameter<Byte> TAME_STATE = EntityDataManager.<Byte>createKey(EntityWorkerBee.class, DataSerializers.BYTE);
	private EntityAIFlyingWander aiFlyingWander;

	public EntityWorkerBee(World world) {
		super(world);
		setSize(0.5F, 0.4F);
		moveHelper = new FlyingMoveHelper(this);
		setPathPriority(PathNodeType.WATER, -8F);
		setPathPriority(PathNodeType.BLOCKED, -8.0F);
		setPathPriority(PathNodeType.OPEN, 8.0F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
        dataManager.register(DROP_POINT_X , Integer.valueOf(0));
        dataManager.register(DROP_POINT_Y , Integer.valueOf(0));
        dataManager.register(DROP_POINT_Z , Integer.valueOf(0));
        dataManager.register(NECTAR_POINTS , Integer.valueOf(0));
        dataManager.register(TAME_STATE , Byte.valueOf((byte)0));
	}

	@Override
	protected void initEntityAI() {
		aiFlyingWander = new EntityAIFlyingWander(this, 0.5D, 20);
		tasks.addTask(0, new EntityAIPolinate(this, 10));
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackMelee(this, 0.3D, true));
		//tasks.addTask(3, new EntityAITempt(this, 0.5D, Items.SUGAR, false));
		tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityWasp.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30D);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness();
		if (light >= 0F)
			return getEntityWorld().checkNoEntityCollision(getEntityBoundingBox()) && getEntityWorld().getCollisionBoxes(this, getEntityBoundingBox()).isEmpty() && !getEntityWorld().containsAnyLiquid(getEntityBoundingBox());
		return super.getCanSpawnHere();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 3;
	}

	@Override
	protected boolean canDespawn() {
		if (getTameState() != 0)
			return false;
		else
			return true;
	}

	public boolean isFlying() {
		return !onGround;
	}

	@Override
	public void onUpdate() {
		if (motionY < 0.0D)
			motionY *= 0.4D;

		if (!getEntityWorld().isRemote) {
			if(ticksExisted == 1)
				if(getTameState() == 0)
					tasks.addTask(3, aiFlyingWander);

			if (beeCollecting && !beePollinating)
				getNavigator().tryMoveToXYZ(getDropPointX() + 0.5D, getDropPointY() + 1D, getDropPointZ() + 0.5D, 0.25D);
			
			if (getDistance(getDropPointX() + 0.5D, getDropPointY() + 0.5D, getDropPointZ() + 0.5D) < 1D && getNectarPoints() > 0) {
				addHoneyToInventory(getDropPointX(), getDropPointY(), getDropPointZ());
				setBeeCollecting(false);
				getNavigator().clearPath();
			}

			if(isInWater())
				getMoveHelper().setMoveTo(this.posX, this.posY + 1, this.posZ, 0.32D);
		}
		super.onUpdate();
	}

	private void addHoneyToInventory(int x, int y, int z) {
		if (Utils.addItemStackToInventory(Utils.getTileEntity(getEntityWorld(),new BlockPos(x, y, z), IInventory.class), ItemMaterials.EnumErebusMaterialsType.NECTAR.createStack(getNectarPoints())))
			setNectarPoints(0);
	}

	public void setBeeFlying(boolean state) {
		beeFlying = state;
	}

	public void setBeePollinating(boolean state) {
		beePollinating = state;
	}

	public void setBeeCollecting(boolean state) {
		beeCollecting = state;
	}

	@Override
	protected PathNavigate createNavigator(World world) {
		return new PathNavigateFlying(this, world);
	}

	public void flyAbout() {

		flyToTarget();
	}

	public void flyToTarget() {

	}
	
	public void fall(float distance, float damageMultiplier) { 
	 }
	
	private void land() {
	}
	
	@Override
	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.WASP_SOUND;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.WASP_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (recentlyHit) {
			entityDropItem(ItemMaterials.EnumErebusMaterialsType.NECTAR.createStack(2), 0.0F);
		}
	}

	@Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
    }

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.inventory.getCurrentItem();
		if (!getEntityWorld().isRemote  && !stack.isEmpty() && stack.getItem() ==  ModItems.NECTAR_COLLECTOR)
			if (getNectarPoints() > 0) {
				entityDropItem(ItemMaterials.EnumErebusMaterialsType.NECTAR.createStack(2), 0.0F);
				stack.damageItem(1, player);
				setNectarPoints(getNectarPoints() - 2);
				return true;
			}

		if (stack != null && stack.getItem() == ModItems.BEE_TAMING_AMULET && stack.hasTagCompound() && stack.getTagCompound().hasKey("homeX")) {
			if (!getEntityWorld().isRemote) {
				setDropPoint(stack.getTagCompound().getInteger("homeX"), stack.getTagCompound().getInteger("homeY"), stack.getTagCompound().getInteger("homeZ"));
				setTameState((byte) 1);
				tasks.removeTask(aiFlyingWander);
				setAttackTarget((EntityLivingBase) null);
			}
			playTameEffect(true);
			player.swingArm(hand);
			return true;
		}
		return super.processInteract(player, hand);
	}

	public void setDropPoint(int x, int y, int z) {
		dataManager.set(DROP_POINT_X, Integer.valueOf(x));
		dataManager.set(DROP_POINT_Y, Integer.valueOf(y));
		dataManager.set(DROP_POINT_Z, Integer.valueOf(z));
	}

	public int getDropPointX() {
		return dataManager.get(DROP_POINT_X);
	}

	public int getDropPointY() {
		return dataManager.get(DROP_POINT_Y);
	}

	public int getDropPointZ() {
		return dataManager.get(DROP_POINT_Z);
	}

	public void setTameState(byte state) {
		dataManager.set(TAME_STATE, Byte.valueOf(state));
	}

	public byte getTameState() {
		return dataManager.get(TAME_STATE);
	}

	public void setNectarPoints(int count) {
		dataManager.set(NECTAR_POINTS, Integer.valueOf(count));
	}

	public int getNectarPoints() {
		return dataManager.get(NECTAR_POINTS);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return null;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("nectarPoints", getNectarPoints());
		nbt.setByte("tameState", getTameState());
		nbt.setInteger("dropPointX", getDropPointX());
		nbt.setInteger("dropPointY", getDropPointY());
		nbt.setInteger("dropPointZ", getDropPointZ());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setNectarPoints(nbt.getInteger("nectarPoints"));
		setTameState(nbt.getByte("tameState"));
		setDropPoint(nbt.getInteger("dropPointX"), nbt.getInteger("dropPointY"), nbt.getInteger("dropPointZ"));
	}
}