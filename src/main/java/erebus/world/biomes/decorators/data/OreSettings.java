package erebus.world.biomes.decorators.data;

import java.util.Random;

import erebus.ModBlocks;
import erebus.world.feature.decoration.WorldGenErebusMinable;
import net.minecraft.block.Block;
import net.minecraft.world.World;

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
		chance = 1F;
		minY = 5;
		maxY = 112;
		checkArea = 3;
		return this;
	}

	public OreSettings setChance(float chance) {
		this.chance = chance;
		return this;
	}

	public OreSettings setIterations(int iterations) {
		minIterations = maxIterations = (byte) iterations;
		return this;
	}

	public OreSettings setIterations(int minIterations, int maxIterations) {
		this.minIterations = (byte) minIterations;
		this.maxIterations = (byte) maxIterations;
		return this;
	}

	public OreSettings setOreAmount(int amount) {
		minAmount = maxAmount = (byte) amount;
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

		for (int iteration = 0, attempt, xx, yy, zz, testX, testY, testZ, oreAmount, a; iteration < iterations; iteration++)
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
							if (world.getBlock(xx, yy, zz) == ModBlocks.umberstone)
								world.setBlock(xx, yy, zz, oreType.oreBlock, oreType.oreMeta, 2);
						} else {
							genMinable.prepare(oreType.oreBlock, oreType.oreMeta, oreAmount);
							genMinable.generate(world, rand, xx, yy, zz);
						}

						attempt = 99;
						break;
					}
				}
			}
	}

	public static enum OreType {
		COAL(ModBlocks.oreCoal, true),
		IRON(ModBlocks.oreIron, true),
		GOLD(ModBlocks.oreGold, true),
		LAPIS(ModBlocks.oreLapis, true),
		EMERALD(ModBlocks.oreEmerald, true),
		DIAMOND(ModBlocks.oreDiamond, true),
		DIAMOND_ENCRUSTED(ModBlocks.oreEncrustedDiamond, true),
		JADE(ModBlocks.oreJade, true),
		PETRIFIED_WOOD(ModBlocks.orePetrifiedWood, true),
		FOSSIL(ModBlocks.oreFossil, true),
		ALUMINIUM(ModBlocks.oreAluminium, false),
		COPPER(ModBlocks.oreCopper, false),
		LEAD(ModBlocks.oreLead, false),
		SILVER(ModBlocks.oreSilver, false),
		TIN(ModBlocks.oreTin, false),
		GNEISS(ModBlocks.oreGneiss, true);

		final Block oreBlock;
		final byte oreMeta;
		boolean isEnabled;

		OreType(Block oreBlock, int oreMeta, boolean isEnabled) {
			this.oreBlock = oreBlock;
			this.oreMeta = (byte) oreMeta;
			this.isEnabled = isEnabled;
		}

		OreType(Block oreBlock, boolean isEnabled) {
			this(oreBlock, 0, isEnabled);
		}

		public boolean isEnabled() {
			return isEnabled;
		}

		public void setEnabled(boolean flag) {
			isEnabled = flag;
		}

		@SuppressWarnings("incomplete-switch")
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
				case DIAMOND_ENCRUSTED:
					settings.setChance(0F);
					break;
				case JADE:
					settings.setChance(0.5F).setIterations(1, 4).setOreAmount(4).setCheckArea(2);
					break;
				case PETRIFIED_WOOD:
					settings.setIterations(extraOres ? 1 : 2, extraOres ? 3 : 4).setOreAmount(7, 9).setCheckArea(2);
					break;
				case FOSSIL:
					settings.setChance(0.25F).setIterations(1, 2).setOreAmount(8, 11).setY(36, 112);
					break;
				case ALUMINIUM:
					settings.setChance(1F).setIterations(2, 3).setOreAmount(3, 4).setCheckArea(2);
					break;
				case COPPER:
					settings.setChance(1F).setIterations(7, 9).setOreAmount(5, 7);
					break;
				case LEAD:
					settings.setChance(1F).setIterations(4).setOreAmount(3).setCheckArea(2);
					break;
				case SILVER:
					settings.setChance(1F).setIterations(5).setOreAmount(6, 8);
					break;
				case TIN:
					settings.setChance(1F).setIterations(2, 4).setOreAmount(3, 4).setCheckArea(2);
					break;
			}
		}
	}
}