package erebus.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import erebus.lib.EnumWood;
import erebus.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;

public class BlockErebusLeaves extends BlockLeaves {
	private final EnumWood wood;
	@SideOnly(Side.CLIENT)
	private IIcon fastIcon;

	public BlockErebusLeaves(EnumWood wood) {
		this.wood = wood;
		setHardness(0.2F);
		setLightOpacity(1);
		setCreativeTab(ModTabs.plants);
		setStepSound(Block.soundTypeGrass);
		setBlockName(Reference.MOD_ID + ".leaves." + wood.name());
		setBlockTextureName(Reference.MOD_ID + ":leaves_" + wood.name().toLowerCase());
	}

	@Override
	public String getLocalizedName() {
		return StatCollector.translateToLocal("tile." + Reference.MOD_ID + ".leaves_" + wood.getUnlocalisedName() + ".name");
	}

	@Override
	public String[] func_150125_e() {
		return new String[] { wood.name() };
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Item.getItemFromBlock(wood.getSapling());
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return Minecraft.getMinecraft().gameSettings.fancyGraphics ? blockIcon : fastIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		return !Minecraft.getMinecraft().gameSettings.fancyGraphics && world.getBlock(x, y, z) == this ? false : true;
	}

	@Override
	public boolean isOpaqueCube() {
		return Blocks.leaves.isOpaqueCube();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon(getTextureName());
		fastIcon = reg.registerIcon(getTextureName() + "_fast");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta) {
		return 0xFFFFFF;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		return 0xFFFFFF;
	}
}