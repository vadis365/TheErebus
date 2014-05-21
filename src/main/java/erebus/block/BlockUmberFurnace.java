package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModBlocks;
import erebus.core.helper.Utils;
import erebus.core.proxy.CommonProxy;
import erebus.tileentity.TileEntityUmberFurnace;

public class BlockUmberFurnace extends BlockContainer {

	private final boolean isActive;
	private static boolean keepFurnaceInventory;

	@SideOnly(Side.CLIENT)
	private IIcon furnaceIconTop, furnaceIconFront;

	public BlockUmberFurnace(boolean isActive) {
		super(Material.rock);
		this.isActive = isActive;
	}

	@Override
	public Item getItemDropped(int id, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.umberFurnace);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		setDefaultDirection(world, x, y, z);
	}

	private void setDefaultDirection(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		if (!world.isRemote) {
			Block block = world.getBlock(x, y, z - 1);
			Block block1 = world.getBlock(x, y, z + 1);
			Block block2 = world.getBlock(x - 1, y, z);
			Block block3 = world.getBlock(x + 1, y, z);

			byte b0 = 3;

			if (block1.func_149730_j() && !block.func_149730_j())
				b0 = 2;
			if (block2.func_149730_j() && !block3.func_149730_j())
				b0 = 5;
			if (block3.func_149730_j() && !block2.func_149730_j())
				b0 = 4;

			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? furnaceIconTop : side == 0 ? furnaceIconTop : side != meta ? blockIcon : furnaceIconFront;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon("erebus:umberfurnaceSides");
		furnaceIconFront = reg.registerIcon(isActive ? "erebus:umberfurnaceFrontLit" : "erebus:umberfurnaceFront");
		furnaceIconTop = reg.registerIcon("erebus:umberfurnaceEnd");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		TileEntityUmberFurnace tile = Utils.getTileEntity(world, x, y, z, TileEntityUmberFurnace.class);

		if (player.isSneaking())
			return false;

		if (player.getCurrentEquippedItem() != null) {
			ItemStack oldItem = player.getCurrentEquippedItem();
			ItemStack newItem = tile.fillTankWithBucket(player.inventory.getStackInSlot(player.inventory.currentItem));

			player.inventory.setInventorySlotContents(player.inventory.currentItem, newItem);
			if (!ItemStack.areItemStacksEqual(oldItem, newItem))
				return true;
		}

		if (tile != null)
			player.openGui(Erebus.instance, CommonProxy.GUI_ID_UMBER_FURNACE, world, x, y, z);

		return true;

	}

	public static void updateFurnaceBlockState(boolean isActive, World world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);
		TileEntity tileentity = world.getTileEntity(x, y, z);
		keepFurnaceInventory = true;

		if (isActive)
			world.setBlock(x, y, z, ModBlocks.umberFurnace_on);
		else
			world.setBlock(x, y, z, ModBlocks.umberFurnace);

		keepFurnaceInventory = false;
		world.setBlockMetadataWithNotify(x, y, z, l, 2);

		if (tileentity != null) {
			tileentity.validate();
			world.setTileEntity(x, y, z, tileentity);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		if (isActive) {
			int l = world.getBlockMetadata(x, y, z);
			float f = x + 0.5F;
			float f1 = y + 0.0F + rand.nextFloat() * 6.0F / 16.0F;
			float f2 = z + 0.5F;
			float f3 = 0.52F;
			float f4 = rand.nextFloat() * 0.6F - 0.3F;

			if (l == 4) {
				world.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			} else if (l == 5) {
				world.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			} else if (l == 2) {
				world.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
			} else if (l == 3) {
				world.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityUmberFurnace();
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack is) {
		int l = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		if (l == 0)
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		if (l == 1)
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		if (l == 2)
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		if (l == 3)
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		if (!keepFurnaceInventory) {
			TileEntityUmberFurnace tile = Utils.getTileEntity(world, x, y, z, TileEntityUmberFurnace.class);

			if (tile != null)
				for (int i = 0; i < tile.getSizeInventory(); i++) {
					ItemStack stack = tile.getStackInSlot(i);
					if (stack != null)
						Utils.dropStack(world, x, y, z, stack);
				}
		}
		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
		return Container.calcRedstoneFromInventory(Utils.getTileEntity(world, x, y, z, IInventory.class));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int x, int y, int z) {
		return ModBlocks.umberFurnace.blockID;
	}
}