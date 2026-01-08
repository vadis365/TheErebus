package erebus.datagen.tags;

import erebus.Erebus;
import erebus.registries.data.tags.ModItemTags;
import erebus.registries.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsData extends ItemTagsProvider {

	public ModItemTagsData(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Erebus.MODID);
    }

    @SuppressWarnings("unchecked")
	@Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
    	/* Old materials
    			Material.CACTUS, 
    			Material.CAKE, 
    			Material.CORAL,
    			Material.GOURD,
    			Material.GRASS,
    			Material.LEAVES, 
    			Material.PLANTS,
    			Material.SPONGE, 
    			Material.VINE, 
    			Material.WEB, 
    			Material.WOOD;
    	*/
    	
        // MARK: Composter 'fuels'
        tag(ModItemTags.COMPOSTABLE).addTags(
        		ItemTags.BAMBOO_BLOCKS,
        		ItemTags.LOGS,
        		ItemTags.LEAVES,
        		ItemTags.WOODEN_BUTTONS,
        		ItemTags.WOODEN_DOORS,
        		ItemTags.WOODEN_FENCES,
        		ItemTags.WOODEN_PRESSURE_PLATES,
        		ItemTags.WOODEN_SLABS,
        		ItemTags.WOODEN_STAIRS,
        		ItemTags.WOODEN_TRAPDOORS,
        		ItemTags.WOODEN_STAIRS,
        		ItemTags.PLANKS,
        		ItemTags.SAPLINGS
        		)
                .add(
                		Items.STICK,
                		Items.WOODEN_AXE,
                		Items.WOODEN_HOE,
                		Items.WOODEN_PICKAXE,
                		Items.WOODEN_SHOVEL,
                		Items.WOODEN_SWORD,
                		Items.WHEAT,
                		Items.POTATO,
                		Items.POISONOUS_POTATO,
                		ModItems.DARK_FRUIT_SEEDS.get(),
                		ModItems.BLUEBELL_PETAL.get(),
                		ModItems.PAPYRUS.get(),
                		ModItems.NETTLE_LEAVES.get(),
                		ModItems.NETTLE_FLOWERS.get(),
                		ModItems.MOSS_BALL.get(),
                		ModItems.GLOWSHROOM.get(),
                		ModItems.JADE_BERRIES.get(),
                		ModItems.BOGMAW_ROOT.get(),
                		ModItems.BAMBOO.get(),
                		ModItems.BAMBOO_SHOOT.get()
                );

    }
}
