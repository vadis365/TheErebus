package erebus.client.render.entity;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityDragonfly;
import erebus.entity.EntityScorpion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

@SideOnly(Side.CLIENT)
public class MobGrabbingHealthBarRemoval {

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