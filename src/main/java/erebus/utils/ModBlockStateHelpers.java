package erebus.utils;

import erebus.Erebus;
import erebus.block.GaeanKeystoneBlock;
import erebus.block.HoneyTreatBlock;
import erebus.block.plants.ModBerryBushBlock;
import erebus.client.render.block.renderer.stack.BlockOfBonesSpecialRenderer;
import erebus.client.render.block.renderer.stack.ErebusChestSpecialRenderer;
import erebus.datagen.ModModelTemplates;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

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
        blockModels.registerSimpleFlatItemModel(ModBlocks.HONEY_TREAT.asItem());
        ModModelTemplates.honeyTreatTemplate.create(Erebus.prefix("block/%s".formatted(ModBlocks.HONEY_TREAT.getId().getPath())), getHoneyTreatTextureMap(ModBlocks.HONEY_TREAT.get()), blockModels.modelOutput);

        HoneyTreatBlock.BITES.getPossibleValues().forEach(bite -> {
            if(bite != 0) {
                ModModelTemplates.honeyTreatBites[bite - 1].create(
                        Erebus.prefix("block/%s_slice_%d".formatted(ModBlocks.HONEY_TREAT.getId().getPath(), bite)),
                        getHoneyTreatTextureMap(ModBlocks.HONEY_TREAT.get())
                                .put(TextureSlot.INSIDE, TextureMapping.getBlockTexture(ModBlocks.HONEY_TREAT.get(), "_inside")),
                        blockModels.modelOutput
                );
            }
        });

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
        ItemModel.Unbaked plainModel = ItemModelUtils.specialModel(base, new BlockOfBonesSpecialRenderer.Unbaked(Erebus.prefix("bone_block")));

        itemModels.itemModelOutput.accept(boneBlockItem, plainModel);
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
}
