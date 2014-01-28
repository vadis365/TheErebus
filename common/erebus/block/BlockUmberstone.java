package erebus.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockUmberstone extends Block {

	public static final String[] iconPaths = new String[] { "Umberstone", "cobbleUmber", "cobbleUmberMossy", "cobbleUmberWebbed", "umberstoneBricks", "smoothUmbertile", "smoothUmbertiles" };
	public static final Icon[] icons = new Icon[iconPaths.length];

	public BlockUmberstone(int id) {
		super(id, Material.rock);
	}

	@Override
	public void registerIcons(IconRegister reg) {
		int i = 0;
		for (String path : iconPaths)
			icons[i++] = reg.registerIcon("erebus:" + path);
	}

	@Override
	public Icon getIcon(int side, int meta) {
		if (meta < 0 || meta >= icons.length)
			return null;
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (int i = 0; i < icons.length; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	public int damageDropped(int meta) {
		if (meta == 0)
			return 1;
		else
			return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		return 1;
	}

	@Override
	public int idDropped(int meta, Random random, int fortune) {
		return blockID;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}
}