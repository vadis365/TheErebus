package erebus.block.portal;

import erebus.registries.ModBlocks;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class ErebusPortalForcer {

    protected final ServerLevel level;

    public ErebusPortalForcer(ServerLevel level) {
        this.level = level;
    }

    public Optional<BlockPos> findClosestPortalPosition(BlockPos exitPos, boolean isErebus, WorldBorder border) {
        PoiManager poiManager = this.level.getPoiManager();
        int offset = isErebus ? 16 : 128;
        poiManager.ensureLoadedAndValid(level, exitPos, offset);
        Stream<BlockPos> stream = poiManager.getInSquare(type -> type.is(PoiTypes.NETHER_PORTAL), exitPos, offset, PoiManager.Occupancy.ANY).map(PoiRecord::getPos);
        Objects.requireNonNull(border);
        return stream
                .filter(border::isWithinBounds)
                .filter((pos) -> this.level.getBlockState(pos)
                        .hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
                .min(Comparator.comparingDouble((BlockPos pos) -> pos.distSqr(exitPos))
                        .thenComparingInt(Vec3i::getY));
    }

    public Optional<BlockUtil.FoundRectangle> createPortal(BlockPos pos, Direction.Axis axis) {
        Direction direction = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        double bestPortalDistanceSq = -1.0F;
        BlockPos bestPortalPos = null;
        double fallbackPortalDistanceSq = -1.0F;
        BlockPos fallbackPortalPos = null;
        WorldBorder worldBorder = this.level.getWorldBorder();
        int maxBuildHeight = Math.min(this.level.getMaxBuildHeight(), this.level.getMinBuildHeight() + this.level.getLogicalHeight()) - 1;
        BlockPos.MutableBlockPos mutablePos = pos.mutable();

        for (BlockPos.MutableBlockPos checkPos : BlockPos.spiralAround(pos, 16, Direction.EAST, Direction.SOUTH)) {
            int surfaceHeight = Math.min(maxBuildHeight, this.level.getHeight(Heightmap.Types.MOTION_BLOCKING, checkPos.getX(), checkPos.getZ()));
            if (worldBorder.isWithinBounds(checkPos) && worldBorder.isWithinBounds(checkPos.move(direction, 1))) {
                checkPos.move(direction.getOpposite(), 1);

                for (int currentY = surfaceHeight; currentY >= this.level.getMinBuildHeight(); --currentY) {
                    checkPos.setY(currentY);
                    if (this.canPortalReplaceBlock(checkPos)) {
                        int startY;
                        for (startY = currentY; currentY > this.level.getMinBuildHeight() && this.canPortalReplaceBlock(checkPos.move(Direction.DOWN)); --currentY) {
                        }

                        if (currentY + 4 <= maxBuildHeight) {
                            int heightDifference = startY - currentY;
                            if (heightDifference <= 0 || heightDifference >= 3) {
                                checkPos.setY(currentY);
                                if (this.canHostFrame(checkPos, mutablePos, direction, 0)) {
                                    double currentDistanceSq = pos.distSqr(checkPos);
                                    if (this.canHostFrame(checkPos, mutablePos, direction, -1) && this.canHostFrame(checkPos, mutablePos, direction, 1) && (bestPortalDistanceSq == (double) -1.0F || bestPortalDistanceSq > currentDistanceSq)) {
                                        bestPortalDistanceSq = currentDistanceSq;
                                        bestPortalPos = checkPos.immutable();
                                    }

                                    if (bestPortalDistanceSq == (double) -1.0F && (fallbackPortalDistanceSq == (double) -1.0F || fallbackPortalDistanceSq > currentDistanceSq)) {
                                        fallbackPortalDistanceSq = currentDistanceSq;
                                        fallbackPortalPos = checkPos.immutable();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (bestPortalDistanceSq == (double) -1.0F && fallbackPortalDistanceSq != (double) -1.0F) {
            bestPortalPos = fallbackPortalPos;
            bestPortalDistanceSq = fallbackPortalDistanceSq;
        }

        if (bestPortalDistanceSq == (double) -1.0F) {
            int minPortalHeight = Math.max(this.level.getMinBuildHeight() - -1, 70);
            int adjustedMaxHeight = maxBuildHeight - 9;
            if (adjustedMaxHeight < minPortalHeight) {
                return Optional.empty();
            }

            bestPortalPos = (new BlockPos(pos.getX() - direction.getStepX(), Mth.clamp(pos.getY(), minPortalHeight, adjustedMaxHeight), pos.getZ() - direction.getStepZ())).immutable();
            bestPortalPos = worldBorder.clampToBounds(bestPortalPos);
            Direction perpendicularDirection = direction.getClockWise();

            for (int x = -1; x < 2; ++x) {
                for (int z = 0; z < 2; ++z) {
                    for (int y = -1; y < 3; ++y) {
                        BlockState state = y < 0 ? Blocks.STONE_BRICKS.defaultBlockState() : Blocks.AIR.defaultBlockState();
                        mutablePos.setWithOffset(bestPortalPos, z * direction.getStepX() + x * perpendicularDirection.getStepX(), y, z * direction.getStepZ() + x * perpendicularDirection.getStepZ());
                        this.level.setBlockAndUpdate(mutablePos, state);
                    }
                }
            }
        }

        for (int x = -1; x < 3; ++x) {
            for (int z = -1; z < 4; ++z) {
                if (x == -1 || x == 2 || z == -1 || z == 3) {
                    mutablePos.setWithOffset(bestPortalPos, x * direction.getStepX(), z, x * direction.getStepZ());
                    this.level.setBlock(mutablePos, Blocks.STONE_BRICKS.defaultBlockState(), 3);
                }
            }
        }

        BlockState blockstate = ModBlocks.PORTAL.get().defaultBlockState().setValue(ErebusPortalBlock.AXIS, axis);

        for (int x = 0; x < 2; ++x) {
            for (int z = 0; z < 3; ++z) {
                mutablePos.setWithOffset(bestPortalPos, x * direction.getStepX(), z, x * direction.getStepZ());
                this.level.setBlock(mutablePos, blockstate, 18);
            }
        }

        return Optional.of(new BlockUtil.FoundRectangle(bestPortalPos.immutable(), 2, 3));
    }

    private boolean canPortalReplaceBlock(BlockPos.MutableBlockPos pos) {
        return level.getBlockState(pos).canBeReplaced() && level.getBlockState(pos).getFluidState().isEmpty();
    }

    private boolean canHostFrame(BlockPos pos, BlockPos.MutableBlockPos offset, Direction direction, int scale) {
        Direction perpendicularDirection = direction.getClockWise();

        for (int xOffset = -1; xOffset < 3; xOffset++) {
            for (int yOffset = -1; yOffset < 4; yOffset++) {
                offset.setWithOffset(pos, direction.getStepX() * xOffset + perpendicularDirection.getStepX() * scale, yOffset, direction.getStepZ() * xOffset + perpendicularDirection.getStepZ() * scale);
                if (yOffset < 0 && !level.getBlockState(offset).isSolid()) return false;
                if (yOffset >= 0 && !canPortalReplaceBlock(offset)) return false;
            }
        }

        return true;
    }
}
