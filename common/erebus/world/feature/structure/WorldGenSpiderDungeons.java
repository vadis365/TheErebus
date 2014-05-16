package erebus.world.feature.structure;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.item.ItemErebusMaterial.DATA;
import erebus.world.loot.LootItemStack;
import erebus.world.loot.LootUtil;
import erebus.world.loot.WeightedLootList;

// @formatter:off
public class WorldGenSpiderDungeons extends WorldGenerator { // TODO

	public static final WeightedLootList chestLoot=new WeightedLootList(new LootItemStack[]{
		new LootItemStack(Item.silk).setAmount(5,10).setWeight(13),
		new LootItemStack(Block.web).setAmount(3,8).setWeight(13),
		new LootItemStack(Item.stick).setAmount(1,8).setWeight(12),
		new LootItemStack(Item.goldNugget).setAmount(3,11).setWeight(12),
		new LootItemStack(ModItems.erebusMaterials).setAmount(3,8).setDamage(DATA.shardBone.ordinal()).setWeight(12),
		new LootItemStack(Item.bone).setAmount(1,3).setWeight(11),
		new LootItemStack(Item.ingotIron).setAmount(1,3).setWeight(10),
		new LootItemStack(Item.ingotGold).setAmount(1,2).setWeight(10),
		new LootItemStack(ModItems.erebusMaterials).setAmount(1,5).setDamage(DATA.flyWing.ordinal()).setWeight(10),
		new LootItemStack(ModItems.erebusMaterials).setAmount(1).setDamage(DATA.jade.ordinal()).setWeight(9),
		new LootItemStack(ModItems.erebusMaterials).setAmount(3,6).setDamage(DATA.plateExo.ordinal()).setWeight(8),
		new LootItemStack(ModItems.erebusMaterials).setAmount(2,6).setDamage(DATA.compoundEyes.ordinal()).setWeight(7),
		new LootItemStack(ModItems.erebusMaterials).setDamage(DATA.compoundLens.ordinal()).setWeight(2),
		new LootItemStack(ModItems.maxSpeedBow).setWeight(1),
		new LootItemStack(ModBlocks.umberGolemStatue).setWeight(1),
		new LootItemStack(ModItems.webSlinger).setWeight(1)
	});

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z){
		byte height=4;
		int halfSizeX=rand.nextInt(4)+4;
		int halfSizeZ=rand.nextInt(4)+4;
		int j1=0;
		int k1;
		int l1;
		int i2;

		for(int xx=x-halfSizeX-1; xx<=x+halfSizeX+1; ++xx)
			for(int yy=y-1; yy<=y+height+1; ++yy)
				for(int zz=z-halfSizeZ-1; zz<=z+halfSizeZ+1; ++zz){
					Material mat=world.getBlockMaterial(xx,yy,zz);

					if ((yy==y-1||yy==y+height+1)&&!mat.isSolid())return false;
					if ((xx==x-halfSizeX-1||xx==x+halfSizeX+1||zz==z-halfSizeZ-1||zz==z+halfSizeZ+1)&&yy==y&&world.isAirBlock(xx,yy,zz)&&world.isAirBlock(xx,yy+1,zz))++j1;
				}

		if (j1>=1&&j1<=5){
			for(int xx=x-halfSizeX-1; xx<=x+halfSizeX+1; ++xx)
				for(int yy=y+height+1; yy>=y-1; --yy)
					for(int zz=z-halfSizeZ-1; zz<=z+halfSizeZ+1; ++zz)
						if (xx!=x-halfSizeX-1 && yy!=y-1 && zz!=z-halfSizeZ-1 && xx!=x+halfSizeX+1 && yy!=y+height+1 && zz!=z+halfSizeZ+1)world.setBlockToAir(xx,yy,zz);
						else if (yy>=0 && !world.getBlockMaterial(xx,yy-1,zz).isSolid())world.setBlockToAir(xx,yy,zz);
						else if (world.getBlockMaterial(xx,yy,zz).isSolid()){
							if (yy==y-1 && rand.nextInt(4)==0 || yy==y+height+1 && rand.nextInt(4)==0) world.setBlock(xx,yy,zz,ModBlocks.umberstone.blockID,3,2); // umbercobbleWebbed
							else if (yy==y-1 && rand.nextInt(4)==0 || yy==y+height+1 && rand.nextInt(4)==0) world.setBlock(xx,yy,zz,ModBlocks.umberstone.blockID,2,2); // umbercobbleMossy
							else world.setBlock(xx,yy,zz,ModBlocks.umberstone.blockID,1,2); // umbercobble
						}
			for(int iteration=0; iteration<2; iteration++)
				for(int attempt=0; attempt<(iteration==0?Integer.MAX_VALUE:3); attempt++){

					i2=x+rand.nextInt(halfSizeX*2+1)-halfSizeX;
					int j2=z+rand.nextInt(halfSizeZ*2+1)-halfSizeZ;

					if (world.isAirBlock(i2,y,j2)){
						int adjacentSolidBlocks=0;

						if (world.getBlockMaterial(i2-1,y,j2).isSolid())++adjacentSolidBlocks;
						if (world.getBlockMaterial(i2+1,y,j2).isSolid())++adjacentSolidBlocks;
						if (world.getBlockMaterial(i2,y,j2-1).isSolid())++adjacentSolidBlocks;
						if (world.getBlockMaterial(i2,y,j2+1).isSolid())++adjacentSolidBlocks;

						if (adjacentSolidBlocks==1){
							world.setBlock(i2,y,j2,Block.chest.blockID,0,2);

							TileEntityChest chest=(TileEntityChest)world.getBlockTileEntity(i2,y,j2);
							if (chest!=null)LootUtil.generateLoot(chest,rand,chestLoot,3,10);

							break;
						}
					}
				}

			world.setBlock(x+1,y,z,Block.web.blockID,0,2);
			world.setBlock(x-1,y,z,Block.web.blockID,0,2);
			world.setBlock(x,y,z-1,Block.web.blockID,0,2);
			world.setBlock(x,y,z+1,Block.web.blockID,0,2);
			world.setBlock(x,y+1,z,Block.web.blockID,0,2);
			world.setBlock(x,y-1,z,Block.web.blockID,0,2);
			world.setBlock(x,y,z,ModBlocks.spiderSpawner.blockID,0,2);
			System.out.println("Spider Dungeon Here: " + x + " "+ y + " "+ z);
			return true;
		}
		
		return false;
	}
}
// @formatter:on
