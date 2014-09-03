package erebus.world;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.IMob;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import erebus.core.handler.configs.ConfigHandler;
import erebus.world.biomes.BiomeBaseErebus;
import erebus.world.loot.IWeightProvider;

public final class SpawnerErebus{
	public static final SpawnerErebus INSTANCE = new SpawnerErebus();
	
	public static void onChunkPopulate(World world, Random rand, BiomeBaseErebus biome, int x, int z){
		if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doMobSpawning"))INSTANCE.runPopulationSpawning((WorldServer)world,rand,biome,x,z);
	}
	
	@SubscribeEvent
	public void onServerTick(ServerTickEvent e){
		if (e.phase != Phase.START)return;
		
		WorldServer erebusWorld = DimensionManager.getWorld(ConfigHandler.INSTANCE.erebusDimensionID);
		if (erebusWorld != null && erebusWorld.getGameRules().getGameRuleBooleanValue("doMobSpawning"))runGradualSpawning(erebusWorld);
	}
	
	private boolean canSpawnHostiles;
	private boolean canSpawnAnimals;
	
	private void prepare(WorldServer world){
		WorldProviderErebus provider = (WorldProviderErebus)world.provider;
		canSpawnHostiles = provider.getCanSpawnHostiles();
		canSpawnAnimals = provider.getCanSpawnAnimals();
	}
	
	private void runGradualSpawning(WorldServer world){
		prepare(world);
		
	}
	
	private void runPopulationSpawning(WorldServer world, Random rand, BiomeBaseErebus biome, int x, int z){
		prepare(world);
		
	}
	
	private SpawnerErebus(){}
	
	public static final class SpawnEntry implements IWeightProvider{
		protected final Class<? extends EntityLiving> mobClass;
		protected final short weight;
		protected final boolean isHostile;
		protected byte minGroupSize,maxGroupSize;
		protected int worldLimit;
		protected Block blockBelow = null;
		
		public SpawnEntry(Class<? extends EntityLiving> mobClass, int weight){
			this.mobClass = mobClass;
			this.weight = (short)weight;
			this.isHostile = IMob.class.isAssignableFrom(mobClass);
		}
		
		public SpawnEntry setGroupSize(int minGroupSize, int maxGroupSize){
			this.minGroupSize = (byte)minGroupSize;
			this.maxGroupSize = (byte)maxGroupSize;
			return this;
		}
		
		public SpawnEntry setWorldLimit(int mobAmountLimit){
			this.worldLimit = mobAmountLimit;
			return this;
		}
		
		/**
		 * Defaults to null = any solid block
		 */
		public SpawnEntry setBlockBelow(Block block){
			this.blockBelow = block;
			return this;
		}

		@Override
		public short getWeight(){
			return weight;
		}
	}
}
