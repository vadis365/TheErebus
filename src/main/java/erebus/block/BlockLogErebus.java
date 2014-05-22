package erebus.block;

import java.util.List;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLogErebus extends BlockLog {

	public static final String[][] logTypes = new String[][] { new String[] { "Acacia", "Eucalyptus", "Mahogany", "Baobab" }, new String[] { "Mossbark", "Pink", "Scorched", "Asper" }, new String[] { "Cypress" } };

	public static final byte dataAcacia = 0, dataEucalyptus = 1, dataMahogany = 2, dataBaobab = 3, dataMossbark = 0, dataPink = 1, dataScorched = 2, dataAsper = 3, dataCypress = 0;

	@SideOnly(Side.CLIENT)
	private IIcon[] iconSide, iconTop;

	private final byte logGroup;

	public BlockLogErebus(int logGroup) {
		this.logGroup = (byte) logGroup;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getSideIcon(int meta) {
		return iconSide[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getTopIcon(int meta) {
		return iconTop[meta];
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs creativeTab, List list) {
		for (int i = 0; i < logTypes[logGroup].length; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		iconSide = new IIcon[logTypes[logGroup].length];
		iconTop = new IIcon[logTypes[logGroup].length];

		for (int i = 0; i < logTypes[logGroup].length; i++) {
			iconSide[i] = iconRegister.registerIcon("erebus:tree" + logTypes[logGroup][i]);
			iconTop[i] = iconRegister.registerIcon("erebus:tree" + logTypes[logGroup][i] + "Top");
		}
	}
}