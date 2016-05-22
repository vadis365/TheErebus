package erebus.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import erebus.ModTabs;
import erebus.core.handler.configs.ConfigHandler;
import erebus.world.teleporter.TeleporterHandler;

public class ErebusPortal extends Block {

	public ErebusPortal() {
		super(Material.PORTAL);
		setLightLevel(1.0F);
		setBlockUnbreakable();
		setRegistryName("portal");
		setUnlocalizedName(getRegistryName().toString());
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
	public int quantityDropped(Random rand) {
		return 0;
	}

    public AxisAlignedBB getSelectedBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
        return NULL_AABB;
    }

//	@Override
//	 public AxisAlignedBB getCollisionBoundingBox(IBlockState worldIn, World pos, BlockPos state) {
 //   }

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

/*
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return true;
	}*/
}