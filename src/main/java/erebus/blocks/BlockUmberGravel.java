package erebus.blocks;

import erebus.ModTabs;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;

public class BlockUmberGravel extends BlockFalling {

	public BlockUmberGravel() {
		setHardness(0.6F);
		setSoundType(SoundType.GROUND);
		setCreativeTab(ModTabs.BLOCKS);
	}
}