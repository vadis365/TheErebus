package erebus.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPlanksErebus extends Block {

	public static final String[] plankTypes = new String[] { "Acacia", "Eucalyptus", "Mahogany", "Baobab", "Mossbark", "Pink", "Scorched", "Asper", "White", "Bamboo", "Cypress" };
	public static final byte dataAcacia = 0, dataEucalyptus = 1, dataMahogany = 2, dataBaobab = 3, dataMossbark = 4, dataPink = 5, dataScorched = 6, dataAsper = 7, dataWhite = 8, dataBamboo = 9, dataCypress = 10;

	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;

	public BlockPlanksErebus() {
		super(Material.wood);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return iconArray[meta < 0 || meta >= iconArray.length ? 0 : meta];
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs creativeTabs, List list) {
		for (int a = 0; a < iconArray.length; a++)
			list.add(new ItemStack(id, 1, a));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		iconArray = new IIcon[plankTypes.length];

		for (int i = 0; i < iconArray.length; i++)
			iconArray[i] = iconRegister.registerIcon("erebus:plank" + plankTypes[i]);
	}
}