package erebus.client.model.block;

import erebus.tileentity.TileEntityLiquifier;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelLiquifier extends ModelBase {
	ModelRenderer tank;
	ModelRenderer lid_mid;
	ModelRenderer lid_bottom;
	ModelRenderer feed_pipe;
	ModelRenderer crossbar_left;
	ModelRenderer crossbar_right;
	ModelRenderer paddle_left;
	ModelRenderer paddle_right;

	public ModelLiquifier() {
		textureWidth = 128;
		textureHeight = 64;
		paddle_left = new ModelRenderer(this, 0, 8);
		paddle_left.setRotationPoint(0.0F, 0.0F, 0.0F);
		paddle_left.addBox(-6.5F, 0.0F, -0.5F, 3, 7, 1, 0.0F);
		crossbar_left = new ModelRenderer(this, 48, 5);
		crossbar_left.setRotationPoint(0.0F, 0.0F, 0.0F);
		crossbar_left.addBox(-6.0F, -1.0F, -1.0F, 4, 1, 2, 0.0F);
		feed_pipe = new ModelRenderer(this, 0, 0);
		feed_pipe.setRotationPoint(0.0F, 16.0F, 0.0F);
		feed_pipe.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F);
		crossbar_right = new ModelRenderer(this, 108, 5);
		crossbar_right.setRotationPoint(0.0F, 0.0F, 0.0F);
		crossbar_right.addBox(2.0F, -1.0F, -1.0F, 4, 1, 2, 0.0F);
		tank = new ModelRenderer(this, 0, 0);
		tank.setRotationPoint(0.0F, 16.0F, 0.0F);
		tank.addBox(-8.0F, -3.0F, -8.0F, 16, 11, 16, 0.0F);
		lid_bottom = new ModelRenderer(this, 84, 0);
		lid_bottom.setRotationPoint(0.0F, 16.0F, 0.0F);
		lid_bottom.addBox(-3.0F, -4.0F, -3.0F, 6, 2, 6, 0.0F);
		lid_mid = new ModelRenderer(this, 48, 0);
		lid_mid.setRotationPoint(0.0F, 16.0F, 0.0F);
		lid_mid.addBox(-6.0F, -6.0F, -6.0F, 12, 2, 12, 0.0F);
		paddle_right = new ModelRenderer(this, 119, 7);
		paddle_right.setRotationPoint(0.0F, 0.0F, 0.0F);
		paddle_right.addBox(3.5F, 0.0F, -0.5F, 3, 7, 1, 0.0F);
		feed_pipe.addChild(paddle_left);
		feed_pipe.addChild(crossbar_left);
		feed_pipe.addChild(crossbar_right);
		feed_pipe.addChild(paddle_right);
	}

	public void renderTank() {
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(0.8F, 0.8F, 0.8F, 0.8F);
		tank.render(0.0625F);
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}

	public void renderBlades() {
		feed_pipe.render(0.0625F);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void renderLidAnimated(TileEntityLiquifier tile, float partialTick) {
		float size = 1F / 360F * tile.animationTicks;
		float prevSize = 1F / 360F * tile.prevAnimationTicks;
		float interAnimationSize = prevSize + (size - prevSize) * partialTick;
		GlStateManager.pushMatrix();
		if (interAnimationSize <= 0.5F)
			GlStateManager.scale(1F - interAnimationSize * 0.5F, 1F, 1F - interAnimationSize * 0.5F);
		if (interAnimationSize > 0.5F)
			GlStateManager.scale(0.5F + interAnimationSize * 0.5F, 1F, 0.5F + interAnimationSize * 0.5F);
		lid_mid.render(0.0625F);
		lid_bottom.render(0.0625F);
		GlStateManager.popMatrix();
	}
}
