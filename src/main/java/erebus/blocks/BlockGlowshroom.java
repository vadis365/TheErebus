package erebus.blocks;

import java.util.Random;

import erebus.ModItems;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGlowshroom extends Block {

	protected static final AxisAlignedBB GLOWSHROOM_AABB = new AxisAlignedBB(0.0625D, 0D, 0.0625D, 0.9375D, 1D, 0.9375D);

	public BlockGlowshroom() {
		super(Material.CIRCUITS);
		setHardness(0.2F);
		setLightLevel(0.9375F);
		setCreativeTab(ModTabs.PLANTS);
		setSoundType(SoundType.WOOD);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return GLOWSHROOM_AABB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return GLOWSHROOM_AABB;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.MATERIALS;
	}

	@Override
    public int damageDropped(IBlockState state) {
		return EnumErebusMaterialsType.GLOWSHROOM.ordinal();
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos.down());
		return isValidBlock(state);
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (world.isRemote)
			return;
		if (!canPlaceBlockAt(world, pos)) {
			Utils.dropStack(world, pos, new ItemStack(Item.getItemFromBlock(this)));
			world.setBlockToAir(pos);
		}
	}

	private boolean isValidBlock(IBlockState state) {
		return state.getMaterial().blocksMovement();
	}
}