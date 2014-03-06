package erebus.world.biomes;
import java.util.Random;
import net.minecraft.world.World;

// @formatter:off
public class BiomeElysianFields extends BiomeBaseErebus{
	public BiomeElysianFields(int biomeID){
		super(biomeID);
		
		setBiomeName("Elysian Fields");
		setColors(0xC6FF54);
		setTemperatureRainfall(0.85F, 0.5F);
		setWeight(20);
	}

	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){
		
	}
}
// @formatter:on