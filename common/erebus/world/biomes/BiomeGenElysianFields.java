package erebus.world.biomes;
import java.util.Random;
import net.minecraft.world.World;

//@formatter:off
public class BiomeGenElysianFields extends BiomeGenBaseErebus{
	public BiomeGenElysianFields(int biomeID){
		super(biomeID);
		
		setBiomeName("Elysian Fields");
		setColors(0x7FA735);
		setTemperatureRainfall(0.85F, 0.5F);
		setWeight(20);
	}

	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){
		
	}
}
//@formatter:on