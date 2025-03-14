package erebus.datagen;

import erebus.registries.ModBlocks;
import erebus.registries.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

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
        block(ModBlocks.UMBER_FURNACE);

        block(ModBlocks.PORTAL);
        block(ModBlocks.GAEAN_KEYSTONE);

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
