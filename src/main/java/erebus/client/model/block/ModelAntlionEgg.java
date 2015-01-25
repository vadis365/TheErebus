package erebus.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelAntlionEgg extends ModelBase {
	ModelRenderer top;
	ModelRenderer part1;
	ModelRenderer part2;
	ModelRenderer part3;
	ModelRenderer main;

	public ModelAntlionEgg() {
		textureWidth = 128;
		textureHeight = 64;

		top = new ModelRenderer(this, 0, 57);
		top.addBox(-1.5F, 0F, -1.5F, 3, 1, 3);
		top.setRotationPoint(0F, 8F, 0F);
		setRotation(top, 0F, 0F, 0F);
		part1 = new ModelRenderer(this, 0, 22);
		part1.addBox(-3.5F, 0F, -3.5F, 7, 15, 7);
		part1.setRotationPoint(0F, 9F, 0F);
		setRotation(part1, 0F, 0.7853982F, 0F);
		part2 = new ModelRenderer(this, 92, 0);
		part2.addBox(-4F, -3F, -4F, 8, 13, 8);
		part2.setRotationPoint(0F, 13F, 0F);
		setRotation(part2, 0F, 0F, 0F);
		part3 = new ModelRenderer(this, 50, 0);
		part3.addBox(-5F, 0F, -5F, 10, 10, 10);
		part3.setRotationPoint(0F, 12F, 0F);
		setRotation(part3, 0F, 0.7853982F, 0F);
		main = new ModelRenderer(this, 0, 0);
		main.addBox(-6F, 0F, -6F, 12, 7, 12);
		main.setRotationPoint(0F, 14F, 0F);
		setRotation(main, 0F, 0F, 0F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void renderAll() {
		top.render(0.0625F);
		part1.render(0.0625F);
		part2.render(0.0625F);
		part3.render(0.0625F);;
		main.render(0.0625F);
	}

}
