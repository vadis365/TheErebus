package erebus.blocks;

import erebus.ModTabs;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockUmberstonePillar extends BlockRotatedPillar {

	public BlockUmberstonePillar() {
		super(Material.ROCK);
		setHardness(1.5F);
		setResistance(10.0F);
		setSoundType(SoundType.STONE);
		setCreativeTab(ModTabs.BLOCKS);
	}
}