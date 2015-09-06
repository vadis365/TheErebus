package erebus.block.altars;

import erebus.ModTabs;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class AltarAbstract extends BlockContainer {
	public AltarAbstract() {
		super(Material.rock);
		setHardness(20.0F);
		setCreativeTab(ModTabs.blocks);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state) {
		float f = 0.0625F;
		return AxisAlignedBB.fromBounds(pos.getX() + f, pos.getY(), pos.getZ() + f, pos.getX() + 1 - f, pos.getY() + 1 - f, pos.getZ() + 1 - f);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}
}