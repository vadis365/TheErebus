package erebus.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;

public class ItemErebusFlowerSeeds extends Item {

	public static final String[] iconPaths = new String[] { "flowerSeedBlack", "flowerSeedRed", "flowerSeedBrown", "flowerSeedBlue", "flowerSeedPurple", "flowerSeedCyan", "flowerSeedLtGray", "flowerSeedGray", "flowerSeedPink", "flowerSeedYellow", "flowerSeedLtBlue", "flowerSeedMagenta", "flowerSeedOrange", "flowerSeedWhite"};

	public static final short dataFlowerSeedBlack = 0, dataFlowerSeedRed = 1, dataFlowerSeedBrown = 2, dataFlowerSeedBlue = 3, dataFlowerSeedPurple = 4, dataFlowerSeedCyan = 5, dataFlowerSeedLtGray = 6, dataFlowerSeedGray = 7, dataFlowerSeedPink = 8, dataFlowerSeedYellow = 9, dataFlowerSeedLtBlue = 10, dataFlowerSeedMagenta = 11, dataFlowerSeedOrange = 12, dataFlowerSeedWhite = 13;

	@SideOnly(Side.CLIENT)
	public static Icon[] icons;

	public ItemErebusFlowerSeeds(int id) {
		super(id);
		setHasSubtypes(true);
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side != 1)
			return false;
		else if (player.canPlayerEdit(x, y, z, side, is) && player.canPlayerEdit(x, y + 1, z, side, is)) {
			int block = world.getBlockId(x, y, z);
			Block soil = Block.blocksList[block];
			if (soil != null && soil.blockID == Block.grass.blockID && world.isAirBlock(x, y + 1, z)) {
				world.setBlock(x, y + 1, z, ModBlocks.flowerPlanted.blockID, is.getItemDamage(), 3);
				--is.stackSize;
				return true;
			} else
				return false;
		} else
			return false;
	}

	@Override
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[iconPaths.length];
		int i = 0;
		for (String path : iconPaths)
			icons[i++] = iconRegister.registerIcon("erebus:" + path);
	}

	@Override
	public Icon getIconFromDamage(int meta) {
		if (meta < 0 || meta >= icons.length)
			return null;
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for (int a = 0; a < iconPaths.length; a++)
			list.add(new ItemStack(id, 1, a));
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		int i = is.getItemDamage();
		return super.getUnlocalizedName() + "." + i;
	}
}