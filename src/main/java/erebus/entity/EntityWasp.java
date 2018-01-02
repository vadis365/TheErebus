package erebus.entity;

import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import erebus.entity.ai.EntityAIFlyingWander;
import erebus.entity.ai.FlyingMoveHelper;
import erebus.entity.ai.PathNavigateFlying;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityWasp extends EntityMob implements IEntityAdditionalSpawnData {

	private boolean areAttributesSetup = false;
	public boolean waspFlying;
	private static final DataParameter<Byte> IS_BOSS = EntityDataManager.<Byte>createKey(EntityWasp.class, DataSerializers.BYTE);

	public EntityWasp(World world) {
		super(world);
		moveHelper = new FlyingMoveHelper(this);
		setPathPriority(PathNodeType.WATER, -8F);
		setPathPriority(PathNodeType.BLOCKED, -8.0F);
		setPathPriority(PathNodeType.OPEN, 8.0F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(IS_BOSS, new Byte((byte) rand.nextInt(32)));
	}
	
	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIErebusAttackMelee(this, 0.5D, true));
		tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(4, new EntityAILookIdle(this));
		tasks.addTask(5, new EntityAIFlyingWander(this, 0.75D, 1));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityGrasshopper.class, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityBeetle.class, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityBeetleLarva.class, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWorkerBee.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		areAttributesSetup = true;
		updateBossAttributes();
	}

	protected void updateBossAttributes() {
		if (getEntityWorld() != null && !getEntityWorld().isRemote)
			if (getIsBoss() == 1) {
				setSize(1.5F, 1.0F);
				experienceValue = 25;
				getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.9D);
				getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 60D : 60D * ConfigHandler.INSTANCE.mobHealthMultipier);
				getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 8D : 8D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
				getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
			} else {
				setSize(0.5F, 0.4F);
				experienceValue = 10;
				getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.75D);
				getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 25D : 25D * ConfigHandler.INSTANCE.mobHealthMultipier);
				getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 4D : 4D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
				getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
			}
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness();
		if (light >= 0F)
			return isNotColliding();
		return super.getCanSpawnHere();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
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
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
    }

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.WASP_STING.ordinal()), 0.0F);
		if (getIsBoss() == 1)
			entityDropItem(new ItemStack(ModItems.ANTI_VENOM_BOTTLE), 0.0F);
	}

	public boolean isFlying() {
		return !onGround;
	}

	public void setWaspFlying(boolean state) {
		waspFlying = state;
	}

	@Override
	public void onUpdate() {
		byte i;
		if (getEntityWorld().isRemote) {
			i = getIsBoss();
			if (i == 1) {
				setSize(1.5F, 1.0F);
				if (!hasCustomName())
					if (rand.nextBoolean())
						setCustomNameTag("Livid's Bane");
					else
						setCustomNameTag("Hornet of Despair");
			} else
				setSize(0.5F, 0.4F);
		}

		if (motionY < 0.0D)
			motionY *= 0.4D;

		if(isInWater())
			getMoveHelper().setMoveTo(this.posX, this.posY + 1, this.posZ, 0.32D);

		super.onUpdate();
	}

	@Override
    protected PathNavigate createNavigator(World world) {
		return new PathNavigateFlying(this, world);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean canAttackClass(Class entity) {
		return EntityWasp.class != entity;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (canEntityBeSeen(entity)) {
			if (super.attackEntityAsMob(entity)) {
				if (entity instanceof EntityLivingBase) {
					byte duration = 0;

					if (getEntityWorld().getDifficulty().ordinal() > EnumDifficulty.EASY.ordinal())
						if (getEntityWorld().getDifficulty() == EnumDifficulty.NORMAL)
							duration = 3;
						else if (getEntityWorld().getDifficulty() == EnumDifficulty.HARD)
							duration = 5;

					if (duration > 0)
						((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.POISON, duration * 20, 0));
				}
			}
			return true;
		} else
			return false;
	}

	public byte getIsBoss() {
		return dataManager.get(IS_BOSS).byteValue();
	}

	public void setIsBoss(byte boss) {
		dataManager.set(IS_BOSS, Byte.valueOf(boss));
		getEntityWorld().setEntityState(this, (byte) 25);
		if (areAttributesSetup)
			updateBossAttributes();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setByte("mobType", getIsBoss());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setIsBoss(nbt.getByte("mobType"));
	}

	@Override
	public void writeSpawnData(ByteBuf data) {
		data.writeByte(getIsBoss());
	}

	@Override
	public void readSpawnData(ByteBuf data) {
		setIsBoss(data.readByte());
	}
}