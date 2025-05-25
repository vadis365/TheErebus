package erebus.registries.helpers;

import erebus.block.util.ConnectedTextureBlock;
import erebus.block.util.ModBerryBushBlock;
import erebus.block.util.ModCropBlock;
import erebus.registries.ModItems;
import net.minecraft.world.item.BlockItem;
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
    protected static DeferredBlock<TransparentBlock> registerTransparentBlock(String name, Properties properties) {
        return registerBlock(name, () -> new TransparentBlock(properties));
    }

    protected static DeferredBlock<ConnectedTextureBlock> registerConnectedTextureBlock(String name, Properties properties) {
        return registerBlock(name, () -> new ConnectedTextureBlock(properties));
    }

    protected static DeferredBlock<StairBlock> registerStairs(String name, Supplier<? extends Block> baseTexture, Properties props) {
        return registerBlock(name, () -> new StairBlock(baseTexture.get().defaultBlockState(), props));
    }

    protected static DeferredBlock<DoorBlock> registerDoor(String name, BlockSetType type, Properties props) {
        return registerBlock(name, () -> new DoorBlock(type, props));
    }

    protected static DeferredBlock<SlabBlock> registerSlab(String name, Properties props) {
        return registerBlock(name, () -> new SlabBlock(props));
    }

    protected static DeferredBlock<FenceBlock> registerFence(String name, Properties props) {
        return registerBlock(name, () -> new FenceBlock(props));
    }

    protected static DeferredBlock<FenceGateBlock> registerSimpleFenceGate(String name) {
        return registerFenceGate(name, WoodType.OAK, Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    }

    protected static DeferredBlock<FenceGateBlock> registerFenceGate(String name, WoodType type, Properties props) {
        return registerBlock(name, () -> new FenceGateBlock(type, props));
    }

    protected static DeferredBlock<ModBerryBushBlock> registerBush(String name, Supplier<? extends Item> berry, Properties properties) {
        return registerBlock(name, () -> new ModBerryBushBlock(berry, properties));
    }

    protected static DeferredBlock<ModCropBlock> registerCrop(String name, Supplier<? extends Item> seed, Properties properties) {
        return registerBlock(name, () -> new ModCropBlock(properties, seed));
    }

    protected static DeferredBlock<SaplingBlock> registerSapling(String name, TreeGrower grower) {
        return registerBlock(name, () -> new SaplingBlock(grower, Properties.ofFullCopy(Blocks.SHORT_GRASS)));
    }

    protected static DeferredBlock<DoublePlantBlock> registerDoublePlant(String name, Properties properties) {
        return registerBlock(name, () -> new DoublePlantBlock(properties));
    }

    protected static DeferredBlock<HugeMushroomBlock> registerHugeMushroom(String name, Properties properties) {
        return registerBlock(name, () -> new HugeMushroomBlock(properties));
    }

    protected static DeferredBlock<Block> registerSimpleBlock(String name, Properties properties) {
        DeferredBlock<Block> deferredBlock = BLOCKS.registerSimpleBlock(name, properties);
        registerBlockItem(name, deferredBlock);
        return deferredBlock;
    }

    protected static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> deferredBlock = BLOCKS.register(name, block);
        registerBlockItem(name, deferredBlock);
        return deferredBlock;
    }
    
    protected static <T extends Block> DeferredBlock<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    protected static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
