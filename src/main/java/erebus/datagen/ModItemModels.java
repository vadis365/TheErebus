package erebus.datagen;

import erebus.registries.ModBlockItems;
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

        blockFlat(ModBlocks.ALGAE);
        block(ModBlocks.AMBER);
        block(ModBlocks.AMBER_BRICKS);
        block(ModBlocks.AMBER_GLASS);

        // MARK: Umberstone

        block(ModBlocks.UMBERSTONE);
        block(ModBlocks.UMBERPAVER);
        block(ModBlocks.UMBERGRAVEL);

        withExistingParent(ModBlockItems.UMBERSTONE.getId().toString(), modLoc("block/umberstone"));
        withExistingParent(ModBlockItems.UMBERGRAVEL.getId().toString(), modLoc("block/umbergravel"));
        withExistingParent(ModBlockItems.UMBERPAVER.getId().toString(), modLoc("block/umberpaver"));
        withExistingParent(ModBlockItems.UMBERSTONE_PILLAR.getId().toString(), modLoc("block/umberstone_pillar"));
        //withExistingParent(ModBlockItems.UMBER_FURNACE.getId().toString(), modLoc("block/umber_furnace"));
        //withExistingParent(ModBlockItems.UMBER_FURNACE_ACTIVE.getId().toString(), modLoc("block/umber_furnace_active"));

        //withExistingParent(ModBlockItems.AMBER_GLASS.getId().toString(), modLoc("block/amber_glass"));
        //withExistingParent(ModBlockItems.AMBER_DOOR.getId().toString(), modLoc("block/amber_door"));
        //withExistingParent(ModBlockItems.PLANKS.getId().toString(), modLoc("block/planks"));
        withExistingParent(ModBlockItems.PORTAL.getId().toString(), modLoc("block/portal"));
        //withExistingParent(ModBlockItems.GAEAN_KEYSTONE.getId().toString(), modLoc("block/gaean_keystone"));
        withExistingParent(ModBlockItems.PETRIFIED_WOOD_ROCK.getId().toString(), modLoc("block/petrified_wood_rock"));
        withExistingParent(ModBlockItems.PETRIFIED_WOOD_ROCK_2.getId().toString(), modLoc("block/petrified_wood_rock_2"));
        withExistingParent(ModBlockItems.PETRIFIED_WOOD_ROCK_3.getId().toString(), modLoc("block/petrified_wood_rock_3"));
        withExistingParent(ModBlockItems.PETRIFIED_WOOD_ROCK_4.getId().toString(), modLoc("block/petrified_wood_rock_4"));
        withExistingParent(ModBlockItems.PETRIFIED_WOOD_ROCK_5.getId().toString(), modLoc("block/petrified_wood_rock_5"));
        withExistingParent(ModBlockItems.PETRIFIED_WOOD_ROCK_6.getId().toString(), modLoc("block/petrified_wood_rock_6"));
        withExistingParent(ModBlockItems.PETRIFIED_BARK_RED.getId().toString(), modLoc("block/petrified_bark_red"));
        withExistingParent(ModBlockItems.PETRIFIED_BARK_BROWN.getId().toString(), modLoc("block/petrified_bark_brown"));
        //withExistingParent(ModBlockItems.DUST_LAYER.getId().toString(), modLoc("block/dust_layer"));
        withExistingParent(ModBlockItems.DUST.getId().toString(), modLoc("block/dust"));
        withExistingParent(ModBlockItems.DUNG.getId().toString(), modLoc("block/dung"));
        withExistingParent(ModBlockItems.ORE_IRON.getId().toString(), modLoc("block/ore_iron"));
        withExistingParent(ModBlockItems.ORE_GOLD.getId().toString(), modLoc("block/ore_gold"));
        withExistingParent(ModBlockItems.ORE_COAL.getId().toString(), modLoc("block/ore_coal"));
        withExistingParent(ModBlockItems.ORE_DIAMOND.getId().toString(), modLoc("block/ore_diamond"));
        withExistingParent(ModBlockItems.ORE_EMERALD.getId().toString(), modLoc("block/ore_emerald"));
        withExistingParent(ModBlockItems.ORE_LAPIS.getId().toString(), modLoc("block/ore_lapis"));
        withExistingParent(ModBlockItems.ORE_QUARTZ.getId().toString(), modLoc("block/ore_quartz"));
        withExistingParent(ModBlockItems.ORE_PETRIFIED_QUARTZ.getId().toString(), modLoc("block/ore_petrified_quartz"));
        withExistingParent(ModBlockItems.ORE_COPPER.getId().toString(), modLoc("block/ore_copper"));
        withExistingParent(ModBlockItems.ORE_SILVER.getId().toString(), modLoc("block/ore_silver"));
        withExistingParent(ModBlockItems.ORE_TIN.getId().toString(), modLoc("block/ore_tin"));
        withExistingParent(ModBlockItems.ORE_LEAD.getId().toString(), modLoc("block/ore_lead"));
        withExistingParent(ModBlockItems.ORE_ALUMINUM.getId().toString(), modLoc("block/ore_aluminum"));
        withExistingParent(ModBlockItems.ORE_JADE.getId().toString(), modLoc("block/ore_jade"));
        withExistingParent(ModBlockItems.ORE_ENCRUSTED_DIAMOND.getId().toString(), modLoc("block/ore_encrusted_diamond"));
        withExistingParent(ModBlockItems.ORE_FOSSIL.getId().toString(), modLoc("block/ore_fossil"));
        withExistingParent(ModBlockItems.ORE_GNEISS.getId().toString(), modLoc("block/ore_gneiss"));
        withExistingParent(ModBlockItems.ORE_PETRIFIED_WOOD.getId().toString(), modLoc("block/ore_petrified_wood"));
        withExistingParent(ModBlockItems.ORE_TEMPLE.getId().toString(), modLoc("block/ore_temple"));
        withExistingParent(ModBlockItems.JADE_BLOCK.getId().toString(), modLoc("block/jade_block"));
        //withExistingParent(ModBlockItems.PRESERVED_BLOCK.getId().toString(), modLoc("block/preserved_block"));
        withExistingParent(ModBlockItems.MUD.getId().toString(), modLoc("block/mud"));
        withExistingParent(ModBlockItems.QUICK_SAND.getId().toString(), modLoc("block/quick_sand"));
        withExistingParent(ModBlockItems.RED_GEM.getId().toString(), modLoc("block/red_gem"));
        //withExistingParent(ModBlockItems.SWAMP_VENT.getId().toString(), modLoc("block/swamp_vent"));
        withExistingParent(ModBlockItems.GHOST_SAND.getId().toString(), modLoc("block/ghost_sand"));
        //withExistingParent(ModBlockItems.CROP_TURNIP.getId().toString(), modLoc("block/crop_turnip"));
        //withExistingParent(ModBlockItems.CROP_CABBAGE.getId().toString(), modLoc("block/crop_cabbage"));
        //withExistingParent(ModBlockItems.CROP_MANDRAKE.getId().toString(), modLoc("block/crop_mandrake"));
        withExistingParent(ModBlockItems.JADE_BERRY_BUSH.getId().toString(), modLoc("block/jade_berry_bush"));
        withExistingParent(ModBlockItems.HEART_BERRY_BUSH.getId().toString(), modLoc("block/heart_berry_bush"));
        //withExistingParent(ModBlockItems.SWAMP_BERRY_BUSH.getId().toString(), modLoc("block/swamp_berry_bush"));
        //withExistingParent(ModBlockItems.DARK_FRUIT_VINE.getId().toString(), modLoc("block/dark_fruit_vine"));
        withExistingParent(ModBlockItems.PRICKLY_PEAR.getId().toString(), modLoc("block/prickly_pear"));
        //withExistingParent(ModBlockItems.GIANT_FLOWER.getId().toString(), modLoc("block/giant_flower"));
        //withExistingParent(ModBlockItems.GIANT_FLOWER_STIGMA.getId().toString(), modLoc("block/giant_flower_stigma"));
        //withExistingParent(ModBlockItems.PLANTED_FLOWER.getId().toString(), modLoc("block/planted_flower"));
        //withExistingParent(ModBlockItems.SMALL_PLANT.getId().toString(), modLoc("block/small_plant"));
        withExistingParent(ModBlockItems.THORNS.getId().toString(), modLoc("block/thorns"));
        withExistingParent(ModBlockItems.HANGING_WEB.getId().toString(), modLoc("block/hanging_web"));
        //withExistingParent(ModBlockItems.DOUBLE_PLANT.getId().toString(), modLoc("block/double_plant"));
        //withExistingParent(ModBlockItems.WALL_PLANTS.getId().toString(), modLoc("block/wall_plants"));
        //withExistingParent(ModBlockItems.WALL_PLANTS_CULTIVATED.getId().toString(), modLoc("block/wall_plants_cultivated"));
        //withExistingParent(ModBlockItems.HONEY_TREAT.getId().toString(), modLoc("block/honey_treat"));
        //withExistingParent(ModBlockItems.DARK_CAPPED_MUSHROOM.getId().toString(), modLoc("block/dark_capped_mushroom"));
        //withExistingParent(ModBlockItems.SARCASTIC_CZECH_MUSHROOM.getId().toString(), modLoc("block/sarcastic_czech_mushroom"));
        //withExistingParent(ModBlockItems.GRANDMAS_SHOES_MUSHROOM.getId().toString(), modLoc("block/grandmas_shoes_mushroom"));
        //withExistingParent(ModBlockItems.DUTCH_CAP_MUSHROOM.getId().toString(), modLoc("block/dutch_cap_mushroom"));
        //withExistingParent(ModBlockItems.KAIZERS_FINGERS_MUSHROOM.getId().toString(), modLoc("block/kaizers_fingers_mushroom"));
        //withExistingParent(ModBlockItems.DARK_CAPPED_MUSHROOM_BLOCK.getId().toString(), modLoc("block/dark_capped_mushroom_block"));
        //withExistingParent(ModBlockItems.SARCASTIC_CZECH_MUSHROOM_BLOCK.getId().toString(), modLoc("block/sarcastic_czech_mushroom_block"));
        //withExistingParent(ModBlockItems.GRANDMAS_SHOES_MUSHROOM_BLOCK.getId().toString(), modLoc("block/grandmas_shoes_mushroom_block"));
        //withExistingParent(ModBlockItems.DUTCH_CAP_MUSHROOM_BLOCK.getId().toString(), modLoc("block/dutch_cap_mushroom_block"));
        //withExistingParent(ModBlockItems.KAIZERS_FINGERS_MUSHROOM_BLOCK.getId().toString(), modLoc("block/kaizers_fingers_mushroom_block"));
        //withExistingParent(ModBlockItems.GLOWSHROOM.getId().toString(), modLoc("block/glowshroom"));
        //withExistingParent(ModBlockItems.GLOWSHROOM_STALK_MAIN.getId().toString(), modLoc("block/glowshroom_stalk_main"));
        //withExistingParent(ModBlockItems.WITHER_WEB.getId().toString(), modLoc("block/wither_web"));
        withExistingParent(ModBlockItems.SILK.getId().toString(), modLoc("block/silk"));
        withExistingParent(ModBlockItems.MIR_BRICK.getId().toString(), modLoc("block/mir_brick"));
        //withExistingParent(ModBlockItems.SLAB_MIR_BRICKS.getId().toString(), modLoc("block/slab_mir_bricks"));
        withExistingParent(ModBlockItems.PLANKS_PETRIFIED_WOOD.getId().toString(), modLoc("block/planks_petrified_wood"));
        //withExistingParent(ModBlockItems.SLAB_PLANKS_PETRIFIED_WOOD.getId().toString(), modLoc("block/slab_planks_petrified_wood"));
        //withExistingParent(ModBlockItems.DOOR_PETRIFIED_WOOD.getId().toString(), modLoc("block/door_petrified_wood"));
        withExistingParent(ModBlockItems.REIN_EXO.getId().toString(), modLoc("block/rein_exo"));
        withExistingParent(ModBlockItems.MUD_BRICK.getId().toString(), modLoc("block/mud_brick"));
        //withExistingParent(ModBlockItems.SLAB_MUD_BRICKS.getId().toString(), modLoc("block/slab_mud_bricks"));
        withExistingParent(ModBlockItems.TEMPLE_BRICK.getId().toString(), modLoc("block/temple_brick"));
        withExistingParent(ModBlockItems.TEMPLE_PILLAR.getId().toString(), modLoc("block/temple_pillar"));
        withExistingParent(ModBlockItems.TEMPLE_TILE.getId().toString(), modLoc("block/temple_tile"));
        withExistingParent(ModBlockItems.VOLCANIC_ROCK.getId().toString(), modLoc("block/volcanic_rock"));
        withExistingParent(ModBlockItems.GNEISS.getId().toString(), modLoc("block/gneiss"));
        //withExistingParent(ModBlockItems.GNEISS_VENT.getId().toString(), modLoc("block/gneiss_vent"));
        //withExistingParent(ModBlockItems.HOLLOW_LOG.getId().toString(), modLoc("block/hollow_log"));
        withExistingParent(ModBlockItems.LOG_BALSAM_RESINLESS.getId().toString(), modLoc("block/log_balsam_resinless"));
        //withExistingParent(ModBlockItems.PETRIFIED_CRAFTING_TABLE.getId().toString(), modLoc("block/petrified_crafting_table"));
        withExistingParent(ModBlockItems.BAMBOO_CRATE.getId().toString(), modLoc("block/bamboo_crate"));
        //withExistingParent(ModBlockItems.BAMBOO_BRIDGE.getId().toString(), modLoc("block/bamboo_bridge"));
        withExistingParent(ModBlockItems.BAMBOO_LADDER.getId().toString(), modLoc("block/bamboo_ladder"));
        //withExistingParent(ModBlockItems.BAMBOO_NERD_POLE.getId().toString(), modLoc("block/bamboo_nerd_pole"));
        //withExistingParent(ModBlockItems.BAMBOO_EXTENDER.getId().toString(), modLoc("block/bamboo_extender"));
        withExistingParent(ModBlockItems.BAMBOO_TORCH.getId().toString(), modLoc("block/bamboo_torch"));
        //withExistingParent(ModBlockItems.BAMBOO_PIPE.getId().toString(), modLoc("block/bamboo_pipe"));
        //withExistingParent(ModBlockItems.BAMBOO_PIPE_EXTRACT.getId().toString(), modLoc("block/bamboo_pipe_extract"));
        //withExistingParent(ModBlockItems.BAMBOO_PIPE_EXTRACT_ACTIVE.getId().toString(), modLoc("block/bamboo_pipe_extract_active"));
        //withExistingParent(ModBlockItems.LIQUIFIER.getId().toString(), modLoc("block/liquifier"));
        withExistingParent(ModBlockItems.SILO_ROOF.getId().toString(), modLoc("block/silo_roof"));
        //withExistingParent(ModBlockItems.SILO_TANK.getId().toString(), modLoc("block/silo_tank"));
        withExistingParent(ModBlockItems.SILO_SUPPORTS.getId().toString(), modLoc("block/silo_supports"));
        //withExistingParent(ModBlockItems.HONEY_COMB.getId().toString(), modLoc("block/honey_comb"));
        //withExistingParent(ModBlockItems.UMBER_GOLEM_STATUE.getId().toString(), modLoc("block/umber_golem_statue"));
        //withExistingParent(ModBlockItems.INSECT_REPELLENT.getId().toString(), modLoc("block/insect_repellent"));
        //withExistingParent(ModBlockItems.PETRIFIED_WOOD_CHEST.getId().toString(), modLoc("block/petrified_wood_chest"));
        //withExistingParent(ModBlockItems.GLOWING_JAR.getId().toString(), modLoc("block/glowing_jar"));
        //withExistingParent(ModBlockItems.FLUID_JAR.getId().toString(), modLoc("block/fluid_jar"));
        //withExistingParent(ModBlockItems.COMPOSTER.getId().toString(), modLoc("block/composter"));
        //withExistingParent(ModBlockItems.SMOOTHIE_MAKER.getId().toString(), modLoc("block/smoothie_maker"));
        //withExistingParent(ModBlockItems.UMBERSTONE_BUTTON.getId().toString(), modLoc("block/umberstone_button"));
        withExistingParent(ModBlockItems.GLOW_GEM_ACTIVE.getId().toString(), modLoc("block/glow_gem_active"));
        withExistingParent(ModBlockItems.GLOW_GEM_INACTIVE.getId().toString(), modLoc("block/glow_gem_inactive"));
        //withExistingParent(ModBlockItems.MUCUS_BOMB.getId().toString(), modLoc("block/mucus_bomb"));
        withExistingParent(ModBlockItems.SPIDER_SPAWNER.getId().toString(), modLoc("block/spider_spawner"));
        //withExistingParent(ModBlockItems.JUMPING_SPIDER_SPAWNER.getId().toString(), modLoc("block/jumping_spider_spawner"));
        //withExistingParent(ModBlockItems.TARANTULA_SPAWNER.getId().toString(), modLoc("block/tarantula_spawner"));
        withExistingParent(ModBlockItems.WASP_SPAWNER.getId().toString(), modLoc("block/wasp_spawner"));
        withExistingParent(ModBlockItems.ANTLION_SPAWNER.getId().toString(), modLoc("block/antlion_spawner"));
        withExistingParent(ModBlockItems.DRAGON_FLY_SPAWNER.getId().toString(), modLoc("block/dragon_fly_spawner"));
        withExistingParent(ModBlockItems.ZOMBIE_ANT_SPAWNER.getId().toString(), modLoc("block/zombie_ant_spawner"));
        //withExistingParent(ModBlockItems.ZOMBIE_ANT_SOLDIER_SPAWNER.getId().toString(), modLoc("block/zombie_ant_soldier_spawner"));
        withExistingParent(ModBlockItems.MAGMA_CRAWLER_SPAWNER.getId().toString(), modLoc("block/magma_crawler_spawner"));
        withExistingParent(ModBlockItems.LOCUST_SPAWNER.getId().toString(), modLoc("block/locust_spawner"));
        withExistingParent(ModBlockItems.GIANT_LILY_PAD.getId().toString(), modLoc("block/giant_lily_pad"));
        withExistingParent(ModBlockItems.WASP_NEST.getId().toString(), modLoc("block/wasp_nest"));
        //withExistingParent(ModBlockItems.STAIRS_WASP_NEST.getId().toString(), modLoc("block/stairs_wasp_nest"));
        withExistingParent(ModBlockItems.ANTLION_EGG.getId().toString(), modLoc("block/antlion_egg"));
        withExistingParent(ModBlockItems.TARANTULA_EGG.getId().toString(), modLoc("block/tarantula_egg"));
        withExistingParent(ModBlockItems.CAPSTONE.getId().toString(), modLoc("block/capstone"));
        withExistingParent(ModBlockItems.ANT_HILL_BLOCK.getId().toString(), modLoc("block/ant_hill_block"));
        withExistingParent(ModBlockItems.FORCE_FIELD.getId().toString(), modLoc("block/force_field"));
        withExistingParent(ModBlockItems.FORCE_LOCK.getId().toString(), modLoc("block/force_lock"));
        //withExistingParent(ModBlockItems.TEMPLE_BRICK_UNBREAKING.getId().toString(), modLoc("block/temple_brick_unbreaking"));
        //withExistingParent(ModBlockItems.TEMPLE_TELEPORTER.getId().toString(), modLoc("block/temple_teleporter"));
        //withExistingParent(ModBlockItems.BLOCK_OF_BONES.getId().toString(), modLoc("block/block_of_bones"));
        //withExistingParent(ModBlockItems.DUNG_SPAWNER_BOT_FLY.getId().toString(), modLoc("block/dung_spawner_bot_fly"));
        //withExistingParent(ModBlockItems.DUNG_SPAWNER_FLY.getId().toString(), modLoc("block/dung_spawner_fly"));
        //withExistingParent(ModBlockItems.WALL_UMBERSTONE.getId().toString(), modLoc("block/wall_umberstone"));
        //withExistingParent(ModBlockItems.WALL_UMBERCOBBLE.getId().toString(), modLoc("block/wall_umbercobble"));
        //withExistingParent(ModBlockItems.WALL_UMBERCOBBLE_MOSSY.getId().toString(), modLoc("block/wall_umbercobble_mossy"));
        //withExistingParent(ModBlockItems.WALL_UMBERCOBBLE_WEBBED.getId().toString(), modLoc("block/wall_umbercobble_webbed"));
        //withExistingParent(ModBlockItems.WALL_UMBERSTONE_BRICKS.getId().toString(), modLoc("block/wall_umberstone_bricks"));
        //withExistingParent(ModBlockItems.WALL_UMBERTILE_SMOOTH.getId().toString(), modLoc("block/wall_umbertile_smooth"));
        //withExistingParent(ModBlockItems.WALL_UMBERTILE_SMOOTH_SMALL.getId().toString(), modLoc("block/wall_umbertile_smooth_small"));
        //withExistingParent(ModBlockItems.WALL_UMBERPAVER.getId().toString(), modLoc("block/wall_umberpaver"));
        //withExistingParent(ModBlockItems.WALL_UMBERPAVER_MOSSY.getId().toString(), modLoc("block/wall_umberpaver_mossy"));
        //withExistingParent(ModBlockItems.WALL_UMBERPAVER_WEBBED.getId().toString(), modLoc("block/wall_umberpaver_webbed"));
        //withExistingParent(ModBlockItems.WALL_AMBER.getId().toString(), modLoc("block/wall_amber"));
        //withExistingParent(ModBlockItems.WALL_AMBER_BRICKS.getId().toString(), modLoc("block/wall_amber_bricks"));
        //withExistingParent(ModBlockItems.SLAB_UMBERSTONE.getId().toString(), modLoc("block/slab_umberstone"));
        //withExistingParent(ModBlockItems.SLAB_UMBERCOBBLE.getId().toString(), modLoc("block/slab_umbercobble"));
        //withExistingParent(ModBlockItems.SLAB_UMBERCOBBLE_MOSSY.getId().toString(), modLoc("block/slab_umbercobble_mossy"));
        //withExistingParent(ModBlockItems.SLAB_UMBERCOBBLE_WEBBED.getId().toString(), modLoc("block/slab_umbercobble_webbed"));
        //withExistingParent(ModBlockItems.SLAB_UMBERSTONE_BRICKS.getId().toString(), modLoc("block/slab_umberstone_bricks"));
        //withExistingParent(ModBlockItems.SLAB_UMBERTILE_SMOOTH.getId().toString(), modLoc("block/slab_umbertile_smooth"));
        //withExistingParent(ModBlockItems.SLAB_UMBERTILE_SMOOTH_SMALL.getId().toString(), modLoc("block/slab_umbertile_smooth_small"));
        //withExistingParent(ModBlockItems.SLAB_UMBERPAVER.getId().toString(), modLoc("block/slab_umberpaver"));
        //withExistingParent(ModBlockItems.SLAB_AMBER.getId().toString(), modLoc("block/slab_amber"));
        //withExistingParent(ModBlockItems.SLAB_UMBERPAVER_MOSSY.getId().toString(), modLoc("block/slab_umberpaver_mossy"));
        //withExistingParent(ModBlockItems.SLAB_UMBERPAVER_WEBBED.getId().toString(), modLoc("block/slab_umberpaver_webbed"));
        //withExistingParent(ModBlockItems.SLAB_AMBER_BRICKS.getId().toString(), modLoc("block/slab_amber_bricks"));
        //withExistingParent(ModBlockItems.STAIRS_UMBERCOBBLE.getId().toString(), modLoc("block/stairs_umbercobble"));
        //withExistingParent(ModBlockItems.STAIRS_UMBERCOBBLE_MOSSY.getId().toString(), modLoc("block/stairs_umbercobble_mossy"));
        //withExistingParent(ModBlockItems.STAIRS_UMBERCOBBLE_WEBBED.getId().toString(), modLoc("block/stairs_umbercobble_webbed"));
        //withExistingParent(ModBlockItems.STAIRS_UMBERSTONE_BRICKS.getId().toString(), modLoc("block/stairs_umberstone_bricks"));
        //withExistingParent(ModBlockItems.STAIRS_UMBERTILE_SMOOTH.getId().toString(), modLoc("block/stairs_umbertile_smooth"));
        //withExistingParent(ModBlockItems.STAIRS_UMBERTILE_SMOOTH_SMALL.getId().toString(), modLoc("block/stairs_umbertile_smooth_small"));
        //withExistingParent(ModBlockItems.STAIRS_UMBERPAVER.getId().toString(), modLoc("block/stairs_umberpaver"));
        //withExistingParent(ModBlockItems.STAIRS_UMBERPAVER_MOSSY.getId().toString(), modLoc("block/stairs_umberpaver_mossy"));
        //withExistingParent(ModBlockItems.STAIRS_UMBERPAVER_WEBBED.getId().toString(), modLoc("block/stairs_umberpaver_webbed"));
        //withExistingParent(ModBlockItems.STAIRS_AMBER.getId().toString(), modLoc("block/stairs_amber"));
        //withExistingParent(ModBlockItems.STAIRS_AMBER_BRICKS.getId().toString(), modLoc("block/stairs_amber_bricks"));
        //withExistingParent(ModBlockItems.STAIRS_PETRIFIED_WOOD.getId().toString(), modLoc("block/stairs_petrified_wood"));
        //withExistingParent(ModBlockItems.STAIRS_MUD_BRICKS.getId().toString(), modLoc("block/mud_bricks"));
        //withExistingParent(ModBlockItems.STAIRS_MIR_BRICKS.getId().toString(), modLoc("block/mir_bricks"));
    }
}
