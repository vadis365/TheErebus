package erebus.block;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.item.ItemErebusMaterial;

public class BlockBambooShoot extends BlockFlower implements IPlantable {
	public static byte calculateBambooHappiness(World world, int x, int y, int z, int blockID) {
		double happiness = 0;
		int bottomY = y;

		while (world.getBlockId(x, --bottomY, z) == blockID);
		++bottomY;

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
			if (world.getBlockMaterial(xx = x + world.rand.nextInt(10) - 5, bottomY + world.rand.nextInt(4) - 2, zz = z + world.rand.nextInt(10) - 5) == Material.water)
				waterFound += 8D - Math.sqrt(Math.pow(xx - x, 2) + Math.pow(zz - z, 2));

		happiness += (5.5D - 0.25D * Math.abs(waterFound - perfectWaterAmount)) * 0.8D;

		// SOIL

		Float soilValue = soilValues.get(world.getBlockId(x, bottomY - 1, z));
		if (soilValue != null)
			happiness *= soilValue;

		return (byte) Math.floor(Math.min(22, Math.max(-22, happiness)));
	}

	private static Map<Integer, Float> soilValues = new HashMap<Integer, Float>();
	static {
		soilValues.put(Block.dirt.blockID, 1F);
		soilValues.put(Block.grass.blockID, 1F);
		soilValues.put(Block.blockClay.blockID, 0.75F);
		soilValues.put(Block.sand.blockID, 0.4F);
		// add mulch 1.5 and claysoil 1.25
	}

	public BlockBambooShoot(int id) {
		super(id, Material.wood);
		setTickRandomly(true);
		float f = 0.2F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.5F, 0.5F + f);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote) {
			super.updateTick(world, x, y, z, rand);

			int happiness = calculateBambooHappiness(world, x, y, z, blockID);

			if (rand.nextInt(5) <= 1 && happiness > rand.nextInt(35)) {
				int meta = world.getBlockMetadata(x, y, z);

				/*
				 * if
				 * ((meta&7)<7)world.setBlockMetadataWithNotify(x,y,z,meta+1,4);
				 * else
				 */world.setBlock(x, y, z, ModBlocks.bambooCrop.blockID, (meta >= 8 ? 8 : 0) + (int) Math.min(7D, 2D + happiness / 7D + (meta >= 8 ? 0.5D : 0D) + Math.abs((happiness / 10D + (meta >= 8 ? 0.6D : 0D)) * rand.nextGaussian())), 3);
			}
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		Block soil = blocksList[world.getBlockId(x, y - 1, z)];
		return soil != null && soilValues.containsKey(soil.blockID);
	}

	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		return EnumPlantType.Plains;
	}

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
		return ModItems.erebusMaterials.itemID;
	}

	@Override
	public int damageDropped(int meta) {
		return ItemErebusMaterial.dataBambooShoot;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int x, int y, int z) {
		return ModItems.erebusMaterials.itemID;
	}

	@ForgeSubscribe
	public void onBonemeal(BonemealEvent e) {
		if (!e.world.isRemote && e.ID == blockID) {
			int meta = e.world.getBlockMetadata(e.X, e.Y, e.Z);
			if (meta < 7) {
				e.world.setBlockMetadataWithNotify(e.X, e.Y, e.Z, meta + 9, 4); // prevent
																				// autogrowing
																				// full
																				// bamboo
				e.setResult(Result.ALLOW);
			}
		}
	}
}
