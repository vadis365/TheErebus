package erebus.datagen.loot;

import erebus.block.plants.DarkFruitVineBlock;
import erebus.block.plants.TallFernBlock;
import erebus.datagen.providers.ModBlockLootTableProvider;
import erebus.loot.BalsamResinCount;
import erebus.loot.RedGemDropCount;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.ModDataComponents;
import erebus.registries.item.ModItems;
import net.minecraft.advancements.criterion.BlockPredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.LocationPredicate;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

public class ModBlockLootTables extends ModBlockLootTableProvider {

    public ModBlockLootTables(HolderLookup.Provider provider) {
        super(provider);
    }

    private void darkFruitVine() {
        var vine = ModBlocks.DARK_FRUIT_VINE.get();
        var player = LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(registries.lookupOrThrow(Registries.ENTITY_TYPE), EntityType.PLAYER));
        var table = LootTable.lootTable();
        for (int age : new int[]{5, 6}) {
            table.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                    .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(vine)
                            .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DarkFruitVineBlock.AGE, age)))
                    .add(LootItem.lootTableItem(age == 5 ? ModItems.DARK_FRUIT.get() : ModItems.DARK_FRUIT_SEEDS.get())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2)).when(player))));
        }
        add(vine, table);
    }

    private LootTable.Builder tallFernDrops() {
        var block = ModBlocks.TALL_FERN.get();
        var entry = LootItem.lootTableItem(block).when(hasShears()).otherwise(DynamicLoot.dynamicEntry(TallFernBlock.SEEDS));
        return pairedPlantDrops(block, entry);
    }

    private LootTable.Builder pairedPlantDrops(Block block, LootPoolEntryContainer.Builder<?> entry) {
        var blocks = registries.lookupOrThrow(Registries.BLOCK);
        var table = LootTable.lootTable();
        for (var half : DoubleBlockHalf.values()) {
            boolean lower = half == DoubleBlockHalf.LOWER;
            var other = lower ? DoubleBlockHalf.UPPER : DoubleBlockHalf.LOWER;
            table.withPool(LootPool.lootPool().add(entry)
                    .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                            StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, half)))
                    .when(LocationCheck.checkLocation(
                            LocationPredicate.Builder.location().setBlock(
                                    BlockPredicate.Builder.block().of(blocks, block)
                                            .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, other))),
                            new BlockPos(0, lower ? 1 : -1, 0))));
        }
        return table;
    }

    @Override
    protected void generate() {
        // MARK: Umberstone
        add(ModBlocks.UMBERSTONE.get(), block -> createSingleItemTableWithSilkTouch(block, ModBlocks.UMBERCOBBLE.get()));
        dropSelf(ModBlocks.UMBERSTONE_BRICKS);
        dropSelf(ModBlocks.UMBERCOBBLE);
        dropSelf(ModBlocks.UMBERCOBBLE_MOSSY);
        dropSelf(ModBlocks.UMBERCOBBLE_WEBBED);
        dropSelf(ModBlocks.UMBERTILE_SMOOTH);
        dropSelf(ModBlocks.UMBERTILE_SMOOTH_SMALL);
        add(ModBlocks.UMBERGRAVEL.get(), block -> createSilkTouchDispatchTable(block,
                applyExplosionCondition(block, LootItem.lootTableItem(Items.FLINT)
                        .when(BonusLevelTableCondition.bonusLevelFlatChance(registries.lookupOrThrow(Registries.ENCHANTMENT)
                                .getOrThrow(Enchantments.FORTUNE), 0.1F, 1F / 7, 0.25F, 1F))
                        .otherwise(LootItem.lootTableItem(block)))));
        dropSelf(ModBlocks.UMBERPAVER);
        dropSelf(ModBlocks.UMBERPAVER_MOSSY);
        dropSelf(ModBlocks.UMBERPAVER_WEBBED);
        dropSelf(ModBlocks.UMBERSTONE_PILLAR);
        dropSelf(ModBlocks.VOLCANIC_ROCK);
        dropSelf(ModBlocks.DUST);
        add(ModBlocks.DUST_LAYER.get(), LootTable.lootTable());
        dropSelf(ModBlocks.PETRIFIED_WOOD_ROCK);
        dropSelf(ModBlocks.PETRIFIED_WOOD_ROCK_2);
        dropSelf(ModBlocks.PETRIFIED_WOOD_ROCK_3);
        dropSelf(ModBlocks.PETRIFIED_WOOD_ROCK_4);
        dropSelf(ModBlocks.PETRIFIED_WOOD_ROCK_5);
        dropSelf(ModBlocks.PETRIFIED_WOOD_ROCK_6);
        dropSelf(ModBlocks.PETRIFIED_BARK_RED);
        dropSelf(ModBlocks.PETRIFIED_BARK_BROWN);
        dropSelf(ModBlocks.PETRIFIED_LOG_INNER);
        dropSelf(ModBlocks.DUNG);

        // MARK: Amber
        dropSelf(ModBlocks.AMBER);
        dropSelf(ModBlocks.AMBER_GLASS);
        dropSelf(ModBlocks.AMBER_BRICKS);
        dropWhenSilkTouch(ModBlocks.PRESERVED_AMBER.get());
        dropWhenSilkTouch(ModBlocks.PRESERVED_AMBER_GLASS.get());
        dropSelf(ModBlocks.GLOWING_JAR);
        add(ModBlocks.AMBER_DOOR.get(), this::createDoorTable);

        dropSelf(ModBlocks.MIR_BRICKS);
        dropSelf(ModBlocks.MUD_BRICKS);

        // MARK: Ores
        ore(ModBlocks.ORE_IRON, Items.RAW_IRON);
        ore(ModBlocks.ORE_GOLD, Items.RAW_GOLD);
        ore(ModBlocks.ORE_COAL, Items.COAL);
        ore(ModBlocks.ORE_DIAMOND, Items.DIAMOND);
        ore(ModBlocks.ORE_EMERALD, Items.EMERALD);
        add(ModBlocks.ORE_LAPIS.get(), block -> createSilkTouchDispatchTable(block,
                applyExplosionDecay(block, LootItem.lootTableItem(Items.LAPIS_LAZULI)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 8)))
                        .apply(ApplyBonusCount.addOreBonusCount(registries.lookupOrThrow(Registries.ENCHANTMENT)
                                .getOrThrow(Enchantments.FORTUNE))))));
        ore(ModBlocks.ORE_QUARTZ, Items.QUARTZ);
        ore(ModBlocks.ORE_PETRIFIED_QUARTZ, Items.QUARTZ);
        ore(ModBlocks.ORE_COPPER, Items.RAW_COPPER);
        dropSelf(ModBlocks.ORE_SILVER);
        dropSelf(ModBlocks.ORE_TIN);
        dropSelf(ModBlocks.ORE_LEAD);
        dropSelf(ModBlocks.ORE_ALUMINUM);
        ore(ModBlocks.ORE_JADE, ModItems.JADE);
        ore(ModBlocks.ORE_ENCRUSTED_DIAMOND, Items.DIAMOND);
        ore(ModBlocks.ORE_FOSSIL, ModItems.SHARD_BONE);
        ore(ModBlocks.ORE_GNEISS, ModItems.GNEISS_ROCK);
        ore(ModBlocks.ORE_PETRIFIED_WOOD, ModItems.PETRIFIED_WOOD);
        ore(ModBlocks.ORE_TEMPLE, ModItems.TEMPLE_ROCK);

        // MARK: Logs
        dropSelf(ModBlocks.LOG_BAOBAB);
        dropSelf(ModBlocks.LOG_EUCALYPTUS);
        dropSelf(ModBlocks.LOG_MAHOGANY);
        dropSelf(ModBlocks.LOG_MOSSBARK);
        dropSelf(ModBlocks.LOG_ASPER);
        dropSelf(ModBlocks.LOG_CYPRESS);
        add(ModBlocks.LOG_BALSAM.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModBlocks.LOG_BALSAM).when(hasSilkTouch())
                                .otherwise(LootItem.lootTableItem(ModBlocks.LOG_BALSAM_RESINLESS))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(hasSilkTouch().invert())
                        .add(LootItem.lootTableItem(ModItems.RESIN)
                                .apply(() -> BalsamResinCount.INSTANCE))));
        dropSelf(ModBlocks.LOG_BALSAM_RESINLESS);
        dropSelf(ModBlocks.LOG_ROTTEN);
        dropSelf(ModBlocks.LOG_MARSHWOOD);
        dropSelf(ModBlocks.LOG_SCORCHED);
        dropSelf(ModBlocks.LOG_HOLLOW);

        // MARK: Saplings
        dropSelf(ModBlocks.SAPLING_BAOBAB);
        dropSelf(ModBlocks.SAPLING_EUCALYPTUS);
        dropSelf(ModBlocks.SAPLING_MAHOGANY);
        dropSelf(ModBlocks.SAPLING_MOSSBARK);
        dropSelf(ModBlocks.SAPLING_ASPER);
        dropSelf(ModBlocks.SAPLING_CYPRESS);
        dropSelf(ModBlocks.SAPLING_BALSAM);
        dropSelf(ModBlocks.SAPLING_MARSHWOOD);
        dropSelf(ModBlocks.SAPLING_BAMBOO);

        // MARK: Leaves
        dropSelf(ModBlocks.LEAVES_BAOBAB);
        dropSelf(ModBlocks.LEAVES_EUCALYPTUS);
        dropSelf(ModBlocks.LEAVES_MAHOGANY);
        dropSelf(ModBlocks.LEAVES_MOSSBARK);
        dropSelf(ModBlocks.LEAVES_ASPER);
        dropSelf(ModBlocks.LEAVES_CYPRESS);
        dropSelf(ModBlocks.LEAVES_BALSAM);
        dropSelf(ModBlocks.LEAVES_MARSHWOOD);

        // MARK: Planks
        dropSelf(ModBlocks.PLANKS_BAOBAB);
        dropSelf(ModBlocks.PLANKS_EUCALYPTUS);
        dropSelf(ModBlocks.PLANKS_MAHOGANY);
        dropSelf(ModBlocks.PLANKS_MOSSBARK);
        dropSelf(ModBlocks.PLANKS_ASPER);
        dropSelf(ModBlocks.PLANKS_CYPRESS);
        dropSelf(ModBlocks.PLANKS_BALSAM);
        dropSelf(ModBlocks.PLANKS_WHITE);
        dropSelf(ModBlocks.PLANKS_BAMBOO);
        dropSelf(ModBlocks.PLANKS_ROTTEN);
        dropSelf(ModBlocks.PLANKS_MARSHWOOD);
        dropSelf(ModBlocks.PLANKS_SCORCHED);
        dropSelf(ModBlocks.PLANKS_VARNISHED);
        dropSelf(ModBlocks.PLANKS_PETRIFIED);

        // MARK: Slabs Wood
        add(ModBlocks.SLAB_PLANKS_BAOBAB.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_PLANKS_EUCALYPTUS.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_PLANKS_MAHOGANY.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_PLANKS_MOSSBARK.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_PLANKS_ASPER.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_PLANKS_CYPRESS.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_PLANKS_BALSAM.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_PLANKS_WHITE.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_PLANKS_BAMBOO.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_PLANKS_ROTTEN.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_PLANKS_MARSHWOOD.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_PLANKS_SCORCHED.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_PLANKS_VARNISHED.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_PLANKS_PETRIFIED.get(), this::createSlabItemTable);

        // MARK: Slabs Stone
        add(ModBlocks.SLAB_UMBERSTONE.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_UMBERCOBBLE.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_UMBERCOBBLE_MOSSY.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_UMBERCOBBLE_WEBBED.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_UMBERSTONE_BRICKS.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_UMBERTILE_SMOOTH.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_UMBERTILE_SMOOTH_SMALL.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_UMBERPAVER.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_AMBER.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_AMBER_BRICKS.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_UMBERPAVER_MOSSY.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_UMBERPAVER_WEBBED.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_MIR_BRICKS.get(), this::createSlabItemTable);
        add(ModBlocks.SLAB_MUD_BRICKS.get(), this::createSlabItemTable);

        // MARK: Stairs Wood
        dropSelf(ModBlocks.STAIRS_BAOBAB);
        dropSelf(ModBlocks.STAIRS_EUCALYPTUS);
        dropSelf(ModBlocks.STAIRS_MAHOGANY);
        dropSelf(ModBlocks.STAIRS_MOSSBARK);
        dropSelf(ModBlocks.STAIRS_ASPER);
        dropSelf(ModBlocks.STAIRS_CYPRESS);
        dropSelf(ModBlocks.STAIRS_BALSAM);
        dropSelf(ModBlocks.STAIRS_WHITE);
        dropSelf(ModBlocks.STAIRS_BAMBOO);
        dropSelf(ModBlocks.STAIRS_ROTTEN);
        dropSelf(ModBlocks.STAIRS_MARSHWOOD);
        dropSelf(ModBlocks.STAIRS_SCORCHED);
        dropSelf(ModBlocks.STAIRS_VARNISHED);
        dropSelf(ModBlocks.STAIRS_PETRIFIED);

        // MARK: Stairs Stone
        dropSelf(ModBlocks.STAIRS_UMBERSTONE);
        dropSelf(ModBlocks.STAIRS_UMBERCOBBLE);
        dropSelf(ModBlocks.STAIRS_UMBERCOBBLE_MOSSY);
        dropSelf(ModBlocks.STAIRS_UMBERCOBBLE_WEBBED);
        dropSelf(ModBlocks.STAIRS_UMBERSTONE_BRICKS);
        dropSelf(ModBlocks.STAIRS_UMBERTILE_SMOOTH);
        dropSelf(ModBlocks.STAIRS_UMBERTILE_SMOOTH_SMALL);
        dropSelf(ModBlocks.STAIRS_UMBERPAVER);
        dropSelf(ModBlocks.STAIRS_UMBERPAVER_MOSSY);
        dropSelf(ModBlocks.STAIRS_UMBERPAVER_WEBBED);
        dropSelf(ModBlocks.STAIRS_AMBER);
        dropSelf(ModBlocks.STAIRS_AMBER_BRICKS);
        dropSelf(ModBlocks.STAIRS_MUD_BRICKS);
        dropSelf(ModBlocks.STAIRS_MIR_BRICKS);
        dropSelf(ModBlocks.STAIRS_WASP_NEST);

        // MARK: Doors
        add(ModBlocks.DOOR_BAOBAB.get(), this::createDoorTable);
        add(ModBlocks.DOOR_EUCALYPTUS.get(), this::createDoorTable);
        add(ModBlocks.DOOR_MAHOGANY.get(), this::createDoorTable);
        add(ModBlocks.DOOR_MOSSBARK.get(), this::createDoorTable);
        add(ModBlocks.DOOR_ASPER.get(), this::createDoorTable);
        add(ModBlocks.DOOR_CYPRESS.get(), this::createDoorTable);
        add(ModBlocks.DOOR_BALSAM.get(), this::createDoorTable);
        add(ModBlocks.DOOR_WHITE.get(), this::createDoorTable);
        add(ModBlocks.DOOR_PETRIFIED.get(), this::createDoorTable);
        add(ModBlocks.DOOR_ROTTEN.get(), this::createDoorTable);
        add(ModBlocks.DOOR_MARSHWOOD.get(), this::createDoorTable);
        add(ModBlocks.DOOR_SCORCHED.get(), this::createDoorTable);

        // MARK: Fences
        dropSelf(ModBlocks.FENCE_BAOBAB);
        dropSelf(ModBlocks.FENCE_EUCALYPTUS);
        dropSelf(ModBlocks.FENCE_MAHOGANY);
        dropSelf(ModBlocks.FENCE_MOSSBARK);
        dropSelf(ModBlocks.FENCE_ASPER);
        dropSelf(ModBlocks.FENCE_CYPRESS);
        dropSelf(ModBlocks.FENCE_BALSAM);
        dropSelf(ModBlocks.FENCE_WHITE);
        dropSelf(ModBlocks.FENCE_BAMBOO);
        dropSelf(ModBlocks.FENCE_ROTTEN);
        dropSelf(ModBlocks.FENCE_MARSHWOOD);
        dropSelf(ModBlocks.FENCE_SCORCHED);
        dropSelf(ModBlocks.FENCE_VARNISHED);

        // MARK: Fence Gates
        dropSelf(ModBlocks.FENCE_GATE_BAOBAB);
        dropSelf(ModBlocks.FENCE_GATE_EUCALYPTUS);
        dropSelf(ModBlocks.FENCE_GATE_MAHOGANY);
        dropSelf(ModBlocks.FENCE_GATE_MOSSBARK);
        dropSelf(ModBlocks.FENCE_GATE_ASPER);
        dropSelf(ModBlocks.FENCE_GATE_CYPRESS);
        dropSelf(ModBlocks.FENCE_GATE_BALSAM);
        dropSelf(ModBlocks.FENCE_GATE_WHITE);
        dropSelf(ModBlocks.FENCE_GATE_BAMBOO);
        dropSelf(ModBlocks.FENCE_GATE_ROTTEN);
        dropSelf(ModBlocks.FENCE_GATE_MARSHWOOD);
        dropSelf(ModBlocks.FENCE_GATE_SCORCHED);
        dropSelf(ModBlocks.FENCE_GATE_VARNISHED);

        // MARK: Walls
        dropSelf(ModBlocks.WALL_UMBERSTONE);
        dropSelf(ModBlocks.WALL_UMBERCOBBLE);
        dropSelf(ModBlocks.WALL_UMBERCOBBLE_MOSSY);
        dropSelf(ModBlocks.WALL_UMBERCOBBLE_WEBBED);
        dropSelf(ModBlocks.WALL_UMBERSTONE_BRICKS);
        dropSelf(ModBlocks.WALL_UMBERTILE_SMOOTH);
        dropSelf(ModBlocks.WALL_UMBERTILE_SMOOTH_SMALL);
        dropSelf(ModBlocks.WALL_UMBERPAVER);
        dropSelf(ModBlocks.WALL_UMBERPAVER_MOSSY);
        dropSelf(ModBlocks.WALL_UMBERPAVER_WEBBED);
        dropSelf(ModBlocks.WALL_AMBER);
        dropSelf(ModBlocks.WALL_AMBER_BRICKS);

        // MARK: Plants
        dropCropBasedOffCondition(ModBlocks.CROP_TURNIP, ModItems.TURNIP, ModItems.TURNIP);
        dropCropBasedOffCondition(ModBlocks.CROP_CABBAGE, ModItems.CABBAGE, ModItems.CABBAGE_SEEDS);
        dropCropBasedOffCondition(ModBlocks.CROP_MANDRAKE, ModItems.MANDRAKE_ROOT, ModItems.MANDRAKE_ROOT);
        dropSelf(ModBlocks.JADE_BERRY_BUSH);
        dropSelf(ModBlocks.HEART_BERRY_BUSH);
        dropSelf(ModBlocks.SWAMP_BERRY_BUSH);
        darkFruitVine();
        dropPricklyPearBasedOffCondition(ModBlocks.PRICKLY_PEAR);
        dropColossalBambooBasedOffCondition(ModBlocks.COLOSSAL_BAMBOO);
        dropSelf(ModBlocks.DARK_CAPPED_MUSHROOM);
        dropSelf(ModBlocks.DUTCH_CAP_MUSHROOM);
        dropSelf(ModBlocks.GRANDMAS_SHOES_MUSHROOM);
        dropSelf(ModBlocks.KAIZERS_FINGERS_MUSHROOM);
        dropSelf(ModBlocks.SARCASTIC_CZECH_MUSHROOM);
        hugeMushroom(ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK.get(), ModBlocks.DARK_CAPPED_MUSHROOM.get());
        hugeMushroom(ModBlocks.DARK_CAPPED_MUSHROOM_STEM.get(), ModBlocks.DARK_CAPPED_MUSHROOM.get());
        hugeMushroom(ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK.get(), ModBlocks.DUTCH_CAP_MUSHROOM.get());
        hugeMushroom(ModBlocks.DUTCH_CAP_MUSHROOM_STEM.get(), ModBlocks.DUTCH_CAP_MUSHROOM.get());
        hugeMushroom(ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK.get(), ModBlocks.GRANDMAS_SHOES_MUSHROOM.get());
        hugeMushroom(ModBlocks.GRANDMAS_SHOES_MUSHROOM_STEM.get(), ModBlocks.GRANDMAS_SHOES_MUSHROOM.get());
        hugeMushroom(ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK.get(), ModBlocks.KAIZERS_FINGERS_MUSHROOM.get());
        hugeMushroom(ModBlocks.KAIZERS_FINGERS_MUSHROOM_STEM.get(), ModBlocks.KAIZERS_FINGERS_MUSHROOM.get());
        hugeMushroom(ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK.get(), ModBlocks.SARCASTIC_CZECH_MUSHROOM.get());
        hugeMushroom(ModBlocks.SARCASTIC_CZECH_MUSHROOM_STEM.get(), ModBlocks.SARCASTIC_CZECH_MUSHROOM.get());
        dropSelf(ModBlocks.DESERT_SHRUB);
        add(ModBlocks.MIRE_CORAL.get(), createShearsOnlyDrop(ModBlocks.MIRE_CORAL));
        add(ModBlocks.NETTLE.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModBlocks.NETTLE).when(hasShears())
                        .otherwise(LootItem.lootTableItem(ModItems.NETTLE_LEAVES)))));
        add(ModBlocks.NETTLE_FLOWERED.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModBlocks.NETTLE_FLOWERED).when(hasShears())
                        .otherwise(LootItem.lootTableItem(ModItems.NETTLE_FLOWERS)))));
        add(ModBlocks.SWAMP_PLANT.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModBlocks.SWAMP_PLANT).when(hasShears())
                        .otherwise(LootItem.lootTableItem(ModItems.CABBAGE_SEEDS)))));
        dropSelf(ModBlocks.FIRE_BLOOM);
        add(ModBlocks.FERN.get(), createShearsOnlyDrop(ModBlocks.FERN));
        add(ModBlocks.FIDDLE_HEAD.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModBlocks.FIDDLE_HEAD).when(hasShears())
                        .otherwise(LootItem.lootTableItem(Items.MELON_SEEDS)))));
        add(ModBlocks.THORNS.get(), createShearsOnlyDrop(ModBlocks.THORNS));
        add(ModBlocks.MOSS.get(), createShearsOnlyDrop(ModBlocks.MOSS));
        add(ModBlocks.MOULD.get(), createShearsOnlyDrop(ModBlocks.MOULD));
        dropSelf(ModBlocks.MOSS_CULTIVATED);
        dropSelf(ModBlocks.MOULD_CULTIVATED);
        add(ModBlocks.ALGAE.get(), LootTable.lootTable());
        dropOther(ModBlocks.GLOWSHROOM_BLOCK, ModItems.GLOWSHROOM.get());
        add(ModBlocks.GLOWSHROOM_STALK.get(), LootTable.lootTable());
        add(ModBlocks.HANGING_WEB.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.STRING).when(hasShears()))));
        dropSelf(ModBlocks.GIANT_LILY_PAD);

        // MARK: Flowers
        dropSelf(ModBlocks.PETAL_BLACK);
        dropSelf(ModBlocks.PETAL_RED);
        dropSelf(ModBlocks.PETAL_BROWN);
        dropSelf(ModBlocks.PETAL_BLUE);
        dropSelf(ModBlocks.PETAL_PURPLE);
        dropSelf(ModBlocks.PETAL_CYAN);
        dropSelf(ModBlocks.PETAL_LIGHT_GRAY);
        dropSelf(ModBlocks.PETAL_GRAY);
        dropSelf(ModBlocks.PETAL_PINK);
        dropSelf(ModBlocks.PETAL_YELLOW);
        dropSelf(ModBlocks.PETAL_LIGHT_BLUE);
        dropSelf(ModBlocks.PETAL_MAGENTA);
        dropSelf(ModBlocks.PETAL_ORANGE);
        dropSelf(ModBlocks.PETAL_WHITE);
        dropSelf(ModBlocks.PETAL_RAINBOW);
        dropSelf(ModBlocks.PETAL_RAINBOW_CHASE);

        dropOther(ModBlocks.EXPLODING_STIGMA, Items.GUNPOWDER);
        dropSelf(ModBlocks.STEM);
        add(ModBlocks.STIGMA_BLACK.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ModItems.SEED_BLACK.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.STIGMA_RED.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ModItems.SEED_RED.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.STIGMA_BROWN.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ModItems.SEED_BROWN.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.STIGMA_BLUE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ModItems.SEED_BLUE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.STIGMA_PURPLE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ModItems.SEED_PURPLE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.STIGMA_CYAN.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ModItems.SEED_CYAN.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.STIGMA_LIGHT_GRAY.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ModItems.SEED_LIGHT_GRAY.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.STIGMA_GRAY.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ModItems.SEED_GRAY.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.STIGMA_PINK.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ModItems.SEED_PINK.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.STIGMA_YELLOW.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ModItems.SEED_YELLOW.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.STIGMA_LIGHT_BLUE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ModItems.SEED_LIGHT_BLUE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.STIGMA_MAGENTA.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ModItems.SEED_MAGENTA.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.STIGMA_ORANGE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ModItems.SEED_ORANGE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.STIGMA_WHITE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ModItems.SEED_WHITE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));

        dropOther(ModBlocks.FLOWER_BLACK, ModItems.SEED_BLACK.get());
        dropOther(ModBlocks.FLOWER_RED, ModItems.SEED_RED.get());
        dropOther(ModBlocks.FLOWER_BROWN, ModItems.SEED_BROWN.get());
        dropOther(ModBlocks.FLOWER_BLUE, ModItems.SEED_BLUE.get());
        dropOther(ModBlocks.FLOWER_PURPLE, ModItems.SEED_PURPLE.get());
        dropOther(ModBlocks.FLOWER_CYAN, ModItems.SEED_CYAN.get());
        dropOther(ModBlocks.FLOWER_LIGHT_GRAY, ModItems.SEED_LIGHT_GRAY.get());
        dropOther(ModBlocks.FLOWER_GRAY, ModItems.SEED_GRAY.get());
        dropOther(ModBlocks.FLOWER_PINK, ModItems.SEED_PINK.get());
        dropOther(ModBlocks.FLOWER_YELLOW, ModItems.SEED_YELLOW.get());
        dropOther(ModBlocks.FLOWER_LIGHT_BLUE, ModItems.SEED_LIGHT_BLUE.get());
        dropOther(ModBlocks.FLOWER_MAGENTA, ModItems.SEED_MAGENTA.get());
        dropOther(ModBlocks.FLOWER_ORANGE, ModItems.SEED_ORANGE.get());
        dropOther(ModBlocks.FLOWER_WHITE, ModItems.SEED_WHITE.get());
        dropOther(ModBlocks.FLOWER_RAINBOW, ModItems.SEED_RAINBOW.get());

        // MARK: Flowers Double Height
        dropSelf(ModBlocks.BULLRUSH);
        add(ModBlocks.WEEPING_BLUEBELL.get(), pairedPlantDrops(ModBlocks.WEEPING_BLUEBELL.get(),
                LootItem.lootTableItem(ModBlocks.WEEPING_BLUEBELL).when(hasShears())
                        .otherwise(LootItem.lootTableItem(ModItems.BLUEBELL_PETAL))));
        dropSelf(ModBlocks.SUNDEW);
        add(ModBlocks.DROUGHTED_SHRUB.get(), pairedPlantDrops(ModBlocks.DROUGHTED_SHRUB.get(), LootItem.lootTableItem(ModBlocks.DROUGHTED_SHRUB)));
        add(ModBlocks.TALL_BLOOM.get(), pairedPlantDrops(ModBlocks.TALL_BLOOM.get(), LootItem.lootTableItem(ModBlocks.TALL_BLOOM)));
        dropSelf(ModBlocks.TANGLED_STALK);
        dropSelf(ModBlocks.HIGH_CAPPED_MUSHROOM);
        add(ModBlocks.TALL_FERN.get(), tallFernDrops());

        dropSelf(ModBlocks.PORTAL);
        dropSelf(ModBlocks.JADE_BLOCK);
        dropSelf(ModBlocks.MUD);
        dropSelf(ModBlocks.QUICK_SAND);
        add(ModBlocks.GHOST_SAND.get(), createSingleItemTableWithSilkTouch(ModBlocks.GHOST_SAND.get(), Blocks.SAND));
        dropSelf(ModBlocks.SWAMP_VENT);
        add(ModBlocks.GNEISS_VENT.get(), LootTable.lootTable());
        add(ModBlocks.RED_GEM_BLOCK.get(), createSilkTouchDispatchTable(ModBlocks.RED_GEM_BLOCK.get(),
                applyExplosionDecay(ModBlocks.RED_GEM_BLOCK, LootItem.lootTableItem(ModItems.RED_GEM)
                        .apply(() -> RedGemDropCount.INSTANCE))));
        dropSelf(ModBlocks.RED_GEM_LAMP);
        dropSelf(ModBlocks.GNEISS);
        dropSelf(ModBlocks.GNEISS_CARVED);
        dropSelf(ModBlocks.GNEISS_RELIEF);
        dropSelf(ModBlocks.GNEISS_BRICKS);
        dropSelf(ModBlocks.GNEISS_SMOOTH);
        dropSelf(ModBlocks.GNEISS_TILES);
        dropSelf(ModBlocks.GNEISS_TILES_CRACKED);
        dropSelf(ModBlocks.TEMPLE_BRICK);
        dropSelf(ModBlocks.TEMPLE_PILLAR);
        dropSelf(ModBlocks.TEMPLE_TILE);
        dropSelf(ModBlocks.SILK);
        dropSelf(ModBlocks.REIN_EXO);
        dropSelf(ModBlocks.VELOCITY_BLOCK);
        dropSelf(ModBlocks.VELOCITY_BLOCK_LIGHTNING_SPEED);
        add(ModBlocks.BLOCK_OF_BONES.get(), LootTable.lootTable());
        dropSelf(ModBlocks.ANTLION_EGG);
        dropSelf(ModBlocks.TARANTULA_EGG);
        add(ModBlocks.HONEY_TREAT.get(), LootTable.lootTable());
        dropOther(ModBlocks.CANDLE_HONEY_TREAT.get(), Blocks.CANDLE);
        dropOther(ModBlocks.WHITE_CANDLE_HONEY_TREAT.get(), Blocks.WHITE_CANDLE);
        dropOther(ModBlocks.ORANGE_CANDLE_HONEY_TREAT.get(), Blocks.ORANGE_CANDLE);
        dropOther(ModBlocks.MAGENTA_CANDLE_HONEY_TREAT.get(), Blocks.MAGENTA_CANDLE);
        dropOther(ModBlocks.LIGHT_BLUE_CANDLE_HONEY_TREAT.get(), Blocks.LIGHT_BLUE_CANDLE);
        dropOther(ModBlocks.YELLOW_CANDLE_HONEY_TREAT.get(), Blocks.YELLOW_CANDLE);
        dropOther(ModBlocks.LIME_CANDLE_HONEY_TREAT.get(), Blocks.LIME_CANDLE);
        dropOther(ModBlocks.PINK_CANDLE_HONEY_TREAT.get(), Blocks.PINK_CANDLE);
        dropOther(ModBlocks.GRAY_CANDLE_HONEY_TREAT.get(), Blocks.GRAY_CANDLE);
        dropOther(ModBlocks.LIGHT_GRAY_CANDLE_HONEY_TREAT.get(), Blocks.LIGHT_GRAY_CANDLE);
        dropOther(ModBlocks.CYAN_CANDLE_HONEY_TREAT.get(), Blocks.CYAN_CANDLE);
        dropOther(ModBlocks.PURPLE_CANDLE_HONEY_TREAT.get(), Blocks.PURPLE_CANDLE);
        dropOther(ModBlocks.BLUE_CANDLE_HONEY_TREAT.get(), Blocks.BLUE_CANDLE);
        dropOther(ModBlocks.BROWN_CANDLE_HONEY_TREAT.get(), Blocks.BROWN_CANDLE);
        dropOther(ModBlocks.GREEN_CANDLE_HONEY_TREAT.get(), Blocks.GREEN_CANDLE);
        dropOther(ModBlocks.RED_CANDLE_HONEY_TREAT.get(), Blocks.RED_CANDLE);
        dropOther(ModBlocks.BLACK_CANDLE_HONEY_TREAT.get(), Blocks.BLACK_CANDLE);
        add(ModBlocks.WASP_NEST.get(), LootTable.lootTable());
        add(ModBlocks.INSECT_REPELLENT.get(), LootTable.lootTable());

        // MARK: Spawners
        add(ModBlocks.ANTLION_SPAWNER.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModBlocks.GHOST_SAND.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.DRAGON_FLY_SPAWNER.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.ENDER_PEARL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.JUMPING_SPIDER_SPAWNER.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.STRING).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.SPIDER_SPAWNER.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.STRING).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.TARANTULA_SPAWNER.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.STRING).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        dropOther(ModBlocks.WASP_SPAWNER, ModItems.WASP_SWORD.get());
        add(ModBlocks.ZOMBIE_ANT_SPAWNER.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.ENDER_PEARL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.ZOMBIE_ANT_SOLDIER_SPAWNER.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.ENDER_PEARL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.MAGMA_CRAWLER_SPAWNER.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.MAGMA_CREAM).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
        add(ModBlocks.DUNG_SPAWNER_FLY.get(), LootTable.lootTable());
        add(ModBlocks.DUNG_SPAWNER_BOT_FLY.get(), LootTable.lootTable());
        dropOther(ModBlocks.LOCUST_SPAWNER, ModItems.REIN_EXOSKELETON_SHIELD.get());

        // MARK: Utility Blocks
        dropSelf(ModBlocks.PETRIFIED_CRAFTING_TABLE);
        dropSelf(ModBlocks.BAMBOO_CRATE);
        dropSelf(ModBlocks.BAMBOO_BRIDGE);
        dropSelf(ModBlocks.BAMBOO_LADDER);
        dropSelf(ModBlocks.BAMBOO_NERD_POLE);
        dropSelf(ModBlocks.BAMBOO_EXTENDER);
        dropSingleBambooTorchCondition(ModBlocks.BAMBOO_TORCH);
        dropSelf(ModBlocks.BAMBOO_PIPE);
        dropSelf(ModBlocks.BAMBOO_PIPE_EXTRACT);
        dropSelf(ModBlocks.SILO_ROOF);
        dropSelf(ModBlocks.SILO_TANK);
        dropSelf(ModBlocks.SILO_SUPPORTS);
        dropSelf(ModBlocks.HONEY_COMB);
        dropSelf(ModBlocks.COMPOSTER);
        dropSelf(ModBlocks.BLENDER);
        dropSelf(ModBlocks.UMBER_FURNACE);
        dropSelf(ModBlocks.UMBERSTONE_BUTTON);
        dropSelf(ModBlocks.LIQUIFIER);
        dropSelf(ModBlocks.GLOW_GEM_ACTIVE);
        dropOther(ModBlocks.GLOW_GEM_INACTIVE, ModBlocks.GLOW_GEM_ACTIVE);
        dropSelf(ModBlocks.MUCUS_BOMB.get());
        dropSelf(ModBlocks.UMBER_GOLEM_STATUE);

        // MARK: Chests
        dropSelf(ModBlocks.CHEST_ASPER);
        dropSelf(ModBlocks.CHEST_BALSAM);
        dropSelf(ModBlocks.CHEST_BAOBAB);
        dropSelf(ModBlocks.CHEST_BAMBOO);
        dropSelf(ModBlocks.CHEST_CYPRESS);
        dropSelf(ModBlocks.CHEST_EUCALYPTUS);
        dropSelf(ModBlocks.CHEST_MAHOGANY);
        dropSelf(ModBlocks.CHEST_MARSHWOOD);
        dropSelf(ModBlocks.CHEST_MOSSBARK);
        dropSelf(ModBlocks.CHEST_PETRIFIED);
        dropSelf(ModBlocks.CHEST_ROTTEN);
        dropSelf(ModBlocks.CHEST_SCORCHED);
        dropSelf(ModBlocks.CHEST_VARNISHED);
        dropSelf(ModBlocks.CHEST_WHITE);

        dropSelf(ModBlocks.ALTAR_BASE);
        dropSelf(ModBlocks.ALTAR_LIGHTNING);
        dropSelf(ModBlocks.ALTAR_HEALING);
        dropSelf(ModBlocks.ALTAR_EXPERIENCE);
        dropSelf(ModBlocks.ALTAR_REPAIR);
        dropSelf(ModBlocks.OFFERING_ALTAR);
        dropSelf(ModBlocks.GAEAN_KEYSTONE);

        add(ModBlocks.CAPSTONE.get(), LootTable.lootTable());
        add(ModBlocks.CAPSTONE_MUD.get(), LootTable.lootTable());
        add(ModBlocks.CAPSTONE_IRON.get(), LootTable.lootTable());
        add(ModBlocks.CAPSTONE_GOLD.get(), LootTable.lootTable());
        add(ModBlocks.CAPSTONE_JADE.get(), LootTable.lootTable());
        add(ModBlocks.TEMPLE_BRICK_UNBREAKING.get(), LootTable.lootTable());
        add(ModBlocks.TEMPLE_BRICK_UNBREAKING_JADE.get(), LootTable.lootTable());
        add(ModBlocks.TEMPLE_BRICK_UNBREAKING_EXO.get(), LootTable.lootTable());
        add(ModBlocks.TEMPLE_BRICK_UNBREAKING_CREAM.get(), LootTable.lootTable());
        add(ModBlocks.TEMPLE_BRICK_UNBREAKING_EYE.get(), LootTable.lootTable());
        add(ModBlocks.TEMPLE_BRICK_UNBREAKING_STRING.get(), LootTable.lootTable());
        add(ModBlocks.TEMPLE_TELEPORTER.get(), LootTable.lootTable());
        add(ModBlocks.FORCE_FIELD.get(), LootTable.lootTable());
        add(ModBlocks.FORCE_LOCK.get(), LootTable.lootTable());
        dropSelf(ModBlocks.ANT_HILL_BLOCK);

        //Webs
        add(ModBlocks.WITHER_WEB.get(), createSilkTouchOrShearsDispatchTable(ModBlocks.WITHER_WEB.get(), applyExplosionCondition(ModBlocks.WITHER_WEB, LootItem.lootTableItem(Items.STRING))));
        add(ModBlocks.LAVA_WEB.get(), createSilkTouchOrShearsDispatchTable(ModBlocks.LAVA_WEB.get(), applyExplosionCondition(ModBlocks.WITHER_WEB, LootItem.lootTableItem(Items.STRING))));

        // Fluid Tank Blocks
        CopyComponentsFunction.Builder copyFluid = CopyComponentsFunction.copyComponentsFromBlockEntity(LootContextParams.BLOCK_ENTITY)
                .include(ModDataComponents.FLUID.get()).include(ModDataComponents.FLUID_AMOUNT.get());

        // Portable Inventory Storage Blocks
        CopyComponentsFunction.Builder copyItems = CopyComponentsFunction.copyComponentsFromBlockEntity(LootContextParams.BLOCK_ENTITY)
                .include(DataComponents.CONTAINER);

        dropComponents(ModBlocks.FLUID_JAR, $ -> $.apply(copyFluid));
        dropComponents(ModBlocks.LIQUIFIER, $ -> $.apply(copyFluid));
        dropComponents(ModBlocks.BAMBOO_CRATE, $ -> $.apply(copyItems));
    }

    private void hugeMushroom(Block block, Block mushroom) {
        // 1.12 uses ten equiprobable rolls: eight empty, one single and one double drop.
        add(block, createSilkTouchDispatchTable(block, applyExplosionDecay(block,
                LootItem.lootTableItem(mushroom)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(-7, 2)))
                        .apply(LimitCount.limitCount(IntRange.lowerBound(0))))));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(e -> (Block) e.value()).toList();
    }
}
