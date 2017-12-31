package erebus.blocks;

import erebus.ModTabs;
import net.minecraft.block.BlockButtonStone;
import net.minecraft.block.SoundType;

public class BlockButtonUmberstone extends BlockButtonStone {

	public BlockButtonUmberstone() {
		setHardness(0.5F);
		setSoundType(SoundType.STONE);
		setCreativeTab(ModTabs.BLOCKS);
	}
}