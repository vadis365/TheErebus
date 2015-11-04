package erebus.client.model.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class ModelOfferingAltar extends ModelBase {
	public ModelRenderer[] boxes = new ModelRenderer[8];

	public ModelOfferingAltar() {
		textureWidth = 128;
		textureHeight = 64;

		boxes[0] = new ModelRenderer(this, 0, 43);
		boxes[0].addBox(-7.5F, 0F, -7.5F, 15, 2, 15);
		boxes[0].setRotationPoint(0F, 22F, 0F);
		boxes[1] = new ModelRenderer(this, 0, 0);
		boxes[1].addBox(-6F, 0F, -6F, 12, 10, 12);
		boxes[1].setRotationPoint(0F, 12F, 0F);
		boxes[2] = new ModelRenderer(this, 0, 24);
		boxes[2].addBox(-7.5F, 0F, -7.5F, 15, 2, 15);
		boxes[2].setRotationPoint(0F, 10F, 0F);
		boxes[3] = new ModelRenderer(this, 84, 4);
		boxes[3].addBox(-7.5F, 0F, 0F, 15, 1, 2);
		boxes[3].setRotationPoint(0F, 9F, -7.5F);
		boxes[4] = new ModelRenderer(this, 84, 0);
		boxes[4].addBox(-7.5F, 0F, 0F, 15, 1, 2);
		boxes[4].setRotationPoint(0F, 9F, 5.5F);
		boxes[5] = new ModelRenderer(this, 64, 12);
		boxes[5].addBox(0F, 0F, -5.5F, 2, 1, 11);
		boxes[5].setRotationPoint(-7.5F, 9F, 0F);
		boxes[6] = new ModelRenderer(this, 64, 0);
		boxes[6].addBox(0F, 0F, -5.5F, 2, 1, 11);
		boxes[6].setRotationPoint(5.5F, 9F, 0F);
		boxes[7] = new ModelRenderer(this, 60, 33);
		boxes[7].addBox(-3.5F, 0F, -3.5F, 7, 1, 7);
		boxes[7].setRotationPoint(0F, 9F, 0F);
	}

	public void render() {
		for (ModelRenderer box : boxes)
			box.render(0.0625f);
	}
}
