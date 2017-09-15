package erebus.client.model.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class ModelWandOfAnimation extends ModelBase {

	private final ModelRenderer Jewel1, Jewel2, Jewel3, TopR1, TopR2, TopR3, TopR4, TopR5, Dec4, Dec3, Dec2, Dec1, Shaft, Pommel1, Pommel2, Pommel3;
	boolean up;

	public ModelWandOfAnimation() {
		textureWidth = 32;
		textureHeight = 64;
		Jewel1 = new ModelRenderer(this, 0, 7);
		Jewel1.addBox(-2F, -7.7F, -2F, 4, 4, 4);
		setRotation(Jewel1, 0F, 0.7853982F, 0F);
		Jewel2 = new ModelRenderer(this, 0, 7);
		Jewel2.addBox(-2F, -6F, 2F, 4, 4, 4, -0.01F);
		setRotation(Jewel2, 0.7853982F, 0F, 0F);
		Jewel3 = new ModelRenderer(this, 0, 7);
		Jewel3.addBox(-6F, -6F, -2F, 4, 4, 4, -0.01F);
		setRotation(Jewel3, 0F, 0F, 0.7853982F);
		TopR1 = new ModelRenderer(this, 5, 0);
		TopR1.addBox(-2F, -4F, -0.5F, 1, 4, 1);
		setRotation(TopR1, 0F, 0F, -0.6981317F);
		TopR2 = new ModelRenderer(this, 10, 0);
		TopR2.addBox(-2F, -6F, -1F, 1, 2, 2);
		setRotation(TopR2, 0F, 0F, -0.6981317F);
		TopR3 = new ModelRenderer(this, 0, 0);
		TopR3.addBox(4F, -8.5F, -0.5F, 1, 5, 1);
		TopR3.setRotationPoint(0F, -4F, 0F);
		setRotation(TopR3, 0F, 3.141593F, 0F);
		TopR4 = new ModelRenderer(this, 17, 0);
		TopR4.addBox(8.3F, -5.3F, -1F, 1, 2, 2);
		setRotation(TopR4, 0F, 3.141593F, 0.6981317F);
		TopR5 = new ModelRenderer(this, 24, 0);
		TopR5.addBox(9F, -8.5F, -0.5F, 1, 5, 1);
		setRotation(TopR5, 0F, 3.141593F, 0.8726646F);
		Dec4 = new ModelRenderer(this, 0, 16);
		Dec4.addBox(-1.5F, 0F, -1.5F, 3, 3, 3);
		setRotation(Dec4, 0F, 0F, 0F);
		Dec3 = new ModelRenderer(this, 0, 23);
		Dec3.addBox(-1F, 3F, -1F, 2, 2, 2);
		setRotation(Dec3, 0F, 0F, 0F);
		Dec2 = new ModelRenderer(this, 0, 28);
		Dec2.addBox(-1.5F, 5F, -1.5F, 3, 1, 3);
		setRotation(Dec2, 0F, 0F, 0F);
		Dec1 = new ModelRenderer(this, 0, 28);
		Dec1.addBox(-1.5F, 5F, -1.5F, 3, 1, 3);
		setRotation(Dec1, 0F, 0.7853982F, 0F);
		Shaft = new ModelRenderer(this, 0, 33);
		Shaft.addBox(-1F, 6F, -1F, 2, 19, 2);
		Shaft.setRotationPoint(0F, -4F, 0F);
		setRotation(Shaft, 0F, 0F, 0F);
		Pommel1 = new ModelRenderer(this, 0, 55);
		Pommel1.addBox(-1.5F, 16.25F, -19.25F, 3, 3, 3);
		setRotation(Pommel1, 0.7853982F, 0F, 0F);
		Pommel2 = new ModelRenderer(this, 0, 55);
		Pommel2.addBox(16.3F, 16.3F, -1.5F, 3, 3, 3);
		setRotation(Pommel2, 0F, 0F, 0.7853982F);
		Pommel3 = new ModelRenderer(this, 0, 55);
		Pommel3.addBox(-1.5F, 23.5F, -1.5F, 3, 3, 3);
		setRotation(Pommel3, 0F, 0.7853982F, 0F);
		Jewel1.addChild(Jewel2);
		Jewel1.addChild(Jewel3);
		Shaft.addChild(Dec4);
		Shaft.addChild(Dec3);
		Shaft.addChild(Dec2);
		Shaft.addChild(Dec1);
		Shaft.addChild(Pommel1);
		Shaft.addChild(Pommel2);
		Shaft.addChild(Pommel3);
		TopR3.addChild(TopR1);
		TopR3.addChild(TopR2);
		TopR3.addChild(TopR4);
		TopR3.addChild(TopR5);
	}

	public void render(float unitPixel) {
		float tick = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);
		// System.out.println(up);
		if (tick <= 360)
			up = true;
		if (tick >= 361)
			up = false;

		Shaft.render(unitPixel);
		GL11.glRotatef(tick, 0.0F, 1.0F, 0.0F);
		TopR3.render(unitPixel);
		GL11.glPushMatrix();
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		TopR3.render(unitPixel);
		GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
		TopR3.render(unitPixel);
		GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
		TopR3.render(unitPixel);

		GL11.glTranslatef(0f, -0.29f + (up ? tick / 360 : 1 + 1 - tick / 360) / 10f, 0f);

		GL11.glRotatef(-tick * 2, 0.0F, 1.0F, 0.0F);
		Jewel1.render(unitPixel);
		GL11.glPopMatrix();
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}