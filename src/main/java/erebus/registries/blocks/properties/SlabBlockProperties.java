package erebus.registries.blocks.properties;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;

public class SlabBlockProperties {

    // Wood slabs
    public static final Properties SLAB_PLANKS;

    // Stone-like slabs
    public static final Properties SLAB_STONE;

    public static final Properties SLAB_AMBER;
    public static final Properties SLAB_AMBER_BRICKS;

    static {
        // Base presets to mirror vanilla behavior used in provider
        SLAB_PLANKS = Properties.ofFullCopy(Blocks.OAK_SLAB);
        SLAB_STONE = Properties.ofFullCopy(Blocks.STONE_SLAB);

        // Amber variants copy glass with tweaks same as provider
        SLAB_AMBER = Properties.ofFullCopy(Blocks.GLASS)
                .strength(1.5F)
                .noOcclusion()
                .isViewBlocking((blockState, blockGetter, blockPos) -> false)
                .sound(SoundType.GLASS)
                .mapColor(MapColor.GOLD);

        SLAB_AMBER_BRICKS = Properties.ofFullCopy(Blocks.GLASS)
                .strength(1.5F)
                .noOcclusion()
                .isViewBlocking((blockState, blockGetter, blockPos) -> false)
                .sound(SoundType.GLASS)
                .mapColor(MapColor.GOLD);
    }
}
