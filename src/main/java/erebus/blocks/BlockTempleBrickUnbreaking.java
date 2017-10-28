package erebus.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import erebus.ModBlocks;
import erebus.ModBlocks.IHasCustomItem;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.api.IErebusEnum;
import erebus.items.ItemMaterials;
import erebus.items.block.ItemBlockEnum;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTempleBrickUnbreaking extends Block implements IHasCustomItem, ISubBlocksBlock {

	public static final PropertyEnum<EnumTempleBrickType> TYPE = PropertyEnum.create("type", EnumTempleBrickType.class);

	public BlockTempleBrickUnbreaking() {
		super(Material.ROCK);
		setSoundType(SoundType.STONE);
		setBlockUnbreakable();
		setResistance(6000000.0F);
		setCreativeTab(ModTabs.BLOCKS);
		setDefaultState(blockState.getBaseState().withProperty(TYPE, EnumTempleBrickType.TEMPLE_BRICK));
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
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (tab == ModTabs.BLOCKS)
			for (EnumTempleBrickType type : EnumTempleBrickType.values())
				list.add(new ItemStack(this, 1, type.ordinal()));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TYPE, EnumTempleBrickType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumTempleBrickType type = state.getValue(TYPE);
		return type.ordinal();
	}

	@Override
	 public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		if (player.isSneaking())
			return false;

		ItemStack stack = player.getHeldItem(hand);
		EnumTempleBrickType type = state.getValue(TYPE);
		if (!stack.isEmpty()) {
			switch (type) {
				case TEMPLE_BRICK:
				case TEMPLE_BRICK_JADE_ACTIVE:
				case TEMPLE_BRICK_EXO_ACTIVE:
				case TEMPLE_BRICK_CREAM_ACTIVE:
				case TEMPLE_BRICK_EYE_ACTIVE:
				case TEMPLE_BRICK_STRING_ACTIVE:
					return true;
				case TEMPLE_BRICK_JADE:
					if (stack.getItem() == ModItems.MATERIALS && stack.getItemDamage() == ItemMaterials.EnumErebusMaterialsType.JADE.ordinal()) {
						world.setBlockState(pos, this.getDefaultState().withProperty(TYPE, EnumTempleBrickType.TEMPLE_BRICK_JADE_ACTIVE), 3);
						if (!player.capabilities.isCreativeMode)
							stack.shrink(1);
					}
					return true;
				case TEMPLE_BRICK_EXO:
					if (stack.getItem() == ModItems.MATERIALS && stack.getItemDamage() == ItemMaterials.EnumErebusMaterialsType.PLATE_EXO.ordinal()) {
						world.setBlockState(pos, this.getDefaultState().withProperty(TYPE, EnumTempleBrickType.TEMPLE_BRICK_EXO_ACTIVE), 3);
						if (!player.capabilities.isCreativeMode)
							stack.shrink(1);
					}
					return true;
				case TEMPLE_BRICK_CREAM:
					if (stack.getItem() == Items.MAGMA_CREAM) {
						world.setBlockState(pos, this.getDefaultState().withProperty(TYPE, EnumTempleBrickType.TEMPLE_BRICK_CREAM_ACTIVE), 3);
						if (!player.capabilities.isCreativeMode)
							stack.shrink(1);
					}
					return true;
				case TEMPLE_BRICK_EYE:
					if (stack.getItem() == ModItems.MATERIALS && stack.getItemDamage() == ItemMaterials.EnumErebusMaterialsType.MAGMA_CRAWLER_EYE.ordinal()) {
						world.setBlockState(pos, this.getDefaultState().withProperty(TYPE, EnumTempleBrickType.TEMPLE_BRICK_EYE_ACTIVE), 3);
						if (!player.capabilities.isCreativeMode)
							stack.shrink(1);
					}
					return true;
				case TEMPLE_BRICK_STRING:
					if (stack.getItem() == Items.STRING) {
						world.setBlockState(pos, this.getDefaultState().withProperty(TYPE, EnumTempleBrickType.TEMPLE_BRICK_STRING_ACTIVE), 3);
						if (!player.capabilities.isCreativeMode)
							stack.shrink(1);
					}
					return true;
			}
		}
		return true;
	}

	@Override
	public ItemBlock getItemBlock() {
		return ItemBlockEnum.create(this, EnumTempleBrickType.class);
	}

	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (EnumTempleBrickType type : EnumTempleBrickType.values())
			models.add(type.getName());
		return models;
	}

	public enum EnumTempleBrickType implements IErebusEnum {
		TEMPLE_BRICK,
		TEMPLE_BRICK_JADE,
		TEMPLE_BRICK_EXO,
		TEMPLE_BRICK_CREAM,
		TEMPLE_BRICK_EYE,
		TEMPLE_BRICK_STRING,
		TEMPLE_BRICK_JADE_ACTIVE,
		TEMPLE_BRICK_EXO_ACTIVE,
		TEMPLE_BRICK_CREAM_ACTIVE,
		TEMPLE_BRICK_EYE_ACTIVE,
		TEMPLE_BRICK_STRING_ACTIVE;

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModBlocks.TEMPLE_BRICK_UNBREAKING, size, ordinal());
		}

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}
}