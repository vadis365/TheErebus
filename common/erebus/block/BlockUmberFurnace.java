package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ErebusMod;
import erebus.ModBlocks;
import erebus.core.proxy.CommonProxy;
import erebus.tileentity.TileEntityUmberFurnace;

public class BlockUmberFurnace extends BlockContainer {

	private final boolean isActive;
	private static boolean keepFurnaceInventory;

	@SideOnly(Side.CLIENT)
	private Icon furnaceIconTop, furnaceIconFront;

	public BlockUmberFurnace(int id, boolean isActive) {
		super(id, Material.rock);
		this.isActive = isActive;
	}

	@Override
	public int idDropped(int id, Random rand, int fortune) {
		return ModBlocks.umberFurnace.blockID;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		setDefaultDirection(world, x, y, z);
	}

	private void setDefaultDirection(World world, int x, int y, int z) {
		if (!world.isRemote) {
			int l = world.getBlockId(x, y, z - 1);
			int i1 = world.getBlockId(x, y, z + 1);
			int j1 = world.getBlockId(x - 1, y, z);
			int k1 = world.getBlockId(x + 1, y, z);
			byte b0 = 3;

			if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
				b0 = 3;
			if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
				b0 = 2;
			if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
				b0 = 5;
			if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
				b0 = 4;

			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side == 1 ? furnaceIconTop : side == 0 ? furnaceIconTop : side != meta ? blockIcon : furnaceIconFront;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockIcon = reg.registerIcon("erebus:umberfurnace_sides");
		furnaceIconFront = reg.registerIcon(isActive ? "erebus:umberfurnace_front_lit" : "erebus:umberfurnace_front");
		furnaceIconTop = reg.registerIcon("erebus:umberfurnace_topbottom");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		TileEntityUmberFurnace tile = (TileEntityUmberFurnace) world.getBlockTileEntity(x, y, z);

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
			player.openGui(ErebusMod.instance, CommonProxy.GUI_ID_UMBER_FURNACE, world, x, y, z);

		return true;

	}

	public static void updateFurnaceBlockState(boolean isActive, World world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);
		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
		keepFurnaceInventory = true;

		if (isActive)
			world.setBlock(x, y, z, ModBlocks.umberFurnace_on.blockID);
		else
			world.setBlock(x, y, z, ModBlocks.umberFurnace.blockID);

		keepFurnaceInventory = false;
		world.setBlockMetadataWithNotify(x, y, z, l, 2);

		if (tileentity != null) {
			tileentity.validate();
			world.setBlockTileEntity(x, y, z, tileentity);
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
	public TileEntity createNewTileEntity(World world) {
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
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		if (!keepFurnaceInventory) {
			Random furnaceRand = new Random();
			TileEntityUmberFurnace tile = (TileEntityUmberFurnace) world.getBlockTileEntity(x, y, z);

			if (tile != null) {
				for (int j1 = 0; j1 < tile.getSizeInventory(); ++j1) {
					ItemStack is = tile.getStackInSlot(j1);

					if (is != null) {
						float f = furnaceRand.nextFloat() * 0.8F + 0.1F;
						float f1 = furnaceRand.nextFloat() * 0.8F + 0.1F;
						float f2 = furnaceRand.nextFloat() * 0.8F + 0.1F;

						while (is.stackSize > 0) {
							int k1 = furnaceRand.nextInt(21) + 10;

							if (k1 > is.stackSize)
								k1 = is.stackSize;

							is.stackSize -= k1;
							EntityItem entityitem = new EntityItem(world, x + f, y + f1, z + f2, new ItemStack(is.itemID, k1, is.getItemDamage()));

							if (is.hasTagCompound())
								entityitem.getEntityItem().setTagCompound((NBTTagCompound) is.getTagCompound().copy());

							float f3 = 0.05F;
							entityitem.motionX = (float) furnaceRand.nextGaussian() * f3;
							entityitem.motionY = (float) furnaceRand.nextGaussian() * f3 + 0.2F;
							entityitem.motionZ = (float) furnaceRand.nextGaussian() * f3;
							world.spawnEntityInWorld(entityitem);
						}
					}
				}
				world.func_96440_m(x, y, z, par5);
			}
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int par2, int par3, int par4, int par5) {
		return Container.calcRedstoneFromInventory((IInventory) world.getBlockTileEntity(par2, par3, par4));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int x, int y, int z) {
		return ModBlocks.umberFurnace.blockID;
	}
}
