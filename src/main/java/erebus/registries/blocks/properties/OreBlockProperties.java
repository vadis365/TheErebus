package erebus.registries.blocks.properties;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class OreBlockProperties {

    public static final Properties IRON;
    public static final Properties GOLD;
    public static final Properties COAL;
    public static final Properties DIAMOND;
    public static final Properties EMERALD;
    public static final Properties LAPIS;
    public static final Properties QUARTZ;
    public static final Properties PETRIFIED_QUARTZ;
    public static final Properties COPPER;
    public static final Properties SILVER;
    public static final Properties TIN;
    public static final Properties LEAD;
    public static final Properties ALUMINUM;
    public static final Properties JADE;
    public static final Properties ENCRUSTED_DIAMOND;
    public static final Properties FOSSIL;
    public static final Properties GNEISS;
    public static final Properties PETRIFIED_WOOD;
    public static final Properties TEMPLE;

    private static final Properties ORE = Properties.of()
            .strength(3.0F)
            .explosionResistance(5.0F)
            .sound(SoundType.STONE);

    static {
        IRON = ORE;
        GOLD = ORE;
        COAL = ORE;
        DIAMOND = ORE;
        EMERALD = ORE;
        LAPIS = ORE;
        QUARTZ = ORE;
        PETRIFIED_QUARTZ = ORE;
        COPPER = ORE;
        SILVER = ORE;
        TIN = ORE;
        LEAD = ORE;
        ALUMINUM = ORE;
        JADE = ORE;
        ENCRUSTED_DIAMOND = ORE;
        FOSSIL = ORE;
        GNEISS = ORE;
        PETRIFIED_WOOD = ORE;
        TEMPLE = ORE;
    }
}
