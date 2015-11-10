package erebus.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.ModTabs;
import erebus.item.block.ItemBlockAmber;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockAmber extends Block implements ISubBlocksBlock {

	@SideOnly(Side.CLIENT)
	private IIcon blockAmber, glassAmber, brickAmber;
	@SideOnly(Side.CLIENT)
	private IIcon[] connectedGlass;

	private final String[] connectedGlassStr = new String[] { "center", "bottomleft", "bottomright", "topleft", "topright", "sidingleft", "sidingright", "sidingbottom", "sidingtop", // 8
			"sidestopbottom", "sidesleftright", "fullsideleft", "fullsideright", "fullsidebottom", "fullsidetop", // 14
			"topleft_c", "topright_c", "bottomleft_c", "bottomright_c", "centerall", // 19
			"center_bl_br", "center_tl_tr", "center_tl_bl", "center_tr_br", "center_tl_br", "center_tr_bl", // 25
			"center_tl_bl_br", "center_tl_tr_bl", "center_tl_tr_br", "center_tr_bl_br", // 29
			"center_tl", "center_tr", "center_bl", "center_br", // 33
			"sidingbottom_l", "sidingbottom_r", "sidingbottom_lr", "sidingtop_l", "sidingtop_r", "sidingtop_lr", // 39
			"sidingleft_t", "sidingleft_b", "sidingleft_tb", "sidingright_t", "sidingright_b", "sidingright_tb" // 45
	};

	public BlockAmber() {
		super(Material.rock);
		setHardness(1.5F);
		setLightOpacity(3);
		setResistance(10.0F);
		setStepSound(soundTypeGlass);
		setBlockName("erebus.amber");
		setHarvestLevel("pickaxe", 0);
		setCreativeTab(ModTabs.blocks);
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return world.getBlockMetadata(x, y, z) == 2;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderBlockPass() {
		return 1;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		Block block = world.getBlock(x, y, z);
		return block == this || block == ModBlocks.preservedBlock ? false : super.shouldSideBeRendered(world, x, y, z, side);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 1) {
			boolean[] nearby = null;

			if (side == 0 || side == 1)
				nearby = new boolean[] { isGlass(world, x - 1, y, z), isGlass(world, x + 1, y, z), isGlass(world, x, y, z - 1), isGlass(world, x, y, z + 1), isGlass(world, x - 1, y, z - 1), isGlass(world, x - 1, y, z + 1), isGlass(world, x + 1, y, z - 1), isGlass(world, x + 1, y, z + 1) };
			else if (side == 2)
				nearby = new boolean[] { isGlass(world, x + 1, y, z), isGlass(world, x - 1, y, z), isGlass(world, x, y + 1, z), isGlass(world, x, y - 1, z), isGlass(world, x + 1, y + 1, z), isGlass(world, x + 1, y - 1, z), isGlass(world, x - 1, y + 1, z), isGlass(world, x - 1, y - 1, z) };
			else if (side == 3)
				nearby = new boolean[] { isGlass(world, x - 1, y, z), isGlass(world, x + 1, y, z), isGlass(world, x, y + 1, z), isGlass(world, x, y - 1, z), isGlass(world, x - 1, y + 1, z), isGlass(world, x - 1, y - 1, z), isGlass(world, x + 1, y + 1, z), isGlass(world, x + 1, y - 1, z) };
			else if (side == 4)
				nearby = new boolean[] { isGlass(world, x, y, z - 1), isGlass(world, x, y, z + 1), isGlass(world, x, y + 1, z), isGlass(world, x, y - 1, z), isGlass(world, x, y + 1, z - 1), isGlass(world, x, y - 1, z - 1), isGlass(world, x, y + 1, z + 1), isGlass(world, x, y - 1, z + 1) };
			else if (side == 5)
				nearby = new boolean[] { isGlass(world, x, y, z + 1), isGlass(world, x, y, z - 1), isGlass(world, x, y + 1, z), isGlass(world, x, y - 1, z), isGlass(world, x, y + 1, z + 1), isGlass(world, x, y - 1, z + 1), isGlass(world, x, y + 1, z - 1), isGlass(world, x, y - 1, z - 1) };
			else
				return glassAmber;

			if (!nearby[0] && !nearby[1] && !nearby[2] && !nearby[3])
				return glassAmber;
			else if (nearby[0] && nearby[1] && nearby[2] && nearby[3]) {
				if (!nearby[4] && !nearby[5] && !nearby[6] && !nearby[7])
					return connectedGlass[19];
				else if (nearby[4] && nearby[5] && nearby[6] && nearby[7])
					return connectedGlass[0];
				else if (nearby[4] && !nearby[5] && nearby[6] && !nearby[7])
					return connectedGlass[20];
				else if (!nearby[4] && nearby[5] && !nearby[6] && nearby[7])
					return connectedGlass[21];
				else if (!nearby[4] && !nearby[5] && nearby[6] && nearby[7])
					return connectedGlass[22];
				else if (nearby[4] && nearby[5] && !nearby[6] && !nearby[7])
					return connectedGlass[23];
				else if (!nearby[4] && nearby[5] && nearby[6] && !nearby[7])
					return connectedGlass[24];
				else if (nearby[4] && !nearby[5] && !nearby[6] && nearby[7])
					return connectedGlass[25];
				else if (!nearby[4] && !nearby[5] && nearby[6] && !nearby[7])
					return connectedGlass[26];
				else if (!nearby[4] && !nearby[5] && !nearby[6] && nearby[7])
					return connectedGlass[27];
				else if (!nearby[4] && nearby[5] && !nearby[6] && !nearby[7])
					return connectedGlass[28];
				else if (nearby[4] && !nearby[5] && !nearby[6] && !nearby[7])
					return connectedGlass[29];
				else if (!nearby[4] && nearby[5] && nearby[6] && nearby[7])
					return connectedGlass[30];
				else if (nearby[4] && nearby[5] && !nearby[6] && nearby[7])
					return connectedGlass[31];
				else if (nearby[4] && !nearby[5] && nearby[6] && nearby[7])
					return connectedGlass[32];
				else if (nearby[4] && nearby[5] && nearby[6] && !nearby[7])
					return connectedGlass[33];
			} else if (!nearby[0] && nearby[1] && nearby[2] && !nearby[3]) {
				if (nearby[6])
					return connectedGlass[1];
				else
					return connectedGlass[17];
			} else if (nearby[0] && !nearby[1] && nearby[2] && !nearby[3]) {
				if (nearby[4])
					return connectedGlass[2];
				else
					return connectedGlass[18];
			} else if (!nearby[0] && nearby[1] && !nearby[2] && nearby[3]) {
				if (nearby[7])
					return connectedGlass[3];
				else
					return connectedGlass[15];
			} else if (nearby[0] && !nearby[1] && !nearby[2] && nearby[3]) {
				if (nearby[5])
					return connectedGlass[4];
				else
					return connectedGlass[16];
			} else if (!nearby[0] && nearby[1] && nearby[2] && nearby[3]) {
				if (!nearby[6] && !nearby[7])
					return connectedGlass[42];
				else if (!nearby[7])
					return connectedGlass[41];
				else if (!nearby[6])
					return connectedGlass[40];
				return connectedGlass[5];
			} else if (nearby[0] && !nearby[1] && nearby[2] && nearby[3]) {
				if (!nearby[4] && !nearby[5])
					return connectedGlass[45];
				else if (!nearby[5])
					return connectedGlass[44];
				else if (!nearby[4])
					return connectedGlass[43];
				else
					return connectedGlass[6];
			} else if (nearby[0] && nearby[1] && nearby[2] && !nearby[3]) {
				if (!nearby[4] && !nearby[6])
					return connectedGlass[36];
				else if (!nearby[6])
					return connectedGlass[35];
				else if (!nearby[4])
					return connectedGlass[34];
				else
					return connectedGlass[7];
			} else if (nearby[0] && nearby[1] && !nearby[2] && nearby[3]) {
				if (!nearby[5] && !nearby[7])
					return connectedGlass[39];
				else if (!nearby[7])
					return connectedGlass[38];
				else if (!nearby[5])
					return connectedGlass[37];
				else
					return connectedGlass[8];
			} else if (nearby[0] && nearby[1])
				return connectedGlass[9];
			else if (nearby[2] && nearby[3])
				return connectedGlass[10];
			else if (!nearby[0] && nearby[1] && !nearby[2] && !nearby[3])
				return connectedGlass[11];
			else if (nearby[0] && !nearby[1] && !nearby[2] && !nearby[3])
				return connectedGlass[12];
			else if (!nearby[0] && !nearby[1] && nearby[2] && !nearby[3])
				return connectedGlass[13];
			else if (!nearby[0] && !nearby[1] && !nearby[2] && nearby[3])
				return connectedGlass[14];
		}

		return getIcon(side, meta);
	}

	private boolean isGlass(IBlockAccess world, int x, int y, int z) {
		return world.getBlock(x, y, z) == this && world.getBlockMetadata(x, y, z) == 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		switch (meta) {
			case 0:
				return blockAmber;
			case 1:
				return glassAmber;
			case 2:
				return brickAmber;
		}
		return null;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockAmber = iconRegister.registerIcon("erebus:blockAmber");
		glassAmber = iconRegister.registerIcon("erebus:glassAmber");
		brickAmber = iconRegister.registerIcon("erebus:brickAmber");

		connectedGlass = new IIcon[connectedGlassStr.length];
		for (int a = 0; a < connectedGlassStr.length; a++)
			connectedGlass[a] = iconRegister.registerIcon("erebus:glassAmber_" + connectedGlassStr[a]);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs tab, List list) {
		list.add(new ItemStack(id, 1, 0));
		list.add(new ItemStack(id, 1, 1));
		list.add(new ItemStack(id, 1, 2));
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockAmber.class;
	}
}