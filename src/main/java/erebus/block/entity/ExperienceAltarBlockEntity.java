package erebus.block.entity;

import erebus.block.altars.AltarAbstract;
import erebus.client.particle.ClientParticles;
import erebus.network.client.AltarAnimationTimerPacket;
import erebus.registries.blocks.ModBlockEntities;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
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
							new AltarAnimationTimerPacket(altar.getBlockPos().getX(), altar.getBlockPos().getY(),
									altar.getBlockPos().getZ(), altar.animationTicks));
			}

			if (level.isClientSide()) {
				if (altar.animationTicks == 6)
					ClientParticles.spawnCloudBurstParticles(pos);
			}
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
	protected void writeTileToNBT(ValueOutput output) {
		output.putInt("animationTicks", animationTicks);
		output.putInt("spawnTicks", spawnTicks);
		output.putBoolean("active", active);
		output.putInt("uses", uses);
	}

	@Override
	protected void readTileFromNBT(ValueInput input) {
		animationTicks = input.getIntOr("animationTicks", 0);
		spawnTicks = input.getIntOr("spawnTicks", 0);
		active = input.getBooleanOr("active", false);
		uses = input.getIntOr("uses", 0);
	}
}
