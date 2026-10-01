package erebus.datagen.tags;

import erebus.Erebus;
import erebus.registries.blocks.ModBlockFamilies;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.tags.ModBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsData extends BlockTagsProvider {

    public ModBlockTagsData(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Erebus.MODID);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        addWoodMaterialTags();
        addStoneBuildingTags();
        tag(Tags.Blocks.GLASS_BLOCKS).add(ModBlocks.AMBER_GLASS.get());
        tag(ModBlockTags.STORAGE_BLOCKS_JADE).add(ModBlocks.JADE_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModBlockTags.STORAGE_BLOCKS_JADE);
        tag(BlockTags.DOORS).add(ModBlocks.DOOR_PETRIFIED.get(), ModBlocks.AMBER_DOOR.get());
        tag(BlockTags.STONE_BUTTONS).add(ModBlocks.UMBERSTONE_BUTTON.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.DOOR_PETRIFIED.get(), ModBlocks.PETRIFIED_CRAFTING_TABLE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ALTAR_BASE.get(), ModBlocks.ALTAR_HEALING.get(),
                ModBlocks.ALTAR_REPAIR.get(), ModBlocks.ALTAR_EXPERIENCE.get(), ModBlocks.ALTAR_LIGHTNING.get(), ModBlocks.OFFERING_ALTAR.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.VELOCITY_BLOCK.get(), ModBlocks.VELOCITY_BLOCK_LIGHTNING_SPEED.get(),
                ModBlocks.GAEAN_KEYSTONE.get(), ModBlocks.UMBER_GOLEM_STATUE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.HONEY_COMB.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.COMPOSTER.get(), ModBlocks.SILO_TANK.get());
        tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.SILO_ROOF.get(), ModBlocks.SILO_SUPPORTS.get());
        tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.BAMBOO_CRATE.get(), ModBlocks.BAMBOO_BRIDGE.get(),
                ModBlocks.BAMBOO_LADDER.get(), ModBlocks.BAMBOO_NERD_POLE.get(), ModBlocks.BAMBOO_EXTENDER.get(),
                ModBlocks.BAMBOO_TORCH.get(), ModBlocks.BAMBOO_PIPE.get(), ModBlocks.BAMBOO_PIPE_EXTRACT.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.UMBER_FURNACE.get(),
                ModBlocks.PETRIFIED_WOOD_ROCK.get(), ModBlocks.PETRIFIED_WOOD_ROCK_2.get(),
                ModBlocks.PETRIFIED_WOOD_ROCK_3.get(), ModBlocks.PETRIFIED_WOOD_ROCK_4.get(),
                ModBlocks.PETRIFIED_WOOD_ROCK_5.get(), ModBlocks.PETRIFIED_WOOD_ROCK_6.get(),
                ModBlocks.PETRIFIED_BARK_RED.get(), ModBlocks.PETRIFIED_BARK_BROWN.get(), ModBlocks.PETRIFIED_LOG_INNER.get(),
                ModBlocks.VOLCANIC_ROCK.get(), ModBlocks.MIR_BRICKS.get(), ModBlocks.MUD_BRICKS.get(), ModBlocks.REIN_EXO.get(), ModBlocks.JADE_BLOCK.get(), ModBlocks.BLENDER.get());
        tag(BlockTags.PLANKS).add(
                ModBlocks.PLANKS_ASPER.get(), ModBlocks.PLANKS_BALSAM.get(), ModBlocks.PLANKS_BAMBOO.get(),
                ModBlocks.PLANKS_BAOBAB.get(), ModBlocks.PLANKS_CYPRESS.get(), ModBlocks.PLANKS_EUCALYPTUS.get(),
                ModBlocks.PLANKS_MAHOGANY.get(), ModBlocks.PLANKS_MARSHWOOD.get(), ModBlocks.PLANKS_MOSSBARK.get(),
                ModBlocks.PLANKS_ROTTEN.get(), ModBlocks.PLANKS_SCORCHED.get(), ModBlocks.PLANKS_VARNISHED.get(),
                ModBlocks.PLANKS_WHITE.get());

        tag(ModBlockTags.DUNGEON_GUARDIAN_IMMUNE).add(
                ModBlocks.GNEISS.get(), ModBlocks.GNEISS_CARVED.get(), ModBlocks.GNEISS_RELIEF.get(),
                ModBlocks.GNEISS_BRICKS.get(), ModBlocks.GNEISS_SMOOTH.get(), ModBlocks.GNEISS_TILES.get(), ModBlocks.GNEISS_TILES_CRACKED.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.GNEISS.get(), ModBlocks.GNEISS_CARVED.get(), ModBlocks.GNEISS_RELIEF.get(),
                ModBlocks.GNEISS_BRICKS.get(), ModBlocks.GNEISS_SMOOTH.get(), ModBlocks.GNEISS_TILES.get(), ModBlocks.GNEISS_TILES_CRACKED.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.GNEISS_VENT.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.TEMPLE_BRICK.get(), ModBlocks.TEMPLE_PILLAR.get(), ModBlocks.TEMPLE_TILE.get());
        // MARK: Paxel
        //noinspection unchecked
        tag(ModBlockTags.MINEABLE_WITH_PAXEL)
                .addTags(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.MINEABLE_WITH_AXE, BlockTags.MINEABLE_WITH_SHOVEL);

        // MARK: Walls
        tag(BlockTags.WALLS)
                .add(
                        ModBlocks.WALL_UMBERSTONE.get(),
                        ModBlocks.WALL_UMBERCOBBLE.get(),
                        ModBlocks.WALL_UMBERCOBBLE_MOSSY.get(),
                        ModBlocks.WALL_UMBERCOBBLE_WEBBED.get(),
                        ModBlocks.WALL_UMBERSTONE_BRICKS.get(),
                        ModBlocks.WALL_UMBERTILE_SMOOTH.get(),
                        ModBlocks.WALL_UMBERTILE_SMOOTH_SMALL.get(),
                        ModBlocks.WALL_UMBERPAVER.get(),
                        ModBlocks.WALL_UMBERPAVER_MOSSY.get(),
                        ModBlocks.WALL_UMBERPAVER_WEBBED.get(),
                        ModBlocks.WALL_AMBER.get(),
                        ModBlocks.WALL_AMBER_BRICKS.get()
                );

        // MARK: Stone
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SPIDER_SPAWNER.get(), ModBlocks.TARANTULA_SPAWNER.get(), ModBlocks.JUMPING_SPIDER_SPAWNER.get())
                .add(ModBlocks.ANTLION_SPAWNER.get(), ModBlocks.MAGMA_CRAWLER_SPAWNER.get(), ModBlocks.DRAGON_FLY_SPAWNER.get())
                .add(ModBlocks.DUNG_SPAWNER_FLY.get(), ModBlocks.DUNG_SPAWNER_BOT_FLY.get())
                .add(ModBlocks.ZOMBIE_ANT_SPAWNER.get(), ModBlocks.ZOMBIE_ANT_SOLDIER_SPAWNER.get())
                .add(ModBlocks.LOCUST_SPAWNER.get(), ModBlocks.WASP_SPAWNER.get(), ModBlocks.WASP_NEST.get(), ModBlocks.STAIRS_WASP_NEST.get(), ModBlocks.ANTLION_EGG.get(), ModBlocks.TARANTULA_EGG.get())
                .add(
                        ModBlocks.UMBERSTONE.get(), ModBlocks.UMBERSTONE_BRICKS.get(), ModBlocks.UMBERCOBBLE.get(),
                        ModBlocks.UMBERCOBBLE_MOSSY.get(), ModBlocks.UMBERCOBBLE_WEBBED.get(), ModBlocks.UMBERTILE_SMOOTH.get(),
                        ModBlocks.UMBERTILE_SMOOTH_SMALL.get(), ModBlocks.UMBERPAVER.get(), ModBlocks.UMBERPAVER_MOSSY.get(),
                        ModBlocks.UMBERPAVER_WEBBED.get(), ModBlocks.UMBERSTONE_PILLAR.get(), ModBlocks.ORE_IRON.get(),
                        ModBlocks.ORE_GOLD.get(), ModBlocks.ORE_COAL.get(), ModBlocks.ORE_DIAMOND.get(), ModBlocks.ORE_EMERALD.get(),
                        ModBlocks.ORE_LAPIS.get(), ModBlocks.ORE_QUARTZ.get(), ModBlocks.ORE_PETRIFIED_QUARTZ.get(), ModBlocks.ORE_COPPER.get(),
                        ModBlocks.ORE_SILVER.get(), ModBlocks.ORE_TIN.get(), ModBlocks.ORE_LEAD.get(), ModBlocks.ORE_ALUMINUM.get(), ModBlocks.ORE_JADE.get(),
                        ModBlocks.ORE_FOSSIL.get(), ModBlocks.ORE_GNEISS.get(), ModBlocks.ORE_PETRIFIED_WOOD.get(), ModBlocks.ORE_TEMPLE.get(),
                        ModBlocks.ORE_ENCRUSTED_DIAMOND.get(), ModBlocks.AMBER.get(), ModBlocks.AMBER_BRICKS.get()
                );

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.QUICK_SAND.get(), ModBlocks.GHOST_SAND.get(), ModBlocks.DUNG.get(), ModBlocks.UMBERGRAVEL.get(), ModBlocks.DUST.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.GIANT_LILY_PAD.get())
                .add(ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK.get(), ModBlocks.DARK_CAPPED_MUSHROOM_STEM.get(),
                        ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK.get(), ModBlocks.DUTCH_CAP_MUSHROOM_STEM.get(),
                        ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK.get(), ModBlocks.GRANDMAS_SHOES_MUSHROOM_STEM.get(),
                        ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK.get(), ModBlocks.KAIZERS_FINGERS_MUSHROOM_STEM.get(),
                        ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK.get(), ModBlocks.SARCASTIC_CZECH_MUSHROOM_STEM.get())
                .add(
                        ModBlocks.LOG_ASPER.get(), ModBlocks.LOG_BALSAM.get(), ModBlocks.LOG_BALSAM_RESINLESS.get(), ModBlocks.LOG_BAOBAB.get(), ModBlocks.LOG_CYPRESS.get(), ModBlocks.LOG_EUCALYPTUS.get(), ModBlocks.LOG_MAHOGANY.get(), ModBlocks.LOG_MARSHWOOD.get(), ModBlocks.LOG_MOSSBARK.get(), ModBlocks.LOG_ROTTEN.get(), ModBlocks.LOG_SCORCHED.get(),
                        ModBlocks.LOG_HOLLOW.get(),
                        ModBlocks.PLANKS_ASPER.get(), ModBlocks.PLANKS_BAMBOO.get(), ModBlocks.PLANKS_BAOBAB.get(), ModBlocks.PLANKS_BALSAM.get(), ModBlocks.PLANKS_CYPRESS.get(), ModBlocks.PLANKS_EUCALYPTUS.get(), ModBlocks.PLANKS_MAHOGANY.get(), ModBlocks.PLANKS_MARSHWOOD.get(), ModBlocks.PLANKS_MOSSBARK.get(), ModBlocks.PLANKS_ROTTEN.get(), ModBlocks.PLANKS_SCORCHED.get(),
                        ModBlocks.PLANKS_VARNISHED.get(), ModBlocks.PLANKS_WHITE.get()
                );

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.ORE_IRON.get(), ModBlocks.ORE_LAPIS.get(), ModBlocks.ORE_COPPER.get(), ModBlocks.ORE_TIN.get(), ModBlocks.ORE_LEAD.get(), ModBlocks.ORE_ALUMINUM.get(), ModBlocks.MIR_BRICKS.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(
                        ModBlocks.ORE_GOLD.get(), ModBlocks.ORE_DIAMOND.get(), ModBlocks.ORE_EMERALD.get(), ModBlocks.ORE_SILVER.get(), ModBlocks.ORE_JADE.get(), ModBlocks.ORE_ENCRUSTED_DIAMOND.get(),
                        ModBlocks.QUICK_SAND.get()
                );


        // MARK: Ores
        tag(Tags.Blocks.ORES_QUARTZ).add(ModBlocks.ORE_QUARTZ.get(), ModBlocks.ORE_PETRIFIED_QUARTZ.get());
        tag(ModBlockTags.ORES_ALUMINUM).add(ModBlocks.ORE_ALUMINUM.get());
        tag(ModBlockTags.ORES_LEAD).add(ModBlocks.ORE_LEAD.get());
        tag(ModBlockTags.ORES_SILVER).add(ModBlocks.ORE_SILVER.get());
        tag(ModBlockTags.ORES_TIN).add(ModBlocks.ORE_TIN.get());
        tag(ModBlockTags.ORES_JADE).add(ModBlocks.ORE_JADE.get());
        tag(ModBlockTags.ORES_PETRIFIED_WOOD).add(ModBlocks.ORE_PETRIFIED_WOOD.get());
        tag(ModBlockTags.ORES_FOSSIL).add(ModBlocks.ORE_FOSSIL.get());
        tag(ModBlockTags.ORES_GNEISS).add(ModBlocks.ORE_GNEISS.get());
        tag(Tags.Blocks.ORES).addTags(ModBlockTags.ORES_ALUMINUM, ModBlockTags.ORES_LEAD, ModBlockTags.ORES_SILVER,
                ModBlockTags.ORES_TIN, ModBlockTags.ORES_JADE, ModBlockTags.ORES_PETRIFIED_WOOD,
                ModBlockTags.ORES_FOSSIL, ModBlockTags.ORES_GNEISS);
        tag(BlockTags.COAL_ORES).add(ModBlocks.ORE_COAL.get());
        tag(BlockTags.IRON_ORES).add(ModBlocks.ORE_IRON.get());
        tag(BlockTags.GOLD_ORES).add(ModBlocks.ORE_GOLD.get());
        tag(BlockTags.DIAMOND_ORES).add(ModBlocks.ORE_DIAMOND.get(), ModBlocks.ORE_ENCRUSTED_DIAMOND.get());
        tag(BlockTags.EMERALD_ORES).add(ModBlocks.ORE_EMERALD.get());
        tag(BlockTags.COPPER_ORES).add(ModBlocks.ORE_COPPER.get());
        tag(BlockTags.LAPIS_ORES).add(ModBlocks.ORE_LAPIS.get());

        tag(BlockTags.CANDLE_CAKES)
                .add(
                        ModBlocks.CANDLE_HONEY_TREAT.get(),
                        ModBlocks.WHITE_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.ORANGE_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.MAGENTA_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.LIGHT_BLUE_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.YELLOW_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.LIME_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.PINK_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.GRAY_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.LIGHT_GRAY_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.CYAN_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.PURPLE_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.BLUE_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.BROWN_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.GREEN_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.RED_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.BLACK_CANDLE_HONEY_TREAT.get()
                );

        tag(BlockTags.FENCES)
                .add(ModBlocks.FENCE_ASPER.get())
                .add(ModBlocks.FENCE_BAMBOO.get())
                .add(ModBlocks.FENCE_BAOBAB.get())
                .add(ModBlocks.FENCE_BALSAM.get())
                .add(ModBlocks.FENCE_CYPRESS.get())
                .add(ModBlocks.FENCE_EUCALYPTUS.get())
                .add(ModBlocks.FENCE_MAHOGANY.get())
                .add(ModBlocks.FENCE_MARSHWOOD.get())
                .add(ModBlocks.FENCE_MOSSBARK.get())
                .add(ModBlocks.FENCE_ROTTEN.get())
                .add(ModBlocks.FENCE_SCORCHED.get())
                .add(ModBlocks.FENCE_VARNISHED.get())
                .add(ModBlocks.FENCE_WHITE.get());

        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.FENCE_ASPER.get())
                .add(ModBlocks.FENCE_BAMBOO.get())
                .add(ModBlocks.FENCE_BAOBAB.get())
                .add(ModBlocks.FENCE_BALSAM.get())
                .add(ModBlocks.FENCE_CYPRESS.get())
                .add(ModBlocks.FENCE_EUCALYPTUS.get())
                .add(ModBlocks.FENCE_MAHOGANY.get())
                .add(ModBlocks.FENCE_MARSHWOOD.get())
                .add(ModBlocks.FENCE_MOSSBARK.get())
                .add(ModBlocks.FENCE_ROTTEN.get())
                .add(ModBlocks.FENCE_SCORCHED.get())
                .add(ModBlocks.FENCE_VARNISHED.get())
                .add(ModBlocks.FENCE_WHITE.get());

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.FENCE_GATE_ASPER.get())
                .add(ModBlocks.FENCE_GATE_BAMBOO.get())
                .add(ModBlocks.FENCE_GATE_BAOBAB.get())
                .add(ModBlocks.FENCE_GATE_BALSAM.get())
                .add(ModBlocks.FENCE_GATE_CYPRESS.get())
                .add(ModBlocks.FENCE_GATE_EUCALYPTUS.get())
                .add(ModBlocks.FENCE_GATE_MAHOGANY.get())
                .add(ModBlocks.FENCE_GATE_MARSHWOOD.get())
                .add(ModBlocks.FENCE_GATE_MOSSBARK.get())
                .add(ModBlocks.FENCE_GATE_ROTTEN.get())
                .add(ModBlocks.FENCE_GATE_SCORCHED.get())
                .add(ModBlocks.FENCE_GATE_VARNISHED.get())
                .add(ModBlocks.FENCE_GATE_WHITE.get());

        for (var block : new Block[]{
                ModBlocks.UMBERCOBBLE.get(), ModBlocks.PETRIFIED_WOOD_ROCK.get(), ModBlocks.PETRIFIED_WOOD_ROCK_2.get(),
                ModBlocks.PETRIFIED_WOOD_ROCK_4.get(), ModBlocks.PETRIFIED_WOOD_ROCK_5.get(), ModBlocks.PETRIFIED_WOOD_ROCK_6.get(),
                ModBlocks.PETRIFIED_LOG_INNER.get(), ModBlocks.PETRIFIED_BARK_BROWN.get(), ModBlocks.PETRIFIED_BARK_RED.get()}) {
            tag(Tags.Blocks.COBBLESTONES_NORMAL).add(block);
        }
        tag(Tags.Blocks.STONES).add(ModBlocks.UMBERSTONE.get());

        tag(BlockTags.LOGS_THAT_BURN)
                .add(
                        ModBlocks.LOG_ASPER.get(),
                        ModBlocks.LOG_BALSAM.get(),
                        ModBlocks.LOG_BAOBAB.get(),
                        ModBlocks.LOG_CYPRESS.get(),
                        ModBlocks.LOG_EUCALYPTUS.get(),
                        ModBlocks.LOG_BALSAM_RESINLESS.get(),
                        ModBlocks.COLOSSAL_BAMBOO.get(),
                        ModBlocks.LOG_HOLLOW.get(),
                        ModBlocks.LOG_MAHOGANY.get(),
                        ModBlocks.LOG_MARSHWOOD.get(),
                        ModBlocks.LOG_MOSSBARK.get(),
                        ModBlocks.LOG_ROTTEN.get(),
                        ModBlocks.LOG_SCORCHED.get()
                );

        tag(BlockTags.LEAVES)
                .add(
                        ModBlocks.LEAVES_ASPER.get(),
                        ModBlocks.LEAVES_BALSAM.get(),
                        ModBlocks.LEAVES_BAOBAB.get(),
                        ModBlocks.LEAVES_CYPRESS.get(),
                        ModBlocks.LEAVES_EUCALYPTUS.get(),
                        ModBlocks.LEAVES_MAHOGANY.get(),
                        ModBlocks.LEAVES_MARSHWOOD.get(),
                        ModBlocks.LEAVES_MOSSBARK.get()
                );

        tag(ModBlockTags.UMBERSTONE_ORE_REPLACEABLES)
                .add(ModBlocks.UMBERSTONE.get());

        tag(ModBlockTags.EREBUS_CARVER_REPLACEABLES)
                .add(ModBlocks.UMBERSTONE.get());

        // Stigma Blocks for Bees
        tag(ModBlockTags.BEE_POLLINATION_BLOCKS)
                .add(
                        ModBlocks.STIGMA_BLACK.get(),
                        ModBlocks.STIGMA_BLUE.get(),
                        ModBlocks.STIGMA_BROWN.get(),
                        ModBlocks.STIGMA_CYAN.get(),
                        ModBlocks.STIGMA_GRAY.get(),
                        ModBlocks.STIGMA_LIGHT_BLUE.get(),
                        ModBlocks.STIGMA_LIGHT_GRAY.get(),
                        ModBlocks.STIGMA_MAGENTA.get(),
                        ModBlocks.STIGMA_ORANGE.get(),
                        ModBlocks.STIGMA_PINK.get(),
                        ModBlocks.STIGMA_PURPLE.get(),
                        ModBlocks.STIGMA_RED.get(),
                        ModBlocks.STIGMA_WHITE.get(),
                        ModBlocks.STIGMA_YELLOW.get(),
                        ModBlocks.EXPLODING_STIGMA.get()
                );

        tag(BlockTags.CLIMBABLE)
                .add(ModBlocks.BAMBOO_LADDER.get())
                .add(ModBlocks.BAMBOO_NERD_POLE.get());

    }

    private void addWoodMaterialTags() {
        ModBlockFamilies.getWoodFamilies().forEach(family -> {
            tag(BlockTags.MINEABLE_WITH_AXE).add(family.getBaseBlock());
            family.getVariants().forEach((variant, block) -> {
                var materialTag = switch (variant) {
                    case SLAB -> BlockTags.WOODEN_SLABS;
                    case STAIRS -> BlockTags.WOODEN_STAIRS;
                    case DOOR -> BlockTags.WOODEN_DOORS;
                    case FENCE -> BlockTags.WOODEN_FENCES;
                    case FENCE_GATE -> BlockTags.FENCE_GATES;
                    default -> null;
                };
                if (materialTag != null) {
                    tag(materialTag).add(block);
                    tag(BlockTags.MINEABLE_WITH_AXE).add(block);
                }
            });
        });
        tag(BlockTags.SAPLINGS).add(
                ModBlocks.SAPLING_ASPER.get(), ModBlocks.SAPLING_BAOBAB.get(), ModBlocks.SAPLING_BAMBOO.get(),
                ModBlocks.SAPLING_BALSAM.get(), ModBlocks.SAPLING_CYPRESS.get(), ModBlocks.SAPLING_EUCALYPTUS.get(),
                ModBlocks.SAPLING_MAHOGANY.get(), ModBlocks.SAPLING_MARSHWOOD.get(), ModBlocks.SAPLING_MOSSBARK.get());
    }

    private void addStoneBuildingTags() {
        var wooden = ModBlockFamilies.getWoodFamilies().toList();
        ModBlockFamilies.getAllFamilies().filter(family -> !wooden.contains(family)).forEach(family ->
                family.getVariants().forEach((variant, block) -> {
                    var shapeTag = switch (variant) {
                        case SLAB -> BlockTags.SLABS;
                        case STAIRS -> BlockTags.STAIRS;
                        case WALL -> BlockTags.WALLS;
                        default -> null;
                    };
                    if (shapeTag != null) {
                        tag(shapeTag).add(block);
                        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
                    }
                }));
    }
}
