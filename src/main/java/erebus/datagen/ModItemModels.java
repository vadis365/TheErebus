package erebus.datagen;

import erebus.Erebus;
import erebus.client.render.item.renderer.*;
import erebus.registries.item.ModItems;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.SpecialModelWrapper;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jspecify.annotations.NonNull;

public class ModItemModels {

    private ItemModelGenerators itemModels;

    public void registerModels(@NonNull ItemModelGenerators itemModels) {

        this.itemModels = itemModels;

        // MARK: Materials
        normalItem(ModItems.PLATE_EXO);
        normalItem(ModItems.JADE);
        normalItem(ModItems.SHARD_BONE);
        normalItem(ModItems.BAMBOO);
        normalItem(ModItems.COMPOUND_EYES);
        normalItem(ModItems.COMPOUND_LENS);
        normalItem(ModItems.FLY_WING);
        normalItem(ModItems.PETRIFIED_WOOD);
        normalItem(ModItems.BIO_VELOCITY);
        normalItem(ModItems.ELASTIC_FIBER);
        normalItem(ModItems.WASP_STING);
        //normalItem(ModItems.BAMBOO_SHOOT); TODO: Figure out what this is
        normalItem(ModItems.RED_GEM);
        normalItem(ModItems.BIO_LUMINESCENCE);
        normalItem(ModItems.SUPERNATURAL_VELOCITY);
        normalItem(ModItems.ALTAR_FRAGMENT);
        normalItem(ModItems.REINFORCED_PLATE_EXO);
        normalItem(ModItems.GLIDER_WING);
        itemModels.itemModelOutput.accept(
                ModItems.SCORPION_PINCER.get(),
                new SpecialModelWrapper.Unbaked(
                        Erebus.prefix("item/enhanced_scoprion_pincer"),
                        new ScorpionPincerSpecialRenderer.Unbaked()
                )
        );
        normalItem(ModItems.CAMO_POWDER);
        normalItem(ModItems.NECTAR);
        itemModels.generateFlatItem(ModItems.HONEY_DRIP.get(), ModelTemplates.FLAT_HANDHELD_ITEM.extend().renderType("cutout").build());
        normalItem(ModItems.POISON_GLAND);
        normalItem(ModItems.MUD_BRICK);
        normalItem(ModItems.WHETSTONE_POWDER);
        normalItem(ModItems.DRAGONFLY_WING);
        normalItem(ModItems.BLUEBELL_PETAL);
        normalItem(ModItems.PAPYRUS);
        normalItem(ModItems.ENHANCED_GLIDER_WING);
        normalItem(ModItems.REPELLENT);
        normalItem(ModItems.MUCUS_CHARGE);
        normalItem(ModItems.NETTLE_LEAVES);
        normalItem(ModItems.NETTLE_FLOWERS);
        normalItem(ModItems.DARK_FRUIT_SEEDS);
        normalItem(ModItems.MOSS_BALL);
        normalItem(ModItems.GLOWSHROOM);
        normalItem(ModItems.PLATE_EXO_RHINO);
        normalItem(ModItems.RHINO_BEETLE_HORN);
        normalItem(ModItems.ANT_PHEROMONES);
        normalItem(ModItems.GAEAN_GEM);
        normalItem(ModItems.CRIMSON_HEART);
        normalItem(ModItems.RESIN);
        normalItem(ModItems.AMBER_STAR);
        normalItem(ModItems.INGOT_ALUMINUM);
        normalItem(ModItems.INGOT_LEAD);
        normalItem(ModItems.INGOT_SILVER);
        normalItem(ModItems.INGOT_TIN);
        normalItem(ModItems.GNEISS_ROCK);
        normalItem(ModItems.HIDE_SHROOM);
        normalItem(ModItems.BEETLE_RIDING_KIT);
        normalItem(ModItems.BEETLE_TAMING_AMULET);
        normalItem(ModItems.UMBERGOLEM_CORE);
        normalItem(ModItems.UMBERGOLEM_HEAD);
        normalItem(ModItems.UMBERGOLEM_CLAW);
        normalItem(ModItems.UMBERGOLEM_LEGS);
        normalItem(ModItems.BOGMAW_ROOT);
        normalItem(ModItems.HYDROFUGE);
        normalItem(ModItems.WATER_REPELLENT);
        normalItem(ModItems.SMOOTHIE_GLASS);
        normalItem(ModItems.MAGMA_CRAWLER_EYE);
        normalItem(ModItems.STEW_POT);
        normalItem(ModItems.TITAN_STEW);
        normalItem(ModItems.FORCE_KEY);
        normalItem(ModItems.SOUL_CRYSTAL);
        normalItem(ModItems.PLATE_ZOMBIE_ANT);
        normalItem(ModItems.STAG_BEETLE_MANDIBLES);
        normalItem(ModItems.TERPSISHROOM);
        normalItem(ModItems.BAMBOO_PIPE_WRENCH);
        normalItem(ModItems.TEMPLE_ROCK);

        // MARK: Food
        normalItem(ModItems.BEETLE_LARVA_RAW);
        normalItem(ModItems.BEETLE_LARVA_COOKED);
        normalItem(ModItems.GRASSHOPPER_LEG_RAW);
        normalItem(ModItems.GRASSHOPPER_LEG_COOKED);
        normalItem(ModItems.TARANTULA_LEG_RAW);
        normalItem(ModItems.TARANTULA_LEG_COOKED);
        normalItem(ModItems.BAMBOO_SOUP);
        normalItem(ModItems.MELONADE);
        normalItem(ModItems.MELONADE_SPARKLY);
        normalItem(ModItems.LARVAE_ON_STICK);
        normalItem(ModItems.HONEY_SANDWICH);
        normalItem(ModItems.DARK_FRUIT);
        normalItem(ModItems.TITAN_CHOP_RAW);
        normalItem(ModItems.TITAN_CHOP_COOKED);
        normalItem(ModItems.CABBAGE);
        normalItem(ModItems.TITAN_STEW_COOKED);
        normalItem(ModItems.PRICKLY_PEAR_RAW);
        normalItem(ModItems.PRICKLY_PEAR_COOKED);
        normalItem(ModItems.DARK_FRUIT_PIE);

        // MARK: Smoothies
        normalItem(ModItems.GREEN_TEA_GRASSHOPPER);
        normalItem(ModItems.MONEY_HONEY);
        normalItem(ModItems.NOTHING_IN_THE_MIDDLE);
        normalItem(ModItems.GREEN_GIANT);
        normalItem(ModItems.SEEDY_GOODNESS);
        normalItem(ModItems.GIVIN_ME_THE_BLUES);
        normalItem(ModItems.HOT_HOT_BABY);
        normalItem(ModItems.DONT_MEDDLE_WITH_THE_NETTLE);
        normalItem(ModItems.LIQUID_GOLD);
        normalItem(ModItems.BRYUFS_BREW);

        // MARK: Bamboo Armor
        normalItem(ModItems.BAMBOO_HELMET);
        normalItem(ModItems.BAMBOO_CHESTPLATE);
        normalItem(ModItems.BAMBOO_LEGGINGS);
        normalItem(ModItems.BAMBOO_BOOTS);

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

        // MARK: Jade Armor
        normalItem(ModItems.JADE_HELMET);
        normalItem(ModItems.JADE_CHESTPLATE);
        normalItem(ModItems.JADE_LEGGINGS);
        normalItem(ModItems.JADE_BOOTS);

        // MARK: Jade Tools
        toolItem(ModItems.JADE_SWORD);
        toolItem(ModItems.JADE_PICKAXE);
        toolItem(ModItems.JADE_AXE);
        toolItem(ModItems.JADE_SHOVEL);
        toolItem(ModItems.JADE_PAXEL);
        toolItem(ModItems.JADE_HOE);

        // MARK: Misc Armor & Weapons
        normalItem(ModItems.REIN_COMPOUND_GOGGLES);
        normalItem(ModItems.COMPOUND_GOGGLES);
        normalItem(ModItems.MUSHROOM_HELMET);
        normalItem(ModItems.GLIDER_CHESTPLATE);
        normalItem(ModItems.GLIDER_CHESTPLATE_POWERED);
        normalItem(ModItems.SPIDER_T_SHIRT);
        normalItem(ModItems.SPRINT_LEGGINGS);
        normalItem(ModItems.JUMP_BOOTS);
        normalItem(ModItems.WATER_STRIDERS);
        itemModels.generateBow(ModItems.MAX_SPEED_BOW.get());

        itemModels.itemModelOutput.accept(
                ModItems.QUAKE_HAMMER.get(),
                new SpecialModelWrapper.Unbaked(Erebus.prefix("item/quake_hammer"), new QuakeHammerSpecialRenderer.Unbaked())
        );
        itemModels.itemModelOutput.accept(
                ModItems.WEB_SLINGER.get(),
                new SpecialModelWrapper.Unbaked(Erebus.prefix("item/web_slinger"), new WebSlingerSpecialRenderer.Unbaked(false))
        );
        itemModels.itemModelOutput.accept(
                ModItems.WEB_SLINGER_WITHER.get(),
                new SpecialModelWrapper.Unbaked(Erebus.prefix("item/web_slinger_wither"), new WebSlingerSpecialRenderer.Unbaked(true))
        );
        itemModels.itemModelOutput.accept(
                ModItems.BAMBOO_SHIELD.get(),
                new SpecialModelWrapper.Unbaked(Erebus.prefix("item/bamboo_shield"), new ErebusShieldSpecialRenderer.Unbaked())
        );
        itemModels.itemModelOutput.accept(
                ModItems.EXOSKELETON_SHIELD.get(),
                new SpecialModelWrapper.Unbaked(Erebus.prefix("item/exoskeleton_shield"), new ErebusShieldSpecialRenderer.Unbaked())
        );
        itemModels.itemModelOutput.accept(
                ModItems.JADE_SHIELD.get(),
                new SpecialModelWrapper.Unbaked(Erebus.prefix("item/jade_shield"), new ErebusShieldSpecialRenderer.Unbaked())
        );
        itemModels.itemModelOutput.accept(
                ModItems.REIN_EXOSKELETON_SHIELD.get(),
                new SpecialModelWrapper.Unbaked(Erebus.prefix("item/rein_exoskeleton_shield"), new ErebusShieldSpecialRenderer.Unbaked())
        );
        itemModels.itemModelOutput.accept(
                ModItems.WAND_OF_ANIMATION.get(),
                new SpecialModelWrapper.Unbaked(Erebus.prefix("item/wand_of_animation"), new WandOfAnimationItemSpecialRenderer.Unbaked())
        );
        itemModels.itemModelOutput.accept(
                ModItems.WAND_OF_PRESERVATION.get(),
                new SpecialModelWrapper.Unbaked(Erebus.prefix("item/wand_of_preservation"), new WandOfPreservationSpecialRenderer.Unbaked())
        );
        itemModels.itemModelOutput.accept(
                ModItems.WASP_SWORD.get(),
                new SpecialModelWrapper.Unbaked(Erebus.prefix("item/wasp_sword"), new WaspSwordSpecialRenderer.Unbaked())
        );
        itemModels.itemModelOutput.accept(
                ModItems.WASP_DAGGER.get(),
                new SpecialModelWrapper.Unbaked(Erebus.prefix("item/wasp_dagger"), new WaspDaggerSpecialRenderer.Unbaked())
        );

        // MARK: Misc
        itemModels.itemModelOutput.accept(
                ModItems.PORTAL_ACTIVATOR.get(),
                new SpecialModelWrapper.Unbaked(Erebus.prefix("item/portal_activator"), new PortalActivatorSpecialRenderer.Unbaked())
        );
        //normalItem(ModItems.WOODLOUSE_BALL);
        normalItem(ModItems.NECTAR_COLLECTOR);
        normalItem(ModItems.ANT_TAMING_AMULET);
        normalItem(ModItems.BEE_TAMING_AMULET);
        normalItem(ModItems.ANTI_VENOM_BOTTLE);
        itemModels.generateRecoveryCompassItem(ModItems.DEATH_COMPASS.get());
        normalItem(ModItems.ROLLED_NEWSPAPER);
        normalItem(ModItems.BAMBUCKET);
        itemModels.generateStandardCompassItem(ModItems.HOMING_BEECON.get());
        itemModels.generateStandardCompassItem(ModItems.HOMING_BEECON_ADVANCED.get());
        normalItem(ModItems.SPRAY_CAN);
        normalItem(ModItems.WHETSTONE);
        normalItem(ModItems.COMPOST);
        normalItem(ModItems.PLANTICIDE);
        normalItem(ModItems.SMOOTHIE_BOOK);
        normalItem(ModItems.HORN_OF_SUMMONING);

        // MARK: Idols
        normalItem(ModItems.MUD_SCARAB);
        normalItem(ModItems.IRON_SCARAB);
        normalItem(ModItems.GOLD_SCARAB);
        normalItem(ModItems.JADE_SCARAB);
        normalItem(ModItems.MUD_UMBERGOLEM);
        normalItem(ModItems.IRON_UMBERGOLEM);
        normalItem(ModItems.GOLD_UMBERGOLEM);
        normalItem(ModItems.JADE_UMBERGOLEM);

        // MARK: Maps
        //normalItem(ModItems.EREBUS_MAP);
        //normalItem(ModItems.EREBUS_MAP_FILLED);

        // MARK: Plants
        normalItem(ModItems.TURNIP);
        normalItem(ModItems.CABBAGE_SEEDS);
        normalItem(ModItems.MANDRAKE_ROOT);
        normalItem(ModItems.SEED_BLACK);
        normalItem(ModItems.SEED_RED);
        normalItem(ModItems.SEED_BROWN);
        normalItem(ModItems.SEED_BLUE);
        normalItem(ModItems.SEED_PURPLE);
        normalItem(ModItems.SEED_CYAN);
        normalItem(ModItems.SEED_LIGHT_GRAY);
        normalItem(ModItems.SEED_GRAY);
        normalItem(ModItems.SEED_PINK);
        normalItem(ModItems.SEED_YELLOW);
        normalItem(ModItems.SEED_LIGHT_BLUE);
        normalItem(ModItems.SEED_MAGENTA);
        normalItem(ModItems.SEED_ORANGE);
        normalItem(ModItems.SEED_WHITE);
        normalItem(ModItems.SEED_RAINBOW);

        normalItem(ModItems.LIFE_BLOOD);
        normalItem(ModItems.STAG_HEART_RAW);
        normalItem(ModItems.STAG_HEART_COOKED);

        // MARK: Spawn Eggs
        normalItem(ModItems.BEETLE_LARVA_SPAWN_EGG);
        normalItem(ModItems.WASP_SPAWN_EGG);
        normalItem(ModItems.CENTIPEDE_SPAWN_EGG);
        normalItem(ModItems.BEETLE_SPAWN_EGG);
        normalItem(ModItems.FLY_SPAWN_EGG);
        normalItem(ModItems.BOT_FLY_SPAWN_EGG);
        normalItem(ModItems.GRASSHOPPER_SPAWN_EGG);
        normalItem(ModItems.LOCUST_SPAWN_EGG);
        normalItem(ModItems.MOTH_SPAWN_EGG);
        normalItem(ModItems.BLACK_WIDOW_SPAWN_EGG);
        normalItem(ModItems.BOMBARDIER_BEETLE_SPAWN_EGG);
        normalItem(ModItems.SCYTODES_SPAWN_EGG);
        normalItem(ModItems.MONEY_SPIDER_SPAWN_EGG);
        normalItem(ModItems.WORKER_BEE_SPAWN_EGG);
        normalItem(ModItems.VELVET_WORM_SPAWN_EGG);
        normalItem(ModItems.DRAGON_FLY_SPAWN_EGG);
        normalItem(ModItems.BOT_FLY_LARVA_SPAWN_EGG);
        normalItem(ModItems.FUNGAL_WEEVIL_SPAWN_EGG);
        normalItem(ModItems.CROP_WEEVIL_SPAWN_EGG);
        normalItem(ModItems.LAVA_WEB_SPIDER_SPAWN_EGG);
        normalItem(ModItems.PUNCHROOM_SPAWN_EGG);
        normalItem(ModItems.BLACK_ANT_SPAWN_EGG);
        normalItem(ModItems.ZOMBIE_ANT_SPAWN_EGG);
        normalItem(ModItems.HONEY_POT_ANT_SPAWN_EGG);
        normalItem(ModItems.BOMBARDIER_BEETLE_LARVA_SPAWN_EGG);
        normalItem(ModItems.ZOMBIE_ANT_SOLDIER_SPAWN_EGG);
        normalItem(ModItems.BED_BUG_SPAWN_EGG);
    }

    private void normalItem(DeferredHolder<Item, ?> item) {
        itemModels.generateFlatItem(item.get(), ModelTemplates.FLAT_ITEM);
    }

    private void toolItem(DeferredHolder<Item, ?> item) {
        itemModels.generateFlatItem(item.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
    }
}
