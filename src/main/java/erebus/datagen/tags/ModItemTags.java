package erebus.datagen.tags;

import erebus.Erebus;
import erebus.registries.ModItems;
import erebus.registries.data.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTags extends IntrinsicHolderTagsProvider<Item> {

    @SuppressWarnings("deprecation")
	public ModItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.ITEM, lookupProvider, item -> item.builtInRegistryHolder().key(), Erebus.MODID, existingFileHelper);
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
        tag(ModTags.COMPOSTABLE).addTags(
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
