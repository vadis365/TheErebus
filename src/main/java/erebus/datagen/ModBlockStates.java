package erebus.datagen;

import erebus.registries.ModBlockItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static erebus.registries.ModBlocks.*;

public class ModBlockStates extends ModBlockStateProvider {

    public ModBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlockItems.ALGAE.get().getBlock(), models().getExistingFile(modLoc("block/algae")));

        // MARK: Amber

        blockTranslucent(AMBER);
        blockTranslucent(AMBER_BRICKS);

        // MARK: Umberstone

        block(UMBERSTONE);
        block(UMBERGRAVEL);
        block(UMBERPAVER);
        log(UMBERSTONE_PILLAR, "umberstone_pillar");

        // MARK: TODO

        //simpleBlock(ModBlockItems.AMBER_DOOR.get().getBlock());
        //simpleBlock(ModBlockItems.PLANKS.get().getBlock());
        simpleBlock(ModBlockItems.PORTAL.get().getBlock());
        //simpleBlock(ModBlockItems.GAEAN_KEYSTONE.get().getBlock());
        //simpleBlock(ModBlockItems.SLAB_UMBERPAVER_MOSSY.get().getBlock());
        //simpleBlock(ModBlockItems.SLAB_UMBERPAVER_WEBBED.get().getBlock());
        simpleBlock(ModBlockItems.PETRIFIED_WOOD_ROCK.get().getBlock());
        simpleBlock(ModBlockItems.PETRIFIED_WOOD_ROCK_2.get().getBlock());
        simpleBlock(ModBlockItems.PETRIFIED_WOOD_ROCK_3.get().getBlock());
        simpleBlock(ModBlockItems.PETRIFIED_WOOD_ROCK_4.get().getBlock());
        simpleBlock(ModBlockItems.PETRIFIED_WOOD_ROCK_5.get().getBlock());
        simpleBlock(ModBlockItems.PETRIFIED_WOOD_ROCK_6.get().getBlock());
        simpleBlock(ModBlockItems.PETRIFIED_BARK_RED.get().getBlock());
        simpleBlock(ModBlockItems.PETRIFIED_BARK_BROWN.get().getBlock());
        //simpleBlock(ModBlockItems.DUST_LAYER.get().getBlock());
        simpleBlock(ModBlockItems.DUST.get().getBlock());
        simpleBlock(ModBlockItems.DUNG.get().getBlock());
        simpleBlock(ModBlockItems.ORE_IRON.get().getBlock());
        simpleBlock(ModBlockItems.ORE_GOLD.get().getBlock());
        simpleBlock(ModBlockItems.ORE_COAL.get().getBlock());
        simpleBlock(ModBlockItems.ORE_DIAMOND.get().getBlock());
        simpleBlock(ModBlockItems.ORE_EMERALD.get().getBlock());
        simpleBlock(ModBlockItems.ORE_LAPIS.get().getBlock());
        simpleBlock(ModBlockItems.ORE_QUARTZ.get().getBlock());
        simpleBlock(ModBlockItems.ORE_PETRIFIED_QUARTZ.get().getBlock());
        simpleBlock(ModBlockItems.ORE_COPPER.get().getBlock());
        simpleBlock(ModBlockItems.ORE_SILVER.get().getBlock());
        simpleBlock(ModBlockItems.ORE_TIN.get().getBlock());
        simpleBlock(ModBlockItems.ORE_LEAD.get().getBlock());
        simpleBlock(ModBlockItems.ORE_ALUMINUM.get().getBlock());
        simpleBlock(ModBlockItems.ORE_JADE.get().getBlock());
        simpleBlock(ModBlockItems.ORE_ENCRUSTED_DIAMOND.get().getBlock());
        simpleBlock(ModBlockItems.ORE_FOSSIL.get().getBlock());
        simpleBlock(ModBlockItems.ORE_GNEISS.get().getBlock());
        simpleBlock(ModBlockItems.ORE_PETRIFIED_WOOD.get().getBlock());
        simpleBlock(ModBlockItems.ORE_TEMPLE.get().getBlock());
        simpleBlock(ModBlockItems.JADE_BLOCK.get().getBlock());
        //simpleBlock(ModBlockItems.PRESERVED_BLOCK.get().getBlock());
        simpleBlock(ModBlockItems.MUD.get().getBlock());
        simpleBlock(ModBlockItems.QUICK_SAND.get().getBlock());
        simpleBlock(ModBlockItems.RED_GEM.get().getBlock());
        //simpleBlock(ModBlockItems.SWAMP_VENT.get().getBlock());
        simpleBlock(ModBlockItems.GHOST_SAND.get().getBlock());
        //simpleBlock(ModBlockItems.CROP_TURNIP.get().getBlock());
        //simpleBlock(ModBlockItems.CROP_CABBAGE.get().getBlock());
        //simpleBlock(ModBlockItems.CROP_MANDRAKE.get().getBlock());
        simpleBlock(ModBlockItems.JADE_BERRY_BUSH.get().getBlock());
        simpleBlock(ModBlockItems.HEART_BERRY_BUSH.get().getBlock());
        //simpleBlock(ModBlockItems.SWAMP_BERRY_BUSH.get().getBlock());
        //simpleBlock(ModBlockItems.DARK_FRUIT_VINE.get().getBlock());
        simpleBlock(ModBlockItems.PRICKLY_PEAR.get().getBlock());
        //simpleBlock(ModBlockItems.GIANT_FLOWER.get().getBlock());
        //simpleBlock(ModBlockItems.GIANT_FLOWER_STIGMA.get().getBlock());
        //simpleBlock(ModBlockItems.PLANTED_FLOWER.get().getBlock());
        //simpleBlock(ModBlockItems.SMALL_PLANT.get().getBlock());
        simpleBlock(ModBlockItems.THORNS.get().getBlock());
        simpleBlock(ModBlockItems.HANGING_WEB.get().getBlock());
        //simpleBlock(ModBlockItems.DOUBLE_PLANT.get().getBlock());
        //simpleBlock(ModBlockItems.WALL_PLANTS.get().getBlock());
        //simpleBlock(ModBlockItems.WALL_PLANTS_CULTIVATED.get().getBlock());
        //simpleBlock(ModBlockItems.HONEY_TREAT.get().getBlock());
        //simpleBlock(ModBlockItems.DARK_CAPPED_MUSHROOM.get().getBlock());
        //simpleBlock(ModBlockItems.SARCASTIC_CZECH_MUSHROOM.get().getBlock());
        //simpleBlock(ModBlockItems.GRANDMAS_SHOES_MUSHROOM.get().getBlock());
        //simpleBlock(ModBlockItems.DUTCH_CAP_MUSHROOM.get().getBlock());
        //simpleBlock(ModBlockItems.KAIZERS_FINGERS_MUSHROOM.get().getBlock());
        //simpleBlock(ModBlockItems.DARK_CAPPED_MUSHROOM_BLOCK.get().getBlock());
        //simpleBlock(ModBlockItems.SARCASTIC_CZECH_MUSHROOM_BLOCK.get().getBlock());
        //simpleBlock(ModBlockItems.GRANDMAS_SHOES_MUSHROOM_BLOCK.get().getBlock());
        //simpleBlock(ModBlockItems.DUTCH_CAP_MUSHROOM_BLOCK.get().getBlock());
        //simpleBlock(ModBlockItems.KAIZERS_FINGERS_MUSHROOM_BLOCK.get().getBlock());
        //simpleBlock(ModBlockItems.GLOWSHROOM.get().getBlock());
        //simpleBlock(ModBlockItems.GLOWSHROOM_STALK_MAIN.get().getBlock());
        //simpleBlock(ModBlockItems.WITHER_WEB.get().getBlock());
        simpleBlock(ModBlockItems.SILK.get().getBlock());
        simpleBlock(ModBlockItems.MIR_BRICK.get().getBlock());
        //simpleBlock(ModBlockItems.SLAB_MIR_BRICKS.get().getBlock());
        simpleBlock(ModBlockItems.PLANKS_PETRIFIED_WOOD.get().getBlock());
        //simpleBlock(ModBlockItems.SLAB_PLANKS_PETRIFIED_WOOD.get().getBlock());
        //simpleBlock(ModBlockItems.DOOR_PETRIFIED_WOOD.get().getBlock());
        simpleBlock(ModBlockItems.REIN_EXO.get().getBlock());
        simpleBlock(ModBlockItems.MUD_BRICK.get().getBlock());
        //simpleBlock(ModBlockItems.SLAB_MUD_BRICKS.get().getBlock());
        simpleBlock(ModBlockItems.TEMPLE_BRICK.get().getBlock());
        simpleBlock(ModBlockItems.TEMPLE_PILLAR.get().getBlock());
        simpleBlock(ModBlockItems.TEMPLE_TILE.get().getBlock());
        simpleBlock(ModBlockItems.VOLCANIC_ROCK.get().getBlock());
        simpleBlock(ModBlockItems.GNEISS.get().getBlock());
        //simpleBlock(ModBlockItems.GNEISS_VENT.get().getBlock());
        //simpleBlock(ModBlockItems.HOLLOW_LOG.get().getBlock());
        simpleBlock(ModBlockItems.LOG_BALSAM_RESINLESS.get().getBlock());
        //simpleBlock(ModBlockItems.UMBER_FURNACE.get().getBlock());
        //simpleBlock(ModBlockItems.UMBER_FURNACE_ACTIVE.get().getBlock());
        //simpleBlock(ModBlockItems.PETRIFIED_CRAFTING_TABLE.get().getBlock());
        simpleBlock(ModBlockItems.BAMBOO_CRATE.get().getBlock());
        //simpleBlock(ModBlockItems.BAMBOO_BRIDGE.get().getBlock());
        simpleBlock(ModBlockItems.BAMBOO_LADDER.get().getBlock());
        //simpleBlock(ModBlockItems.BAMBOO_NERD_POLE.get().getBlock());
        //simpleBlock(ModBlockItems.BAMBOO_EXTENDER.get().getBlock());
        simpleBlock(ModBlockItems.BAMBOO_TORCH.get().getBlock());
        //simpleBlock(ModBlockItems.BAMBOO_PIPE.get().getBlock());
        //simpleBlock(ModBlockItems.BAMBOO_PIPE_EXTRACT.get().getBlock());
        //simpleBlock(ModBlockItems.BAMBOO_PIPE_EXTRACT_ACTIVE.get().getBlock());
        //simpleBlock(ModBlockItems.LIQUIFIER.get().getBlock());
        simpleBlock(ModBlockItems.SILO_ROOF.get().getBlock());
        //simpleBlock(ModBlockItems.SILO_TANK.get().getBlock());
        simpleBlock(ModBlockItems.SILO_SUPPORTS.get().getBlock());
        //simpleBlock(ModBlockItems.HONEY_COMB.get().getBlock());
        //simpleBlock(ModBlockItems.UMBER_GOLEM_STATUE.get().getBlock());
        //simpleBlock(ModBlockItems.INSECT_REPELLENT.get().getBlock());
        //simpleBlock(ModBlockItems.PETRIFIED_WOOD_CHEST.get().getBlock());
        //simpleBlock(ModBlockItems.GLOWING_JAR.get().getBlock());
        //simpleBlock(ModBlockItems.FLUID_JAR.get().getBlock());
        //simpleBlock(ModBlockItems.COMPOSTER.get().getBlock());
        //simpleBlock(ModBlockItems.SMOOTHIE_MAKER.get().getBlock());
        //simpleBlock(ModBlockItems.UMBERSTONE_BUTTON.get().getBlock());
        simpleBlock(ModBlockItems.GLOW_GEM_ACTIVE.get().getBlock());
        simpleBlock(ModBlockItems.GLOW_GEM_INACTIVE.get().getBlock());
        //simpleBlock(ModBlockItems.MUCUS_BOMB.get().getBlock());
        simpleBlock(ModBlockItems.SPIDER_SPAWNER.get().getBlock());
        //simpleBlock(ModBlockItems.JUMPING_SPIDER_SPAWNER.get().getBlock());
        //simpleBlock(ModBlockItems.TARANTULA_SPAWNER.get().getBlock());
        simpleBlock(ModBlockItems.WASP_SPAWNER.get().getBlock());
        simpleBlock(ModBlockItems.ANTLION_SPAWNER.get().getBlock());
        simpleBlock(ModBlockItems.DRAGON_FLY_SPAWNER.get().getBlock());
        simpleBlock(ModBlockItems.ZOMBIE_ANT_SPAWNER.get().getBlock());
        //simpleBlock(ModBlockItems.ZOMBIE_ANT_SOLDIER_SPAWNER.get().getBlock());
        simpleBlock(ModBlockItems.MAGMA_CRAWLER_SPAWNER.get().getBlock());
        simpleBlock(ModBlockItems.LOCUST_SPAWNER.get().getBlock());
        simpleBlock(ModBlockItems.GIANT_LILY_PAD.get().getBlock());
        simpleBlock(ModBlockItems.WASP_NEST.get().getBlock());
        //simpleBlock(ModBlockItems.STAIRS_WASP_NEST.get().getBlock());
        simpleBlock(ModBlockItems.ANTLION_EGG.get().getBlock());
        simpleBlock(ModBlockItems.TARANTULA_EGG.get().getBlock());
        simpleBlock(ModBlockItems.CAPSTONE.get().getBlock());
        simpleBlock(ModBlockItems.ANT_HILL_BLOCK.get().getBlock());
        simpleBlock(ModBlockItems.FORCE_FIELD.get().getBlock());
        simpleBlock(ModBlockItems.FORCE_LOCK.get().getBlock());
        //simpleBlock(ModBlockItems.TEMPLE_BRICK_UNBREAKING.get().getBlock());
        //simpleBlock(ModBlockItems.TEMPLE_TELEPORTER.get().getBlock());
        //simpleBlock(ModBlockItems.BLOCK_OF_BONES.get().getBlock());
        //simpleBlock(ModBlockItems.DUNG_SPAWNER_BOT_FLY.get().getBlock());
        //simpleBlock(ModBlockItems.DUNG_SPAWNER_FLY.get().getBlock());
        //simpleBlock(ModBlockItems.WALL_UMBERSTONE.get().getBlock());
        //simpleBlock(ModBlockItems.WALL_UMBERCOBBLE.get().getBlock());
        //simpleBlock(ModBlockItems.WALL_UMBERCOBBLE_MOSSY.get().getBlock());
        //simpleBlock(ModBlockItems.WALL_UMBERCOBBLE_WEBBED.get().getBlock());
        //simpleBlock(ModBlockItems.WALL_UMBERSTONE_BRICKS.get().getBlock());
        //simpleBlock(ModBlockItems.WALL_UMBERTILE_SMOOTH.get().getBlock());
        //simpleBlock(ModBlockItems.WALL_UMBERTILE_SMOOTH_SMALL.get().getBlock());
        //simpleBlock(ModBlockItems.WALL_UMBERPAVER.get().getBlock());
        //simpleBlock(ModBlockItems.WALL_UMBERPAVER_MOSSY.get().getBlock());
        //simpleBlock(ModBlockItems.WALL_UMBERPAVER_WEBBED.get().getBlock());
        //simpleBlock(ModBlockItems.WALL_AMBER.get().getBlock());
        //simpleBlock(ModBlockItems.WALL_AMBER_BRICKS.get().getBlock());
        //simpleBlock(ModBlockItems.SLAB_UMBERSTONE.get().getBlock());
        //simpleBlock(ModBlockItems.SLAB_UMBERCOBBLE.get().getBlock());
        //simpleBlock(ModBlockItems.SLAB_UMBERCOBBLE_MOSSY.get().getBlock());
        //simpleBlock(ModBlockItems.SLAB_UMBERCOBBLE_WEBBED.get().getBlock());
        //simpleBlock(ModBlockItems.SLAB_UMBERSTONE_BRICKS.get().getBlock());
        //simpleBlock(ModBlockItems.SLAB_UMBERTILE_SMOOTH.get().getBlock());
        //simpleBlock(ModBlockItems.SLAB_UMBERTILE_SMOOTH_SMALL.get().getBlock());
        //simpleBlock(ModBlockItems.SLAB_UMBERPAVER.get().getBlock());
        //simpleBlock(ModBlockItems.SLAB_AMBER.get().getBlock());
        //simpleBlock(ModBlockItems.SLAB_AMBER_BRICKS.get().getBlock());
        //simpleBlock(ModBlockItems.STAIRS_UMBERCOBBLE.get().getBlock());
        //simpleBlock(ModBlockItems.STAIRS_UMBERCOBBLE_MOSSY.get().getBlock());
        //simpleBlock(ModBlockItems.STAIRS_UMBERCOBBLE_WEBBED.get().getBlock());
        //simpleBlock(ModBlockItems.STAIRS_UMBERSTONE_BRICKS.get().getBlock());
        //simpleBlock(ModBlockItems.STAIRS_UMBERTILE_SMOOTH.get().getBlock());
        //simpleBlock(ModBlockItems.STAIRS_UMBERTILE_SMOOTH_SMALL.get().getBlock());
        //simpleBlock(ModBlockItems.STAIRS_UMBERPAVER.get().getBlock());
        //simpleBlock(ModBlockItems.STAIRS_UMBERPAVER_MOSSY.get().getBlock());
        //simpleBlock(ModBlockItems.STAIRS_UMBERPAVER_WEBBED.get().getBlock());
        //simpleBlock(ModBlockItems.STAIRS_AMBER.get().getBlock());
        //simpleBlock(ModBlockItems.STAIRS_AMBER_BRICKS.get().getBlock());
        //simpleBlock(ModBlockItems.STAIRS_PETRIFIED_WOOD.get().getBlock());
        //simpleBlock(ModBlockItems.STAIRS_MUD_BRICKS.get().getBlock());
        //simpleBlock(ModBlockItems.STAIRS_MIR_BRICKS.get().getBlock());
    }
}
