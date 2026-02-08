package erebus.registries.helpers;

import erebus.Erebus;
import erebus.block.ErebusChestBlock;
import erebus.block.plants.ModBerryBushBlock;
import erebus.block.plants.ModCropBlock;
import erebus.block.util.ConnectedTextureBlock;
import erebus.registries.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

import static erebus.registries.blocks.ModBlocks.BLOCKS;

public class ModBlockHelpers {
    protected static DeferredBlock<Block> registerTransparentBlock(String name, Properties properties) {
        return registerBlock(name, () -> new TransparentBlock(properties.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix(name)))));
    }

    protected static DeferredBlock<Block> registerConnectedTextureBlock(String name, Properties properties) {
        return registerBlock(name, () -> new ConnectedTextureBlock(properties.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix(name)))));
    }

    protected static DeferredBlock<Block> registerStairs(String name, Supplier<? extends Block> baseTexture, Properties properties) {
        return registerBlock(name, () -> new StairBlock(baseTexture.get().defaultBlockState(), properties.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix(name)))));
    }

    protected static DeferredBlock<Block> registerDoor(String name, BlockSetType type, Properties properties) {
        return registerBlock(name, () -> new DoorBlock(type, properties.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix(name)))));
    }

    protected static DeferredBlock<Block> registerSlab(String name, Properties properties) {
        return registerBlock(name, () -> new SlabBlock(properties.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix(name)))));
    }

    protected static DeferredBlock<Block> registerFence(String name, Properties properties) {
        return registerBlock(name, () -> new FenceBlock(properties.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix(name)))));
    }

    protected static DeferredBlock<Block> registerSimpleFenceGate(String name) {
        return registerFenceGate(name, WoodType.OAK, Properties.ofFullCopy(Blocks.OAK_FENCE_GATE).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix(name))));
    }

    protected static DeferredBlock<Block> registerFenceGate(String name, WoodType type, Properties properties) {
        return registerBlock(name, () -> new FenceGateBlock(type, properties.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix(name)))));
    }

    protected static DeferredBlock<Block> registerBush(String name, Supplier<? extends Item> berry, Properties properties) {
        return registerBlock(name, () -> new ModBerryBushBlock(berry, properties.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix(name)))));
    }

    protected static DeferredBlock<Block> registerCrop(String name, Supplier<? extends Item> seed, Properties properties) {
        return registerBlock(name, () -> new ModCropBlock(properties.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix(name))), seed));
    }

    protected static DeferredBlock<Block> registerLog(String name, Properties properties) {
        return registerBlock(name, () -> new RotatedPillarBlock(properties.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix(name)))));
    }

    protected static DeferredBlock<Block> registerSapling(String name, TreeGrower grower) {
        return registerBlock(name, () -> new SaplingBlock(grower, Properties.ofFullCopy(Blocks.SHORT_GRASS).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix(name)))));
    }

    protected static DeferredBlock<Block> registerDoublePlant(String name, Properties properties) {
        return registerBlock(name, () -> new DoublePlantBlock(properties.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix(name)))));
    }

    protected static DeferredBlock<Block> registerHugeMushroom(String name, Properties properties) {
        return registerBlock(name, () -> new HugeMushroomBlock(properties.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix(name)))));
    }

    protected static DeferredBlock<Block> registerChest(String name, Properties properties) {
        return registerBlock(name, () -> new ErebusChestBlock(properties.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix(name))), name));
    }

    protected static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> deferredBlock = BLOCKS.registerBlock(name, (_ -> block.get()));
        registerBlockItem(deferredBlock);
        return deferredBlock;
    }

    protected static DeferredBlock<Block> registerSimpleBlock(String name, Properties properties) {
        return registerBlock(name, () -> new Block(properties.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix(name)))));
    }
    
    protected static <T extends Block> DeferredBlock<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.registerBlock(name, (_ -> block.get()));
    }

    protected static <T extends Block> void registerBlockItem(DeferredBlock<T> block) {
        ModItems.ITEMS.registerSimpleBlockItem(block);
    }
}
