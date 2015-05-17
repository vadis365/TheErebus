package erebus.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.core.helper.Utils;
import erebus.lib.EnumColour;
import erebus.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class ItemFlowerSeeds extends Item {

	@SideOnly(Side.CLIENT)
	public IIcon normal, rainbow;

	public ItemFlowerSeeds() {
		setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass) {
		float[] colour = EntitySheep.fleeceColorTable[BlockColored.func_150032_b(Utils.getFlowerMetadata(stack))];
		return Utils.getColour((int) (colour[0] * 255), (int) (colour[1] * 255), (int) (colour[2] * 255));
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side != 1)
			return false;
		else if (player.canPlayerEdit(x, y, z, side, stack) && player.canPlayerEdit(x, y + 1, z, side, stack)) {
			Block soil = world.getBlock(x, y, z);
			if (soil != null && soil == Blocks.grass && world.isAirBlock(x, y + 1, z)) {
				world.setBlock(x, y + 1, z, ModBlocks.flowerPlanted, stack.getItemDamage(), 3);
				stack.stackSize--;
				return true;
			} else
				return false;
		} else
			return false;
	}

	@Override
	public void registerIcons(IIconRegister reg) {
		normal = reg.registerIcon("erebus:flowerSeed0");
		rainbow = reg.registerIcon("erebus:flowerSeed1");
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		return meta == SEED_TYPE.RAINBOW.ordinal() ? rainbow : normal;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void getSubItems(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i < SEED_TYPE.values().length; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String colour = EnumColour.values()[Utils.getFlowerMetadata(stack)].getTranslatedName();
		if (stack.getItemDamage() == SEED_TYPE.RAINBOW.ordinal())
			colour = "colour." + Reference.MOD_ID + ".rainbow";
		return String.format(super.getItemStackDisplayName(stack), colour);
	}

	public enum SEED_TYPE {
		BLACK,
		RED,
		BROWN,
		BLUE,
		PURPLE,
		CYAN,
		LIGHT_GRAY,
		GRAY,
		PINK,
		YELLOW,
		LIGHT_BLUE,
		MAGENTA,
		ORANGE,
		WHITE,
		RAINBOW;
	}
}