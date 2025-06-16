package erebus.datagen.advancement;

import erebus.Erebus;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.function.Consumer;

public abstract class ModAdvancements implements AdvancementProvider.AdvancementGenerator {

    private Consumer<AdvancementHolder> consumer;
    private ExistingFileHelper existingFileHelper;
    private final String prefix;

    public ModAdvancements(String prefix) {
        this.prefix = prefix;
    }

    protected void setConsumer(Consumer<AdvancementHolder> consumer) {
        this.consumer = consumer;
    }

    protected void setExistingFileHelper(ExistingFileHelper existingFileHelper) {
        this.existingFileHelper = existingFileHelper;
    }

    /**
     * Generic method to create advancement with one criterion
     * @param displayItem The item to display for the advancement
     * @param name The key for title and description translations
     * @param criterionName The name of the criterion
     * @param criterion Criteria for completion
     */
    protected AdvancementHolder createSimpleAdvancement(AdvancementType type, ItemLike displayItem, String name, String criterionName, Criterion<?> criterion) {
        return save(getBuilder(type, displayItem, name, criterionName, criterion), name);
    }

    /**
     * Generic method to create advancement with one criterion and parent
     * @param displayItem The item to display for the advancement
     * @param name The key for title and description translations
     * @param criterionName The name of the criterion
     * @param criterion Criteria for completion
     */
    protected AdvancementHolder createSimpleAdvancementWithParent(AdvancementHolder parent, AdvancementType type, ItemLike displayItem, String name, String criterionName, Criterion<?> criterion) {
        return save(getBuilder(type, displayItem, name, criterionName, criterion).parent(parent), name);
    }

    protected Advancement.Builder getAdvancedBuilder(AdvancementType type, ItemLike displayItem, String name) {
        return Advancement.Builder.advancement()
                .display(displayItem, getTitle(name), getDescription(name), null, type, true, true, false);
    }

    protected Advancement.Builder getAdvancedBuilderWithParent(AdvancementHolder parent, AdvancementType type, ItemLike displayItem, String name) {
        return getAdvancedBuilder(type, displayItem, name).parent(parent);
    }

    protected Advancement.Builder getBuilder(AdvancementType type, ItemLike displayItem, String name, String criterionName, Criterion<?> criterion) {
        return getRootBuilder(type, displayItem, name, null)
                .addCriterion(criterionName, criterion)
                .requirements(AdvancementRequirements.allOf(List.of(criterionName)));
    }

    protected Advancement.Builder getRootBuilder(AdvancementType type, ItemLike displayItem, String name, ResourceLocation background) {
        return Advancement.Builder.advancement()
                .display(displayItem, getTitle(name), getDescription(name), background, type, true, true, false);
    }

    protected AdvancementHolder save(Advancement.Builder builder, String name) {
        return builder.save(consumer, Erebus.prefix(getName(name)), existingFileHelper);
    }

    protected Component getTitle(String name) {
        return Component.translatable("advancement.%s.%s.title".formatted(Erebus.MODID, getName(name)));
    }

    protected Component getDescription(String name) {
        return Component.translatable("advancement.%s.%s.desc".formatted(Erebus.MODID, getName(name)));
    }

    protected Criterion<?> hasItems(ItemLike... items) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(items);
    }

    private String getName(String name) {
        return "%s/%s".formatted(prefix, name);
    }
}
