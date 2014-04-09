package erebus.block;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockMushroom;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ErebusMushroomSmall extends BlockMushroom {
	
	public static final String[] iconPaths = new String[] { "bulbCappedShroom", "mushroomSmall1", "mushroomSmall2", "mushroomSmall3", "mushroomSmall4" };
	public static final Icon[] icons = new Icon[iconPaths.length];
	
    public ErebusMushroomSmall(int id) {
        super(id);
        float var3 = 0.2F;
        this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.7F, 0.9F);
        this.setTickRandomly(true);
    }
    
	@Override
	public void registerIcons(IconRegister reg) {
		int i = 0;
		for (String path : iconPaths)
			icons[i++] = reg.registerIcon("erebus:" + path);
	}

	@Override
	public Icon getIcon(int side, int meta) {
		if (meta < 0 || meta >= icons.length)
			return null;
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (int i = 0; i < icons.length; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	public int damageDropped(int meta) {
			return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		return 1;
	}

	@Override
	public int idDropped(int id, Random random, int fortune) {
		return blockID;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}
	
}
