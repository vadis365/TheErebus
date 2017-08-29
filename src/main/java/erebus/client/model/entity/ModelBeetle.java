package erebus.client.model.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

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
		setRotation(backbody, 0F, 0F, 0F);
		body = new ModelRenderer(this, 0, 20);
		body.addBox(-4F, -2F, -5F, 12, 8, 5);
		body.setRotationPoint(-3F, 14F, 0F);
		setRotation(body, 0F, 0F, 0F);
		head = new ModelRenderer(this, 1, 10);
		head.addBox(-2F, -1F, -2F, 6, 5, 3);
		head.setRotationPoint(-2F, 15F, -6F);
		setRotation(head, 0F, 0F, 0F);
		bum = new ModelRenderer(this, 35, 27);
		bum.addBox(-6F, -2F, 0F, 12, 6, 3);
		bum.setRotationPoint(-1F, 15F, 17F);
		setRotation(bum, 0F, 0F, 0F);
		head2 = new ModelRenderer(this, 2, 6);
		head2.addBox(-5F, 1F, -5F, 12, 0, 4);
		head2.setRotationPoint(-2F, 15F, -6F);
		setRotation(head2, 0F, 0F, 0F);
		LBL1 = new ModelRenderer(this, 0, 95);
		LBL1.addBox(-4F, -1F, -1F, 4, 2, 2);
		LBL1.setRotationPoint(5F, 17F, 15F);
		setRotation(LBL1, 0F, 2.617994F, -0.3490659F);
		LBL2 = new ModelRenderer(this, 0, 88);
		LBL2.addBox(-5F, 0F, -1F, 2, 4, 2);
		setRotation(LBL2, 0F, 0F, 0.3490659F);
		LBL3 = new ModelRenderer(this, 0, 82);
		LBL3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(LBL3, 0F, 0F, 0.6981317F);
		LBL4 = new ModelRenderer(this, 0, 76);
		LBL4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
		setRotation(LBL4, 0F, 0F, 0.8726646F);
		LFL1 = new ModelRenderer(this, 0, 95);
		LFL1.addBox(-4F, -1F, -1F, 4, 2, 2);
		LFL1.setRotationPoint(5F, 17F, 2F);
		setRotation(LFL1, 0F, -2.617994F, -0.3490659F);
		LFL2 = new ModelRenderer(this, 0, 88);
		LFL2.addBox(-5F, 0F, -1F, 2, 4, 2);
		setRotation(LFL2, 0F, 0F, 0.3490659F);
		LFL3 = new ModelRenderer(this, 0, 82);
		LFL3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(LFL3, 0F, 0F, 0.6981317F);
		LFL4 = new ModelRenderer(this, 0, 76);
		LFL4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
		setRotation(LFL4, 0F, 0F, 0.8726646F);
		LML1 = new ModelRenderer(this, 0, 95);
		LML1.addBox(-4F, -1F, -1F, 4, 2, 2);
		LML1.setRotationPoint(5F, 17F, 8.5F);
		setRotation(LML1, 0F, -3.141593F, -0.3490659F);
		LML2 = new ModelRenderer(this, 0, 88);
		LML2.addBox(-5F, 0F, -1F, 2, 4, 2);
		setRotation(LML2, 0F, 0F, 0.3490659F);
		LML3 = new ModelRenderer(this, 0, 82);
		LML3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(LML3, 0F, 0F, 0.6981317F);
		LML4 = new ModelRenderer(this, 0, 76);
		LML4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
		setRotation(LML4, 0F, 0F, 0.8726646F);
		RFL1 = new ModelRenderer(this, 0, 95);
		RFL1.addBox(-4F, -1F, -1F, 4, 2, 2);
		RFL1.setRotationPoint(-7F, 17F, 2F);
		setRotation(RFL1, 0F, -0.5235988F, 0.3490659F);
		RFL2 = new ModelRenderer(this, 0, 88);
		RFL2.addBox(-5F, 0F, -1F, 2, 4, 2);
		setRotation(RFL2, 0F, 0F, 0.3490659F);
		RFL3 = new ModelRenderer(this, 0, 82);
		RFL3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(RFL3, 0F, 0F, 0.6981317F);
		RFL4 = new ModelRenderer(this, 0, 76);
		RFL4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
		setRotation(RFL4, 0F, 0F, 0.8726646F);
		RML1 = new ModelRenderer(this, 0, 95);
		RML1.addBox(-4F, -1F, -1F, 4, 2, 2);
		RML1.setRotationPoint(-7F, 17F, 8.5F);
		setRotation(RML1, 0F, 0F, 0.3490659F);
		RML2 = new ModelRenderer(this, 0, 88);
		RML2.addBox(-5F, 0F, -1F, 2, 4, 2);
		setRotation(RML2, 0F, 0F, 0.3490659F);
		RML3 = new ModelRenderer(this, 0, 82);
		RML3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(RML3, 0F, 0F, 0.6981317F);
		RML4 = new ModelRenderer(this, 0, 76);
		RML4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
		setRotation(RML4, 0F, 0F, 0.8726646F);
		RBL1 = new ModelRenderer(this, 0, 95);
		RBL1.addBox(-4F, -1F, -1F, 4, 2, 2);
		RBL1.setRotationPoint(-7F, 17F, 15F);
		setRotation(RBL1, 0F, 0F, 0.3490659F);
		RBL2 = new ModelRenderer(this, 0, 88);
		RBL2.addBox(-5F, 0F, -1F, 2, 4, 2);
		setRotation(RBL2, 0F, 0F, 0.3490659F);
		RBL3 = new ModelRenderer(this, 0, 82);
		RBL3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(RBL3, 0F, 0F, 0.6981317F);
		RBL4 = new ModelRenderer(this, 0, 76);
		RBL4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
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
		GL11.glPushMatrix();
		GL11.glTranslated(0F, 0F, -0.375F);
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
		GL11.glPopMatrix();
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
