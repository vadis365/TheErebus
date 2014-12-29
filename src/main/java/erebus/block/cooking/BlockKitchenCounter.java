package erebus.block.cooking;

import java.util.List;
import java.util.Random;

import mcp.mobius.waila.api.IWailaBlock;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
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
import erebus.core.helper.Utils;
import erebus.core.proxy.CommonProxy;
import erebus.tileentity.TileEntityKitchenCounter;

public class BlockKitchenCounter extends BlockContainer implements IWailaBlock {

	public static IIcon honeyIcon, milkIcon, beetleJuiceIcon, antiVenomIcon;

	public BlockKitchenCounter() {
		super(Material.rock);
		setHardness(2.0F);
		setResistance(5.0F);
		setBlockName("erebus.kitchenCounter");
		setCreativeTab(ModTabs.blocks);
	}

	@Override
	public void registerBlockIcons(IIconRegister icon) {
		honeyIcon = icon.registerIcon("erebus:honeyFlow");
		milkIcon = icon.registerIcon("erebus:milkFlow");
		beetleJuiceIcon = icon.registerIcon("erebus:beetleJuiceFlow");
		antiVenomIcon = icon.registerIcon("erebus:antiVenomFlow");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float x1, float y1, float z1) {
		if (world.isRemote)
			return true;

		TileEntityKitchenCounter tile = Utils.getTileEntity(world, x, y, z, TileEntityKitchenCounter.class);

		if (player.isSneaking())
			return false;

		if (player.getCurrentEquippedItem() != null) {
			ItemStack oldItem = player.getCurrentEquippedItem();
			ItemStack newItem = tile.fillTankWithBucket(player.inventory.getStackInSlot(player.inventory.currentItem));

			player.inventory.setInventorySlotContents(player.inventory.currentItem, newItem);
			if (!ItemStack.areItemStacksEqual(oldItem, newItem))
				return true;
		}
		
		if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().stackSize ==1 && player.getCurrentEquippedItem().getItem() == Items.book)
			player.setCurrentItemOrArmor(0, new ItemStack (ModItems.smoothieBook));

		if (tile != null)
			player.openGui(Erebus.instance, CommonProxy.GUI_ID_KITCHEN_COUNTER, world, x, y, z);

		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, block, par6);
	}

	private void dropItems(World world, int x, int y, int z) {
		Random random = new Random();
		TileEntity tile = world.getTileEntity(x, y, z);

		if (!(tile instanceof IInventory))
			return;

		IInventory inventory = (IInventory) tile;

		for (int c = 0; c < inventory.getSizeInventory(); c++) {
			ItemStack item = inventory.getStackInSlot(c);

			if (item != null && item.stackSize > 0) {
				float rx = random.nextFloat() * 0.8F + 0.1F;
				float ry = random.nextFloat() * 0.8F + 0.1F;
				float rz = random.nextFloat() * 0.8F + 0.1F;

				EntityItem entity = new EntityItem(world, x + rx, y + ry, z + rz, item);

				if (item.hasTagCompound())
					entity.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());

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
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int id) {
		return new TileEntityKitchenCounter();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return ModBlocks.umberstone.getIcon(side, 0);
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		currenttip.add("Kitchen Counter");
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		boolean isCounter = accessor.getTileEntity() instanceof TileEntityKitchenCounter;

		if (isCounter) {
			TileEntityKitchenCounter tile = (TileEntityKitchenCounter) accessor.getTileEntity();

			currenttip.add("Honey : " + tile.getHoneyAmount() + "/" + tile.getTanksFullValue());
			currenttip.add("Milk : " + tile.getMilkAmount() + "/" + tile.getTanksFullValue());
			currenttip.add("Beetle Juice : " + tile.getBeetleJuiceAmount() + "/" + tile.getTanksFullValue());
			currenttip.add("Anti-Venom : " + tile.getAntiVenomAmount() + "/" + tile.getTanksFullValue());
		}

		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return null;
	}

}
