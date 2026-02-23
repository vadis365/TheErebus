package erebus.entity;

import erebus.registries.entity.ModEntities;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class TarantulaMiniBoss extends Monster {
    public TarantulaMiniBoss(Level level) {
        super(ModEntities.TARANTULA_MINI_BOSS.get(), level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 400F)
                .add(Attributes.MOVEMENT_SPEED, 0.5F)
                .add(Attributes.ATTACK_DAMAGE, 6.0)
                .add(Attributes.FOLLOW_RANGE, 36.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0);
    }
}
