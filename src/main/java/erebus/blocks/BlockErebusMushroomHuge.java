package erebus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import erebus.ModTabs;

public class BlockErebusMushroomHuge extends BlockErebusMushroomHuge {

	public BlockErebusMushroomHuge(MapColor colour, Block drop) {
		super(Material.WOOD, colour, drop);
		setHardness(0.2F);
		setCreativeTab(ModTabs.BLOCKS);
		setSoundType(SoundType.WOOD);
	}
}