package erebus.blocks;

import erebus.ModItems;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.items.ItemErebusFood;
import erebus.items.ItemMaterials.EnumType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockDarkFruitVine extends BlockBush {

public static final PropertyInteger DARK_VINE_AGE = PropertyInteger.create("age", 0, 6);

    private static final AxisAlignedBB[] DARK_VINE_AABB = new AxisAlignedBB[] {
	new AxisAlignedBB(0.375D, 0.875D, 0.375D, 0.625D, 1D, 0.625D),
	new AxisAlignedBB(0.1875D, 0.625D, 0.1875D, 0.8125D, 1D, 0.8125D),
	new AxisAlignedBB(0.1875D, 0.375D, 0.1875D, 0.8125D, 1D, 0.8125D),
	new AxisAlignedBB(0.1875D, 0.1875D, 0.1875D, 0.8125D, 1D, 0.8125D),
	new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 1D, 0.8125D),
	new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D),
	new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 1.0D, 0.875D)};

	public BlockDarkFruitVine() {
		setHardness(0.0F);
		setCreativeTab(ModTabs.BLOCKS);
		setSoundType(SoundType.PLANT);
		setDefaultState(blockState.getBaseState().withProperty(DARK_VINE_AGE, 0));
	}

	@Override
	public int tickRate(World world) {
		return 5;
	}

	private PropertyInteger getAgeProperty() {
		return DARK_VINE_AGE;
    }

    public int getMaxAge() {
        return 6;
    }

	private int getAge(IBlockState state) {
		return state.getValue(this.getAgeProperty());
	}

	private IBlockState withAge(int age) {
		return this.getDefaultState().withProperty(this.getAgeProperty(), age);
	}

	@Override
    protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, DARK_VINE_AGE);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(DARK_VINE_AGE, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(DARK_VINE_AGE);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return DARK_VINE_AABB[state.getValue(this.getAgeProperty())];
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		int age = getAge(state);
		// New shoot
		if (world.isAirBlock(pos.down()) && canBlockStay(world, pos.down()))
			switch (age) {
				case 4:
				case 5:
				case 6:
					world.setBlockState(pos.down(), this.withAge(0));
					break;
			}

		// Shoot maturity
		switch (age) {
			case 0:
				world.setBlockState(pos, this.withAge(1));
				break;
			case 1:
				world.setBlockState(pos, this.withAge(2));
				break;
			case 2:
				world.setBlockState(pos, this.withAge(3));
				break;
			case 3:
				world.setBlockState(pos, this.withAge(4));
				break;
		}

		// Fruit
		if (age == 4 && rand.nextInt(50) == 0)
			world.setBlockState(pos, this.withAge(5));

		// Seeds
		if (age == 5 && rand.nextInt(10) == 0)
			world.setBlockState(pos, this.withAge(6));

		// Revert Seeds
		if (age == 6 && rand.nextInt(10) == 0)
			world.setBlockState(pos, this.withAge(4));
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return 0;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		int age = getAge(state);

		if (age == 5) {
			ItemStack item = new ItemStack(ModItems.EREBUS_FOOD, 1, ItemErebusFood.EnumFoodType.DARK_FRUIT.ordinal());
			world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.5F, 2.0F);
			if (!player.inventory.addItemStackToInventory(item))
				Utils.dropStack(world, pos, item);
			world.setBlockState(pos, this.withAge(4));
			return true;
		}

		if (age == 6) {
			ItemStack item = new ItemStack(ModItems.MATERIALS, 1, EnumType.DARK_FRUIT_SEEDS.ordinal());
			world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.5F, 2.0F);
			if (!player.inventory.addItemStackToInventory(item))
				Utils.dropStack(world, pos, item);
			world.setBlockState(pos, this.withAge(4));
			return true;
		}
		return true;
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		int age = getAge(state);
		ItemStack item = null;
		if (age == 5) {
			item = new ItemStack(ModItems.EREBUS_FOOD, 2, ItemErebusFood.EnumFoodType.DARK_FRUIT.ordinal());
			Utils.dropStack(world, pos, item);
		}

		if (age == 6) {
			item = new ItemStack(ModItems.MATERIALS, 2, EnumType.DARK_FRUIT_SEEDS.ordinal());
			Utils.dropStack(world, pos, item);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos.up());
		return isValidBlock(state) && canBlockStay(world, pos);
	}

	public boolean canBlockStay(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos.up());
		return isValidBlock(state);
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos from) {
		int age = getAge(state);
		ItemStack item = null;
		if (world.isAirBlock(pos.up())) {
			if (age == 5) {
				item = new ItemStack(ModItems.EREBUS_FOOD, 1, ItemErebusFood.EnumFoodType.DARK_FRUIT.ordinal());
				Utils.dropStack(world, pos, item);
			}
			if (age == 6) {
				item = new ItemStack(ModItems.MATERIALS, 1, EnumType.DARK_FRUIT_SEEDS.ordinal());
				Utils.dropStack(world, pos, item);
			}
			world.setBlockToAir(pos);
		}
		canBlockStay(world, pos);
	}

	private boolean isValidBlock(IBlockState state) {
		Block block = state.getBlock();
		return state.getMaterial().blocksMovement() || block == this;
	}
}