package erebus.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import erebus.ModBlocks;
import erebus.ModBlocks.IHasCustomItem;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.api.IErebusEnum;
import erebus.core.helper.Utils;
import erebus.items.block.ItemBlockEnum;
import erebus.tileentity.TileEntityPreservedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPreservedBlock extends Block implements ITileEntityProvider, IHasCustomItem, ISubBlocksBlock {
	public static final PropertyEnum<EnumAmberType> TYPE = PropertyEnum.create("type", EnumAmberType.class);
	public BlockPreservedBlock() {
		super(Material.GLASS);
		setDefaultState(blockState.getBaseState().withProperty(TYPE, EnumAmberType.AMBER));
		setHardness(10F);
		setResistance(10.0F);
		setSoundType(SoundType.GLASS);
		setHarvestLevel("pickaxe", 0);
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE });
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TYPE, EnumAmberType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumAmberType type = state.getValue(TYPE);
		return type.ordinal();
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (!world.isRemote && !player.capabilities.isCreativeMode) {
			TileEntityPreservedBlock tile = Utils.getTileEntity(world, pos, TileEntityPreservedBlock.class);
			if (tile != null) {
				if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player.getHeldItemMainhand()) > 0) {
					NBTTagCompound nbt = new NBTTagCompound();
					tile.writeToNBT(nbt);
					ItemStack stack = new ItemStack(Item.getItemFromBlock(this), 1, damageDropped(world.getBlockState(pos)));
					nbt.setTag("EntityNBT", tile.getEntityNBT());
					stack.setTagCompound(nbt);
					InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
				} else
					tile.spawnTrappedEntity();
				world.removeTileEntity(pos);
			}
		}
	}

	@Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		super.onBlockPlacedBy(world, pos, state, placer, stack);
		if(!world.isRemote && stack.hasTagCompound() && stack.getTagCompound().hasKey("EntityNBT")) {
			TileEntityPreservedBlock tile = Utils.getTileEntity(world, pos, TileEntityPreservedBlock.class);
			if (tile != null) {
				NBTTagCompound nbt = stack.getTagCompound().getCompoundTag("EntityNBT");
				tile.setEntityNBT(nbt);
				tile.rotation = (byte) (((MathHelper.floor((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3)) % 4);
			}
			world.notifyBlockUpdate(pos, state, state, 3);
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
		EnumAmberType type = state.getValue(TYPE);
		if (tile != null) {
			ItemStack stack = new ItemStack(this, 1, damageDropped(state));
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setTag("EntityNBT", tile.getEntityNBT());
			stack.setTagCompound(nbt);
			return stack;
		}
		if(type != null && type.ordinal() != 0)
			return new ItemStack(ModBlocks.AMBER_GLASS, 1, 0);

		return new ItemStack(ModBlocks.AMBER, 1, 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		IBlockState iblockstate = world.getBlockState(pos.offset(side));
		Block block = iblockstate.getBlock();

		return block == this || block == ModBlocks.AMBER ? false : super.shouldSideBeRendered(state, world, pos, side);
	}

	@Override
	public ItemBlock getItemBlock() {
		return ItemBlockEnum.create(this, EnumAmberType.class);
	}

	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (EnumAmberType type : EnumAmberType.values())
			models.add(type.getName());
		return models;
	}

	public enum EnumAmberType implements IErebusEnum {
		AMBER,
		AMBER_GLASS;

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModBlocks.PRESERVED_BLOCK, size, ordinal());
		}

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}
}