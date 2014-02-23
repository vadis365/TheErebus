package erebus.world.biomes;
import java.util.Random;
import net.minecraft.world.World;

public class BiomeUlteriorOutback extends BiomeBaseErebus{
	public BiomeUlteriorOutback(int biomeID){
		super(biomeID);
		
		setBiomeName("Ulterior Outback");
		setColors(0xEEAA55);
		setFog(234,194,114);
		setTemperatureRainfall(1.1F,0.2F);
		setWeight(15);
	}

	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){}
}
