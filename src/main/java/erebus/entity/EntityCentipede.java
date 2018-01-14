package erebus.entity;

import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityCentipede extends EntityMob {
	private static final DataParameter<Integer> SKIN_TYPE = EntityDataManager.<Integer>createKey(EntityCentipede.class, DataSerializers.VARINT);

	public EntityCentipede(World world) {
		super(world);
		setSize(1.0F, 0.8F);
		setPathPriority(PathNodeType.WATER, -8F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(SKIN_TYPE, new Integer(rand.nextInt(3)));
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIErebusAttackMelee(this, 0.3D, false));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.3D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 25D : 25D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? getAttackStrength() : getAttackStrength() * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness();
		if (light >= 0F)
			return isNotColliding();
		return super.getCanSpawnHere();
	}

	public double getAttackStrength() {
		switch (getEntityWorld().getDifficulty()) {
			default:
				return 2.0D;
			case EASY:
				return 2.0D;
			case NORMAL:
				return 2.0D;
			case HARD:
				return 4.0D;
		}
	}

	@Override
	public int getTotalArmorValue() {
		return 8;
	}

	@Override
    public SoundEvent getAmbientSound() {
		return ModSounds.CENTIPEDE_SOUND;
	}

	@Override
    protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.CENTIPEDE_HURT;
	}

	@Override
    protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH;
	}

	@Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(ModSounds.CENTIPEDE_WALK, 0.5F, 1.0F);
    }

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount) {
			entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.BIO_VELOCITY.ordinal()), 0.0F);
			entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.POISON_GLAND.ordinal()), 0.0F);
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		if (this.world.getGameRules().getBoolean("doMobLoot") && rand.nextInt(50) == 0)
			if (cause.getTrueSource() instanceof EntityLivingBase)
				entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.SUPERNATURAL_VELOCITY.ordinal()), 0.0F);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity)) {
			if (entity instanceof EntityLivingBase) {
				byte duration = 0;

				if (getEntityWorld().getDifficulty().ordinal() > EnumDifficulty.EASY.ordinal())
					if (getEntityWorld().getDifficulty() == EnumDifficulty.NORMAL)
						duration = 7;
					else if (getEntityWorld().getDifficulty() == EnumDifficulty.HARD)
						duration = 15;

				if (duration > 0)
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.POISON, duration * 20, 0));
			}

			return true;
		} else
			return false;
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
