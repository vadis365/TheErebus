package erebus.block.bamboo;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockBambooTorch extends Block {

	public static final String[] iconPaths = new String[] { "bambooTorchLower", "bambooTorch", "bambooTorchTop", "bambooTorchBottom" };
	public static IIcon[] icons;

	public BlockBambooTorch() {
		super(Material.wood);
		setHardness(0.0F);
		setLightLevel(0.9F);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.bambooTorch");
		setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		icons = new IIcon[iconPaths.length];

		int i = 0;
		for (String path : iconPaths)
			icons[i++] = iconRegister.registerIcon("erebus:" + path);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta < 0 || meta >= icons.length)
			return null;
		if (meta == 0)
			return side > 1 ? icons[0] : icons[3];
		if (meta == 1)
			return side > 1 ? icons[1] : icons[2];
		return null;
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
		Block block = world.getBlock(x, y - 1, z);

		if (block == null || !world.isAirBlock(x, y + 1, z))
			return false;
		if (block == this && (world.getBlockMetadata(x, y - 1, z) & 7) == 7)
			return true;
		if (block.isLeaves(world, x, y - 1, z))
			return false;
		world.setBlock(x, y + 1, z, this, 1, 3);
		return block.getMaterial().blocksMovement();
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		dropTorchIfCantStay(world, x, y, z);
	}

	protected boolean dropTorchIfCantStay(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y + 1, z);
		if (world.isAirBlock(x, y - 1, z)) {
			world.setBlockToAir(x, y, z);
			if (meta == 0)
				dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			return false;
		}
		return true;
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int id, EntityPlayer player) {
		if (world.getBlock(x, y - 1, z) == this)
			world.setBlockToAir(x, y - 1, z);
		if (world.getBlock(x, y + 1, z) == this)
			world.setBlockToAir(x, y + 1, z);
		if (world.getBlock(x, y + 1, z) == this || world.getBlock(x, y - 1, z) == this)
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 0) {
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
	}
}