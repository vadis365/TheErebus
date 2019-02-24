package erebus.core.handler;

import erebus.ModItems;
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

@SideOnly(Side.CLIENT)
public class GogglesClientTickHandler {
	public static Minecraft mc = FMLClientHandler.instance().getClient();

	private static PotionEffect nightVisionEffect = new PotionEffect(MobEffects.NIGHT_VISION, 300, 1, true, false);

	@SubscribeEvent
	public void onRenderTick(RenderTickEvent e) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		if (isWearingGoggles(player)) {
			if (e.phase == Phase.START) {
				player.addPotionEffect(nightVisionEffect);
			} else if (e.phase == Phase.END) {
				if (player != null) {
					PotionEffect eff = player.getActivePotionEffect(MobEffects.NIGHT_VISION);
					if (eff != null && eff.getAmplifier() == 1)
						player.removePotionEffect(MobEffects.NIGHT_VISION);
				}
			}
		}
	}

	public boolean isWearingGoggles(EntityPlayer player) {
		if (player != null && !player.inventory.armorItemInSlot(3).isEmpty() && player.inventory.armorItemInSlot(3).getItem() == ModItems.COMPOUND_GOGGLES)
			return true;
		if (player != null && !player.inventory.armorItemInSlot(3).isEmpty() && player.inventory.armorItemInSlot(3).getItem() == ModItems.REIN_COMPOUND_GOGGLES)
			return true;
		return false;
	}
}
