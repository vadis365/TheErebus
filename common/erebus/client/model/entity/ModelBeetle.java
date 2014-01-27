package erebus.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBeetle extends ModelBase {

	// fields
	ModelRenderer backbody;
	ModelRenderer body;
	ModelRenderer head;
	ModelRenderer bum;
	ModelRenderer head2;
	ModelRenderer LBL1;
	ModelRenderer LBL2;
	ModelRenderer LBL3;
	ModelRenderer LBL4;
	ModelRenderer LFL1;
	ModelRenderer LFL2;
	ModelRenderer LFL3;
	ModelRenderer LFL4;
	ModelRenderer LML1;
	ModelRenderer LML2;
	ModelRenderer LML3;
	ModelRenderer LML4;
	ModelRenderer RFL1;
	ModelRenderer RFL2;
	ModelRenderer RFL3;
	ModelRenderer RFL4;
	ModelRenderer RML1;
	ModelRenderer RML2;
	ModelRenderer RML3;
	ModelRenderer RML4;
	ModelRenderer RBL1;
	ModelRenderer RBL2;
	ModelRenderer RBL3;
	ModelRenderer RBL4;

	public ModelBeetle() {
		textureWidth = 128;
		textureHeight = 128;

		backbody = new ModelRenderer(this, 35, 0);
		backbody.addBox(-7F, -4F, 0F, 14, 9, 17);
		backbody.setRotationPoint(-1F, 15F, 0F);
		backbody.setTextureSize(128, 128);
		backbody.mirror = true;
		setRotation(backbody, 0F, 0F, 0F);
		body = new ModelRenderer(this, 0, 20);
		body.addBox(-4F, -2F, -5F, 12, 8, 5);
		body.setRotationPoint(-3F, 14F, 0F);
		body.setTextureSize(128, 128);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		head = new ModelRenderer(this, 1, 10);
		head.addBox(-2F, -1F, -2F, 6, 5, 3);
		head.setRotationPoint(-2F, 15F, -6F);
		head.setTextureSize(128, 128);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		bum = new ModelRenderer(this, 35, 27);
		bum.addBox(-6F, -2F, 0F, 12, 6, 3);
		bum.setRotationPoint(-1F, 15F, 17F);
		bum.setTextureSize(128, 128);
		bum.mirror = true;
		setRotation(bum, 0F, 0F, 0F);
		head2 = new ModelRenderer(this, 2, 6);
		head2.addBox(-5F, 1F, -5F, 12, 0, 4);
		head2.setRotationPoint(-2F, 15F, -6F);
		head2.setTextureSize(128, 128);
		head2.mirror = true;
		setRotation(head2, 0F, 0F, 0F);
		LBL1 = new ModelRenderer(this, 0, 95);
		LBL1.addBox(-4F, -1F, -1F, 4, 2, 2);
		LBL1.setRotationPoint(5F, 17F, 15F);
		LBL1.setTextureSize(128, 128);
		LBL1.mirror = true;
		setRotation(LBL1, 0F, 2.617994F, -0.3490659F);
		LBL2 = new ModelRenderer(this, 0, 88);
		LBL2.addBox(-5F, 0F, -1F, 2, 4, 2);
		// LBL2.setRotationPoint(5F, 17F, 15F);
		LBL2.setTextureSize(128, 128);
		LBL2.mirror = true;
		setRotation(LBL2, 0F, 0F, 0.3490659F);
		LBL3 = new ModelRenderer(this, 0, 82);
		LBL3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		// LBL3.setRotationPoint(5F, 17F, 15F);
		LBL3.setTextureSize(128, 128);
		LBL3.mirror = true;
		setRotation(LBL3, 0F, 0F, 0.6981317F);
		LBL4 = new ModelRenderer(this, 0, 76);
		LBL4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
		// LBL4.setRotationPoint(5F, 17F, 15F);
		LBL4.setTextureSize(128, 128);
		LBL4.mirror = true;
		setRotation(LBL4, 0F, 0F, 0.8726646F);
		LFL1 = new ModelRenderer(this, 0, 95);
		LFL1.addBox(-4F, -1F, -1F, 4, 2, 2);
		LFL1.setRotationPoint(5F, 17F, 2F);
		LFL1.setTextureSize(128, 128);
		LFL1.mirror = true;
		setRotation(LFL1, 0F, -2.617994F, -0.3490659F);
		LFL2 = new ModelRenderer(this, 0, 88);
		LFL2.addBox(-5F, 0F, -1F, 2, 4, 2);
		// LFL2.setRotationPoint(5F, 17F, 2F);
		LFL2.setTextureSize(128, 128);
		LFL2.mirror = true;
		setRotation(LFL2, 0F, 0F, 0.3490659F);
		LFL3 = new ModelRenderer(this, 0, 82);
		LFL3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		// LFL3.setRotationPoint(5F, 17F, 2F);
		LFL3.setTextureSize(128, 128);
		LFL3.mirror = true;
		setRotation(LFL3, 0F, 0F, 0.6981317F);
		LFL4 = new ModelRenderer(this, 0, 76);
		LFL4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
		// LFL4.setRotationPoint(5F, 17F, 2F);
		LFL4.setTextureSize(128, 128);
		LFL4.mirror = true;
		setRotation(LFL4, 0F, 0F, 0.8726646F);
		LML1 = new ModelRenderer(this, 0, 95);
		LML1.addBox(-4F, -1F, -1F, 4, 2, 2);
		LML1.setRotationPoint(5F, 17F, 8.5F);
		LML1.setTextureSize(128, 128);
		LML1.mirror = true;
		setRotation(LML1, 0F, -3.141593F, -0.3490659F);
		LML2 = new ModelRenderer(this, 0, 88);
		LML2.addBox(-5F, 0F, -1F, 2, 4, 2);
		// LML2.setRotationPoint(5F, 17F, 8.5F);
		LML2.setTextureSize(128, 128);
		LML2.mirror = true;
		setRotation(LML2, 0F, 0F, 0.3490659F);
		LML3 = new ModelRenderer(this, 0, 82);
		LML3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		// LML3.setRotationPoint(5F, 17F, 8.5F);
		LML3.setTextureSize(128, 128);
		LML3.mirror = true;
		setRotation(LML3, 0F, 0F, 0.6981317F);
		LML4 = new ModelRenderer(this, 0, 76);
		LML4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
		// LML4.setRotationPoint(5F, 17F, 8.5F);
		LML4.setTextureSize(128, 128);
		LML4.mirror = true;
		setRotation(LML4, 0F, 0F, 0.8726646F);
		RFL1 = new ModelRenderer(this, 0, 95);
		RFL1.addBox(-4F, -1F, -1F, 4, 2, 2);
		RFL1.setRotationPoint(-7F, 17F, 2F);
		RFL1.setTextureSize(128, 128);
		RFL1.mirror = true;
		setRotation(RFL1, 0F, -0.5235988F, 0.3490659F);
		RFL2 = new ModelRenderer(this, 0, 88);
		RFL2.addBox(-5F, 0F, -1F, 2, 4, 2);
		// RFL2.setRotationPoint(-7F, 17F, 2F);
		RFL2.setTextureSize(128, 128);
		RFL2.mirror = true;
		setRotation(RFL2, 0F, 0F, 0.3490659F);
		RFL3 = new ModelRenderer(this, 0, 82);
		RFL3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		// RFL3.setRotationPoint(-7F, 17F, 2F);
		RFL3.setTextureSize(128, 128);
		RFL3.mirror = true;
		setRotation(RFL3, 0F, 0F, 0.6981317F);
		RFL4 = new ModelRenderer(this, 0, 76);
		RFL4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
		// RFL4.setRotationPoint(-7F, 17F, 2F);
		RFL4.setTextureSize(128, 128);
		RFL4.mirror = true;
		setRotation(RFL4, 0F, 0F, 0.8726646F);
		RML1 = new ModelRenderer(this, 0, 95);
		RML1.addBox(-4F, -1F, -1F, 4, 2, 2);
		RML1.setRotationPoint(-7F, 17F, 8.5F);
		RML1.setTextureSize(128, 128);
		RML1.mirror = true;
		setRotation(RML1, 0F, 0F, 0.3490659F);
		RML2 = new ModelRenderer(this, 0, 88);
		RML2.addBox(-5F, 0F, -1F, 2, 4, 2);
		// RML2.setRotationPoint(-7F, 17F, 8.5F);
		RML2.setTextureSize(128, 128);
		RML2.mirror = true;
		setRotation(RML2, 0F, 0F, 0.3490659F);
		RML3 = new ModelRenderer(this, 0, 82);
		RML3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		// RML3.setRotationPoint(-7F, 17F, 8.5F);
		RML3.setTextureSize(128, 128);
		RML3.mirror = true;
		setRotation(RML3, 0F, 0F, 0.6981317F);
		RML4 = new ModelRenderer(this, 0, 76);
		RML4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
		// RML4.setRotationPoint(-7F, 17F, 8.5F);
		RML4.setTextureSize(128, 128);
		RML4.mirror = true;
		setRotation(RML4, 0F, 0F, 0.8726646F);
		RBL1 = new ModelRenderer(this, 0, 95);
		RBL1.addBox(-4F, -1F, -1F, 4, 2, 2);
		RBL1.setRotationPoint(-7F, 17F, 15F);
		RBL1.setTextureSize(128, 128);
		RBL1.mirror = true;
		setRotation(RBL1, 0F, 0F, 0.3490659F);
		RBL2 = new ModelRenderer(this, 0, 88);
		RBL2.addBox(-5F, 0F, -1F, 2, 4, 2);
		// RBL2.setRotationPoint(-7F, 17F, 15F);
		RBL2.setTextureSize(128, 128);
		RBL2.mirror = true;
		setRotation(RBL2, 0F, 0F, 0.3490659F);
		RBL3 = new ModelRenderer(this, 0, 82);
		RBL3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		// RBL3.setRotationPoint(-7F, 17F, 15F);
		RBL3.setTextureSize(128, 128);
		RBL3.mirror = true;
		setRotation(RBL3, 0F, 0F, 0.6981317F);
		RBL4 = new ModelRenderer(this, 0, 76);
		RBL4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
		// RBL4.setRotationPoint(-7F, 17F, 15F);
		RBL4.setTextureSize(128, 128);
		RBL4.mirror = true;
		setRotation(RBL4, 0F, 0F, 0.8726646F);

		LFL1.addChild(LFL2);
		LFL1.addChild(LFL3);
		LFL1.addChild(LFL4);

		LML1.addChild(LML2);
		LML1.addChild(LML3);
		LML1.addChild(LML4);

		LBL1.addChild(LBL2);
		LBL1.addChild(LBL3);
		LBL1.addChild(LBL4);

		RFL1.addChild(RFL2);
		RFL1.addChild(RFL3);
		RFL1.addChild(RFL4);

		RML1.addChild(RML2);
		RML1.addChild(RML3);
		RML1.addChild(RML4);

		RBL1.addChild(RBL2);
		RBL1.addChild(RBL3);
		RBL1.addChild(RBL4);
	}

	@Override
	public void render(Entity entity, float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.render(entity, limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		setRotationAngles(limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		backbody.render(unitPixel);
		body.render(unitPixel);
		head.render(unitPixel);
		bum.render(unitPixel);
		head2.render(unitPixel);
		LBL1.render(unitPixel);
		LFL1.render(unitPixel);
		LML1.render(unitPixel);
		RFL1.render(unitPixel);
		RML1.render(unitPixel);
		RBL1.render(unitPixel);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
		super.setRotationAngles(limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		float cos1 = MathHelper.cos(limbSwing * 1.0F + (float) Math.PI) * 0.5F * prevLimbSwing;
		float cos2 = MathHelper.cos(limbSwing * 1.0F) * 0.5F * prevLimbSwing;
		LBL1.rotateAngleX = cos1;
		LML1.rotateAngleX = cos2;
		LFL1.rotateAngleX = cos1;
		RBL1.rotateAngleX = -cos2;
		RML1.rotateAngleX = -cos1;
		RFL1.rotateAngleX = -cos2;
		LBL1.rotateAngleY = cos1 + 2.617994F;
		LML1.rotateAngleY = cos2 - 3.142F;
		LFL1.rotateAngleY = cos1 - 2.617994F;
		RBL1.rotateAngleY = -cos2 + 0.3490659F;
		RML1.rotateAngleY = -cos1;
		RFL1.rotateAngleY = -cos2 - 0.3490659F;
	}

}
