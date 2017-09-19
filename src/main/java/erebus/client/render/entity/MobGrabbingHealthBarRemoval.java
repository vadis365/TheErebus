package erebus.client.render.entity;

import erebus.entity.EntityDragonfly;
import erebus.entity.EntityScorpion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MobGrabbingHealthBarRemoval {

	@SubscribeEvent
	public void onRenderHUD(Pre event) {
		if (event.getType().equals(RenderGameOverlayEvent.ElementType.HEALTHMOUNT)) {
			EntityPlayerSP player = Minecraft.getMinecraft().player;
			if (player != null && player.isRiding())
				if (player.getRidingEntity() instanceof EntityScorpion || player.getRidingEntity() instanceof EntityDragonfly)
					event.setCanceled(true);
		}
	}
}