package erebus.client.model.block;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.tileentity.TileEntityErebusAltarLightning;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class ModelAltarLightning extends ModelBase {

	ModelRenderer Mid;
	ModelRenderer Top;
	ModelRenderer Bot;
	ModelRenderer SmallBox;
	ModelRenderer ElectrodeF1;
	ModelRenderer ElectrodeF2;
	ModelRenderer ElectrodeL1;
	ModelRenderer ElectrodeL2;
	ModelRenderer ElectrodeB1;
	ModelRenderer ElectrodeB2;
	ModelRenderer ElectrodeR1;
	ModelRenderer ElectrodeR2;
	ModelRenderer Sparks;

	public ModelAltarLightning() {
		textureWidth = 256;
		textureHeight = 64;

		Mid = new ModelRenderer(this, 130, 0);
		Mid.addBox(-12F, 0F, -12F, 24, 24, 24);
		Mid.setRotationPoint(0F, -4F, 0F);
		setRotation(Mid, 0F, 0F, 0F);
		Top = new ModelRenderer(this, 0, 0);
		Top.addBox(-16F, 0F, -16F, 32, 4, 32);
		Top.setRotationPoint(0F, -8F, 0F);
		setRotation(Top, 0F, 0F, 0F);
		Bot = new ModelRenderer(this, 0, 0);
		Bot.addBox(-16F, 0F, -16F, 32, 4, 32);
		Bot.setRotationPoint(0F, 20F, 0F);
		setRotation(Bot, 0F, 0F, 0F);
		SmallBox = new ModelRenderer(this, 0, 36);
		SmallBox.addBox(-7F, 0F, -7F, 14, 14, 14);
		SmallBox.setRotationPoint(0F, -22F, 0F);
		setRotation(SmallBox, 0F, 0.7853982F, 0F);
		ElectrodeF1 = new ModelRenderer(this, 58, 38);
		ElectrodeF1.addBox(-2F, -9F, -11F, 4, 14, 4);
		ElectrodeF1.setRotationPoint(0F, -22F, 0F);
		setRotation(ElectrodeF1, 0.5235988F, 0F, 0F);
		ElectrodeF2 = new ModelRenderer(this, 78, 38);
		ElectrodeF2.addBox(-1F, 1F, -11F, 2, 10, 2);
		ElectrodeF2.setRotationPoint(0F, -22F, 0F);
		setRotation(ElectrodeF2, -1.047198F, 0F, 0F);
		ElectrodeL1 = new ModelRenderer(this, 58, 38);
		ElectrodeL1.addBox(-2F, -9F, -11F, 4, 14, 4);
		ElectrodeL1.setRotationPoint(0F, -22F, 0F);
		setRotation(ElectrodeL1, 0.5235988F, -1.570796F, 0F);
		ElectrodeL2 = new ModelRenderer(this, 78, 38);
		ElectrodeL2.addBox(-1F, 1F, -11F, 2, 10, 2);
		ElectrodeL2.setRotationPoint(0F, -22F, 0F);
		setRotation(ElectrodeL2, -1.047198F, -1.570796F, 0F);
		ElectrodeB1 = new ModelRenderer(this, 58, 38);
		ElectrodeB1.addBox(-2F, -9F, -11F, 4, 14, 4);
		ElectrodeB1.setRotationPoint(0F, -22F, 0F);
		setRotation(ElectrodeB1, 0.5235988F, 3.141593F, 0F);
		ElectrodeB2 = new ModelRenderer(this, 78, 38);
		ElectrodeB2.addBox(-1F, 1F, -11F, 2, 10, 2);
		ElectrodeB2.setRotationPoint(0F, -22F, 0F);
		setRotation(ElectrodeB2, -1.047198F, 3.141593F, 0F);
		ElectrodeR1 = new ModelRenderer(this, 58, 38);
		ElectrodeR1.addBox(-2F, -9F, -11F, 4, 14, 4);
		ElectrodeR1.setRotationPoint(0F, -22F, 0F);
		setRotation(ElectrodeR1, 0.5235988F, 1.570796F, 0F);
		ElectrodeR2 = new ModelRenderer(this, 78, 38);
		ElectrodeR2.addBox(-1F, 1F, -11F, 2, 10, 2);
		ElectrodeR2.setRotationPoint(0F, -22F, 0F);
		setRotation(ElectrodeR2, -1.047198F, 1.570796F, 0F);
		Sparks = new ModelRenderer(this, 90, 166);
		Sparks.addBox(-5F, -11F, -5F, 10, 6, 10);
		Sparks.setRotationPoint(0F, -22F, 0F);
		setRotation(Sparks, 0F, 0.7853982F, 0F);
	}

	public void render(TileEntityErebusAltarLightning tile) {
		float x = tile.animationTicks;
		GL11.glPushMatrix();
		GL11.glScalef(0.04F * x, 0.04F * x, 0.04F * x);
		SmallBox.render(0.0625F);
		Sparks.render(0.0625F);
		ElectrodeF1.render(0.0625F);
		ElectrodeF2.render(0.0625F);
		ElectrodeR1.render(0.0625F);
		ElectrodeR2.render(0.0625F);
		ElectrodeB1.render(0.0625F);
		ElectrodeB2.render(0.0625F);
		ElectrodeL1.render(0.0625F);
		ElectrodeL2.render(0.0625F);
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

	public void setRotationAngles() {
	}

}
