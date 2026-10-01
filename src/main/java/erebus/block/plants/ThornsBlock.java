package erebus.block.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class ThornsBlock extends VineBlock {
    public ThornsBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void entityInside(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Entity entity, @NonNull InsideBlockEffectApplier effects, boolean precise) {
        if (level instanceof ServerLevel server && entity instanceof LivingEntity living) {
            living.hurtServer(server, server.damageSources().cactus(), 1.0F);
        }
    }
}
