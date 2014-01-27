package erebus.world;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBiomes;
import erebus.core.handler.ConfigurationHandler;

public class WorldProviderErebus extends WorldProvider {

	@SideOnly(Side.CLIENT)
	private double[] currentFogColor, targetFogColor;

	public WorldProviderErebus() {
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public boolean canCoordinateBeSpawn(int x, int z) {
		return true;
	}

	@Override
	public float calculateCelestialAngle(long par1, float par3) {
		return 0.5F;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Vec3 getFogColor(float par1, float par2) {
		BiomeGenBase b = worldObj.getBiomeGenForCoords((int) Minecraft.getMinecraft().thePlayer.posX, (int) Minecraft.getMinecraft().thePlayer.posZ);

		if (targetFogColor == null)
			targetFogColor = new double[3];
		targetFogColor[0] = 0.029999999329447746D * 255D;
		targetFogColor[1] = 0.49999999329447746D * 255D;
		targetFogColor[2] = 0.029999999329447746D * 255D;

		if (b.biomeID == ModBiomes.savannahID) {
			targetFogColor[0] = 140D;
			targetFogColor[1] = 116D;
			targetFogColor[2] = 9D;
		} else if (b.biomeID == ModBiomes.desertID) {
			targetFogColor[0] = 255D;
			targetFogColor[1] = 231D;
			targetFogColor[2] = 10D;
		} else if (b.biomeID == ModBiomes.cavernID) {
			targetFogColor[0] = 100D;
			targetFogColor[1] = 100D;
			targetFogColor[2] = 100D;
		}

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

		return worldObj.getWorldVec3Pool().getVecFromPool(currentFogColor[0] / 255D, currentFogColor[1] / 255D, currentFogColor[2] / 255D);
	}

	@Override
	protected void generateLightBrightnessTable() {
		float f = 0.1F;

		for (int i = 0; i <= 15; i++) {
			float f1 = 1.0F - i / 15F;
			lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3F + 1.0F) * (1.0F - f) + f;
		}
	}

	@Override
	public void registerWorldChunkManager() {
		worldChunkMgr = new WorldChunkManagerErebus(1.0F, 0.0F, worldObj);
		hasNoSky = true;
		dimensionId = ConfigurationHandler.erebusDimensionID;
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
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int par1, int par2) {
		return false;
	}

	@Override
	public String getDimensionName() {
		return "Erebus";
	}

	@Override
	public ChunkCoordinates getRandomizedSpawnPoint() {
		ChunkCoordinates chunkcoordinates = new ChunkCoordinates(worldObj.getSpawnPoint());

		boolean isAdventure = worldObj.getWorldInfo().getGameType() == EnumGameType.ADVENTURE;
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