package erebus.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityStagBeetle;

@SideOnly(Side.CLIENT)
public class ModelStagBeetle extends ModelBase {
    ModelRenderer headMid;
    ModelRenderer thoraxMain;
    ModelRenderer thoraxBack;
    ModelRenderer abdomenLTop;
    ModelRenderer abdomenLTopRear;
    ModelRenderer abdomenLMid;
    ModelRenderer abdomenL;
    ModelRenderer abdomenRTop;
    ModelRenderer abdomenRTopRear;
    ModelRenderer abdomenRMid;
    ModelRenderer abdomenR;
    ModelRenderer abdomenBelly;
    ModelRenderer abdomenBellyBack;
    ModelRenderer bumL;
    ModelRenderer bumL2;
    ModelRenderer bumR;
    ModelRenderer bumR2;
    ModelRenderer spine;
    ModelRenderer legLF1;
    ModelRenderer legRF1;
    ModelRenderer legLM1;
    ModelRenderer legRM1;
    ModelRenderer legLB1;
    ModelRenderer legRB1;
    ModelRenderer headL;
    ModelRenderer headR;
    ModelRenderer eyeL;
    ModelRenderer mandibleL1;
    ModelRenderer mandibleL2;
    ModelRenderer mandibleL3;
    ModelRenderer mandibleL4;
    ModelRenderer mandibleL5;
    ModelRenderer mandibleL6;
    ModelRenderer mandibleL7;
    ModelRenderer eyeR;
    ModelRenderer mandibleR1;
    ModelRenderer mandibleR2;
    ModelRenderer mandibleR3;
    ModelRenderer mandibleR4;
    ModelRenderer mandibleR5;
    ModelRenderer mandibleR6;
    ModelRenderer mandibleR7;
    ModelRenderer legLF2;
    ModelRenderer legLF3;
    ModelRenderer legRF2;
    ModelRenderer legRF3;
    ModelRenderer legLM2;
    ModelRenderer legLM3;
    ModelRenderer legRM2;
    ModelRenderer legRM3;
    ModelRenderer legLB2;
    ModelRenderer legLB3;
    ModelRenderer legRB2;
    ModelRenderer legRB3;

    public ModelStagBeetle() {
        textureWidth = 128;
        textureHeight = 128;
        abdomenRTop = new ModelRenderer(this, 74, 52);
        abdomenRTop.setRotationPoint(0.0F, 15.0F, 0.0F);
        abdomenRTop.addBox(-8.0F, -5.0F, 0.5F, 7, 1, 17, 0.0F);
        setRotation(abdomenRTop, 0.0F, -0.0F, -0.17453292012214658F);
        legLB1 = new ModelRenderer(this, 0, 38);
        legLB1.setRotationPoint(9.0F, 16.0F, 10.5F);
        legLB1.addBox(-0.5F, -2.0F, -2.0F, 10, 4, 4, 0.0F);
        setRotation(legLB1, -0.17453292519943295F, -0.5235987755982988F, 0.3490658503988659F);
        legLF3 = new ModelRenderer(this, 0, 0);
        legLF3.setRotationPoint(7.5F, 0.0F, -0.5F);
        legLF3.addBox(0.5F, -1.1F, -1.0F, 8, 2, 2, 0.0F);
        setRotation(legLF3, 0.0F, -0.5235987755982988F, 0.0F);
        abdomenBellyBack = new ModelRenderer(this, 47, 121);
        abdomenBellyBack.setRotationPoint(0.0F, 15.0F, 0.0F);
        abdomenBellyBack.addBox(-6.0F, 2.0F, 15.5F, 12, 2, 5, 0.0F);
        legRM3 = new ModelRenderer(this, 106, 33);
        legRM3.setRotationPoint(7.5F, 0.0F, 0.0F);
        legRM3.addBox(-0.5F, -1.0F, -1.0F, 9, 2, 2, 0.0F);
        setRotation(legRM3, 0.0F, 0.2617993877991494F, 0.17453292519943295F);
        eyeL = new ModelRenderer(this, 29, 35);
        eyeL.setRotationPoint(0.0F, 0.0F, 0.0F);
        eyeL.addBox(7.5F, -2.0F, -6.0F, 1, 2, 2, 0.0F);
        legLM3 = new ModelRenderer(this, 0, 33);
        legLM3.setRotationPoint(7.5F, 0.0F, 0.0F);
        legLM3.addBox(-0.5F, -1.0F, -1.0F, 9, 2, 2, 0.0F);
        setRotation(legLM3, 0.0F, -0.2617993877991494F, 0.17453292519943295F);
        abdomenBelly = new ModelRenderer(this, 35, 103);
        abdomenBelly.setRotationPoint(0.0F, 15.0F, 0.0F);
        abdomenBelly.addBox(-7.0F, 2.0F, 0.5F, 14, 2, 15, 0.0F);
        abdomenL = new ModelRenderer(this, 15, 106);
        abdomenL.setRotationPoint(0.0F, 15.0F, 0.0F);
        abdomenL.addBox(9.0F, -3.0F, 1.5F, 1, 4, 15, 0.0F);
        setRotation(abdomenL, 0.0F, -0.0F, 0.17453292012214658F);
        thoraxBack = new ModelRenderer(this, 47, 61);
        thoraxBack.setRotationPoint(0.0F, 15.0F, 0.0F);
        thoraxBack.addBox(-7.5F, -2.5F, -2.5F, 15, 5, 2, 0.0F);
        abdomenRMid = new ModelRenderer(this, 72, 77);
        abdomenRMid.setRotationPoint(0.0F, 15.0F, 0.0F);
        abdomenRMid.addBox(-9.0F, -4.0F, -0.5F, 9, 6, 19, 0.0F);
        setRotation(abdomenRMid, 0.0F, -0.0F, -0.17453292012214658F);
        legRM2 = new ModelRenderer(this, 106, 26);
        legRM2.setRotationPoint(8.5F, 0.0F, 0.0F);
        legRM2.addBox(-0.5F, -1.5F, -1.5F, 8, 3, 3, 0.0F);
        setRotation(legRM2, 2.96705972839036F, -0.4363323129985824F, 0.0F);
        mandibleR6 = new ModelRenderer(this, 81, 6);
        mandibleR6.setRotationPoint(0.0F, 0.0F, 0.0F);
        mandibleR6.addBox(-5.5F, -0.5F, -11.5F, 3, 1, 1, 0.0F);
        legLB3 = new ModelRenderer(this, 0, 54);
        legLB3.setRotationPoint(7.5F, 0.0F, 0.0F);
        legLB3.addBox(-0.5F, -1.0F, -1.0F, 8, 2, 2, 0.0F);
        setRotation(legLB3, 0.0F, 0.6981317007977318F, -0.17453292519943295F);
        mandibleL3 = new ModelRenderer(this, 43, 16);
        mandibleL3.setRotationPoint(0.0F, 0.0F, 0.0F);
        mandibleL3.addBox(1.5F, -1.0F, -8.0F, 1, 2, 2, 0.0F);
        headR = new ModelRenderer(this, 73, 35);
        headR.setRotationPoint(0.0F, 0.0F, 0.0F);
        headR.addBox(-7.5F, -2.5F, -8.0F, 6, 6, 7, 0.0F);
        setRotation(headR, 0.0F, -0.0F, -0.17453292519943295F);
        legLF1 = new ModelRenderer(this, 0, 12);
        legLF1.setRotationPoint(5.0F, 15.0F, -4.5F);
        legLF1.addBox(-0.5F, -1.5F, -1.5F, 8, 3, 3, 0.0F);
        setRotation(legLF1, 0.17453292519943295F, 0.3490658503988659F, 0.3490658503988659F);
        legRM1 = new ModelRenderer(this, 104, 19);
        legRM1.setRotationPoint(-6.0F, 15.0F, -0.5F);
        legRM1.addBox(0.0F, -1.5F, -1.5F, 9, 3, 3, 0.0F);
        setRotation(legRM1, 0.0F, 0.0F, 2.792526803190927F);
        legLM1 = new ModelRenderer(this, 0, 19);
        legLM1.setRotationPoint(6.0F, 15.0F, -0.5F);
        legLM1.addBox(-0.5F, -1.5F, -1.5F, 9, 3, 3, 0.0F);
        setRotation(legLM1, 0.0F, 0.0F, 0.3490658503988659F);
        bumL2 = new ModelRenderer(this, 0, 117);
        bumL2.setRotationPoint(0.0F, 15.0F, 0.0F);
        bumL2.addBox(0.5F, -3.0F, 21.5F, 6, 4, 1, 0.0F);
        setRotation(bumL2, 0.0F, -0.0F, 0.17453292012214658F);
        headMid = new ModelRenderer(this, 54, 30);
        headMid.setRotationPoint(0.0F, 13.0F, -6.5F);
        headMid.addBox(-2.0F, -2.0F, -7.0F, 4, 5, 6, 0.0F);
        setRotation(headMid, -0.17453292519943295F, 0.0F, 0.0F);
        mandibleL4 = new ModelRenderer(this, 50, 18);
        mandibleL4.setRotationPoint(0.0F, 0.0F, 0.0F);
        mandibleL4.addBox(-0.5F, -0.5F, -7.5F, 2, 1, 1, 0.0F);
        mandibleL2 = new ModelRenderer(this, 29, 14);
        mandibleL2.setRotationPoint(0.0F, 0.0F, 0.0F);
        mandibleL2.addBox(2.5F, -1.5F, -10.0F, 3, 3, 7, 0.0F);
        setRotation(mandibleL2, 0.0F, 0.7853981633974483F, 0.0F);
        thoraxMain = new ModelRenderer(this, 47, 49);
        thoraxMain.setRotationPoint(0.0F, 13.0F, -7.5F);
        thoraxMain.addBox(-5.5F, -1.5F, 0.0F, 11, 5, 6, 0.0F);
        setRotation(thoraxMain, -0.17453292012214658F, -0.0F, 0.0F);
        mandibleL6 = new ModelRenderer(this, 39, 6);
        mandibleL6.setRotationPoint(0.0F, 0.0F, 0.0F);
        mandibleL6.addBox(2.5F, -0.5F, -11.5F, 3, 1, 1, 0.0F);
        mandibleR7 = new ModelRenderer(this, 89, 0);
        mandibleR7.setRotationPoint(0.0F, 0.0F, 0.0F);
        mandibleR7.addBox(-2.0F, -0.5F, -18.0F, 1, 1, 4, 0.0F);
        setRotation(mandibleR7, 0.0F, 0.3490658503988659F, 0.0F);
        legRF2 = new ModelRenderer(this, 106, 5);
        legRF2.setRotationPoint(7.0F, 0.0F, -0.5F);
        legRF2.addBox(0.0F, -1.5F, -1.5F, 8, 3, 3, 0.0F);
        setRotation(legRF2, -0.5235987755982988F, -1.0471975511965976F, 0.3490658503988659F);
        legRF1 = new ModelRenderer(this, 106, 12);
        legRF1.setRotationPoint(-5.0F, 15.0F, -4.5F);
        legRF1.addBox(-0.5F, -1.5F, -1.5F, 8, 3, 3, 0.0F);
        setRotation(legRF1, -0.17453292519943295F, 2.792526803190927F, -0.3490658503988659F);
        abdomenLMid = new ModelRenderer(this, 0, 77);
        abdomenLMid.setRotationPoint(0.0F, 15.0F, 0.0F);
        abdomenLMid.addBox(0.0F, -4.0F, -0.5F, 9, 6, 19, 0.0F);
        setRotation(abdomenLMid, 0.0F, -0.0F, 0.17453292012214658F);
        mandibleR3 = new ModelRenderer(this, 79, 16);
        mandibleR3.setRotationPoint(0.0F, 0.0F, 0.0F);
        mandibleR3.addBox(-2.5F, -1.0F, -8.0F, 1, 2, 2, 0.0F);
        legRB1 = new ModelRenderer(this, 100, 38);
        legRB1.setRotationPoint(-9.0F, 16.0F, 10.5F);
        legRB1.addBox(-0.5F, -2.0F, -2.0F, 10, 4, 4, 0.0F);
        setRotation(legRB1, -2.96705972839036F, -0.5235987755982988F, 2.792526803190927F);
        mandibleL5 = new ModelRenderer(this, 29, 6);
        mandibleL5.setRotationPoint(0.0F, 0.0F, 0.0F);
        mandibleL5.addBox(5.5F, -1.0F, -13.0F, 2, 2, 5, 0.0F);
        setRotation(mandibleL5, 0.0F, 0.3490658503988659F, 0.0F);
        abdomenLTopRear = new ModelRenderer(this, 22, 71);
        abdomenLTopRear.setRotationPoint(0.0F, 15.0F, 0.0F);
        abdomenLTopRear.addBox(1.5F, -5.0F, 17.5F, 5, 1, 3, 0.0F);
        setRotation(abdomenLTopRear, 0.0F, -0.0F, 0.17453292012214658F);
        eyeR = new ModelRenderer(this, 93, 35);
        eyeR.setRotationPoint(0.0F, 0.0F, 0.0F);
        eyeR.addBox(-8.5F, -2.0F, -6.0F, 1, 2, 2, 0.0F);
        legRF3 = new ModelRenderer(this, 108, 0);
        legRF3.setRotationPoint(7.5F, 0.0F, 0.5F);
        legRF3.addBox(0.5F, -1.0F, -1.0F, 8, 2, 2, 0.0F);
        setRotation(legRF3, 0.0F, 0.5235987755982988F, 0.0F);
        mandibleR2 = new ModelRenderer(this, 79, 14);
        mandibleR2.setRotationPoint(0.0F, 0.0F, 0.0F);
        mandibleR2.addBox(-5.5F, -1.5F, -10.0F, 3, 3, 7, 0.0F);
        setRotation(mandibleR2, 0.0F, -0.7853981633974483F, 0.0F);
        legLM2 = new ModelRenderer(this, 0, 26);
        legLM2.setRotationPoint(8.5F, 0.0F, 0.0F);
        legLM2.addBox(-0.5F, -1.5F, -1.5F, 8, 3, 3, 0.0F);
        setRotation(legLM2, 0.17453292519943295F, -0.4363323129985824F, 0.0F);
        mandibleR4 = new ModelRenderer(this, 72, 18);
        mandibleR4.setRotationPoint(0.0F, 0.0F, 0.0F);
        mandibleR4.addBox(-1.5F, -0.5F, -7.5F, 2, 1, 1, 0.0F);
        bumL = new ModelRenderer(this, 0, 107);
        bumL.setRotationPoint(0.0F, 15.0F, 0.0F);
        bumL.addBox(0.0F, -4.0F, 18.5F, 8, 6, 3, 0.0F);
        setRotation(bumL, 0.0F, -0.0F, 0.17453292012214658F);
        mandibleR5 = new ModelRenderer(this, 85, 6);
        mandibleR5.setRotationPoint(0.0F, 0.0F, 0.0F);
        mandibleR5.addBox(-7.5F, -1.0F, -13.0F, 2, 2, 5, 0.0F);
        setRotation(mandibleR5, 0.0F, -0.3490658503988659F, 0.0F);
        spine = new ModelRenderer(this, 40, 69);
        spine.setRotationPoint(0.0F, 15.0F, 0.0F);
        spine.addBox(-1.5F, -3.5F, -0.5F, 3, 5, 21, 0.0F);
        mandibleL7 = new ModelRenderer(this, 29, 0);
        mandibleL7.setRotationPoint(0.0F, 0.0F, 0.0F);
        mandibleL7.addBox(1.0F, -0.5F, -18.0F, 1, 1, 4, 0.0F);
        setRotation(mandibleL7, 0.0F, -0.3490658503988659F, 0.0F);
        bumR = new ModelRenderer(this, 106, 107);
        bumR.setRotationPoint(0.0F, 15.0F, 0.0F);
        bumR.addBox(-8.0F, -4.0F, 18.5F, 8, 6, 3, 0.0F);
        setRotation(bumR, 0.0F, -0.0F, -0.17453292012214658F);
        abdomenLTop = new ModelRenderer(this, 6, 52);
        abdomenLTop.setRotationPoint(0.0F, 15.0F, 0.0F);
        abdomenLTop.addBox(1.0F, -5.0F, 0.5F, 7, 1, 17, 0.0F);
        setRotation(abdomenLTop, 0.0F, -0.0F, 0.17453292012214658F);
        abdomenR = new ModelRenderer(this, 81, 106);
        abdomenR.setRotationPoint(0.0F, 15.0F, 0.0F);
        abdomenR.addBox(-10.0F, -3.0F, 1.5F, 1, 4, 15, 0.0F);
        setRotation(abdomenR, 0.0F, -0.0F, -0.15707963705062866F);
        mandibleL1 = new ModelRenderer(this, 29, 25);
        mandibleL1.setRotationPoint(4.0F, 1.0F, -7.5F);
        mandibleL1.addBox(-1.5F, -1.0F, -6.0F, 3, 2, 7, 0.0F);
        setRotation(mandibleL1, 0.17453292519943295F, -0.7853981633974483F, -0.17453292519943295F);
        legRB3 = new ModelRenderer(this, 108, 54);
        legRB3.setRotationPoint(7.5F, 0.0F, 0.0F);
        legRB3.addBox(-0.5F, -1.0F, -1.0F, 8, 2, 2, 0.0F);
        setRotation(legRB3, 0.0F, -0.6981317007977318F, -0.17453292519943295F);
        legRB2 = new ModelRenderer(this, 106, 47);
        legRB2.setRotationPoint(8.0F, -0.2F, 0.5F);
        legRB2.addBox(0.0F, -1.5F, -1.5F, 8, 3, 3, 0.0F);
        setRotation(legRB2, 0.3490658503988659F, 0.8726646259971648F, 0.3490658503988659F);
        abdomenRTopRear = new ModelRenderer(this, 90, 71);
        abdomenRTopRear.setRotationPoint(0.0F, 15.0F, 0.0F);
        abdomenRTopRear.addBox(-6.5F, -5.0F, 17.5F, 5, 1, 3, 0.0F);
        setRotation(abdomenRTopRear, 0.0F, -0.0F, -0.17453292012214658F);
        legLB2 = new ModelRenderer(this, 0, 47);
        legLB2.setRotationPoint(8.0F, -0.2F, -0.5F);
        legLB2.addBox(0.0F, -1.5F, -1.5F, 8, 3, 3, 0.0F);
        setRotation(legLB2, -0.3490658503988659F, -0.8726646259971648F, 0.3490658503988659F);
        bumR2 = new ModelRenderer(this, 114, 117);
        bumR2.setRotationPoint(0.0F, 15.0F, 0.0F);
        bumR2.addBox(-6.5F, -3.0F, 21.5F, 6, 4, 1, 0.0F);
        setRotation(bumR2, 0.0F, -0.0F, -0.17453292012214658F);
        headL = new ModelRenderer(this, 29, 35);
        headL.setRotationPoint(0.0F, 0.0F, 0.0F);
        headL.addBox(1.5F, -2.5F, -8.0F, 6, 6, 7, 0.0F);
        setRotation(headL, 0.0F, 0.0F, 0.17453292519943295F);
        mandibleR1 = new ModelRenderer(this, 79, 25);
        mandibleR1.setRotationPoint(-4.0F, 1.0F, -7.5F);
        mandibleR1.addBox(-1.5F, -1.0F, -6.0F, 3, 2, 7, 0.0F);
        setRotation(mandibleR1, 0.17453292519943295F, 0.7853981633974483F, 0.17453292519943295F);
        legLF2 = new ModelRenderer(this, 0, 5);
        legLF2.setRotationPoint(7.0F, 0.0F, 0.5F);
        legLF2.addBox(0.0F, -1.5F, -1.5F, 8, 3, 3, 0.0F);
        setRotation(legLF2, 0.5235987755982988F, 1.0471975511965976F, 0.3490658503988659F);
        legLF2.addChild(legLF3);
        legRM2.addChild(legRM3);
        headL.addChild(eyeL);
        legLM2.addChild(legLM3);
        legRM1.addChild(legRM2);
        mandibleR5.addChild(mandibleR6);
        legLB2.addChild(legLB3);
        mandibleL2.addChild(mandibleL3);
        headMid.addChild(headR);
        mandibleL3.addChild(mandibleL4);
        mandibleL1.addChild(mandibleL2);
        mandibleL5.addChild(mandibleL6);
        mandibleR6.addChild(mandibleR7);
        legRF1.addChild(legRF2);
        mandibleR2.addChild(mandibleR3);
        mandibleL4.addChild(mandibleL5);
        headR.addChild(eyeR);
        legRF2.addChild(legRF3);
        mandibleR1.addChild(mandibleR2);
        legLM1.addChild(legLM2);
        mandibleR3.addChild(mandibleR4);
        mandibleR4.addChild(mandibleR5);
        mandibleL6.addChild(mandibleL7);
        headL.addChild(mandibleL1);
        legRB2.addChild(legRB3);
        legRB1.addChild(legRB2);
        legLB1.addChild(legLB2);
        headMid.addChild(headL);
        headR.addChild(mandibleR1);
        legLF1.addChild(legLF2);
    }

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.render(entity, limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		setRotationAngles(limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		GL11.glPushMatrix();
		GL11.glTranslated(0F, 0F, 0.375F);
        abdomenRTop.render(unitPixel);
        legLB1.render(unitPixel);
        abdomenBellyBack.render(unitPixel);
        abdomenBelly.render(unitPixel);
        abdomenL.render(unitPixel);
        thoraxBack.render(unitPixel);
        abdomenRMid.render(unitPixel);
        legLF1.render(unitPixel);
        legRM1.render(unitPixel);
        legLM1.render(unitPixel);
        bumL2.render(unitPixel);
        headMid.render(unitPixel);
        thoraxMain.render(unitPixel);
        legRF1.render(unitPixel);
        abdomenLMid.render(unitPixel);
        legRB1.render(unitPixel);
        abdomenLTopRear.render(unitPixel);
        bumL.render(unitPixel);
        spine.render(unitPixel);
        bumR.render(unitPixel);
        abdomenLTop.render(unitPixel);
        abdomenR.render(unitPixel);
        abdomenRTopRear.render(unitPixel);
        bumR2.render(unitPixel);
		GL11.glPopMatrix();
    }

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float limbSwing, float limbSwingAngle, float partialTickTime) {
		EntityStagBeetle beetle = (EntityStagBeetle)entity;
		float legMovement = MathHelper.cos(limbSwing * 0.75F) * 0.3F * limbSwingAngle;
		float correction = 0.3490659F;
		int prevAnimation = beetle.prevAnimation;
		float interAnimationTicks = beetle.getDataWatcher().getWatchableObjectInt(30) + (beetle.getDataWatcher().getWatchableObjectInt(30) - prevAnimation) * partialTickTime;
		legLB1.rotateAngleY = legMovement - correction;
		legLM1.rotateAngleY = -legMovement;
		legLF1.rotateAngleY = legMovement + correction;
		legRB1.rotateAngleY = -legMovement - correction;
		legRM1.rotateAngleY = legMovement;
		legRF1.rotateAngleY = legMovement + 3.142F - correction;
		if(beetle.getDataWatcher().getWatchableObjectByte(29) == 1) {
			if(beetle.getDataWatcher().getWatchableObjectByte(28) == 0)
				headMid.rotateAngleX = -0.175F + 0.085F * interAnimationTicks;
			else if(beetle.getDataWatcher().getWatchableObjectByte(28) == 2)
				headMid.rotateAngleX = -0.175F - 0.17F * interAnimationTicks;
			else if(beetle.getDataWatcher().getWatchableObjectByte(28) == 1)
				if(headMid.rotateAngleX > -0.175F)
					headMid.rotateAngleX -= 0.17F * interAnimationTicks;
				else if(headMid.rotateAngleX < -0.175F)
					headMid.rotateAngleX += 0.085F * interAnimationTicks;
				else
					headMid.rotateAngleX = -0.175F;
			if(interAnimationTicks < 5) {
				mandibleR1.rotateAngleY = + 1.2F + (0.025F * interAnimationTicks);
				mandibleL1.rotateAngleY = - 1.2F - (0.025F * interAnimationTicks);
			}
			else {
				mandibleR1.rotateAngleY = 1.45F - (0.175F * interAnimationTicks);
				mandibleL1.rotateAngleY = - 1.45F + (0.175F * interAnimationTicks);
			}
		}
		else {
			mandibleR1.rotateAngleY = legMovement * 0.2F * limbSwingAngle + 0.8F;
			mandibleL1.rotateAngleY = -legMovement * 0.2F * limbSwingAngle - 0.8F;
			headMid.rotateAngleX = -0.175F;
		}
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		

	}
}
