package erebus.block.plants;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAlgae extends BlockBush {

	public BlockAlgae() {
		super(Material.plants);
		setTickRandomly(false);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0F, 1.0F);
		setCreativeTab(ModTabs.plants);
	}

	@Override
	public int getRenderType() {
		return 23;
	}

	@Override
	protected boolean canPlaceBlockOn(Block block) {
		return block == Blocks.water;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		return 2129968;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int colour) {
		return 2129968;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		return 2129968;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return y >= 0 && y < 128 ? world.getBlock(x, y - 1, z).getMaterial() == Material.water && world.getBlockMetadata(x, y - 1, z) == 0 : false;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		return null;
	}

	@Override
	public int getRenderBlockPass() {
		return 1;
	}
}
