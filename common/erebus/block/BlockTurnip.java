package erebus.block;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

public class BlockTurnip extends BlockCrops {

	@SideOnly(Side.CLIENT)
	private Icon[] iconArray;

	public BlockTurnip(int id) {
		super(id);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if (meta < 7) {
			if (meta == 6)
				meta = 5;

			return iconArray[meta >> 1];
		} else
			return iconArray[3];
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		int dropAmount = 1;

		if (metadata >= 7)
			dropAmount += world.rand.nextInt(3);
		else if (metadata >= 4)
			dropAmount += world.rand.nextInt(2);

		for (int n = 0; n < dropAmount + fortune; n++)
			ret.add(new ItemStack(getSeedItem(), 1, 0));

		return ret;
	}

	@Override
	protected int getSeedItem() {
		return ModItems.turnip.itemID;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		Block soil = blocksList[world.getBlockId(x, y - 1, z)];
		return soil != null && soil == Block.grass || soil == Block.tilledField || soil == Block.dirt;
	}

	@Override
	protected int getCropItem() {
		return ModItems.turnip.itemID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		iconArray = new Icon[4];

		for (int i = 0; i < iconArray.length; ++i)
			iconArray[i] = iconRegister.registerIcon("erebus:turnips_" + i);
	}
}
