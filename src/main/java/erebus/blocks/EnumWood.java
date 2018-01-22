package erebus.blocks;

import java.util.Locale;

import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.IStringSerializable;

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
	private Block stairs, log;
	private Block slab;
	private Block leaves;
	private Block sapling;
	private Block door;
	private Block fence;
	private Block gate;

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
	
	public boolean hasDoor() {
		return hasDoor;
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

	public Block getFence() {
		return fence;
	}

	public Block getGate() {
		return gate;
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

				Block slabs = new BlockSlabErebus(ModBlocks.PLANKS.getDefaultState().withProperty(BlockPlanksErebus.TYPE, wood), "axe", 0);
				ModBlocks.registerBlock("slab_planks_" + wood.getName(), slabs);
				Blocks.FIRE.setFireInfo(slabs, 5, 5);
				wood.slab = slabs;

				if (wood.hasDoor) {
					Block door = new BlockDoorErebus(ModBlocks.PLANKS.getDefaultState().withProperty(BlockPlanksErebus.TYPE, wood), "axe", 0);
					ModBlocks.registerBlock("door_" + wood.getName(), door);

					wood.door = door;
				}

				Block fence = new BlockWoodFence(ModBlocks.PLANKS.getDefaultState().withProperty(BlockPlanksErebus.TYPE, wood));
				ModBlocks.registerBlock("fence_" + wood.getName(), fence);
				wood.fence = fence;

				Block fenceGate = new BlockWoodFenceGate(ModBlocks.PLANKS.getDefaultState().withProperty(BlockPlanksErebus.TYPE, wood));
				ModBlocks.registerBlock("fence_gate_" + wood.getName(), fenceGate);
				wood.gate = fenceGate;
			}
		}
	}
}