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

public class BlockUmberPaver extends Block implements ISubBlocksBlock {

	@SideOnly(Side.CLIENT)
	private IIcon[] blockIcon;
	private final String[] types = new String[] { "", "Mossy", "Webbed" };

	public BlockUmberPaver() {
		super(Material.rock);
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i < types.length; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return blockIcon[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = new IIcon[types.length];
		for (int i = 0; i < blockIcon.length; i++)
			blockIcon[i] = reg.registerIcon("erebus:umberpaver" + types[i]);
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockGeneric.class;
	}
}