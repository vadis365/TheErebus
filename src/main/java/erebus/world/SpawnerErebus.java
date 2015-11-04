package erebus.world;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import erebus.core.handler.configs.ConfigHandler;
import erebus.world.biomes.BiomeBaseErebus;
import erebus.world.loot.IWeightProvider;
import gnu.trove.map.hash.TObjectIntHashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;

public final class SpawnerErebus {
	public static final SpawnerErebus INSTANCE = new SpawnerErebus();
	public static final int MAX_MOBS_PER_WORLD = 300;

	public static void onChunkPopulate(World world, Random rand, BiomeBaseErebus biome, int x, int z) {
		if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doMobSpawning"))
			INSTANCE.runPopulationSpawning((WorldServer) world, rand, biome, x, z);
	}

	@SubscribeEvent
	public void onServerTick(ServerTickEvent e) {
		if (e.phase != Phase.START)
			return;

		WorldServer erebusWorld = DimensionManager.getWorld(ConfigHandler.INSTANCE.erebusDimensionID);
		if (erebusWorld != null && erebusWorld.getGameRules().getGameRuleBooleanValue("doMobSpawning"))
			runGradualSpawning(erebusWorld);
	}

	private boolean canSpawnHostiles;
	private boolean canSpawnAnimals;
	private Map<ChunkCoordIntPair, Boolean> spawnChunks = new HashMap<ChunkCoordIntPair, Boolean>(64);

	private void prepare(WorldServer world) {
		WorldProviderErebus provider = (WorldProviderErebus) world.provider;
		canSpawnHostiles = provider.getCanSpawnHostiles();
		canSpawnAnimals = provider.getCanSpawnAnimals();
	}

	@SuppressWarnings("unchecked")
	private void runGradualSpawning(WorldServer world) {
		prepare(world);

		if (!canSpawnHostiles && !canSpawnAnimals)
			return;

		spawnChunks.clear();

		List<EntityPlayer> players = world.playerEntities;
		int chunkX, chunkZ, px, pz;
		byte dist = 8;

		for (EntityPlayer player : players) {
			chunkX = player.chunkCoordX;
			chunkZ = player.chunkCoordZ;

			for (px = -dist; px <= dist; px++)
				for (pz = -dist; pz <= dist; pz++) {
					ChunkCoordIntPair coords = new ChunkCoordIntPair(chunkX + px, chunkZ + pz);

					if (px == -dist || px == dist || pz == -dist || pz == dist || Math.abs(px) <= 1 || Math.abs(pz) <= 1) {
						if (!spawnChunks.containsKey(coords))
							spawnChunks.put(coords, Boolean.valueOf(false));
					} else
						spawnChunks.put(coords, Boolean.valueOf(true));
				}
		}

		if (spawnChunks.isEmpty())
			return;

		TObjectIntHashMap<Class<? extends Entity>> entityCount = new TObjectIntHashMap<Class<? extends Entity>>();

		for (Object entity : world.loadedEntityList)
			if (entity instanceof EntityLiving)
				entityCount.adjustOrPutValue(((Entity) entity).getClass(), 1, 1);

		int totalAmount = 0;
		for (int amt : entityCount.values())
			totalAmount += amt;

		if (totalAmount >= Math.min(spawnChunks.size() >> 1, MAX_MOBS_PER_WORLD) / (world.difficultySetting == EnumDifficulty.PEACEFUL ? 2 : 1))
			return;

		List<ChunkCoordIntPair> chunksToTest = new ArrayList<ChunkCoordIntPair>();

		for (Entry<ChunkCoordIntPair, Boolean> entry : spawnChunks.entrySet())
			if (entry.getValue())
				chunksToTest.add(entry.getKey());

		Random rand = world.rand;
		int x, y, z, spawned, spawnGroup, attempts, posAttempts, maxPosAttempts, testedChunks = 3 + rand.nextInt(1 + 2 * world.difficultySetting.getDifficultyId());
		float fx, fy, fz, yaw = 0F;
		boolean continueSpawning, coordsFinal;

		Collections.shuffle(chunksToTest, rand);

		for (ChunkCoordIntPair coords : chunksToTest) {
			spawned = attempts = 0;

			while (attempts < 4 && spawned < 2) {
				x = coords.chunkXPos * 16 + rand.nextInt(16);
				z = coords.chunkZPos * 16 + rand.nextInt(16);
				y = 10 + rand.nextInt(100);

				BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
				if (!(biome instanceof BiomeBaseErebus))
					break;

				SpawnEntry entry = ((BiomeBaseErebus) biome).getRandomSpawnGradual(rand);
				if (entry == null)
					break;

				++attempts;

				if (entry.isHostile && !canSpawnHostiles || !entry.isHostile && !canSpawnAnimals)
					continue;
				if (rand.nextFloat() > biome.getSpawningChance() || entry.worldLimit != -1 && entityCount.get(entry.mobClass) >= entry.worldLimit)
					continue;

				EntityLiving entity = null;

				spawnGroup = entry.minGroupSize + rand.nextInt(entry.maxGroupSize - entry.minGroupSize + 1);
				continueSpawning = true;
				coordsFinal = false;
				maxPosAttempts = 20 + spawnGroup * 2;

				while (continueSpawning) {
					for (posAttempts = 0; posAttempts < maxPosAttempts; posAttempts++) {
						fx = x + rand.nextInt(12) - 6 + 0.5F;
						fy = y + rand.nextInt(2) - 1;
						fz = z + rand.nextInt(12) - 6 + 0.5F;

						if ((entry.blockBelow == null && world.getBlock((int) fx, (int) fy - 1, (int) fz).isNormalCube() || world.getBlock((int) fx, (int) fy - 1, (int) fz) == entry.blockBelow) && world.getClosestPlayer(fx, fy, fz, 24D) == null) {
							if (!coordsFinal) {
								coordsFinal = true;
								posAttempts = 10;
							}

							if (entity == null) {
								entity = entry.createEntity(world);
								yaw = rand.nextFloat() * 360F;
							}

							if (entity == null) {
								continueSpawning = false;
								--spawned;
								break;
							}

							entity.setLocationAndAngles(fx, fy, fz, yaw, 0F);

							if (entity.getCanSpawnHere()) {
								world.spawnEntityInWorld(entity);
								entity = null;

								if (--spawnGroup <= 0)
									continueSpawning = false;
								break;
							}
						}

						if (!coordsFinal) {
							x = coords.chunkXPos * 16 + rand.nextInt(16);
							z = coords.chunkZPos * 16 + rand.nextInt(16);
							y = 20 + rand.nextInt(80);
						}

						if (posAttempts == maxPosAttempts - 1)
							continueSpawning = false;
					}

					++spawned;
				}
			}

			if (--testedChunks <= 0)
				break;
		}
	}

	private void runPopulationSpawning(WorldServer world, Random rand, BiomeBaseErebus biome, int x, int z) {
		prepare(world);
		// TODO maybe I'll finish this one sometime...
	}

	private SpawnerErebus() {
	}

	public static final class SpawnEntry implements IWeightProvider {
		private final Constructor<? extends EntityLiving> mobConstructor;
		protected final Class<? extends EntityLiving> mobClass;
		protected final short weight;
		protected final boolean isHostile;
		protected byte minGroupSize = 1, maxGroupSize = 1;
		protected int worldLimit = 10;
		protected Block blockBelow = null;

		public SpawnEntry(Class<? extends EntityLiving> mobClass, int weight) {
			this.mobClass = mobClass;
			this.weight = (short) weight;
			isHostile = IMob.class.isAssignableFrom(mobClass);

			try {
				mobConstructor = mobClass.getConstructor(World.class);
			} catch (Exception e) {
				throw new RuntimeException("Could not find constructor (World) of mob " + mobClass.getSimpleName(), e);
			}
		}

		/**
		 * The spawner will attempt to spawn the mob in a group (it is not guaranteed to spawn minGroupSize mobs, but it will go for a number between that and maxGroupSize)
		 */
		public SpawnEntry setGroupSize(int minGroupSize, int maxGroupSize) {
			this.minGroupSize = (byte) minGroupSize;
			this.maxGroupSize = (byte) maxGroupSize;
			return this;
		}

		/**
		 * Maximum amount of mobs of this type per world
		 */
		public SpawnEntry setWorldLimit(int mobAmountLimit) {
			worldLimit = mobAmountLimit;
			return this;
		}

		/**
		 * Defaults to null = any solid block
		 */
		public SpawnEntry setBlockBelow(Block block) {
			blockBelow = block;
			return this;
		}

		@Override
		public short getWeight() {
			return weight;
		}

		public EntityLiving createEntity(World world) {
			try {
				return mobConstructor.newInstance(world);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
