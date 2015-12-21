package erebus.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelArmchair extends ModelBase {
	ModelRenderer back;
	ModelRenderer seat;
	ModelRenderer leftArm;
	ModelRenderer leftSide;
	ModelRenderer rightArm;
	ModelRenderer rightSide;
	ModelRenderer legFrontLeft;
	ModelRenderer legBackLeft;
	ModelRenderer legFrontRight;
	ModelRenderer legBackRight;

	public ModelArmchair() {
		textureWidth = 64;
		textureHeight = 64;

		back = new ModelRenderer(this, 29, 17);
		back.addBox(-7F, 0F, 5F, 14, 14, 3);
		back.setRotationPoint(0F, 8F, 0F);
		setRotation(back, 0F, 0F, 0F);
		seat = new ModelRenderer(this, 9, 0);
		seat.addBox(-5F, 9F, -6F, 10, 5, 11);
		seat.setRotationPoint(0F, 8F, 0F);
		setRotation(seat, 0F, 0F, 0F);
		leftArm = new ModelRenderer(this, 0, 36);
		leftArm.addBox(5F, 5F, -8F, 3, 3, 14);
		leftArm.setRotationPoint(0F, 8F, 0F);
		setRotation(leftArm, 0F, 0F, 0F);
		leftSide = new ModelRenderer(this, 0, 17);
		leftSide.addBox(5F, 8F, -7F, 2, 6, 12);
		leftSide.setRotationPoint(0F, 8F, 0F);
		setRotation(leftSide, 0F, 0F, 0F);
		rightArm = new ModelRenderer(this, 0, 36);
		rightArm.addBox(-8F, 5F, -8F, 3, 3, 14);
		rightArm.setRotationPoint(0F, 8F, 0F);
		setRotation(rightArm, 0F, 0F, 0F);
		rightSide = new ModelRenderer(this, 0, 17);
		rightSide.addBox(-7F, 8F, -7F, 2, 6, 12);
		rightSide.setRotationPoint(0F, 8F, 0F);
		setRotation(rightSide, 0F, 0F, 0F);
		legFrontLeft = new ModelRenderer(this, 0, 0);
		legFrontLeft.addBox(5F, 14F, -7F, 2, 2, 2);
		legFrontLeft.setRotationPoint(0F, 8F, 0F);
		setRotation(legFrontLeft, 0F, 0F, 0F);
		legBackLeft = new ModelRenderer(this, 0, 0);
		legBackLeft.addBox(5F, 14F, 6F, 2, 2, 2);
		legBackLeft.setRotationPoint(0F, 8F, 0F);
		setRotation(legBackLeft, 0F, 0F, 0F);
		legFrontRight = new ModelRenderer(this, 0, 0);
		legFrontRight.addBox(-7F, 14F, -7F, 2, 2, 2);
		legFrontRight.setRotationPoint(0F, 8F, 0F);
		setRotation(legFrontRight, 0F, 0F, 0F);
		legBackRight = new ModelRenderer(this, 0, 0);
		legBackRight.addBox(-7F, 14F, 6F, 2, 2, 2);
		legBackRight.setRotationPoint(0F, 8F, 0F);
		setRotation(legBackRight, 0F, 0F, 0F);
	}

	public void render() {
		back.render(0.0625F);
		seat.render(0.0625F);
		leftArm.render(0.0625F);
		leftSide.render(0.0625F);
		rightArm.render(0.0625F);
		rightSide.render(0.0625F);
		legFrontLeft.render(0.0625F);
		legBackLeft.render(0.0625F);
		legFrontRight.render(0.0625F);
		legBackRight.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
