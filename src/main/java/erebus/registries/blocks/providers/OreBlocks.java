package erebus.registries.blocks.providers;

import erebus.registries.blocks.properties.OreBlockProperties;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class OreBlocks extends ModBlockHelpers {

    public static final DeferredBlock<Block> IRON;
    public static final DeferredBlock<Block> GOLD;
    public static final DeferredBlock<Block> COAL;
    public static final DeferredBlock<Block> DIAMOND;
    public static final DeferredBlock<Block> EMERALD;
    public static final DeferredBlock<Block> LAPIS;
    public static final DeferredBlock<Block> QUARTZ;
    public static final DeferredBlock<Block> PETRIFIED_QUARTZ;
    public static final DeferredBlock<Block> COPPER;
    public static final DeferredBlock<Block> SILVER;
    public static final DeferredBlock<Block> TIN;
    public static final DeferredBlock<Block> LEAD;
    public static final DeferredBlock<Block> ALUMINUM;
    public static final DeferredBlock<Block> JADE;
    public static final DeferredBlock<Block> ENCRUSTED_DIAMOND;
    public static final DeferredBlock<Block> FOSSIL;
    public static final DeferredBlock<Block> GNEISS;
    public static final DeferredBlock<Block> PETRIFIED_WOOD;
    public static final DeferredBlock<Block> TEMPLE;

    static {
        IRON = registerBlock("ore_iron", () -> new DropExperienceBlock(UniformInt.of(2, 4), OreBlockProperties.IRON));
        GOLD = registerBlock("ore_gold", () -> new DropExperienceBlock(UniformInt.of(2, 4), OreBlockProperties.GOLD));
        COAL = registerBlock("ore_coal", () -> new DropExperienceBlock(UniformInt.of(2, 4), OreBlockProperties.COAL));
        DIAMOND = registerBlock("ore_diamond", () -> new DropExperienceBlock(UniformInt.of(2, 4), OreBlockProperties.DIAMOND));
        EMERALD = registerBlock("ore_emerald", () -> new DropExperienceBlock(UniformInt.of(2, 4), OreBlockProperties.EMERALD));
        LAPIS = registerBlock("ore_lapis", () -> new DropExperienceBlock(UniformInt.of(2, 4), OreBlockProperties.LAPIS));
        QUARTZ = registerBlock("ore_quartz", () -> new DropExperienceBlock(UniformInt.of(2, 4), OreBlockProperties.QUARTZ));
        PETRIFIED_QUARTZ = registerBlock("ore_petrified_quartz", () -> new DropExperienceBlock(UniformInt.of(2, 4), OreBlockProperties.PETRIFIED_QUARTZ));
        COPPER = registerBlock("ore_copper", () -> new DropExperienceBlock(UniformInt.of(2, 4), OreBlockProperties.COPPER));
        SILVER = registerBlock("ore_silver", () -> new DropExperienceBlock(UniformInt.of(2, 4), OreBlockProperties.SILVER));
        TIN = registerBlock("ore_tin", () -> new DropExperienceBlock(UniformInt.of(2, 4), OreBlockProperties.TIN));
        LEAD = registerBlock("ore_lead", () -> new DropExperienceBlock(UniformInt.of(2, 4), OreBlockProperties.LEAD));
        ALUMINUM = registerBlock("ore_aluminum", () -> new DropExperienceBlock(UniformInt.of(2, 4), OreBlockProperties.ALUMINUM));
        JADE = registerBlock("ore_jade", () -> new DropExperienceBlock(UniformInt.of(2, 4), OreBlockProperties.JADE));
        ENCRUSTED_DIAMOND = registerBlock("ore_encrusted_diamond", () -> new DropExperienceBlock(UniformInt.of(2, 4), OreBlockProperties.ENCRUSTED_DIAMOND));
        FOSSIL = registerBlock("ore_fossil", () -> new DropExperienceBlock(UniformInt.of(2, 4), OreBlockProperties.FOSSIL));
        GNEISS = registerBlock("ore_gneiss", () -> new DropExperienceBlock(UniformInt.of(2, 4), OreBlockProperties.GNEISS));
        PETRIFIED_WOOD = registerBlock("ore_petrified_wood", () -> new DropExperienceBlock(UniformInt.of(2, 4), OreBlockProperties.PETRIFIED_WOOD));
        TEMPLE = registerBlock("ore_temple", () -> new DropExperienceBlock(UniformInt.of(2, 4), OreBlockProperties.TEMPLE));
    }

    public static void init() {
    }
}