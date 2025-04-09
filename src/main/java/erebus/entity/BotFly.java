package erebus.entity;

import javax.annotation.Nullable;

import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;

public class BotFly extends Monster {
	// AKA ButtFly

	public BotFly(EntityType<? extends BotFly> type, Level level) {
		super(type, level);
		this.moveControl = new FlyingMoveControl(this, 10, true);
		setPathfindingMalus(PathType.WATER, -8F);
		setPathfindingMalus(PathType.BLOCKED, -8.0F);
		setPathfindingMalus(PathType.OPEN, 8.0F);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.5D, false));
		goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
		goalSelector.addGoal(3,  new RandomLookAroundGoal(this));
		goalSelector.addGoal(4, new AIFlyingWander(this, 0.5D, 0.01F));
		targetSelector.addGoal(3, new HurtByTargetGoal(this).setAlertOthers(BotFly.class));
		targetSelector.addGoal(0, new NearestAttackableTargetGoal<Player>(this, Player.class, true, false));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 15D)
				.add(Attributes.FOLLOW_RANGE, 16D)
				.add(Attributes.MOVEMENT_SPEED, 0.3D)
				.add(Attributes.ATTACK_DAMAGE, 1D);
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, LevelReader level) {
		return level.getBlockState(pos).isAir() ? 10.0F : 0.0F;
	}

	@Override
    protected PathNavigation createNavigation(Level level){
		return new FlyingPathNavigation(this, level);
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.FLY_SOUND.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.FLY_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH.get();
	}

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isIgnoringBlockTriggers() {
        return true;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
    }

	@Override
	public void tick() {
		super.tick();
		Vec3 vec3 = this.getDeltaMovement();
		if (!this.onGround() && vec3.y < 0.0D)
			this.setDeltaMovement(vec3.multiply(1.0D, 0.4D, 1.0D));

		if(isInWater())
			getNavigation().moveTo(getX(), getY() + 1D, getZ(), 0.32D);

		super.tick();
	}

	public static boolean canSpawnHere(EntityType<Moth> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
		if (pos.getY() > 100)
			return false;
		else {
			int light = level.getMaxLocalRawBrightness(pos);
			return light > random.nextInt(7) ? false : checkMobSpawnRules(entity, level, spawn, pos, random);
		}
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader world) {
		return !world.containsAnyLiquid(getBoundingBox()) && world.noCollision(this);
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 2;
	}
/*
	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = random.nextInt(4) + random.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount) {
			entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.FLY_WING.ordinal()), 0.0F);
			if (random.nextInt(5) == 0)
				entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.COMPOUND_EYES.ordinal()), 0.0F);
		}
	}
*/
@Override
public boolean doHurtTarget(Entity entity) {
	if (hasLineOfSight(entity)) {
		if (super.doHurtTarget(entity)) {
			if (entity instanceof Player) {
			//	if (random.nextInt(20) == 0 && !entity.hasPassenger(BotFlyLava)) {
					// BotFlyLarva entityBotFlyLarva = new BotFlyLarva(getEntityWorld());
					// entityBotFlyLarva.setPosition(entity.posX, entity.posY + 1, entity.posZ);
					// entityBotFlyLarva.setParasiteCount((byte) 1);
					// entityBotFlyLarva.startRiding(entity, true);
					// getEntityWorld().spawnEntity(entityBotFlyLarva);
			//	} // else if (random.nextInt(20) == 0 && getParasite((Player) entity) != null)
					// if (((BotFlyLarva) getParasite((Player) entity)).getParasiteCount() < 3)
					// ((BotFlyLarva) getParasite((Player) entity)).setParasiteCount((byte)
					// (((BotFlyLarva) getParasite((Player) entity)).getParasiteCount() + 1));
			}
		}
		return true;
	}
	return false;
}
/* TODO Add back the larva
	public Entity getParasite(Player player) {
		for (Entity entity : player.getPassengers())
			if (entity instanceof BotFlyLarva)
				return entity;
		return null;
	}
	*/

	class AIFlyingWander extends WaterAvoidingRandomStrollGoal {
		public AIFlyingWander(BotFly creatureIn, double speedIn, float chance) {
			super(creatureIn, speedIn, chance);
		}
	
		@Nullable
		protected Vec3 getPosition() {
			Vec3 vec3 = this.mob.getViewVector(0.0F);
			Vec3 vec31 = HoverRandomPos.getPos(this.mob, 8, 7, vec3.x, vec3.z, ((float) Math.PI / 2F), 2, 1);
			return vec31 != null ? vec31 : AirAndWaterRandomPos.getPos(this.mob, 8, 4, -2, vec3.x, vec3.z, (double) ((float) Math.PI / 2F));
		}
	}
}
