package erebus.registries;

import erebus.Erebus;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Erebus.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCKS = CREATIVE_MODE_TABS.register(
            "blocks",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable(String.format("%s.blocks", Erebus.MODID)))
                    .icon(() -> new ItemStack(ModBlocks.UMBERSTONE.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.ALGAE.get());

                        // WIP

                        output.accept(ModBlocks.UMBERSTONE.get());
                        output.accept(ModBlocks.UMBERSTONE_BRICKS.get());
                        output.accept(ModBlocks.UMBERCOBBLE.get());
                        output.accept(ModBlocks.UMBERCOBBLE_MOSSY.get());
                        output.accept(ModBlocks.UMBERCOBBLE_WEBBED.get());
                        output.accept(ModBlocks.UMBERTILE_SMOOTH.get());
                        output.accept(ModBlocks.UMBERTILE_SMOOTH_SMALL.get());
                        output.accept(ModBlocks.UMBERPAVER_MOSSY.get());
                        output.accept(ModBlocks.UMBERPAVER_WEBBED.get());
                        output.accept(ModBlocks.STAIRS_UMBERSTONE.get());
                        output.accept(ModBlocks.PORTAL.get());
                        output.accept(ModBlocks.GAEAN_KEYSTONE.get());
                        output.accept(ModBlocks.UMBERGRAVEL.get());
                        output.accept(ModBlocks.UMBERPAVER.get());
                        output.accept(ModBlocks.SLAB_UMBERPAVER_MOSSY.get());
                        output.accept(ModBlocks.SLAB_UMBERPAVER_WEBBED.get());
                        output.accept(ModBlocks.UMBERSTONE_PILLAR.get());
                        output.accept(ModBlocks.PETRIFIED_WOOD_ROCK.get());
                        output.accept(ModBlocks.PETRIFIED_WOOD_ROCK_2.get());
                        output.accept(ModBlocks.PETRIFIED_WOOD_ROCK_3.get());
                        output.accept(ModBlocks.PETRIFIED_WOOD_ROCK_4.get());
                        output.accept(ModBlocks.PETRIFIED_WOOD_ROCK_5.get());
                        output.accept(ModBlocks.PETRIFIED_WOOD_ROCK_6.get());
                        output.accept(ModBlocks.PETRIFIED_BARK_RED.get());
                        output.accept(ModBlocks.PETRIFIED_BARK_BROWN.get());
                        output.accept(ModBlocks.DUST_LAYER.get());
                        output.accept(ModBlocks.DUST.get());
                        output.accept(ModBlocks.DUNG.get());
                        output.accept(ModBlocks.ORE_IRON.get());
                        output.accept(ModBlocks.ORE_GOLD.get());
                        output.accept(ModBlocks.ORE_COAL.get());
                        output.accept(ModBlocks.ORE_DIAMOND.get());
                        output.accept(ModBlocks.ORE_EMERALD.get());
                        output.accept(ModBlocks.ORE_LAPIS.get());
                        output.accept(ModBlocks.ORE_QUARTZ.get());
                        output.accept(ModBlocks.ORE_PETRIFIED_QUARTZ.get());
                        output.accept(ModBlocks.ORE_COPPER.get());
                        output.accept(ModBlocks.ORE_SILVER.get());
                        output.accept(ModBlocks.ORE_TIN.get());
                        output.accept(ModBlocks.ORE_LEAD.get());
                        output.accept(ModBlocks.ORE_ALUMINUM.get());
                        output.accept(ModBlocks.ORE_JADE.get());
                        output.accept(ModBlocks.ORE_ENCRUSTED_DIAMOND.get());
                        output.accept(ModBlocks.ORE_FOSSIL.get());
                        output.accept(ModBlocks.ORE_GNEISS.get());
                        output.accept(ModBlocks.ORE_PETRIFIED_WOOD.get());
                        output.accept(ModBlocks.ORE_TEMPLE.get());
                        output.accept(ModBlocks.JADE_BLOCK.get());
                        output.accept(ModBlocks.AMBER.get());
                        output.accept(ModBlocks.SLAB_AMBER.get());
                        output.accept(ModBlocks.AMBER_BRICKS.get());
                        output.accept(ModBlocks.SLAB_AMBER_BRICKS.get());
                        output.accept(ModBlocks.AMBER_GLASS.get());
                        output.accept(ModBlocks.AMBER_DOOR.get());
                        output.accept(ModBlocks.PRESERVED_BLOCK.get());
                        output.accept(ModBlocks.MUD.get());
                        output.accept(ModBlocks.QUICK_SAND.get());
                        output.accept(ModBlocks.RED_GEM.get());
                        output.accept(ModBlocks.SWAMP_VENT.get());
                        output.accept(ModBlocks.GHOST_SAND.get());
                        output.accept(ModBlocks.CROP_TURNIP.get());
                        output.accept(ModBlocks.CROP_CABBAGE.get());
                        output.accept(ModBlocks.CROP_MANDRAKE.get());
                        output.accept(ModBlocks.JADE_BERRY_BUSH.get());
                        output.accept(ModBlocks.HEART_BERRY_BUSH.get());
                        output.accept(ModBlocks.SWAMP_BERRY_BUSH.get());
                        output.accept(ModBlocks.DARK_FRUIT_VINE.get());
                        output.accept(ModBlocks.PRICKLY_PEAR.get());
                        output.accept(ModBlocks.GIANT_FLOWER.get());
                        output.accept(ModBlocks.GIANT_FLOWER_STIGMA.get());
                        output.accept(ModBlocks.PLANTED_FLOWER.get());
                        output.accept(ModBlocks.SMALL_PLANT.get());
                        output.accept(ModBlocks.THORNS.get());
                        output.accept(ModBlocks.HANGING_WEB.get());
                        output.accept(ModBlocks.DOUBLE_PLANT.get());
                        output.accept(ModBlocks.WALL_PLANTS.get());
                        output.accept(ModBlocks.WALL_PLANTS_CULTIVATED.get());
                        output.accept(ModBlocks.HONEY_TREAT.get());
                        output.accept(ModBlocks.DARK_CAPPED_MUSHROOM.get());
                        output.accept(ModBlocks.SARCASTIC_CZECH_MUSHROOM.get());
                        output.accept(ModBlocks.GRANDMAS_SHOES_MUSHROOM.get());
                        output.accept(ModBlocks.DUTCH_CAP_MUSHROOM.get());
                        output.accept(ModBlocks.KAIZERS_FINGERS_MUSHROOM.get());
                        output.accept(ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK.get());
                        output.accept(ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK.get());
                        output.accept(ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK.get());
                        output.accept(ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK.get());
                        output.accept(ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK.get());
                        output.accept(ModBlocks.GLOWSHROOM.get());
                        output.accept(ModBlocks.GLOWSHROOM_STALK_MAIN.get());
                        output.accept(ModBlocks.WITHER_WEB.get());
                        output.accept(ModBlocks.SILK.get());
                        output.accept(ModBlocks.MIR_BRICK.get());
                        output.accept(ModBlocks.SLAB_MIR_BRICKS.get());
                        output.accept(ModBlocks.PLANKS_PETRIFIED_WOOD.get());
                        output.accept(ModBlocks.SLAB_PLANKS_PETRIFIED_WOOD.get());
                        output.accept(ModBlocks.DOOR_PETRIFIED_WOOD.get());
                        output.accept(ModBlocks.REIN_EXO.get());
                        output.accept(ModBlocks.MUD_BRICK.get());
                        output.accept(ModBlocks.SLAB_MUD_BRICKS.get());
                        output.accept(ModBlocks.TEMPLE_BRICK.get());
                        output.accept(ModBlocks.TEMPLE_PILLAR.get());
                        output.accept(ModBlocks.TEMPLE_TILE.get());
                        output.accept(ModBlocks.VOLCANIC_ROCK.get());
                        output.accept(ModBlocks.GNEISS.get());
                        output.accept(ModBlocks.GNEISS_VENT.get());
                        output.accept(ModBlocks.HOLLOW_LOG.get());
                        output.accept(ModBlocks.LOG_BALSAM_RESINLESS.get());
                        output.accept(ModBlocks.UMBER_FURNACE.get());
                        output.accept(ModBlocks.UMBER_FURNACE_ACTIVE.get());
                        output.accept(ModBlocks.PETRIFIED_CRAFTING_TABLE.get());
                        output.accept(ModBlocks.BAMBOO_CRATE.get());
                        output.accept(ModBlocks.BAMBOO_BRIDGE.get());
                        output.accept(ModBlocks.BAMBOO_LADDER.get());
                        output.accept(ModBlocks.BAMBOO_NERD_POLE.get());
                        output.accept(ModBlocks.BAMBOO_EXTENDER.get());
                        output.accept(ModBlocks.BAMBOO_TORCH.get());
                        output.accept(ModBlocks.BAMBOO_PIPE.get());
                        output.accept(ModBlocks.BAMBOO_PIPE_EXTRACT.get());
                        output.accept(ModBlocks.BAMBOO_PIPE_EXTRACT_ACTIVE.get());
                        output.accept(ModBlocks.LIQUIFIER.get());
                        output.accept(ModBlocks.SILO_ROOF.get());
                        output.accept(ModBlocks.SILO_TANK.get());
                        output.accept(ModBlocks.SILO_SUPPORTS.get());
                        output.accept(ModBlocks.HONEY_COMB.get());
                        output.accept(ModBlocks.UMBER_GOLEM_STATUE.get());
                        output.accept(ModBlocks.INSECT_REPELLENT.get());
                        output.accept(ModBlocks.PETRIFIED_WOOD_CHEST.get());
                        output.accept(ModBlocks.GLOWING_JAR.get());
                        output.accept(ModBlocks.FLUID_JAR.get());
                        output.accept(ModBlocks.COMPOSTER.get());
                        output.accept(ModBlocks.SMOOTHIE_MAKER.get());
                        output.accept(ModBlocks.UMBERSTONE_BUTTON.get());
                        output.accept(ModBlocks.GLOW_GEM_ACTIVE.get());
                        output.accept(ModBlocks.GLOW_GEM_INACTIVE.get());
                        output.accept(ModBlocks.MUCUS_BOMB.get());
                        output.accept(ModBlocks.SPIDER_SPAWNER.get());
                        output.accept(ModBlocks.JUMPING_SPIDER_SPAWNER.get());
                        output.accept(ModBlocks.TARANTULA_SPAWNER.get());
                        output.accept(ModBlocks.WASP_SPAWNER.get());
                        output.accept(ModBlocks.ANTLION_SPAWNER.get());
                        output.accept(ModBlocks.DRAGON_FLY_SPAWNER.get());
                        output.accept(ModBlocks.ZOMBIE_ANT_SPAWNER.get());
                        output.accept(ModBlocks.ZOMBIE_ANT_SOLDIER_SPAWNER.get());
                        output.accept(ModBlocks.MAGMA_CRAWLER_SPAWNER.get());
                        output.accept(ModBlocks.LOCUST_SPAWNER.get());
                        output.accept(ModBlocks.GIANT_LILY_PAD.get());
                        output.accept(ModBlocks.WASP_NEST.get());
                        output.accept(ModBlocks.STAIRS_WASP_NEST.get());
                        output.accept(ModBlocks.ANTLION_EGG.get());
                        output.accept(ModBlocks.TARANTULA_EGG.get());
                        output.accept(ModBlocks.CAPSTONE.get());
                        output.accept(ModBlocks.ANT_HILL_BLOCK.get());
                        output.accept(ModBlocks.FORCE_FIELD.get());
                        output.accept(ModBlocks.FORCE_LOCK.get());
                        output.accept(ModBlocks.TEMPLE_BRICK_UNBREAKING.get());
                        output.accept(ModBlocks.TEMPLE_TELEPORTER.get());
                        output.accept(ModBlocks.BLOCK_OF_BONES.get());
                        output.accept(ModBlocks.DUNG_SPAWNER_BOT_FLY.get());
                        output.accept(ModBlocks.DUNG_SPAWNER_FLY.get());
                        output.accept(ModBlocks.WALL_UMBERSTONE.get());
                        output.accept(ModBlocks.WALL_UMBERCOBBLE.get());
                        output.accept(ModBlocks.WALL_UMBERCOBBLE_MOSSY.get());
                        output.accept(ModBlocks.WALL_UMBERCOBBLE_WEBBED.get());
                        output.accept(ModBlocks.WALL_UMBERSTONE_BRICKS.get());
                        output.accept(ModBlocks.WALL_UMBERTILE_SMOOTH.get());
                        output.accept(ModBlocks.WALL_UMBERTILE_SMOOTH_SMALL.get());
                        output.accept(ModBlocks.WALL_UMBERPAVER.get());
                        output.accept(ModBlocks.WALL_UMBERPAVER_MOSSY.get());
                        output.accept(ModBlocks.WALL_UMBERPAVER_WEBBED.get());
                        output.accept(ModBlocks.WALL_AMBER.get());
                        output.accept(ModBlocks.WALL_AMBER_BRICKS.get());
                        output.accept(ModBlocks.SLAB_UMBERSTONE.get());
                        output.accept(ModBlocks.SLAB_UMBERCOBBLE.get());
                        output.accept(ModBlocks.SLAB_UMBERCOBBLE_MOSSY.get());
                        output.accept(ModBlocks.SLAB_UMBERCOBBLE_WEBBED.get());
                        output.accept(ModBlocks.SLAB_UMBERSTONE_BRICKS.get());
                        output.accept(ModBlocks.SLAB_UMBERTILE_SMOOTH.get());
                        output.accept(ModBlocks.SLAB_UMBERTILE_SMOOTH_SMALL.get());
                        output.accept(ModBlocks.SLAB_UMBERPAVER.get());
                        output.accept(ModBlocks.STAIRS_UMBERCOBBLE.get());
                        output.accept(ModBlocks.STAIRS_UMBERCOBBLE_MOSSY.get());
                        output.accept(ModBlocks.STAIRS_UMBERCOBBLE_WEBBED.get());
                        output.accept(ModBlocks.STAIRS_UMBERSTONE_BRICKS.get());
                        output.accept(ModBlocks.STAIRS_UMBERTILE_SMOOTH.get());
                        output.accept(ModBlocks.STAIRS_UMBERTILE_SMOOTH_SMALL.get());
                        output.accept(ModBlocks.STAIRS_UMBERPAVER.get());
                        output.accept(ModBlocks.STAIRS_UMBERPAVER_MOSSY.get());
                        output.accept(ModBlocks.STAIRS_UMBERPAVER_WEBBED.get());
                        output.accept(ModBlocks.STAIRS_AMBER.get());
                        output.accept(ModBlocks.STAIRS_AMBER_BRICKS.get());
                        output.accept(ModBlocks.STAIRS_PETRIFIED_WOOD.get());
                        output.accept(ModBlocks.STAIRS_MUD_BRICKS.get());
                        output.accept(ModBlocks.STAIRS_MIR_BRICKS.get());
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
