package erebus.world.biomes.decorators;
import static erebus.core.handler.ConfigHandler.*;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.block.BlockErebusOre;
import erebus.block.BlockErebusOreExtras;
import erebus.world.biomes.decorators.type.FeatureType;
import erebus.world.biomes.decorators.type.OreType;
import erebus.world.feature.decoration.WorldGenAmberGround;
import erebus.world.feature.decoration.WorldGenAmberUmberstone;
import erebus.world.feature.decoration.WorldGenErebusMinable;
import erebus.world.feature.decoration.WorldGenRedGem;

public abstract class BiomeDecoratorErebus{
	protected World world;
	protected Random rand;
	protected int x,z;
	protected int xx,yy,zz,attempt;
	
	protected static final WorldGenErebusMinable genMinable = new WorldGenErebusMinable();
	protected static final WorldGenAmberGround genAmberGround = new WorldGenAmberGround();
	protected static final WorldGenAmberUmberstone genAmberUmberstone = new WorldGenAmberUmberstone();
	protected static final WorldGenRedGem genRedGem = new WorldGenRedGem();
	
	protected BiomeDecoratorErebus(){}
	
	public final void decorate(World world, Random rand, int x, int z){
		// TODO if (this.world != null)throw new RuntimeException("Already decorating Erebus!");
		this.world = world;
		this.rand = rand;
		this.x = x;
		this.z = z;
		
		for(FeatureType featureType:FeatureType.values())generateFeature(featureType);
		
		boolean extraOres = lead||silver||copper||tin||aluminium;
		for(OreType oreType:OreType.values())generateOre(oreType,extraOres);
		
		generateBiomeFeatures();
		
		//this.world = null;
	}
	
	protected abstract void generateBiomeFeatures();

	protected void generateFeature(FeatureType featureType){
		switch(featureType){
			case AMBER_GROUND:
				if (rand.nextInt(6) != 0)return;
				
				for(attempt = 0; attempt < 4; attempt++){
					if (genAmberGround.generate(world,rand,x+offsetXZ(),10+rand.nextInt(40),z+offsetXZ()))break;
				}
				
				break;
				
			case AMBER_UMBERSTONE:
				if (rand.nextInt(3) != 0)return;
				
				for(attempt = 0; attempt < 5; attempt++){
					if (genAmberUmberstone.generate(world,rand,x+offsetXZ(),rand.nextInt(120),z+offsetXZ()))break;
				}
				
				break;
				
			case REDGEM:
				for(attempt = 0; attempt < 5; attempt++){
					genRedGem.generate(world,rand,x+offsetXZ(),64+rand.nextInt(60),z+offsetXZ());
				}
				
				break;
		}
	}
	
	protected void generateOre(OreType oreType, boolean extraOres){
		switch(oreType){
			case COAL:
				generateOreCluster(extraOres?6:8,ModBlocks.umberOreBlock,BlockErebusOre.dataCoal,9,12,world,rand,x,z,6,112,3); break;
				
			case IRON:
				generateOreCluster((extraOres?7:9)+rand.nextInt(2),ModBlocks.umberOreBlock,BlockErebusOre.dataIron,6,10,world,rand,x,z,6,112,3); break;
			
			case GOLD:
				generateOreCluster(extraOres?4:5,ModBlocks.umberOreBlock,BlockErebusOre.dataGold,6,world,rand,x,z,6,112,3); break;
				
			case LAPIS:
				generateOreCluster(3,ModBlocks.umberOreBlock,BlockErebusOre.dataLapis,5,world,rand,x,z,6,112,2); break;
				
			case EMERALD:
				generateOreCluster(rand.nextInt(3),ModBlocks.umberOreBlock,BlockErebusOre.dataEmerald,3,world,rand,x,z,6,112,1); break;
				
			case DIAMOND:
				if (rand.nextInt(3)!=0)generateOreCluster(2+rand.nextInt(2),ModBlocks.umberOreBlock,BlockErebusOre.dataDiamond,1,world,rand,x,z,6,112,1); break;
				
			case JADE:
				if (rand.nextBoolean())generateOreCluster(1+rand.nextInt(4),ModBlocks.umberOreBlock,BlockErebusOre.dataJade,4,world,rand,x,z,6,112,2); break;
				
			case PETRIFIED_WOOD:
				generateOreCluster((extraOres?2:3)+rand.nextInt(2)-rand.nextInt(2),ModBlocks.umberOreBlock,BlockErebusOre.dataPetrifiedWood,7,9,world,rand,x,z,6,112,2); break;
				
			case FOSSIL:
				if (rand.nextInt(8)==0)generateOreCluster(1+rand.nextInt(2)*rand.nextInt(2),ModBlocks.oreFossil,3,8,11,world,rand,x,z,36,112,3); break;
				
			case ALUMINIUM:
				if (aluminium)generateOreCluster(2+rand.nextInt(2),ModBlocks.erebusOreExtra,BlockErebusOreExtras.dataAluminium,3,4,world,rand,x,z,6,112,2); break;
				
			case COPPER:
				if (copper)generateOreCluster(7+rand.nextInt(3),ModBlocks.erebusOreExtra,BlockErebusOreExtras.dataCopper,5,7,world,rand,x,z,6,112,3); break;
				
			case LEAD:
				if (lead)generateOreCluster(4,ModBlocks.erebusOreExtra,BlockErebusOreExtras.dataLead,3,world,rand,x,z,6,112,2); break;
				
			case SILVER:
				if (silver)generateOreCluster(5,ModBlocks.erebusOreExtra,BlockErebusOreExtras.dataSilver,6,8,world,rand,x,z,6,112,3); break;
				
			case TIN:
				if (tin)generateOreCluster(2+rand.nextInt(3),ModBlocks.erebusOreExtra,BlockErebusOreExtras.dataTin,3,4,world,rand,x,z,6,112,2); break;
		}
	}

	protected final int offsetXZ(){
		return rand.nextInt(16)+8;
	}

	protected static final byte[] checkX = new byte[]{ -1, -1, 1, 1, 0, 0 },
							      checkY = new byte[]{ 0, 0, 0, 0, -1, 1 },
							      checkZ = new byte[]{-1, 1, -1, 1, 0, 0 };

	protected void generateOreCluster(int iterations, Block oreBlock, int oreMeta, int oreAmount, World world, Random rand, int x, int z, int minY, int maxY, int checkArea){
		generateOreCluster(iterations,oreBlock,oreMeta,oreAmount,oreAmount,world,rand,x,z,minY,maxY,checkArea);
	}
	
	protected void generateOreCluster(int iterations, Block oreBlock, int oreMeta, int minOreAmount, int maxOreAmount, World world, Random rand, int x, int z, int minY, int maxY, int checkArea){
		for(int iteration = 0, oreAmount; iteration < iterations; iteration++){
			for(attempt = 0; attempt < 12; attempt++){
				xx = x+2+rand.nextInt(12);
				zz = z+2+rand.nextInt(12);
				yy = minY+rand.nextInt(Math.max(1,1+maxY-minY));
	
				for(int a = 0; a < 6; a++){
					int testX = xx+checkX[a]*checkArea, testY = yy+checkY[a]*checkArea, testZ = zz+checkZ[a]*checkArea;
					if (testX>>4 != x>>4)testX = x;
					if (testZ>>4 != z>>4)testZ = z;
	
					if (world.isAirBlock(testX,testY,testZ)){
						if ((oreAmount = minOreAmount+rand.nextInt(maxOreAmount-minOreAmount+1)) == 1){
							if (world.getBlockId(xx,yy,zz) == ModBlocks.umberstone.blockID)world.setBlock(xx,yy,zz,oreBlock.blockID,oreMeta,2);
						}
						else{
							genMinable.prepare(oreBlock.blockID,oreMeta,oreAmount);
							genMinable.generate(world,rand,xx,yy,zz);
						}
						
						attempt = 99;
						break;
					}
				}
			}
		}
	}
}
