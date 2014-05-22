package erebus.world.biomes.decorators.data;

import static erebus.core.handler.ConfigHandler.*;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.block.BlockErebusOre;
import erebus.block.BlockErebusOreExtras;
import erebus.world.feature.decoration.WorldGenErebusMinable;

public final class OreSettings {
	private static final byte[] checkX = new byte[] { -1, -1, 1, 1, 0, 0 }, checkY = new byte[] { 0, 0, 0, 0, -1, 1 }, checkZ = new byte[] { -1, 1, -1, 1, 0, 0 };

	private static final WorldGenErebusMinable genMinable = new WorldGenErebusMinable();

	private OreType oreType;
	private float chance;
	private byte minIterations, maxIterations;
	private byte minAmount, maxAmount;
	private byte minY, maxY;
	private byte checkArea;

	public OreSettings setType(OreType oreType) {
		this.oreType = oreType;
		return this;
	}

	public OreSettings reset() {
		this.chance = 1F;
		this.minY = 5;
		this.maxY = 112;
		this.checkArea = 3;
		return this;
	}

	public OreSettings setChance(float chance) {
		this.chance = chance;
		return this;
	}

	public OreSettings setIterations(int iterations) {
		this.minIterations = this.maxIterations = (byte) iterations;
		return this;
	}

	public OreSettings setIterations(int minIterations, int maxIterations) {
		this.minIterations = (byte) minIterations;
		this.maxIterations = (byte) maxIterations;
		return this;
	}

	public OreSettings setOreAmount(int amount) {
		this.minAmount = this.maxAmount = (byte) amount;
		return this;
	}

	public OreSettings setOreAmount(int minAmount, int maxAmount) {
		this.minAmount = (byte) minAmount;
		this.maxAmount = (byte) maxAmount;
		return this;
	}

	public OreSettings setY(int minY, int maxY) {
		this.minY = (byte) minY;
		this.maxY = (byte) maxY;
		return this;
	}

	public OreSettings setCheckArea(int checkArea) {
		this.checkArea = (byte) checkArea;
		return this;
	}

	public void generate(World world, Random rand, int x, int z) {
		if (rand.nextFloat() >= chance)
			return;

		int iterations = minIterations + rand.nextInt(maxIterations - minIterations + 1);

		for (int iteration = 0, attempt, xx, yy, zz, testX, testY, testZ, oreAmount, a; iteration < iterations; iteration++) {
			for (attempt = 0; attempt < 12; attempt++) {
				xx = x + rand.nextInt(16);
				zz = z + rand.nextInt(16);
				yy = minY + rand.nextInt(Math.max(1, 1 + maxY - minY));

				for (a = 0; a < 6; a++) {
					testX = xx + checkX[a] * checkArea;
					testY = yy + checkY[a] * checkArea;
					testZ = zz + checkZ[a] * checkArea;

					if (testX >> 4 != x >> 4)
						testX = x;
					if (testZ >> 4 != z >> 4)
						testZ = z;

					if (world.isAirBlock(testX, testY, testZ)) {
						if ((oreAmount = minAmount + rand.nextInt(maxAmount - minAmount + 1)) == 1) {
							if (world.getBlockId(xx, yy, zz) == ModBlocks.umberstone.blockID)
								world.setBlock(xx, yy, zz, oreType.oreId, oreType.oreMeta, 2);
						} else {
							genMinable.prepare(oreType.oreId, oreType.oreMeta, oreAmount);
							genMinable.generate(world, rand, xx, yy, zz);
						}

						attempt = 99;
						break;
					}
				}
			}
		}
	}

	public static enum OreType {
		COAL(BlockErebusOre.dataCoal), IRON(BlockErebusOre.dataIron), GOLD(BlockErebusOre.dataGold), LAPIS(BlockErebusOre.dataLapis), EMERALD(BlockErebusOre.dataEmerald), DIAMOND(BlockErebusOre.dataDiamond), JADE(BlockErebusOre.dataJade), PETRIFIED_WOOD(BlockErebusOre.dataPetrifiedWood), FOSSIL(ModBlocks.oreFossil, 3), ALUMINIUM(ModBlocks.erebusOreExtra, BlockErebusOreExtras.dataAluminium), COPPER(ModBlocks.erebusOreExtra, BlockErebusOreExtras.dataCopper), LEAD(ModBlocks.erebusOreExtra,
		BlockErebusOreExtras.dataLead), SILVER(ModBlocks.erebusOreExtra, BlockErebusOreExtras.dataSilver), TIN(ModBlocks.erebusOreExtra, BlockErebusOreExtras.dataTin);

		final short oreId;
		final byte oreMeta;

		OreType(Block oreBlock, int oreMeta) {
			this.oreId = (short) oreBlock.blockID;
			this.oreMeta = (byte) oreMeta;
		}

		OreType(int oreMeta) {
			this.oreId = (short) ModBlocks.umberOreBlock.blockID;
			this.oreMeta = (byte) oreMeta;
		}

		public void setupDefault(OreSettings settings, boolean extraOres) {
			settings.reset();
			settings.setType(this);

			switch (this) {
				case COAL:
					settings.setIterations(extraOres ? 6 : 8).setOreAmount(9, 12);
					break;
				case IRON:
					settings.setIterations(extraOres ? 7 : 9, extraOres ? 8 : 10).setOreAmount(6, 10);
					break;
				case GOLD:
					settings.setIterations(extraOres ? 4 : 5).setOreAmount(6);
					break;
				case LAPIS:
					settings.setIterations(3).setOreAmount(5).setCheckArea(2);
					break;
				case EMERALD:
					settings.setChance(0.33F).setIterations(0, 2).setOreAmount(3).setCheckArea(1);
					break;
				case DIAMOND:
					settings.setChance(0.66F).setIterations(2, 4).setOreAmount(1).setCheckArea(1);
					break;
				case JADE:
					settings.setChance(0.5F).setIterations(1, 4).setOreAmount(4).setCheckArea(2);
					break;
				case PETRIFIED_WOOD:
					settings.setIterations(extraOres ? 1 : 2, extraOres ? 3 : 4).setOreAmount(7, 9).setCheckArea(2);
					break;
				case FOSSIL:
					settings.setChance(0.125F).setIterations(0, 2).setOreAmount(8, 11).setY(36, 112);
					break;
				case ALUMINIUM:
					settings.setChance(aluminium ? 1F : 0F).setIterations(2, 3).setOreAmount(3, 4).setCheckArea(2);
					break;
				case COPPER:
					settings.setChance(copper ? 1F : 0F).setIterations(7, 9).setOreAmount(5, 7);
					break;
				case LEAD:
					settings.setChance(lead ? 1F : 0F).setIterations(4).setOreAmount(3).setCheckArea(2);
					break;
				case SILVER:
					settings.setChance(silver ? 1F : 0F).setIterations(5).setOreAmount(6, 8);
					break;
				case TIN:
					settings.setChance(tin ? 1F : 0F).setIterations(2, 4).setOreAmount(3, 4).setCheckArea(2);
					break;
			}
		}
	}
}
