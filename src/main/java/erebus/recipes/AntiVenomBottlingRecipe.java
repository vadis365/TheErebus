package erebus.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.item.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.List;

public final class AntiVenomBottlingRecipe extends NormalCraftingRecipe {
    public static final RecipeSerializer<AntiVenomBottlingRecipe> SERIALIZER = new RecipeSerializer<>(
            RecordCodecBuilder.mapCodec(i -> i.group(
                    Recipe.CommonInfo.MAP_CODEC.forGetter(r -> r.commonInfo),
                    CraftingBookInfo.MAP_CODEC.forGetter(r -> r.bookInfo),
                    Codec.BOOL.fieldOf("bamboo").forGetter(r -> r.bamboo)
            ).apply(i, AntiVenomBottlingRecipe::new)),
            StreamCodec.composite(Recipe.CommonInfo.STREAM_CODEC, r -> r.commonInfo,
                    CraftingBookInfo.STREAM_CODEC, r -> r.bookInfo,
                    ByteBufCodecs.BOOL, r -> r.bamboo, AntiVenomBottlingRecipe::new));

    private final boolean bamboo;
    private final ShapelessRecipe delegate;

    public AntiVenomBottlingRecipe(Recipe.CommonInfo common, CraftingBookInfo book, boolean bamboo) {
        super(common, book);
        this.bamboo = bamboo;
        delegate = new ShapelessRecipe(common, book, new ItemStackTemplate(ModItems.ANTI_VENOM_BOTTLE.get()),
                List.of(Ingredient.of(bamboo ? ModItems.ANTI_VENOM_BAMBUCKET : ModItems.ANTI_VENOM_BUCKET),
                        Ingredient.of(Items.GLASS_BOTTLE), Ingredient.of(Items.GLASS_BOTTLE)));
    }

    @Override
    public boolean matches(@NonNull CraftingInput input, @NonNull Level level) {
        return delegate.matches(input, level);
    }

    @Override
    public @NonNull ItemStack assemble(@NonNull CraftingInput input) {
        return delegate.assemble(input);
    }

    @Override
    protected @NonNull PlacementInfo createPlacementInfo() {
        return delegate.placementInfo();
    }

    @Override
    public @NonNull RecipeSerializer<AntiVenomBottlingRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public @NonNull NonNullList<ItemStack> getRemainingItems(@NonNull CraftingInput input) {
        var remainder = CraftingRecipe.defaultCraftingReminder(input);
        for (int slot = 0; slot < input.size(); slot++) {
            if (input.getItem(slot).is(Items.GLASS_BOTTLE)) {
                remainder.set(slot, new ItemStack(ModItems.ANTI_VENOM_BOTTLE.get()));
                break;
            }
        }
        return remainder;
    }

    @Override
    public @NonNull List<RecipeDisplay> display() {
        var bottle = new SlotDisplay.ItemSlotDisplay(ModItems.ANTI_VENOM_BOTTLE.get());
        return List.of(new ShapelessCraftingRecipeDisplay(List.of(
                new SlotDisplay.WithRemainder(
                        new SlotDisplay.ItemSlotDisplay((bamboo ? ModItems.ANTI_VENOM_BAMBUCKET : ModItems.ANTI_VENOM_BUCKET).get()),
                        new SlotDisplay.ItemSlotDisplay(bamboo ? ModItems.BAMBUCKET.get() : Items.BUCKET)),
                new SlotDisplay.WithRemainder(new SlotDisplay.ItemSlotDisplay(Items.GLASS_BOTTLE), bottle),
                new SlotDisplay.ItemSlotDisplay(Items.GLASS_BOTTLE)), bottle,
                new SlotDisplay.ItemSlotDisplay(Items.CRAFTING_TABLE)));
    }
}
