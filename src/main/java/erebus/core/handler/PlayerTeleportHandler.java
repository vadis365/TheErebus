package erebus.core.handler;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import erebus.core.teleport.TeleportServer;

// TODO I'm not registered anywhere :(
public class PlayerTeleportHandler{
	public HashMap<String, TeleportServer> serverPlayers = new HashMap<String, TeleportServer>();

	public void onTick(EntityPlayerMP player) {
		getPlayer(player.getCommandSenderName()).onTick();
	}

	public TeleportServer getPlayer(String username) {
		return serverPlayers.get(username);
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent e){
		serverPlayers.put(e.player.getCommandSenderName(), new TeleportServer((EntityPlayerMP) e.player));
	}

	@SubscribeEvent
	public void onPlayerLogout(PlayerLoggedOutEvent e){
		serverPlayers.remove(e.player.getCommandSenderName());
	}
}