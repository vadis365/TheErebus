package erebus.world.biomes.decorators;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.world.biomes.decorators.type.FeatureType;
import erebus.world.feature.plant.WorldGenGiantFlowers;
import erebus.world.feature.tree.WorldGenCypressTree;

public class BiomeDecoratorElysianFields extends BiomeDecoratorBaseErebus{
	private final WorldGenerator genTreeCypress = new WorldGenCypressTree();
	private final WorldGenerator genGiantFlowers = new WorldGenGiantFlowers();
	
	@Override
	public void decorate(){
		for(int attempt = 0, xx, yy, zz; attempt < 200; attempt++){
			xx = x + offsetXZ();
			zz = z + offsetXZ();
			yy = 20 + rand.nextInt(80);

			if (world.getBlockId(xx,yy - 1,zz) == Block.grass.blockID && world.isAirBlock(xx,yy,zz)){
				genTreeCypress.generate(world,rand,xx,yy,zz);
			}
		}

		if (rand.nextBoolean()){
			for(int attempt = 0, xx, yy, zz; attempt < 65; attempt++){
				xx = x + offsetXZ();
				yy = 15 + rand.nextInt(90);
				zz = z + offsetXZ();

				if (world.getBlockId(xx,yy - 1,zz) == Block.grass.blockID && world.isAirBlock(xx,yy,zz)){
					genGiantFlowers.generate(world,rand,xx,yy,zz);
				}
			}
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
	
	/*@Override
	public void generateOre(OreType oreType, boolean extraOres){
		if (oreType != OreType.FOSSIL)super.generateOre(oreType,extraOres);
	}*/
}
