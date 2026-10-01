package erebus.block.entity;

import erebus.block.ForceFieldBlock;
import erebus.block.TempleSealBlock;
import erebus.block.TempleTeleporterBlock;
import erebus.block.plants.ThornsBlock;
import erebus.registries.ModSounds;
import erebus.registries.blocks.ModBlockEntities;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TempleTeleporterBlockEntity extends BlockEntity {
    private static final List<BlockPos> LANDING_OFFSETS = landingOffsets();
    @Nullable
    private BlockPos destination;
    private boolean arenaReleased;

    public TempleTeleporterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TEMPLE_TELEPORTER.get(), pos, state);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, TempleTeleporterBlockEntity teleporter) {
        if (!(level instanceof ServerLevel server) || level.getGameTime() % 5 != 0) return;
        teleporter.advanceOrdinarySealRing(server);
        teleporter.teleportOccupants(server);
    }

    @Nullable
    public static Vec3 findLanding(ServerLevel level, LivingEntity entity, BlockPos destination) {
        for (BlockPos offset : LANDING_OFFSETS) {
            Vec3 feet = Vec3.atBottomCenterOf(destination.above().offset(offset));
            AABB box = entity.getBoundingBox().move(feet.subtract(entity.position()));
            if (box.minY - 1 < level.getMinY() || box.maxY > level.getMaxY() + 1 || !level.getWorldBorder().isWithinBounds(box)) continue;
            // Check the full collision query margin before asking Minecraft for collisions.
            BlockPos min = BlockPos.containing(box.minX - 1, box.minY - 1, box.minZ - 1);
            BlockPos max = BlockPos.containing(box.maxX + 1, box.maxY + 1, box.maxZ + 1);
            if (!level.hasChunksAt(min, max)) continue;
            boolean safe = true;
            for (BlockPos p : BlockPos.betweenClosed(BlockPos.containing(box.minX, box.minY - 1, box.minZ),
                    BlockPos.containing(box.maxX - 1.0E-7, box.maxY - 1.0E-7, box.maxZ - 1.0E-7))) {
                BlockState state = level.getBlockState(p);
                PathType path = level.getPathTypeCache().getOrCompute(level, p);
                if (state.getBlock() instanceof ForceFieldBlock || state.getBlock() instanceof WebBlock || state.getBlock() instanceof ThornsBlock || !state.getFluidState().isEmpty() || (path != PathType.OPEN && path != PathType.WALKABLE && path != PathType.BLOCKED)
                        || (p.getY() < box.minY && !state.isFaceSturdy(level, p, Direction.UP))) {
                    safe = false;
                    break;
                }
            }
            if (safe && level.noCollision(entity, box)) return feet;
        }
        return null;
    }

    private static List<BlockPos> landingOffsets() {
        List<BlockPos> result = new ArrayList<>();
        for (int x = -4; x <= 4; x++) for (int y = -2; y <= 2; y++) for (int z = -4; z <= 4; z++) result.add(new BlockPos(x, y, z));
        result.sort(Comparator.comparingDouble(p -> p.distSqr(BlockPos.ZERO)));
        return List.copyOf(result);
    }

    @Nullable
    public BlockPos getDestination() {
        return destination;
    }

    public void setDestination(BlockPos pos) {
        destination = pos.immutable();
        setChanged();
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        output.putBoolean("arenaReleased", arenaReleased);
        if (destination != null) output.store("destination", BlockPos.CODEC, destination);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        arenaReleased = input.getBooleanOr("arenaReleased", false);
        destination = input.read("destination", BlockPos.CODEC).orElse(null);
    }

    private void advanceOrdinarySealRing(ServerLevel level) {
        BlockState state = getBlockState();
        int phase = state.getValue(TempleTeleporterBlock.PHASE);
        if (phase >= 4) return;
        int stringSeals = 0;
        for (int x = -1; x <= 1; x++)
            for (int z = -1; z <= 1; z++) {
                if (x == 0 && z == 0) continue;
                BlockPos pos = worldPosition.offset(x, 0, z);
                if (!level.hasChunkAt(pos)) return;
                BlockState seal = level.getBlockState(pos);
                if (!(seal.getBlock() instanceof TempleSealBlock)
                        || !seal.getValue(TempleSealBlock.ACTIVE)) return;
                if (seal.is(ModBlocks.TEMPLE_BRICK_UNBREAKING_STRING)) stringSeals++;
            }
        if (stringSeals != 0 && stringSeals != 8) return;
        if (stringSeals == 8 && phase == 3 && !arenaReleased) {
            if (!AntlionArenaRitual.release(level, worldPosition)) return;
            arenaReleased = true;
            setChanged();
        }
        if (level.setBlock(worldPosition, state.setValue(TempleTeleporterBlock.PHASE, phase + 1), 3) && phase == 0)
            level.playSound(null, worldPosition, ModSounds.ALTAR_CHANGE_STATE.get(), SoundSource.BLOCKS, 1, 1.3F);
    }

    public void teleportOccupants(ServerLevel level) {
        if (destination == null || getBlockState().getValue(TempleTeleporterBlock.PHASE) < 4) return;
        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, new AABB(worldPosition.above()).inflate(0, 0.25, 0))) {
            if (!entity.isAlive() || entity.isShiftKeyDown()) continue;
            Vec3 landing = findLanding(level, entity, destination);
            if (landing == null) continue;
            // Passengers are checked independently; never carry an unchecked rider into a low ceiling.
            entity.stopRiding();
            entity.ejectPassengers();
            entity.teleportTo(landing.x, landing.y, landing.z);
            entity.fallDistance = 0;
            level.playSound(null, worldPosition, SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS, 1, 1);
        }
    }
}
