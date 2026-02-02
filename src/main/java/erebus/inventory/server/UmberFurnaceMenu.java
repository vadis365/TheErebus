package erebus.inventory.server;

import erebus.inventory.slot.FluidContainerSlot;
import erebus.inventory.slot.UmberFurnaceFuelSlot;
import erebus.registries.client.ModMenuTypes;
import net.minecraft.recipebook.ServerPlaceRecipe;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class UmberFurnaceMenu extends RecipeBookMenu {

    public static final int BUCKET_SLOT = 0;
    public static final int INGREDIENT_SLOT = 1;
    public static final int FUEL_SLOT = 2;
    public static final int RESULT_SLOT = 3;
    public static final int SLOT_COUNT = 4;
    public static final int DATA_COUNT = 5;
    public static final int DATA_LIT_TIME = 0;
    public static final int DATA_LIT_DURATION = 1;
    public static final int DATA_COOKING_PROGRESS = 2;
    public static final int DATA_COOKING_TOTAL_TIME = 3;
    public static final int DATA_TANK_AMOUNT = 4;
    private static final int INV_SLOT_START = 4;
    private static final int INV_SLOT_END = 31;
    private static final int HOTBAR_SLOT_START = 31;
    private static final int HOTBAR_SLOT_END = 40;
    private final Container container;
    private final ContainerData data;
    protected final Level level;
    private final RecipeType<? extends AbstractCookingRecipe> recipeType;
    private final RecipePropertySet acceptedInputs;
    private final RecipeBookType recipeBookType;

    public UmberFurnaceMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(SLOT_COUNT), new SimpleContainerData(DATA_COUNT));
    }

    public UmberFurnaceMenu(int containerId, Inventory inventory, Container container, ContainerData data) {
        super(ModMenuTypes.UMBER_FURNACE_MENU.get(), containerId);
        recipeType = RecipeType.SMELTING;
        recipeBookType = RecipeBookType.FURNACE;
        checkContainerSize(container, SLOT_COUNT);
        checkContainerDataCount(data, DATA_COUNT);
        this.container = container;
        this.data = data;
        level = inventory.player.level();
        acceptedInputs = level.recipeAccess().propertySet(RecipePropertySet.FURNACE_INPUT);
        addSlot(new Slot(container, INGREDIENT_SLOT, 56, 17));
        addSlot(new UmberFurnaceFuelSlot(this, container, FUEL_SLOT, 56, 53));
        addSlot(new FurnaceResultSlot(inventory.player, container, RESULT_SLOT, 116, 35));
        addSlot(new FluidContainerSlot(container, BUCKET_SLOT, 31, 35));
        addStandardInventorySlots(inventory, 8, 84);
        addDataSlots(data);
    }

    protected boolean canSmelt(ItemStack stack) {
        return acceptedInputs.test(stack);
    }

    public boolean isFuel(ItemStack stack) {
        return stack.getBurnTime(recipeType, level.fuelValues()) > 0;
    }

    public Slot getResultSlot() {
        return this.slots.get(RESULT_SLOT);
    }

    public float getBurnProgress() {
        int progress = data.get(DATA_COOKING_PROGRESS);
        int totalTime = data.get(DATA_COOKING_TOTAL_TIME);
        return totalTime != 0 && progress != 0 ? Mth.clamp((float) progress / totalTime, 0.0F, 1.0F) : 0.0F;
    }

    public float getLitProgress() {
        return data.get(DATA_LIT_DURATION) == 0 ? 200 : Mth.clamp((float) data.get(DATA_LIT_TIME) / data.get(DATA_LIT_DURATION), 0.0F, 1.0F);
    }

    public boolean isLit() {
        return data.get(DATA_LIT_TIME) > 0;
    }

    public int getTankAmount() {
        return data.get(DATA_TANK_AMOUNT);
    }

    @Override
    @SuppressWarnings("unchecked")
    public @NonNull PostPlaceAction handlePlacement(boolean useMaxItems, boolean allowDroppingItemsToClear, @NonNull RecipeHolder<?> recipe, final @NonNull ServerLevel level, @NonNull Inventory inventory) {
        final List<Slot> slotsToClear = List.of(getSlot(0), getSlot(2));
        return ServerPlaceRecipe.placeRecipe(new ServerPlaceRecipe.CraftingMenuAccess<>() {
            public void fillCraftSlotsStackedContents(@NonNull StackedItemContents stackedContents) {
                UmberFurnaceMenu.this.fillCraftSlotsStackedContents(stackedContents);
            }

            public void clearCraftingContent() {
                slotsToClear.forEach((s) -> s.set(ItemStack.EMPTY));
            }

            public boolean recipeMatches(@NonNull RecipeHolder<AbstractCookingRecipe> recipeHolder) {
                return recipeHolder.value().matches(new SingleRecipeInput(UmberFurnaceMenu.this.container.getItem(INGREDIENT_SLOT)), level);
            }
        }, 1, 1, List.of(getSlot(0)), slotsToClear, inventory, (RecipeHolder<AbstractCookingRecipe>) recipe, useMaxItems, allowDroppingItemsToClear);
    }

    @Override
    public void fillCraftSlotsStackedContents(@NonNull StackedItemContents contents) {
        if(container instanceof StackedContentsCompatible) {
            ((StackedContentsCompatible) container).fillStackedContents(contents);
        }
    }

    @Override
    public @NonNull RecipeBookType getRecipeBookType() {
        return recipeBookType;
    }

    @Override
    public @NonNull ItemStack quickMoveStack(@NonNull Player player, int index) {
        return null;
    }

    @Override
    public boolean stillValid(@NonNull Player player) {
        return container.stillValid(player);
    }
}