package erebus.block.plants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.item.ItemMaterials;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SmallPlant extends BlockTallGrass {
	protected final String name;
	protected final boolean colour;

	public SmallPlant(String name, boolean colour) {
		super();
		this.name = name;
		setHardness(0.0F);
		this.colour = colour;
		setStepSound(soundTypeGrass);
		setCreativeTab(ModTabs.plants);
		setBlockName("erebus." + name);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		if (rand.nextInt(25) == 0) {
			int xx;
			int yy;
			int zz;
			xx = x + rand.nextInt(3) - 1;
			yy = y + rand.nextInt(2) - rand.nextInt(2);
			zz = z + rand.nextInt(3) - 1;
			if (world.isAirBlock(xx, yy, zz) && canBlockStay(world, xx, yy, zz)) {
				if ("nettle".equals(name) && rand.nextInt(3) == 0)
					world.setBlock(x, y, z, ModBlocks.nettleFlowered);
				if ("nettleFlowered".equals(name))
					world.setBlock(xx, yy, zz, ModBlocks.nettle);
			}
		}
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		if ("nettle".equals(name)) {
			ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
			ret.add(ItemMaterials.DATA.nettleleaves.makeStack());
			return ret;
		} else if ("nettleFlowered".equals(name)) {
			ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
			ret.add(ItemMaterials.DATA.nettleflowers.makeStack());
			return ret;
		} else if ("swampPlant".equals(name) && world.rand.nextInt(8) == 0) {
			ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
			ret.add(new ItemStack(ModItems.cabbageSeeds, 1, 0));
			return ret;
		} else if ("desertShrub".equals(name) || "mireCoral".equals(name) || "fireBloom".equals(name)) {
			ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
			ret.add(new ItemStack(this));
			return ret;
		} else
			return super.getDrops(world, x, y, z, meta, fortune);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta) {
		return colour ? ColorizerGrass.getGrassColor(0.5D, 1.0D) : 0xFFFFFF;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		return colour ? world.getBiomeGenForCoords(x, z).getBiomeGrassColor(x, y, z) : 0xFFFFFF;
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
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		if ("fireBloom".equals(name))
			world.spawnParticle("flame", x + 0.5F, y + 1F, z + 0.5F, 0.0D, 0.0D, 0.0D);
	}
}