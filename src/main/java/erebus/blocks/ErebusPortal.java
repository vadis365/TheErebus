package erebus.blocks;

import java.util.Random;

import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.core.handler.configs.ConfigHandler;
import erebus.world.teleporter.TeleporterHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ErebusPortal extends Block {

	public ErebusPortal() {
		super(Material.PORTAL);
		setLightLevel(1.0F);
		setBlockUnbreakable();
		setSoundType(SoundType.GLASS);
		setCreativeTab(ModTabs.BLOCKS);
	}

	
		public static boolean makePortal(World world, BlockPos pos) {
			if (isPatternValid(world, pos)) {
				world.setBlockState(pos, ModBlocks.PORTAL.getDefaultState());
				world.setBlockState(pos.up(), ModBlocks.PORTAL.getDefaultState());
				return true;
			}
			return false;
		}

		public static boolean isPatternValid(World world, BlockPos pos) {
			// Layer 0
			//System.out.println("Block Found: " + world.getBlockState(pos.down()));
			if (!check(world, pos.down(), Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CHISELED))) {
				return false;
			}

			// Layer 1
			if (!check(world, pos.add(- 1, 0, - 1), Blocks.STONEBRICK.getDefaultState()))
				return false;
			if (!check(world, pos.add(- 1, 0, 1), Blocks.STONEBRICK.getDefaultState()))
				return false;
			if (!check(world, pos.add(1, 0, - 1), Blocks.STONEBRICK.getDefaultState()))
				return false;
			if (!check(world, pos.add(1, 0, 1), Blocks.STONEBRICK.getDefaultState()))
				return false;
			if (!world.isAirBlock(pos) && world.getBlockState(pos) != ModBlocks.PORTAL.getDefaultState())
				return false;

			// Layer 2
			if (!check(world, pos.add(- 1, 1, - 1), Blocks.STONEBRICK.getDefaultState()))
				return false;
			if (!check(world, pos.add(- 1, 1, 1), Blocks.STONEBRICK.getDefaultState()))
				return false;
			if (!check(world, pos.add(1, 1, - 1), Blocks.STONEBRICK.getDefaultState()))
				return false;
			if (!check(world, pos.add( 1, 1, 1), Blocks.STONEBRICK.getDefaultState()))
				return false;
			if (!world.isAirBlock(pos.up()) && world.getBlockState(pos.up()) != ModBlocks.PORTAL.getDefaultState())
				return false;


			// Layer 3
			if (world.getBlockState(pos.up(2)) != ModBlocks.GAEAN_KEYSTONE.getDefaultState() && world.getBlockState(pos.up(2)) != ModBlocks.GAEAN_KEYSTONE.getDefaultState().withProperty(BlockGaeanKeystone.ACTIVE, true))
				return false;


			for (int i = -1; i <= -1; i++)
				for (int j = -1; j <= -1; j++) {
					if (i == 0 && j == 0)
						continue;
					if (!check(world, pos.add(i, 2, j), Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.HALF, BlockStoneSlab.EnumBlockHalf.BOTTOM).withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.SMOOTHBRICK)))
						return false;
				}

			return true;
		}

		private static boolean check(World world, BlockPos pos, IBlockState target) {
			return world.getBlockState(pos) == target;
		}
	

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (!entityIn.isRiding() && !entityIn.isBeingRidden() && entityIn.timeUntilPortal <= 0) {
			if (entityIn.dimension == 0)
				TeleporterHandler.transferToErebus(entityIn);
			else
				TeleporterHandler.transferToOverworld(entityIn);
			if (entityIn != null)
				entityIn.timeUntilPortal = ConfigHandler.INSTANCE.portalCooldown * 20;
			return;
		}
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("deprecation")
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		IBlockState iblockstate = world.getBlockState(pos.offset(side));
		Block block = iblockstate.getBlock();

		if (state != iblockstate)
			return true;
		if (block == this)
			return false;

		return block == this ? false : super.shouldSideBeRendered(state, world, pos, side);
	}
}