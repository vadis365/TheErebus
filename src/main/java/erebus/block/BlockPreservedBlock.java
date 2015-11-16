package erebus.block;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityPreservedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.world.BlockEvent;

public class BlockPreservedBlock extends BlockContainer {

	public BlockPreservedBlock() {
		super(Material.glass);
		setHardness(10F);
		setStepSound(soundTypeGlass);
		setBlockName("erebus.preservedBlock");
	}

	@Override
	public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
		int meta = world.getBlockMetadata(x, y, z) + 1;
		if (meta > 9)
			meta = 6;
		else if (meta > 5)
			meta = 2;
		world.setBlockMetadataWithNotify(x, y, z, meta, 2);
		return true;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
		if (!world.isRemote && stack.hasTagCompound()) {
			TileEntityPreservedBlock tile = Utils.getTileEntity(world, x, y, z, TileEntityPreservedBlock.class);
			if (tile != null)
				tile.setEntityNBT(stack.getTagCompound().getCompoundTag("EntityNBT"));
		}

		int rotation = MathHelper.floor_double(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		if (rotation == 0)
			world.setBlockMetadataWithNotify(x, y, z, 2 + 4 * stack.getItemDamage(), 2);
		else if (rotation == 1)
			world.setBlockMetadataWithNotify(x, y, z, 5 + 4 * stack.getItemDamage(), 2);
		else if (rotation == 2)
			world.setBlockMetadataWithNotify(x, y, z, 3 + 4 * stack.getItemDamage(), 2);
		else if (rotation == 3)
			world.setBlockMetadataWithNotify(x, y, z, 4 + 4 * stack.getItemDamage(), 2);
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
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityPreservedBlock();
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		TileEntityPreservedBlock tile = Utils.getTileEntity(world, x, y, z, TileEntityPreservedBlock.class);
		if (tile != null) {
			ItemStack stack = new ItemStack(this, 1, world.getBlockMetadata(x, y, z) > 5 ? 1 : 0);
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setTag("EntityNBT", tile.getEntityNBT());
			stack.setTagCompound(nbt);
			return stack;
		}

		return new ItemStack(ModBlocks.amber, 1, world.getBlockMetadata(x, y, z) > 5 ? 0 : 1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		Block block = world.getBlock(x, y, z);
		return block == this || block == ModBlocks.amber ? false : super.shouldSideBeRendered(world, x, y, z, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return ModBlocks.amber.getIcon(side, meta > 5 ? 0 : 1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@SubscribeEvent
	public void onBreakEvent(BlockEvent.BreakEvent event) {
		World world = event.world;
		EntityPlayer player = event.getPlayer();
		int x = event.x;
		int y = event.y;
		int z = event.z;

		if (player.capabilities.isCreativeMode)
			return;

		TileEntityPreservedBlock tile = Utils.getTileEntity(world, x, y, z, TileEntityPreservedBlock.class);
		if (tile != null)
			if (EnchantmentHelper.getSilkTouchModifier(player)) {
				ItemStack stack = new ItemStack(this, 1, world.getBlockMetadata(x, y, z) > 5 ? 1 : 0);
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setTag("EntityNBT", tile.getEntityNBT());
				stack.setTagCompound(nbt);
				this.dropBlockAsItem(world, x, y, z, stack);
			} else
				tile.spawnTrappedEntity();
	}
}