package erebus.datagen;

import erebus.Erebus;
import erebus.block.GaeanKeystoneBlock;
import erebus.block.HoneyTreatBlock;
import erebus.block.plants.ModBerryBushBlock;
import erebus.block.plants.ModCropBlock;
import erebus.client.render.block.renderer.stack.BlockOfBonesSpecialRenderer;
import erebus.client.render.block.renderer.stack.ErebusChestSpecialRenderer;
import erebus.registries.ModBlockFamilies;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

import static net.minecraft.client.data.models.BlockModelGenerators.plainVariant;
import static net.minecraft.client.data.models.model.TextureMapping.craftingTable;

public class ModBlockStates {

    private BlockModelGenerators blockModels;
    protected ItemModelGenerators itemModels;

    protected void registerModels(BlockModelGenerators blockModels) {
        this.blockModels = blockModels;

        ModBlockFamilies.getAllFamilies()
                .filter(BlockFamily::shouldGenerateModel)
                .forEach(family -> blockModels.family(family.getBaseBlock()).generateFor(family));

        // MARK: Umberstone
        createBlock(ModBlocks.UMBERGRAVEL);
        blockModels.createRotatedPillarWithHorizontalVariant(ModBlocks.UMBERSTONE_PILLAR.get(), TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
        createBlock(ModBlocks.VOLCANIC_ROCK);
        createBlock(ModBlocks.DUST);
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
        createBlock(ModBlocks.PRESERVED_AMBER);
        createBlock(ModBlocks.PRESERVED_AMBER_GLASS);

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
        blockModels.woodProvider(ModBlocks.LOG_EUCALYPTUS.get()).logWithHorizontal(ModBlocks.LOG_EUCALYPTUS.get());
        blockModels.woodProvider(ModBlocks.LOG_MAHOGANY.get()).logWithHorizontal(ModBlocks.LOG_MAHOGANY.get());
        blockModels.woodProvider(ModBlocks.LOG_MOSSBARK.get()).logWithHorizontal(ModBlocks.LOG_MOSSBARK.get());
        blockModels.woodProvider(ModBlocks.LOG_ASPER.get()).logWithHorizontal(ModBlocks.LOG_ASPER.get());
        blockModels.woodProvider(ModBlocks.LOG_CYPRESS.get()).logWithHorizontal(ModBlocks.LOG_CYPRESS.get());
        blockModels.woodProvider(ModBlocks.LOG_BALSAM.get()).logWithHorizontal(ModBlocks.LOG_BALSAM.get());
        blockModels.woodProvider(ModBlocks.LOG_BALSAM_RESINLESS.get()).logWithHorizontal(ModBlocks.LOG_BALSAM_RESINLESS.get());
        blockModels.woodProvider(ModBlocks.LOG_ROTTEN.get()).logWithHorizontal(ModBlocks.LOG_ROTTEN.get());
        blockModels.woodProvider(ModBlocks.LOG_MARSHWOOD.get()).logWithHorizontal(ModBlocks.LOG_MARSHWOOD.get());
        blockModels.woodProvider(ModBlocks.LOG_SCORCHED.get()).logWithHorizontal(ModBlocks.LOG_SCORCHED.get());

        // MARK: Saplings
        blockModels.createCrossBlock(ModBlocks.SAPLING_BAOBAB.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.SAPLING_EUCALYPTUS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.SAPLING_MAHOGANY.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.SAPLING_MOSSBARK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.SAPLING_ASPER.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.SAPLING_CYPRESS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.SAPLING_BALSAM.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.SAPLING_MARSHWOOD.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.SAPLING_BAMBOO.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        // MARK: Leaves
        blockModels.createTintedLeaves(ModBlocks.LEAVES_BAOBAB.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(ModBlocks.LEAVES_EUCALYPTUS.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(ModBlocks.LEAVES_MAHOGANY.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(ModBlocks.LEAVES_MOSSBARK.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(ModBlocks.LEAVES_ASPER.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(ModBlocks.LEAVES_CYPRESS.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(ModBlocks.LEAVES_BALSAM.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(ModBlocks.LEAVES_MARSHWOOD.get(), TexturedModel.LEAVES, -50000);

        // MARK: Plants
        blockModels.createCropBlock(ModBlocks.CROP_TURNIP.get(), ModCropBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(ModBlocks.CROP_CABBAGE.get(), ModCropBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(ModBlocks.CROP_MANDRAKE.get(), ModCropBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(ModBlocks.JADE_BERRY_BUSH.get(), ModBerryBushBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(ModBlocks.HEART_BERRY_BUSH.get(), ModBerryBushBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(ModBlocks.SWAMP_BERRY_BUSH.get(), ModBerryBushBlock.AGE, 0, 1, 2, 3);
        blockModels.createCrossBlock(ModBlocks.NETTLE.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.NETTLE_FLOWERED.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.SWAMP_PLANT.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.createCrossBlock(ModBlocks.FIRE_BLOOM.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.createCrossBlock(ModBlocks.FIDDLE_HEAD.get(), BlockModelGenerators.PlantType.TINTED);

        blockModels.createCrossBlock(ModBlocks.HANGING_WEB.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createMushroomBlock(ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK.get());
        blockModels.createMushroomBlock(ModBlocks.DARK_CAPPED_MUSHROOM_STEM.get());
        blockModels.createMushroomBlock(ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK.get());
        blockModels.createMushroomBlock(ModBlocks.SARCASTIC_CZECH_MUSHROOM_STEM.get());
        blockModels.createMushroomBlock(ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK.get());
        blockModels.createMushroomBlock(ModBlocks.GRANDMAS_SHOES_MUSHROOM_STEM.get());
        blockModels.createMushroomBlock(ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK.get());
        blockModels.createMushroomBlock(ModBlocks.DUTCH_CAP_MUSHROOM_STEM.get());
        blockModels.createMushroomBlock(ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK.get());
        blockModels.createMushroomBlock(ModBlocks.KAIZERS_FINGERS_MUSHROOM_STEM.get());
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

        createBlock(ModBlocks.EXPLODING_STIGMA);
        createBlock(ModBlocks.STEM);
        createBlock(ModBlocks.STIGMA_BLACK);
        createBlock(ModBlocks.STIGMA_RED);
        createBlock(ModBlocks.STIGMA_BROWN);
        createBlock(ModBlocks.STIGMA_BLUE);
        createBlock(ModBlocks.STIGMA_PURPLE);
        createBlock(ModBlocks.STIGMA_CYAN);
        createBlock(ModBlocks.STIGMA_LIGHT_GRAY);
        createBlock(ModBlocks.STIGMA_GRAY);
        createBlock(ModBlocks.STIGMA_PINK);
        createBlock(ModBlocks.STIGMA_YELLOW);
        createBlock(ModBlocks.STIGMA_LIGHT_BLUE);
        createBlock(ModBlocks.STIGMA_MAGENTA);
        createBlock(ModBlocks.STIGMA_ORANGE);
        createBlock(ModBlocks.STIGMA_WHITE);

        blockModels.createDoublePlant(ModBlocks.BULLRUSH.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createDoublePlant(ModBlocks.WEEPING_BLUEBELL.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createDoublePlant(ModBlocks.SUNDEW.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createDoublePlant(ModBlocks.TALL_BLOOM.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createDoublePlant(ModBlocks.TANGLED_STALK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createDoublePlant(ModBlocks.HIGH_CAPPED_MUSHROOM.get(), BlockModelGenerators.PlantType.NOT_TINTED);

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

        blockModels.createCrossBlock(ModBlocks.WITHER_WEB.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.LAVA_WEB.get(), BlockModelGenerators.PlantType.NOT_TINTED);
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
        createBlock(ModBlocks.JUMPING_SPIDER_SPAWNER);
        createBlock(ModBlocks.SPIDER_SPAWNER);
        createBlock(ModBlocks.TARANTULA_SPAWNER);
        createBlock(ModBlocks.WASP_SPAWNER);
        createBlock(ModBlocks.ZOMBIE_ANT_SPAWNER);
        createBlock(ModBlocks.ZOMBIE_ANT_SOLDIER_SPAWNER);
        createBlock(ModBlocks.MAGMA_CRAWLER_SPAWNER);
        createBlock(ModBlocks.LOCUST_SPAWNER);

        // MARK: Utility Blocks
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ModBlocks.PETRIFIED_CRAFTING_TABLE.get(), plainVariant(ModelTemplates.CUBE.create(ModBlocks.PETRIFIED_CRAFTING_TABLE.get(), craftingTable(ModBlocks.PETRIFIED_CRAFTING_TABLE.get(), ModBlocks.PLANKS_PETRIFIED.get()), blockModels.modelOutput))));
        blockModels.createFurnace(ModBlocks.UMBER_FURNACE.get(), TexturedModel.ORIENTABLE_ONLY_TOP);

        // MARK: Antlion Dungeon
        createBlock(ModBlocks.CAPSTONE);
        createBlock(ModBlocks.CAPSTONE_MUD);
        createBlock(ModBlocks.CAPSTONE_IRON);
        createBlock(ModBlocks.CAPSTONE_GOLD);
        createBlock(ModBlocks.CAPSTONE_JADE);
        createBlock(ModBlocks.TEMPLE_BRICK_UNBREAKING);
        createBlock(ModBlocks.TEMPLE_BRICK_UNBREAKING_JADE);
        createBlock(ModBlocks.TEMPLE_BRICK_UNBREAKING_EXO);
        createBlock(ModBlocks.TEMPLE_BRICK_UNBREAKING_CREAM);
        createBlock(ModBlocks.TEMPLE_BRICK_UNBREAKING_EYE);
        createBlock(ModBlocks.TEMPLE_BRICK_UNBREAKING_STRING);
        createBlock(ModBlocks.TEMPLE_TELEPORTER);
        createBlock(ModBlocks.FORCE_FIELD);
        createBlock(ModBlocks.FORCE_LOCK);
        createBlock(ModBlocks.ANT_HILL_BLOCK);

        // MARK: Custom
        createCustomBlock(ModBlocks.ANTLION_EGG);
        createCustomBlock(ModBlocks.TARANTULA_EGG);
        createCustomHorizontalBlock(ModBlocks.ALTAR_BASE);
        createCustomHorizontalBlock(ModBlocks.ALTAR_EXPERIENCE);
        createCustomHorizontalBlock(ModBlocks.ALTAR_HEALING);
        createCustomHorizontalBlock(ModBlocks.ALTAR_LIGHTNING);
        createCustomHorizontalBlock(ModBlocks.ALTAR_REPAIR);
        createCustomHorizontalBlock(ModBlocks.BAMBOO_BRIDGE);
        createCustomBlock(ModBlocks.OFFERING_ALTAR);
        createCustomBlock(ModBlocks.FLOWER_BLACK);
        createCustomBlock(ModBlocks.FLOWER_BLUE);
        createCustomBlock(ModBlocks.FLOWER_BROWN);
        createCustomBlock(ModBlocks.FLOWER_CYAN);
        createCustomBlock(ModBlocks.FLOWER_GRAY);
        createCustomBlock(ModBlocks.FLOWER_LIGHT_BLUE);
        createCustomBlock(ModBlocks.FLOWER_LIGHT_GRAY);
        createCustomBlock(ModBlocks.FLOWER_MAGENTA);
        createCustomBlock(ModBlocks.FLOWER_ORANGE);
        createCustomBlock(ModBlocks.FLOWER_PINK);
        createCustomBlock(ModBlocks.FLOWER_PURPLE);
        createCustomBlock(ModBlocks.FLOWER_RED);
        createCustomBlock(ModBlocks.FLOWER_WHITE);
        createCustomBlock(ModBlocks.FLOWER_YELLOW);
        createCustomBlock(ModBlocks.FLOWER_RAINBOW);
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

        createBlockOfBones();
    }

    private void createBlock(DeferredBlock<Block> block) {
        blockModels.createTrivialCube(block.get());
    }

    private void createCustomBlock(Supplier<? extends Block> block) {
        blockModels.createNonTemplateModelBlock(block.get());
    }

    private void createCustomHorizontalBlock(DeferredBlock<Block> block) {
        blockModels.createNonTemplateHorizontalBlock(block.get());
    }

    private void createGaeanKeystone() {
        blockModels.blockStateOutput.accept(
                MultiVariantGenerator
                        .dispatch(ModBlocks.GAEAN_KEYSTONE.get())
                        .with(
                                PropertyDispatch
                                        .initial(GaeanKeystoneBlock.ACTIVE)
                                        .select(false, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.GAEAN_KEYSTONE.get())))
                                        .select(true, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.GAEAN_KEYSTONE.get())))
                        )
        );
    }

    private void createHoneyTreat() {
        blockModels.registerSimpleFlatItemModel(ModBlocks.HONEY_TREAT.get().asItem());
        blockModels.blockStateOutput.accept(MultiVariantGenerator
                .dispatch(ModBlocks.HONEY_TREAT.get())
                .with(PropertyDispatch
                        .initial(HoneyTreatBlock.BITES)
                        .select(0, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get())))
                        .select(1, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get(), "_slice1")))
                        .select(2, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get(), "_slice2")))
                        .select(3, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get(), "_slice3")))
                        .select(4, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get(), "_slice4")))
                        .select(5, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get(), "_slice5")))
                        .select(6, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get(), "_slice6")))
                )
        );
    }

    private void createCandleHoneyTreat(Block candleBlock, Block candleCakeBlock) {
        MultiVariant candleCake = plainVariant(
                ModelTemplates.CANDLE_CAKE.create(
                        candleCakeBlock,
                        candleHoneyTreat(candleBlock, false),
                        blockModels.modelOutput
                )
        );
        MultiVariant litCandleCake = plainVariant(
                ModelTemplates.CANDLE_CAKE.createWithSuffix(
                        candleCakeBlock,
                        "_lit",
                        candleHoneyTreat(candleBlock, true),
                        blockModels.modelOutput
                )
        );

        blockModels.blockStateOutput.accept(
                MultiVariantGenerator
                        .dispatch(candleCakeBlock)
                        .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, litCandleCake, candleCake))
        );
    }

    private static TextureMapping candleHoneyTreat(Block block, boolean lit) {
        return (new TextureMapping())
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(ModBlocks.HONEY_TREAT.get(), "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(ModBlocks.HONEY_TREAT.get(), "_bottom"))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(ModBlocks.HONEY_TREAT.get(), "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(ModBlocks.HONEY_TREAT.get(), "_side"))
                .put(TextureSlot.CANDLE, TextureMapping.getBlockTexture(block, lit ? "_lit" : ""));
    }

    private void createBlockOfBones() {
        createCustomHorizontalBlock(ModBlocks.BLOCK_OF_BONES);
        Item boneBlockItem = ModBlocks.BLOCK_OF_BONES.get().asItem();
        Identifier base = ModelTemplates
                .createItem("block_of_bones_special", TextureSlot.TEXTURE)
                .create(boneBlockItem, TextureMapping.defaultTexture(ModBlocks.BLOCK_OF_BONES.get()), blockModels.modelOutput);
        ItemModel.Unbaked plainModel = ItemModelUtils.specialModel(base, new BlockOfBonesSpecialRenderer.Unbaked(Erebus.prefix("bone_block")));

        itemModels.itemModelOutput.accept(boneBlockItem, plainModel);
    }

    public void createChest(Supplier<Block> block, Supplier<Block> particle, Identifier texture) {
        blockModels.createParticleOnlyBlock(block.get(), particle.get());
        Item chestItem = block.get().asItem();
        Identifier itemModelBase = ModelTemplates.CHEST_INVENTORY.create(chestItem, TextureMapping.particle(particle.get()), blockModels.modelOutput);
        ItemModel.Unbaked plainModel = ItemModelUtils.specialModel(itemModelBase, new ErebusChestSpecialRenderer.Unbaked(texture));

        itemModels.itemModelOutput.accept(chestItem, plainModel);
    }

    public void createChests() {
        createChest(ModBlocks.CHEST_ASPER, ModBlocks.PLANKS_ASPER, ErebusChestSpecialRenderer.ASPER_TEXTURE);
        createChest(ModBlocks.CHEST_BAMBOO, ModBlocks.PLANKS_BAMBOO, ErebusChestSpecialRenderer.BAMBOO_TEXTURE);
        createChest(ModBlocks.CHEST_BALSAM, ModBlocks.PLANKS_BALSAM, ErebusChestSpecialRenderer.BALSAM_TEXTURE);
        createChest(ModBlocks.CHEST_BAOBAB, ModBlocks.PLANKS_BAOBAB, ErebusChestSpecialRenderer.BAOBAB_TEXTURE);
        createChest(ModBlocks.CHEST_CYPRESS, ModBlocks.PLANKS_CYPRESS, ErebusChestSpecialRenderer.CYPRESS_TEXTURE);
        createChest(ModBlocks.CHEST_EUCALYPTUS, ModBlocks.PLANKS_EUCALYPTUS, ErebusChestSpecialRenderer.EUCALYPTUS_TEXTURE);
        createChest(ModBlocks.CHEST_MAHOGANY, ModBlocks.PLANKS_MAHOGANY, ErebusChestSpecialRenderer.MAHOGANY_TEXTURE);
        createChest(ModBlocks.CHEST_MARSHWOOD, ModBlocks.PLANKS_MARSHWOOD, ErebusChestSpecialRenderer.MARSHWOOD_TEXTURE);
        createChest(ModBlocks.CHEST_MOSSBARK, ModBlocks.PLANKS_MOSSBARK, ErebusChestSpecialRenderer.MOSSBARK_TEXTURE);
        createChest(ModBlocks.CHEST_PETRIFIED, ModBlocks.PLANKS_PETRIFIED, ErebusChestSpecialRenderer.PETRIFIED_TEXTURE);
        createChest(ModBlocks.CHEST_ROTTEN, ModBlocks.PLANKS_ROTTEN, ErebusChestSpecialRenderer.ROTTEN_TEXTURE);
        createChest(ModBlocks.CHEST_SCORCHED, ModBlocks.PLANKS_SCORCHED, ErebusChestSpecialRenderer.SCORCHED_TEXTURE);
        createChest(ModBlocks.CHEST_VARNISHED, ModBlocks.PLANKS_VARNISHED, ErebusChestSpecialRenderer.VARNISHED_TEXTURE);
        createChest(ModBlocks.CHEST_WHITE, ModBlocks.PLANKS_WHITE, ErebusChestSpecialRenderer.WHITE_TEXTURE);
    }
}
