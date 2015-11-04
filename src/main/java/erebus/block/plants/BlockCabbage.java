package erebus.block.plants;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.item.ItemErebusFood;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCabbage extends BlockCrops {

	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;

	public BlockCabbage() {
		setBlockName("erebus.cabbageCrop");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta < 7) {
			if (meta == 6)
				meta = 5;

			return iconArray[meta >> 1];
		} else
			return iconArray[3];
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);

		if (metadata >= 7) {
			for (int i = 0; i < 1 + fortune; ++i) {
				if (world.rand.nextInt(15) <= metadata) {
					ret.add(new ItemStack(this.func_149866_i(), 1, 0));
				}
			}
			ret.add(new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.CABBAGE.ordinal()));
		}
		return ret;
	}

	@Override
	protected Item func_149866_i() {
		return ModItems.cabbageSeeds;
	}

	@Override
	protected Item func_149865_P() {
		return null;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		Block soil = world.getBlock(x, y - 1, z);
		return soil != null && soil == Blocks.grass || soil == Blocks.farmland || soil == Blocks.dirt;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		iconArray = new IIcon[4];

		for (int i = 0; i < iconArray.length; ++i)
			iconArray[i] = iconRegister.registerIcon("erebus:cabbage" + i);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return 1;
	}
}