package erebus.world.biomes.decorators;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.WorldGenLakes;
import erebus.ModBlocks;
import erebus.world.biomes.decorators.type.FeatureType;
import erebus.world.feature.decoration.WorldGenScorchedWood;
import erebus.world.feature.structure.WorldGenAntlionLair;

public class BiomeDecoratorVolcanicDesert extends BiomeDecoratorBaseErebus{
	private final WorldGenAntlionLair genAntlionLair = new WorldGenAntlionLair();
	private final WorldGenLakes genLavaLakes = new WorldGenLakes(Block.lavaMoving.blockID);
	private final WorldGenScorchedWood genScorchedWood = new WorldGenScorchedWood();
	
	@Override
	public void populate(){
		for(attempt = 0; attempt < 35; attempt++){
			xx = x+offsetXZ();
			yy = 15+rand.nextInt(90);
			zz = z+offsetXZ();
			
			if (world.getBlockId(xx,yy-1,zz) == Block.sand.blockID && world.isAirBlock(xx,yy,zz)){
				genLavaLakes.generate(world,world.rand,xx,yy,zz);
			}
		}
	}
	
	@Override
	public void decorate(){
		for(attempt = 0; attempt < 10; attempt++){
			xx = x+offsetXZ();
			yy = rand.nextInt(120);
			zz = z+offsetXZ();
			
			if (world.getBlockId(xx,yy,zz) == ModBlocks.umberstone.blockID && world.isAirBlock(xx,yy-1,zz)){
				world.setBlock(xx,yy,zz,Block.lavaMoving.blockID);
				world.scheduledUpdatesAreImmediate = true;
				Block.blocksList[Block.lavaMoving.blockID].updateTick(world,xx,yy,zz,rand);
				world.scheduledUpdatesAreImmediate = false;
			}
		}

		for(attempt = 0; attempt < 22; attempt++){
			xx = x+offsetXZ();
			yy = rand.nextInt(120);
			zz = z+offsetXZ();
			
			if (world.getBlockId(xx,yy-1,zz) == Block.sand.blockID && world.isAirBlock(xx,yy,zz) && !world.isAirBlock(xx,yy-2,zz)){
				genScorchedWood.generate(world,rand,xx,yy,zz);
				if (rand.nextInt(4)!=0)break;
			}
		}

		if (rand.nextInt(34) == 0){
			for(int attempt = 0; attempt < 15; attempt++){
				if (genAntlionLair.generate(world,rand,x+5+rand.nextInt(6)+8,15+rand.nextInt(35),z+5+rand.nextInt(6)+8))break;
			}
		}
	}
	
	@Override
	public void generateFeature(FeatureType featureType){
		if (featureType == FeatureType.REDGEM){
			for(attempt = 0; attempt < 10; attempt++){
				genRedGem.generate(world,rand,x+offsetXZ(),rand.nextInt(64),z+offsetXZ());
			}
		}
		else super.generateFeature(featureType);
	}
	
	/*@Override
	public void generateOre(OreType oreType, boolean extraOres){
		if (oreType == OreType.PETRIFIED_WOOD){
			generateOreCluster((extraOres?3:4)+rand.nextInt(2),ModBlocks.umberOreBlock,BlockErebusOre.dataPetrifiedWood,8,9,world,rand,x,z,6,112,2);
		}
		else if (oreType == OreType.FOSSIL){
			if (rand.nextInt(6) == 0)generateOreCluster(1+rand.nextInt(2)*rand.nextInt(2),ModBlocks.oreFossil,3,8,11,world,rand,x,z,36,112,3);
		}
		else super.generateOre(oreType,extraOres);
	}*/
}
