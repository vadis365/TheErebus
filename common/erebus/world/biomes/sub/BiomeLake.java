package erebus.world.biomes.sub;
import java.util.Random;
import erebus.world.biomes.BiomeUndergroundJungle;
import net.minecraft.world.World;

//@formatter:off
public class BiomeLake extends BiomeUndergroundJungle{
	public BiomeLake(int biomeID){
		super(biomeID);
		
		setBiomeName("Underground Jungle - Lake");
	}

	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){
		
	}
}
//@formatter:on