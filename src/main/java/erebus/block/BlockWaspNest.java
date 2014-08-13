package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWaspNest extends Block
{

	@SideOnly(Side.CLIENT)
	private IIcon Top, Bottom;

	public BlockWaspNest()
	{
		super(Material.rock);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return true;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		return null;
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return 0;
	}

	@Override
	protected boolean canSilkHarvest()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 0 ? Bottom : side == 1 ? Top : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		blockIcon = reg.registerIcon("erebus:waspNestBlock");// Side
		Top = reg.registerIcon("erebus:waspNestBlock");// Top
		Bottom = reg.registerIcon("erebus:waspNestBlock");
	}
}