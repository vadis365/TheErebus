package erebus.block.entity;

import java.util.List;

import erebus.block.altars.AltarAbstract;
import erebus.client.particle.ClientParticleTypes;
import erebus.client.particle.ClientParticles;
import erebus.network.client.AltarAnimatonTimerPacket;
import erebus.network.client.AntlionParticlePacket;
import erebus.network.client.ParticlePacket;
import erebus.registries.ModBlockEntities;
import erebus.registries.ModBlocks;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.network.PacketDistributor;
import net.minecraft.world.phys.Vec3;

public class LightningAltarBlockEntity extends AltarAbstractBlockEntity {

	public boolean active;
	public int fuzz;
	private int spawnTicks;
	
	public LightningAltarBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.ALTAR_LIGHTNING.get(), pos, state);
	}

	public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T blockEntity) {
		if (blockEntity instanceof LightningAltarBlockEntity altar) {
			altar.prevAnimationTicks = altar.animationTicks;
			if (!level.isClientSide()) {
				altar.spawnTicks--;
				if (altar.active) {
					altar.findEnemyToAttack();
					if (altar.animationTicks < 20)
						altar.animationTicks++;
				}
				if (!altar.active) {
					if (altar.animationTicks > 0)
						altar.animationTicks--;
					if (altar.animationTicks == 1)
						level.setBlockAndUpdate(pos, ModBlocks.ALTAR_BASE.get().defaultBlockState().setValue(AltarAbstract.FACING, altar.getBlockState().getValue(AltarAbstract.FACING)));
				}
				if (altar.spawnTicks == 0) {
					altar.setActive(false);
					level.playSound(null, pos, ModSounds.ALTAR_CHANGE_STATE.get(), SoundSource.BLOCKS, 1.0F, 1.3F);
				}
				if (altar.prevAnimationTicks != altar.animationTicks)
					PacketDistributor.sendToPlayersNear((ServerLevel) altar.getLevel(), null, altar.getBlockPos().getX(),
							altar.getBlockPos().getY(), altar.getBlockPos().getZ(), 30,
							new AltarAnimatonTimerPacket(altar.getBlockPos().getX(), altar.getBlockPos().getY(),
									altar.getBlockPos().getZ(), altar.animationTicks));
			}
	
			if (level.isClientSide()) {
				if(altar.animationTicks == 20) {
				float vx = (level.random.nextFloat() * 0.5f - 0.25f);
				float vy = (level.random.nextFloat() * 0.5f - 0.25f);
				float vz = (level.random.nextFloat() * 0.5f - 0.25f);
				level.addParticle(ParticleTypes.ELECTRIC_SPARK, false, pos.getX() + 0.5D, pos.getY() +1.6D, pos.getZ() + 0.5D, vx, vy, vz);
				}
				if (altar.animationTicks == 6)
					altar.flameOn(level, pos);
				//if (altar.animationTicks == 20)
				//	if (altar.fuzz < 20) {
				//		altar.fuzz++;
				//		if (altar.fuzz >= 20)
					//		altar.fuzz = 0;
				//	}
			}
		}
	}

	public void flameOn(Level level, BlockPos pos) {
		if (level.isClientSide()) {
			double x = pos.getX() + 0.53125F;
			double y = pos.getY() + 1.25F;
			double z = pos.getZ() + 0.53125F;
			ClientParticles.spawnCustomParticle("smoke", x, y, z, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("flame", x, y, z, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("smoke", x, y, z - 0.265625, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("flame", x, y, z - 0.265625, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("smoke", x, y, z + 0.265625, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("flame", x, y, z + 0.265625, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("smoke", x - 0.265625, y, z, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("flame", x - 0.265625, y, z, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("smoke", x + 0.265625, y, z, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("flame", x + 0.265625, y, z, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("smoke", x, y + 0.25, z, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("flame", x, y + 0.25, z, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("smoke", x, y + 0.5, z, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("flame", x, y + 0.5, z, 0.0D, 0.0D, 0.0D);
		}
	}

	public void setActive(boolean isActive) {
		active = isActive;
	}

	public void setSpawnTicks(int i) {
		spawnTicks = i;
	}

	protected void findEnemyToAttack() {
		List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, new AABB(getBlockPos()).inflate(6D, 2D, 6D));
		if (active)
			for (int i = 0; i < list.size(); i++) {
				Entity entity = list.get(i);
				if (entity != null)
					if (entity instanceof LivingEntity target)
						if (target.getType().is(EntityTypeTags.ARTHROPOD) && entity.invulnerableTime == 0) {
							double a = entity.getX();
							double b = entity.getY() + entity.getBbHeight() * 0.5F;
							double c = entity.getZ();

							target.hurt(target.damageSources().lightningBolt(), 0.5F); // just a test amount

							Vec3 targetVector = new Vec3(a - getBlockPos().getX() - 0.5D, b - getBlockPos().getY() - 1.6D, c - getBlockPos().getZ() - 0.5D);
							for (int particles = 0; particles < 40; particles++) {
								float offsetLen = level.random.nextFloat();
								Vec3 offset = new Vec3(
										targetVector.x * offsetLen + level.random.nextFloat() * 0.2f - 0.1f,
										targetVector.y * offsetLen + level.random.nextFloat() * 0.2f - 0.1f,
										targetVector.z * offsetLen + level.random.nextFloat() * 0.2f - 0.1f);
								PacketDistributor.sendToPlayersNear((ServerLevel) level, null, getBlockPos().getX(),
										getBlockPos().getY() + 1D, getBlockPos().getZ(), 30,
										new ParticlePacket((byte) ClientParticleTypes.ParticleType.ELECTRIC.ordinal(),
												getBlockPos().getX() + 0.5D + offset.x,
												getBlockPos().getY() + 1.6D + offset.y,
												getBlockPos().getZ() + 0.5D + offset.z));
							}

						}
			}
	}

	@Override
	protected void writeTileToNBT(CompoundTag nbt) {
		nbt.putInt("animationTicks", animationTicks);
		nbt.putInt("spawnTicks", spawnTicks);
		nbt.putBoolean("active", active);
	}

	@Override
	protected void readTileFromNBT(CompoundTag nbt) {
		animationTicks = nbt.getInt("animationTicks");
		spawnTicks = nbt.getInt("spawnTicks");
		active = nbt.getBoolean("active");
	}

}
