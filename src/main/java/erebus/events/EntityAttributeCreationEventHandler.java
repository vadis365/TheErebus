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
        event.put(ModEntities.WASP.get(), Wasp.createAttributes().build());
        event.put(ModEntities.ANIMATED_BLOCK.get(), AnimatedBlock.createAttributes().build());
        event.put(ModEntities.SCYTODES.get(), Scytodes.createAttributes().build());
        event.put(ModEntities.MONEY_SPIDER.get(), MoneySpider.createAttributes().build());
        event.put(ModEntities.BLACK_WIDOW.get(), BlackWidow.createAttributes().build());
        event.put(ModEntities.LAVA_WEB_SPIDER.get(), LavaWebSpider.createAttributes().build());
        event.put(ModEntities.MOTH.get(), Moth.createAttributes().build());
        event.put(ModEntities.VELVET_WORM.get(), VelvetWorm.createAttributes().build());
        event.put(ModEntities.ANTLION.get(), Antlion.createAttributes().build());
        event.put(ModEntities.BOT_FLY.get(), BotFly.createAttributes().build());
        event.put(ModEntities.BOT_FLY_LARVA.get(), BotFlyLarva.createAttributes().build());
        event.put(ModEntities.FLY.get(), Fly.createAttributes().build());
        event.put(ModEntities.DRAGON_FLY.get(), Dragonfly.createAttributes().build());
        event.put(ModEntities.CENTIPEDE.get(), Centipede.createAttributes().build());
        event.put(ModEntities.GRASSHOPPER.get(), Grasshopper.createAttributes().build());
        event.put(ModEntities.LOCUST.get(), Locust.createAttributes().build());
        event.put(ModEntities.BEETLE_LARVA.get(), BeetleLarva.createAttributes().build());
        event.put(ModEntities.BOMBARDIER_BEETLE_LARVA.get(), BombardierBeetleLarva.createAttributes().build());
        event.put(ModEntities.BEETLE.get(), Beetle.createAttributes().build());
        event.put(ModEntities.WORKER_BEE.get(), WorkerBee.createAttributes().build());
        event.put(ModEntities.BOMBARDIER_BEETLE.get(), BombardierBeetle.createAttributes().build());
        event.put(ModEntities.BLACK_ANT.get(), BlackAnt.createAttributes().build());
        event.put(ModEntities.PUNCHROOM.get(), Punchroom.createAttributes().build());
        event.put(ModEntities.CROP_WEEVIL.get(), CropWeevil.createAttributes().build());
        event.put(ModEntities.FUNGAL_WEEVIL.get(), FungalWeevil.createAttributes().build());
        event.put(ModEntities.BED_BUG.get(), BedBug.createAttributes().build());
        event.put(ModEntities.HONEY_POT_ANT.get(), HoneyPotAnt.createAttributes().build());
        event.put(ModEntities.ZOMBIE_ANT.get(), ZombieAnt.createAttributes().build());
        event.put(ModEntities.ZOMBIE_ANT_SOLDIER.get(), ZombieAntSoldier.createAttributes().build());
    }
}
