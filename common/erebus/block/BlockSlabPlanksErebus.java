package erebus.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;

public class BlockSlabPlanksErebus extends BlockHalfSlab {

	public static final String[][] slabTypes = new String[][] { new String[] { "acacia", "eucalyptus", "mahogany", "baobab", "mossbark", "pink", "scorched", "asper" }, new String[] { "white", "bamboo" } };
	public static final byte dataAcacia = 0, dataEucalyptus = 1, dataMahogany = 2, dataBaobab = 3, dataMossbark = 4, dataPink = 5, dataScorched = 6, dataAsper = 7, dataWhite = 0, dataBamboo = 1;

	private final int slabGroup;

	public BlockSlabPlanksErebus(int id, int slabGroup, boolean isDouble) {
		super(id, isDouble, Material.wood);
		this.slabGroup = slabGroup;
		setLightOpacity(0);
	}

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
		return ModBlocks.plankSlabs[slabGroup * 2].blockID;
	}

	@Override
	protected ItemStack createStackedBlock(int meta) {
		return new ItemStack(ModBlocks.plankSlabs[slabGroup * 2], isDoubleSlab ? 2 : 1, meta & 7);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int x, int y, int z) {
		return ModBlocks.plankSlabs[slabGroup * 2].blockID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return ModBlocks.planksErebus.getIcon(side, getPlankMeta(meta));
	}

	@Override
	public String getFullSlabName(int meta) {
		return super.getUnlocalizedName() + "." + slabTypes[slabGroup][getPlankMeta(meta)];
	}

	private int getPlankMeta(int meta) {
		return (meta & 7) + slabGroup * 8;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs creativeTab, List list) {
		if (isDoubleSlab)
			return;

		for (int a = 0; a < slabTypes[slabGroup].length; a++)
			list.add(new ItemStack(id, 1, a));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
	}

}
