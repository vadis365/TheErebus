package erebus.block.entity;

import erebus.inventory.server.UmberFurnaceMenu;
import erebus.registries.blocks.ModBlockEntities;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class UmberFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

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
    protected final FluidStacksResourceHandler TANK;

    public FluidStacksResourceHandler getTank() {
        return TANK;
    }
    protected final ContainerData dataAccess;
    private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCookingRecipe> quickCheck;
    protected NonNullList<ItemStack> items;
    int litTime, litDuration, cookingProgress, cookingTotalTime;

    public UmberFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.UMBERFURNACE.get(), pos, state, RecipeType.SMELTING);
        this.TANK = new FluidStacksResourceHandler(1, 1000 * 16);
        this.items = NonNullList.withSize(4, ItemStack.EMPTY);
        this.dataAccess = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case DATA_LIT_TIME -> {
                        if (litDuration > 32767) yield Mth.floor((double) litTime / litDuration * 32767);
                        yield litTime;
                    }
                    case DATA_LIT_DURATION -> Math.min(litDuration, 32767);
                    case DATA_COOKING_PROGRESS -> cookingProgress;
                    case DATA_COOKING_TOTAL_TIME -> cookingTotalTime;
                    case DATA_TANK_AMOUNT -> getScaledFluidAmount(65);
                    default -> 0;
                };
            }

            @Override
            public void set(int key, int value) {
                switch (key) {
                    case DATA_LIT_TIME:
                        litTime = value;
                        break;
                    case DATA_LIT_DURATION:
                        litDuration = value;
                        break;
                    case DATA_COOKING_PROGRESS:
                        cookingProgress = value;
                        break;
                    case DATA_COOKING_TOTAL_TIME:
                        cookingTotalTime = value;
                        break;
                    case DATA_TANK_AMOUNT:
                        try(Transaction transaction = Transaction.openRoot()) {
                            if(TANK.insert(FluidResource.of(Fluids.LAVA), FluidType.BUCKET_VOLUME, transaction) == FluidType.BUCKET_VOLUME) {
                                transaction.commit();
                            }
                        }
                        break;
                }
            }

            @Override
            public int getCount() {
                return NUM_DATA_VALUES;
            }
        };
        Object2IntOpenHashMap<Identifier> recipesUsed = new Object2IntOpenHashMap<>();
        this.quickCheck = RecipeManager.createCheck(RecipeType.SMELTING);
        RecipeType<? extends AbstractCookingRecipe> recipeType = RecipeType.SMELTING;
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        TANK.serialize(output);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        TANK.deserialize(input);
    }

    @Override
    protected @NonNull Component getDefaultName() {
        return Component.translatable("erebus.container.umberfurnace");
    }

    @Override
    protected @NonNull AbstractContainerMenu createMenu(int id, @NonNull Inventory player) {
        return new UmberFurnaceMenu(id, player, this, this.dataAccess);
    }

    @Override
    public int @NonNull [] getSlotsForFace(@NonNull Direction direction) {
        if (direction == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        }

        return direction == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, @NonNull ItemStack stack, @Nullable Direction direction) {
        return canPlaceItem(slot, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, @NonNull ItemStack stack, @NonNull Direction direction) {
        return direction != Direction.DOWN || slot != FUEL_SLOT || stack.is(Items.WATER_BUCKET) || stack.is(Items.BUCKET);
    }

    public int getScaledFluidAmount(float scale) {
        return (int) ((float) TANK.getAmountAsInt(0) / 1000 * 16 * scale);
    }
}
