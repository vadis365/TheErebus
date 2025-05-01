package erebus.block.entity;

import java.util.List;

import org.joml.Vector3f;

import erebus.block.altars.AltarAbstract;
import erebus.client.particle.ClientParticles;
import erebus.network.client.AltarAnimatonTimerPacket;
import erebus.network.client.LightningAltarRenderPacket;
import erebus.registries.ModBlockEntities;
import erebus.registries.ModBlocks;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.network.PacketDistributor;

public class LightningAltarBlockEntity extends AltarAbstractBlockEntity {

	public boolean active;
	private int spawnTicks;
	public Vector3f targetVector;
	
	public LightningAltarBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.ALTAR_LIGHTNING.get(), pos, state);
	}

	public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T blockEntity) {
		if (blockEntity instanceof LightningAltarBlockEntity altar) {
			altar.prevAnimationTicks = altar.animationTicks;
			if (!level.isClientSide()) {
				altar.spawnTicks--;
				if (altar.active) {
					if (altar.animationTicks == 20)
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
				if (altar.animationTicks == 20 && level.getGameTime()%2 == 0) {
					float vx = (level.random.nextFloat() * 0.5f - 0.25f);
					float vy = (level.random.nextFloat() * 0.5f - 0.25f);
					float vz = (level.random.nextFloat() * 0.5f - 0.25f);
					level.addParticle(ParticleTypes.ELECTRIC_SPARK, false, pos.getX() + 0.5D, pos.getY() + 1.6D, pos.getZ() + 0.5D, vx, vy, vz);
				}

				if (altar.animationTicks == 6)
					altar.flameOn(level, pos);
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
							float a = (float) entity.getX();
							float b = (float) (entity.getY() + entity.getBbHeight() * 0.5F);
							float c = (float) entity.getZ();

							targetVector = new Vector3f().add(a - getBlockPos().getX() - 0.5F, b - getBlockPos().getY() - 1.6F, c - getBlockPos().getZ() - 0.5F);
							
							PacketDistributor.sendToPlayersNear((ServerLevel) level, null, getBlockPos().getX(),
									getBlockPos().getY() + 1D, getBlockPos().getZ(), 30,
									new LightningAltarRenderPacket(
											getBlockPos().getX(),
											getBlockPos().getY(),
											getBlockPos().getZ(),
											targetVector));

							target.hurt(target.damageSources().lightningBolt(), 1.0F); // just a test amount

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
