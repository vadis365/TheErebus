package erebus.entity.projectile;

import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jspecify.annotations.NonNull;

public class GooBall extends ThrowableProjectile implements ItemSupplier {

	public GooBall(EntityType<GooBall> type, Level level) {
		super(type, level);
	}

	public GooBall(Level level, Entity owner, float damageCaused) {
		super(ModEntities.GOO_BALL.get(), level);
		this.setOwner(owner);
		setXRot(owner.getXRot());
		setYRot(owner.getYRot());
	}

	public GooBall(double x, double y, double z, Level level) {
		super(ModEntities.GOO_BALL.get(), x, y, z, level);
	}

	@Override
	public void tick() {
		super.tick();
		if (level().isClientSide())
			trailParticles(level(), getX() - 0.5D, getY(), getZ() - 0.5D, random);
	}

	protected SoundEvent getJumpedOnSound() {
		return ModSounds.BEETLE_LARVA_SPLAT.get();
	}

	@Override
	protected void onHit(HitResult result) {
		HitResult.Type typeOfHit = result.getType();
		if (typeOfHit == HitResult.Type.ENTITY) {
			EntityHitResult entityhitresult = (EntityHitResult) result;
			Entity entity = entityhitresult.getEntity();
			if (entity instanceof Player) {
				if (!level().isClientSide()) {
					((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 5 * 20, 3));
					kill((ServerLevel) level());
				}
			}
		}

		if (typeOfHit == HitResult.Type.BLOCK)
			kill((ServerLevel) level());

		if (level().isClientSide())
			level().levelEvent(null, 2001, blockPosition(), Block.getId(Blocks.SLIME_BLOCK.defaultBlockState()));

		level().playSound(null, blockPosition(), getJumpedOnSound(), SoundSource.HOSTILE, 1.0F, 1.0F);
	}

	@Override
	public boolean canBeCollidedWith(Entity other) {
		return false;
	}

	public void trailParticles(Level level, double x, double y, double z, RandomSource rand) {
		for (int count = 0; count < 20; ++count) {
			double velX;
			double velY;
			double velZ;
			int motionX = rand.nextInt(2) * 2 - 1;
			int motionZ = rand.nextInt(2) * 2 - 1;
			velY = (rand.nextFloat() - 0.5D) * 0.125D;
			velZ = rand.nextFloat() * 1.0F * motionZ;
			velX = rand.nextFloat() * 1.0F * motionX;
			level.addParticle(ParticleTypes.ITEM_SLIME, x + 0.5D, y, z + 0.5D, velX, velY, velZ);
		}
	}

	@Override
	public @NonNull ItemStack getItem() {
		return new ItemStack(Items.SLIME_BALL);
	}

	@Override
	protected void defineSynchedData(@NonNull Builder builder) {
	}
}
