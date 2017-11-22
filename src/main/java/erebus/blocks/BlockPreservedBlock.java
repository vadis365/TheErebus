package erebus.blocks;

import java.util.Random;

import erebus.ModBlocks;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityPreservedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPreservedBlock extends BlockContainer {

	public BlockPreservedBlock() {
		super(Material.GLASS);
		setHardness(10F);
		setSoundType(SoundType.GLASS);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (!world.isRemote && stack.hasTagCompound()) {
			TileEntityPreservedBlock tile = Utils.getTileEntity(world, pos, TileEntityPreservedBlock.class);
			if (tile != null)
				tile.setEntityNBT(stack.getTagCompound().getCompoundTag("EntityNBT"));
		}
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