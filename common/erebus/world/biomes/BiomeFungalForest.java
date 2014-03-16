package erebus.world.biomes;
import java.util.Random;
import net.minecraft.world.World;

// @formatter:off
public class BiomeFungalForest extends BiomeBaseErebus{
	public BiomeFungalForest(int biomeID){
		super(biomeID);
		
		setBiomeName("Fungal Forest");
		setColors(0x4E8833);
		setTemperatureRainfall(0.9F, 0.95F);
		setWeight(12);
	}
	
	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){}
}
//@formatter:on