package erebus.client.render.init;

import erebus.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BlockRenderingRegistry {

    private static ItemModelMesher modelMesher;

    public static void init() {
        modelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

    }

    private static void registerBlock(Block block, int meta, String unlocalizedName) {
        modelMesher.register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(Reference.MOD_ID + ":" + unlocalizedName, "inventory"));
    }

    private static void registerBlock(Block block, String unlocalizedName) {
        registerBlock(block, 0, unlocalizedName);
    }

    private static void registerBlock(Block block) {
        registerBlock(block, 0, block.getUnlocalizedName().substring(5));
    }
}
