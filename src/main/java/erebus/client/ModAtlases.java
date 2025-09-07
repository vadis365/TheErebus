package erebus.client;

import erebus.Erebus;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;

public class ModAtlases {

    public static Material PETRIFIED_CHEST;
    public static Material PETRIFIED_CHEST_LEFT;
    public static Material PETRIFIED_CHEST_RIGHT;

    public static void registerPetrifiedChestAtlases() {
        PETRIFIED_CHEST = getChestMaterial("petrified_chest");
        PETRIFIED_CHEST_LEFT = getChestMaterial("petrified_chest_left");
        PETRIFIED_CHEST_RIGHT = getChestMaterial("petrified_chest_right");
    }

    public static Material getChestMaterial(String chestName) {
        return new Material(Sheets.CHEST_SHEET, Erebus.prefix("entity/chest/" + chestName));
    }
}
