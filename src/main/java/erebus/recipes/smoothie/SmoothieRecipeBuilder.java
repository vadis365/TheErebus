package erebus.recipes.smoothie;

import erebus.recipes.util.SimpleRecipeBuilder;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SmoothieRecipeBuilder extends SimpleRecipeBuilder {

    private final List<SizedFluidIngredient> fluids = new ArrayList<>();
    private final List<Ingredient> items = new ArrayList<>();

    public SmoothieRecipeBuilder(ItemLike result) {
        super(result);
    }

    public SmoothieRecipeBuilder addFluidIngredient(SizedFluidIngredient fluid) {
        this.fluids.add(fluid);
        return this;
    }

    public SmoothieRecipeBuilder addItemIngredient(Ingredient item) {
        this.items.add(item);
        return this;
    }

    @Override
    public void save(@NotNull RecipeOutput output, @NotNull ResourceLocation id) {
        Advancement.Builder advancement = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement::addCriterion);

        SmoothieRecipe recipe = new SmoothieRecipe(NonNullList.copyOf(fluids), NonNullList.copyOf(items), new ItemStack(this.result, 1));
        output.accept(id.withPrefix("smoothie/"), recipe, advancement.build(id.withPrefix("smoothie/")));
    }
}
