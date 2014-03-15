package erebus.world.genlayer;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

public abstract class GenLayerErebus extends GenLayer{

	private long worldGenSeed;
	protected GenLayer parent;
	private long chunkSeed;
	private long baseSeed;

	public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType){
		byte biomeSize = getModdedBiomeSize(worldType,(byte)(worldType == WorldType.LARGE_BIOMES?7:5));

		GenLayer genLayer = new GenLayerIsland(1L);
		genLayer = new GenLayerFuzzyZoom(2000L,genLayer);
		genLayer = new GenLayerBiomes(100L,genLayer);
		genLayer = GenLayerZoom.magnify(2000L,genLayer,1);
		genLayer = new GenLayerSubBiomes(101L,genLayer);
		genLayer = GenLayerZoom.magnify(2100L,genLayer,biomeSize);
		genLayer = new GenLayerVoronoiZoom(10L,genLayer);
		genLayer.initWorldGenSeed(seed);
		return new GenLayer[]{null, genLayer, null};
	}

	public GenLayerErebus(long seed){
		super(seed);
	}

	@Override
	public abstract int[] getInts(int x, int z, int sizeX, int sizeZ);

	public static byte getModdedBiomeSize(WorldType worldType, byte original){
		WorldTypeEvent.BiomeSize event = new WorldTypeEvent.BiomeSize(worldType,original);
		MinecraftForge.TERRAIN_GEN_BUS.post(event);
		return event.newSize;
	}
}
