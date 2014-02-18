package erebus.world.biomes;
import java.util.Random;
import net.minecraft.world.World;

public class BiomeGenUlteriorOutback extends BiomeGenBaseErebus{
	public BiomeGenUlteriorOutback(int biomeID){
		super(biomeID);
		
		setBiomeName("Ulterior Outback");
		setWeight(15);
	}

	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){}
}
