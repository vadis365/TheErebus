package erebus.client.model.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityMosquito;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelMosquito extends ModelBase {

	// fields
	ModelRenderer[] Tail = new ModelRenderer[2];
	ModelRenderer Body;
	ModelRenderer[] Head1 = new ModelRenderer[2];
	ModelRenderer[] Head2 = new ModelRenderer[2];
	ModelRenderer[] Head3 = new ModelRenderer[2];
	ModelRenderer[] Head4 = new ModelRenderer[2];
	ModelRenderer[] LegLeft1 = new ModelRenderer[2];
	ModelRenderer[] LegLeft2 = new ModelRenderer[2];
	ModelRenderer[] LegLeft3 = new ModelRenderer[2];
	ModelRenderer[] LegRight1 = new ModelRenderer[2];
	ModelRenderer[] LegRight2 = new ModelRenderer[2];
	ModelRenderer[] LegRight3 = new ModelRenderer[2];
	ModelRenderer ArmLeft1;
	ModelRenderer ArmLeft2;
	ModelRenderer ArmRight1;
	ModelRenderer ArmRight2;
	ModelRenderer WingLeft;
	ModelRenderer WingRight;

	public ModelMosquito() {
		textureWidth = 128;
		textureHeight = 128;

		Tail[0] = new ModelRenderer(this, 0, 41);
		Tail[0].addBox(-3F, -3F, 16F, 6, 6, 40);
		Tail[0].setRotationPoint(0F, 0F, 0F);
		setRotation(Tail[0], 0F, 0F, 0F);

		Tail[1] = new ModelRenderer(this, 0, 41);
		Tail[1].addBox(-3F, -3F, 16F, 6, 6, 40);
		Tail[1].setRotationPoint(0F, 0F, 0F);
		setRotation(Tail[1], -0.3141593F, 0F, 0F);

		Body = new ModelRenderer(this, 0, 0);
		Body.addBox(-8F, -8F, 0F, 16, 16, 16);
		Body.setRotationPoint(0F, 0F, 0F);
		setRotation(Body, 0F, 0F, 0F);

		Head1[0] = new ModelRenderer(this, 0, 61);
		Head1[0].addBox(-5F, 0F, 0F, 10, 10, 10);
		Head1[0].setRotationPoint(0F, 0F, 5F);
		setRotation(Head1[0], -2.356194F, 0F, 0F);

		Head1[1] = new ModelRenderer(this, 0, 61);
		Head1[1].addBox(-5F, 0F, 0F, 10, 10, 10);
		Head1[1].setRotationPoint(0F, 0F, 5F);
		setRotation(Head1[1], -2.129302F, 0F, 0F);

		Head2[0] = new ModelRenderer(this, 0, 45);
		Head2[0].addBox(-4F, -4F, -4F, 8, 8, 8);
		Head2[0].setRotationPoint(0F, 4F, -4F);
		setRotation(Head2[0], 0F, 0F, 0F);

		Head2[1] = new ModelRenderer(this, 0, 45);
		Head2[1].addBox(-4F, -4F, -2F, 8, 8, 8);
		Head2[1].setRotationPoint(0F, 4F, -4F);
		setRotation(Head2[1], -0.2268928F, 0F, 0F);

		Head3[0] = new ModelRenderer(this, 0, 33);
		Head3[0].addBox(-2F, 3F, -3F, 4, 8, 4);
		Head3[0].setRotationPoint(0F, 4F, -4F);
		setRotation(Head3[0], -0.1745329F, 0F, 0F);

		Head3[1] = new ModelRenderer(this, 0, 33);
		Head3[1].addBox(-2F, 3F, -3F, 4, 8, 4);
		Head3[1].setRotationPoint(0F, 4F, -4F);
		setRotation(Head3[1], 0.1745329F, 0F, 0F);

		Head4[0] = new ModelRenderer(this, 16, 35);
		Head4[0].addBox(-1F, 10F, 0F, 2, 8, 2);
		Head4[0].setRotationPoint(0F, 4F, -4F);
		setRotation(Head4[0], -0.3490659F, 0F, 0F);

		Head4[1] = new ModelRenderer(this, 16, 35);
		Head4[1].addBox(-1F, 10F, 0F, 2, 8, 2);
		Head4[1].setRotationPoint(0F, 4F, -4F);
		setRotation(Head4[1], 0.0174533F, 0F, 0F);

		LegLeft1[0] = new ModelRenderer(this, 52, 53);
		LegLeft1[0].addBox(-2F, -2F, -24F, 4, 4, 24);
		LegLeft1[0].setRotationPoint(6F, 6F, 16F);
		setRotation(LegLeft1[0], 0.7853982F, 3.141593F, 0F);

		LegLeft1[1] = new ModelRenderer(this, 52, 53);
		LegLeft1[1].addBox(-2F, -2F, -24F, 4, 4, 24);
		LegLeft1[1].setRotationPoint(6F, 6F, 16F);
		setRotation(LegLeft1[1], 1.22173F, 3.141593F, -0.2617994F);

		LegLeft2[0] = new ModelRenderer(this, 80, 6);
		LegLeft2[0].addBox(-1F, 2F, -23F, 2, 16, 2);
		LegLeft2[0].setRotationPoint(6F, 6F, 16F);
		setRotation(LegLeft2[0], 0.7853982F, 3.141593F, 0F);

		LegLeft2[1] = new ModelRenderer(this, 80, 6);
		LegLeft2[1].addBox(-1F, -13F, -20F, 2, 16, 2);
		LegLeft2[1].setRotationPoint(6F, 6F, 16F);
		setRotation(LegLeft2[1], 1.884956F, 3.141593F, -0.2617994F);

		LegLeft3[0] = new ModelRenderer(this, 64, 26);
		LegLeft3[0].addBox(-0.5F, 3.5F, -51F, 1, 1, 24);
		LegLeft3[0].setRotationPoint(6F, 6F, 16F);
		setRotation(LegLeft3[0], 1.308997F, 3.141593F, 0F);

		LegLeft3[1] = new ModelRenderer(this, 64, 26);
		LegLeft3[1].addBox(-9.5F, -4.5F, -41F, 1, 1, 24);
		LegLeft3[1].setRotationPoint(6F, 6F, 16F);
		setRotation(LegLeft3[1], 2.303835F, 3.167191F, 0.2617994F);

		LegRight1[0] = new ModelRenderer(this, 52, 53);
		LegRight1[0].addBox(-2F, -2F, -24F, 4, 4, 24);
		LegRight1[0].setRotationPoint(-6F, 6F, 16F);
		setRotation(LegRight1[0], 0.7853982F, 3.141593F, 0F);

		LegRight1[1] = new ModelRenderer(this, 52, 53);
		LegRight1[1].addBox(-2F, -2F, -24F, 4, 4, 24);
		LegRight1[1].setRotationPoint(-6F, 6F, 16F);
		setRotation(LegRight1[1], 1.22173F, 3.141593F, 0.2617994F);

		LegRight2[0] = new ModelRenderer(this, 80, 6);
		LegRight2[0].addBox(-1F, 2F, -23F, 2, 16, 2);
		LegRight2[0].setRotationPoint(-6F, 6F, 16F);
		setRotation(LegRight2[0], 0.7853982F, 3.141593F, 0F);

		LegRight2[1] = new ModelRenderer(this, 80, 6);
		LegRight2[1].addBox(-1F, -13F, -20F, 2, 16, 2);
		LegRight2[1].setRotationPoint(-6F, 6F, 16F);
		setRotation(LegRight2[1], 1.884956F, 3.141593F, 0.2617994F);

		LegRight3[0] = new ModelRenderer(this, 64, 26);
		LegRight3[0].addBox(-0.5F, 3.5F, -51F, 1, 1, 24);
		LegRight3[0].setRotationPoint(-6F, 6F, 16F);
		setRotation(LegRight3[0], 1.308997F, 3.141593F, 0F);

		LegRight3[1] = new ModelRenderer(this, 64, 26);
		LegRight3[1].addBox(8.5F, -4.5F, -41F, 1, 1, 24);
		LegRight3[1].setRotationPoint(-6F, 6F, 16F);
		setRotation(LegRight3[1], 2.303835F, 3.141593F, -0.2617994F);

		ArmLeft1 = new ModelRenderer(this, 64, 0);
		ArmLeft1.addBox(-1F, -1F, -24F, 2, 2, 24);
		ArmLeft1.setRotationPoint(6F, 8F, 4F);
		setRotation(ArmLeft1, 1.570796F, 0F, 0F);

		ArmLeft2 = new ModelRenderer(this, 64, 0);
		ArmLeft2.addBox(-1F, -1F, -24F, 2, 2, 24);
		ArmLeft2.setRotationPoint(6F, 8F, 8F);
		setRotation(ArmLeft2, 1.570796F, 0F, 0F);

		ArmRight1 = new ModelRenderer(this, 64, 0);
		ArmRight1.addBox(-1F, -1F, -24F, 2, 2, 24);
		ArmRight1.setRotationPoint(-6F, 8F, 4F);
		setRotation(ArmRight1, 1.570796F, 0F, 0F);

		ArmRight2 = new ModelRenderer(this, 64, 0);
		ArmRight2.addBox(-1F, -1F, -24F, 2, 2, 24);
		ArmRight2.setRotationPoint(-6F, 8F, 8F);
		setRotation(ArmRight2, 1.570796F, 0F, 0F);

		WingLeft = new ModelRenderer(this, 0, 87);
		WingLeft.addBox(-16F, 0F, 0F, 16, 1, 32);
		WingLeft.setRotationPoint(-4F, -9F, 4F);
		setRotation(WingLeft, 0F, 0F, 0F);

		WingRight = new ModelRenderer(this, 0, 87);
		WingRight.addBox(0F, 0F, 0F, 16, 1, 32);
		WingRight.setRotationPoint(4F, -9F, 4F);
		setRotation(WingRight, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		int b = entity.ridingEntity != null ? 1 : 0;

		EntityMosquito mosquito = (EntityMosquito) entity;
		float blood = mosquito.getBloodConsumed() / 10F;

		GL11.glPushMatrix();
		Tail[b].render(f5);
		Head1[b].render(f5);
		LegLeft1[b].render(f5);
		LegLeft2[b].render(f5);
		LegLeft3[b].render(f5);
		LegRight1[b].render(f5);
		LegRight2[b].render(f5);
		LegRight3[b].render(f5);
		ArmLeft1.render(f5);
		ArmLeft2.render(f5);
		ArmRight1.render(f5);
		ArmRight2.render(f5);

		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		WingLeft.render(f5);
		WingRight.render(f5);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();

		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glScalef(1.0F + blood, 1.0F, 1.0F);
		Body.render(f5);
		GL11.glScalef(1.0F - blood, 1.0F, 1.0F);
		GL11.glScalef(mosquito.suckFloat, 1.0F, 1.0F);
		Head2[b].render(f5);
		Head3[b].render(f5);
		Head4[b].render(f5);
		GL11.glPopMatrix();
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		// wing animation
		EntityMosquito mosquito = (EntityMosquito) entity;
		WingLeft.rotateAngleZ = mosquito.wingFloat;
		WingRight.rotateAngleZ = -mosquito.wingFloat;
	}
}