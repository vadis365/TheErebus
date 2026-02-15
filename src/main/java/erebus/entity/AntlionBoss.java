package erebus.entity;

import erebus.client.particle.ClientParticleTypes;
import erebus.entity.ai.SandThrowAttackGoal;
import erebus.registries.ModSounds;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.Util;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class AntlionBoss extends Monster {

    private final ServerBossEvent bossEvent = Util.make(
            new ServerBossEvent(
                    Mth.createInsecureUUID(random),
                    getDisplayName(),
                    ServerBossEvent.BossBarColor.PURPLE,
                    ServerBossEvent.BossBarOverlay.PROGRESS
            ),
            e -> e.setDarkenScreen(true)
    );

    private static final EntityDataAccessor<BlockPos> SPAWN_ORIGIN = SynchedEntityData.defineId(AntlionBoss.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Byte> IN_PYRAMID = SynchedEntityData.defineId(AntlionBoss.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Byte> BLAM = SynchedEntityData.defineId(AntlionBoss.class, EntityDataSerializers.BYTE);

    private int blamCount;
    private int deathTicks;

    public AntlionBoss(EntityType<? extends AntlionBoss> type, Level level) {
        super(type, level);
        setHealth(getMaxHealth());
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new SandThrowAttackGoal(this));
        goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 16.0F));
        targetSelector.addGoal(0, new HurtByTargetGoal(this));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder data) {
        super.defineSynchedData(data);
        data.define(SPAWN_ORIGIN, BlockPos.ZERO);
        data.define(IN_PYRAMID, (byte) 0);
        data.define(BLAM, (byte) 3);
    }

    @Override
    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);
        bossEvent.setName(getDisplayName());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 400F)
                .add(Attributes.MOVEMENT_SPEED, 0.5F)
                .add(Attributes.ATTACK_DAMAGE, 6.0)
                .add(Attributes.FOLLOW_RANGE, 36.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0);
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return ModSounds.ANTLION_GROWL.get();
    }

    @Override
    protected @NonNull SoundEvent getHurtSound(@NonNull DamageSource source) {
        return ModSounds.ANTLION_GROWL.get();
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        bossEvent.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        bossEvent.removePlayer(player);
    }

    @Override
    public void tick() {
        super.tick();

        if(blamCount <= 75) {
            blamCount++;
        }

        if(blamCount == 5) {
            setBlam(5, (byte) 3);
        }

        if(blamCount < 75 && blamCount > 10) {
            if(getBlam() >= 1 && getBlam() <= 2) {
                setBlam(blamCount, (byte) 2);
            } else {
                setBlam(blamCount, (byte) 1);
                areaOfEffect();
            }
        }

        if(blamCount == 75) {
            setBlam(75, (byte) 3);
        }

        if(getBlam() == 1) {
            spawnRumbleParticles();
        }

        destroyBlocksInAABB(getBoundingBox());
    }

    @Override
    public void aiStep() {
        super.aiStep();
        bossEvent.setProgress(getHealth() / getMaxHealth());
    }

    @Override
    public boolean hurtServer(@NonNull ServerLevel level, DamageSource source, float damage) {
        if(source.is(DamageTypes.IN_WALL) || source.is(DamageTypes.DROWN) || !source.isDirect()) {
            return false;
        }
        return super.hurtServer(level, source, damage);
    }

    public void spawnBlamParticles() {
        if(level().isClientSide() && onGround()) {
            ClientParticleTypes.spawnParticles(ClientParticleTypes.ParticleType.ANTLION_BLAM, getX(), getY(), getZ(), 0D, 0D, 0D);
        }
    }

    public void spawnRumbleParticles() {
        if(level().isClientSide()) {
            ClientParticleTypes.spawnParticles(ClientParticleTypes.ParticleType.ANTLION_RUMBLE, getX(), getY(), getZ(), 0D, 0D, 0D);
        }
    }

    public void setBlam(int count, byte action) {
        blamCount = count;
        entityData.set(BLAM, action);
    }

    public byte getBlam() {
        return entityData.get(BLAM);
    }

    public void setInPyramid(byte state) {
        entityData.set(IN_PYRAMID, state);
    }

    public byte getInPyramid() {
        return entityData.get(IN_PYRAMID);
    }

    public BlockPos getSpawnOrigin() {
        return entityData.get(SPAWN_ORIGIN);
    }

    public void setSpawnOrigin(BlockPos pos) {
        entityData.set(SPAWN_ORIGIN, pos);
    }

    @Override
    protected void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.store("spawnOrigin", BlockPos.CODEC, getSpawnOrigin());
        output.putByte("inPyramid", getInPyramid());
    }

    @Override
    protected void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        setInPyramid(input.getByteOr("inPyramid", (byte) 0));
        setSpawnOrigin(input.read("spawnOrigin", BlockPos.CODEC).orElse(BlockPos.ZERO));
    }

    private void areaOfEffect() {
        List<Entity> entities = level().getEntities(this, getBoundingBox().inflate(16, 1, 16));
        for (Entity entity : entities) {
            if (entity instanceof LivingEntity target) {
                if (!level().isClientSide()) {
                    float knockback = (-3 + random.nextInt(4)) * 0.1F;
                    doHurtTarget((ServerLevel) level(), target);
                    target.push(-Mth.sin(getYRot() * -Mth.PI + random.nextInt(3) + 0.141593F / 180.0F) * knockback, 0.01D, Mth.cos(getYRot() * -Mth.PI + random.nextInt(3) + 0.141593F / 180.0F) * knockback);
                }
                level().playSound(this, getOnPos(), ModSounds.ANTLION_SLAM.get(), SoundSource.HOSTILE);
            }
        }
    }

    private void destroyBlocksInAABB(AABB box) {
        if (level().isClientSide())
            return;
        int minX = Mth.floor(box.minX - 1);
        int minY = Mth.floor(box.minY - 0.2);
        int minZ = Mth.floor(box.minZ - 1);
        int maxX = Mth.floor(box.maxX + 1);
        int maxY = Mth.floor(box.maxY);
        int maxZ = Mth.floor(box.maxZ + 1);

        for (int x = minX; x <= maxX; ++x) {
            for (int y = minY; y <= maxY; ++y) {
                for (int z = minZ; z <= maxZ; ++z) {
                    BlockPos pos = new BlockPos(x, y, z);
                    Block block = level().getBlockState(pos).getBlock();
                    Block blockBelow = level().getBlockState(pos.below()).getBlock();
                    if (block == Blocks.SAND && blockBelow != ModBlocks.TEMPLE_BRICK_UNBREAKING.get()) {
                        level().setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                        level().playSound(null, pos, SoundEvents.GENERIC_EXPLODE.value(), SoundSource.HOSTILE);
                    }
                }
            }
        }
    }
}
