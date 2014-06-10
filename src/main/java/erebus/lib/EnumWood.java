package erebus.lib;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.ModBlocks;
import erebus.block.BlockLogErebus;
import erebus.block.BlockSaplingErebus;
import erebus.block.BlockSlabPlanks;
import erebus.block.BlockStairPlanks;
import erebus.item.block.ItemBlockLocalised;
import erebus.item.block.ItemBlockSlabSimple;

public enum EnumWood {

	//@formatter:off
	Acacia,
	Eucalyptus,
	Mahogany,
	Mossbark,
	Pink,
	Scorched,
	Asper,
	Cypress,
	Sap(true, false),
	Weedwood,
	White(false, true),
	Bamboo(false, true);
	//@formatter:on

	private final boolean hasLog;
	private final boolean hasPlanks;

	EnumWood(boolean hasLog, boolean hasPlanks) {
		this.hasLog = hasLog;
		this.hasPlanks = hasPlanks;
	}

	EnumWood() {
		this(true, true);
	}

	public boolean hasPlanks() {
		return hasPlanks;
	}

	public boolean hasLog() {
		return hasLog;
	}

	public Block getStair() {
		return stairs.get(this);
	}

	public Block getLog() {
		return logs.get(this);
	}

	public Block getSlab() {
		return slabs.get(this);
	}

	public Block getleaves() {
		return ModBlocks.leavesErebus;
	}

	public String getTranslatedName() {
		return StatCollector.translateToLocal("wood." + Reference.MOD_ID + "." + name().toLowerCase());
	}

	private static final HashMap<EnumWood, Block> logs = new HashMap<EnumWood, Block>();
	private static final HashMap<EnumWood, Block> slabs = new HashMap<EnumWood, Block>();
	private static final HashMap<EnumWood, Block> stairs = new HashMap<EnumWood, Block>();
	private static final HashMap<EnumWood, Block> saplings = new HashMap<EnumWood, Block>();

	public static void initBlocks() {
		for (EnumWood wood : values()) {
			if (wood.hasLog) {
				Block log = new BlockLogErebus(wood);
				GameRegistry.registerBlock(log, ItemBlockLocalised.class, "log" + wood.name());
				Blocks.fire.setFireInfo(log, 5, 5);
				logs.put(wood, log);

				Block sapling = new BlockSaplingErebus(wood);
				GameRegistry.registerBlock(sapling, ItemBlockLocalised.class, "sapling" + wood.name());
				saplings.put(wood, sapling);
			}
			if (wood.hasPlanks) {
				Block stair = new BlockStairPlanks(ModBlocks.planksErebus, wood);
				GameRegistry.registerBlock(stair, ItemBlockLocalised.class, "plankStair" + wood.name());
				Blocks.fire.setFireInfo(stair, 5, 5);
				stairs.put(wood, stair);

				Block slab = new BlockSlabPlanks(wood);
				GameRegistry.registerBlock(slab, ItemBlockSlabSimple.class, "slabPlanks" + wood.name());
				Blocks.fire.setFireInfo(slab, 5, 5);
				slabs.put(wood, slab);
			}
		}
	}

	public static void initRecipes() {
		for (EnumWood wood : values()) {
			if (wood.hasLog) {
				Block log = logs.get(wood);
				OreDictionary.registerOre("logWood", log);
				GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.planksErebus, 4, wood.ordinal()), new ItemStack(log));
				GameRegistry.addSmelting(new ItemStack(Items.coal, 1, 1), new ItemStack(log), 1.0F);

				OreDictionary.registerOre("treeSapling", saplings.get(wood));
			}
			if (wood.hasPlanks) {
				Block stair = stairs.get(wood);
				OreDictionary.registerOre("stairWood", stair);
				GameRegistry.addRecipe(new ItemStack(stair, 4), new Object[] { "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.planksErebus, 1, wood.ordinal()) });

				Block slab = slabs.get(wood);
				OreDictionary.registerOre("slabWood", slab);
				GameRegistry.addRecipe(new ItemStack(slab, 6), new Object[] { "xxx", 'x', new ItemStack(ModBlocks.planksErebus, 1, wood.ordinal()) });
			}
		}
	}
}