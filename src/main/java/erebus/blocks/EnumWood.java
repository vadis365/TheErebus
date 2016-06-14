package erebus.blocks;

import java.util.Locale;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import erebus.ModBlocks;

public enum EnumWood implements IStringSerializable {

	BAOBAB,
	EUCALYPTUS,
	MAHOGANY,
	MOSSBARK,
	ASPER,
	CYPRESS,
	BALSAM(true, true, true, true, false),
	WHITE(false, true, false, false, false),
	BAMBOO(true, true, true, false, false),
	ROTTEN(true, true, false, false, true),
	MARSHWOOD,
	SCORCHED(true, true, false, false, true),
	VARNISHED(false, true, false, false, false);

	private final boolean hasLog, hasPlanks, hasSapling, hasLeaves, hasDoor;
	private Block stairs, log, slab, leaves, sapling, door;

	EnumWood(boolean hasLog, boolean hasPlanks, boolean hasSapling, boolean hasLeaves, boolean hasDoor) {
		this.hasLog = hasLog;
		this.hasPlanks = hasPlanks;
		this.hasSapling = hasSapling;
		this.hasLeaves = hasLeaves;
		this.hasDoor = hasDoor;
	}

	EnumWood() {
		this(true, true, true, true, true);
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

	public Block getDoor() {
		return door;
	}

	public static void init() {
		for (EnumWood wood : values()) {
			if (wood.hasLog && wood != EnumWood.BAMBOO) {
				Block log = new BlockLogErebus();
				ModBlocks.registerBlock("log_" + wood.getName(), log);

				wood.log = log;
			}
			
			else if (wood.hasLog && wood == EnumWood.BAMBOO) {
				Block log = new BlockBamboo();
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
				Block stairs = BlockStairsErebus.createWooden(ModBlocks.PLANKS.getDefaultState().withProperty(BlockPlanksErebus.TYPE, wood));
				ModBlocks.registerBlock("stairs_" + wood.getName(), stairs);

				wood.stairs = stairs;

				/*Block slab = new BlockSlabPlanks(wood);
				GameRegistry.registerBlock(slab, ItemBlockSlabSimple.class, "slabPlanks" + wood.name());
				Blocks.fire.setFireInfo(slab, 5, 5);
				slabs.put(wood, slab);*/

				if (wood.hasDoor) {
					Block door = new BlockDoorErebus();
					ModBlocks.registerBlock("door_" + wood.getName(), door);

					wood.door = door;
				}
			}
		}
	}

	public static void initRecipes() {
		for (EnumWood wood : values()) {
			if (wood.hasLog) {
				Block log = wood.log;
				OreDictionary.registerOre("logWood", log);
				GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.PLANKS, 4, wood.ordinal()), new ItemStack(log));
				GameRegistry.addSmelting(new ItemStack(log), new ItemStack(Items.COAL, 1, 1), 0.15F);
			}
			if (wood.hasSapling)
				OreDictionary.registerOre("treeSapling", wood.sapling);
			if (wood.hasPlanks) {
				Block stairs = wood.stairs;
				OreDictionary.registerOre("stairWood", stairs);
				GameRegistry.addRecipe(new ItemStack(stairs, 4), new Object[] { "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, wood.ordinal()) });

				/*Block slab = wood.slab;
				OreDictionary.registerOre("slabWood", slab);
				GameRegistry.addRecipe(new ItemStack(slab, 6), new Object[] { "xxx", 'x', new ItemStack(ModBlocks.planks, 1, wood.ordinal()) });*/

				if (wood.hasDoor)
					GameRegistry.addRecipe(new ItemStack(wood.door, 3), new Object[] { "xx", "xx", "xx", 'x', new ItemStack(ModBlocks.PLANKS, 1, wood.ordinal()) });
			}
			if (wood.hasLeaves)
				OreDictionary.registerOre("treeLeaves", wood.getLeaves());
		}
	}
}