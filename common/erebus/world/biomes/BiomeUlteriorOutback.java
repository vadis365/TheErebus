package erebus.world.biomes;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityDragonfly;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityMosquito;
import erebus.world.feature.tree.WorldGenEucalyptusTree;

// @formatter:off
public class BiomeUlteriorOutback extends BiomeBaseErebus{
	public BiomeUlteriorOutback(int biomeID){
		super(biomeID);
		
		setBiomeName("Ulterior Outback");
		setColors(0xEEAA55);
		setFog(234,194,114);
		setTemperatureRainfall(1.1F,0.2F);
		setWeight(15);
		
		spawnableMonsterList.add(new SpawnEntry(EntityCentipede.class,10,4,8));
		spawnableMonsterList.add(new SpawnEntry(EntityJumpingSpider.class,10,2,6));
		
		spawnableCaveCreatureList.add(new SpawnEntry(EntityMosquito.class,20,1,2));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityDragonfly.class,20,1,2));
		
		topBlock = (byte)Block.sand.blockID;
	}

	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){
		for(int attempt = 0, xx, zz; attempt < 112; attempt++){
			xx = x+getRandomXZOffset(rand);
			zz = z+getRandomXZOffset(rand);
			
			for(int yy = 20; yy < 100; yy+=rand.nextInt(2)+1){
				if (world.getBlockId(xx,yy,zz) == Block.sand.blockID && world.isAirBlock(xx,yy+1,zz)){
					world.setBlock(xx,yy,zz,Block.grass.blockID);
					break;
				}
			}
		}
		
		for(int attempt = 0, xx, zz, id; attempt < 164; attempt++){
			xx = x+getRandomXZOffset(rand);
			zz = z+getRandomXZOffset(rand);
			
			for(int yy = 20; yy < 100; yy+=rand.nextInt(2)+1){
				if (world.isAirBlock(xx,yy,zz) && (((id = world.getBlockId(xx,yy-1,zz)) == Block.sand.blockID) || id == Block.grass.blockID)){
					world.setBlock(xx,yy,zz,ModBlocks.erebusGrass.blockID,1,2);
					break;
				}
			}
		}
		
		if (rand.nextBoolean()){
			for(int attempt = 0, xx, yy, zz; attempt < 180; attempt++){
				xx = x+getRandomXZOffset(rand);
				yy = 20 + rand.nextInt(80);
				zz = z+getRandomXZOffset(rand);
				
				if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz) == Block.grass.blockID){
					if (new WorldGenEucalyptusTree().generate(world,rand,xx,yy,zz) && rand.nextBoolean())break;
				}
			}
		}
	}
}
// @formatter:on
