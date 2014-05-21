package erebus.world.feature.plant;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

//@formatter:off
public class WorldGenBigMushroomErebus extends WorldGenerator{

	private int mushroomType = -1;

	public WorldGenBigMushroomErebus(int mushroomType){
		super(true);
		this.mushroomType=mushroomType;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z){
		int height=rand.nextInt(3)+4;

		if (y>=1&&y+height+1<256){
			boolean canGenerate=true;

			for(int yy=y; yy<=y+1+height; ++yy){
				byte rad=3;

				if (yy<=y+3) rad=0;

				for(int xx=x-rad; xx<=x+rad&&canGenerate; ++xx){
					for(int zz=z-rad; zz<=z+rad&&canGenerate; ++zz){
						if (yy>=0&&yy<256){
							Block block=Block.blocksList[world.getBlockId(xx,yy,zz)];
							if (block!=null&&!block.isLeaves(world,xx,yy,zz))canGenerate=false;
						}
						else canGenerate=false;
					}
				}
			}

			if (!canGenerate)return false;

			int id=world.getBlockId(x,y-1,z);
			if (id!=ModBlocks.umberstone.blockID&&id!=Block.obsidian.blockID&&id!=Block.glowStone.blockID)return false;

			for(int yy=y+height-(mushroomType==1?3:0); yy<=y+height; ++yy){
				int capRad=mushroomType==0?3:(yy<y+height?2:1);

				for(int xx=x-capRad; xx<=x+capRad; ++xx){
					for(int zz=z-capRad; zz<=z+capRad; ++zz){
						int meta=5;

						if (xx==x-capRad) --meta;
						if (xx==x+capRad) ++meta;
						if (zz==z-capRad) meta-=3;
						if (zz==z+capRad) meta+=3;

						if (mushroomType==0||yy<y+height){
							if ((xx==x-capRad||xx==x+capRad)&&(zz==z-capRad||zz==z+capRad))continue;

							if (xx==x-(capRad-1)&&zz==z-capRad) meta=1;
							if (xx==x-capRad&&zz==z-(capRad-1)) meta=1;
							if (xx==x+capRad-1&&zz==z-capRad) meta=3;
							if (xx==x+capRad&&zz==z-(capRad-1)) meta=3;
							if (xx==x-(capRad-1)&&zz==z+capRad) meta=7;
							if (xx==x-capRad&&zz==z+capRad-1) meta=7;
							if (xx==x+capRad-1&&zz==z+capRad) meta=9;
							if (xx==x+capRad&&zz==z+capRad-1) meta=9;
						}

						if (meta==5&&yy<y+height)meta=0;

						Block block=Block.blocksList[world.getBlockId(xx,yy,zz)];

						if ((meta!=0||y>=y+height-1)&&(block==null||block.canBeReplacedByLeaves(world,xx,yy,zz))){
							setBlockAndMetadata(world,xx,yy,zz,Block.mushroomCapBrown.blockID+mushroomType,meta);
						}
					}
				}
			}

			for(int yy=0; yy<height; ++yy){
				Block block=Block.blocksList[world.getBlockId(x,y+yy,z)];

				if (block==null||block.canBeReplacedByLeaves(world,x,y+yy,z)){
					setBlockAndMetadata(world,x,y+yy,z,Block.mushroomCapBrown.blockID+mushroomType,10);
				}
			}

			return true;
		}
		
		return false;
	}
}
// @formatter:on