package erebus.block.plants;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.world.feature.plant.WorldGenGiantMushrooms;
import erebus.world.feature.plant.WorldGenGiantMushrooms.MushroomType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMushroom;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class SmallMushroom extends BlockMushroom {
	private final String name;

	public SmallMushroom(String name) {
		super();
		this.name = name;
		setHardness(0.0F);
		setStepSound(soundTypeGrass);
		setBlockName("erebus." + name);
		setCreativeTab(ModTabs.plants);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon("erebus:" + name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemIconName() {
		return "erebus:" + name;
	}

	@Override
	public void func_149853_b(World world, Random rand, int x, int y, int z) {
		int xx = x;
		int yy = y;
		int zz = z;
		if (isMushroom(world, xx, yy, zz))
			if (getShroomData(world.getBlock(x, y, z)) != 0 && getShroomData(world.getBlock(x, y, z)) != 3) {
				if (isMushroom(world, xx + 1, yy, zz) && isMushroom(world, xx + 1, yy, zz + 1) && isMushroom(world, xx, yy, zz + 1))
					growPlants(world, x + 1, y, z, world.rand);

				if (isMushroom(world, xx - 1, yy, zz) && isMushroom(world, xx - 1, yy, zz + 1) && isMushroom(world, xx, yy, zz + 1))
					growPlants(world, x, y, z, world.rand);

				if (isMushroom(world, xx + 1, yy, zz) && isMushroom(world, xx + 1, yy, zz - 1) && isMushroom(world, xx, yy, zz - 1))
					growPlants(world, x + 1, y, z - 1, world.rand);

				if (isMushroom(world, xx - 1, yy, zz) && isMushroom(world, xx - 1, yy, zz - 1) && isMushroom(world, xx, yy, zz - 1))
					growPlants(world, x, y, z - 1, world.rand);
			} else
				growPlants(world, x, y, z, world.rand);
	}

	public void growPlants(World world, int x, int y, int z, Random rand) {
		if (isMushroom(world, x, y, z)) {
			if (getShroomData(world.getBlock(x, y, z)) != 0 && getShroomData(world.getBlock(x, y, z)) != 3) {
				if (isMushroom(world, x + 1, y, z) && isMushroom(world, x + 1, y, z + 1) && isMushroom(world, x, y, z + 1)) {
					world.setBlockToAir(x, y, z);
					world.setBlockToAir(x + 1, y, z);
					world.setBlockToAir(x + 1, y, z + 1);
					world.setBlockToAir(x, y, z + 1);
					WorldGenGiantMushrooms genGiantMushrooms = new WorldGenGiantMushrooms();
					genGiantMushrooms.setMushroomType(MushroomType.values()[getShroomData(this)]);

					if (!genGiantMushrooms.generate(world, rand, x, y, z)) {
						world.setBlock(x, y, z, this);
						world.setBlock(x + 1, y, z, this);
						world.setBlock(x + 1, y, z + 1, this);
						world.setBlock(x, y, z + 1, this);
					}
				}

				if (isMushroom(world, x - 1, y, z) && isMushroom(world, x - 1, y, z + 1) && isMushroom(world, x, y, z + 1)) {
					world.setBlockToAir(x, y, z);
					world.setBlockToAir(x - 1, y, z);
					world.setBlockToAir(x - 1, y, z + 1);
					world.setBlockToAir(x, y, z + 1);
					WorldGenGiantMushrooms genGiantMushrooms = new WorldGenGiantMushrooms();
					genGiantMushrooms.setMushroomType(MushroomType.values()[getShroomData(this)]);

					if (!genGiantMushrooms.generate(world, rand, x, y, z)) {
						world.setBlock(x, y, z, this);
						world.setBlock(x - 1, y, z, this);
						world.setBlock(x - 1, y, z + 1, this);
						world.setBlock(x, y, z + 1, this);
					}
				}

				if (isMushroom(world, x + 1, y, z) && isMushroom(world, x + 1, y, z - 1) && isMushroom(world, x, y, z - 1)) {
					world.setBlockToAir(x, y, z);
					world.setBlockToAir(x + 1, y, z);
					world.setBlockToAir(x + 1, y, z - 1);
					world.setBlockToAir(x, y, z - 1);
					WorldGenGiantMushrooms genGiantMushrooms = new WorldGenGiantMushrooms();
					genGiantMushrooms.setMushroomType(MushroomType.values()[getShroomData(this)]);

					if (!genGiantMushrooms.generate(world, rand, x, y, z)) {
						world.setBlock(x, y, z, this);
						world.setBlock(x + 1, y, z, this);
						world.setBlock(x + 1, y, z - 1, this);
						world.setBlock(x, y, z - 1, this);
					}
				}

				if (isMushroom(world, x - 1, y, z) && isMushroom(world, x - 1, y, z - 1) && isMushroom(world, x, y, z - 1)) {
					world.setBlockToAir(x, y, z);
					world.setBlockToAir(x - 1, y, z);
					world.setBlockToAir(x - 1, y, z - 1);
					world.setBlockToAir(x, y, z - 1);
					WorldGenGiantMushrooms genGiantMushrooms = new WorldGenGiantMushrooms();
					genGiantMushrooms.setMushroomType(MushroomType.values()[getShroomData(this)]);

					if (!genGiantMushrooms.generate(world, rand, x, y, z)) {
						world.setBlock(x, y, z, this);
						world.setBlock(x - 1, y, z, this);
						world.setBlock(x - 1, y, z - 1, this);
						world.setBlock(x, y, z - 1, this);
					}
				}
			} else {
				world.setBlockToAir(x, y, z);
				WorldGenGiantMushrooms genGiantMushrooms = new WorldGenGiantMushrooms();
				genGiantMushrooms.setMushroomType(MushroomType.values()[getShroomData(this)]);
				if (!genGiantMushrooms.generate(world, rand, x, y, z))
					world.setBlock(x, y, z, this);
			}
		}
	}

	private boolean isMushroom(World world, int x, int y, int z) {
		return world.getBlock(x, y, z) == this;
	}

	private int getShroomData(Block block) {
		if (block == ModBlocks.bulbCapped)
			return 0;
		else if (block == ModBlocks.greenMushroom)
			return 1;
		else if (block == ModBlocks.bundleshroom)
			return 2;
		else if (block == ModBlocks.kaizerfinger)
			return 3;
		else if (block == ModBlocks.dutchCap)
			return 4;
		else
			return -1;
	}
}