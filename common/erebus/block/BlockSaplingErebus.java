package erebus.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.world.feature.WorldGenEucalyptus;
import erebus.world.feature.trees.WorldGenAsperTree;
import erebus.world.feature.trees.WorldGenErebusHugeTree;
import erebus.world.feature.trees.WorldGenErebusTrees;
import erebus.world.feature.trees.WorldGenMossbarkTree;
import erebus.world.feature.trees.WorldGenSavannaTree;

public class BlockSaplingErebus extends BlockSapling {

	public static final String[] saplingTypes = new String[] { "acacia", "eucalyptus", "mahogany", "mossbark", "asper" };
	public static final byte dataAcacia = 0, dataEucalyptus = 1, dataMahogany = 2, dataMossbark = 3, dataAsper = 4;

	@SideOnly(Side.CLIENT)
	private Icon[] iconArray;

	public BlockSaplingErebus(int id) {
		super(id);
		float var3 = 0.4F;
		setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		Block soil = blocksList[world.getBlockId(x, y - 1, z)];
		return soil != null && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote) {
			super.updateTick(world, x, y, z, rand);

			if (rand.nextInt(13 - (world.getBlockLightValue(x, y + 1, z) >> 1)) == 0)
				growTree(world, x, y, z, rand);
		}
	}

	@Override
	public void markOrGrowMarked(World world, int x, int y, int z, Random rand) {
	}

	@Override
	public void growTree(World world, int x, int y, int z, Random rand) {
		if (!TerrainGen.saplingGrowTree(world, rand, x, y, z))
			return;

		int meta = world.getBlockMetadata(x, y, z);
		WorldGenerator worldGen = null;
		int var8 = 0;
		int var9 = 0;
		boolean var10 = false;

		if (meta == dataEucalyptus)
			worldGen = new WorldGenEucalyptus(ModBlocks.logErebusGroup1.blockID, BlockLogErebus.dataEucalyptus, ModBlocks.leavesErebus.blockID, BlockLeavesErebus.dataEucalyptusDecay, 8 + rand.nextInt(4), 5, 8, Block.grass.blockID);
		else if (meta == dataAcacia)
			worldGen = new WorldGenSavannaTree(world.rand.nextInt(3));
		else if (meta == dataMossbark)
			worldGen = new WorldGenMossbarkTree();
		else if (meta == dataAsper)
			worldGen = new WorldGenAsperTree();
		else if (meta == dataMahogany)
			for (var8 = 0; var8 >= -1; --var8) {
				for (var9 = 0; var9 >= -1; --var9)
					if (isSameSapling(world, x + var8, y, z + var9, 0) && isSameSapling(world, x + var8 + 1, y, z + var9, 0) && isSameSapling(world, x + var8, y, z + var9 + 1, 0) && isSameSapling(world, x + var8 + 1, y, z + var9 + 1, 0)) {
						worldGen = new WorldGenErebusHugeTree(true, 20 + rand.nextInt(5), BlockLogErebus.dataMahogany, BlockLeavesErebus.dataMahoganyDecay, true, ModBlocks.logErebusGroup1.blockID, ModBlocks.leavesErebus.blockID);
						var10 = true;
						break;
					}

				if (worldGen != null)
					break;
			}

		if (worldGen == null) {
			var9 = 0;
			var8 = 0;
			worldGen = new WorldGenErebusTrees(true, 5, BlockLogErebus.dataMahogany, BlockLeavesErebus.dataMahoganyDecay, false, ModBlocks.logErebusGroup1.blockID, ModBlocks.leavesErebus.blockID, ModBlocks.thorns.blockID);
		}

		if (var10) {
			world.setBlock(x + var8, y, z + var9, 0);
			world.setBlock(x + var8 + 1, y, z + var9, 0);
			world.setBlock(x + var8, y, z + var9 + 1, 0);
			world.setBlock(x + var8 + 1, y, z + var9 + 1, 0);
		} else
			world.setBlock(x, y, z, 0);

		if (!worldGen.generate(world, rand, x + var8, y, z + var9))
			if (var10) {
				world.setBlock(x + var8, y, z + var9, blockID, meta, 3);
				world.setBlock(x + var8 + 1, y, z + var9, blockID, meta, 3);
				world.setBlock(x + var8, y, z + var9 + 1, blockID, meta, 3);
				world.setBlock(x + var8 + 1, y, z + var9 + 1, blockID, meta, 3);
			} else
				world.setBlock(x, y, z, blockID, meta, 3);
	}

	@Override
	public boolean isSameSapling(World world, int x, int y, int z, int meta) {
		return world.getBlockId(x, y, z) == blockID && world.getBlockMetadata(x, y, z) == meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return iconArray[meta < 0 || meta >= iconArray.length ? 0 : meta];
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs creativeTabs, List list) {
		for (int a = 0; a < iconArray.length; a++)
			list.add(new ItemStack(id, 1, a));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		iconArray = new Icon[saplingTypes.length];

		for (int i = 0; i < iconArray.length; i++)
			iconArray[i] = iconRegister.registerIcon("erebus:sapling_" + saplingTypes[i]);
	}

	@ForgeSubscribe
	public void onBonemeal(BonemealEvent e) {
		if (!e.world.isRemote && e.ID == blockID) {
			growTree(e.world, e.X, e.Y, e.Z, e.world.rand);
			e.setResult(Result.ALLOW);
		}
	}
}
