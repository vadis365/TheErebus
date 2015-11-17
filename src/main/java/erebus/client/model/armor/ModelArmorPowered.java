package erebus.client.model.armor;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelArmorPowered extends ModelBiped {

	ModelRenderer Body;
	ModelRenderer RArm;
	ModelRenderer LArm;
	ModelRenderer RWingbase;
	ModelRenderer LWingbase;
	ModelRenderer ChestEngine;
	ModelRenderer RWingUpgradeTop;
	ModelRenderer RWingUpgradeMid;
	ModelRenderer RWingUpgradeBottom;
	ModelRenderer LWingUpgradeTop;
	ModelRenderer LWingUpgradeMid;
	ModelRenderer LWingUpgradeBottom;

	public boolean isGliding;
	public boolean isPowered;

	public ModelArmorPowered() {
		textureWidth = 64;
		textureHeight = 64;

		Body = new ModelRenderer(this, 19, 12);
		Body.addBox(-4F, 0F, -2F, 8, 11, 4);
		Body.setRotationPoint(0F, 0F, 0F);
		setRotation(Body, 0F, 0F, 0F);
		RArm = new ModelRenderer(this, 42, 0);
		RArm.addBox(-3F, -2F, -2F, 4, 5, 4);
		RArm.setRotationPoint(-5F, 2F, 0F);
		setRotation(RArm, 0F, 0F, 0F);
		LArm = new ModelRenderer(this, 0, 0);
		LArm.addBox(-1F, -2F, -2F, 4, 5, 4);
		LArm.setRotationPoint(5F, 2F, 0F);
		setRotation(LArm, 0F, 0F, 0F);
		RWingbase = new ModelRenderer(this, 52, 16);
		RWingbase.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		RWingbase.setRotationPoint(-2F, 2F, 3.5F);
		setRotation(RWingbase, 0F, 0F, 0F);
		LWingbase = new ModelRenderer(this, 0, 16);
		LWingbase.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		LWingbase.setRotationPoint(2F, 2F, 3.5F);
		setRotation(LWingbase, 0F, 0F, 0F);
		ChestEngine = new ModelRenderer(this, 24, 3);
		ChestEngine.addBox(-2F, 2F, -4F, 4, 4, 2);
		ChestEngine.setRotationPoint(0F, 0F, 0F);
		setRotation(ChestEngine, 0F, 0F, 0F);
		RWingUpgradeTop = new ModelRenderer(this, 54, 49);
		RWingUpgradeTop.addBox(-4.5F, 0F, -0.51F, 4, 14, 1);
		RWingUpgradeTop.setRotationPoint(-2F, 3F, 3.5F);
		setRotation(RWingUpgradeTop, 0F, 0F, 1.570796F);
		RWingUpgradeMid = new ModelRenderer(this, 45, 49);
		RWingUpgradeMid.addBox(-0.5F, 0F, -0.51F, 3, 10, 1);
		RWingUpgradeMid.setRotationPoint(-2F, 3F, 3.5F);
		setRotation(RWingUpgradeMid, 0F, 0F, 1.570796F);
		RWingUpgradeBottom = new ModelRenderer(this, 38, 49);
		RWingUpgradeBottom.addBox(2.5F, 0F, -0.51F, 2, 7, 1);
		RWingUpgradeBottom.setRotationPoint(-2F, 3F, 3.5F);
		setRotation(RWingUpgradeBottom, 0F, 0F, 1.570796F);
		LWingUpgradeTop = new ModelRenderer(this, 0, 49);
		LWingUpgradeTop.addBox(0.5F, 0F, -0.5F, 4, 14, 1);
		LWingUpgradeTop.setRotationPoint(2F, 3F, 3.5F);
		setRotation(LWingUpgradeTop, 0F, 0F, -1.570796F);
		LWingUpgradeMid = new ModelRenderer(this, 11, 49);
		LWingUpgradeMid.addBox(-2.5F, 0F, -0.5F, 3, 10, 1);
		LWingUpgradeMid.setRotationPoint(2F, 3F, 3.5F);
		setRotation(LWingUpgradeMid, 0F, 0F, -1.570796F);
		LWingUpgradeBottom = new ModelRenderer(this, 20, 49);
		LWingUpgradeBottom.addBox(-4.5F, 0F, -0.5F, 2, 7, 1);
		LWingUpgradeBottom.setRotationPoint(2F, 3F, 3.5F);
		setRotation(LWingUpgradeBottom, 0F, 0F, -1.570796F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.render(entity, limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		setRotationAngles(limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, -0.05F, 0.0F);
		GL11.glScalef(1.1F, 1.2F, 1.3F);
		Body.render(unitPixel);
		ChestEngine.render(unitPixel);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(0.15F, -0.05F, 0.0F);
		GL11.glScalef(1.5F, 1.2F, 1.3F);
		RArm.render(unitPixel);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.15F, -0.05F, 0.0F);
		GL11.glScalef(1.5F, 1.2F, 1.3F);
		LArm.render(unitPixel);
		GL11.glPopMatrix();
		RWingbase.render(unitPixel);
		LWingbase.render(unitPixel);

		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		if (entity instanceof EntityLivingBase) {
			EntityLivingBase living = (EntityLivingBase) entity;
			ItemStack chestplate = living.getEquipmentInSlot(3);
			if (chestplate != null && chestplate.getItem() == ModItems.armorGliderPowered && ModItems.armorGliderPowered.hasColor(chestplate)) {
				int colour = ModItems.armorGliderPowered.getColor(chestplate);
				float red = (colour >> 16 & 255) / 255.0F;
				float green = (colour >> 8 & 255) / 255.0F;
				float blue = (colour & 255) / 255.0F;
				GL11.glColor3f(red, green, blue);
			}
		}

		RWingUpgradeTop.render(unitPixel);
		RWingUpgradeMid.render(unitPixel);
		RWingUpgradeBottom.render(unitPixel);
		LWingUpgradeTop.render(unitPixel);
		LWingUpgradeMid.render(unitPixel);
		LWingUpgradeBottom.render(unitPixel);
		GL11.glColor3f(1, 1, 1);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
		super.setRotationAngles(limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);

		prevLimbSwing /= 100;
		RArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * prevLimbSwing * 0.5F;
		LArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * prevLimbSwing * 0.5F;
		if (!isGliding && !isPowered) {
			RWingUpgradeTop.rotateAngleZ = 0F;
			RWingUpgradeMid.rotateAngleZ = 0F;
			RWingUpgradeBottom.rotateAngleZ = 0F;
			LWingUpgradeTop.rotateAngleZ = 0F;
			LWingUpgradeMid.rotateAngleZ = 0F;
			LWingUpgradeBottom.rotateAngleZ = 0F;

			if (entity.prevPosX != entity.posX || entity.prevPosZ != entity.posZ) {
				RWingUpgradeTop.rotateAngleX = 0.7F;
				RWingUpgradeMid.rotateAngleX = 0.7F;
				RWingUpgradeBottom.rotateAngleX = 0.7F;
				LWingUpgradeTop.rotateAngleX = 0.7F;
				LWingUpgradeMid.rotateAngleX = 0.7F;
				LWingUpgradeBottom.rotateAngleX = 0.7F;

			} else {
				RWingUpgradeTop.rotateAngleX = 0F;
				RWingUpgradeMid.rotateAngleX = 0F;
				RWingUpgradeBottom.rotateAngleX = 0F;
				LWingUpgradeTop.rotateAngleX = 0F;
				LWingUpgradeMid.rotateAngleX = 0F;
				LWingUpgradeBottom.rotateAngleX = 0F;
			}
		}
		if (isGliding || isPowered && !entity.onGround) {
			RWingUpgradeTop.rotateAngleZ = 1.570796F;
			RWingUpgradeMid.rotateAngleZ = 1.570796F;
			RWingUpgradeBottom.rotateAngleZ = 1.570796F;
			LWingUpgradeTop.rotateAngleZ = -1.570796F;
			LWingUpgradeMid.rotateAngleZ = -1.570796F;
			LWingUpgradeBottom.rotateAngleZ = -1.570796F;
			if (isPowered) {
				RWingUpgradeTop.rotateAngleX = 0.3F + MathHelper.cos(entityTickTime) * 4.0F * prevLimbSwing * 120F;
				RWingUpgradeMid.rotateAngleX = 0.3F + MathHelper.cos(entityTickTime) * 4.0F * prevLimbSwing * 120F;
				RWingUpgradeBottom.rotateAngleX = 0.3F + MathHelper.cos(entityTickTime) * 4.0F * prevLimbSwing * 120F;
				LWingUpgradeTop.rotateAngleX = 0.3F + MathHelper.cos(entityTickTime) * 4.0F * prevLimbSwing * 120F;
				;
				LWingUpgradeMid.rotateAngleX = 0.3F + MathHelper.cos(entityTickTime) * 4.0F * prevLimbSwing * 120F;
				LWingUpgradeBottom.rotateAngleX = 0.3F + MathHelper.cos(entityTickTime) * 4.0F * prevLimbSwing * 120F;
			}
		}
		if (entity.isSneaking()) {
			Body.rotateAngleX = 0.4F;
			RArm.rotateAngleX += 0.4F;
			LArm.rotateAngleX += 0.4F;
			RWingbase.rotateAngleX = 0.5F;
			LWingbase.rotateAngleX = 0.5F;
			if (!isGliding || !isPowered) {
				RWingUpgradeTop.rotateAngleX = 0.5F;
				RWingUpgradeMid.rotateAngleX = 0.5F;
				RWingUpgradeBottom.rotateAngleX = 0.5F;
				LWingUpgradeTop.rotateAngleX = 0.5F;
				LWingUpgradeMid.rotateAngleX = 0.5F;
				LWingUpgradeBottom.rotateAngleX = 0.5F;
			}
			RWingbase.rotationPointZ = 4.5F;
			LWingbase.rotationPointZ = 4.5F;
			RWingUpgradeTop.rotationPointZ = 4.5F;
			RWingUpgradeMid.rotationPointZ = 4.5F;
			RWingUpgradeBottom.rotationPointZ = 4.5F;
			LWingUpgradeTop.rotationPointZ = 4.5F;
			LWingUpgradeMid.rotationPointZ = 4.5F;
			LWingUpgradeBottom.rotationPointZ = 4.5F;
		} else {
			Body.rotateAngleX = 0.0F;
			RWingbase.rotateAngleX = 0.0F;
			LWingbase.rotateAngleX = 0.0F;
			RWingbase.rotationPointZ = 3.5F;
			LWingbase.rotationPointZ = 3.5F;
			RWingUpgradeTop.rotationPointZ = 3.5F;
			RWingUpgradeMid.rotationPointZ = 3.5F;
			RWingUpgradeBottom.rotationPointZ = 3.5F;
			LWingUpgradeTop.rotationPointZ = 3.5F;
			LWingUpgradeMid.rotationPointZ = 3.5F;
			LWingUpgradeBottom.rotationPointZ = 3.5F;
		}
	}
}
