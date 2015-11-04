package erebus.client.model.block;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.tileentity.TileEntityErebusAltarRepair;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelAltarRepair extends ModelBase {
	ModelRenderer AnvilFrontFoot;
	ModelRenderer AnvilRearFoot;
	ModelRenderer AnvilBase;
	ModelRenderer AnvilWaist;
	ModelRenderer AnvilFace;
	ModelRenderer AnvilTable;
	ModelRenderer AnvilHorn;
	ModelRenderer AnvilHeel;
	ModelRenderer Top;
	ModelRenderer Mid;
	ModelRenderer Bot;

	public ModelAltarRepair() {
		textureWidth = 256;
		textureHeight = 64;

		AnvilFrontFoot = new ModelRenderer(this, 0, 37);
		AnvilFrontFoot.addBox(-6F, 20F, -6F, 4, 4, 12);
		AnvilFrontFoot.setRotationPoint(0F, -32F, 0F);
		setRotation(AnvilFrontFoot, 0F, 0F, 0F);
		AnvilRearFoot = new ModelRenderer(this, 0, 37);
		AnvilRearFoot.addBox(2F, 20F, -6F, 4, 4, 12);
		AnvilRearFoot.setRotationPoint(0F, -32F, 0F);
		setRotation(AnvilRearFoot, 0F, 0F, 0F);
		AnvilBase = new ModelRenderer(this, 33, 37);
		AnvilBase.addBox(-5F, 19F, -4F, 10, 5, 8);
		AnvilBase.setRotationPoint(0F, -32F, 0F);
		setRotation(AnvilBase, 0F, 0F, 0F);
		AnvilWaist = new ModelRenderer(this, 0, 54);
		AnvilWaist.addBox(-4F, 15F, -2F, 8, 4, 4);
		AnvilWaist.setRotationPoint(0F, -32F, 0F);
		setRotation(AnvilWaist, 0F, 0F, 0F);
		AnvilFace = new ModelRenderer(this, 70, 37);
		AnvilFace.addBox(-7F, 8F, -5F, 14, 7, 10);
		AnvilFace.setRotationPoint(0F, -32F, 0F);
		setRotation(AnvilFace, 0F, 0F, 0F);
		AnvilTable = new ModelRenderer(this, 33, 51);
		AnvilTable.addBox(-10F, 9F, -3.5F, 3, 5, 7);
		AnvilTable.setRotationPoint(0F, -32F, 0F);
		setRotation(AnvilTable, 0F, 0F, 0F);
		AnvilHorn = new ModelRenderer(this, 55, 51);
		AnvilHorn.addBox(-13F, 9F, -2F, 3, 3, 4);
		AnvilHorn.setRotationPoint(0F, -32F, 0F);
		setRotation(AnvilHorn, 0F, 0F, 0F);
		AnvilHeel = new ModelRenderer(this, 109, 45);
		AnvilHeel.addBox(7F, 8F, -5F, 3, 4, 10);
		AnvilHeel.setRotationPoint(0F, -32F, 0F);
		setRotation(AnvilHeel, 0F, 0F, 0F);
		Top = new ModelRenderer(this, 0, 0);
		Top.addBox(-16F, 0F, -16F, 32, 4, 32);
		Top.setRotationPoint(0F, -8F, 0F);
		setRotation(Top, 0F, 0F, 0F);
		Mid = new ModelRenderer(this, 130, 0);
		Mid.addBox(-12F, 0F, -12F, 24, 24, 24);
		Mid.setRotationPoint(0F, -4F, 0F);
		setRotation(Mid, 0F, 0F, 0F);
		Bot = new ModelRenderer(this, 0, 0);
		Bot.addBox(-16F, 0F, -16F, 32, 4, 32);
		Bot.setRotationPoint(0F, 20F, 0F);
		setRotation(Bot, 0F, 0F, 0F);
	}

	public void render(TileEntityErebusAltarRepair tile) {

		float x = tile.animationTicks;
		GL11.glPushMatrix();
		GL11.glScalef(0.04F * x, 0.04F * x, 0.04F * x);
		;
		AnvilFrontFoot.render(0.0625F);
		AnvilRearFoot.render(0.0625F);
		AnvilBase.render(0.0625F);
		AnvilWaist.render(0.0625F);
		AnvilFace.render(0.0625F);
		AnvilTable.render(0.0625F);
		AnvilHorn.render(0.0625F);
		AnvilHeel.render(0.0625F);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glRotatef(-x * 7.2F, 0F, 1F, 0F);
		Mid.render(0.0625F);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glRotatef(x * 7.2F, 0F, 1F, 0F);
		Top.render(0.0625F);
		Bot.render(0.0625F);
		GL11.glPopMatrix();
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
	}

}
