package erebus.block.plants;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.block.BlockUndergroundFlower;
import erebus.item.block.ItemBlockColoredSingle;

public class BlockFern extends BlockUndergroundFlower implements IShearable, ISubBlocksBlock {

	public BlockFern() {
		float var3 = 0.4F;
		setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		return ColorizerGrass.getGrassColor(0.5D, 1D);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta) {
		return getBlockColor();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		int red = 0;
		int green = 0;
		int blue = 0;

		for (int i = -1; i <= 1; ++i)
			for (int j = -1; j <= 1; ++j) {
				int colour = world.getBiomeGenForCoords(x + j, z + i).getBiomeGrassColor(x + j, y, z + i);
				red += (colour & 16711680) >> 16;
			green += (colour & 65280) >> 8;
			blue += colour & 255;
			}

		return (red / 9 & 255) << 16 | (green / 9 & 255) << 8 | blue / 9 & 255;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		if (rand.nextInt(16) == 0)
			return Items.wheat_seeds;
		else if (rand.nextInt(15) == 0)
			return Items.melon_seeds;
		else
			return null;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon("erebus:erebusfern");
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		return true;
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockColoredSingle.class;
	}
}