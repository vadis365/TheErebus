package erebus.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSiloIntake extends ModelBase {

	ModelRenderer conduitMain;
	ModelRenderer conduitDecoTop;
	ModelRenderer conduitDecoMid;
	ModelRenderer conduitDecoBottom;
	ModelRenderer funnelTop;
	ModelRenderer funnelMid;
	ModelRenderer funnelRimLeft;
	ModelRenderer funnelRimRight;
	ModelRenderer funnelRimFront;
	ModelRenderer funnelRimBack;

	public ModelSiloIntake() {
		textureWidth = 64;
		textureHeight = 32;

		conduitMain = new ModelRenderer(this, 0, 0);
		conduitMain.addBox(-3F, -7F, -3F, 6, 10, 6);
		conduitMain.setRotationPoint(0F, 0F, 0F);
		setRotation(conduitMain, 0F, 0F, 0F);
		conduitDecoTop = new ModelRenderer(this, 25, 0);
		conduitDecoTop.addBox(-3.5F, -9F, -3.5F, 7, 2, 7);
		conduitDecoTop.setRotationPoint(0F, 0F, 0F);
		setRotation(conduitDecoTop, 0F, 0F, 0F);
		conduitDecoMid = new ModelRenderer(this, 25, 0);
		conduitDecoMid.addBox(-3.5F, -5F, -3.5F, 7, 2, 7);
		conduitDecoMid.setRotationPoint(0F, 0F, 0F);
		setRotation(conduitDecoMid, 0F, 0F, 0F);
		conduitDecoBottom = new ModelRenderer(this, 25, 0);
		conduitDecoBottom.addBox(-3.5F, -1F, -3.5F, 7, 2, 7);
		conduitDecoBottom.setRotationPoint(0F, 0F, 0F);
		setRotation(conduitDecoBottom, 0F, 0F, 0F);
		funnelTop = new ModelRenderer(this, 25, 10);
		funnelTop.addBox(-4F, 3F, -4F, 8, 1, 8);
		funnelTop.setRotationPoint(0F, 0F, 0F);
		setRotation(funnelTop, 0F, 0F, 0F);
		funnelMid = new ModelRenderer(this, 23, 20);
		funnelMid.addBox(-5F, 4F, -5F, 10, 1, 10);
		funnelMid.setRotationPoint(0F, 0F, 0F);
		setRotation(funnelMid, 0F, 0F, 0F);
		funnelRimLeft = new ModelRenderer(this, 0, 16);
		funnelRimLeft.addBox(5F, 5F, -5F, 1, 2, 10);
		funnelRimLeft.setRotationPoint(0F, 0F, 0F);
		setRotation(funnelRimLeft, 0F, 0F, 0F);
		funnelRimRight = new ModelRenderer(this, 0, 16);
		funnelRimRight.addBox(-6F, 5F, -5F, 1, 2, 10);
		funnelRimRight.setRotationPoint(0F, 0F, 0F);
		setRotation(funnelRimRight, 0F, 0F, 0F);
		funnelRimFront = new ModelRenderer(this, 0, 29);
		funnelRimFront.addBox(-5F, 5F, -6F, 10, 2, 1);
		funnelRimFront.setRotationPoint(0F, 0F, 0F);
		setRotation(funnelRimFront, 0F, 0F, 0F);
		funnelRimBack = new ModelRenderer(this, 0, 29);
		funnelRimBack.addBox(-5F, 5F, 5F, 10, 2, 1);
		funnelRimBack.setRotationPoint(0F, 0F, 0F);
		setRotation(funnelRimBack, 0F, 0F, 0F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void renderModel() {
		conduitMain.render(0.0625F);
		conduitDecoTop.render(0.0625F);
		conduitDecoMid.render(0.0625F);
		conduitDecoBottom.render(0.0625F);
		funnelTop.render(0.0625F);
		funnelMid.render(0.0625F);
		funnelRimLeft.render(0.0625F);
		funnelRimRight.render(0.0625F);
		funnelRimFront.render(0.0625F);
		funnelRimBack.render(0.0625F);
	}
}
