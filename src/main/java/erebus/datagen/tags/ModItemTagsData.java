package erebus.datagen.tags;

import erebus.Erebus;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.tags.ModItemTags;
import erebus.registries.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
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

		tag(ModItemTags.REPAIRS_JADE_ARMOR)
				.add(ModItems.JADE.get());
		tag(ModItemTags.REPAIRS_EXOSKELETON_ARMOR)
				.add(ModItems.PLATE_EXO.get());
		tag(ModItemTags.REPAIRS_REINFORCED_EXOSKELETON_ARMOR)
				.add(ModItems.REINFORCED_PLATE_EXO.get());
		tag(ModItemTags.REPAIRS_RHINO_ARMOR)
				.add(ModItems.PLATE_EXO_RHINO.get());
		tag(ModItemTags.REPAIRS_BAMBOO_ARMOR)
				.add(ModItems.BAMBOO.get());
		tag(ModItemTags.REPAIRS_REINFORCED_COMPOUND_GOGGLES)
				.add(ModItems.COMPOUND_LENS.get());
		tag(ModItemTags.REPAIRS_MUSHROOM_HELM)
				.add();
		tag(ModItemTags.REPAIRS_SPIDER_T_SHIRT)
				.add();
		tag(ModItemTags.REPAIRS_WATER_STRIDERS)
				.add();
		tag(ModItemTags.REPAIRS_JUMP_BOOTS)
				.add(ModItems.ELASTIC_FIBER.get());
		tag(ModItemTags.REPAIRS_SPRINT_LEGGINGS)
				.add();
		tag(ModItemTags.JADE_TOOL_MATERIALS)
				.add();
		tag(ModItemTags.WASP_SWORD_TOOL_MATERIALS)
				.add();
		tag(ModItemTags.WASP_DAGGER_TOOL_MATERIALS)
				.add();
		tag(ModItemTags.ROLLED_NEWSPAPER_TOOL_MATERIALS)
				.add();
		tag(ModItemTags.SCORPION_PINCER_TOOL_MATERIALS)
				.add();
		tag(ModItemTags.QUAKE_HAMMER_TOOL_MATERIALS)
				.add();
		tag(ModItemTags.TITAN_BEETLE_FOOD)
				.add(ModItems.TURNIP.get());
		tag(ModItemTags.TITAN_BEETLE_CHESTS)
				.addTag(ItemTags.COPPER_CHESTS)
				.add(Blocks.CHEST.asItem())
				.add(ModBlocks.CHEST_ASPER.asItem())
				.add(ModBlocks.CHEST_BAMBOO.asItem())
				.add(ModBlocks.CHEST_BAOBAB.asItem())
				.add(ModBlocks.CHEST_BALSAM.asItem())
				.add(ModBlocks.CHEST_CYPRESS.asItem())
				.add(ModBlocks.CHEST_EUCALYPTUS.asItem())
				.add(ModBlocks.CHEST_MAHOGANY.asItem())
				.add(ModBlocks.CHEST_MARSHWOOD.asItem())
				.add(ModBlocks.CHEST_MOSSBARK.asItem())
				.add(ModBlocks.CHEST_PETRIFIED.asItem())
				.add(ModBlocks.CHEST_ROTTEN.asItem())
				.add(ModBlocks.CHEST_SCORCHED.asItem())
				.add(ModBlocks.CHEST_VARNISHED.asItem())
				.add(ModBlocks.CHEST_WHITE.asItem());
    }
}
