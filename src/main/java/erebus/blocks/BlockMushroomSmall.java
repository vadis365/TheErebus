package erebus.blocks;

import java.util.Random;

import erebus.ModTabs;
import erebus.world.feature.plant.WorldGenGiantMushrooms;
import erebus.world.feature.plant.WorldGenGiantMushrooms.MushroomType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMushroomSmall extends BlockMushroom {
	private boolean requires2x2ToGrow;
	public BlockMushroomSmall(boolean requires2x2ToGrow) {
		setHardness(0.0F);
		setSoundType(SoundType.PLANT);
		setCreativeTab(ModTabs.PLANTS);
		this.requires2x2ToGrow = requires2x2ToGrow;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (tab == ModTabs.PLANTS)
			list.add(new ItemStack(this));
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {

		if (requires2x2ToGrow) {
			if (isMushroom(world, pos.east()) && isMushroom(world, pos.east().south()) && isMushroom(world, pos.south())) {

				world.setBlockToAir(pos);
				world.setBlockToAir(pos.east());
				world.setBlockToAir(pos.east().south());
				world.setBlockToAir(pos.south());
				WorldGenGiantMushrooms genGiantMushrooms = new WorldGenGiantMushrooms();
				genGiantMushrooms.setMushroomType(MushroomType.getFromShroom(this));

				if (!genGiantMushrooms.generate(world, rand, pos.east())) {
					world.setBlockState(pos, this.getDefaultState());
					world.setBlockState(pos.east(), this.getDefaultState());
					world.setBlockState(pos.east().south(), this.getDefaultState());
					world.setBlockState(pos.south(), this.getDefaultState());
				}

			} else if (isMushroom(world, pos.west()) && isMushroom(world, pos.west().south()) && isMushroom(world, pos.south())) {

				world.setBlockToAir(pos);
				world.setBlockToAir(pos.west());
				world.setBlockToAir(pos.west().south());
				world.setBlockToAir(pos.south());
				WorldGenGiantMushrooms genGiantMushrooms = new WorldGenGiantMushrooms();
				genGiantMushrooms.setMushroomType(MushroomType.getFromShroom(this));

				if (!genGiantMushrooms.generate(world, rand, pos)) {
					world.setBlockState(pos, this.getDefaultState());
					world.setBlockState(pos.west(), this.getDefaultState());
					world.setBlockState(pos.west().south(), this.getDefaultState());
					world.setBlockState(pos.south(), this.getDefaultState());
				}

			} else if (isMushroom(world, pos.east()) && isMushroom(world, pos.east().north()) && isMushroom(world, pos.north())) {

				world.setBlockToAir(pos);
				world.setBlockToAir(pos.east());
				world.setBlockToAir(pos.east().north());
				world.setBlockToAir(pos.north());
				WorldGenGiantMushrooms genGiantMushrooms = new WorldGenGiantMushrooms();
				genGiantMushrooms.setMushroomType(MushroomType.getFromShroom(this));

				if (!genGiantMushrooms.generate(world, rand, pos.east().north())) {
					world.setBlockState(pos, this.getDefaultState());
					world.setBlockState(pos.east(), this.getDefaultState());
					world.setBlockState(pos.east().north(), this.getDefaultState());
					world.setBlockState(pos.north(), this.getDefaultState());
				}

			} else if (isMushroom(world, pos.west()) && isMushroom(world, pos.west().north()) && isMushroom(world, pos.north())) {

				world.setBlockToAir(pos);
				world.setBlockToAir(pos.west());
				world.setBlockToAir(pos.west().north());
				world.setBlockToAir(pos.north());
				WorldGenGiantMushrooms genGiantMushrooms = new WorldGenGiantMushrooms();
				genGiantMushrooms.setMushroomType(MushroomType.getFromShroom(this));

				if (!genGiantMushrooms.generate(world, rand, pos.north())) {
					world.setBlockState(pos, this.getDefaultState());
					world.setBlockState(pos.west(), this.getDefaultState());
					world.setBlockState(pos.west().north(), this.getDefaultState());
					world.setBlockState(pos.north(), this.getDefaultState());
				}

			}
		} else {
			world.setBlockToAir(pos);
			WorldGenGiantMushrooms genGiantMushrooms = new WorldGenGiantMushrooms();
			genGiantMushrooms.setMushroomType(MushroomType.getFromShroom(this));
			if (!genGiantMushrooms.generate(world, rand, pos))
				world.setBlockState(pos, this.getDefaultState());

		}
	}

	private boolean isMushroom(World world, BlockPos pos) {
		return world.getBlockState(pos).getBlock() == this;
	}

	@Override
    public Block.EnumOffsetType getOffsetType() {
        return Block.EnumOffsetType.XZ;
    }

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return MUSHROOM_AABB.offset(state.getOffset(source, pos));
	}
}