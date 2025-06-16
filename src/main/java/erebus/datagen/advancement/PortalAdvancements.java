package erebus.datagen.advancement;

import erebus.registries.ModItems;
import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

import static net.minecraft.advancements.AdvancementType.GOAL;
import static net.minecraft.advancements.AdvancementType.TASK;

public class PortalAdvancements extends ModAdvancements {

    public AdvancementHolder root;
    public AdvancementHolder altar;
    public AdvancementHolder portal;
    public AdvancementHolder portal_activator;
    public AdvancementHolder gaean_gem;

    public PortalAdvancements() {
        super("portal");
    }

    @Override
    public void generate(HolderLookup.@NotNull Provider provider, @NotNull Consumer<AdvancementHolder> consumer, @NotNull ExistingFileHelper existingFileHelper) {
        setConsumer(consumer);
        setExistingFileHelper(existingFileHelper);

        root = save(
                getRootBuilder(TASK, Blocks.STONE_BRICKS, "root", ResourceLocation.withDefaultNamespace("textures/block/stonebrick_mossy.png"))
                        .addCriterion("diamond", hasItems(Items.DIAMOND))
                        .addCriterion("emerald", hasItems(Items.EMERALD))
                        .addCriterion("obsidian", hasItems(Blocks.OBSIDIAN))
                        .requirements(AdvancementRequirements.allOf(List.of("diamond", "emerald", "obsidian"))),
                "root");

        altar = createSimpleAdvancementWithParent(root, TASK, OtherBlocks.OFFERING_ALTAR, "altar", "obtain_altar", hasItems(OtherBlocks.OFFERING_ALTAR));
        gaean_gem = createSimpleAdvancementWithParent(altar, TASK, ModItems.GAEAN_GEM, "gaean_gem", "obtain_gem", hasItems(ModItems.GAEAN_GEM));

        portal_activator = save(
                getAdvancedBuilderWithParent(gaean_gem, TASK, ModItems.PORTAL_ACTIVATOR, "portal_activator")
                        .addCriterion("obtain_activator", hasItems(ModItems.PORTAL_ACTIVATOR))
                        .addCriterion("obtain_keystone", hasItems(OtherBlocks.GAEAN_KEYSTONE))
                        .requirements(AdvancementRequirements.allOf(List.of("obtain_activator", "obtain_keystone"))),
                "portal_activator"
        );

        portal = createSimpleAdvancementWithParent(
                portal_activator,
                GOAL,
                Blocks.OAK_LEAVES,
                "portal",
                "portal_activate",
                ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(
                        LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(OtherBlocks.GAEAN_KEYSTONE.get())),
                        ItemPredicate.Builder.item().of(ModItems.PORTAL_ACTIVATOR.get())
                )
        );
    }
}
