package erebus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSimple extends Block {

	public BlockSimple(Material material) {
		super(material);
	}

	public BlockSimple(Material material, String harvestType, int harvestLevel) {
		super(material);
		setHarvestLevel(harvestType, harvestLevel);
	}
}