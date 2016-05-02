package erebus.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.ModTabs;
import erebus.block.terrain.IMetaBlockName;
import erebus.lib.EnumWood;

public class BlockPlanksErebus extends Block implements IMetaBlockName {

	public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumWood.class);

	public BlockPlanksErebus() {
		super(Material.wood);
		setHardness(2.0F);
		setHarvestLevel("axe", 0);
		setStepSound(SoundType.WOOD);
		setRegistryName("planks");
		setUnlocalizedName(getRegistryName().toString());
		setCreativeTab(ModTabs.blocks);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < EnumWood.values().length; i++)
			if (EnumWood.values()[i].hasPlanks())
				list.add(new ItemStack(item, 1, i));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TYPE, EnumWood.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumWood type = (EnumWood) state.getValue(TYPE);
	    return type.getID();
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE });
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumWood.values() [stack.getItemDamage()].getName();
	}
}