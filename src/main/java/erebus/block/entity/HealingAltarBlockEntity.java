package erebus.block.entity;

import java.util.List;

import erebus.block.altars.AltarAbstract;
import erebus.client.particle.ClientParticles;
import erebus.network.client.AltarAnimatonTimerPacket;
import erebus.registries.ModBlockEntities;
import erebus.registries.ModBlocks;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.network.PacketDistributor;

public class HealingAltarBlockEntity extends AltarAbstractBlockEntity {

	public HealingAltarBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.ALTAR_HEALING.get(), pos, state);
	}

	public boolean active;
	private int spawnTicks;

	public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T blockEntity) {
		if (blockEntity instanceof HealingAltarBlockEntity altar) {
			if (!level.isClientSide()) {
				altar.prevAnimationTicks = altar.animationTicks;
				
				altar.spawnTicks--;
				if (altar.active) {
					altar.findPlayerToHeal();
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
									altar.getBlockPos().getZ(), altar.animationTicks, altar.prevAnimationTicks));
			}
	
			if (level.isClientSide()) {
				if (altar.animationTicks == 6)
					altar.bigLove(level, pos);
			}
		}
	}

	public void bigLove(Level level, BlockPos pos) {
		if (level.isClientSide()) {
			double x = pos.getX() + 0.53125F;
			double y = pos.getY() + 1.25F;
			double z = pos.getZ() + 0.53125F;
			ClientParticles.spawnCustomParticle("heart", x, y, z, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("heart", x, y, z - 0.265625, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("heart", x, y, z + 0.265625, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("heart", x - 0.265625, y, z, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("heart", x + 0.265625, y, z, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("heart", x, y + 0.25, z, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("heart", x, y + 0.5, z, 0.0D, 0.0D, 0.0D);
		}
	}

	public void setActive(boolean isActive) {
		active = isActive;
	}

	public void setSpawnTicks(int i) {
		spawnTicks = i;
	}

	public void findPlayerToHeal() {
		List<Player> list = level.getEntitiesOfClass(Player.class, new AABB(getBlockPos()).inflate(4D, 2D, 4D));
		if (active)
			for (int i = 0; i < list.size(); i++) {
				Entity entity = list.get(i);
				if (!(entity instanceof FakePlayer))
					((Player) entity).addEffect(new MobEffectInstance(MobEffects.HEAL, 1 * 20, 0));
			}
	}

	@Override
	protected void writeTileToNBT(CompoundTag nbt) {
		nbt.putInt("animationTicks", animationTicks);
		nbt.putInt("prevAnimationTicks", prevAnimationTicks);
		nbt.putInt("spawnTicks", spawnTicks);
		nbt.putBoolean("active", active);
	}

	@Override
	protected void readTileFromNBT(CompoundTag nbt) {
		animationTicks = nbt.getInt("animationTicks");
		prevAnimationTicks = nbt.getInt("prevAnimationTicks");
		spawnTicks = nbt.getInt("spawnTicks");
		active = nbt.getBoolean("active");
	}

}
