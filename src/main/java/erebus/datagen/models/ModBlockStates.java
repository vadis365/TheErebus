package erebus.datagen.models;

import erebus.Erebus;
import erebus.block.TempleSealBlock;
import erebus.block.TempleTeleporterBlock;
import erebus.block.plants.ModCropBlock;
import erebus.client.render.block.renderer.stack.*;
import erebus.datagen.models.ModModelTemplates.FlowerType;
import erebus.registries.blocks.ModBlockFamilies;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.registries.DeferredBlock;

import static net.minecraft.client.data.models.BlockModelGenerators.plainVariant;
import static net.minecraft.client.data.models.model.TextureMapping.craftingTable;

public class ModBlockStates extends ModBlockStateHelpers {

    public ModBlockStates(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        super(blockModels, itemModels);
    }

    private void createTempleSeal(DeferredBlock<?> block, String texture) {
        var inactive = ModelTemplates.CUBE_ALL.create(block.get(),
                new TextureMapping().put(TextureSlot.ALL,
                        new Material(Erebus.prefix("block/temple_brick_" + texture))), blockModels.modelOutput);
        var active = ModelTemplates.CUBE_ALL.createWithSuffix(block.get(), "_active",
                new TextureMapping().put(TextureSlot.ALL,
                        new Material(Erebus.prefix("block/temple_brick_" + texture + "_active"))), blockModels.modelOutput);
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block.get()).with(
                PropertyDispatch.initial(TempleSealBlock.ACTIVE)
                        .generate(enabled -> plainVariant(enabled ? active : inactive))));
    }

    private void createCrop(DeferredBlock<?> block, String itemTexture) {
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block.get()).with(
                PropertyDispatch.initial(ModCropBlock.AGE).generate(age -> plainVariant(
                        blockModels.createSuffixedVariant(block.get(), "_stage" + age, ModelTemplates.CROP, TextureMapping::crop)))));
        blockModels.registerSimpleItemModel(block.get(), ModelTemplates.FLAT_ITEM.create(block.asItem(),
                new TextureMapping().put(TextureSlot.LAYER0, new Material(Erebus.prefix("item/" + itemTexture))), blockModels.modelOutput));
    }

    private void createCapstone(DeferredBlock<?> block) {
        String texture = "block/" + block.getId().getPath();
        var inactive = ModelTemplates.CUBE_ALL.create(block.get(),
                new TextureMapping().put(TextureSlot.ALL, new Material(Erebus.prefix(texture))), blockModels.modelOutput);
        var active = ModelTemplates.CUBE_ALL.createWithSuffix(block.get(), "_active",
                new TextureMapping().put(TextureSlot.ALL, new Material(Erebus.prefix(texture + "_active"))), blockModels.modelOutput);
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block.get()).with(
                PropertyDispatch.initial(erebus.block.CapstoneBlock.ACTIVE)
                        .generate(enabled -> plainVariant(enabled ? active : inactive))));
    }

    private void createPetrifiedDoor() {
        var door = ModBlocks.DOOR_PETRIFIED.get();
        var textures = new TextureMapping()
                .put(TextureSlot.TOP, new Material(Erebus.prefix("block/door_petrified_wood_top")))
                .put(TextureSlot.BOTTOM, new Material(Erebus.prefix("block/door_petrified_wood_bottom")));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createDoor(door,
                plainVariant(ModelTemplates.DOOR_BOTTOM_LEFT.create(door, textures, blockModels.modelOutput)),
                plainVariant(ModelTemplates.DOOR_BOTTOM_LEFT_OPEN.create(door, textures, blockModels.modelOutput)),
                plainVariant(ModelTemplates.DOOR_BOTTOM_RIGHT.create(door, textures, blockModels.modelOutput)),
                plainVariant(ModelTemplates.DOOR_BOTTOM_RIGHT_OPEN.create(door, textures, blockModels.modelOutput)),
                plainVariant(ModelTemplates.DOOR_TOP_LEFT.create(door, textures, blockModels.modelOutput)),
                plainVariant(ModelTemplates.DOOR_TOP_LEFT_OPEN.create(door, textures, blockModels.modelOutput)),
                plainVariant(ModelTemplates.DOOR_TOP_RIGHT.create(door, textures, blockModels.modelOutput)),
                plainVariant(ModelTemplates.DOOR_TOP_RIGHT_OPEN.create(door, textures, blockModels.modelOutput))));
        blockModels.registerSimpleFlatItemModel(door.asItem());
    }

    public void registerModels() {
        blockModels.registerSimpleItemModel(ModBlocks.DARK_CAPPED_MUSHROOM.get(), Erebus.prefix("item/dark_capped_mushroom"));
        blockModels.registerSimpleItemModel(ModBlocks.DUTCH_CAP_MUSHROOM.get(), Erebus.prefix("item/dutch_cap_mushroom"));
        blockModels.registerSimpleItemModel(ModBlocks.GRANDMAS_SHOES_MUSHROOM.get(), Erebus.prefix("item/grandmas_shoes_mushroom"));
        blockModels.registerSimpleItemModel(ModBlocks.KAIZERS_FINGERS_MUSHROOM.get(), Erebus.prefix("item/kaizers_fingers_mushroom"));
        blockModels.registerSimpleItemModel(ModBlocks.SARCASTIC_CZECH_MUSHROOM.get(), Erebus.prefix("item/sarcastic_czech_mushroom"));
        blockModels.registerSimpleItemModel(ModBlocks.BAMBOO_PIPE.get(), Erebus.prefix("item/bamboo_pipe"));
        blockModels.registerSimpleItemModel(ModBlocks.BAMBOO_PIPE_EXTRACT.get(), Erebus.prefix("item/bamboo_pipe_extract"));
        blockModels.registerSimpleItemModel(ModBlocks.SILO_TANK.get(), Erebus.prefix("item/silo_tank"));
        blockModels.registerSimpleItemModel(ModBlocks.BAMBOO_CRATE.get(), Erebus.prefix("block/bamboo_crate_default"));
        blockModels.registerSimpleItemModel(ModBlocks.GLOWSHROOM_STALK.get(), Erebus.prefix("block/glowshroom_stalk_main"));
        blockModels.registerSimpleItemModel(ModBlocks.BAMBOO_TORCH.get(), Erebus.prefix("block/bamboo_torch_lower"));
        blockModels.registerSimpleItemModel(ModBlocks.TALL_FERN.get(), Erebus.prefix("block/tall_fern_lower"));
        blockModels.registerSimpleItemModel(ModBlocks.DROUGHTED_SHRUB.get(), Erebus.prefix("block/droughted_shrub_lower"));
        blockModels.registerSimpleItemModel(ModBlocks.INSECT_REPELLENT.get(), Erebus.prefix("block/blank"));
        createCustomHorizontalBlock(ModBlocks.UMBER_GOLEM_STATUE);
        ModBlockFamilies.getAllFamilies()
                .filter(BlockFamily::shouldGenerateModel)
                .forEach(family -> blockModels.family(family.getBaseBlock()).generateFor(family));
        createPetrifiedDoor();

        // MARK: Umberstone
        createBlock(ModBlocks.UMBERGRAVEL);
        blockModels.createRotatedPillarWithHorizontalVariant(ModBlocks.UMBERSTONE_PILLAR.get(), TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
        createBlock(ModBlocks.VOLCANIC_ROCK);
        createDustBlocks();
        createBlock(ModBlocks.PETRIFIED_WOOD_ROCK);
        createBlock(ModBlocks.PETRIFIED_WOOD_ROCK_2);
        createBlock(ModBlocks.PETRIFIED_WOOD_ROCK_3);
        createBlock(ModBlocks.PETRIFIED_WOOD_ROCK_4);
        createBlock(ModBlocks.PETRIFIED_WOOD_ROCK_5);
        createBlock(ModBlocks.PETRIFIED_WOOD_ROCK_6);
        createBlock(ModBlocks.PETRIFIED_BARK_RED);
        createBlock(ModBlocks.PETRIFIED_BARK_BROWN);
        createBlock(ModBlocks.PETRIFIED_LOG_INNER);
        createBlock(ModBlocks.DUNG);

        // MARK: Amber
        blockModels.createTrivialBlock(ModBlocks.PRESERVED_AMBER.get(), TexturedModel.CUBE.updateTexture(mapping ->
                mapping.put(TextureSlot.ALL, new Material(Erebus.prefix("block/amber"), true))));
        blockModels.createTrivialBlock(ModBlocks.PRESERVED_AMBER_GLASS.get(), TexturedModel.CUBE.updateTexture(mapping ->
                mapping.put(TextureSlot.ALL, new Material(Erebus.prefix("block/amber_glass_island"), true))));

        // MARK: Ores
        createBlock(ModBlocks.ORE_IRON);
        createBlock(ModBlocks.ORE_GOLD);
        createBlock(ModBlocks.ORE_COAL);
        createBlock(ModBlocks.ORE_DIAMOND);
        createBlock(ModBlocks.ORE_EMERALD);
        createBlock(ModBlocks.ORE_LAPIS);
        createBlock(ModBlocks.ORE_QUARTZ);
        createBlock(ModBlocks.ORE_PETRIFIED_QUARTZ);
        createBlock(ModBlocks.ORE_COPPER);
        createBlock(ModBlocks.ORE_SILVER);
        createBlock(ModBlocks.ORE_TIN);
        createBlock(ModBlocks.ORE_LEAD);
        createBlock(ModBlocks.ORE_ALUMINUM);
        createBlock(ModBlocks.ORE_JADE);
        createBlock(ModBlocks.ORE_ENCRUSTED_DIAMOND);
        createBlock(ModBlocks.ORE_FOSSIL);
        createBlock(ModBlocks.ORE_GNEISS);
        createBlock(ModBlocks.ORE_PETRIFIED_WOOD);
        createBlock(ModBlocks.ORE_TEMPLE);

        // MARK: Logs
        blockModels.woodProvider(ModBlocks.LOG_BAOBAB.get()).logWithHorizontal(ModBlocks.LOG_BAOBAB.get());
        createBarkLog(ModBlocks.LOG_EUCALYPTUS);
        blockModels.woodProvider(ModBlocks.LOG_MAHOGANY.get()).logWithHorizontal(ModBlocks.LOG_MAHOGANY.get());
        blockModels.woodProvider(ModBlocks.LOG_MOSSBARK.get()).logWithHorizontal(ModBlocks.LOG_MOSSBARK.get());
        blockModels.woodProvider(ModBlocks.LOG_ASPER.get()).logWithHorizontal(ModBlocks.LOG_ASPER.get());
        blockModels.woodProvider(ModBlocks.LOG_CYPRESS.get()).logWithHorizontal(ModBlocks.LOG_CYPRESS.get());
        blockModels.woodProvider(ModBlocks.LOG_BALSAM.get()).logWithHorizontal(ModBlocks.LOG_BALSAM.get());
        blockModels.woodProvider(ModBlocks.LOG_BALSAM_RESINLESS.get()).logWithHorizontal(ModBlocks.LOG_BALSAM_RESINLESS.get());
        createBarkLog(ModBlocks.LOG_ROTTEN);
        blockModels.woodProvider(ModBlocks.LOG_MARSHWOOD.get()).logWithHorizontal(ModBlocks.LOG_MARSHWOOD.get());
        blockModels.woodProvider(ModBlocks.LOG_SCORCHED.get()).logWithHorizontal(ModBlocks.LOG_SCORCHED.get());

        // MARK: Saplings
        createCrossBlock(ModBlocks.SAPLING_ASPER);
        createCrossBlock(ModBlocks.SAPLING_BALSAM);
        createCrossBlock(ModBlocks.SAPLING_BAMBOO);
        createCrossBlock(ModBlocks.SAPLING_BAOBAB);
        createCrossBlock(ModBlocks.SAPLING_CYPRESS);
        createCrossBlock(ModBlocks.SAPLING_EUCALYPTUS);
        createCrossBlock(ModBlocks.SAPLING_MAHOGANY);
        createCrossBlock(ModBlocks.SAPLING_MARSHWOOD);
        createCrossBlock(ModBlocks.SAPLING_MOSSBARK);

        // MARK: Leaves
        createBlock(ModBlocks.LEAVES_ASPER);
        createBlock(ModBlocks.LEAVES_BALSAM);
        createBlock(ModBlocks.LEAVES_BAOBAB);
        createBlock(ModBlocks.LEAVES_CYPRESS);
        createBlock(ModBlocks.LEAVES_EUCALYPTUS);
        createBlock(ModBlocks.LEAVES_MAHOGANY);
        createBlock(ModBlocks.LEAVES_MARSHWOOD);
        createBlock(ModBlocks.LEAVES_MOSSBARK);

        // MARK: Plants
        createCrop(ModBlocks.CROP_TURNIP, "turnip");
        createCrop(ModBlocks.CROP_CABBAGE, "cabbage_seeds");
        createCrop(ModBlocks.CROP_MANDRAKE, "mandrake_root");
        createVines();
        createBush(ModBlocks.JADE_BERRY_BUSH, ModItems.JADE_BERRIES);
        createBush(ModBlocks.HEART_BERRY_BUSH, ModItems.HEART_BERRIES, "_");
        createBush(ModBlocks.SWAMP_BERRY_BUSH, ModItems.SWAMP_BERRIES, "_");
        createCrossBlock(ModBlocks.NETTLE);
        createCrossBlock(ModBlocks.NETTLE_FLOWERED);
        createCrossBlock(ModBlocks.SWAMP_PLANT);
        var swampWeed = ModelTemplates.CROSS.create(ModBlocks.MIRE_CORAL.get(), TextureMapping.cross(ModBlocks.SWAMP_PLANT.get()), blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ModBlocks.MIRE_CORAL.get(), plainVariant(swampWeed)));
        createCrossBlock(ModBlocks.FIRE_BLOOM);
        createCrossBlockTinted(ModBlocks.FIDDLE_HEAD);

        HangingWebModels.create(blockModels);
        createMushroomBlock(ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK);
        createMushroomBlock(ModBlocks.DARK_CAPPED_MUSHROOM_STEM, ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK);
        createMushroomBlock(ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK);
        createMushroomBlock(ModBlocks.SARCASTIC_CZECH_MUSHROOM_STEM, ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK);
        createMushroomBlock(ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK);
        createMushroomBlock(ModBlocks.GRANDMAS_SHOES_MUSHROOM_STEM, ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK);
        createMushroomBlock(ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK);
        createMushroomBlock(ModBlocks.DUTCH_CAP_MUSHROOM_STEM, ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK);
        createMushroomBlock(ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK);
        createMushroomBlock(ModBlocks.KAIZERS_FINGERS_MUSHROOM_STEM, ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK);
        createBlock(ModBlocks.GIANT_LILY_PAD);

        createBlock(ModBlocks.PETAL_BLACK);
        createBlock(ModBlocks.PETAL_RED);
        createBlock(ModBlocks.PETAL_BROWN);
        createBlock(ModBlocks.PETAL_BLUE);
        createBlock(ModBlocks.PETAL_PURPLE);
        createBlock(ModBlocks.PETAL_CYAN);
        createBlock(ModBlocks.PETAL_LIGHT_GRAY);
        createBlock(ModBlocks.PETAL_GRAY);
        createBlock(ModBlocks.PETAL_PINK);
        createBlock(ModBlocks.PETAL_YELLOW);
        createBlock(ModBlocks.PETAL_LIGHT_BLUE);
        createBlock(ModBlocks.PETAL_MAGENTA);
        createBlock(ModBlocks.PETAL_ORANGE);
        createBlock(ModBlocks.PETAL_WHITE);
        createBlock(ModBlocks.PETAL_RAINBOW);
        createBlock(ModBlocks.PETAL_RAINBOW_CHASE);

        createStigma(ModBlocks.EXPLODING_STIGMA);
        createBlock(ModBlocks.STEM);
        createStigma(ModBlocks.STIGMA_BLACK);
        createStigma(ModBlocks.STIGMA_RED);
        createStigma(ModBlocks.STIGMA_BROWN);
        createStigma(ModBlocks.STIGMA_BLUE);
        createStigma(ModBlocks.STIGMA_PURPLE);
        createStigma(ModBlocks.STIGMA_CYAN);
        createStigma(ModBlocks.STIGMA_LIGHT_GRAY);
        createStigma(ModBlocks.STIGMA_GRAY);
        createStigma(ModBlocks.STIGMA_PINK);
        createStigma(ModBlocks.STIGMA_YELLOW);
        createStigma(ModBlocks.STIGMA_LIGHT_BLUE);
        createStigma(ModBlocks.STIGMA_MAGENTA);
        createStigma(ModBlocks.STIGMA_ORANGE);
        createStigma(ModBlocks.STIGMA_WHITE);

        createDoublePlant(ModBlocks.BULLRUSH);
        createDoublePlant(ModBlocks.WEEPING_BLUEBELL);
        createDoublePlant(ModBlocks.SUNDEW);
        createDoublePlant(ModBlocks.TALL_BLOOM);
        createDoublePlant(ModBlocks.TANGLED_STALK);
        createDoublePlant(ModBlocks.HIGH_CAPPED_MUSHROOM);

        // MARK: Other
        createGaeanKeystone();
        createChests();
        createBlock(ModBlocks.PORTAL);
        createBlock(ModBlocks.JADE_BLOCK);
        createBlock(ModBlocks.MUD);
        createBlock(ModBlocks.QUICK_SAND);
        createBlock(ModBlocks.GHOST_SAND);
        createBlock(ModBlocks.RED_GEM_BLOCK);

        MultiVariant off = plainVariant(TexturedModel.CUBE.create(ModBlocks.RED_GEM_LAMP.get(), blockModels.modelOutput));
        MultiVariant on = plainVariant(blockModels.createSuffixedVariant(ModBlocks.RED_GEM_LAMP.get(), "_on", ModelTemplates.CUBE_ALL, TextureMapping::cube));
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.RED_GEM_LAMP.get()).with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, on, off)));

        createCrossBlock(ModBlocks.WITHER_WEB);
        createCrossBlock(ModBlocks.LAVA_WEB);
        createBlock(ModBlocks.GNEISS);
        createBlock(ModBlocks.GNEISS_CARVED);
        createBlock(ModBlocks.GNEISS_RELIEF);
        createBlock(ModBlocks.GNEISS_BRICKS);
        createBlock(ModBlocks.GNEISS_SMOOTH);
        createBlock(ModBlocks.GNEISS_TILES);
        createBlock(ModBlocks.GNEISS_TILES_CRACKED);
        createBlock(ModBlocks.TEMPLE_BRICK);
        createBlock(ModBlocks.TEMPLE_PILLAR);
        createBlock(ModBlocks.TEMPLE_TILE);
        createBlock(ModBlocks.SILK);
        createBlock(ModBlocks.REIN_EXO);

        createHoneyTreat();
        createCandleHoneyTreat(Blocks.CANDLE, ModBlocks.CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.WHITE_CANDLE, ModBlocks.WHITE_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.ORANGE_CANDLE, ModBlocks.ORANGE_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.MAGENTA_CANDLE, ModBlocks.MAGENTA_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.LIGHT_BLUE_CANDLE, ModBlocks.LIGHT_BLUE_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.YELLOW_CANDLE, ModBlocks.YELLOW_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.LIME_CANDLE, ModBlocks.LIME_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.PINK_CANDLE, ModBlocks.PINK_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.GRAY_CANDLE, ModBlocks.GRAY_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.LIGHT_GRAY_CANDLE, ModBlocks.LIGHT_GRAY_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.CYAN_CANDLE, ModBlocks.CYAN_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.PURPLE_CANDLE, ModBlocks.PURPLE_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.BLUE_CANDLE, ModBlocks.BLUE_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.BROWN_CANDLE, ModBlocks.BROWN_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.GREEN_CANDLE, ModBlocks.GREEN_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.RED_CANDLE, ModBlocks.RED_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.BLACK_CANDLE, ModBlocks.BLACK_CANDLE_HONEY_TREAT.get());

        // MARK: Spawners
        createBlock(ModBlocks.ANTLION_SPAWNER);
        createBlock(ModBlocks.DRAGON_FLY_SPAWNER);
        createBlock(ModBlocks.JUMPING_SPIDER_SPAWNER, "spider_spawner");
        createBlock(ModBlocks.SPIDER_SPAWNER);
        createBlock(ModBlocks.TARANTULA_SPAWNER);
        createBlock(ModBlocks.WASP_SPAWNER);
        createBlock(ModBlocks.ZOMBIE_ANT_SPAWNER);
        createBlock(ModBlocks.ZOMBIE_ANT_SOLDIER_SPAWNER, "zombie_ant_spawner");
        createBlock(ModBlocks.MAGMA_CRAWLER_SPAWNER);
        createBlock(ModBlocks.LOCUST_SPAWNER);

        // MARK: Utility Blocks
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ModBlocks.PETRIFIED_CRAFTING_TABLE.get(), plainVariant(ModelTemplates.CUBE.create(ModBlocks.PETRIFIED_CRAFTING_TABLE.get(), craftingTable(ModBlocks.PETRIFIED_CRAFTING_TABLE.get(), ModBlocks.PLANKS_PETRIFIED.get()), blockModels.modelOutput))));
        blockModels.createFurnace(ModBlocks.UMBER_FURNACE.get(), TexturedModel.ORIENTABLE_ONLY_TOP);

        // MARK: Antlion Dungeon
        createBlock(ModBlocks.CAPSTONE);
        createCapstone(ModBlocks.CAPSTONE_MUD);
        createCapstone(ModBlocks.CAPSTONE_IRON);
        createCapstone(ModBlocks.CAPSTONE_GOLD);
        createCapstone(ModBlocks.CAPSTONE_JADE);
        createBlock(ModBlocks.TEMPLE_BRICK_UNBREAKING, "temple_brick");
        createTempleSeal(ModBlocks.TEMPLE_BRICK_UNBREAKING_JADE, "jade");
        createTempleSeal(ModBlocks.TEMPLE_BRICK_UNBREAKING_EXO, "exo");
        createTempleSeal(ModBlocks.TEMPLE_BRICK_UNBREAKING_CREAM, "cream");
        createTempleSeal(ModBlocks.TEMPLE_BRICK_UNBREAKING_EYE, "eye");
        createTempleSeal(ModBlocks.TEMPLE_BRICK_UNBREAKING_STRING, "string");
        String[] teleporterTextures = {"0", "1", "2", "3", "4", "5", "ne", "nw", "se", "sw"};
        var teleporter = ModBlocks.TEMPLE_TELEPORTER.get();
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(teleporter).with(
                PropertyDispatch.initial(TempleTeleporterBlock.PHASE).generate(phase ->
                        plainVariant(ModelTemplates.CUBE_ALL.create(
                                Erebus.prefix(phase == 0 ? "block/temple_teleporter" : "block/temple_teleporter_" + phase),
                                new TextureMapping().put(TextureSlot.ALL, new Material(Erebus.prefix("block/temple_teleport_" + teleporterTextures[phase]))), blockModels.modelOutput)))));
        var forceField = ModBlocks.FORCE_FIELD.get();
        var forceFieldModel = ModelTemplates.CUBE_ALL
                .create(forceField, TextureMapping.cube(forceField).forceAllTranslucent(), blockModels.modelOutput);
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(forceField, plainVariant(forceFieldModel)));
        var lockTextures = new TextureMapping();
        for (var slot : new TextureSlot[]{TextureSlot.PARTICLE,
                TextureSlot.DOWN, TextureSlot.UP,
                TextureSlot.EAST, TextureSlot.SOUTH,
                TextureSlot.WEST})
            lockTextures.put(slot, TextureMapping.getBlockTexture(forceField));
        lockTextures.put(TextureSlot.NORTH, TextureMapping.getBlockTexture(ModBlocks.FORCE_LOCK.get()));
        ModelTemplates.CUBE.create(ModBlocks.FORCE_LOCK.get(), lockTextures.forceAllTranslucent(), blockModels.modelOutput);
        createCustomHorizontalBlock(ModBlocks.FORCE_LOCK);
        createBlock(ModBlocks.ANT_HILL_BLOCK);

        // MARK: Custom
        createCustomBlock(ModBlocks.ANTLION_EGG);
        createCustomBlock(ModBlocks.TARANTULA_EGG);
        createCustomHorizontalBlock(ModBlocks.ALTAR_BASE);
        createCustomHorizontalBlock(ModBlocks.ALTAR_EXPERIENCE);
        createSpecialItem(ModBlocks.ALTAR_EXPERIENCE, new ExperienceAltarSpecialRenderer.Unbaked(Erebus.prefix("")));
        createCustomHorizontalBlock(ModBlocks.ALTAR_HEALING);
        createSpecialItem(ModBlocks.ALTAR_HEALING, new HealingAltarSpecialRenderer.Unbaked(Erebus.prefix("")));
        createCustomHorizontalBlock(ModBlocks.ALTAR_LIGHTNING);
        createSpecialItem(ModBlocks.ALTAR_LIGHTNING, new LightningAltarSpecialRenderer.Unbaked(Erebus.prefix("")));
        createCustomHorizontalBlock(ModBlocks.ALTAR_REPAIR);
        createSpecialItem(ModBlocks.ALTAR_REPAIR, new RepairAltarSpecialRenderer.Unbaked(Erebus.prefix("")));
        createCustomHorizontalBlock(ModBlocks.BAMBOO_BRIDGE);
        createCustomBlock(ModBlocks.OFFERING_ALTAR);
        createSpecialItem(ModBlocks.OFFERING_ALTAR, new OfferingAltarSpecialRenderer.Unbaked(Erebus.prefix("offering_altar")));
        createCustomBlock(ModBlocks.FLUID_ANTI_VENOM_BLOCK);
        createCustomBlock(ModBlocks.FLUID_BEETLE_JUICE_BLOCK);
        createCustomBlock(ModBlocks.FLUID_FORMIC_ACID_BLOCK);
        createCustomBlock(ModBlocks.FLUID_HONEY_BLOCK);
        createCustomBlock(ModBlocks.BAMBOO_NERD_POLE);
        createCustomHorizontalBlock(ModBlocks.BAMBOO_LADDER);
        createCustomHorizontalBlock(ModBlocks.BLENDER);
        createCustomBlock(ModBlocks.COMPOSTER);
        createCustomBlock(ModBlocks.DESERT_SHRUB);
        createCustomBlock(ModBlocks.FERN);
        createCustomBlock(ModBlocks.FLUID_JAR);
        createCustomBlock(ModBlocks.GLOWING_JAR);
        createCustomBlock(ModBlocks.GLOWSHROOM_BLOCK);
        createCustomBlock(ModBlocks.HONEY_COMB);
        createCustomBlock(ModBlocks.SWAMP_VENT);

        createFlower(FlowerType.THICK, ModBlocks.FLOWER_BLACK, ModBlocks.PETAL_BLACK);
        createFlower(FlowerType.THICK, ModBlocks.FLOWER_BLUE, ModBlocks.PETAL_BLUE);
        createFlower(FlowerType.THICK, ModBlocks.FLOWER_BROWN, ModBlocks.PETAL_BROWN);
        createFlower(FlowerType.THICK, ModBlocks.FLOWER_CYAN, ModBlocks.PETAL_CYAN);
        createFlower(FlowerType.THICK, ModBlocks.FLOWER_GRAY, ModBlocks.PETAL_GRAY);
        createFlower(FlowerType.NORMAL, ModBlocks.FLOWER_LIGHT_BLUE, ModBlocks.PETAL_LIGHT_BLUE);
        createFlower(FlowerType.NORMAL, ModBlocks.FLOWER_LIGHT_GRAY, ModBlocks.PETAL_LIGHT_GRAY);
        createFlower(FlowerType.NORMAL, ModBlocks.FLOWER_MAGENTA, ModBlocks.PETAL_MAGENTA);
        createFlower(FlowerType.NORMAL, ModBlocks.FLOWER_ORANGE, ModBlocks.PETAL_ORANGE);
        createFlower(FlowerType.DROOP, ModBlocks.FLOWER_PINK, ModBlocks.PETAL_PINK);
        createFlower(FlowerType.DROOP, ModBlocks.FLOWER_PURPLE, ModBlocks.PETAL_PURPLE);
        createFlower(FlowerType.DROOP, ModBlocks.FLOWER_RED, ModBlocks.PETAL_RED);
        createFlower(FlowerType.DROOP, ModBlocks.FLOWER_WHITE, ModBlocks.PETAL_WHITE);
        createFlower(FlowerType.DROOP, ModBlocks.FLOWER_YELLOW, ModBlocks.PETAL_YELLOW);
        createFlower(FlowerType.THICK, ModBlocks.FLOWER_RAINBOW, ModBlocks.PETAL_RAINBOW_CHASE, ModBlocks.PETAL_RAINBOW);

        createBlockOfBones();
        createHollowLog();
    }
}
