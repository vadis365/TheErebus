package erebus.events;

import erebus.registries.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderFrameEvent;

	public class GogglesClientTickHandler {
	
	private static MobEffectInstance NIGHT_VISION_EFFECT = new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 1, true, false, false);

	@SuppressWarnings("resource")
	@SubscribeEvent
	public void onRenderTickPre(RenderFrameEvent.Pre event) {
		Player player = Minecraft.getInstance().player;
		if (player != null && isWearingGoggles(player))
			player.addEffect(NIGHT_VISION_EFFECT);
	}

	@SuppressWarnings("resource")
	@SubscribeEvent
	public void onRenderTickPost(RenderFrameEvent.Post event) {
		Player player = Minecraft.getInstance().player;
		if (player != null && isWearingGoggles(player)) {
			if (player.hasEffect(MobEffects.NIGHT_VISION)) {
				MobEffectInstance eff = player.getEffect(MobEffects.NIGHT_VISION);
				if (eff.getAmplifier() == 1)
					player.removeEffect(MobEffects.NIGHT_VISION);
			}
		}
	}

	public boolean isWearingGoggles(Player player) {
		return player != null && !player.getItemBySlot(EquipmentSlot.HEAD).isEmpty() && (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.REIN_COMPOUND_GOGGLES.get() || player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.COMPOUND_GOGGLES.get());
	}
}
