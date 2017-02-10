package erebus.blocks;

import erebus.ModItems;
import erebus.items.ItemErebusFood;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockCabbage extends BlockCrops {
    private static final AxisAlignedBB[] CABBAGE_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D)};

    @Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
    	List<ItemStack> ret = new ArrayList<ItemStack>();
        int age = getAge(state);
        Random rand = world instanceof World ? ((World) world).rand : RANDOM;

        if (age >= getMaxAge()) {
            int k = 3 + fortune;
            for (int i = 0; i < 3 + fortune; ++i)
                if (rand.nextInt(2 * getMaxAge()) <= age)
                    ret.add(new ItemStack(getSeed(), 1, 0));
            ret.add(new ItemStack(ModItems.EREBUS_FOOD, 1, ItemErebusFood.EnumFoodType.CABBAGE.ordinal()));
        }
		else
			ret.add(new ItemStack(getSeed(), 1, 0));
        return ret;
    }

    @Override
    protected Item getSeed() {
        return ModItems.CABBAGE_SEEDS;
    }

	@Override
    protected Item getCrop() {
        return null;
    }

	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return CABBAGE_AABB[state.getValue(this.getAgeProperty())];
	}
}
