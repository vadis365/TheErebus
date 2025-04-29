package erebus.block.entity;

import java.util.List;

import erebus.client.particle.ClientParticles;
import erebus.network.client.AltarAnimatonTimerPacket;
import erebus.registries.ModBlockEntities;
import erebus.registries.ModBlocks;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.network.PacketDistributor;

public class LightningAltarBlockEntity extends AltarAbstractBlockEntity {

	public boolean active;
	public int fuzz;
	private int spawnTicks;
	
	public LightningAltarBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.ALTAR_LIGHTNING.get(), pos, state);
	}

	public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T blockEntity) {
		if (blockEntity instanceof LightningAltarBlockEntity altar) {
			if (!level.isClientSide()) {
				altar.prevAnimationTicks = altar.animationTicks;
				
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
						level.setBlockAndUpdate(pos, ModBlocks.ALTAR_BASE.get().defaultBlockState());
				}
				if (altar.spawnTicks == 0) {
					altar.setActive(false);
					level.playSound(null, pos, ModSounds.ALTAR_CHANGE_STATE.get(), SoundSource.BLOCKS, 1.0F, 1.3F);
				}
				if (altar.prevAnimationTicks != altar.animationTicks)
					PacketDistributor.sendToPlayersNear((ServerLevel) altar.getLevel(), null, altar.getBlockPos().getX(),
							altar.getBlockPos().getY(), altar.getBlockPos().getZ(), 30,
							new AltarAnimatonTimerPacket(altar.getBlockPos().getX(), altar.getBlockPos().getY(),
									altar.getBlockPos().getZ(), altar.animationTicks, altar.prevAnimationTicks));
			}
	
			if (level.isClientSide()) {
			if (altar.animationTicks >= 0 && altar.animationTicks <= 20)
				altar.flameOn(level, pos);
			if (altar.animationTicks == 20)
				if (altar.fuzz < 20) {
					altar.fuzz++;
					if (altar.fuzz >= 20)
						altar.fuzz = 0;
				}
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

	@SuppressWarnings("unlikely-arg-type")
	protected void findEnemyToAttack() {
		List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, new AABB(getBlockPos()).inflate(6D, 2D, 6D));
		if (active)
			for (int i = 0; i < list.size(); i++) {
				Entity entity = list.get(i);
				if (entity != null)
					if (entity instanceof LivingEntity target)
						if (!target.getTags().isEmpty() && target.getTags().contains(EntityTypeTags.ARTHROPOD)); {
							double a = entity.getX();
							double b = entity.getBoundingBox().minY;
							double c = entity.getZ();
							LightningBolt entitybolt = EntityType.LIGHTNING_BOLT.create(level);
							if(entitybolt != null) {
							entitybolt.setPos(a, b, c);
							level.addFreshEntity(entitybolt);
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
