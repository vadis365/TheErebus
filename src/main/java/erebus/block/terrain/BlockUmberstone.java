package erebus.block.terrain;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import erebus.ModTabs;

public class BlockUmberstone extends Block implements IMetaBlockName {

	public static final PropertyEnum TYPE = PropertyEnum.create("type", BlockUmberstone.EnumType.class);

	public BlockUmberstone() {
		super(Material.rock);
		setHardness(1.5F);
		setResistance(10.0F);
		setHarvestLevel("pickaxe", 0);
		setRegistryName("umberstone");
		setUnlocalizedName(getRegistryName().toString());
		setCreativeTab(ModTabs.blocks);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumType.UMBER_STONE));
	}
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TYPE, EnumType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumType type = (EnumType) state.getValue(TYPE);
	    return type.getID();
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state) == 0 ? 1 : getMetaFromState(state);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE });
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		for(EnumType type : EnumType.values())
			list.add(new ItemStack(itemIn, 1, type.ordinal()));
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumType.values() [stack.getItemDamage()].name;
	}

	public enum EnumType implements IStringSerializable {
		
		UMBER_STONE(0, "umberstone"),
		UMBER_COBBLE(1, "umbercobble"),
		UMBER_COBBLE_MOSSY(2, "umbercobble_mossy"),
		UMBER_COBBLE_WEBBED(3, "umbercobble_webbed"),
		UMBER_STONE_BRICKS(4, "umberstone_bricks"),
		UMBER_TILE_SMOOTH(5, "umbertile_smooth"),
		UMBER_TILE_SMOOTH_SMALL(6, "umbertile_smooth_small");

		private int ID;
		private String name;

		private EnumType(int ID, String name) {
			this.ID = ID;
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}

		public int getID() {
			return this.ID;
		}

		@Override
		public String toString() {
			return this.getName();
		}
	}
/*
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		EntityPlayer player = world.getClosestPlayer(x, y, z, 40);
		if (player != null)
			player.triggerAchievement(ModAchievements.umberstone);
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icons = new IIcon[iconPaths.length];

		int i = 0;
		for (String path : iconPaths)
			icons[i++] = reg.registerIcon("erebus:" + path);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if (meta < 0 || meta >= icons.length)
			return null;
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i < icons.length; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	public int damageDropped(int meta) {
		return meta == 0 ? 1 : meta;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockGeneric.class;
	}*/
}