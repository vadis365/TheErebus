package erebus.registries.helpers;

import erebus.block.ConnectedTextureBlock;
import erebus.block.ModBushBlock;
import erebus.registries.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

import static erebus.registries.ModBlocks.BLOCKS;

public class ModBlockHelpers {
    protected static DeferredBlock<TransparentBlock> registerTransparentBlock(String name, BlockBehaviour.Properties properties) {
        return registerBlock(name, () -> new TransparentBlock(properties));
    }

    protected static DeferredBlock<ConnectedTextureBlock> registerConnectedTextureBlock(String name, BlockBehaviour.Properties properties) {
        return registerBlock(name, () -> new ConnectedTextureBlock(properties));
    }

    protected static DeferredBlock<StairBlock> registerStairs(String name, Supplier<? extends Block> baseTexture, BlockBehaviour.Properties props) {
        return registerBlock(name, () -> new StairBlock(baseTexture.get().defaultBlockState(), props));
    }

    protected static DeferredBlock<DoorBlock> registerDoor(String name, BlockSetType type, BlockBehaviour.Properties props) {
        return registerBlock(name, () -> new DoorBlock(type, props));
    }

    protected static DeferredBlock<BushBlock> registerBush(String name, BlockBehaviour.Properties properties) {
        return registerBlock(name, () -> new ModBushBlock(properties));
    }

    protected static DeferredBlock<DoublePlantBlock> registerDoublePlant(String name, BlockBehaviour.Properties properties) {
        return registerBlock(name, () -> new DoublePlantBlock(properties));
    }

    protected static DeferredBlock<Block> registerSimpleBlock(String name, BlockBehaviour.Properties properties) {
        DeferredBlock<Block> deferredBlock = BLOCKS.registerSimpleBlock(name, properties);
        registerBlockItem(name, deferredBlock);
        return deferredBlock;
    }

    protected static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> deferredBlock = BLOCKS.register(name, block);
        registerBlockItem(name, deferredBlock);
        return deferredBlock;
    }

    protected static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
