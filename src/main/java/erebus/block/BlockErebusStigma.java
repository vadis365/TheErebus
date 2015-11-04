package erebus.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.item.block.ItemBlockErebusStigma;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockErebusStigma extends Block implements ISubBlocksBlock {

	public enum STIGMA_BLOCK_TYPE {
		BLACK_STIGMA,
		RED_STIGMA,
		BROWN_STIGMA,
		BLUE_STIGMA,
		PURPLE_STIGMA,
		CYAN_STIGMA,
		LIGHT_GRAY_STIGMA,
		GRAY_STIGMA,
		PINK_STIGMA,
		YELLOW_STIGMA,
		LIGHT_BLUE_STIGMA,
		MAGENTA_STIGMA,
		ORANGE_STIGMA,
		WHITE_STIGMA
	}

	@SideOnly(Side.CLIENT)
	private IIcon[] blockIcon;

	public BlockErebusStigma() {
		super(Material.plants);
		setLightLevel(1.0F);
		setCreativeTab(ModTabs.plants);
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, meta, fortune);

		ret.add(new ItemStack(ModItems.flowerSeeds, 1 + new Random().nextInt(3), meta));

		return ret;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i < 14; i++)
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
		blockIcon = new IIcon[14];
		for (int i = 0; i < blockIcon.length; i++)
			blockIcon[i] = reg.registerIcon("erebus:erebusFlower0");
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockErebusStigma.class;
	}
}