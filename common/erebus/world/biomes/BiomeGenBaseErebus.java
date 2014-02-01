package erebus.world.biomes;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import erebus.ModBiomes;
import erebus.ModBlocks;
import erebus.block.BlockErebusOre;
import erebus.block.BlockErebusOreExtras;
import erebus.core.handler.ConfigurationHandler;
import erebus.world.feature.decoration.WorldGenErebusMinable;
import erebus.world.feature.util.OreType;
import erebus.world.loot.IWeightProvider;

// @formatter:off
public abstract class BiomeGenBaseErebus extends BiomeGenBase implements IWeightProvider{
	public BiomeGenBaseErebus(int biomeID){
		super(biomeID);
		ModBiomes.biomeList.add(this);
		
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableCaveCreatureList.clear();
	}
	
	protected abstract short getBiomeWeight();
	
	@Override
	public final short getWeight(){
		return getBiomeWeight();
	}

	@Override
	public void decorate(World world, Random rand, int x, int z){
		generateTerrain(world,rand,x,z);
		for(OreType oreType:OreType.values())generateOre(world,rand,x,z,oreType);
	}

	protected final int getRandomXZOffset(Random rand){
		return rand.nextInt(16)+8;
	}
	
	public abstract void generateTerrain(World world, Random rand, int x, int z);

	public void generateOre(World worldObj, Random rand, int x, int z, OreType oreType){
		boolean extraOres=ConfigurationHandler.lead||ConfigurationHandler.silver||ConfigurationHandler.copper||ConfigurationHandler.tin||ConfigurationHandler.aluminium;

		switch(oreType){
			case COAL:
				generateOreCluster(extraOres?8:10,ModBlocks.umberOreBlock,BlockErebusOre.dataCoal,11,14,worldObj,rand,x,z,6,112,3); break;
				
			case IRON:
				generateOreCluster(extraOres?9:11,ModBlocks.umberOreBlock,BlockErebusOre.dataIron,8,11,worldObj,rand,x,z,6,112,3); break;
			
			case GOLD:
				generateOreCluster(extraOres?4:5,ModBlocks.umberOreBlock,BlockErebusOre.dataGold,6,worldObj,rand,x,z,6,112,3); break;
				
			case LAPIS:
				generateOreCluster(3,ModBlocks.umberOreBlock,BlockErebusOre.dataLapis,5,worldObj,rand,x,z,6,112,2); break;
				
			case EMERALD:
				generateOreCluster(1+rand.nextInt(3),ModBlocks.umberOreBlock,BlockErebusOre.dataEmerald,3,worldObj,rand,x,z,6,112,1); break;
				
			case DIAMOND:
				if (rand.nextInt(3)!=0)generateOreCluster(2+rand.nextInt(2),ModBlocks.umberOreBlock,BlockErebusOre.dataDiamond,1,worldObj,rand,x,z,6,112,1); break;
				
			case JADE:
				if (rand.nextInt(2)==0)generateOreCluster(1+rand.nextInt(4),ModBlocks.umberOreBlock,BlockErebusOre.dataJade,4,worldObj,rand,x,z,6,112,2); break;
				
			case PETRIFIED_WOOD:
				generateOreCluster((extraOres?3:4)+rand.nextInt(2),ModBlocks.umberOreBlock,BlockErebusOre.dataPetrifiedWood,7,9,worldObj,rand,x,z,6,112,2); break;
				
			case FOSSIL:
				if (rand.nextInt(5)==0)generateOreCluster(1+rand.nextInt(3),ModBlocks.oreFossil,3,8,11,worldObj,rand,x,z,36,112,3); break;
				
			case ALUMINIUM:
				if (ConfigurationHandler.aluminium)generateOreCluster(2+rand.nextInt(2),ModBlocks.erebusOreExtra,BlockErebusOreExtras.dataAluminium,3,4,worldObj,rand,x,z,6,112,2); break;
				
			case COPPER:
				if (ConfigurationHandler.copper)generateOreCluster(8+rand.nextInt(3),ModBlocks.erebusOreExtra,BlockErebusOreExtras.dataCopper,5,7,worldObj,rand,x,z,6,112,3); break;
				
			case LEAD:
				if (ConfigurationHandler.lead)generateOreCluster(4,ModBlocks.erebusOreExtra,BlockErebusOreExtras.dataLead,3,worldObj,rand,x,z,6,112,2); break;
				
			case SILVER:
				if (ConfigurationHandler.silver)generateOreCluster(6,ModBlocks.erebusOreExtra,BlockErebusOreExtras.dataSilver,6,8,worldObj,rand,x,z,6,112,3); break;
				
			case TIN:
				if (ConfigurationHandler.tin)generateOreCluster(2+rand.nextInt(3),ModBlocks.erebusOreExtra,BlockErebusOreExtras.dataTin,3,4,worldObj,rand,x,z,6,112,2); break;
		}
	}

	protected static final byte[] checkX=new byte[]{-1,-1,1,1,0,0},checkY=new byte[]{0,0,0,0,-1,1},checkZ=new byte[]{-1,1,-1,1,0,0};

	protected void generateOreCluster(int iterations, Block oreBlock, int oreMeta, int oreAmount, World world, Random rand, int x, int z, int minY, int maxY, int checkArea){
		generateOreCluster(iterations,oreBlock,oreMeta,oreAmount,oreAmount,world,rand,x,z,minY,maxY,checkArea);
	}
	
	protected void generateOreCluster(int iterations, Block oreBlock, int oreMeta, int minOreAmount, int maxOreAmount, World world, Random rand, int x, int z, int minY, int maxY, int checkArea){
		for(int iteration=0,xx,yy,zz,oreAmount; iteration<iterations; iteration++){
			for(int attempt=0; attempt<12; attempt++){
				xx=x+2+rand.nextInt(12);
				zz=z+2+rand.nextInt(12);
				yy=minY+rand.nextInt(Math.max(1,1+maxY-minY));
	
				for(int a=0; a<6; a++){
					int testX=xx+checkX[a]*checkArea,testY=yy+checkY[a]*checkArea,testZ=zz+checkZ[a]*checkArea;
					if (testX>>4!=x>>4)testX=x;
					if (testZ>>4!=z>>4)testZ=z;
	
					if (world.isAirBlock(testX,testY,testZ)){
						if ((oreAmount=minOreAmount+rand.nextInt(maxOreAmount-minOreAmount+1))==1){
							if (world.getBlockId(xx,yy,zz)==ModBlocks.umberstone.blockID)world.setBlock(xx,yy,zz,oreBlock.blockID,oreMeta,2);
						}
						else new WorldGenErebusMinable(oreBlock.blockID,oreMeta,oreAmount).generate(world,rand,xx,yy,zz);
						
						attempt=99;
						break;
					}
				}
			}
		}
	}
}
// @formatter:on
