package erebus.registries.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties BAMBOO_SOUP = food(3, 0.2F).build();
    public static final FoodProperties BEETLE_LARVA_RAW = food(1, 0.1F).build();
    public static final FoodProperties BEETLE_LARVA_COOKED = food(3, 0.4F).build();
    public static final FoodProperties GRASSHOPPER_LEG_RAW = food(1, 0.1F).build();
    public static final FoodProperties GRASSHOPPER_LEG_COOKED = food(4, 0.4F).build();
    public static final FoodProperties TARANTULA_LEG_RAW = food(1, 0.1F).build();
    public static final FoodProperties TARANTULA_LEG_COOKED = food(5, 0.4F).build();
    public static final FoodProperties MELONADE = food(3, 0.2F).build();
    public static final FoodProperties MELONADE_SPARKLY = food(5, 0.4F).build();
    public static final FoodProperties LARVAE_ON_STICK = food(9, 0.5F).build();
    public static final FoodProperties HONEY_SANDWICH = food(6, 0.5F).build();
    public static final FoodProperties DARK_FRUIT = food(2, 0.3F).build();
    public static final FoodProperties TITAN_CHOP_RAW = food(4, 0.3F).build();
    public static final FoodProperties TITAN_CHOP_COOKED = food(8, 0.8F).build();
    public static final FoodProperties SWAMP_BERRIES = food(1, 0.1F).build();
    public static final FoodProperties CABBAGE = food(1, 0.3F).build();
    public static final FoodProperties TITAN_STEW_COOKED = food(20, 4.0F).build();
    public static final FoodProperties PRICKLY_PEAR_RAW = food(3, 0.3F).build();
    public static final FoodProperties PRICKLY_PEAR_COOKED = food(4, 0.5F).build();
    public static final FoodProperties DARK_FRUIT_PIE = food(8, 0.3F).build();
    public static final FoodProperties GREEN_TEA_GRASSHOPPER = food(4, 0.4F).build();
    public static final FoodProperties MONEY_HONEY = food(4, 0.4F).build();
    public static final FoodProperties NOTHING_IN_THE_MIDDLE = food(4, 0.4F).build();
    public static final FoodProperties GREEN_GIANT = food(4, 0.4F).build();
    public static final FoodProperties SEEDY_GOODNESS = food(4, 0.4F).build();
    public static final FoodProperties GIVIN_ME_THE_BLUES = food(4, 0.4F).build();
    public static final FoodProperties HOT_HOT_BABY = food(4, 0.4F).build();
    public static final FoodProperties DONT_MEDDLE_WITH_THE_NETTLE = food(4, 0.4F).build();
    public static final FoodProperties LIQUID_GOLD = food(4, 0.4F).build();
    public static final FoodProperties BRYUFS_BREW = food(4, 0.4F).build();
    public static final FoodProperties LIFE_BLOOD = food(4, 0.4F).build();
    public static final FoodProperties HEART_BERRIES = food(4, 0.4F).build();
    public static final FoodProperties STAG_HEART_RAW = food(4, 0.4F).build();
    public static final FoodProperties STAG_HEART_COOKED = food(4, 0.4F).build();
    public static final FoodProperties JADE_BERRIES = food(1, 0.1F).build();

    private static FoodProperties.Builder food(int nutrition, float saturationModifier) {
        return new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturationModifier);
    }
}
