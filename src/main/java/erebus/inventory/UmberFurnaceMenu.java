package erebus.inventory;

import erebus.registries.ModMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;

public class UmberFurnaceMenu extends AbstractFurnaceMenu {

    public UmberFurnaceMenu(int id, Inventory inv) {
        super(ModMenuTypes.UMBER_FURNACE_MENU.get(), RecipeType.SMELTING, RecipeBookType.FURNACE, id, inv);
    }

    public UmberFurnaceMenu(int id, Inventory inv, Container container, ContainerData data) {
        super(ModMenuTypes.UMBER_FURNACE_MENU.get(), RecipeType.SMELTING, RecipeBookType.FURNACE, id, inv, container, data);
    }
}
