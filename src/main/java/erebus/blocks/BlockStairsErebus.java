package erebus.blocks;

import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStairs.EnumHalf;
import net.minecraft.block.BlockStairs.EnumShape;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockStairsErebus extends BlockStairs {

	public BlockStairsErebus(IBlockState modelState) {
		super(modelState);
		setLightOpacity(0);
		setCreativeTab(ModTabs.BLOCKS);
	}

	public static Block createWooden(IBlockState modelState) {
		Block block = new BlockStairsErebus(modelState);
		Blocks.FIRE.setFireInfo(block, 5, 5);
		block.setHarvestLevel("axe", 0);
		return block;
	}

	public static Block createWaspStairs(IBlockState modelState) {
		Block block = new BlockStairsErebus(modelState);
		block.setHardness(50.0F);
		return block;
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		if (state.getMaterial() == Material.GLASS)
			return false;

		if (net.minecraftforge.common.ForgeModContainer.disableStairSlabCulling)
			return super.doesSideBlockRendering(state, world, pos, face);

		if (state.isOpaqueCube())
			return true;

		state = this.getActualState(state, world, pos);

		EnumHalf half = state.getValue(HALF);
		EnumFacing side = state.getValue(FACING);
		EnumShape shape = state.getValue(SHAPE);
		if (face == EnumFacing.UP)
			return half == EnumHalf.TOP;
		if (face == EnumFacing.DOWN)
			return half == EnumHalf.BOTTOM;
		if (shape == EnumShape.OUTER_LEFT || shape == EnumShape.OUTER_RIGHT)
			return false;
		if (face == side)
			return true;
		if (shape == EnumShape.INNER_LEFT && face.rotateY() == side)
			return true;
		if (shape == EnumShape.INNER_RIGHT && face.rotateYCCW() == side)
			return true;
		return false;
	}
}