package erebus.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.ModItems;
import erebus.item.block.ItemBlockGeneric;

public class BlockErebusOre extends Block implements ISubBlocksBlock
{

	protected String[] iconPaths = new String[] { "oreCoalU", "oreIronU", "oreGoldU", "oreLapisU", "oreDiamondU", "oreEmeraldU", "oreJadeU", "petrifiedWoodOre", "oreVolcanicDiamond" };
	public static final byte dataCoal = 0, dataIron = 1, dataGold = 2, dataLapis = 3, dataDiamond = 4, dataEmerald = 5, dataJade = 6, dataPetrifiedWood = 7, dataEncrustedDiamond = 8;

	@SideOnly(Side.CLIENT)
	protected IIcon[] icons;

	public BlockErebusOre()
	{
		super(Material.rock);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons = new IIcon[iconPaths.length];

		for (int a = 0; a < iconPaths.length; a++)
		{
			icons[a] = iconRegister.registerIcon("erebus:" + iconPaths[a]);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= icons.length)
		{
			return null;
		}
		return icons[meta];
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs creativeTab, List list)
	{
		for (int a = 0; a < iconPaths.length; a++)
		{
			list.add(new ItemStack(id, 1, a));
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		switch (meta)
		{
			case dataCoal:
			case dataDiamond:
			case dataEmerald:
			case dataEncrustedDiamond:
				return 0;
			case dataLapis:
				return 4;
			case dataJade:
				return 1;
			case dataPetrifiedWood:
				return 7;
		}
		return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		int _default = meta == dataLapis ? 4 + random.nextInt(5) : 1;

		if (Item.getItemFromBlock(this) != getItemDropped(meta, random, fortune))
		{
			int j = random.nextInt(fortune + 2) - 1;

			if (j < 0)
			{
				j = 0;
			}

			return _default * (j + 1);
		}

		return _default;
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		switch (meta)
		{
			case 0:
				return Items.coal;
			case 3:
				return Items.dye;
			case 4:
				return Items.diamond;
			case 5:
				return Items.emerald;
			case 6:
				return ModItems.erebusMaterials;
			case 7:
				return ModItems.erebusMaterials;
			case dataEncrustedDiamond:
				return ModItems.encrustedDiamond;
		}
		return super.getItemDropped(meta, random, fortune);
	}

	@Override
	protected ItemStack createStackedBlock(int meta)
	{
		return new ItemStack(this, 1, meta);
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float chance, int fortune)
	{
		super.dropBlockAsItemWithChance(world, x, y, z, meta, chance, fortune);

		if (getItemDropped(meta, world.rand, fortune) != Item.getItemFromBlock(this))
		{
			int j1 = 0;

			switch (meta)
			{
				case 0:
					j1 = MathHelper.getRandomIntegerInRange(world.rand, 0, 2);
					break;
				case 3:
					j1 = MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
					break;
				case 4:
				case 5:
				case 6:
					j1 = MathHelper.getRandomIntegerInRange(world.rand, 3, 7);
			}

			dropXpOnBlockBreak(world, x, y, z, j1);
		}
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass()
	{
		return ItemBlockGeneric.class;
	}
}