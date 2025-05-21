package erebus.registries.blocks.providers;

import erebus.block.DarkFruitVineBlock;
import erebus.block.GlowshroomStalkBlock;
import erebus.block.PricklyPearBlock;
import erebus.block.util.ModBerryBushBlock;
import erebus.block.util.ModCropBlock;
import erebus.registries.ModItems;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;

public class PlantBlocks extends ModBlockHelpers {

    private static final Properties FLOWER_PROPS = Properties.of().noCollission().noOcclusion().instabreak().sound(SoundType.AZALEA).isViewBlocking((state, level, pos) -> false);

    // MARK: Plants
    public static final DeferredBlock<ModCropBlock> CROP_TURNIP;
    public static final DeferredBlock<ModCropBlock> CROP_CABBAGE;
    public static final DeferredBlock<ModCropBlock> CROP_MANDRAKE;
    public static final DeferredBlock<ModBerryBushBlock> JADE_BERRY_BUSH;
    public static final DeferredBlock<ModBerryBushBlock> HEART_BERRY_BUSH;
    public static final DeferredBlock<ModBerryBushBlock> SWAMP_BERRY_BUSH;
    public static final DeferredBlock<DarkFruitVineBlock> DARK_FRUIT_VINE;
    public static final DeferredBlock<Block> PRICKLY_PEAR;
    public static final DeferredBlock<Block> DARK_CAPPED_MUSHROOM;
    public static final DeferredBlock<Block> DUTCH_CAP_MUSHROOM;
    public static final DeferredBlock<Block> GRANDMAS_SHOES_MUSHROOM;
    public static final DeferredBlock<Block> KAIZERS_FINGERS_MUSHROOM;
    public static final DeferredBlock<Block> SARCASTIC_CZECH_MUSHROOM;
    public static final DeferredBlock<Block> DARK_CAPPED_MUSHROOM_BLOCK;
    public static final DeferredBlock<Block> DUTCH_CAP_MUSHROOM_BLOCK;
    public static final DeferredBlock<Block> GRANDMAS_SHOES_MUSHROOM_BLOCK;
    public static final DeferredBlock<Block> KAIZERS_FINGERS_MUSHROOM_BLOCK;
    public static final DeferredBlock<Block> SARCASTIC_CZECH_MUSHROOM_BLOCK;
    public static final DeferredBlock<Block> GIANT_LILY_PAD;
    public static final DeferredBlock<Block> DESERT_SHRUB;
    public static final DeferredBlock<Block> MIRE_CORAL;
    public static final DeferredBlock<Block> NETTLE;
    public static final DeferredBlock<Block> NETTLE_FLOWERED;
    public static final DeferredBlock<Block> SWAMP_PLANT;
    public static final DeferredBlock<Block> FIRE_BLOOM;
    public static final DeferredBlock<Block> FERN;
    public static final DeferredBlock<Block> FIDDLE_HEAD;
    public static final DeferredBlock<VineBlock> THORNS;
    public static final DeferredBlock<Block> MOSS;
    public static final DeferredBlock<Block> MOULD;
    public static final DeferredBlock<Block> CULTIVATED_MOSS_DOWN;
    public static final DeferredBlock<Block> CULTIVATED_MOULD_DOWN;
    public static final DeferredBlock<WaterlilyBlock> ALGAE;
    public static final DeferredBlock<Block> GLOWSHROOM_BLOCK;
    public static final DeferredBlock<Block> GLOWSHROOM_STALK;
    public static final DeferredBlock<Block> HANGING_WEB;

    // MARK: Flowers
    public static final DeferredBlock<Block> PETAL_BLACK;
    public static final DeferredBlock<Block> PETAL_RED;
    public static final DeferredBlock<Block> PETAL_BROWN;
    public static final DeferredBlock<Block> PETAL_BLUE;
    public static final DeferredBlock<Block> PETAL_PURPLE;
    public static final DeferredBlock<Block> PETAL_CYAN;
    public static final DeferredBlock<Block> PETAL_LIGHT_GRAY;
    public static final DeferredBlock<Block> PETAL_GRAY;
    public static final DeferredBlock<Block> PETAL_PINK;
    public static final DeferredBlock<Block> PETAL_YELLOW;
    public static final DeferredBlock<Block> PETAL_LIGHT_BLUE;
    public static final DeferredBlock<Block> PETAL_MAGENTA;
    public static final DeferredBlock<Block> PETAL_ORANGE;
    public static final DeferredBlock<Block> PETAL_WHITE;

    public static final DeferredBlock<Block> EXPLODING_STIGMA;
    public static final DeferredBlock<Block> STEM;
    public static final DeferredBlock<Block> STIGMA_BLACK;
    public static final DeferredBlock<Block> STIGMA_RED;
    public static final DeferredBlock<Block> STIGMA_BROWN;
    public static final DeferredBlock<Block> STIGMA_BLUE;
    public static final DeferredBlock<Block> STIGMA_PURPLE;
    public static final DeferredBlock<Block> STIGMA_CYAN;
    public static final DeferredBlock<Block> STIGMA_LIGHT_GRAY;
    public static final DeferredBlock<Block> STIGMA_GRAY;
    public static final DeferredBlock<Block> STIGMA_PINK;
    public static final DeferredBlock<Block> STIGMA_YELLOW;
    public static final DeferredBlock<Block> STIGMA_LIGHT_BLUE;
    public static final DeferredBlock<Block> STIGMA_MAGENTA;
    public static final DeferredBlock<Block> STIGMA_ORANGE;
    public static final DeferredBlock<Block> STIGMA_WHITE;

    public static final DeferredBlock<Block> FLOWER_BLACK;
    public static final DeferredBlock<Block> FLOWER_RED;
    public static final DeferredBlock<Block> FLOWER_BROWN;
    public static final DeferredBlock<Block> FLOWER_BLUE;
    public static final DeferredBlock<Block> FLOWER_PURPLE;
    public static final DeferredBlock<Block> FLOWER_CYAN;
    public static final DeferredBlock<Block> FLOWER_LIGHT_GRAY;
    public static final DeferredBlock<Block> FLOWER_GRAY;
    public static final DeferredBlock<Block> FLOWER_PINK;
    public static final DeferredBlock<Block> FLOWER_YELLOW;
    public static final DeferredBlock<Block> FLOWER_LIGHT_BLUE;
    public static final DeferredBlock<Block> FLOWER_MAGENTA;
    public static final DeferredBlock<Block> FLOWER_ORANGE;
    public static final DeferredBlock<Block> FLOWER_WHITE;
    public static final DeferredBlock<Block> FLOWER_RAINBOW;

    // MARK: Flowers Double Height
    public static final DeferredBlock<DoublePlantBlock> BULLRUSH;
    public static final DeferredBlock<DoublePlantBlock> WEEPING_BLUEBELL;
    public static final DeferredBlock<DoublePlantBlock> SUNDEW;
    public static final DeferredBlock<DoublePlantBlock> DROUGHTED_SHRUB;
    public static final DeferredBlock<DoublePlantBlock> TALL_BLOOM;
    public static final DeferredBlock<DoublePlantBlock> TANGLED_STALK;
    public static final DeferredBlock<DoublePlantBlock> HIGH_CAPPED_MUSHROOM;
    public static final DeferredBlock<DoublePlantBlock> TALL_FERN;

    static {
        // MARK: Plants
        CROP_TURNIP = registerCrop("crop_turnip", ModItems.TURNIP, Properties.ofFullCopy(Blocks.BEETROOTS));
        CROP_CABBAGE = registerCrop("crop_cabbage", ModItems.CABBAGE_SEEDS, Properties.ofFullCopy(Blocks.BEETROOTS));
        CROP_MANDRAKE = registerCrop("crop_mandrake", ModItems.MANDRAKE_ROOT, Properties.ofFullCopy(Blocks.BEETROOTS));
        JADE_BERRY_BUSH = registerBush("jade_berry_bush", ModItems.JADE_BERRIES, Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH));
        HEART_BERRY_BUSH = registerBush("heart_berry_bush", ModItems.HEART_BERRIES, Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH));
        SWAMP_BERRY_BUSH = registerBush("swamp_berry_bush", ModItems.SWAMP_BERRIES, Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH));
        DARK_FRUIT_VINE = registerBlock("dark_fruit_vine", () -> new DarkFruitVineBlock(Properties.ofFullCopy(Blocks.OAK_LEAVES).noCollission()));
        PRICKLY_PEAR = registerBlock("prickly_pear", () -> new PricklyPearBlock(Properties.ofFullCopy(Blocks.CACTUS).noOcclusion()));
        DARK_CAPPED_MUSHROOM = registerBlock("dark_capped_mushroom", () -> new MushroomBlock(VegetationFeatures.PATCH_BROWN_MUSHROOM, Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)));
        DUTCH_CAP_MUSHROOM = registerBlock("dutch_cap_mushroom", () -> new MushroomBlock(VegetationFeatures.PATCH_BROWN_MUSHROOM, Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)));
        GRANDMAS_SHOES_MUSHROOM = registerBlock("grandmas_shoes_mushroom", () -> new MushroomBlock(VegetationFeatures.PATCH_BROWN_MUSHROOM, Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)));
        KAIZERS_FINGERS_MUSHROOM = registerBlock("kaizers_fingers_mushroom", () -> new MushroomBlock(VegetationFeatures.PATCH_BROWN_MUSHROOM, Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)));
        SARCASTIC_CZECH_MUSHROOM = registerBlock("sarcastic_czech_mushroom", () -> new MushroomBlock(VegetationFeatures.PATCH_BROWN_MUSHROOM, Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)));
        DARK_CAPPED_MUSHROOM_BLOCK = registerSimpleBlock("dark_capped_mushroom_block", Properties.of().mapColor(MapColor.STONE));
        DUTCH_CAP_MUSHROOM_BLOCK = registerSimpleBlock("dutch_cap_mushroom_block", Properties.of().mapColor(MapColor.STONE));
        GRANDMAS_SHOES_MUSHROOM_BLOCK = registerSimpleBlock("grandmas_shoes_mushroom_block", Properties.of().mapColor(MapColor.STONE));
        KAIZERS_FINGERS_MUSHROOM_BLOCK = registerSimpleBlock("kaizers_fingers_mushroom_block", Properties.of().mapColor(MapColor.STONE));
        SARCASTIC_CZECH_MUSHROOM_BLOCK = registerSimpleBlock("sarcastic_czech_mushroom_block", Properties.of().mapColor(MapColor.STONE));
        GIANT_LILY_PAD = registerSimpleBlock("giant_lily_pad", Properties.of().mapColor(MapColor.STONE));
        DESERT_SHRUB = registerSimpleBlock("desert_shrub", Properties.ofFullCopy(Blocks.TALL_GRASS));
        MIRE_CORAL = registerSimpleBlock("mire_coral", Properties.of().mapColor(MapColor.STONE));
        NETTLE = registerSimpleBlock("nettle", Properties.ofFullCopy(Blocks.SHORT_GRASS));
        NETTLE_FLOWERED = registerSimpleBlock("nettle_flowered", Properties.ofFullCopy(Blocks.SHORT_GRASS));
        SWAMP_PLANT = registerSimpleBlock("swamp_plant", Properties.ofFullCopy(Blocks.TALL_GRASS));
        FIRE_BLOOM = registerSimpleBlock("fire_bloom", Properties.ofFullCopy(Blocks.TALL_GRASS));
        FERN = registerSimpleBlock("fern", Properties.ofFullCopy(Blocks.SHORT_GRASS));
        FIDDLE_HEAD = registerSimpleBlock("fiddle_head", Properties.ofFullCopy(Blocks.TALL_GRASS));
        THORNS = registerBlock("thorns", () -> new VineBlock(Properties.ofFullCopy(Blocks.VINE)));
        MOSS = registerBlock("moss", () -> new VineBlock(Properties.ofFullCopy(Blocks.VINE)));
        MOULD = registerBlock("mould", () -> new VineBlock(Properties.ofFullCopy(Blocks.VINE)));
        CULTIVATED_MOSS_DOWN = registerBlock("cultivated_moss", () -> new VineBlock(Properties.ofFullCopy(Blocks.VINE)));
        CULTIVATED_MOULD_DOWN = registerBlock("cultivated_mould", () -> new VineBlock(Properties.ofFullCopy(Blocks.VINE)));
        ALGAE = registerBlock(
                "algae",
                () -> new WaterlilyBlock(Properties.of()
                        .mapColor(MapColor.PLANT)
                        .instabreak()
                        .sound(SoundType.LILY_PAD)
                        .noOcclusion()
                        .pushReaction(PushReaction.DESTROY)
                )
        );
        GLOWSHROOM_BLOCK = registerSimpleBlock("glowshroom_block", Properties.of().mapColor(MapColor.STONE).noOcclusion());
        GLOWSHROOM_STALK = registerBlock("glowshroom_stalk", () -> new GlowshroomStalkBlock(Properties.of().mapColor(MapColor.STONE)));
        HANGING_WEB = registerSimpleBlock("hanging_web", Properties.of().mapColor(MapColor.STONE).noOcclusion());

        // MARK: Flowers
        PETAL_BLACK = registerSimpleBlock("petal_black", Properties.of().mapColor(MapColor.COLOR_BLACK));
        PETAL_RED = registerSimpleBlock("petal_red", Properties.of().mapColor(MapColor.COLOR_RED));
        PETAL_BROWN = registerSimpleBlock("petal_brown", Properties.of().mapColor(MapColor.COLOR_BROWN));
        PETAL_BLUE = registerSimpleBlock("petal_blue", Properties.of().mapColor(MapColor.COLOR_BLUE));
        PETAL_PURPLE = registerSimpleBlock("petal_purple", Properties.of().mapColor(MapColor.COLOR_PURPLE));
        PETAL_CYAN = registerSimpleBlock("petal_cyan", Properties.of().mapColor(MapColor.COLOR_CYAN));
        PETAL_LIGHT_GRAY = registerSimpleBlock("petal_light_gray", Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY));
        PETAL_GRAY = registerSimpleBlock("petal_gray", Properties.of().mapColor(MapColor.COLOR_GRAY));
        PETAL_PINK = registerSimpleBlock("petal_pink", Properties.of().mapColor(MapColor.COLOR_PINK));
        PETAL_YELLOW = registerSimpleBlock("petal_yellow", Properties.of().mapColor(MapColor.COLOR_YELLOW));
        PETAL_LIGHT_BLUE = registerSimpleBlock("petal_light_blue", Properties.of().mapColor(MapColor.COLOR_YELLOW));
        PETAL_MAGENTA = registerSimpleBlock("petal_magenta", Properties.of().mapColor(MapColor.COLOR_YELLOW));
        PETAL_ORANGE = registerSimpleBlock("petal_orange", Properties.of().mapColor(MapColor.COLOR_ORANGE));
        PETAL_WHITE = registerSimpleBlock("petal_white", Properties.of().mapColor(MapColor.TERRACOTTA_WHITE));

        EXPLODING_STIGMA = registerSimpleBlock("exploding_stigma", Properties.of().mapColor(MapColor.COLOR_YELLOW));
        STEM = registerSimpleBlock("stem", Properties.of().mapColor(MapColor.COLOR_GREEN));
        STIGMA_BLACK = registerSimpleBlock("stigma_black", Properties.of().mapColor(MapColor.COLOR_YELLOW));
        STIGMA_RED = registerSimpleBlock("stigma_red", Properties.of().mapColor(MapColor.COLOR_YELLOW));
        STIGMA_BROWN = registerSimpleBlock("stigma_brown", Properties.of().mapColor(MapColor.COLOR_YELLOW));
        STIGMA_BLUE = registerSimpleBlock("stigma_blue", Properties.of().mapColor(MapColor.COLOR_YELLOW));
        STIGMA_PURPLE = registerSimpleBlock("stigma_purple", Properties.of().mapColor(MapColor.COLOR_YELLOW));
        STIGMA_CYAN = registerSimpleBlock("stigma_cyan", Properties.of().mapColor(MapColor.COLOR_YELLOW));
        STIGMA_LIGHT_GRAY = registerSimpleBlock("stigma_light_gray", Properties.of().mapColor(MapColor.COLOR_YELLOW));
        STIGMA_GRAY = registerSimpleBlock("stigma_gray", Properties.of().mapColor(MapColor.COLOR_YELLOW));
        STIGMA_PINK = registerSimpleBlock("stigma_pink", Properties.of().mapColor(MapColor.COLOR_YELLOW));
        STIGMA_YELLOW = registerSimpleBlock("stigma_yellow", Properties.of().mapColor(MapColor.COLOR_YELLOW));
        STIGMA_LIGHT_BLUE = registerSimpleBlock("stigma_light_blue", Properties.of().mapColor(MapColor.COLOR_YELLOW));
        STIGMA_MAGENTA = registerSimpleBlock("stigma_magenta", Properties.of().mapColor(MapColor.COLOR_YELLOW));
        STIGMA_ORANGE = registerSimpleBlock("stigma_orange", Properties.of().mapColor(MapColor.COLOR_YELLOW));
        STIGMA_WHITE = registerSimpleBlock("stigma_white", Properties.of().mapColor(MapColor.COLOR_YELLOW));

        FLOWER_BLACK = registerSimpleBlock("flower_black", FLOWER_PROPS.mapColor(MapColor.COLOR_BLACK));
        FLOWER_RED = registerSimpleBlock("flower_red", FLOWER_PROPS.mapColor(MapColor.COLOR_RED));
        FLOWER_BROWN = registerSimpleBlock("flower_brown", FLOWER_PROPS.mapColor(MapColor.COLOR_BROWN));
        FLOWER_BLUE = registerSimpleBlock("flower_blue", FLOWER_PROPS.mapColor(MapColor.COLOR_BLUE));
        FLOWER_PURPLE = registerSimpleBlock("flower_purple", FLOWER_PROPS.mapColor(MapColor.COLOR_PURPLE));
        FLOWER_CYAN = registerSimpleBlock("flower_cyan", FLOWER_PROPS.mapColor(MapColor.COLOR_CYAN));
        FLOWER_LIGHT_GRAY = registerSimpleBlock("flower_light_gray", FLOWER_PROPS.mapColor(MapColor.COLOR_LIGHT_GRAY));
        FLOWER_GRAY = registerSimpleBlock("flower_gray", FLOWER_PROPS.mapColor(MapColor.COLOR_GRAY));
        FLOWER_PINK = registerSimpleBlock("flower_pink", FLOWER_PROPS.mapColor(MapColor.COLOR_PINK));
        FLOWER_YELLOW = registerSimpleBlock("flower_yellow", FLOWER_PROPS.mapColor(MapColor.COLOR_YELLOW));
        FLOWER_LIGHT_BLUE = registerSimpleBlock("flower_light_blue", FLOWER_PROPS.mapColor(MapColor.COLOR_LIGHT_BLUE));
        FLOWER_MAGENTA = registerSimpleBlock("flower_magenta", FLOWER_PROPS.mapColor(MapColor.COLOR_MAGENTA));
        FLOWER_ORANGE = registerSimpleBlock("flower_orange", FLOWER_PROPS.mapColor(MapColor.COLOR_ORANGE));
        FLOWER_WHITE = registerSimpleBlock("flower_white", FLOWER_PROPS.mapColor(MapColor.TERRACOTTA_WHITE));
        FLOWER_RAINBOW = registerSimpleBlock("flower_rainbow", FLOWER_PROPS.mapColor(MapColor.COLOR_RED));

        // MARK: Flowers Double Height
        BULLRUSH = registerDoublePlant("bullrush", Properties.ofFullCopy(Blocks.ROSE_BUSH));
        WEEPING_BLUEBELL = registerDoublePlant("weeping_bluebell", Properties.ofFullCopy(Blocks.ROSE_BUSH));
        SUNDEW = registerDoublePlant("sundew", Properties.ofFullCopy(Blocks.ROSE_BUSH));
        DROUGHTED_SHRUB = registerDoublePlant("droughted_shrub", Properties.ofFullCopy(Blocks.ROSE_BUSH));
        TALL_BLOOM = registerDoublePlant("tall_bloom", Properties.ofFullCopy(Blocks.ROSE_BUSH));
        TANGLED_STALK = registerDoublePlant("tangled_stalk", Properties.ofFullCopy(Blocks.ROSE_BUSH));
        HIGH_CAPPED_MUSHROOM = registerDoublePlant("high_capped_mushroom", Properties.ofFullCopy(Blocks.ROSE_BUSH));
        TALL_FERN = registerDoublePlant("tall_fern", Properties.ofFullCopy(Blocks.ROSE_BUSH));
    }

    public static void init() {
    }
}
