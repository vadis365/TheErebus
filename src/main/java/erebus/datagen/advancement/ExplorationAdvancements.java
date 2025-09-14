package erebus.datagen.advancement;

import erebus.Erebus;
import erebus.registries.ModItems;
import erebus.registries.blocks.providers.OreBlocks;
import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.blocks.providers.WoodBlocks;
import erebus.registries.entity.ModEntities;
import erebus.registries.world.ModDimensionRegistries;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.ChangeDimensionTrigger;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.EnterBlockTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

import static net.minecraft.advancements.AdvancementType.*;

public class ExplorationAdvancements extends ModAdvancements {

    public AdvancementHolder root;
    public AdvancementHolder antivenom;
    public AdvancementHolder arborist;
    public AdvancementHolder beetledrink;
    public AdvancementHolder beetlejuice;
    public AdvancementHolder entomology;
    public AdvancementHolder exo_set;
    public AdvancementHolder find_beetles;
    public AdvancementHolder jade_ore;
    public AdvancementHolder jade_set;
    public AdvancementHolder kill_all;
    public AdvancementHolder newspaper;
    public AdvancementHolder petrified_chest;
    public AdvancementHolder petrified_wood;
    public AdvancementHolder planticide;
    public AdvancementHolder poison_sac;
    public AdvancementHolder quicksand;
    public AdvancementHolder reinexo_set;
    public AdvancementHolder repellent;
    public AdvancementHolder rhino_beetle;
    public AdvancementHolder rhino_exo_set;
    public AdvancementHolder smoothie_all;
    public AdvancementHolder smoothie_book;
    public AdvancementHolder smoothie_blender;
    public AdvancementHolder spray_can;
    public AdvancementHolder stung;
    public AdvancementHolder titan_beetle;
    public AdvancementHolder titan_stew;
    public AdvancementHolder traveller;
    public AdvancementHolder water_repellent;
    public AdvancementHolder water_striders;
    public AdvancementHolder whetstone;
    public AdvancementHolder woodlouse;
    public AdvancementHolder woodlouse_ball;

    /**
     * TODO: Fix the buckets with correct fluids
     */
    public ExplorationAdvancements() {
        super("exploration");
    }

    @Override
    public void generate(HolderLookup.@NotNull Provider provider, @NotNull Consumer<AdvancementHolder> consumer, @NotNull ExistingFileHelper existingFileHelper) {
        setConsumer(consumer);
        setExistingFileHelper(existingFileHelper);

        root = save(
                getRootBuilder(TASK, OtherBlocks.PORTAL, "root", Erebus.prefix("textures/block/umberstone.png"))
                        .addCriterion("enter_dimension", ChangeDimensionTrigger.TriggerInstance.changedDimension(Level.OVERWORLD, ModDimensionRegistries.DIMENSION_KEY))
                        .requirements(AdvancementRequirements.allOf(List.of("enter_dimension"))),
                "root");

        smoothie_blender = createSimpleAdvancementWithParent(root, TASK, OtherBlocks.BLENDER, "smoothie_blender", "has_blender", hasItems(OtherBlocks.BLENDER));
        entomology = save(addEntities(getAdvancedBuilderWithParent(root, CHALLENGE, ModItems.PLATE_EXO, "entomology"), false), "entomology");
        petrified_wood = createSimpleAdvancementWithParent(root, TASK, OreBlocks.PETRIFIED_WOOD, "petrified_wood", "has_petrified_wood", hasItems(ModItems.PETRIFIED_WOOD));
        quicksand = createSimpleAdvancementWithParent(root, TASK, OtherBlocks.QUICK_SAND, "quicksand", "has_quicksand", EnterBlockTrigger.TriggerInstance.entersBlock(OtherBlocks.QUICK_SAND.get()));
        kill_all = save(addEntities(getAdvancedBuilderWithParent(entomology, CHALLENGE, ModItems.JADE_SWORD, "kill_all"), true), "kill_all");

        arborist = save(
                getAdvancedBuilderWithParent(root, AdvancementType.CHALLENGE, WoodBlocks.SAPLING_MAHOGANY.get(), "arborist")
                        .addCriterion("collect_mossbark_sapling", InventoryChangeTrigger.TriggerInstance.hasItems(WoodBlocks.SAPLING_MOSSBARK.get()))
                        .addCriterion("collect_asper_sapling", InventoryChangeTrigger.TriggerInstance.hasItems(WoodBlocks.SAPLING_ASPER.get()))
                        .addCriterion("collect_eucalyptus_sapling", InventoryChangeTrigger.TriggerInstance.hasItems(WoodBlocks.SAPLING_EUCALYPTUS.get()))
                        .addCriterion("collect_mahogany_sapling", InventoryChangeTrigger.TriggerInstance.hasItems(WoodBlocks.SAPLING_MAHOGANY.get()))
                        .addCriterion("collect_balsam_sapling", InventoryChangeTrigger.TriggerInstance.hasItems(WoodBlocks.SAPLING_BALSAM.get()))
                        .addCriterion("collect_baobab_sapling", InventoryChangeTrigger.TriggerInstance.hasItems(WoodBlocks.SAPLING_BAOBAB.get()))
                        .addCriterion("collect_marshwood_sapling", InventoryChangeTrigger.TriggerInstance.hasItems(WoodBlocks.SAPLING_MARSHWOOD.get()))
                        .addCriterion("collect_cypress_sapling", InventoryChangeTrigger.TriggerInstance.hasItems(WoodBlocks.SAPLING_CYPRESS.get()))
                        .requirements(AdvancementRequirements.allOf(List.of(
                                "collect_mossbark_sapling",
                                "collect_asper_sapling",
                                "collect_eucalyptus_sapling",
                                "collect_mahogany_sapling",
                                "collect_balsam_sapling",
                                "collect_baobab_sapling",
                                "collect_marshwood_sapling",
                                "collect_cypress_sapling"
                        ))),
                "arborist"
        );
        jade_ore = createSimpleAdvancementWithParent(root, TASK, OreBlocks.JADE, "jade_ore", "has_jade_ore", hasItems(OreBlocks.JADE));

        antivenom = save(
                getAdvancedBuilderWithParent(smoothie_blender, TASK, ModItems.ANTI_VENOM_BOTTLE, "antivenom")
                        .addCriterion("has_antivenom_bottle", hasItems(ModItems.ANTI_VENOM_BOTTLE))
                        .addCriterion("has_antivenom_bambucket", hasItems(ModItems.ANTI_VENOM_BUCKET))
                        .addCriterion("has_antivenom_bucket", hasItems(ModItems.ANTI_VENOM_BUCKET))
                        .requirements(AdvancementRequirements.anyOf(List.of("has_antivenom_bottle", "has_antivenom_bambucket", "has_antivenom_bucket"))),
                "antivenom"
        );

        find_beetles = save(
                getAdvancedBuilderWithParent(entomology, TASK, ModItems.BEETLE_LARVA_RAW, "find_beetles")
                        .addCriterion("find_rhino", seen(ModEntities.BEETLE))
                        .addCriterion("find_titan", seen(ModEntities.BEETLE))
                        .addCriterion("find_stag", seen(ModEntities.BEETLE))
                        .addCriterion("find_bombardier", seen(ModEntities.BOMBARDIER_BEETLE))
                        .addCriterion("find_bombardier_larva", seen(ModEntities.BOMBARDIER_BEETLE_LARVA))
                        .addCriterion("find_beetle", seen(ModEntities.BEETLE))
                        .addCriterion("find_larva", seen(ModEntities.BEETLE_LARVA))
                        .requirements(AdvancementRequirements.allOf(List.of("find_rhino", "find_titan", "find_stag", "find_bombardier", "find_bombardier_larva", "find_beetle", "find_larva"))),
                "find_beetles"
        );

        beetlejuice = save(
                getAdvancedBuilderWithParent(find_beetles, TASK, ModItems.BAMBUCKET, "beetlejuice")
                        .addCriterion("bambucket", hasItems(ModItems.BAMBUCKET))
                        .addCriterion("bucket", hasItems(ModItems.BAMBUCKET))
                        .requirements(AdvancementRequirements.anyOf(List.of("bambucket", "bucket"))),
                "beetlejuice"
        );
        beetledrink = createSimpleAdvancementWithParent(beetlejuice, TASK, ModItems.BAMBUCKET, "beetledrink", "drink_juice", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.BAMBUCKET));

        exo_set = save(
                getAdvancedBuilderWithParent(entomology, GOAL, ModItems.EXOSKELETON_CHESTPLATE, "exo_set")
                        .addCriterion("has_helm", hasItems(ModItems.EXOSKELETON_HELMET))
                        .addCriterion("has_chest", hasItems(ModItems.EXOSKELETON_CHESTPLATE))
                        .addCriterion("has_legs", hasItems(ModItems.EXOSKELETON_LEGGINGS))
                        .addCriterion("has_boots", hasItems(ModItems.EXOSKELETON_BOOTS))
                        .addCriterion("has_shield", hasItems(ModItems.EXOSKELETON_SHIELD))
                        .requirements(AdvancementRequirements.allOf(List.of("has_helm", "has_chest", "has_legs", "has_boots", "has_shield"))),
                "exo_set"
        );

        jade_set = save(
                getAdvancedBuilderWithParent(jade_ore, GOAL, ModItems.JADE_CHESTPLATE, "jade_set")
                        .addCriterion("has_helm", hasItems(ModItems.JADE_HELMET))
                        .addCriterion("has_chest", hasItems(ModItems.JADE_CHESTPLATE))
                        .addCriterion("has_legs", hasItems(ModItems.JADE_LEGGINGS))
                        .addCriterion("has_boots", hasItems(ModItems.JADE_BOOTS))
                        .addCriterion("has_shield", hasItems(ModItems.JADE_SHIELD))
                        .requirements(AdvancementRequirements.allOf(List.of("has_helm", "has_chest", "has_legs", "has_boots", "has_shield"))),
                "jade_set"
        );
        woodlouse = createSimpleAdvancementWithParent(entomology, TASK, WoodBlocks.LOG_HOLLOW, "woodlouse", "killed_woodlouse", killed(ModEntities.BEETLE_LARVA));
        whetstone = save(
                getAdvancedBuilderWithParent(woodlouse, TASK, ModItems.WHETSTONE, "whetstone")
                        .addCriterion("has_powder", hasItems(ModItems.WHETSTONE_POWDER))
                        .addCriterion("has_whetstone", hasItems(ModItems.WHETSTONE))
                        .requirements(AdvancementRequirements.allOf(List.of("has_powder", "has_whetstone"))),
                "whetstone"
        );
        newspaper = createSimpleAdvancementWithParent(whetstone, TASK, ModItems.ROLLED_NEWSPAPER, "newspaper", "has_newspaper", hasItems(ModItems.ROLLED_NEWSPAPER));
        petrified_chest = createSimpleAdvancementWithParent(petrified_wood, TASK, OtherBlocks.PETRIFIED_WOOD_CHEST, "petrified_chest", "has_petrified_chest", hasItems(OtherBlocks.PETRIFIED_WOOD_CHEST));
        poison_sac = createSimpleAdvancementWithParent(entomology, TASK, ModItems.POISON_GLAND, "poison_sac", "has_poison_sac", hasItems(ModItems.POISON_GLAND));
        planticide = createSimpleAdvancementWithParent(poison_sac, TASK, ModItems.PLANTICIDE, "planticide", "has_planticide", hasItems(ModItems.PLANTICIDE));
        reinexo_set = save(
                getAdvancedBuilderWithParent(exo_set, GOAL, ModItems.REIN_EXOSKELETON_CHESTPLATE, "reinexo_set")
                        .addCriterion("has_helm", hasItems(ModItems.REIN_EXOSKELETON_HELMET))
                        .addCriterion("has_chest", hasItems(ModItems.REIN_EXOSKELETON_CHESTPLATE))
                        .addCriterion("has_legs", hasItems(ModItems.REIN_EXOSKELETON_LEGGINGS))
                        .addCriterion("has_boots", hasItems(ModItems.REIN_EXOSKELETON_BOOTS))
                        .addCriterion("has_shield", hasItems(ModItems.REIN_EXOSKELETON_SHIELD))
                        .requirements(AdvancementRequirements.allOf(List.of("has_helm", "has_chest", "has_legs", "has_boots", "has_shield"))),
                "reinexo_set"
        );
        repellent = createSimpleAdvancementWithParent(entomology, TASK, ModItems.REPELLENT, "repellent", "has_repellent", hasItems(ModItems.REPELLENT));
        rhino_beetle = save(
                getAdvancedBuilderWithParent(find_beetles, TASK, ModItems.PLATE_EXO_RHINO, "rhino_beetle")
                        .addCriterion("kill_rhino", killed(ModEntities.BEETLE))
                        .addCriterion("has_plate", hasItems(ModItems.PLATE_EXO_RHINO))
                        .addCriterion("has_horn", hasItems(ModItems.RHINO_BEETLE_HORN))
                        .requirements(AdvancementRequirements.allOf(List.of("kill_rhino", "has_plate", "has_horn"))),
                "rhino_beetle"
        );
        rhino_exo_set = save(
                getAdvancedBuilderWithParent(exo_set, GOAL, ModItems.RHINO_EXOSKELETON_CHESTPLATE, "rhino_exo_set")
                        .addCriterion("has_helm", hasItems(ModItems.RHINO_EXOSKELETON_HELMET))
                        .addCriterion("has_chest", hasItems(ModItems.RHINO_EXOSKELETON_CHESTPLATE))
                        .addCriterion("has_legs", hasItems(ModItems.RHINO_EXOSKELETON_LEGGINGS))
                        .addCriterion("has_boots", hasItems(ModItems.RHINO_EXOSKELETON_BOOTS))
                        .addCriterion("has_shield", hasItems(ModItems.RHINO_EXOSKELETON_SHIELD))
                        .requirements(AdvancementRequirements.allOf(List.of("has_helm", "has_chest", "has_legs", "has_boots", "has_shield"))),
                "rhino_exo_set"
        );
        smoothie_book = createSimpleAdvancementWithParent(smoothie_blender, TASK, ModItems.SMOOTHIE_BOOK, "smoothie_book", "has_smoothie_book", hasItems(ModItems.SMOOTHIE_BOOK));
        smoothie_all = save(
                getAdvancedBuilderWithParent(smoothie_book, CHALLENGE, ModItems.SMOOTHIE_GLASS, "smoothie_all")
                        .addCriterion("has_green_tea_grasshopper", hasItems(ModItems.GREEN_TEA_GRASSHOPPER))
                        .addCriterion("has_money_honey", hasItems(ModItems.MONEY_HONEY))
                        .addCriterion("has_nothing_in_the_middle", hasItems(ModItems.NOTHING_IN_THE_MIDDLE))
                        .addCriterion("has_green_giant", hasItems(ModItems.GREEN_GIANT))
                        .addCriterion("has_seedy_goodness", hasItems(ModItems.SEEDY_GOODNESS))
                        .addCriterion("has_givin_me_the_blues", hasItems(ModItems.GIVIN_ME_THE_BLUES))
                        .addCriterion("has_hot_hot_baby", hasItems(ModItems.HOT_HOT_BABY))
                        .addCriterion("has_dont_meddle_with_the_nettle", hasItems(ModItems.DONT_MEDDLE_WITH_THE_NETTLE))
                        .addCriterion("has_liquid_gold", hasItems(ModItems.LIQUID_GOLD))
                        .addCriterion("has_bryufs_brew", hasItems(ModItems.BRYUFS_BREW))
                        .requirements(AdvancementRequirements.allOf(List.of(
                                "has_green_tea_grasshopper",
                                "has_money_honey",
                                "has_nothing_in_the_middle",
                                "has_green_giant",
                                "has_seedy_goodness",
                                "has_givin_me_the_blues",
                                "has_hot_hot_baby",
                                "has_dont_meddle_with_the_nettle",
                                "has_liquid_gold",
                                "has_bryufs_brew"
                        ))),
                "smoothie_all"
        );
        spray_can = createSimpleAdvancementWithParent(repellent, TASK, ModItems.SPRAY_CAN, "spray_can", "has_spray_can", hasItems(ModItems.SPRAY_CAN));
    }

    private Advancement.Builder addEntities(Advancement.Builder builder, boolean isAll) {
        List<String> entities = List.of(
                "kill_beetle_larva" ,
                "kill_beetle" ,
                "kill_titan" ,
                "kill_rhino" ,
                "kill_stag" ,
                "kill_fly" ,
                "kill_bot_fly" ,
                "kill_mosquito" ,
                "kill_centipede" ,
                "kill_wasp" ,
                "kill_scorpion" ,
                "kill_tarantula" ,
                "kill_solifuge" ,
                "kill_solifuge_small" ,
                "kill_grasshopper" ,
                "kill_locust" ,
                "kill_moth" ,
                "kill_antlion" ,
                "kill_black_widow" ,
                "kill_glow_worm" ,
                "kill_bombardier_beetle" ,
                "kill_scytodes" ,
                "kill_money_spider" ,
                "kill_praying_mantis" ,
                "kill_jumping_spider" ,
                "kill_fire_ant" ,
                "kill_fire_ant_soldier" ,
                "kill_worker_bee" ,
                "kill_velvet_worm" ,
                "kill_dragonfly" ,
                "kill_fungal_weevil" ,
                "kill_crop_weevil" ,
                "kill_woodlouse" ,
                "kill_cicada" ,
                "kill_lava_web_spider" ,
                "kill_chameleon_tick" ,
                "kill_midge_swarm" ,
                "kill_punchroom" ,
                "kill_black_ant" ,
                "kill_zombie_ant" ,
                "kill_zombie_ant_soldier" ,
                "kill_pond_skater" ,
                "kill_magma_crawler" ,
                "kill_bog_maw" ,
                "kill_honey_pot_ant" ,
                "kill_bed_bug" ,
                "kill_antlion_boss" ,
                "kill_tarantula_baby" ,
                "kill_tarantula_boss" ,
                "kill_bf_larva" ,
                "kill_bombardier_beetle_larva" ,
                "kill_crushroom"
        );
        builder
                .addCriterion("kill_beetle_larva", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_beetle", killed(ModEntities.BEETLE))
                .addCriterion("kill_titan", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_rhino", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_stag", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_fly", killed(ModEntities.FLY))
                .addCriterion("kill_bot_fly", killed(ModEntities.BOT_FLY))
                .addCriterion("kill_mosquito", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_centipede", killed(ModEntities.CENTIPEDE))
                .addCriterion("kill_wasp", killed(ModEntities.WASP))
                .addCriterion("kill_scorpion", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_tarantula", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_solifuge", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_solifuge_small", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_grasshopper", killed(ModEntities.GRASSHOPPER))
                .addCriterion("kill_locust", killed(ModEntities.LOCUST))
                .addCriterion("kill_moth", killed(ModEntities.MOTH))
                .addCriterion("kill_antlion", killed(ModEntities.ANTLION))
                .addCriterion("kill_black_widow", killed(ModEntities.BLACK_WIDOW))
                .addCriterion("kill_glow_worm", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_bombardier_beetle", killed(ModEntities.BOMBARDIER_BEETLE))
                .addCriterion("kill_scytodes", killed(ModEntities.SCYTODES))
                .addCriterion("kill_money_spider", killed(ModEntities.MONEY_SPIDER))
                .addCriterion("kill_praying_mantis", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_jumping_spider", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_fire_ant", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_fire_ant_soldier", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_worker_bee", killed(ModEntities.WORKER_BEE))
                .addCriterion("kill_velvet_worm", killed(ModEntities.VELVET_WORM))
                .addCriterion("kill_dragonfly", killed(ModEntities.DRAGON_FLY))
                .addCriterion("kill_fungal_weevil", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_crop_weevil", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_woodlouse", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_cicada", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_lava_web_spider", killed(ModEntities.LAVA_WEB_SPIDER))
                .addCriterion("kill_chameleon_tick", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_midge_swarm", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_punchroom", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_black_ant", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_zombie_ant", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_zombie_ant_soldier", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_pond_skater", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_magma_crawler", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_bog_maw", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_honey_pot_ant", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_bed_bug", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_antlion_boss", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_tarantula_baby", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_tarantula_boss", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_bf_larva", killed(ModEntities.BEETLE_LARVA))
                .addCriterion("kill_bombardier_beetle_larva", killed(ModEntities.BOMBARDIER_BEETLE_LARVA))
                .addCriterion("kill_crushroom", killed(ModEntities.BEETLE_LARVA));

        if(isAll) return builder.requirements(AdvancementRequirements.allOf(entities));
        return builder.requirements(AdvancementRequirements.anyOf(entities));
    }
}
