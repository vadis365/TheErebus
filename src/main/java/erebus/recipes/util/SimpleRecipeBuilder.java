package erebus.recipes.util;

import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class SimpleRecipeBuilder implements RecipeBuilder {

    protected final ItemLike result;
    protected final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    protected String group;

    public SimpleRecipeBuilder(ItemLike result) {
        this.result = result;
    }

    @Override
    public @NotNull SimpleRecipeBuilder unlockedBy(@NotNull String name, @NotNull Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public @NotNull SimpleRecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return this.result.asItem();
    }
}
