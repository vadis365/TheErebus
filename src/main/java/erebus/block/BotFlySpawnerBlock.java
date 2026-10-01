package erebus.block;

import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Locale;
import java.util.function.Supplier;

/**
 * A dung mob spawner with legacy attachment states and a harvest cloud.
 * This block can be placed in various orientations and creates negative status effects when broken.
 */
public class BotFlySpawnerBlock extends ErebusSpawnerBlock {
    public static final EnumProperty<Attachment> TYPE = EnumProperty.create("type", Attachment.class);

    public BotFlySpawnerBlock(Properties properties) {
        this(properties, ModEntities.BOT_FLY);
    }

    public BotFlySpawnerBlock(Properties properties, Supplier<? extends EntityType<?>> entity) {
        super(properties, entity);
        registerDefaultState(getStateDefinition().any().setValue(TYPE, Attachment.DOWN_NORTH));
    }

    /**
     * Adds the TYPE property to the block state definition.
     */
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE);
    }

    /**
     * Determines the appropriate block state when the block is placed in the world.
     * Uses the player's horizontal and vertical looking directions to determine orientation.
     */
    @Override
    public @Nullable BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        var state = placementState(context.getClickedFace(), context.getHorizontalDirection());
        return state.canSurvive(context.getLevel(), context.getClickedPos()) ? state : null;
    }

    public BlockState placementState(Direction clickedFace, Direction playerDirection) {
        String name = clickedFace == Direction.UP ? "DOWN_" + playerDirection.name()
                : clickedFace == Direction.DOWN ? "UP_" + playerDirection.name() : clickedFace.getOpposite().name();
        return defaultBlockState().setValue(TYPE, Attachment.valueOf(name));
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var direction = state.getValue(TYPE).supportDirection();
        var support = pos.relative(direction);
        var block = level.getBlockState(support);
        return block.isSolidRender() && block.isRedstoneConductor(level, support) && block.isFaceSturdy(level, support, direction.getOpposite());
    }

    /**
     * Called when the block is destroyed by a player. Creates an area effect cloud with negative status effects.
     * The cloud applies Mining Fatigue and Nausea effects to entities in the area.
     */
    @Override
    public boolean onDestroyedByPlayer(@NonNull BlockState state, Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull ItemStack toolStack, boolean willHarvest, @NonNull FluidState fluid) {
        if (!level.isClientSide()) {
            // Create an area effect cloud at the block's position
            AreaEffectCloud cloud = new AreaEffectCloud(level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
            cloud.setRadius(3);
            cloud.setRadiusOnUse(-0.5F);
            cloud.setWaitTime(10);
            cloud.setDuration(cloud.getDuration() * 2);
            cloud.setRadiusPerTick(-cloud.getRadius() / cloud.getDuration());

            // Add negative status effects to the cloud
            cloud.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 140));
            cloud.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 200));
            level.addFreshEntity(cloud);
        }

        return super.onDestroyedByPlayer(state, level, pos, player, toolStack, willHarvest, fluid);
    }

    public enum Attachment implements StringRepresentable {
        DOWN_NORTH, DOWN_SOUTH, DOWN_WEST, DOWN_EAST,
        UP_NORTH, UP_SOUTH, UP_WEST, UP_EAST, NORTH, SOUTH, WEST, EAST;

        @Override
        public String getSerializedName() {
            return name().toLowerCase(Locale.ROOT);
        }

        public Direction supportDirection() {
            if (name().startsWith("DOWN_")) return Direction.DOWN;
            if (name().startsWith("UP_")) return Direction.UP;
            return Direction.valueOf(name());
        }
    }
}
