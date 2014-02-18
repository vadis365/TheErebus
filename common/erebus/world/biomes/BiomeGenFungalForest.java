package erebus.world.biomes;
import java.util.Random;
import net.minecraft.world.World;

public class BiomeGenFungalForest extends BiomeGenBaseErebus{
	public BiomeGenFungalForest(int biomeID){
		super(biomeID);
		
		setBiomeName("Fungal Forest");
		setColors(0x4E8833);
		setTemperatureRainfall(0.9F, 0.95F);
		setWeight(10);
	}
	
	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){}
}
