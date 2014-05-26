package erebus.core.handler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.lwjgl.input.Keyboard;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.core.teleport.TeleportClient;

@SideOnly(Side.CLIENT)
public class ClientTickHandler{
	public static Minecraft mc = FMLClientHandler.instance().getClient();
	public boolean keyStates[] = new boolean[256];

	private static PotionEffect nightVisionEffect = new PotionEffect(Potion.nightVision.id, 300, 1, true);

	public boolean checkKey(int i) {
		if (Minecraft.getMinecraft().currentScreen != null)
			return false;
		if (Keyboard.isKeyDown(i) != keyStates[i])
			return keyStates[i] = !keyStates[i];
		else
			return false;
	}
	
	@SubscribeEvent
	public void onRenderTick(RenderTickEvent e){
		if (e.phase == Phase.START){
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			if (player != null && player.inventory.armorInventory[3] != null && player.inventory.armorInventory[3].getItem() == ModItems.compoundGoggles)
				player.addPotionEffect(nightVisionEffect);
			if (player != null && player.inventory.armorInventory[3] != null && player.inventory.armorInventory[3].getItem() == ModItems.reinCompoundGoggles)
				player.addPotionEffect(nightVisionEffect);
		}
		else if (e.phase == Phase.END){
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			if (player != null) {
				PotionEffect eff = player.getActivePotionEffect(Potion.nightVision);
				if (eff != null && eff.getAmplifier() == 1)
					player.removePotionEffectClient(Potion.nightVision.id);
			}

			float time = TeleportClient.prevTimeInPortal + (TeleportClient.timeInPortal - TeleportClient.prevTimeInPortal) * e.renderTickTime;

			if (!(time > 0F && TeleportClient.mc.currentScreen == null) && TeleportClient.mc.thePlayer != null) {
				PotionEffect eff = TeleportClient.mc.thePlayer.getActivePotionEffect(Potion.confusion);
				if (eff != null && eff.getAmplifier() == 69)
					TeleportClient.mc.thePlayer.removePotionEffect(Potion.confusion.id);
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent e){
		TeleportClient.onTick(mc.thePlayer);
	}
}