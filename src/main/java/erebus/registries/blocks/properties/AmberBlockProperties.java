package erebus.registries.blocks.properties;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class AmberBlockProperties {

    public static final Properties AMBER_PROPERTIES = Properties.of()
            .noOcclusion()
            .strength(1.5F, 10.0F)
            .isViewBlocking((blockState, blockGetter, blockPos) -> false)
            .mapColor(MapColor.GOLD)
            .sound(SoundType.GLASS);

    public static final Properties GLOWING_JAR_PROPERTIES = Properties.of()
            .strength(0.5F, 10.0F)
            .sound(SoundType.GLASS)
            .lightLevel(value -> 15)
            .noOcclusion()
            .noTerrainParticles()
            .randomTicks()
            .isViewBlocking((state, getter, pos) -> false);

    public static final Properties FLUID_JAR_PROPERTIES = Properties.ofFullCopy(Blocks.GLASS)
            .strength(1.0F, 2000.0F)
            .sound(SoundType.GLASS)
            .noOcclusion()
            .isViewBlocking((state, getter, pos) -> false);

    public static final Properties AMBER_DOOR_PROPERTIES = Properties.of()
            .mapColor(MapColor.GOLD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0F)
            .noOcclusion()
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY);
}
