package erebus.entity;

import javax.annotation.Nullable;

import erebus.ModItems;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import erebus.items.ItemErebusFood.EnumFoodType;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
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
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityTarantula extends EntityMob {

	private static final DataParameter<Integer> SKIN_TYPE = EntityDataManager.<Integer>createKey(EntityTarantula.class, DataSerializers.VARINT);
	private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityTarantula.class, DataSerializers.BYTE);

	public EntityTarantula(World world) {
		super(world);
		setSize(1.3F, 0.6F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(SKIN_TYPE, rand.nextInt(3));
		dataManager.register(CLIMBING, Byte.valueOf((byte)0));
	}

	@Override
    protected void initEntityAI() {
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAILeapAtTarget(this, 0.4F));
        tasks.addTask(2, new EntityAIErebusAttackMelee(this, 0.6D, true));
        tasks.addTask(3, new EntityAIWanderAvoidWater(this, 0.4D));
        tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(5, new EntityAILookIdle(this));
        targetTasks.addTask(0, new EntityAIHurtByTarget(this, false, new Class[0]));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 30D : 30D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 5D : 5D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
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
	public int getTotalArmorValue() {
		return 4;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
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
		this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
	}

	@Override
    protected PathNavigate createNavigator(World worldIn) {
        return new PathNavigateClimber(this, worldIn);
    }

	@Override
    public boolean isOnLadder() {
        return isBesideClimbableBlock();
    }

	@Override
    public void setInWeb() {
    }

	@Override
    public boolean isPotionApplicable(PotionEffect potioneffectIn) {
        return potioneffectIn.getPotion() == MobEffects.POISON ? false : super.isPotionApplicable(potioneffectIn);
    }

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!this.world.isRemote)
			setBesideClimbableBlock(this.isCollidedHorizontally);
	}

    public boolean isBesideClimbableBlock() {
        return (((Byte)dataManager.get(CLIMBING)).byteValue() & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean climbing){
        byte b0 = ((Byte)this.dataManager.get(CLIMBING)).byteValue();
        if (climbing)
            b0 = (byte)(b0 | 1);
        else
            b0 = (byte)(b0 & -2);
        dataManager.set(CLIMBING, Byte.valueOf(b0));
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
						((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.POISON, duration * 20, 0));
				}
			}
			return true;
		} else
			return false;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (isBurning())
			entityDropItem(new ItemStack(ModItems.EREBUS_FOOD, 1 + rand.nextInt(2 + looting), EnumFoodType.TARANTULA_LEG_COOKED.ordinal()), 0.0F);
		else
			entityDropItem(new ItemStack(ModItems.EREBUS_FOOD, 1 + rand.nextInt(2 + looting), EnumFoodType.TARANTULA_LEG_RAW.ordinal()), 0.0F);
		dropItem(Items.SPIDER_EYE, rand.nextInt(2) + looting);
	}

	@Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);

		if (getEntityWorld().rand.nextInt(100) == 0) {
			EntityMoneySpider moneyspider = new EntityMoneySpider(getEntityWorld());
			moneyspider.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
			moneyspider.onInitialSpawn(difficulty, (IEntityLivingData) null);
			getEntityWorld().spawnEntity(moneyspider);
			moneyspider.startRiding(this);
		}
		if (livingdata == null) {
			livingdata = new EntitySpider.GroupData();
            if (this.world.getDifficulty() == EnumDifficulty.HARD && this.world.rand.nextFloat() < 0.1F * difficulty.getClampedAdditionalDifficulty())
                ((EntitySpider.GroupData)livingdata).setRandomEffect(this.world.rand);

            if (livingdata instanceof EntitySpider.GroupData) {
                Potion potion = ((EntitySpider.GroupData)livingdata).effect;
                if (potion != null)
                    this.addPotionEffect(new PotionEffect(potion, Integer.MAX_VALUE));
            }
		}
		return livingdata;
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
		nbt.setInteger("skin", getSkin());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		if (nbt.hasKey("skin"))
			setSkin(nbt.getInteger("skin"));
		else
			setSkin(rand.nextInt(3));
	}
}