package erebus.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static erebus.registries.ModBlocks.*;

public class ModBlockStates extends ModBlockStateProvider {

    public ModBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ALGAE.get(), models().getExistingFile(modLoc("block/algae")));

        // MARK: Amber

        blockTranslucent(AMBER);
        blockTranslucent(AMBER_BRICKS);

        // MARK: Umberstone

        block(UMBERSTONE);
        block(UMBERGRAVEL);
        block(UMBERPAVER);
        log(UMBERSTONE_PILLAR, "umberstone_pillar");

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
        wallTranslucent(WALL_AMBER, AMBER);
        wallTranslucent(WALL_AMBER_BRICKS, AMBER_BRICKS);

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

        // MARK: TODO

        //simpleBlock(AMBER_DOOR);
        //simpleBlock(PLANKS);
        blockTranslucent(PORTAL);
        //simpleBlock(GAEAN_KEYSTONE);
        block(PETRIFIED_WOOD_ROCK);
        block(PETRIFIED_WOOD_ROCK_2);
        block(PETRIFIED_WOOD_ROCK_3);
        block(PETRIFIED_WOOD_ROCK_4);
        block(PETRIFIED_WOOD_ROCK_5);
        block(PETRIFIED_WOOD_ROCK_6);
        block(PETRIFIED_BARK_RED);
        block(PETRIFIED_BARK_BROWN);
        //block(DUST_LAYER);
        block(DUST);
        block(DUNG);
        block(JADE_BLOCK);
        //block(PRESERVED_BLOCK);
        block(MUD);
        block(QUICK_SAND);
        block(RED_GEM);
        //block(SWAMP_VENT);
        block(GHOST_SAND);
        //block(CROP_TURNIP);
        //block(CROP_CABBAGE);
        //block(CROP_MANDRAKE);
        block(JADE_BERRY_BUSH);
        block(HEART_BERRY_BUSH);
        //block(SWAMP_BERRY_BUSH);
        //block(DARK_FRUIT_VINE);
        block(PRICKLY_PEAR);
        //block(GIANT_FLOWER);
        //block(GIANT_FLOWER_STIGMA);
        //block(PLANTED_FLOWER);
        //block(SMALL_PLANT);
        block(THORNS);
        block(HANGING_WEB);
        //block(DOUBLE_PLANT);
        //block(WALL_PLANTS);
        //block(WALL_PLANTS_CULTIVATED);
        //block(HONEY_TREAT);
        //block(DARK_CAPPED_MUSHROOM);
        //block(SARCASTIC_CZECH_MUSHROOM);
        //block(GRANDMAS_SHOES_MUSHROOM);
        //block(DUTCH_CAP_MUSHROOM);
        //block(KAIZERS_FINGERS_MUSHROOM);
        //block(DARK_CAPPED_MUSHROOM_BLOCK);
        //block(SARCASTIC_CZECH_MUSHROOM_BLOCK);
        //block(GRANDMAS_SHOES_MUSHROOM_BLOCK);
        //block(DUTCH_CAP_MUSHROOM_BLOCK);
        //block(KAIZERS_FINGERS_MUSHROOM_BLOCK);
        //block(GLOWSHROOM);
        //block(GLOWSHROOM_STALK_MAIN);
        //block(WITHER_WEB);
        block(SILK);
        block(MIR_BRICK);
        block(PLANKS_PETRIFIED_WOOD);
        //block(DOOR_PETRIFIED_WOOD);
        block(REIN_EXO);
        block(MUD_BRICK);
        block(TEMPLE_BRICK);
        block(TEMPLE_PILLAR);
        block(TEMPLE_TILE);
        block(VOLCANIC_ROCK);
        block(GNEISS);
        //block(GNEISS_VENT);
        //block(HOLLOW_LOG);
        block(LOG_BALSAM_RESINLESS);
        //block(UMBER_FURNACE);
        //block(UMBER_FURNACE_ACTIVE);
        //block(PETRIFIED_CRAFTING_TABLE);
        block(BAMBOO_CRATE);
        //block(BAMBOO_BRIDGE);
        block(BAMBOO_LADDER);
        //block(BAMBOO_NERD_POLE);
        //block(BAMBOO_EXTENDER);
        block(BAMBOO_TORCH);
        //block(BAMBOO_PIPE);
        //block(BAMBOO_PIPE_EXTRACT);
        //block(BAMBOO_PIPE_EXTRACT_ACTIVE);
        //block(LIQUIFIER);
        block(SILO_ROOF);
        //block(SILO_TANK);
        block(SILO_SUPPORTS);
        //block(HONEY_COMB);
        //block(UMBER_GOLEM_STATUE);
        //block(INSECT_REPELLENT);
        //block(PETRIFIED_WOOD_CHEST);
        //block(GLOWING_JAR);
        //block(FLUID_JAR);
        //block(COMPOSTER);
        //block(SMOOTHIE_MAKER);
        //block(UMBERSTONE_BUTTON);
        block(GLOW_GEM_ACTIVE);
        block(GLOW_GEM_INACTIVE);
        //block(MUCUS_BOMB);
        block(SPIDER_SPAWNER);
        //block(JUMPING_SPIDER_SPAWNER);
        //block(TARANTULA_SPAWNER);
        block(WASP_SPAWNER);
        block(ANTLION_SPAWNER);
        block(DRAGON_FLY_SPAWNER);
        block(ZOMBIE_ANT_SPAWNER);
        //block(ZOMBIE_ANT_SOLDIER_SPAWNER);
        block(MAGMA_CRAWLER_SPAWNER);
        block(LOCUST_SPAWNER);
        block(GIANT_LILY_PAD);
        block(WASP_NEST);
        //block(STAIRS_WASP_NEST);
        block(ANTLION_EGG);
        block(TARANTULA_EGG);
        block(CAPSTONE);
        block(ANT_HILL_BLOCK);
        block(FORCE_FIELD);
        block(FORCE_LOCK);
        //block(TEMPLE_BRICK_UNBREAKING);
        //block(TEMPLE_TELEPORTER);
        //block(BLOCK_OF_BONES);
        //block(DUNG_SPAWNER_BOT_FLY);
        //block(DUNG_SPAWNER_FLY);
    }
}
