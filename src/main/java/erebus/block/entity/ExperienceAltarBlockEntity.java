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
    public static final int CAPACITY = 165;
    private int uses;

    public ExperienceAltarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALTAR_EXPERIENCE.get(), pos, state);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T blockEntity) {
        if (blockEntity instanceof ExperienceAltarBlockEntity altar) {
            altar.prevAnimationTicks = altar.animationTicks;
            if (!level.isClientSide()) {
                if (altar.active) {
                    if (altar.animationTicks < 20)
                        altar.animationTicks++;
                    if (altar.spawnTicks == 0)
                        altar.setActive(false);
                    if (altar.spawnTicks > 0) altar.spawnTicks--;
                }
                if (!altar.active) {
                    if (altar.animationTicks > 0)
                        altar.animationTicks--;
                    if (altar.animationTicks == 1)
                        level.setBlockAndUpdate(pos, ModBlocks.ALTAR_BASE.get().defaultBlockState().setValue(AltarAbstract.FACING, altar.getBlockState().getValue(AltarAbstract.FACING)));
                }
                if (altar.active || altar.spawnTicks > 0 || altar.prevAnimationTicks != altar.animationTicks) altar.setChanged();
                if (altar.prevAnimationTicks != altar.animationTicks)
                    PacketDistributor.sendToPlayersTrackingChunk((ServerLevel) level, net.minecraft.world.level.ChunkPos.containing(pos),
                            new AltarAnimationTimerPacket(altar.getBlockPos().getX(), altar.getBlockPos().getY(),
                                    altar.getBlockPos().getZ(), altar.animationTicks));
            }

            if (level.isClientSide()) {
                if (altar.animationTicks == 6)
                    ClientParticles.spawnCloudBurstParticles(level, pos);
            }
        }
    }

    public int getUses() {
        return uses;
    }

    public void setUses(int isSize) {
        uses = Math.clamp(isSize, 0, CAPACITY);
        setChanged();
    }

    public int getExcess() {
        return Math.max(0, uses - CAPACITY);
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
        uses = Math.clamp(input.getIntOr("uses", 0), 0, CAPACITY);
        if (uses == CAPACITY) active = false;
    }
}
