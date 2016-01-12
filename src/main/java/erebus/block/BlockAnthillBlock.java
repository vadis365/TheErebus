package erebus.block;

import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockAnthillBlock extends Block {

	public BlockAnthillBlock() {
		super(Material.rock);
		setBlockUnbreakable();
		setResistance(6000000.0F);
		setStepSound(soundTypeStone);
		//setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.anthillBlock");
		setBlockTextureName("erebus:anthillBlock");
	}
}