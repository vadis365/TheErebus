package erebus.block.entity;

import erebus.inventory.UmberFurnaceMenu;
import erebus.registries.ModBlockEntities;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UmberFurnaceBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeCraftingHolder, StackedContentsCompatible {

    public static final int DATA_LIT_TIME = 0;
    public static final int DATA_LIT_DURATION = 1;
    public static final int DATA_COOKING_PROGRESS = 2;
    public static final int DATA_COOKING_TOTAL_TIME = 3;
    public static final int DATA_TANK_AMOUNT = 4;
    public static final int NUM_DATA_VALUES = 5;
    public static final int BURN_TIME_STANDARD = 200;
    public static final int BURN_COOL_SPEED = 2;
    protected static final int BUCKET_SLOT = 0;
    protected static final int INGREDIENT_SLOT = 1;
    protected static final int FUEL_SLOT = 2;
    protected static final int RESULT_SLOT = 3;
    private static final int[] SLOTS_FOR_UP = new int[]{INGREDIENT_SLOT};
    private static final int[] SLOTS_FOR_DOWN = new int[]{RESULT_SLOT, BUCKET_SLOT};
    private static final int[] SLOTS_FOR_SIDES = new int[]{BUCKET_SLOT, FUEL_SLOT, INGREDIENT_SLOT};
    protected final FluidTank TANK;
    protected final ContainerData dataAccess;
    private final RecipeType<? extends AbstractCookingRecipe> recipeType;
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed;
    private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCookingRecipe> quickCheck;
    protected NonNullList<ItemStack> items;
    int litTime, litDuration, cookingProgress, cookingTotalTime;

    public UmberFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.UMBERFURNACE.get(), pos, state);
        this.TANK = new FluidTank(1000 * 16);
        this.items = NonNullList.withSize(4, ItemStack.EMPTY);
        this.dataAccess = new ContainerData() {
            @Override
            public int get(int i) {
                switch (i) {
                    case DATA_LIT_TIME:
                        if (litDuration > 32767) return Mth.floor((double) litTime / litDuration * 32767);
                        return litTime;
                    case DATA_LIT_DURATION:
                        return Math.min(litDuration, 32767);
                    case DATA_COOKING_PROGRESS:
                        return cookingProgress;
                    case DATA_COOKING_TOTAL_TIME:
                        return cookingTotalTime;
                    case DATA_TANK_AMOUNT:
                        return getScaledFluidAmount(65);
                    default:
                        return 0;
                }
            }

            @Override
            public void set(int key, int value) {
                switch (key) {
                    case DATA_LIT_TIME -> litTime = value;
                    case DATA_LIT_DURATION -> litDuration = value;
                    case DATA_COOKING_PROGRESS -> cookingProgress = value;
                    case DATA_COOKING_TOTAL_TIME -> cookingTotalTime = value;
                    case DATA_TANK_AMOUNT -> TANK.setFluid(new FluidStack(Fluids.LAVA, value));
                }
            }

            @Override
            public int getCount() {
                return NUM_DATA_VALUES;
            }
        };
        this.recipesUsed = new Object2IntOpenHashMap<>();
        this.quickCheck = RecipeManager.createCheck(RecipeType.SMELTING);
        this.recipeType = RecipeType.SMELTING;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, UmberFurnaceBlockEntity furnace) {
        furnace.getItems().set(BUCKET_SLOT, furnace.fillTankWithBucket(furnace.getItem(BUCKET_SLOT)));

        boolean isLit = furnace.isLit();
        boolean hasChanged = false;
        if (furnace.isLit()) {
            --furnace.litTime;
        }

        ItemStack fuel = furnace.items.get(FUEL_SLOT);
        ItemStack input = furnace.items.get(INGREDIENT_SLOT);
        boolean hasInput = !input.isEmpty();
        boolean hasFuel = !fuel.isEmpty();
        if (furnace.isLit() || hasFuel && hasInput) {
            RecipeHolder<?> recipeholder;
            if (hasInput) {
                recipeholder = furnace.quickCheck.getRecipeFor(new SingleRecipeInput(input), level).orElse(null);
            } else {
                recipeholder = null;
            }

            int i = furnace.getMaxStackSize();
            if (!furnace.isLit() && canBurn(level.registryAccess(), recipeholder, furnace.items, i, furnace)) {
                furnace.litTime = furnace.getBurnDuration(fuel);
                furnace.litDuration = furnace.litTime;
                if (furnace.isLit()) {
                    hasChanged = true;
                    if (fuel.hasCraftingRemainingItem()) {
                        furnace.items.set(FUEL_SLOT, fuel.getCraftingRemainingItem());
                    } else if (hasFuel) {
                        fuel.shrink(1);
                        if (fuel.isEmpty()) {
                            furnace.items.set(FUEL_SLOT, fuel.getCraftingRemainingItem());
                        }
                    }
                }
            }

            if (furnace.isLit() && canBurn(level.registryAccess(), recipeholder, furnace.items, i, furnace)) {
                ++furnace.cookingProgress;
                furnace.cookingTotalTime = getTotalCookTime(level, furnace);
                if (furnace.cookingProgress >= furnace.cookingTotalTime) {
                    furnace.cookingProgress = 0;
                    if (burn(level.registryAccess(), recipeholder, furnace.items, i, furnace)) {
                        furnace.setRecipeUsed(recipeholder);
                    }

                    hasChanged = true;
                }
            } else {
                furnace.cookingProgress = 0;
            }
        } else if (!furnace.isLit() && furnace.cookingProgress > 0) {
            furnace.cookingProgress = Mth.clamp(furnace.cookingProgress - BURN_COOL_SPEED, 0, furnace.cookingTotalTime);
        }

        if (isLit != furnace.isLit()) {
            hasChanged = true;
            state = state.setValue(AbstractFurnaceBlock.LIT, furnace.isLit());
            level.setBlock(pos, state, 3);
        }

        if (hasChanged) {
            setChanged(level, pos, state);
        }
    }

    private static boolean canBurn(RegistryAccess access, RecipeHolder<?> recipe, NonNullList<ItemStack> inventory, int maxStackSize, UmberFurnaceBlockEntity furnace) {
        if (!inventory.get(INGREDIENT_SLOT).isEmpty() && recipe != null) {
            ItemStack recipeOutput = ((AbstractCookingRecipe) recipe.value()).assemble(new SingleRecipeInput(furnace.getItem(INGREDIENT_SLOT)), access);
            if (recipeOutput.isEmpty()) {
                return false;
            } else {
                ItemStack result = inventory.get(RESULT_SLOT);
                if (result.isEmpty()) {
                    return true;
                } else if (!ItemStack.isSameItemSameComponents(result, recipeOutput)) {
                    return false;
                } else {
                    return result.getCount() + recipeOutput.getCount() <= maxStackSize
                            && result.getCount() + recipeOutput.getCount() <= result.getMaxStackSize()
                            || result.getCount() + recipeOutput.getCount() <= recipeOutput.getMaxStackSize();
                }
            }
        } else {
            return false;
        }
    }

    private static boolean burn(RegistryAccess access, RecipeHolder<?> recipe, NonNullList<ItemStack> inventory, int maxStackSize, UmberFurnaceBlockEntity furnace) {
        if (recipe != null && canBurn(access, recipe, inventory, maxStackSize, furnace)) {
            ItemStack input = inventory.get(INGREDIENT_SLOT);
            ItemStack recipeOutput = ((AbstractCookingRecipe) recipe.value()).assemble(new SingleRecipeInput(furnace.getItem(INGREDIENT_SLOT)), access);
            ItemStack output = inventory.get(RESULT_SLOT);
            if (output.isEmpty()) {
                inventory.set(RESULT_SLOT, recipeOutput.copy());
            } else if (ItemStack.isSameItemSameComponents(output, recipeOutput)) {
                output.grow(recipeOutput.getCount());
            }

            if (input.is(Blocks.WET_SPONGE.asItem()) && !inventory.get(FUEL_SLOT).isEmpty() && inventory.get(FUEL_SLOT).is(Items.BUCKET)) {
                inventory.set(FUEL_SLOT, new ItemStack(Items.WATER_BUCKET));
            }

            input.shrink(1);
            return true;
        } else {
            return false;
        }
    }

    private static int getTotalCookTime(Level level, UmberFurnaceBlockEntity furnace) {
        int time = furnace.quickCheck.getRecipeFor(new SingleRecipeInput(furnace.getItem(INGREDIENT_SLOT)), level).map(recipe -> recipe.value().getCookingTime()).orElse(BURN_TIME_STANDARD);

        return (int) (time - (time * 0.8F * ((float) furnace.TANK.getFluidAmount() / furnace.TANK.getCapacity())));
    }

    private static void createExperience(ServerLevel level, Vec3 pos, int index, float xp) {
        int i = Mth.floor(index * xp);
        float f = Mth.floor(index * xp);
        if (f != 0.0F && Math.random() < f) {
            ++i;
        }

        ExperienceOrb.award(level, pos, i);
    }

    public ItemStack fillTankWithBucket(ItemStack bucket) {
        if (TANK.getFluidAmount() <= TANK.getCapacity() - FluidType.BUCKET_VOLUME) {
            if (FluidUtil.getFluidHandler(bucket).isPresent() && FluidUtil.getFluidContained(bucket).isPresent()) {
                if (FluidUtil.getFluidContained(bucket).get().is(Fluids.LAVA)) {
                    return FluidUtil.tryEmptyContainer(bucket, TANK, FluidType.BUCKET_VOLUME, null, true).getResult();
                }
            }
        }

        return bucket;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("BurnTime", litTime);
        tag.putInt("CookTime", cookingProgress);
        tag.putInt("CookTimeTotal", cookingTotalTime);
        ContainerHelper.saveAllItems(tag, items, registries);
        CompoundTag compoundTag = new CompoundTag();
        recipesUsed.forEach((key, value) -> compoundTag.putInt(key.toString(), value));
        tag.put("RecipesUsed", compoundTag);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, items, registries);
        litTime = tag.getInt("BurnTime");
        cookingProgress = tag.getInt("CookTime");
        cookingTotalTime = tag.getInt("CookTimeTotal");
        litDuration = getBurnDuration(items.get(FUEL_SLOT));
        CompoundTag compoundTag = tag.getCompound("RecipesUsed");

        compoundTag.getAllKeys().forEach(key -> recipesUsed.put(ResourceLocation.parse(key), compoundTag.getInt(key)));
    }

    protected int getBurnDuration(ItemStack fuel) {
        return fuel.isEmpty() ? 0 : fuel.getBurnTime(recipeType);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.umberfurnace");
    }

    private boolean isLit() {
        return litTime > 0;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return new UmberFurnaceMenu(id, player, this, this.dataAccess);
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        items = nonNullList;
    }

    @Override
    public int getContainerSize() {
        return items.size();
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        if (direction == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        }

        return direction == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
        return canPlaceItem(slot, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
        return direction != Direction.DOWN || slot != FUEL_SLOT || stack.is(Items.WATER_BUCKET) || stack.is(Items.BUCKET);
    }

    @Override
    public @Nullable RecipeHolder<?> getRecipeUsed() {
        return null;
    }

    @Override
    public void setRecipeUsed(@Nullable RecipeHolder<?> recipe) {
        if (recipe != null) {
            recipesUsed.addTo(recipe.id(), 1);
        }
    }

    public void awardUsedRecipesAndPopExperience(ServerPlayer player) {
        List<RecipeHolder<?>> list = getRecipesToAwardAndPopExperience(player.serverLevel(), player.position());
        player.awardRecipes(list);

        list.forEach(recipe -> {
            if (recipe != null) player.triggerRecipeCrafted(recipe, items);
        });

        recipesUsed.clear();
    }

    public List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(ServerLevel level, Vec3 pos) {
        List<RecipeHolder<?>> list = Lists.newArrayList();
        ObjectIterator iterator = recipesUsed.object2IntEntrySet().iterator();

        while (iterator.hasNext()) {
            Object2IntMap.Entry<ResourceLocation> entry = (Object2IntMap.Entry) iterator.next();
            level.getRecipeManager().byKey(entry.getKey()).ifPresent(recipe -> {
                list.add(recipe);
                createExperience(level, pos, entry.getIntValue(), ((AbstractCookingRecipe) recipe.value()).getExperience());
            });
        }

        return list;
    }

    @Override
    public void fillStackedContents(StackedContents helper) {
        items.forEach(helper::accountStack);
    }

    public int getScaledFluidAmount(float scale) {
        return (int) ((float) TANK.getFluidAmount() / TANK.getCapacity() * scale);
    }
}
