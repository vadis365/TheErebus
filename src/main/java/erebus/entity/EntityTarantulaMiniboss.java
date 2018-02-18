package erebus.entity;

import javax.annotation.Nullable;

import erebus.Erebus;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.core.handler.configs.ConfigHandler;
import erebus.core.helper.Utils;
import erebus.entity.ai.EntityAITarantulaMinibossAttack;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityTarantulaMiniboss extends EntityMob {
	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.NOTCHED_10)).setDarkenSky(false);
	private static final DataParameter<Byte> SKIN_TYPE = EntityDataManager.<Byte>createKey(EntityTarantulaMiniboss.class, DataSerializers.BYTE);
	public int deathTicks;

	public EntityTarantulaMiniboss(World world) {
		super(world);
		setSize(4.0F, 1.2F);
		experienceValue = 100;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(SKIN_TYPE, (byte)1);
	}

	@Override
    protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAITarantulaMinibossAttack(this, EntityPlayer.class, 0.3D, false));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 300D : 300D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 8D : 8D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.9D);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public int getTotalArmorValue() {
		return 8;
	}

	@Override
	public void setInWeb() {
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
    public boolean isPotionApplicable(PotionEffect potioneffectIn) {
        return potioneffectIn.getPotion() == MobEffects.POISON ? false : super.isPotionApplicable(potioneffectIn);
    }

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_SPIDER_AMBIENT;
	}

	@Override
	protected SoundEvent  getHurtSound(DamageSource source) {
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
	public void onUpdate() {
		super.onUpdate();

		if (getHealth() <= getMaxHealth()/2 && getFancyRenderOverlay())
			dataManager.set(SKIN_TYPE, (byte) 0);

		if (getHealth() <= getMaxHealth()/2 && getHealth() > 0 && getAttackTarget() != null) {
			float distance = (float) getDistance(getAttackTarget().posX, getAttackTarget().getEntityBoundingBox().minY, getAttackTarget().posZ);
			forceCollideWithPlayer(getAttackTarget(), distance);
		}

	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();
		bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
	}

	@Override
	protected void onDeathUpdate() {
		++deathTicks;

		if (deathTicks % 25 == 1) {
			getEntityWorld().playSound(null, getPosition(), getDeathSound(), SoundCategory.HOSTILE, 1.0F, 0.1F);
			getEntityWorld().playSound(null, getPosition(), SoundEvents.ENTITY_SPIDER_HURT, SoundCategory.HOSTILE, 1.0F, 0.1F);
			getEntityWorld().playSound(null, getPosition(), SoundEvents.ENTITY_GHAST_HURT, SoundCategory.HOSTILE, 1.0F, 0.1F);
		}

		if (deathTicks >= 180 && deathTicks <= 200)
			if (!getEntityWorld().isRemote)
				Erebus.NETWORK_WRAPPER.sendToAll(new PacketParticle(ParticleType.BOSS_DEATH, (float) posX, (float)posY, (float)posZ));

		int i;
		int j;

		if (!getEntityWorld().isRemote)
			if (deathTicks > 150 && deathTicks % 5 == 0) {
				i = 1000;

				while (i > 0) {
					j = EntityXPOrb.getXPSplit(i);
					i -= j;
					getEntityWorld().spawnEntity(new EntityXPOrb(getEntityWorld(), posX, posY, posZ, j));
				}
			}

		move(MoverType.SELF, 0.0D, 0.310000000149011612D, 0.0D);
		renderYawOffset = prevRenderYawOffset += 0.05F;
		limbSwingAmount = 0.5F;
		if (deathTicks == 200 && !getEntityWorld().isRemote) {
			i = 2000;

			while (i > 0) {
				j = EntityXPOrb.getXPSplit(i);
				i -= j;
				getEntityWorld().spawnEntity(new EntityXPOrb(getEntityWorld(), posX, posY, posZ, j));
			}
			getEntityWorld().setBlockState(getPosition(), ModBlocks.TARANTULA_EGG.getDefaultState());
			Utils.dropStackNoRandom(getEntityWorld(), getPosition().up(2), new ItemStack(ModItems.SPIDER_T_SHIRT));
			setDead();
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (getHealth() > getMaxHealth()/2 && !(source instanceof EntityDamageSourceIndirect))
			return false;
		if (getHealth() <= getMaxHealth()/2 && source instanceof EntityDamageSourceIndirect)
			return false;
		return super.attackEntityFrom(source, damage);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity)) {
			if (entity instanceof EntityLiving) {
				byte duration = 0;

				if (getEntityWorld().getDifficulty().ordinal() > EnumDifficulty.EASY.ordinal() && rand.nextInt(19) == 0)
					if (getEntityWorld().getDifficulty() == EnumDifficulty.NORMAL)
						duration = 5;
					else if (getEntityWorld().getDifficulty() == EnumDifficulty.HARD)
						duration = 10;

				if (duration > 0)
					((EntityLiving) entity).addPotionEffect(new PotionEffect(MobEffects.POISON, duration * 20, 0));
			}
			return true;
		} else
			return false;
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

	public boolean getFancyRenderOverlay() {
		return dataManager.get(SKIN_TYPE) == 1;
	}

	public void forceCollideWithPlayer(EntityLivingBase entity, float distance) {
		if (distance > 1.0F && distance < 8.0F)
			if (onGround) {
				getLookHelper().setLookPositionWithEntity(entity, 30.0F, 30.0F);
				faceEntity(entity, 30.0F, 30.0F);
				double distanceX = entity.posX - posX;
				double distanceZ = entity.posZ - posZ;
				float squareRoot = MathHelper.sqrt(distanceX * distanceX + distanceZ * distanceZ);
				motionX = distanceX / squareRoot * 0.5D * 0.300000011920929D + motionX * 0.10000000298023224D;
				motionZ = distanceZ / squareRoot * 0.5D * 0.300000011920929D + motionZ * 0.10000000298023224D;
				motionY = 0D;
			}
	}

	@Override
	public void addVelocity(double x, double y, double z) {
		if (getHealth() > 150) {
			motionX = 0;
			motionY += y;
			motionZ = 0;
			isAirBorne = false;
		} else {
			motionX += x;
			motionY += y;
			motionZ += z;
			isAirBorne = true;
		}
	}

	public void spawnBlamParticles() {
		if (!getEntityWorld().isRemote)
			Erebus.NETWORK_WRAPPER.sendToAll(new PacketParticle(ParticleType.TARANTULA_BLAM, (float) posX, (float)posY, (float)posZ));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
        if(hasCustomName())
        	bossInfo.setName(this.getDisplayName());
	}

	@Override
    public void setCustomNameTag(String name) {
        super.setCustomNameTag(name);
        bossInfo.setName(this.getDisplayName());
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
}