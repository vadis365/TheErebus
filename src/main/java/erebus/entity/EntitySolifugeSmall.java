package erebus.entity;

import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntitySolifugeSmall extends EntityMob implements IEntityAdditionalSpawnData {

	private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntitySolifugeSmall.class, DataSerializers.BYTE);
	private static final DataParameter<Byte> POTION_TYPE = EntityDataManager.<Byte>createKey(EntitySolifugeSmall.class, DataSerializers.BYTE);
	public final String[] POTION_NAME = new String[] { "Move Slowdown", "Dig Slowdown", "Confusion", "Blindness", "Hunger", "Weakness", "Poison", "Wither", "Levitation" };
	public final byte[] POTION_IDS = new byte[] { 2, 4, 9, 15, 17, 18, 19, 20, 25 };

	public EntitySolifugeSmall(World world) {
		super(world);
		setSize(1.0F, 0.5F);
		isImmuneToFire = true;
		experienceValue = 3;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(POTION_TYPE, new Byte((byte) 0));
		dataManager.register(CLIMBING, new Byte((byte)0));
	}
	
	@Override
    protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAILeapAtTarget(this, 0.4F));
		tasks.addTask(2, new EntityAIErebusAttackMelee(this, 0.3D, false));
		tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(4, new EntityAIWander(this, 0.5D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.7D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 10D : 10D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 1D : 1D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
	}

	@Override
    protected PathNavigate createNavigator(World worldIn) {
        return new PathNavigateClimber(this, worldIn);
    }

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness();
		if (light >= 0F)
			return isNotColliding();
		return super.getCanSpawnHere();
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(!hasCustomName())
			setCustomNameTag(POTION_NAME[getPotionEffect()] + " Solifuge");
        if (!world.isRemote) {
            setBesideClimbableBlock(isCollidedHorizontally);
        }
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}

	@Override
	public void setInWeb() {
	}

	@Override
    public boolean isOnLadder() {
        return isBesideClimbableBlock();
    }
	
    public boolean isBesideClimbableBlock() {
        return (((Byte)dataManager.get(CLIMBING)).byteValue() & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean climbing) {
        byte b0 = ((Byte)dataManager.get(CLIMBING)).byteValue();
        if (climbing)
            b0 = (byte)(b0 | 1);
        else
            b0 = (byte)(b0 & -2);

        dataManager.set(CLIMBING, Byte.valueOf(b0));
    }

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_SPIDER_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_SPIDER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SPIDER_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block block) {
		playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
	}

	@Override
	protected float getSoundPitch() {
		return super.getSoundPitch() * 2F;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.equals(DamageSource.IN_WALL))
			return false;
		return super.attackEntityFrom(source, damage);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (canEntityBeSeen(entity)) {
			if (super.attackEntityAsMob(entity)) {
				if (entity instanceof EntityLivingBase) {
					byte duration = 0;

					if (getEntityWorld().getDifficulty().ordinal() > EnumDifficulty.EASY.ordinal())
						if (getEntityWorld().getDifficulty() == EnumDifficulty.NORMAL)
							duration = 5;
						else if (getEntityWorld().getDifficulty() == EnumDifficulty.HARD)
							duration = 10;

					if (duration > 0)
						((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.getPotionById(POTION_IDS[getPotionEffect()]), duration * 20, 0));
				}
			}
			return true;
		} else
			return false;
	}

	public byte getPotionEffect() {
		return dataManager.get(POTION_TYPE);
	}

	public void setPotionEffect(byte type) {
		dataManager.set(POTION_TYPE, type);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setByte("effect", getPotionEffect());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setPotionEffect(nbt.getByte("effect"));
	}

	@Override
	public void writeSpawnData(ByteBuf data) {
		data.writeByte(getPotionEffect());
	}

	@Override
	public void readSpawnData(ByteBuf data) {
		setPotionEffect(data.readByte());
	}
}