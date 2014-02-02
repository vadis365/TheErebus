package erebus.world.biomes;
import java.util.Random;
import net.minecraft.world.World;

//@formatter:off
public class BiomeGenElysianFields extends BiomeGenBaseErebus{
	public BiomeGenElysianFields(int biomeID){
		super(biomeID);
	}

	@Override
	protected short getBiomeWeight(){
		return 50;
	}

	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){
		
	}
}
//@formatter:on