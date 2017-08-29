package erebus.client.model.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityCicada;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelCicada extends ModelBase {

	ModelRenderer LEye;
	ModelRenderer REye;
	ModelRenderer HeadL;
	ModelRenderer HeadR;
	ModelRenderer HeadFront;
	ModelRenderer HeadMain;
	ModelRenderer HeadTop;
	ModelRenderer HeadBack;
	ModelRenderer ThoraxTop;
	ModelRenderer AbMain;
	ModelRenderer AbBack1;
	ModelRenderer AbBack2;
	ModelRenderer LBLeg1;
	ModelRenderer LBLeg2;
	ModelRenderer LBLeg3;
	ModelRenderer LBLeg4;
	ModelRenderer RBLeg1;
	ModelRenderer RBLeg2;
	ModelRenderer RBLeg3;
	ModelRenderer RBLeg4;
	ModelRenderer LFLeg1;
	ModelRenderer LFLeg2;
	ModelRenderer LFLeg3;
	ModelRenderer LFLeg4;
	ModelRenderer RFLeg1;
	ModelRenderer RFLeg2;
	ModelRenderer RFLeg3;
	ModelRenderer RFLeg4;
	ModelRenderer LMLeg1;
	ModelRenderer LMLeg2;
	ModelRenderer LMLeg3;
	ModelRenderer LMLeg4;
	ModelRenderer RMLeg1;
	ModelRenderer RMLeg2;
	ModelRenderer RMLeg3;
	ModelRenderer RMLeg4;
	ModelRenderer RWingFront;
	ModelRenderer RWingTop;
	ModelRenderer RWingMain;
	ModelRenderer RWingBack;
	ModelRenderer LWingFront;
	ModelRenderer LWingTop;
	ModelRenderer LWingMain;
	ModelRenderer LWingBack;

	public ModelCicada() {
		textureWidth = 64;
		textureHeight = 128;

		LEye = new ModelRenderer(this, 0, 0);
		LEye.addBox(4F, -3.5F, -6.5F, 2, 3, 3);
		LEye.setRotationPoint(0F, 19F, -7F);
		setRotation(LEye, 0F, 0F, -0.2617994F);
		REye = new ModelRenderer(this, 54, 0);
		REye.addBox(-6F, -3.5F, -6.5F, 2, 3, 3);
		REye.setRotationPoint(0F, 19F, -7F);
		setRotation(REye, 0F, 0F, 0.2617994F);
		HeadL = new ModelRenderer(this, 11, 0);
		HeadL.addBox(2F, -3.5F, -7F, 2, 4, 4);
		HeadL.setRotationPoint(0F, 19F, -7F);
		setRotation(HeadL, 0F, 0F, -0.2617994F);
		HeadR = new ModelRenderer(this, 41, 0);
		HeadR.addBox(-4F, -3.5F, -7F, 2, 4, 4);
		HeadR.setRotationPoint(0F, 19F, -7F);
		setRotation(HeadR, 0F, 0F, 0.2617994F);
		HeadFront = new ModelRenderer(this, 24, 0);
		HeadFront.addBox(-6F, -4F, -6F, 4, 3, 4);
		HeadFront.setRotationPoint(0F, 19F, -7F);
		setRotation(HeadFront, 0.2617994F, -0.7853982F, -0.2150F);
		HeadMain = new ModelRenderer(this, 23, 9);
		HeadMain.addBox(-3F, -4F, -7F, 6, 5, 3);
		HeadMain.setRotationPoint(0F, 19F, -7F);
		setRotation(HeadMain, 0F, 0F, 0F);
		HeadTop = new ModelRenderer(this, 23, 18);
		HeadTop.addBox(-3.5F, -5F, -3F, 7, 1, 3);
		HeadTop.setRotationPoint(0F, 19F, -7F);
		setRotation(HeadTop, 0F, 0F, 0F);
		HeadBack = new ModelRenderer(this, 20, 23);
		HeadBack.addBox(-4.5F, -4F, -4F, 9, 5, 4);
		HeadBack.setRotationPoint(0F, 19F, -7F);
		setRotation(HeadBack, 0F, 0F, 0F);
		ThoraxTop = new ModelRenderer(this, 20, 33);
		ThoraxTop.addBox(-4F, -6F, 0F, 8, 2, 5);
		ThoraxTop.setRotationPoint(0F, 19F, -7F);
		setRotation(ThoraxTop, 0F, 0F, 0F);
		AbMain = new ModelRenderer(this, 10, 41);
		AbMain.addBox(-5F, -4F, 0F, 10, 6, 13);
		AbMain.setRotationPoint(0F, 19F, -7F);
		setRotation(AbMain, 0F, 0F, 0F);
		AbBack1 = new ModelRenderer(this, 23, 61);
		AbBack1.addBox(-4F, -3F, 13F, 8, 5, 2);
		AbBack1.setRotationPoint(0F, 19F, -7F);
		setRotation(AbBack1, 0F, 0F, 0F);
		AbBack2 = new ModelRenderer(this, 27, 69);
		AbBack2.addBox(-2F, -2F, 15F, 4, 4, 2);
		AbBack2.setRotationPoint(0F, 19F, -7F);
		setRotation(AbBack2, 0F, 0F, 0F);
		LBLeg1 = new ModelRenderer(this, 0, 72);
		LBLeg1.addBox(-1.5F, 0F, -1.5F, 3, 3, 3);
		LBLeg1.setRotationPoint(5F, 19F, 0F);
		setRotation(LBLeg1, 0F, -0.7853982F, -0.3490659F);
		LBLeg2 = new ModelRenderer(this, 0, 81);
		LBLeg2.addBox(-1F, 3F, -1F, 2, 2, 2);
		LBLeg2.setRotationPoint(5F, 19F, 0F);
		setRotation(LBLeg2, 0F, -0.7853982F, -0.3490659F);
		LBLeg3 = new ModelRenderer(this, 0, 89);
		LBLeg3.addBox(1F, 3F, -0.5F, 5, 2, 1);
		LBLeg3.setRotationPoint(5F, 19F, 0F);
		setRotation(LBLeg3, 0F, -0.7853982F, -0.3490659F);
		LBLeg4 = new ModelRenderer(this, 0, 99);
		LBLeg4.addBox(5.95F, 3F, -0.5F, 1, 5, 1);
		LBLeg4.setRotationPoint(5F, 19F, 0F);
		setRotation(LBLeg4, 0F, -0.7853982F, -0.3490659F);
		RBLeg1 = new ModelRenderer(this, 0, 72);
		RBLeg1.addBox(-1.5F, 0F, -1.5F, 3, 3, 3);
		RBLeg1.setRotationPoint(-5F, 19F, 0F);
		setRotation(RBLeg1, 0F, -2.356194F, 0.3490659F);
		RBLeg2 = new ModelRenderer(this, 0, 81);
		RBLeg2.addBox(-1F, 3F, -1F, 2, 2, 2);
		RBLeg2.setRotationPoint(-5F, 19F, 0F);
		setRotation(RBLeg2, 0F, -2.356194F, 0.3490659F);
		RBLeg3 = new ModelRenderer(this, 0, 89);
		RBLeg3.addBox(1F, 3F, -0.5F, 5, 2, 1);
		RBLeg3.setRotationPoint(-5F, 19F, 0F);
		setRotation(RBLeg3, 0F, -2.356194F, 0.3490659F);
		RBLeg4 = new ModelRenderer(this, 0, 99);
		RBLeg4.addBox(5.95F, 3F, -0.5F, 1, 5, 1);
		RBLeg4.setRotationPoint(-5F, 19F, 0F);
		setRotation(RBLeg4, 0F, -2.356194F, 0.3490659F);
		LFLeg1 = new ModelRenderer(this, 0, 72);
		LFLeg1.addBox(-1.5F, 0F, -1.5F, 3, 3, 3);
		LFLeg1.setRotationPoint(5F, 19F, -8F);
		setRotation(LFLeg1, 0F, 1.22173F, -0.3490659F);
		LFLeg2 = new ModelRenderer(this, 0, 81);
		LFLeg2.addBox(-1F, 3F, -1F, 2, 2, 2);
		LFLeg2.setRotationPoint(5F, 19F, -8F);
		setRotation(LFLeg2, 0F, 1.22173F, -0.3490659F);
		LFLeg3 = new ModelRenderer(this, 0, 89);
		LFLeg3.addBox(1F, 3F, -0.5F, 5, 2, 1);
		LFLeg3.setRotationPoint(5F, 19F, -8F);
		setRotation(LFLeg3, 0F, 1.22173F, -0.3490659F);
		LFLeg4 = new ModelRenderer(this, 0, 99);
		LFLeg4.addBox(5.95F, 3F, -0.5F, 1, 5, 1);
		LFLeg4.setRotationPoint(5F, 19F, -8F);
		setRotation(LFLeg4, 0F, 1.22173F, -0.3490659F);
		RFLeg1 = new ModelRenderer(this, 0, 72);
		RFLeg1.addBox(-1.5F, 0F, -1.5F, 3, 3, 3);
		RFLeg1.setRotationPoint(-5F, 19F, -8F);
		setRotation(RFLeg1, 0F, 1.919862F, 0.3490659F);
		RFLeg2 = new ModelRenderer(this, 0, 81);
		RFLeg2.addBox(-1F, 3F, -1F, 2, 2, 2);
		RFLeg2.setRotationPoint(-5F, 19F, -8F);
		setRotation(RFLeg2, 0F, 1.919862F, 0.3490659F);
		RFLeg3 = new ModelRenderer(this, 0, 89);
		RFLeg3.addBox(1F, 3F, -0.5F, 5, 2, 1);
		RFLeg3.setRotationPoint(-5F, 19F, -8F);
		setRotation(RFLeg3, 0F, 1.919862F, 0.3490659F);
		RFLeg4 = new ModelRenderer(this, 0, 99);
		RFLeg4.addBox(5.95F, 3F, -0.5F, 1, 5, 1);
		RFLeg4.setRotationPoint(-5F, 19F, -8F);
		setRotation(RFLeg4, 0F, 1.919862F, 0.3490659F);
		LMLeg1 = new ModelRenderer(this, 0, 72);
		LMLeg1.addBox(-1.5F, 0F, -1.5F, 3, 3, 3);
		LMLeg1.setRotationPoint(5F, 19F, -4F);
		setRotation(LMLeg1, 0F, 0F, -0.3490659F);
		LMLeg2 = new ModelRenderer(this, 0, 81);
		LMLeg2.addBox(-1F, 3F, -1F, 2, 2, 2);
		LMLeg2.setRotationPoint(5F, 19F, -4F);
		setRotation(LMLeg2, 0F, 0F, -0.3490659F);
		LMLeg3 = new ModelRenderer(this, 0, 89);
		LMLeg3.addBox(1F, 3F, -0.5F, 5, 2, 1);
		LMLeg3.setRotationPoint(5F, 19F, -4F);
		setRotation(LMLeg3, 0F, 0F, -0.3490659F);
		LMLeg4 = new ModelRenderer(this, 0, 99);
		LMLeg4.addBox(5.95F, 3F, -0.5F, 1, 5, 1);
		LMLeg4.setRotationPoint(5F, 19F, -4F);
		setRotation(LMLeg4, 0F, 0F, -0.3490659F);
		RMLeg1 = new ModelRenderer(this, 0, 72);
		RMLeg1.addBox(-1.5F, 0F, -1.5F, 3, 3, 3);
		RMLeg1.setRotationPoint(-5F, 19F, -4F);
		setRotation(RMLeg1, 0F, 3.141593F, 0.3490659F);
		RMLeg2 = new ModelRenderer(this, 0, 81);
		RMLeg2.addBox(-1F, 3F, -1F, 2, 2, 2);
		RMLeg2.setRotationPoint(-5F, 19F, -4F);
		setRotation(RMLeg2, 0F, 3.141593F, 0.3490659F);
		RMLeg3 = new ModelRenderer(this, 0, 89);
		RMLeg3.addBox(1F, 3F, -0.5F, 5, 2, 1);
		RMLeg3.setRotationPoint(-5F, 19F, -4F);
		setRotation(RMLeg3, 0F, 3.141593F, 0.3490659F);
		RMLeg4 = new ModelRenderer(this, 0, 99);
		RMLeg4.addBox(5.95F, 3F, -0.5F, 1, 5, 1);
		RMLeg4.setRotationPoint(-5F, 19F, -4F);
		setRotation(RMLeg4, 0F, 3.141593F, 0.3490659F);
		RWingFront = new ModelRenderer(this, 13, 95);
		RWingFront.addBox(-1F, -2F, -1F, 1, 4, 2);
		RWingFront.setRotationPoint(-4F, 14F, -6F);
		setRotation(RWingFront, -0.1745329F, 0F, 0.4363323F);
		RWingTop = new ModelRenderer(this, 20, 87);
		RWingTop.addBox(-1F, -4F, 4F, 1, 1, 13);
		setRotation(RWingTop, 0F, 0F, 0F);
		RWingMain = new ModelRenderer(this, 16, 102);
		RWingMain.addBox(-1F, -3F, 1F, 1, 6, 17);
		setRotation(RWingMain, 0F, 0F, 0F);
		RWingBack = new ModelRenderer(this, 49, 96);
		RWingBack.addBox(-1F, -2F, 18F, 1, 4, 1);
		setRotation(RWingBack, 0F, 0F, 0F);
		LWingFront = new ModelRenderer(this, 13, 95);
		LWingFront.addBox(0F, -2F, -1F, 1, 4, 2);
		LWingFront.setRotationPoint(4F, 14F, -6F);
		setRotation(LWingFront, -0.1745329F, 0F, -0.4363323F);
		LWingTop = new ModelRenderer(this, 20, 87);
		LWingTop.addBox(0F, -4F, 4F, 1, 1, 13);
		setRotation(LWingTop, 0F, 0F, 0F);
		LWingMain = new ModelRenderer(this, 16, 102);
		LWingMain.addBox(0F, -3F, 1F, 1, 6, 17);
		setRotation(LWingMain, 0F, 0F, 0F);
		LWingBack = new ModelRenderer(this, 16, 63);
		LWingBack.addBox(0F, -2F, 18F, 1, 4, 1);
		setRotation(LWingBack, 0F, 0F, 0F);

		RWingFront.addChild(RWingTop);
		RWingFront.addChild(RWingMain);
		RWingFront.addChild(RWingBack);
		LWingFront.addChild(LWingTop);
		LWingFront.addChild(LWingMain);
		LWingFront.addChild(LWingBack);
	}

	@Override
	public void render(Entity entity, float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.render(entity, limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		setRotationAngles(limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		EntityCicada cicada = (EntityCicada) entity;

		LEye.render(unitPixel);
		REye.render(unitPixel);
		HeadL.render(unitPixel);
		HeadR.render(unitPixel);
		HeadFront.render(unitPixel);
		HeadMain.render(unitPixel);
		HeadTop.render(unitPixel);
		HeadBack.render(unitPixel);
		ThoraxTop.render(unitPixel);
		AbMain.render(unitPixel);
		AbBack1.render(unitPixel);
		AbBack2.render(unitPixel);
		LBLeg1.render(unitPixel);
		LBLeg2.render(unitPixel);
		LBLeg3.render(unitPixel);
		LBLeg4.render(unitPixel);
		RBLeg1.render(unitPixel);
		RBLeg2.render(unitPixel);
		RBLeg3.render(unitPixel);
		RBLeg4.render(unitPixel);
		LFLeg1.render(unitPixel);
		LFLeg2.render(unitPixel);
		LFLeg3.render(unitPixel);
		LFLeg4.render(unitPixel);
		RFLeg1.render(unitPixel);
		RFLeg2.render(unitPixel);
		RFLeg3.render(unitPixel);
		RFLeg4.render(unitPixel);
		LMLeg1.render(unitPixel);
		LMLeg2.render(unitPixel);
		LMLeg3.render(unitPixel);
		LMLeg4.render(unitPixel);
		RMLeg1.render(unitPixel);
		RMLeg2.render(unitPixel);
		RMLeg3.render(unitPixel);
		RMLeg4.render(unitPixel);
		RWingFront.render(unitPixel);
		LWingFront.render(unitPixel);

		if (cicada.isFlying()) {

			GL11.glPushMatrix();
			GL11.glTranslatef(0.05F, 0.4F, 0.1F);
			GL11.glRotatef(20F, 0F, 1F, 0F);
			GL11.glScalef(0.7F, 0.7F, 0.7F);
			RWingFront.render(unitPixel);
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			GL11.glTranslatef(-0.05F, 0.4F, 0.1F);
			GL11.glRotatef(-20F, 0F, 1F, 0F);
			GL11.glScalef(0.7F, 0.7F, 0.7F);
			LWingFront.render(unitPixel);
			GL11.glPopMatrix();
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		EntityCicada cicada = (EntityCicada) entity;

		if (cicada.onGround) {
			RWingFront.rotateAngleX = 0F;
			LWingFront.rotateAngleX = 0F;
			RWingFront.rotateAngleY = 0F;
			LWingFront.rotateAngleY = 0F;
			RWingFront.rotateAngleZ = 0.3F;
			LWingFront.rotateAngleZ = -0.3F;
		}

		if (cicada.isFlying()) {
			RWingFront.rotateAngleZ = 1.8F - cicada.wingFloat;
			;
			LWingFront.rotateAngleZ = -1.8F + cicada.wingFloat;
			;
			RWingFront.rotateAngleX = -1.8F;
			LWingFront.rotateAngleX = -1.8F;
			RWingFront.rotateAngleY = 0F;
			LWingFront.rotateAngleY = 0F;

		}
	}
}
