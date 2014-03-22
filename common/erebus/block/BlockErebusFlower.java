package erebus.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockErebusFlower extends Block {
	private final float explosionRadius = 3;
	
	public enum FLOWER_BLOCK_TYPE {
		EXPLODING_STIGMA, STEM, BLACK_PETAL, RED_PETAL, BROWN_PETAL, BLUE_PETAL, PURPLE_PETAL, CYAN_PETAL, LIGHT_GRAY_PETAL, GRAY_PETAL, PINK_PETAL, YELLOW_PETAL, LIGHT_BLUE_PETAL, MAGENTA_PETAL, ORANGE_PETAL, WHITE_PETAL
	}

	@SideOnly(Side.CLIENT)
	private Icon[] blockIcon;

	public BlockErebusFlower(int id) {
		super(id, Material.plants);
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
    	if(meta==0) {
    		boolean rule = world.getGameRules().getGameRuleBooleanValue("mobGriefing");
    		world.createExplosion(player, x, y, z, explosionRadius, rule);
    	}
    }


	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> ret = super.getBlockDropped(world, x, y, z, meta, fortune);

		if (meta == 0)
			ret.add(new ItemStack(Item.gunpowder, 1));
		else
			ret.add(new ItemStack(blockID, 1 + new Random().nextInt(3), meta));

		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (int i = 0; i < 16; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return blockIcon[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockIcon = new Icon[16];
		for (int i = 0; i < blockIcon.length; i++)
			blockIcon[i] = reg.registerIcon("erebus:erebusFlower" + i);
	}
}