package erebus.entity;

import erebus.core.handler.configs.ConfigHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityBogMaw extends EntityMob {

	private static final DataParameter<Float> ROTATION = EntityDataManager.<Float>createKey(EntityBogMaw.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> JAW_ANGLE = EntityDataManager.<Float>createKey(EntityBogMaw.class, DataSerializers.FLOAT);

	public EntityBogMaw(World world) {
		super(world);
		stepHeight = 0.0F;
		setSize(1.0F, 0.8F);
		tasks.addTask(0, new EntityAILeapAtTarget(this, 0.5F));
		tasks.addTask(1, new EntityAIAttackMelee(this, 0, true));
		targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(ROTATION, 0F);
		dataManager.register(JAW_ANGLE, 0F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 25D : 25D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 1D : 1D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
	}

	@Override
	public boolean attackEntityAsMob(Entity player) {
		player.attackEntityFrom(DamageSource.CACTUS, 4);
		((EntityLivingBase) player).addPotionEffect(new PotionEffect(MobEffects.POISON, 50, 0));
		((EntityLivingBase) player).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 50, 0));
		return true;
	}

	@Override
	public void onUpdate() {
		rotationYaw = prevRotationYaw = 0;
		if (!getEntityWorld().isRemote && getAttackTarget() != null) {
			double distance = getDistance(getAttackTarget().posX, getAttackTarget().getEntityBoundingBox().minY, getAttackTarget().posZ);
			float rot = getAttackTarget().rotationYaw;
			setRotation(rot);
			if (distance <= 4.0D && getJawAngle() < 1.0F)
				setJawAngle(getJawAngle() + 0.1F);
			if (distance > 4.0D && getJawAngle() > 0F)
				setJawAngle(getJawAngle() - 0.1F);
		}
		super.onUpdate();
	}

	@Override
	public boolean getCanSpawnHere() {
		return getEntityWorld().getDifficulty() != EnumDifficulty.PEACEFUL && getEntityWorld().checkNoEntityCollision(getEntityBoundingBox()) && getEntityWorld().getCollisionBoxes(this, getEntityBoundingBox()).size() == 0 && !getEntityWorld().containsAnyLiquid(getEntityBoundingBox());
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 6;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(3) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			entityDropItem(erebus.items.ItemMaterials.EnumErebusMaterialsType.BOGMAW_ROOT.createStack(), 0.0F);
	}

	public void setRotation(float rotation) {
		dataManager.set(ROTATION, rotation);
	}

	public float getRotation() {
		return dataManager.get(ROTATION);
	}

	public void setJawAngle(float angle) {
		dataManager.set(JAW_ANGLE, angle);
	}

	public float getJawAngle() {
		return dataManager.get(JAW_ANGLE);
	}
}
