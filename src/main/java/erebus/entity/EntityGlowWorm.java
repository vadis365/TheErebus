package erebus.entity;

import java.util.List;

import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.items.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGlowWorm extends EntityCreature {
	public int lastX;
	public int lastY;
	public int lastZ;
	private boolean triggerOnce;
	private static final DataParameter<Byte> TRIGGRED = EntityDataManager.<Byte>createKey(EntityGlowWorm.class, DataSerializers.BYTE);

	public EntityGlowWorm(World world) {
		super(world);
		setSize(1.5F, 0.5F);
		stepHeight = 0.0F;
		isImmuneToFire = true;
		setPathPriority(PathNodeType.WATER, -8F);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAIWanderAvoidWater(this, 0.5D));
		tasks.addTask(4, new EntityAIPanic(this, 0.7F));
		tasks.addTask(5, new EntityAILookIdle(this));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(TRIGGRED, new Byte((byte) 0));
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
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 15D : 15D * ConfigHandler.INSTANCE.mobHealthMultipier);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.GLOW_WORM_SOUND;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.GLOW_WORM_HURT;
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
			entityDropItem(ItemMaterials.EnumErebusMaterialsType.BIO_LUMINESCENCE.createStack(), 0.0F);
	}

	@Override
	public void onUpdate() {
		if (!getEntityWorld().isRemote)
			findNearEntity();
		if (getEntityWorld().isRemote && isGlowing())
			lightUp(getEntityWorld(), getPosition());
		if (getEntityWorld().isRemote && !isGlowing())
			switchOff();
		super.onUpdate();
	}

	@SideOnly(Side.CLIENT)
	private void lightUp(World world, BlockPos pos) {
		if (!ConfigHandler.INSTANCE.bioluminescence)
			return;
		world.setLightFor(EnumSkyBlock.BLOCK, pos, 9);
		for (int i = -2; i < 2; i++)
			for (int j = -2; j < 2; j++)
				for (int k = -2; k < 2; k++)
					if (pos.getX() + i != lastX || pos.getY() + j != lastY || pos.getZ() + k != lastZ || isDead) {
						world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(lastX + i, lastY + j, lastZ + k));
						lastX = pos.getX();
						lastY = pos.getY();
						lastZ = pos.getZ();
					}
		triggerOnce = true;
	}

	@SideOnly(Side.CLIENT)
	private void switchOff() {
		if (!ConfigHandler.INSTANCE.bioluminescence)
			return;
		if(triggerOnce) {
			getEntityWorld().checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(lastX, lastY, lastZ));
			getEntityWorld().checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY), MathHelper.floor(posZ)));
			triggerOnce = false;
		}
	}

	@SuppressWarnings("unchecked")
	protected Entity findNearEntity() {
		List<EntityLivingBase> list = getEntityWorld().getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(6D, 6D, 6D));
		for (int i = 0; i < list.size(); i++) {
			Entity entity = list.get(i);
			if (entity != null && !getIsNearEntity())
				setIsNearEntity(true);
		}
		if (list.isEmpty() && getIsNearEntity())
			setIsNearEntity(false);
		return null;
	}

	public boolean isGlowing() {
		return getEntityWorld().getSunBrightness(1.0F) < 0.5F && getIsNearEntity();
	}

	public void setIsNearEntity(boolean entityNear) {
		dataManager.set(TRIGGRED, entityNear ? (byte) 1 : (byte) 0);
	}

	public boolean getIsNearEntity() {
		return dataManager.get(TRIGGRED) == 1 ? true : false;
	}

	@Override
	public void setDead() {
		super.setDead();
		if (getEntityWorld().isRemote)
			switchOff();
	}
}
