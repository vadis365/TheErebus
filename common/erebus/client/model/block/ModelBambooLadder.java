package erebus.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelBambooLadder extends ModelBase {
	ModelRenderer BambooStep1;
	ModelRenderer BambooStep2;
	ModelRenderer BambooStep3;
	ModelRenderer String1;
	ModelRenderer String2;

	public ModelBambooLadder() {
		textureWidth = 64;
		textureHeight = 32;

		BambooStep1 = new ModelRenderer(this, 0, 0);
		BambooStep1.addBox(0F, 0F, 0F, 14, 3, 3);
		BambooStep1.setRotationPoint(-7F, 19.5F, 5F);
		BambooStep1.setTextureSize(64, 32);
		BambooStep1.mirror = true;
		setRotation(BambooStep1, 0F, 0F, 0F);
		BambooStep2 = new ModelRenderer(this, 0, 0);
		BambooStep2.addBox(0F, 0F, 0F, 14, 3, 3);
		BambooStep2.setRotationPoint(-7F, 14.5F, 5F);
		BambooStep2.setTextureSize(64, 32);
		BambooStep2.mirror = true;
		setRotation(BambooStep2, 0F, 0F, 0F);
		BambooStep3 = new ModelRenderer(this, 0, 0);
		BambooStep3.addBox(0F, 0F, 0F, 14, 3, 3);
		BambooStep3.setRotationPoint(-7F, 9.5F, 5F);
		BambooStep3.setTextureSize(64, 32);
		BambooStep3.mirror = true;
		setRotation(BambooStep3, 0F, 0F, 0F);
		String1 = new ModelRenderer(this, 0, 6);
		String1.addBox(0F, 0F, 0F, 1, 16, 1);
		String1.setRotationPoint(4F, 8F, 6F);
		String1.setTextureSize(64, 32);
		String1.mirror = true;
		setRotation(String1, 0F, 0F, 0F);
		String2 = new ModelRenderer(this, 0, 6);
		String2.addBox(0F, 0F, 0F, 1, 16, 1);
		String2.setRotationPoint(-5F, 8F, 6F);
		String2.setTextureSize(64, 32);
		String2.mirror = true;
		setRotation(String2, 0F, 0F, 0F);
	}

	public void render() {
		BambooStep1.render(0.0625F);
		BambooStep2.render(0.0625F);
		BambooStep3.render(0.0625F);
		String1.render(0.0625F);
		String2.render(0.0625F);;
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
