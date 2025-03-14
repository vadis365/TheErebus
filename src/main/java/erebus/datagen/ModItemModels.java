package erebus.datagen;

import static erebus.registries.ModBlocks.*;
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
        blockFlat(ALGAE);
        block(AMBER);
        block(AMBER_BRICKS);
        block(AMBER_GLASS);
        blockFlatWithItemTexture(AMBER_DOOR, "door_amber");

        // MARK: Umberstone

        block(UMBERSTONE);
        block(UMBERSTONE_BRICKS);
        block(UMBERCOBBLE);
        block(UMBERCOBBLE_MOSSY);
        block(UMBERCOBBLE_WEBBED);
        block(UMBERTILE_SMOOTH);
        block(UMBERTILE_SMOOTH_SMALL);
        block(UMBERGRAVEL);
        block(UMBERPAVER);
        block(UMBERPAVER_MOSSY);
        block(UMBERPAVER_WEBBED);
        block(UMBERSTONE_PILLAR);
        //block(UMBER_FURNACE);

        // MARK: Walls

        wall(WALL_UMBERSTONE, UMBERSTONE);
        wall(WALL_UMBERCOBBLE, UMBERCOBBLE);
        wall(WALL_UMBERCOBBLE_MOSSY, UMBERCOBBLE_MOSSY);
        wall(WALL_UMBERCOBBLE_WEBBED, UMBERCOBBLE_WEBBED);
        wall(WALL_UMBERSTONE_BRICKS, UMBERSTONE_BRICKS);
        wall(WALL_UMBERTILE_SMOOTH, UMBERTILE_SMOOTH);
        wall(WALL_UMBERTILE_SMOOTH_SMALL, UMBERTILE_SMOOTH_SMALL);
        wall(WALL_UMBERPAVER, UMBERPAVER);
        wall(WALL_UMBERPAVER_MOSSY, UMBERPAVER_MOSSY);
        wall(WALL_UMBERPAVER_WEBBED, UMBERPAVER_WEBBED);
        wall(WALL_AMBER, AMBER);
        wall(WALL_AMBER_BRICKS, AMBER_BRICKS);

        // MARK: Slabs

        slab(SLAB_UMBERSTONE, UMBERSTONE);
        slab(SLAB_UMBERCOBBLE, UMBERCOBBLE);
        slab(SLAB_UMBERCOBBLE_MOSSY, UMBERCOBBLE_MOSSY);
        slab(SLAB_UMBERCOBBLE_WEBBED, UMBERCOBBLE_WEBBED);
        slab(SLAB_UMBERSTONE_BRICKS, UMBERSTONE_BRICKS);
        slab(SLAB_UMBERTILE_SMOOTH, UMBERTILE_SMOOTH);
        slab(SLAB_UMBERTILE_SMOOTH_SMALL, UMBERTILE_SMOOTH_SMALL);
        slab(SLAB_UMBERPAVER, UMBERPAVER);
        slab(SLAB_AMBER, AMBER);
        slab(SLAB_AMBER_BRICKS, AMBER_BRICKS);
        slab(SLAB_UMBERPAVER_MOSSY, UMBERPAVER_MOSSY);
        slab(SLAB_UMBERPAVER_WEBBED, UMBERPAVER_WEBBED);
        slab(SLAB_MIR_BRICKS, MIR_BRICK);
        slab(SLAB_PLANKS_PETRIFIED_WOOD, PLANKS_PETRIFIED_WOOD);
        slab(SLAB_MUD_BRICKS, MUD_BRICK);

        // MARK: Stairs

        stairs(STAIRS_UMBERSTONE, UMBERSTONE);
        stairs(STAIRS_UMBERCOBBLE, UMBERCOBBLE);
        stairs(STAIRS_UMBERCOBBLE_MOSSY, UMBERCOBBLE_MOSSY);
        stairs(STAIRS_UMBERCOBBLE_WEBBED, UMBERCOBBLE_WEBBED);
        stairs(STAIRS_UMBERSTONE_BRICKS, UMBERSTONE_BRICKS);
        stairs(STAIRS_UMBERTILE_SMOOTH, UMBERTILE_SMOOTH);
        stairs(STAIRS_UMBERTILE_SMOOTH_SMALL, UMBERTILE_SMOOTH_SMALL);
        stairs(STAIRS_UMBERPAVER, UMBERPAVER);
        stairs(STAIRS_UMBERPAVER_MOSSY, UMBERPAVER_MOSSY);
        stairs(STAIRS_UMBERPAVER_WEBBED, UMBERPAVER_WEBBED);
        stairs(STAIRS_AMBER, AMBER);
        stairs(STAIRS_AMBER_BRICKS, AMBER_BRICKS);
        stairs(STAIRS_PETRIFIED_WOOD, PLANKS_PETRIFIED_WOOD);
        stairs(STAIRS_MUD_BRICKS, MUD_BRICK);
        stairs(STAIRS_MIR_BRICKS, MIR_BRICK);

        // MARK: Ores

        block(ORE_IRON);
        block(ORE_GOLD);
        block(ORE_COAL);
        block(ORE_DIAMOND);
        block(ORE_EMERALD);
        block(ORE_LAPIS);
        block(ORE_QUARTZ);
        block(ORE_PETRIFIED_QUARTZ);
        block(ORE_COPPER);
        block(ORE_SILVER);
        block(ORE_TIN);
        block(ORE_LEAD);
        block(ORE_ALUMINUM);
        block(ORE_JADE);
        block(ORE_ENCRUSTED_DIAMOND);
        block(ORE_FOSSIL);
        block(ORE_GNEISS);
        block(ORE_PETRIFIED_WOOD);
        block(ORE_TEMPLE);


        block(PORTAL);
        block(GAEAN_KEYSTONE);

        block(PETRIFIED_WOOD_ROCK);
        block(PETRIFIED_WOOD_ROCK_2);
        block(PETRIFIED_WOOD_ROCK_3);
        block(PETRIFIED_WOOD_ROCK_4);
        block(PETRIFIED_WOOD_ROCK_5);
        block(PETRIFIED_WOOD_ROCK_6);
        block(PETRIFIED_BARK_RED);
        block(PETRIFIED_BARK_BROWN);
        //block(DUST_LAYER.getId().toString(), modLoc("block/dust_layer"));
        block(DUST);
        block(DUNG);
        block(JADE_BLOCK);
        block(MUD);
        block(QUICK_SAND);
        block(RED_GEM);
        block(GHOST_SAND);
        block(JADE_BERRY_BUSH);
        block(HEART_BERRY_BUSH);
        block(PRICKLY_PEAR);
        block(THORNS);
        block(HANGING_WEB);
        block(SILK);
        block(MIR_BRICK);
        block(PLANKS_PETRIFIED_WOOD);
        block(REIN_EXO);
        block(MUD_BRICK);
        block(TEMPLE_BRICK);
        block(TEMPLE_PILLAR);
        block(TEMPLE_TILE);
        block(VOLCANIC_ROCK);
        block(GNEISS);
        block(LOG_BALSAM_RESINLESS);
        block(BAMBOO_CRATE);
        block(BAMBOO_LADDER);
        block(BAMBOO_TORCH);
        block(SILO_ROOF);
        block(SILO_SUPPORTS);
        block(GLOW_GEM_ACTIVE);
        block(GLOW_GEM_INACTIVE);
        block(SPIDER_SPAWNER);
        block(WASP_SPAWNER);
        block(ANTLION_SPAWNER);
        block(DRAGON_FLY_SPAWNER);
        block(ZOMBIE_ANT_SPAWNER);
        block(MAGMA_CRAWLER_SPAWNER);
        block(LOCUST_SPAWNER);
        block(GIANT_LILY_PAD);
        block(WASP_NEST);
        block(ANTLION_EGG);
        block(TARANTULA_EGG);
        block(CAPSTONE);
        block(ANT_HILL_BLOCK);
        block(FORCE_FIELD);
        block(FORCE_LOCK);
    }
}
