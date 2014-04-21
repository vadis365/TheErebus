package erebus.world.biomes.decorators;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.world.biomes.decorators.type.FeatureType;
import erebus.world.feature.decoration.WorldGenPonds;
import erebus.world.feature.decoration.WorldGenRottenAcacia;
import erebus.world.feature.decoration.WorldGenSavannahRock;
import erebus.world.feature.plant.WorldGenBamboo;
import erebus.world.feature.tree.WorldGenAcaciaTree;
import erebus.world.feature.tree.WorldGenAsperTree;

public class BiomeDecoratorSubterraneanSavannah extends BiomeDecoratorBaseErebus{
	private final WorldGenPonds genPonds = new WorldGenPonds();
	private final WorldGenBamboo genBamboo = new WorldGenBamboo(7,true);
	private final WorldGenSavannahRock genRocks = new WorldGenSavannahRock();
	private final WorldGenRottenAcacia genRottenAcacia = new WorldGenRottenAcacia();
	
	private final WorldGenTallGrass genGrass = new WorldGenTallGrass(ModBlocks.erebusGrass.blockID,1);
	
	private final WorldGenerator genTreeAcacia = new WorldGenAcaciaTree();
	private final WorldGenerator genTreeAsper = new WorldGenAsperTree();
	
	@Override
	public void populate(){
		if (rand.nextBoolean() && rand.nextBoolean()){
			for(attempt = 0; attempt < 8; attempt++){
				xx = x+16;
				yy = rand.nextInt(120);
				zz = z+16;
				
				if (world.getBlockId(xx,yy-1,zz) == Block.grass.blockID && world.isAirBlock(xx,yy,zz)){
					genPonds.prepare((rand.nextDouble()+0.75D)*1.2D);
					genPonds.generate(world,rand,xx,yy,zz);
				}
			}
			
			if (rand.nextInt(3) != 0){
				for(yy = 100; yy > 20; yy--){
					xx = x+offsetXZ();
					zz = z+offsetXZ();
					
					if (world.getBlockId(xx,yy-1,zz) == Block.grass.blockID && world.isAirBlock(xx,yy,zz)){
						genBamboo.generate(world,rand,xx,yy,zz);
					}
				}
			}
		}
	}
	
	@Override
	public void decorate(){
		for(attempt = 0; attempt < 65; attempt++){
			xx = x+offsetXZ();
			yy = 15+rand.nextInt(90);
			zz = z+offsetXZ();

			if (world.getBlockId(xx,yy-1,zz) == Block.grass.blockID && world.isAirBlock(xx,yy,zz)){
				genTreeAcacia.generate(world,rand,xx,yy,zz);
			}
		}

		if (rand.nextBoolean() && rand.nextBoolean()){
			xx = x+offsetXZ();
			zz = z+offsetXZ();
			
			for(yy = 100; yy > 20; yy--){
				if (world.getBlockId(xx,yy-1,zz) == Block.grass.blockID && world.isAirBlock(xx,yy,zz)){
					genRocks.generate(world,rand,xx,yy,zz);
				}
			}
		}
		
		for(attempt = 0; attempt < 10; attempt++){
			xx = x+offsetXZ();
			yy = rand.nextInt(120);
			zz = z+offsetXZ();
			
			if (world.getBlockId(xx,yy-1,zz) == Block.grass.blockID && world.isAirBlock(xx,yy,zz)){
				genTreeAsper.generate(world,rand,xx,yy,zz);
			}
		}

		for(attempt = 0; attempt < 28; attempt++){
			xx = x+offsetXZ();
			yy = 15+rand.nextInt(90);
			zz = z+offsetXZ();
			
			if (world.getBlockId(xx,yy-1,zz)  ==  Block.grass.blockID && world.isAirBlock(xx,yy,zz)){
				genRottenAcacia.generate(world,rand,xx,yy,zz);
			}
		}

		for(attempt = 0; attempt < 180; attempt++){
			xx = x+offsetXZ();
			yy = 15+rand.nextInt(90);
			zz = z+offsetXZ();
			
			if (world.getBlockId(xx,yy-1,zz)  ==  Block.grass.blockID && world.isAirBlock(xx,yy,zz)){
				genGrass.generate(world,rand,xx,yy,zz);
			}
		}
	}
	
	@Override
	public void generateFeature(FeatureType featureType){
		if ((featureType == FeatureType.AMBER_GROUND || featureType == FeatureType.AMBER_UMBERSTONE) && rand.nextInt(4) != 0)return;
		super.generateFeature(featureType);
	}
}
