package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBambooTorch extends Block {

	@SideOnly(Side.CLIENT)
	private Icon torchIconTop, torchIconBottom;

	public BlockBambooTorch(int id) {
		super(id, Material.wood);
		setTickRandomly(true);
		setLightValue(0.9F);
		setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 2.0F, 0.625F);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
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
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		int l = world.getBlockId(x, y - 1, z);
		int m = world.getBlockId(x, y + 1, z);
		Block block = Block.blocksList[l];
		if (block == null || m != 0)
			return false;
		if (block == this && (world.getBlockMetadata(x, y - 1, z) & 7) == 7)
			return true;
		if (!block.isLeaves(world, x, y - 1, z) && !Block.blocksList[l].isOpaqueCube())
			return false;
		return world.getBlockMaterial(x, y - 1, z).blocksMovement();
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		dropTorchIfCantStay(world, x, y, z);
	}

	protected boolean dropTorchIfCantStay(World world, int x, int y, int z) {
		if (!canPlaceBlockAt(world, x, y, z)) {
			if (world.getBlockId(x, y, z) == blockID) {
				dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
				world.setBlockToAir(x, y, z);
			}
			return false;
		} else
			return true;
	}

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
		return blockID;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		double d0 = x + 0.4375F;
		double d1 = y + 2.0625F;
		double d2 = z + 0.4375F;
		double d3 = x + 0.5625F;
		double d4 = z + 0.5625F;
		double d5 = x + 0.5F;
		double d6 = y + 2.25F;
		double d7 = z + 0.5F;
		world.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("flame", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("smoke", d0, d1, d4, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("flame", d0, d1, d4, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("smoke", d3, d1, d2, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("flame", d3, d1, d2, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("smoke", d3, d1, d4, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("flame", d3, d1, d4, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("smoke", d5, d6, d7, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("flame", d5, d6, d7, 0.0D, 0.0D, 0.0D);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side == 0 ? torchIconBottom : side == 1 ? torchIconTop : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon("erebus:blockBambooTorch");// Side
		torchIconTop = iconRegister.registerIcon("erebus:blockBambooTorch_top");// Top
		torchIconBottom = iconRegister.registerIcon("erebus:blockBambooTorch_bottom");
	}
}
