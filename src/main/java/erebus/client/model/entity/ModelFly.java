package erebus.client.model.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityFly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelFly extends ModelBase {

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Shape13;

	public ModelFly() {
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 24, 7);
		Shape1.addBox(-2.5F, -2F, -2.5F, 5, 5, 4);
		Shape1.setRotationPoint(0F, 19F, 0F);
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(-3F, -3F, 2F, 6, 6, 6);
		Shape2.setRotationPoint(0F, 19F, 0F);
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 24, 0);
		Shape3.addBox(0.5F, -2.5F, -3.5F, 2, 4, 3);
		Shape3.setRotationPoint(0F, 19F, -2F);
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 24, 0);
		Shape4.addBox(-2.5F, -2.5F, -3.5F, 2, 4, 3);
		Shape4.setRotationPoint(0F, 19F, -2F);
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 12);
		Shape5.addBox(0F, 0F, 0F, 6, 1, 1);
		Shape5.setRotationPoint(3F, 19F, 0F);
		setRotation(Shape5, 0F, -0.5235988F, 0.7853982F);
		Shape6 = new ModelRenderer(this, 34, 0);
		Shape6.addBox(-2F, -2F, -3F, 4, 4, 3);
		Shape6.setRotationPoint(0F, 19F, -2F);
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 0, 12);
		Shape7.addBox(0F, 0F, 0.5F, 6, 1, 1);
		Shape7.setRotationPoint(3F, 19F, 0F);
		setRotation(Shape7, 0F, 0.5235988F, 0.7853982F);
		Shape8 = new ModelRenderer(this, 0, 12);
		Shape8.addBox(0F, 0F, -0.5F, 6, 1, 1);
		Shape8.setRotationPoint(3F, 19F, -0.5F);
		setRotation(Shape8, 0F, 0.5235988F, 0.7853982F);
		Shape9 = new ModelRenderer(this, 0, 12);
		Shape9.addBox(-6F, 0F, 0F, 6, 1, 1);
		Shape9.setRotationPoint(-3F, 19F, -1F);
		setRotation(Shape9, 0F, 0.5235988F, -0.7853982F);
		Shape10 = new ModelRenderer(this, 0, 12);
		Shape10.addBox(-6F, 0F, -1F, 6, 1, 1);
		Shape10.setRotationPoint(-3F, 19F, -1F);
		setRotation(Shape10, 0F, -0.5235988F, -0.7853982F);
		Shape11 = new ModelRenderer(this, 0, 12);
		Shape11.addBox(-6F, 0F, -0.5F, 6, 1, 1);
		Shape11.setRotationPoint(-3F, 19F, -0.5F);
		setRotation(Shape11, 0F, 0F, -0.7853982F);
		Shape12 = new ModelRenderer(this, 0, 25);
		Shape12.addBox(-6F, 0F, 0F, 6, 1, 6);
		Shape12.setRotationPoint(-1F, 16F, 0F);
		setRotation(Shape12, 0.5235988F, -0.1745329F, 0F);
		Shape13 = new ModelRenderer(this, 0, 25);
		Shape13.addBox(0F, 0F, 0F, 6, 1, 6);
		Shape13.setRotationPoint(1F, 16F, 0F);
		setRotation(Shape13, 0.5235988F, 0.1745329F, 0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.render(entity, limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		setRotationAngles(limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		Shape1.render(unitPixel);
		Shape2.render(unitPixel);
		Shape3.render(unitPixel);
		Shape4.render(unitPixel);
		Shape5.render(unitPixel);
		Shape6.render(unitPixel);
		Shape7.render(unitPixel);
		Shape8.render(unitPixel);
		Shape9.render(unitPixel);
		Shape10.render(unitPixel);
		Shape11.render(unitPixel);
		Shape12.render(unitPixel);
		Shape13.render(unitPixel);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
		super.setRotationAngles(limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		EntityFly var8 = (EntityFly) entity;
		Shape12.rotateAngleX = var8.wingFloat;
		Shape13.rotateAngleX = var8.wingFloat;
		Shape12.rotateAngleZ = var8.wingFloat;
		Shape13.rotateAngleZ = -var8.wingFloat;
	}

	public int getFlySize() {
		return 72;
	}
}