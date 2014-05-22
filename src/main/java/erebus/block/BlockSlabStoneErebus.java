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

public class BlockSlabStoneErebus extends BlockSlab {

	public static final String[] slabTypes = new String[] { "umberstone", "umbercobble", "umbercobbleMossy", "umbercobbleWebbed", "umbrick", "umberpaver", "umberpaverMossy", "umberpaverWebbed" };
	public static final byte dataUmberstone = 0, dataUmbercobble = 1, dataUmbercobbleMossy = 2, dataUmbercobbleWebbed = 3, dataUmbrick = 4, dataUmberpaver = 5, dataUmberpaverMossy = 6, dataUmberpaverWebbed = 7;

	public BlockSlabStoneErebus(boolean isDouble) {
		super(isDouble, Material.rock);
		setLightOpacity(0);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.stoneSlabs[0]);
	}

	@Override
	protected ItemStack createStackedBlock(int meta) {
		return new ItemStack(ModBlocks.stoneSlabs[0], field_150004_a ? 2 : 1, meta & 7);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return Item.getItemFromBlock(ModBlocks.stoneSlabs[0]);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
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
	public String func_150002_b(int meta) {
		return super.getUnlocalizedName() + "." + slabTypes[meta & 7];
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs creativeTab, List list) {
		if (field_150004_a)
			return;

		for (int a = 0; a < slabTypes.length; a++)
			list.add(new ItemStack(id, 1, a));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
	}
}