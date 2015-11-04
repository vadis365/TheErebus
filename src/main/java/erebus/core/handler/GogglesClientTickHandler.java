package erebus.core.handler;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

@SideOnly(Side.CLIENT)
public class GogglesClientTickHandler {
	public static Minecraft mc = FMLClientHandler.instance().getClient();

	private static PotionEffect nightVisionEffect = new PotionEffect(Potion.nightVision.id, 300, 1, true);

	@SubscribeEvent
	public void onRenderTick(RenderTickEvent e) {
		if (e.phase == Phase.START) {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			if (player != null && player.inventory.armorInventory[3] != null && player.inventory.armorInventory[3].getItem() == ModItems.compoundGoggles)
				player.addPotionEffect(nightVisionEffect);
			if (player != null && player.inventory.armorInventory[3] != null && player.inventory.armorInventory[3].getItem() == ModItems.reinCompoundGoggles)
				player.addPotionEffect(nightVisionEffect);
		} else if (e.phase == Phase.END) {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			if (player != null) {
				PotionEffect eff = player.getActivePotionEffect(Potion.nightVision);
				if (eff != null && eff.getAmplifier() == 1)
					player.removePotionEffectClient(Potion.nightVision.id);
			}
		}
	}
}
