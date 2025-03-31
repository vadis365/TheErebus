package erebus.registries.helpers;

import erebus.block.ConnectedTextureBlock;
import erebus.block.ModBerryBushBlock;
import erebus.block.ModCropBlock;
import erebus.registries.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
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

    protected static DeferredBlock<FenceBlock> registerFence(String name, BlockBehaviour.Properties props) {
        return registerBlock(name, () -> new FenceBlock(props));
    }

    protected static DeferredBlock<FenceGateBlock> registerSimpleFenceGate(String name) {
        return registerBlock(name, () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    }

    protected static DeferredBlock<FenceGateBlock> registerFenceGate(String name, WoodType type, BlockBehaviour.Properties props) {
        return registerBlock(name, () -> new FenceGateBlock(type, props));
    }

    protected static DeferredBlock<ModBerryBushBlock> registerBush(String name, Supplier<? extends Item> berry, BlockBehaviour.Properties properties) {
        return registerBlock(name, () -> new ModBerryBushBlock(berry.get(), properties));
    }

    protected static DeferredBlock<ModCropBlock> registerCrop(String name, Supplier<? extends Item> seed, BlockBehaviour.Properties properties) {
        return registerBlock(name, () -> new ModCropBlock(properties, seed));
    }

    protected static DeferredBlock<SaplingBlock> registerSapling(String name, TreeGrower grower) {
        return registerBlock(name, () -> new SaplingBlock(grower, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
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
