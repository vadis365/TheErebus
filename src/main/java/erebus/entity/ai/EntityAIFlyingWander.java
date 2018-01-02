package erebus.entity.ai;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

public class EntityAIFlyingWander extends EntityAIBase {
	protected final EntityCreature entity;
    protected double x;
    protected double y;
    protected double z;
    protected final double speed;
    protected int executionChance;
    protected boolean mustUpdate;

    public EntityAIFlyingWander(EntityCreature creatureIn, double speedIn) {
        this(creatureIn, speedIn, 120);
    }

    public EntityAIFlyingWander(EntityCreature creatureIn, double speedIn, int chance) {
        this.entity = creatureIn;
        this.speed = speedIn;
        this.executionChance = chance;
        this.setMutexBits(1);
    }
    
	@Override
    public boolean shouldExecute() {
        if (!this.mustUpdate) {
            if (this.entity.getIdleTime() >= 100)
                return false;
            if (this.entity.getRNG().nextInt(this.executionChance) != 0)
                return false;
        }

        Vec3d vec3d = this.getPosition();

        if (vec3d == null)
            return false;
        else {
            this.x = vec3d.x;
            this.y = vec3d.y;
            this.z = vec3d.z;
            this.mustUpdate = false;
            return true;
        }
    }

	@Nullable
	protected Vec3d getPosition() {
		return RandomPositionGenerator.findRandomTarget(entity, 32, 32);
	}

	@Override
	public boolean shouldContinueExecuting() {
		return !entity.getNavigator().noPath();
	}

	@Override
	public void startExecuting() {
		entity.getNavigator().tryMoveToXYZ(x, y, z, speed);
	}
	
    public void makeUpdate() {
        this.mustUpdate = true;
    }

    public void setExecutionChance(int newchance) {
        this.executionChance = newchance;
    }
}
