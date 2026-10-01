package erebus;

import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@EventBusSubscriber(modid = Erebus.MODID)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    private static final ModConfigSpec.BooleanValue GENERATE_COPPER_ORE = BUILDER
            .comment("Generate Erebus copper ore in new chunks. Disabled by default.")
            .define("generateCopperOre", true);
    private static final ModConfigSpec.BooleanValue GENERATE_TIN_ORE = BUILDER
            .comment("Generate Erebus tin ore in new chunks. Disabled by default.")
            .define("generateTinOre", false);
    private static final ModConfigSpec.BooleanValue GENERATE_SILVER_ORE = BUILDER
            .comment("Generate Erebus silver ore in new chunks. Disabled by default.")
            .define("generateSilverOre", false);
    private static final ModConfigSpec.BooleanValue GENERATE_LEAD_ORE = BUILDER
            .comment("Generate Erebus lead ore in new chunks. Disabled by default.")
            .define("generateLeadOre", false);
    private static final ModConfigSpec.BooleanValue GENERATE_ALUMINUM_ORE = BUILDER
            .comment("Generate Erebus aluminum ore in new chunks. Disabled by default.")
            .define("generateAluminumOre", false);
    private static final ModConfigSpec.BooleanValue PETRIFIED_QUARTZ_GEN = BUILDER
            .comment("Generate petrified quartz inside petrified trees and fallen logs; disabled uses petrified wood rock instead.")
            .define("petrifiedQuartzGen", true);
    private static final ModConfigSpec.BooleanValue GENERATE_GLOWSHROOMS = BUILDER
            .comment("Generate growing glowshroom colonies beneath Fungal Forest umberstone ceilings.")
            .define("generateGlowshrooms", true);
    private static final ModConfigSpec.BooleanValue GENERATE_VENTS = BUILDER
            .comment("Generate gas vent patches in Submerged Swamp.")
            .define("generateVents", true);
    private static final ModConfigSpec.BooleanValue GENERATE_MOSS = BUILDER
            .comment("Generate natural moss patches in Elysian biomes, Fungal Forest, and Submerged Swamp.")
            .define("generateMoss", true);
    private static final ModConfigSpec.BooleanValue MOSS_SPREAD = BUILDER
            .comment("Allow natural moss to spread during random ticks.")
            .define("mossSpread", true);
    private static final ModConfigSpec.BooleanValue MOULD_SPREAD = BUILDER
            .comment("Allow natural mould to spread during random ticks.")
            .define("mouldSpread", true);
    private static final ModConfigSpec.BooleanValue SCORPION_GRAB = BUILDER
            .comment("Allow scorpions to grab players and sting captured victims.")
            .define("scorpionGrab", true);
    private static final ModConfigSpec.BooleanValue DRAGONFLY_GRAB = BUILDER
            .comment("Allow dragonflies to grab players, lift them and drop them.")
            .define("dragonflyGrab", true);
    private static final ModConfigSpec.BooleanValue BOMBARDIER_BLOCK_DESTROY = BUILDER
            .comment("Allow Bombardier Beetles to clear obstructing blocks with explosions. Also respects mob griefing.")
            .define("bombardierBlockDestroy", true);
    private static final ModConfigSpec.ConfigValue<List<? extends String>> ANIMATION_BLACKLIST = BUILDER
            .comment("Block IDs ignored by the Wand of Animation. An empty list disables this blacklist.")
            .defineListAllowEmpty("animationBlacklist", List.of("minecraft:obsidian"), () -> "minecraft:obsidian",
                    entry -> entry instanceof String name && Identifier.tryParse(name.trim()) != null);

    static final ModConfigSpec SPEC = BUILDER.build();
    public static boolean generateCopperOre = true;
    public static boolean generateTinOre = false;
    public static boolean generateSilverOre = false;
    public static boolean generateLeadOre = false;
    public static boolean generateAluminumOre = false;
    public static boolean petrifiedQuartzGen = true;
    public static boolean mossSpread = true;
    public static boolean mouldSpread = true;
    public static boolean generateVents = true;
    public static boolean generateMoss = true;
    public static boolean generateGlowshrooms = true;
    public static boolean scorpionGrab = true;
    public static boolean dragonflyGrab = true;
    public static boolean bombardierBlockDestroy = true;
    public static Set<Identifier> animationBlacklist = Set.of(Identifier.withDefaultNamespace("obsidian"));

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        if (event.getConfig().getSpec() != SPEC) return;
        animationBlacklist = ANIMATION_BLACKLIST.get().stream().map(String::trim).map(Identifier::parse).collect(Collectors.toUnmodifiableSet());
        scorpionGrab = SCORPION_GRAB.get();
        dragonflyGrab = DRAGONFLY_GRAB.get();
        bombardierBlockDestroy = BOMBARDIER_BLOCK_DESTROY.get();
        generateCopperOre = GENERATE_COPPER_ORE.get();
        generateTinOre = GENERATE_TIN_ORE.get();
        generateSilverOre = GENERATE_SILVER_ORE.get();
        generateLeadOre = GENERATE_LEAD_ORE.get();
        generateAluminumOre = GENERATE_ALUMINUM_ORE.get();
        petrifiedQuartzGen = PETRIFIED_QUARTZ_GEN.get();
        generateGlowshrooms = GENERATE_GLOWSHROOMS.get();
        generateVents = GENERATE_VENTS.get();
        generateMoss = GENERATE_MOSS.get();
        mossSpread = MOSS_SPREAD.get();
        mouldSpread = MOULD_SPREAD.get();
    }
}
