package erebus.block.bamboo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.item.ItemMaterials.DATA;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.EnumPlantType;

public class BlockBambooShoot extends BlockFlower implements IGrowable {

	public static byte calculateBambooHappiness(World world, int x, int y, int z, Block block) {
		double happiness = 0;
		int bottomY = y;

		while (world.getBlock(x, --bottomY, z) == block)
			;
		bottomY++;

		// CLIMATE

		BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
		boolean canSeeSun = world.canBlockSeeTheSky(x, y, z);

		if (biome == BiomeGenBase.hell)
			happiness -= 8;
		else if (biome.temperature >= 2F)
			happiness -= 6;
		else if (biome.temperature >= 1.4F && biome.temperature < 2F)
			happiness += canSeeSun ? -2 : 5;
		else if (biome.temperature >= 0.9F && biome.temperature < 1.4F)
			happiness += canSeeSun ? 2 : 6;
		else if (biome.temperature >= 0.7F && biome.temperature < 0.9F)
			happiness += canSeeSun ? 4 : 1;
		else if (biome.temperature >= 0.3F && biome.temperature < 0.7F)
			happiness += canSeeSun ? 4 : -2;
		else if (biome.temperature >= 0.1F && biome.temperature < 0.3F)
			happiness -= canSeeSun ? 8 : 4;
		else if (biome.temperature < 0.1F)
			happiness -= canSeeSun ? 16 : 10;

		happiness += biome.canSpawnLightningBolt() ? 4 : -2;

		// WATER

		int perfectWaterAmount = (int) Math.floor(35D * (Math.pow(biome.temperature + 1D, 2) / 4D) * (biome.canSpawnLightningBolt() ? 1D : 1.75D));
		double waterFound = 0D;

		for (int a = 0, xx, zz; a < 150; a++)
			if (world.getBlock(xx = x + world.rand.nextInt(10) - 5, bottomY + world.rand.nextInt(4) - 2, zz = z + world.rand.nextInt(10) - 5).getMaterial() == Material.water)
				waterFound += 8D - Math.sqrt(Math.pow(xx - x, 2) + Math.pow(zz - z, 2));

		happiness += (5.5D - 0.25D * Math.abs(waterFound - perfectWaterAmount)) * 0.8D;

		// SOIL

		Float soilValue = soilValues.get(world.getBlock(x, bottomY - 1, z));
		if (soilValue != null)
			happiness *= soilValue;

		return (byte) Math.floor(Math.min(22, Math.max(-22, happiness)));
	}

	private static Map<Block, Float> soilValues = new HashMap<Block, Float>();

	static {
		soilValues.put(Blocks.dirt, 1F);
		soilValues.put(Blocks.grass, 1F);
		soilValues.put(Blocks.clay, 0.75F);
		soilValues.put(Blocks.sand, 0.4F);
		// add mulch 1.5 and claysoil 1.25
	}

	public BlockBambooShoot() {
		super(0);
		setTickRandomly(true);
		float f = 0.2F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.5F, 0.5F + f);
		setCreativeTab(null);
		setBlockName("erebus.bambooShoot");
		setBlockTextureName("erebus:bambooShoot");
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote) {
			super.updateTick(world, x, y, z, rand);

			int happiness = calculateBambooHappiness(world, x, y, z, this);

			if (rand.nextInt(5) <= 1 && happiness > rand.nextInt(35)) {
				int meta = world.getBlockMetadata(x, y, z);

				/*
				 * if ((meta&7)<7)world.setBlockMetadataWithNotify(x,y,z,meta+1,4); else
				 */world.setBlock(x, y, z, ModBlocks.bambooCrop, (meta >= 8 ? 8 : 0) + (int) Math.min(7D, 2D + happiness / 7D + (meta >= 8 ? 0.5D : 0D) + Math.abs((happiness / 10D + (meta >= 8 ? 0.6D : 0D)) * rand.nextGaussian())), 3);
			}
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		Block soil = world.getBlock(x, y - 1, z);
		return soil != null && soilValues.containsKey(soil);
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Plains;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return ModItems.materials;
	}

	@Override
	public int damageDropped(int meta) {
		return DATA.bambooShoot.ordinal();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return ModItems.materials;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon(getTextureName());
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item));
	}

	@Override
	public boolean func_149851_a(World world, int x, int y, int z, boolean isRemote) {
		return true;
	}

	@Override
	public boolean func_149852_a(World world, Random rand, int x, int y, int z) {
		return true;
	}

	@Override
	public void func_149853_b(World world, Random rand, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta < 7)
			world.setBlockMetadataWithNotify(x, y, z, meta + 9, 4);
	}
}