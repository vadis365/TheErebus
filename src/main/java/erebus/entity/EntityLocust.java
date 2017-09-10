package erebus.entity;

import javax.annotation.Nullable;

import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import erebus.entity.ai.FlyingMoveHelper;
import erebus.entity.ai.PathNavigateFlying;
import erebus.items.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityLocust extends EntityMob {
	public boolean canJump = true;

	public EntityLocust(World world) {
		super(world);
		setSize(2F, 1F);
		moveHelper = new FlyingMoveHelper(this);
		stepHeight = 1.0F;
		jumpMovementFactor = 0.05F;
		setPathPriority(PathNodeType.WATER, -8F);
		setPathPriority(PathNodeType.BLOCKED, -8.0F);
		setPathPriority(PathNodeType.OPEN, 8.0F);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIErebusAttackMelee(this, 0.5D, true));
		tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(4, new EntityAILookIdle(this));
		tasks.addTask(4, new EntityLocust.AIRandomJumpWhenIdle(this));
		tasks.addTask(5, new EntityLocust.AIFlyingWander(this, 0.75D, 10));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 100D : 100D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 4D : 4D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
	}

	@Override
	protected PathNavigate createNavigator(World world) {
		return new PathNavigateFlying(this, world);
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
	protected SoundEvent getAmbientSound() {
		return ModSounds.LOCUST_SOUND;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.LOCUST_HURT;
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
			entityDropItem(new ItemStack(ModItems.MATERIALS, amount, ItemMaterials.EnumErebusMaterialsType.ELASTIC_FIBRE.ordinal()), 0.0F);
	}

	public boolean randJump() {
		return rand.nextInt(50) == 0;
	}

	@Override
	protected void jump() {
		motionY = 0.75D;
		setCanJump(false);
	}

	public void setCanJump(boolean ableToJump) {
		canJump = ableToJump;
	}

	@Override
	public void onLivingUpdate() {
		if (getMoveHelper().isUpdating() && motionY < 0.0D)
			motionY *= 0.2D;
		else
			motionY *= 0.75D;

		if (isInWater())
			getMoveHelper().setMoveTo(this.posX, this.posY + 1, this.posZ, 0.32D);
		super.onLivingUpdate();
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (canEntityBeSeen(entity)) {
			if (super.attackEntityAsMob(entity)) {
				if (entity instanceof EntityLivingBase) {
					byte duration = 0;

					if (getEntityWorld().getDifficulty().ordinal() > EnumDifficulty.EASY.ordinal())
						if (getEntityWorld().getDifficulty() == EnumDifficulty.NORMAL)
							duration = 8;
						else if (getEntityWorld().getDifficulty() == EnumDifficulty.HARD)
							duration = 15;

					if (duration > 0)
						((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.HUNGER, duration * 20, 0));
				}
			}
			return true;
		} else
			return false;
	}

	public class AIFlyingWander extends EntityAIWander {

		private final EntityLocust locust;

		public AIFlyingWander(EntityLocust locustIn, double speedIn, int chance) {
			super(locustIn, speedIn, chance);
			setMutexBits(1);
			this.locust = locustIn;
		}

		@Nullable
		@Override
		protected Vec3d getPosition() {
			return RandomPositionGenerator.findRandomTarget(locust, 32, 32);
		}
	}

	public class AIRandomJumpWhenIdle extends EntityAIBase {

		private final EntityLocust locust;
		private int idleTime;

		public AIRandomJumpWhenIdle(EntityLocust locustIn) {
			setMutexBits(3);
			this.locust = locustIn;
		}

		@Override
		public boolean shouldExecute() {
			return locust.getRNG().nextFloat() < 0.02F && locust.onGround && locust.canJump;
		}

		@Override
		public boolean shouldContinueExecuting() {
			return idleTime >= 0;
		}

		@Override
		public void startExecuting() {
			locust.jump();
			idleTime = 20 + locust.getRNG().nextInt(20);
		}

		@Override
		public void updateTask() {
			if (locust.onGround && !locust.canJump)
				locust.setCanJump(true);
			--this.idleTime;
		}
	}
}