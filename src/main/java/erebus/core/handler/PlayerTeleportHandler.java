package erebus.core.handler;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.common.IPlayerTracker;
import erebus.core.teleport.TeleportServer;

public class PlayerTeleportHandler implements IPlayerTracker {

	public HashMap<String, TeleportServer> serverPlayers = new HashMap<String, TeleportServer>();

	public void onTick(EntityPlayerMP player) {
		getPlayer(player.username).onTick();
	}

	public TeleportServer getPlayer(String username) {
		return serverPlayers.get(username);
	}

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		serverPlayers.put(player.username, new TeleportServer((EntityPlayerMP) player));
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		serverPlayers.remove(player.username);
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
	}
}