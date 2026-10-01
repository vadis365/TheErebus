package erebus.datagen.tags;

import erebus.Erebus;
import erebus.registries.blocks.ModBlockFamilies;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.tags.ModItemTags;
import erebus.registries.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ModItemTagsData extends ItemTagsProvider {

    public ModItemTagsData(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Erebus.MODID);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        addBasicMaterialTags();
        addOreMaterialTags();
        tag(ModItemTags.GEMS_JADE).add(ModItems.JADE.get());
        tag(Tags.Items.GEMS).addTag(ModItemTags.GEMS_JADE);
        tag(ModItemTags.STORAGE_BLOCKS_JADE).add(ModBlocks.JADE_BLOCK.asItem());
        tag(Tags.Items.STORAGE_BLOCKS).addTag(ModItemTags.STORAGE_BLOCKS_JADE);
        addWoodMaterialTags();
        addStoneBuildingTags();
        tag(Tags.Items.GLASS_BLOCKS).add(ModBlocks.AMBER_GLASS.asItem());
        tag(ItemTags.STONE_BUTTONS).add(ModBlocks.UMBERSTONE_BUTTON.asItem());
        tag(Tags.Items.MUSHROOMS).add(ModBlocks.DARK_CAPPED_MUSHROOM.asItem(), ModBlocks.DUTCH_CAP_MUSHROOM.asItem(),
                ModBlocks.GRANDMAS_SHOES_MUSHROOM.asItem(), ModBlocks.KAIZERS_FINGERS_MUSHROOM.asItem(),
                ModBlocks.SARCASTIC_CZECH_MUSHROOM.asItem());

        tag(Tags.Items.DYES_BLACK).add(ModBlocks.PETAL_BLACK.asItem());
        tag(Tags.Items.DYES_RED).add(ModBlocks.PETAL_RED.asItem());
        tag(Tags.Items.DYES_BROWN).add(ModBlocks.PETAL_BROWN.asItem());
        tag(Tags.Items.DYES_BLUE).add(ModBlocks.PETAL_BLUE.asItem());
        tag(Tags.Items.DYES_PURPLE).add(ModBlocks.PETAL_PURPLE.asItem());
        tag(Tags.Items.DYES_CYAN).add(ModBlocks.PETAL_CYAN.asItem());
        tag(Tags.Items.DYES_LIGHT_GRAY).add(ModBlocks.PETAL_LIGHT_GRAY.asItem());
        tag(Tags.Items.DYES_GRAY).add(ModBlocks.PETAL_GRAY.asItem());
        tag(Tags.Items.DYES_PINK).add(ModBlocks.PETAL_PINK.asItem());
        tag(Tags.Items.DYES_YELLOW).add(ModBlocks.PETAL_YELLOW.asItem());
        tag(Tags.Items.DYES_LIGHT_BLUE).add(ModBlocks.PETAL_LIGHT_BLUE.asItem());
        tag(Tags.Items.DYES_MAGENTA).add(ModBlocks.PETAL_MAGENTA.asItem());
        tag(Tags.Items.DYES_ORANGE).add(ModBlocks.PETAL_ORANGE.asItem());
        tag(Tags.Items.DYES_WHITE).add(ModBlocks.PETAL_WHITE.asItem());
        tag(Tags.Items.DYES_GREEN).add(ModBlocks.STEM.asItem(), ModBlocks.GIANT_LILY_PAD.asItem());

        tag(ItemTags.DOORS).add(ModBlocks.DOOR_PETRIFIED.asItem(), ModBlocks.AMBER_DOOR.asItem());

        tag(ItemTags.PLANKS).add(
                ModBlocks.PLANKS_ASPER.asItem(), ModBlocks.PLANKS_BALSAM.asItem(), ModBlocks.PLANKS_BAMBOO.asItem(),
                ModBlocks.PLANKS_BAOBAB.asItem(), ModBlocks.PLANKS_CYPRESS.asItem(), ModBlocks.PLANKS_EUCALYPTUS.asItem(),
                ModBlocks.PLANKS_MAHOGANY.asItem(), ModBlocks.PLANKS_MARSHWOOD.asItem(), ModBlocks.PLANKS_MOSSBARK.asItem(),
                ModBlocks.PLANKS_ROTTEN.asItem(), ModBlocks.PLANKS_SCORCHED.asItem(), ModBlocks.PLANKS_VARNISHED.asItem(),
                ModBlocks.PLANKS_WHITE.asItem());

        tag(ModItemTags.EXPERIENCE_ALTAR_FUEL).add(
                ModItems.PLATE_EXO.get(), ModItems.JADE.get(), ModItems.SHARD_BONE.get(), ModItems.BAMBOO.get(),
                ModItems.COMPOUND_EYES.get(), ModItems.COMPOUND_LENS.get(), ModItems.FLY_WING.get(), ModItems.PETRIFIED_WOOD.get(),
                ModItems.BIO_VELOCITY.get(), ModItems.WASP_STING.get(), ModItems.RED_GEM.get(), ModItems.BIO_LUMINESCENCE.get(),
                ModItems.SUPERNATURAL_VELOCITY.get(), ModItems.ALTAR_FRAGMENT.get(), ModItems.REINFORCED_PLATE_EXO.get(), ModItems.GLIDER_WING.get(),
                ModItems.SCORPION_PINCER.get(), ModItems.CAMO_POWDER.get(), ModItems.NECTAR.get(), ModItems.HONEY_DRIP.get(),
                ModItems.POISON_GLAND.get(), ModItems.MUD_BRICK.get(), ModItems.WHETSTONE_POWDER.get(), ModItems.DRAGONFLY_WING.get(),
                ModItems.BLUEBELL_PETAL.get(), ModItems.PAPYRUS.get(), ModItems.ENHANCED_GLIDER_WING.get(), ModItems.REPELLENT.get(),
                ModItems.MUCUS_CHARGE.get(), ModItems.NETTLE_LEAVES.get(), ModItems.NETTLE_FLOWERS.get(), ModItems.DARK_FRUIT_SEEDS.get(),
                ModItems.MOSS_BALL.get(), ModItems.GLOWSHROOM.get(), ModItems.PLATE_EXO_RHINO.get(), ModItems.RHINO_BEETLE_HORN.get(),
                ModItems.ANT_PHEROMONES.get(), ModItems.GAEAN_GEM.get(), ModItems.CRIMSON_HEART.get(), ModItems.RESIN.get(),
                ModItems.INGOT_ALUMINUM.get(), ModItems.INGOT_LEAD.get(), ModItems.INGOT_SILVER.get(), ModItems.INGOT_TIN.get(),
                ModItems.GNEISS_ROCK.get(), ModItems.HIDE_SHROOM.get(), ModItems.UMBERGOLEM_CORE.get(), ModItems.UMBERGOLEM_HEAD.get(),
                ModItems.UMBERGOLEM_CLAW.get(), ModItems.UMBERGOLEM_LEGS.get(), ModItems.JADE_BERRIES.get(), ModItems.BOGMAW_ROOT.get(),
                ModItems.HYDROFUGE.get(), ModItems.WATER_REPELLENT.get(), ModItems.SMOOTHIE_GLASS.get(), ModItems.MAGMA_CRAWLER_EYE.get(),
                ModItems.STEW_POT.get(), ModItems.SOUL_CRYSTAL.get(), ModItems.PLATE_ZOMBIE_ANT.get(), ModItems.STAG_BEETLE_MANDIBLES.get(),
                ModItems.TERPSISHROOM.get(), ModItems.TEMPLE_ROCK.get(),
                ModItems.ELASTIC_FIBER.get(), Items.COPPER_INGOT);
        tag(ModItemTags.REPAIRS_GLIDER).add(ModItems.GLIDER_WING.get());
        tag(ItemTags.CAULDRON_CAN_REMOVE_DYE).add(ModItems.GLIDER_CHESTPLATE.get(), ModItems.GLIDER_CHESTPLATE_POWERED.get());
        tag(ModItemTags.GLIDER_FUEL).add(ModBlocks.RED_GEM_BLOCK.get().asItem(), ModBlocks.RED_GEM_LAMP.get().asItem());

        tag(ItemTags.SWORDS).add(ModItems.JADE_SWORD.get(), ModItems.ROLLED_NEWSPAPER.get(), ModItems.WASP_SWORD.get(), ModItems.WASP_DAGGER.get());
        tag(Tags.Items.TOOLS_SHIELD).add(ModItems.BAMBOO_SHIELD.get(), ModItems.EXOSKELETON_SHIELD.get(), ModItems.JADE_SHIELD.get(),
                ModItems.REIN_EXOSKELETON_SHIELD.get(), ModItems.RHINO_EXOSKELETON_SHIELD.get());
        tag(ItemTags.DURABILITY_ENCHANTABLE).add(ModItems.BAMBOO_SHIELD.get(), ModItems.EXOSKELETON_SHIELD.get(), ModItems.JADE_SHIELD.get(),
                ModItems.REIN_EXOSKELETON_SHIELD.get(), ModItems.RHINO_EXOSKELETON_SHIELD.get());
        tag(ItemTags.PICKAXES).add(ModItems.JADE_PICKAXE.get());
        tag(ItemTags.AXES).add(ModItems.JADE_AXE.get());
        tag(ItemTags.SHOVELS).add(ModItems.JADE_SHOVEL.get());
        tag(ItemTags.HEAD_ARMOR).add(ModItems.JADE_HELMET.get());
        tag(ItemTags.CHEST_ARMOR).add(ModItems.JADE_CHESTPLATE.get());
        tag(ItemTags.LEG_ARMOR).add(ModItems.JADE_LEGGINGS.get());
        tag(ItemTags.FOOT_ARMOR).add(ModItems.JADE_BOOTS.get());

        tag(Tags.Items.FOODS).add(
                ModItems.TURNIP.get(), ModItems.CABBAGE_SEEDS.get(), ModItems.MANDRAKE_ROOT.get(), ModItems.PRICKLY_PEAR_RAW.get(),
                ModItems.JADE_BERRIES.get(), ModItems.BEETLE_LARVA_RAW.get(), ModItems.BEETLE_LARVA_COOKED.get(), ModItems.GRASSHOPPER_LEG_RAW.get(),
                ModItems.GRASSHOPPER_LEG_COOKED.get(), ModItems.TARANTULA_LEG_RAW.get(), ModItems.TARANTULA_LEG_COOKED.get(), ModItems.BAMBOO_SOUP.get(),
                ModItems.MELONADE.get(), ModItems.MELONADE_SPARKLY.get(), ModItems.LARVAE_ON_STICK.get(), ModItems.HONEY_SANDWICH.get(),
                ModItems.DARK_FRUIT.get(), ModItems.TITAN_CHOP_RAW.get(), ModItems.TITAN_CHOP_COOKED.get(), ModItems.SWAMP_BERRIES.get(),
                ModItems.CABBAGE.get(), ModItems.TITAN_STEW_COOKED.get(), ModItems.PRICKLY_PEAR_COOKED.get(), ModItems.DARK_FRUIT_PIE.get(),
                ModItems.GREEN_TEA_GRASSHOPPER.get(), ModItems.MONEY_HONEY.get(), ModItems.NOTHING_IN_THE_MIDDLE.get(), ModItems.GREEN_GIANT.get(),
                ModItems.SEEDY_GOODNESS.get(), ModItems.GIVIN_ME_THE_BLUES.get(), ModItems.HOT_HOT_BABY.get(), ModItems.DONT_MEDDLE_WITH_THE_NETTLE.get(),
                ModItems.LIQUID_GOLD.get(), ModItems.BRYUFS_BREW.get(), ModItems.LIFE_BLOOD.get(), ModItems.HEART_BERRIES.get(),
                ModItems.STAG_HEART_RAW.get(), ModItems.STAG_HEART_COOKED.get());
        tag(Tags.Items.SEEDS).add(ModItems.DARK_FRUIT_SEEDS.get(), ModItems.CABBAGE_SEEDS.get());

        // MARK: Composter inputs
        tag(ModItemTags.COMPOSTABLE).addTags(
                        Tags.Items.FOODS,
                        Tags.Items.SEEDS,
                        Tags.Items.MUSHROOMS,
                        ItemTags.FLOWERS,
                        ItemTags.SIGNS,
                        ItemTags.HANGING_SIGNS,
                        ItemTags.BAMBOO_BLOCKS,
                        ItemTags.LOGS,
                        ItemTags.LEAVES,
                        ItemTags.WOODEN_BUTTONS,
                        ItemTags.WOODEN_DOORS,
                        ItemTags.WOODEN_FENCES,
                        ItemTags.FENCE_GATES,
                        ItemTags.WOODEN_PRESSURE_PLATES,
                        ItemTags.WOODEN_SLABS,
                        ItemTags.WOODEN_STAIRS,
                        ItemTags.WOODEN_TRAPDOORS,
                        ItemTags.WOODEN_STAIRS,
                        ItemTags.PLANKS,
                        ItemTags.SAPLINGS
                )
                .add(
                        Items.CACTUS, Items.PUMPKIN, Items.CARVED_PUMPKIN, Items.JACK_O_LANTERN, Items.MELON,
                        Items.GRASS_BLOCK, Items.MYCELIUM, Items.HAY_BLOCK, Items.SPONGE, Items.WET_SPONGE,
                        Items.COBWEB, Items.VINE, Items.SHORT_GRASS, Items.TALL_GRASS, Items.FERN, Items.LARGE_FERN,
                        Items.DEAD_BUSH, Items.SUGAR_CANE, Items.LILY_PAD,
                        Items.BOOKSHELF, Items.CRAFTING_TABLE, Items.CHEST, Items.TRAPPED_CHEST, Items.JUKEBOX,
                        Items.NOTE_BLOCK, Items.RED_MUSHROOM_BLOCK, Items.BROWN_MUSHROOM_BLOCK, Items.MUSHROOM_STEM,
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

        Stream.of(
                        ModBlocks.DESERT_SHRUB.get(), ModBlocks.SWAMP_PLANT.get(), ModBlocks.FIRE_BLOOM.get(), ModBlocks.FIDDLE_HEAD.get(),
                        ModBlocks.CHEST_ASPER.get(), ModBlocks.CHEST_BAMBOO.get(), ModBlocks.CHEST_BAOBAB.get(), ModBlocks.CHEST_BALSAM.get(),
                        ModBlocks.CHEST_CYPRESS.get(), ModBlocks.CHEST_EUCALYPTUS.get(), ModBlocks.CHEST_MAHOGANY.get(), ModBlocks.CHEST_MARSHWOOD.get(),
                        ModBlocks.CHEST_MOSSBARK.get(), ModBlocks.CHEST_ROTTEN.get(), ModBlocks.CHEST_SCORCHED.get(), ModBlocks.CHEST_VARNISHED.get(),
                        ModBlocks.CHEST_WHITE.get(),
                        ModBlocks.WITHER_WEB.get(), ModBlocks.HONEY_TREAT.get(), ModBlocks.PRICKLY_PEAR.get(), ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK.get(),
                        ModBlocks.DARK_CAPPED_MUSHROOM_STEM.get(), ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK.get(), ModBlocks.DUTCH_CAP_MUSHROOM_STEM.get(), ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK.get(),
                        ModBlocks.GRANDMAS_SHOES_MUSHROOM_STEM.get(), ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK.get(), ModBlocks.KAIZERS_FINGERS_MUSHROOM_STEM.get(), ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK.get(),
                        ModBlocks.SARCASTIC_CZECH_MUSHROOM_STEM.get(), ModBlocks.GIANT_LILY_PAD.get(), ModBlocks.NETTLE.get(), ModBlocks.NETTLE_FLOWERED.get(),
                        ModBlocks.FERN.get(), ModBlocks.THORNS.get(), ModBlocks.MOULD.get(), ModBlocks.MOULD_CULTIVATED.get(),
                        ModBlocks.ALGAE.get(), ModBlocks.GLOWSHROOM_STALK.get(), ModBlocks.HANGING_WEB.get(), ModBlocks.PETAL_BLACK.get(),
                        ModBlocks.PETAL_RED.get(), ModBlocks.PETAL_BROWN.get(), ModBlocks.PETAL_BLUE.get(), ModBlocks.PETAL_PURPLE.get(),
                        ModBlocks.PETAL_CYAN.get(), ModBlocks.PETAL_LIGHT_GRAY.get(), ModBlocks.PETAL_GRAY.get(), ModBlocks.PETAL_PINK.get(),
                        ModBlocks.PETAL_YELLOW.get(), ModBlocks.PETAL_LIGHT_BLUE.get(), ModBlocks.PETAL_MAGENTA.get(), ModBlocks.PETAL_ORANGE.get(),
                        ModBlocks.PETAL_WHITE.get(), ModBlocks.PETAL_RAINBOW.get(), ModBlocks.PETAL_RAINBOW_CHASE.get(), ModBlocks.EXPLODING_STIGMA.get(),
                        ModBlocks.STEM.get(), ModBlocks.STIGMA_BLACK.get(), ModBlocks.STIGMA_RED.get(), ModBlocks.STIGMA_BROWN.get(),
                        ModBlocks.STIGMA_BLUE.get(), ModBlocks.STIGMA_PURPLE.get(), ModBlocks.STIGMA_CYAN.get(), ModBlocks.STIGMA_LIGHT_GRAY.get(),
                        ModBlocks.STIGMA_GRAY.get(), ModBlocks.STIGMA_PINK.get(), ModBlocks.STIGMA_YELLOW.get(), ModBlocks.STIGMA_LIGHT_BLUE.get(),
                        ModBlocks.STIGMA_MAGENTA.get(), ModBlocks.STIGMA_ORANGE.get(), ModBlocks.STIGMA_WHITE.get(), ModBlocks.FLOWER_BLACK.get(),
                        ModBlocks.FLOWER_RED.get(), ModBlocks.FLOWER_BROWN.get(), ModBlocks.FLOWER_BLUE.get(), ModBlocks.FLOWER_PURPLE.get(),
                        ModBlocks.FLOWER_CYAN.get(), ModBlocks.FLOWER_LIGHT_GRAY.get(), ModBlocks.FLOWER_GRAY.get(), ModBlocks.FLOWER_PINK.get(),
                        ModBlocks.FLOWER_YELLOW.get(), ModBlocks.FLOWER_LIGHT_BLUE.get(), ModBlocks.FLOWER_MAGENTA.get(), ModBlocks.FLOWER_ORANGE.get(),
                        ModBlocks.FLOWER_WHITE.get(), ModBlocks.FLOWER_RAINBOW.get(), ModBlocks.BULLRUSH.get(), ModBlocks.WEEPING_BLUEBELL.get(),
                        ModBlocks.SUNDEW.get(), ModBlocks.DROUGHTED_SHRUB.get(), ModBlocks.TALL_BLOOM.get(), ModBlocks.TANGLED_STALK.get(),
                        ModBlocks.HIGH_CAPPED_MUSHROOM.get(), ModBlocks.TALL_FERN.get(), ModBlocks.BAMBOO_CRATE.get(), ModBlocks.BAMBOO_BRIDGE.get(),
                        ModBlocks.BAMBOO_NERD_POLE.get(), ModBlocks.BAMBOO_EXTENDER.get(), ModBlocks.BAMBOO_TORCH.get(), ModBlocks.BAMBOO_PIPE.get(),
                        ModBlocks.BAMBOO_PIPE_EXTRACT.get(), ModBlocks.SILO_ROOF.get(), ModBlocks.SILO_SUPPORTS.get())
                .map(Block::asItem).filter(item -> item != Items.AIR)
                .forEach(item -> tag(ModItemTags.COMPOSTABLE).add(item));

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
                .add(ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK.get().asItem(), ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK.get().asItem(),
                        ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK.get().asItem(), ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK.get().asItem(),
                        ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK.get().asItem(), Items.RED_MUSHROOM_BLOCK, Items.BROWN_MUSHROOM_BLOCK);
        tag(ModItemTags.REPAIRS_SPIDER_T_SHIRT)
                .add();
        tag(ModItemTags.REPAIRS_WATER_STRIDERS)
                .add(ModItems.WATER_REPELLENT.get());
        tag(ModItemTags.REPAIRS_JUMP_BOOTS)
                .add(ModItems.ELASTIC_FIBER.get());
        tag(ModItemTags.REPAIRS_SPRINT_LEGGINGS)
                .add(ModItems.BIO_VELOCITY.get());
        tag(ModItemTags.JADE_TOOL_MATERIALS)
                .add(ModItems.JADE.get());
        tag(ModItemTags.WASP_SWORD_TOOL_MATERIALS)
                .add();
        tag(ModItemTags.WASP_DAGGER_TOOL_MATERIALS)
                .add();
        tag(ModItemTags.ROLLED_NEWSPAPER_TOOL_MATERIALS)
                .add();
        tag(ModItemTags.SCORPION_PINCER_TOOL_MATERIALS)
                .add();
        tag(ModItemTags.QUAKE_HAMMER_TOOL_MATERIALS)
                .add(ModItems.REINFORCED_PLATE_EXO.get());
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

    private void addOreMaterialTags() {
        tag(ModItemTags.INGOTS_ALUMINUM).add(ModItems.INGOT_ALUMINUM.get());
        tag(ModItemTags.INGOTS_LEAD).add(ModItems.INGOT_LEAD.get());
        tag(ModItemTags.INGOTS_SILVER).add(ModItems.INGOT_SILVER.get());
        tag(ModItemTags.INGOTS_TIN).add(ModItems.INGOT_TIN.get());
        tag(Tags.Items.INGOTS).addTags(ModItemTags.INGOTS_ALUMINUM, ModItemTags.INGOTS_LEAD,
                ModItemTags.INGOTS_SILVER, ModItemTags.INGOTS_TIN);
        tag(ItemTags.COAL_ORES).add(ModBlocks.ORE_COAL.asItem());
        tag(ItemTags.IRON_ORES).add(ModBlocks.ORE_IRON.asItem());
        tag(ItemTags.GOLD_ORES).add(ModBlocks.ORE_GOLD.asItem());
        tag(ItemTags.DIAMOND_ORES).add(ModBlocks.ORE_DIAMOND.asItem(), ModBlocks.ORE_ENCRUSTED_DIAMOND.asItem());
        tag(ItemTags.EMERALD_ORES).add(ModBlocks.ORE_EMERALD.asItem());
        tag(ItemTags.COPPER_ORES).add(ModBlocks.ORE_COPPER.asItem());
        tag(ItemTags.LAPIS_ORES).add(ModBlocks.ORE_LAPIS.asItem());
        tag(Tags.Items.ORES_QUARTZ).add(ModBlocks.ORE_QUARTZ.asItem(), ModBlocks.ORE_PETRIFIED_QUARTZ.asItem());
        tag(ModItemTags.ORES_ALUMINUM).add(ModBlocks.ORE_ALUMINUM.asItem());
        tag(ModItemTags.ORES_LEAD).add(ModBlocks.ORE_LEAD.asItem());
        tag(ModItemTags.ORES_SILVER).add(ModBlocks.ORE_SILVER.asItem());
        tag(ModItemTags.ORES_TIN).add(ModBlocks.ORE_TIN.asItem());
        tag(ModItemTags.ORES_JADE).add(ModBlocks.ORE_JADE.asItem());
        tag(ModItemTags.ORES_PETRIFIED_WOOD).add(ModBlocks.ORE_PETRIFIED_WOOD.asItem());
        tag(ModItemTags.ORES_FOSSIL).add(ModBlocks.ORE_FOSSIL.asItem());
        tag(ModItemTags.ORES_GNEISS).add(ModBlocks.ORE_GNEISS.asItem());
        tag(Tags.Items.ORES).addTags(ModItemTags.ORES_ALUMINUM, ModItemTags.ORES_LEAD, ModItemTags.ORES_SILVER,
                ModItemTags.ORES_TIN, ModItemTags.ORES_JADE, ModItemTags.ORES_PETRIFIED_WOOD,
                ModItemTags.ORES_FOSSIL, ModItemTags.ORES_GNEISS);
    }

    private void addBasicMaterialTags() {
        tag(ItemTags.LOGS_THAT_BURN).add(
                ModBlocks.LOG_ASPER.asItem(), ModBlocks.LOG_BALSAM.asItem(), ModBlocks.LOG_BALSAM_RESINLESS.asItem(),
                ModBlocks.LOG_BAOBAB.asItem(), ModBlocks.LOG_CYPRESS.asItem(), ModBlocks.LOG_EUCALYPTUS.asItem(),
                ModBlocks.LOG_MAHOGANY.asItem(), ModBlocks.LOG_MARSHWOOD.asItem(), ModBlocks.LOG_MOSSBARK.asItem(),
                ModBlocks.LOG_ROTTEN.asItem(), ModBlocks.LOG_SCORCHED.asItem(), ModBlocks.LOG_HOLLOW.asItem(),
                ModBlocks.COLOSSAL_BAMBOO.asItem());

        for (var block : new Block[]{
                ModBlocks.UMBERCOBBLE.get(), ModBlocks.PETRIFIED_WOOD_ROCK.get(), ModBlocks.PETRIFIED_WOOD_ROCK_2.get(),
                ModBlocks.PETRIFIED_WOOD_ROCK_4.get(), ModBlocks.PETRIFIED_WOOD_ROCK_5.get(), ModBlocks.PETRIFIED_WOOD_ROCK_6.get(),
                ModBlocks.PETRIFIED_LOG_INNER.get(), ModBlocks.PETRIFIED_BARK_BROWN.get(), ModBlocks.PETRIFIED_BARK_RED.get()}) {
            tag(Tags.Items.COBBLESTONES_NORMAL).add(block.asItem());
            tag(ItemTags.STONE_CRAFTING_MATERIALS).add(block.asItem());
            tag(ItemTags.STONE_TOOL_MATERIALS).add(block.asItem());
        }
        tag(Tags.Items.STONES).add(ModBlocks.UMBERSTONE.asItem());
    }

    private void addWoodMaterialTags() {
        ModBlockFamilies.getWoodFamilies().forEach(family -> family.getVariants().forEach((variant, block) -> {
            var materialTag = switch (variant) {
                case SLAB -> ItemTags.WOODEN_SLABS;
                case STAIRS -> ItemTags.WOODEN_STAIRS;
                case DOOR -> ItemTags.WOODEN_DOORS;
                case FENCE -> ItemTags.WOODEN_FENCES;
                case FENCE_GATE -> ItemTags.FENCE_GATES;
                default -> null;
            };
            if (materialTag != null) tag(materialTag).add(block.asItem());
        }));
        tag(ItemTags.SAPLINGS).add(
                ModBlocks.SAPLING_ASPER.asItem(), ModBlocks.SAPLING_BAOBAB.asItem(), ModBlocks.SAPLING_BAMBOO.asItem(),
                ModBlocks.SAPLING_BALSAM.asItem(), ModBlocks.SAPLING_CYPRESS.asItem(), ModBlocks.SAPLING_EUCALYPTUS.asItem(),
                ModBlocks.SAPLING_MAHOGANY.asItem(), ModBlocks.SAPLING_MARSHWOOD.asItem(), ModBlocks.SAPLING_MOSSBARK.asItem());
        tag(ItemTags.LEAVES).add(
                ModBlocks.LEAVES_ASPER.asItem(), ModBlocks.LEAVES_BAOBAB.asItem(), ModBlocks.LEAVES_BALSAM.asItem(),
                ModBlocks.LEAVES_CYPRESS.asItem(), ModBlocks.LEAVES_EUCALYPTUS.asItem(), ModBlocks.LEAVES_MAHOGANY.asItem(),
                ModBlocks.LEAVES_MARSHWOOD.asItem(), ModBlocks.LEAVES_MOSSBARK.asItem());
    }

    private void addStoneBuildingTags() {
        var wooden = ModBlockFamilies.getWoodFamilies().toList();
        ModBlockFamilies.getAllFamilies().filter(family -> !wooden.contains(family)).forEach(family ->
                family.getVariants().forEach((variant, block) -> {
                    var shapeTag = switch (variant) {
                        case SLAB -> ItemTags.SLABS;
                        case STAIRS -> ItemTags.STAIRS;
                        case WALL -> ItemTags.WALLS;
                        default -> null;
                    };
                    if (shapeTag != null) tag(shapeTag).add(block.asItem());
                }));
    }
}
