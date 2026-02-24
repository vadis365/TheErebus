package erebus.entity;

import erebus.client.particle.ClientParticles;
import erebus.inventory.container.TitanBeetleContainer;
import erebus.registries.ModSounds;
import erebus.registries.data.tags.ModItemTags;
import erebus.registries.entity.ModEntities;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemStackWithSlot;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class TitanBeetle extends TamableAnimal implements PlayerRideable {

    private static final EntityDataAccessor<Byte> BEETLE_TYPE = SynchedEntityData.defineId(TitanBeetle.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Float> OPEN_TICKS = SynchedEntityData.defineId(TitanBeetle.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> PREV_OPEN_TICKS = SynchedEntityData.defineId(TitanBeetle.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Boolean> HAS_CHEST = SynchedEntityData.defineId(TitanBeetle.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_ENDER_CHEST = SynchedEntityData.defineId(TitanBeetle.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_SADDLE = SynchedEntityData.defineId(TitanBeetle.class, EntityDataSerializers.BOOLEAN);

    private boolean isOpen;

    private ItemStack chest;
    private final TitanBeetleContainer inventory = new TitanBeetleContainer(this);
    private NearestAttackableTargetGoal<Player> attackNearbyPlayersGoal;

    public TitanBeetle(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        super.tick();
        if(level().isClientSide()) {
            if(isDeadOrDying()) {
                inventory.forEach(stack -> drop(stack, true, false));
            }

            setPrevOpenTicks(getOpenTicks());
            if(isOpen()) {
                if(getOpenTicks() < 1) setOpenTicks(getOpenTicks() + 0.1F);
            } else {
                if(getOpenTicks() > 0) setOpenTicks(getOpenTicks() - 0.1F);
            }

            if(getOpenTicks() > 1) setOpenTicks(1);
            if(getOpenTicks() < 0) setOpenTicks(0);
        }

        if(!level().isClientSide() && hasEnderChest()) {
            double angle = Math.toRadians(yo);
            double xOff = -Math.sin(angle) * 0.5;
            double zOff = Math.cos(angle) * 0.5;
            BlockPos pos = blockPosition();
            ClientParticles.enderChestParticles(level(), pos.getX() + xOff, pos.getY() + 1.2F, pos.getZ() + zOff);
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        attackNearbyPlayersGoal = new NearestAttackableTargetGoal<>(this, Player.class, true);
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.5D, true));
        goalSelector.addGoal(2, new BreedGoal(this, 0.5D));
        goalSelector.addGoal(3, new TemptGoal(this, 0.5D, i -> i.is(ModItemTags.TITAN_BEETLE_FOOD), false));
        goalSelector.addGoal(4, new RandomStrollGoal(this, 0.5D));
        goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        targetSelector.addGoal(0, new HurtByTargetGoal(this));
        targetSelector.addGoal(1, attackNearbyPlayersGoal);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(@NonNull ServerLevel level, @NonNull AgeableMob partner) {
        BeetleLarva larva = ModEntities.BEETLE_LARVA.get().create(level, EntitySpawnReason.BREEDING);
        larva.setLarvaType((byte) 3);
        return larva;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder data) {
        super.defineSynchedData(data);
        data.define(BEETLE_TYPE, (byte) 0);
        data.define(OPEN_TICKS, 0F);
        data.define(PREV_OPEN_TICKS, 0F);
        data.define(HAS_CHEST, false);
        data.define(HAS_ENDER_CHEST, false);
        data.define(HAS_SADDLE, false);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 60F)
                .add(Attributes.MOVEMENT_SPEED, 0.6F)
                .add(Attributes.FOLLOW_RANGE, 16.0F)
                .add(Attributes.TEMPT_RANGE, 6.0F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.75F);
    }

    @Override
    public boolean isFood(@NonNull ItemStack stack) {
        return stack.is(ModItemTags.TITAN_BEETLE_FOOD);
    }

    @Override
    public void playAmbientSound() {
        playSound(ModSounds.BOMBARDIER_BEETLE_SOUND.get());
    }

    @Override
    protected void playHurtSound(@NonNull DamageSource source) {
        playSound(ModSounds.BOMBARDIER_BEETLE_HURT.get());
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
    public int getMaxSpawnClusterSize() {
        return 2;
    }

    @Override
    public boolean canBeLeashed() {
        return true;
    }

    @Override
    public @NonNull InteractionResult interact(@NonNull Player player, @NonNull InteractionHand hand, @NonNull Vec3 location) {
        ItemStack stack = player.getItemInHand(hand);

        if(hasChest() && player.isCrouching()) {
            setOpen(true);
            player.openMenu(new SimpleMenuProvider(
                    (id, playerInv, p) -> ChestMenu.threeRows(id, playerInv, inventory),
                    Component.translatable("container.erebus.titan_beetle_chest")
            ));
            return InteractionResult.SUCCESS;
        }

        if(hasEnderChest() && player.isCrouching()) {
            setOpen(true);
            player.openMenu(new SimpleMenuProvider(
                    (id, playerInv, p) -> ChestMenu.threeRows(id, playerInv, player.getEnderChestInventory()),
                    Component.translatable("container.enderchest")
            ));
            return InteractionResult.SUCCESS;
        }

        if(stack.isEmpty() && isTame()) {
            if(getOwner().is(player)) {
                player.startRiding(this);
                return InteractionResult.SUCCESS;
            }
        }

        if(!stack.isEmpty()) {
            if(stack.is(ModItems.BEETLE_TAMING_AMULET.get()) && !isTame()) {
                tame(player);
                spawnTamingParticles(true);
                player.swing(hand);
                targetSelector.removeGoal(attackNearbyPlayersGoal);
                setTarget(null);
                getAttribute(Attributes.MAX_HEALTH).setBaseValue(80.0);
                heal(20.0F);
                return InteractionResult.SUCCESS;
            }

            if(isTame()) {
                if (stack.is(ModItems.BEETLE_RIDING_KIT) && !hasSaddle()) {
                    setHasSaddle(true);
                    stack.shrink(1);
                    player.swing(hand);
                    return InteractionResult.SUCCESS;
                }

                if(stack.is(ModItems.TURNIP) && !isInLove()) {
                    setInLoveTime(600);
                    stack.shrink(1);
                    player.swing(hand);
                    playSound(ModSounds.BEETLE_LARVA_MUNCH.get(), 1.0F, 0.75F);
                    return InteractionResult.SUCCESS;
                }

                if(stack.is(ModItems.BAMBOO)) {
                    if(getHealth() < getMaxHealth()) {
                        heal(5.0F);
                        stack.shrink(1);
                        spawnTamingParticles(true);
                        player.swing(hand);
                        if(getHealth() == getMaxHealth()) {
                            playSound(ModSounds.BEETLE_LARVA_MUNCH.get(), 1.0F, 0.75F);
                        }
                    }
                    return InteractionResult.SUCCESS;
                }

                if(hasSaddle()) {
                    if(stack.is(ModItemTags.TITAN_BEETLE_CHESTS) && !hasChest()) {
                        setHasChest(true);
                        chest = stack.copy();
                        player.swing(hand);
                        playSound(SoundEvents.CHICKEN_EGG, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1F);
                    }

                    if(stack.is(Blocks.ENDER_CHEST.asItem()) && !hasEnderChest()) {
                        setHasEnderChest(true);
                        player.swing(hand);
                        playSound(SoundEvents.CHICKEN_EGG, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1F);
                    }
                }
            }
        }

        return super.interact(player, hand, location);
    }

    public void setOpen(boolean open) {
        if(level().isClientSide()) {
            if(open) {
                if(hasChest()) {
                    playSound(SoundEvents.CHEST_OPEN, 0.5F, 0.9F);
                } else {
                    playSound(SoundEvents.ENDER_CHEST_OPEN, 0.5F, random.nextFloat() * 0.1F + 0.95F);
                }
            } else {
                if(hasChest()) {
                    playSound(SoundEvents.CHEST_CLOSE, 0.5F, 0.9F);
                } else {
                    playSound(SoundEvents.ENDER_CHEST_CLOSE, 0.5F, random.nextFloat() * 0.1F + 0.95F);
                }
            }
        }

        isOpen = open;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpenTicks(float ticks) {
        entityData.set(OPEN_TICKS, ticks);
    }

    public float getOpenTicks() {
        return entityData.get(OPEN_TICKS);
    }

    public void setPrevOpenTicks(float ticks) {
        entityData.set(PREV_OPEN_TICKS, ticks);
    }

    public float getPrevOpenTicks() {
        return entityData.get(PREV_OPEN_TICKS);
    }

    public void setHasChest(boolean hasChest) {
        entityData.set(HAS_CHEST, hasChest);
    }

    public boolean hasChest() {
        return entityData.get(HAS_CHEST);
    }

    public void setHasEnderChest(boolean hasChest) {
        entityData.set(HAS_ENDER_CHEST, hasChest);
    }

    public boolean hasEnderChest() {
        return entityData.get(HAS_ENDER_CHEST);
    }

    public void setHasSaddle(boolean hasSaddle) {
        entityData.set(HAS_SADDLE, hasSaddle);
    }

    public boolean hasSaddle() {
        return entityData.get(HAS_SADDLE);
    }

    @Override
    protected @NonNull Vec3 getRiddenInput(@NonNull Player controller, @NonNull Vec3 selfInput) {
        if (this.onGround()) {
            return Vec3.ZERO;
        } else {
            float sideways = controller.xxa * 0.5F;
            float forward = controller.zza;
            if (forward <= 0.0F) {
                forward *= 0.25F;
            }

            return new Vec3(sideways, 0.0F, forward);
        }
    }

    @Override
    protected float getRiddenSpeed(@NonNull Player controller) {
        return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED);
    }

    @Override
    protected void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putByte("OpenTicks", (byte) getOpenTicks());
        output.putByte("PrevOpenTicks", (byte) getPrevOpenTicks());
        output.putBoolean("HasChest", hasChest());
        output.putBoolean("HasEnderChest", hasEnderChest());
        output.putBoolean("HasSaddle", hasSaddle());
        inventory.storeSlots(output.list("inventory", ItemStackWithSlot.CODEC));
    }

    @Override
    protected void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        setOpenTicks(input.getByteOr("OpenTicks", (byte) 0));
        setPrevOpenTicks(input.getByteOr("PrevOpenTicks", (byte) 0));
        setHasChest(input.getBooleanOr("HasChest", false));
        setHasEnderChest(input.getBooleanOr("HasEnderChest", false));
        setHasSaddle(input.getBooleanOr("HasSaddle", false));
        inventory.fromSlots(input.list("inventory", ItemStackWithSlot.CODEC).get());
    }
}
