package erebus.datagen.advancement;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.item.ModItems;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.criterion.BlockPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.ItemUsedOnLocationTrigger;
import net.minecraft.advancements.criterion.LocationPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

import static net.minecraft.advancements.AdvancementType.GOAL;
import static net.minecraft.advancements.AdvancementType.TASK;

public class PortalAdvancements extends ModAdvancementsHelper {

    public AdvancementHolder root;
    public AdvancementHolder altar;
    public AdvancementHolder portal;
    public AdvancementHolder portal_activator;
    public AdvancementHolder gaean_gem;

    public PortalAdvancements() {
        super("portal");
    }

    @Override
    public void generate(HolderLookup.@NotNull Provider provider, @NotNull Consumer<AdvancementHolder> consumer) {
        HolderLookup<Block> blocks = provider.lookupOrThrow(Registries.BLOCK);
        HolderLookup<Item> items = provider.lookupOrThrow(Registries.ITEM);
        setConsumer(consumer);

        root = save(
                getRootBuilder(TASK, Blocks.STONE_BRICKS, "root", Identifier.withDefaultNamespace("textures/block/mossy_stone_bricks.png"))
                        .addCriterion("diamond", hasItems(Items.DIAMOND))
                        .addCriterion("emerald", hasItems(Items.EMERALD))
                        .addCriterion("obsidian", hasItems(Blocks.OBSIDIAN))
                        .requirements(AdvancementRequirements.allOf(List.of("diamond", "emerald", "obsidian"))),
                "root");

        altar = createSimpleAdvancementWithParent(root, TASK, ModBlocks.OFFERING_ALTAR, "altar", "obtain_altar", hasItems(ModBlocks.OFFERING_ALTAR));
        gaean_gem = createSimpleAdvancementWithParent(altar, TASK, ModItems.GAEAN_GEM, "gaean_gem", "obtain_gem", hasItems(ModItems.GAEAN_GEM));

        portal_activator = save(
                getAdvancedBuilderWithParent(gaean_gem, TASK, ModItems.PORTAL_ACTIVATOR, "portal_activator")
                        .addCriterion("obtain_activator", hasItems(ModItems.PORTAL_ACTIVATOR))
                        .addCriterion("obtain_keystone", hasItems(ModBlocks.GAEAN_KEYSTONE))
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
                        LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(blocks, ModBlocks.GAEAN_KEYSTONE.get())),
                        ItemPredicate.Builder.item().of(items, ModItems.PORTAL_ACTIVATOR.get())
                )
        );
    }
}
