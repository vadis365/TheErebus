package erebus.client.model.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelVelvetWorm extends ModelBase {

	ModelRenderer Head1;
	ModelRenderer Head2;
	ModelRenderer Head3;
	ModelRenderer BodA1;
	ModelRenderer RLA1;
	ModelRenderer LLA1;
	ModelRenderer BodB1;
	ModelRenderer RLB1;
	ModelRenderer LLB1;
	ModelRenderer BodC1;
	ModelRenderer RLC1;
	ModelRenderer LLC1;
	ModelRenderer BodD1;
	ModelRenderer RLD1;
	ModelRenderer LLD1;
	ModelRenderer BodE1;
	ModelRenderer RLE1;
	ModelRenderer LLE1;
	ModelRenderer BodF1;
	ModelRenderer BodF2;
	ModelRenderer RAnt;
	ModelRenderer LAnt;

	public ModelVelvetWorm() {
		textureWidth = 64;
		textureHeight = 32;
		Head1 = new ModelRenderer(this, 21, 19);
		Head1.addBox(-2.5F, -1.5F, -5F, 5, 4, 5);
		Head1.setRotationPoint(0F, 21F, -10F);
		setRotation(Head1, 0F, 0F, 0F);
		Head2 = new ModelRenderer(this, 26, 12);
		Head2.addBox(-2.5F, 1.5F, -7F, 1, 1, 2);
		Head2.setRotationPoint(0F, 21F, -10F);
		setRotation(Head2, 0F, 0F, 0F);
		Head3 = new ModelRenderer(this, 26, 12);
		Head3.addBox(1.5F, 1.5F, -7F, 1, 1, 2);
		Head3.setRotationPoint(0F, 21F, -10F);
		setRotation(Head3, 0F, 0F, 0F);
		BodA1 = new ModelRenderer(this, 40, 0);
		BodA1.addBox(-3F, -2.5F, -3F, 6, 5, 5);
		BodA1.setRotationPoint(0F, 21F, -7F);
		setRotation(BodA1, 0F, 0F, 0F);
		RLA1 = new ModelRenderer(this, 0, 0);
		RLA1.addBox(-1F, -1F, -1F, 4, 2, 2);
		RLA1.setRotationPoint(-5F, 21.5F, -7F);
		setRotation(RLA1, 0F, 3.141593F, -0.4363323F);
		LLA1 = new ModelRenderer(this, 0, 0);
		LLA1.addBox(-1F, -1F, -1F, 4, 2, 2);
		LLA1.setRotationPoint(3F, 21.5F, -7F);
		setRotation(LLA1, 0F, 0F, 0.4363323F);
		BodB1 = new ModelRenderer(this, 0, 7);
		BodB1.addBox(-3F, -3.5F, -3F, 6, 6, 5);
		BodB1.setRotationPoint(0F, 21F, -2F);
		setRotation(BodB1, 0F, 0F, 0F);
		RLB1 = new ModelRenderer(this, 0, 0);
		RLB1.addBox(-1F, -1F, -1F, 4, 2, 2);
		RLB1.setRotationPoint(-5F, 21.5F, -2F);
		setRotation(RLB1, 0F, 3.141593F, -0.4363323F);
		LLB1 = new ModelRenderer(this, 0, 0);
		LLB1.addBox(-1F, -1F, -1F, 4, 2, 2);
		LLB1.setRotationPoint(3F, 21.5F, -2F);
		setRotation(LLB1, 0F, 0F, 0.4363323F);
		BodC1 = new ModelRenderer(this, 0, 7);
		BodC1.addBox(-3F, -3.5F, -3F, 6, 6, 5);
		BodC1.setRotationPoint(0F, 21F, 3F);
		setRotation(BodC1, 0F, 0F, 0F);
		RLC1 = new ModelRenderer(this, 0, 0);
		RLC1.addBox(-1F, -1F, -1F, 4, 2, 2);
		RLC1.setRotationPoint(-5F, 21.5F, 3F);
		setRotation(RLC1, 0F, 3.141593F, -0.4363323F);
		LLC1 = new ModelRenderer(this, 0, 0);
		LLC1.addBox(-1F, -1F, -1F, 4, 2, 2);
		LLC1.setRotationPoint(3F, 21.5F, 3F);
		setRotation(LLC1, 0F, 0F, 0.4363323F);
		BodD1 = new ModelRenderer(this, 40, 0);
		BodD1.addBox(-3F, -2.5F, -3F, 6, 5, 5);
		BodD1.setRotationPoint(0F, 21F, 8F);
		setRotation(BodD1, 0F, 0F, 0F);
		RLD1 = new ModelRenderer(this, 0, 0);
		RLD1.addBox(-1F, -1F, -1F, 4, 2, 2);
		RLD1.setRotationPoint(-5F, 21.5F, 8F);
		setRotation(RLD1, 0F, 3.141593F, -0.4363323F);
		LLD1 = new ModelRenderer(this, 0, 0);
		LLD1.addBox(-1F, -1F, -1F, 4, 2, 2);
		LLD1.setRotationPoint(3F, 21.5F, 8F);
		setRotation(LLD1, 0F, 0F, 0.4363323F);
		BodE1 = new ModelRenderer(this, 40, 11);
		BodE1.addBox(-3F, -1.5F, -3F, 6, 4, 5);
		BodE1.setRotationPoint(0F, 21F, 13F);
		setRotation(BodE1, 0F, 0F, 0F);
		RLE1 = new ModelRenderer(this, 0, 0);
		RLE1.addBox(-1F, -1F, -1F, 4, 2, 2);
		RLE1.setRotationPoint(-5F, 21.5F, 13F);
		setRotation(RLE1, 0F, 3.141593F, -0.4363323F);
		LLE1 = new ModelRenderer(this, 0, 0);
		LLE1.addBox(-1F, -1F, -1F, 4, 2, 2);
		LLE1.setRotationPoint(3F, 21.5F, 13F);
		setRotation(LLE1, 0F, 0F, 0.4363323F);
		BodF1 = new ModelRenderer(this, 0, 20);
		BodF1.addBox(-2F, -0.5F, -3F, 4, 3, 5);
		BodF1.setRotationPoint(0F, 21F, 18F);
		setRotation(BodF1, 0F, 0F, 0F);
		BodF2 = new ModelRenderer(this, 38, 21);
		BodF2.addBox(-3F, 1.5F, -3F, 6, 0, 7);
		BodF2.setRotationPoint(0F, 21F, 18F);
		setRotation(BodF2, 0F, 0F, 0F);
		RAnt = new ModelRenderer(this, 23, 0);
		RAnt.addBox(-1.5F, -1F, -12F, 1, 1, 7);
		RAnt.setRotationPoint(0F, 21F, -10F);
		setRotation(RAnt, 0F, 0.1745329F, 0F);
		LAnt = new ModelRenderer(this, 23, 0);
		LAnt.addBox(0.5F, -1F, -12F, 1, 1, 7);
		LAnt.setRotationPoint(0F, 21F, -10F);
		setRotation(LAnt, 0F, -0.1745329F, 0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.render(entity, limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		setRotationAngles(limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		Head1.render(unitPixel);
		Head2.render(unitPixel);
		Head3.render(unitPixel);
		BodA1.render(unitPixel);
		RLA1.render(unitPixel);
		LLA1.render(unitPixel);
		BodB1.render(unitPixel);
		RLB1.render(unitPixel);
		LLB1.render(unitPixel);
		BodC1.render(unitPixel);
		RLC1.render(unitPixel);
		LLC1.render(unitPixel);
		BodD1.render(unitPixel);
		RLD1.render(unitPixel);
		LLD1.render(unitPixel);
		BodE1.render(unitPixel);
		RLE1.render(unitPixel);
		LLE1.render(unitPixel);
		BodF1.render(unitPixel);
		BodF2.render(unitPixel);
		RAnt.render(unitPixel);
		LAnt.render(unitPixel);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {

		float ba = MathHelper.cos(limbSwing * 1.0F) * 1.0F * prevLimbSwing;
		float bb = MathHelper.cos(limbSwing + 1.0F * 1.0F) * 2.0F * prevLimbSwing;
		float bc = MathHelper.cos(limbSwing + 2.0F * 1.0F) * 2.6F * prevLimbSwing;
		float bd = MathHelper.cos(limbSwing + 3.0F * 1.0F) * 2.0F * prevLimbSwing;
		float be = MathHelper.cos(limbSwing + 4.0F * 1.0F) * 1.0F * prevLimbSwing;
		float bf = MathHelper.cos(limbSwing + 5.0F * 1.0F) * 0.35F * prevLimbSwing;

		Head1.rotationPointX = bf;
		Head2.rotationPointX = bf;
		Head3.rotationPointX = bf;
		RAnt.rotationPointX = bf;
		LAnt.rotationPointX = bf;

		BodA1.rotationPointY = ba + 20F;

		BodB1.rotationPointY = bb + 20F;

		BodC1.rotationPointY = bc + 20F;

		BodD1.rotationPointY = bd + 20F;

		BodE1.rotationPointY = be + 20F;

		BodF1.rotationPointY = bf + 20F;
		BodF2.rotationPointY = bf + 20F;

		RLA1.rotationPointY = ba + 21.5F;
		LLA1.rotationPointY = ba + 21F;

		RLB1.rotationPointY = bb + 21.5F;
		LLB1.rotationPointY = bb + 21F;

		RLC1.rotationPointY = bc + 21.5F;
		LLC1.rotationPointY = bc + 21F;

		RLD1.rotationPointY = bd + 21.5F;
		LLD1.rotationPointY = bd + 21F;

		RLE1.rotationPointY = be + 21.5F;
		LLE1.rotationPointY = be + 21F;

		RLA1.rotateAngleY = -MathHelper.cos(limbSwing * 1.0F) * 1.0F * prevLimbSwing;
		LLA1.rotateAngleY = MathHelper.cos(limbSwing * 1.0F) * 1.0F * prevLimbSwing;

		RLB1.rotateAngleY = MathHelper.cos(limbSwing * 1.0F) * 1.0F * prevLimbSwing;
		LLB1.rotateAngleY = -MathHelper.cos(limbSwing * 1.0F) * 1.0F * prevLimbSwing;

		RLC1.rotateAngleY = -MathHelper.cos(limbSwing * 1.0F) * 1.0F * prevLimbSwing;
		LLC1.rotateAngleY = MathHelper.cos(limbSwing * 1.0F) * 1.0F * prevLimbSwing;

		RLD1.rotateAngleY = MathHelper.cos(limbSwing * 1.0F) * 1.0F * prevLimbSwing;
		LLD1.rotateAngleY = -MathHelper.cos(limbSwing * 1.0F) * 1.0F * prevLimbSwing;

		RLE1.rotateAngleY = -MathHelper.cos(limbSwing * 1.0F) * 1.0F * prevLimbSwing;
		LLE1.rotateAngleY = MathHelper.cos(limbSwing * 1.0F) * 1.0F * prevLimbSwing;

		Head1.rotateAngleY = rotationYaw / (180F / (float) Math.PI);
		Head2.rotateAngleY = rotationYaw / (180F / (float) Math.PI);
		Head3.rotateAngleY = rotationYaw / (180F / (float) Math.PI);
		RAnt.rotateAngleY = rotationYaw / (180F / (float) Math.PI) + 0.175F;
		LAnt.rotateAngleY = rotationYaw / (180F / (float) Math.PI) - 0.175F;

		Head1.rotateAngleX = rotationPitch / (180F / (float) Math.PI);
		Head2.rotateAngleX = rotationPitch / (180F / (float) Math.PI);
		Head3.rotateAngleX = rotationPitch / (180F / (float) Math.PI);
		RAnt.rotateAngleX = rotationPitch / (180F / (float) Math.PI);
		LAnt.rotateAngleX = rotationPitch / (180F / (float) Math.PI);
		BodF1.rotateAngleX = rotationPitch / (180F / (float) Math.PI);
		BodF2.rotateAngleX = rotationPitch / (180F / (float) Math.PI);
	}
}
