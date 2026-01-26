package erebus.block.entity;

import erebus.block.altars.AltarAbstract;
import erebus.client.particle.ClientParticles;
import erebus.network.client.AltarAnimatonTimerPacket;
import erebus.registries.blocks.ModBlockEntities;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.PacketDistributor;

public class RepairAltarBlockEntity extends AltarAbstractBlockEntity {
	
	public RepairAltarBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.ALTAR_REPAIR.get(), pos, state);
	}
	public boolean active;
	private int spawnTicks;
	public boolean notUsed = true;
	private int collisions;

	public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T blockEntity) {
		if (blockEntity instanceof RepairAltarBlockEntity altar) {
			altar.prevAnimationTicks = altar.animationTicks;
			if (!level.isClientSide()) {
				if (altar.active) {
					if (altar.animationTicks < 20)
						altar.animationTicks++;
				}
				if (!altar.active) {
					if (altar.animationTicks > 0)
						altar.animationTicks--;
					if (altar.animationTicks == 1)
						level.setBlockAndUpdate(pos, ModBlocks.ALTAR_BASE.get().defaultBlockState().setValue(AltarAbstract.FACING, altar.getBlockState().getValue(AltarAbstract.FACING)));
				}
				if (altar.spawnTicks == 160)
					altar.setcanBeUsed(false);
				if (altar.spawnTicks == 0)
					altar.setActive(false);
				altar.spawnTicks--;

				if (altar.prevAnimationTicks != altar.animationTicks)
					PacketDistributor.sendToPlayersNear((ServerLevel) altar.getLevel(), null,
							altar.getBlockPos().getX(), altar.getBlockPos().getY(), altar.getBlockPos().getZ(), 30,
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

	public int getSpawnTicks() {
		return spawnTicks;
	}

	public void setCollisions(int i) {
		collisions = i;
	}

	public int getCollisions() {
		return collisions;
	}

	public void setcanBeUsed(boolean canBeUsed) {
		notUsed = canBeUsed;
	}

	public void sparky(Level level, BlockPos pos) {
		if (level.isClientSide()) {
			double x = pos.getX() + 0.53125F;
			double y = pos.getY() + 1.5F;
			double z = pos.getZ() + 0.53125F;
			ClientParticles.spawnCustomParticle("enchantmenttable", x, y, z, 0.5D, 0.0D, -0.5D);
			ClientParticles.spawnCustomParticle("enchantmenttable", x, y, z, -0.5D, 0.0D, 0.5D);
			ClientParticles.spawnCustomParticle("enchantmenttable", x, y, z, -0.5D, 0.0D, -0.5D);
			ClientParticles.spawnCustomParticle("enchantmenttable", x, y, z, 0.5D, 0.0D, 0.5D);
			ClientParticles.spawnCustomParticle("portal", x, y + 0.5, z, 0.0D, 0.0D, 0.0D);
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
