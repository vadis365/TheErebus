package erebus.datagen.advancement;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

import static net.minecraft.advancements.AdvancementType.TASK;

public class AgricultureAdvancements extends ModAdvancements {

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

    }
}
