package erebus.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ChameleonTick extends Monster {

    public BlockState state;
    public int animation;
    public boolean active;

    private final NearestAttackableTargetGoal<Player> attackTargetGoal = new NearestAttackableTargetGoal<>(this, Player.class, true);
    private final MeleeAttackGoal meleeAttackGoal = new MeleeAttackGoal(this, 0.65D, false);

    public ChameleonTick(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        //goalSelector.addGoal(0, attackTargetGoal);
        //targetSelector.addGoal(1, meleeAttackGoal);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 30F)
                .add(Attributes.MOVEMENT_SPEED, 0.5F)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.FOLLOW_RANGE, 16.0);
    }

    @Override
    public void tick() {
        super.tick();
        state = level().getBlockState(getOnPos().below());
        Player player = findPlayerToAttack();

        if (player != null) {
            setTarget(player);
            if (!active)
                active = true;
            animation++;
            if (animation >= 10)
                animation = 10;

        } else {
            setTarget(null);
            if (active)
                active = false;
            animation--;
            if (animation <= 0)
                animation = 0;
        }

        if (level().isClientSide() && animation == 9 && active)
            setAIs(true);

        if (level().isClientSide() && !active) {
            stationaryEntity();
            if (animation == 1)
                setAIs(false);
        }
    }

    public void stationaryEntity() {
        getMoveControl().setWantedPosition(getX() + 0.5, getY(), getZ() + 0.5, 0.0F);
        if(state.isAir()) {
            getMoveControl().setWantedPosition(getX(), getY() - 1, getZ(), 0.0F);
        }
    }

    protected Player findPlayerToAttack() {
        return level().getNearestPlayer(getX(), getY(), getZ(), 16.0D, (player) -> !player.isSpectator() && !player.isInvulnerable());
    }

    public void setAIs(boolean active) {
        if (active) {
            attackTargetGoal.start();
            meleeAttackGoal.start();
        } else {
            attackTargetGoal.stop();
            meleeAttackGoal.stop();
        }
    }
}
