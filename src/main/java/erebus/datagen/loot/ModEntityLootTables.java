package erebus.datagen.loot;

import erebus.datagen.loot.predicates.DragonflyPredicate;
import erebus.datagen.loot.predicates.WaspPredicate;
import erebus.datagen.providers.ModEntityLootSubProvider;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.entity.ModEntities;
import erebus.registries.item.ModItems;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ModEntityLootTables extends ModEntityLootSubProvider {

    public ModEntityLootTables(HolderLookup.Provider registries) {
        super(registries);
    }

    @Override
    public void generate() {
        noLoot(ModEntities.ANIMATED_BLOCK);
        noLoot(ModEntities.THROWN_BLOCK_AS_ITEM);
        noLoot(ModEntities.GOO_BALL);
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
        add(
                ModEntities.ANTLION.get(),
                createMultiPoolLootTable(
                        createStandardPool(ModItems.PLATE_EXO, UniformGenerator.between(-1, 1)),
                        createStandardPool(Blocks.SAND, ConstantValue.exactly(1))
                )
        );

        add(
                ModEntities.ANTLION_BOSS.get(),
                createMultiPoolLootTable(
                        createStandardPool(ModItems.SOUL_CRYSTAL, ConstantValue.exactly(1)),
                        createStandardPool(ModItems.QUAKE_HAMMER, ConstantValue.exactly(1))
                )
        );

        add(
                ModEntities.ANTLION_MINI_BOSS.get(),

                createMultiPoolLootTable(
                        createStandardPool(ModItems.PLATE_EXO, UniformGenerator.between(-1, 1)),
                        createStandardPool(Blocks.SAND, ConstantValue.exactly(1))
                )
        );
    }

    private void addBedBug() {
        add(
                ModEntities.BED_BUG.get(),
                createSimpleLootTable(Items.WHITE_WOOL, UniformGenerator.between(1, 2))
        );
    }

    private void addBeetle() {
        add(
                ModEntities.BEETLE.get(),
                createSimpleLootTable(ModItems.PLATE_EXO, UniformGenerator.between(1, 3))
        );

        add(
                ModEntities.BEETLE_LARVA.get(),
                createMultiPoolLootTable(
                        createCookableFoodPool(ModItems.BEETLE_LARVA_RAW, ConstantValue.exactly(1))
                )
        );
    }

    private void addBlackAnt() {
        noLoot(ModEntities.BLACK_ANT);
    }

    private void addBlackWidow() {
        add(
                ModEntities.BLACK_WIDOW.get(),
                createMultiPoolLootTable(
                        createPlayerKillPool(Items.SPIDER_EYE, UniformGenerator.between(-1, 1)),
                        createStandardPool(ModItems.POISON_GLAND, ConstantValue.exactly(1))
                )
        );
    }

    private void addBogMaw() {
        add(
                ModEntities.BOG_MAW.get(),
                createSimpleLootTable(ModItems.BOGMAW_ROOT, UniformGenerator.between(1, 3))
        );
    }

    private void addBombardierBeetle() {
        add(
                ModEntities.BOMBARDIER_BEETLE_LARVA.get(),
                createMultiPoolLootTable(
                        createCookableFoodPool(ModItems.BEETLE_LARVA_RAW, ConstantValue.exactly(1))
                )
        );

        add(
                ModEntities.BOMBARDIER_BEETLE.get(),
                createMultiPoolLootTable(
                        createStandardPool(Items.GUNPOWDER, ConstantValue.exactly(1)),
                        createStandardPool(Items.BLAZE_POWDER, ConstantValue.exactly(1)),
                        createStandardPool(ModItems.PLATE_EXO, UniformGenerator.between(1, 3))
                )
        );
    }

    private void addBotFly() {
        add(
                ModEntities.BOT_FLY.get(),
                createMultiPoolLootTable(
                        createStandardPool(ModItems.FLY_WING, UniformGenerator.between(-1, 1)),
                        createChancePool(ModItems.COMPOUND_EYES, UniformGenerator.between(-1, 1), 0.2F)
                )
        );

        add(ModEntities.BOT_FLY_LARVA.get(), createSimpleLootTable(Items.SLIME_BALL, ConstantValue.exactly(1)));
    }

    private void addCentipede() {
        add(
                ModEntities.CENTIPEDE.get(),
                createMultiPoolLootTable(
                        createStandardPool(ModItems.BIO_VELOCITY, UniformGenerator.between(-1, 1)),
                        createStandardPool(ModItems.POISON_GLAND, UniformGenerator.between(-1, 1))
                )
        );
    }

    private void addChameleonTick() {
        add(ModEntities.CHAMELEON_TICK.get(), createSimpleLootTable(ModItems.CAMO_POWDER, UniformGenerator.between(1, 4)));
    }

    private void addCicada() {
        add(ModEntities.CICADA.get(), createSimpleLootTable(ModItems.REPELLENT, UniformGenerator.between(1, 4)));
    }

    private void addCropWeevil() {
        add(
                ModEntities.CROP_WEEVIL.get(),
                createMultiPoolLootTable(
                        addItemsToPool(
                                LootPool.lootPool().setRolls(ConstantValue.exactly(1)),
                                ConstantValue.exactly(1),
                                ModBlocks.STIGMA_BLACK,
                                ModBlocks.STIGMA_RED,
                                ModBlocks.STIGMA_BROWN,
                                ModBlocks.STIGMA_BLUE,
                                ModBlocks.STIGMA_PURPLE,
                                ModBlocks.STIGMA_CYAN,
                                ModBlocks.STIGMA_LIGHT_GRAY,
                                ModBlocks.STIGMA_GRAY,
                                ModBlocks.STIGMA_PINK,
                                ModBlocks.STIGMA_YELLOW,
                                ModBlocks.STIGMA_LIGHT_BLUE,
                                ModBlocks.STIGMA_MAGENTA,
                                ModBlocks.STIGMA_ORANGE,
                                ModBlocks.STIGMA_WHITE
                        ),
                        addItemsToPool(
                                LootPool.lootPool().setRolls(ConstantValue.exactly(1)),
                                UniformGenerator.between(1, 2),
                                Items.PUMPKIN_SEEDS,
                                Items.MELON_SEEDS,
                                Items.WHEAT_SEEDS,
                                ModItems.CABBAGE_SEEDS
                        ),
                        addItemsToPool(
                                LootPool.lootPool().setRolls(ConstantValue.exactly(1)),
                                UniformGenerator.between(1, 2),
                                ModItems.TURNIP,
                                Items.NETHER_WART,
                                Items.WHEAT,
                                Items.SUGAR_CANE,
                                ModItems.BAMBOO_SHOOT,
                                Items.CARROT,
                                Items.POTATO
                        ).when(LootItemRandomChanceCondition.randomChance(0.1F))
                )
        );
    }

    private void addCrushroom() {
        add(ModEntities.CRUSHROOM.get(), createSimpleLootTable(ModItems.HIDE_SHROOM, UniformGenerator.between(1, 3)));
    }

    private void addDragonfly() {
        add(
                ModEntities.DRAGON_FLY.get(),
                LootTable.lootTable()
                        .withPool(createStandardPool(ModItems.DRAGONFLY_WING, UniformGenerator.between(-1, 1)))
                        .withPool(createChancePool(ModItems.COMPOUND_EYES, UniformGenerator.between(0, 2), 0.2F))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(createLootItem(Items.ENDER_PEARL, ConstantValue.exactly(1)))
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
                        createStandardPool(Items.MAGMA_CREAM, UniformGenerator.between(1, 2)),
                        createChancePool(Items.FIRE_CHARGE, UniformGenerator.between(1, 2), 0.5F)
                )
        );

        add(
                ModEntities.FIRE_ANT_SOLDIER.get(),
                createMultiPoolLootTable(
                        createStandardPool(Items.BLAZE_POWDER, UniformGenerator.between(1, 2)),
                        createChancePool(Items.BLAZE_ROD, UniformGenerator.between(1, 2), 0.5F)
                )
        );
    }

    private void addFly() {
        add(
                ModEntities.FLY.get(),
                createMultiPoolLootTable(
                        createChancePool(ModItems.FLY_WING, UniformGenerator.between(-1, 1), 0.1F),
                        createChancePool(ModItems.COMPOUND_EYES, UniformGenerator.between(-1, 1), 0.05F)
                )
        );
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
        add(ModEntities.GLOW_WORM.get(), createSimpleLootTable(ModItems.BIO_LUMINESCENCE, UniformGenerator.between(1, 4)));
    }

    private void addGrasshopper() {
        add(
                ModEntities.GRASSHOPPER.get(),
                createMultiPoolLootTable(
                        createCookableFoodPool(ModItems.GRASSHOPPER_LEG_RAW, UniformGenerator.between(1, 4))
                )
        );
    }

    private void addHoneyPotAnt() {
        add(ModEntities.HONEY_POT_ANT.get(), createSimpleLootTable(ModItems.NECTAR, ConstantValue.exactly(1)));
    }

    private void addJumpingSpider() {
        add(ModEntities.JUMPING_SPIDER.get(), createSimpleLootTable(ModItems.POISON_GLAND, UniformGenerator.between(1, 4)));
    }

    private void addLavaSpider() {
        add(
                ModEntities.LAVA_WEB_SPIDER.get(),
                createMultiPoolLootTable(
                        createPlayerKillPool(Items.SPIDER_EYE, UniformGenerator.between(-1, 1)),
                        createStandardPool(Items.FIRE_CHARGE, ConstantValue.exactly(1))
                )
        );
    }

    private void addLeech() {
        noLoot(ModEntities.LEECH);
    }

    private void addLocust() {
        add(ModEntities.LOCUST.get(), createSimpleLootTable(ModItems.ELASTIC_FIBER, UniformGenerator.between(1, 4)));
    }

    private void addMagmaCrawler() {
        add(ModEntities.MAGMA_CRAWLER.get(), createSimpleLootTable(ModItems.MAGMA_CRAWLER_EYE, ConstantValue.exactly(1)));
    }

    private void addMidgeSwarm() {
        add(
                ModEntities.MIDGE_SWARM.get(),
                createMultiPoolLootTable(
                        createStandardPool(ModItems.FLY_WING, ConstantValue.exactly(1)),
                        createChancePool(ModItems.COMPOUND_EYES, ConstantValue.exactly(1), 0.2F)
                )
        );
    }

    private void addMoneySpider() {
        add(
                ModEntities.MONEY_SPIDER.get(),
                createMultiPoolLootTable(
                        createPlayerKillChancePool(Items.GOLD_INGOT, UniformGenerator.between(-1, 1), 0.1F),
                        createStandardPool(Items.GOLD_NUGGET, ConstantValue.exactly(1))
                )
        );
    }

    private void addMosquito() {
        add(ModEntities.MOSQUITO.get(), createSimpleLootTable(ModItems.LIFE_BLOOD, ConstantValue.exactly(1)));
    }

    private void addMoth() {
        add(
                ModEntities.MOTH.get(),
                createMultiPoolLootTable(
                        createChancePool(Items.GLOWSTONE_DUST, UniformGenerator.between(-1, 1), 0.2F)
                )
        );
    }

    private void addPondSkater() {
        add(ModEntities.POND_SKATER.get(), createSimpleLootTable(ModItems.HYDROFUGE, UniformGenerator.between(1, 4)));
    }

    private void addPrayingMantis() {
        add(ModEntities.PRAYING_MANTIS.get(), createSimpleLootTable(ModItems.CAMO_POWDER, UniformGenerator.between(1, 4)));
    }

    private void addPunchroom() {
        add(ModEntities.PUNCHROOM.get(), createMultiPoolLootTable(createChancePool(ModItems.ELASTIC_FIBER, ConstantValue.exactly(1), 0.2F)));
    }

    private void addRhinoBeetle() {
        add(
                ModEntities.RHINO_BEETLE.get(),
                createMultiPoolLootTable(
                        createStandardPool(ModItems.PLATE_EXO_RHINO, UniformGenerator.between(1, 2)),
                        createChancePool(ModItems.RHINO_BEETLE_HORN, ConstantValue.exactly(1), 0.05F)
                )
        );
    }

    private void addScorpion() {
        add(
                ModEntities.SCORPION.get(),
                createMultiPoolLootTable(
                        createStandardPool(ModItems.POISON_GLAND, UniformGenerator.between(1, 4)),
                        createChancePool(ModItems.SCORPION_PINCER, ConstantValue.exactly(1), 0.03F)
                )
        );
    }

    private void addScytodes() {
        add(
                ModEntities.SCYTODES.get(),
                createMultiPoolLootTable(
                        createPlayerKillPool(Items.SPIDER_EYE, UniformGenerator.between(-1, 1)),
                        createStandardPool(Items.STRING, ConstantValue.exactly(1))
                )
        );
    }

    private void addSolifuge() {
        noLoot(ModEntities.SOLIFUGE);
        noLoot(ModEntities.BABY_SOLIFUGE);
    }

    private void addStagBeetle() {
        add(
                ModEntities.STAG_BEETLE.get(),
                createMultiPoolLootTable(
                        createStandardPool(ModItems.PLATE_EXO, UniformGenerator.between(0, 3)),
                        createChancePool(ModItems.STAG_BEETLE_MANDIBLES, ConstantValue.exactly(1), 0.03F), // ~ 1 / 30
                        createCookableFoodPool(ModItems.STAG_HEART_RAW, ConstantValue.exactly(1)).when(LootItemRandomChanceCondition.randomChance(0.13F)) // ~ 4 in 30
                )
        );
    }

    private void addTarantula() {
        noLoot(ModEntities.TARANTULA);
        noLoot(ModEntities.BABY_TARANTULA);
        noLoot(ModEntities.TARANTULA_MINI_BOSS);
    }

    private void addTitanBeetle() {
        add(
                ModEntities.TITAN_BEETLE.get(),
                createMultiPoolLootTable(
                        createStandardPool(ModItems.PLATE_EXO, UniformGenerator.between(1, 4)),
                        createCookableFoodPool(ModItems.TITAN_CHOP_RAW, UniformGenerator.between(1, 2))
                )
        );
    }

    private void addUmberGolem() {
        add(ModEntities.UMBER_GOLEM.get(), createSimpleLootTable(Blocks.STONE, ConstantValue.exactly(5)));
    }

    private void addVelvetWorm() {
        add(ModEntities.VELVET_WORM.get(), createSimpleLootTable(Items.SLIME_BALL, UniformGenerator.between(1, 2)));
    }

    private void addWasp() {
        add(
                ModEntities.WASP.get(),
                LootTable.lootTable()
                        .withPool(createPlayerKillChancePool(ModItems.WASP_STING, UniformGenerator.between(-1, 1), 0.1F))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(createLootItem(ModItems.ANTI_VENOM_BOTTLE, ConstantValue.exactly(1)))
                                .when(LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        EntityPredicate.Builder.entity()
                                                .subPredicate(WaspPredicate.isBoss(true))
                                )))
        );
    }

    private void addWoodlouse() {
        add(ModEntities.WOODLOUSE.get(), createSimpleLootTable(ModItems.WHETSTONE_POWDER, UniformGenerator.between(1, 4)));
    }

    private void addWorkerBee() {
        add(
                ModEntities.WORKER_BEE.get(),
                createSimpleLootTable(ModItems.NECTAR, UniformGenerator.between(-1, 1))
        );
    }

    private void addZombieAnt() {
        add(
                ModEntities.ZOMBIE_ANT.get(),
                createMultiPoolLootTable(
                        createStandardPool(ModItems.PLATE_ZOMBIE_ANT, UniformGenerator.between(1, 4)),
                        createChancePool(ModItems.ANT_PHEROMONES, ConstantValue.exactly(1), 0.2F)
                )
        );

        add(
                ModEntities.ZOMBIE_ANT_SOLDIER.get(),
                createMultiPoolLootTable(
                        createStandardPool(ModItems.PLATE_ZOMBIE_ANT, UniformGenerator.between(1, 4)),
                        addItemsToPool(
                                LootPool.lootPool().setRolls(ConstantValue.exactly(1)),
                                ConstantValue.exactly(1),
                                ModItems.ANT_PHEROMONES,
                                ModItems.TERPSISHROOM
                        ).when(LootItemRandomChanceCondition.randomChance(0.2F))
                )
        );
    }
}
