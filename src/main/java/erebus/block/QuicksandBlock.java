package erebus.block;

import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

public class QuicksandBlock extends Block {

    public QuicksandBlock(Properties properties) {
        super(properties);
    }

    public static boolean entityWillSink(Entity entity) {
        if (entity instanceof Player player) {
            if (player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.WATER_STRIDERS)) {
                return false;
            }

            if (player.isCreative()) return false;
        }

        return !(entity instanceof ItemEntity);
    }

    @Override
    protected @NonNull VoxelShape getCollisionShape(@NonNull BlockState state, @NonNull BlockGetter level,
                                                    @NonNull BlockPos pos, @NonNull CollisionContext context) {
        if (context instanceof EntityCollisionContext entityContext) {
            Entity entity = entityContext.getEntity();
            if (entity != null && !entityWillSink(entity)) return Shapes.block();
        }
        return Shapes.empty();
    }

    @Override
    protected void entityInside(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Entity entity, @NonNull InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        if (entityWillSink(entity)) {
            entity.makeStuckInBlock(state, new Vec3(0.08D, 0.08D, 0.08D));

            if (level instanceof ServerLevel server && entity instanceof LivingEntity living
                    && BlockPos.containing(living.getEyePosition()).equals(pos)) {
                living.hurtServer(server, living.damageSources().inWall(), 2.0F);
            }
        }
    }
}
