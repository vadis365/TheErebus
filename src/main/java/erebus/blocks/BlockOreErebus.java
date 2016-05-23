package erebus.blocks;

import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockOreErebus extends Block {

	public BlockOreErebus(int harvestLevel) {
		super(Material.ROCK);
		setHardness(3.0F);
		setResistance(5.0F);
		setSoundType(SoundType.STONE);
		setCreativeTab(ModTabs.BLOCKS);
		setHarvestLevel("pickaxe", harvestLevel);
	}
}