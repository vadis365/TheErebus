package erebus.entity;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.core.helper.Utils;
import erebus.items.ItemDungeonIdols.EnumIdolType;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityUmberGolemDungeonTypes extends EntityMob implements IEntityAdditionalSpawnData {
	
	private static final DataParameter<Byte> BOSS_TYPE = EntityDataManager.<Byte>createKey(EntityUmberGolemDungeonTypes.class, DataSerializers.BYTE);
	private static final DataParameter<Integer> RANGE_TIMER = EntityDataManager.<Integer>createKey(EntityUmberGolemDungeonTypes.class, DataSerializers.VARINT);
	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
	IBlockState blockState;
	boolean hasBlock = false;
	float hardness;
	int breakTime = 0;

	public EntityUmberGolemDungeonTypes(World world) {
		super(world);
		setSize(1.0F, 1.9F);
		isImmuneToFire = true;
		experienceValue = 120;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(RANGE_TIMER, 0);
		dataManager.register(BOSS_TYPE, new Byte((byte) 0));
	}
	
	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAttackMelee(this, 0.5D, true));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.7D);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
	}

	@Override
    public boolean isNonBoss() {
        return false;
    }

	private double getAttackStrength() {
		switch (getType()) {
			case 0:
				return 4.0D;
			case 1:
				return 5.0D;
			case 2:
				return 6.0D;
			case 3:
				return 7.0D;
			default:
				return 4.0D;
		}
	}

	private double getGolemHealth() {
		switch (getType()) {
			case 0:
				return 100.0D;
			case 1:
				return 150.0D;
			case 2:
				return 200.0D;
			case 3:
				return 250.0D;
			default:
				return 100.0D;
		}
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean canDespawn() {
		return false;
	}

	@Override
	public void setInWeb() {
	}

	/*
	 * protected String getLivingSound() { return "erebus:umbergolemsound"; } protected String getHurtSound() { return "erebus:umbergolemhurt"; }
	 */

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean hitByPlayer, int looting) {
		EnumIdolType type = EnumIdolType.values()[Math.max(0, Math.min(EnumIdolType.values().length, getType()))];

		entityDropItem(new ItemStack(ModItems.IDOLS, 1, type.ordinal()), 0.0F);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (getAttackTarget() != null) {
			float distance = (float) getDistance(getAttackTarget().posX, getAttackTarget().getEntityBoundingBox().minY, getAttackTarget().posZ);
			if (!hasBlock) {
				if (getRangeAttackTimer() < 100 && distance > 3)
					setRangeAttackTimer(getRangeAttackTimer() + 2);
				if (getRangeAttackTimer() == 100 && distance > 3)
					shootMissile(getAttackTarget(), distance);
			}

			if (oneShotMoveCheat())
				getMoveHelper().setMoveTo(getAttackTarget().posX, getAttackTarget().posY, getAttackTarget().posZ, 0.5D);

			if (collidedHorizontally) {
				double direction = Math.toRadians(renderYawOffset);
				BlockPos posLower = new BlockPos((posX + -Math.sin(direction) * 1.5D), posY, (posZ + Math.cos(direction) * 1.5D));
				BlockPos posUpper = new BlockPos((posX + -Math.sin(direction) * 1.5D), posY + 1, (posZ + Math.cos(direction) * 1.5D));
				if(!getEntityWorld().isAirBlock(posLower))
					removeBlock(posLower);
				if(!getEntityWorld().isAirBlock(posUpper))
					removeBlock(posUpper);
			}
		}
	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();

		if (!getEntityWorld().isRemote) {
			bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
			switch (getType()) {
			case 0:
				bossInfo.setColor(BossInfo.Color.RED);
				break;
			case 1:
				bossInfo.setColor(BossInfo.Color.WHITE);
				break;
			case 2:
				bossInfo.setColor(BossInfo.Color.YELLOW);
				break;
			case 3:
				bossInfo.setColor(BossInfo.Color.GREEN);
				break;
			default:
				bossInfo.setColor(BossInfo.Color.GREEN);
				break;
			}
		}
	}

	private boolean oneShotMoveCheat() {
		return !getNavigator().tryMoveToEntityLiving(getAttackTarget(), 0.5D);
	}

	protected void removeBlock(BlockPos pos) {
		if (!hasBlock) {
			hasBlock = true;
			breakTime = 0;
			blockState = getEntityWorld().getBlockState(pos);
			hardness = blockState.getBlockHardness(getEntityWorld(), pos);
		}

		if (hardness <= 0.0F || blockState == null || !canBreakBlock(blockState) || !isInSamePos())
			hasBlock = false;

		if (hasBlock) {
			breakTime += 1;
			blockState = getEntityWorld().getBlockState(pos);
			int i = (int) (breakTime / (hardness * 160.0F) * 10.0F);
			getEntityWorld().sendBlockBreakProgress(getEntityId(), pos, i);

			if (getEntityWorld().rand.nextInt(30) == 0)
				getEntityWorld().playEvent(null, 2001, pos, Block.getIdFromBlock(blockState.getBlock()));

			if (breakTime >= hardness * 160.0F) {
				Utils.dropStack(getEntityWorld(), pos, new ItemStack(blockState.getBlock(), 1));
				getEntityWorld().setBlockToAir(pos);
				breakTime = 0;
				hasBlock = false;
				getEntityWorld().playEvent(null, 2001, pos, Block.getIdFromBlock(blockState.getBlock()));
			}
		}
	}

	protected boolean canBreakBlock(IBlockState state) {
		if (state.getBlock() == ModBlocks.GNEISS)
			return false;
		if (state.getBlock().hasTileEntity(state))
			return false;
		return true;
	}

	private boolean isInSamePos() {
		return (int) posX == (int) lastTickPosX && (int) posY == (int) lastTickPosY && (int) posZ == (int) lastTickPosZ;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		// TODO Add a different attack style for each type
		return attack(entity);
	}

	protected boolean attack(Entity entity) {
		if (!getEntityWorld().isRemote && canEntityBeSeen(entity)) {
			if (onGround) {
				double d0 = entity.posX - posX;
				double d1 = entity.posZ - posZ;
				float f2 = MathHelper.sqrt(d0 * d0 + d1 * d1);
				motionX = d0 / f2 * 0.5D * 0.800000011920929D + motionX * 0.20000000298023224D;
				motionZ = d1 / f2 * 0.5D * 0.800000011920929D + motionZ * 0.20000000298023224D;
				motionY = 0.4000000059604645D;
			}
			int knockback = 1;
			entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) (ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? getAttackStrength() : getAttackStrength() * ConfigHandler.INSTANCE.mobAttackDamageMultiplier));
			entity.addVelocity(-MathHelper.sin(rotationYaw * 3.141593F / 180.0F) * knockback * 0.5F, 0.4D, MathHelper.cos(rotationYaw * 3.141593F / 180.0F) * knockback * 0.5F);
			getEntityWorld().playSound(null, getPosition(), SoundEvents.ENTITY_PLAYER_BIG_FALL, SoundCategory.HOSTILE, 1.0F, 1.0F);
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, getEntityWorld().getDifficulty().ordinal() * 50, 0));
			return true;
		}
		return true;
	}

	public EntityThrowable getMissileType() {
		switch (getType()) {
			case 0:
				return new EntityGooBall(getEntityWorld(), this);
			case 1:
			case 2:
				return new EntityWebSling(getEntityWorld(), this);
			case 3:
				return new EntityPoisonJet(getEntityWorld(), this);
			default:
				return new EntityGooBall(getEntityWorld(), this);
		}
	}

	public void shootMissile(EntityLivingBase entity, float distance) {
		setRangeAttackTimer(0);
		if (canEntityBeSeen(entity)) {
			EntityThrowable missile = getMissileType();
			if (getType() == 1)
				((EntityWebSling) missile).setType((byte) 0);
			if (getType() == 2)
				((EntityWebSling) missile).setType((byte) 2);
			missile.rotationPitch -= -20.0F;
			double targetX = entity.posX + entity.motionX - posX;
			double targetY = entity.posY + entity.getEyeHeight() - 1.100000023841858D - posY;
			double targetZ = entity.posZ + entity.motionZ - posZ;
			float target = MathHelper.sqrt(targetX * targetX + targetZ * targetZ);
			missile.shoot(targetX, targetY + target * 0.1F, targetZ, 0.75F, 8.0F);
			getEntityWorld().spawnEntity(missile);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setByte("type", getType());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setType(nbt.getByte("type"));
        if(hasCustomName())
        	bossInfo.setName(this.getDisplayName());
	}

	@Override
    public void setCustomNameTag(String name) {
        super.setCustomNameTag(name);
        bossInfo.setName(this.getDisplayName());
    }

	public void setType(byte type) {
		dataManager.set(BOSS_TYPE, type);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? getGolemHealth() : getGolemHealth() * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? getAttackStrength() : getAttackStrength() * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
	}

	@Override
    public void addTrackingPlayer(EntityPlayerMP player) {
		super.addTrackingPlayer(player);
        bossInfo.addPlayer(player);
    }

	@Override
    public void removeTrackingPlayer(EntityPlayerMP player) {
        super.removeTrackingPlayer(player);
        bossInfo.removePlayer(player);
    }

	public byte getType() {
		return dataManager.get(BOSS_TYPE);
	}

	public void setRangeAttackTimer(int time) {
		dataManager.set(RANGE_TIMER, time);
	}

	public int getRangeAttackTimer() {
		return dataManager.get(RANGE_TIMER);
	}

	@Override
	public void writeSpawnData(ByteBuf data) {
		data.writeByte(getType());
	}

	@Override
	public void readSpawnData(ByteBuf data) {
		setType(data.readByte());
	}
}
