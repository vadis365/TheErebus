package erebus.entity;

import erebus.entity.helper.SurfaceClimbing;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public abstract class ClimbingMonster extends Monster {
    protected ClimbingMonster(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    public void calculateEntityAnimation(boolean useY) {
        super.calculateEntityAnimation(useY || SurfaceClimbing.isOnWall(this));
    }
}
