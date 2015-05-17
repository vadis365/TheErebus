package erebus.block;

import net.minecraft.block.BlockFalling;
import erebus.ModTabs;

public class BlockUmberGravel extends BlockFalling {

	public BlockUmberGravel() {
		setHardness(0.6F);
		setStepSound(soundTypeGravel);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.umberGravel");
		setBlockTextureName("erebus:umberGravel");
	}
}