package erebus.block.bamboo;

import erebus.ModItems;
import erebus.ModTabs;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;
import erebus.item.ItemMaterials.DATA;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockBambooCrop extends Block {

	public BlockBambooCrop() {
		super(Material.wood);
		setTickRandomly(true);
		setCreativeTab(ModTabs.plants);
		setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return getMetaFromState(state) >= 8 && rand.nextInt(17) <= 3 ? null : ModItems.materials;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return DATA.bamboo.ordinal();
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		if (getMetaFromState(state) == 0 && RANDOM.nextInt(getMetaFromState(state) >= 8 ? 35 : 20) == 0) {
			ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
			ret.add(new ItemStack(ModItems.materials, 1, DATA.bambooShoot.ordinal()));
			return ret;
		}
		return super.getDrops(world, pos, state, fortune);
	}

	@Override
	public int getRenderType() {
		return BlockRenderIDs.BAMBOO_CROP.id();
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		int meta = getMetaFromState(state);

		if ((meta & 7) != 0)
			if (BlockBambooShoot.calculateBambooHappiness(world, pos, this) > rand.nextInt(110 - meta * 2) && world.isAirBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())))
				world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), getStateFromMeta(meta - 1), 3);
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighbour) {
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z, getMetaFromState(state), 0);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		Block block = world.getBlock(x, y - 1, z);
		return world.getBlock(x, y - 1, z) == this || block != null && block.isSideSolid(world, x, y, z, ForgeDirection.UP);
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, BlockPos pos, int side) {
		Block block = world.getBlock(x, y - 1, z);
		if (!(world.getBlock(x, y - 1, z) == this && world.getBlockMetadata(x, y - 1, z) == 0 || block != null && block.isSideSolid(world, x, y, z, ForgeDirection.UP)))
			return false;

		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, EntityLivingBase entityLivingBase, ItemStack is) {
		Block block = world.getBlock(x, y - 1, z);
		if (!(world.getBlock(x, y - 1, z) == this && world.getBlockMetadata(x, y - 1, z) == 0 || block != null && block.isSideSolid(world, x, y, z, ForgeDirection.UP)))
			world.setBlockToAir(x, y, z);
	}
}