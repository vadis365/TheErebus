package erebus.block;

import erebus.ModTabs;
import net.minecraft.block.BlockFalling;

public class BlockUmberGravel extends BlockFalling {

	public BlockUmberGravel() {
		setHardness(0.6F);
		setStepSound(soundTypeGravel);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.umberGravel");
		setBlockTextureName("erebus:umberGravel");
	}
}