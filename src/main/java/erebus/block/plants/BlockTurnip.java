package erebus.block.plants;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockTurnip extends BlockCrops {

	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;

	public BlockTurnip() {
		setBlockName("erebus.turnipsCrop");
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
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		int dropAmount = 1;

		if (metadata >= 7)
			dropAmount += world.rand.nextInt(3);
		else if (metadata >= 4)
			dropAmount += world.rand.nextInt(2);

		for (int n = 0; n < dropAmount + fortune; n++)
			ret.add(new ItemStack(func_149866_i(), 1, 0));

		return ret;
	}

	@Override
	protected Item func_149866_i() {
		return ModItems.turnip;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		Block soil = world.getBlock(x, y - 1, z);
		return soil != null && soil == Blocks.grass || soil == Blocks.farmland || soil == Blocks.dirt;
	}

	@Override
	protected Item func_149865_P() {
		return ModItems.turnip;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		iconArray = new IIcon[4];

		for (int i = 0; i < iconArray.length; ++i)
			iconArray[i] = iconRegister.registerIcon("erebus:turnips" + i);
	}
}