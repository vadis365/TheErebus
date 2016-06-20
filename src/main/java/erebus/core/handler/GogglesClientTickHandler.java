package erebus.core.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.ModItems;

@SideOnly(Side.CLIENT)
public class GogglesClientTickHandler {
	public static Minecraft mc = FMLClientHandler.instance().getClient();

	private static PotionEffect nightVisionEffect = new PotionEffect(MobEffects.NIGHT_VISION, 300, 1, true, false);

	@SubscribeEvent
	public void onRenderTick(RenderTickEvent e) {
		if (e.phase == Phase.START) {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			if (player != null && player.inventory.armorInventory[3] != null && player.inventory.armorInventory[3].getItem() == ModItems.COMPOUND_GOGGLES)
				player.addPotionEffect(nightVisionEffect);
			if (player != null && player.inventory.armorInventory[3] != null && player.inventory.armorInventory[3].getItem() == ModItems.REIN_COMPOUND_GOGGLES)
				player.addPotionEffect(nightVisionEffect);
		} else if (e.phase == Phase.END) {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			if (player != null) {
				PotionEffect eff = player.getActivePotionEffect(MobEffects.NIGHT_VISION);
				if (eff != null && eff.getAmplifier() == 1)
					player.removePotionEffect(MobEffects.NIGHT_VISION);
			}
		}
	}
}
