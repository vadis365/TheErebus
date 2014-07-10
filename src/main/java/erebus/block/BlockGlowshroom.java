package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import erebus.ModBlocks;

public class BlockGlowshroom extends Block{

	public BlockGlowshroom() {
		super(Material.gourd);
		setTickRandomly(true);
		setLightLevel(0.8F);
	}

	public static final int dataToadStool0 = 0, dataToadStool1 = 1, dataToadStool2 = 2, dataToadStool3 = 3, dataToadStool4 = 4, dataToadStool5 = 5, dataToadStool6 = 6, dataToadStool7 = 7, dataToadStool8 = 8, dataToadStool9 = 9, dataToadStool10 = 10, dataToadStool11 = 11, dataToadStool12 = 12, dataToadStool13 = 13, dataToadStool14 = 14, dataToadStool15 = 15;

	@Override
	public int tickRate(World world) {
		return 5;
	}
	
	@Override
	public int getRenderType() {
		return 0;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return true;
	}
	
	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
		int meta = access.getBlockMetadata(x, y, z);
		float widthMin = 0, heightMin = 0, depthMin = 0;
		float widthMax = 0, heightMax = 0, depthMax = 0;

		switch (meta) {
			case dataToadStool0:
				widthMin= 0F;
				heightMin = 0.25F;
				depthMin= 0F;
				widthMax= 0F;
				heightMax = 0.25F;
				depthMax= 0F;
				break;
		//down
			case dataToadStool1:
				widthMin= 0.1875F;
				heightMin = 0.875F;
				depthMin= 0.1875F;
				widthMax= 0.1875F;
				heightMax = 0F;
				depthMax= 0.1875F;
				break;
			case dataToadStool2:
				widthMin= 0.1875F;
				heightMin = 0.375F;
				depthMin= 0.1875F;
				widthMax= 0.1875F;
				heightMax = 0F;
				depthMax= 0.1875F;
				break;
			case dataToadStool3:
				widthMin= 0.1875F;
				heightMin = 0F;
				depthMin= 0.1875F;
				widthMax= 0.1875F;
				heightMax = 0F;
				depthMax= 0.1875F;
				break;
		//north		
			case dataToadStool4:
				widthMin = 0.375F;
				heightMin = 0.4375F;
				depthMin = 0.75F;
				widthMax = 0.375F;
				heightMax = 0.4375F;
				depthMax = 0F;
				break;
			case dataToadStool5:
				widthMin = 0.25F;
				heightMin = 0.375F;
				depthMin = 0.5F;
				widthMax = 0.25F;
				heightMax = 0.375F;
				depthMax = 0F;
				break;
			case dataToadStool6:
				widthMin = 0.125F;
				heightMin = 0.3125F;
				depthMin = 0.25F;
				widthMax = 0.125F;
				heightMax = 0.3125F;
				depthMax = 0F;
				break;
				
		//south
			case dataToadStool7:
				widthMin = 0.375F;
				heightMin = 0.4375F;
				depthMin = 0F;
				widthMax = 0.375F;
				heightMax = 0.4375F;
				depthMax = 0.75F;
				break;
			case dataToadStool8:
				widthMin = 0.25F;
				heightMin = 0.375F;
				depthMin = 0F;
				widthMax = 0.25F;
				heightMax = 0.375F;
				depthMax = 0.5F;
			case dataToadStool9:
				widthMin = 0.125F;
				heightMin = 0.3125F;
				depthMin = 0F;
				widthMax = 0.125F;
				heightMax = 0.3125F;
				depthMax = 0.25F;
				break;
		//west	
			case dataToadStool10:
				widthMin = 0.75F;
				heightMin = 0.4375F;
				depthMin = 0.375F;
				widthMax = 0F;
				heightMax = 0.4375F;
				depthMax = 0.375F;
				break;
			case dataToadStool11:
				widthMin = 0.5F;
				heightMin = 0.375F;
				depthMin = 0.25F;
				widthMax = 0F;
				heightMax = 0.375F;
				depthMax = 0.25F;
				break;
			case dataToadStool12:
				widthMin = 0.25F;
				heightMin = 0.3125F;
				depthMin = 0.125F;
				widthMax = 0F;
				heightMax = 0.3125F;
				depthMax = 0.125F;
				break;	
		//east
			case dataToadStool13:
				widthMin = 0F;
				heightMin = 0.4375F;
				depthMin = 0.375F;
				widthMax = 0.75F;
				heightMax = 0.4375F;
				depthMax = 0.375F;
				break;
			case dataToadStool14:
				widthMin = 0F;
				heightMin = 0.375F;
				depthMin = 0.25F;
				widthMax = 0.5F;
				heightMax = 0.375F;
				depthMax = 0.25F;
				break;
			case dataToadStool15:
				widthMin = 0F;
				heightMin = 0.3125F;
				depthMin = 0.125F;
				widthMax = 0.25F;
				heightMax = 0.3125F;
				depthMax = 0.125F;
				break;
		
			
		}
		setBlockBounds(0F + widthMin, 0F + heightMin, 0F + depthMin, 1F - widthMax, 1F - heightMax, 1F - depthMax);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		int meta = world.getBlockMetadata(x, y, z);

		// ToadStool maturity
		switch (meta) {
			case dataToadStool4:
				world.setBlock(x, y, z, this, dataToadStool5, 2);
				break;
			case dataToadStool5:
				world.setBlock(x, y, z, this, dataToadStool6, 2);
				break;
			case dataToadStool6:
				world.setBlock(x, y, z, this, dataToadStool0, 2);
				break;
			case dataToadStool7:
				world.setBlock(x, y, z, this, dataToadStool8, 2);
				break;
			case dataToadStool8:
				world.setBlock(x, y, z, this, dataToadStool9, 2);
				break;
			case dataToadStool9:
				world.setBlock(x, y, z, this, dataToadStool0, 2);
				break;
			case dataToadStool10:
				world.setBlock(x, y, z, this, dataToadStool11, 2);
				break;
			case dataToadStool11:
				world.setBlock(x, y, z, this, dataToadStool12, 2);
				break;
			case dataToadStool12:
				world.setBlock(x, y, z, this, dataToadStool0, 2);
				break;
			case dataToadStool13:
				world.setBlock(x, y, z, this, dataToadStool14, 2);
				break;
			case dataToadStool14:
				world.setBlock(x, y, z, this, dataToadStool15, 2);
				break;
			case dataToadStool15:
				world.setBlock(x, y, z, this, dataToadStool0, 2);
				break;
		}
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		return 0;
	}

	@Override
	public Item getItemDropped(int id, Random random, int fortune) {
		return null;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return 	isValidBlock(world.getBlock(x - 1, y, z), getDamageValue(world, x - 1, y, z))
				|| isValidBlock(world.getBlock(x + 1, y, z), getDamageValue(world, x + 1, y, z))
				|| isValidBlock(world.getBlock(x, y, z - 1), getDamageValue(world, x, y, z - 1))
				|| isValidBlock(world.getBlock(x, y, z + 1), getDamageValue(world, x, y, z + 1));
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
			if (side == 2)
				meta = 4;

			if (side == 3)
				meta = 7;

			if (side == 4)
				meta = 10;

			if (side == 5)
				meta = 13;
			return meta;
		}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		int meta = world.getBlockMetadata(x, y, z);
		boolean flag = false;
		
		if (meta == 0 || meta == 4 || meta == 5 || meta == 6)
			if (isValidBlock(world.getBlock(x, y, z + 1), getDamageValue(world, x, y, z + 1)))
				flag = true;
		if (meta == 0 || meta == 7 || meta == 8 || meta == 9)
			if (isValidBlock(world.getBlock(x, y, z - 1), getDamageValue(world, x, y, z - 1)))
				flag = true;
		if (meta == 0 || meta == 10 || meta == 11 || meta == 12)
			if (isValidBlock(world.getBlock(x + 1, y, z), getDamageValue(world, x + 1, y, z)))
				flag = true;
		if (meta == 0 || meta == 13 || meta == 14 || meta == 15)
			if (isValidBlock(world.getBlock(x - 1, y, z), getDamageValue(world, x - 1, y, z)))
				flag = true;

		if (!flag) {
			breakBlock(world, x, y, z, neighbour, meta);
			world.setBlockToAir(x, y, z);
		}

		super.onNeighborBlockChange(world, x, y, z, neighbour);
	}

	private boolean isValidBlock(Block block, int meta) {
		return block == ModBlocks.erebusGlowshroomStalk && meta == 0;
	}
}