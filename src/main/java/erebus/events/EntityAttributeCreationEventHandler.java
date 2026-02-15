package erebus.events;

import erebus.Erebus;
import erebus.entity.*;
import erebus.registries.entity.ModEntities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = Erebus.MODID)
public class EntityAttributeCreationEventHandler {

    @SubscribeEvent
    public static void initializeAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ANIMATED_BLOCK.get(), AnimatedBlock.createAttributes().build());
        event.put(ModEntities.ANTLION.get(), Antlion.createAttributes().build());
        event.put(ModEntities.ANTLION_BOSS.get(), AntlionBoss.createAttributes().build());
        event.put(ModEntities.ANTLION_MINI_BOSS.get(), AntlionMiniBoss.createAttributes().build());
        event.put(ModEntities.BABY_SOLIFUGE.get(), BabySolifuge.createAttributes().build());
        event.put(ModEntities.BABY_TARANTULA.get(), BabyTarantula.createAttributes().build());
        event.put(ModEntities.BED_BUG.get(), BedBug.createAttributes().build());
        event.put(ModEntities.BEETLE.get(), Beetle.createAttributes().build());
        event.put(ModEntities.BEETLE_LARVA.get(), BeetleLarva.createAttributes().build());
        event.put(ModEntities.BLACK_ANT.get(), BlackAnt.createAttributes().build());
        event.put(ModEntities.BLACK_WIDOW.get(), BlackWidow.createAttributes().build());
        event.put(ModEntities.BOG_MAW.get(), BogMaw.createAttributes().build());
        event.put(ModEntities.BOMBARDIER_BEETLE.get(), BombardierBeetle.createAttributes().build());
        event.put(ModEntities.BOMBARDIER_BEETLE_LARVA.get(), BombardierBeetleLarva.createAttributes().build());
        event.put(ModEntities.BOT_FLY.get(), BotFly.createAttributes().build());
        event.put(ModEntities.BOT_FLY_LARVA.get(), BotFlyLarva.createAttributes().build());
        event.put(ModEntities.CENTIPEDE.get(), Centipede.createAttributes().build());
        event.put(ModEntities.CHAMELEON_TICK.get(), ChameleonTick.createAttributes().build());
        event.put(ModEntities.CICADA.get(), Cicada.createAttributes().build());
        event.put(ModEntities.CROP_WEEVIL.get(), CropWeevil.createAttributes().build());
        event.put(ModEntities.CRUSHROOM.get(), Crushroom.createAttributes().build());
        event.put(ModEntities.DRAGON_FLY.get(), Dragonfly.createAttributes().build());
        event.put(ModEntities.FIRE_ANT.get(), FireAnt.createAttributes().build());
        event.put(ModEntities.FIRE_ANT_SOLDIER.get(), FireAntSoldier.createAttributes().build());
        event.put(ModEntities.FLY.get(), Fly.createAttributes().build());
        event.put(ModEntities.FUNGAL_WEEVIL.get(), FungalWeevil.createAttributes().build());
        event.put(ModEntities.GLOW_WORM.get(), GlowWorm.createAttributes().build());
        event.put(ModEntities.GRASSHOPPER.get(), Grasshopper.createAttributes().build());
        event.put(ModEntities.HONEY_POT_ANT.get(), HoneyPotAnt.createAttributes().build());
        event.put(ModEntities.JUMPING_SPIDER.get(), JumpingSpider.createAttributes().build());
        event.put(ModEntities.LAVA_WEB_SPIDER.get(), LavaWebSpider.createAttributes().build());
        event.put(ModEntities.LOCUST.get(), Locust.createAttributes().build());
        event.put(ModEntities.MAGMA_CRAWLER.get(), MagmaCrawler.createAttributes().build());
        event.put(ModEntities.MIDGE_SWARM.get(), MidgeSwarm.createAttributes().build());
        event.put(ModEntities.MONEY_SPIDER.get(), MoneySpider.createAttributes().build());
        event.put(ModEntities.MOSQUITO.get(), Mosquito.createAttributes().build());
        event.put(ModEntities.MOTH.get(), Moth.createAttributes().build());
        event.put(ModEntities.POND_SKATER.get(), PondSkater.createAttributes().build());
        event.put(ModEntities.PRAYING_MANTIS.get(), PrayingMantis.createAttributes().build());
        event.put(ModEntities.PUNCHROOM.get(), Punchroom.createAttributes().build());
        event.put(ModEntities.RHINO_BEETLE.get(), RhinoBeetle.createAttributes().build());
        event.put(ModEntities.SCORPION.get(), Scorpion.createAttributes().build());
        event.put(ModEntities.SCYTODES.get(), Scytodes.createAttributes().build());
        event.put(ModEntities.SOLIFUGE.get(), Solifuge.createAttributes().build());
        event.put(ModEntities.STAG_BEETLE.get(), StagBeetle.createAttributes().build());
        event.put(ModEntities.TARANTULA.get(), Tarantula.createAttributes().build());
        event.put(ModEntities.TARANTULA_MINI_BOSS.get(), TarantulaMiniBoss.createAttributes().build());
        event.put(ModEntities.TITAN_BEETLE.get(), TitanBeetle.createAttributes().build());
        event.put(ModEntities.UMBER_GOLEM.get(), UmberGolem.createAttributes().build());
        event.put(ModEntities.VELVET_WORM.get(), VelvetWorm.createAttributes().build());
        event.put(ModEntities.WASP.get(), Wasp.createAttributes().build());
        event.put(ModEntities.WOODLOUSE.get(), Woodlouse.createAttributes().build());
        event.put(ModEntities.WORKER_BEE.get(), WorkerBee.createAttributes().build());
        event.put(ModEntities.ZOMBIE_ANT.get(), ZombieAnt.createAttributes().build());
        event.put(ModEntities.ZOMBIE_ANT_SOLDIER.get(), ZombieAntSoldier.createAttributes().build());
    }
}
