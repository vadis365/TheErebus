package erebus.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.math.MathHelper;

public class FlyingMoveHelper extends EntityMoveHelper {
    private EntityCreature entity;
    private int courseChangeCooldown;

    public FlyingMoveHelper(EntityCreature entity) {
        super(entity);
        this.entity = entity;
    }

    public void onUpdateMoveHelper() {
        if (this.action == EntityMoveHelper.Action.MOVE_TO) {
            double x = this.posX - this.entity.posX;
            double y = this.posY + 1D - this.entity.posY;
            double z = this.posZ - this.entity.posZ;
            float distance = (float) Math.sqrt(x * x + y * y + z * z);

            if (this.courseChangeCooldown-- <= 0) {
                this.courseChangeCooldown += 1;
                if(distance >= 1D) {
                	this.entity.motionX += (Math.signum(x) * 0.5D - entity.motionX) * 0.1D;
                	this.entity.motionY += (Math.signum(y) * 0.5D - entity.motionY) * 0.2D;
                    this.entity.motionZ += (Math.signum(z) * 0.5D - entity.motionZ) * 0.1D;
					float angle = (float) (Math.atan2(entity.motionZ, entity.motionX) * 180.0D / Math.PI) - 90.0F;
					float rotation = MathHelper.wrapDegrees(angle - entity.rotationYaw);
					entity.rotationYaw += rotation;
                }
                else {
                    this.action = EntityMoveHelper.Action.WAIT;
                }
            }
        }

        if (this.entity.getAttackTarget() != null) {
            EntityLivingBase entitylivingbase = this.entity.getAttackTarget();
            double distanceX = entitylivingbase.posX - this.entity.posX;
            double distanceZ = entitylivingbase.posZ - this.entity.posZ;
            this.entity.renderYawOffset = this.entity.rotationYaw = -((float)MathHelper.atan2(distanceX, distanceZ)) * (180F / (float)Math.PI);
        }
        else if(this.action == EntityMoveHelper.Action.MOVE_TO) {
            this.entity.renderYawOffset = this.entity.rotationYaw = -((float)MathHelper.atan2(this.entity.motionX, this.entity.motionZ)) * (180F / (float)Math.PI);
        }
    }
}
