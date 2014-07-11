package erebus.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;

public class BlockGlowshroomStalk extends Block{

	public BlockGlowshroomStalk() {
		super(Material.gourd);
		this.setTickRandomly(true);
	}

	public static final int dataStalk0 = 0, dataStalk1 = 1, dataStalk2 = 2, dataStalk3 = 3, dataStalk4 = 4, dataStalk5 = 5, dataStalk6 = 6, dataStalk7 = 7, dataStalk8 = 8, dataStalk9 = 9, dataStalk10 = 10, dataStalk11 = 11, dataStalk12 = 12, dataStalk13 = 13, dataStalk14 = 14, dataStalk15 = 15;

	@Override
	public int tickRate(World world) {
		return 5;
	}
	
	@Override
	public int getRenderType() {
		return BlockRenderIDs.GLOWSHROOM_STALK.id();
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
			case dataStalk0:
				widthMin= 0F;
				heightMin = 0F;
				depthMin= 0F;
				widthMax= 0F;
				heightMax = 0F;
				depthMax= 0F;
				break;
		//down
			case dataStalk1:
				widthMin= 0.1875F;
				heightMin = 0.875F;
				depthMin= 0.1875F;
				widthMax= 0.1875F;
				heightMax = 0F;
				depthMax= 0.1875F;
				break;
			case dataStalk2:
				widthMin= 0.1875F;
				heightMin = 0.375F;
				depthMin= 0.1875F;
				widthMax= 0.1875F;
				heightMax = 0F;
				depthMax= 0.1875F;
				break;
			case dataStalk3:
				widthMin= 0.1875F;
				heightMin = 0F;
				depthMin= 0.1875F;
				widthMax= 0.1875F;
				heightMax = 0F;
				depthMax= 0.1875F;
				break;
		//north		
			case dataStalk4:
				widthMin = 0.3125F;
				heightMin = 0.3125F;
				depthMin = 0.3125F;
				widthMax = 0.3125F;
				heightMax = 0.3125F;
				depthMax = 0F;
				break;
			case dataStalk5:
				widthMin = 0.1875F;
				heightMin = 0.1875F;
				depthMin = 0F;
				widthMax = 0.1875F;
				heightMax = 0.1875F;
				depthMax = 0F;
				break;
			case dataStalk6:
				widthMin = 0.3125F;
				heightMin = 0.3125F;
				depthMin = 0.3125F;
				widthMax = 0.3125F;
				heightMax = 0F;
				depthMax = 0F;
				break;
				
		//south
			case dataStalk7:
				widthMin = 0.3125F;
				heightMin = 0.3125F;
				depthMin = 0F;
				widthMax = 0.3125F;
				heightMax = 0.3125F;
				depthMax = 0.3125F;
				break;
			case dataStalk8:
				widthMin = 0.1875F;
				heightMin = 0.1875F;
				depthMin = 0F;
				widthMax = 0.1875F;
				heightMax = 0.1875F;
				depthMax = 0F;
				break;
			case dataStalk9:
				widthMin = 0.3125F;
				heightMin = 0.3125F;
				depthMin = 0F;
				widthMax = 0.3125F;
				heightMax = 0F;
				depthMax = 0.3125F;
				break;
		//west
			case dataStalk10:
				widthMin = 0.3125F;
				heightMin = 0.3125F;
				depthMin = 0.3125F;
				widthMax = 0F;
				heightMax = 0.3125F;
				depthMax = 0.3125F;
				break;
			case dataStalk11:
				widthMin = 0F;
				heightMin = 0.1875F;
				depthMin = 0.1875F;
				widthMax = 0F;
				heightMax = 0.1875F;
				depthMax = 0.1875F;
				break;
			case dataStalk12:
				widthMin = 0.3125F;
				heightMin = 0.3125F;
				depthMin = 0.3125F;
				widthMax = 0F;
				heightMax = 0F;
				depthMax = 0.3125F;
				break;
		//east
			case dataStalk13:
				widthMin = 0F;
				heightMin = 0.3125F;
				depthMin = 0.3125F;
				widthMax = 0.3125F;
				heightMax = 0.3125F;
				depthMax = 0.3125F;
				break;
			case dataStalk14:
				widthMin = 0F;
				heightMin = 0.1875F;
				depthMin = 0.1875F;
				widthMax = 0F;
				heightMax = 0.1875F;
				depthMax = 0.1875F;
				break;
			case dataStalk15:
				widthMin = 0F;
				heightMin = 0.3125F;
				depthMin = 0.3125F;
				widthMax = 0.3125F;
				heightMax = 0F;
				depthMax = 0.3125F;
				break;		
		}
		setBlockBounds(0F + widthMin, 0F + heightMin, 0F + depthMin, 1F - widthMax, 1F - heightMax, 1F - depthMax);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB box, List list, Entity entity) {
		int meta = world.getBlockMetadata(x, y, z);
		float widthMin = 0, heightMin = 0, depthMin = 0;
		float widthMax = 0, heightMax = 0, depthMax = 0;

		switch (meta) {
		case dataStalk0:
			widthMin= 0F;
			heightMin = 0F;
			depthMin= 0F;
			widthMax= 0F;
			heightMax = 0F;
			depthMax= 0F;
			break;
	//down
		case dataStalk1:
			widthMin= 0.1875F;
			heightMin = 0.875F;
			depthMin= 0.1875F;
			widthMax= 0.1875F;
			heightMax = 0F;
			depthMax= 0.1875F;
			break;
		case dataStalk2:
			widthMin= 0.1875F;
			heightMin = 0.375F;
			depthMin= 0.1875F;
			widthMax= 0.1875F;
			heightMax = 0F;
			depthMax= 0.1875F;
			break;
		case dataStalk3:
			widthMin= 0.1875F;
			heightMin = 0F;
			depthMin= 0.1875F;
			widthMax= 0.1875F;
			heightMax = 0F;
			depthMax= 0.1875F;
			break;
	//north		
		case dataStalk4:
			widthMin = 0.3125F;
			heightMin = 0.3125F;
			depthMin = 0.3125F;
			widthMax = 0.3125F;
			heightMax = 0.3125F;
			depthMax = 0F;
			break;
		case dataStalk5:
			widthMin = 0.1875F;
			heightMin = 0.1875F;
			depthMin = 0F;
			widthMax = 0.1875F;
			heightMax = 0.1875F;
			depthMax = 0F;
			break;
		case dataStalk6:
			widthMin = 0.3125F;
			heightMin = 0.3125F;
			depthMin = 0.3125F;
			widthMax = 0.3125F;
			heightMax = 0F;
			depthMax = 0F;
			break;
			
	//south
		case dataStalk7:
			widthMin = 0.3125F;
			heightMin = 0.3125F;
			depthMin = 0F;
			widthMax = 0.3125F;
			heightMax = 0.3125F;
			depthMax = 0.3125F;
			break;
		case dataStalk8:
			widthMin = 0.1875F;
			heightMin = 0.1875F;
			depthMin = 0F;
			widthMax = 0.1875F;
			heightMax = 0.1875F;
			depthMax = 0F;
			break;
		case dataStalk9:
			widthMin = 0.3125F;
			heightMin = 0.3125F;
			depthMin = 0F;
			widthMax = 0.3125F;
			heightMax = 0F;
			depthMax = 0.3125F;
			break;
	//west
		case dataStalk10:
			widthMin = 0.3125F;
			heightMin = 0.3125F;
			depthMin = 0.3125F;
			widthMax = 0F;
			heightMax = 0.3125F;
			depthMax = 0.3125F;
			break;
		case dataStalk11:
			widthMin = 0F;
			heightMin = 0.1875F;
			depthMin = 0.1875F;
			widthMax = 0F;
			heightMax = 0.1875F;
			depthMax = 0.1875F;
			break;
		case dataStalk12:
			widthMin = 0.3125F;
			heightMin = 0.3125F;
			depthMin = 0.3125F;
			widthMax = 0F;
			heightMax = 0F;
			depthMax = 0.3125F;
			break;
	//east
		case dataStalk13:
			widthMin = 0F;
			heightMin = 0.3125F;
			depthMin = 0.3125F;
			widthMax = 0.3125F;
			heightMax = 0.3125F;
			depthMax = 0.3125F;
			break;
		case dataStalk14:
			widthMin = 0F;
			heightMin = 0.1875F;
			depthMin = 0.1875F;
			widthMax = 0F;
			heightMax = 0.1875F;
			depthMax = 0.1875F;
			break;
		case dataStalk15:
			widthMin = 0F;
			heightMin = 0.3125F;
			depthMin = 0.3125F;
			widthMax = 0.3125F;
			heightMax = 0F;
			depthMax = 0.3125F;
			break;		
		}
		setBlockBounds(0F + widthMin, 0F + heightMin, 0F + depthMin, 1F - widthMax, 1F - heightMax, 1F - depthMax);
		super.addCollisionBoxesToList(world, x, y, z, box, list, entity);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		int meta = world.getBlockMetadata(x, y, z);

		int randomiseSide = rand.nextInt(5);
		int offset = 1;

		if (meta == 0) {
			switch (randomiseSide) {
			case 0:
				if (world.isAirBlock(x, y - offset, z))
					world.setBlock(x, y - offset, z, ModBlocks.erebusGlowshroomStalk, dataStalk1, 2);
				break;
			case 1:
				if (world.isAirBlock(x, y , z - offset))
					world.setBlock(x, y , z - offset, ModBlocks.erebusGlowshroomStalk, dataStalk4, 2);
				break;
			case 2:
				if (world.isAirBlock(x, y , z + offset))
					world.setBlock(x, y , z + offset, ModBlocks.erebusGlowshroomStalk, dataStalk7, 2);
				break;
			case 3:
				if (world.isAirBlock(x - offset, y , z))
					world.setBlock(x - offset, y , z, ModBlocks.erebusGlowshroomStalk, dataStalk10, 2);
				break;
			case 4:
				if (world.isAirBlock(x + offset, y , z))
					world.setBlock(x + offset, y , z, ModBlocks.erebusGlowshroomStalk, dataStalk13, 2);
				break;
			}
		}

		// Stalk maturity
		switch (meta) {
			case dataStalk0:
				if(rand.nextInt(5)== 0) {
					if (world.isAirBlock(x, y + 1 , z))
						world.setBlock(x, y + 1 , z, ModBlocks.erebusGlowshroom, 0, 2);
						}
				break;
			case dataStalk1:
				world.setBlock(x, y, z, this, dataStalk2, 2);
				break;
			case dataStalk2:
				world.setBlock(x, y, z, this, dataStalk3, 2);
				break;
			case dataStalk3:
				world.setBlock(x, y, z, this, dataStalk0, 2);
				break;
			case dataStalk4:
				if (world.getBlock(x, y, z + 1) == ModBlocks.erebusGlowshroomStalk && getDamageValue(world, x, y, z + 1) == 0)
				world.setBlock(x, y, z, this, dataStalk5, 2);
				if(rand.nextInt(2)== 0) {
					if (world.getBlock(x, y, z + 1) == ModBlocks.erebusGlowshroomStalk && getDamageValue(world, x, y, z + 1) == 5 && world.isAirBlock(x, y + 1 , z)) {
						world.setBlock(x, y, z, this, dataStalk6, 2);
						world.setBlock(x, y + 1 , z, ModBlocks.erebusGlowshroom, 0, 2);
						}
					else {
						world.setBlock(x, y, z, this, dataStalk5, 2);
						world.setBlock(x, y, z + 1, this, dataStalk0, 2);
					}
				}
				break;
			case dataStalk5:
				if (world.getBlock(x, y, z + 1) == ModBlocks.erebusGlowshroomStalk && getDamageValue(world, x, y, z + 1) == 0)
					if(rand.nextInt(2)== 0 && world.isAirBlock(x, y , z -1)) {
						world.setBlock(x, y, z -1, this, dataStalk4, 2);
					}
					else if((world.getBlock(x, y, z -1) == ModBlocks.erebusGlowshroomStalk) && getDamageValue(world, x, y, z - 1) == 4) {
						break;
						}
					else if((world.getBlock(x, y, z -1) == ModBlocks.erebusGlowshroomStalk) && getDamageValue(world, x, y, z - 1) == 6) {
						break;
						}
					else {
						world.setBlock(x, y, z, this, dataStalk0, 2);
					}
				break;
			case dataStalk6:
				//world.setBlock(x, y, z, this, dataStalk0, 2);
				break;
			case dataStalk7:
				if (world.getBlock(x, y, z - 1) == ModBlocks.erebusGlowshroomStalk && getDamageValue(world, x, y, z - 1) == 0)
				world.setBlock(x, y, z, this, dataStalk8, 2);
				if(rand.nextInt(2)== 0) {
					if (world.getBlock(x, y, z - 1) == ModBlocks.erebusGlowshroomStalk && getDamageValue(world, x, y, z - 1) == 8 && world.isAirBlock(x, y + 1 , z)) {
						world.setBlock(x, y, z, this, dataStalk9, 2);
						world.setBlock(x, y + 1 , z, ModBlocks.erebusGlowshroom, 0, 2);
						}
					else {
						world.setBlock(x, y, z, this, dataStalk8, 2);
						world.setBlock(x, y, z - 1, this, dataStalk0, 2);
					}
				}
				break;
	
			case dataStalk8:
				if (world.getBlock(x, y, z - 1) == ModBlocks.erebusGlowshroomStalk && getDamageValue(world, x, y, z - 1) == 0)
					if(rand.nextInt(2)== 0 && world.isAirBlock(x, y , z + 1)) {
						world.setBlock(x, y, z + 1, this, dataStalk7, 2);
					}
					else if((world.getBlock(x, y, z + 1) == ModBlocks.erebusGlowshroomStalk) && getDamageValue(world, x, y, z + 1) == 7) {
						break;
						}
					else if((world.getBlock(x, y, z + 1) == ModBlocks.erebusGlowshroomStalk) && getDamageValue(world, x, y, z + 1) == 9) {
						break;
						}
					else {
						world.setBlock(x, y, z, this, dataStalk0, 2);
					}
				break;
			case dataStalk9:
				//world.setBlock(x, y, z, this, dataStalk0, 2);
				break;
			case dataStalk10:
				if (world.getBlock(x + 1, y, z) == ModBlocks.erebusGlowshroomStalk && getDamageValue(world, x + 1, y, z) == 0)
				world.setBlock(x, y, z, this, dataStalk11, 2);
				if(rand.nextInt(2)== 0) {
					if (world.getBlock(x + 1, y, z) == ModBlocks.erebusGlowshroomStalk && getDamageValue(world, x + 1, y, z) == 11 && world.isAirBlock(x, y + 1 , z)) {
						world.setBlock(x, y, z, this, dataStalk12, 2);
						world.setBlock(x, y + 1 , z, ModBlocks.erebusGlowshroom, 0, 2);
						}
					else {
						world.setBlock(x, y, z, this, dataStalk11, 2);
						world.setBlock(x + 1, y, z, this, dataStalk0, 2);
					}
				}
				break;
	
			case dataStalk11:
				if (world.getBlock(x + 1, y, z) == ModBlocks.erebusGlowshroomStalk && getDamageValue(world, x + 1, y, z) == 0)
					if(rand.nextInt(2)== 0 && world.isAirBlock(x - 1, y , z)) {
						world.setBlock(x - 1, y, z, this, dataStalk10, 2);
					}
					else if((world.getBlock(x - 1, y, z) == ModBlocks.erebusGlowshroomStalk) && getDamageValue(world, x - 1, y, z) == 10) {
						break;
						}
					else if((world.getBlock(x - 1, y, z) == ModBlocks.erebusGlowshroomStalk) && getDamageValue(world, x - 1, y, z) == 12) {
						break;
						}
					else {
						world.setBlock(x, y, z, this, dataStalk0, 2);
					}
				break;
			case dataStalk12:
				//world.setBlock(x, y, z, this, dataStalk0, 2);
				break;
			case dataStalk13:
				if (world.getBlock(x - 1, y, z) == ModBlocks.erebusGlowshroomStalk && getDamageValue(world, x - 1, y, z) == 0)
				world.setBlock(x, y, z, this, dataStalk14, 2);
				if(rand.nextInt(2)== 0) {
					if (world.getBlock(x - 1, y, z) == ModBlocks.erebusGlowshroomStalk && getDamageValue(world, x - 1, y, z) == 14 && world.isAirBlock(x, y + 1 , z)) {
						world.setBlock(x, y, z, this, dataStalk15, 2);
						world.setBlock(x, y + 1 , z, ModBlocks.erebusGlowshroom, 0, 2);
						}
					else {
						world.setBlock(x, y, z, this, dataStalk14, 2);
						world.setBlock(x - 1, y, z, this, dataStalk0, 2);
					}
				}
				break;
	
			case dataStalk14:
				if (world.getBlock(x - 1, y, z) == ModBlocks.erebusGlowshroomStalk && getDamageValue(world, x - 1, y, z) == 0)
					if(rand.nextInt(2)== 0 && world.isAirBlock(x + 1, y , z)) {
						world.setBlock(x + 1, y, z, this, dataStalk13, 2);
					}
					else if((world.getBlock(x + 1, y, z) == ModBlocks.erebusGlowshroomStalk) && getDamageValue(world, x + 1, y, z) == 13) {
						break;
						}
					else if((world.getBlock(x + 1, y, z) == ModBlocks.erebusGlowshroomStalk) && getDamageValue(world, x + 1, y, z) == 15) {
						break;
						}
					else {
						world.setBlock(x, y, z, this, dataStalk0, 2);
					}
				break;
			case dataStalk15:
				//world.setBlock(x, y, z, this, dataStalk0, 2);
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
		return isValidBlock(world.getBlock(x, y + 1, z), getDamageValue(world, x, y + 1, z))
				|| isValidBlock(world.getBlock(x - 1, y, z), getDamageValue(world, x - 1, y, z))
				|| isValidBlock(world.getBlock(x + 1, y, z), getDamageValue(world, x + 1, y, z))
				|| isValidBlock(world.getBlock(x, y, z - 1), getDamageValue(world, x, y, z - 1))
				|| isValidBlock(world.getBlock(x, y, z + 1), getDamageValue(world, x, y, z + 1));
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
			if (side == 0)
				meta = 1;

			if (side == 2)
				meta = 4;

			if (side == 3)
				meta = 7;

			if (side == 4)
				meta = 10;

			if (side == 5)
				meta = 13;

			return 0;// meta;
		}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		int meta = world.getBlockMetadata(x, y, z);
		boolean flag = false;
		if (meta == 0)
			flag = true;
		if (meta == 1 || meta == 2|| meta == 3)
			if (isValidBlock(world.getBlock(x, y + 1, z), getDamageValue(world, x, y + 1, z)))
				flag = true;
		if (meta == 4 || meta == 5 || meta == 6)
			if (isValidBlock(world.getBlock(x, y, z + 1), getDamageValue(world, x, y, z + 1)))
				flag = true;
		if (meta == 7 || meta == 8 || meta == 9)
			if (isValidBlock(world.getBlock(x, y, z - 1), getDamageValue(world, x, y, z - 1)))
				flag = true;
		if (meta == 10 || meta == 11 || meta == 12)
			if (isValidBlock(world.getBlock(x + 1, y, z), getDamageValue(world, x + 1, y, z)))
				flag = true;
		if (meta == 13 || meta == 14 || meta == 15)
			if (isValidBlock(world.getBlock(x - 1, y, z), getDamageValue(world, x - 1, y, z)))
				flag = true;

		if (!flag) {
			breakBlock(world, x, y, z, neighbour, meta);
			world.setBlockToAir(x, y, z);
		}

		super.onNeighborBlockChange(world, x, y, z, neighbour);
	}

	private boolean isValidBlock(Block block, int meta) {
		return block.getMaterial().blocksMovement() || block == this && meta == 0;
	}
}