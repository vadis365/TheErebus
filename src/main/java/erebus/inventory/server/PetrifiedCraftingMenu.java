package erebus.inventory.server;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.client.ModMenuTypes;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Optional;

public class PetrifiedCraftingMenu extends AbstractCraftingMenu {

    private static final int CRAFTING_GRID_WIDTH = 3;
    private static final int CRAFTING_GRID_HEIGHT = 3;
    private static final int RESULT_SLOT = 0;
    private static final int CRAFT_SLOT_START = 1;
    private static final int CRAFT_SLOT_END = 10;
    private static final int INV_SLOT_START = 10;
    private static final int INV_SLOT_END = 37;
    private static final int USE_ROW_SLOT_START = 37;
    private static final int USE_ROW_SLOT_END = 46;
    private final ContainerLevelAccess access;
    private final Player player;
    private boolean placingRecipe;

    public PetrifiedCraftingMenu(int containerID, Inventory inventory) {
        this(containerID, inventory, ContainerLevelAccess.NULL);
    }

    public PetrifiedCraftingMenu(int containerID, Inventory inventory, ContainerLevelAccess access) {
        super(ModMenuTypes.PETRIFIED_CRAFTING_MENU.get(), containerID, CRAFTING_GRID_WIDTH, CRAFTING_GRID_HEIGHT);
        this.access = access;
        this.player = inventory.player;
        this.addResultSlot(player, 124, 35);
        this.addCraftingGridSlots(30, 17);
        this.addStandardInventorySlots(inventory, 8, 84);
    }

    protected static void slotChangedCraftingGrid(AbstractContainerMenu menu, ServerLevel level, Player player, CraftingContainer container, ResultContainer resultSlots, RecipeHolder<CraftingRecipe> recipeHint) {
        CraftingInput input = container.asCraftInput();
        ServerPlayer serverPlayer = (ServerPlayer) player;
        ItemStack result = ItemStack.EMPTY;
        Optional<RecipeHolder<CraftingRecipe>> recipeLookup = level.getServer().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, input, level, recipeHint);

        if(recipeLookup.isPresent()) {
            RecipeHolder<CraftingRecipe> recipeHolder = recipeLookup.get();
            CraftingRecipe recipe = recipeHolder.value();

            if(resultSlots.setRecipeUsed(serverPlayer, recipeHolder)) {
                ItemStack recipeResult = recipe.assemble(input, level.registryAccess());
                if(recipeResult.isItemEnabled(level.enabledFeatures())) {
                    result = recipeResult;
                }
            }
        }

        resultSlots.setItem(RESULT_SLOT, result);
        menu.setRemoteSlot(RESULT_SLOT, result);
        serverPlayer.connection.send(new ClientboundContainerSetSlotPacket(menu.containerId, menu.incrementStateId(), RESULT_SLOT, result));
    }

    @Override
    public void slotsChanged(@NonNull Container container) {
        if(!placingRecipe) {
            access.execute((level, _) -> {
                if(level instanceof ServerLevel serverLevel) {
                    slotChangedCraftingGrid(this, serverLevel, player, craftSlots, resultSlots, null);
                }
            });
        }
    }

    public void beginPlacingRecipe() {
        placingRecipe = true;
    }

    public void finishPlacingRecipe(@NonNull ServerLevel level, @NonNull RecipeHolder<CraftingRecipe> recipe) {
        placingRecipe = false;
        slotChangedCraftingGrid(this, level, player, craftSlots, resultSlots, recipe);
    }

    public boolean canTakeItemForPickAll(@NonNull ItemStack carried, Slot target) {
        return target.container != resultSlots && super.canTakeItemForPickAll(carried, target);
    }

    @Override
    public void removed(@NonNull Player player) {
        super.removed(player);
        access.execute((_, _) -> clearContainer(player, craftSlots));
    }

    @Override
    public @NonNull Slot getResultSlot() {
        return this.slots.get(RESULT_SLOT);
    }

    @Override
    public @NonNull List<Slot> getInputGridSlots() {
        return slots.subList(CRAFT_SLOT_START, CRAFT_SLOT_END);
    }

    @Override
    protected @NonNull Player owner() {
        return player;
    }

    @Override
    public @NonNull RecipeBookType getRecipeBookType() {
        return RecipeBookType.CRAFTING;
    }

    @Override
    public @NonNull ItemStack quickMoveStack(@NonNull Player player, int index) {
        ItemStack clicked = ItemStack.EMPTY;
        Slot slot = slots.get(index);

        if(slot.hasItem()) {
            ItemStack stack = slot.getItem();
            clicked = stack.copy();

            if(index == RESULT_SLOT) {
                stack.getItem().onCraftedBy(stack, player);
                if(!moveItemStackTo(stack, INV_SLOT_START, USE_ROW_SLOT_END, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(stack, clicked);
            } else if(index >= INV_SLOT_START && index < USE_ROW_SLOT_END) {
                if (!moveItemStackTo(stack, CRAFT_SLOT_START, CRAFT_SLOT_END, false)) {
                    if(index < INV_SLOT_END) {
                        if(!moveItemStackTo(stack, USE_ROW_SLOT_START, USE_ROW_SLOT_END, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if(!moveItemStackTo(stack, INV_SLOT_START, INV_SLOT_END, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!moveItemStackTo(stack, INV_SLOT_START, USE_ROW_SLOT_END, false)) {
                return ItemStack.EMPTY;
            }

            if(stack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if(stack.getCount() == clicked.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);

            if(index == RESULT_SLOT) {
                player.drop(stack, false);
            }
        }

        return clicked;
    }

    @Override
    public boolean stillValid(@NonNull Player player) {
        return stillValid(access, player, ModBlocks.PETRIFIED_CRAFTING_TABLE.get());
    }
}
