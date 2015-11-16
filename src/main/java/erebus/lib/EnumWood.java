package erebus.lib;

import java.util.HashMap;

import cpw.mods.fml.common.registry.GameRegistry;
import erebus.ModBlocks;
import erebus.block.BlockErebusLeaves;
import erebus.block.BlockLogErebus;
import erebus.block.BlockSlabPlanks;
import erebus.block.BlockStairPlanks;
import erebus.block.BlockWoodFence;
import erebus.block.BlockWoodFenceGate;
import erebus.block.plants.BlockSaplingErebus;
import erebus.item.block.ItemBlockLocalised;
import erebus.item.block.ItemBlockSlabSimple;
import erebus.item.block.ItemErebusLeaves;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public enum EnumWood {

	Baobab,
	Eucalyptus,
	Mahogany,
	Mossbark,
	Asper,
	Cypress,
	Sap,
	White(false, true, false, false),
	Bamboo(false, true, false, false),
	Rotten(true, true, false, false),
	Marshwood;

	private final boolean hasLog, hasPlanks, hasSapling, hasLeaves;

	EnumWood(boolean hasLog, boolean hasPlanks, boolean hasSapling, boolean hasLeaves) {
		this.hasLog = hasLog;
		this.hasPlanks = hasPlanks;
		this.hasSapling = hasSapling;
		this.hasLeaves = hasLeaves;
	}

	EnumWood() {
		this(true, true, true, true);
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

	public Block getStair() {
		return stairs.get(this);
	}

	public Block getLog() {
		return logs.get(this);
	}

	public Block getSlab() {
		return slabs.get(this);
	}

	public Block getLeaves() {
		return leaves.get(this);
	}

	public Block getSapling() {
		return saplings.get(this);
	}

	public Block getFence() {
		return fences.get(this);
	}

	public Block getFenceGate() {
		return fenceGates.get(this);
	}

	public String getUnlocalisedName() {
		return name().toLowerCase();
	}

	private static final HashMap<EnumWood, Block> logs = new HashMap<EnumWood, Block>();
	private static final HashMap<EnumWood, Block> slabs = new HashMap<EnumWood, Block>();
	private static final HashMap<EnumWood, Block> stairs = new HashMap<EnumWood, Block>();
	private static final HashMap<EnumWood, Block> saplings = new HashMap<EnumWood, Block>();
	private static final HashMap<EnumWood, Block> leaves = new HashMap<EnumWood, Block>();
	private static final HashMap<EnumWood, Block> fences = new HashMap<EnumWood, Block>();
	private static final HashMap<EnumWood, Block> fenceGates = new HashMap<EnumWood, Block>();

	public static void initBlocks() {
		for (EnumWood wood : values()) {
			if (wood.hasLog) {
				Block log = new BlockLogErebus(wood);
				GameRegistry.registerBlock(log, ItemBlockLocalised.class, "log" + wood.name());
				Blocks.fire.setFireInfo(log, 5, 5);
				logs.put(wood, log);
			}
			if (wood.hasSapling) {
				Block sapling = new BlockSaplingErebus(wood);
				GameRegistry.registerBlock(sapling, ItemBlockLocalised.class, "sapling" + wood.name());
				saplings.put(wood, sapling);
			}
			if (wood.hasPlanks) {
				Block stair = new BlockStairPlanks(ModBlocks.planks, wood);
				GameRegistry.registerBlock(stair, ItemBlockLocalised.class, "plankStair" + wood.name());
				Blocks.fire.setFireInfo(stair, 5, 5);
				stairs.put(wood, stair);

				Block slab = new BlockSlabPlanks(wood);
				GameRegistry.registerBlock(slab, ItemBlockSlabSimple.class, "slabPlanks" + wood.name());
				Blocks.fire.setFireInfo(slab, 5, 5);
				slabs.put(wood, slab);

				Block fence = new BlockWoodFence(wood);
				GameRegistry.registerBlock(fence, ItemBlockLocalised.class, "fence" + wood.name());
				fences.put(wood, fence);

				Block fenceGate = new BlockWoodFenceGate(wood);
				GameRegistry.registerBlock(fenceGate, ItemBlockLocalised.class, "fenceGate" + wood.name());
				fenceGates.put(wood, fenceGate);
			}
			if (wood.hasLeaves) {
				Block leaf = new BlockErebusLeaves(wood);
				GameRegistry.registerBlock(leaf, ItemErebusLeaves.class, "leaves" + wood.name());
				Blocks.fire.setFireInfo(leaf, 30, 60);
				leaves.put(wood, leaf);
			}
		}
	}

	public static void initRecipes() {
		for (EnumWood wood : values()) {
			if (wood.hasLog) {
				Block log = logs.get(wood);
				OreDictionary.registerOre("logWood", log);
				GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.planks, 4, wood.ordinal()), new ItemStack(log));
				GameRegistry.addSmelting(new ItemStack(log), new ItemStack(Items.coal, 1, 1), 0.15F);
			}
			if (wood.hasSapling)
				OreDictionary.registerOre("treeSapling", saplings.get(wood));
			if (wood.hasPlanks) {
				Block stair = stairs.get(wood);
				OreDictionary.registerOre("stairWood", stair);
				GameRegistry.addRecipe(new ItemStack(stair, 4), new Object[] { "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.planks, 1, wood.ordinal()) });

				Block slab = slabs.get(wood);
				OreDictionary.registerOre("slabWood", slab);
				GameRegistry.addRecipe(new ItemStack(slab, 6), new Object[] { "xxx", 'x', new ItemStack(ModBlocks.planks, 1, wood.ordinal()) });
			}
			if (wood.hasLeaves) {
				Block leaf = wood.getLeaves();
				OreDictionary.registerOre("treeLeaves", leaf);
			}
		}
	}
}