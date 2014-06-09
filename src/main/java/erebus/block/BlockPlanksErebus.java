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
import erebus.item.block.ItemBlockPlanks;
import erebus.lib.EnumWood;
import erebus.lib.Reference;

public class BlockPlanksErebus extends Block implements ISubBlocksBlock {

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public BlockPlanksErebus() {
		super(Material.wood);
		setBlockName(Reference.MOD_ID + ".planks");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icons[meta < 0 || meta >= icons.length ? 0 : meta];
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < EnumWood.values().length; i++)
			if (EnumWood.values()[i].hasPlanks())
				list.add(new ItemStack(item, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		icons = new IIcon[EnumWood.values().length];

		for (int i = 0; i < icons.length; i++)
			if (EnumWood.values()[i].hasPlanks())
				icons[i] = reg.registerIcon("erebus:planks_" + EnumWood.values()[i].name().toLowerCase());
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockPlanks.class;
	}
}