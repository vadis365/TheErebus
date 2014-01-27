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

public class BlockSlabStoneErebus extends BlockHalfSlab {

	public static final String[] slabTypes = new String[] { "umberstone", "umbercobble", "umbercobbleMossy", "umbercobbleWebbed", "umbrick", "umberpaver", "umberpaverMossy", "umberpaverWebbed" };
	public static final byte dataUmberstone = 0, dataUmbercobble = 1, dataUmbercobbleMossy = 2, dataUmbercobbleWebbed = 3, dataUmbrick = 4, dataUmberpaver = 5, dataUmberpaverMossy = 6, dataUmberpaverWebbed = 7;

	public BlockSlabStoneErebus(int id, boolean isDouble) {
		super(id, isDouble, Material.rock);
		setLightOpacity(0);
	}

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
		return ModBlocks.stoneSlabs[0].blockID;
	}

	@Override
	protected ItemStack createStackedBlock(int meta) {
		return new ItemStack(ModBlocks.stoneSlabs[0], isDoubleSlab ? 2 : 1, meta & 7);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int x, int y, int z) {
		return ModBlocks.stoneSlabs[0].blockID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		int baseMeta = meta & 7;

		switch (baseMeta) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				return ModBlocks.umberstone.getIcon(side, baseMeta);
			case 5:
			case 6:
			case 7:
				return ModBlocks.umberPaver.getIcon(side, baseMeta - 5);
		}

		return null;
	}

	@Override
	public String getFullSlabName(int meta) {
		return super.getUnlocalizedName() + "." + slabTypes[meta & 7];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs creativeTab, List list) {
		if (isDoubleSlab)
			return;

		for (int a = 0; a < slabTypes.length; a++)
			list.add(new ItemStack(id, 1, a));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
	}
}
