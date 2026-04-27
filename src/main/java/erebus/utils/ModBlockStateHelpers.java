package erebus.utils;

import erebus.Erebus;
import erebus.block.GaeanKeystoneBlock;
import erebus.block.HoneyTreatBlock;
import erebus.block.plants.ModBerryBushBlock;
import erebus.client.render.block.renderer.stack.BlockOfBonesSpecialRenderer;
import erebus.client.render.block.renderer.stack.ErebusChestSpecialRenderer;
import erebus.datagen.ModModelTemplates;
import erebus.registries.blocks.ModBlocks;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.ConditionBuilder;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.registries.DeferredBlock;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static erebus.datagen.ModModelTemplates.FlowerType;
import static net.minecraft.client.data.models.BlockModelGenerators.*;

public class ModBlockStateHelpers {

    protected BlockModelGenerators blockModels;
    protected ItemModelGenerators itemModels;

    public ModBlockStateHelpers(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        this.blockModels = blockModels;
        this.itemModels = itemModels;
    }

    protected void createBlock(DeferredBlock<Block> block) {
        blockModels.createTrivialCube(block.get());
    }

    protected void createStigma(DeferredBlock<Block> block) {
        blockModels.blockStateOutput.accept(createSimpleBlock(block.get(), plainVariant(TexturedModel.CUBE.updateTexture((mapping) -> mapping.put(TextureSlot.ALL, new Material(Erebus.prefix("block/stigma")))).create(block.get(), blockModels.modelOutput))));
    }

    protected void createCustomBlock(Supplier<? extends Block> block) {
        blockModels.createNonTemplateModelBlock(block.get());
    }

    protected void createCustomHorizontalBlock(DeferredBlock<Block> block) {
        blockModels.createNonTemplateHorizontalBlock(block.get());
    }

    protected void createSpecialItem(DeferredBlock<Block> block, String id, SpecialModelRenderer.Unbaked renderer) {
        Item item = block.get().asItem();
        Identifier itemModelBase = ModelTemplates.create(id, TextureSlot.PARTICLE).create(item, TextureMapping.particle(block.get()), blockModels.modelOutput);
        ItemModel.Unbaked plainModel = ItemModelUtils.specialModel(itemModelBase, renderer);
        itemModels.itemModelOutput.accept(item, plainModel);
    }

    protected void createGaeanKeystone() {
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

    protected void createHoneyTreat() {
        HoneyTreatBlock.BITES.getPossibleValues().forEach(bite -> {
            ModModelTemplates.honeyTreatBite(bite).create(
                    Erebus.prefix(bite == 0 ? "block/%s".formatted(ModBlocks.HONEY_TREAT.getId().getPath()) : "block/%s_slice_%d".formatted(ModBlocks.HONEY_TREAT.getId().getPath(), bite)),
                    getHoneyTreatTextureMap(ModBlocks.HONEY_TREAT.get())
                            .put(TextureSlot.INSIDE, TextureMapping.getBlockTexture(ModBlocks.HONEY_TREAT.get(), "_inside")),
                    blockModels.modelOutput
            );
        });
        itemModels.itemModelOutput.accept(ModBlocks.HONEY_TREAT.asItem(), ItemModelUtils.plainModel(Erebus.prefix("block/%s".formatted(ModBlocks.HONEY_TREAT.getId().getPath()))));

        blockModels.blockStateOutput.accept(MultiVariantGenerator
                .dispatch(ModBlocks.HONEY_TREAT.get())
                .with(PropertyDispatch
                        .initial(HoneyTreatBlock.BITES)
                        .select(0, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get())))
                        .select(1, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get(), "_slice_1")))
                        .select(2, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get(), "_slice_2")))
                        .select(3, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get(), "_slice_3")))
                        .select(4, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get(), "_slice_4")))
                        .select(5, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get(), "_slice_5")))
                        .select(6, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get(), "_slice_6")))
                )
        );
    }

    private TextureMapping getHoneyTreatTextureMap(Block block) {
        return (new TextureMapping())
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block, "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(block, "_bottom"))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"));
    }

    protected void createCandleHoneyTreat(Block candleBlock, Block candleCakeBlock) {
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

    protected void createBlockOfBones() {
        createCustomHorizontalBlock(ModBlocks.BLOCK_OF_BONES);
        Item boneBlockItem = ModBlocks.BLOCK_OF_BONES.get().asItem();
        Identifier base = ModelTemplates
                .createItem("block_of_bones_special", TextureSlot.TEXTURE)
                .create(boneBlockItem, TextureMapping.defaultTexture(ModBlocks.BLOCK_OF_BONES.get()), blockModels.modelOutput);
        ItemModel.Unbaked plainModel = ItemModelUtils.specialModel(base, new BlockOfBonesSpecialRenderer.Unbaked(Erebus.prefix("block/bone_block")));

        itemModels.itemModelOutput.accept(boneBlockItem, plainModel);
    }

    protected void createHollowLog() {
        createCustomHorizontalBlock(ModBlocks.LOG_HOLLOW);
        Identifier base = ModModelTemplates.hollowLog().create(
                ModBlocks.LOG_HOLLOW.get(),
                new TextureMapping()
                        .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(ModBlocks.LOG_HOLLOW.get(), "_top"))
                        .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(ModBlocks.LOG_HOLLOW.get(), "_side"))
                        .put(TextureSlot.END, TextureMapping.getBlockTexture(ModBlocks.LOG_HOLLOW.get(), "_end"))
                        .put(TextureSlot.TOP, TextureMapping.getBlockTexture(ModBlocks.LOG_HOLLOW.get(), "_top")),
                blockModels.modelOutput
        );
        itemModels.itemModelOutput.accept(ModBlocks.LOG_HOLLOW.asItem(), ItemModelUtils.plainModel(base));
    }

    public void createChest(DeferredBlock<Block> block, DeferredBlock<Block> particle, Identifier texture) {
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

    public void createCrossBlock(DeferredBlock<Block> block) {
        MultiVariant model = plainVariant(PlantType.NOT_TINTED.getCross().create(block.get(), PlantType.NOT_TINTED.getTextureMapping(block.get()), blockModels.modelOutput));
        blockModels.blockStateOutput.accept(createSimpleBlock(block.get(), model));
    }

    public void createCrossBlockTinted(DeferredBlock<Block> block) {
        MultiVariant model = plainVariant(PlantType.TINTED.getCross().create(block.get(), PlantType.NOT_TINTED.getTextureMapping(block.get()), blockModels.modelOutput));
        blockModels.blockStateOutput.accept(createSimpleBlock(block.get(), model));
    }

    public void createDoublePlant(DeferredBlock<Block> block) {
        MultiVariant topModel = plainVariant(blockModels.createSuffixedVariant(block.get(), "_top", ModelTemplates.CROSS, TextureMapping::cross));
        MultiVariant bottomModel = plainVariant(blockModels.createSuffixedVariant(block.get(), "_bottom", ModelTemplates.CROSS, TextureMapping::cross));
        blockModels.createDoubleBlock(block.get(), topModel, bottomModel);
    }

    public void createBush(DeferredBlock<Block> bush, Supplier<Item> item) {
        blockModels.registerSimpleFlatItemModel(item.get());
        blockModels.blockStateOutput
                .accept(
                        MultiVariantGenerator.dispatch(bush.get())
                                .with(
                                        PropertyDispatch.initial(ModBerryBushBlock.AGE)
                                                .generate(
                                                        age -> plainVariant(
                                                                blockModels.createSuffixedVariant(bush.get(), "_stage" + age, ModelTemplates.CROSS, TextureMapping::cross)
                                                        )
                                                )
                                )
                );
    }

    protected void createMushroomBlock(DeferredBlock<Block> block) {
        createMushroomBlock(block, block);
    }

    protected void createMushroomBlock(DeferredBlock<Block> stem, DeferredBlock<Block> inside) {
        MultiVariant skin = plainVariant(ModelTemplates.SINGLE_FACE.create(stem.get(), TextureMapping.defaultTexture(stem.get()), blockModels.modelOutput));
        MultiVariant skinless;

        if(stem.get() == inside.get()) {
            skinless = plainVariant(Erebus.prefix("block/%s_inside".formatted(inside.getId().getPath())));
        } else {
            skinless = plainVariant(ModelTemplates.SINGLE_FACE.create(Erebus.prefix("block/%s_inside".formatted(inside.getId().getPath())), TextureMapping.defaultTexture(inside.get()), blockModels.modelOutput));
        }

        blockModels.blockStateOutput.accept(
                MultiPartGenerator
                        .multiPart(stem.get())
                        .with(condition().term(BlockStateProperties.NORTH, true), skin)
                        .with(condition().term(BlockStateProperties.EAST, true), skin.with(Y_ROT_90).with(UV_LOCK))
                        .with(condition().term(BlockStateProperties.SOUTH, true), skin.with(Y_ROT_180).with(UV_LOCK))
                        .with(condition().term(BlockStateProperties.WEST, true), skin.with(Y_ROT_270).with(UV_LOCK))
                        .with(condition().term(BlockStateProperties.UP, true), skin.with(X_ROT_270).with(UV_LOCK))
                        .with(condition().term(BlockStateProperties.DOWN, true), skin.with(X_ROT_90).with(UV_LOCK))
                        .with(condition().term(BlockStateProperties.NORTH, false), skinless)
                        .with(condition().term(BlockStateProperties.EAST, false), skinless.with(Y_ROT_90))
                        .with(condition().term(BlockStateProperties.SOUTH, false), skinless.with(Y_ROT_180))
                        .with(condition().term(BlockStateProperties.WEST, false), skinless.with(Y_ROT_270))
                        .with(condition().term(BlockStateProperties.UP, false), skinless.with(X_ROT_270))
                        .with(condition().term(BlockStateProperties.DOWN, false), skinless.with(X_ROT_90)));
        blockModels.registerSimpleItemModel(stem.get(), TexturedModel.CUBE.createWithSuffix(stem.get(), "_inventory", blockModels.modelOutput));
    }

    public void createDustBlocks() {
        TextureMapping textures = TextureMapping.cube(ModBlocks.DUST.get());
        MultiVariant snowModel = plainVariant(ModelTemplates.CUBE_ALL.create(ModBlocks.DUST.get(), textures, blockModels.modelOutput));

        SnowLayerBlock.LAYERS.getPossibleValues().forEach((layer) -> ModModelTemplates.dustLayer(layer * 2).create(Erebus.prefix("block/%s_height%d".formatted(ModBlocks.DUST_LAYER.getId().getPath(), layer * 2)), TextureMapping.defaultTexture(ModBlocks.DUST.get()), blockModels.modelOutput));

        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.DUST_LAYER.get()).with(PropertyDispatch.initial(BlockStateProperties.LAYERS).generate((level) -> {
            MultiVariant variant;
            if (level < 8) {
                Block dustLayer = ModBlocks.DUST_LAYER.get();
                variant = plainVariant(ModelLocationUtils.getModelLocation(dustLayer, "_height" + level * 2));
            } else {
                variant = snowModel;
            }

            return variant;
        })));

        blockModels.registerSimpleItemModel(ModBlocks.DUST_LAYER.get(), ModelLocationUtils.getModelLocation(ModBlocks.DUST_LAYER.get(), "_height2"));
        blockModels.blockStateOutput.accept(createSimpleBlock(ModBlocks.DUST.get(), snowModel));
    }

    protected void createFlower(FlowerType type, DeferredBlock<Block> block, DeferredBlock<Block> petal) {
        createFlower(type, block, petal, null);
    }

    protected void createChest(DeferredBlock<Block> block, String name) {
        blockModels.generateSimpleSpecialItemModel(block.get(), Optional.empty(), new ErebusChestSpecialRenderer.Unbaked(Erebus.prefix(name)));
    }

    protected void createFlower(FlowerType type, DeferredBlock<Block> block, DeferredBlock<Block> petal, @Nullable DeferredBlock<Block> petal2) {
        createCustomBlock(block);

        Material stem = TextureMapping.getBlockTexture(ModBlocks.STEM.get());
        Material petalTexture = TextureMapping.getBlockTexture(petal.get());
        Material stigma = new Material(Erebus.prefix("block/stigma"));
        Material petal2Texture = petal2 != null ? TextureMapping.getBlockTexture(petal2.get()) : petalTexture;

        TextureMapping textureMapping = new TextureMapping().put(TextureSlot.PARTICLE, petalTexture).put(ModModelTemplates.STEM, stem).put(ModModelTemplates.PETAL, petalTexture).put(ModModelTemplates.PETAL_CHASE, petal2Texture).put(ModModelTemplates.STIGMA, stigma);

        switch (type) {
            case DROOP:
                ModModelTemplates.flower().create(Erebus.prefix("block/%s".formatted(block.getId().getPath())), textureMapping, blockModels.modelOutput);
                break;
            case NORMAL:
                ModModelTemplates.flowerTall().create(Erebus.prefix("block/%s".formatted(block.getId().getPath())), textureMapping, blockModels.modelOutput);
                break;
            case THICK:
                ModModelTemplates.flowerThick().create(Erebus.prefix("block/%s".formatted(block.getId().getPath())), textureMapping, blockModels.modelOutput);
                break;
        }

        itemModels.itemModelOutput.accept(block.asItem(), ItemModelUtils.plainModel(Erebus.prefix("block/%s".formatted(block.getId().getPath()))));
    }

    protected void createVines(DeferredBlock<Block> block, Property<Integer> ageProperty) {
        blockModels.registerSimpleFlatItemModel(block.asItem());
        MultiPartGenerator generator = MultiPartGenerator.multiPart(block.get());
        Map<Property<Boolean>, VariantMutator> directionProperties = selectMultifaceProperties(block.get().defaultBlockState(), MultifaceBlock::getFaceProperty);

        ageProperty.getPossibleValues().forEach(age -> {
            Identifier stageModelId = blockModels.createSuffixedVariant(block.get(), "_" + age, ModelTemplates.CUBE_ALL, TextureMapping::cube);
            MultiVariant model = plainVariant(stageModelId);

            if (directionProperties.isEmpty()) {
                generator.with(condition().term(ageProperty, age), model);
            } else {
                ConditionBuilder noFaces = condition().term(ageProperty, age);
                directionProperties.forEach((property, _) -> noFaces.term(property, false));

                directionProperties.forEach((property, mutator) -> {
                    generator.with(condition().term(property, true).term(ageProperty, age), model.with(mutator));
                    generator.with(noFaces, model.with(mutator));
                });
            }
        });

        blockModels.blockStateOutput.accept(generator);
    }
}
