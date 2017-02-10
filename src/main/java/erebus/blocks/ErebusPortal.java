package erebus.blocks;

import erebus.ModTabs;
import erebus.core.handler.configs.ConfigHandler;
import erebus.world.teleporter.TeleporterHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class ErebusPortal extends Block {

	public ErebusPortal() {
		super(Material.PORTAL);
		setLightLevel(1.0F);
		setBlockUnbreakable();
		setSoundType(SoundType.GLASS);
		setCreativeTab(ModTabs.BLOCKS);
	}

	/*
		public static boolean makePortal(World world, int x, int y, int z) {
			if (isPatternValid(world, x, y, z)) {
				world.setBlock(x, y, z, ModBlocks.portal);
				world.setBlock(x, y + 1, z, ModBlocks.portal);
				return true;
			}
			return false;
		}

		public static boolean isPatternValid(World world, int x, int y, int z) {
			// Layer 0
			if (!check(world, x, y - 1, z, Blocks.stonebrick, 3))
				return false;

			// Layer 1
			if (!check(world, x - 1, y, z - 1, Blocks.stonebrick, 0))
				return false;
			if (!check(world, x - 1, y, z + 1, Blocks.stonebrick, 0))
				return false;
			if (!check(world, x + 1, y, z - 1, Blocks.stonebrick, 0))
				return false;
			if (!check(world, x + 1, y, z + 1, Blocks.stonebrick, 0))
				return false;
			if (!world.isAirBlock(x, y, z) && world.getBlock(x, y, z) != ModBlocks.portal)
				return false;

			// Layer 2
			if (!check(world, x - 1, y + 1, z - 1, Blocks.stonebrick, 0))
				return false;
			if (!check(world, x - 1, y + 1, z + 1, Blocks.stonebrick, 0))
				return false;
			if (!check(world, x + 1, y + 1, z - 1, Blocks.stonebrick, 0))
				return false;
			if (!check(world, x + 1, y + 1, z + 1, Blocks.stonebrick, 0))
				return false;
			if (!world.isAirBlock(x, y + 1, z) && world.getBlock(x, y + 1, z) != ModBlocks.portal)
				return false;

			// Layer 3
			if (world.getBlock(x, y + 2, z) != ModBlocks.gaeanKeystone)
				return false;

			for (int i = -1; i <= -1; i++)
				for (int j = -1; j <= -1; j++) {
					if (i == 0 && j == 0)
						continue;
					if (!check(world, x + i, y + 2, z + j, Blocks.stone_slab, 5))
						return false;
				}

			return true;
		}

		private static boolean check(World world, int x, int y, int z, Block target, int meta) {
			return world.getBlock(x, y, z) == target && world.getBlockMetadata(x, y, z) == meta;
		}
	*/

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
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
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

		return state != iblockstate || block != this && block != this && super.shouldSideBeRendered(state, world, pos, side);

	}
}