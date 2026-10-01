package erebus.entity;

import erebus.entity.ai.AnimatedBlockFollowOwnerGoal;
import erebus.inventory.server.BambooCrateMenu;
import erebus.registries.ModSounds;
import erebus.registries.item.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

public abstract class AnimatedContainer extends AnimatedBlock implements Container, MenuProvider {
    private final NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);

    public AnimatedContainer(EntityType<? extends AnimatedContainer> type, Level level) {
        super(type, level);
        setPersistenceRequired();
        setInvulnerable(true);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new AnimatedBlockFollowOwnerGoal(this));
    }

    @Override
    public boolean removeWhenFarAway(double distance) {
        return false;
    }

    protected void copyContents(Container source) {
        for (int slot = 0; slot < items.size(); slot++) items.set(slot, source.getItem(slot).copy());
    }

    @Override
    public @NonNull InteractionResult mobInteract(Player player, @NonNull InteractionHand hand) {
        var held = player.getItemInHand(hand);
        if (held.is(ModItems.WAND_OF_ANIMATION)) {
            if (level().isClientSide()) return InteractionResult.SUCCESS;
            var pos = blockPosition();
            var replaced = level().getBlockState(pos);
            if (!level().getWorldBorder().isWithinBounds(pos) || !level().mayInteract(player, pos)
                    || !player.mayUseItemAt(pos, Direction.UP, held) || !replaced.canBeReplaced() || replaced.hasBlockEntity()) return InteractionResult.FAIL;
            if (!level().setBlock(pos, reversionState(), 3)) return InteractionResult.FAIL;
            closeMenus();
            if (!restoreContents(level().getBlockEntity(pos))) {
                level().setBlock(pos, replaced, 3);
                return InteractionResult.FAIL;
            }
            clearContent();
            discard();
            level().playSound(null, pos, ModSounds.ALTAR_OFFERING.get(), SoundSource.BLOCKS, 0.2F, 1);
            return InteractionResult.SUCCESS_SERVER;
        }
        if (!level().isClientSide()) player.openMenu(this);
        return InteractionResult.SUCCESS;
    }

    protected BlockState reversionState() {
        return getBlockType();
    }

    protected boolean restoreContents(BlockEntity destination) {
        if (!(destination instanceof Container container) || container.getContainerSize() != items.size()) return false;
        for (int slot = 0; slot < items.size(); slot++) container.setItem(slot, items.get(slot));
        return true;
    }

    private void closeMenus() {
        if (level() instanceof ServerLevel level) {
            for (var player : level.players()) {
                if ((player.containerMenu instanceof BambooCrateMenu menu && menu.crate == this)
                        || (player.containerMenu instanceof ChestMenu chest && chest.getContainer() == this)) player.closeContainer();
            }
        }
    }

    @Override
    public int getContainerSize() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public @NonNull ItemStack getItem(int slot) {
        return items.get(slot);
    }

    @Override
    public @NonNull ItemStack removeItem(int slot, int count) {
        return ContainerHelper.removeItem(items, slot, count);
    }

    @Override
    public @NonNull ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(items, slot);
    }

    @Override
    public void setItem(int slot, @NonNull ItemStack stack) {
        items.set(slot, stack);
        stack.limitSize(getMaxStackSize(stack));
    }

    @Override
    public void setChanged() {
    }

    @Override
    public boolean stillValid(@NonNull Player player) {
        return isAlive() && player.level() == level() && distanceToSqr(player) <= 64;
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    @Override
    protected void dropEquipment(@NonNull ServerLevel level) {
        super.dropEquipment(level);
        closeMenus();
        for (var stack : items) if (!stack.isEmpty()) spawnAtLocation(level, stack.copy());
        clearContent();
    }

    @Override
    protected void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        ContainerHelper.saveAllItems(output, items, false);
    }

    @Override
    public void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        clearContent();
        ContainerHelper.loadAllItems(input, items);
    }
}
