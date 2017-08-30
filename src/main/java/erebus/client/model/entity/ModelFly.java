package erebus.client.model.entity;

import erebus.entity.EntityFly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFly extends ModelBase {
	ModelRenderer thorax;
	ModelRenderer abdomen;
	ModelRenderer right_eye;
	ModelRenderer left_eye;
	ModelRenderer leg_left_back;
	ModelRenderer head;
	ModelRenderer leg_left_front;
	ModelRenderer leg_left_mid;
	ModelRenderer leg_right_back;
	ModelRenderer leg_right_front;
	ModelRenderer leg_right_mid;
	ModelRenderer wing_right;
	ModelRenderer wing_left;

	public ModelFly() {
		textureWidth = 64;
		textureHeight = 32;
		leg_right_back = new ModelRenderer(this, 0, 12);
		leg_right_back.setRotationPoint(-3.0F, 19.0F, 0.0F);
		leg_right_back.addBox(-6.0F, 0.0F, 0.0F, 6, 1, 1, 0.0F);
		setRotation(leg_right_back, -0.3876376268679406F, 0.36128315516282616F, -0.8571311956544152F);
		wing_left = new ModelRenderer(this, 0, 25);
		wing_left.mirror = true;
		wing_left.setRotationPoint(1.0F, 16.0F, 0.0F);
		wing_left.addBox(0.0F, 0.0F, 0.0F, 6, 1, 6, 0.0F);
		setRotation(wing_left, 0.5235987755982988F, 0.17453292519943295F, 0.0F);
		leg_right_mid = new ModelRenderer(this, 0, 12);
		leg_right_mid.setRotationPoint(-3.0F, 19.0F, -0.5F);
		leg_right_mid.addBox(-6.0F, 0.0F, -0.5F, 6, 1, 1, 0.0F);
		setRotation(leg_right_mid, 0.0F, -0.0F, -0.7853981633974483F);
		abdomen = new ModelRenderer(this, 0, 0);
		abdomen.setRotationPoint(0.0F, 19.0F, 0.0F);
		abdomen.addBox(-3.0F, -3.0F, 2.0F, 6, 6, 6, 0.0F);
		leg_left_back = new ModelRenderer(this, 0, 12);
		leg_left_back.setRotationPoint(3.0F, 19.0F, 0.0F);
		leg_left_back.addBox(0.0F, 0.0F, 0.0F, 6, 1, 1, 0.0F);
		setRotation(leg_left_back, -0.3876376268679406F, -0.36128315516282616F, 0.8571311956544152F);
		thorax = new ModelRenderer(this, 24, 7);
		thorax.setRotationPoint(0.0F, 19.0F, 0.0F);
		thorax.addBox(-2.5F, -2.5F, -2.0F, 5, 5, 4, 0.0F);
		left_eye = new ModelRenderer(this, 24, 0);
		left_eye.setRotationPoint(0.0F, 19.0F, -2.0F);
		left_eye.addBox(0.5F, -2.5F, -3.5F, 2, 4, 3, 0.0F);
		leg_left_mid = new ModelRenderer(this, 0, 12);
		leg_left_mid.setRotationPoint(3.0F, 19.0F, -0.5F);
		leg_left_mid.addBox(0.0F, 0.0F, -0.5F, 6, 1, 1, 0.0F);
		setRotation(leg_left_mid, 0.0F, -0.0F, 0.7853981633974483F);
		leg_left_front = new ModelRenderer(this, 0, 12);
		leg_left_front.setRotationPoint(3.0F, 19.0F, -1.0F);
		leg_left_front.addBox(0.0F, 0.0F, -1.0F, 6, 1, 1, 0.0F);
		setRotation(leg_left_front, 0.3876376268679406F, 0.36128315516282616F, 0.8571311956544152F);
		right_eye = new ModelRenderer(this, 24, 0);
		right_eye.mirror = true;
		right_eye.setRotationPoint(0.0F, 19.0F, -2.0F);
		right_eye.addBox(-2.5F, -2.5F, -3.5F, 2, 4, 3, 0.0F);
		wing_right = new ModelRenderer(this, 0, 25);
		wing_right.setRotationPoint(-1.0F, 16.0F, 0.0F);
		wing_right.addBox(-6.0F, 0.0F, 0.0F, 6, 1, 6, 0.0F);
		setRotation(wing_right, 0.5235987755982988F, -0.17453292519943295F, 0.0F);
		leg_right_front = new ModelRenderer(this, 0, 12);
		leg_right_front.setRotationPoint(-3.0F, 19.0F, -1.0F);
		leg_right_front.addBox(-6.0F, 0.0F, -1.0F, 6, 1, 1, 0.0F);
		setRotation(leg_right_front, 0.3876376268679406F, -0.36128315516282616F, -0.8571311956544152F);
		head = new ModelRenderer(this, 34, 0);
		head.setRotationPoint(0.0F, 19.0F, -2.0F);
		head.addBox(-2.0F, -2.0F, -3.0F, 4, 4, 3, 0.0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.render(entity, limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel);
        leg_right_back.render(unitPixel);
        wing_left.render(unitPixel);
        leg_right_mid.render(unitPixel);
        abdomen.render(unitPixel);
        leg_left_back.render(unitPixel);
        thorax.render(unitPixel);
        left_eye.render(unitPixel);
        leg_left_mid.render(unitPixel);
        leg_left_front.render(unitPixel);
        right_eye.render(unitPixel);
        wing_right.render(unitPixel);
        leg_right_front.render(unitPixel);
        head.render(unitPixel);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float limbSwing, float limbSwingAngle, float partialRenderTicks) {
		EntityFly fly = (EntityFly) entity;
		float flap = MathHelper.sin((fly.ticksExisted) * 0.95F) * 1F;
		if (!fly.getIsFlyHanging()) {
			wing_left.rotateAngleX = 0.5235988F + flap * 0.2F;
			wing_right.rotateAngleX = 0.5235988F + flap * 0.2F;
			wing_left.rotateAngleZ = 0F + flap * 0.5F;
			wing_right.rotateAngleZ = 0F - flap * 0.5F;
			wing_left.rotateAngleY = 0.5235988F;
			wing_right.rotateAngleY = -0.5235988F;
		} else {
			wing_left.rotateAngleX = 0.25235988F;
			wing_right.rotateAngleX = 0.25235988F;
			wing_left.rotateAngleZ = 0F;
			wing_right.rotateAngleZ = 0F;
			wing_left.rotateAngleY = -0.1745329F;
			wing_right.rotateAngleY = 0.1745329F;
		}
	}

	@Override
	public void setRotationAngles(float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
		super.setRotationAngles(limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
	}

}