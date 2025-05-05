package erebus.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.NotNull;

public class BotFlySpawnerBlock extends SpawnerBlock {

    public static final EnumProperty<EnumDungDirection> TYPE = EnumProperty.create("type", EnumDungDirection.class);

    public BotFlySpawnerBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(TYPE, EnumDungDirection.DOWN_NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE);
    }

    @Override
    public boolean onDestroyedByPlayer(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, boolean willHarvest, @NotNull FluidState fluid) {
        if (!level.isClientSide()) {
            AreaEffectCloud cloud = new AreaEffectCloud(level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
            cloud.setRadius(3);
            cloud.setRadiusOnUse(-0.5F);
            cloud.setWaitTime(10);
            cloud.setDuration(cloud.getDuration() * 2);
            cloud.setRadiusPerTick(-cloud.getRadius() / cloud.getDuration());
            cloud.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 140));
            cloud.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200));
            level.addFreshEntity(cloud);
        }

        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }
}
