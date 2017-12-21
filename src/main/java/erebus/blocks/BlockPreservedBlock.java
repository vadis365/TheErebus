package erebus.blocks;

import java.util.Random;

import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityPreservedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPreservedBlock extends Block implements ITileEntityProvider {

	public BlockPreservedBlock() {
		super(Material.GLASS);
		setHardness(10F);
		setResistance(10.0F);
		setSoundType(SoundType.GLASS);
		setCreativeTab(ModTabs.BLOCKS);
		setHarvestLevel("pickaxe", 0);
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
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityPreservedBlock();
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		TileEntityPreservedBlock tile = Utils.getTileEntity(world, pos, TileEntityPreservedBlock.class);
		if (tile != null) {
			ItemStack stack = new ItemStack(this, 1, world.getBlockState(pos) > 5 ? 1 : 0);
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setTag("EntityNBT", tile.getEntityNBT());
			stack.setTagCompound(nbt);
			return stack;
		}

		return new ItemStack(ModBlocks.AMBER, 1, world.getBlockState(pos) > 5 ? 0 : 1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		IBlockState iblockstate = world.getBlockState(pos.offset(side));
		Block block = iblockstate.getBlock();

		return block == this || block == ModBlocks.AMBER ? false : super.shouldSideBeRendered(state, world, pos, side);
	}

	@SubscribeEvent
	public void onBreakEvent(BlockEvent.BreakEvent event) {
		World world = event.getWorld();
		EntityPlayer player = event.getPlayer();
		BlockPos pos = event.getPos();

		if (player.capabilities.isCreativeMode)
			return;

		TileEntityPreservedBlock tile = Utils.getTileEntity(world, pos, TileEntityPreservedBlock.class);
		if (tile != null)
			if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player.getHeldItemMainhand()) > 0) {
				ItemStack stack = new ItemStack(this, 1, world.getBlockState(pos) > 5 ? 1 : 0);
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setTag("EntityNBT", tile.getEntityNBT());
				stack.setTagCompound(nbt);
				this.dropBlockAsItem(world, pos, stack);
			} else
				tile.spawnTrappedEntity();
	}
}