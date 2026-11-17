package erebus.datagen.advancement;

import erebus.Erebus;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.entity.ModEntities;
import erebus.registries.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.NonNull;

import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.function.Consumer;

import static net.minecraft.advancements.AdvancementType.TASK;

public class AgricultureAdvancements extends ModAdvancementsHelper {

    public AdvancementHolder root;
    public AdvancementHolder ant_amulet;
    public AdvancementHolder bamboo;
    public AdvancementHolder bamboo_bridge;
    public AdvancementHolder bamboo_crate;
    public AdvancementHolder bamboo_extender;
    public AdvancementHolder bamboo_plant;
    public AdvancementHolder bamboo_soup;
    public AdvancementHolder bee_amulet;
    public AdvancementHolder beecon;
    public AdvancementHolder beetle_breed;
    public AdvancementHolder farm_complete;
    public AdvancementHolder honey;
    public AdvancementHolder honeycomb;
    public AdvancementHolder honeyfoods;
    public AdvancementHolder nectar;
    public AdvancementHolder nerd_pole;
    public AdvancementHolder silo;
    public AdvancementHolder spoon;
    public AdvancementHolder turnip;
    public AdvancementHolder varnished_planks;

    public AgricultureAdvancements() {
        super("agriculture");
    }

    @Override
    public void generate(HolderLookup.@NonNull Provider provider, @NonNull Consumer<AdvancementHolder> consumer) {
        setConsumer(consumer);
        var items = provider.lookupOrThrow(Registries.ITEM);
        var flowers = InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items,
                ModItems.SEED_BLACK.get(), ModItems.SEED_RED.get(), ModItems.SEED_BROWN.get(), ModItems.SEED_BLUE.get(),
                ModItems.SEED_PURPLE.get(), ModItems.SEED_CYAN.get(), ModItems.SEED_LIGHT_GRAY.get(), ModItems.SEED_GRAY.get(),
                ModItems.SEED_PINK.get(), ModItems.SEED_YELLOW.get(), ModItems.SEED_LIGHT_BLUE.get(), ModItems.SEED_MAGENTA.get(),
                ModItems.SEED_ORANGE.get(), ModItems.SEED_WHITE.get(), ModItems.SEED_RAINBOW.get()).build());
        var farmables = new LinkedHashMap<String, Criterion<?>>();
        farmables.put("turnip", hasItems(ModItems.TURNIP));
        farmables.put("cabbage", hasItems(ModItems.CABBAGE_SEEDS));
        farmables.put("shoot", hasItems(ModBlocks.SAPLING_BAMBOO));
        farmables.put("flowers", flowers);
        farmables.put("dark_fruit", hasItems(ModItems.DARK_FRUIT_SEEDS));
        farmables.put("mould", hasItems(ModBlocks.MOULD));
        farmables.put("moss", hasItems(ModBlocks.MOSS));
        farmables.put("mandrake", hasItems(ModItems.MANDRAKE_ROOT));
        farmables.put("swampberry", hasItems(ModBlocks.SWAMP_BERRY_BUSH));
        farmables.put("heartberry", hasItems(ModBlocks.HEART_BERRY_BUSH));
        farmables.put("jadeberry", hasItems(ModBlocks.JADE_BERRY_BUSH));
        farmables.put("prickly", hasItems(ModBlocks.PRICKLY_PEAR));
        var rootBuilder = getRootBuilder(TASK, ModItems.JADE_HOE, "root", Erebus.prefix("block/planks_varnished"));
        farmables.forEach(rootBuilder::addCriterion);
        rootBuilder.addCriterion("jadeberries", hasItems(ModItems.JADE_BERRIES))
                .addCriterion("heartberries", hasItems(ModItems.HEART_BERRIES))
                .addCriterion("swampberries", hasItems(ModItems.SWAMP_BERRIES))
                .addCriterion("bamboo", hasItems(ModItems.BAMBOO));
        root = save(rootBuilder.requirements(net.minecraft.advancements.AdvancementRequirements.Strategy.OR), "root");
        var complete = getAdvancedBuilderWithParent(root, AdvancementType.CHALLENGE, Items.DIAMOND_HOE, "farm_complete");
        farmables.forEach(complete::addCriterion);
        farm_complete = save(complete, "farm_complete");

        varnished_planks = inventory(root, "varnished_planks", "varnished_planks", ModBlocks.PLANKS_VARNISHED, false);
        silo = save(getAdvancedBuilderWithParent(varnished_planks, TASK, ModBlocks.SILO_SUPPORTS, "silo")
                .addCriterion("roof", hasItems(ModBlocks.SILO_ROOF))
                .addCriterion("tank", hasItems(ModBlocks.SILO_TANK))
                .addCriterion("supports", hasItems(ModBlocks.SILO_SUPPORTS)), "silo");
        ant_amulet = inventory(silo, "ant_amulet", "amulet_get", ModItems.ANT_TAMING_AMULET, false);
        nectar = inventory(root, "nectar", "get_nectar", ModItems.NECTAR, true);
        honey = inventory(nectar, "honey", "get_honey", ModItems.HONEY_DRIP, false);
        honeyfoods = save(getAdvancedBuilderWithParent(honey, TASK, ModBlocks.HONEY_TREAT, "honeyfoods")
                .addCriterion("treat", hasItems(ModBlocks.HONEY_TREAT))
                .addCriterion("sandwich", hasItems(ModItems.HONEY_SANDWICH)), "honeyfoods");
        honeycomb = inventory(nectar, "honeycomb", "get_comb", ModBlocks.HONEY_COMB, false);
        bee_amulet = inventory(honeycomb, "bee_amulet", "get_amulet", ModItems.BEE_TAMING_AMULET, false);
        spoon = inventory(bee_amulet, "spoon", "get_spoon", ModItems.NECTAR_COLLECTOR, false);
        beecon = inventory(nectar, "beecon", "get_homing_beecon", ModItems.HOMING_BEECON, false);
        turnip = inventory(root, "turnip", "repellent", ModItems.TURNIP, true);
        var beetle = Optional.of(EntityPredicate.Builder.entity().of(provider.lookupOrThrow(Registries.ENTITY_TYPE), ModEntities.BEETLE.get()).build());
        beetle_breed = save(getAdvancedBuilderWithParent(turnip, TASK, ModItems.BEETLE_LARVA_RAW, "beetle_breed")
                .addCriterion("beetlebred", BredAnimalsTrigger.TriggerInstance.bredAnimals(beetle, Optional.empty(), Optional.empty()))
                .addCriterion("beetlebred2", BredAnimalsTrigger.TriggerInstance.bredAnimals(Optional.empty(), beetle, Optional.empty())), "beetle_breed");
        bamboo = save(Advancement.Builder.advancement().parent(root)
                .display(ModItems.BAMBOO, getTitle("bamboo"), getDescription("bamboo"), null, TASK, true, true, true)
                .addCriterion("collect_bamboo", hasItems(ModItems.BAMBOO))
                .addCriterion("collect_shoot", hasItems(ModBlocks.SAPLING_BAMBOO)), "bamboo");
        bamboo_extender = inventory(bamboo, "bamboo_extender", "craft_extender", ModBlocks.BAMBOO_EXTENDER, false);
        bamboo_bridge = inventory(bamboo_extender, "bamboo_bridge", "craft_bridge", ModBlocks.BAMBOO_BRIDGE, false);
        nerd_pole = inventory(bamboo_extender, "nerd_pole", "craft_pole", ModBlocks.BAMBOO_NERD_POLE, false);
        bamboo_crate = inventory(bamboo, "bamboo_crate", "craft_crate", ModBlocks.BAMBOO_CRATE, false);
        bamboo_soup = inventory(bamboo, "bamboo_soup", "craft_bamboo_soup", ModItems.BAMBOO_SOUP, false);
        bamboo_plant = createSimpleAdvancementWithParent(bamboo_soup, TASK, ModBlocks.SAPLING_BAMBOO, "bamboo_plant", "plant_shoot",
                ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ModBlocks.SAPLING_BAMBOO.get()));
    }

    private AdvancementHolder inventory(AdvancementHolder parent, String name, String criterion, ItemLike item, boolean hidden) {
        return save(Advancement.Builder.advancement().parent(parent)
                .display(item, getTitle(name), getDescription(name), null, TASK, true, true, hidden)
                .addCriterion(criterion, hasItems(item)), name);
    }
}
