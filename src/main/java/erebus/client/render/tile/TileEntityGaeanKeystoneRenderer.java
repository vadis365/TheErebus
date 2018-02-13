package erebus.client.render.tile;

import erebus.blocks.BlockGaeanKeystone;
import erebus.client.model.item.ModelPortalActivator;
import erebus.lib.Reference;
import erebus.tileentity.TileEntityGaeanKeystone;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityGaeanKeystoneRenderer extends TileEntitySpecialRenderer<TileEntityGaeanKeystone> {

    private final ModelPortalActivator MODEL_PORTAL_ACTIVATOR = new ModelPortalActivator();
    private final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/special/items/portal_activator.png");

	@Override
	public void render(TileEntityGaeanKeystone tile, double x, double y, double z, float partialTick, int destroyStage, float alpha) {
		if (!BlockGaeanKeystone.isGemActive(tile.getBlockMetadata()))
			return;

		bindTexture(TEXTURE);
		double now = 0;
		if (tile.getWorld() != null)
			now = (tile.getWorld().getTotalWorldTime() % Short.MAX_VALUE) + partialTick;
		GlStateManager.pushMatrix();
		double hover = (Math.sin(now / 40) + 1) / 16;
		float s = 1.25F;
		x += 0.5F;
		z += 0.5F;
		y += s + 12F / 18F + hover;

		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(180F, 1F, 0F, 0F);
		GlStateManager.rotate((float) -now, 0F, 1F, 0F);
		GlStateManager.scale(s, s, s);
		MODEL_PORTAL_ACTIVATOR.render();
		GlStateManager.popMatrix();
	}
}
