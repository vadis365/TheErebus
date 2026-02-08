package erebus.datagen;

import erebus.Erebus;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.util.Set;
import java.util.stream.Stream;

public class ModModelProvider extends ModelProvider {

    private final ModBlockStates blockStates = new ModBlockStates();
    private final ModItemModels itemModels = new ModItemModels();

    private static final Set<Block> EXCLUDED_BLOCKS = Set.of(
            ModBlocks.ALGAE.get(),
            ModBlocks.AMBER_GLASS.get(),
            ModBlocks.BAMBOO_CRATE.get(),
            ModBlocks.BAMBOO_EXTENDER.get(),
            ModBlocks.BAMBOO_PIPE.get(),
            ModBlocks.BAMBOO_PIPE_EXTRACT.get(),
            ModBlocks.BAMBOO_TORCH.get(),
            ModBlocks.COLOSSAL_BAMBOO.get(),
            ModBlocks.COMPOSTER.get(),
            ModBlocks.DARK_CAPPED_MUSHROOM.get(),
            ModBlocks.DESERT_SHRUB.get(),
            ModBlocks.DARK_FRUIT_VINE.get(),
            ModBlocks.DROUGHTED_SHRUB.get(),
            ModBlocks.DUST_LAYER.get(),
            ModBlocks.DUNG_SPAWNER_BOT_FLY.get(),
            ModBlocks.DUNG_SPAWNER_FLY.get(),
            ModBlocks.DUTCH_CAP_MUSHROOM.get(),
            ModBlocks.GLOW_GEM_ACTIVE.get(),
            ModBlocks.GLOW_GEM_INACTIVE.get(),
            ModBlocks.GLOWSHROOM_STALK.get(),
            ModBlocks.GNEISS_VENT.get(),
            ModBlocks.GRANDMAS_SHOES_MUSHROOM.get(),
            ModBlocks.INSECT_REPELLENT.get(),
            ModBlocks.KAIZERS_FINGERS_MUSHROOM.get(),
            ModBlocks.LIQUIFIER.get(),
            ModBlocks.LOG_HOLLOW.get(),
            ModBlocks.MIRE_CORAL.get(),
            ModBlocks.MOSS.get(),
            ModBlocks.MOSS_CULTIVATED.get(),
            ModBlocks.MOULD.get(),
            ModBlocks.MOULD_CULTIVATED.get(),
            ModBlocks.MUCUS_BOMB.get(),
            ModBlocks.PRICKLY_PEAR.get(),
            ModBlocks.SARCASTIC_CZECH_MUSHROOM.get(),
            ModBlocks.SILO_ROOF.get(),
            ModBlocks.SILO_SUPPORTS.get(),
            ModBlocks.SILO_TANK.get(),
            ModBlocks.SWAMP_VENT.get(),
            ModBlocks.TALL_FERN.get(),
            ModBlocks.THORNS.get(),
            ModBlocks.UMBER_GOLEM_STATUE.get(),
            ModBlocks.VELOCITY_BLOCK.get(),
            ModBlocks.VELOCITY_BLOCK_LIGHTNING_SPEED.get()
    );

    private static final Set<Item> EXCLUDED_ITEMS = Set.of(
            ModItems.ANTLION_SPAWN_EGG.get(),
            ModItems.ANTI_VENOM_BUCKET.get(),
            ModItems.BAMBOO_SHIELD.get(),
            ModItems.BAMBOO_SHOOT.get(),
            ModItems.BEETLE_JUICE_BUCKET.get(),
            ModItems.DEATH_COMPASS.get(),
            ModItems.ENHANCED_SCORPION_PINCER.get(),
            ModItems.EREBUS_MAP.get(),
            ModItems.EREBUS_MAP_FILLED.get(),
            ModItems.EXOSKELETON_SHIELD.get(),
            ModItems.FORMIC_ACID_BUCKET.get(),
            ModItems.HONEY_BUCKET.get(),
            ModItems.JADE_SHIELD.get(),
            ModItems.PORTAL_ACTIVATOR.get(),
            ModItems.QUAKE_HAMMER.get(),
            ModItems.REIN_EXOSKELETON_SHIELD.get(),
            ModItems.RHINO_EXOSKELETON_SHIELD.get(),
            ModItems.WAND_OF_ANIMATION.get(),
            ModItems.WAND_OF_PRESERVATION.get(),
            ModItems.WASP_DAGGER.get(),
            ModItems.WASP_SWORD.get(),
            ModItems.WEB_SLINGER.get(),
            ModItems.WEB_SLINGER_WITHER.get(),
            ModItems.WOODLOUSE_BALL.get()
    );

    public ModModelProvider(PackOutput output) {
        super(output, Erebus.MODID);
    }

    @Override
    protected void registerModels(@NonNull BlockModelGenerators blockModelGenerators, @NonNull ItemModelGenerators itemModelGenerators) {
        blockStates.itemModels = itemModelGenerators;

        blockStates.registerModels(blockModelGenerators);
        itemModels.registerModels(itemModelGenerators);
    }

    @Override
    protected @NonNull Stream<? extends Holder<Block>> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().filter(holder -> !EXCLUDED_BLOCKS.contains(holder.value()));
    }

    @Override
    protected @NonNull Stream<? extends Holder<Item>> getKnownItems() {
        return ModItems.ITEMS.getEntries().stream().filter(holder -> !EXCLUDED_ITEMS.contains(holder.value()));
    }
}
