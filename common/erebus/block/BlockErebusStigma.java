package erebus.block;

import java.util.ArrayList;
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
import erebus.ModItems;

public class BlockErebusStigma extends Block {

	public enum STIGMA_BLOCK_TYPE {
		BLACK_STIGMA, RED_STIGMA, BROWN_STIGMA, BLUE_STIGMA, PURPLE_STIGMA, CYAN_STIGMA, LIGHT_GRAY_STIGMA, GRAY_STIGMA, PINK_STIGMA, YELLOW_STIGMA, LIGHT_BLUE_STIGMA, MAGENTA_STIGMA, ORANGE_STIGMA, WHITE_STIGMA
	}

	@SideOnly(Side.CLIENT)
	private Icon[] blockIcon;

	public BlockErebusStigma(int id) {
		super(id, Material.plants);
		setLightValue(1.0F);
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
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> ret = super.getBlockDropped(world, x, y, z, meta, fortune);

			ret.add(new ItemStack(ModItems.flowerSeeds, 1 + new Random().nextInt(3), meta));

		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (int i = 0; i < 14; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return blockIcon[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockIcon = new Icon[14];
		for (int i = 0; i < blockIcon.length; i++)
			blockIcon[i] = reg.registerIcon("erebus:erebusFlower0");
	}
}