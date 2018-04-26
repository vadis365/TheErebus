package erebus.blocks;

import erebus.ModItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMandrake extends BlockCrops {
    private static final AxisAlignedBB[] MANDRAKE_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D)};

    @Override
    protected Item getSeed() {
        return ModItems.MANDRAKE_ROOT;
    }

	@Override
    protected Item getCrop() {
        return ModItems.MANDRAKE_ROOT;
    }

	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return MANDRAKE_AABB[((Integer)state.getValue(this.getAgeProperty())).intValue()];
    }

	@Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
        IBlockState soil = world.getBlockState(pos.down());
        return (soil.getBlock().canSustainPlant(soil, world, pos.down(), net.minecraft.util.EnumFacing.UP, this));
    }
}