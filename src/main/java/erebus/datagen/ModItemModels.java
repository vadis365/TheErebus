package erebus.datagen;

import static erebus.registries.ModBlocks.AMBER;
import static erebus.registries.ModBlocks.AMBER_BRICKS;
import static erebus.registries.ModBlocks.SLAB_AMBER;
import static erebus.registries.ModBlocks.SLAB_AMBER_BRICKS;
import static erebus.registries.ModBlocks.SLAB_MIR_BRICKS;
import static erebus.registries.ModBlocks.SLAB_MUD_BRICKS;
import static erebus.registries.ModBlocks.SLAB_PLANKS_PETRIFIED_WOOD;
import static erebus.registries.ModBlocks.SLAB_UMBERCOBBLE;
import static erebus.registries.ModBlocks.SLAB_UMBERCOBBLE_MOSSY;
import static erebus.registries.ModBlocks.SLAB_UMBERCOBBLE_WEBBED;
import static erebus.registries.ModBlocks.SLAB_UMBERPAVER;
import static erebus.registries.ModBlocks.SLAB_UMBERPAVER_MOSSY;
import static erebus.registries.ModBlocks.SLAB_UMBERPAVER_WEBBED;
import static erebus.registries.ModBlocks.SLAB_UMBERSTONE;
import static erebus.registries.ModBlocks.SLAB_UMBERSTONE_BRICKS;
import static erebus.registries.ModBlocks.SLAB_UMBERTILE_SMOOTH;
import static erebus.registries.ModBlocks.SLAB_UMBERTILE_SMOOTH_SMALL;
import static erebus.registries.ModBlocks.STAIRS_AMBER;
import static erebus.registries.ModBlocks.STAIRS_AMBER_BRICKS;
import static erebus.registries.ModBlocks.STAIRS_MIR_BRICKS;
import static erebus.registries.ModBlocks.STAIRS_MUD_BRICKS;
import static erebus.registries.ModBlocks.STAIRS_PETRIFIED_WOOD;
import static erebus.registries.ModBlocks.STAIRS_UMBERCOBBLE;
import static erebus.registries.ModBlocks.STAIRS_UMBERCOBBLE_MOSSY;
import static erebus.registries.ModBlocks.STAIRS_UMBERCOBBLE_WEBBED;
import static erebus.registries.ModBlocks.STAIRS_UMBERPAVER;
import static erebus.registries.ModBlocks.STAIRS_UMBERPAVER_MOSSY;
import static erebus.registries.ModBlocks.STAIRS_UMBERPAVER_WEBBED;
import static erebus.registries.ModBlocks.STAIRS_UMBERSTONE;
import static erebus.registries.ModBlocks.STAIRS_UMBERSTONE_BRICKS;
import static erebus.registries.ModBlocks.STAIRS_UMBERTILE_SMOOTH;
import static erebus.registries.ModBlocks.STAIRS_UMBERTILE_SMOOTH_SMALL;
import static erebus.registries.ModBlocks.UMBERPAVER;
import static erebus.registries.ModBlocks.UMBERSTONE;
import static erebus.registries.ModBlocks.WALL_AMBER;
import static erebus.registries.ModBlocks.WALL_AMBER_BRICKS;
import static erebus.registries.ModBlocks.WALL_UMBERCOBBLE;
import static erebus.registries.ModBlocks.WALL_UMBERCOBBLE_MOSSY;
import static erebus.registries.ModBlocks.WALL_UMBERCOBBLE_WEBBED;
import static erebus.registries.ModBlocks.WALL_UMBERPAVER;
import static erebus.registries.ModBlocks.WALL_UMBERPAVER_MOSSY;
import static erebus.registries.ModBlocks.WALL_UMBERPAVER_WEBBED;
import static erebus.registries.ModBlocks.WALL_UMBERSTONE;
import static erebus.registries.ModBlocks.WALL_UMBERSTONE_BRICKS;
import static erebus.registries.ModBlocks.WALL_UMBERTILE_SMOOTH;
import static erebus.registries.ModBlocks.WALL_UMBERTILE_SMOOTH_SMALL;

import erebus.registries.ModBlocks;
import erebus.registries.ModEntities;
import erebus.registries.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModItemModels extends ModItemModelProvider {

    public ModItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerItemModels();
        registerBlockItemModels();
    }

    private void registerItemModels() {

    	// Mob Spawn Eggs
		for (DeferredHolder<Item, ?> item : ModEntities.SPAWN_EGGS.getEntries()) {
			if (item.get() instanceof SpawnEggItem) {
				this.getBuilder(item.getId().getPath()).parent(this.getExistingFile(ResourceLocation.withDefaultNamespace("item/template_spawn_egg")));
			}
		}

        // MARK: Jade Tools

        toolItem(ModItems.JADE_SWORD);
        toolItem(ModItems.JADE_PICKAXE);
        toolItem(ModItems.JADE_AXE);
        toolItem(ModItems.JADE_SHOVEL);
        toolItem(ModItems.JADE_PAXEL);
        toolItem(ModItems.JADE_HOE);

        // MARK: Jade Armor
        normalItem(ModItems.JADE_HELMET);
        normalItem(ModItems.JADE_CHESTPLATE);
        normalItem(ModItems.JADE_LEGGINGS);
        normalItem(ModItems.JADE_BOOTS);

        // MARK: Exoskeleton Armor
        normalItem(ModItems.EXOSKELETON_HELMET);
        normalItem(ModItems.EXOSKELETON_CHESTPLATE);
        normalItem(ModItems.EXOSKELETON_LEGGINGS);
        normalItem(ModItems.EXOSKELETON_BOOTS);

        // MARK: Reinforced Exoskeleton Armor
        normalItem(ModItems.REIN_EXOSKELETON_HELMET);
        normalItem(ModItems.REIN_EXOSKELETON_CHESTPLATE);
        normalItem(ModItems.REIN_EXOSKELETON_LEGGINGS);
        normalItem(ModItems.REIN_EXOSKELETON_BOOTS);

        // MARK: Rhino Exoskeleton Armor
        normalItem(ModItems.RHINO_EXOSKELETON_HELMET);
        normalItem(ModItems.RHINO_EXOSKELETON_CHESTPLATE);
        normalItem(ModItems.RHINO_EXOSKELETON_LEGGINGS);
        normalItem(ModItems.RHINO_EXOSKELETON_BOOTS);

        normalItem(ModItems.HEART_BERRIES);
        normalItem(ModItems.LIFE_BLOOD);
        normalItem(ModItems.TURNIP);
        normalItem(ModItems.CABBAGE_SEEDS);
        normalItem(ModItems.MANDRAKE_ROOT);
        normalItem(ModItems.STAG_HEART_RAW);
        normalItem(ModItems.STAG_HEART_COOKED);
        normalItem(ModItems.BAMBOO_HELMET);
        normalItem(ModItems.BAMBOO_CHESTPLATE);
        normalItem(ModItems.BAMBOO_LEGGINGS);
        normalItem(ModItems.BAMBOO_BOOTS);
        normalItem(ModItems.REIN_COMPOUND_GOGGLES);
        normalItem(ModItems.COMPOUND_GOGGLES);
        normalItem(ModItems.SPRINT_LEGGINGS);
        normalItem(ModItems.JUMP_BOOTS);
        normalItem(ModItems.GLIDER_CHESTPLATE);
        normalItem(ModItems.GLIDER_CHESTPLATE_POWERED);
        normalItem(ModItems.MUSHROOM_HELMET);
        normalItem(ModItems.SPIDER_T_SHIRT);
        normalItem(ModItems.WATER_STRIDERS);
        normalItem(ModItems.BAMBOO_SHIELD);
        normalItem(ModItems.EXOSKELETON_SHIELD);
        normalItem(ModItems.JADE_SHIELD);
        normalItem(ModItems.REIN_EXOSKELETON_SHIELD);
        normalItem(ModItems.RHINO_EXOSKELETON_SHIELD);
        normalItem(ModItems.ROLLED_NEWSPAPER);
        normalItem(ModItems.WASP_SWORD);
        normalItem(ModItems.NECTAR_COLLECTOR);
        normalItem(ModItems.ANT_TAMING_AMULET);
        normalItem(ModItems.BEE_TAMING_AMULET);
        normalItem(ModItems.ANTI_VENOM_BOTTLE);
        normalItem(ModItems.SPRAY_CAN);
        normalItem(ModItems.WHETSTONE);
        normalItem(ModItems.COMPOST);
        normalItem(ModItems.PLANTICIDE);
        normalItem(ModItems.SMOOTHIE_BOOK);
        normalItem(ModItems.HORN_OF_SUMMONING);
    }

    private void registerBlockItemModels() {
        blockFlat(ModBlocks.ALGAE);
        block(ModBlocks.AMBER);
        block(ModBlocks.AMBER_BRICKS);
        block(ModBlocks.AMBER_GLASS);
        blockFlatWithItemTexture(ModBlocks.AMBER_DOOR, "door_amber");

        // MARK: Umberstone

        block(ModBlocks.UMBERSTONE);
        block(ModBlocks.UMBERPAVER);
        block(ModBlocks.UMBERGRAVEL);
        block(ModBlocks.UMBERSTONE_PILLAR);
        //block(ModBlocks.UMBER_FURNACE);

        // MARK: Walls

        wall(WALL_UMBERSTONE, UMBERSTONE);
        wall(WALL_UMBERCOBBLE, UMBERSTONE);
        wall(WALL_UMBERCOBBLE_MOSSY, UMBERSTONE);
        wall(WALL_UMBERCOBBLE_WEBBED, UMBERSTONE);
        wall(WALL_UMBERSTONE_BRICKS, UMBERSTONE);
        wall(WALL_UMBERTILE_SMOOTH, UMBERSTONE);
        wall(WALL_UMBERTILE_SMOOTH_SMALL, UMBERSTONE);
        wall(WALL_UMBERPAVER, UMBERPAVER);
        wall(WALL_UMBERPAVER_MOSSY, UMBERPAVER);
        wall(WALL_UMBERPAVER_WEBBED, UMBERSTONE);
        wall(WALL_AMBER, AMBER);
        wall(WALL_AMBER_BRICKS, AMBER_BRICKS);

        // MARK: Slabs

        slab(SLAB_UMBERSTONE, UMBERSTONE);
        slab(SLAB_UMBERCOBBLE, UMBERSTONE);
        slab(SLAB_UMBERCOBBLE_MOSSY, UMBERSTONE);
        slab(SLAB_UMBERCOBBLE_WEBBED, UMBERSTONE);
        slab(SLAB_UMBERSTONE_BRICKS, UMBERSTONE);
        slab(SLAB_UMBERTILE_SMOOTH, UMBERSTONE);
        slab(SLAB_UMBERTILE_SMOOTH_SMALL, UMBERSTONE);
        slab(SLAB_UMBERPAVER, UMBERSTONE);
        slab(SLAB_AMBER, UMBERSTONE);
        slab(SLAB_AMBER_BRICKS, UMBERSTONE);
        slab(SLAB_UMBERPAVER_MOSSY, UMBERSTONE);
        slab(SLAB_UMBERPAVER_WEBBED, UMBERSTONE);
        slab(SLAB_MIR_BRICKS, UMBERSTONE);
        slab(SLAB_PLANKS_PETRIFIED_WOOD, UMBERSTONE);
        slab(SLAB_MUD_BRICKS, UMBERSTONE);

        // MARK: Stairs

        stairs(STAIRS_UMBERSTONE, UMBERSTONE);
        stairs(STAIRS_UMBERCOBBLE, UMBERSTONE);
        stairs(STAIRS_UMBERCOBBLE_MOSSY, UMBERSTONE);
        stairs(STAIRS_UMBERCOBBLE_WEBBED, UMBERSTONE);
        stairs(STAIRS_UMBERSTONE_BRICKS, UMBERSTONE);
        stairs(STAIRS_UMBERTILE_SMOOTH, UMBERSTONE);
        stairs(STAIRS_UMBERTILE_SMOOTH_SMALL, UMBERSTONE);
        stairs(STAIRS_UMBERPAVER, UMBERSTONE);
        stairs(STAIRS_UMBERPAVER_MOSSY, UMBERSTONE);
        stairs(STAIRS_UMBERPAVER_WEBBED, UMBERSTONE);
        stairs(STAIRS_AMBER, UMBERSTONE);
        stairs(STAIRS_AMBER_BRICKS, UMBERSTONE);
        stairs(STAIRS_PETRIFIED_WOOD, UMBERSTONE);
        stairs(STAIRS_MUD_BRICKS, UMBERSTONE);
        stairs(STAIRS_MIR_BRICKS, UMBERSTONE);

        // MARK: Ores

        block(ModBlocks.ORE_IRON);
        block(ModBlocks.ORE_GOLD);
        block(ModBlocks.ORE_COAL);
        block(ModBlocks.ORE_DIAMOND);
        block(ModBlocks.ORE_EMERALD);
        block(ModBlocks.ORE_LAPIS);
        block(ModBlocks.ORE_QUARTZ);
        block(ModBlocks.ORE_PETRIFIED_QUARTZ);
        block(ModBlocks.ORE_COPPER);
        block(ModBlocks.ORE_SILVER);
        block(ModBlocks.ORE_TIN);
        block(ModBlocks.ORE_LEAD);
        block(ModBlocks.ORE_ALUMINUM);
        block(ModBlocks.ORE_JADE);
        block(ModBlocks.ORE_ENCRUSTED_DIAMOND);
        block(ModBlocks.ORE_FOSSIL);
        block(ModBlocks.ORE_GNEISS);
        block(ModBlocks.ORE_PETRIFIED_WOOD);
        block(ModBlocks.ORE_TEMPLE);


        block(ModBlocks.PORTAL);
        //block(ModBlocks.GAEAN_KEYSTONE);

        block(ModBlocks.PETRIFIED_WOOD_ROCK);
        block(ModBlocks.PETRIFIED_WOOD_ROCK_2);
        block(ModBlocks.PETRIFIED_WOOD_ROCK_3);
        block(ModBlocks.PETRIFIED_WOOD_ROCK_4);
        block(ModBlocks.PETRIFIED_WOOD_ROCK_5);
        block(ModBlocks.PETRIFIED_WOOD_ROCK_6);
        block(ModBlocks.PETRIFIED_BARK_RED);
        block(ModBlocks.PETRIFIED_BARK_BROWN);
        //block(ModBlocks.DUST_LAYER.getId().toString(), modLoc("block/dust_layer"));
        block(ModBlocks.DUST);
        block(ModBlocks.DUNG);
        block(ModBlocks.JADE_BLOCK);
        block(ModBlocks.MUD);
        block(ModBlocks.QUICK_SAND);
        block(ModBlocks.RED_GEM);
        block(ModBlocks.GHOST_SAND);
        block(ModBlocks.JADE_BERRY_BUSH);
        block(ModBlocks.HEART_BERRY_BUSH);
        block(ModBlocks.PRICKLY_PEAR);
        block(ModBlocks.THORNS);
        block(ModBlocks.HANGING_WEB);
        block(ModBlocks.SILK);
        block(ModBlocks.MIR_BRICK);
        block(ModBlocks.PLANKS_PETRIFIED_WOOD);
        block(ModBlocks.REIN_EXO);
        block(ModBlocks.MUD_BRICK);
        block(ModBlocks.TEMPLE_BRICK);
        block(ModBlocks.TEMPLE_PILLAR);
        block(ModBlocks.TEMPLE_TILE);
        block(ModBlocks.VOLCANIC_ROCK);
        block(ModBlocks.GNEISS);
        block(ModBlocks.LOG_BALSAM_RESINLESS);
        block(ModBlocks.BAMBOO_CRATE);
        block(ModBlocks.BAMBOO_LADDER);
        block(ModBlocks.BAMBOO_TORCH);
        block(ModBlocks.SILO_ROOF);
        block(ModBlocks.SILO_SUPPORTS);
        block(ModBlocks.GLOW_GEM_ACTIVE);
        block(ModBlocks.GLOW_GEM_INACTIVE);
        block(ModBlocks.SPIDER_SPAWNER);
        block(ModBlocks.WASP_SPAWNER);
        block(ModBlocks.ANTLION_SPAWNER);
        block(ModBlocks.DRAGON_FLY_SPAWNER);
        block(ModBlocks.ZOMBIE_ANT_SPAWNER);
        block(ModBlocks.MAGMA_CRAWLER_SPAWNER);
        block(ModBlocks.LOCUST_SPAWNER);
        block(ModBlocks.GIANT_LILY_PAD);
        block(ModBlocks.WASP_NEST);
        block(ModBlocks.ANTLION_EGG);
        block(ModBlocks.TARANTULA_EGG);
        block(ModBlocks.CAPSTONE);
        block(ModBlocks.ANT_HILL_BLOCK);
        block(ModBlocks.FORCE_FIELD);
        block(ModBlocks.FORCE_LOCK);
    }
}
