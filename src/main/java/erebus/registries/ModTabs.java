package erebus.registries;

import erebus.Erebus;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Erebus.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCKS = CREATIVE_MODE_TABS.register(
            "blocks",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable(String.format("%s.blocks", Erebus.MODID)))
                    .icon(() -> ModBlockItems.UMBERSTONE.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlockItems.ALGAE.get());

                        // WIP

                        output.accept(ModBlockItems.UMBERSTONE.get());
                        output.accept(ModBlockItems.STAIRS_UMBERSTONE.get());
                        output.accept(ModBlockItems.PLANKS.get());
                        output.accept(ModBlockItems.PORTAL.get());
                        output.accept(ModBlockItems.GAEAN_KEYSTONE.get());
                        output.accept(ModBlockItems.UMBERGRAVEL.get());
                        output.accept(ModBlockItems.UMBERPAVER.get());
                        output.accept(ModBlockItems.SLAB_UMBERPAVER_MOSSY.get());
                        output.accept(ModBlockItems.SLAB_UMBERPAVER_WEBBED.get());
                        output.accept(ModBlockItems.UMBERSTONE_PILLAR.get());
                        output.accept(ModBlockItems.PETRIFIED_WOOD_ROCK.get());
                        output.accept(ModBlockItems.PETRIFIED_WOOD_ROCK_2.get());
                        output.accept(ModBlockItems.PETRIFIED_WOOD_ROCK_3.get());
                        output.accept(ModBlockItems.PETRIFIED_WOOD_ROCK_4.get());
                        output.accept(ModBlockItems.PETRIFIED_WOOD_ROCK_5.get());
                        output.accept(ModBlockItems.PETRIFIED_WOOD_ROCK_6.get());
                        output.accept(ModBlockItems.PETRIFIED_BARK_RED.get());
                        output.accept(ModBlockItems.PETRIFIED_BARK_BROWN.get());
                        output.accept(ModBlockItems.DUST_LAYER.get());
                        output.accept(ModBlockItems.DUST.get());
                        output.accept(ModBlockItems.DUNG.get());
                        output.accept(ModBlockItems.ORE_IRON.get());
                        output.accept(ModBlockItems.ORE_GOLD.get());
                        output.accept(ModBlockItems.ORE_COAL.get());
                        output.accept(ModBlockItems.ORE_DIAMOND.get());
                        output.accept(ModBlockItems.ORE_EMERALD.get());
                        output.accept(ModBlockItems.ORE_LAPIS.get());
                        output.accept(ModBlockItems.ORE_QUARTZ.get());
                        output.accept(ModBlockItems.ORE_PETRIFIED_QUARTZ.get());
                        output.accept(ModBlockItems.ORE_COPPER.get());
                        output.accept(ModBlockItems.ORE_SILVER.get());
                        output.accept(ModBlockItems.ORE_TIN.get());
                        output.accept(ModBlockItems.ORE_LEAD.get());
                        output.accept(ModBlockItems.ORE_ALUMINUM.get());
                        output.accept(ModBlockItems.ORE_JADE.get());
                        output.accept(ModBlockItems.ORE_ENCRUSTED_DIAMOND.get());
                        output.accept(ModBlockItems.ORE_FOSSIL.get());
                        output.accept(ModBlockItems.ORE_GNEISS.get());
                        output.accept(ModBlockItems.ORE_PETRIFIED_WOOD.get());
                        output.accept(ModBlockItems.ORE_TEMPLE.get());
                        output.accept(ModBlockItems.JADE_BLOCK.get());
                        output.accept(ModBlockItems.AMBER.get());
                        output.accept(ModBlockItems.SLAB_AMBER.get());
                        output.accept(ModBlockItems.AMBER_BRICKS.get());
                        output.accept(ModBlockItems.SLAB_AMBER_BRICKS.get());
                        output.accept(ModBlockItems.AMBER_GLASS.get());
                        output.accept(ModBlockItems.AMBER_DOOR.get());
                        output.accept(ModBlockItems.PRESERVED_BLOCK.get());
                        output.accept(ModBlockItems.MUD.get());
                        output.accept(ModBlockItems.QUICK_SAND.get());
                        output.accept(ModBlockItems.RED_GEM.get());
                        output.accept(ModBlockItems.SWAMP_VENT.get());
                        output.accept(ModBlockItems.GHOST_SAND.get());
                        output.accept(ModBlockItems.CROP_TURNIP.get());
                        output.accept(ModBlockItems.CROP_CABBAGE.get());
                        output.accept(ModBlockItems.CROP_MANDRAKE.get());
                        output.accept(ModBlockItems.JADE_BERRY_BUSH.get());
                        output.accept(ModBlockItems.HEART_BERRY_BUSH.get());
                        output.accept(ModBlockItems.SWAMP_BERRY_BUSH.get());
                        output.accept(ModBlockItems.DARK_FRUIT_VINE.get());
                        output.accept(ModBlockItems.PRICKLY_PEAR.get());
                        output.accept(ModBlockItems.GIANT_FLOWER.get());
                        output.accept(ModBlockItems.GIANT_FLOWER_STIGMA.get());
                        output.accept(ModBlockItems.PLANTED_FLOWER.get());
                        output.accept(ModBlockItems.SMALL_PLANT.get());
                        output.accept(ModBlockItems.THORNS.get());
                        output.accept(ModBlockItems.HANGING_WEB.get());
                        output.accept(ModBlockItems.DOUBLE_PLANT.get());
                        output.accept(ModBlockItems.WALL_PLANTS.get());
                        output.accept(ModBlockItems.WALL_PLANTS_CULTIVATED.get());
                        output.accept(ModBlockItems.HONEY_TREAT.get());
                        output.accept(ModBlockItems.DARK_CAPPED_MUSHROOM.get());
                        output.accept(ModBlockItems.SARCASTIC_CZECH_MUSHROOM.get());
                        output.accept(ModBlockItems.GRANDMAS_SHOES_MUSHROOM.get());
                        output.accept(ModBlockItems.DUTCH_CAP_MUSHROOM.get());
                        output.accept(ModBlockItems.KAIZERS_FINGERS_MUSHROOM.get());
                        output.accept(ModBlockItems.DARK_CAPPED_MUSHROOM_BLOCK.get());
                        output.accept(ModBlockItems.SARCASTIC_CZECH_MUSHROOM_BLOCK.get());
                        output.accept(ModBlockItems.GRANDMAS_SHOES_MUSHROOM_BLOCK.get());
                        output.accept(ModBlockItems.DUTCH_CAP_MUSHROOM_BLOCK.get());
                        output.accept(ModBlockItems.KAIZERS_FINGERS_MUSHROOM_BLOCK.get());
                        output.accept(ModBlockItems.GLOWSHROOM.get());
                        output.accept(ModBlockItems.GLOWSHROOM_STALK_MAIN.get());
                        output.accept(ModBlockItems.WITHER_WEB.get());
                        output.accept(ModBlockItems.SILK.get());
                        output.accept(ModBlockItems.MIR_BRICK.get());
                        output.accept(ModBlockItems.SLAB_MIR_BRICKS.get());
                        output.accept(ModBlockItems.PLANKS_PETRIFIED_WOOD.get());
                        output.accept(ModBlockItems.SLAB_PLANKS_PETRIFIED_WOOD.get());
                        output.accept(ModBlockItems.DOOR_PETRIFIED_WOOD.get());
                        output.accept(ModBlockItems.REIN_EXO.get());
                        output.accept(ModBlockItems.MUD_BRICK.get());
                        output.accept(ModBlockItems.SLAB_MUD_BRICKS.get());
                        output.accept(ModBlockItems.TEMPLE_BRICK.get());
                        output.accept(ModBlockItems.TEMPLE_PILLAR.get());
                        output.accept(ModBlockItems.TEMPLE_TILE.get());
                        output.accept(ModBlockItems.VOLCANIC_ROCK.get());
                        output.accept(ModBlockItems.GNEISS.get());
                        output.accept(ModBlockItems.GNEISS_VENT.get());
                        output.accept(ModBlockItems.HOLLOW_LOG.get());
                        output.accept(ModBlockItems.LOG_BALSAM_RESINLESS.get());
                        output.accept(ModBlockItems.UMBER_FURNACE.get());
                        output.accept(ModBlockItems.UMBER_FURNACE_ACTIVE.get());
                        output.accept(ModBlockItems.PETRIFIED_CRAFTING_TABLE.get());
                        output.accept(ModBlockItems.BAMBOO_CRATE.get());
                        output.accept(ModBlockItems.BAMBOO_BRIDGE.get());
                        output.accept(ModBlockItems.BAMBOO_LADDER.get());
                        output.accept(ModBlockItems.BAMBOO_NERD_POLE.get());
                        output.accept(ModBlockItems.BAMBOO_EXTENDER.get());
                        output.accept(ModBlockItems.BAMBOO_TORCH.get());
                        output.accept(ModBlockItems.BAMBOO_PIPE.get());
                        output.accept(ModBlockItems.BAMBOO_PIPE_EXTRACT.get());
                        output.accept(ModBlockItems.BAMBOO_PIPE_EXTRACT_ACTIVE.get());
                        output.accept(ModBlockItems.LIQUIFIER.get());
                        output.accept(ModBlockItems.SILO_ROOF.get());
                        output.accept(ModBlockItems.SILO_TANK.get());
                        output.accept(ModBlockItems.SILO_SUPPORTS.get());
                        output.accept(ModBlockItems.HONEY_COMB.get());
                        output.accept(ModBlockItems.UMBER_GOLEM_STATUE.get());
                        output.accept(ModBlockItems.INSECT_REPELLENT.get());
                        output.accept(ModBlockItems.PETRIFIED_WOOD_CHEST.get());
                        output.accept(ModBlockItems.GLOWING_JAR.get());
                        output.accept(ModBlockItems.FLUID_JAR.get());
                        output.accept(ModBlockItems.COMPOSTER.get());
                        output.accept(ModBlockItems.SMOOTHIE_MAKER.get());
                        output.accept(ModBlockItems.UMBERSTONE_BUTTON.get());
                        output.accept(ModBlockItems.GLOW_GEM_ACTIVE.get());
                        output.accept(ModBlockItems.GLOW_GEM_INACTIVE.get());
                        output.accept(ModBlockItems.MUCUS_BOMB.get());
                        output.accept(ModBlockItems.SPIDER_SPAWNER.get());
                        output.accept(ModBlockItems.JUMPING_SPIDER_SPAWNER.get());
                        output.accept(ModBlockItems.TARANTULA_SPAWNER.get());
                        output.accept(ModBlockItems.WASP_SPAWNER.get());
                        output.accept(ModBlockItems.ANTLION_SPAWNER.get());
                        output.accept(ModBlockItems.DRAGON_FLY_SPAWNER.get());
                        output.accept(ModBlockItems.ZOMBIE_ANT_SPAWNER.get());
                        output.accept(ModBlockItems.ZOMBIE_ANT_SOLDIER_SPAWNER.get());
                        output.accept(ModBlockItems.MAGMA_CRAWLER_SPAWNER.get());
                        output.accept(ModBlockItems.LOCUST_SPAWNER.get());
                        output.accept(ModBlockItems.GIANT_LILY_PAD.get());
                        output.accept(ModBlockItems.WASP_NEST.get());
                        output.accept(ModBlockItems.STAIRS_WASP_NEST.get());
                        output.accept(ModBlockItems.ANTLION_EGG.get());
                        output.accept(ModBlockItems.TARANTULA_EGG.get());
                        output.accept(ModBlockItems.CAPSTONE.get());
                        output.accept(ModBlockItems.ANT_HILL_BLOCK.get());
                        output.accept(ModBlockItems.FORCE_FIELD.get());
                        output.accept(ModBlockItems.FORCE_LOCK.get());
                        output.accept(ModBlockItems.TEMPLE_BRICK_UNBREAKING.get());
                        output.accept(ModBlockItems.TEMPLE_TELEPORTER.get());
                        output.accept(ModBlockItems.BLOCK_OF_BONES.get());
                        output.accept(ModBlockItems.DUNG_SPAWNER_BOT_FLY.get());
                        output.accept(ModBlockItems.DUNG_SPAWNER_FLY.get());
                        output.accept(ModBlockItems.WALL_UMBERSTONE.get());
                        output.accept(ModBlockItems.WALL_UMBERCOBBLE.get());
                        output.accept(ModBlockItems.WALL_UMBERCOBBLE_MOSSY.get());
                        output.accept(ModBlockItems.WALL_UMBERCOBBLE_WEBBED.get());
                        output.accept(ModBlockItems.WALL_UMBERSTONE_BRICKS.get());
                        output.accept(ModBlockItems.WALL_UMBERTILE_SMOOTH.get());
                        output.accept(ModBlockItems.WALL_UMBERTILE_SMOOTH_SMALL.get());
                        output.accept(ModBlockItems.WALL_UMBERPAVER.get());
                        output.accept(ModBlockItems.WALL_UMBERPAVER_MOSSY.get());
                        output.accept(ModBlockItems.WALL_UMBERPAVER_WEBBED.get());
                        output.accept(ModBlockItems.WALL_AMBER.get());
                        output.accept(ModBlockItems.WALL_AMBER_BRICKS.get());
                        output.accept(ModBlockItems.SLAB_UMBERSTONE.get());
                        output.accept(ModBlockItems.SLAB_UMBERCOBBLE.get());
                        output.accept(ModBlockItems.SLAB_UMBERCOBBLE_MOSSY.get());
                        output.accept(ModBlockItems.SLAB_UMBERCOBBLE_WEBBED.get());
                        output.accept(ModBlockItems.SLAB_UMBERSTONE_BRICKS.get());
                        output.accept(ModBlockItems.SLAB_UMBERTILE_SMOOTH.get());
                        output.accept(ModBlockItems.SLAB_UMBERTILE_SMOOTH_SMALL.get());
                        output.accept(ModBlockItems.SLAB_UMBERPAVER.get());
                        output.accept(ModBlockItems.STAIRS_UMBERCOBBLE.get());
                        output.accept(ModBlockItems.STAIRS_UMBERCOBBLE_MOSSY.get());
                        output.accept(ModBlockItems.STAIRS_UMBERCOBBLE_WEBBED.get());
                        output.accept(ModBlockItems.STAIRS_UMBERSTONE_BRICKS.get());
                        output.accept(ModBlockItems.STAIRS_UMBERTILE_SMOOTH.get());
                        output.accept(ModBlockItems.STAIRS_UMBERTILE_SMOOTH_SMALL.get());
                        output.accept(ModBlockItems.STAIRS_UMBERPAVER.get());
                        output.accept(ModBlockItems.STAIRS_UMBERPAVER_MOSSY.get());
                        output.accept(ModBlockItems.STAIRS_UMBERPAVER_WEBBED.get());
                        output.accept(ModBlockItems.STAIRS_AMBER.get());
                        output.accept(ModBlockItems.STAIRS_AMBER_BRICKS.get());
                        output.accept(ModBlockItems.STAIRS_PETRIFIED_WOOD.get());
                        output.accept(ModBlockItems.STAIRS_MUD_BRICKS.get());
                        output.accept(ModBlockItems.STAIRS_MIR_BRICKS.get());
                    })
                    .build()
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ITEMS = CREATIVE_MODE_TABS.register(
            "items",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable(String.format("%s.items", Erebus.MODID)))
                    .icon(() -> ModItems.SPRAY_CAN.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.HEART_BERRIES);
                        output.accept(ModItems.LIFE_BLOOD);
                        output.accept(ModItems.TURNIP);
                        output.accept(ModItems.CABBAGE_SEEDS);
                        output.accept(ModItems.MANDRAKE_ROOT);
                        output.accept(ModItems.STAG_HEART_RAW);
                        output.accept(ModItems.STAG_HEART_COOKED);
                        output.accept(ModItems.SMOOTHIE);
                        output.accept(ModItems.IDOLS);

                        /**
                         * Random Stuff
                         */
                        output.accept(ModItems.NECTAR_COLLECTOR);
                        output.accept(ModItems.ANT_TAMING_AMULET);
                        output.accept(ModItems.BEE_TAMING_AMULET);
                        output.accept(ModItems.WOODLOUSE_BALL);
                        output.accept(ModItems.WAND_OF_ANIMATION);
                        output.accept(ModItems.ANTI_VENOM_BOTTLE);
                        output.accept(ModItems.FLOWER_SEED);
                        output.accept(ModItems.DEATH_COMPASS);
                        output.accept(ModItems.SPRAY_CAN);
                        output.accept(ModItems.WHETSTONE);
                        output.accept(ModItems.PORTAL_ACTIVATOR);
                        output.accept(ModItems.HOMING_BEECON);
                        output.accept(ModItems.HOMING_BEECON_ADVANCED);
                        output.accept(ModItems.WAND_OF_PRESERVATION);
                        output.accept(ModItems.COMPOST);
                        output.accept(ModItems.PLANTICIDE);
                        output.accept(ModItems.SMOOTHIE_BOOK);
                        output.accept(ModItems.HORN_OF_SUMMONING);
                        output.accept(ModItems.EREBUS_MAP);
                        output.accept(ModItems.EREBUS_MAP_FILLED);
                    })
                    .build()
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GEAR = CREATIVE_MODE_TABS.register(
            "gear",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable(String.format("%s.gear", Erebus.MODID)))
                    .icon(() -> ModItems.JADE_PICKAXE.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        /**
                         * Jade Stuff
                         */
                        output.accept(ModItems.JADE_HELMET);
                        output.accept(ModItems.JADE_CHESTPLATE);
                        output.accept(ModItems.JADE_LEGGINGS);
                        output.accept(ModItems.JADE_BOOTS);
                        output.accept(ModItems.JADE_SWORD);
                        output.accept(ModItems.JADE_PICKAXE);
                        output.accept(ModItems.JADE_AXE);
                        output.accept(ModItems.JADE_SHOVEL);
                        output.accept(ModItems.JADE_PAXEL);
                        output.accept(ModItems.JADE_HOE);

                        /**
                         * Exoskeleton stuff
                         */
                        output.accept(ModItems.EXOSKELETON_HELMET);
                        output.accept(ModItems.EXOSKELETON_CHESTPLATE);
                        output.accept(ModItems.EXOSKELETON_LEGGINGS);
                        output.accept(ModItems.EXOSKELETON_BOOTS);
                        output.accept(ModItems.REIN_EXOSKELETON_HELMET);
                        output.accept(ModItems.REIN_EXOSKELETON_CHESTPLATE);
                        output.accept(ModItems.REIN_EXOSKELETON_LEGGINGS);
                        output.accept(ModItems.REIN_EXOSKELETON_BOOTS);

                        output.accept(ModItems.RHINO_EXOSKELETON_HELMET);
                        output.accept(ModItems.RHINO_EXOSKELETON_CHESTPLATE);
                        output.accept(ModItems.RHINO_EXOSKELETON_LEGGINGS);
                        output.accept(ModItems.RHINO_EXOSKELETON_BOOTS);

                        /**
                         * Bamboo Stuff
                         */
                        output.accept(ModItems.BAMBOO_HELMET);
                        output.accept(ModItems.BAMBOO_CHESTPLATE);
                        output.accept(ModItems.BAMBOO_LEGGINGS);
                        output.accept(ModItems.BAMBOO_BOOTS);
                        output.accept(ModItems.BAMBUCKET);

                        /**
                         * Misc Armor
                         */
                        output.accept(ModItems.REIN_COMPOUND_GOGGLES);
                        output.accept(ModItems.COMPOUND_GOGGLES);
                        output.accept(ModItems.SPRINT_LEGGINGS);
                        output.accept(ModItems.JUMP_BOOTS);
                        output.accept(ModItems.GLIDER_CHESTPLATE);
                        output.accept(ModItems.GLIDER_CHESTPLATE_POWERED);
                        output.accept(ModItems.MUSHROOM_HELMET);
                        output.accept(ModItems.SPIDER_T_SHIRT);
                        output.accept(ModItems.WATER_STRIDERS);

                        /**
                         * Shields
                         */
                        output.accept(ModItems.BAMBOO_SHIELD);
                        output.accept(ModItems.EXOSKELETON_SHIELD);
                        output.accept(ModItems.JADE_SHIELD);
                        output.accept(ModItems.REIN_EXOSKELETON_SHIELD);
                        output.accept(ModItems.RHINO_EXOSKELETON_SHIELD);

                        /**
                         * Misc Weapons
                         */
                        output.accept(ModItems.ROLLED_NEWSPAPER);
                        output.accept(ModItems.WASP_SWORD);
                        output.accept(ModItems.WASP_DAGGER);
                        output.accept(ModItems.ENHANCED_SCORPION_PINCER);
                        output.accept(ModItems.WAR_HAMMER);
                        output.accept(ModItems.WEB_SLINGER);
                        output.accept(ModItems.WEB_SLINGER_WITHER);
                        output.accept(ModItems.MAX_SPEED_BOW);
                    })
                    .build()
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> PLANTS = CREATIVE_MODE_TABS.register(
            "plants",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable(String.format("%s.plants", Erebus.MODID)))
                    .icon(() -> ModItems.CABBAGE_SEEDS.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {

                    })
                    .build()
    );

    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }
}
