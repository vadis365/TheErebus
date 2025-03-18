package erebus.datagen.providers;

import erebus.Erebus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public abstract class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, Erebus.MODID, helper);
    }

    protected ResourceLocation texture(String name) {
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

    public void crossBlock(Supplier<? extends Block> block) {
        crossBlock(block, models().cross(name(block), texture(name(block))).renderType("cutout"));
    }

    public void tintedCrossBlock(Supplier<? extends Block> block) {
        crossBlock(block, models().withExistingParent(name(block), mcLoc("block/tinted_cross")).texture("cross", texture(name(block))).renderType("cutout"));
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

    public void door(Supplier<? extends DoorBlock> block, String name) {
        doorBlockWithRenderType(block.get(), name(block), texture("%s_door_bottom".formatted(name)), texture("%s_door_top".formatted(name)), "cutout");
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

    public void sign(Supplier<? extends StandingSignBlock> standingBlock, Supplier<? extends WallSignBlock> wallBlock, String name) {
        signBlock(standingBlock.get(), wallBlock.get(), modLoc("block/%s".formatted(name)));
    }

    public void hangingSign(Supplier<? extends CeilingHangingSignBlock> standingBlock, Supplier<? extends WallHangingSignBlock> wallBlock, String name) {
        ModelFile model = models().getBuilder(name(standingBlock)).texture("particle", modLoc("block/%s".formatted(name)));
        simpleBlock(standingBlock.get(), model);
        simpleBlock(wallBlock.get(), model);
    }
}
