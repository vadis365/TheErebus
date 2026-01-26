package erebus.events;

import erebus.Erebus;
import erebus.entity.*;
import erebus.registries.entity.ModEntities;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = Erebus.MODID)
public class RegisterSpawnPlacementsEventHandler {

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.WASP.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Wasp::canSpawnHere, null);
        event.register(ModEntities.SCYTODES.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Scytodes::canSpawnHere, null);
        event.register(ModEntities.BLACK_WIDOW.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BlackWidow::canSpawnHere, null);
        event.register(ModEntities.LAVA_WEB_SPIDER.get(), SpawnPlacementTypes.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaWebSpider::canSpawnHere, null);
        event.register(ModEntities.MOTH.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Moth::canSpawnHere, null);
        event.register(ModEntities.VELVET_WORM.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, VelvetWorm::canSpawnHere, null);
        event.register(ModEntities.ANTLION.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Antlion::canSpawnHere, null);
        event.register(ModEntities.BOT_FLY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BotFly::canSpawnHere, null);
        event.register(ModEntities.FLY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Fly::canSpawnHere, null);
        event.register(ModEntities.DRAGON_FLY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Dragonfly::canSpawnHere, null);
        event.register(ModEntities.CENTIPEDE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Centipede::canSpawnHere, null);
        event.register(ModEntities.GRASSHOPPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Grasshopper::canSpawnHere, null);
        event.register(ModEntities.LOCUST.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Locust::canSpawnHere, null);
        event.register(ModEntities.BEETLE_LARVA.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BeetleLarva::canSpawnHere, null);
        event.register(ModEntities.BEETLE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Beetle::canSpawnHere, null);
        event.register(ModEntities.BOMBARDIER_BEETLE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BombardierBeetle::canSpawnHere, null);
        event.register(ModEntities.BOMBARDIER_BEETLE_LARVA.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BombardierBeetleLarva::canSpawnHereAlt, null);
        event.register(ModEntities.WORKER_BEE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WorkerBee::canSpawnHere, null);
        event.register(ModEntities.BLACK_ANT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BlackAnt::canSpawnHere, null);
        event.register(ModEntities.PUNCHROOM.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Punchroom::canSpawnHere, null);
        event.register(ModEntities.CROP_WEEVIL.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CropWeevil::canSpawnHereAlt, null);
        event.register(ModEntities.FUNGAL_WEEVIL.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FungalWeevil::canSpawnHereAlt, null);
        event.register(ModEntities.HONEY_POT_ANT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HoneyPotAnt::canSpawnHere, null);
        event.register(ModEntities.ZOMBIE_ANT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ZombieAnt::canSpawnHere, null);
        event.register(ModEntities.ZOMBIE_ANT_SOLDIER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ZombieAntSoldier::canSpawnHere, null);
    }
}
