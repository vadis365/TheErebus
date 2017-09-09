package erebus.entity;

import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIFlyingWander;
import erebus.entity.ai.FlyingMoveHelper;
import erebus.entity.ai.PathNavigateFlying;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityBotFly extends EntityMob {
	// AKA ButtFly

	public EntityBotFly(World world) {
		super(world);
		setSize(0.9F, 0.75F);
		moveHelper = new FlyingMoveHelper(this);
		setPathPriority(PathNodeType.WATER, -8F);
		setPathPriority(PathNodeType.BLOCKED, -8.0F);
		setPathPriority(PathNodeType.OPEN, 8.0F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAttackMelee(this, 0.5D, true));
		tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(4, new EntityAILookIdle(this));
		tasks.addTask(5, new EntityAIFlyingWander(this, 0.75D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 15D : 15D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 1D : 1D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
	}

	@Override
    protected PathNavigate createNavigator(World world) {
		return new PathNavigateFlying(this, world);
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.FLY_SOUND;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.FLY_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}

	@Override
	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
	}

	@Override
	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (motionY < 0.0D)
			motionY *= 0.4D;

		if(isInWater())
			getMoveHelper().setMoveTo(this.posX, this.posY + 1, this.posZ, 0.32D);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (getIsInvulnerable())
			return false;
		else return super.attackEntityFrom(source, amount);
	}

	@Override
	public boolean getCanSpawnHere() {
		BlockPos blockpos = new BlockPos(posX, getEntityBoundingBox().minY, posZ);
		if (blockpos.getY() > 100)
			return false;
		else {
			int lightValue = world.getLightFromNeighbors(blockpos);
			return lightValue > rand.nextInt(7) ? false : isNotColliding() && super.getCanSpawnHere();
		}
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount) {
			entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.FLY_WING.ordinal()), 0.0F);
			if (rand.nextInt(5) == 0)
				entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.COMPOUND_EYES.ordinal()), 0.0F);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity)) {
			if (entity instanceof EntityPlayer)
				if (rand.nextInt(2) == 0 && !entity.isBeingRidden()) {
					EntityBotFlyLarva entityBotFlyLarva = new EntityBotFlyLarva(getEntityWorld());
					entityBotFlyLarva.setPosition(entity.posX, entity.posY + 1, entity.posZ);
					entityBotFlyLarva.setParasiteCount((byte) 1);
					entityBotFlyLarva.startRiding(entity, true);
					getEntityWorld().spawnEntity(entityBotFlyLarva);
				} else if (rand.nextInt(2) == 0 && getParasite((EntityPlayer) entity) != null)
					if (((EntityBotFlyLarva) getParasite((EntityPlayer) entity)).getParasiteCount() < 3)
						((EntityBotFlyLarva) getParasite((EntityPlayer) entity)).setParasiteCount((byte) (((EntityBotFlyLarva) getParasite((EntityPlayer) entity)).getParasiteCount() + 1));
			return true;
		}
		return false;
	}

	public Entity getParasite(EntityPlayer player) {
		for (Entity entity : player.getPassengers())
			if (entity instanceof EntityBotFlyLarva)
				return entity;
		return null;
	}
}
