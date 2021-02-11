package erebus.blocks;

import java.util.Random;

import erebus.ModTabs;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockUmberGravel extends BlockFalling {

	public BlockUmberGravel() {
		setHardness(0.6F);
		setSoundType(SoundType.GROUND);
		setCreativeTab(ModTabs.BLOCKS);
		setHarvestLevel("shovel", 0);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if (fortune > 3)
			fortune = 3;

		return rand.nextInt(10 - fortune * 3) == 0 ? Items.FLINT : super.getItemDropped(state, rand, fortune);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.STONE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getDustColor(IBlockState state) {
		return -8356741;
	}
}