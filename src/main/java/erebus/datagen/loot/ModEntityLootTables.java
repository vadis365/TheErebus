package erebus.datagen.loot;

import erebus.datagen.loot.predicates.DragonflyPredicate;
import erebus.datagen.loot.predicates.WaspPredicate;
import erebus.datagen.providers.ModEntityLootSubProvider;
import erebus.loot.*;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.entity.ModEntities;
import erebus.registries.item.ModItems;
import net.minecraft.advancements.criterion.EntityFlagsPredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.MobEffectsPredicate;
import net.minecraft.advancements.criterion.NbtPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;

public class ModEntityLootTables extends ModEntityLootSubProvider {

    public ModEntityLootTables(HolderLookup.Provider registries) {
        super(registries);
    }

    @Override
    public void generate() {
        var guardianLoot = LootTable.lootTable();
        var scarabs = List.of(ModItems.MUD_SCARAB, ModItems.IRON_SCARAB, ModItems.GOLD_SCARAB, ModItems.JADE_SCARAB);
        for (int variant = 0; variant < scarabs.size(); variant++) {
            var data = new CompoundTag();
            data.putInt("GuardianVariant", variant);
            guardianLoot.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                    .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                            EntityPredicate.Builder.entity().nbt(new NbtPredicate(data))))
                    .add(LootItem.lootTableItem(scarabs.get(variant))));
        }
        add(ModEntities.DUNGEON_UMBER_GOLEM.get(), guardianLoot);
        noLoot(ModEntities.ANIMATED_BLOCK);
        noLoot(ModEntities.ANIMATED_BAMBOO_CRATE);
        noLoot(ModEntities.ANIMATED_CHEST);
        noLoot(ModEntities.THROWN_BLOCK_AS_ITEM);
        noLoot(ModEntities.THROWN_SAND);
        noLoot(ModEntities.GOO_BALL);
        noLoot(ModEntities.MUCUS_BOMB_PRIMED);
        noLoot(ModEntities.WASP_DAGGER);
        noLoot(ModEntities.WOODLOUSE_BALL);
        noLoot(ModEntities.WEB_SLING);
        noLoot(ModEntities.AMBER_STAR);
        noLoot(ModEntities.POISON_JET);
        noLoot(ModEntities.TARANTULA_EGG);

        addAntlion();
        addBedBug();
        addBeetle();
        addBlackAnt();
        addBlackWidow();
        addBogMaw();
        addBombardierBeetle();
        addBotFly();
        addCentipede();
        addChameleonTick();
        addCicada();
        addCropWeevil();
        addCrushroom();
        addDragonfly();
        addFireAnt();
        addFly();
        addFungalWeevil();
        addGlowWorm();
        addGrasshopper();
        addHoneyPotAnt();
        addJumpingSpider();
        addLavaSpider();
        addLeech();
        addLocust();
        addMagmaCrawler();
        addMidgeSwarm();
        addMoneySpider();
        addMosquito();
        addMoth();
        addPondSkater();
        addPrayingMantis();
        addPunchroom();
        addRhinoBeetle();
        addScorpion();
        addScytodes();
        addSolifuge();
        addStagBeetle();
        addTarantula();
        addTitanBeetle();
        addUmberGolem();
        addVelvetWorm();
        addWasp();
        addWoodlouse();
        addWorkerBee();
        addZombieAnt();
    }

    private void addAntlion() {
        add(ModEntities.ANTLION.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                .add(LootItem.lootTableItem(ModItems.PLATE_EXO).apply(() -> JumpingSpiderDropCount.INSTANCE))));
        add(ModEntities.ANTLION_MINI_BOSS.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.PLATE_EXO).apply(() -> JumpingSpiderDropCount.INSTANCE))));
        add(ModEntities.ANTLION_BOSS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.SOUL_CRYSTAL)))
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.QUAKE_HAMMER))));
    }

    private void addBedBug() {
        add(ModEntities.BED_BUG.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.WHITE_WOOL))));
    }

    private void addBeetle() {
        add(ModEntities.BEETLE.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                .add(LootItem.lootTableItem(ModItems.PLATE_EXO).apply(() -> BeetlePlateCount.INSTANCE))));

        add(
                ModEntities.BEETLE_LARVA.get(),
                createMultiPoolLootTable(
                        createCookableFoodPool(ModItems.BEETLE_LARVA_RAW, ConstantValue.exactly(1))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                )
        );
    }

    private void addBlackAnt() {
        noLoot(ModEntities.BLACK_ANT);
    }

    private void addBlackWidow() {
        add(ModEntities.BLACK_WIDOW.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.STRING).apply(() -> BeetlePlateCount.INSTANCE)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        .add(LootItem.lootTableItem(Items.SPIDER_EYE).apply(() -> ScytodesEyeCount.INSTANCE)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.POISON_GLAND))));
    }

    private void addBogMaw() {
        add(ModEntities.BOG_MAW.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(ModItems.BOGMAW_ROOT)
                        .apply(() -> BeetlePlateCount.INSTANCE))));
    }

    private void addBombardierBeetle() {
        add(
                ModEntities.BOMBARDIER_BEETLE_LARVA.get(),
                createMultiPoolLootTable(
                        createCookableFoodPool(ModItems.BEETLE_LARVA_RAW, ConstantValue.exactly(1))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                )
        );

        add(ModEntities.BOMBARDIER_BEETLE.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.GUNPOWDER)))
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.BLAZE_POWDER)))
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.PLATE_EXO).apply(() -> StagPlateCount.INSTANCE))));
    }

    private void addBotFly() {
        add(ModEntities.BOT_FLY.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(LegacyArthropodRolls.INSTANCE)
                        .add(NestedLootTable.inlineLootTable(LootTable.lootTable()
                                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.FLY_WING)))
                                .withPool(LootPool.lootPool().when(LootItemRandomChanceCondition.randomChance(0.2F))
                                        .add(LootItem.lootTableItem(ModItems.COMPOUND_EYES))).build()))));

        add(ModEntities.BOT_FLY_LARVA.get(), createSimpleLootTable(Items.SLIME_BALL, ConstantValue.exactly(1)));
    }

    private void addCentipede() {
        add(ModEntities.CENTIPEDE.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(LegacyArthropodRolls.INSTANCE)
                        .add(NestedLootTable.inlineLootTable(LootTable.lootTable()
                                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.BIO_VELOCITY)))
                                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.POISON_GLAND))).build())))
                .withPool(LootPool.lootPool().when(LootItemRandomChanceCondition.randomChance(0.02F))
                        // An empty effects predicate accepts every LivingEntity, but no nonliving attacker.
                        .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER,
                                EntityPredicate.Builder.entity().effects(MobEffectsPredicate.Builder.effects())))
                        .add(LootItem.lootTableItem(ModItems.SUPERNATURAL_VELOCITY))));
    }

    private void addChameleonTick() {
        add(ModEntities.CHAMELEON_TICK.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(ModItems.CAMO_POWDER)
                        .apply(() -> JumpingSpiderDropCount.INSTANCE))));
    }

    private void addCicada() {
        add(ModEntities.CICADA.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                .add(LootItem.lootTableItem(ModItems.REPELLENT).apply(() -> JumpingSpiderDropCount.INSTANCE))));
    }

    private void addCropWeevil() {
        var stigmas = List.of(ModBlocks.STIGMA_BLACK, ModBlocks.STIGMA_RED, ModBlocks.STIGMA_BROWN,
                ModBlocks.STIGMA_BLUE, ModBlocks.STIGMA_PURPLE, ModBlocks.STIGMA_CYAN, ModBlocks.STIGMA_LIGHT_GRAY,
                ModBlocks.STIGMA_GRAY, ModBlocks.STIGMA_PINK, ModBlocks.STIGMA_YELLOW, ModBlocks.STIGMA_LIGHT_BLUE,
                ModBlocks.STIGMA_MAGENTA, ModBlocks.STIGMA_ORANGE, ModBlocks.STIGMA_WHITE);
        var stigmaPool = LootPool.lootPool().setRolls(ConstantValue.exactly(1));
        for (var stigma : stigmas) stigmaPool.add(cropWeevilReward(stigma, true));
        var primary = LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(NestedLootTable.inlineLootTable(LootTable.lootTable().withPool(stigmaPool).build()))
                .add(LootItem.lootTableItem(Items.WHEAT_SEEDS).apply(() -> CropWeevilSeedCount.INSTANCE))
                .add(cropWeevilReward(Items.PUMPKIN_SEEDS, true))
                .add(cropWeevilReward(Items.MELON_SEEDS, true))
                .add(cropWeevilReward(Items.COCOA_BEANS, true));
        var rare = LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .when(LootItemRandomChanceCondition.randomChance(0.1F));
        for (ItemLike crop : List.of(ModItems.TURNIP, Items.NETHER_WART, Items.WHEAT, Items.SUGAR_CANE,
                ModItems.BAMBOO_SHOOT, Items.CARROT, Items.POTATO))
            rare.add(cropWeevilReward(crop, false));
        add(ModEntities.CROP_WEEVIL.get(), LootTable.lootTable().withPool(primary).withPool(rare));
    }

    private LootItem.Builder<?> cropWeevilReward(ItemLike item, boolean variable) {
        return LootItem.lootTableItem(item)
                .apply(SetItemCountFunction.setCount(variable ? UniformGenerator.between(1, 3) : ConstantValue.exactly(1)))
                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, ConstantValue.exactly(1)));
    }

    private void addCrushroom() {
        add(ModEntities.CRUSHROOM.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.HIDE_SHROOM).apply(() -> BeetlePlateCount.INSTANCE))));
    }

    private void addDragonfly() {
        add(
                ModEntities.DRAGON_FLY.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(ModItems.DRAGONFLY_WING)))
                        .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                                .when(LootItemRandomChanceCondition.randomChance(0.2F))
                                .add(LootItem.lootTableItem(ModItems.COMPOUND_EYES)
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, ConstantValue.exactly(1)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.ENDER_PEARL)
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, ConstantValue.exactly(1))))
                                .when(LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        EntityPredicate.Builder.entity()
                                                .subPredicate(DragonflyPredicate.skin(0))
                                )))
        );
    }

    private void addFireAnt() {
        add(
                ModEntities.FIRE_ANT.get(),
                createMultiPoolLootTable(
                        fireAntReward(Items.MAGMA_CREAM),
                        fireAntReward(Items.FIRE_CHARGE).when(LootItemRandomChanceCondition.randomChance(0.02F))
                )
        );

        add(
                ModEntities.FIRE_ANT_SOLDIER.get(),
                createMultiPoolLootTable(
                        fireAntReward(Items.BLAZE_POWDER),
                        fireAntReward(Items.BLAZE_ROD).when(LootItemRandomChanceCondition.randomChance(0.2F))
                )
        );
    }

    private LootPool.Builder fireAntReward(ItemLike item) {
        return LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                .add(LootItem.lootTableItem(item)
                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, ConstantValue.exactly(1))));
    }

    private void addFly() {
        add(ModEntities.FLY.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.1F)).add(LootItem.lootTableItem(ModItems.FLY_WING)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.05F)).add(LootItem.lootTableItem(ModItems.COMPOUND_EYES))));
    }

    private void addFungalWeevil() {
        add(
                ModEntities.FUNGAL_WEEVIL.get(),
                createMultiPoolLootTable(
                        addItemsToPool(
                                LootPool.lootPool().setRolls(ConstantValue.exactly(1)),
                                ConstantValue.exactly(1),
                                Items.BROWN_MUSHROOM,
                                Items.RED_MUSHROOM,
                                ModBlocks.DARK_CAPPED_MUSHROOM,
                                ModBlocks.DUTCH_CAP_MUSHROOM,
                                ModBlocks.GRANDMAS_SHOES_MUSHROOM,
                                ModBlocks.KAIZERS_FINGERS_MUSHROOM,
                                ModBlocks.SARCASTIC_CZECH_MUSHROOM
                        )
                )
        );
    }

    private void addGlowWorm() {
        add(ModEntities.GLOW_WORM.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.BIO_LUMINESCENCE).apply(() -> JumpingSpiderDropCount.INSTANCE))));
    }

    private void addGrasshopper() {
        add(ModEntities.GRASSHOPPER.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                .add(LootItem.lootTableItem(ModItems.GRASSHOPPER_LEG_RAW).apply(() -> JumpingSpiderDropCount.INSTANCE)
                        .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true))))))));
    }

    private void addHoneyPotAnt() {
        add(ModEntities.HONEY_POT_ANT.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ModItems.NECTAR.get()).apply(() -> HoneyPotNectarCount.INSTANCE))));
    }

    private void addJumpingSpider() {
        add(ModEntities.JUMPING_SPIDER.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ModItems.POISON_GLAND)
                        .apply(() -> JumpingSpiderDropCount.INSTANCE))));
    }

    private void addLavaSpider() {
        add(ModEntities.LAVA_WEB_SPIDER.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.FIRE_CHARGE).apply(() -> BeetlePlateCount.INSTANCE)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        .add(LootItem.lootTableItem(Items.SPIDER_EYE).apply(() -> ScytodesEyeCount.INSTANCE))));
    }

    private void addLeech() {
        noLoot(ModEntities.LEECH);
    }

    private void addLocust() {
        add(ModEntities.LOCUST.get(), createSimpleLootTable(ModItems.ELASTIC_FIBER, UniformGenerator.between(1, 4)));
    }

    private void addMagmaCrawler() {
        add(ModEntities.MAGMA_CRAWLER.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.MAGMA_CRAWLER_EYE))));
    }

    private void addMidgeSwarm() {
        add(ModEntities.MIDGE_SWARM.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(LegacyArthropodRolls.INSTANCE)
                .add(NestedLootTable.inlineLootTable(LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(ModItems.FLY_WING)))
                        .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(LootItemRandomChanceCondition.randomChance(0.2F))
                                .add(LootItem.lootTableItem(ModItems.COMPOUND_EYES))).build()))));
    }

    private void addMoneySpider() {
        add(
                ModEntities.MONEY_SPIDER.get(),
                createMultiPoolLootTable(
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(() -> MoneySpiderIngotCount.INSTANCE)),
                        createStandardPool(Items.GOLD_NUGGET, ConstantValue.exactly(1))
                )
        );
    }

    private void addMosquito() {
        var table = LootTable.lootTable();
        for (int blood = 0; blood <= 5; blood++) {
            var data = new CompoundTag();
            data.putByte("BloodLevel", (byte) blood);
            table.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                    .when(LootItemKilledByPlayerCondition.killedByPlayer())
                    .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                            EntityPredicate.Builder.entity().nbt(new NbtPredicate(data))))
                    .add(LootItem.lootTableItem(ModItems.LIFE_BLOOD)
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1 + blood)))));
        }
        add(ModEntities.MOSQUITO.get(), table);
    }

    private void addMoth() {
        add(ModEntities.MOTH.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1)).when(LootItemRandomChanceCondition.randomChance(0.2F))
                .add(LootItem.lootTableItem(Items.GLOWSTONE_DUST))));
    }

    private void addPondSkater() {
        add(ModEntities.POND_SKATER.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(ModItems.HYDROFUGE)
                        .apply(() -> JumpingSpiderDropCount.INSTANCE))));
    }

    private void addPrayingMantis() {
        add(ModEntities.PRAYING_MANTIS.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(ModItems.CAMO_POWDER)
                        .apply(() -> JumpingSpiderDropCount.INSTANCE))));
    }

    private void addPunchroom() {
        add(ModEntities.PUNCHROOM.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .when(LootItemRandomChanceCondition.randomChance(0.2F))
                .add(LootItem.lootTableItem(ModItems.ELASTIC_FIBER)
                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, ConstantValue.exactly(1))))));
    }

    private void addRhinoBeetle() {
        // Same uniform 1..(2 + Looting) range as legacy tarantula legs.
        add(ModEntities.RHINO_BEETLE.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.PLATE_EXO_RHINO).apply(() -> TarantulaDropCount.LEGS)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.05F))
                        .add(LootItem.lootTableItem(ModItems.RHINO_BEETLE_HORN))));
    }

    private void addScorpion() {
        add(ModEntities.SCORPION.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(LegacyArthropodRolls.INSTANCE)
                .add(NestedLootTable.inlineLootTable(LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                                .when(LootItemRandomChanceCondition.randomChance(1F / 30))
                                .add(LootItem.lootTableItem(ModItems.SCORPION_PINCER)))
                        .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(ModItems.POISON_GLAND)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))).build()))));
    }

    private void addScytodes() {
        add(ModEntities.SCYTODES.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.STRING).apply(() -> BeetlePlateCount.INSTANCE)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        .add(LootItem.lootTableItem(Items.SPIDER_EYE).apply(() -> ScytodesEyeCount.INSTANCE))));
    }

    private void addSolifuge() {
        add(ModEntities.SOLIFUGE.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.BIO_VELOCITY)
                                .apply(() -> JumpingSpiderDropCount.INSTANCE)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.02F))
                        .add(LootItem.lootTableItem(ModItems.SUPERNATURAL_VELOCITY))));
        noLoot(ModEntities.BABY_SOLIFUGE);
    }

    private void addStagBeetle() {
        add(ModEntities.STAG_BEETLE.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.PLATE_EXO).apply(() -> StagPlateCount.INSTANCE)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.STAG_BEETLE_MANDIBLES).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.STAG_HEART_RAW).setWeight(4)
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                        EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true))))))
                        .add(EmptyLootItem.emptyItem().setWeight(25))));
    }

    private void addTarantula() {
        for (var type : List.of(ModEntities.TARANTULA.get(), ModEntities.BABY_TARANTULA.get())) {
            add(type, LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(ModItems.TARANTULA_LEG_RAW)
                                    .apply(() -> TarantulaDropCount.LEGS)
                                    .apply(SmeltItemFunction.smelted()
                                            .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                                    EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))))
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(Items.SPIDER_EYE)
                                    .apply(() -> TarantulaDropCount.EYES))));
        }
        noLoot(ModEntities.TARANTULA_MINI_BOSS);
    }

    private void addTitanBeetle() {
        add(ModEntities.TITAN_BEETLE.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.PLATE_EXO).apply(() -> StagPlateCount.INSTANCE)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.TITAN_CHOP_RAW)
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                        EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true))))))));
    }

    private void addUmberGolem() {
        add(ModEntities.UMBER_GOLEM.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(Blocks.STONE).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))))));
    }

    private void addVelvetWorm() {
        add(ModEntities.VELVET_WORM.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.SLIME_BALL)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, ConstantValue.exactly(1))))));
    }

    private void addWasp() {
        add(
                ModEntities.WASP.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(LegacyArthropodRolls.INSTANCE)
                                .add(LootItem.lootTableItem(ModItems.WASP_STING)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(ModItems.ANTI_VENOM_BOTTLE))
                                .when(LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        EntityPredicate.Builder.entity()
                                                .subPredicate(WaspPredicate.isBoss(true))
                                )))
        );
    }

    private void addWoodlouse() {
        add(ModEntities.WOODLOUSE.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.WHETSTONE_POWDER).apply(() -> JumpingSpiderDropCount.INSTANCE))));
    }

    private void addWorkerBee() {
        add(
                ModEntities.WORKER_BEE.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        .add(LootItem.lootTableItem(ModItems.NECTAR.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2)))))
        );
    }

    private void addZombieAnt() {
        for (var type : List.of(ModEntities.ZOMBIE_ANT, ModEntities.ZOMBIE_ANT_SOLDIER)) {
            var rare = LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.2F))
                    .add(LootItem.lootTableItem(ModItems.ANT_PHEROMONES));
            if (type == ModEntities.ZOMBIE_ANT_SOLDIER) rare.add(LootItem.lootTableItem(ModItems.TERPSISHROOM));
            add(type.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(ModItems.PLATE_ZOMBIE_ANT).apply(() -> StagPlateCount.INSTANCE)))
                    .withPool(rare));
        }
    }
}
