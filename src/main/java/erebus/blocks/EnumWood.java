package erebus.blocks;

import java.util.Locale;

import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public enum EnumWood implements IStringSerializable {

	BAOBAB,
	EUCALYPTUS,
	MAHOGANY,
	MOSSBARK,
	ASPER,
	CYPRESS,
	BALSAM,
	WHITE(false, true, false, false),
	BAMBOO(false, true, false, false),
	ROTTEN(true, true, false, false),
	MARSHWOOD,
	SCORCHED(true, true, false, false),
	VARNISHED(false, true, false, false);

	private final boolean hasLog;
	private final boolean hasPlanks;
	private final boolean hasSapling;
	private final boolean hasLeaves;

	private Block stairs, log, slab, leaves, sapling;

	EnumWood(boolean hasLog, boolean hasPlanks, boolean hasSapling, boolean hasLeaves) {
		this.hasLog = hasLog;
		this.hasPlanks = hasPlanks;
		this.hasSapling = hasSapling;
		this.hasLeaves = hasLeaves;
	}

	EnumWood() {
		this(true, true, true, true);
	}

	@Override
	public String getName() {
		return name().toLowerCase(Locale.ENGLISH);
	}

	public int getID() {
		return ordinal();
	}

	public boolean hasSapling() {
		return hasSapling;
	}

	public boolean hasPlanks() {
		return hasPlanks;
	}

	public boolean hasLog() {
		return hasLog;
	}

	public boolean hasLeaves() {
		return hasLeaves;
	}

	public Block getStairs() {
		return stairs;
	}

	public Block getLog() {
		return log;
	}

	public Block getSlab() {
		return slab;
	}

	public Block getLeaves() {
		return leaves;
	}

	public Block getSapling() {
		return sapling;
	}

	public static void init() {
		for (EnumWood wood : values()) {
			if (wood.hasLog) {
				Block log = new BlockLogErebus();
				ModBlocks.registerBlock("log_" + wood.getName(), log);

				wood.log = log;
			}
			if (wood.hasSapling) {
				Block sapling = new BlockSaplingErebus(wood);
				ModBlocks.registerBlock("sapling_" + wood.getName(), sapling);

				wood.sapling = sapling;
			}
			if (wood.hasLeaves) {
				Block leaves = new BlockLeavesErebus(wood);
				ModBlocks.registerBlock("leaves_" + wood.getName(), leaves);

				wood.leaves = leaves;
			}
			if (wood.hasPlanks) {
				/*Block stair = new BlockStairPlanks(ModBlocks.planks, wood);
				GameRegistry.registerBlock(stair, ItemBlockLocalised.class, "plankStair" + wood.name());
				Blocks.fire.setFireInfo(stair, 5, 5);
				stairs.put(wood, stair);
				
				Block slab = new BlockSlabPlanks(wood);
				GameRegistry.registerBlock(slab, ItemBlockSlabSimple.class, "slabPlanks" + wood.name());
				Blocks.fire.setFireInfo(slab, 5, 5);
				slabs.put(wood, slab);*/
			}
		}
	}

	public static void initRecipes() {
		for (EnumWood wood : values()) {
			if (wood.hasLog) {
				Block log = wood.log;
				OreDictionary.registerOre("logWood", log);
				//GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.planks, 4, wood.ordinal()), new ItemStack(log));
				GameRegistry.addSmelting(new ItemStack(log), new ItemStack(Items.COAL, 1, 1), 0.15F);
			}
			if (wood.hasSapling)
				OreDictionary.registerOre("treeSapling", wood.sapling);
			if (wood.hasPlanks) {
				/*Block stairs = wood.stairs;
				OreDictionary.registerOre("stairWood", stairs);
				GameRegistry.addRecipe(new ItemStack(stairs, 4), new Object[] { "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.planks, 1, wood.ordinal()) });

				Block slab = wood.slab;
				OreDictionary.registerOre("slabWood", slab);
				GameRegistry.addRecipe(new ItemStack(slab, 6), new Object[] { "xxx", 'x', new ItemStack(ModBlocks.planks, 1, wood.ordinal()) });*/
			}
			if (wood.hasLeaves)
				OreDictionary.registerOre("treeLeaves", wood.getLeaves());
		}
	}
}