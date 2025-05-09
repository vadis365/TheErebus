package erebus.registries.blocks.providers;

import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.neoforge.registries.DeferredBlock;

public class OreBlocks extends ModBlockHelpers {
    // Ores
    public static final DeferredBlock<Block> ORE_IRON;
    public static final DeferredBlock<Block> ORE_GOLD;
    public static final DeferredBlock<Block> ORE_COAL;
    public static final DeferredBlock<Block> ORE_DIAMOND;
    public static final DeferredBlock<Block> ORE_EMERALD;
    public static final DeferredBlock<Block> ORE_LAPIS;
    public static final DeferredBlock<Block> ORE_QUARTZ;
    public static final DeferredBlock<Block> ORE_PETRIFIED_QUARTZ;
    public static final DeferredBlock<Block> ORE_COPPER;
    public static final DeferredBlock<Block> ORE_SILVER;
    public static final DeferredBlock<Block> ORE_TIN;
    public static final DeferredBlock<Block> ORE_LEAD;
    public static final DeferredBlock<Block> ORE_ALUMINUM;
    public static final DeferredBlock<Block> ORE_JADE;
    public static final DeferredBlock<Block> ORE_ENCRUSTED_DIAMOND;
    public static final DeferredBlock<Block> ORE_FOSSIL;
    public static final DeferredBlock<Block> ORE_GNEISS;
    public static final DeferredBlock<Block> ORE_PETRIFIED_WOOD;
    public static final DeferredBlock<Block> ORE_TEMPLE;

    static {
        ORE_IRON = registerBlock("ore_iron", () -> new DropExperienceBlock(UniformInt.of(2, 4), Properties.ofFullCopy(Blocks.IRON_ORE)));
        ORE_GOLD = registerBlock("ore_gold", () -> new DropExperienceBlock(UniformInt.of(2, 4), Properties.ofFullCopy(Blocks.GOLD_ORE)));
        ORE_COAL = registerBlock("ore_coal", () -> new DropExperienceBlock(UniformInt.of(2, 4), Properties.ofFullCopy(Blocks.COAL_ORE)));
        ORE_DIAMOND = registerBlock("ore_diamond", () -> new DropExperienceBlock(UniformInt.of(2, 4), Properties.ofFullCopy(Blocks.DIAMOND_ORE)));
        ORE_EMERALD = registerBlock("ore_emerald", () -> new DropExperienceBlock(UniformInt.of(2, 4), Properties.ofFullCopy(Blocks.EMERALD_ORE)));
        ORE_LAPIS = registerBlock("ore_lapis", () -> new DropExperienceBlock(UniformInt.of(2, 4), Properties.ofFullCopy(Blocks.LAPIS_ORE)));
        ORE_QUARTZ = registerBlock("ore_quartz", () -> new DropExperienceBlock(UniformInt.of(2, 4), Properties.ofFullCopy(Blocks.NETHER_QUARTZ_ORE)));
        ORE_PETRIFIED_QUARTZ = registerBlock("ore_petrified_quartz", () -> new DropExperienceBlock(UniformInt.of(2, 4), Properties.ofFullCopy(Blocks.NETHER_QUARTZ_ORE)));
        ORE_COPPER = registerBlock("ore_copper", () -> new DropExperienceBlock(UniformInt.of(2, 4), Properties.ofFullCopy(Blocks.COPPER_ORE)));
        ORE_SILVER = registerBlock("ore_silver", () -> new DropExperienceBlock(UniformInt.of(2, 4), Properties.ofFullCopy(Blocks.IRON_ORE)));
        ORE_TIN = registerBlock("ore_tin", () -> new DropExperienceBlock(UniformInt.of(2, 4), Properties.ofFullCopy(Blocks.IRON_ORE)));
        ORE_LEAD = registerBlock("ore_lead", () -> new DropExperienceBlock(UniformInt.of(2, 4), Properties.ofFullCopy(Blocks.IRON_ORE)));
        ORE_ALUMINUM = registerBlock("ore_aluminum", () -> new DropExperienceBlock(UniformInt.of(2, 4), Properties.ofFullCopy(Blocks.IRON_ORE)));
        ORE_JADE = registerBlock("ore_jade", () -> new DropExperienceBlock(UniformInt.of(2, 4), Properties.ofFullCopy(Blocks.IRON_ORE)));
        ORE_ENCRUSTED_DIAMOND = registerBlock("ore_encrusted_diamond", () -> new DropExperienceBlock(UniformInt.of(2, 4), Properties.ofFullCopy(Blocks.IRON_ORE)));
        ORE_FOSSIL = registerBlock("ore_fossil", () -> new DropExperienceBlock(UniformInt.of(2, 4), Properties.ofFullCopy(Blocks.IRON_ORE)));
        ORE_GNEISS = registerBlock("ore_gneiss", () -> new DropExperienceBlock(UniformInt.of(2, 4), Properties.ofFullCopy(Blocks.IRON_ORE)));
        ORE_PETRIFIED_WOOD = registerBlock("ore_petrified_wood", () -> new DropExperienceBlock(UniformInt.of(2, 4), Properties.ofFullCopy(Blocks.IRON_ORE)));
        ORE_TEMPLE = registerBlock("ore_temple", () -> new DropExperienceBlock(UniformInt.of(2, 4), Properties.ofFullCopy(Blocks.IRON_ORE)));
    }

    public static void init() {
    }
}