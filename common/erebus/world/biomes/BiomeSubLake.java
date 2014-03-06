package erebus.world.biomes;
import java.util.Random;
import net.minecraft.world.World;

//@formatter:off
public class BiomeSubLake extends BiomeUndergroundJungle{
	public BiomeSubLake(int biomeID){
		super(biomeID);
		
		setBiomeName("Underground Jungle - Lake");
	}

	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){
		
	}
}
//@formatter:on