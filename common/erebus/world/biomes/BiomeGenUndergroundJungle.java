package erebus.world.biomes;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.block.BlockLeavesErebus;
import erebus.block.BlockLogErebus;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBombardierBeetle;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityFly;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityMosquito;
import erebus.entity.EntityMoth;
import erebus.entity.EntityPrayingMantis;
import erebus.entity.EntityScytodes;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityWasp;
import erebus.world.feature.decoration.WorldGenPonds;
import erebus.world.feature.decoration.WorldGenQuickSand;
import erebus.world.feature.plant.WorldGenBamboo;
import erebus.world.feature.plant.WorldGenMelon;
import erebus.world.feature.plant.WorldGenTurnips;
import erebus.world.feature.structure.WorldGenWaspDungeon;
import erebus.world.feature.tree.WorldGenAsperTree;
import erebus.world.feature.tree.WorldGenErebusHugeTree;
import erebus.world.feature.tree.WorldGenErebusTrees;
import erebus.world.feature.tree.WorldGenEucalyptusTree;
import erebus.world.feature.tree.WorldGenMossbarkTree;
import erebus.world.feature.tree.WorldGenTallJungleTree;

//@formatter:off
public class BiomeGenUndergroundJungle extends BiomeGenBaseErebus{
	public BiomeGenUndergroundJungle(int biomeID){
		super(biomeID);

		spawnableMonsterList.add(new SpawnListEntry(EntityWasp.class,30,4,8));
		spawnableMonsterList.add(new SpawnListEntry(EntityCentipede.class,10,4,8));
		spawnableMonsterList.add(new SpawnListEntry(EntityPrayingMantis.class,10,4,8));
		spawnableMonsterList.add(new SpawnListEntry(EntityScytodes.class,35,1,4));
		spawnableMonsterList.add(new SpawnListEntry(EntityJumpingSpider.class,10,1,4));
		spawnableMonsterList.add(new SpawnListEntry(EntityTarantula.class,5,4,8));
		spawnableMonsterList.add(new SpawnListEntry(EntityBombardierBeetle.class,4,1,1));

		spawnableCaveCreatureList.add(new SpawnListEntry(EntityBotFly.class,10,2,3));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityBeetle.class,8,1,2));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityBeetleLarva.class,8,2,4));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityFly.class,10,8,8));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityMoth.class,5,4,4));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityMosquito.class,60,1,3));

		topBlock=(byte)Block.grass.blockID;
		fillerBlock=(byte)Block.dirt.blockID;
	}
	
	@Override
	public short getBiomeWeight(){
		return 10;
	}

	@Override
	public float getSpawningChance(){
		return 0.2F;
	}

	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){
		for(int attempt=0; attempt<20; attempt++){
			int xx=x+getRandomXZOffset(rand),
				yy=rand.nextInt(120),
				zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				new WorldGenPonds((rand.nextDouble()+0.7D)*2.2D).generate(world,rand,xx,yy,zz);
			}
		}

		if (rand.nextInt(37)==0){
			WorldGenWaspDungeon genWaspDungeon=new WorldGenWaspDungeon();
			
			for(int attempt=0; attempt<5; attempt++){
				if (genWaspDungeon.generate(world,rand,x+getRandomXZOffset(rand),127,z+getRandomXZOffset(rand))) break;
			}
		}
		
		for(int attempt=0; attempt<10; attempt++){
			int xx=x+getRandomXZOffset(rand),
				yy=rand.nextInt(120),
				zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				new WorldGenQuickSand().generate(world,rand,xx,yy,zz);
			}
		}

		for(int attempt=0,xx,yy,zz; attempt<2200; attempt++){
			xx=x+getRandomXZOffset(rand);
			yy=15+rand.nextInt(90);
			zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				WorldGenerator treeGen=null;
				
				switch(rand.nextInt(8)){
					case 0: treeGen=new WorldGenHugeTrees(true,4+rand.nextInt(40),3,3); break;
					case 1: treeGen=new WorldGenMossbarkTree(); break;
					case 2: treeGen=new WorldGenTallJungleTree(); break;
					case 3: treeGen=new WorldGenAsperTree(); break;
					case 4: treeGen=new WorldGenTrees(true,6,3,3,true); break;
					case 5: treeGen=new WorldGenErebusHugeTree(true,20+rand.nextInt(5),BlockLogErebus.dataMahogany,BlockLeavesErebus.dataMahoganyDecay,false,ModBlocks.logErebusGroup1.blockID,ModBlocks.leavesErebus.blockID); break;
					case 6: treeGen=new WorldGenErebusTrees(true,5,BlockLogErebus.dataMahogany,BlockLeavesErebus.dataMahoganyDecay,false,ModBlocks.logErebusGroup1.blockID,ModBlocks.leavesErebus.blockID,ModBlocks.thorns.blockID); break;
					case 7: treeGen=new WorldGenEucalyptusTree(); break;
				}
				
				treeGen.generate(world,rand,xx,yy,zz);
			}
		}
		
		new WorldGenFlowers(Block.mushroomBrown.blockID).generate(world,rand,x+getRandomXZOffset(rand),rand.nextInt(128),z+getRandomXZOffset(rand));
		new WorldGenFlowers(Block.mushroomRed.blockID).generate(world,rand,x+getRandomXZOffset(rand),rand.nextInt(128),z+getRandomXZOffset(rand));

		for(int attempt=0; attempt<12; attempt++){
			int xx=x+getRandomXZOffset(rand),
				yy=15+rand.nextInt(90),
				zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				new WorldGenBigMushroom(0).generate(world,rand,xx,yy,zz);
			}
		}

		for(int attempt=0; attempt<20; attempt++){
			int xx=x+getRandomXZOffset(rand),
				yy=15+rand.nextInt(90),
				zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				new WorldGenBigMushroom(1).generate(world,rand,xx,yy,zz);
			}
		}

		if (rand.nextInt(11)==0){
			for(int yy=90; yy>20; yy--){
				int xx=x+getRandomXZOffset(rand),
					zz=z+getRandomXZOffset(rand);
				
				if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
					if (new WorldGenBamboo(13).generate(world,rand,xx,yy,zz)) break;
				}
			}
		}

		for(int attempt=0; attempt<40; attempt++){
			int xx=x+getRandomXZOffset(rand),
				yy=15+rand.nextInt(90),
				zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				new WorldGenTallGrass(ModBlocks.fern.blockID,1).generate(world,rand,xx,yy,zz);
			}
		}

		for(int attempt=0; attempt<16; attempt++){
			int xx=x+getRandomXZOffset(rand),
				yy=15+rand.nextInt(90),
				zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				new WorldGenTallGrass(ModBlocks.fiddlehead.blockID,1).generate(world,rand,xx,yy,zz);
			}
		}

		WorldGenerator gen=new WorldGenTallGrass(ModBlocks.erebusGrass.blockID,1);
		for(int attempt=0; attempt<900; attempt++){
			int xx=x+getRandomXZOffset(rand),
				yy=15+rand.nextInt(90),
				zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				gen.generate(world,rand,xx,yy,zz);
			}
		}

		byte[] offsetX=new byte[]{ -1, 1, 0, 0 }, offsetZ=new byte[]{ 0, 0, -1, 1  };
		for(int attempt=0,xx,yy,zz,block,offset; attempt<800; attempt++){
			xx=x+getRandomXZOffset(rand);
			yy=30+rand.nextInt(80);
			zz=z+getRandomXZOffset(rand);

			if (world.isAirBlock(xx,yy,zz)){
				block=rand.nextInt(2)==0?Block.vine.blockID:ModBlocks.thorns.blockID;
				offset=rand.nextInt(4);
				
				if (!Block.isNormalCube(world.getBlockId(xx+offsetX[offset],yy,zz+offsetZ[offset])))continue;
				
				for(int vineY=rand.nextInt(30); vineY>0; vineY--){
					if (world.getBlockId(xx+offsetX[offset],yy-vineY,zz+offsetZ[offset])==0){
						world.setBlock(xx+offsetX[offset],yy-vineY,zz+offsetZ[offset],Block.vine.blockID,offset==3?1:offset==2?4:offset==1?0:2,3);
					}
				}
			}
		}

		if (rand.nextInt(3)==0){
			for(int attempt=0; attempt<6; ++attempt){
				int xx=x+getRandomXZOffset(rand),
					yy=15+rand.nextInt(90),
					zz=z+getRandomXZOffset(rand);
				
				if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
					new WorldGenTurnips().generate(world,rand,xx,yy,zz);
				}
			}
		}
		else if (rand.nextInt(2)==0){
			for(int attempt=0; attempt<3; ++attempt){
				int xx=x+getRandomXZOffset(rand),
					yy=15+rand.nextInt(90),
					zz=z+getRandomXZOffset(rand);
				
				if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
					new WorldGenMelon().generate(world,rand,xx,yy,zz);
				}
			}
		}
	}
}
//@formatter:on