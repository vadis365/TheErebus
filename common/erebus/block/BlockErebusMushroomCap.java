package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;

public class BlockErebusMushroomCap extends Block {
	
    public static final String[] shroomTypes = new String[] {"BulbCap", "1", "2", "3", "4"};//need names

    private final int mushroomType;
    @SideOnly(Side.CLIENT)
    private Icon[] iconArray;
    @SideOnly(Side.CLIENT)
    private Icon[] stalkIcon;
    @SideOnly(Side.CLIENT)
    private Icon[] insideIcon;

    public BlockErebusMushroomCap(int id, Material material, int type) {
        super(id, material);
        mushroomType = type;
    }

    @SideOnly(Side.CLIENT)
	@Override
    public Icon getIcon(int side, int meta) {
        return meta == 10 && side > 1 ? stalkIcon[mushroomType] : (meta >= 1 && meta <= 9 && side == 1 ? iconArray[mushroomType] : (meta >= 1 && meta <= 3 && side == 2 ? iconArray[mushroomType] : (meta >= 7 && meta <= 9 && side == 3 ? iconArray[mushroomType] : ((meta == 1 || meta == 4 || meta == 7) && side == 4 ? iconArray[mushroomType] : ((meta == 3 || meta == 6 || meta == 9) && side == 5 ? iconArray[mushroomType] : (meta == 14 ? iconArray[mushroomType] : (meta == 15 ? stalkIcon[mushroomType] : insideIcon[mushroomType])))))));
    }

	@Override
    public int quantityDropped(Random rand) {
        int amount = rand.nextInt(10) - 7;
        if (amount < 0)
            amount = 0;
        return amount;
    }

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
        return ModBlocks.erebusPlantSmall.blockID;
    }
	
	@Override
	public int damageDropped(int meta) {
			return mushroomType;
	}

    @SideOnly(Side.CLIENT)
	@Override
    public void registerIcons(IconRegister icon) {
        iconArray = new Icon[shroomTypes.length];
        insideIcon = new Icon[shroomTypes.length];
        stalkIcon = new Icon[shroomTypes.length];
        
        for (int i = 0; i < this.iconArray.length; ++i) {
            iconArray[i] = icon.registerIcon("erebus:mushroom" + shroomTypes[i]);
            insideIcon[i] = icon.registerIcon("erebus:mushroom" + shroomTypes[i] + "Inside");
            stalkIcon[i] = icon.registerIcon("erebus:mushroom" + shroomTypes[i] + "Stalk");
        }
    }
}
