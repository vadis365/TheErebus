package erebus.entity;

import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
import erebus.registries.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.LockCode;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AnimatedChest extends AnimatedContainer {
    private static final EntityDataAccessor<Boolean> OPEN = SynchedEntityData.defineId(AnimatedChest.class, EntityDataSerializers.BOOLEAN);
    private final Set<UUID> viewers = new HashSet<>();
    private CompoundTag chestData = new CompoundTag();
    private LockCode lock = LockCode.NO_LOCK;
    private float openness, previousOpenness;

    public AnimatedChest(EntityType<? extends AnimatedChest> type, Level level) {
        super(type, level);
        setBlockType(Blocks.CHEST.defaultBlockState());
    }

    public static InteractionResult animate(UseOnContext context) {
        var level = context.getLevel();
        var pos = context.getClickedPos();
        var player = context.getPlayer();
        var state = level.getBlockState(pos);
        if (player == null || !state.is(Blocks.CHEST) || !(level.getBlockEntity(pos) instanceof ChestBlockEntity source)) return InteractionResult.PASS;
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        if (!source.canOpen(player)) {
            BaseContainerBlockEntity.sendChestLockedNotifications(pos.getCenter(), player, source.getDisplayName());
            return InteractionResult.FAIL;
        }
        var chest = ModEntities.ANIMATED_CHEST.get().create(level, EntitySpawnReason.TRIGGERED);
        if (chest == null) return InteractionResult.FAIL;
        chest.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        chest.setBlockType(state);
        chest.setOwnerId(player.getUUID());
        if (!level.addFreshEntity(chest)) return InteractionResult.FAIL;
        if (level.getBlockEntity(pos) != source || !level.getBlockState(pos).equals(state)) {
            chest.discard();
            return InteractionResult.FAIL;
        }
        source.unpackLootTable(player);
        chest.chestData = source.saveWithoutMetadata(level.registryAccess());
        chest.chestData.remove("Items");
        chest.lock = LockCode.fromTag(TagValueInput.create(ProblemReporter.DISCARDING, level.registryAccess(), chest.chestData));
        chest.setCustomName(source.getCustomName());
        chest.copyContents(source);
        source.clearContent();
        if (!level.setBlock(pos, state.getFluidState().createLegacyBlock(), 3)) {
            for (int slot = 0; slot < chest.getContainerSize(); slot++) source.setItem(slot, chest.getItem(slot));
            chest.clearContent();
            chest.discard();
            return InteractionResult.FAIL;
        }
        var hand = context.getHand();
        context.getItemInHand().hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        level.playSound(null, pos, ModSounds.ALTAR_OFFERING.get(), SoundSource.BLOCKS, 0.2F, 1);
        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(OPEN, false);
    }

    @Override
    public void setBlockType(BlockState state) {
        super.setBlockType((state.is(Blocks.CHEST) ? state : Blocks.CHEST.defaultBlockState()).setValue(ChestBlock.TYPE, ChestType.SINGLE));
    }

    @Override
    protected BlockState reversionState() {
        return getBlockType().setValue(ChestBlock.WATERLOGGED, level().getFluidState(blockPosition()).is(FluidTags.WATER));
    }

    @Override
    protected boolean restoreContents(BlockEntity destination) {
        if (!(destination instanceof ChestBlockEntity chest)) return false;
        var restored = chestData.copy();
        if (getCustomName() == null) restored.remove("CustomName");
        else restored.store("CustomName", ComponentSerialization.CODEC, level().registryAccess().createSerializationContext(NbtOps.INSTANCE), getCustomName());
        chest.loadWithComponents(TagValueInput.create(ProblemReporter.DISCARDING, level().registryAccess(), restored));
        return super.restoreContents(chest);
    }

    @Override
    public @NonNull InteractionResult mobInteract(Player player, @NonNull InteractionHand hand) {
        if (player.getItemInHand(hand).is(ModItems.WAND_OF_ANIMATION)) return super.mobInteract(player, hand);
        if (!player.getItemInHand(hand).isEmpty() && lock.equals(LockCode.NO_LOCK)) return InteractionResult.PASS;
        if (!level().isClientSide() && !lock.canUnlock(player)) {
            BaseContainerBlockEntity.sendChestLockedNotifications(position(), player, getDisplayName());
            return InteractionResult.FAIL;
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public AbstractContainerMenu createMenu(int id, @NonNull Inventory inventory, @NonNull Player player) {
        return lock.canUnlock(player) ? ChestMenu.threeRows(id, inventory, this) : null;
    }

    @Override
    public void startOpen(@NonNull ContainerUser user) {
        if (user instanceof Player player && !player.isSpectator() && !level().isClientSide()) {
            viewers.add(player.getUUID());
            updateOpen();
        }
    }

    @Override
    public void stopOpen(@NonNull ContainerUser user) {
        if (user instanceof Player player && !level().isClientSide()) {
            viewers.remove(player.getUUID());
            updateOpen();
        }
    }

    private void updateOpen() {
        boolean open = !viewers.isEmpty();
        if (entityData.get(OPEN) != open) {
            entityData.set(OPEN, open);
            playSound(open ? SoundEvents.CHEST_OPEN : SoundEvents.CHEST_CLOSE, 0.5F, 0.9F);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide()) {
            viewers.removeIf(id -> !(level().getPlayerByUUID(id) instanceof ServerPlayer player)
                    || !(player.containerMenu instanceof ChestMenu menu) || menu.getContainer() != this);
            updateOpen();
        }
        previousOpenness = openness;
        openness = Mth.clamp(openness + (entityData.get(OPEN) ? 0.1F : -0.1F), 0, 1);
    }

    public float getOpenness(float partialTick) {
        return Mth.lerp(partialTick, previousOpenness, openness);
    }

    public boolean isOpen() {
        return entityData.get(OPEN);
    }

    @Override
    protected void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.store("ChestData", CompoundTag.CODEC, chestData);
    }

    @Override
    public void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        chestData = input.read("ChestData", CompoundTag.CODEC).orElseGet(CompoundTag::new);
        lock = LockCode.fromTag(TagValueInput.create(ProblemReporter.DISCARDING, level().registryAccess(), chestData));
    }
}
