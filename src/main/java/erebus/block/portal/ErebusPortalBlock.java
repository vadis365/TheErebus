package erebus.block.portal;

import com.mojang.serialization.MapCodec;
import erebus.Erebus;
import erebus.registries.ModBlocks;
import erebus.registries.world.ModDimensionRegistries;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ErebusPortalBlock extends Block implements Portal {

    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    public static final MapCodec<ErebusPortalBlock> CODEC = simpleCodec(ErebusPortalBlock::new);

    public ErebusPortalBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(AXIS, Direction.Axis.X));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    public static boolean obeysPortalRule(Level level, BlockPos pos, boolean actualPortal) {
        int neighborPortals = 0;
        int axisFlag = 0;

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        for (Direction direction : Direction.values()) {
            final int atX = x + direction.getStepX();
            final int atY = y + direction.getStepY();
            final int atZ = z + direction.getStepZ();
            BlockState state = level.getBlockState(new BlockPos(atX, atY, atZ));
            if (isSubstrate(state, actualPortal)) continue;

            final int opX = x - direction.getStepZ();
            final int opY = y - direction.getStepY();
            final int opZ = z - direction.getStepX();
            BlockState stateOpposite = level.getBlockState(new BlockPos(opX, opY, opZ));

            if (!stateOpposite.isCollisionShapeFullBlock(level, new BlockPos(opX, opY, opZ)) && isSubstrate(stateOpposite, actualPortal)) {
                return false;
            }

            neighborPortals++;
            axisFlag |= 1 << (direction.getAxis().ordinal() >> 1);
        }

        if (neighborPortals < 1) return false;
        return axisFlag != 0x7;
    }

    private static boolean isSubstrate(BlockState state, boolean actualPortal) {
        return actualPortal ? !state.is(ModBlocks.PORTAL) : !state.is(BlockTags.LEAVES);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if (!obeysPortalRule(level, pos, true)) level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity.canUsePortal(false)) {
            entity.setAsInsidePortal(this, entity.blockPosition());
        }
    }

    @Override
    public @Nullable DimensionTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos pos) {
        ResourceKey<Level> dimension = level.dimension() != ModDimensionRegistries.DIMENSION_KEY ? ModDimensionRegistries.DIMENSION_KEY : Level.OVERWORLD;
        ServerLevel server = level.getServer().getLevel(dimension);

        if (server == null) {
            return null;
        }

        WorldBorder border = server.getWorldBorder();
        double scale = DimensionType.getTeleportationScale(level.dimensionType(), server.dimensionType());
        BlockPos exitPos = border.clampToBounds(pos.getX() * scale, pos.getY(), pos.getZ() * scale);
        return getExitPortal(server, entity, pos, exitPos, level.dimension() == ModDimensionRegistries.DIMENSION_KEY, border);
    }

    private DimensionTransition getExitPortal(ServerLevel level, Entity entity, BlockPos pos, BlockPos exitPos, boolean isErebus, WorldBorder border) {
        ErebusPortalForcer portalForcer = new ErebusPortalForcer(level);
        Optional<BlockPos> optional = portalForcer.findClosestPortalPosition(exitPos, isErebus, border);
        BlockUtil.FoundRectangle foundRectangle;
        DimensionTransition.PostDimensionTransition postDimensionTransition;

        if (optional.isPresent()) {
            BlockPos blockPos = optional.get();
            BlockState state = level.getBlockState(blockPos);
            foundRectangle = BlockUtil.getLargestRectangleAround(
                    blockPos,
                    state.getValue(BlockStateProperties.HORIZONTAL_AXIS),
                    ErebusPortalShape.MAX_WIDTH,
                    Direction.Axis.Y,
                    ErebusPortalShape.MAX_HEIGHT,
                    check -> level.getBlockState(check) == state
            );
            postDimensionTransition = DimensionTransition.PLAY_PORTAL_SOUND.then(player -> player.placePortalTicket(blockPos));
        } else {
            Direction.Axis axis = entity.level().getBlockState(pos).getOptionalValue(AXIS).orElse(Direction.Axis.X);
            Optional<BlockUtil.FoundRectangle> optionalPortal = portalForcer.createPortal(exitPos, axis);
            if (optionalPortal.isEmpty()) {
                Erebus.LOGGER.error("Unable to create a portal, likely target out of world border");
                return null;
            }

            foundRectangle = optionalPortal.get();
            postDimensionTransition = DimensionTransition.PLAY_PORTAL_SOUND.then(DimensionTransition.PLACE_PORTAL_TICKET);
        }

        return getDimensionTransitionFromExit(entity, pos, foundRectangle, level, postDimensionTransition);
    }

    private DimensionTransition getDimensionTransitionFromExit(Entity entity, BlockPos pos, BlockUtil.FoundRectangle foundRectangle, ServerLevel level, DimensionTransition.PostDimensionTransition postDimensionTransition) {
        BlockState state = entity.level().getBlockState(pos);
        Direction.Axis axis;
        Vec3 vec3;

        if (state.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
            axis = state.getValue(BlockStateProperties.HORIZONTAL_AXIS);
            BlockUtil.FoundRectangle largestRectangleAround = BlockUtil.getLargestRectangleAround(
                    pos,
                    axis,
                    ErebusPortalShape.MAX_WIDTH,
                    Direction.Axis.Y,
                    ErebusPortalShape.MAX_HEIGHT,
                    check -> entity.level().getBlockState(check) == state
            );
            vec3 = entity.getRelativePortalPosition(axis, foundRectangle);
        } else {
            axis = Direction.Axis.X;
            vec3 = new Vec3(0, 0, 0);
        }

        return createDimensionTransition(level, foundRectangle, axis, vec3, entity, entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(), postDimensionTransition);
    }

    private DimensionTransition createDimensionTransition(ServerLevel level, BlockUtil.FoundRectangle rectangle, Direction.Axis axis, Vec3 offset, Entity entity, Vec3 speed, float yRot, float xRot, DimensionTransition.PostDimensionTransition postDimensionTransition) {
        BlockPos pos = rectangle.minCorner;
        BlockState state = level.getBlockState(pos);
        Direction.Axis facing = state.getOptionalValue(BlockStateProperties.HORIZONTAL_AXIS).orElse(Direction.Axis.X);
        double width = rectangle.axis1Size;
        double height = rectangle.axis2Size;
        EntityDimensions dimensions = entity.getDimensions(entity.getPose());
        int rotation = axis == facing ? 0 : 90;
        Vec3 newSpeed = axis == facing ? speed : new Vec3(speed.z, speed.y, -speed.x);
        double offsetX = dimensions.width() / 2 + (width - dimensions.width()) * offset.x;
        double offsetY = (height - dimensions.height()) * offset.y;
        double offsetZ = 0.5 + offset.z;
        boolean flag = facing == Direction.Axis.X;
        Vec3 newPos = new Vec3(pos.getX() + (flag ? offsetX : offsetZ), pos.getY() + offsetY, pos.getZ() + (flag ? offsetZ : offsetX));
        Vec3 collisionFreePos = ErebusPortalShape.findCollisionFreePosition(newPos, level, entity, dimensions);
        return new DimensionTransition(level, collisionFreePos, newSpeed, yRot + rotation, xRot, postDimensionTransition);
    }

    @Override
    public int getPortalTransitionTime(ServerLevel level, Entity entity) {
        return entity instanceof Player player ? player.getAbilities().invulnerable ? 1 : 80 : 0;
    }

    @Override
    public Transition getLocalTransition() {
        return Transition.CONFUSION;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        for (int i = 0; i < 4; i++) {
            double particleX = pos.getX() + random.nextFloat();
            double particleY = pos.getY() + random.nextFloat();
            double particleZ = pos.getZ() + random.nextFloat();
            double motionX;
            double motionY;
            double motionZ;
            float multi = (random.nextFloat() * 2.0F - 1.0F) / 4.0F;

            motionX = (random.nextFloat() - 0.5D) * 0.25D;
            motionY = (random.nextFloat() - 0.5D) * 0.25D;
            motionZ = (random.nextFloat() - 0.5D) * 0.25D;

            if (!level.getBlockState(pos.offset(-1, 0, 0)).is(this) && !level.getBlockState(pos.offset(1, 0, 0)).is(this)) {
                particleX = pos.getX() + 0.5D + 0.25D * multi;
                motionX = random.nextFloat() * 2.0F * multi;
            } else {
                particleZ = pos.getZ() + 0.5D + 0.25D * multi;
                motionZ = random.nextFloat() * 2.0F * multi;
            }

            level.addParticle(ParticleTypes.PORTAL, particleX, particleY, particleZ, motionX, motionY, motionZ);
        }

        if (random.nextInt(20) == 0) {
            level.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS, 0.3F, random.nextFloat() * 0.4F + 0.8F, true);
        }
    }
}
