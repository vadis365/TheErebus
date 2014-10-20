package erebus.block.cooking;

import java.util.List;
import java.util.Random;

import mcp.mobius.waila.api.IWailaBlock;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.core.proxy.CommonProxy;
import erebus.lib.Reference;
import erebus.tileentity.TileEntityKitchenCounter;

public class BlockKitchenCounter extends BlockContainer implements IWailaBlock{

	public BlockKitchenCounter() {
		super(Material.wood);
		setHardness(2.0F);
		setResistance(5.0F);
		setBlockName("kitchenCounter");
		setCreativeTab(ModTabs.blocks);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float x1, float y1, float z1){
		TileEntity tile = world.getTileEntity(x, y, z);
		if(world.isRemote){
			return true;
		} else {
			if(tile instanceof TileEntityKitchenCounter){
				TileEntityKitchenCounter kitchen = (TileEntityKitchenCounter) tile;
				
				ItemStack stack = player.inventory.getCurrentItem();
				
				if(stack == null || tile == null || player.isSneaking()){
					player.openGui(Erebus.instance, CommonProxy.GUI_ID_KITCHEN_COUNTER, world, x, y, z);
					return true;
				} else {
					if(stack.getItem() == Items.milk_bucket){
						kitchen.addMilk(1000);
						player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));
						return true;
					} else if(stack.getItem() == ModItems.bambucketHoney){
						kitchen.addHoney(1500);
						player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ModItems.bambucket));
						return true;
					} else if(stack.getItem() == ModItems.bucketHoney){
						kitchen.addHoney(1000);
						player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));
						return true;
					} else if(stack.getItem() == ModItems.bambucketBeetleJuice){
						kitchen.addBeetleJuice(1500);
						player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ModItems.bambucket));
						return true;
					} else if(stack.getItem() == ModItems.bucketBeetleJuice){
						kitchen.addBeetleJuice(1000);
						player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));
						return true;
					} else if(stack.getItem() == ModItems.bambucketAntiVenom){
						kitchen.addAntiVenom(1500);
						player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ModItems.bambucket));
						return true;
					} else if(stack.getItem() == ModItems.bucketAntiVenom){
						kitchen.addAntiVenom(1000);
						player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		}
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6){
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, block, par6);
	}
	
	private void dropItems(World world, int x, int y, int z){
		Random random = new Random();
		TileEntity tile = world.getTileEntity(x, y, z);
		
		if(!(tile instanceof IInventory)){
			return;
		}
		
		IInventory inventory = (IInventory) tile;
		
		for(int c = 0; c < inventory.getSizeInventory(); c++){
			ItemStack item = inventory.getStackInSlot(c);
			
			if(item != null && item.stackSize > 0){
				float rx = random.nextFloat() * 0.8F + 0.1F;
				float ry = random.nextFloat() * 0.8F + 0.1F;
				float rz = random.nextFloat() * 0.8F + 0.1F;
				
				EntityItem entity = new EntityItem(world, x + rx, y + ry, z + rz, item);
				
				if(item.hasTagCompound()){
					entity.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
				}
				
				float factor = 0.05F;
				entity.motionX = random.nextGaussian() * factor;
				entity.motionY = random.nextGaussian() * factor + 0.2F;
				entity.motionZ = random.nextGaussian() * factor;
				world.spawnEntityInWorld(entity);
				item.stackSize = 0;
			}
		}
	}
	
	@Override
	public int getRenderType(){
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int id) {
		return new TileEntityKitchenCounter();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return ModBlocks.umberstone.getIcon(side, 0);
	}

	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		boolean isCounter = accessor.getTileEntity() instanceof TileEntityKitchenCounter;
		
		if(isCounter){
			TileEntityKitchenCounter tile = (TileEntityKitchenCounter) accessor.getTileEntity();
			
			currenttip.add("Honey : " + tile.getHoneyAmount() + "/" + tile.getTanksFullValue());
			currenttip.add("Milk : " + tile.getMilkAmount() + "/" + tile.getTanksFullValue());
			currenttip.add("BeetleJuice : " + tile.getBeetleJuiceAmount() + "/" + tile.getTanksFullValue());
			currenttip.add("AntiVenom : " + tile.getAntiVenomAmount() + "/" + tile.getTanksFullValue());
		}
		
		return currenttip;
	}

	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return null;
	}

}
