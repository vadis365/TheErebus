package erebus.datagen.providers;

import erebus.Erebus;
import erebus.block.CandleHoneyTreatBlock;
import erebus.block.HoneyTreatBlock;
import erebus.block.plants.ModCropBlock;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;

import java.util.function.Supplier;

public abstract class ModBlockStateProvider extends ModelProvider {

    public ModBlockStateProvider() {
        super();
    }

    protected Identifier texture(String name) {
        return modLoc("block/%s".formatted(name));
    }

    protected String name(Supplier<? extends Block> block) {
        return BuiltInRegistries.BLOCK.getKey(block.get()).getPath();
    }

    public void block(Supplier<? extends Block> block) {
        simpleBlock(block.get());
    }

    public void block(Supplier<? extends Block> block, String texture) {
        simpleBlock(block.get(), models().cubeAll(name(block), texture(texture)));
    }

    public void blockTranslucent(Supplier<? extends Block> block) {
        simpleBlock(block.get(), models().cubeAll(name(block), blockTexture(block.get())).renderType("translucent"));
    }

    public void blockTranslucent(Supplier<? extends Block> block, String texture) {
        simpleBlock(block.get(), models().cubeAll(name(block), texture(texture)).renderType("translucent"));
    }

    public void log(Supplier<? extends RotatedPillarBlock> block) {
        axisBlock(block.get(), texture(name(block)));
    }

    private void crossBlock(Supplier<? extends Block> block, ModelFile model) {
        getVariantBuilder(block.get()).forAllStates(state ->
                ConfiguredModel.builder()
                        .modelFile(model)
                        .build());
    }

    public void torchBlock(Supplier<? extends Block> block, Supplier<? extends Block> wall) {
        ModelFile torch = models().torch(name(block), texture(name(block))).renderType("cutout");
        ModelFile torchwall = models().torchWall(name(wall), texture(name(block))).renderType("cutout");
        simpleBlock(block.get(), torch);
        getVariantBuilder(wall.get()).forAllStates(state ->
                ConfiguredModel.builder()
                        .modelFile(torchwall)
                        .rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 90) % 360)
                        .build());
    }

    public void crossBlock(Supplier<? extends Block> block, String texture) {
        crossBlock(block, models().cross(name(block), texture(texture)).renderType("cutout"));
    }

    public void crossBlock(Supplier<? extends Block> block) {
        crossBlock(block, models().cross(name(block), texture(name(block))).renderType("cutout"));
    }

    public void tintedCrossBlock(Supplier<? extends Block> block) {
        crossBlock(block, models().withExistingParent(name(block), mcLoc("block/tinted_cross")).texture("cross", texture(name(block))).renderType("cutout"));
    }

    public void doubleCrossBlock(Supplier<? extends DoublePlantBlock> block) {
        getVariantBuilder(block.get())
                .forAllStates(
                        state -> {
                            block.get();
                            return ConfiguredModel.builder()
                                    .modelFile(
                                            models().cross(
                                                    "%s_%s".formatted(
                                                            name(block),
                                                            state.getValue(DoublePlantBlock.HALF)
                                                    ),
                                                    texture(
                                                            "%s_%s".formatted(
                                                                    name(block),
                                                                    state.getValue(DoublePlantBlock.HALF)
                                                            ))
                                            ).renderType("cutout")
                                    ).build();
                        }
                );
    }

    public void stairs(Supplier<? extends StairBlock> stairs, Supplier<? extends Block> fullBlock) {
        stairsBlock(stairs.get(), texture(name(fullBlock)));
    }

    public void stairsTranslucent(Supplier<? extends StairBlock> stairs, Supplier<? extends Block> fullBlock) {
        String baseName = name(stairs);
        ResourceLocation texture = texture(name(fullBlock));

        ModelFile stairsBase = this.models().stairs(baseName, texture, texture, texture).renderType("translucent");
        ModelFile stairsInner = this.models().stairsInner("%s_inner".formatted(baseName), texture, texture, texture).renderType("translucent");
        ModelFile stairsOuter = this.models().stairsOuter("%s_outer".formatted(baseName), texture, texture, texture).renderType("translucent");
        this.stairsBlock(stairs.get(), stairsBase, stairsInner, stairsOuter);
    }

    public void slab(Supplier<? extends SlabBlock> slab, Supplier<? extends Block> fullBlock) {
        slabBlock(slab.get(), texture(name(fullBlock)), texture(name(fullBlock)));
    }

    public void slabTranslucent(Supplier<? extends SlabBlock> slab, Supplier<? extends Block> fullBlock) {
        ResourceLocation texture = texture(name(fullBlock));
        slabBlock(
                slab.get(),
                this.models().slab(this.name(slab), texture, texture, texture).renderType("translucent"),
                this.models().slabTop("%s_top".formatted(this.name(slab)), texture, texture, texture).renderType("translucent"),
                this.models().getExistingFile(texture(name(fullBlock)))
        );
    }

    public void wall(Supplier<? extends WallBlock> wall, Supplier<? extends Block> fullBlock) {
        wallBlock(wall.get(), texture(name(fullBlock)));
    }

    public void wallTranslucent(Supplier<? extends WallBlock> wall, Supplier<? extends Block> fullBlock) {
        String baseName = name(fullBlock);
        ResourceLocation texture = texture(baseName);
        wallBlock(
                wall.get(),
                this.models().wallPost("%s_post".formatted(baseName), texture).renderType("translucent"),
                this.models().wallSide("%s_side".formatted(baseName), texture).renderType("translucent"),
                this.models().wallSideTall("%s_side_tall".formatted(baseName), texture).renderType("translucent")
        );
    }

    public void fence(Supplier<? extends FenceBlock> block, Supplier<? extends Block> fullBlock) {
        fenceBlock(block.get(), texture(name(fullBlock)));
        fenceColumn(block, name(fullBlock));
    }

    private void fenceColumn(Supplier<? extends FenceBlock> block, String name) {
        String baseName = name(block);
        fourWayBlock(block.get(),
                models().fencePost("%s_post".formatted(baseName), texture(name)),
                models().fenceSide("%s_side".formatted(baseName), texture(name)));
    }

    public void fenceGate(Supplier<? extends FenceGateBlock> block, Supplier<? extends Block> fullBlock) {
        fenceGateBlock(block.get(), texture(name(fullBlock)));
    }

    public void door(Supplier<? extends DoorBlock> block) {
        doorBlockWithRenderType(block.get(), name(block), texture("%s_lower".formatted(name(block))), texture("%s_upper".formatted(name(block))), "cutout");
    }

    public void trapdoor(Supplier<? extends TrapDoorBlock> block, String name) {
        trapdoorBlockWithRenderType(block.get(), texture("%s_trapdoor".formatted(name)), true, "cutout");
    }

    public void carpet(Supplier<? extends WoolCarpetBlock> block) {
        simpleBlock(block.get(), models().carpet(name(block), texture(name(block))));
    }

    public void button(Supplier<? extends ButtonBlock> block, Supplier<? extends Block> fullBlock) {
        buttonBlock(block.get(), texture(name(fullBlock)));
    }

    public void pressurePlate(Supplier<? extends PressurePlateBlock> block, Supplier<? extends Block> fullBlock) {
        pressurePlateBlock(block.get(), texture(name(fullBlock)));
    }

    public void cutout(Supplier<? extends Block> block) {
        simpleBlock(block.get(), models().cubeAll(name(block), texture(name(block))).renderType("cutout"));
    }

    public void cake(Supplier<? extends HoneyTreatBlock> treat) {
        String name = name(treat);
        ResourceLocation side = ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "block/%s_side".formatted(name));
        ResourceLocation bottom = ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "block/%s_bottom".formatted(name));
        ResourceLocation top = ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "block/%s_top".formatted(name));
        ResourceLocation inside = ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "block/%s_inner".formatted(name));

        getVariantBuilder(treat.get())
                .forAllStates(
                        state -> {
                            treat.get();
                            if (state.getValue(HoneyTreatBlock.BITES) == 0) {
                                return ConfiguredModel.builder()
                                        .modelFile(models()
                                                .withExistingParent(name, mcLoc("block/cake"))
                                                .texture("particle", side)
                                                .texture("side", side)
                                                .texture("bottom", bottom)
                                                .texture("top", top))
                                        .build();
                            } else {
                                return ConfiguredModel.builder()
                                        .modelFile(models()
                                                .withExistingParent(
                                                        "%s_slice_%d".formatted(name, state.getValue(HoneyTreatBlock.BITES)),
                                                        mcLoc("block/cake_slice%d".formatted(state.getValue(HoneyTreatBlock.BITES)))
                                                )
                                                .texture("particle", side)
                                                .texture("bottom", bottom)
                                                .texture("top", top)
                                                .texture("side", side)
                                                .texture("inside", inside))
                                        .build();
                            }
                        }
                );
    }

    public void cakeWithCandle(Supplier<? extends CandleHoneyTreatBlock> candleTreat, Supplier<? extends HoneyTreatBlock> treat, Block candle) {
        String name = name(treat);
        ResourceLocation side = ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "block/%s_side".formatted(name));
        ResourceLocation bottom = ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "block/%s_bottom".formatted(name));
        ResourceLocation top = ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "block/%s_top".formatted(name));
        ResourceLocation inside = ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "block/%s_inner".formatted(name));
        ResourceLocation candleRL = ResourceLocation.fromNamespaceAndPath("minecraft", "block/%s".formatted(candle.getDescriptionId().replaceFirst("block.minecraft.", "")));
        ResourceLocation candleLit = ResourceLocation.fromNamespaceAndPath("minecraft", "block/%s_lit".formatted(candle.getDescriptionId().replaceFirst("block.minecraft.", "")));
        getVariantBuilder(candleTreat.get())
                .forAllStates(
                        state -> {
                            candleTreat.get();
                            if (state.getValue(CandleHoneyTreatBlock.LIT)) {
                                return ConfiguredModel.builder()
                                        .modelFile(models()
                                                .withExistingParent(
                                                        "%s_lit".formatted(name(candleTreat)),
                                                        mcLoc("block/template_cake_with_candle")
                                                )
                                                .texture("particle", side)
                                                .texture("bottom", bottom)
                                                .texture("top", top)
                                                .texture("candle_lit", candleLit)
                                                .texture("side", side)
                                                .texture("inside", inside))
                                        .build();
                            } else {
                                return ConfiguredModel.builder()
                                        .modelFile(models()
                                                .withExistingParent(
                                                        name(candleTreat),
                                                        mcLoc("block/template_cake_with_candle")
                                                )
                                                .texture("particle", side)
                                                .texture("bottom", bottom)
                                                .texture("top", top)
                                                .texture("candle", candleRL)
                                                .texture("side", side)
                                                .texture("inside", inside))
                                        .build();
                            }
                        }
                );
    }

    public void craftingTable(Supplier<? extends CraftingTableBlock> craftingTable, Supplier<? extends Block> bottom) {
        ResourceLocation down = ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "block/%s".formatted(name(bottom)));
        ResourceLocation up = ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "block/%s_top".formatted(name(craftingTable)));
        ResourceLocation side = ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "block/%s_side".formatted(name(craftingTable)));
        simpleBlock(craftingTable.get(), models().cube(name(craftingTable), down, up, side, side, side, side).texture("particle", side));
    }

    public void craftingTable(Supplier<? extends CraftingTableBlock> craftingTable) {
        ResourceLocation down = ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "block/%s_bottom".formatted(name(craftingTable)));
        ResourceLocation up = ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "block/%s_top".formatted(name(craftingTable)));
        ResourceLocation side = ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "block/%s_side".formatted(name(craftingTable)));
        simpleBlock(craftingTable.get(), models().cube(name(craftingTable), down, up, side, side, side, side).texture("particle", side));
    }

    public void furnace(Supplier<? extends Block> furnace) {
        ResourceLocation front = ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "block/%s_front".formatted(name(furnace)));
        ResourceLocation front_on = ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "block/%s_front_on".formatted(name(furnace)));
        ResourceLocation side = ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "block/%s_side".formatted(name(furnace)));
        ResourceLocation top = ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "block/%s_top".formatted(name(furnace)));
        getVariantBuilder(furnace.get()).forAllStates(
                state -> {
                    if (state.getValue(AbstractFurnaceBlock.LIT)) {
                        return ConfiguredModel.builder()
                                .rotationY(state.getValue(AbstractFurnaceBlock.FACING) == Direction.EAST ? 90 : state.getValue(AbstractFurnaceBlock.FACING) == Direction.SOUTH ? 180 : state.getValue(AbstractFurnaceBlock.FACING) == Direction.WEST ? 270 : 0)
                                .modelFile(models().orientable("%s_lit".formatted(name(furnace)), side, front_on, top))
                                .build();
                    } else {
                        return ConfiguredModel.builder()
                                .rotationY(state.getValue(AbstractFurnaceBlock.FACING) == Direction.EAST ? 90 : state.getValue(AbstractFurnaceBlock.FACING) == Direction.SOUTH ? 180 : state.getValue(AbstractFurnaceBlock.FACING) == Direction.WEST ? 270 : 0)
                                .modelFile(models().orientable(name(furnace), side, front, top))
                                .build();
                    }
                }
        );
    }

    public void sign(Supplier<? extends StandingSignBlock> standingBlock, Supplier<? extends WallSignBlock> wallBlock, String name) {
        signBlock(standingBlock.get(), wallBlock.get(), modLoc("block/%s".formatted(name)));
    }

    public void hangingSign(Supplier<? extends CeilingHangingSignBlock> standingBlock, Supplier<? extends WallHangingSignBlock> wallBlock, String name) {
        ModelFile model = models().getBuilder(name(standingBlock)).texture("particle", modLoc("block/%s".formatted(name)));
        simpleBlock(standingBlock.get(), model);
        simpleBlock(wallBlock.get(), model);
    }

    public void vines(Supplier<? extends VineBlock> vine) {
        simpleBlock(vine.get(), models().withExistingParent(name(vine), mcLoc("block/vine")).texture("vine", texture(name(vine))).texture("particle", texture(name(vine))).renderType("cutout"));
    }

    public void crop(Supplier<? extends ModCropBlock> crop) {
        getVariantBuilder(crop.get()).forAllStates(state -> cropStates(state, name(crop)));
    }

    public void bush(Supplier<? extends Block> bush, IntegerProperty age) {
        getVariantBuilder(bush.get()).forAllStates(state -> bushStates(state, name(bush), age));
    }

    public void egg(Supplier<? extends Block> egg) {
        ModelFile model = models().withExistingParent(name(egg), mcLoc("block/dragon_egg"))
                .texture("particle", texture(name(egg)))
                .texture("all", texture(name(egg)));
        simpleBlock(egg.get(), model);
    }

    public void dust(Supplier<? extends Block> dust) {
        getVariantBuilder(dust.get()).forAllStates(this::dustStates);
    }

    public void lamp(Supplier<? extends Block> lamp) {
        getVariantBuilder(lamp.get()).forAllStates(this::lampStates);
    }

    public void stigma(Supplier<? extends Block> stigma) {
        block(stigma, "stigma");
    }

    public void hugeMushroom(Supplier<? extends HugeMushroomBlock> hugeMushroom, String texture, boolean isStem) {
        ModelFile outerModel = models().withExistingParent(name(hugeMushroom), mcLoc("block/template_single_face")).texture("texture", texture("mushroom_block_skin_%s".formatted(isStem ? "stem_%s".formatted(texture) : texture)));
        ModelFile innerModel = models().withExistingParent("%s_inside".formatted(name(hugeMushroom)), mcLoc("block/template_single_face")).texture("texture", texture("mushroom_block_inside_%s".formatted(texture)));

        MultiPartBlockStateBuilder builder = getMultipartBuilder(hugeMushroom.get());
        builder
                .part().modelFile(outerModel).addModel()
                .condition(HugeMushroomBlock.NORTH, true)
                .end()

                .part().modelFile(outerModel).uvLock(true).rotationY(90).addModel()
                .condition(HugeMushroomBlock.EAST, true)
                .end()

                .part().modelFile(outerModel).uvLock(true).rotationY(180).addModel()
                .condition(HugeMushroomBlock.SOUTH, true)
                .end()

                .part().modelFile(outerModel).uvLock(true).rotationY(270).addModel()
                .condition(HugeMushroomBlock.WEST, true)
                .end()

                .part().modelFile(outerModel).uvLock(true).rotationX(270).addModel()
                .condition(HugeMushroomBlock.UP, true)
                .end()

                .part().modelFile(outerModel).uvLock(true).rotationX(90).addModel()
                .condition(HugeMushroomBlock.DOWN, true)
                .end()

                .part().modelFile(innerModel).addModel()
                .condition(HugeMushroomBlock.NORTH, false)
                .end()

                .part().modelFile(innerModel).uvLock(true).rotationY(90).addModel()
                .condition(HugeMushroomBlock.EAST, false)
                .end()

                .part().modelFile(innerModel).uvLock(true).rotationY(180).addModel()
                .condition(HugeMushroomBlock.SOUTH, false)
                .end()

                .part().modelFile(innerModel).uvLock(true).rotationY(270).addModel()
                .condition(HugeMushroomBlock.WEST, false)
                .end()

                .part().modelFile(innerModel).uvLock(true).rotationX(270).addModel()
                .condition(HugeMushroomBlock.UP, false)
                .end()

                .part().modelFile(innerModel).uvLock(true).rotationX(90).addModel()
                .condition(HugeMushroomBlock.DOWN, false)
                .end();
    }

    private ConfiguredModel[] cropStates(BlockState state, String modelName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(
                models().crop(
                        "%s_%d".formatted(modelName, state.getValue(ModCropBlock.AGE)),
                        modLoc("block/%s_%d".formatted(modelName, state.getValue(ModCropBlock.AGE)))
                ).renderType("cutout")
        );

        return models;
    }

    private ConfiguredModel[] bushStates(BlockState state, String modelName, IntegerProperty property) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(
                models().cross(
                        "%s_%d".formatted(modelName, state.getValue(property)),
                        modLoc("block/%s_%d".formatted(modelName, state.getValue(property)))
                ).renderType("cutout")
        );
        return models;
    }

    private ConfiguredModel[] dustStates(BlockState state) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        int height = state.getValue(SnowLayerBlock.LAYERS) * 2;

        if (height != 16) {
            models[0] = new ConfiguredModel(
                    models().withExistingParent(
                                    "dust_height%d".formatted(height),
                                    mcLoc("snow_height%d".formatted(height))
                            )
                            .texture("texture", modLoc("block/dust"))
                            .texture("particle", modLoc("block/dust"))
            );
        } else {
            models[0] = new ConfiguredModel(
                    models().cubeAll("dust", modLoc("block/dust"))
            );
        }
        return models;
    }

    private ConfiguredModel[] lampStates(BlockState state) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        boolean lit = state.getValue(RedstoneLampBlock.LIT);

        models[0] = new ConfiguredModel(
                models().withExistingParent("red_gem_lamp_%s".formatted(lit ? "on" : "off"), mcLoc("block/redstone_lamp"))
                        .texture("all", modLoc(lit ? "block/red_lamp_on" : "block/red_lamp_off"))
        );

        return models;
    }
}
