package erebus.client.model.armor;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelRhinoHelm extends ModelBiped {

	ModelRenderer helmFront;
	ModelRenderer helmTop;
	ModelRenderer helmRight;
	ModelRenderer helmLeft;
	ModelRenderer helmBack;
	ModelRenderer crestTop;
	ModelRenderer crestBack;
	ModelRenderer rHorn1;
	ModelRenderer rHorn2;
	ModelRenderer rHorn3;
	ModelRenderer lHorn1;
	ModelRenderer lHorn2;
	ModelRenderer lHorn3;
	ModelRenderer rTopPlate;
	ModelRenderer lTopPlate;
	ModelRenderer rEarCup;
	ModelRenderer lEarCup;
	ModelRenderer facePlate1;
	ModelRenderer facePlate2;

	public ModelRhinoHelm() {
		textureWidth = 64;
		textureHeight = 32;

		helmFront = new ModelRenderer(this, 22, 0);
		helmFront.addBox(-4.5F, -8F, -5.5F, 9, 2, 1);
		helmFront.setRotationPoint(0F, 0F, 0F);
		setRotation(helmFront, 0F, 0F, 0F);
		helmTop = new ModelRenderer(this, 17, 4);
		helmTop.addBox(-3.5F, -8F, -4.5F, 7, 1, 8);
		setRotation(helmTop, 0F, 0F, 0F);
		helmRight = new ModelRenderer(this, 46, 8);
		helmRight.addBox(-4.5F, -8F, -4.5F, 1, 8, 8);
		setRotation(helmRight, 0F, 0F, 0F);
		helmLeft = new ModelRenderer(this, 1, 8);
		helmLeft.addBox(3.5F, -8F, -4.5F, 1, 8, 8);
		setRotation(helmLeft, 0F, 0F, 0F);
		helmBack = new ModelRenderer(this, 25, 15);
		helmBack.addBox(-4.5F, -8F, 3.5F, 9, 8, 1);
		setRotation(helmBack, 0F, 0F, 0F);
		crestTop = new ModelRenderer(this, 8, 18);
		crestTop.addBox(-1F, -10F, -5.5F, 2, 2, 12);
		setRotation(crestTop, 0F, 0F, 0F);
		crestBack = new ModelRenderer(this, 0, 5);
		crestBack.addBox(-1F, -8F, 4.5F, 2, 8, 2);
		setRotation(crestBack, 0F, 0F, 0F);
		rHorn1 = new ModelRenderer(this, 43, 0);
		rHorn1.addBox(-3F, -14.3F, -1.5F, 2, 6, 2);
		setRotation(rHorn1, 0.3490659F, 0.1745329F, 0F);
		rHorn2 = new ModelRenderer(this, 52, 0);
		rHorn2.addBox(-2.5F, -13.5F, -7.5F, 1, 2, 2);
		setRotation(rHorn2, -0.1396263F, 0.1745329F, 0F);
		rHorn3 = new ModelRenderer(this, 59, 0);
		rHorn3.addBox(-2.5F, -16.5F, -7.5F, 1, 3, 1);
		setRotation(rHorn3, -0.1396263F, 0.1745329F, 0F);
		lHorn1 = new ModelRenderer(this, 13, 0);
		lHorn1.addBox(1F, -14.3F, -1.5F, 2, 6, 2);
		setRotation(lHorn1, 0.3490659F, -0.1745329F, 0F);
		lHorn2 = new ModelRenderer(this, 6, 0);
		lHorn2.addBox(1.5F, -13.5F, -7.5F, 1, 2, 2);
		setRotation(lHorn2, -0.1396263F, -0.1745329F, 0F);
		lHorn3 = new ModelRenderer(this, 1, 0);
		lHorn3.addBox(1.5F, -16.5F, -7.5F, 1, 3, 1);
		setRotation(lHorn3, -0.1396263F, -0.1745329F, 0F);
		rTopPlate = new ModelRenderer(this, 45, 25);
		rTopPlate.addBox(-3F, -6.8F, 4.5F, 1, 3, 3);
		setRotation(rTopPlate, 0.7853982F, 0F, 0F);
		lTopPlate = new ModelRenderer(this, 45, 25);
		lTopPlate.addBox(2F, -6.8F, 4.5F, 1, 3, 3);
		setRotation(lTopPlate, 0.7853982F, 0F, 0F);
		rEarCup = new ModelRenderer(this, 54, 25);
		rEarCup.addBox(-5.5F, -4F, 1.5F, 1, 3, 3);
		setRotation(rEarCup, 0.7853982F, 0F, 0F);
		lEarCup = new ModelRenderer(this, 54, 25);
		lEarCup.addBox(4.5F, -4F, 1.5F, 1, 3, 3);
		setRotation(lEarCup, 0.7853982F, 0F, 0F);
		facePlate1 = new ModelRenderer(this, 25, 25);
		facePlate1.addBox(-4.5F, -4F, -5.5F, 9, 4, 1);
		setRotation(facePlate1, 0F, 0F, 0F);
		facePlate2 = new ModelRenderer(this, 0, 24);
		facePlate2.addBox(-2F, -4F, -7.5F, 4, 4, 2);
		setRotation(facePlate2, 0F, 0F, 0F);

		helmFront.addChild(helmTop);
		helmFront.addChild(helmRight);
		helmFront.addChild(helmLeft);
		helmFront.addChild(helmBack);
		helmFront.addChild(crestTop);
		helmFront.addChild(crestBack);
		helmFront.addChild(rHorn1);
		helmFront.addChild(rHorn2);
		helmFront.addChild(rHorn3);
		helmFront.addChild(lHorn1);
		helmFront.addChild(lHorn2);
		helmFront.addChild(lHorn3);
		helmFront.addChild(rTopPlate);
		helmFront.addChild(lTopPlate);
		helmFront.addChild(rEarCup);
		helmFront.addChild(lEarCup);
		helmFront.addChild(facePlate1);
		helmFront.addChild(facePlate2);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.render(entity, limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		setRotationAngles(limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		GL11.glPushMatrix();
		GL11.glScalef(1.2F, 1F, 1.2F);
		helmFront.render(unitPixel);
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
		helmFront.rotateAngleY = bipedHead.rotateAngleY;
		helmFront.rotateAngleX = bipedHead.rotateAngleX;
	}
}
