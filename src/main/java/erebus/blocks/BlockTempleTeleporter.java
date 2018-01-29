package erebus.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import erebus.Erebus;
import erebus.ModBlocks;
import erebus.ModBlocks.IHasCustomItem;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.ModTabs;
import erebus.api.IErebusEnum;
import erebus.items.block.ItemBlockEnum;
import erebus.tileentity.TileEntityTempleTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTempleTeleporter extends Block implements ITileEntityProvider, IHasCustomItem, ISubBlocksBlock {

	public static final PropertyEnum<EnumTeleporterType> TYPE = PropertyEnum.create("type", EnumTeleporterType.class);
	
	public BlockTempleTeleporter() {
		super(Material.ROCK);
		setSoundType(SoundType.STONE);
		setBlockUnbreakable();
		setResistance(6000000.0F);
		setDefaultState(blockState.getBaseState().withProperty(TYPE, EnumTeleporterType.TEMPLE_TELEPORT_0));
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityTempleTeleporter();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE });
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (tab == ModTabs.BLOCKS)
			for (EnumTeleporterType type : EnumTeleporterType.values())
				list.add(new ItemStack(this, 1, type.ordinal()));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TYPE, EnumTeleporterType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumTeleporterType type = state.getValue(TYPE);
		return type.ordinal();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		EnumTeleporterType type = state.getValue(TYPE);
		Random random = world.rand;
		double pixel = 0.0625D;
			for (int sideIndex = 0; sideIndex < 6; ++sideIndex) {
				double particleX = (double) ((float) pos.getX() + random.nextFloat());
				double particleY = (double) ((float) pos.getY() + random.nextFloat());
				double particleZ = (double) ((float) pos.getZ() + random.nextFloat());

				if (sideIndex == 0 && !world.getBlockState(pos.up()).isOpaqueCube())
					particleY = (double) pos.getY() + 0.0625D + 1.0D;

				if (sideIndex == 1 && !world.getBlockState(pos.down()).isOpaqueCube())
					particleY = (double) pos.getY() - 0.0625D;

				if (sideIndex == 2 && !world.getBlockState(pos.south()).isOpaqueCube())
					particleZ = (double) pos.getZ() + 0.0625D + 1.0D;

				if (sideIndex == 3 && !world.getBlockState(pos.north()).isOpaqueCube())
					particleZ = (double) pos.getZ() - 0.0625D;

				if (sideIndex == 4 && !world.getBlockState(pos.east()).isOpaqueCube())
					particleX = (double) pos.getX() + 0.0625D + 1.0D;

				if (sideIndex == 5 && !world.getBlockState(pos.west()).isOpaqueCube())
					particleX = (double) pos.getX() - 0.0625D;

				if (particleX < (double) pos.getX() || particleX > (double) (pos.getX() + 1) || particleY < 0.0D || particleY > (double) (pos.getY() + 1) || particleZ < (double) pos.getZ() || particleZ > (double) (pos.getZ() + 1)) {
					if (type.ordinal() >= 1 && type.ordinal() <= 9 && type.ordinal() != 5)
						Erebus.PROXY.spawnCustomParticle("portal", world, particleX, particleY, particleZ, 0D, 0D, 0D);
					if (type.ordinal() == 5)
						Erebus.PROXY.spawnCustomParticle("repellent", world, particleX, particleY, particleZ, 0D, 0D, 0D);
		}
	}
}
	
	@Override
	public ItemBlock getItemBlock() {
		return ItemBlockEnum.create(this, EnumTeleporterType.class);
	}

	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (EnumTeleporterType type : EnumTeleporterType.values())
			models.add(type.getName());
		return models;
	}

	public enum EnumTeleporterType implements IErebusEnum {
		TEMPLE_TELEPORT_0,
		TEMPLE_TELEPORT_1,
		TEMPLE_TELEPORT_2,
		TEMPLE_TELEPORT_3,
		TEMPLE_TELEPORT_4,
		TEMPLE_TELEPORT_5,
		TEMPLE_TELEPORT_NE,
		TEMPLE_TELEPORT_NW,
		TEMPLE_TELEPORT_SE,
		TEMPLE_TELEPORT_SW;

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModBlocks.TEMPLE_TELEPORTER, size, ordinal());
		}

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}	

}