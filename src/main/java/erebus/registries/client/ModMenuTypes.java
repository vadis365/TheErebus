package erebus.registries.client;

import erebus.Erebus;
import erebus.inventory.server.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, Erebus.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<PetrifiedCraftingMenu>> PETRIFIED_CRAFTING_MENU = MENU_TYPES.register("petrified_crafting_table", () -> new MenuType<>(PetrifiedCraftingMenu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final DeferredHolder<MenuType<?>, MenuType<UmberFurnaceMenu>> UMBER_FURNACE_MENU = MENU_TYPES.register("umberfurnace", () -> new MenuType<>(UmberFurnaceMenu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final DeferredHolder<MenuType<?>, MenuType<LiquifierMenu>> LIQUIFIER = MENU_TYPES.register("liquifier", () -> new MenuType<>(LiquifierMenu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final DeferredHolder<MenuType<?>, MenuType<HoneyCombMenu>> HONEY_COMB = MENU_TYPES.register("honey_comb", () -> new MenuType<>(HoneyCombMenu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final DeferredHolder<MenuType<?>, MenuType<BambooExtenderMenu>> BAMBOO_EXTENDER = MENU_TYPES.register("bamboo_extender", () -> new MenuType<>(BambooExtenderMenu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final DeferredHolder<MenuType<?>, MenuType<BambooCrateMenu>> BAMBOO_CRATE = MENU_TYPES.register("bamboo_crate", () -> new MenuType<>(BambooCrateMenu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final DeferredHolder<MenuType<?>, MenuType<BambooCrateMenu>> ANIMATED_BAMBOO_CRATE = MENU_TYPES.register("animated_bamboo_crate", () -> new MenuType<>(BambooCrateMenu::animatedClient, FeatureFlags.DEFAULT_FLAGS));
    public static final DeferredHolder<MenuType<?>, MenuType<ColossalCrateMenu>> COLOSSAL_CRATE = MENU_TYPES.register("colossal_crate", () -> new MenuType<>(ColossalCrateMenu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final DeferredHolder<MenuType<?>, MenuType<ComposterMenu>> COMPOSTER = MENU_TYPES.register("composter", () -> new MenuType<>(ComposterMenu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final DeferredHolder<MenuType<?>, MenuType<SiloTankMenu>> SILO_TANK = MENU_TYPES.register("silo_tank", () -> new MenuType<>(SiloTankMenu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final DeferredHolder<MenuType<?>, MenuType<BlenderMenu>> BLENDER = MENU_TYPES.register("blender", () -> new MenuType<>(BlenderMenu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final DeferredHolder<MenuType<?>, MenuType<BlackAntMenu>> BLACK_ANT = MENU_TYPES.register("black_ant", () -> new MenuType<>(BlackAntMenu::new, FeatureFlags.DEFAULT_FLAGS));

    static {
    }
}
