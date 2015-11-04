package erebus.client.model.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityBeetleLarva;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelBeetleLarva extends ModelBase {
	ModelRenderer torso1;
	ModelRenderer torso2;
	ModelRenderer torso3;
	ModelRenderer torso4;
	ModelRenderer torso5;
	ModelRenderer torso6;
	ModelRenderer torso7;
	ModelRenderer torso8;
	ModelRenderer legleft1;
	ModelRenderer legright1;
	ModelRenderer legleft2;
	ModelRenderer legright2;
	ModelRenderer legleft3;
	ModelRenderer legright3;
	ModelRenderer head;
	ModelRenderer jawleft;
	ModelRenderer jawright;
	ModelRenderer mouthjaw;
	ModelRenderer sensorleft;
	ModelRenderer sensorright;
	ModelRenderer horn1;
	ModelRenderer horn2;
	ModelRenderer horn3;
	ModelRenderer titanL1;
	ModelRenderer titanL2;
	ModelRenderer titanR1;
	ModelRenderer titanR2;

	public ModelBeetleLarva() {
		textureWidth = 128;
		textureHeight = 64;

		torso1 = new ModelRenderer(this, 0, 0);
		torso1.addBox(-3F, -2F, 0F, 6, 5, 2);
		torso1.setRotationPoint(0F, 20F, -10F);
		setRotation(torso1, 0.1487144F, 0F, 0F);
		torso2 = new ModelRenderer(this, 0, 8);
		torso2.addBox(-3.5F, -2.5F, 1F, 7, 6, 2);
		torso2.setRotationPoint(0F, 20F, -10F);
		setRotation(torso2, 0.0743572F, 0F, 0F);
		torso3 = new ModelRenderer(this, 0, 17);
		torso3.addBox(-3.5F, -3.5F, 2.5F, 7, 7, 6);
		torso3.setRotationPoint(0F, 20F, -10F);
		setRotation(torso3, -0.0546319F, 0F, 0F);
		torso4 = new ModelRenderer(this, 0, 31);
		torso4.addBox(-3.5F, -3.1F, 8F, 7, 7, 5);
		torso4.setRotationPoint(0F, 20F, -10F);
		setRotation(torso4, 0F, 0F, 0F);
		torso5 = new ModelRenderer(this, 0, 44);
		torso5.addBox(-3.5F, -3.5F, 12.5F, 7, 6, 3);
		torso5.setRotationPoint(0F, 20F, -10F);
		setRotation(torso5, -0.0766292F, 0F, 0F);
		torso6 = new ModelRenderer(this, 0, 54);
		torso6.addBox(-3F, -3.5F, 15.5F, 6, 6, 2);
		torso6.setRotationPoint(0F, 20F, -10F);
		setRotation(torso6, -0.0766374F, 0F, 0F);
		torso7 = new ModelRenderer(this, 17, 54);
		torso7.addBox(-2.5F, -6F, 16.5F, 5, 5, 2);
		torso7.setRotationPoint(0F, 20F, -10F);
		setRotation(torso7, -0.2602503F, 0F, 0F);
		torso8 = new ModelRenderer(this, 32, 54);
		torso8.addBox(-2F, -6.5F, 18F, 4, 4, 1);
		torso8.setRotationPoint(0F, 20F, -10F);
		setRotation(torso8, -0.3346075F, 0F, 0F);
		legleft1 = new ModelRenderer(this, 65, 0);
		legleft1.addBox(-0.5F, 0F, -0.5F, 1, 3, 1);
		legleft1.setRotationPoint(3F, 21.5F, -7F);
		setRotation(legleft1, -0.2230717F, 0.1115358F, -0.1858931F);
		legright1 = new ModelRenderer(this, 70, 0);
		legright1.addBox(-0.5F, 0F, -0.5F, 1, 3, 1);
		legright1.setRotationPoint(-3F, 21.5F, -7F);
		setRotation(legright1, -0.2230705F, -0.111544F, 0.185895F);
		legleft2 = new ModelRenderer(this, 65, 5);
		legleft2.addBox(-0.5F, 0F, -0.5F, 1, 3, 1);
		legleft2.setRotationPoint(3F, 21.5F, -6F);
		setRotation(legleft2, 0F, 0F, -0.3346075F);
		legright2 = new ModelRenderer(this, 70, 5);
		legright2.addBox(-0.5F, 0F, -0.5F, 1, 3, 1);
		legright2.setRotationPoint(-3F, 21.5F, -6F);
		setRotation(legright2, 0F, 0F, 0.3346145F);
		legleft3 = new ModelRenderer(this, 65, 10);
		legleft3.addBox(-0.5F, 0F, -0.5F, 1, 3, 1);
		legleft3.setRotationPoint(3F, 21.5F, -5F);
		setRotation(legleft3, 0.1858931F, -0.2230717F, -0.3346075F);
		legright3 = new ModelRenderer(this, 70, 10);
		legright3.addBox(-0.5F, 0F, -0.5F, 1, 3, 1);
		legright3.setRotationPoint(-3F, 21.5F, -5F);
		setRotation(legright3, 0.1858931F, 0.2230705F, 0.3346145F);
		head = new ModelRenderer(this, 40, 0);
		head.addBox(-2F, -1F, -3F, 4, 3, 4);
		head.setRotationPoint(0F, 20F, -10F);
		setRotation(head, 1.226894F, 0F, 0F);
		jawleft = new ModelRenderer(this, 40, 8);
		jawleft.addBox(1.8F, 0F, -0.5F, 1, 3, 1);
		jawleft.setRotationPoint(0F, 20F, -10F);
		setRotation(jawleft, -0.2602503F, 0F, 0.1858931F);
		jawright = new ModelRenderer(this, 45, 8);
		jawright.addBox(-2.8F, 0F, -0.5F, 1, 3, 1);
		jawright.setRotationPoint(0F, 20F, -10F);
		setRotation(jawright, -0.260246F, 0F, -0.185895F);
		mouthjaw = new ModelRenderer(this, 40, 13);
		mouthjaw.addBox(-1.5F, 2.3F, 1F, 3, 2, 0);
		mouthjaw.setRotationPoint(0F, 20F, -10F);
		setRotation(mouthjaw, -0.6320364F, 0F, 0F);
		sensorleft = new ModelRenderer(this, 40, 16);
		sensorleft.addBox(1F, 0F, -1F, 1, 4, 0);
		sensorleft.setRotationPoint(0F, 20F, -10F);
		setRotation(sensorleft, -0.7063936F, -0.1487144F, 0F);
		sensorright = new ModelRenderer(this, 43, 16);
		sensorright.addBox(-2F, 0F, -1F, 1, 4, 0);
		sensorright.setRotationPoint(0F, 20F, -10F);
		setRotation(sensorright, -0.7063936F, 0.1487195F, 0F);
		horn1 = new ModelRenderer(this, 44, 3);
		horn1.addBox(-1F, -0.5F, -2.6F, 2, 2, 2);
		horn1.setRotationPoint(0F, 20F, -10F);
		setRotation(horn1, 0.3490659F, 0F, 0F);
		horn2 = new ModelRenderer(this, 45, 3);
		horn2.addBox(-0.5F, -1F, -2.4F, 1, 2, 2);
		horn2.setRotationPoint(0F, 20F, -10F);
		setRotation(horn2, -0.1396263F, 0F, 0F);
		horn3 = new ModelRenderer(this, 46, 4);
		horn3.addBox(-0.5F, -3F, -2.4F, 1, 2, 1);
		horn3.setRotationPoint(0F, 20F, -10F);
		setRotation(horn3, -0.1396263F, 0F, 0F);
		titanL1 = new ModelRenderer(this, 45, 3);
		titanL1.addBox(0.5F, -1F, -1.4F, 1, 2, 2);
		titanL1.setRotationPoint(0F, 20F, -10F);
		setRotation(titanL1, -0.1396263F, 0F, 0F);
		titanL2 = new ModelRenderer(this, 44, 0);
		titanL2.addBox(0.5F, -4F, -1.4F, 1, 3, 1);
		titanL2.setRotationPoint(0F, 20F, -10F);
		setRotation(titanL2, -0.1396263F, 0F, 0F);
		titanR1 = new ModelRenderer(this, 45, 3);
		titanR1.addBox(-1.5F, -1F, -1.4F, 1, 2, 2);
		titanR1.setRotationPoint(0F, 20F, -10F);
		setRotation(titanR1, -0.1396263F, 0F, 0F);
		titanR2 = new ModelRenderer(this, 44, 0);
		titanR2.addBox(-1.5F, -4F, -1.4F, 1, 3, 1);
		titanR2.setRotationPoint(0F, 20F, -10F);
		setRotation(titanR2, -0.1396263F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.render(entity, limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		setRotationAngles(limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		EntityBeetleLarva larva = (EntityBeetleLarva) entity;
		torso1.render(unitPixel);
		torso2.render(unitPixel);
		torso3.render(unitPixel);
		torso4.render(unitPixel);
		torso5.render(unitPixel);
		torso6.render(unitPixel);
		torso7.render(unitPixel);
		torso8.render(unitPixel);
		legleft1.render(unitPixel);
		legright1.render(unitPixel);
		legleft2.render(unitPixel);
		legright2.render(unitPixel);
		legleft3.render(unitPixel);
		legright3.render(unitPixel);
		head.render(unitPixel);
		jawleft.render(unitPixel);
		jawright.render(unitPixel);
		mouthjaw.render(unitPixel);
		sensorleft.render(unitPixel);
		sensorright.render(unitPixel);
		if (larva.getTame() == 2) {
			horn1.render(unitPixel);
			horn2.render(unitPixel);
			horn3.render(unitPixel);
		}
		if (larva.getTame() == 3) {
			titanL1.render(unitPixel);
			titanL2.render(unitPixel);
			titanR1.render(unitPixel);
			titanR2.render(unitPixel);
		}

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
		float ba = MathHelper.cos(limbSwing * 1.0F) * 1.5F * prevLimbSwing;
		float bb = MathHelper.cos(limbSwing + 1.0F * 1.0F) * 2.25F * prevLimbSwing;
		float bc = MathHelper.cos(limbSwing + 2.0F * 1.0F) * 3.F * prevLimbSwing;
		float bd = MathHelper.cos(limbSwing + 3.0F * 1.0F) * 2.5F * prevLimbSwing;
		float be = MathHelper.cos(limbSwing + 4.0F * 1.0F) * 1.5F * prevLimbSwing;
		float bf = MathHelper.cos(limbSwing + 5.0F * 1.0F) * 0.75F * prevLimbSwing;

		head.rotationPointX = bf;
		jawleft.rotationPointX = bf;
		jawright.rotationPointX = bf;
		mouthjaw.rotationPointX = bf;
		sensorleft.rotationPointX = bf;
		sensorright.rotationPointX = bf;
		horn1.rotationPointX = bf;
		horn2.rotationPointX = bf;
		horn3.rotationPointX = bf;
		titanL1.rotationPointX = bf;
		titanL2.rotationPointX = bf;
		titanR1.rotationPointX = bf;
		titanR2.rotationPointX = bf;

		torso1.rotationPointY = ba + 20F;

		torso2.rotationPointY = bb + 20F;

		torso3.rotationPointY = bc + 20F;

		torso4.rotationPointY = bd + 20F;

		torso5.rotationPointY = be + 20F;

		torso6.rotationPointY = bf + 20F;

		legright1.rotationPointY = bc + 21F;
		legleft1.rotationPointY = bc + 21F;

		legright2.rotationPointY = bc + 21F;
		legleft2.rotationPointY = bc + 21F;

		legright3.rotationPointY = bc + 21F;
		legleft3.rotationPointY = bc + 21F;

		legright1.rotateAngleX = -MathHelper.cos(limbSwing * 1.0F) * 1.3F * prevLimbSwing;
		legleft1.rotateAngleX = MathHelper.cos(limbSwing * 1.0F) * 1.3F * prevLimbSwing;

		legright1.rotateAngleY = -MathHelper.cos(limbSwing * 1.0F) * 0.5F * prevLimbSwing;
		legleft1.rotateAngleY = MathHelper.cos(limbSwing * 1.0F) * 0.5F * prevLimbSwing;

		legright2.rotateAngleX = -MathHelper.cos(limbSwing * 1.0F + (float) Math.PI) * 1.3F * prevLimbSwing;
		legleft2.rotateAngleX = MathHelper.cos(limbSwing * 1.0F + (float) Math.PI) * 1.3F * prevLimbSwing;

		legright2.rotateAngleY = -MathHelper.cos(limbSwing + 0.5F * 1.0F + (float) Math.PI) * 0.5F * prevLimbSwing;
		legleft2.rotateAngleY = MathHelper.cos(limbSwing + 0.5F * 1.0F + (float) Math.PI) * 0.5F * prevLimbSwing;

		legright3.rotateAngleX = -MathHelper.cos(limbSwing * 1.0F) * 1.3F * prevLimbSwing;
		legleft3.rotateAngleX = MathHelper.cos(limbSwing * 1.0F) * 1.3F * prevLimbSwing;

		legright3.rotateAngleY = -MathHelper.cos(limbSwing + 1.0F * 1.0F) * 0.5F * prevLimbSwing;
		legleft3.rotateAngleY = MathHelper.cos(limbSwing + 1.0F * 1.0F) * 0.5F * prevLimbSwing;
	}

}