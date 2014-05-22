package erebus.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.world.feature.tree.WorldGenAcaciaTree;
import erebus.world.feature.tree.WorldGenAsperTree;
import erebus.world.feature.tree.WorldGenErebusHugeTree;
import erebus.world.feature.tree.WorldGenErebusTrees;
import erebus.world.feature.tree.WorldGenEucalyptusTree;
import erebus.world.feature.tree.WorldGenMossbarkTree;

public class BlockSaplingErebus extends BlockSapling {

	public static final String[] saplingTypes = new String[] { "Acacia", "Eucalyptus", "Mahogany", "Mossbark", "Asper", "Cypress" };
	public static final byte dataAcacia = 0, dataEucalyptus = 1, dataMahogany = 2, dataMossbark = 3, dataAsper = 4, dataCypress = 5;

	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;

	public BlockSaplingErebus() {
		float var3 = 0.4F;
		setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		Block soil = world.getBlock(x, y - 1, z);
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
			worldGen = new WorldGenEucalyptusTree();
		else if (meta == dataAcacia)
			worldGen = new WorldGenAcaciaTree();
		else if (meta == dataMossbark)
			worldGen = new WorldGenMossbarkTree();
		else if (meta == dataAsper)
			worldGen = new WorldGenAsperTree();
		else if (meta == dataMahogany)
			for (var8 = 0; var8 >= -1; --var8) {
				for (var9 = 0; var9 >= -1; --var9)
					if (isSameSapling(world, x + var8, y, z + var9, 0) && isSameSapling(world, x + var8 + 1, y, z + var9, 0) && isSameSapling(world, x + var8, y, z + var9 + 1, 0) && isSameSapling(world, x + var8 + 1, y, z + var9 + 1, 0)) {
						worldGen = new WorldGenErebusHugeTree(true, BlockLogErebus.dataMahogany, BlockLeavesErebus.dataMahoganyDecay, true, ModBlocks.logErebusGroup1.blockID, ModBlocks.leavesErebus.blockID);
						((WorldGenErebusHugeTree) worldGen).prepare(20 + rand.nextInt(5));
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
	public IIcon getIcon(int side, int meta) {
		return iconArray[meta < 0 || meta >= iconArray.length ? 0 : meta];
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs creativeTabs, List list) {
		for (int a = 0; a < iconArray.length; a++)
			list.add(new ItemStack(id, 1, a));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		iconArray = new IIcon[saplingTypes.length];

		for (int i = 0; i < iconArray.length; i++)
			iconArray[i] = iconRegister.registerIcon("erebus:sapling" + saplingTypes[i]);
	}

	@SubscribeEvent
	public void onBonemeal(BonemealEvent event) {
		if (!event.world.isRemote && event.block == this) {
			if (event.world.rand.nextFloat() < 0.45D)
				growTree(event.world, event.x, event.y, event.z, event.world.rand);
			event.setResult(Result.ALLOW);
		}
	}
}