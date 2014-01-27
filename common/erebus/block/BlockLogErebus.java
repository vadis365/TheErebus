package erebus.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLogErebus extends BlockLog {

	public static final String[][] logTypes = new String[][] { new String[] { "acacia", "eucalyptus", "mahogany", "baobab" }, new String[] { "mossbark", "pink", "scorched", "asper" } };

	public static final byte dataAcacia = 0, dataEucalyptus = 1, dataMahogany = 2, dataBaobab = 3, dataMossbark = 0, dataPink = 1, dataScorched = 2, dataAsper = 3;

	@SideOnly(Side.CLIENT)
	private Icon[] iconSide, iconTop;

	private final byte logGroup;

	public BlockLogErebus(int id, int logGroup) {
		super(id);
		this.logGroup = (byte) logGroup;
	}

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
		return blockID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected Icon getSideIcon(int meta) {
		return iconSide[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected Icon getEndIcon(int meta) {
		return iconTop[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs creativeTab, List list) {
		for (int a = 0; a < logTypes[logGroup].length; a++)
			list.add(new ItemStack(id, 1, a));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		iconSide = new Icon[logTypes[logGroup].length];
		iconTop = new Icon[logTypes[logGroup].length];

		for (int a = 0; a < logTypes[logGroup].length; a++) {
			iconSide[a] = iconRegister.registerIcon("erebus:tree_" + logTypes[logGroup][a]);
			iconTop[a] = iconRegister.registerIcon("erebus:tree_" + logTypes[logGroup][a] + "_top");
		}
	}
}
