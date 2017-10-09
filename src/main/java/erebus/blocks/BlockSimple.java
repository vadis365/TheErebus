package erebus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockSimple extends Block {

	public BlockSimple(Material material) {
		super(material);
	}

	public BlockSimple(Material material, SoundType soundType) {
		super(material);
		setSoundType(soundType);
	}

	public BlockSimple(Material material, String harvestType, int harvestLevel) {
		super(material);
		setHarvestLevel(harvestType, harvestLevel);
	}
	
	public BlockSimple(Material material, String harvestType, int harvestLevel, SoundType soundType) {
		super(material);
		setHarvestLevel(harvestType, harvestLevel);
		setSoundType(soundType);
	}
}