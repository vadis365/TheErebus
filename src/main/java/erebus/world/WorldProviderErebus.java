package erebus.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.handler.configs.ConfigHandler;
import erebus.world.biomes.BiomeBaseErebus;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderErebus extends WorldProvider {

	@SideOnly(Side.CLIENT)
	private double[] currentFogColor;
	@SideOnly(Side.CLIENT)
	private short[] targetFogColor;

	private boolean allowHostiles, allowAnimals;

	@Override
	public boolean canRespawnHere() {
		if (ConfigHandler.INSTANCE.allowRespawning)
			return true;
		return false;
	}

	@Override
	public boolean canCoordinateBeSpawn(int x, int z) {
		return this.worldObj.getTopBlock(x, z) != Blocks.bedrock && this.worldObj.getTopBlock(x, z) != Blocks.air;
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTickTime) {
		return 0.5F;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Vec3 getFogColor(float celestialAngle, float partialTickTime) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		BiomeGenBase biome = worldObj.getBiomeGenForCoords((int) player.posX, (int) player.posZ);
		if (biome instanceof BiomeBaseErebus)
			targetFogColor = ((BiomeBaseErebus) biome).getFogRGB();
		else
			targetFogColor = new short[] { 255, 255, 255 };

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

		return Vec3.createVectorHelper(currentFogColor[0] / 255D, currentFogColor[1] / 255D, currentFogColor[2] / 255D);
	}

	@Override
	protected void generateLightBrightnessTable() {
		float f = 0.1F;

		for (int i = 0; i <= 15; i++) {
			float f1 = 1F - i / 15F;
			lightBrightnessTable[i] = (1F - f1) / (f1 * 3F + 1F) * (1F - f) + f;
		}
	}

	@Override
	public void registerWorldChunkManager() {
		worldChunkMgr = new WorldChunkManagerErebus(worldObj);
		hasNoSky = true;
		dimensionId = ConfigHandler.INSTANCE.erebusDimensionID;
	}

	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderErebus(worldObj, worldObj.getSeed());
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
	public String getDimensionName() {
		return "Erebus";
	}

	@Override
	public ChunkCoordinates getRandomizedSpawnPoint() {
		ChunkCoordinates chunkcoordinates = new ChunkCoordinates(worldObj.getSpawnPoint());

		boolean isAdventure = worldObj.getWorldInfo().getGameType() == GameType.ADVENTURE;
		int spawnFuzz = 100;
		int spawnFuzzHalf = spawnFuzz / 2;

		if (!hasNoSky && !isAdventure) {
			chunkcoordinates.posX += worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
			chunkcoordinates.posZ += worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
			chunkcoordinates.posY = worldObj.getTopSolidOrLiquidBlock(chunkcoordinates.posX, chunkcoordinates.posZ);
		}

		return chunkcoordinates;
	}
}