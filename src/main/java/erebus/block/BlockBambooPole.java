package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.tileentity.TileEntityBambooPole;

public class BlockBambooPole extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private Icon poleIconTop, poleIconBottom;

	public BlockBambooPole(int id) {
		super(id, Material.wood);
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
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBambooPole();
	}

	@Override
	public boolean isLadder(World world, int x, int y, int z, EntityLivingBase entity) {
		return true;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		int l = world.getBlockId(x, y - 1, z);
		Block block = Block.blocksList[l];
		if (block == null)
			return false;
		if (block == this)
			return true;
		if (block.isLeaves(world, x, y - 1, z) && !Block.blocksList[l].isOpaqueCube())
			return false;
		return world.getBlockMaterial(x, y - 1, z).blocksMovement();
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
	public Icon getIcon(int side, int meta) {
		return side == 0 ? poleIconBottom : side == 1 ? poleIconTop : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon("erebus:bambooPole");// Side
		poleIconTop = iconRegister.registerIcon("erebus:bambooPole");// Top
		poleIconBottom = iconRegister.registerIcon("erebus:bambooPole");
	}
}
