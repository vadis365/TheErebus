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
import erebus.world.feature.plant.WorldGenGiantFlowers;

public class BlockPlantedGiantFlower extends BlockSapling {

	public static final String[] flowerTypes = new String[] { "Black", "Red", "Brown", "Blue", "Purple", "Cyan", "LtGray", "Gray", "Pink", "Yellow", "LtBlue", "Magenta", "Orange", "White"  };
	public static final byte dataBlack = 0, dataRed = 1, dataBrown = 2, dataBlue = 3, dataPurple = 4, dataCyan = 5, dataLtGray = 6, dataGray = 7, dataPink = 8, dataYellow = 9, dataLtBlue = 10, dataMagenta = 11, dataOrange = 12, dataWhite = 13;

	@SideOnly(Side.CLIENT)
	private Icon[] iconArray;

	public BlockPlantedGiantFlower(int id) {
		super(id);
		float var3 = 0.4F;
		setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 1F, 0.5F + var3);
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
		int meta = world.getBlockMetadata(x, y, z);
		WorldGenerator worldGen = new WorldGenGiantFlowers();

		if (meta >=0 && meta <=13){
			((WorldGenGiantFlowers) worldGen).setFlowerColour(meta+2);
			world.setBlockToAir(x, y, z);
			if (!worldGen.generate(world, rand, x-4, y, z-3))
				world.setBlock(x, y, z, ModBlocks.flowerPlanted.blockID, meta, 3);
			worldGen.generate(world, rand, x-4, y, z-3);
			}
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
		iconArray = new Icon[flowerTypes.length];

		for (int i = 0; i < iconArray.length; i++)
			iconArray[i] = iconRegister.registerIcon("erebus:flowerPlanted" + flowerTypes[i]);
	}

	@ForgeSubscribe
	public void onBonemeal(BonemealEvent event) {
		if (!event.world.isRemote && event.ID == blockID) {
			if (event.world.rand.nextFloat() < 0.45D)
				growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			event.setResult(Result.ALLOW);
		}
	}
}