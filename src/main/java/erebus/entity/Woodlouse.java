package erebus.entity;

import erebus.client.particle.ClientParticles;
import erebus.registries.ModSounds;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class Woodlouse extends Animal {
    public Woodlouse(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 10.0F, 0.7D, 0.6D));
        goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Mob.class, 10.0F, 0.7D, 0.6D));
        goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        goalSelector.addGoal(4, new PanicGoal(this, 0.6D));
        goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 15F)
                .add(Attributes.MOVEMENT_SPEED, 0.6F);
    }

    @Override
    protected void playHurtSound(@NonNull DamageSource source) {
        playSound(ModSounds.BEETLE_HURT.get());
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return ModSounds.SQUISH.get();
    }

    @Override
    protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState state) {
        playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

    @Override
    public @NonNull InteractionResult interact(Player player, @NonNull InteractionHand hand, @NonNull Vec3 location) {
        if(player.getUseItem().isEmpty()) {
            if(!level().isClientSide()) {
                kill((ServerLevel) level());
                drop(new ItemStack(ModItems.WOODLOUSE_BALL.get()), false, false);
            }
            return InteractionResult.SUCCESS;
        }

        return super.interact(player, hand, location);
    }

    @Override
    public void kill(@NonNull ServerLevel level) {
        ClientParticles.spawnCloudBurstParticles(level, getOnPos());
        super.kill(level);
    }

    @Override
    public boolean hurtServer(@NonNull ServerLevel level, DamageSource source, float damage) {
        return !source.is(DamageTypes.IN_WALL) && !source.is(DamageTypes.DROWN) && source.isDirect() && super.hurtServer(level, source, damage);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob ageableMob) {
        return null;
    }
}
