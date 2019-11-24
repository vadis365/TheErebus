package erebus.world.teleporter;

import java.util.UUID;

import erebus.Erebus;
import erebus.core.handler.configs.ConfigHandler;
import gnu.trove.map.TObjectByteMap;
import gnu.trove.map.hash.TObjectByteHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;

public final class TeleporterHandler {
	private static TeleporterHandler INSTANCE = new TeleporterHandler();

	public static void init() {
		MinecraftForge.EVENT_BUS.register(INSTANCE);
	}

	public static void transferToOverworld(Entity entity) {
		INSTANCE.transferEntity(entity, 0);
	}

	public static void transferToErebus(Entity entity) {
		INSTANCE.transferEntity(entity, ConfigHandler.INSTANCE.erebusDimensionID);
	}

	private final TObjectByteMap<UUID> waitingPlayers = new TObjectByteHashMap<UUID>();
	private boolean checkWaitingPlayers = false;

	private TeleporterErebus teleportToOverworld;
	private TeleporterErebus teleportToErebus;

	private TeleporterHandler() {
	}

	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load e) {
		if (!(e.getWorld() instanceof WorldServer))
			return;

		WorldServer world = (WorldServer) e.getWorld();

		if (world.provider.getDimensionType() == DimensionType.OVERWORLD)
			world.customTeleporters.add(teleportToOverworld = new TeleporterErebus(world));
		else if (world.provider.getDimensionType() == Erebus.dimensionType)
			world.customTeleporters.add(teleportToErebus = new TeleporterErebus(world));
	}

	@SubscribeEvent
	public void onServerTick(ServerTickEvent e) {
		if (e.phase != Phase.END || !checkWaitingPlayers)
			return;

		UUID[] ids = waitingPlayers.keys(new UUID[waitingPlayers.size()]);

		for (UUID uuid : ids)
			if (waitingPlayers.adjustOrPutValue(uuid, (byte) -1, (byte) 0) <= 0) {
				waitingPlayers.remove(uuid);
				if (waitingPlayers.isEmpty())
					checkWaitingPlayers = false;
			}
	}

	private void transferEntity(Entity entity, int dimensionId) {
		if (dimensionId != 0 && dimensionId != ConfigHandler.INSTANCE.erebusDimensionID)
			throw new IllegalArgumentException("Supplied invalid dimension ID into Erebus teleporter: " + dimensionId);
		World world = entity.world;
		if (!world.isRemote && !entity.isDead && !(entity instanceof FakePlayer) && world instanceof WorldServer) {
			if (!net.minecraftforge.common.ForgeHooks.onTravelToDimension(entity, dimensionId))
				return;
			MinecraftServer server = world.getMinecraftServer();
			WorldServer toWorld = server.getWorld(dimensionId);
			AxisAlignedBB aabb = entity.getEntityBoundingBox();
			aabb = new AxisAlignedBB(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY, aabb.maxZ);
			if (entity instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) entity;
				// player.invulnerableDimensionChange = true;
				if (waitingPlayers.containsKey(player.getUniqueID())) {
					waitingPlayers.put(player.getUniqueID(), (byte) 20);
					return;
				}
				waitingPlayers.put(player.getUniqueID(), (byte) 40);
				checkWaitingPlayers = true;
				player.server.getPlayerList().transferPlayerToDimension(player, dimensionId, dimensionId == 0 ? teleportToOverworld : teleportToErebus);
				player.timeUntilPortal = 0;
			} else {
				entity.setDropItemsWhenDead(false);
				world.removeEntityDangerously(entity);
				entity.dimension = dimensionId;
				entity.isDead = false;
				WorldServer oldWorld = server.getWorld(entity.dimension);
				server.getPlayerList().transferEntityToWorld(entity, dimensionId, oldWorld, toWorld, new TeleporterErebus(toWorld));
				toWorld.updateEntity(entity);
			}
		}
	}
}