package erebus.blocks;

import erebus.ModTabs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockGiantLilyPad extends BlockSimple {

	public BlockGiantLilyPad(Material material, String harvestType, int harvestLevel) {
		super(material, harvestType, harvestLevel);
		setHardness(5.0F);
		setSoundType(SoundType.PLANT);
		setCreativeTab(ModTabs.BLOCKS);
	}
}
