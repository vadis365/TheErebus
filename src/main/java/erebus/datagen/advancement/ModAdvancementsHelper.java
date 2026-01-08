package erebus.datagen.advancement;

import erebus.Erebus;
import net.minecraft.advancements.*;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.KilledTrigger;
import net.minecraft.advancements.criterion.PlayerTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class ModAdvancementsHelper implements AdvancementSubProvider {

    private Consumer<AdvancementHolder> consumer;
    private final String prefix;

    public ModAdvancementsHelper(String prefix) {
        this.prefix = prefix;
    }

    protected void setConsumer(Consumer<AdvancementHolder> consumer) {
        this.consumer = consumer;
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

    protected Advancement.Builder getRootBuilder(AdvancementType type, ItemLike displayItem, String name, Identifier background) {
        return Advancement.Builder.advancement()
                .display(displayItem, getTitle(name), getDescription(name), background, type, true, true, false);
    }

    protected AdvancementHolder save(Advancement.Builder builder, String name) {
        return builder.save(consumer, Erebus.prefix(getName(name)));
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

    protected Criterion<?> killed(HolderGetter<EntityType<? extends Entity>> lookup, Supplier<? extends EntityType<?>> entity) {
        return KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(lookup, entity.get()));
    }

    protected Criterion<?> seen(HolderGetter<EntityType<? extends Entity>> lookup, Supplier<? extends EntityType<?>> entity) {
        return PlayerTrigger.TriggerInstance.located(EntityPredicate.Builder.entity().of(lookup, entity.get()));
    }

    private String getName(String name) {
        return "%s/%s".formatted(prefix, name);
    }
}
