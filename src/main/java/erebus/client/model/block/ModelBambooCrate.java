package erebus.client.model.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class ModelBambooCrate extends ModelBase {

	ModelRenderer BottomBamb1;
	ModelRenderer BottomBamb2;
	ModelRenderer BottomBamb3;
	ModelRenderer BottomBamb4;
	ModelRenderer PillarBamb1;
	ModelRenderer PillarBamb2;
	ModelRenderer PillarBamb3;
	ModelRenderer PillarBamb4;
	ModelRenderer TopBamb1;
	ModelRenderer TopBamb2;
	ModelRenderer TopBamb3;
	ModelRenderer TopBamb4;
	ModelRenderer Core;

	public ModelBambooCrate() {
		textureWidth = 128;
		textureHeight = 64;

		BottomBamb1 = new ModelRenderer(this, 0, 8);
		BottomBamb1.addBox(-2F, 0F, -8F, 4, 4, 16);
		BottomBamb1.setRotationPoint(-5F, 19F, 0F);
		setRotation(BottomBamb1, 0F, 0F, 0F);
		BottomBamb2 = new ModelRenderer(this, 0, 8);
		BottomBamb2.addBox(-2F, 0F, -8F, 4, 4, 16);
		BottomBamb2.setRotationPoint(5F, 19F, 0F);
		setRotation(BottomBamb2, 0F, 0F, 0F);
		BottomBamb3 = new ModelRenderer(this, 0, 0);
		BottomBamb3.addBox(-8F, 0F, -2F, 16, 4, 4);
		BottomBamb3.setRotationPoint(0F, 19F, 5F);
		setRotation(BottomBamb3, 0F, 0F, 0F);
		BottomBamb4 = new ModelRenderer(this, 0, 0);
		BottomBamb4.addBox(-8F, 0F, -2F, 16, 4, 4);
		BottomBamb4.setRotationPoint(0F, 19F, -5F);
		setRotation(BottomBamb4, 0F, 0F, 0F);
		PillarBamb1 = new ModelRenderer(this, 0, 28);
		PillarBamb1.addBox(-2F, 0F, -2F, 4, 16, 4);
		PillarBamb1.setRotationPoint(-5F, 8F, -5F);
		setRotation(PillarBamb1, 0F, 0F, 0F);
		PillarBamb2 = new ModelRenderer(this, 0, 28);
		PillarBamb2.addBox(-2F, 0F, -2F, 4, 16, 4);
		PillarBamb2.setRotationPoint(5F, 8F, -5F);
		setRotation(PillarBamb2, 0F, 0F, 0F);
		PillarBamb3 = new ModelRenderer(this, 0, 28);
		PillarBamb3.addBox(-2F, 0F, -2F, 4, 16, 4);
		PillarBamb3.setRotationPoint(5F, 8F, 5F);
		setRotation(PillarBamb3, 0F, 0F, 0F);
		PillarBamb4 = new ModelRenderer(this, 0, 28);
		PillarBamb4.addBox(-2F, 0F, -2F, 4, 16, 4);
		PillarBamb4.setRotationPoint(-5F, 8F, 5F);
		setRotation(PillarBamb4, 0F, 0F, 0F);
		TopBamb1 = new ModelRenderer(this, 0, 8);
		TopBamb1.addBox(-2F, -2F, 0F, 4, 4, 16);
		TopBamb1.setRotationPoint(5F, 11F, -8F);
		setRotation(TopBamb1, 0F, 0F, 0F);
		TopBamb2 = new ModelRenderer(this, 0, 8);
		TopBamb2.addBox(-2F, -2F, 0F, 4, 4, 16);
		TopBamb2.setRotationPoint(-5F, 11F, -8F);
		setRotation(TopBamb2, 0F, 0F, 0F);
		TopBamb3 = new ModelRenderer(this, 0, 0);
		TopBamb3.addBox(0F, -2F, -2F, 16, 4, 4);
		TopBamb3.setRotationPoint(-8F, 11F, -5F);
		setRotation(TopBamb3, 0F, 0F, 0F);
		TopBamb4 = new ModelRenderer(this, 0, 0);
		TopBamb4.addBox(0F, -2F, -2F, 16, 4, 4);
		TopBamb4.setRotationPoint(-8F, 11F, 5F);
		setRotation(TopBamb4, 0F, 0F, 0F);
		Core = new ModelRenderer(this, 40, 0);
		Core.addBox(0F, 0F, 0F, 13, 13, 13);
		Core.setRotationPoint(-6.5F, 9.5F, -6.5F);
		setRotation(Core, 0F, 0F, 0F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void renderModel() {
		BottomBamb1.render(0.0625F);
		BottomBamb2.render(0.0625F);
		BottomBamb3.render(0.0625F);
		BottomBamb4.render(0.0625F);
		PillarBamb1.render(0.0625F);
		PillarBamb2.render(0.0625F);
		PillarBamb3.render(0.0625F);
		PillarBamb4.render(0.0625F);
		TopBamb1.render(0.0625F);
		TopBamb2.render(0.0625F);
		TopBamb3.render(0.0625F);
		TopBamb4.render(0.0625F);
		Core.render(0.0625F);
	}
}
