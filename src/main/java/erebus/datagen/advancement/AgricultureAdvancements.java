package erebus.datagen.advancement;

import erebus.Erebus;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;

import java.util.List;
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
        root = save(
                getRootBuilder(TASK, Blocks.STONE_BRICKS, "root", Erebus.prefix("textures/block/planks_varnished.png"))
                        .addCriterion("diamond", hasItems(Items.DIAMOND))
                        .addCriterion("emerald", hasItems(Items.EMERALD))
                        .addCriterion("obsidian", hasItems(Blocks.OBSIDIAN))
                        .requirements(AdvancementRequirements.allOf(List.of("diamond", "emerald", "obsidian"))),
                "root");
    }
}
