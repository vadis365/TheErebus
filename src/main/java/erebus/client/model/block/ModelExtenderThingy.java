package erebus.client.model.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.tileentity.TileEntityExtenderThingy;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class ModelExtenderThingy extends ModelBase {
	ModelRenderer BambooStep1;
	ModelRenderer BambooStep2;
	ModelRenderer SupportR1;
	ModelRenderer SupportL1;
	ModelRenderer String1;
	ModelRenderer String2;
	ModelRenderer String3;
	ModelRenderer String4;
	ModelRenderer StringR1;
	ModelRenderer StringL1;
	ModelRenderer Polebit;
	ModelRenderer Main;

	public ModelExtenderThingy() {
		textureWidth = 64;
		textureHeight = 64;

		BambooStep1 = new ModelRenderer(this, 25, 31);
		BambooStep1.addBox(0F, 0F, 0F, 14, 3, 2);
		BambooStep1.setRotationPoint(-7F, 22F, -4.5F);
		setRotation(BambooStep1, -1.570796F, 0F, 0F);
		BambooStep2 = new ModelRenderer(this, 25, 31);
		BambooStep2.addBox(0F, 0F, 0F, 14, 3, 2);
		BambooStep2.setRotationPoint(-7F, 22F, -0.5F);
		setRotation(BambooStep2, -1.570796F, 0F, 0F);
		SupportR1 = new ModelRenderer(this, 0, 0);
		SupportR1.addBox(0F, 0F, 0F, 14, 2, 3);
		SupportR1.setRotationPoint(-8F, 24F, -7.5F);
		setRotation(SupportR1, 0F, 0F, -1.570796F);
		SupportL1 = new ModelRenderer(this, 0, 0);
		SupportL1.addBox(0F, 0F, 0F, 14, 2, 3);
		SupportL1.setRotationPoint(6F, 24F, -7.5F);
		setRotation(SupportL1, 0F, 0F, -1.570796F);
		String1 = new ModelRenderer(this, 0, 6);
		String1.addBox(0F, 9F, 0F, 1, 7, 1);
		String1.setRotationPoint(5F, 22.5F, 8F);
		setRotation(String1, -1.570796F, 0F, 0F);
		String2 = new ModelRenderer(this, 0, 6);
		String2.addBox(0F, 9F, 0F, 1, 7, 1);
		String2.setRotationPoint(-6F, 22.5F, 8F);
		setRotation(String2, -1.570796F, 0F, 0F);
		String3 = new ModelRenderer(this, 5, 6);
		String3.addBox(0F, 0F, 0F, 1, 7, 1);
		String3.setRotationPoint(6.6F, 12F, -8F);
		setRotation(String3, 1.570796F, 0F, 0F);
		String4 = new ModelRenderer(this, 5, 6);
		String4.addBox(0F, 0F, 0F, 1, 7, 1);
		String4.setRotationPoint(-7.4F, 12F, -8F);
		setRotation(String4, 1.570796F, 0F, 0F);
		StringR1 = new ModelRenderer(this, 0, 6);
		StringR1.addBox(0F, 0.2F, 0F, 1, 12, 1);
		StringR1.setRotationPoint(-7.4F, 11F, -2.5F);
		setRotation(StringR1, 0F, 0F, -0.122173F);
		StringL1 = new ModelRenderer(this, 0, 6);
		StringL1.addBox(0F, 0.1F, 0F, 1, 12, 1);
		StringL1.setRotationPoint(6.6F, 11F, -2.5F);
		setRotation(StringL1, 0F, 0F, 0.122173F);
		Polebit = new ModelRenderer(this, 0, 31);
		Polebit.addBox(-3.5F, 8F, -3.5F, 7, 14, 7);
		Polebit.setRotationPoint(0F, 0F, 0F);
		setRotation(Polebit, 0F, 0F, 0F);
		Main = new ModelRenderer(this, 10, 7);
		Main.addBox(-8F, 10F, -1F, 16, 14, 9);
		Main.setRotationPoint(0F, 0F, 0F);
		setRotation(Main, 0F, 0F, 0F);
	}

	public void render(TileEntityExtenderThingy tile) {
		BambooStep1.render(0.0625F);
		BambooStep2.render(0.0625F);
		SupportR1.render(0.0625F);
		SupportL1.render(0.0625F);
		String1.render(0.0625F);
		String2.render(0.0625F);
		String3.render(0.0625F);
		String4.render(0.0625F);
		StringR1.render(0.0625F);
		StringL1.render(0.0625F);
		Polebit.render(0.0625F);
		Main.render(0.0625F);
	}

	public void render2(TileEntityExtenderThingy tile) {
		Polebit.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
