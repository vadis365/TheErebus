package erebus.client.model.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelLavaWebSpider extends ModelBase {
	ModelRenderer ThxTop;
	ModelRenderer Thx;
	ModelRenderer ThxS;
	ModelRenderer Ab;
	ModelRenderer AbSide;
	ModelRenderer AbTop;
	ModelRenderer AbBack;
	ModelRenderer LBL1;
	ModelRenderer LBL2;
	ModelRenderer LBL3;
	ModelRenderer LBL4;
	ModelRenderer LMBL1;
	ModelRenderer LMBL2;
	ModelRenderer LMBL3;
	ModelRenderer LMBL4;
	ModelRenderer RMBL1;
	ModelRenderer RMBL2;
	ModelRenderer RMBL3;
	ModelRenderer RMBL4;
	ModelRenderer RBL1;
	ModelRenderer RBL2;
	ModelRenderer RBL3;
	ModelRenderer RBL4;
	ModelRenderer LMFL1;
	ModelRenderer LMFL2;
	ModelRenderer LMFL3;
	ModelRenderer LMFL4;
	ModelRenderer RMFL1;
	ModelRenderer RMFL2;
	ModelRenderer RMFL3;
	ModelRenderer RMFL4;
	ModelRenderer LFL1;
	ModelRenderer LFL2;
	ModelRenderer LFL3;
	ModelRenderer LFL4;
	ModelRenderer RFL1;
	ModelRenderer RFL2;
	ModelRenderer RFL3;
	ModelRenderer RFL4;
	ModelRenderer Lmand;
	ModelRenderer Rmand;

	public ModelLavaWebSpider() {
		textureWidth = 64;
		textureHeight = 128;

		ThxTop = new ModelRenderer(this, 16, 0);
		ThxTop.addBox(-2.5F, -3.5F, -3.5F, 5, 7, 11);
		ThxTop.setRotationPoint(0F, 17F, 0F);
		setRotation(ThxTop, 0F, 0F, 0F);
		Thx = new ModelRenderer(this, 10, 19);
		Thx.addBox(-5.5F, -1.5F, -2.5F, 11, 3, 11);
		Thx.setRotationPoint(0F, 17F, 0F);
		setRotation(Thx, 0F, 0F, 0F);
		ThxS = new ModelRenderer(this, 9, 34);
		ThxS.addBox(-3.5F, -2.5F, -6F, 7, 5, 16);
		ThxS.setRotationPoint(0F, 17F, 0F);
		setRotation(ThxS, 0F, 0F, 0F);
		Ab = new ModelRenderer(this, 9, 88);
		Ab.addBox(-5.5F, -2.5F, 0F, 11, 6, 12);
		Ab.setRotationPoint(0F, 17F, 10F);
		setRotation(Ab, 0F, 0F, 0F);
		AbSide = new ModelRenderer(this, 10, 56);
		AbSide.addBox(-6.5F, -2F, 2F, 13, 5, 8);
		AbSide.setRotationPoint(0F, 17F, 10F);
		setRotation(AbSide, 0F, 0F, 0F);
		AbTop = new ModelRenderer(this, 15, 70);
		AbTop.addBox(-4F, -3.5F, 2F, 8, 8, 8);
		AbTop.setRotationPoint(0F, 17F, 10F);
		setRotation(AbTop, 0F, 0F, 0F);
		AbBack = new ModelRenderer(this, 23, 107);
		AbBack.addBox(-3.5F, -2F, 12F, 7, 5, 1);
		AbBack.setRotationPoint(0F, 17F, 10F);
		setRotation(AbBack, 0F, 0F, 0F);

		float correction = 0.3490659F;
		LBL1 = new ModelRenderer(this, 0, 95);
		LBL1.addBox(0F, -1F, -1F, 5, 2, 2);
		LBL1.setRotationPoint(5F, 17F, 8F);
		setRotation(LBL1, 0.3490659F, -0.6981317F, -0.3490659F);
		LBL2 = new ModelRenderer(this, 0, 88);
		LBL2.addBox(4F, 0F, -1F, 2, 4, 2);
		setRotation(LBL2, 0F, 0F, 0F);
		LBL3 = new ModelRenderer(this, 0, 82);
		LBL3.addBox(2.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(LBL3, 0F, 0F, -0.6981317F + correction);
		LBL4 = new ModelRenderer(this, 0, 76);
		LBL4.addBox(1.5F, 8F, -0.5F, 1, 4, 1);
		setRotation(LBL4, 0F, 0F, -0.8726646F + correction);
		LMBL1 = new ModelRenderer(this, 0, 95);
		LMBL1.addBox(0F, -1F, -1F, 5, 2, 2);
		LMBL1.setRotationPoint(5F, 17F, 4F);
		setRotation(LMBL1, 0.175F, -0.2617994F, -0.3490659F);
		LMBL2 = new ModelRenderer(this, 0, 88);
		LMBL2.addBox(4F, 0F, -1F, 2, 4, 2);
		setRotation(LMBL2, 0F, 0F, 0);
		LMBL3 = new ModelRenderer(this, 0, 82);
		LMBL3.addBox(2.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(LMBL3, 0F, 0F, -0.6981317F + correction);
		LMBL4 = new ModelRenderer(this, 0, 76);
		LMBL4.addBox(1.5F, 8F, -0.5F, 1, 4, 1);
		setRotation(LMBL4, 0F, 0F, -0.8726646F + correction);
		RMBL1 = new ModelRenderer(this, 0, 95);
		RMBL1.addBox(-5F, -1F, -1F, 5, 2, 2);
		RMBL1.setRotationPoint(-5F, 17F, 4F);
		setRotation(RMBL1, 0.175F, 0.2617994F, 0.3490659F);
		RMBL2 = new ModelRenderer(this, 0, 88);
		RMBL2.addBox(-6F, 0F, -1F, 2, 4, 2);
		setRotation(RMBL2, 0F, 0F, 0F);
		RMBL3 = new ModelRenderer(this, 0, 82);
		RMBL3.addBox(-4.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(RMBL3, 0F, 0F, 0.6981317F - correction);
		RMBL4 = new ModelRenderer(this, 0, 76);
		RMBL4.addBox(-2.5F, 8F, -0.5F, 1, 4, 1);
		setRotation(RMBL4, 0F, 0F, 0.8726646F - correction);
		RBL1 = new ModelRenderer(this, 0, 95);
		RBL1.addBox(-5F, -1F, -1F, 5, 2, 2);
		RBL1.setRotationPoint(-5F, 17F, 8F);
		setRotation(RBL1, 0.3490659F, 0.6981317F, 0.3490659F);
		RBL2 = new ModelRenderer(this, 0, 88);
		RBL2.addBox(-6F, 0F, -1F, 2, 4, 2);
		setRotation(RBL2, 0F, 0F, 0F);
		RBL3 = new ModelRenderer(this, 0, 82);
		RBL3.addBox(-4.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(RBL3, 0F, 0F, 0.6981317F - correction);
		RBL4 = new ModelRenderer(this, 0, 76);
		RBL4.addBox(-2.5F, 8F, -0.5F, 1, 4, 1);
		setRotation(RBL4, 0F, 0F, 0.8726646F - correction);
		LMFL1 = new ModelRenderer(this, 0, 95);
		LMFL1.addBox(0F, -1F, -1F, 5, 2, 2);
		LMFL1.setRotationPoint(5F, 17F, 1F);
		setRotation(LMFL1, -0.175F, 0.2617994F, -0.3490659F);
		LMFL2 = new ModelRenderer(this, 0, 88);
		LMFL2.addBox(4F, 0F, -1F, 2, 4, 2);
		setRotation(LMFL2, 0F, 0F, 0F);
		LMFL3 = new ModelRenderer(this, 0, 82);
		LMFL3.addBox(2.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(LMFL3, 0F, 0F, -0.6981317F + correction);
		LMFL4 = new ModelRenderer(this, 0, 76);
		LMFL4.addBox(1.5F, 8F, -0.5F, 1, 4, 1);
		setRotation(LMFL4, 0F, 0F, -0.8726646F + correction);
		RMFL1 = new ModelRenderer(this, 0, 95);
		RMFL1.addBox(-5F, -1F, -1F, 5, 2, 2);
		RMFL1.setRotationPoint(-5F, 17F, 1F);
		setRotation(RMFL1, -0.175F, -0.2617994F, 0.3490659F);
		RMFL2 = new ModelRenderer(this, 0, 88);
		RMFL2.addBox(-6F, 0F, -1F, 2, 4, 2);
		setRotation(RMFL2, 0F, 0F, 0F);
		RMFL3 = new ModelRenderer(this, 0, 82);
		RMFL3.addBox(-4.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(RMFL3, 0F, 0F, 0.6981317F - correction);
		RMFL4 = new ModelRenderer(this, 0, 76);
		RMFL4.addBox(-2.5F, 8F, -0.5F, 1, 4, 1);
		setRotation(RMFL4, 0F, 0F, 0.8726646F - correction);
		LFL1 = new ModelRenderer(this, 0, 95);
		LFL1.addBox(0F, -1F, -1F, 5, 2, 2);
		LFL1.setRotationPoint(5F, 17F, -2F);
		setRotation(LFL1, -0.3490659F, 0.6981317F, -0.3490659F);
		LFL2 = new ModelRenderer(this, 0, 88);
		LFL2.addBox(4F, 0F, -1F, 2, 4, 2);
		setRotation(LFL2, 0F, 0F, 0F);
		LFL3 = new ModelRenderer(this, 0, 82);
		LFL3.addBox(2.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(LFL3, 0F, 0F, -0.6981317F + correction);
		LFL4 = new ModelRenderer(this, 0, 76);
		LFL4.addBox(1.5F, 8F, -0.5F, 1, 4, 1);
		setRotation(LFL4, 0F, 0F, -0.8726646F + correction);
		RFL1 = new ModelRenderer(this, 0, 95);
		RFL1.addBox(-5F, -1F, -1F, 5, 2, 2);
		RFL1.setRotationPoint(-5F, 17F, -2F);
		setRotation(RFL1, -0.3490659F, -0.6981317F, 0.3490659F);
		RFL2 = new ModelRenderer(this, 0, 88);
		RFL2.addBox(-6F, 0F, -1F, 2, 4, 2);
		setRotation(RFL2, 0F, 0F, 0F);
		RFL3 = new ModelRenderer(this, 0, 82);
		RFL3.addBox(-4.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(RFL3, 0F, 0F, 0.6981317F - correction);
		RFL4 = new ModelRenderer(this, 0, 76);
		RFL4.addBox(-2.5F, 8F, -0.5F, 1, 4, 1);
		setRotation(RFL4, 0F, 0F, 0.8726646F - correction);
		Lmand = new ModelRenderer(this, 9, 0);
		Lmand.addBox(-1F, -1.5F, -1.5F, 2, 4, 2);
		Lmand.setRotationPoint(1.5F, 17F, -6F);
		setRotation(Lmand, -0.6981317F, 0F, 0F);
		Rmand = new ModelRenderer(this, 0, 0);
		Rmand.addBox(-1F, -1.5F, -1.5F, 2, 4, 2);
		Rmand.setRotationPoint(-1.5F, 17F, -6F);
		setRotation(Rmand, -0.6981317F, 0F, 0F);

		LFL1.addChild(LFL2);
		LFL1.addChild(LFL3);
		LFL1.addChild(LFL4);

		LMFL1.addChild(LMFL2);
		LMFL1.addChild(LMFL3);
		LMFL1.addChild(LMFL4);

		LMBL1.addChild(LMBL2);
		LMBL1.addChild(LMBL3);
		LMBL1.addChild(LMBL4);

		LBL1.addChild(LBL2);
		LBL1.addChild(LBL3);
		LBL1.addChild(LBL4);

		RFL1.addChild(RFL2);
		RFL1.addChild(RFL3);
		RFL1.addChild(RFL4);

		RMFL1.addChild(RMFL2);
		RMFL1.addChild(RMFL3);
		RMFL1.addChild(RMFL4);

		RMBL1.addChild(RMBL2);
		RMBL1.addChild(RMBL3);
		RMBL1.addChild(RMBL4);

		RBL1.addChild(RBL2);
		RBL1.addChild(RBL3);
		RBL1.addChild(RBL4);

	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.render(entity, limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		setRotationAngles(limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		GL11.glPushMatrix();
		GL11.glTranslated(0F, 0F, -0.375F);
		Thx.render(unitPixel);
		ThxTop.render(unitPixel);
		Thx.render(unitPixel);
		ThxS.render(unitPixel);
		Ab.render(unitPixel);
		AbSide.render(unitPixel);
		AbTop.render(unitPixel);
		AbBack.render(unitPixel);
		LBL1.render(unitPixel);
		LMBL1.render(unitPixel);
		RMBL1.render(unitPixel);
		RBL1.render(unitPixel);
		LMFL1.render(unitPixel);
		RMFL1.render(unitPixel);
		LFL1.render(unitPixel);
		RFL1.render(unitPixel);
		Lmand.render(unitPixel);
		Rmand.render(unitPixel);
		GL11.glPopMatrix();
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);

		float cosZ = MathHelper.cos(limbSwing * 1.0F) * 0.5F * limbSwingAngle;
		float cosY = MathHelper.cos(limbSwing * 1.0F) * 0.5F * limbSwingAngle;
		float fixZ = 0.3490659F;
		float fixY = 0.6108652F;
		LBL1.rotateAngleZ = -cosZ - fixZ;
		LMBL1.rotateAngleZ = cosZ - fixZ;
		LMFL1.rotateAngleZ = -cosZ - fixZ;
		LFL1.rotateAngleZ = cosZ - fixZ;

		RBL1.rotateAngleZ = -cosZ + fixZ;
		RMBL1.rotateAngleZ = cosZ + fixZ;
		RMFL1.rotateAngleZ = -cosZ + fixZ;
		RFL1.rotateAngleZ = cosZ + fixZ;

		LBL1.rotateAngleY = -cosY - fixY;
		LMBL1.rotateAngleY = cosY - fixY * 0.4F;
		LMFL1.rotateAngleY = -cosY + fixY * 0.4F;
		LFL1.rotateAngleY = cosY + fixY;

		RBL1.rotateAngleY = -cosY + fixY;
		RMBL1.rotateAngleY = cosY + fixY * 0.4F;
		RMFL1.rotateAngleY = -cosY - fixY * 0.4F;
		RFL1.rotateAngleY = cosY - fixY;

		Rmand.rotateAngleY = -MathHelper.cos(limbSwing * 0.5F) * 0.4F * limbSwingAngle;
		Lmand.rotateAngleY = -MathHelper.cos(limbSwing * 0.5F + (float) Math.PI) * 0.4F * limbSwingAngle;

	}
}
