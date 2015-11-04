package erebus.client.model.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelMushroomHelm extends ModelBiped {

	ModelRenderer capTop;
	ModelRenderer capMiddle;
	ModelRenderer capFront;
	ModelRenderer capBack;
	ModelRenderer capRight;
	ModelRenderer capLeft;
	ModelRenderer mainHead;

	public ModelMushroomHelm() {
		textureWidth = 64;
		textureHeight = 64;

		capTop = new ModelRenderer(this, 28, 0);
		capTop.addBox(-4.5F, -12F, -4.5F, 9, 1, 9);
		capTop.setRotationPoint(0F, 0F, 0F);
		setRotation(capTop, 0F, 0F, 0F);
		capMiddle = new ModelRenderer(this, 6, 21);
		capMiddle.addBox(-6.5F, -11F, -6.5F, 13, 2, 13);
		setRotation(capMiddle, 0F, 0F, 0F);
		capFront = new ModelRenderer(this, 0, 37);
		capFront.addBox(-8.5F, -9F, -8.5F, 17, 2, 2);
		setRotation(capFront, 0F, 0F, 0F);
		capBack = new ModelRenderer(this, 0, 42);
		capBack.addBox(-8.5F, -9F, 6.5F, 17, 2, 2);
		setRotation(capBack, 0F, 0F, 0F);
		capRight = new ModelRenderer(this, 33, 48);
		capRight.addBox(-8.5F, -9F, -6.5F, 2, 2, 13);
		setRotation(capRight, 0F, 0F, 0F);
		capLeft = new ModelRenderer(this, 1, 48);
		capLeft.addBox(6.5F, -9F, -6.5F, 2, 2, 13);
		setRotation(capLeft, 0F, 0F, 0F);
		mainHead = new ModelRenderer(this, 0, 2);
		mainHead.addBox(-4.5F, -9F, -4.5F, 9, 9, 9);
		setRotation(mainHead, 0F, 0F, 0F);

		capTop.addChild(capMiddle);
		capTop.addChild(capFront);
		capTop.addChild(capBack);
		capTop.addChild(capRight);
		capTop.addChild(capLeft);
		capTop.addChild(mainHead);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.render(entity, limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		setRotationAngles(limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		capTop.render(unitPixel);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		capTop.rotateAngleY = bipedHead.rotateAngleY;
		capTop.rotateAngleX = bipedHead.rotateAngleX;
	}
}
