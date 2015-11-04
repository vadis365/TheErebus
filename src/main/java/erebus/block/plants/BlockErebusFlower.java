package erebus.block.plants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.item.block.ItemBlockErebusFlower;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockErebusFlower extends Block implements ISubBlocksBlock {

	public enum FLOWER_TYPE {
		BLACK_PETAL,
		RED_PETAL,
		BROWN_PETAL,
		BLUE_PETAL,
		PURPLE_PETAL,
		CYAN_PETAL,
		LIGHT_GRAY_PETAL,
		GRAY_PETAL,
		PINK_PETAL,
		YELLOW_PETAL,
		LIGHT_BLUE_PETAL,
		MAGENTA_PETAL,
		ORANGE_PETAL,
		WHITE_PETAL,
		EXPLODING_STIGMA,
		STEM
	}

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public BlockErebusFlower() {
		super(Material.plants);
		setCreativeTab(ModTabs.plants);
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
		if (meta == FLOWER_TYPE.EXPLODING_STIGMA.ordinal())
			world.createExplosion(player, x, y, z, 3, world.getGameRules().getGameRuleBooleanValue("mobGriefing"));
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, meta, fortune);

		if (meta == FLOWER_TYPE.EXPLODING_STIGMA.ordinal())
			ret.add(new ItemStack(Items.gunpowder));
		else
			ret.add(new ItemStack(this, 1, meta));

		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		return getRenderColor(world.getBlockMetadata(x, y, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta) {
		if (meta == FLOWER_TYPE.EXPLODING_STIGMA.ordinal() || meta == FLOWER_TYPE.STEM.ordinal())
			return super.getRenderColor(meta);

		float[] colour = EntitySheep.fleeceColorTable[BlockColored.func_150032_b(Utils.getFlowerMetadata(meta))];
		return Utils.getColour((int) (colour[0] * 255), (int) (colour[1] * 255), (int) (colour[2] * 255));
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 16; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta < 14)
			meta = 2;
		else
			meta -= 14;
		return icons[Math.min(meta, 2)];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		icons = new IIcon[3];
		for (int i = 0; i < icons.length; i++)
			icons[i] = reg.registerIcon("erebus:erebusFlower" + i);
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockErebusFlower.class;
	}
}