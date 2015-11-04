package erebus.client.model.block;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.tileentity.TileEntityErebusAltarXP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelAltarXP extends ModelBase {
	ModelRenderer GlassTop;
	ModelRenderer GlassBot;
	ModelRenderer GlassMid;
	ModelRenderer BPlate;
	ModelRenderer TPlate;
	ModelRenderer RFSupport;
	ModelRenderer RBSupport;
	ModelRenderer LFSupport;
	ModelRenderer LBSupport;
	ModelRenderer Top;
	ModelRenderer Mid;
	ModelRenderer Bot;

	public ModelAltarXP() {
		textureWidth = 256;
		textureHeight = 64;

		GlassTop = new ModelRenderer(this, 0, 37);
		GlassTop.addBox(-3.5F, 8F, -3.5F, 7, 6, 7);
		GlassTop.setRotationPoint(0F, -32F, 0F);
		setRotation(GlassTop, 0F, 0F, 0F);
		GlassBot = new ModelRenderer(this, 0, 51);
		GlassBot.addBox(-3.5F, 16F, -3.5F, 7, 6, 7);
		GlassBot.setRotationPoint(0F, -32F, 0F);
		setRotation(GlassBot, 0F, 0F, 0F);
		GlassMid = new ModelRenderer(this, 29, 37);
		GlassMid.addBox(-1.5F, 14F, -1.5F, 3, 2, 3);
		GlassMid.setRotationPoint(0F, -32F, 0F);
		setRotation(GlassMid, 0F, 0F, 0F);
		BPlate = new ModelRenderer(this, 42, 37);
		BPlate.addBox(-7F, 22F, -7F, 14, 2, 14);
		BPlate.setRotationPoint(0F, -32F, 0F);
		setRotation(BPlate, 0F, 0F, 0F);
		TPlate = new ModelRenderer(this, 42, 37);
		TPlate.addBox(-7F, 6F, -7F, 14, 2, 14);
		TPlate.setRotationPoint(0F, -32F, 0F);
		setRotation(TPlate, 0F, 0F, 0F);
		RFSupport = new ModelRenderer(this, 99, 37);
		RFSupport.addBox(-6F, 5F, -6F, 2, 17, 2);
		RFSupport.setRotationPoint(0F, -32F, 0F);
		setRotation(RFSupport, 0F, 0F, 0F);
		RBSupport = new ModelRenderer(this, 99, 37);
		RBSupport.addBox(-6F, 5F, 4F, 2, 17, 2);
		RBSupport.setRotationPoint(0F, -32F, 0F);
		setRotation(RBSupport, 0F, 0F, 0F);
		LFSupport = new ModelRenderer(this, 99, 37);
		LFSupport.addBox(4F, 5F, -6F, 2, 17, 2);
		LFSupport.setRotationPoint(0F, -32F, 0F);
		setRotation(LFSupport, 0F, 0F, 0F);
		LBSupport = new ModelRenderer(this, 99, 37);
		LBSupport.addBox(4F, 5F, 4F, 2, 17, 2);
		LBSupport.setRotationPoint(0F, -32F, 0F);
		setRotation(LBSupport, 0F, 0F, 0F);
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

	public void render(TileEntityErebusAltarXP tile) {
		float x = tile.animationTicks;
		GL11.glPushMatrix();
		GL11.glScalef(0.04F * x, 0.04F * x, 0.04F * x);
		GlassTop.render(0.0625F);
		GlassBot.render(0.0625F);
		GlassMid.render(0.0625F);
		BPlate.render(0.0625F);
		TPlate.render(0.0625F);
		RFSupport.render(0.0625F);
		RBSupport.render(0.0625F);
		LFSupport.render(0.0625F);
		LBSupport.render(0.0625F);
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
