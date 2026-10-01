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

public class RepairAltarBlockEntity extends AltarAbstractBlockEntity {

    public boolean notUsed = true;
    private int collisions;
    private java.util.UUID repairItem;
    public RepairAltarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALTAR_REPAIR.get(), pos, state);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T blockEntity) {
        if (blockEntity instanceof RepairAltarBlockEntity altar) {
            altar.prevAnimationTicks = altar.animationTicks;
            if (!level.isClientSide()) {
                if (altar.active) {
                    altar.processRepair((ServerLevel) level);
                    if (altar.animationTicks < 20)
                        altar.animationTicks++;
                }
                if (!altar.active) {
                    if (altar.animationTicks > 0)
                        altar.animationTicks--;
                    if (altar.animationTicks == 1)
                        level.setBlockAndUpdate(pos, ModBlocks.ALTAR_BASE.get().defaultBlockState().setValue(AltarAbstract.FACING, altar.getBlockState().getValue(AltarAbstract.FACING)));
                }
                if (altar.spawnTicks == 0)
                    altar.setActive(false);
                if (altar.spawnTicks > 0) altar.spawnTicks--;

                if (altar.active || altar.spawnTicks > 0 || altar.prevAnimationTicks != altar.animationTicks) altar.setChanged();
                if (altar.prevAnimationTicks != altar.animationTicks)
                    PacketDistributor.sendToPlayersTrackingChunk((ServerLevel) level, net.minecraft.world.level.ChunkPos.containing(pos),
                            new AltarAnimationTimerPacket(altar.getBlockPos().getX(), altar.getBlockPos().getY(),
                                    altar.getBlockPos().getZ(), altar.animationTicks));
            }

            if (level.isClientSide()) {
                if (altar.animationTicks == 6)
                    ClientParticles.spawnCloudBurstParticles(level, pos);
                if (altar.active && !altar.notUsed && altar.collisions < 100 && level.getGameTime() % 2 == 0)
                    altar.sparky(level, pos);
            }
        }
    }

    @Override
    protected void writeTileToNBT(ValueOutput output) {
        output.putInt("animationTicks", animationTicks);
        output.putInt("spawnTicks", spawnTicks);
        output.putBoolean("active", active);
        output.putInt("RepairTicks", collisions);
        output.putBoolean("RepairUnused", notUsed);
        if (repairItem != null) output.store("RepairItem", net.minecraft.core.UUIDUtil.CODEC, repairItem);
    }

    @Override
    protected void readTileFromNBT(ValueInput input) {
        animationTicks = input.getIntOr("animationTicks", 0);
        spawnTicks = input.getIntOr("spawnTicks", 0);
        active = input.getBooleanOr("active", false);
        collisions = Math.clamp(input.getIntOr("RepairTicks", 0), 0, 100);
        notUsed = input.getBooleanOr("RepairUnused", spawnTicks == 0 || spawnTicks > 160);
        repairItem = input.read("RepairItem", net.minecraft.core.UUIDUtil.CODEC).orElse(null);
    }

    public int getSpawnTicks() {
        return spawnTicks;
    }

    public int getCollisions() {
        return collisions;
    }

    public void setCollisions(int i) {
        collisions = Math.clamp(i, 0, 100);
        setChanged();
    }

    public void setcanBeUsed(boolean canBeUsed) {
        notUsed = canBeUsed;
        if (canBeUsed) {
            collisions = 0;
            repairItem = null;
        }
        setChanged();
    }

    public void beginRepair(net.minecraft.world.entity.item.ItemEntity item) {
        if (!active || !notUsed || !item.isAlive() || !item.getItem().isDamageableItem()
                || item.getItem().getDamageValue() <= 0) return;
        repairItem = item.getUUID();
        collisions = 0;
        notUsed = false;
        setSpawnTicks(160);
    }

    private void processRepair(ServerLevel level) {
        if (repairItem == null || collisions >= 100) return;
        if (!(level.getEntity(repairItem) instanceof net.minecraft.world.entity.item.ItemEntity item)
                || !item.isAlive() || !new net.minecraft.world.phys.AABB(worldPosition).inflate(0.5, 1.5, 0.5).contains(item.position())) return;
        var stack = item.getItem();
        if (!stack.isDamageableItem() || stack.getDamageValue() <= 0) return;
        item.setPos(worldPosition.getX() + 0.5, worldPosition.getY() + 1.6, worldPosition.getZ() + 0.5);
        item.setDeltaMovement(net.minecraft.world.phys.Vec3.ZERO);
        item.needsSync = true;
        collisions++;
        setChanged();
        if (collisions == 100) {
            var repaired = stack.copy();
            repaired.setDamageValue(0);
            item.setItem(repaired);
            repairItem = null;
            level.playSound(null, worldPosition, net.minecraft.sounds.SoundEvents.ANVIL_USE,
                    net.minecraft.sounds.SoundSource.BLOCKS, 0.2F, 1);
            syncState();
        }
    }

    public void sparky(Level level, BlockPos pos) {
        if (level.isClientSide()) {
            double x = pos.getX() + 0.53125F;
            double y = pos.getY() + 1.5F;
            double z = pos.getZ() + 0.53125F;
            ClientParticles.spawnParticles(ClientParticles.ParticleType.ENCHANTMENT_TABLE, x, y, z, 0.5D, 0.0D, -0.5D);
            ClientParticles.spawnParticles(ClientParticles.ParticleType.ENCHANTMENT_TABLE, x, y, z, -0.5D, 0.0D, 0.5D);
            ClientParticles.spawnParticles(ClientParticles.ParticleType.ENCHANTMENT_TABLE, x, y, z, -0.5D, 0.0D, -0.5D);
            ClientParticles.spawnParticles(ClientParticles.ParticleType.ENCHANTMENT_TABLE, x, y, z, 0.5D, 0.0D, 0.5D);
            ClientParticles.spawnParticles(ClientParticles.ParticleType.EREBUS_PORTAL, x, y + 0.5, z, 0.0D, 0.0D, 0.0D);
        }
    }
}
