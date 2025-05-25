package erebus.datagen.providers;

import erebus.Erebus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public abstract class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Erebus.MODID, existingFileHelper);
    }

    private String blockName(Supplier<? extends Block> block) {
        return BuiltInRegistries.BLOCK.getKey(block.get()).getPath();
    }

    private ResourceLocation texture(String name) {
        return modLoc("block/%s".formatted(name));
    }

    public void itemFence(Supplier<? extends FenceBlock> block, Supplier<? extends Block> texture) {
        withExistingParent(blockName(block), mcLoc("block/fence_inventory")).texture("texture", "block/%s".formatted(blockName(texture)));
    }

    public void fenceGate(Supplier<? extends FenceGateBlock> fence, Supplier<? extends Block> texture) {
        fenceGate(blockName(fence), modLoc("block/%s".formatted(blockName(texture))));
    }

    public ItemModelBuilder block(Supplier<? extends Block> block) {
        return block(block, blockName(block));
    }

    public ItemModelBuilder block(Supplier<? extends Block> block, String name) {
        return withExistingParent(blockName(block), modLoc("block/%s".formatted(name)));
    }

    public void hugeMushroom(Supplier<? extends Block> block, Supplier<? extends Block> stem) {
        withExistingParent(blockName(block), modLoc("block/%s_inventory".formatted(blockName(block))));
        withExistingParent(blockName(stem), modLoc("block/%s_inventory".formatted(blockName(block))));
    }

    public void blockFlat(Supplier<? extends Block> block) {
        withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/%s".formatted(blockName(block))));
    }

    public void blockFlatWithBlockTexture(Supplier<? extends Block> block, String name) {
        withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/%s".formatted(name)))
                .renderType("translucent");
    }

    public void blockFlatWithItemTexture(Supplier<? extends Block> block, String name) {
        withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/%s".formatted(name)));
    }

    public void normalItem(Supplier<? extends Item> item) {
        withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/%s".formatted(BuiltInRegistries.ITEM.getKey(item.get()).getPath())));
    }
    
    public void normalItemCutout(Supplier<? extends Item> item) {
        withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/%s".formatted(BuiltInRegistries.ITEM.getKey(item.get()).getPath()))).renderType("cutout");
    }

    public void normalItemSpecifiedTexture(Supplier<? extends Item> item, String name) {
        withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/%s".formatted(name)));
    }

    public void torchItem(Supplier<? extends Block> item) {
        withExistingParent(BuiltInRegistries.BLOCK.getKey(item.get()).getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/%s".formatted(BuiltInRegistries.BLOCK.getKey(item.get()).getPath())));
    }

    public void toolItem(Supplier<? extends Item> item) {
        withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/%s".formatted(BuiltInRegistries.ITEM.getKey(item.get()).getPath())));
    }

    public void rodItem(Supplier<? extends Item> item) {
        withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/handheld_rod"))
                .texture("layer0", modLoc("item/%s".formatted(BuiltInRegistries.ITEM.getKey(item.get()).getPath())));
    }

    public void egg(Supplier<? extends Item> item) {
        withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/template_spawn_egg"));
    }

    public void sign(Supplier<? extends SignBlock> sign) {
        withExistingParent(blockName(sign), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/%s".formatted(blockName(sign))));
    }

    public ItemModelBuilder wall(Supplier<? extends WallBlock> wall, Supplier<? extends Block> fullBlock) {
        return wallInventory(BuiltInRegistries.BLOCK.getKey(wall.get()).getPath(), texture(blockName(fullBlock)));
    }

    public ItemModelBuilder slab(Supplier<? extends SlabBlock> slab, Supplier<? extends Block> fullBlock) {
        ResourceLocation texture = texture(blockName(fullBlock));
        return slab(BuiltInRegistries.BLOCK.getKey(slab.get()).getPath(), texture, texture, texture);
    }

    public ItemModelBuilder stairs(Supplier<? extends StairBlock> stairs, Supplier<? extends Block> fullBlock) {
        ResourceLocation texture = texture(blockName(fullBlock));
        return stairs(BuiltInRegistries.BLOCK.getKey(stairs.get()).getPath(), texture, texture, texture);
    }

    public ItemModelBuilder button(Supplier<? extends ButtonBlock> button, Supplier<? extends Block> fullBlock) {
        return buttonInventory(BuiltInRegistries.BLOCK.getKey(button.get()).getPath(), texture(blockName(fullBlock)));
    }

    public void trapdoor(Supplier<? extends TrapDoorBlock> trapdoor) {
        withExistingParent(BuiltInRegistries.BLOCK.getKey(trapdoor.get()).getPath(), ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "block/%s_bottom".formatted(blockName(trapdoor))));
    }

    public void bucket(Supplier<? extends Item> bucket) {
        withExistingParent(BuiltInRegistries.ITEM.getKey(bucket.get()).getPath(), "bucketlib:item/universal_bucket")
                .texture("base", modLoc("item/%s_base".formatted(BuiltInRegistries.ITEM.getKey(bucket.get()))))
                .texture("fluidMask", modLoc("item/%s_fluid".formatted(BuiltInRegistries.ITEM.getKey(bucket.get()))));
    }
}
