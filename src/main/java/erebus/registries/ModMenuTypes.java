package erebus.registries;

import erebus.Erebus;
import erebus.inventory.PetrifiedCraftingMenu;
import erebus.inventory.UmberFurnaceMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, Erebus.MODID);

    public static final Supplier<MenuType<PetrifiedCraftingMenu>> PETRIFIED_CRAFTING_MENU = MENU_TYPES.register("petrified_crafting_table", () -> new MenuType<>(PetrifiedCraftingMenu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final Supplier<MenuType<UmberFurnaceMenu>> UMBER_FURNACE_MENU = MENU_TYPES.register("umberfurnace", () -> new MenuType<>(UmberFurnaceMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static void register(IEventBus bus) {
        MENU_TYPES.register(bus);
    }
}
