package erebus.world.biomes.decorators;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.world.biomes.decorators.data.FeatureType;
import erebus.world.biomes.decorators.data.OreSettings;
import erebus.world.biomes.decorators.data.SurfaceType;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import erebus.world.feature.plant.WorldGenGiantFlowers;
import erebus.world.feature.tree.WorldGenCypressTree;

public class BiomeDecoratorElysianFields extends BiomeDecoratorBaseErebus{
	private final WorldGenerator genTreeCypress = new WorldGenCypressTree();
	private final WorldGenerator genGiantFlowers = new WorldGenGiantFlowers();
	
	@Override
	public void decorate(){
		for(attempt = 0; attempt < 105; attempt++){
			xx = x + offsetXZ();
			zz = z + offsetXZ();
			yy = 20 + rand.nextInt(80);

			if (checkSurface(SurfaceType.GRASS,xx,yy,zz)){
				genTreeCypress.generate(world,rand,xx,yy,zz);
			}
		}

		if (rand.nextBoolean()){
			for(int attempt = 0, xx, yy, zz; attempt < 65; attempt++){
				xx = x + offsetXZ();
				yy = 15 + rand.nextInt(90);
				zz = z + offsetXZ();

				if (checkSurface(SurfaceType.GRASS,xx,yy,zz)){
					genGiantFlowers.generate(world,rand,xx,yy,zz);
				}
			}
		}
		
		int id;
		for(attempt = 0; attempt < 25; attempt++){
			xx = x + offsetXZ();
			zz = z + offsetXZ();

			for(yy = 20; yy < 100; yy += rand.nextBoolean() ? 2 : 1){
				if (checkSurface(SurfaceType.MIXED,xx,yy,zz)){
					if (rand.nextInt(10) == 0 && world.isAirBlock(xx,yy+1,zz)){
						world.setBlock(xx,yy,zz,ModBlocks.doubleHeightPlant.blockID,4,2);
						world.setBlock(xx,yy+1,zz,ModBlocks.doubleHeightPlant.blockID,4+8,2);
					}
					else world.setBlock(xx,yy,zz,ModBlocks.erebusGrass.blockID,1,2);
					
					break;
				}
			}
		}
		
		for(attempt = 0; attempt < 12; attempt++){
			xx = x + offsetXZ();
			zz = z + offsetXZ();

			for(yy = 20; yy < 100; yy += rand.nextBoolean() ? 2 : 1){
				if (checkSurface(SurfaceType.MIXED,xx,yy,zz)){
					if (rand.nextInt(10) == 0 && world.isAirBlock(xx,yy+1,zz)){
						world.setBlock(xx,yy,zz,ModBlocks.doubleHeightPlant.blockID,7,2);
						world.setBlock(xx,yy+1,zz,ModBlocks.doubleHeightPlant.blockID,7+8,2);
					}
					else world.setBlock(xx,yy,zz,ModBlocks.fern.blockID,0,2);
					
					break;
				}
			}
		}
		
		for(attempt = 0; attempt < 5; attempt++){
			xx = x+offsetXZ();
			yy = 20+rand.nextInt(80);
			zz = z+offsetXZ();

			if (world.getBlockId(xx,yy-1,zz) == Block.grass.blockID && world.isAirBlock(xx,yy,zz) && world.isAirBlock(xx,yy+1,zz)){
				world.setBlock(xx,yy,zz,ModBlocks.doubleHeightPlant.blockID,0,2);
				world.setBlock(xx,yy+1,zz,ModBlocks.doubleHeightPlant.blockID,0+8,2);
			}
		}
		
		for(attempt = 0; attempt < 5; attempt++){
			xx = x+offsetXZ();
			yy = 20+rand.nextInt(80);
			zz = z+offsetXZ();

			if (world.getBlockId(xx,yy-1,zz) == Block.grass.blockID && world.isAirBlock(xx,yy,zz) && world.isAirBlock(xx,yy+1,zz)){
				world.setBlock(xx,yy,zz,ModBlocks.doubleHeightPlant.blockID,1,2);
				world.setBlock(xx,yy+1,zz,ModBlocks.doubleHeightPlant.blockID,1+8,2);
			}
		}
	}
	
	@Override
	@SuppressWarnings("incomplete-switch")
	protected void modifyOreGen(OreSettings oreGen, OreType oreType, boolean extraOres){
		switch(oreType){
			case COAL: oreGen.setIterations(extraOres?2:3,extraOres?3:4).setY(5,48); break; // ~2.5 times smaller area, thus less iterations
			case IRON: oreGen.setChance(0.75F).setIterations(extraOres?2:3,extraOres?4:5).setY(5,42); break; // ~3 times smaller area, thus lower chance and iterations
			case GOLD: oreGen.setIterations(extraOres?2:3); break; // 2 veins less
			case EMERALD: oreGen.setIterations(2,4).setCheckArea(2); break; // 2 veins more
			case JADE: oreGen.setIterations(2,5); break; // 1 vein more
			case PETRIFIED_WOOD: oreGen.setChance(0F); break;
			case FOSSIL: oreGen.setChance(0.25F).setOreAmount(5,8); break; // double chance, lower amount per vein
		}
	}

	@Override
	public void generateFeature(FeatureType featureType){
		if (featureType == FeatureType.REDGEM){
			for(attempt = 0; attempt < 2+rand.nextInt(2); attempt++){
				genRedGem.generate(world,rand,x+offsetXZ(),64+rand.nextInt(60),z+offsetXZ());
			}
		}
		else super.generateFeature(featureType);
	}
}
