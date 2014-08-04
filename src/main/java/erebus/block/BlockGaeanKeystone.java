package erebus.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockGaeanKeystone extends Block
{
    @SideOnly(Side.CLIENT)
    public IIcon icons[];

    public BlockGaeanKeystone()
    {
        super(Material.rock);
        setBlockName("gaeanKeystone");
        setBlockTextureName("erebus:gaeanKeystone");
        setHardness(3.0f);
        setBlockBounds(0f, 0f, 0f, 1f, 0.8125f, 1f);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister r)
    {
        icons = new IIcon[3];
        icons[0] = r.registerIcon("erebus:keystone_top");
        icons[1] = r.registerIcon("erebus:keystone_sides");
        icons[2] = r.registerIcon("erebus:keystone_bottom");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? icons[0] : (side == 0 ? icons[2] : icons[1]);
    }
}
