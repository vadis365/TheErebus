package erebus.client.render.init;

import erebus.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemRenderingRegistry {

    private static ItemModelMesher modelMesher;

    public static void init() {
        modelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

    }

    private static void registerItem(Item item, int meta, String unlocalizedName) {
        modelMesher.register(item, meta, new ModelResourceLocation(Reference.MOD_ID + ":" + unlocalizedName, "inventory"));
    }

    private static void registerItem(Item item, String unlocalizedName) {
        registerItem(item, 0, unlocalizedName);
    }

    private static void registerItem(Item item) {
        registerItem(item, 0, item.getUnlocalizedName().substring(5));
    }
}
