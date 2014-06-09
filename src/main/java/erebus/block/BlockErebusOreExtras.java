package erebus.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.item.block.ItemBlockGeneric;

public class BlockErebusOreExtras extends Block implements ISubBlocksBlock {

	private final String[] types = { "oreAluminiumU", "oreCopperU", "oreLeadU", "oreSilverU", "oreTinU" };
	public static final byte dataAluminium = 0, dataCopper = 1, dataLead = 2, dataSilver = 3, dataTin = 4;

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public BlockErebusOreExtras() {
		super(Material.rock);
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item id, CreativeTabs tab, List list) {
		list.add(new ItemStack(id, 1, dataAluminium));
		list.add(new ItemStack(id, 1, dataCopper));
		list.add(new ItemStack(id, 1, dataLead));
		list.add(new ItemStack(id, 1, dataSilver));
		list.add(new ItemStack(id, 1, dataTin));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		icons = new IIcon[types.length];
		for (int i = 0; i < icons.length; i++)
			icons[i] = reg.registerIcon("erebus:" + types[i]);
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockGeneric.class;
	}
}