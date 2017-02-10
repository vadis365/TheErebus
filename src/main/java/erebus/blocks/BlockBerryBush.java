package erebus.blocks;

import erebus.ModItems;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.items.ItemErebusFood.EnumFoodType;
import erebus.items.ItemMaterials.EnumType;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;
import java.util.Random;

public class BlockBerryBush extends Block {
    public static final PropertyInteger BUSH_AGE = PropertyInteger.create("age", 0, 3);
    private static final AxisAlignedBB[] BUSH_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D), new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.75D, 0.875D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
	private String type;

	public BlockBerryBush(String bushType) {
		super(Material.LEAVES);
		type = bushType;
		setTickRandomly(true);
		setHardness(0.2F);
		setLightOpacity(1);
		setCreativeTab(ModTabs.BLOCKS);
		setSoundType(SoundType.PLANT);
	}

	@Override
	public int tickRate(World world) {
		return 10;
	}

	private PropertyInteger getAgeProperty() {
		return BUSH_AGE;
    }

    public int getMaxAge() {
        return 3;
    }

	private int getAge(IBlockState state) {
		return state.getValue(this.getAgeProperty());
	}

	private IBlockState withAge(int age) {
		return this.getDefaultState().withProperty(this.getAgeProperty(), age);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return Blocks.LEAVES.getBlockLayer();
	}

	@Override
    protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BUSH_AGE);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(BUSH_AGE, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(BUSH_AGE);
	}

	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BUSH_AABB[state.getValue(this.getAgeProperty())];
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return BUSH_AABB[state.getValue(this.getAgeProperty())];
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		int age = getAge(state);
		if (rand.nextInt(25) == 0)
			switch (age) {
				case 0:
					world.setBlockState(pos, this.withAge(1), 2);
					break;
				case 1:
					world.setBlockState(pos, this.withAge(2), 2);
					break;
			}
		if (age == 2 && rand.nextInt(50) == 0)
			world.setBlockState(pos, this.withAge(3), 2);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		int age = getAge(state);
		if (age == 3) {
			world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.5F, 2.0F);
			if (!player.inventory.addItemStackToInventory(getBerry()))
				Utils.dropStack(world, pos, getBerry());
			world.setBlockState(pos, this.withAge(2), 2);
			return true;
		}
		return false;
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		int age = getAge(state);
		if (age == 3)
			Utils.dropStack(world, pos, getBerry());
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos.down());
		return isValidBlock(state);
	}

	private boolean canBlockStay(World world, BlockPos pos) {
		return canPlaceBlockAt(world, pos);
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos from) {
		int age = getAge(state);
		if (world.isAirBlock(pos.down())) {
			if (age == 3)
				Utils.dropStack(world, pos, getBerry());
			Utils.dropStack(world, pos, new ItemStack(Item.getItemFromBlock(this)));
			world.setBlockToAir(pos);
		}
		canBlockStay(world, pos);
	}

	private boolean isValidBlock(IBlockState state) {
		Block block = state.getBlock();
		return block == Blocks.DIRT || block == Blocks.FARMLAND || block == Blocks.GRASS || block == this && getAge(state) >= 2;//&& block == ModBlocks.MUD
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return 1;
	}

	private ItemStack getBerry() {
		ItemStack item = null;
		if (Objects.equals(type, "JADE"))
			item = new ItemStack(ModItems.MATERIALS, 1, EnumType.JADE_BERRIES.ordinal());
		if (Objects.equals(type, "HEART"))
			item = new ItemStack(ModItems.HEART_BERRIES, 1);
		if (Objects.equals(type, "SWAMP"))
			item = new ItemStack(ModItems.EREBUS_FOOD, 1, EnumFoodType.SWAMP_BERRIES.ordinal());
		return item;
	}
}