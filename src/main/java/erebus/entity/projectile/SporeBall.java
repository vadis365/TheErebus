package erebus.entity.projectile;

import erebus.client.particle.ClientParticles;
import erebus.entity.Crushroom;
import erebus.registries.item.ModItems;
import net.minecraft.network.protocol.game.ClientboundSetPlayerInventoryPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class SporeBall extends ThrowableProjectile {
    private static final EntityDataAccessor<Optional<EntityReference<LivingEntity>>> HOST = SynchedEntityData.defineId(SporeBall.class, EntityDataSerializers.OPTIONAL_LIVING_ENTITY_REFERENCE);
    private int remaining = 200;

    public SporeBall(EntityType<? extends SporeBall> type, Level level) {
        super(type, level);
    }

    private static boolean protectedPlayer(Player player) {
        return !player.isAlive() || player.isCreative() || player.isSpectator()
                || player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.MUSHROOM_HELMET);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(HOST, Optional.empty());
    }

    public Player getHost() {
        var host = entityData.get(HOST).map(reference -> reference.getEntity(level(), LivingEntity.class)).orElse(null);
        return host instanceof Player player ? player : null;
    }

    @Override
    public void tick() {
        if (entityData.get(HOST).isPresent()) {
            baseTick();
            var player = getHost();
            if (player == null || player.level() != level() || protectedPlayer(player)) {
                if (!level().isClientSide()) discard();
                return;
            }
            setDeltaMovement(Vec3.ZERO);
            setPos(player.getX(), player.getY() + 1, player.getZ());
            if (player instanceof ServerPlayer serverPlayer && !serverPlayer.getMainHandItem().isEmpty()) {
                int slot = serverPlayer.getInventory().getSelectedSlot();
                ItemStack before = serverPlayer.getMainHandItem().copy();
                serverPlayer.drop(true);
                ItemStack after = serverPlayer.getInventory().getItem(slot);
                // Native drop assumes client prediction and marks the remote slot current without sending it.
                if (!ItemStack.matches(before, after)) {
                    serverPlayer.connection.send(new ClientboundSetPlayerInventoryPacket(slot, after.copy()));
                }
            }
        } else super.tick();
        if (!level().isClientSide() && --remaining <= 0) discard();
        if (level().isClientSide()) for (int i = 0; i < 2; i++)
            ClientParticles.spawnParticles(ClientParticles.ParticleType.SPELL, getRandomX(0.7), getRandomY(), getRandomZ(0.7), 0, 0.02, 0);
    }

    @Override
    protected void onHit(@NonNull HitResult result) {
        if (!(level() instanceof ServerLevel server) || isRemoved() || result.getType() == HitResult.Type.MISS) return;
        if (result instanceof EntityHitResult hit) {
            if (hit.getEntity() instanceof Crushroom) return;
            if (hit.getEntity() instanceof Player player) {
                if (protectedPlayer(player)) {
                    discard();
                    return;
                }
                boolean attached = !level().getEntitiesOfClass(SporeBall.class, player.getBoundingBox().inflate(2),
                        cloud -> cloud != this && cloud.getHost() == player).isEmpty();
                if (attached) {
                    discard();
                    return;
                }
                entityData.set(HOST, Optional.of(EntityReference.of(player)));
                remaining = 140;
                setNoGravity(true);
                setDeltaMovement(Vec3.ZERO);
                return;
            }
            if (hit.getEntity() instanceof LivingEntity target) {
                target.addEffect(new MobEffectInstance(MobEffects.POISON, 100), getOwner());
                target.hurtServer(server, getOwner() instanceof LivingEntity owner ? damageSources().mobAttack(owner) : damageSources().magic(), 1);
            }
        }
        discard();
    }

    @Override
    protected void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        EntityReference.store(entityData.get(HOST).orElse(null), output, "Host");
        output.putInt("Remaining", remaining);
    }

    @Override
    protected void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        entityData.set(HOST, Optional.ofNullable(EntityReference.readWithOldOwnerConversion(input, "Host", level())));
        remaining = Math.clamp(input.getIntOr("Remaining", 200), 0, entityData.get(HOST).isPresent() ? 140 : 200);
    }
}
