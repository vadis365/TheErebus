package erebus.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;

public class BlockSlabPlanksErebus extends BlockSlab {

	public static final String[][] slabTypes = new String[][] { new String[] { "acacia", "eucalyptus", "mahogany", "baobab", "mossbark", "pink", "scorched", "asper" }, new String[] { "white", "bamboo", "cypress" } };
	public static final byte dataAcacia = 0, dataEucalyptus = 1, dataMahogany = 2, dataBaobab = 3, dataMossbark = 4, dataPink = 5, dataScorched = 6, dataAsper = 7, dataWhite = 0, dataBamboo = 1, dataCypress = 2;

	private final int slabGroup;

	public BlockSlabPlanksErebus(int slabGroup, boolean isDouble) {
		super(isDouble, Material.wood);
		this.slabGroup = slabGroup;
		setLightOpacity(0);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.plankSlabs[slabGroup * 2]);
	}

	@Override
	protected ItemStack createStackedBlock(int meta) {
		return new ItemStack(ModBlocks.plankSlabs[slabGroup * 2], field_150004_a ? 2 : 1, meta & 7);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return Item.getItemFromBlock(ModBlocks.plankSlabs[slabGroup * 2]);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return ModBlocks.planksErebus.getIcon(side, getPlankMeta(meta));
	}

	@Override
	public String func_150002_b(int meta) {
		return super.getUnlocalizedName() + "." + slabTypes[slabGroup][getPlankMeta(meta)];
	}

	private int getPlankMeta(int meta) {
		return (meta & 7) + slabGroup * 8;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs creativeTab, List list) {
		if (field_150004_a)
			return;

		for (int a = 0; a < slabTypes[slabGroup].length; a++)
			list.add(new ItemStack(id, 1, a));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
	}
}