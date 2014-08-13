package erebus.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBloodSnail extends ModelBase
{
	ModelRenderer shellMain;
	ModelRenderer shellBack;
	ModelRenderer shellMid;
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer feelerR;
	ModelRenderer foot;
	ModelRenderer feelerL;

	public ModelBloodSnail()
	{
		textureWidth = 64;
		textureHeight = 128;

		shellMain = new ModelRenderer(this, 0, 40);
		shellMain.addBox(-6F, -3F, -7F, 13, 10, 13);
		shellMain.setRotationPoint(0F, 14F, 0F);
		setRotation(shellMain, 1.710216F, 0F, 0F);

		shellBack = new ModelRenderer(this, 36, 28);
		shellBack.addBox(-2F, 13F, -3F, 7, 3, 7);
		shellBack.setRotationPoint(0F, 14F, 0F);
		setRotation(shellBack, 1.710216F, 0F, 0F);

		shellMid = new ModelRenderer(this, 0, 0);
		shellMid.addBox(-4F, 7F, -5F, 10, 6, 10);
		shellMid.setRotationPoint(0F, 14F, 0F);
		setRotation(shellMid, 1.710216F, 0F, 0F);

		head = new ModelRenderer(this, 0, 63);
		head.addBox(-3F, -5F, -5F, 6, 7, 8);
		head.setRotationPoint(0F, 18F, -5F);
		setRotation(head, 0F, 0F, 0F);

		body = new ModelRenderer(this, 0, 17);
		body.addBox(-3.5F, -8F, -4F, 7, 17, 6);
		body.setRotationPoint(0F, 18F, 0F);
		setRotation(body, 1.570796F, 0F, 0F);

		feelerR = new ModelRenderer(this, 46, 58);
		feelerR.addBox(-2F, -3F, -11F, 1, 1, 8);
		feelerR.setRotationPoint(0F, 18F, -5F);
		setRotation(feelerR, -0.1396263F, 0.2094395F, 0F);

		foot = new ModelRenderer(this, 40, 0);
		foot.addBox(-5F, -11F, -6F, 10, 25, 2);
		foot.setRotationPoint(0F, 18F, 0F);
		setRotation(foot, 1.570796F, 0F, 0F);

		feelerL = new ModelRenderer(this, 46, 58);
		feelerL.addBox(1F, -3F, -11F, 1, 1, 8);
		feelerL.setRotationPoint(0F, 18F, -5F);
		setRotation(feelerL, -0.1396263F, -0.2094395F, 0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel)
	{
		super.render(entity, limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		setRotationAngles(limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		shellMain.render(unitPixel);
		shellBack.render(unitPixel);
		shellMid.render(unitPixel);
		head.render(unitPixel);
		body.render(unitPixel);
		feelerR.render(unitPixel);
		foot.render(unitPixel);
		feelerL.render(unitPixel);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity)
	{
		super.setRotationAngles(limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		feelerL.rotateAngleX = MathHelper.cos(limbSwing * 1F + (float) Math.PI) * 0.5F * prevLimbSwing;
		feelerR.rotateAngleX = MathHelper.cos(limbSwing * 1F) * 0.5F * prevLimbSwing;
		feelerL.rotateAngleY = MathHelper.cos(limbSwing * 1F + (float) Math.PI) * 0.5F * prevLimbSwing - 0.2F;
		feelerR.rotateAngleY = MathHelper.cos(limbSwing * 1F) * 0.5F * prevLimbSwing + 0.2F;

	}
}
