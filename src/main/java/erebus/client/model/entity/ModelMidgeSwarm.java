package erebus.client.model.entity;

import org.lwjgl.opengl.GL11;

import erebus.entity.EntityMidgeSwarm;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMidgeSwarm extends ModelBase {
	ModelRenderer LFLeg;
	ModelRenderer LFLeg2;
	ModelRenderer LMLeg;
	ModelRenderer LMLeg2;
	ModelRenderer LBLeg;
	ModelRenderer LBLeg2;
	ModelRenderer RBLeg;
	ModelRenderer RBLeg2;
	ModelRenderer RMLeg;
	ModelRenderer RMLeg2;
	ModelRenderer RFLeg;
	ModelRenderer RFLeg2;
	ModelRenderer Proboscis;
	ModelRenderer Head;
	ModelRenderer Thorax_Front;
	ModelRenderer Thorax_Mid;
	ModelRenderer Thorax_Back;
	ModelRenderer AB1;
	ModelRenderer AB2;
	ModelRenderer AB3;
	ModelRenderer AB4;
	ModelRenderer WingL;
	ModelRenderer WingL2;
	ModelRenderer WingL3;
	ModelRenderer WingL4;
	ModelRenderer WingR;
	ModelRenderer WingR2;
	ModelRenderer WingR3;
	ModelRenderer WingR4;
	ModelRenderer MidWing;

	public ModelMidgeSwarm() {
		textureWidth = 64;
		textureHeight = 32;

		LFLeg = new ModelRenderer(this, 0, 10);
		LFLeg.addBox(-0.5F, 0F, -0.5F, 1, 7, 1);
		LFLeg.setRotationPoint(4.5F, 17F, -4.5F);
		setRotation(LFLeg, 0F, 0F, 0F);
		LFLeg2 = new ModelRenderer(this, 0, 0);
		LFLeg2.addBox(0F, -0.4666667F, -1F, 4, 1, 1);
		LFLeg2.setRotationPoint(2F, 20F, -4F);
		setRotation(LFLeg2, 0F, 0F, -0.9599311F);
		LMLeg = new ModelRenderer(this, 0, 10);
		LMLeg.addBox(-0.5F, 0F, -0.5F, 1, 7, 1);
		LMLeg.setRotationPoint(4.5F, 17F, -3F);
		setRotation(LMLeg, 0F, 0F, 0F);
		LMLeg2 = new ModelRenderer(this, 0, 0);
		LMLeg2.addBox(0F, -0.4666667F, -0.5F, 4, 1, 1);
		LMLeg2.setRotationPoint(2F, 20F, -3F);
		setRotation(LMLeg2, 0F, 0F, -0.9599311F);
		LBLeg = new ModelRenderer(this, 0, 10);
		LBLeg.addBox(-0.5F, 0F, -0.5F, 1, 7, 1);
		LBLeg.setRotationPoint(4.5F, 17F, -1.5F);
		setRotation(LBLeg, 0F, 0F, 0F);
		LBLeg2 = new ModelRenderer(this, 0, 0);
		LBLeg2.addBox(0F, -0.4666667F, -0.5F, 4, 1, 1);
		LBLeg2.setRotationPoint(2F, 20F, -1.5F);
		setRotation(LBLeg2, 0F, 0F, -0.9599311F);
		RBLeg = new ModelRenderer(this, 0, 10);
		RBLeg.addBox(-0.5F, 0F, -0.5F, 1, 7, 1);
		RBLeg.setRotationPoint(-4.5F, 17F, -1.5F);
		setRotation(RBLeg, 0F, 0F, 0F);
		RBLeg2 = new ModelRenderer(this, 0, 0);
		RBLeg2.addBox(-4F, -0.4666667F, -0.5F, 4, 1, 1);
		RBLeg2.setRotationPoint(-2F, 20F, -1.5F);
		setRotation(RBLeg2, 0F, 0F, 0.9599311F);
		RMLeg = new ModelRenderer(this, 0, 10);
		RMLeg.addBox(-0.5F, 0F, -0.5F, 1, 7, 1);
		RMLeg.setRotationPoint(-4.5F, 17F, -3F);
		setRotation(RMLeg, 0F, 0F, 0F);
		RMLeg2 = new ModelRenderer(this, 0, 0);
		RMLeg2.addBox(-4F, -0.5F, -0.5F, 4, 1, 1);
		RMLeg2.setRotationPoint(-2F, 20F, -3F);
		setRotation(RMLeg2, 0F, 0F, 0.9599311F);
		RFLeg = new ModelRenderer(this, 0, 10);
		RFLeg.addBox(-0.5F, 0F, -0.5F, 1, 7, 1);
		RFLeg.setRotationPoint(-4.5F, 17F, -4.5F);
		setRotation(RFLeg, 0F, 0F, 0F);
		RFLeg2 = new ModelRenderer(this, 0, 0);
		RFLeg2.addBox(-4F, -0.4666667F, -1F, 4, 1, 1);
		RFLeg2.setRotationPoint(-2F, 20F, -4F);
		setRotation(RFLeg2, 0F, 0F, 0.9599311F);
		Proboscis = new ModelRenderer(this, 21, 0);
		Proboscis.addBox(-0.5F, 0F, -1F, 1, 4, 1);
		Proboscis.setRotationPoint(0F, 19F, -6F);
		setRotation(Proboscis, -0.2443461F, 0F, 0F);
		Head = new ModelRenderer(this, 26, 0);
		Head.addBox(-1.5F, -1F, -2F, 3, 2, 2);
		Head.setRotationPoint(0F, 19F, -5F);
		setRotation(Head, 0F, 0F, 0F);
		Thorax_Front = new ModelRenderer(this, 24, 5);
		Thorax_Front.addBox(-2F, -2F, 0F, 4, 3, 3);
		Thorax_Front.setRotationPoint(0F, 19F, -5F);
		setRotation(Thorax_Front, 0F, 0F, 0F);
		Thorax_Mid = new ModelRenderer(this, 24, 12);
		Thorax_Mid.addBox(-2F, -3F, 1F, 4, 3, 3);
		Thorax_Mid.setRotationPoint(0F, 19F, -5F);
		setRotation(Thorax_Mid, 0F, 0F, 0F);
		Thorax_Back = new ModelRenderer(this, 30, 19);
		Thorax_Back.addBox(-0.5F, -2F, 4F, 1, 1, 1);
		Thorax_Back.setRotationPoint(0F, 19F, -5F);
		setRotation(Thorax_Back, 0F, 0F, 0F);
		AB1 = new ModelRenderer(this, 43, 17);
		AB1.addBox(-1.5F, -1F, 0F, 3, 3, 2);
		AB1.setRotationPoint(0F, 17F, 0F);
		setRotation(AB1, 0F, 0F, 0F);
		AB2 = new ModelRenderer(this, 42, 11);
		AB2.addBox(-2F, 0F, 2F, 4, 3, 2);
		AB2.setRotationPoint(0F, 17F, 0F);
		setRotation(AB2, 0F, 0F, 0F);
		AB3 = new ModelRenderer(this, 43, 5);
		AB3.addBox(-1.5F, 1F, 4F, 3, 3, 2);
		AB3.setRotationPoint(0F, 17F, 0F);
		setRotation(AB3, 0F, 0F, 0F);
		AB4 = new ModelRenderer(this, 43, 0);
		AB4.addBox(-1.5F, 3F, 6F, 3, 2, 2);
		AB4.setRotationPoint(0F, 17F, 0F);
		setRotation(AB4, 0F, 0F, 0F);
		WingL = new ModelRenderer(this, 0, 20);
		WingL.addBox(-3F, -3.5F, 2F, 2, 0, 2);
		WingL.setRotationPoint(0F, 19F, -5F);
		setRotation(WingL, 0F, 0F, 0F);
		WingL2 = new ModelRenderer(this, 0, 24);
		WingL2.addBox(-5F, -3.5F, 3F, 3, 0, 2);
		WingL2.setRotationPoint(0F, 19F, -5F);
		setRotation(WingL2, 0F, 0F, 0F);
		WingL3 = new ModelRenderer(this, 0, 27);
		WingL3.addBox(-6F, -3.5F, 4F, 3, 0, 4);
		WingL3.setRotationPoint(0F, 19F, -5F);
		setRotation(WingL3, 0F, 0F, 0F);
		WingL4 = new ModelRenderer(this, 6, 10);
		WingL4.addBox(-8F, -3.5F, 7F, 3, 0, 5);
		WingL4.setRotationPoint(0F, 19F, -5F);
		setRotation(WingL4, 0F, 0F, 0F);
		WingR = new ModelRenderer(this, 0, 20);
		WingR.addBox(1F, -3.5F, 2F, 2, 0, 2);
		WingR.setRotationPoint(0F, 19F, -5F);
		setRotation(WingR, 0F, 0F, 0F);
		WingR2 = new ModelRenderer(this, 0, 24);
		WingR2.addBox(2F, -3.5F, 3F, 3, 0, 2);
		WingR2.setRotationPoint(0F, 19F, -5F);
		setRotation(WingR2, 0F, 0F, 0F);
		WingR3 = new ModelRenderer(this, 0, 27);
		WingR3.addBox(3F, -3.5F, 4F, 3, 0, 4);
		WingR3.setRotationPoint(0F, 19F, -5F);
		setRotation(WingR3, 0F, 0F, 0F);
		WingR4 = new ModelRenderer(this, 6, 10);
		WingR4.addBox(5F, -3.5F, 7F, 3, 0, 5);
		WingR4.setRotationPoint(0F, 19F, -5F);
		setRotation(WingR4, 0F, 0F, 0F);
		MidWing = new ModelRenderer(this, 12, 0);
		MidWing.addBox(-1F, -4F, 2F, 2, 1, 1);
		MidWing.setRotationPoint(0F, 19F, -5F);
		setRotation(MidWing, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.render(entity, limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		setRotationAngles(limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		EntityMidgeSwarm midge = (EntityMidgeSwarm) entity;

		if (midge.getHealth() > 0) {
			GL11.glPushMatrix();
			GL11.glTranslated(0F + -midge.wingFloat, -1F + midge.wingFloat, 0F - midge.wingFloat);
			renderMultiBits(unitPixel);
			GL11.glPopMatrix();
		}

		if (midge.getHealth() > 3) {
			GL11.glPushMatrix();
			GL11.glTranslated(0.5F + midge.wingFloat, -1.5F + midge.wingFloat, 0.5F + midge.wingFloat);
			renderMultiBits(unitPixel);
			GL11.glPopMatrix();
		}

		if (midge.getHealth() > 6) {
			GL11.glPushMatrix();
			GL11.glTranslated(-0.5F - midge.wingFloat, 0F - midge.wingFloat, -0.5F - midge.wingFloat);
			renderMultiBits(unitPixel);
			GL11.glPopMatrix();
		}

		if (midge.getHealth() > 9) {
			GL11.glPushMatrix();
			GL11.glTranslated(0.5F + midge.wingFloat, 0F + midge.wingFloat, -0.5F + midge.wingFloat);
			renderMultiBits(unitPixel);
			GL11.glPopMatrix();
		}

		if (midge.getHealth() > 12) {
			GL11.glPushMatrix();
			GL11.glTranslated(-0.5F - midge.wingFloat, -1.5D - midge.wingFloat, -0.5F - midge.wingFloat);
			renderMultiBits(unitPixel);
			GL11.glPopMatrix();
		}
	}

	private void renderMultiBits(float unitPixel) {
		LFLeg.render(unitPixel);
		LFLeg2.render(unitPixel);
		LMLeg.render(unitPixel);
		LMLeg2.render(unitPixel);
		LBLeg.render(unitPixel);
		LBLeg2.render(unitPixel);
		RBLeg.render(unitPixel);
		RBLeg2.render(unitPixel);
		RMLeg.render(unitPixel);
		RMLeg2.render(unitPixel);
		RFLeg.render(unitPixel);
		RFLeg2.render(unitPixel);
		Proboscis.render(unitPixel);
		Head.render(unitPixel);
		Thorax_Front.render(unitPixel);
		Thorax_Mid.render(unitPixel);
		Thorax_Back.render(unitPixel);
		AB1.render(unitPixel);
		AB2.render(unitPixel);
		AB3.render(unitPixel);
		AB4.render(unitPixel);
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		WingL.render(unitPixel);
		WingL2.render(unitPixel);
		WingL3.render(unitPixel);
		WingL4.render(unitPixel);
		WingR.render(unitPixel);
		WingR2.render(unitPixel);
		WingR3.render(unitPixel);
		WingR4.render(unitPixel);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
		MidWing.render(unitPixel);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		EntityMidgeSwarm midge = (EntityMidgeSwarm) entity;
		WingL.rotateAngleX = midge.wingFloat;
		WingL2.rotateAngleX = midge.wingFloat;
		WingL3.rotateAngleX = midge.wingFloat;
		WingL4.rotateAngleX = midge.wingFloat;
		WingR.rotateAngleX = midge.wingFloat;
		WingR2.rotateAngleX = midge.wingFloat;
		WingR3.rotateAngleX = midge.wingFloat;
		WingR4.rotateAngleX = midge.wingFloat;
	}
}
