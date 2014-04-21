package erebus.world.biomes;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityDragonfly;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityMosquito;
import erebus.world.biomes.decorators.BiomeDecoratorEmpty;
import erebus.world.biomes.decorators.type.FeatureType;
import erebus.world.biomes.decorators.type.OreType;

// @formatter:off
public class BiomeBetweenlands extends BiomeBaseErebus{
	public BiomeBetweenlands(int biomeID){
		super(biomeID,new BiomeDecoratorEmpty());
		
		setBiomeName("Betweenlands");
		setColors(0x314D31);
		setTemperatureRainfall(0.75F, 0.85F);
		setWeight(15);
		
		spawnableMonsterList.add(new SpawnEntry(EntityCentipede.class,10,4,8));
		spawnableMonsterList.add(new SpawnEntry(EntityJumpingSpider.class,10,2,6));
		
		spawnableCaveCreatureList.add(new SpawnEntry(EntityMosquito.class,20,1,2));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityDragonfly.class,20,1,2));
	}

	/*@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){}
	
	@Override
	public void generateFeature(World world, Random rand, int x, int z, FeatureType featureType){
		if (featureType != FeatureType.REDGEM)generateFeature(world,rand,x,z,featureType);
	}
	
	@Override
	public void generateOre(World world, Random rand, int x, int z, OreType oreType, boolean extraOres){
		if (oreType == OreType.FOSSIL){
			if (rand.nextInt(7)==0)generateOreCluster(1+rand.nextInt(2)*rand.nextInt(2),ModBlocks.oreFossil,3,9,12,world,rand,x,z,36,112,3);
		}
		else super.generateOre(world,rand,x,z,oreType,extraOres);
	}*/
	
	@Override
	public byte placeCaveBlock(byte blockID, int x, int y, int z, Random rand){
		return blockID == (byte)ModBlocks.umberstone.blockID || blockID == topBlock || blockID == fillerBlock || blockID == Block.sandStone.blockID ? (y < 20 ? (byte)Block.waterMoving.blockID : 0) : blockID;
	}
}
//@formatter:on