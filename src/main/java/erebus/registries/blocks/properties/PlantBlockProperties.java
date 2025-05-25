package erebus.registries.blocks.properties;

import erebus.utils.BlockPropUtils;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class PlantBlockProperties {
    private static final Properties BASE = Properties.of().mapColor(MapColor.PLANT);

    public static final Properties CROP_PROPS = BASE
            .noCollission()
            .randomTicks()
            .instabreak()
            .sound(SoundType.CROP)
            .pushReaction(PushReaction.DESTROY);

    public static final Properties BUSH_PROPS = BASE
            .randomTicks()
            .noCollission()
            .sound(SoundType.SWEET_BERRY_BUSH)
            .pushReaction(PushReaction.DESTROY);

    public static final Properties DARK_FRUIT_VINE_PROPS = BASE
            .replaceable()
            .noCollission()
            .randomTicks()
            .strength(0.2F)
            .sound(SoundType.VINE)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY);

    public static final Properties PRICKLY_PEAR_PROPS = BASE
            .randomTicks()
            .strength(0.4F)
            .sound(SoundType.WOOL)
            .pushReaction(PushReaction.DESTROY);

    private static final Properties MUSHROOM_PROPS = Properties.of()
            .noCollission()
            .randomTicks()
            .instabreak()
            .sound(SoundType.GRASS)
            .lightLevel((state) -> 1)
            .hasPostProcess(BlockPropUtils::always)
            .pushReaction(PushReaction.DESTROY);

    private static final Properties HUGE_MUSHROOM_PROPS = Properties.of()
            .mapColor(MapColor.DIRT)
            .instrument(NoteBlockInstrument.BASS)
            .strength(0.2F)
            .sound(SoundType.WOOD)
            .ignitedByLava();

    private static final Properties FLOWER_PROPS = Properties.of()
            .noCollission()
            .noOcclusion()
            .instabreak()
            .sound(SoundType.AZALEA)
            .isViewBlocking(BlockPropUtils::never);

    public static final Properties DARK_CAPPED_MUSHROOM_PROPS = MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties DUTCH_CAP_MUSHROOM_PROPS = MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties GRANDMAS_SHOES_MUSHROOM_PROPS = MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties KAIZERS_FINGERS_MUSHROOM_PROPS = MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties SARCASTIC_CZECH_MUSHROOM_PROPS = MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties DARK_CAPPED_MUSHROOM_BLOCK_PROPS = HUGE_MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties DUTCH_CAP_MUSHROOM_BLOCK_PROPS = HUGE_MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties GRANDMAS_SHOES_MUSHROOM_BLOCK_PROPS = HUGE_MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties KAIZERS_FINGERS_MUSHROOM_BLOCK_PROPS = HUGE_MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties SARCASTIC_CZECH_MUSHROOM_BLOCK_PROPS = HUGE_MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);

    public static final Properties FLOWER_BLACK_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_BLACK);
    public static final Properties FLOWER_RED_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_RED);
    public static final Properties FLOWER_BROWN_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties FLOWER_BLUE_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_BLUE);
    public static final Properties FLOWER_PURPLE_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_PURPLE);
    public static final Properties FLOWER_CYAN_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_CYAN);
    public static final Properties FLOWER_LIGHT_GRAY_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_LIGHT_GRAY);
    public static final Properties FLOWER_GRAY_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_GRAY);
    public static final Properties FLOWER_PINK_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_PINK);
    public static final Properties FLOWER_YELLOW_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_YELLOW);
    public static final Properties FLOWER_LIGHT_BLUE_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_LIGHT_BLUE);
    public static final Properties FLOWER_MAGENTA_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_MAGENTA);
    public static final Properties FLOWER_ORANGE_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_ORANGE);
    public static final Properties FLOWER_WHITE_PROPS = FLOWER_PROPS.mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties FLOWER_RAINBOW_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_RED);
}
