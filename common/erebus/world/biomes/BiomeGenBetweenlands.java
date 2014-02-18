package erebus.world.biomes;
import java.util.Random;
import net.minecraft.world.World;

public class BiomeGenBetweenlands extends BiomeGenBaseErebus{
	public BiomeGenBetweenlands(int biomeID){
		super(biomeID);
		
		setBiomeName("Betweenlands");
		setColors(0x314D31);
		setTemperatureRainfall(0.75F, 0.85F);
		setWeight(15);
	}

	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){}
}
