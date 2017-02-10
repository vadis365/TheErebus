package erebus.world;

import erebus.Erebus;
import erebus.core.handler.configs.ConfigHandler;
import erebus.world.biomes.BiomeBaseErebus;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.GameType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderErebus extends WorldProvider {

	@SideOnly(Side.CLIENT)
	private double[] currentFogColor;
	@SideOnly(Side.CLIENT)
	private short[] targetFogColor;

	private boolean allowHostiles, allowAnimals;
	

	
	@Override
	public boolean canRespawnHere() {
		return ConfigHandler.INSTANCE.allowRespawning;
	}

	@Override
	public boolean canCoordinateBeSpawn(int x, int z) {
		return getGroundAvailableUp(new BlockPos(x, 0, z)) != Blocks.BEDROCK;
	}
	
    public Block getGroundAvailableUp(BlockPos pos) {
        BlockPos blockpos1;
	    for (blockpos1 = new BlockPos(pos.getX(), 20, pos.getZ()); !this.world.isAirBlock(blockpos1.up()); blockpos1 = blockpos1.up())
		    ;
	    return this.world.getBlockState(blockpos1).getBlock();
    }

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTickTime) {
		return 0.5F;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Vec3d getFogColor(float celestialAngle, float partialTickTime) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		Biome biome = world.getBiome(new BlockPos(player.posX, player.posY, player.posZ));
		if (biome instanceof BiomeBaseErebus)
			targetFogColor = ((BiomeBaseErebus) biome).getFogRGB();
		else
			targetFogColor = new short[]{255, 255, 255};

		if (currentFogColor == null) {
			currentFogColor = new double[3];
			for (int a = 0; a < 3; a++)
				currentFogColor[a] = targetFogColor[a];
		}

		for (int a = 0; a < 3; a++)
			if (currentFogColor[a] != targetFogColor[a])
				if (currentFogColor[a] < targetFogColor[a]) {
					currentFogColor[a] += 2D;
					if (currentFogColor[a] > targetFogColor[a])
						currentFogColor[a] = targetFogColor[a];
				} else if (currentFogColor[a] > targetFogColor[a]) {
					currentFogColor[a] -= 2D;
					if (currentFogColor[a] < targetFogColor[a])
						currentFogColor[a] = targetFogColor[a];
				}

		return new Vec3d(currentFogColor[0] / 255D, currentFogColor[1] / 255D, currentFogColor[2] / 255D);
	}

	@Override
	protected void generateLightBrightnessTable() {
		float f = 0.1F;

		for (int i = 0; i <= 15; i++) {
			float f1 = 1F - i / 15F;
			lightBrightnessTable[i] = (1F - f1) / (f1 * 3F + 1F) * (1F - f) + f;
		}
	}

	/*@Override
	public void createBiomeProvider() {
		setDimension(ConfigHandler.INSTANCE.erebusDimensionID);
		biomeProvider = new BiomeProviderErebus(world);
		hasNoSky = true;
	}*/

	@Override
	public DimensionType getDimensionType() {
		return Erebus.dimensionType;
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkProviderErebus(world, world.getSeed());
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	@Override
    public int getActualHeight() {
        return 128;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int x, int z) {
		return false;
	}

	@Override
	public void setAllowedSpawnTypes(boolean allowHostiles, boolean allowAnimals) {
		this.allowHostiles = allowHostiles;
		this.allowAnimals = allowAnimals;
		super.setAllowedSpawnTypes(allowHostiles, allowAnimals);
	}

	public boolean getCanSpawnHostiles() {
		return allowHostiles;
	}

	public boolean getCanSpawnAnimals() {
		return allowAnimals;
	}

	@Override
    public BlockPos getRandomizedSpawnPoint() {
		BlockPos ret = this.world.getSpawnPoint();
		boolean isAdventure = world.getWorldInfo().getGameType() == GameType.ADVENTURE;
		int spawnFuzz = 100;
		int border = MathHelper.floor(world.getWorldBorder().getClosestDistance(ret.getX(), ret.getZ()));
		if (border < spawnFuzz) spawnFuzz = border;
        if (spawnFuzz < 1) spawnFuzz = 1;
        int spawnFuzzHalf = spawnFuzz / 2;

		if (!hasNoSky() && !isAdventure)
			ret = world.getTopSolidOrLiquidBlock(ret.add(world.rand.nextInt(spawnFuzzHalf) - spawnFuzz, 0, world.rand.nextInt(spawnFuzzHalf) - spawnFuzz));

        return ret;
    }
}