package erebus.entity.ai;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;

public class FlyingMoveControlLessSpin extends MoveControl {
    private final int maxTurn;
    private final boolean hoversInPlace;

    public FlyingMoveControlLessSpin(Mob mob, int maxTurn, boolean hoversInPlace) {
        super(mob);
        this.maxTurn = maxTurn;
        this.hoversInPlace = hoversInPlace;
    }

    @Override
    public void tick() {
        if (this.operation == MoveControl.Operation.MOVE_TO) {
           this.operation = MoveControl.Operation.WAIT;
            this.mob.setNoGravity(true);
            double d0 = this.wantedX - this.mob.getX();
            double d1 = this.wantedY - this.mob.getY();
            double d2 = this.wantedZ - this.mob.getZ();
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            if (d3 < 2.5000003E-7F) {
                this.mob.setYya(0.0F);
                this.mob.setZza(0.0F);
                return;
            }

            float f = (float)(Mth.atan2(d2, d0) * 180.0F / (float)Math.PI) - 90.0F;
            this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f, this.maxTurn));
            float f1;
            if (this.mob.onGround()) {
                f1 = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
            } else {
                f1 = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.FLYING_SPEED));
            }

            this.mob.setSpeed(f1);
            double d4 = Math.sqrt(d0 * d0 + d2 * d2);
            if (Math.abs(d1) > 1.0E-5F || Math.abs(d4) > 1.0E-5F) {
                float f2 = (float)(-(Mth.atan2(d1, d4) * 180.0F / (float)Math.PI));
                this.mob.setXRot(this.rotlerp(this.mob.getXRot(), f2, (float)this.maxTurn));
                this.mob.setYya(d1 > 0.0 ? f1 : -f1);
            }
        } else {
            if (!this.hoversInPlace) {
                this.mob.setNoGravity(false);
            }

            this.mob.setYya(0.0F);
            this.mob.setZza(0.0F);
        }
    }
}

