package erebus.datagen.providers.recipes;

import erebus.registries.ModItems;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class CustomRecipeProvider {

	public void buildRecipes(RecipeOutput output) {
		OfferingAltarRecipeBuilder.assembly(ModItems.GAEAN_GEM)
			.requires(Items.DIAMOND)
			.requires(Items.EMERALD)
			.requires(Blocks.OBSIDIAN)
			.save(output);
	}
}
