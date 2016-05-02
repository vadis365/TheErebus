package erebus.lib;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import erebus.Erebus;
import erebus.block.trees.BlockLeavesErebus;
import erebus.block.trees.BlockLogErebus;
import erebus.block.trees.BlockSaplingErebus;

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
		return this.name().toLowerCase();
	}

	public int getID() {
		return this.ordinal();
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

	private static final HashMap<EnumWood, Block> logs = new HashMap<EnumWood, Block>();
	private static final HashMap<EnumWood, Block> slabs = new HashMap<EnumWood, Block>();
	private static final HashMap<EnumWood, Block> stairs = new HashMap<EnumWood, Block>();
	private static final HashMap<EnumWood, Block> saplings = new HashMap<EnumWood, Block>();
	private static final HashMap<EnumWood, Block> leaves = new HashMap<EnumWood, Block>();

	public static void initBlocks() {
		for (EnumWood wood : values()) {
			if (wood.hasLog) {
				Block log = new BlockLogErebus(wood);
				GameRegistry.register(log);
				ItemBlock itemBlock = new ItemBlock(log);
				GameRegistry.register(itemBlock.setRegistryName(log.getRegistryName()));
				Erebus.proxy.reg(log);
				Blocks.fire.setFireInfo(log, 5, 5);
				logs.put(wood, log);
			}
			if (wood.hasSapling) {
				Block sapling = new BlockSaplingErebus(wood);
				GameRegistry.register(sapling);
				ItemBlock itemBlock = new ItemBlock(sapling);
				GameRegistry.register(itemBlock.setRegistryName(sapling.getRegistryName()));
				Erebus.proxy.setCustomStateMap(sapling, new StateMap.Builder().ignore(new IProperty[] {BlockSapling.TYPE}).build());
				Erebus.proxy.reg(sapling);
				saplings.put(wood, sapling);
			}
			if (wood.hasLeaves) {
				Block leaf = new BlockLeavesErebus(wood);
				GameRegistry.register(leaf);
				ItemBlock itemBlock = new ItemBlock(leaf);
				GameRegistry.register(itemBlock.setRegistryName(leaf.getRegistryName()));
				Erebus.proxy.setCustomStateMap(leaf, new StateMap.Builder().ignore(new IProperty[] {BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE}).build());
				Erebus.proxy.reg(leaf);
				Blocks.fire.setFireInfo(leaf, 30, 60);
				leaves.put(wood, leaf);
			}
			/*
			//TODO After logs and saplings
			if (wood.hasPlanks) {
				Block stair = new BlockStairPlanks(ModBlocks.planks, wood);
				GameRegistry.registerBlock(stair, ItemBlockLocalised.class, "plankStair" + wood.name());
				Blocks.fire.setFireInfo(stair, 5, 5);
				stairs.put(wood, stair);

				Block slab = new BlockSlabPlanks(wood);
				GameRegistry.registerBlock(slab, ItemBlockSlabSimple.class, "slabPlanks" + wood.name());
				Blocks.fire.setFireInfo(slab, 5, 5);
				slabs.put(wood, slab);
			}
			*/
		}
	}
/*
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
	*/
}