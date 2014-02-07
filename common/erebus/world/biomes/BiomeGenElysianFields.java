package erebus.world.biomes;
import java.util.Random;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor(){
		return 8365877;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeFoliageColor(){
		return 8365877;
	}

	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){
		
	}
}
//@formatter:on