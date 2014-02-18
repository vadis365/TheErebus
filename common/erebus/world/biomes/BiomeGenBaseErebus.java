package erebus.world.biomes;
import static erebus.core.handler.ConfigHandler.*;
import java.util.Random;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import erebus.ModBiomes;
import erebus.ModBlocks;
import erebus.block.BlockErebusOre;
import erebus.block.BlockErebusOreExtras;
import erebus.world.feature.decoration.WorldGenAmberGround;
import erebus.world.feature.decoration.WorldGenAmberUmberstone;
import erebus.world.feature.decoration.WorldGenErebusMinable;
import erebus.world.feature.decoration.WorldGenRedGem;
import erebus.world.feature.util.FeatureType;
import erebus.world.feature.util.OreType;
import erebus.world.loot.IWeightProvider;

// @formatter:off
public abstract class BiomeGenBaseErebus extends BiomeGenBase implements IWeightProvider{
	private short biomeWeight;
	private int grassColor,foliageColor;
	
	public BiomeGenBaseErebus(int biomeID){
		super(biomeID);
		ModBiomes.biomeList.add(this);
		
		setDisableRain();
		
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableCaveCreatureList.clear();
	}
	
	protected BiomeGenBaseErebus setColors(int grassAndFoliage){
		setColors(grassAndFoliage,grassAndFoliage);
		return this;
	}
	
	protected BiomeGenBaseErebus setColors(int grass, int foliage){
		setColor(grass);
		func_76733_a(grass);
		grassColor=grass;
		foliageColor=foliage;
		return this;
	}
	
	protected BiomeGenBaseErebus setWeight(int weight){
		this.biomeWeight=(short)weight;
		return this;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor(){
		return grassColor;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeFoliageColor(){
		return foliageColor;
	}
	
	@Override
	public final short getWeight(){
		return biomeWeight;
	}

	@Override
	public void decorate(World world, Random rand, int x, int z){
		for(FeatureType featureType:FeatureType.values()){
			if (featureType.canGenerateIn(this))generateFeature(world,rand,x,z,featureType);
		}
		
		for(OreType oreType:OreType.values())generateOre(world,rand,x,z,oreType);
		
		generateBiomeFeatures(world,rand,x,z);
	}
	
	public abstract void generateBiomeFeatures(World world, Random rand, int x, int z);

	public void generateFeature(World world, Random rand, int x, int z, FeatureType featureType){
		switch(featureType){
			case AMBER_GROUND:
				if (rand.nextInt(6)!=0)return;
				
				WorldGenAmberGround genAmberGround=new WorldGenAmberGround();
				for(int attempt=0; attempt<4; attempt++){
					if (genAmberGround.generate(world,rand,x+getRandomXZOffset(rand),10+rand.nextInt(40),z+getRandomXZOffset(rand))) break;
				}
				break;
				
			case AMBER_UMBERSTONE:
				if (rand.nextInt(3)!=0)return;
				
				WorldGenAmberUmberstone genAmberUmberstone=new WorldGenAmberUmberstone();
				for(int attempt=0; attempt<5; attempt++){
					if (genAmberUmberstone.generate(world,rand,x+getRandomXZOffset(rand),rand.nextInt(120),z+getRandomXZOffset(rand))) break;
				}
				break;
				
			case REDGEM:
				for(int attempt=0; attempt<5; attempt++){
					new WorldGenRedGem().generate(world,rand,x+getRandomXZOffset(rand),64+rand.nextInt(60),z+getRandomXZOffset(rand));
				}
				break;
		}
	}
	
	public void generateOre(World world, Random rand, int x, int z, OreType oreType){
		boolean extraOres=lead||silver||copper||tin||aluminium;

		switch(oreType){
			case COAL:
				generateOreCluster(extraOres?8:10,ModBlocks.umberOreBlock,BlockErebusOre.dataCoal,11,14,world,rand,x,z,6,112,3); break;
				
			case IRON:
				generateOreCluster((extraOres?9:11)+rand.nextInt(2),ModBlocks.umberOreBlock,BlockErebusOre.dataIron,7,11,world,rand,x,z,6,112,3); break;
			
			case GOLD:
				generateOreCluster(extraOres?4:5,ModBlocks.umberOreBlock,BlockErebusOre.dataGold,6,world,rand,x,z,6,112,3); break;
				
			case LAPIS:
				generateOreCluster(3,ModBlocks.umberOreBlock,BlockErebusOre.dataLapis,5,world,rand,x,z,6,112,2); break;
				
			case EMERALD:
				generateOreCluster(1+rand.nextInt(3),ModBlocks.umberOreBlock,BlockErebusOre.dataEmerald,3,world,rand,x,z,6,112,1); break;
				
			case DIAMOND:
				if (rand.nextInt(3)!=0)generateOreCluster(2+rand.nextInt(2),ModBlocks.umberOreBlock,BlockErebusOre.dataDiamond,1,world,rand,x,z,6,112,1); break;
				
			case JADE:
				if (rand.nextBoolean())generateOreCluster(1+rand.nextInt(4),ModBlocks.umberOreBlock,BlockErebusOre.dataJade,4,world,rand,x,z,6,112,2); break;
				
			case PETRIFIED_WOOD:
				generateOreCluster((extraOres?3:4)+rand.nextInt(2),ModBlocks.umberOreBlock,BlockErebusOre.dataPetrifiedWood,7,9,world,rand,x,z,6,112,2); break;
				
			case FOSSIL:
				if (rand.nextInt(5)==0)generateOreCluster(1+rand.nextInt(3),ModBlocks.oreFossil,3,8,11,world,rand,x,z,36,112,3); break;
				
			case ALUMINIUM:
				if (aluminium)generateOreCluster(2+rand.nextInt(2),ModBlocks.erebusOreExtra,BlockErebusOreExtras.dataAluminium,3,4,world,rand,x,z,6,112,2); break;
				
			case COPPER:
				if (copper)generateOreCluster(8+rand.nextInt(3),ModBlocks.erebusOreExtra,BlockErebusOreExtras.dataCopper,5,7,world,rand,x,z,6,112,3); break;
				
			case LEAD:
				if (lead)generateOreCluster(4,ModBlocks.erebusOreExtra,BlockErebusOreExtras.dataLead,3,world,rand,x,z,6,112,2); break;
				
			case SILVER:
				if (silver)generateOreCluster(6,ModBlocks.erebusOreExtra,BlockErebusOreExtras.dataSilver,6,8,world,rand,x,z,6,112,3); break;
				
			case TIN:
				if (tin)generateOreCluster(2+rand.nextInt(3),ModBlocks.erebusOreExtra,BlockErebusOreExtras.dataTin,3,4,world,rand,x,z,6,112,2); break;
		}
	}
	
	protected final int getRandomXZOffset(Random rand){
		return rand.nextInt(16)+8;
	}

	protected static final byte[] checkX=new byte[]{ -1, -1, 1, 1, 0, 0 }, checkY=new byte[]{ 0, 0, 0, 0, -1, 1 }, checkZ=new byte[]{-1, 1, -1, 1, 0, 0 };

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
