package erebus.client.render.tile;

import erebus.blocks.BlockGaeanKeystone;
import erebus.tileentity.TileEntityGaeanKeystone;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityGaeanKeystoneRenderer extends TileEntitySpecialRenderer<TileEntityGaeanKeystone> {
	private static final ResourceLocation beamTexture = new ResourceLocation("textures/entity/beacon_beam.png");

	@Override
	public void render(TileEntityGaeanKeystone tile, double x, double y, double z, float partialTick, int destroyStage, float alpha) {
		if (!BlockGaeanKeystone.isGemActive(tile.getBlockMetadata()))
			return;

		GlStateManager.alphaFunc(516, 0.1F);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
		bindTexture(beamTexture);
        GlStateManager.glTexParameteri(3553, 10242, 10497);
        GlStateManager.glTexParameteri(3553, 10243, 10497);
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		float f2 = tile.getWorld().getTotalWorldTime() + partialTick;
		float f3 = -f2 * 0.2F - MathHelper.floor(-f2 * 0.1F);
		byte b0 = 1;
		double d3 = f2 * 0.025D * (1.0D - (b0 & 1) * 2.5D);
		double d5 = b0 * 0.2D;
		double d7 = 0.5D + Math.cos(d3 + 2.356194490192345D) * d5;
		double d9 = 0.5D + Math.sin(d3 + 2.356194490192345D) * d5;
		double d11 = 0.5D + Math.cos(d3 + Math.PI / 4D) * d5;
		double d13 = 0.5D + Math.sin(d3 + Math.PI / 4D) * d5;
		double d15 = 0.5D + Math.cos(d3 + 3.9269908169872414D) * d5;
		double d17 = 0.5D + Math.sin(d3 + 3.9269908169872414D) * d5;
		double d19 = 0.5D + Math.cos(d3 + 5.497787143782138D) * d5;
		double d21 = 0.5D + Math.sin(d3 + 5.497787143782138D) * d5;
		double d23 = 256.0F;
		double d25 = 0.0D;
		double d27 = 1.0D;
		double d28 = -1.0F + f3;
		double d29 = 2.0F * (0.5D / d5) + d28;
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
		vertexbuffer.pos(x + d7, y + d23, z + d9).tex(d27, d29).color(0.5F, 1F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d7, y, z + d9).tex(d27, d28).color(0.5F, 1F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d11, y, z + d13).tex(d25, d28).color(0.5F, 1F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d11, y + d23, z + d13).tex(d25, d29).color(0.5F, 1F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d19, y + d23, z + d21).tex(d27, d29).color(0.5F, 1F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d19, y, z + d21).tex(d27, d28).color(0.5F, 1F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d15, y, z + d17).tex(d25, d28).color(0.5F, 1F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d15, y + d23, z + d17).tex(d25, d29).color(0.5F, 1F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d11, y + d23, z + d13).tex(d27, d29).color(0.5F, 1F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d11, y, z + d13).tex(d27, d28).color(0.5F, 1F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d19, y, z + d21).tex(d25, d28).color(0.5F, 1F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d19, y + d23, z + d21).tex(d25, d29).color(0.5F, 1F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d15, y + d23, z + d17).tex(d27, d29).color(0.5F, 1F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d15, y, z + d17).tex(d27, d28).color(0.5F, 1F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d7, y, z + d9).tex(d25, d28).color(0.5F, 1F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d7, y + d23, z + d9).tex(d25, d29).color(0.5F, 1F, 0.5F, 0.2F).endVertex();
		tessellator.draw();
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.depthMask(false);
		double d30 = 0.2D;
		double d4 = 0.2D;
		double d6 = 0.8D;
		double d8 = 0.2D;
		double d10 = 0.2D;
		double d12 = 0.8D;
		double d14 = 0.8D;
		double d16 = 0.8D;
		double d18 = 256.0F;
		double d20 = 0.0D;
		double d22 = 1.0D;
		double d24 = -1.0F + f3;
		double d26 = 2.0F + d24;
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
		vertexbuffer.pos(x + d30, y + d18, z + d4).tex(d22, d26).color(0.5F, 0.5F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d30, y, z + d4).tex(d22, d24).color(0.5F, 0.5F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d6, y, z + d8).tex(d20, d24).color(0.5F, 0.5F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d6, y + d18, z + d8).tex(d20, d26).color(0.5F, 0.5F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d14, y + d18, z + d16).tex(d22, d26).color(0.5F, 0.5F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d14, y, z + d16).tex(d22, d24).color(0.5F, 0.5F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d10, y, z + d12).tex(d20, d24).color(0.5F, 0.5F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d10, y + d18, z + d12).tex(d20, d26).color(0.5F, 0.5F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d6, y + d18, z + d8).tex(d22, d26).color(0.5F, 0.5F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d6, y, z + d8).tex(d22, d24).color(0.5F, 0.5F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d14, y, z + d16).tex(d20, d24).color(0.5F, 0.5F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d14, y + d18, z + d16).tex(d20, d26).color(0.5F, 0.5F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d10, y + d18, z + d12).tex(d22, d26).color(0.5F, 0.5F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d10, y, z + d12).tex(d22, d24).color(0.5F, 0.5F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d30, y, z + d4).tex(d20, d24).color(0.5F, 0.5F, 0.5F, 0.2F).endVertex();
		vertexbuffer.pos(x + d30, y + d18, z + d4).tex(d20, d26).color(0.5F, 0.5F, 0.5F, 0.2F).endVertex();
		tessellator.draw();
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.depthMask(true);
	}
}
