package erebus.block.bamboo;

import erebus.ModTabs;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBambooLadder extends BlockLadder {

	public BlockBambooLadder() {
		super();
		setHardness(0.4F);
		setHarvestLevel("axe", 0);
		setSoundType(SoundType.LADDER);
		setCreativeTab(ModTabs.BLOCKS);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
}