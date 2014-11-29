package erebus.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPunchroom extends ModelBase {
	ModelRenderer mushBase;
	ModelRenderer mushBase2;
	ModelRenderer mushBase3;
	ModelRenderer mushSpores;
	ModelRenderer mushCap1;
	ModelRenderer mushCap2;
	ModelRenderer mushCap3;

	public ModelPunchroom() {
		textureWidth = 128;
		textureHeight = 64;

		mushBase = new ModelRenderer(this, 0, 0);
		mushBase.addBox(-3.5F, -5F, -3.5F, 7, 5, 7);
		mushBase.setRotationPoint(0F, 24F, 0F);
		setRotation(mushBase, 0F, 0F, 0F);
		mushBase2 = new ModelRenderer(this, 0, 12);
		mushBase2.addBox(-3F, -5F, -3F, 6, 5, 6);
		mushBase2.setRotationPoint(0F, 20.5F, 0F);
		setRotation(mushBase2, 0F, 0F, 0F);
		mushBase3 = new ModelRenderer(this, 0, 23);
		mushBase3.addBox(-2.5F, -7F, -2.5F, 5, 7, 5);
		mushBase3.setRotationPoint(0F, 17F, 0F);
		setRotation(mushBase3, 0F, 0F, 0F);
		mushSpores = new ModelRenderer(this, 64, 0);
		mushSpores.addBox(-5.5F, -1F, -5.5F, 11, 1, 11);
		mushSpores.setRotationPoint(0F, 12F, 0F);
		setRotation(mushSpores, 0F, 0F, 0F);
		mushCap1 = new ModelRenderer(this, 64, 12);
		mushCap1.addBox(-6F, -2F, -6F, 12, 2, 12);
		mushCap1.setRotationPoint(0F, 11.5F, 0F);
		setRotation(mushCap1, 0F, 0F, 0F);
		mushCap2 = new ModelRenderer(this, 64, 26);
		mushCap2.addBox(-5.5F, -2F, -5.5F, 11, 2, 11);
		mushCap2.setRotationPoint(0F, 10.5F, 0F);
		setRotation(mushCap2, 0F, 0F, 0F);
		mushCap3 = new ModelRenderer(this, 64, 39);
		mushCap3.addBox(-4F, -2F, -5F, 10, 1, 10);
		mushCap3.setRotationPoint(-1F, 10F, 0F);
		setRotation(mushCap3, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.render(entity, limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		setRotationAngles(limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);

		mushBase.render(unitPixel);
		mushBase2.render(unitPixel);
		mushBase3.render(unitPixel);
		mushSpores.render(unitPixel);
		mushCap1.render(unitPixel);
		mushCap2.render(unitPixel);
		mushCap3.render(unitPixel);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}