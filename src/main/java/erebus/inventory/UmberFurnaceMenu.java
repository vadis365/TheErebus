package erebus.inventory;

import erebus.client.screen.slot.UmberFurnaceFuelSlot;
import erebus.registries.ModMenuTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;

public class UmberFurnaceMenu extends RecipeBookMenu<SingleRecipeInput, AbstractCookingRecipe> {

    public static final int INGREDIENT_SLOT = 0;
    public static final int FUEL_SLOT = 1;
    public static final int RESULT_SLOT = 2;
    public static final int SLOT_COUNT = 3;
    public static final int DATA_COUNT = 4;
    private static final int INV_SLOT_START = 3;
    private static final int INV_SLOT_END = 30;
    private static final int HOTBAR_SLOT_START = 30;
    private static final int HOTBAR_SLOT_END = 39;
    private final Container container;
    private final ContainerData data;
    protected final Level level;
    private final RecipeType<? extends AbstractCookingRecipe> recipeType;
    private final RecipeBookType recipeBookType;

    public UmberFurnaceMenu(int id, Inventory inv) {
        this(id, inv, new SimpleContainer(SLOT_COUNT), new SimpleContainerData(DATA_COUNT));
    }

    public UmberFurnaceMenu(int id, Inventory inv, Container container, ContainerData data) {
        super(ModMenuTypes.UMBER_FURNACE_MENU.get(), id);
        recipeType = RecipeType.SMELTING;
        recipeBookType = RecipeBookType.FURNACE;
        checkContainerSize(container, SLOT_COUNT);
        checkContainerDataCount(data, DATA_COUNT);
        this.container = container;
        this.data = data;
        this.level = inv.player.level();
        addSlot(new Slot(container, 0, 56, 17));
        addSlot(new UmberFurnaceFuelSlot(this, container, 1, 56, 53));
        addSlot(new FurnaceResultSlot(inv.player, container, 2, 116, 35));
        addInventorySlots(inv);
        addHotbarSlots(inv);
        addDataSlots(data);
    }

    private void addInventorySlots(Inventory inv) {
        for(int c = 0; c < 3; c++) {
            for(int d = 0; d < 9; d++) {
                addSlot(new Slot(inv, d + c * 9 + 9, 8 + d * 18, 84 + c * 18));
            }
        }
    }

    private void addHotbarSlots(Inventory inv) {
        for(int c = 0; c < 9; c++) {
            addSlot(new Slot(inv, c, 8 + c * 18, 142));
        }
    }

    @Override
    public void fillCraftSlotsStackedContents(StackedContents contents) {
        if(contents instanceof StackedContentsCompatible) {
            ((StackedContentsCompatible) contents).fillStackedContents(contents);
        }
    }

    @Override
    public void clearCraftingContent() {
        getSlot(INGREDIENT_SLOT).set(ItemStack.EMPTY);
        getSlot(RESULT_SLOT).set(ItemStack.EMPTY);
    }

    @Override
    public boolean recipeMatches(RecipeHolder<AbstractCookingRecipe> recipe) {
        return recipe.value().matches(new SingleRecipeInput(container.getItem(INGREDIENT_SLOT)), level);
    }

    @Override
    public int getResultSlotIndex() {
        return RESULT_SLOT;
    }

    @Override
    public int getGridWidth() {
        return 1;
    }

    @Override
    public int getGridHeight() {
        return 1;
    }

    @Override
    public int getSize() {
        return SLOT_COUNT;
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return recipeBookType;
    }

    @Override
    public boolean shouldMoveToInventory(int index) {
        return index != FUEL_SLOT;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if(slot != null && slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            stack = stackInSlot.copy();

            if(index == RESULT_SLOT) {
                if(!moveItemStackTo(stackInSlot, INV_SLOT_START, HOTBAR_SLOT_END, true)) {
                    return ItemStack.EMPTY;
                }
            }

            if(index != FUEL_SLOT && index != INGREDIENT_SLOT) {
                if(canSmelt(stackInSlot)) {
                    if (!moveItemStackTo(stackInSlot, INGREDIENT_SLOT, FUEL_SLOT, false)) return ItemStack.EMPTY;
                }

                if(isFuel(stackInSlot)) {
                    if (!moveItemStackTo(stackInSlot, FUEL_SLOT, RESULT_SLOT, false)) return ItemStack.EMPTY;
                }

                if(index >= INV_SLOT_START && index < INV_SLOT_END) {
                    if (!moveItemStackTo(stackInSlot, HOTBAR_SLOT_START, HOTBAR_SLOT_END, false)) return ItemStack.EMPTY;
                }

                if(index >= HOTBAR_SLOT_START && index < HOTBAR_SLOT_END) {
                    if (!moveItemStackTo(stackInSlot, INV_SLOT_START, INV_SLOT_END, false)) return ItemStack.EMPTY;
                }
            }

            if (!moveItemStackTo(stackInSlot, INV_SLOT_START, HOTBAR_SLOT_END, false)) return ItemStack.EMPTY;

            if(stackInSlot.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if(stackInSlot.getCount() == stack.getCount()) return ItemStack.EMPTY;

            slot.onTake(player, stack);
        }

        return stack;
    }

    @Override
    public boolean stillValid(Player player) {
        return container.stillValid(player);
    }

    public boolean isLit() {
        return data.get(0) > 0;
    }

    public float getLitProgress() {
        return data.get(1) == 0 ? 200 : Mth.clamp((float) data.get(0) / data.get(1), 0.0F, 1.0F);
    }

    public float getBurnProgress() {
        int c = data.get(2);
        int d = data.get(3);
        return d != 0 && c != 0 ? Mth.clamp((float) c / d, 0.0F, 1.0F) : 0.0F;
    }

    protected boolean canSmelt(ItemStack stack) {
        return level.getRecipeManager().getRecipeFor(recipeType, new SingleRecipeInput(stack), level).isPresent();
    }

    public boolean isFuel(ItemStack stack) {
        return stack.getBurnTime(recipeType) > 0;
    }
}
