package erebus.client.gui;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAntiVenomBar extends Gui {
	public ResourceLocation ANTI_VENOM_TEXTURE = new ResourceLocation("erebus:textures/gui/overlay/anti_venom_bar.png");
	public Minecraft mc = Minecraft.getMinecraft();
	public Random random = new Random();
	
	@SubscribeEvent
	public void renderGui(RenderGameOverlayEvent.Post event) {

		if (!mc.player.capabilities.isCreativeMode) {
			int startX = event.getResolution().getScaledWidth() / 2 + 82;
			int startY = event.getResolution().getScaledHeight() - 51;
			if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
				mc.getTextureManager().bindTexture(ANTI_VENOM_TEXTURE);

				for (int i = 0; i < 10; i++) {
					int offsetY = mc.player.isInsideOfMaterial(Material.WATER) ? -10 : 0;

					if (mc.player.getEntityData().hasKey("anti_venom_duration")) {
						int duration = mc.player.getEntityData().getInteger("anti_venom_duration") / 9;
						GlStateManager.enableBlend();
						GlStateManager.enableAlpha();
						GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
						GlStateManager.color(1F, 1F, 1F, 1F);
						if (duration <= 3 && mc.world.getTotalWorldTime() % 60 == 0)
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
