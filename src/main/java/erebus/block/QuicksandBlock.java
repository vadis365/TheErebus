package erebus.block;

import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class QuicksandBlock extends Block {

    public QuicksandBlock(Properties properties) {
        super(properties);
    }

    public static boolean entityWillSink(Entity entity) {
        if(entity instanceof Player player) {
            if(player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.WATER_STRIDERS)) {
                return false;
            }

            if(player.isCreative()) return false;
        }

        return !(entity instanceof ItemEntity);
    }

    @Override
    protected void entityInside(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Entity entity) {
        if (entityWillSink(entity)) {
            entity.makeStuckInBlock(state, new Vec3(0.08D, 0.08D, 0.08D));

            if(pos.getY() < 0) {
                if(Math.abs(entity.getBoundingBox().maxY - entity.getEyeHeight()) >= Math.abs(pos.getY())) entity.hurt(entity.damageSources().inWall(), 2.0F);
            } else {
                if(Math.abs(entity.getBoundingBox().maxY) <= Math.abs(pos.getY())) entity.hurt(entity.damageSources().inWall(), 2.0F);
            }
        }
    }
}
