package erebus.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.handler.ConfigHandler;

public class BlockErebusOreExtras extends Block {

	private final String[] types = { "oreAluminiumU", "oreCopperU", "oreLeadU", "oreSilverU", "oreTinU" };
	public static final byte dataAluminium = 0, dataCopper = 1, dataLead = 2, dataSilver = 3, dataTin = 4;

	@SideOnly(Side.CLIENT)
	private Icon[] blockIcon;

	public BlockErebusOreExtras(int id) {
		super(id, Material.rock);
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		if (ConfigHandler.aluminium)
			list.add(new ItemStack(id, 1, 0));
		if (ConfigHandler.copper)
			list.add(new ItemStack(id, 1, 1));
		if (ConfigHandler.lead)
			list.add(new ItemStack(id, 1, 2));
		if (ConfigHandler.silver)
			list.add(new ItemStack(id, 1, 3));
		if (ConfigHandler.tin)
			list.add(new ItemStack(id, 1, 4));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return blockIcon[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockIcon = new Icon[types.length];
		for (int i = 0; i < blockIcon.length; i++)
			blockIcon[i] = reg.registerIcon("erebus:" + types[i]);
	}
}
