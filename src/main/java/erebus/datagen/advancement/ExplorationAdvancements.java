package erebus.datagen.advancement;

import erebus.Erebus;
import erebus.registries.ModItems;
import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.blocks.providers.WoodBlocks;
import erebus.registries.world.ModDimensionRegistries;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.ChangeDimensionTrigger;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

import static net.minecraft.advancements.AdvancementType.TASK;

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

        antivenom = save(
                getAdvancedBuilderWithParent(smoothie_blender, TASK, ModItems.ANTI_VENOM_BOTTLE, "antivenom")
                        .addCriterion("has_antivenom_bottle", hasItems(ModItems.ANTI_VENOM_BOTTLE))
                        .addCriterion("has_antivenom_bambucket", hasItems(ModItems.ANTI_VENOM_BUCKET))
                        .addCriterion("has_antivenom_bucket", hasItems(ModItems.ANTI_VENOM_BUCKET))
                        .requirements(AdvancementRequirements.anyOf(List.of("has_antivenom_bottle", "has_antivenom_bambucket", "has_antivenom_bucket"))),
                "antivenom"
        );



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

        //TODO: Find Beetles
        beetlejuice = save(
                getAdvancedBuilderWithParent(root, TASK, ModItems.BAMBUCKET, "beetlejuice")
                        .addCriterion("bambucket", hasItems(ModItems.BAMBUCKET))
                        .addCriterion("bucket", hasItems(ModItems.BAMBUCKET))
                        .requirements(AdvancementRequirements.anyOf(List.of("bambucket", "bucket"))),
                "beetlejuice"
        );
        beetledrink = createSimpleAdvancementWithParent(beetlejuice, TASK, ModItems.BAMBUCKET, "beetledrink", "drink_juice", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.BAMBUCKET));
    }
}
