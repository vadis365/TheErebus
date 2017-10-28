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
import erebus.items.ItemDungeonIdols.EnumIdolType;
import erebus.items.block.ItemBlockEnum;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCapstone extends Block implements IHasCustomItem, ISubBlocksBlock {
	
	public static final PropertyEnum<EnumCapstoneType> TYPE = PropertyEnum.create("type", EnumCapstoneType.class);

	public BlockCapstone() {
		super(Material.ROCK);
		setSoundType(SoundType.STONE);
		setBlockUnbreakable();
		setResistance(6000000.0F);
		setCreativeTab(ModTabs.BLOCKS);
		setDefaultState(blockState.getBaseState().withProperty(TYPE, EnumCapstoneType.CAPSTONE));
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
			for (EnumCapstoneType type : EnumCapstoneType.values())
				list.add(new ItemStack(this, 1, type.ordinal()));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TYPE, EnumCapstoneType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumCapstoneType type = state.getValue(TYPE);
		return type.ordinal();
	}

	@Override
	 public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		if (player.isSneaking())
			return false;

		ItemStack stack = player.getHeldItem(hand);
		EnumCapstoneType type = state.getValue(TYPE);
		if (!stack.isEmpty()) {
			switch (type) {
				case CAPSTONE:
				case CAPSTONE_MUD_ACTIVE:
				case CAPSTONE_IRON_ACTIVE:
				case CAPSTONE_GOLD_ACTIVE:
				case CAPSTONE_JADE_ACTIVE:
					return true;
				case CAPSTONE_MUD:
					if (stack.getItem() == ModItems.IDOLS && stack.getItemDamage() == EnumIdolType.IDOL_MUD.ordinal()) {
						world.setBlockState(pos, this.getDefaultState().withProperty(TYPE, EnumCapstoneType.CAPSTONE_MUD_ACTIVE), 3);
						if (!player.capabilities.isCreativeMode)
							stack.shrink(1);
					}
					return true;
				case CAPSTONE_IRON:
					if (stack.getItem() == ModItems.IDOLS && stack.getItemDamage() == EnumIdolType.IDOL_IRON.ordinal()) {
						world.setBlockState(pos, this.getDefaultState().withProperty(TYPE, EnumCapstoneType.CAPSTONE_IRON_ACTIVE), 3);
						if (!player.capabilities.isCreativeMode)
							stack.shrink(1);
					}
					return true;
				case CAPSTONE_GOLD:
					if (stack.getItem() == ModItems.IDOLS && stack.getItemDamage() == EnumIdolType.IDOL_GOLD.ordinal()) {
						world.setBlockState(pos, this.getDefaultState().withProperty(TYPE, EnumCapstoneType.CAPSTONE_GOLD_ACTIVE), 3);
						if (!player.capabilities.isCreativeMode)
							stack.shrink(1);
					}
					return true;
				case CAPSTONE_JADE:
					if (stack.getItem() == ModItems.IDOLS && stack.getItemDamage() == EnumIdolType.IDOL_JADE.ordinal()) {
						world.setBlockState(pos, this.getDefaultState().withProperty(TYPE, EnumCapstoneType.CAPSTONE_JADE_ACTIVE), 3);
						if (!player.capabilities.isCreativeMode)
							stack.shrink(1);
					}
					return true;
			}
		}
		return true;
	}

	@Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		EnumCapstoneType type = state.getValue(TYPE);
			switch (type) {
				case CAPSTONE:
				case CAPSTONE_MUD:
				case CAPSTONE_IRON:
				case CAPSTONE_GOLD:
				case CAPSTONE_JADE:
					break;
				case CAPSTONE_MUD_ACTIVE:
					if (world.getBlockState(pos.add(1, 0, 0)) == this.getDefaultState().withProperty(TYPE, EnumCapstoneType.CAPSTONE_IRON_ACTIVE))
						if (world.getBlockState(pos.add(0, 0, 1)) == this.getDefaultState().withProperty(TYPE, EnumCapstoneType.CAPSTONE_GOLD_ACTIVE))
							if (world.getBlockState(pos.add(1, 0, 1)) == this.getDefaultState().withProperty(TYPE, EnumCapstoneType.CAPSTONE_JADE_ACTIVE))
								openPyramid(world, pos, 1, 1);
					break;
				case CAPSTONE_IRON_ACTIVE:
					if (world.getBlockState(pos.add(- 1, 0, 0)) == this.getDefaultState().withProperty(TYPE, EnumCapstoneType.CAPSTONE_MUD_ACTIVE))
						if (world.getBlockState(pos.add(0, 0, 1)) == this.getDefaultState().withProperty(TYPE, EnumCapstoneType.CAPSTONE_JADE_ACTIVE))
							if (world.getBlockState(pos.add(- 1, 0, 1)) == this.getDefaultState().withProperty(TYPE, EnumCapstoneType.CAPSTONE_GOLD_ACTIVE))
								openPyramid(world, pos, - 1, 1);
					break;
				case CAPSTONE_GOLD_ACTIVE:
					if (world.getBlockState(pos.add(1, 0, 0)) == this.getDefaultState().withProperty(TYPE, EnumCapstoneType.CAPSTONE_JADE_ACTIVE))
						if (world.getBlockState(pos.add(0, 0, - 1)) == this.getDefaultState().withProperty(TYPE, EnumCapstoneType.CAPSTONE_MUD_ACTIVE))
							if (world.getBlockState(pos.add(1, 0, - 1)) == this.getDefaultState().withProperty(TYPE, EnumCapstoneType.CAPSTONE_IRON_ACTIVE))
								openPyramid(world, pos, 1, - 1);
					break;
				case CAPSTONE_JADE_ACTIVE:
					if (world.getBlockState(pos.add(- 1, 0, 0)) == this.getDefaultState().withProperty(TYPE, EnumCapstoneType.CAPSTONE_GOLD_ACTIVE))
						if (world.getBlockState(pos.add(0, 0, - 1)) == this.getDefaultState().withProperty(TYPE, EnumCapstoneType.CAPSTONE_IRON_ACTIVE))
							if (world.getBlockState(pos.add(- 1, 0, - 1)) == this.getDefaultState().withProperty(TYPE, EnumCapstoneType.CAPSTONE_MUD_ACTIVE))
								openPyramid(world, pos, - 1, - 1);
					break;
			}
	}

	private void openPyramid(World world, BlockPos pos, int offsetX, int offsetZ) {
		EntityLightningBolt entitybolt = new EntityLightningBolt(world, 0D, 0D, 0D, false);
		entitybolt.setLocationAndAngles(pos.getX() + offsetX, pos.getY(), pos.getZ() + offsetZ, 0F, 0F);
		world.addWeatherEffect(entitybolt);
		world.playEvent(null, 2001, pos, Block.getIdFromBlock(world.getBlockState(pos).getBlock()));
		world.setBlockToAir(pos);
		world.playEvent(null, 2001, pos.add(offsetX, 0, 0), Block.getIdFromBlock(world.getBlockState(pos.add(offsetX, 0, 0)).getBlock()));
		world.setBlockToAir(pos.add(offsetX, 0, 0));
		world.playEvent(null, 2001, pos.add(0, 0, offsetZ), Block.getIdFromBlock(world.getBlockState(pos.add(0, 0, offsetZ)).getBlock()));
		world.setBlockToAir(pos.add(0, 0, offsetZ));
		world.playEvent(null, 2001, pos.add(offsetX, 0, offsetZ), Block.getIdFromBlock(world.getBlockState(pos.add(offsetX, 0, offsetZ)).getBlock()));
		world.setBlockToAir(pos.add(offsetX, 0, offsetZ));
	}

	@Override
	public ItemBlock getItemBlock() {
		return ItemBlockEnum.create(this, EnumCapstoneType.class);
	}

	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (EnumCapstoneType type : EnumCapstoneType.values())
			models.add(type.getName());
		return models;
	}

	public enum EnumCapstoneType implements IErebusEnum {
		
		CAPSTONE,
		CAPSTONE_MUD,
		CAPSTONE_IRON,
		CAPSTONE_GOLD,
		CAPSTONE_JADE,
		CAPSTONE_MUD_ACTIVE,
		CAPSTONE_IRON_ACTIVE,
		CAPSTONE_GOLD_ACTIVE,
		CAPSTONE_JADE_ACTIVE;

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModBlocks.CAPSTONE, size, ordinal());
		}

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}

}
