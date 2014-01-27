package erebus.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;

public class BlockLeavesErebus extends BlockLeaves {

	public static final String[] leafTypes = new String[] { "acacia", "eucalyptus", "mahogany", "asper", "mossbark", "pink" };
	public static final byte dataAcacia = 0, dataEucalyptus = 1, dataMahogany = 2, dataAsper = 3, dataMossbark = 4, dataPink = 5;
	public static final byte dataAcaciaDecay = 8, dataEucalyptusDecay = 9, dataMahoganyDecay = 10, dataAsperDecay = 11, dataMossbarkDecay = 12, dataPinkDecay = 13;

	private Icon[] iconArray;
	private int[] adjacentTreeBlocks;

	public BlockLeavesErebus(int id) {
		super(id);
	}

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
			if (meta >= 8) {
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
					int k2;

					for (l1 = -b0; l1 <= b0; ++l1)
						for (i2 = -b0; i2 <= b0; ++i2)
							for (j2 = -b0; j2 <= b0; ++j2) {
								k2 = world.getBlockId(x + l1, y + i2, z + j2);

								Block block = Block.blocksList[k2];

								if (block != null && block.canSustainLeaves(world, x + l1, y + i2, z + j2))
									adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
								else if (block != null && block.isLeaves(world, x + l1, y + i2, z + j2))
									adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
								else
									adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
							}

					for (l1 = 1; l1 <= 10; ++l1)
						for (i2 = -b0; i2 <= b0; ++i2)
							for (j2 = -b0; j2 <= b0; ++j2)
								for (k2 = -b0; k2 <= b0; ++k2)
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

				/*
				 * if (l1>=0)world.setBlockMetadataWithNotify(x,y,z,meta&-9,4);
				 * else removeLeaves(world,x,y,z);
				 */
				if (l1 < 0)
					removeLeaves(world, x, y, z);
			}
		}
	}

	private void removeLeaves(World world, int x, int y, int z) {
		dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
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

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
		return ModBlocks.erebusSapling.blockID;
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
			default:
				return -1;
		}
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float par6, int fortune) {
		if (!world.isRemote) {
			byte saplingChance = (byte) (meta < 8 ? 20 : 40);

			if (world.rand.nextInt(saplingChance) == 0 && damageDropped(meta) != -1)
				dropBlockAsItem_do(world, x, y, z, new ItemStack(idDropped(meta, world.rand, fortune), 1, damageDropped(meta)));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs creativeTab, List list) {
		for (int a = 0; a < leafTypes.length; a++)
			list.add(new ItemStack(id, 1, a));
	}

	@Override
	protected ItemStack createStackedBlock(int meta) {
		return new ItemStack(blockID, 1, meta & ~8);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		iconArray = new Icon[leafTypes.length];

		for (int a = 0; a < leafTypes.length; a++)
			iconArray[a] = iconRegister.registerIcon("erebus:leaves_" + leafTypes[a]);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return iconArray[(meta &= ~8) >= leafTypes.length ? 0 : meta];
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & ~8));
		return ret;
	}

	@Override
	public void beginLeavesDecay(World world, int x, int y, int z) {
	}
}
