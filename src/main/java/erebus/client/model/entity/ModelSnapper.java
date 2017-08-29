package erebus.client.model.entity;

import erebus.entity.EntitySnapper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSnapper extends ModelBase {
	ModelRenderer roots;
	ModelRenderer main;
	ModelRenderer rim1;
	ModelRenderer rim2;
	ModelRenderer rim3;
	ModelRenderer rim4;
	ModelRenderer jawmain1;
	ModelRenderer jawtip1;
	ModelRenderer jawmain2;
	ModelRenderer jawtip2;
	ModelRenderer jawmain3;
	ModelRenderer jawtip3;
	ModelRenderer jawmain4;
	ModelRenderer jawtip4;

	public ModelSnapper() {
		textureWidth = 128;
		textureHeight = 32;
		roots = new ModelRenderer(this, -26, 0);
		roots.addBox(0F, 0F, 0F, 25, 0, 25);
		roots.setRotationPoint(-12.5F, 24F, -12.5F);
		setRotation(roots, 0F, 0F, 0F);
		main = new ModelRenderer(this, 50, 0);
		main.addBox(0F, 0F, 0F, 6, 2, 6);
		main.setRotationPoint(-3F, 22F, -3F);
		setRotation(main, 0F, 0F, 0F);
		rim1 = new ModelRenderer(this, 50, 8);
		rim1.addBox(0F, 0F, 0F, 6, 2, 1);
		rim1.setRotationPoint(-3F, 20F, -3F);
		setRotation(rim1, 0F, 0F, 0F);
		rim2 = new ModelRenderer(this, 50, 11);
		rim2.addBox(0F, 0F, 0F, 1, 2, 4);
		rim2.setRotationPoint(-3F, 20F, -2F);
		setRotation(rim2, 0F, 0F, 0F);
		rim3 = new ModelRenderer(this, 50, 8);
		rim3.addBox(0F, 0F, 0F, 6, 2, 1);
		rim3.setRotationPoint(-3F, 20F, 2F);
		setRotation(rim3, 0F, 0F, 0F);
		rim4 = new ModelRenderer(this, 50, 11);
		rim4.addBox(0F, 0F, 0F, 1, 2, 4);
		rim4.setRotationPoint(2F, 20F, -2F);
		setRotation(rim4, 0F, 0F, 0F);
		jawmain1 = new ModelRenderer(this, 50, 17);
		jawmain1.addBox(-2F, 0F, 0F, 4, 1, 9);
		jawmain1.setRotationPoint(0F, 21.5F, 3F);
		setRotation(jawmain1, -0.122173F, 0F, 0F);
		jawtip1 = new ModelRenderer(this, 50, 27);
		jawtip1.addBox(-1F, 0F, 9F, 2, 1, 1);
		jawtip1.setRotationPoint(0F, 21.5F, 3F);
		setRotation(jawtip1, -0.122173F, 0F, 0F);
		jawmain2 = new ModelRenderer(this, 50, 17);
		jawmain2.addBox(-2F, 0F, -9F, 4, 1, 9);
		jawmain2.setRotationPoint(0F, 21.5F, -3F);
		setRotation(jawmain2, 0.122173F, 0F, 0F);
		jawtip2 = new ModelRenderer(this, 50, 27);
		jawtip2.addBox(-1F, 0F, -10F, 2, 1, 1);
		jawtip2.setRotationPoint(0F, 21.5F, -3F);
		setRotation(jawtip2, 0.122173F, 0F, 0F);
		jawmain3 = new ModelRenderer(this, 50, 17);
		jawmain3.addBox(-2F, 0F, -9F, 4, 1, 9);
		jawmain3.setRotationPoint(3F, 21.5F, 0F);
		setRotation(jawmain3, 0.122173F, -((float) Math.PI / 2F), 0F);
		jawtip3 = new ModelRenderer(this, 50, 27);
		jawtip3.addBox(-1F, 0F, 9F, 2, 1, 1);
		jawtip3.setRotationPoint(3F, 21.5F, 0F);
		setRotation(jawtip3, -0.122173F, ((float) Math.PI / 2F), 0F);
		jawmain4 = new ModelRenderer(this, 50, 17);
		jawmain4.addBox(-2F, 0F, 0F, 4, 1, 9);
		jawmain4.setRotationPoint(-3F, 21.5F, 0F);
		setRotation(jawmain4, -0.122173F, -((float) Math.PI / 2F), 0F);
		jawtip4 = new ModelRenderer(this, 50, 27);
		jawtip4.addBox(-1F, 0F, 9F, 2, 1, 1);
		jawtip4.setRotationPoint(-3F, 21.5F, 0F);
		setRotation(jawtip4, -0.122173F, -((float) Math.PI / 2F), 0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.render(entity, limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		setRotationAngles(limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		roots.render(unitPixel);
		main.render(unitPixel);
		rim1.render(unitPixel);
		rim2.render(unitPixel);
		rim3.render(unitPixel);
		rim4.render(unitPixel);
		jawmain1.render(unitPixel);
		jawtip1.render(unitPixel);
		jawmain2.render(unitPixel);
		jawtip2.render(unitPixel);
		jawmain3.render(unitPixel);
		jawtip3.render(unitPixel);
		jawmain4.render(unitPixel);
		jawtip4.render(unitPixel);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		EntitySnapper snapper = (EntitySnapper) entity;
		jawmain1.rotateAngleX = snapper.getDataWatcher().getWatchableObjectFloat(21);
		jawtip1.rotateAngleX = snapper.getDataWatcher().getWatchableObjectFloat(21);
		jawmain2.rotateAngleX = -snapper.getDataWatcher().getWatchableObjectFloat(21);
		jawtip2.rotateAngleX = -snapper.getDataWatcher().getWatchableObjectFloat(21);
		jawmain3.rotateAngleX = -snapper.getDataWatcher().getWatchableObjectFloat(21);
		jawtip3.rotateAngleX = snapper.getDataWatcher().getWatchableObjectFloat(21);
		jawmain4.rotateAngleX = snapper.getDataWatcher().getWatchableObjectFloat(21);
		jawtip4.rotateAngleX = snapper.getDataWatcher().getWatchableObjectFloat(21);
	}
}
