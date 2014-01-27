package erebus.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

public class BlockErebusOre extends Block {

	protected String[] iconPaths = new String[] { "oreCoalU", "oreIronU", "oreGoldU", "oreLapisU", "oreDiamondU", "oreEmeraldU", "oreJadeU", "petrifiedWoodOre" };
	public static final byte dataCoal = 0, dataIron = 1, dataGold = 2, dataLapis = 3, dataDiamond = 4, dataEmerald = 5, dataJade = 6, dataPetrifiedWood = 7;

	@SideOnly(Side.CLIENT)
	protected Icon[] icons;

	public BlockErebusOre(int id) {
		super(id, Material.rock);
	}

	@Override
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[iconPaths.length];

		for (int a = 0; a < iconPaths.length; a++)
			icons[a] = iconRegister.registerIcon("erebus:" + iconPaths[a]);
	}

	@Override
	public Icon getIcon(int side, int meta) {
		if (meta < 0 || meta >= icons.length)
			return null;
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs creativeTab, List list) {
		for (int a = 0; a < iconPaths.length; a++)
			list.add(new ItemStack(id, 1, a));
	}

	@Override
	public int damageDropped(int meta) {
		switch (meta) {
			case 0:
			case 4:
			case 5:
				return 0;
			case 3:
				return 4;
			case 6:
				return 1;
			case 7:
				return 7;
		}
		return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		int _default = meta == 3 ? 4 + random.nextInt(5) : 1;

		if (blockID != idDropped(meta, random, fortune)) {
			int j = random.nextInt(fortune + 2) - 1;

			if (j < 0)
				j = 0;

			return _default * (j + 1);
		}

		return _default;
	}

	@Override
	public int idDropped(int meta, Random random, int fortune) {
		switch (meta) {
			case 0:
				return Item.coal.itemID;
			case 3:
				return Item.dyePowder.itemID;
			case 4:
				return Item.diamond.itemID;
			case 5:
				return Item.emerald.itemID;
			case 6:
				return ModItems.erebusMaterials.itemID;
			case 7:
				return ModItems.erebusMaterials.itemID;
		}
		return blockID;
	}

	@Override
	protected ItemStack createStackedBlock(int meta) {
		return new ItemStack(blockID, 1, meta);
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float chance, int fortune) {
		super.dropBlockAsItemWithChance(world, x, y, z, meta, chance, fortune);

		if (idDropped(meta, world.rand, fortune) != blockID) {
			int j1 = 0;

			switch (meta) {
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
}
