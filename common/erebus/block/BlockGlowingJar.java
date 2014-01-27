package erebus.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.tileentity.TileEntityGlowingJar;

public class BlockGlowingJar extends BlockContainer {
	public BlockGlowingJar(int id) {
		super(id, Material.glass);
		setCreativeTab(CreativeTabs.tabBlock);
		setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);;
		setLightValue(1.0F);
	}

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
		return ModBlocks.glowingJarID;
	}

	@Override
	public int getRenderType() {
		return -1;
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
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityGlowingJar();
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return world.doesBlockHaveSolidTopSurface(x, y - 1, z) || BlockFence.isIdAFence(world.getBlockId(x, y - 1, z)) || isIdAJar(world.getBlockId(x, y - 1, z));
	}

	public static boolean isIdAJar(int id) {
		return id == ModBlocks.glowingJarID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockIcon = reg.registerIcon("erebus:blockBioJarBreak");
	}
}
