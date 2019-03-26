package erebus.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.annotation.Nullable;

import erebus.ModBlocks;
import erebus.ModBlocks.IHasCustomItem;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.items.ItemMaterials;
import erebus.items.block.ItemBlockEnum;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDoubleHeightPlant extends BlockBush implements IGrowable, IShearable, IHasCustomItem, ISubBlocksBlock
{
    public static final PropertyEnum<BlockDoubleHeightPlant.EnumPlantType> VARIANT = PropertyEnum.<BlockDoubleHeightPlant.EnumPlantType>create("variant", BlockDoubleHeightPlant.EnumPlantType.class);
    public static final PropertyEnum<BlockDoubleHeightPlant.EnumBlockHalf> HALF = PropertyEnum.<BlockDoubleHeightPlant.EnumBlockHalf>create("half", BlockDoubleHeightPlant.EnumBlockHalf.class);

    public BlockDoubleHeightPlant() {
        super(Material.VINE);
        setDefaultState(blockState.getBaseState().withProperty(VARIANT, BlockDoubleHeightPlant.EnumPlantType.BULLRUSH).withProperty(HALF, BlockDoubleHeightPlant.EnumBlockHalf.LOWER));
        setHardness(0.0F);
        setSoundType(SoundType.PLANT);
        setCreativeTab(ModTabs.PLANTS);
    }

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return FULL_BLOCK_AABB;
    }

    private EnumPlantType getType(IBlockAccess blockAccess, BlockPos pos, IBlockState state) {
    	state = state.getActualState(blockAccess, pos);
    	return (EnumPlantType)state.getValue(VARIANT);
    }

	@Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        return world.getBlockState(pos.down()).getBlock() == Blocks.SAND && world.isAirBlock(pos.up()) ? true : super.canPlaceBlockAt(world, pos) && world.isAirBlock(pos.up());
    }

	@Override
    public boolean isReplaceable(IBlockAccess world, BlockPos pos) {
    	return true;
    }

	@Override
    protected void checkAndDropBlock(World world, BlockPos pos, IBlockState state) {
        if (!canBlockStay(world, pos, state)) {
            boolean flag = state.getValue(HALF) == BlockDoubleHeightPlant.EnumBlockHalf.UPPER;
            BlockPos blockpos = flag ? pos : pos.up();
            BlockPos blockpos1 = flag ? pos.down() : pos;
            Block block = (Block)(flag ? this : world.getBlockState(blockpos).getBlock());
            Block block1 = (Block)(flag ? world.getBlockState(blockpos1).getBlock() : this);

            if (!flag)
            	dropBlockAsItem(world, pos, state, 0); //Forge move above the setting to air.

            if (block == this)
                world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
            if (block1 == this)
                world.setBlockState(blockpos1, Blocks.AIR.getDefaultState(), 3);
        }
    }

	@Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		IBlockState iblockstateDown = world.getBlockState(pos.down());
        if (state.getBlock() != this)
        	return iblockstateDown.getBlock() == Blocks.SAND ? true : iblockstateDown.getBlock() == ModBlocks.MUD ? true : super.canBlockStay(world, pos, state); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        if (state.getValue(HALF) == BlockDoubleHeightPlant.EnumBlockHalf.UPPER)
            return world.getBlockState(pos.down()).getBlock() == this;
        else {
            IBlockState iblockstate = world.getBlockState(pos.up());
            return iblockstate.getBlock() == this && iblockstateDown.getBlock() == Blocks.SAND ? true : iblockstate.getBlock() == this && iblockstateDown.getBlock() == ModBlocks.MUD ? true : iblockstate.getBlock() == this && super.canBlockStay(world, pos, iblockstate);
        }
    }

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (state.getValue(HALF) == BlockDoubleHeightPlant.EnumBlockHalf.UPPER)
            return Items.AIR;
        else {
            EnumPlantType planttype = (EnumPlantType)state.getValue(VARIANT);
            return super.getItemDropped(state, rand, fortune);
        }
    }

	@Override
    public int damageDropped(IBlockState state) {
        return ((EnumPlantType)state.getValue(VARIANT)).ordinal();
    }

    public void placeAt(World world, BlockPos lowerPos, EnumPlantType variant, int flags) {
        world.setBlockState(lowerPos, getDefaultState().withProperty(HALF, EnumBlockHalf.LOWER).withProperty(VARIANT, variant), flags);
        world.setBlockState(lowerPos.up(), getDefaultState().withProperty(HALF, EnumBlockHalf.UPPER), flags);
    }

	@Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        world.setBlockState(pos.up(), getDefaultState().withProperty(HALF, EnumBlockHalf.UPPER), 2);
    }

	@Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
    	super.harvestBlock(world, player, pos, state, te, stack);
    }
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
		if (state.getValue(HALF) == EnumBlockHalf.UPPER) {
			IBlockState iblockstate = world.getBlockState(pos.down());
			ret.add(new ItemStack(iblockstate.getBlock().getItemDropped(iblockstate, RANDOM, fortune)));
		} else {
			if (state.getValue(VARIANT) == EnumPlantType.SUNDEW)
				ret.add(new ItemStack(ModItems.MATERIALS, 1, ItemMaterials.EnumErebusMaterialsType.BIO_LUMINESCENCE.ordinal()));
			else if (state.getValue(VARIANT) == EnumPlantType.WEEPING_BLUEBELL)
				ret.add(new ItemStack(ModItems.MATERIALS, 1, ItemMaterials.EnumErebusMaterialsType.BLUEBELL_PETAL.ordinal()));
			else if (state.getValue(VARIANT) == EnumPlantType.BULLRUSH)
				ret.add(new ItemStack(ModItems.MATERIALS, 1, ItemMaterials.EnumErebusMaterialsType.PAPYRUS.ordinal()));
			else if (state.getValue(VARIANT) == EnumPlantType.TALL_BLOOM)
				ret.add(new ItemStack(this, 1, getMetaFromState(state)));
			else if (state.getValue(VARIANT) == EnumPlantType.TANGLED_STALK_MUSHROOM)
				ret.add(new ItemStack(this, 1, getMetaFromState(state)));
			else if (state.getValue(VARIANT) == EnumPlantType.HIGH_CAPPED_MUSHROOM)
				ret.add(new ItemStack(this, 1, getMetaFromState(state)));
			else {
				ItemStack seed = ForgeHooks.getGrassSeed(RANDOM, fortune);
				if (!seed.isEmpty())
					ret.add(seed);
				else
					ret.add(new ItemStack(this, 1, getMetaFromState(state)));
			}
		}
		return ret;
	}

	@Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (state.getValue(HALF) == BlockDoubleHeightPlant.EnumBlockHalf.UPPER) {
            if (world.getBlockState(pos.down()).getBlock() == this) {
                if (player.capabilities.isCreativeMode)
                    world.setBlockToAir(pos.down());
                else {
                    IBlockState iblockstate = world.getBlockState(pos.down());
                    EnumPlantType planttype = (EnumPlantType)iblockstate.getValue(VARIANT);
                    world.destroyBlock(pos.down(), RANDOM.nextInt(5) == 0 ? true : false);
                    if (world.isRemote)
                        world.setBlockToAir(pos.down());
                    else if (!player.getHeldItemMainhand().isEmpty() && player.getHeldItemMainhand().getItem() == Items.SHEARS) {
                        onHarvest(world, pos, iblockstate, player);
                        world.setBlockToAir(pos.down());
                    }
                }
            }
        }
        else if (world.getBlockState(pos.up()).getBlock() == this)
            world.setBlockState(pos.up(), Blocks.AIR.getDefaultState(), 2);
        super.onBlockHarvested(world, pos, state, player);
    }

    private boolean onHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
    	player.addStat(StatList.getBlockStats(this));
    	return true;
    }

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (tab == ModTabs.PLANTS)
			for (EnumPlantType planttype : EnumPlantType.values())
				list.add(new ItemStack(this, 1, planttype.ordinal()));
	}

	@Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
        return new ItemStack(this, 1, getType(world, pos, state).ordinal());
    }

	@Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
    	return true;
	}

	@Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

	@Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
        spawnAsEntity(world, pos, new ItemStack(this, 1, getType(world, pos, state).ordinal()));
    }

	@Override
    public IBlockState getStateFromMeta(int meta) {
        return (meta & 8) > 0 ? getDefaultState().withProperty(HALF, EnumBlockHalf.UPPER) : getDefaultState().withProperty(HALF, EnumBlockHalf.LOWER).withProperty(VARIANT, EnumPlantType.values()[meta&7]);
    }

	@Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        if (state.getValue(HALF) == EnumBlockHalf.UPPER) {
            IBlockState iblockstate = world.getBlockState(pos.down());
            if (iblockstate.getBlock() == this)
                state = state.withProperty(VARIANT, iblockstate.getValue(VARIANT));
        }
        return state;
    }

	@Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(HALF) == EnumBlockHalf.UPPER ? 8 | ((EnumPlantType)state.getValue(VARIANT)).ordinal() : ((EnumPlantType)state.getValue(VARIANT)).ordinal();
    }

	@Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {HALF, VARIANT});
    }

	@Override
    public Block.EnumOffsetType getOffsetType() {
        return Block.EnumOffsetType.XZ;
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        EnumPlantType type = (EnumPlantType)state.getValue(VARIANT);
        return state.getValue(HALF) == EnumBlockHalf.LOWER;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
        List<ItemStack> ret = new ArrayList<ItemStack>();
        EnumPlantType type = (EnumPlantType)world.getBlockState(pos).getValue(VARIANT);
        ret.add(new ItemStack(this, 1, type.ordinal() & 7));
        return ret;
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        if (state.getBlock() ==  this && state.getValue(HALF) == EnumBlockHalf.LOWER && world.getBlockState(pos.up()).getBlock() == this)
            world.setBlockToAir(pos.up());
        return world.setBlockToAir(pos);
    }

    public static enum EnumBlockHalf implements IStringSerializable {
        UPPER,
        LOWER;

        public String toString() {
            return getName();
        }

        public String getName() {
            return this == UPPER ? "upper" : "lower";
        }
    }

    public static enum EnumPlantType implements IStringSerializable {
		BULLRUSH,
		WEEPING_BLUEBELL,
		SUNDEW,
		DROUGHTED_SHRUB,
		TALL_BLOOM,
		TANGLED_STALK_MUSHROOM,
		HIGH_CAPPED_MUSHROOM;

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}

    }

	@Override
	public ItemBlock getItemBlock() {
		return ItemBlockEnum.create(this, EnumPlantType.class);
	}

	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (EnumPlantType type : EnumPlantType.values())
			models.add(type.getName());
		return models;
	}
}
