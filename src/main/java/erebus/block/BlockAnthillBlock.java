package erebus.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import erebus.ModTabs;

public class BlockAnthillBlock extends Block {

	public BlockAnthillBlock() {
		super(Material.rock);
		setCreativeTab(ModTabs.blocks);
		setStepSound(Block.soundTypeGrass);
		setBlockTextureName("erebus:anthillBlock");
		setBlockName("erebus.anthillBlock");
		setBlockUnbreakable();
		setResistance(6000000.0F);
	}
}