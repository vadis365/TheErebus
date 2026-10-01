package erebus.entity.ai;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;

public class LocustMoveControl extends MoveControl {
    public LocustMoveControl(Mob mob) {
        super(mob);
    }

    @Override
    public void tick() {
        if (operation == Operation.MOVE_TO) {
            double x = wantedX - mob.getX(), y = wantedY + 1 - mob.getY(), z = wantedZ - mob.getZ();
            if ((float) Math.sqrt(x * x + y * y + z * z) >= 1) {
                var motion = mob.getDeltaMovement();
                mob.setDeltaMovement(motion.x + (Math.signum(x) * 0.5 - motion.x) * 0.1,
                        motion.y + (Math.signum(y) * 0.5 - motion.y) * 0.2,
                        motion.z + (Math.signum(z) * 0.5 - motion.z) * 0.1);
            } else operation = Operation.WAIT;
        }
        if (mob.getTarget() != null) {
            mob.setYRot(-(float) Mth.atan2(mob.getTarget().getX() - mob.getX(), mob.getTarget().getZ() - mob.getZ()) * 180F / (float) Math.PI);
            mob.yBodyRot = mob.getYRot();
        } else if (operation == Operation.MOVE_TO) {
            var motion = mob.getDeltaMovement();
            mob.setYRot(-(float) Mth.atan2(motion.x, motion.z) * 180F / (float) Math.PI);
            mob.yBodyRot = mob.getYRot();
        }
    }
}
