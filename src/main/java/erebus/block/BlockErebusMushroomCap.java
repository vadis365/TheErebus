package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;

public class BlockErebusMushroomCap extends Block
{

	public static final String[] shroomTypes = new String[] { "BulbCap", "1", "2", "3", "4" };// need names

	private final int mushroomType;
	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray, stalkIcon, insideIcon;

	public BlockErebusMushroomCap(int type)
	{
		super(Material.wood);
		mushroomType = type;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return meta == 10 && side > 1 ? stalkIcon[mushroomType] : meta >= 1 && meta <= 9 && side == 1 ? iconArray[mushroomType] : meta >= 1 && meta <= 3 && side == 2 ? iconArray[mushroomType] : meta >= 7 && meta <= 9 && side == 3 ? iconArray[mushroomType] : (meta == 1 || meta == 4 || meta == 7) && side == 4 ? iconArray[mushroomType] : (meta == 3 || meta == 6 || meta == 9) && side == 5 ? iconArray[mushroomType] : meta == 14 ? iconArray[mushroomType] : meta == 15 ? stalkIcon[mushroomType] : insideIcon[mushroomType];
	}

	@Override
	public int quantityDropped(Random rand)
	{
		int amount = rand.nextInt(10) - 7;
		if (amount < 0)
		{
			amount = 0;
		}
		return amount;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		return Item.getItemFromBlock(ModBlocks.plantSmall);
	}

	@Override
	public int damageDropped(int meta)
	{
		return mushroomType;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon)
	{
		iconArray = new IIcon[shroomTypes.length];
		insideIcon = new IIcon[shroomTypes.length];
		stalkIcon = new IIcon[shroomTypes.length];

		for (int i = 0; i < iconArray.length; i++)
		{
			iconArray[i] = icon.registerIcon("erebus:mushroom" + shroomTypes[i]);
			insideIcon[i] = icon.registerIcon("erebus:mushroom" + shroomTypes[i] + "Inside");
			stalkIcon[i] = icon.registerIcon("erebus:mushroom" + shroomTypes[i] + "Stalk");
		}
	}
}