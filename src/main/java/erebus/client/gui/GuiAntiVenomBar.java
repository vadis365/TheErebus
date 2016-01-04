package erebus.client.gui;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAntiVenomBar extends Gui {
	public ResourceLocation antiVenomTexture = new ResourceLocation("erebus:textures/gui/overlay/antiVenomBar.png");
	public Minecraft mc = Minecraft.getMinecraft();
	public Random random = new Random();
	
	@SubscribeEvent
	public void renderGui(RenderGameOverlayEvent.Post event) {

		if (!mc.thePlayer.capabilities.isCreativeMode) {
			int startX = event.resolution.getScaledWidth() / 2 + 82;
			int startY = event.resolution.getScaledHeight() - 51;
			if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR) {
				mc.getTextureManager().bindTexture(antiVenomTexture);

				for (int i = 0; i < 10; i++) {
					int offsetY = mc.thePlayer.isInsideOfMaterial(Material.water) ? -10 : 0;

					if (mc.thePlayer.getEntityData().hasKey("antivenomDuration")) {
						int duration = mc.thePlayer.getEntityData().getInteger("antivenomDuration") / 9;
						GL11.glEnable(GL11.GL_BLEND);
						GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
						GL11.glColor4f(1, 1, 1, 1);
						if (duration <= 3 && mc.theWorld.getTotalWorldTime() % 60 == 0)
							offsetY += random.nextInt(3) - 1;
						if (i * 2 + 1 < duration)
							drawTexturedModalRect(startX - i * 8, startY + offsetY, 0, 0, 9, 11);
						if (i * 2 + 1 == duration)
							drawTexturedModalRect(startX - i * 8, startY + offsetY, 9, 0, 9, 11);

					}
				}
			}
		}
	}
}
