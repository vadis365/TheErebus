package erebus.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A block that spawns Bot Flies when destroyed.
 * This block can be placed in various orientations and creates negative status effects when broken.
 */
public class BotFlySpawnerBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public BotFlySpawnerBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    /**
     * Adds the TYPE property to the block state definition.
     */
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    /**
     * Determines the appropriate block state when the block is placed in the world.
     * Uses the player's horizontal and vertical looking directions to determine orientation.
     */
    @Override
    public @Nullable BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getNearestLookingDirection());
    }

    /**
     * Called when the block is destroyed by a player. Creates an area effect cloud with negative status effects.
     * The cloud applies Mining Fatigue and Nausea effects to entities in the area.
     */
    @Override
    public boolean onDestroyedByPlayer(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, boolean willHarvest, @NotNull FluidState fluid) {
        if (!level.isClientSide()) {
            // Create an area effect cloud at the block's position
            AreaEffectCloud cloud = new AreaEffectCloud(level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
            cloud.setRadius(3);
            cloud.setRadiusOnUse(-0.5F);
            cloud.setWaitTime(10);
            cloud.setDuration(cloud.getDuration() / 2);
            cloud.setRadiusPerTick(-cloud.getRadius() / cloud.getDuration());

            // Add negative status effects to the cloud
            cloud.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 140)); // Mining Fatigue effect
            cloud.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200));    // Nausea effect
            level.addFreshEntity(cloud);
        }

        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }
}
