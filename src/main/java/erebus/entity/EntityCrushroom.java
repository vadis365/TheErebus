package erebus.entity;

import erebus.Erebus;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.items.ItemMaterials;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityCrushroom extends EntityMob implements IRangedAttackMob {
	
	private static final DataParameter<Integer> SMASH_COUNT = EntityDataManager.<Integer>createKey(EntityCrushroom.class, DataSerializers.VARINT);
	private static final DataParameter<Byte> STANDING = EntityDataManager.<Byte>createKey(EntityCrushroom.class, DataSerializers.BYTE);


	private final EntityAIAttackRanged aiArrowAttack = new EntityAIAttackRanged(this, 0.75D, 40, 12.0F);

	public EntityCrushroom(World world) {
		super(world);
		setSize(3.3F, 4F);
		tasks.addTask(0, new EntityAIWander(this, 0.5D));
		tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(2, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 200D : 200D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 6D : 6D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(SMASH_COUNT, 0);
		dataManager.register(STANDING,(byte) 0);
	}

	@Override
	public void onLivingUpdate() {
		if (getEntityWorld().isRemote)
			Erebus.PROXY.spawnCustomParticle("spores", getEntityWorld(), posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height - 0.25D, posZ + (rand.nextDouble() - 0.5D) * width, 1.0D + rand.nextDouble(), 1.0D + rand.nextDouble(), 1.0D + rand.nextDouble());

		if (!getEntityWorld().isRemote && getAttackTarget() != null) {
			faceEntity(getAttackTarget(), 10.0F, 20.0F);
			double distance = getDistance(getAttackTarget().posX, getAttackTarget().getEntityBoundingBox().minY, getAttackTarget().posZ);

			if (distance > 5.0D && distance <= 12) {
				tasks.addTask(3, aiArrowAttack);
				if (getSmashCount() >= 1) {
					setSmashCount(getSmashCount() - 1);
					setStanding((byte) 3);
					if (getSmashCount() == 0)
						setStanding((byte) 0);
				}
			}

			if (distance <= 5.0D) {
				tasks.removeTask(aiArrowAttack);
				if (getSmashCount() < 20 && getStanding() != 3) {
					setSmashCount(getSmashCount() + 1);
					setStanding((byte) 2);
				}

				if (getSmashCount() >= 20 && getStanding() == 2) {
					setStanding((byte) 3);
					meleeAttackPlayer();
				}

				if (getSmashCount() >= 1 && getStanding() == 3) {
					setSmashCount(getSmashCount() - 1);
					if (getSmashCount() == 0)
						setStanding((byte) 2);
				}
			}
		}

		if (!getEntityWorld().isRemote && getAttackTarget() == null) {
			setStanding((byte) 1);
			tasks.removeTask(aiArrowAttack);
		}

		super.onLivingUpdate();
	}

	private void setStanding(byte state) {
		dataManager.set(STANDING, state);
	}

	public byte getStanding() {
		return dataManager.get(STANDING);
	}

	private void setSmashCount(int count) {
		dataManager.set(SMASH_COUNT, count);
	}

	public int getSmashCount() {
		return dataManager.get(SMASH_COUNT);
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
	protected SoundEvent getAmbientSound() {
		return ModSounds.CRUSHLING_LIVING;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damagesource) {
		return ModSounds.CRUSHLING_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.CRUSHLING_DEATH;
	}

	@Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        playSound(SoundEvents.ENTITY_IRONGOLEM_STEP, 0.15F, 1.0F);
	}

	@Override
	protected float getSoundPitch() {
		return 0.1F;
	}

	@Override
	protected void dropFewItems(boolean recentHit, int fortune) {
		int amount = rand.nextInt(3);
		if (fortune > 0)
			amount += rand.nextInt(fortune + 1);
		entityDropItem(ItemMaterials.EnumErebusMaterialsType.HIDE_SHROOM.createStack(amount), 0.0F);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean canAttackClass(Class entity) {
		return EntityCrushroom.class != entity && EntityFungalWeevil.class != entity && EntityZombieAnt.class != entity && EntityZombieAntSoldier.class != entity && EntityPunchroom.class != entity;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entity, float range) {
		EntitySporeBall sporeball = new EntitySporeBall(getEntityWorld(), this);
		double distanceX = entity.posX - posX;
		double distanceY = entity.posY + height - 4D - sporeball.posY;
		double distanceZ = entity.posZ - posZ;
		float height = MathHelper.sqrt(distanceX * distanceX + distanceZ * distanceZ) * 0.2F;
		sporeball.shoot(distanceX, distanceY + height, distanceZ, 0.8F, 0.0F);
		getEntityWorld().playSound(null, getPosition(), ModSounds.SPRAY_CAN_SOUND, SoundCategory.HOSTILE, 0.5F, 0.1F / (getRNG().nextFloat() * 0.4F + 0.8F));
		getEntityWorld().spawnEntity(sporeball);
	}

	private void meleeAttackPlayer() {
		if (!getEntityWorld().isRemote && getAttackTarget().getEntityBoundingBox().maxY >= getEntityBoundingBox().minY - 1.0D && getAttackTarget().getEntityBoundingBox().minY <= getEntityBoundingBox().maxY && getSmashCount() == 20) {
			getEntityWorld().playSound(null, getPosition(), ModSounds.BLAM_SOUND, SoundCategory.HOSTILE, 0.5F, 1.0F);
			spawnBlamParticles();
			getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this), (float) (ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 6D : 6D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier));
			getAttackTarget().addVelocity(-MathHelper.sin(rotationYaw * 3.141593F / 180.0F) * 0.5D, 0.2D, MathHelper.cos(rotationYaw * 3.141593F / 180.0F) * 0.5D);
		}
	}

	public void spawnBlamParticles() {
		Erebus.NETWORK_WRAPPER.sendToAll(new PacketParticle(ParticleType.CRUSHROOM_BLAM, (float) posX, (float)posY, (float)posZ));
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {
	}
}