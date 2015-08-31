package erebus.client.render.entity;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.entity.EntityDragonfly;
import erebus.entity.EntityScorpion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

@SideOnly(Side.CLIENT)
public class MobGrabbingHealthBarRemoval extends Gui {

	@SubscribeEvent
	public void onRenderHUD(Pre event) {
		if (event.type.equals(RenderGameOverlayEvent.ElementType.HEALTHMOUNT)) {
			EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
			if (player != null && player.ridingEntity != null)
				if (player.ridingEntity instanceof EntityScorpion || player.ridingEntity instanceof EntityDragonfly)
					event.setCanceled(true);
		}
	}
}