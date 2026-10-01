package erebus.entity;

import erebus.entity.ai.AnimatedBlockFollowOwnerGoal;
import erebus.inventory.server.PetrifiedCraftingMenu;
import erebus.registries.ModSounds;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nullable;
import java.util.UUID;

public class AnimatedBlock extends PathfinderMob {

    private static final EntityDataAccessor<BlockState> BLOCK_TYPE = SynchedEntityData.defineId(AnimatedBlock.class, EntityDataSerializers.BLOCK_STATE);
    private @Nullable UUID ownerId;

    public AnimatedBlock(EntityType<? extends AnimatedBlock> type, Level level) {
        super(type, level);
        xpReward = 0;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 10D)
                .add(Attributes.FOLLOW_RANGE, 16D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.ATTACK_DAMAGE, 2D);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(BLOCK_TYPE, Blocks.STONE.defaultBlockState());
    }

    public BlockState getBlockType() {
        return entityData.get(BLOCK_TYPE);
    }

    public void setBlockType(BlockState state) {
        entityData.set(BLOCK_TYPE, state);
    }

    public @Nullable UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(@Nullable UUID ownerId) {
        this.ownerId = ownerId;
    }

    public @Nullable Player getOwner() {
        return ownerId == null ? null : level().getPlayerByUUID(ownerId);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new AnimatedBlockFollowOwnerGoal(this));
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.5D, false));
        goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.5D, 1));
        targetSelector.addGoal(0, new HurtByTargetGoal(this));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Monster.class, true, true));
    }

    @Override
    protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState state) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

    @Override
    public @NonNull InteractionResult mobInteract(Player player, @NonNull InteractionHand hand) {
        ItemStack is = player.getItemInHand(hand);
        if (is.is(ModItems.WAND_OF_ANIMATION.get())) {
            if (level().isClientSide()) return InteractionResult.SUCCESS;
            BlockPos pos = blockPosition();
            if (!level().getWorldBorder().isWithinBounds(pos)) return InteractionResult.FAIL;
            if (!level().mayInteract(player, pos) || !player.mayUseItemAt(pos, Direction.UP, is)) return InteractionResult.FAIL;
            BlockState replacedState = level().getBlockState(pos);
            if (!replacedState.canBeReplaced() || replacedState.hasBlockEntity() || !getBlockType().canSurvive(level(), pos)) return InteractionResult.FAIL;
            if (!level().setBlock(pos, getBlockType(), 3)) return InteractionResult.FAIL;
            discard();
            level().playSound(null, blockPosition(), ModSounds.ALTAR_OFFERING.get(), SoundSource.NEUTRAL, 0.2F, 1.0F);
            return InteractionResult.SUCCESS;
        } else if (getBlockType().is(ModBlocks.PETRIFIED_CRAFTING_TABLE.get()) && is.isEmpty()) {
            if (!level().isClientSide()) {
                player.openMenu(new SimpleMenuProvider(
                        (id, inventory, _) -> new PetrifiedCraftingMenu(id, inventory, this),
                        Component.translatable("erebus.container.petrified_crafting_table")));
            }
            return InteractionResult.SUCCESS;
        } else
            return super.mobInteract(player, hand);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(@NonNull ServerLevelAccessor level, @NonNull DifficultyInstance difficulty, @NonNull EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
        if (spawnReason == EntitySpawnReason.COMMAND || spawnReason == EntitySpawnReason.SPAWN_ITEM_USE || spawnReason == EntitySpawnReason.SPAWNER || spawnReason == EntitySpawnReason.DISPENSER)
            setBlockType(level.getBlockState(blockPosition().below()));
        return super.finalizeSpawn(level, difficulty, spawnReason, groupData);
    }

    @Override
    protected void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.store("BlockState", BlockState.CODEC, getBlockType());
        if (ownerId != null) output.store("OwnerUUID", UUIDUtil.CODEC, ownerId);
    }

    @Override
    public void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        setBlockType(input.read("BlockState", BlockState.CODEC).orElse(Blocks.STONE.defaultBlockState()));
        ownerId = input.read("OwnerUUID", UUIDUtil.CODEC).orElse(null);
    }
}
