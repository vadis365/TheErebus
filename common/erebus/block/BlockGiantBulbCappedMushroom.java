package erebus.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockGiantBulbCappedMushroom extends Block {
    @SideOnly(Side.CLIENT)
    private Icon capIcon;
    @SideOnly(Side.CLIENT)
    private Icon stalkIcon;
    @SideOnly(Side.CLIENT)
    private Icon insideIcon;

    public BlockGiantBulbCappedMushroom(int par1, Material material) {
        super(par1, material);
    }

	@Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta) {
        return meta == 10 && side > 1 ? this.stalkIcon : (meta >= 1 && meta <= 9 && side == 1 ? this.capIcon : (meta >= 1 && meta <= 3 && side == 2 ? this.capIcon : (meta >= 7 && meta <= 9 && side == 3 ? this.capIcon : ((meta == 1 || meta == 4 || meta == 7) && side == 4 ? this.capIcon : ((meta == 3 || meta == 6 || meta == 9) && side == 5 ? this.capIcon : (meta == 14 ? this.capIcon : (meta == 15 ? this.stalkIcon : this.insideIcon)))))));
    }

	@Override
    public int quantityDropped(Random rand) {
        int i = rand.nextInt(10) - 7;

        if (i < 0){
            i = 0;
        }

        return i;
    }

	@Override
    public int idDropped(int par1, Random rand, int par3) {
        return Block.mushroomBrown.blockID;
    }

	@Override
    @SideOnly(Side.CLIENT)
    public int idPicked(World world, int x, int y, int z) {
        return Block.mushroomBrown.blockID;
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister icon) {
        this.capIcon = icon.registerIcon("erebus:bulbCappedShroomCap");
        this.insideIcon = icon.registerIcon("erebus:bulbCappedShroomInside");
        this.stalkIcon = icon.registerIcon("erebus:bulbCappedShroomStalk");
    }
}
