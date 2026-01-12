package erebus.block.portal;

import com.mojang.serialization.MapCodec;
import erebus.Erebus;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModDimensionRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.BlockUtil;
import net.minecraft.util.BlockUtil.FoundRectangle;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

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
            BlockPos neighborPos = new BlockPos(atX, atY, atZ);
            BlockState state = level.getBlockState(neighborPos);
            if (!isSubstrate(state, actualPortal)) {
                continue;
            }

            final int opX = x - direction.getStepX();
            final int opY = y - direction.getStepY();
            final int opZ = z - direction.getStepZ();
            BlockPos opPos = new BlockPos(opX, opY, opZ);
            BlockState stateOpposite = level.getBlockState(opPos);

            if (!isSubstrate(stateOpposite, actualPortal) && !stateOpposite.isCollisionShapeFullBlock(level, opPos)) {
                return false;
            }

            neighborPortals++;
            axisFlag |= 1 << (direction.ordinal() >> 1);
        }

        if (neighborPortals < 1) {
            return false;
        }
        return axisFlag != 0x7;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean isSubstrate(BlockState state, boolean portalNotLeaf) {
        return portalNotLeaf ? state.is(ModBlocks.PORTAL) : state.is(BlockTags.LEAVES);
    }

    @Override
    protected void neighborChanged(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Block block, @org.jspecify.annotations.Nullable Orientation orientation, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, block, orientation, movedByPiston);
    }

    @Override
    protected void entityInside(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, Entity entity, @NonNull InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        if (entity.canUsePortal(false)) {
            entity.setAsInsidePortal(this, entity.blockPosition());
        }
    }

    @Override
    public TeleportTransition getPortalDestination(ServerLevel level, @NonNull Entity entity, @NonNull BlockPos pos) {
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

    private TeleportTransition getExitPortal(ServerLevel level, Entity entity, BlockPos pos, BlockPos exitPos, boolean isErebus, WorldBorder border) {
        Optional<BlockPos> optional = ErebusPortalForcer.findClosestPortalPosition(level, exitPos, isErebus, border);
        BlockUtil.FoundRectangle foundRectangle;
        TeleportTransition.PostTeleportTransition postDimensionTransition;

        if (optional.isPresent()) {
            BlockPos blockPos = optional.get();
            BlockState state = level.getBlockState(blockPos);
            foundRectangle = BlockUtil.getLargestRectangleAround(
                    blockPos,
                    state.getValue(BlockStateProperties.HORIZONTAL_AXIS),
                    ErebusPortalShape.WIDTH,
                    Direction.Axis.Y,
                    ErebusPortalShape.HEIGHT,
                    check -> level.getBlockState(check) == state
            );
            postDimensionTransition = TeleportTransition.PLAY_PORTAL_SOUND.then(player -> player.placePortalTicket(blockPos));
        } else {
            Optional<BlockUtil.FoundRectangle> optionalPortal = ErebusPortalForcer.createPortal(level, pos, Direction.Axis.X);
            if (optionalPortal.isEmpty()) {
                Erebus.LOGGER.error("Unable to create a portal, likely target out of world border");
                return null;
            }

            foundRectangle = optionalPortal.get();
            postDimensionTransition = TeleportTransition.PLAY_PORTAL_SOUND.then(TeleportTransition.PLACE_PORTAL_TICKET);
        }

        return createDimensionTransitionFromExit(entity, pos, foundRectangle, level, postDimensionTransition);
    }

    private TeleportTransition createDimensionTransitionFromExit(Entity entity, BlockPos pos, FoundRectangle rectangle, ServerLevel level, TeleportTransition.PostTeleportTransition postDimensionTransition) {
        BlockState state = entity.level().getBlockState(pos);
        Direction.Axis axis;
        Vec3 vec3;

        if(state.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
            axis = state.getValue(BlockStateProperties.HORIZONTAL_AXIS);
            FoundRectangle foundRectangle = BlockUtil.getLargestRectangleAround(pos, axis, ErebusPortalShape.WIDTH, Direction.Axis.Y, ErebusPortalShape.HEIGHT, check -> entity.level().getBlockState(check) == state);
            vec3 = entity.getRelativePortalPosition(axis, foundRectangle);
        } else {
            axis = Direction.Axis.X;
            vec3 = new Vec3(0.5F, 0.5F, 0.5F);
        }

        return createDimensionTransition(level, rectangle, axis, vec3, entity, entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(), postDimensionTransition);
    }

    private static TeleportTransition createDimensionTransition(ServerLevel level, FoundRectangle rectangle, Direction.Axis axis, Vec3 offset, Entity entity, Vec3 speed, float yRot, float xRot, TeleportTransition.PostTeleportTransition postDimensionTransition) {
        BlockPos blockpos = rectangle.minCorner;
        BlockState blockstate = level.getBlockState(blockpos);
        Direction.Axis direction$axis = blockstate.getOptionalValue(BlockStateProperties.HORIZONTAL_AXIS).orElse(Direction.Axis.X);
        double d0 = rectangle.axis1Size;
        double d1 = rectangle.axis2Size;
        EntityDimensions entitydimensions = entity.getDimensions(entity.getPose());
        int i = axis == direction$axis ? 0 : 90;
        Vec3 vec3 = axis == direction$axis ? speed : new Vec3(speed.z, speed.y, -speed.x);
        double d2 = (double)entitydimensions.width() / (double)2.0F + (d0 - (double)entitydimensions.width()) * offset.x();
        double d3 = (d1 - (double)entitydimensions.height()) * offset.y();
        double d4 = (double)0.5F + offset.z();
        boolean flag = direction$axis == Direction.Axis.X;
        Vec3 vec31 = new Vec3((double)blockpos.getX() + (flag ? d2 : d4), (double)blockpos.getY() + d3, (double)blockpos.getZ() + (flag ? d4 : d2));
        Vec3 vec32 = PortalShape.findCollisionFreePosition(vec31, level, entity, entitydimensions);
        return new TeleportTransition(level, vec32, vec3, yRot + (float)i, xRot, postDimensionTransition);
    }

    @Override
    public int getPortalTransitionTime(@NonNull ServerLevel level, @NonNull Entity entity) {
        return entity instanceof Player player ? player.getAbilities().invulnerable ? 1 : 80 : 0;
    }

    @Override
    public @NonNull Transition getLocalTransition() {
        return Transition.CONFUSION;
    }

    @Override
    public void animateTick(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull RandomSource random) {
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
