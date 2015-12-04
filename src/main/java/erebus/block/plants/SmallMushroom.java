package erebus.block.plants;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import erebus.world.feature.plant.WorldGenGiantMushrooms;
import erebus.world.feature.plant.WorldGenGiantMushrooms.MushroomType;
import net.minecraft.block.BlockMushroom;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class SmallMushroom extends BlockMushroom {

	private final String name, itemTextureName;
	private boolean requires2x2ToGrow;

	public SmallMushroom(String name, String itemTextureName, boolean requires2x2ToGrow) {
		this.name = name;
		setHardness(0.0F);
		setStepSound(soundTypeGrass);
		setBlockName("erebus." + name);
		setCreativeTab(ModTabs.plants);
		this.itemTextureName = itemTextureName;
		this.requires2x2ToGrow = requires2x2ToGrow;
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
		return "erebus:" + itemTextureName;
	}

	@Override
	public void func_149853_b(World world, Random rand, int x, int y, int z) {
		if (requires2x2ToGrow) {
			if (isMushroom(world, x + 1, y, z) && isMushroom(world, x + 1, y, z + 1) && isMushroom(world, x, y, z + 1)) {

				world.setBlockToAir(x, y, z);
				world.setBlockToAir(x + 1, y, z);
				world.setBlockToAir(x + 1, y, z + 1);
				world.setBlockToAir(x, y, z + 1);
				WorldGenGiantMushrooms genGiantMushrooms = new WorldGenGiantMushrooms();
				genGiantMushrooms.setMushroomType(MushroomType.getFromShroom(this));

				if (!genGiantMushrooms.generate(world, rand, x + 1, y, z)) {
					world.setBlock(x, y, z, this);
					world.setBlock(x + 1, y, z, this);
					world.setBlock(x + 1, y, z + 1, this);
					world.setBlock(x, y, z + 1, this);
				}

			} else if (isMushroom(world, x - 1, y, z) && isMushroom(world, x - 1, y, z + 1) && isMushroom(world, x, y, z + 1)) {

				world.setBlockToAir(x, y, z);
				world.setBlockToAir(x - 1, y, z);
				world.setBlockToAir(x - 1, y, z + 1);
				world.setBlockToAir(x, y, z + 1);
				WorldGenGiantMushrooms genGiantMushrooms = new WorldGenGiantMushrooms();
				genGiantMushrooms.setMushroomType(MushroomType.getFromShroom(this));

				if (!genGiantMushrooms.generate(world, rand, x, y, z)) {
					world.setBlock(x, y, z, this);
					world.setBlock(x - 1, y, z, this);
					world.setBlock(x - 1, y, z + 1, this);
					world.setBlock(x, y, z + 1, this);
				}

			} else if (isMushroom(world, x + 1, y, z) && isMushroom(world, x + 1, y, z - 1) && isMushroom(world, x, y, z - 1)) {

				world.setBlockToAir(x, y, z);
				world.setBlockToAir(x + 1, y, z);
				world.setBlockToAir(x + 1, y, z - 1);
				world.setBlockToAir(x, y, z - 1);
				WorldGenGiantMushrooms genGiantMushrooms = new WorldGenGiantMushrooms();
				genGiantMushrooms.setMushroomType(MushroomType.getFromShroom(this));

				if (!genGiantMushrooms.generate(world, rand, x + 1, y, z - 1)) {
					world.setBlock(x, y, z, this);
					world.setBlock(x + 1, y, z, this);
					world.setBlock(x + 1, y, z - 1, this);
					world.setBlock(x, y, z - 1, this);
				}

			} else if (isMushroom(world, x - 1, y, z) && isMushroom(world, x - 1, y, z - 1) && isMushroom(world, x, y, z - 1)) {

				world.setBlockToAir(x, y, z);
				world.setBlockToAir(x - 1, y, z);
				world.setBlockToAir(x - 1, y, z - 1);
				world.setBlockToAir(x, y, z - 1);
				WorldGenGiantMushrooms genGiantMushrooms = new WorldGenGiantMushrooms();
				genGiantMushrooms.setMushroomType(MushroomType.getFromShroom(this));

				if (!genGiantMushrooms.generate(world, rand, x, y, z - 1)) {
					world.setBlock(x, y, z, this);
					world.setBlock(x - 1, y, z, this);
					world.setBlock(x - 1, y, z - 1, this);
					world.setBlock(x, y, z - 1, this);
				}

			}
		} else {

			world.setBlockToAir(x, y, z);
			WorldGenGiantMushrooms genGiantMushrooms = new WorldGenGiantMushrooms();
			genGiantMushrooms.setMushroomType(MushroomType.getFromShroom(this));
			if (!genGiantMushrooms.generate(world, rand, x, y, z))
				world.setBlock(x, y, z, this);

		}
	}

	private boolean isMushroom(World world, int x, int y, int z) {
		return world.getBlock(x, y, z) == this;
	}
}