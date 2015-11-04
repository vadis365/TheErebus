package erebus.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import erebus.block.GaeanKeystone;
import erebus.client.model.item.ModelPortalStaff;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityGaeanKeystoneRenderer extends TileEntitySpecialRenderer {

	private static final ModelPortalStaff model = new ModelPortalStaff();
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/special/items/portalStaff.png");

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick) {
		if (!GaeanKeystone.isGemActive(tile.getBlockMetadata()))
			return;

		final Minecraft mc = Minecraft.getMinecraft();
		mc.getTextureManager().bindTexture(texture);
		double now = 0;
		if (mc.theWorld != null)
			now = (mc.theWorld.getTotalWorldTime() % Short.MAX_VALUE) + partialTick;
		GL11.glPushMatrix();
		double hover = (Math.sin(now / 40) + 1) / 16;
		float s = 1.25F;
		x += 0.5F;
		z += 0.5F;
		y += s + 12F / 18F + hover;

		GL11.glTranslated(x, y, z);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef((float) -now, 0, 1, 0);
		GL11.glScalef(s, s, s);
		model.render();
		GL11.glPopMatrix();
	}
}