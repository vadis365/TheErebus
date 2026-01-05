package erebus.block.entity;

import erebus.block.altars.AltarAbstract;
import erebus.client.particle.ClientParticles;
import erebus.network.client.AltarAnimatonTimerPacket;
import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.PacketDistributor;

public class ExperienceAltarBlockEntity extends AltarAbstractBlockEntity {
	public boolean active;
	private int spawnTicks;
	private int uses;

	public ExperienceAltarBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.ALTAR_EXPERIENCE.get(), pos, state);
	}

	public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T blockEntity) {
		if (blockEntity instanceof ExperienceAltarBlockEntity altar) {
			altar.prevAnimationTicks = altar.animationTicks;
			if (!level.isClientSide()) {
			if (altar.active) {
				if (altar.animationTicks <= 20)
					altar.animationTicks++;
				if (altar.spawnTicks == 0)
					altar.setActive(false);
				altar.spawnTicks--;
			}
			if (!altar.active) {
				if (altar.animationTicks > 0)
					altar.animationTicks--;
				if (altar.animationTicks == 1)
					level.setBlockAndUpdate(pos, ModBlocks.ALTAR_BASE.get().defaultBlockState().setValue(AltarAbstract.FACING, altar.getBlockState().getValue(AltarAbstract.FACING)));
			}
			if (altar.prevAnimationTicks != altar.animationTicks)
				PacketDistributor.sendToPlayersNear((ServerLevel) altar.getLevel(), null, altar.getBlockPos().getX(),
						altar.getBlockPos().getY(), altar.getBlockPos().getZ(), 30,
						new AltarAnimatonTimerPacket(altar.getBlockPos().getX(), altar.getBlockPos().getY(),
								altar.getBlockPos().getZ(), altar.animationTicks));
		}

		if (level.isClientSide()) {
			if (altar.animationTicks == 6)
				altar.cloudBurst(level, pos);
		}
	}
	}

	private void cloudBurst(Level level, BlockPos pos) {
		if (level.isClientSide()) {
			double x = pos.getX() + 0.53125F;
			double y = pos.getY() + 1.25F;
			double z = pos.getZ() + 0.53125F;
			ClientParticles.spawnCustomParticle("cloud", x, y, z, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("cloud", x, y, z - 0.265625, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("cloud", x, y, z + 0.265625, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("cloud", x - 0.265625, y, z, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("cloud", x + 0.265625, y, z, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("cloud", x, y + 0.25, z, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("cloud", x, y + 0.5, z, 0.0D, 0.0D, 0.0D);
		}
	}

	public void setActive(boolean isActive) {
		active = isActive;
	}

	public void setSpawnTicks(int i) {
		spawnTicks = i;
	}

	public void setUses(int isSize) {
		uses = isSize;
	}

	public int getUses() {
		return uses;
	}

	public int getExcess() {
		return uses - 165;
	}

	@Override
	protected void writeTileToNBT(CompoundTag nbt) {
		nbt.putInt("animationTicks", animationTicks);
		nbt.putInt("spawnTicks", spawnTicks);
		nbt.putBoolean("active", active);
		nbt.putInt("uses", uses);
	}

	@Override
	protected void readTileFromNBT(CompoundTag nbt) {
		animationTicks = nbt.getInt("animationTicks");
		spawnTicks = nbt.getInt("spawnTicks");
		active = nbt.getBoolean("active");
		uses = nbt.getInt("uses");
	}
}
