package erebus.block.bamboo;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import erebus.tileentity.TileEntityBambooPole;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBambooPole extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon poleIconTop, poleIconBottom;

	public BlockBambooPole() {
		super(Material.wood);
		setHardness(0.4F);
		setHarvestLevel("axe", 0);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.bambooPole");
		setBlockTextureName("erebus:blockBambooPole");
		setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
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
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityBambooPole();
	}

	@Override
	public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
		return true;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y - 1, z);
		if (block == null)
			return false;
		if (block == this)
			return true;
		if (block.isLeaves(world, x, y - 1, z) && !block.isOpaqueCube())
			return false;
		return block.getMaterial().blocksMovement();
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 0 ? poleIconBottom : side == 1 ? poleIconTop : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon("erebus:bambooPole");// Side
		poleIconTop = iconRegister.registerIcon("erebus:bambooPole");// Top
		poleIconBottom = iconRegister.registerIcon("erebus:bambooPole");
	}
}