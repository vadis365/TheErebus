package erebus.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.item.block.ItemBlockGeneric;

public class BlockLeavesErebus extends BlockLeaves implements ISubBlocksBlock {

	public static final String[] leafTypes = new String[] { "Baobab", "Eucalyptus", "Mahogany", "Asper", "Mossbark", "Cypress" };
	public static final byte dataAcacia = 0, dataEucalyptus = 1, dataMahogany = 2, dataAsper = 3, dataMossbark = 4, dataCypress = 5;
	public static final byte dataAcaciaDecay = 8, dataEucalyptusDecay = 9, dataMahoganyDecay = 10, dataAsperDecay = 11, dataMossbarkDecay = 12, dataCypressDecay = 13;

	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;
	private int[] adjacentTreeBlocks;

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		return 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		return 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int par1) {
		return 16777215;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote) {
			int meta = world.getBlockMetadata(x, y, z);

			if ((meta & 8) != 0 && (meta & 4) == 0) {
				byte b0 = 4;
				int i1 = b0 + 1;
				byte b1 = 32;
				int j1 = b1 * b1;
				int k1 = b1 / 2;

				if (adjacentTreeBlocks == null)
					adjacentTreeBlocks = new int[b1 * b1 * b1];

				int l1;

				if (world.checkChunksExist(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1)) {
					int i2;
					int j2;

					for (l1 = -b0; l1 <= b0; ++l1)
						for (i2 = -b0; i2 <= b0; ++i2)
							for (j2 = -b0; j2 <= b0; ++j2) {
								Block block = world.getBlock(x + l1, y + i2, z + j2);

								if (!block.canSustainLeaves(world, x + l1, y + i2, z + j2))
									if (block.isLeaves(world, x + l1, y + i2, z + j2))
										adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
									else
										adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
								else
									adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
							}

					for (l1 = 1; l1 <= 4; ++l1)
						for (i2 = -b0; i2 <= b0; ++i2)
							for (j2 = -b0; j2 <= b0; ++j2)
								for (int k2 = -b0; k2 <= b0; ++k2)
									if (adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1] == l1 - 1) {
										if (adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
											adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;

										if (adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
											adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;

										if (adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] == -2)
											adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] = l1;

										if (adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] == -2)
											adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] = l1;

										if (adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 - 1] == -2)
											adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 - 1] = l1;

										if (adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] == -2)
											adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] = l1;
									}
				}

				l1 = adjacentTreeBlocks[k1 * j1 + k1 * b1 + k1];

				if (l1 >= 0)
					world.setBlockMetadataWithNotify(x, y, z, meta & -9, 4);
				else
					removeLeaves(world, x, y, z);
			}
		}
	}

	private void removeLeaves(World world, int x, int y, int z) {
		dropBlockAsItem(world, x, y, z, getDamageValue(world, x, y, z), 0);
		world.setBlockToAir(x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		return true;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	/* broken
		@Override
		public Item getItemDropped(int meta, Random rand, int fortune) {
			return Item.getItemFromBlock(ModBlocks.erebusSapling);
		}

		@Override
		public int damageDropped(int meta) {
			switch (meta) {
				case dataAcacia:
					return BlockSaplingErebus.dataAcacia;
				case dataEucalyptus:
					return BlockSaplingErebus.dataEucalyptus;
				case dataMahogany:
					return BlockSaplingErebus.dataMahogany;
				case dataMossbark:
					return BlockSaplingErebus.dataMossbark;
				case dataAsper:
					return BlockSaplingErebus.dataAsper;
				case dataCypress:
					return BlockSaplingErebus.dataCypress;
				default:
					return -1;
			}
		}
	 */
	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z) & ~8;
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float chance, int fortune) {
		if (!world.isRemote)
			if (world.rand.nextInt(20) == 0 && damageDropped(meta) != -1)
				dropBlockAsItem(world, x, y, z, new ItemStack(getItemDropped(meta, world.rand, fortune), 1, damageDropped(meta)));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs creativeTab, List list) {
		for (int i = 0; i < leafTypes.length; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	protected ItemStack createStackedBlock(int meta) {
		return new ItemStack(this, 1, meta & ~8);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		iconArray = new IIcon[leafTypes.length];

		for (int i = 0; i < leafTypes.length; i++)
			iconArray[i] = iconRegister.registerIcon("erebus:leaves" + leafTypes[i]);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return iconArray[(meta &= ~8) >= leafTypes.length ? 0 : meta];
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & ~8));
		return ret;
	}

	@Override
	public void beginLeavesDecay(World world, int x, int y, int z) {
	}

	@Override
	public String[] func_150125_e() {
		return leafTypes;
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockGeneric.class;
	}
}