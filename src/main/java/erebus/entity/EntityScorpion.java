package erebus.entity;

import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import erebus.items.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityScorpion extends EntityMob {
	private boolean sting;
	private boolean poisoned;
	public static float stingticks;
	private int cooldown = 0;
	private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityScorpion.class, DataSerializers.BYTE);

	public EntityScorpion(World world) {
		super(world);
		setPathPriority(PathNodeType.WATER, -8.0F);
		stepHeight = 1F;
		isImmuneToFire = true;
		setSize(2.0F, 2.0F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(CLIMBING, Byte.valueOf((byte)0));
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIErebusAttackMelee(this, 0.3D, false));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAIWanderAvoidWater(this, 0.3D));
        tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(5, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 30D : 30D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 3D : 3D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
	}

	@Override
    protected PathNavigate createNavigator(World worldIn) {
        return new PathNavigateClimber(this, worldIn);
    }

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!getEntityWorld().isRemote && !captured()) {
			setisStinging(false);
			if(cooldown > 0 )
				cooldown = 0;
		}
		if (!getEntityWorld().isRemote && captured()) {
			if(cooldown < 10)
				cooldown++;
			if(cooldown >= 10)
				cooldown = 0;
		}
		if (sting && stingticks < 0.64F) {
			stingticks = stingticks + 0.16F;
			if (stingticks >= 0.64F) {
				setHasBeenStung(true);
				setisStinging(false);
			}
		}
		if (!sting && stingticks > 0F && poisoned) {
			stingticks = stingticks - 0.16F;
			if (stingticks <= 0.0F)
				setHasBeenStung(false);
		}

        if (!world.isRemote)
            setBesideClimbableBlock(collidedHorizontally);
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

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	/*
	 * just to avoid crashes
	 * @Override protected String getLivingSound() { return "erebus:scorpionsound"; }
	 * @Override protected String getHurtSound() { return "erebus:scorpionhurt"; }
	 */

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block block) {
		playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		for (int amount = 0; amount < chance; ++amount) {
			if (rand.nextInt(30) == 0)
				entityDropItem(ItemMaterials.EnumErebusMaterialsType.SCORPION_PINCER.createStack(), 0.0F);
			entityDropItem(ItemMaterials.EnumErebusMaterialsType.POISON_GLAND.createStack(1 + rand.nextInt(2)), 0.0F);
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
	public boolean canRiderInteract() {
		return true;
	}

	@Override
	public boolean shouldRiderSit() {
		return false;
	}

	public boolean captured() {
		return isBeingRidden();
	}

	private void setisStinging(boolean state) {
		sting = state;
	}

	private void setHasBeenStung(boolean state) {
		poisoned = state;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		if (player.isSneaking())
			player.setSneaking(false);
		int poisonDuration = 0;
		int slownessDuration = 0;
		if (!getEntityWorld().isRemote && player.getEntityBoundingBox().maxY >= getEntityBoundingBox().minY && player.getEntityBoundingBox().minY <= getEntityBoundingBox().maxY && captured())
			if (getEntityWorld().getDifficulty().ordinal() > 1)
				if (getEntityWorld().getDifficulty() == EnumDifficulty.NORMAL)
					if (ConfigHandler.INSTANCE.scorpion_normalPoison)
						poisonDuration = ConfigHandler.INSTANCE.scorpion_normalPoisonDuration;
					if (ConfigHandler.INSTANCE.scorpion_normalSlowness)
						slownessDuration = ConfigHandler.INSTANCE.scorpion_normalSlownessDuration;
				else if (getEntityWorld().getDifficulty() == EnumDifficulty.HARD)
					if (ConfigHandler.INSTANCE.scorpion_hardPoison)
						poisonDuration = ConfigHandler.INSTANCE.scorpion_hardPoisonDuration;
					if (ConfigHandler.INSTANCE.scorpion_hardSlowness)
						slownessDuration = ConfigHandler.INSTANCE.scorpion_hardSlownessDuration;
		if ((poisonDuration > 0 || slownessDuration > 0) && rand.nextInt(50) == 0) {
			if (poisonDuration > 0) {
				player.addPotionEffect(new PotionEffect(MobEffects.POISON, poisonDuration , 0));
			}
			if (slownessDuration > 0) {
				player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, slownessDuration , 0));
			}
			setisStinging(true);
			getEntityWorld().playSound(null, getPosition(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.HOSTILE, 0.5F, 2F);
		}
		if (ConfigHandler.INSTANCE.scorpion_pickupPlayer && !player.capabilities.isCreativeMode && !getEntityWorld().isRemote && !captured())
			if(getEntitySenses().canSee(player))
				player.startRiding(this, true);
	}

	@Override
	public void updatePassenger(Entity entity) {
		super.updatePassenger(entity);
		if (entity instanceof EntityLivingBase) {
			double a = Math.toRadians(renderYawOffset);
			double offSetX = -Math.sin(a) * 0.75D;
			double offSetZ = Math.cos(a) * 0.75D;
			if (captured())
				entity.setPosition(posX + offSetX, posY + 0.75D + entity.getYOffset(), posZ + offSetZ);
			if (entity.isSneaking())
				entity.setSneaking(false);
		}
	}

}