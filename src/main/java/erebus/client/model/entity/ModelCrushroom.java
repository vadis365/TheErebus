package erebus.client.model.entity;

import erebus.entity.EntityCrushroom;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelCrushroom extends ModelBase {
	ModelRenderer capTop;
	ModelRenderer capBottom;
	ModelRenderer head;
	ModelRenderer chest;
	ModelRenderer rightArm1;
	ModelRenderer rightArm2;
	ModelRenderer rightFist;
	ModelRenderer leftArm1;
	ModelRenderer leftArm2;
	ModelRenderer leftFist;
	ModelRenderer belly;
	ModelRenderer hips;
	ModelRenderer rightThigh;
	ModelRenderer rightAnkle;
	ModelRenderer rightFoot1;
	ModelRenderer rightFoot2;
	ModelRenderer leftThigh;
	ModelRenderer leftAnkle;
	ModelRenderer leftFoot1;
	ModelRenderer leftFoot2;

	public ModelCrushroom() {
		textureWidth = 128;
		textureHeight = 128;

		capTop = new ModelRenderer(this, 39, 0);
		capTop.addBox(-6F, -8F, -11F, 12, 2, 12);
		capTop.setRotationPoint(0F, 1F, 0F);
		setRotation(capTop, 0F, 0F, 0F);
		capBottom = new ModelRenderer(this, 30, 15);
		capBottom.addBox(-8F, -7F, -13F, 16, 3, 16);
		capBottom.setRotationPoint(0F, 2F, 0F);
		setRotation(capBottom, 0F, 0F, 0F);
		head = new ModelRenderer(this, 46, 35);
		head.addBox(-4F, -4F, -9F, 8, 10, 8);
		head.setRotationPoint(0F, 2F, 0F);
		setRotation(head, 0F, 0F, 0F);
		chest = new ModelRenderer(this, 34, 54);
		chest.addBox(-9F, -14F, -4F, 18, 8, 10);
		chest.setRotationPoint(0F, 10F, 7F);
		setRotation(chest, 1.047198F, 0F, 0F);
		rightArm1 = new ModelRenderer(this, 7, 43);
		rightArm1.addBox(-5F, -2F, -2.5F, 5, 10, 5);
		rightArm1.setRotationPoint(-9F, 2F, -2F);
		setRotation(rightArm1, 0F, 0F, 0F);
		rightArm2 = new ModelRenderer(this, 9, 59);
		rightArm2.addBox(-4.5F, 5F, 2F, 4, 7, 4);
		setRotation(rightArm2, -0.7504916F, 0F, 0F);
		rightFist = new ModelRenderer(this, 5, 71);
		rightFist.addBox(-4.5F, 12F, 1F, 6, 6, 6);
		setRotation(rightFist, -0.7504916F, 0F, 0F);
		leftArm1 = new ModelRenderer(this, 101, 43);
		leftArm1.addBox(0F, -2F, -2.5F, 5, 10, 5);
		leftArm1.setRotationPoint(9F, 2F, -2F);
		setRotation(leftArm1, 0F, 0F, 0F);
		leftArm2 = new ModelRenderer(this, 103, 59);
		leftArm2.addBox(0.5F, 5F, 2F, 4, 7, 4);
		setRotation(leftArm2, -0.7504916F, 0F, 0F);
		leftFist = new ModelRenderer(this, 99, 71);
		leftFist.addBox(-1.5F, 12F, 1F, 6, 6, 6);
		setRotation(leftFist, -0.7504916F, 0F, 0F);
		belly = new ModelRenderer(this, 44, 73);
		belly.addBox(-5F, -6F, -3F, 10, 7, 8);
		belly.setRotationPoint(0F, 10F, 7F);
		setRotation(belly, 1.047198F, 0F, 0F);
		hips = new ModelRenderer(this, 42, 89);
		hips.addBox(-6F, -4F, -4F, 12, 8, 9);
		hips.setRotationPoint(0F, 10F, 7F);
		setRotation(hips, 0F, 0F, 0F);
		rightThigh = new ModelRenderer(this, 7, 84);
		rightThigh.addBox(-2.5F, -1F, -2.5F, 5, 7, 5);
		rightThigh.setRotationPoint(-5F, 11F, 7F);
		setRotation(rightThigh, 0F, 0F, 0.7853982F);
		rightAnkle = new ModelRenderer(this, 9, 97);
		rightAnkle.addBox(-5.5F, 3F, -2F, 4, 5, 4);
		setRotation(rightAnkle, 0F, 0F, -0.7853982F);
		rightFoot1 = new ModelRenderer(this, 5, 107);
		rightFoot1.addBox(-6.5F, 8F, -3F, 6, 2, 6);
		setRotation(rightFoot1, 0F, 0F, -0.7853982F);
		rightFoot2 = new ModelRenderer(this, 1, 116);
		rightFoot2.addBox(-7.5F, 10F, -4F, 8, 3, 8);
		setRotation(rightFoot2, 0F, 0F, -0.7853982F);
		leftThigh = new ModelRenderer(this, 101, 84);
		leftThigh.addBox(-2.5F, -1F, -2.5F, 5, 7, 5);
		leftThigh.setRotationPoint(5F, 11F, 7F);
		setRotation(leftThigh, 0F, 0F, -0.7853982F);
		leftAnkle = new ModelRenderer(this, 103, 97);
		leftAnkle.addBox(1.5F, 3F, -2F, 4, 5, 4);
		setRotation(leftAnkle, 0F, 0F, 0.7853982F);
		leftFoot1 = new ModelRenderer(this, 99, 107);
		leftFoot1.addBox(0.5F, 8F, -3F, 6, 2, 6);
		setRotation(leftFoot1, 0F, 0F, 0.7853982F);
		leftFoot2 = new ModelRenderer(this, 95, 116);
		leftFoot2.addBox(-0.5F, 10F, -4F, 8, 3, 8);
		setRotation(leftFoot2, 0F, 0F, 0.7853982F);

		rightArm1.addChild(rightArm2);
		rightArm1.addChild(rightFist);

		leftArm1.addChild(leftArm2);
		leftArm1.addChild(leftFist);

		rightThigh.addChild(rightAnkle);
		rightThigh.addChild(rightFoot1);
		rightThigh.addChild(rightFoot2);

		leftThigh.addChild(leftAnkle);
		leftThigh.addChild(leftFoot1);
		leftThigh.addChild(leftFoot2);

		chest.addChild(capTop);
		chest.addChild(capBottom);
		chest.addChild(head);
		chest.addChild(rightArm1);
		chest.addChild(leftArm1);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.render(entity, limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		setRotationAngles(limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);

		chest.render(unitPixel);
		belly.render(unitPixel);
		hips.render(unitPixel);
		rightThigh.render(unitPixel);
		leftThigh.render(unitPixel);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		EntityCrushroom crushroom = (EntityCrushroom) entity;
		float movcos1 = MathHelper.cos(limbSwing * 0.7F) * 0.3F * limbSwingAngle;
		float movsin1 = MathHelper.sin(limbSwing * 0.7F) * 0.3F * limbSwingAngle;
		float hitSwing = MathHelper.sin(-crushroom.getSmashCount() * 0.1F);

		rightArm1.setRotationPoint(-9F, -10F, 1F);
		leftArm1.setRotationPoint(9F, -10F, 1F);

		rightArm1.rotateAngleZ = -movcos1 * 0.5F;
		leftArm1.rotateAngleZ = movcos1 * 0.5F;
		rightThigh.rotateAngleX = -movsin1;
		leftThigh.rotateAngleX = movsin1;
		rightThigh.rotateAngleY = -movcos1;
		leftThigh.rotateAngleY = -movcos1;

		if (crushroom.getStanding() == 1) {
			capTop.setRotationPoint(0F, -14F, 1F);
			capBottom.setRotationPoint(0F, -14F, 1F);
			head.setRotationPoint(0F, -14F, 1F);
			capTop.rotateAngleX = rotationPitch / (180F / (float) Math.PI);
			capBottom.rotateAngleX = rotationPitch / (180F / (float) Math.PI);
			head.rotateAngleX = rotationPitch / (180F / (float) Math.PI);
			chest.rotateAngleX = 0F;
			belly.rotateAngleX = 0F;
			rightArm1.rotateAngleX = movsin1;
			leftArm1.rotateAngleX = -movsin1;
		}

		if (crushroom.getStanding() == 0) {
			capTop.setRotationPoint(0F, -12F, 3F);
			capBottom.setRotationPoint(0F, -12F, 3F);
			head.setRotationPoint(0F, -12F, 3F);
			capTop.rotateAngleX = rotationPitch / (180F / (float) Math.PI) - 1.047198F;
			capBottom.rotateAngleX = rotationPitch / (180F / (float) Math.PI) - 1.047198F;
			head.rotateAngleX = rotationPitch / (180F / (float) Math.PI) - 1.047198F;
			chest.rotateAngleX = 1.047198F;
			belly.rotateAngleX = 1.047198F;
			rightArm1.rotateAngleX = movsin1 - 1.047198F;
			leftArm1.rotateAngleX = -movsin1 - 1.047198F;
		}

		if (crushroom.getStanding() == 2) {
			capTop.setRotationPoint(0F, -14F, 1F);
			capBottom.setRotationPoint(0F, -14F, 1F);
			head.setRotationPoint(0F, -14F, 1F);
			capTop.rotateAngleX = rotationPitch / (180F / (float) Math.PI) + hitSwing * 0.5F;
			capBottom.rotateAngleX = rotationPitch / (180F / (float) Math.PI) + hitSwing * 0.5F;
			head.rotateAngleX = rotationPitch / (180F / (float) Math.PI) + hitSwing * 0.5F;
			chest.rotateAngleX = hitSwing * 0.5F;
			belly.rotateAngleX = hitSwing * 0.5F;
			rightArm1.rotateAngleX = hitSwing * 3F;
			leftArm1.rotateAngleX = hitSwing * 3F;
		}

		if (crushroom.getStanding() == 3) {
			capTop.setRotationPoint(0F, -14F, 1F);
			capBottom.setRotationPoint(0F, -14F, 1F);
			head.setRotationPoint(0F, -14F, 1F);
			capTop.rotateAngleX = rotationPitch / (180F / (float) Math.PI) + hitSwing * 2F;
			capBottom.rotateAngleX = rotationPitch / (180F / (float) Math.PI) + hitSwing * 2F;
			head.rotateAngleX = rotationPitch / (180F / (float) Math.PI) + hitSwing * 2F;
			chest.rotateAngleX = -hitSwing * 2F;
			belly.rotateAngleX = -hitSwing * 2F;
			rightArm1.rotateAngleX = hitSwing * 2.5F;
			leftArm1.rotateAngleX = hitSwing * 2.5F;
		}
	}
}
