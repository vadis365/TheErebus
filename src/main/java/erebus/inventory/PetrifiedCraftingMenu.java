package erebus.inventory;

import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.client.ModMenuTypes;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Optional;

public class PetrifiedCraftingMenu extends RecipeBookMenu<CraftingInput, CraftingRecipe> {
    public static final int RESULT_SLOT = 0;
    private static final int CRAFT_SLOT_START = 1;
    private static final int CRAFT_SLOT_END = 10;
    private static final int INV_SLOT_START = 10;
    private static final int INV_SLOT_END = 37;
    private static final int USE_ROW_SLOT_START = 37;
    private static final int USE_ROW_SLOT_END = 46;
    private final CraftingContainer craftSlots;
    private final ResultContainer resultSlots;
    private final ContainerLevelAccess access;
    private final Player player;
    private boolean placingRecipe;

    public PetrifiedCraftingMenu(int id, Inventory inv) {
        this(id, inv, ContainerLevelAccess.NULL);
    }

    public PetrifiedCraftingMenu(int containerId, Inventory inv, ContainerLevelAccess access) {
        super(ModMenuTypes.PETRIFIED_CRAFTING_MENU.get(), containerId);
        craftSlots = new TransientCraftingContainer(this, 3, 3);
        resultSlots = new ResultContainer();
        this.access = access;
        player = inv.player;
        addSlot(new ResultSlot(player, craftSlots, resultSlots, 0, 124, 35));

        addCraftingGridSlots(inv);
        addInventorySlots(inv);
        addHotbarSlots(inv);
    }

    private void addCraftingGridSlots(Inventory inv) {
        for(int c = 0; c < 3; ++c) {
            for(int d = 0; d < 3; ++d) {
                addSlot(new Slot(craftSlots, d + c * 3, 30 + d * 18, 17 + c * 18));
            }
        }
    }

    private void addInventorySlots(Inventory inv) {
        for (int c = 0; c < 3; c++) {
            for (int d = 0; d < 9; d++) {
                addSlot(new Slot(inv, d + c * 9 + 9, 8 + d * 18, 84 + c * 18));
            }
        }
    }

    private void addHotbarSlots(Inventory inv) {
        for (int c = 0; c < 9; c++) {
            addSlot(new Slot(inv, c, 8 + c * 18, 142));
        }
    }

    protected static void slotChangedCraftingGrid(AbstractContainerMenu menu, Level level, Player player, CraftingContainer craftSlots, ResultContainer resultSlots, @Nullable RecipeHolder<CraftingRecipe> recipe) {
        if(!level.isClientSide) {
            CraftingInput craftingInput = craftSlots.asCraftInput();
            ServerPlayer serverPlayer = (ServerPlayer) player;
            ItemStack result = ItemStack.EMPTY;
            Optional<RecipeHolder<CraftingRecipe>> optional = level.getServer().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, craftingInput, level, recipe);

            if(optional.isPresent()) {
                RecipeHolder<CraftingRecipe> holder = optional.get();
                CraftingRecipe craftingRecipe = holder.value();

                if(resultSlots.setRecipeUsed(level, serverPlayer, holder)) {
                    ItemStack stack = craftingRecipe.assemble(craftingInput, level.registryAccess());
                    if(stack.isItemEnabled(level.enabledFeatures())) {
                        result = stack;
                    }
                }
            }

            resultSlots.setItem(0, result);
            menu.setRemoteSlot(0, result);
            serverPlayer.connection.send(new ClientboundContainerSetSlotPacket(menu.containerId, menu.incrementStateId(), 0, result));
        }
    }

    @Override
    public void slotsChanged(Container container) {
        if(!placingRecipe) {
            access.execute(((level, pos) -> slotChangedCraftingGrid(this, level, player, craftSlots, resultSlots, null)));
        }
    }

    public void beginPlacingRecipe() {
        placingRecipe = true;
    }

    public void finishPlacingRecipe(RecipeHolder<CraftingRecipe> recipe) {
        placingRecipe = false;
        access.execute((level, pos) -> slotChangedCraftingGrid(this, level, player, craftSlots, resultSlots, recipe));
    }

    public void fillCraftSlotsStackedContents(StackedContents stackedContents) {
        craftSlots.fillStackedContents(stackedContents);
    }

    public void clearCraftingContent() {
        craftSlots.clearContent();
        resultSlots.clearContent();
    }

    public boolean recipeMatches(RecipeHolder<CraftingRecipe> recipe) {
        return recipe.value().matches(craftSlots.asCraftInput(), player.level());
    }

    public void removed(Player player) {
        super.removed(player);
        access.execute(((level, blockPos) -> clearContainer(player, craftSlots)));
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(access, player, OtherBlocks.PETRIFIED_CRAFTING_TABLE.get());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = slots.get(i);

        if(slot != null && slot.hasItem()) {
            ItemStack item = slot.getItem();
            stack = item.copy();

            if(i == 0) {
                access.execute(((level, blockPos) -> item.getItem().onCraftedBy(item, level, player)));
                if(!moveItemStackTo(item, INV_SLOT_START, USE_ROW_SLOT_END, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(item, stack);
            } else if(i >= INV_SLOT_START && i < USE_ROW_SLOT_END) {
                if(!moveItemStackTo(item, CRAFT_SLOT_START, CRAFT_SLOT_END, false)) {
                    if(i < USE_ROW_SLOT_START) {
                        if(!moveItemStackTo(item, USE_ROW_SLOT_START, USE_ROW_SLOT_END, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if(!moveItemStackTo(item, INV_SLOT_START, INV_SLOT_END, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if(!moveItemStackTo(item, INV_SLOT_START, USE_ROW_SLOT_END, false)) {
                return ItemStack.EMPTY;
            }

            if(item.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if(item.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, item);

            if(i == 0) {
                player.drop(item, false);
            }
        }

        return stack;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != resultSlots && super.canTakeItemForPickAll(stack, slot);
    }

    public int getResultSlotIndex() {
        return RESULT_SLOT;
    }

    public int getGridWidth() {
        return craftSlots.getWidth();
    }

    public int getGridHeight() {
        return craftSlots.getHeight();
    }

    public int getSize() {
        return 10;
    }

    public RecipeBookType getRecipeBookType() {
        return RecipeBookType.CRAFTING;
    }

    public boolean shouldMoveToInventory(int index) {
        return index != getResultSlotIndex();
    }
}
