package erebus.registries.blocks.properties;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;

public class AmberBlockProperties {

    public static final Properties AMBER_PROPERTIES = Properties.of()
            .noOcclusion()
            .isViewBlocking((blockState, blockGetter, blockPos) -> false)
            .mapColor(MapColor.GOLD)
            .explosionResistance(10.0F)
            .sound(SoundType.GLASS);

    public static final Properties GLOWING_JAR_PROPERTIES = Properties.of()
            .strength(0.5F, 10.0F)
            .sound(SoundType.GLASS)
            .lightLevel(value -> 1)
            .noOcclusion()
            .isViewBlocking((state, getter, pos) -> false);

    public static final Properties FLUID_JAR_PROPERTIES = Properties.ofFullCopy(Blocks.GLASS)
            .mapColor(MapColor.STONE)
            .strength(1.0F, 2000.0F)
            .sound(SoundType.GLASS)
            .noOcclusion()
            .isViewBlocking((state, getter, pos) -> false);

    public static final Properties AMBER_DOOR_PROPERTIES = Properties.ofFullCopy(Blocks.OAK_DOOR);
}
