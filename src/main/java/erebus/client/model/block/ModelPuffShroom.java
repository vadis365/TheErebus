package erebus.client.model.block;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.tileentity.TileEntityPuffShroom;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)

public class ModelPuffShroom extends ModelBase {

	ModelRenderer base;
	ModelRenderer block;
	ModelRenderer stalk;
	ModelRenderer cap1;
	ModelRenderer cap2;
	ModelRenderer topBack;
	ModelRenderer topFront;
	ModelRenderer topRight;
	ModelRenderer topLeft;

	public ModelPuffShroom() {
		textureWidth = 64;
		textureHeight = 64;
		cap1 = new ModelRenderer(this, 24, 10);
		cap1.setRotationPoint(0.0F, 0.0F, 0.0F);
		cap1.addBox(-5.0F, -5.9F, -5.0F, 10, 3, 10, 0.0F);
		cap2 = new ModelRenderer(this, 0, 0);
		cap2.setRotationPoint(0.0F, 0.0F, 0.0F);
		cap2.addBox(-4.0F, -6.9F, -4.0F, 8, 1, 8, 0.0F);
		block = new ModelRenderer(this, 0, 32);
		block.setRotationPoint(0.0F, 24.0F, 0.0F);
		block.addBox(-8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F);
		topBack = new ModelRenderer(this, 47, 0);
		topBack.setRotationPoint(0.0F, 0.0F, 0.0F);
		topBack.addBox(-1.0F, -7.9F, 1.0F, 2, 1, 1, 0.0F);
		topFront = new ModelRenderer(this, 47, 6);
		topFront.setRotationPoint(0.0F, 0.0F, 0.0F);
		topFront.addBox(-1.0F, -7.9F, -2.0F, 2, 1, 1, 0.0F);
		base = new ModelRenderer(this, 0, 24);
		base.setRotationPoint(0.0F, 16.0F, 0.0F);
		base.addBox(-3.0F, -0.1F, -3.0F, 6, 1, 6, 0.0F);
		topLeft = new ModelRenderer(this, 36, 0);
		topLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		topLeft.addBox(1.0F, -7.9F, -2.0F, 1, 1, 4, 0.0F);
		stalk = new ModelRenderer(this, 4, 16);
		stalk.setRotationPoint(0.0F, 0.0F, 0.0F);
		stalk.addBox(-2.0F, -2.9F, -2.0F, 4, 3, 4, 0.0F);
		topRight = new ModelRenderer(this, 54, 0);
		topRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		topRight.addBox(-2.0F, -7.9F, -2.0F, 1, 1, 4, 0.0F);
		base.addChild(cap1);
		base.addChild(cap2);
		base.addChild(topBack);
		base.addChild(topFront);
		base.addChild(topLeft);
		base.addChild(stalk);
		base.addChild(topRight);
	}

	public void render(TileEntityPuffShroom tile, float partialTickTime) {
		block.render(0.0625F);
		float animationTicks = tile.animationTicks;
		float prevAnimationTicks = tile.prevAnimationTicks;
		float interAnimationTicks = animationTicks + (animationTicks - prevAnimationTicks) * partialTickTime;

		GL11.glPushMatrix();
		if (interAnimationTicks <= 8)
			GL11.glTranslatef(0F, 0F - 1F / 8 * interAnimationTicks * 0.5F, 0F);
		if (interAnimationTicks > 8)
			GL11.glTranslatef(0F, 0F - 0.5F, 0F);
		if (interAnimationTicks > 1) {
			if (interAnimationTicks > 8 && interAnimationTicks < 12)
				GL11.glScalef(1 + 1F / 8 * interAnimationTicks * 0.25F, 1F, 1 + 1F / 8 * interAnimationTicks * 0.25F);
			if (interAnimationTicks >= 12)
				GL11.glScalef(1 - 1F / 8 * interAnimationTicks * 0.25F, 1F, 1 - 1F / 8 * interAnimationTicks * 0.25F);
			base.render(0.0625F);
		}
		GL11.glPopMatrix();
	}
}