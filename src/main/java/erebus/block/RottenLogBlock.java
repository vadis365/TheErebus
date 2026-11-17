package erebus.block;

import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class RottenLogBlock extends BarkLogBlock {
    public RottenLogBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void destroy(@NonNull LevelAccessor level, @NonNull BlockPos pos, @NonNull BlockState state) {
        super.destroy(level, pos, state);
        if (!(level instanceof ServerLevel server)) return;
        var random = server.getRandom();
        EntityType<? extends Mob> insect;
        if (random.nextInt(30) == 0) insect = ModEntities.WOODLOUSE.get();
        else if (random.nextInt(30) == 0) insect = ModEntities.BEETLE_LARVA.get();
        else if (random.nextInt(30) == 0) insect = EntityType.SILVERFISH;
        else return;
        var mob = insect.create(server, EntitySpawnReason.TRIGGERED);
        if (mob != null) {
            mob.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            server.addFreshEntity(mob);
        }
    }
}
