package erebus.world.feature.decoration;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;

//@formatter:off
public class WorldGenPonds extends WorldGenerator{

	private final double size;

	public WorldGenPonds(double size){
		this.size=size;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z){
		x-=8;
		z-=8;

		for(; y>5 && world.isAirBlock(x,y,z); --y);
		
		if (y<=4)return false;
		y-=4;
		
		boolean[] placeWater=new boolean[2048];

		for(int iteration=0,iterAmount=rand.nextInt(3)+4; iteration<iterAmount; ++iteration){
			double d0=(rand.nextDouble()*6D+3D)*size*(0.4D+rand.nextDouble()*0.6D);
			double d1=(rand.nextDouble()*4D+2D)*size/2.5D;
			double d2=(rand.nextDouble()*6D+3D)*size*(0.4D+rand.nextDouble()*0.6D);
			double d3=rand.nextDouble()*(16D-d0-2D)+1D+d0/2D;
			double d4=rand.nextDouble()*(8D-d1-4D)+2D+d1/2D;
			double d5=rand.nextDouble()*(16D-d2-2D)+1D+d2/2D;

			for(int xx=1; xx<15; ++xx){
				for(int zz=1; zz<15; ++zz){
					for(int yy=1; yy<7; ++yy){
						double d6=(xx-d3)/(d0/2.0D);
						double d7=(yy-d4)/(d1/2.0D);
						double d8=(zz-d5)/(d2/2.0D);
						double dist=d6*d6+d7*d7+d8*d8;

						if (dist<1D)placeWater[(xx*16+zz)*8+yy]=true;
					}
				}
			}
		}

		for(int xx=0; xx<16; ++xx){
			for(int zz=0; zz<16; ++zz){
				for(int yy=0; yy<8; ++yy){
					boolean flag=!placeWater[(xx*16+zz)*8+yy]&&(xx<15&&placeWater[((xx+1)*16+zz)*8+yy]||xx>0&&placeWater[((xx-1)*16+zz)*8+yy]||zz<15&&placeWater[(xx*16+zz+1)*8+yy]||zz>0&&placeWater[(xx*16+zz-1)*8+yy]||yy<7&&placeWater[(xx*16+zz)*8+yy+1]||yy>0&&placeWater[(xx*16+zz)*8+yy-1]);
					if (!flag)continue;

					Material mat=world.getBlockMaterial(x+xx,y+yy,z+zz);
					if ((yy>=4 && mat.isLiquid()) || (yy<4 && !mat.isSolid() && world.getBlockId(x+xx,y+yy,z+zz)!=Block.waterMoving.blockID))return false;
				}
			}
		}

		for(int xx=0; xx<16; ++xx){
			for(int zz=0; zz<16; ++zz){
				for(int yy=0; yy<8; ++yy){
					if (placeWater[(xx*16+zz)*8+yy])world.setBlock(x+xx,y+yy,z+zz,yy>=4?0:Block.waterMoving.blockID,0,2);
				}
			}
		}

		for(int xx=0; xx<16; ++xx){
			for(int zz=0; zz<16; ++zz){
				for(int yy=4; yy<8; ++yy){
					if (placeWater[(xx*16+zz)*8+yy] && world.getBlockId(x+xx,y+yy-1,z+zz)==Block.dirt.blockID && world.getSavedLightValue(EnumSkyBlock.Sky,x+xx,y+yy,z+zz)>0){
						BiomeGenBase biome=world.getBiomeGenForCoords(x+xx,z+zz);

						if (biome.topBlock==Block.mycelium.blockID)world.setBlock(x+xx,y+yy-1,z+zz,Block.mycelium.blockID,0,2);
						else world.setBlock(x+xx,y+yy-1,z+zz,Block.grass.blockID,0,2);
					}
				}
			}
		}
		
		for(int xx=0; xx<16; ++xx){
			for(int zz=0; zz<16; ++zz){
				if (world.isBlockFreezable(x+xx,y+4,z+zz))world.setBlock(x+xx,y+4,z+zz,Block.ice.blockID,0,2);
			}
		}

		for(int xx=0; xx<16; ++xx){
			for(int zz=0; zz<16; ++zz){
				for(int yy=0; yy<8; ++yy){
					boolean flag=!placeWater[(xx*16+zz)*8+yy]&&(xx<15&&placeWater[((xx+1)*16+zz)*8+yy]||xx>0&&placeWater[((xx-1)*16+zz)*8+yy]||zz<15&&placeWater[(xx*16+zz+1)*8+yy]||zz>0&&placeWater[(xx*16+zz-1)*8+yy]||yy<7&&placeWater[(xx*16+zz)*8+yy+1]||yy>0&&placeWater[(xx*16+zz)*8+yy-1]);

					if (flag && (yy<4||rand.nextBoolean()) && world.getBlockMaterial(x+xx,y+yy,z+zz).isSolid()){
						world.setBlock(x+xx,y+yy,z+zz,Block.sand.blockID,0,2);
					}
				}
			}
		}
		
		if (rand.nextBoolean()){
			for(int attempt=0,xx,zz; attempt<2+rand.nextInt(6); attempt++){
				xx=x+rand.nextInt(8)+4;
				zz=z+rand.nextInt(8)+4;
				
				for(int yy=0; yy<8; yy++){
					if (world.getBlockId(xx,y+yy,zz)==Block.sand.blockID){
						double rad=rand.nextDouble()*1.3D+1.8D;
						int irad=(int)Math.ceil(rad);
						
						for(int px=xx-irad; px<=xx+irad; px++){
							for(int pz=zz-irad; pz<=zz+irad; pz++){
								for(int py=y+yy-irad; py<=y+yy+irad; py++){
									if (world.getBlockId(px,py,pz)==Block.sand.blockID && Math.sqrt(Math.pow(px-xx,2)+Math.pow(pz-zz,2)+Math.pow(py-y+yy,2))<=rad+rand.nextFloat()*0.3F){
										world.setBlock(px,py,pz,Block.blockClay.blockID);
									}
								}
							}
						}
						
						break;
					}
				}
			}
		}

		WorldGenWaterlily genLily=new WorldGenWaterlily();
		for(int attempt=0; attempt<5; attempt++){
			genLily.generate(world,rand,x+rand.nextInt(8)-rand.nextInt(8)+8,y+2+rand.nextInt(6),z+rand.nextInt(8)-rand.nextInt(8)+8);
		}
		
		return true;
	}
}
// @formatter:on
