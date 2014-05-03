package erebus.world.biomes.decorators;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.world.biomes.decorators.type.FeatureType;
import erebus.world.biomes.decorators.type.OreSettings;
import erebus.world.biomes.decorators.type.OreSettings.OreType;
import erebus.world.feature.tree.WorldGenAcaciaTree;
import erebus.world.feature.tree.WorldGenEucalyptusTree;

public class BiomeDecoratorUlteriorOutback extends BiomeDecoratorBaseErebus{
	private final WorldGenerator genTreeAcacia = new WorldGenAcaciaTree();
	private final WorldGenerator genTreeEucalyptus = new WorldGenEucalyptusTree();
	
	@Override
	public void decorate(){
		for(attempt = 0; attempt < 112; attempt++){
			xx = x+offsetXZ();
			zz = z+offsetXZ();

			for(yy = 20; yy < 100; yy += rand.nextInt(2)+1){
				if (world.getBlockId(xx,yy,zz) == Block.sand.blockID && world.isAirBlock(xx,yy+1,zz)){
					world.setBlock(xx,yy,zz,Block.grass.blockID);
					break;
				}
			}
		}
		
		if (rand.nextBoolean()){
			for(attempt = 0; attempt < 20; attempt++){
				xx = x+offsetXZ();
				yy = 20+rand.nextInt(80);
				zz = z+offsetXZ();
				
				if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz) == Block.grass.blockID){
					genTreeAcacia.generate(world,rand,xx,yy,zz);
					if (rand.nextBoolean())break;
				}
			}
		}
		
		for(attempt = 0; attempt < 420; attempt++){
			xx = x+offsetXZ();
			yy = 20+rand.nextInt(80);
			zz = z+offsetXZ();

			if (world.getBlockId(xx,yy-1,zz) == Block.sand.blockID && world.isAirBlock(xx,yy,zz) && world.isAirBlock(xx,yy+1,zz)){
				world.setBlock(xx,yy,zz,ModBlocks.doubleHeightPlant.blockID,3,2);
				world.setBlock(xx,yy+1,zz,ModBlocks.doubleHeightPlant.blockID,3+8,2);
			}
		}

		int id;
		for(attempt = 0; attempt < 194; attempt++){
			xx = x + offsetXZ();
			zz = z + offsetXZ();

			for(yy = 20; yy < 100; yy += rand.nextBoolean() ? 2 : 1){
				if ((((id = world.getBlockId(xx,yy-1,zz)) == Block.sand.blockID) || id == Block.grass.blockID) && world.isAirBlock(xx,yy,zz)){
					if (rand.nextInt(10) == 0 && world.isAirBlock(xx,yy+1,zz)){
						world.setBlock(xx,yy,zz,ModBlocks.doubleHeightPlant.blockID,4,2);
						world.setBlock(xx,yy+1,zz,ModBlocks.doubleHeightPlant.blockID,4+8,2);
					}
					else world.setBlock(xx,yy,zz,ModBlocks.erebusGrass.blockID,1,2);
					
					break;
				}
			}
		}

		if (rand.nextBoolean()){
			for(attempt = 0; attempt < 180; attempt++){
				xx = x+offsetXZ();
				yy = 20+rand.nextInt(80);
				zz = z+offsetXZ();

				if (world.getBlockId(xx,yy-1,zz) == Block.grass.blockID && world.isAirBlock(xx,yy,zz)){
					if (genTreeEucalyptus.generate(world,rand,xx,yy,zz) && rand.nextBoolean())break;
				}
			}
		}
	}
	
	@Override
	@SuppressWarnings("incomplete-switch")
	protected void modifyOreGen(OreSettings oreGen, OreType oreType, boolean extraOres){
		switch(oreType){
			case COAL: oreGen.setChance(0.85F).setIterations(extraOres?2:3).setOreAmount(7,10).setY(5,56); break; // less common, lowered amount too, also ~2 times smaller area
			case EMERALD: oreGen.setIterations(1,3); break; // one more vein
			case DIAMOND: oreGen.setIterations(3,4); break; // one more vein
			case PETRIFIED_WOOD: oreGen.setIterations(extraOres?1:2,extraOres?2:3).setY(20,64).setCheckArea(3); break; // more common, but ~1.5 times smaller area
			case FOSSIL: oreGen.setChance(0.06F); break; // more rare
		}
	}

	@Override
	public void generateFeature(FeatureType featureType){
		if (featureType == FeatureType.REDGEM){
			for(attempt = 0; attempt < 8; attempt++){
				genRedGem.generate(world,rand,x+offsetXZ(),64+rand.nextInt(60),z+offsetXZ());
			}
		}
		else super.generateFeature(featureType);
	}
}
