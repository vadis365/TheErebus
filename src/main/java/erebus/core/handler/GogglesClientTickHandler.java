package erebus.core.handler;

import erebus.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GogglesClientTickHandler {
	public static Minecraft mc = FMLClientHandler.instance().getClient();

	private static PotionEffect nightVisionEffect = new PotionEffect(Potion.nightVision.id, 300, 1, true, false);

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
