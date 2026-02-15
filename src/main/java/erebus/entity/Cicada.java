package erebus.entity;

import erebus.entity.ai.FlyingWanderGoal;
import erebus.registries.client.ModParticles;
import erebus.utils.AnimationMathHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class Cicada extends Monster {

    private int sonics;
    public float wingFloat;
    public boolean isFlying;
    private final AnimationMathHelper mathWings = new AnimationMathHelper();

    public Cicada(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(0, new WaterAvoidingRandomStrollGoal(this, 0.5D));
        goalSelector.addGoal(1, new FlyingWanderGoal(this, 0.5D, 0.02F));
        goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 5F)
                .add(Attributes.MOVEMENT_SPEED, 0F)
                .add(Attributes.FOLLOW_RANGE, 8.0);
    }

    @Override
    public void tick() {
        super.tick();
        isFlying = !onGround();

        if (sonics <= 20)
            sonics++;
        if (sonics > 20)
            sonics = 0;
        if (sonics > 10)
            setTarget(null);

        if (!onGround())
            wingFloat = 0.0F;
        else
            wingFloat = mathWings.swing(4.0F, 0.1F);

        if (getDeltaMovement().y < 0.0D)
            getDeltaMovement().multiply(1, 0.6D, 1);
    }

    @Override
    public boolean onClimbable() {
        return horizontalCollision;
    }

    public void spawnSonicParticles() {
        for(int c = 0; c < 360; c++) {
            float ang = c * Mth.PI / 180F;
            level().addParticle(ModParticles.REPELLENT.get(), getX() + -Mth.sin(ang) * 1.0, getY() + 0.5D, getZ() + Mth.cos(ang) * 1.0, -Mth.sin(ang) * 0.3, 0D, Mth.cos(ang) * 0.3);
        }

        for (int c = 0; c < 360; c += 4) {
            float ang = c * Mth.PI / 180F;
            level().addParticle(ModParticles.SONIC.get(), getX() + -Mth.sin(ang) * 1.0, getY() + 0.5D, getZ() + Mth.cos(ang) * 1.0, -Mth.sin(ang) * 0.3, 0D, Mth.cos(ang) * 0.3);
        }
    }
}
