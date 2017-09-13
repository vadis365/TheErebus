package erebus.entity;

import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import erebus.items.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntitySolifuge extends EntityMob {

	private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntitySolifuge.class, DataSerializers.BYTE);

	public EntitySolifuge(World world) {
		super(world);
		setSize(2.0F, 1.0F);
		experienceValue = 10;
		isImmuneToFire = true;
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
		tasks.addTask(3, new EntityAIWander(this, 0.3D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.5D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 30D : 30D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 4D : 4D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
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
	public void onUpdate() {
		super.onUpdate();
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
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			entityDropItem(ItemMaterials.EnumErebusMaterialsType.BIO_VELOCITY.createStack(), 0.0F);
		if(rand.nextInt(50)==0)
			entityDropItem(ItemMaterials.EnumErebusMaterialsType.SUPERNATURAL_VELOCITY.createStack(), 0.0F);
	}

	@Override
	public void setDead() {
		super.setDead();

		if (!getEntityWorld().isRemote && getHealth() <= 0.0F)
			for (int i = 0; i < 4; i++) {
				EntitySolifugeSmall entitySolifugeSmall = new EntitySolifugeSmall(getEntityWorld());
				entitySolifugeSmall.setPosition(posX + (rand.nextFloat() * 0.3D - rand.nextFloat() * 0.3D), posY + 1, posZ + (rand.nextFloat() * 0.3D - rand.nextFloat() * 0.3D));
				entitySolifugeSmall.setPotionEffect(Byte.valueOf((byte) rand.nextInt(9)));
				getEntityWorld().spawnEntity(entitySolifugeSmall);
			}
	}
}