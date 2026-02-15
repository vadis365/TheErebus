package erebus.entity.ai;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class FlyingWanderGoal extends WaterAvoidingRandomStrollGoal {
    public FlyingWanderGoal(PathfinderMob creatureIn, double speedIn, float chance) {
        super(creatureIn, speedIn, chance);
    }

    @Nullable
    protected Vec3 getPosition() {
        Vec3 vec3 = this.mob.getViewVector(0.0F);
        Vec3 vec31 = HoverRandomPos.getPos(this.mob, 8, 7, vec3.x, vec3.z, ((float) Math.PI / 2F), 2, 1);
        return vec31 != null ? vec31 : AirAndWaterRandomPos.getPos(this.mob, 8, 4, -2, vec3.x, vec3.z, (float) Math.PI / 2F);
    }
}
