package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

public class BlockDoorErebus extends Block{
	
	@SideOnly(Side.CLIENT)
	private IIcon[] top;
	@SideOnly(Side.CLIENT)
	private IIcon[] bottom;
	private String blockType;

	public BlockDoorErebus(String blockType) {
		super(Material.wood);
		this.blockType = blockType;
		float f = 0.5F;
		float f1 = 1.0F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
		this.setLightOpacity(0);
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta){
		return this.bottom[0];
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int metadata){
		if(metadata != 1 && metadata != 0){
			int meta = this.func_150012_g(world, x, y, z);
			int meta1 = meta & 3;
			boolean flag = (meta & 4) != 0;
			boolean flag1 = false;
			boolean flag2 = (meta & 8) != 0;
			
			if(flag){
				if(meta1 == 0 && metadata == 2){
					flag1 = !flag1;
				} else if(meta1 == 1 && metadata == 5){
					flag1 = !flag1;
				} else if(meta1 == 2 && metadata == 3){
					flag1 = !flag1;
				} else if(meta1 == 3 && metadata == 4){
					flag1 = !flag1;
				}
			} else {
				if(meta1 == 0 && metadata == 5){
					flag1 = !flag1;
				} else if(meta1 == 1 && metadata == 3){
					flag1 = !flag1;
				} else if(meta1 == 2 && metadata == 4){
					flag1 = !flag1;
				} else if(meta1 == 3 && metadata == 2){
					flag1 = !flag1;
				}
				
				if((meta & 16) != 0){
					flag1 = !flag1;
				}
			}
			
			return flag2 ? this.top[flag1 ? 1 : 0] : this.bottom[flag1 ? 1 : 0];
		} else {
			return this.bottom[0];
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon){
		this.top = new IIcon[2];
		this.bottom = new IIcon[2];
		this.top[0] = icon.registerIcon(this.getTextureName() + "_upper");
		this.bottom[0] = icon.registerIcon(this.getTextureName() + "_bottom");
		this.top[1] = new IconFlipped(this.top[0], true, false);
		this.bottom[1] = new IconFlipped(this.bottom[0], true, false);
	}
	
	public boolean isOpaqueCube(){
		return false;
	}
	
	public boolean getBlocksMovement(IBlockAccess world, int x, int y, int z){
		int metadata = this.func_150012_g(world, x, y, z);
		return (metadata & 4) != 0;
	}
	
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	public int getRenderType(){
		return 7;
	}
	
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z){
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z){
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z){
		this.func_150011_b(this.func_150012_g(world, x, y, z));
	}
	
	public int func_150013_e(IBlockAccess world, int x, int y, int z){
		return this.func_150012_g(world, x, y, z) & 3;
	}
	
	//test metadata
	public boolean func_150015_f(IBlockAccess world, int x, int y, int z){
		return (this.func_150012_g(world, x, y, z) & 4) != 0;
	}
	
	//Set block bounds
	private void func_150011_b(int metadata){
		float f = 0.1875F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
		int meta = metadata & 3;
		boolean flag = (metadata & 4) != 0;
		boolean flag1 = (metadata & 16) != 0;
		
		if(meta == 0){
			if(flag){
				if(!flag1){
					this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
				} else {
					this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
				}
			} else {
				this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
			}
		} else if(meta == 1){
			if(flag){
				if(!flag1){
					this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				} else {
					this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
				}
			} else {
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
			}
		} else if(meta == 2){
			if(flag){
				if(!flag1){
					this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
				} else {
					this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				}
			} else {
				this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
			}
		}
	}
	
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player){
		
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float x1, float y1, float z1){
		int meta = this.func_150012_g(world, x, y, z);
		int meta1 = meta & 7;
		meta1 ^= 4;
		
		if((meta & 8) == 0){
			world.setBlockMetadataWithNotify(x, y, z, meta1, 2);
			world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
		} else {
			world.setBlockMetadataWithNotify(x, y - 1, z, meta1, 2);
			world.markBlockRangeForRenderUpdate(x, y - 1, z, x, y, z);
		}
		
		world.playAuxSFXAtEntity(player, 1003, x, y, z, 0);
		return true;
	}
	
	//Play sound to world
	public void func_150014_a(World world, int x, int y, int z, boolean flag){
		int meta = this.func_150012_g(world, x, y, z);
		boolean flag1 = (meta & 4) != 0;
		
		if(flag1 != flag){
			int meta1 = meta & 7;
			meta1 ^= 4;
			
			if((meta & 8) == 0){
				world.setBlockMetadataWithNotify(x, y, z, meta1, 2);
				world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			} else {
				world.setBlockMetadataWithNotify(x, y - 1, z, meta1, 2);
				world.markBlockRangeForRenderUpdate(x, y - 1, z, x, y, z);
			}
			
			world.playAuxSFXAtEntity(null, 1003, x, y, z, 0);
		}
	}
	
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor){
		int meta = world.getBlockMetadata(x, y, z);
		if((meta & 8) == 0){
			boolean flag = false;
			
			if(world.getBlock(x, y + 1, z) != this){
				world.setBlockToAir(x, y, z);
				flag = true;
			}
			
			if(!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z)){
				world.setBlockToAir(x, y, z);
				flag = true;
				
				if(world.getBlock(x, y + 1, z) == this){
					world.setBlockToAir(x, y + 1, z);
				}
			}
			
			if(flag){
				if(!world.isRemote){
					this.dropBlockAsItem(world, x, y, z, meta, 0);
				}
			} else {
				boolean flag1 = world.isBlockIndirectlyGettingPowered(x, y, z);
				
				if((flag1 || neighbor.canProvidePower()) && neighbor != this){
					this.func_150014_a(world, x, y, z, flag1);
				}
			}
		} else {
			if(world.getBlock(x, y - 1, z) != this){
				world.setBlockToAir(x, y, z);
			}
			
			if(neighbor != this){
				this.onNeighborBlockChange(world, x, y, z, neighbor);
			}
		}
	}
	
	public Item getItemDropped(int x, Random y, int z){
		return (x & 8) != 0 ? null : ModItems.doorAmberItem;
	}
	
	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec){
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.collisionRayTrace(world, x, y, z, startVec, endVec);
	}
	
	public boolean canPlaceBlockAt(World world, int x, int y, int z){
		return y >= world.getHeight() - 1 ? false : World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && super.canPlaceBlockAt(world, x, y, z) && super.canPlaceBlockAt(world, x, y + 1, z);
	}
	
	public int getMobilityFlag(){
		return 1;
	}
	
	//Test for metadata around
	public int func_150012_g(IBlockAccess world, int x, int y, int z){
		int metadata = world.getBlockMetadata(x, y, z);
		boolean flag = (metadata & 8) != 0;
		int meta, meta1;
		
		if(flag){
			meta = world.getBlockMetadata(x, y - 1, z);
			meta1 = metadata;
		} else {
			meta = metadata;
			meta1 = world.getBlockMetadata(x, y + 1, z);
		}
		
		boolean flag1 = (meta1 & 1) != 0;
		
		return meta & 7 | (flag ? 8 : 0) | (flag1 ? 16 : 0);
	}
	
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z){
		return ModItems.doorAmberItem;
	}
	
	public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player){
		if(player.capabilities.isCreativeMode && (metadata & 8) != 0 && world.getBlock(x, y - 1, z) == this){
			world.setBlockToAir(x, y - 1, z);
		}
	}

}
